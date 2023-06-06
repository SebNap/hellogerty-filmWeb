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
      <el-button type="info">NEW<i class="el-icon-circle-plus-outline"></i></el-button>
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

  </div>

</template>

<script>
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





      // fetch("http://localhost:9090/user/page?pageNum="+this.pageNum+"&pageSize="+this.pageSize + "&username=" + this.username)
      //     .then(res => res.json()).then(res => {
      //   console.log(res)
      //   this.tableData = res.data
      //   this.total = res.total
      // })
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

<style scoped>

</style>