<template>
<!--  <div style="margin-right: 200px; margin-left: 136px">-->
<!--    <h1 style="color: orange">welcome to gerty's home</h1>-->
<!--  </div>-->

<!--  <div><div class="meteor" ref="metreorRef" v-for="(item, index) in meteors" :key="index"></div></div>-->
  <div style="margin-right: 200px; margin-left: 136px">
    <h1 style="color: orange">welcome to gerty's home</h1>-->
    <div style="width: 1300px">
      <!--    <p>-->
      <!--      <el-button @click="setLoading">点我重新加载</el-button>-->
      <!--    </p>-->
      <el-skeleton style="width:500px; padding: 0px; color: #42b983" :loading="loading" animated :count="3">
        <template slot="template">
          <el-skeleton-item
              variant="image"
              style="width: 400px; height: 267px;"
          />
          <div style="padding: 14px;">
            <el-skeleton-item variant="h3" style="width: 50%;" />
            <div
                style="display: flex; align-items: center; margin-top: 16px; height: 16px;"
            >
              <el-skeleton-item variant="text" style="margin-right: 16px" />
              <el-skeleton-item variant="text" style="width: 30%;" />
            </div>
          </div>
        </template>
        <template>
          <el-card
              :body-style="{ padding: '0px', marginBottom: '1px' }"
              v-for="item in lists"
              :key="item.name"
          >
            <img :src="item.imgUrl" class="image multi-content" style=" width: 100%"/>
            <div style="padding: 14px;">
              <span>{{ item.name }}</span>
              <div class="bottom card-header">
                <span class="time">{{ currentDate }}</span>
                <el-button type="text" class="button">喜欢</el-button>
              </div>
            </div>
          </el-card>
        </template>
      </el-skeleton>
    </div>

  </div>


</template>

<script>
export default {
  name: "Home",
  data() {
    return {
      loading: true,
      currentDate: '2023-4-14',
      lists: [],
    }
  },
  mounted() {
    this.loading = false
    this.lists = [
      {
        imgUrl:
            'https://pic.imdb.cn/upload/star/2020/07/09/0ae9af732168ea13d873b41300f40011.jpg?imageView2/1/format/jpg/w/200/h/200',
        name: '《搏击俱乐部》',
      },
      {
        imgUrl:
            'https://img.mp.itc.cn/upload/20160401/25f897063c8e4d608e5f9cb4854aecf9_th.jpg',
        name: '《控方证人》',
      },
      {
        imgUrl:
            'https://img1.baidu.com/it/u=1917992144,1825566538&fm=253&fmt=auto&app=138&f=JPEG?w=931&h=500',
        name: '《燃烧女子的肖像》',
      },
    ]
  },
  methods: {
    setLoading() {
      this.loading = true
      setTimeout(() => (this.loading = false), 2000)
    },
  },
}

</script>

<style>


</style>