<template>
  <div style="font-size: 12px; line-height: 64px; display: flex; justify-content: space-between; width: 102%; background: linear-gradient(to right, #000 0%,#222 20%,#111 50%,#222 70%,#000 99%,#000 100%); color: #ccc;">
    <div style="margin-left: 3px; flex: 1; font-size: 20px">
      <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
    </div>
    <h1 class="welcome1" @click="refreshPage">
      Film
    </h1>
    <h1 :class="['welcome2', isPlaying ? 'playing' : '']" @click="togglePlay">Ceremony</h1>
    <div style="margin-left: 18px; margin-right: 380px;">
      <input
          v-model="searchTerm"
          class="search-input"
          type="text"
          placeholder="search for movies"
          @keyup.enter="handleSearch"
          style="background-color: #111; width: 480px; line-height: 30px; border-radius: 8px; color: #999999; padding-left: 10px; border: 2px solid transparent;"
      />
      <button @click="handleSearch" style="height: 30px; background-color: transparent; color: #999; border: none; border-radius: 5px; padding: 0 10px; font-size: 14px;">
<!--        <i class="fa fa-search" style="margin-right: 5px;"></i>-->
<!--        el-icon-search-->
        <i class="el-icon-search" style="margin-right: 5px;font-size: 15px "></i>
      </button>
    </div>


    <el-dropdown style="text-align: right; cursor: pointer">
      <div style="display: inline-block">
<!--        <img :src="" alt=""-->
<!--             style="width: 25px; border:2px solid ; border-radius: 50%; position: relative; top: 8px; right: 5px">-->
        <span style="font-style: italic;" >have a good day    <b class="namecolor1">{{user.nickname}}</b></span>
      </div>

<!--      <i class="el-icon-user-solid" style="margin-left: 0px; margin-right: 20px;"></i>-->
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item>Information</el-dropdown-item>
        <el-dropdown-item>
          <span style="text-decoration: none" @click="logout">LogOut</span>
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
<!--     Hidden audio player-->
    <audio ref="audioPlayer" src="../assets/Ceremony - Cold Cold Night.mp3"></audio>
  </div>
</template>

<script>
import axios from 'axios';
import { EventBus } from '../event-bus.js';
// import { router } from '@/router'; // 假设您的Vue Router实例命名为router


export default {
  name: "Header",
  props: {
    collapseBtnClass: String,
    collapse: Boolean
  },
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      searchTerm: '',
      isPlaying: false
    };
  },
  methods: {
    logout() {
      this.$router.push("/login");
      localStorage.removeItem("user");
      this.$message.success("退出成功");
    },
    handleSearch() {
      if (!this.searchTerm) {
        this.refreshPage();
      } else {
        this.search();
      }
    },
    refreshPage() {
      this.$router.push("/home");
      location.reload();
    },
    async search() {
      const res = await axios.get('http://localhost:9090/filmv3/search', {
        params: { title: this.searchTerm }
      });
      console.log('Search results:', res.data);
      const RnewMovies = res.data.map(record => ({ // 请确认这个转换是否正确
        movieID: record.movieID,
        imgUrl: record.imageURL,
        name: record.title,
        rating: record.voteAverage,
        releaseYear: '1993', // 你的API似乎没有提供发行年份信息，你可能需要从其他地方获取或直接硬编码一个值
        genre: 'Unknown', // 同上，你的API似乎没有提供电影类型信息
      }));
      console.log('Emitting search-result with:', RnewMovies);
      // this.$emit('search-result', newMovies);
      EventBus.$emit('search-result', RnewMovies);
      console.log('search-result event emitted');
    },

    togglePlay() {
      const audio = this.$refs.audioPlayer;
      if (this.isPlaying) {
        audio.pause();
      } else {
        audio.play();
      }
      this.isPlaying = !this.isPlaying;
      this.changeMovieRatingColor();
      EventBus.$emit('play-status-changed', this.isPlaying);
    },
    changeMovieRatingColor() {
      const movieRatingElement = document.querySelector('.movie-rating');
      const nameColor1 = document.querySelector('.namecolor1');
      const movieImage2 = document.querySelector('.movie-image2');
      const movieImage3 = document.querySelector('.movie-image3');

      if (this.isPlaying == true) {
        if (movieRatingElement) {
          movieRatingElement.style.background = 'linear-gradient( to bottom right, #ffcc00 0%, #cf2626 50%, #8d1aff 100%)';
        }
        if (nameColor1) {
          nameColor1.style.background = 'linear-gradient( to bottom right, #ffcc00 0%, #cf2626 50%, #8d1aff 100%)';
          nameColor1.style.webkitBackgroundClip = 'text';
          nameColor1.style.webkitTextFillColor = 'transparent';
        }
        if (movieImage2) {
          movieImage2.style.boxShadow = '-300px 00px 1100px 50px #ffcc00;';
        }
        if (movieImage3) {
          movieImage3.style.boxShadow = '-300px 00px 1100px 50px #8d1aff;';
        }
      } else {
        if (movieRatingElement) {
          movieRatingElement.style.background = 'linear-gradient( to bottom right, #64a6e7 0%, #4880c7 50%, #4880c7 100%)';
        }
        if (nameColor1) {
          nameColor1.style.background = 'linear-gradient( to bottom right, #4880c7 0%, #4880c7 100%)';
          nameColor1.style.webkitBackgroundClip = 'text';
          nameColor1.style.webkitTextFillColor = 'transparent';
        }
        if (movieImage2) {
          movieImage2.style.boxShadow = '-300px 00px 1100px 50px #64a6e7;';
        }
        if (movieImage3) {
          movieImage3.style.boxShadow = '-300px 00px 1100px 50px #64a6e7;';
        }
      }
    }
  }
}
</script>

