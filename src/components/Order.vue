<template>
  <div>
    <div class="top">
      <el-form :inline="true">
        <el-form-item label="订单号">
          <el-input type="text" v-model="username" ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary">查询</el-button>
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
          prop="orderID"
          label="订单号"
          width="180">
        </el-table-column>
        <el-table-column
          prop="user"
          label="用户">
        </el-table-column>
        <el-table-column
          prop="customer"
          label="客户">
        </el-table-column>
        <el-table-column
          prop="time"
          label="时间">
        </el-table-column>
        <el-table-column
          prop="state"
          label="状态">
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="warning">退款 </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "Order",
  data() {
    return {
      username: '',
      tableData: [
        {
          orderID: '1',
          user: '张三',
          customer: '李四',
          time: '2022.11.18',
          state: '已退款'
        },
        {
          orderID: '2',
          user: '张三',
          customer: '王五',
          time: '2002.11.18',
          state: '已完成'
        },
        {
          orderID: '3',
          user: '张三',
          customer: '梅西',
          time: '2002.5.4',
          state: '已退款'
        },
        {
          orderID: '4',
          user: '张三',
          customer: 'c罗',
          time: '2022.11.18',
          state: '已退款'
        },
        {
          orderID: '5',
          user: '张三',
          customer: '姆巴佩',
          time: '2022.11.18',
          state: '已完成'
        },
        {
          orderID: '6',
          user: '张三',
          customer: '苏亚雷斯',
          time: '2022.11.18',
          state: '已完成'
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
