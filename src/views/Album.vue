<template>
  <img :src="require('@/assets/CeremonyPic.jpg')" :class="['imageA', ceremonyClicked ? 'imageAA' : '']" />
</template>

<script>
import {EventBus} from "@/event-bus";

export default {
  name: "Album",
  data() {
    return {
      ceremonyClicked: false,
    };
  },
  mounted() {
    // this.fetchMovies();
    // 新增以下两行代码
    EventBus.$on('play-status-changed', (isPlaying) => {
      this.isPlaying = isPlaying;
      // this.changeMovieRatingColor(); // 你需要实现这个函数，改变电影评分的背景颜色
      this.ceremonyClicked = !this.ceremonyClicked;

    });
  },
}
</script>

<style scoped>

.imageA {
  /*display: none;*/
  position: fixed;
  width: 0px;
  height: 0px;
  object-fit: cover;

  right: 0px;
  z-index: 1;
  box-shadow: -300px 600px 1100px 50px transparent;

}
.imageAA {
  position: fixed;
  width: 180px;
  height: 180px;
  object-fit: cover;
  right: 1215px;
  top: -5px;
  z-index: 12;

  /* 将图片裁剪成圆形 */
  border-radius: 50%;

  /* 添加内阴影来模拟黑胶唱片的凹槽 */
  box-shadow: 2px 2px 8px #606060;


  /* 添加一个小的白色圆点来表示黑胶唱片的中心 */
  background: radial-gradient(circle at center, white 100%, transparent 100%);

  /* 为黑胶唱片添加一个轮廓 */
  border: 40px solid #000;

  /* 添加动画 */
  animation: spin 5s linear infinite;  /*动画名称为spin，持续时间为5秒，线性变化，无限次数*/
}


@keyframes spin {
  from { transform: rotate(0deg); }
  to { transform: rotate(360deg); }
}


</style>