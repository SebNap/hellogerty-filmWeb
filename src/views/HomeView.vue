<template>
  <div>
    <el-container style="min-height: 100vh">
      <el-aside :width="setWidth + 'px'" style="background-color: rgb(238, 241, 246)">
        <el-menu :default-openeds="['1', '3']"
                 style="min-height: 100vh; overflow-x: hidden"
                 background-color="rgb(1,1,1)"
                 text-color="#fff"
                 active-text-color="#ffd04b"
                 :collapse-transition = "false"
                 :collapse="isCollapse"

        >
          <div style="height: 60px; line-height: 60px; text-align: center">
            <img src="../assets/logo.png" alt="" style="width: 20px; position: relative; top: 5px ">
            <b style="color: white">Gerty's Home</b>

          </div>

          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-message"></i>
              <span slot="title">导航一</span>
            </template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="1-1">选项1</el-menu-item>
              <el-menu-item index="1-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="1-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="1-4">
              <template slot="title">选项4</template>
              <el-menu-item index="1-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span slot="title">导航二</span>
            </template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="2-1">选项1</el-menu-item>
              <el-menu-item index="2-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="2-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="2-4">
              <template slot="title">选项4</template>
              <el-menu-item index="2-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-setting"></i>
              <span slot="title">导航三</span>
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
        </el-menu>
      </el-aside>

      <el-container>
        <el-header style=" font-size: 20px ; border-bottom: #ccc 1px solid; line-height: 60px; display: flex">
          <div>
            <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
          </div>


          <el-dropdown style="text-align:right ;width: 1300px; cursor: pointer">
            <span>王宇琪</span>
            <i class="el-icon-setting" style="margin-left: 5px"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>个人信息</el-dropdown-item>
              <el-dropdown-item>退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>

        </el-header>

        <el-main>
          <el-table :data="tableData">
            <el-table-column prop="date" label="日期" width="140">
            </el-table-column>
            <el-table-column prop="name" label="姓名" width="120">
            </el-table-column>
            <el-table-column prop="address" label="地址">
            </el-table-column>
          </el-table>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
// // @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'HomeView',
  // components: {
  //   HelloWorld
  // },
  data(){
    const item = {
      date: '2019-12-27',
      name: '王宇琪',
      address: '北京交通大学家属区畅园2号楼1108'
    };

    return{
      msg:"Gerty's Home",
      tableData: Array(10).fill(item),
      collapseBtnClass:'el-icon-s-fold',
      isCollapse: false,
      setWidth: 200
    }
  },
  methods:{
    collapse(){//点击收缩按钮触发
      this.isCollapse = !this.isCollapse
      if(this.isCollapse){
        this.setWidth = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
      }else{
        this.setWidth = 200
        this.collapseBtnClass = 'el-icon-s-unfold'
      }

    }
  }
}
</script>
