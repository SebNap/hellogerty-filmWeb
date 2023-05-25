<template>
  <div style="background-color: black">
    <el-container style="min-height: 100vh">
      <el-aside
                class="box77"
                :width="setWidth + 'px'"
                style="background-color: rgb(0,0,0);

                 box-shadow: orange;
                 border-right: rgba(255,255,255,0.23) 1px solid;
                 /*border-left: rgba(255,255,255,0.23) 4px solid;*/
                 left: 0; bottom: 0"
      >
        <Aside :is-collapse = "isCollapse"/>
      </el-aside>

      <el-container>
<!--          background-color="rgb(1,1,1)"-->

        <el-header style=" border-bottom: rgba(255,255,255,0.23) 1px solid">
          <Header :collapse-btn-class="collapseBtnClass" :collapse="collapse"/>

<!--          background-image: linear-gradient(to right, #ffcc00, #cf2626, #8d1aff);-->

        </el-header>

        <el-main>
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

export default {
  name: 'HomeView',
  data(){
    return{
      collapseBtnClass:'el-icon-s-fold',
      isCollapse: false,
      setWidth: 200
    }
  },
  components: {
    Aside,
    Header
  },
  methods:{
    collapse(){//点击收缩按钮触发
      this.isCollapse = !this.isCollapse
      if(this.isCollapse){
        this.setWidth = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
      }else{
        this.setWidth = 200
        this.collapseBtnClass = 'el-icon-s-fold'
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
}

</style>