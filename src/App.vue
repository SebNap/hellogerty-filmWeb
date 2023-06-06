<template>
  <div id="app">
    <router-view/>
<!--    背景音乐-->
    <audio src="./assets/qingtian.mp3"
           loop='true'
           autoplay="autoplay"
           ref="MusicPlay"
           hidden
    ></audio>

    <!--    <nav>-->
<!--      <router-link to="/">Home</router-link> |-->
<!--      <router-link to="/about">About</router-link>-->
<!--    </nav>-->

  </div>
</template>

<!--<style>-->
<!--#app {-->
<!--  font-family: Avenir, Helvetica, Arial, sans-serif;-->
<!--  -webkit-font-smoothing: antialiased;-->
<!--  -moz-osx-font-smoothing: grayscale;-->
<!--  text-align: center;-->
<!--  color: #2c3e50;-->
<!--}-->

<!--nav {-->
<!--  padding: 30px;-->
<!--}-->

<!--nav a {-->
<!--  font-weight: bold;-->
<!--  color: #2c3e50;-->
<!--}-->

<!--nav a.router-link-exact-active {-->
<!--  color: #42b983;-->
<!--}-->
<!--</style>-->


<script setup lang="ts">
  const meteors = 10;  // 流星个数
</script>



<style>

html {
  overflow: hidden;
}

/* 或 */

/*body {*/
/*  overflow: hidden;*/
/*}*/



.meteor{
  position: absolute;
  width: 300px;
  height: 1px;
  top: 200%;
  transform: rotate(-45deg);
  background-image: linear-gradient(to right, #fff, rgba(255,255,255,0));
}

.meteor::before {
  content: "";
  position: absolute;
  width: 4px;
  height: 5px;
  border-radius: 50%;
  margin-top: -2px;
  background: rgba(255, 255, 255, 0.26);
  box-shadow: 0 0 15px 3px #fff;
}

@keyframes meteorAnim {
  0% {
    opacity: 1;
    margin-top: -300px;
    margin-right: -300px;
  }
  12% {
    opacity: 0;
  }
  15% {
    margin-top: 300px;
    margin-left: -600px;
    opacity: 0;
  }
  100% {
    opacity: 0;
  }
}



#app{
  min-height: 100vh;
}

#nprogress .bar {
  background-color: #000000 !important;
  background-image: linear-gradient(to left, #ffcc00 0%, #cf2626 50%, #8d1aff 100%) !important;
  height: 6px !important;

}

</style>