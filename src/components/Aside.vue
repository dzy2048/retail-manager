<template>
  <el-row class="tac">
    <el-col :span="12">
      <el-menu
        default-active="3"
        router
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose">
        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-location"></i>
            <span>销售业务</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="/index/sales">开销售单</el-menu-item>
            <el-menu-item index="/index/order">订单管理</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-menu-item index="/index/customer">
          <i class="el-icon-menu"></i>
          <span slot="title">客户信息</span>
        </el-menu-item>
        <el-menu-item index="/index/repository">
          <i class="el-icon-document"></i>
          <span slot="title">库存管理</span>
        </el-menu-item>
        <el-menu-item index="/index/dispatch">
          <i class="el-icon-setting"></i>
          <span slot="title">库存调度</span>
        </el-menu-item>
      </el-menu>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: "Aside",
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    }
  }
}
</script>

<style scoped>
  .el-menu
  {
    overflow: hidden;
  }
  .router-link
  {
    text-decoration: none;
  }
</style>
