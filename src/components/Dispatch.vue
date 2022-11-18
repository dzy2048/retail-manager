<template>
  <div>
    <div class="left">
      <el-dropdown trigger="click">
        <el-button type="primary">
          选择仓库<i class="el-icon-arrow-down el-icon--right"></i>
        </el-button>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>仓库1</el-dropdown-item>
          <el-dropdown-item>仓库2</el-dropdown-item>
          <el-dropdown-item>仓库3</el-dropdown-item>
          <el-dropdown-item>仓库4</el-dropdown-item>
          <el-dropdown-item>仓库5</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <el-table
        :data="tableData_target"
        height="400"
        border
        style="width: 100%">
        <el-table-column
          prop="name"
          label="商品名称"
          width="180">
        </el-table-column>
        <el-table-column
          prop="quantity"
          label="数量">
        </el-table-column>
      </el-table>
    </div>
    <div class="center">
      <h2>调度</h2>
    </div>
    <div class="right">
      <el-dropdown trigger="click">
        <el-button type="primary">
          选择仓库<i class="el-icon-arrow-down el-icon--right"></i>
        </el-button>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>仓库1</el-dropdown-item>
          <el-dropdown-item>仓库2</el-dropdown-item>
          <el-dropdown-item>仓库3</el-dropdown-item>
          <el-dropdown-item>仓库4</el-dropdown-item>
          <el-dropdown-item>仓库5</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <el-table
        :data="tableData_dispatch"
        height="400"
        border
        style="width: 100%">
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
    margin-left: 20px;
  }
  .el-table
  {
    margin-top: 40px;
  }
</style>
