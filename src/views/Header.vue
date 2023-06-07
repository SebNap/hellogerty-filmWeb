<template>
  <div style="font-size: 12px; line-height: 64px; display: flex; justify-content: space-between; width: 102%; background: linear-gradient(to right, #000 0%,#222 20%,#111 50%,#222 70%,#000 99%,#000 100%); color: #ccc;">
    <div style="margin-left: 3px; flex: 1; font-size: 20px">
      <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
    </div>
    <h1 class="welcome1">
      <router-link to="/home" style="color: inherit; text-decoration: none;">Film</router-link>
    </h1>
    <h1 :class="['welcome2', isPlaying ? 'playing' : '']" @click="togglePlay">Ceremony</h1>
    <div style="margin-left: 18px; margin-right: 810px;">
      <el-input style="width: 284px" v-model="searchTerm" placeholder="Search for movies" @keyup.enter="search">
        <template #prepend>
          <el-button icon="el-icon-search" @click="search"></el-button>
        </template>
      </el-input>
    </div>

    <el-dropdown style="text-align: right; cursor: pointer">
      <div style="display: inline-block">
        <img :src="user.avatarUrl" alt=""
             style="width: 25px; border:2px solid orange; border-radius: 50%; position: relative; top: 8px; right: 5px">
        <span>{{user.nickname}}</span>
      </div>

      <i class="el-icon-user-solid" style="margin-left: 0px; margin-right: 20px;"></i>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item>个人信息</el-dropdown-item>
        <el-dropdown-item>
          <span style="text-decoration: none" @click="logout">退出</span>
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
    <!-- Hidden audio player -->
    <audio ref="audioPlayer" src="../assets/Ceremony - Cold Cold Night.mp3"></audio>
  </div>
</template>

<script>
import axios from 'axios';

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
    async search() {
      const res = await axios.get('http://localhost:9090/filmv3/search', {
        params: { title: this.searchTerm }
      });
      console.log('Search results:', res.data);  // 添加这行
      this.$emit('search-result', res.data);
    },
    togglePlay() {
      const audio = this.$refs.audioPlayer;
      if (this.isPlaying) {
        audio.pause();
      } else {
        audio.play();
      }
      this.isPlaying = !this.isPlaying;
    }
  }
}
</script>

<style scoped>
.welcome1 {
  background-image: url('../assets/metal.png');
  background-size: cover; /* 调整背景图片的尺寸以填充元素 */
  background-repeat: no-repeat; /* 防止背景图片重复 */
  border-bottom-left-radius: 8px;
  border-top-left-radius: 8px;
  border-bottom-right-radius: 12px;
  position: sticky;
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

body {
  font-family: "Arial", sans-serif; /* 设置整个文档的字体样式为Arial或sans-serif */
}
</style>
