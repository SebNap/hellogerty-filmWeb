<template>
  <div style="font-size: 12px ; line-height: 60px; display: flex">
    <div style="flex: 1; font-size: 20px">
      <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
    </div>


    <el-dropdown style="text-align:right ;width: 1300px; cursor: pointer">
      <div style="display: inline-block">
        <img :src="user.avatarUrl" alt=""
             style="width: 25px; border:2px solid orange; border-radius: 50%; position: relative; top: 8px; right: 5px">
        <span>{{user.nickname}}</span>

      </div>

      <i class="el-icon-user-solid" style="margin-left: 5px"></i>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item>个人信息</el-dropdown-item>
        <el-dropdown-item>
          <span style="text-decoration: none" @click="logout">退出</span>
<!--          <router-link to="/login" style="text-decoration: none">退出</router-link>-->
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>

  </div>
</template>

<!--<template>-->
<!--  <div>-->

<!--  </div>-->
<!--</template>-->

<script>
export default {
  name: "Header",
  props: {
    collapseBtnClass: String,
    collapse: Boolean
  },
  data(){
    return{
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  methods: {
    logout() {
      this.$router.push("/login")
      localStorage.removeItem("user")
      this.$message.success("退出成功")
    }
  }

}
</script>

<style scoped>

</style>