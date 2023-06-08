<template>
  <div class="popular-movies" v-if="topMovies.length > 0">
    <h2>Top Movies</h2>
    <ul>
      <li v-for="(movie, index) in topMovies" :key="index">
<!--        {{ movie.title }} - Rating: {{ movie.voteAverage }}-->
        《{{ movie.title }}》
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data() {
    return {
      topMovies: [],
    };
  },
  async mounted() {
    try {
      let res = await axios.get('http://localhost:9090/filmv3/top250');
      let shuffledMovies = this.shuffleArray(res.data);
      this.topMovies = shuffledMovies.slice(0, 25);
      console.log(this.topMovies); // log the data
    } catch (error) {
      console.error('Failed to fetch top movies:', error);
    }
  },
  methods: {
    shuffleArray(array) {
      for (let i = array.length - 1; i > 0; i--) {
        let j = Math.floor(Math.random() * (i + 1));
        [array[i], array[j]] = [array[j], array[i]];
      }
      return array;
    },
  },
};
</script>

<style>
.top-movies {
  position: fixed;
  right: 40px;
  margin-left: 1230px;
  border-radius: 8px;
  top: 84px;
  /*width: 18%;*/
  background-color: #222;
  color: #ccc;
  padding: 20px;
  /*height: 80vh;*/
  overflow-y: auto;
  font-size: 12px;
}
</style>
