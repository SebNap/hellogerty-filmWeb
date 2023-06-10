<template>
  <div class="container" ref="container" @scroll="checkScroll">
    <img :src="require('@/assets/allblack.png')" :class="['movie-image2', ceremonyClicked ? 'movie-image22' : '']" />
    <img :src="require('@/assets/allblack.png')" :class="['movie-image3', ceremonyClicked ? 'movie-image33' : '']" />
    <img :src="require('@/assets/allblack.png')" :class="['movie-image4', ceremonyClicked ? 'movie-image44' : '']" />
    <img :src="require('@/assets/allblack.png')" :class="['movie-image5', ceremonyClicked ? 'movie-image55' : '']" />
    <Header @search-result="onSearchResult" />
    <div class="content">
      <el-row :gutter="20">
        <el-col :span="6" v-for="item in lists" :key="item.movieID">
          <el-card class="movie-card">
            <div class="movie-image-wrapper">
              <img :src="item.imageURL" class="movie-image" />
              <div :class="['movie-rating', ceremonyClicked ? 'movie-rating-high' : '']">{{ item.voteAverage }}</div>
              <div class="movie-footer">
                <el-button type="text" class="button like-button" @click="deleteMovie(item.movieID)">
                  <i class="fas fa-trash"></i>
                </el-button>
              </div>
              <div class="movie-footer2">
                <el-button type="text" class="button like-button2" @click="showMore(item.movieID)">more</el-button>
              </div>
            </div>
            <div class="movie-detail">
              <span class="movie-title">{{ item.title }}</span>
            </div>
          </el-card>
        </el-col>
      </el-row>
      <div v-if="reachedEnd" class="end-message">
        <b style="color: #333">All Movies are Loaded</b>
      </div>
    </div>
    <button class="back-to-top" v-show="isScrolled" @click="backToTop">
      <i class="fas fa-angle-double-up"></i>
    </button>
  </div>
</template>

<script>
import axios from "axios";
import Header from "@/views/Header";
import { EventBus } from '../event-bus.js';

export default {
  name: "Like",
  components: {
  },
  created() {
    this.nickname = this.getNickname();  // Get the nickname when the component is created
    // console
    EventBus.$on('search-result', this.onSearchResult);
  },
  beforeDestroy() {
    EventBus.$off('search-result', this.onSearchResult);
  },
  data() {
    return {
      loading: false,
      reachedEnd: true,
      lists: [],
      isScrolled: false,
      currentPage: Math.floor(Math.random() * 6),
      pageSize: 20,
      ceremonyClicked: false,
      nickname: ''  // Initialize nickname
    };
  },

  mounted() {
    this.fetchMovies();
    EventBus.$on('play-status-changed', (isPlaying) => {
      this.ceremonyClicked = !this.ceremonyClicked;
    });
  },
  methods: {
    fetchMovies() {
      this.nickname = this.getNickname();  // Update the nickname before each request
      console.log('my nickname:',this.nickname);
      setTimeout(() => {
        // 在这里进行下一步操作
        console.log('my nickname:',this.nickname);
      }, 1000);  // 1000毫秒 = 1秒


      axios.get(`http://localhost:9090/likev1/search`, {
        params: {
          nickname: this.nickname,
        },
      }).then(res => {
        console.log(res)
        this.reachedEnd = false;
        if (res.data) {
          // this.lists = [...this.lists, ...this.shuffleArray(res.data)]; // 直接使用 res.data
          this.lists = [...this.lists, ...res.data];//个人收藏页就不用打乱顺序了
          this.reachedEnd = res.data.length < this.pageSize; // 检查 res.data 的长度
        } else {
          console.warn('Unexpected response data structure:', res.data);
        }
      })
    },

    shuffleArray(array) {
      for (let i = array.length - 1; i > 0; i--) {
        let j = Math.floor(Math.random() * (i + 1));
        [array[i], array[j]] = [array[j], array[i]];
      }
      return array;
    },
    checkScroll() {
      let bottom = this.$refs.container.scrollHeight - this.$refs.container.scrollTop <= this.$refs.container.clientHeight;
      // if (bottom) {
      //   this.fetchMovies();
      // }
    },
    backToTop() {
      this.$refs.container.scrollTop = 0;
      this.isScrolled = false;
    },
    onScroll() {
      this.isScrolled = this.$refs.container.scrollTop > 20;
    },
    onSearchResult(searchResult) {
      this.lists = searchResult;
    },
    getNickname() {
      // The actual implementation of this method depends on where you store the user data
      // For example, if you store it in the localStorage, you could do something like this:
      let user = JSON.parse(window.localStorage.getItem('user'));
      let nickname = user ? user.nickname : null;
      return nickname;
      // return localStorage.getItem('nickname');
    },
    deleteMovie(movieID) {
      this.nickname = this.getNickname();  // Update the nickname before each request
      console.log('my nickname:',this.nickname);
      axios.delete(`http://localhost:9090/likev1/${movieID}`, {
        params: {
          nickname: this.nickname,
        },
      })
          .then(() => {
            this.lists = this.lists.filter(item => item.movieID !== movieID);
            console.log(this.lists);
          })
          .catch(error => {
            console.error('Error deleting movie:', error);
          });
    },
    showMore(movieId) {
      console.log("Movie ID: ", movieId);
      // 从列表中找到对应的电影
      let movie = this.lists.find(item => item.movieID === movieId);
      if (movie) {
        console.log("More information for movie: ", movie);
      }

      // 将电影ID作为参数传递给路由
      this.$router.push({ name: 'Details', params: { movieId: movieId, imgUrl: movie.imageURL} });
    },
  },
};
</script>

