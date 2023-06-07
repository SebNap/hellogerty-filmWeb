<template>
  <div class="container">
    <div class="movie">
      <div class="movie-header">
        <img :src="movieDetails.imgUrl" class="movie-image" />
        <div class="movie-header-info">
          <h2 class="movie-title">{{ movieDetails.name }}</h2>
          <div class="movie-rating">{{ movieDetails.rating }}</div>
          <el-rate v-model="movieDetails.rating" :colors="colors"></el-rate>
          <div class="movie-info">
            <span class="movie-release-year">{{ movieDetails.releaseYear }}</span>
            <span class="movie-genre">{{ movieDetails.genre }}</span>
          </div>
        </div>
      </div>
      <div class="movie-content">
        <p class="movie-cast">{{ movieDetails.cast }}</p>
        <h3>Comments</h3>
        <div class="comment" v-for="comment in comments" :key="comment.id">
          <div class="comment-header">
            <span class="comment-author">{{ comment.author }}</span>
            <span class="comment-date">{{ comment.date }}</span>
          </div>
          <p class="comment-content">{{ comment.content }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "MovieDetails",
  data() {
    return {
      movieDetails: {
        imgUrl: 'https://m.media-amazon.com/images/M/MV5BMDU2ZWJlMjktMTRhMy00ZTA5LWEzNDgtYmNmZTEwZTViZWJkXkEyXkFqcGdeQXVyNDQ2OTk4MzI@._V1_QL75_UX190_CR0,1,190,281_.jpg',
        name: 'Example Movie',
        rating: 4,
        releaseYear: '2022',
        genre: 'Science Fiction',
        cast: 'Example Cast',
      },
      comments: [
        { id: 1, author: 'John Doe', date: '2022-01-01', content: 'This is a comment.' },
        { id: 2, author: 'Jane Doe', date: '2022-01-02', content: 'This is another comment.' },
      ],
      colors: ['#99a9bf', '#f7ba2a', '#ff9900'],
    };
  },
};
</script>

<style scoped>
.container {
  background-color: #000;
  color: #fff;
  padding: 20px;
}

.movie {
  max-width: 800px;
  margin: 0 auto;
}

.movie-header {
  display: flex;
  gap: 20px;
}

.movie-image {
  width: 200px;
  height: 300px;
  object-fit: cover;
}

.movie-title {
  margin: 0;
  margin-bottom: 10px;
  font-size: 24px;
}

.movie-rating {
  margin-bottom: 10px;
  font-size: 20px;
}

.movie-info {
  font-size: 18px;
}

.movie-release-year, .movie-genre {
  margin-right: 10px;
}

.movie-cast {
  margin-top: 20px;
  margin-bottom: 20px;
}

.comment {
  margin-top: 20px;
  margin-bottom: 20px;
}

.comment-header {
  display: flex;
  justify-content: space-between;
  font-size: 14px;
  margin-bottom: 10px;
}

.comment-author {
  font-weight: bold;
}
</style>
