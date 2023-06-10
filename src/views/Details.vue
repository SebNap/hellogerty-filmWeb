<template>
  <div class="container">
    <TopMovies />
    <img :src="require('@/assets/allblack.png')" :class="['movie-image2', ceremonyClicked ? 'movie-image22' : '']" />
    <img :src="require('@/assets/allblack.png')" :class="['movie-image3', ceremonyClicked ? 'movie-image33' : '']" />
    <img :src="require('@/assets/allblack.png')" :class="['movie-image4', ceremonyClicked ? 'movie-image44' : '']" />
    <img :src="require('@/assets/allblack.png')" :class="['movie-image5', ceremonyClicked ? 'movie-image55' : '']" />
    <div class="movie">
      <div class="movie-header">
<!--        <el-card class="movie-card" >-->
<!--          <div class="movie-image-wrapper">-->
            <img :src="movieDetails.imgUrl" class="movie-image" />

<!--          </div>-->
<!--        </el-card>-->
        <div class="movie-header-info">
          <h2 class="movie-title">{{ movieDetails.title }}</h2>
          <div class="movie-rating">{{ movieDetails.voteAverage }}</div>
          <el-rate v-model="movieDetails.voteAverage / 2 " :colors="colors"></el-rate>
          <div class="movie-info">
            <span class="movie-release-year">{{ movieDetails.releaseDate }}</span>
            <span class="movie-genre">{{ movieDetails.genres }}</span>
          </div>
          <div class="movie-content">
            <p class="movie-overview">{{ movieDetails.overview }}</p>
            <h6>Production Companies</h6>
            <p class="movie-companies">{{ movieDetails.productionCompanies }}</p>
            <h6>Production Countries</h6>
            <p class="movie-countries">{{ movieDetails.productionCountries }}</p>
            <h6>Status</h6>
            <p class="movie-status">{{ movieDetails.status }}</p>
            <h3></h3>
            <p class="movie-tagline"> 【{{ movieDetails.tagline }}】</p>
          </div>
        </div>
      </div>
      <div class="movie-comments">
        <!-- Add the comment form here -->
        <div class="add-comment-form">
          <input v-model="newComment"
                 type="text"
                 placeholder="Add a comment..."
                 class="comment-input"
                 style="background-color: #0a0a0a; width: 480px; line-height: 30px;height: 50px; border-radius: 8px; color: #999999; padding-left: 10px; border: 2px solid transparent;"
          />
          <button @click="submitComment" class="comment-button" style="height: 50px; line-height: 50px; background-color: transparent; color: #999; border: none; border-radius: 5px; padding: 0 10px; font-size: 14px;">Submit</button>
        </div>
        <h3 class="comment-heading">Comments</h3>
        <div v-for="(comment, index) in movieComments" :key="index" class="comment-item">
          <div class="comment-header">
            <img :src="comment.headUrl" class="comment-avatar" />
            <div class="comment-meta">
              <span class="comment-author">{{ comment.author }}</span>
              <span class="comment-time">{{ comment.time }}</span>
            </div>
            <button @click="deleteComment(index)" class="comment-delete">Delete</button>
          </div>
          <p class="comment-content">{{ comment.content }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

// Import axios
import axios from 'axios';
import TopMovies from './TopMovies.vue';
import {EventBus} from "@/event-bus";

export default {
  name: "Details",
  components: {TopMovies},
  data() {
    return {
      movieDetails: {
        imgUrl: '',
        title: '',
        voteAverage: 0,
        releaseDate: '',
        genres: '',
        overview: '',
        productionCompanies: '',
        productionCountries: '',
        status: '',
        tagline: '',
      },
      movieComments: [],
      newComment: '', // New property for the comment text
      colors: ['#99a9bf', '#f7ba2a', '#ff9900'],
      ceremonyClicked: false,
    };
  },
  mounted() {
    // this.fetchMovies();
    EventBus.$on('play-status-changed', (isPlaying) => {
      this.ceremonyClicked = !this.ceremonyClicked;
    });
  },
  methods: {
    submitComment() {
      // 获取用户信息
      let user = JSON.parse(window.localStorage.getItem('user'));
      let nickname = user ? user.nickname : null;
      let headUrl = user ? user.avatarUrl : null;

      // 如果用户没有登录，终止操作
      if (!nickname) {
        alert('Please login first');
        return;
      }

      // Replace with your API URL
      const apiUrl = `http://localhost:9090/commentv1/makemovie/${this.$route.params.movieId}`;

      // 获取当前时间
      let currentTime = new Date().toISOString();

      // Post the new comment
      axios.post(apiUrl, {
        content: this.newComment,
        movieID: this.$route.params.movieId,
        nickname: nickname, // use nickname as userID
        headUrl: headUrl, // user's avatar URL
        time: currentTime, // current time as the comment time
      })
          .then(() => {
            // Clear the comment text
            this.newComment = '';

            // Fetch comments again to update the list
            this.fetchComments();
          })
          .catch(error => console.error('Error:', error));
    },
    fetchComments() {
      const movieId = this.$route.params.movieId;
      const commentsApiUrl = `http://localhost:9090/commentv1/movie/${movieId}`;

      fetch(commentsApiUrl)
          .then(response => response.json())
          .then(data => {
            console.log('Received comments data:', data);
            if (data && Array.isArray(data)) {
              this.movieComments = data.map(comment => {
                return {
                  id: comment.id,
                  uid: comment.uid,
                  author: comment.nickname,
                  time: comment.time,
                  content: comment.content,
                  headUrl: comment.headUrl,
                };
              });
            } else {
              console.error('Unexpected data format:', data);
            }
          })
          .catch(error => {
            console.error('Error:', error);
          });
    },
    deleteComment(index) {
      console.log('Current index:', index);
      // 如果用户没有登录，终止操作
      let user = JSON.parse(window.localStorage.getItem('user'));
      if (!user) {
        alert('Please login first');
        return;
      }

      const comment = this.movieComments[index];
      if (user.nickname !== comment.author) {
        alert('You can only delete your own comments');
        return;
      }
      console.log('Current id:', comment.id);
      const apiUrl = `http://localhost:9090/commentv1/delete/${comment.id}`; // 修改此处的URL为实际删除评论的API地址

      axios.delete(apiUrl)
          .then(() => {
            // 删除评论后更新列表
            this.fetchComments();
          })
          .catch(error => console.error('Error:', error));
    },
  },
  created() {
    const movieId = this.$route.params.movieId;
    const imgUrl = this.$route.params.imgUrl;
    this.movieDetails.imgUrl = imgUrl;

    // 替换为你的API URL
    const apiUrl1 = `http://localhost:9090/allv2/movie/${movieId}`;
    const apiUrl2 = `http://localhost:9090/filmv3/${movieId}`;

    fetch(apiUrl1)
        .then(response => response.json())
        .then(data => {
          this.movieDetails = {
            ...this.movieDetails,
            title: data.title,
            voteAverage: data.voteAverage,
            releaseDate: data.releaseDate,
            genres: data.genres,
            overview: data.overview,
            productionCompanies: data.productionCompanies,
            productionCountries: data.productionCountries,
            status: data.status,
            tagline: data.tagline,
          };
        })
        .catch(error => {
          console.error('Error:', error);
        });

    const commentsApiUrl = `http://localhost:9090/commentv1/movie/${movieId}`;

    fetch(commentsApiUrl)
        .then(response => response.json())
        .then(data => {
          console.log(data);
          // 检查data是否存在，并且是否为数组
          if (data && Array.isArray(data)) {
            this.movieComments = data.map(comment => {
              return {
                id: comment.id,
                uid: comment.uid,
                author: comment.nickname,
                time: comment.time,
                content: comment.content,
                headUrl: comment.headUrl,
              };
            });
          } else {
            console.error('Unexpected data format:', data);
          }
        })
        .catch(error => {
          console.error('Error:', error);
        });


  },
};

</script>

<style scoped>
.container {
  background-color: #000000;
  color: #ccc;
  padding-bottom: 0px;
  padding-right: 30px;
  padding-top: 24px;
}

.movie {
  position: relative;
  max-width: 980px;
  /*margin-left: 100px;*/
  background: linear-gradient( to bottom right, #222 0%,rgba(17, 17, 17, 1) 10%,rgba(17, 17, 17, 0.97) 20%,rgba(17, 17, 17, 0.87) 40%, rgba(17, 17, 17, 1) 50%,rgba(17, 17, 17, 0.93) 80%,rgba(17, 17, 17, 1) 90%,#222 100%);
  border-radius: 8px;
}

.movie-header {
  padding-left: 30px;
  padding-right: 30px;
  padding-top: 35px;
  display: flex;
  gap: 20px;
}

.movie-image {
  position: relative;
  width: 200px;
  height: 300px;
  object-fit: cover;
  margin-top: 0px;
  border: 2px solid #222;
  /*box-shadow: 200px 600px 900px 1px rgba(103, 167, 231, 0.9999);*/
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



.movie-title {
  margin-top: 0px;
  margin-bottom: 10px;
  font-size: 24px;
}

.movie-rating {
  margin-top: 0px;
  margin-right: 30px;
  margin-bottom: 10px;
  font-size: 25px;
}

.movie-info {
  font-size: 18px;
}

.movie-release-year, .movie-genre {
  margin-right: 10px;
}

.movie-overview{
  margin-top: 15px;
  margin-bottom: 15px;
  font-size: 15px;
}

.movie-tagline {
  margin-top: 20px;
  margin-bottom: 15px;
  font-size: 15px;
}

.movie-content {
  position: relative;
  margin-bottom: 20px;

}

.movie-companies, .movie-countries, .movie-status {
  margin-top: 5px;
  margin-bottom: 5px;
  font-size: 10px;
  color: #999;
}

.movie-content h3 {
  font-size: 18px;
  color: #ccc;
  margin-top: 20px;
}

.movie-comments {
  margin-top: 40px;
}

.add-comment-form {
  display: flex;
  gap: 10px;
  margin-top: 0px;
  margin-bottom: 20px;
}

.comment-input {
  padding: 10px;
  border: none;
  border-radius: 8px;
  width: 200px;
  flex: 1;
}

.comment-button {
  padding: 10px 15px;
  background-color: #64a6e7;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}


.comment-header {
  display: flex;
  align-items: center;
  font-size: 14px;
  margin-bottom: 10px;
  /*background-color: #222;*/
  padding: 10px;
  border-radius: 10px;
  margin-right: 5px;
  /*margin-left: 5px;*/
}

.comment-avatar {
  width: 30px;
  height: 30px;
  border-radius: 50%;
  margin-right: 10px;
  border: 2px solid #ccc;
}

.comment-author {
  font-weight: bold;
  color: #64a6e7;
  margin-right: 10px;
}

.comment-time {
  color: #bbb;
  font-size: 12px;
}

.comment-content {
  background-color: #222;
  padding: 10px;
  border-radius: 10px;
  color: #ccc;
  margin-bottom: 20px;
  margin-right: 5px;
  margin-left: 45px;
  margin-top: -10px;
}

.movie-comments {
  border-top: 1px solid #333;
  margin-top: 20px;
  padding-top: 20px;
  padding-left: 30px;
  padding-right: 30px;
  padding-bottom: 30px;
  margin-bottom: 100px;
}

.movie-comments h3 {
  margin-bottom: 20px;
  /*text-align: center;*/
  color: #64a6e7;
}

.comment-delete {
  left: 600px;
  margin-left: auto;
  background-color: transparent;
  color: #333;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 10px;
}

.comment-heading{
  text-shadow: 1px 1px 15px #d5d5d5;
  /*box-shadow: 0px 0px 7px 2px rgba(222, 222, 222, 0.38);*/
}

</style>
