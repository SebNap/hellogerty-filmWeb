<template>
  <div style="font-size: 12px ; line-height: 60px; display: flex">
    <div style="flex: 1; font-size: 20px">
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

  </div>
</template>

<script>
export default {
  name: "Header",
  props: {
    collapseBtnClass: String,
    collapse: Boolean

  }
}
</script>

<style scoped>

</style>