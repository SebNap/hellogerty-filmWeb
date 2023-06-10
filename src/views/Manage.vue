<template>
  <div style="background-color: black">
    <el-container style="min-height: 100vh">
      <el-aside
                class="box77 fixed-element"
                :width="setWidth + 'px'"
                style="background-color: #222;

                 box-shadow: transparent;
                 border-right: rgba(255,255,255,0) 0px solid;
                 /*border-left: rgba(255,255,255,0.23) 4px solid;*/
                 left: 0; bottom: 0;
                 z-index: 0"
      >
        <Aside :is-collapse = "isCollapse"/>
<!--        <el-header class="fixed-element" style=" border-bottom: rgba(255,255,255,0.23) 1px solid; z-index: 1">-->
<!--        </el-header>-->

      </el-aside>
      <el-header class="fixed-element" style=" border-bottom: rgba(255,255,255,0.23) 0px solid;z-index: 100;">
        <Header :collapse-btn-class="collapseBtnClass" :collapse="collapse"/>

        <!--          background-image: linear-gradient(to right, #ffcc00, #cf2626, #8d1aff);-->

      </el-header>
      <div><Album/></div>





      <el-container class="main-content">
<!--          background-color="rgb(1,1,1)"-->
        <el-main class="main-content">
<!--          //page-->
<!--          当前页面的子路由会在routerView里展示-->
          <router-view/>

        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>


// // @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'
import Aside from "@/components/Aside";
import Header from "@/views/Header";
import TopMovies from "@/views/TopMovies";
import Album from "@/views/Album";

export default {
  name: 'HomeView',
  data(){
    return{
      collapseBtnClass:'el-icon-s-unfold',
      isCollapse: false,
      setWidth: 200,
    }
  },
  components: {
    Aside,
    Header,
    Album
  },
  methods:{
    collapse(){//点击收缩按钮触发
      this.isCollapse = !this.isCollapse
      if(this.isCollapse){
        this.setWidth = 64
        // this.collapseBtnClass = 'el-icon-s-unfold'
        this.collapseBtnClass = 'el-icon-s-fold'
      }else{
        this.setWidth = 200
        // this.collapseBtnClass = 'el-icon-s-fold'
        this.collapseBtnClass = 'el-icon-s-unfold'
      }
    }
  }
}
</script>

<style>
.box77{
  background-image: linear-gradient(to bottom, #ffcc00 0%, #cf2626 50%, #8d1aff 100%);
  /*background-size: 100% 100%;*/
  background-size: cover;
  /*overflow: hidden;*/

  overflow: auto;
  scrollbar-width: none; /* Firefox */
  -ms-overflow-style: none;  /* IE 10+ */
}

.fixed-element {
  position: fixed;
}

.box77::-webkit-scrollbar { /* WebKit */
  width: 0;
  height: 0;
}

.main-content {
  padding-top: 30px; /* 根据您的页眉高度进行调整 */
  padding-left: 116px; /* 根据您的 aside 宽度进行调整 */
  overflow: hidden; /* 隐藏垂直滚动条 */
  scrollbar-width: none; /* 隐藏滚动条（适用于 Firefox） */
  -ms-overflow-style: none; /* 隐藏滚动条（适用于 IE 10+） */
}


.main-content::-webkit-scrollbar {
  width: 0;
  height: 0;
  display: none;
}

</style>