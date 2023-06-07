<template>
  <div class="top-movies">
    <h2>Top Movies</h2>
    <ul>
      <li v-for="(movie, index) in topMovies" :key="index">
        {{ movie.name }} - Rating: {{ movie.rating }}
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
    let res = await axios.get('http://localhost:9090/filmv3/top250');
    let shuffledMovies = this.shuffleArray(res.data);
    this.topMovies = shuffledMovies.slice(0, 10);
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
  right: 0;
  top: 0;
  width: 20%;
  background-color: #000;
  color: #fff;
  padding: 20px;
  height: 100vh;
  overflow-y: auto;
}
</style>
