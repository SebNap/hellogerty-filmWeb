import '@fortawesome/fontawesome-free/css/all.css'

<template>
  <div id="app">
    <router-view/>

  </div>
</template>

<style>

html {
  overflow: hidden;
}

#app{
  min-height: 100vh;
}
#nprogress .bar {
  background-color: #000000 !important;
  background-image: linear-gradient(to left, #ffcc00 0%, #cf2626 50%, #8d1aff 100%) !important;
  height: 6px !important;
  z-index: 1500;
}
</style>

