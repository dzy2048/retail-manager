<template>
  <div>
    <div class="top">
      <el-form :inline="true">
        <el-form-item>
          <el-input></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary">新增</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div>
      <el-table :data="tableData" stripe style="width: 100%">
        <el-table-column prop="goodName" label="商品名称"/>
        <el-table-column prop="buyPrice" label="进货价格" />
        <el-table-column prop="wholePrice" label="批发价格" />
        <el-table-column prop="sellPrice" label="零售价格" />
        <el-table-column prop="unit" label="单位" />
        <el-table-column fixed="right" label="操作" width="100">
          <template slot-scope="scope">
            <el-button @click="" type="text" size="small" style="color: blue">修改价格</el-button>
            <el-popconfirm title="确定删除吗？" @confirm="">
              <el-button type="text" slot="reference" size="small" style="color: red">删除</el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
export default {
  name: "Good",
  data() {
    return {
      tableData: []
    }
  },
  created() {
    request.get('http://localhost:9090/good').then(res=>{
      this.tableData = res.data;
    })
  }
}
</script>

<style scoped>

</style>
