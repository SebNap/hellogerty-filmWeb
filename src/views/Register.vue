<template>
  <div class="wrapper">

<!--       :style="{'background-image': `url(${require('@/assets/mcTorch.jpg')})`}"-->
<!--  >-->
    <div style="margin-left: 200px; margin-top: 100px; background-color: rgba(255,255,255,0); width: 350px; height: 300px; padding: 20px; border-radius: 1px">
<!--      <img src="../assets/moonIcon.jpg" alt="" style="width: 130px; position: center ">-->
      <div style="margin: 20px 0; text-align: left; font-size: 24px; color: white"><b>Welcome to Gerty's Home</b></div>

      <el-form :model="user" :rules="rules" ref="userForm">
        <el-form-item prop="username">
          <el-input placeholder="请输入账号" size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" v-model="user.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input placeholder="请输入密码" size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password v-model="user.password"></el-input>
        </el-form-item>
        <el-form-item prop="confirmPassword">
          <el-input placeholder="请再次输入密码" size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password v-model="user.confirmPassword"></el-input>
        </el-form-item>

        <div style="margin: 10px 0; text-align: right">
          <el-button type=""  size="small" autocomplete="off" @click="register">注册</el-button>
          <el-button type="info" size="small" autocomplete="off" @click="$router.push('/login')">返回登录</el-button>
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
        confirmPassword: [
          { required: true, message: '请再次输入密码', trigger:"blur"},
          { min: 1, max: 16, message: '长度1到16个字符', trigger: "blur"}
        ]
      }
    }
  },
  methods:{
    register() {
      //传数据前进行验证
      this.$refs["userForm"].validate((valid) => {
        if (valid){
          //如果合法 传到后端进行验证
          if (this.user.password !== this.user.confirmPassword) {
            this.$message.error("两次密码输入不一致")
            return false
          }
          this.request.post("http://localhost:9090/user/register",this.user).then(res=>{
            if(res.code == "200"){


              this.$message.success("注册成功")
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