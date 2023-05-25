<template>
  <el-menu
           style="min-height: 100vh; overflow-x: hidden; border-right-color: transparent;border-top-color: transparent;border-bottom-color: transparent"
           background-color="rgb(1,1,1)"
           text-color="#fff"
           active-text-color="#ffd04b"
           :collapse-transition = "false"
           :collapse="isCollapse"


  >
<!--    :default-openeds="['1', '3']"-->

    <div style="height: 60px; line-height: 60px; text-align: center">
      <img src="../assets/moonIcon.jpg" alt="" style="width: 30px; position: relative; top: 5px ">
      <b style="color: white"> Gerty's Home </b>

    </div>

    <el-submenu  index="1">
      <template slot="title">
        <i class="el-icon-house"></i>
        <span @click="$router.push('/home')" slot="title">主页</span>
<!--        <router-link to="/home" style="color: white; text-decoration: none"></router-link>-->
      </template>
    </el-submenu>
    <el-submenu index="2">
      <template slot="title">
        <i class="el-icon-search"></i>
        <span @click="$router.push('/user')" slot="title">探索</span>
<!--        <router-link to="/user" style="color: white; text-decoration: none">探索</router-link>-->
      </template>
    </el-submenu>
    <el-submenu  index="3">
      <template slot="title">
        <i class="el-icon-house"></i>
        <span @click="$router.push('/film')" slot="title">电影</span>
        <!--        <router-link to="/home" style="color: white; text-decoration: none"></router-link>-->
      </template>
    </el-submenu>


    <el-submenu index="4">
      <template slot="title">
        <i class="el-icon-message"></i>
        <span slot="title" >消息</span>
      </template>
      <el-menu-item-group>
        <template slot="title">分组一</template>
        <el-menu-item index="3-1">选项1</el-menu-item>
        <el-menu-item index="3-2">选项2</el-menu-item>
      </el-menu-item-group>
      <el-menu-item-group title="分组2">
        <el-menu-item index="3-3">选项3</el-menu-item>
      </el-menu-item-group>
      <el-submenu index="3-4">
        <template slot="title">选项4</template>
        <el-menu-item index="3-4-1">选项4-1</el-menu-item>
      </el-submenu>
    </el-submenu>

    <div style="margin-top: 300%" hidden>
      <el-button style="border-radius: 50%" type=""  size="small" autocomplete="off" @click="">mode</el-button>
    </div>

  </el-menu>
</template>

<script>
export default {
  name: "Aside",
  props: {
    isCollapse: Boolean

  },
  methods: {

  }

}
</script>

<style scoped>

</style>