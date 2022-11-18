<template>
  <div>
    <div class="top">
      <el-form :inline="true">
        <el-form-item label="用户名">
          <el-input type="text" v-model="username" ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary">新增</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="foot">
      <el-table
        :data="tableData"
        height="400"
        border
        style="width: 100%;height: 100%">
        <el-table-column
          prop="name"
          label="姓名"
          width="180">
        </el-table-column>
        <el-table-column
          prop="address"
          label="地址">
        </el-table-column>
        <el-table-column
          prop="phone"
          label="联系方式">
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "Customer",
  data() {
    return {
      username: '',
      tableData: [
        {
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄',
          phone: '12345'
        },
        {
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄',
          phone: '12345'
        },
        {
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄',
          phone: '12345'
        },
        {
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄',
          phone: '12345'
        },
        {
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄',
          phone: '12345'
        },
        {
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄',
          phone: '12345'
        }
      ]
    }
  }
}
</script>

<style scoped>
  .top
  {
    margin-top: 5%;
  }
  .foot
  {
    margin-top: 3%;
  }
</style>
