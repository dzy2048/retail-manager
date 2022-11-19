<template>
  <div>
    <div class="left">
      <el-form :inline="true">
        <el-form-item label="仓库号">
          <el-input type="text"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary">查询</el-button>
        </el-form-item>
      </el-form>
      <el-table
        :data="tableData_target"
        height="500"
        border>
        <el-table-column
          prop="name"
          label="商品名称"
          width="180">
        </el-table-column>
        <el-table-column
          prop="quantity"
          label="数量">
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="60">
          <template slot-scope="scope">
            <el-button type="text" size="small">调度</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!--中间区域-->
    <div class="center">
      <el-button type="primary">&lt;</el-button>
      <el-button type="primary">&gt;</el-button>
    </div>
    <div class="right">
      <el-form :inline="true">
        <el-form-item label="仓库号">
          <el-input type="text"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary">查询</el-button>
        </el-form-item>
      </el-form>
      <el-table
        :data="tableData_dispatch"
        height="500"
        border>
        <el-table-column
          prop="name"
          label="商品名称"
          width="180">
        </el-table-column>
        <el-table-column
          prop="quantity"
          label="数量">
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="60">
          <template slot-scope="scope">
            <el-button type="text" size="small">调度</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "Dispatch",
  data() {
    return {
      tableData_target: [
        {
          name: '苹果',
          quantity: 10
        },
        {
          name: '苹果',
          quantity: 10
        },
        {
          name: '苹果',
          quantity: 10
        },
        {
          name: '苹果',
          quantity: 10
        }
      ],
      tableData_dispatch: [
        {
          name: '香蕉',
          quantity: 20
        },
        {
          name: '香蕉',
          quantity: 20
        },
        {
          name: '香蕉',
          quantity: 20
        },
        {
          name: '香蕉',
          quantity: 20
        }
      ]
    }
  }
}
</script>

<style scoped>
  .left,.center,.right
  {
    float: left;
  }
  .center,.right
  {
    margin-left: 40px;
  }
  .center
  {
    padding-top: 20%;
  }
  .el-table
  {
    margin-top: 20px;
    width: 420px;
  }
</style>
