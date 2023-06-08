<template>
  <div class="container" ref="container" @scroll="checkScroll">
    <Header @search-result="onSearchResult" />
    <TopMovies />
    <div class="content">
      <el-row :gutter="20">
        <el-col :span="6" v-for="item in lists" :key="item.name">
          <el-card class="movie-card">
            <div class="movie-image-wrapper">
              <img :src="item.imgUrl" class="movie-image" />
              <div class="movie-rating">{{ item.rating }}</div>
              <div class="movie-footer">
                <el-button type="text" class="button like-button">
                  <i class="fas fa-heart"></i></el-button>
              </div>
              <div class="movie-footer2">
                <el-button type="text" class="button like-button2">more
                  </el-button>
              </div>
            </div>
            <div class="movie-detail">
              <span class="movie-title">{{ item.name }}</span>
              <div class="movie-info">
                <span class="movie-release-year">{{ item.releaseYear }}</span>
                <span class="movie-genre">{{ item.genre }}</span>
              </div>
            </div>


          </el-card>
        </el-col>
      </el-row>
      <div v-if="reachedEnd" class="end-message"><b style="color: #333">All Movies are Loaded</b></div>
<!--      <div v-if="reachedEnd2" class="end-message"><b style="color: #333">All Movies are Loaded</b></div>-->
    </div>
<!--    <TopMovies />-->
    <button class="back-to-top" v-show="isScrolled" @click="backToTop">
      <i class="fas fa-angle-double-up"></i>
    </button>




  </div>
</template>

<script>
import axios from "axios";
import Header from "@/views/Header";
import { EventBus } from '../event-bus.js';
import TopMovies from './TopMovies.vue'; // Make sure the path is correct


export default {
  name: "Home",
  components: {TopMovies},
      // {Header},

  created() {
    EventBus.$on('search-result', this.onSearchResult);
  },
  beforeDestroy() {
    EventBus.$off('search-result', this.onSearchResult);
  },
  data() {
    return {
      loading: false,
      reachedEnd: false,
      reachedEnd2: false,
      lists: [],
      isScrolled: false,
      currentPage: Math.floor(Math.random() *6), // 生成0-100的随机数
      // currentPage: 0, // 添加当前页面
      pageSize: 20, // 添加每页显示的数量
    };
  },
  mounted() {
    this.fetchMovies();
  },
  methods: {
    fetchMovies() {
      // 先增加页码
      this.currentPage += 1;

      axios.get(`http://localhost:9090/filmv3/page`, {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
        },
      }).then(res => {
        console.log('Origin movies with:', res.data.records);
        let newMovies = res.data.records.map(record => ({
          imgUrl: record.imageURL,
          name: record.title,
          rating: record.voteAverage,
          releaseYear: '', // 你的API似乎没有提供发行年份信息，你可能需要从其他地方获取或直接硬编码一个值
          genre: '', // 同上，你的API似乎没有提供电影类型信息
        }));

        this.reachedEnd = false;
        // this.lists = [...this.lists, ...newMovies];
        this.lists = [...this.lists, ...this.shuffleArray(newMovies)];
        this.reachedEnd = res.data.records.length < this.pageSize; // 检查是否已经到达了最后一页
        console.log('Origin moviesList with:', this.lists);
      })
    },

    shuffleArray(array) {//随机展示电影
      for (let i = array.length - 1; i > 0; i--) {
        let j = Math.floor(Math.random() * (i + 1));
        [array[i], array[j]] = [array[j], array[i]];
      }
      return array;
    },
    checkScroll(event) {
      const { target } = event;
      this.isScrolled = target.scrollTop > 0;
      const nearBottom = target.scrollTop + target.clientHeight >= target.scrollHeight - 20;
      if (nearBottom && !this.reachedEnd) {
        this.fetchMovies();
      }
    },
    updateMovies(RnewMovies) {
      console.log('Updating movies with:', RnewMovies);  // 添加这行
      this.lists = RnewMovies.map(record => ({
        // movieID: record.movieID,
        imgUrl: record.imgUrl,
        name: record.name,
        rating: record.rating,
        releaseYear: '', // 你的API似乎没有提供发行年份信息，你可能需要从其他地方获取或直接硬编码一个值
        genre: '', // 同上，你的API似乎没有提供电影类型信息
      }));
      // this.lists = [...this.lists, ...RnewMovies];
      // 更新currentPage和reachedEnd的值
      this.currentPage = 1;
      this.reachedEnd = true;
      this.reachedEnd2 = true;
    },
    onSearchResult(RnewMovies) {
      console.log('onSearchResult has been called!');
      console.log('Received search-result event with:', RnewMovies);
      this.updateMovies(RnewMovies);
      console.log('lists has been updated to:', this.lists);
    },
    backToTop() {
      this.$refs.container.scrollTop = 0;
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
  top: 80px;
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
  height: 450px;
  margin-bottom: 20px;
  border-radius: 8px;
  background-color: #222;
  color: #ccc;
  overflow: hidden;
  border: none;
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
  border-radius: 8px;
}

.movie-rating {
  position: absolute;
  top: 10px;
  right: 10px;
  padding: 4px 8px;
  /*background-color: #ff4081;*/
  background: linear-gradient( to bottom right, #ffcc00 0%, #cf2626 50%, #8d1aff 100%);
  border-radius: 4px;
  font-weight: bold;
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
  right: 0px;
  padding: 3px;
  /*background-color: #ff4081;*/
  /*background: linear-gradient( to bottom right, #ffcc00 0%, #cf2626 50%, #8d1aff 100%);*/
  border-radius: 4px;
  font-weight: bold;
}

.movie-footer2 {
  position: absolute;
  top: 380px;
  left: 0px;
  padding: 3px;
  background-color: #111;
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
  font-size: 20px;
}

.like-button2 {
  color: #ccc !important;
  font-size: 10px;
  font-weight: bold;
}

</style>
