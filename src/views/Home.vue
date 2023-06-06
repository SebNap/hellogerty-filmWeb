<template>
  <div class="container" @scroll="checkScroll">
    <!-- 欢迎标题 -->
    <Header @search-result="updateMovies" />
    <h1 class="welcome">Welcome to Gerty's Home</h1>
    <div class="content">
      <!-- 使用 Element-UI 的行和列组件创建三列布局 -->
      <el-row :gutter="20">
        <!-- 使用 v-for 循环渲染电影卡片 -->
        <el-col :span="8" v-for="item in lists" :key="item.name">
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

      <!-- 显示已加载全部电影的消息 -->
      <div v-if="reachedEnd" class="end-message">已加载全部电影</div>
    </div>
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
    };
  },
  mounted() {
    this.fetchMovies();
  },
  methods: {
    fetchMovies() {
      axios.get('http://localhost:9090/filmv3').then(res => {
        let newMovies = res.data.map(record => ({
          imgUrl: record.imageURL,
          name: record.title,
          rating: record.voteAverage,
          releaseYear: '1993', // 你的API似乎没有提供发行年份信息，你可能需要从其他地方获取或直接硬编码一个值
          genre: 'Unknown', // 同上，你的API似乎没有提供电影类型信息
        }));

        this.lists = [...this.lists, ...newMovies];
        this.reachedEnd = true; // 同样，你的API似乎没有提供是否还有更多数据的信息，你可能需要修改这个逻辑
      })
    },
    checkScroll(event) {
      const { target } = event;
      this.isScrolled = target.scrollTop > 0;
      const nearBottom = target.scrollTop + target.clientHeight >= target.scrollHeight - 20;
      if (nearBottom && !this.reachedEnd) {
        // 当滚动接近底部且尚未加载全部电影时，获取更多电影数据
        this.fetchMovies();
      }
    },
    updateMovies(newMovies) {
      this.lists = newMovies;
    },
  },
};


</script>

<style>
.container {
  padding: 0px;
  height: 100vh;
  overflow-y: auto;
  background-color: #000; /* 设置背景颜色为黑色 */
}

/* 隐藏滚动条 */
.container::-webkit-scrollbar {
  display: none;
}

.welcome {
  position: sticky;
  margin: fill;
  top: 8px;
  z-index: 1;
  color: orange;
  text-shadow: 2px 2px 4px #000000;
  /*margin-bottom: 20px;*/
}

.content {
  max-width: 1000px;
  margin: fill;
  overflow: hidden;
}

.movie-card {
  margin-bottom: 20px;
  border-radius: 8px;
  /*box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);*/
  background-color: #222; /* 设置卡片背景颜色 */
  color: #fff; /* 设置文本颜色 */
  overflow: hidden;
  border: none; /* 去除边框 */
}

.movie-image-wrapper {
  position: relative;
  width: 100%;
  padding-bottom: 150%; /* 保持宽高比例 */
}

.movie-image {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-top-left-radius: 8px;
  border-top-right-radius: 8px;
}

.movie-rating {
  position: absolute;
  top: 10px;
  right: 10px;
  padding: 4px 8px;
  background-color: #ff4081; /* 设置评分背景颜色 */
  border-radius: 4px;
  font-weight: bold;
}

.movie-detail {
  padding: 16px;
}

.movie-title {
  font-size: 18px;
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
  justify-content: flex-end;
  margin-top: 16px;
}

.end-message {
  text-align: center;
  margin-top: 20px;
  color: #fff; /* 设置文本颜色 */
}
</style>
