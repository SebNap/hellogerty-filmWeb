<template>
  <div style="margin-right: 200px; margin-left: 136px">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item><a href="/">电影表</a></el-breadcrumb-item>
      <el-breadcrumb-item>where is gerty</el-breadcrumb-item>
    </el-breadcrumb>


    <!--          //search part-->
    <div style="margin: 20px 0">
      <el-input style="width: 279px" placeholder="Enter Film's Name" suffix-icon="el-icon-search" v-model="title">
      </el-input>
      <el-button type="info" class="ml-5" @click="load"><b>Search</b></el-button>
    </div>

    <!--          //new information CRUD-->
    <div style="margin: 10px 0">
      <el-button type="info" @click="addFilm">NEW<i class="el-icon-circle-plus-outline"></i></el-button>
      <el-button type="danger">DELETE<i class="el-icon-remove-outline"></i></el-button>
      <el-button >UPLOAD<i class="el-icon-upload2"></i></el-button>
      <el-button >DOWNLOAD<i class="el-icon-download"></i></el-button>

    </div>

    <!--          //table part-->
    <el-table :data="tableData" border stripe style="background-color: black; border-radius: 6px">
      <el-table-column prop="movieID" label="movieID" width="100"></el-table-column>
      <el-table-column prop="imageURL" label="imageURL" width="300"></el-table-column>
      <el-table-column prop="title" label="title" width="120"></el-table-column>
      <el-table-column prop="voteAverage" label="voteAverage"></el-table-column>
<!--      <el-table-column prop="voteCount" label="PhoneNum"></el-table-column>-->
<!--      <el-table-column prop="address" label="Address"></el-table-column>-->



      <el-table-column>
        <template slot-scope="scope">
          <el-button type="warning" @click="editFilm(scope.row)">EDIT <i class="el-icon-edit"></i></el-button>
          <el-button type="danger" @click="deleteFilm(scope.row)">DELE <i class="el-icon-remove-outline"></i></el-button>
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

  </div>

</template>

<script>


import axios from 'axios';

export default {
  name: "Filmform",
  data(){
    return{
      tableData:[],
      total: 0,
      title:"",
      msg:"Gerty's Home",
      pageNum: 1,
      pageSize: 2
    }
  },
  created(){
    this.load()//请求分页查询
  },
  methods:{

    load(){
      this.request.get("http://localhost:9090/filmv3/page",{
        params:{
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          title: this.title
        }
      })
          .then(res => {
            console.log(res)

            this.tableData = res.records
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
    },
    editFilm(row) {
      console.log('Editing:', row)
      // 这里放你的编辑代码
      // 让用户输入新的标题
      let newTitle = prompt('Enter new title', row.title)
      if(newTitle) {
        // 如果用户输入了新的标题，更新 row 对象
        row.title = newTitle
        // 然后发送 POST 请求到服务器更新数据
        axios.post(`http://localhost:9090/filmv3`, row)
            .then(response => {
              console.log(response)
              // 如果更新成功，重新加载电影数据
              if(response.data) {
                this.load()
              }
            })
            .catch(error => {
              console.error(error)
              // 处理错误
            });
      }
    },

    deleteFilm(row) {
      console.log('Deleting:', row)
      // 这里放你的删除代码
      axios.delete(`http://localhost:9090/filmv3/${row.movieID}`)
          .then(response => {
            console.log(response)
            // 如果删除成功，重新加载电影数据
            if(response.data) {
              this.load()
            }
          })
          .catch(error => {
            console.error(error)
            // 处理错误
          });
    },
    addFilm() {
      // 创建一个空对象，用于存放新电影的数据
      let newFilm = {
        movieID: '',
        imageURL: '',
        title: '',
        voteAverage: 0,
        // 如果有其他字段，也可以在这里添加
      }

      // 询问用户输入新电影的信息
      newFilm.movieID = prompt('Enter movie ID');
      newFilm.imageURL = prompt('Enter image URL');
      newFilm.title = prompt('Enter title');
      newFilm.voteAverage = prompt('Enter vote average');
      // 如果有其他字段，也可以在这里询问用户输入

      // 然后用POST请求将新电影的数据发送到服务器
      axios.post(`http://localhost:9090/filmv3`, newFilm)
          .then(response => {
            console.log(response)
            // 如果添加成功，重新加载电影数据
            if (response.data) {
              this.load()
            }
          })
          .catch(error => {
            console.error(error)
            // 处理错误
          });
    },

  }

}
</script>

<style scoped>

</style>