<style>
.container {
  padding-top: 24px;
  height: 100vh;
  overflow-y: auto;
  background-color: #000; /* 设置背景颜色为黑色 */
  position: relative;
}

.back-to-top {
  position: fixed;
  /*right: 290px;*/
  left: 200px;
  top: 82px;
  z-index: 999;
  background-color: transparent;
  border: none;
  box-shadow: none;
  font-size: 20px;
  transition: all 0.3s ease;
  color: #333;
}

.back-to-top:hover {
  color: #ccc;
}

/* 隐藏滚动条 */
.container::-webkit-scrollbar {
  display: none;
}

.content {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(500px, 1fr));
  grid-gap: 20px;
  justify-content: center;
  max-width: 1000px;
  margin: fill;
  overflow: hidden;
}

.el-row {
  width: 100%;
}

.movie-card {
  height: 455px;
  margin-bottom: 20px;
  border-radius: 8px;
  background: linear-gradient( to bottom right, #222 0%,rgba(26, 26, 26, 0.9) 90%,#222 100%);

  color: #ccc;
  overflow: hidden;
  /*border: none;*/
  border: 0px solid #333;
}

.movie-image-wrapper {
  position: relative;
  width: 100%;
  padding-bottom: 150%;
}

.movie-image {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
  /*border-top-left-radius: 8px;*/
  /*border-top-right-radius: 8px;*/
  /*border: 1px solid #333;*/
  border-radius: 8px;
}

.movie-rating {
  position: absolute;
  color: #eee;
  top: -10px;
  right: 10px;
  padding: 4px 8px;
  /*background-color: #64a6e7;*/
  background: linear-gradient( to bottom right, #64a6e7 0%, #4880c7 50%, rgba(72, 128, 199, 0.98) 100%);
  border-radius: 4px;
  font-weight: bold;
  text-shadow: 1px 1px 10px #d5d5d5;
  box-shadow: 0px 0px 7px 1px rgba(222, 222, 222, 0.38);
}

.movie-detail {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  margin-top: 13px;
  padding: 3px;
}

.movie-title {
  font-size: 17px;
  font-weight: bold;
}

.movie-info {
  margin-top: 8px;
}

.movie-release-year {
  margin-right: 8px;
}

.movie-genre {
  font-style: italic;
}

.movie-footer {
  position: absolute;
  top: 377px;
  /*right: 0px;*/
  padding: 3px;
  margin-left: 115px;
  /*background-color: #ff4081;*/
  /*background: linear-gradient( to bottom right, #ffcc00 0%, #cf2626 50%, #8d1aff 100%);*/
  border-radius: 4px;
  font-weight: bold;
}

.movie-footer2 {
  position: absolute;
  top: 385px;
  /*left: 0px;*/
  margin-left: 153px;
  padding-right: 3px;
  padding-left: 3px;
  background-color: rgba(17, 17, 17, 0.66);
  font-weight: bold;
  border-radius: 5px;

}

.end-message {
  text-align: center;
  margin-bottom: 20px;
  color: #fff;
}

.like-button {
  color: #bd0000 !important;
  font-size: 22px;
}

.like-button2 {
  color: #ccc !important;
  font-size: 10px;
  font-weight: bold;
}

.movie-rating-high {
  background: linear-gradient(to bottom right, #ffcc00 0%, #cf2626 50%, #8d1aff 100%);
}

.movie-image2 {
  /*display: none;*/
  position: fixed;
  width: 1px;
  height: 200px;
  object-fit: cover;
  /*margin-top: 35px;*/
  right: 0px;
  z-index: 1;

  box-shadow: -300px 00px 1100px 50px #64a6e7;
  /*filter: url(#gradient-shadow);*/
  background: linear-gradient(to bottom right, #ffcc00 0%, #cf2626 50%, #8d1aff 100%);
}
.movie-image22 {
  /*display: none;*/
  position: fixed;
  width: 1px;
  height: 200px;
  object-fit: cover;
  /*margin-top: 35px;*/
  right: 0px;
  z-index: 1;

  box-shadow: -300px 00px 1100px 50px #ffcc00;
  /*filter: url(#gradient-shadow);*/
  background: linear-gradient(to bottom right, #ffcc00 0%, #cf2626 50%, #8d1aff 100%);
}
.movie-image3 {
  /*display: none;*/
  position: fixed;
  width: 1px;
  height: 200px;
  object-fit: cover;
  /*margin-top: 35px;*/
  right: 0px;
  z-index: 1;

  box-shadow: -1300px 00px 1100px 50px #64a6e7;
}

.movie-image33 {
  /*display: none;*/
  position: fixed;
  width: 1px;
  height: 200px;
  object-fit: cover;
  /*margin-top: 35px;*/
  right: 0px;
  z-index: 1;

  box-shadow: -1300px 00px 1100px 50px #8d1aff;
}

.movie-image4 {
  /*display: none;*/
  position: fixed;
  width: 1px;
  height: 200px;
  object-fit: cover;
  /*margin-top: 35px;*/
  right: 0px;
  z-index: 1;

  box-shadow: -300px 600px 1100px 50px transparent;
  /*filter: url(#gradient-shadow);*/
  background: linear-gradient(to bottom right, #ffcc00 0%, #cf2626 50%, #8d1aff 100%);
}
.movie-image44 {
  /*display: none;*/
  position: fixed;
  width: 1px;
  height: 200px;
  object-fit: cover;
  /*margin-top: 35px;*/
  right: 0px;
  z-index: 1;

  box-shadow: -300px 600px 1100px 50px #ff1af4;
  /*filter: url(#gradient-shadow);*/
  background: linear-gradient(to bottom right, #ffcc00 0%, #cf2626 50%, #8d1aff 100%);
}

.movie-image5 {
  /*display: none;*/
  position: fixed;
  width: 1px;
  height: 200px;
  object-fit: cover;
  /*margin-top: 35px;*/
  right: 0px;
  z-index: 1;

  box-shadow: -1300px 600px 1100px 50px transparent;
  /*filter: url(#gradient-shadow);*/
  background: linear-gradient(to bottom right, #ffcc00 0%, #cf2626 50%, #8d1aff 100%);
}
.movie-image55 {
  /*display: none;*/
  position: fixed;
  width: 1px;
  height: 200px;
  object-fit: cover;
  /*margin-top: 35px;*/
  right: 0px;
  z-index: 1;

  box-shadow: -1300px 600px 1100px 50px #cf2626;
  /*filter: url(#gradient-shadow);*/
  background: linear-gradient(to bottom right, #ffcc00 0%, #cf2626 50%, #8d1aff 100%);
}


</style>
