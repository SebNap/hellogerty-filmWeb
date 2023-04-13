<template>
  <div>
    <el-container style="min-height: 100vh">
      <el-aside :width="setWidth + 'px'" style="background-color: rgb(238, 241, 246); box-shadow: 2px 0 6px black">
        <Aside :is-collapse = "isCollapse"/>
      </el-aside>

      <el-container
          background-color="rgb(1,1,1)"
      >
        <el-header style="  border-bottom: #ccc 1px solid">
          <Header :collapse-btn-class="collapseBtnClass" :collapse="collapse"/>


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
      isCollapse: true,
      setWidth: 64
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
