<template>
  <div id="index">
    <el-container>
      <el-header>
        <Top></Top>
      </el-header>
      <el-container>
        <el-aside width="200px">
          <Aside></Aside>
        </el-aside>
        <el-main style="min-height:calc(100vh - 60px)">
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import Top from '../components/Top'
import Aside from '../components/Aside'

export default {
  name: "Index",
  components: {
    Top,
    Aside
  },
  data() {
    return {

    }
  }
}
</script>

<style type="text/css">
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }
  .el-aside {
    color: #333;
    height: 100%;
  }
  html,body,.el-container,#index
  {
    padding: 0px;
    margin: 0px;
    height: 100%;
  }
</style>
