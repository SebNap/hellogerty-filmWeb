<template>
  <el-menu
      style="min-height: 100vh; overflow-x: hidden; border-right-color: transparent;border-top-color: transparent;border-bottom-color: transparent"
      background-color= '#000'
      text-color="#fff"
      active-text-color= #64a6e7
      :collapse-transition="false"
      :collapse="isCollapse"
  >
    <div style="height: 60px; line-height: 60px; text-align: center">
      <img src="../assets/moonIcon.jpg" alt="" style="width: 30px; position: relative; top: 5px">
      <span class="gradient-text">Gerty's Home</span>
    </div>

    <el-menu-item index="/home" @click="$router.push('/home')">
      <i class="el-icon-house"></i>
      <span @click="$router.push('/home')"><b> Home</b></span>
    </el-menu-item>


    <el-menu-item index="/user" @click="$router.push('/user')">
      <i class="el-icon-search"></i>
      <span @click="$router.push('/user')"><b> User</b></span>
    </el-menu-item>
    <el-menu-item index="/film" @click="$router.push('/film')">
      <i class="el-icon-house"></i>
      <span @click="$router.push('/film')"><b> FilmDetail</b></span>
    </el-menu-item>
    <el-menu-item index="/filmform" @click="$router.push('/filmform')">
      <i class="el-icon-house"></i>
      <span @click="$router.push('/filmform')"><b> Film</b></span>
    </el-menu-item>


    <div style="margin-top: 300%" hidden>
      <el-button style="border-radius: 50%" type="" size="small" autocomplete="off" @click="">mode</el-button>
    </div>

    <div class="audio-player">
      <audio controls>
        <source src="../assets/Ceremony - Cold Cold Night.mp3" type="audio/mpeg">
        Your browser does not support the audio element.
      </audio>
    </div>

  </el-menu>
</template>

<script>
export default {
  name: "Aside",
  props: {
    isCollapse: Boolean
  },
  methods: {}
};
</script>

<style scoped>

.gradient-text {
  background: linear-gradient(to bottom right, #ffcc00 0%, #cf2626 50%, #8d1aff 100%);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  font-weight: bold;
  display: inline-block;
}
.el-menu-item.is-active .gradient-text {
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  background: linear-gradient(to bottom right, #ffcc00 0%, #cf2626 50%, #8d1aff 100%);
}
.audio-player {
  position: absolute;
  bottom: 25px;
  width: 90%;
  left: 5%;
  background-color: #222;
  /*background: linear-gradient(to bottom right, #ffcc00 0%, #cf2626 50%, #8d1aff 100%);*/
  border-radius: 50px;
  padding: 2px;
}

.audio-player audio {
  width: 100%;
  outline: none;
}

.audio-player audio::-webkit-media-controls-panel {
  background-color: #222;
}

/*.audio-player audio::-webkit-media-controls-play-button,*/
/*.audio-player audio::-webkit-media-controls-volume-slider-container {*/
/*  color: #fff;*/
/*}*/


</style>
