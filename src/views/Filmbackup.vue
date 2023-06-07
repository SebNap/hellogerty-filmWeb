<template>
  <div style="margin-right: 200px; margin-left: 136px">
    <h1 style="color: lawngreen">a bad Film Page</h1>

    <el-carousel :interval="4000" type="card" height="200px">
      <el-carousel-item v-for="item in imgs" :key="item">
        <img :src="item" alt="" style="height: 100%; width: 100%">

        <h3 class="medium">{{ item }}</h3>
      </el-carousel-item>
    </el-carousel>
    <h2 style="color: lawngreen">《The Moon》</h2>
    <a>1</a>
    <a style="color: #99a9bf; padding: 20px">《月球》是由邓肯·琼斯执导的一部经典科幻悬疑片，山姆·洛克威尔，罗宾·查克等参加演出。该片讲述了月球能源公司矿工萨姆・贝尔在月球基地开采能源，渴望回家的他遭遇了一起事故，随后又遇见了另一个自己，进而发现了公司不可告人秘密的故事。</a>

    <div class="block">
      <span class="demonstration">区分颜色</span>
      <el-rate

          v-model="value2"
          :colors="colors">
      </el-rate>
    </div>

  </div>

</template>

<script>
export default {
  name: "Film",
  data(){
    return {
      imgs: [
        'https://img2.doubanio.com/view/group_topic/l/public/p499647763.webp',
        'https://img2.doubanio.com/view/group_topic/l/public/p499647762.webp',
        'https://img21.mtime.cn/mg/2011/08/15/182711.21868141.jpg',
        'https://pic3.zhimg.com/50/v2-a3d0bf3775178d3147c284de1ddd090a_hd.jpg'

      ]
    }
  }
}
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}

</style>