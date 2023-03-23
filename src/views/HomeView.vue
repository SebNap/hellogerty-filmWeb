<template>
  <div>
    <el-container style="min-height: 100vh">
      <el-aside :width="setWidth + 'px'" style="background-color: rgb(238, 241, 246); box-shadow: 2px 0 6px black">
        <el-menu :default-openeds="['1', '3']"
                 style="min-height: 100vh; overflow-x: hidden; border-right-color: transparent;border-top-color: transparent;border-bottom-color: transparent"
                 background-color="rgb(1,1,1)"
                 text-color="#fff"
                 active-text-color="#ffd04b"
                 :collapse-transition = "false"
                 :collapse="isCollapse"

        >
          <div style="height: 60px; line-height: 60px; text-align: center">
            <img src="../assets/moonIcon.jpg" alt="" style="width: 30px; position: relative; top: 5px ">
            <b style="color: white"> Gerty's Home </b>

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

      <el-container
          background-color="rgb(1,1,1)"
      >
        <el-header style=" font-size: 20px ; border-bottom: #ccc 1px solid; line-height: 60px; display: flex">
          <div>
            <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
          </div>


          <el-dropdown style="text-align:right ;width: 1300px; cursor: pointer">
            <span><b>Sebastian Castellanos</b></span>
            <i class="el-icon-user-solid" style="margin-left: 5px"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>个人信息</el-dropdown-item>
              <el-dropdown-item>退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>

        </el-header>

        <el-main>
<!--          //page-->
          <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item><a href="/">后台管理</a></el-breadcrumb-item>
            <el-breadcrumb-item>where is gerty</el-breadcrumb-item>
          </el-breadcrumb>




<!--          //search part-->
          <div style="margin: 20px 0">
            <el-input style="width: 279px" placeholder="Enter Film's Name" suffix-icon="el-icon-search">
            </el-input>
            <el-button type="info" class="ml-5"><b>Search</b></el-button>
          </div>

<!--          //new information CRUD-->
          <div style="margin: 10px 0">
            <el-button type="info">NEW<i class="el-icon-circle-plus-outline"></i></el-button>
            <el-button type="danger">DELETE<i class="el-icon-remove-outline"></i></el-button>
            <el-button >UPLOAD<i class="el-icon-upload2"></i></el-button>
            <el-button >DOWNLOAD<i class="el-icon-download"></i></el-button>

          </div>

<!--          //table part-->
          <el-table :data="tableData" border stripe>
            <el-table-column prop="id" label="ID" width="60"></el-table-column>
            <el-table-column prop="username" label=UserName width="100"></el-table-column>
            <el-table-column prop="nickname" label="NickName" width="100"></el-table-column>
            <el-table-column prop="email" label="Email"></el-table-column>
            <el-table-column prop="phone" label="PhoneNum"></el-table-column>
            <el-table-column prop="address" label="Address"></el-table-column>



            <el-table-column>
              <template slot-scope="scope">
                <el-button type="warning">EDIT <i class="el-icon-edit"></i></el-button>
                <el-button type="danger">DELE <i class="el-icon-remove-outline"></i></el-button>
              </template>
            </el-table-column>
          </el-table>
          <div style="padding: 10px 0">
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="pageNum"
                :page-sizes="[2, 5, 10, 20]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="total"
            >
            </el-pagination>


          </div>

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
  data(){
    return{
      tableData:[],
      total: 0,
      msg:"Gerty's Home",
      pageNum: 1,
      pageSize: 2,

      collapseBtnClass:'el-icon-s-fold',
      isCollapse: false,
      setWidth: 200
    }
  },
  created(){
    //请求分页查询数据
    this.load()
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

    },

    load(){
      fetch("http://localhost:9090/user/page?pageNum="+this.pageNum+"&pageSize="+this.pageSize)
          .then(res => res.json()).then(res => {
        console.log(res)
        this.tableData = res.data
        this.total = res.total
      })
    },


    handleSizeChange(pageSize){
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      console.log(pageNum)
      this.pageNum = pageNum
      this.load()
    }
  }
}
</script>
