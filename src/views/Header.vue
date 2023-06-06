<template>
  <div style="font-size: 12px; line-height: 60px; display: flex; justify-content: space-between;">
    <div style="flex: 1; font-size: 20px">
      <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
    </div>

    <div style="margin-right: auto; margin-left: auto;">
      <el-input v-model="searchTerm" placeholder="Search for movies" @keyup.enter="search">
        <template #prepend>
          <el-button icon="el-icon-search" @click="search"></el-button>
        </template>
      </el-input>
    </div>

    <el-dropdown style="text-align: right; cursor: pointer">
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
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "Header",
  props: {
    collapseBtnClass: String,
    collapse: Boolean
  },
  data() {
    return {
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
      searchTerm: ''
    };
  },
  methods: {
    logout() {
      this.$router.push("/login");
      localStorage.removeItem("user");
      this.$message.success("退出成功");
    },
    async search() {
      const res = await axios.get('http://localhost:9090/filmv3/search', {
        params: { title: this.searchTerm }
      });
      this.$emit('search-result', res.data);
    },
  }
}
</script>

<style scoped>

</style>