<style scoped>

.namecolor1{
  color: rgba(100, 161, 231, 0.91);
  text-shadow: 1px 1px 20px #d5d5d5;
  padding-left: 5px;
  text-decoration: underline;
  text-decoration-color: #333;
  font-weight: bold;

}

.welcome1 {
  background-image: url('../assets/metal.png');
  background-size: cover; /* 调整背景图片的尺寸以填充元素 */
  background-repeat: no-repeat; /* 防止背景图片重复 */
  border-bottom-left-radius: 8px;
  border-top-left-radius: 8px;
  border-bottom-right-radius: 12px;
  position: sticky;
  margin-left: 160px;
  padding: 3px;  /* 为元素增加一些内边距 */
  line-height: 32px;  /* 调整元素的行高 */
  margin-top: 12px;  /* 在元素的上方增加一些外边距 */
  margin-bottom: 12px;  /* 在元素的下方增加一些外边距 */
  top: 8px;
  z-index: 1;
  color: #000;
  font-weight: bold;
  text-shadow: 1px 1px 10px #d5d5d5;
  box-shadow: 0px 0px 7px 2px rgba(222, 222, 222, 0.38);
}
.welcome2 {
  background-image: url('../assets/metal.png');
  background-size: cover; /* 调整背景图片的尺寸以填充元素 */
  background-repeat: no-repeat; /* 防止背景图片重复 */
  border-bottom-right-radius: 8px;
  border-top-right-radius: 8px;
  border-bottom-left-radius: 12px;

  position: sticky;
  padding: 3px;  /* 为元素增加一些内边距 */
  line-height: 32px;  /* 调整元素的行高 */
  margin-top: 12px;  /* 在元素的上方增加一些外边距 */
  margin-bottom: 12px;  /* 在元素的下方增加一些外边距 */
  top: 8px;
  z-index: 1;
  color: #4880c7;
  /*color: #64a6e7;*/
  font-weight: bold;
  text-shadow: 1px 1px 10px #d5d5d5;
  box-shadow: 0px 0px 7px 2px rgba(222, 222, 222, 0.38);
}

.playing {
  background: linear-gradient(to bottom right, #ffcc00 0%, #cf2626 50%, #8d1aff 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  font-weight: bold;
  display: inline-block;
}

.search-input:focus {
  border-color: yellow;
  outline: none;
}

body {
  font-family: "Arial", sans-serif; /* 设置整个文档的字体样式为Arial或sans-serif */
}
</style>
