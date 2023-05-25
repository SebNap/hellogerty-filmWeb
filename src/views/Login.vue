<template>

  <div class="wrapper">

<!--    <div class="star" ref="starsRef"></div>-->
<!--    <div class="meteor" ref="metreorRef" v-for="(item, index) in meteors" :key="index"></div>-->

    <div style="margin-left: 200px; margin-top: 100px; background-color: rgba(255,255,255,0); width: 350px; height: 300px; padding: 20px; border-radius: 1px">
      <img src="../assets/moonIcon.jpg" alt="" style="width: 130px; position: center ">
      <div style="margin: 20px 0; text-align: left; font-size: 24px; color: white"><b>Welcome to Gerty's Home</b></div>

      <el-form :model="user" :rules="rules" ref="userForm">
        <el-form-item prop="username">
          <el-input size="medium" style="border:2px solid orange; border-radius: 6px; margin: 10px 0" prefix-icon="el-icon-user" v-model="user.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password v-model="user.password"></el-input>
        </el-form-item>

        <div style="margin: 10px 0; text-align: right">
          <el-button type=""  size="small" autocomplete="off" @click="login">Sign in</el-button>
          <el-button type="info" size="small" autocomplete="off" @click="$router.push('/register')">Sign up</el-button>
        </div>
      </el-form>

    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data(){
    return{
      user:{},
      rules:{
        username: [
          { required: true, message: '请输入用户名', trigger:"blur"},
          { min: 1, max: 16, message: '长度1到16个字符', trigger: "blur"}
        ],
        password: [
          { required: true, message: '请输入密码', trigger:"blur"},
          { min: 1, max: 16, message: '长度1到16个字符', trigger: "blur"}
        ],
      }
    }
  },
  methods:{
    login() {
      //传数据前进行验证
      this.$refs["userForm"].validate((valid) => {
        if (valid){
          //如果合法 传到后端进行验证
          this.request.post("http://localhost:9090/user/login",this.user).then(res=>{
            if(res.code == "200"){
              localStorage.setItem("user", JSON.stringify(res.data)) //存储用户信息到浏览器
              this.$router.push("/")
              this.$message.success("登录成功")
            } else {
              this.$message.error(res.msg)
            }
          })
        } else {
          console.log("用户名或密码不合法!!!");
          return false;
        }

      });


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