<template>
  <div class="container" ref="container" @scroll="checkScroll">
    <Header @search-result="updateMovies" />
    <div class="content">
      <el-row :gutter="20">
        <el-col :span="6" v-for="item in lists" :key="item.name">
          <el-card class="movie-card">
            <div class="movie-image-wrapper">
              <img :src="item.imgUrl" class="movie-image" />
              <div class="movie-rating">{{ item.rating }}</div>
            </div>
            <div class="movie-detail">
              <span class="movie-title">{{ item.name }}</span>
              <div class="movie-info">
                <span class="movie-release-year">{{ item.releaseYear }}</span>
                <span class="movie-genre">{{ item.genre }}</span>
              </div>
              <div class="movie-footer">
                <el-button type="text" class="button like-button">Like</el-button>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
      <div v-if="reachedEnd" class="end-message">已加载全部电影</div>
    </div>
    <button class="back-to-top" v-show="isScrolled" @click="backToTop">
      <i class="fas fa-angle-double-up"></i>
    </button>


  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Home",
  data() {
    return {
      loading: false,
      reachedEnd: false,
      lists: [],
      isScrolled: false,
      currentPage: 1, // 添加当前页面
      pageSize: 20, // 添加每页显示的数量
    };
  },
  mounted() {
    this.fetchMovies();
  },
  methods: {
    fetchMovies() {
      axios.get(`http://localhost:9090/filmv3/page`, {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
        },
      }).then(res => {
        let newMovies = res.data.records.map(record => ({
          imgUrl: record.imageURL,
          name: record.title,
          rating: record.voteAverage,
          releaseYear: '1993', // 你的API似乎没有提供发行年份信息，你可能需要从其他地方获取或直接硬编码一个值
          genre: 'Unknown', // 同上，你的API似乎没有提供电影类型信息
        }));

        this.lists = [...this.lists, ...newMovies];
        this.reachedEnd = res.data.records.length < this.pageSize; // 检查是否已经到达了最后一页
        this.currentPage += 1; // 每次获取到新的电影数据后，页码加1
      })
    },
    checkScroll(event) {
      const { target } = event;
      this.isScrolled = target.scrollTop > 0;
      const nearBottom = target.scrollTop + target.clientHeight >= target.scrollHeight - 20;
      if (nearBottom && !this.reachedEnd) {
        this.fetchMovies();
      }
    },
    updateMovies(newMovies) {
      console.log('Updating movies with:', newMovies);  // 添加这行
      this.lists = newMovies.map(record => ({
        imgUrl: record.imageURL,
        name: record.title,
        rating: record.voteAverage,
        releaseYear: '1993', // 你的API似乎没有提供发行年份信息，你可能需要从其他地方获取或直接硬编码一个值
        genre: 'Unknown', // 同上，你的API似乎没有提供电影类型信息
      }));
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
  right: 290px;
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
  height: 480px;
  margin-bottom: 20px;
  border-radius: 8px;
  background-color: #222;
  color: #fff;
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
  font-size: 16px;
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
  display: flex;
  font-style: italic;

}

.end-message {
  text-align: center;
  margin-top: 20px;
  color: #fff;
}
</style>
