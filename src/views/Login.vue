<template>
  <div class="wrapper">

<!--       :style="{'background-image': `url(${require('@/assets/mcTorch.jpg')})`}"-->
<!--  >-->
    <div style="margin-left: 200px; margin-top: 150px; background-color: rgba(255,255,255,0); width: 350px; height: 300px; padding: 20px; border-radius: 1px">
      <img src="../assets/moonIcon.jpg" alt="" style="width: 77px; margin-left: 230px ">
      <div style="margin: 20px 0; text-align: left; font-size: 24px; color: white"><b>Welcome to Gerty's Home</b></div>
      <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" v-model="user.username"></el-input>
      <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password v-model="user.password"></el-input>
      <div style="margin: 10px 0; text-align: right">
        <el-button type=""  size="small" autocomplete="off" @click="login">Sign in</el-button>
        <el-button type="info" size="small" autocomplete="off">Sign up</el-button>
      </div>
    </div>
  </div>

</template>

<script>
export default {
  name: "Login",
  data(){
    return{
      user:{}
    }
  },
  methods:{
    login(){

    }
  }
}
</script>

<style>
  .wrapper{
    height: 100vh;
    /*background-image: linear-gradient(to bottom left, rgb(255, 97, 0), rgb(85, 0, 255));*/
    /*background-image: url('/assets/moon.jpg');*/
    background: url("~@/assets/blockBlack.png") no-repeat center center;
    /*background-size: 100% 100%;*/
    background-size: cover;

    overflow: hidden;
  }

</style>