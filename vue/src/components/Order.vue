<template>
  <div>
    <div class="top">
      <el-form :inline="true">
        <el-form-item label="订单号">
          <el-input type="text" v-model="inputId"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="searchById">查询</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="foot">
      <el-table :data="tableData" height="400" border style="width: 100%;height: 100%">
        <el-table-column prop="orderID" label="订单号" width="140"></el-table-column>
        <el-table-column prop="userId" label="用户ID"></el-table-column>
        <el-table-column prop="customerId" label="客户ID"></el-table-column>
        <el-table-column prop="time" label="时间"></el-table-column>
        <el-table-column prop="shouldPay" label="应付金额"></el-table-column>
        <el-table-column prop="havePaid" label="已付金额"></el-table-column>
        <el-table-column prop="profit" label="利润"></el-table-column>
        <el-table-column prop="state" label="状态"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-popconfirm title="确定退款吗？" @confirm="confirmEvent(scope.$index)">
              <el-button type="waring" slot="reference" size="small">退款</el-button>
            </el-popconfirm>
            <el-button type="waring" slot="reference" size="small" @click="handleMoney">修改金额</el-button>
            <el-button type="waring" slot="reference" size="small" @click="finish">完成</el-button>
            <el-button type="waring" slot="reference" size="small" @click="detail">查看</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-dialog title="修改金额" :visible.sync="modifyMoneyVisible" size="small" >
      <el-form :model="modifyMoneyForm" label-width="120px" size="small">
        <el-form-item label="金额" >
          <el-input v-model="inputMoney" autocomplete="off" style="width:60%"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="modifyMoneyVisible = false">取 消</el-button>
        <el-button type="primary" @click="modifyMoney">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="订单详情" :visible.sync="orderVisible" size="small" >
      <el-table :data="orderData" height="400" border style="width: 100%;height: 100%">
        <el-table-column prop="goodName" label="商品名称" width="180"></el-table-column>
        <el-table-column prop="sellPrice" label="零售价格"></el-table-column>
        <el-table-column prop="number" label="数量"></el-table-column>
        <!--        <el-table-column prop="number" label="数量"></el-table-column>-->
      </el-table>
      <p>总价：270</p>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="orderVisible = false">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Order",
  data() {
    return {
      username: '',
      inputId:'',
      inputMoney:'',
      modifyMoneyForm:'',
      modifyMoneyVisible:false,
      orderVisible: false,
      orderData: [
        {
          goodName: '牙刷',
          sellPrice: 5,
          number: 10
        },
        {
          goodName: '牙膏',
          sellPrice: 2,
          number: 10
        },
        {
          goodName: '苹果',
          sellPrice: 1,
          number: 100
        },
        {
          goodName: '香蕉',
          sellPrice: 1,
          number: 100
        }
      ],
      tableData: [
        {
          orderID: '1',
          userId: '1',
          customerId: '2',
          time: '2022-11-20 17:25:08',
          profit: '3',
          state: '未完成',
          shouldPay:'30',
          havePaid:'20'
        },
        {
          orderID: '2',
          userId: '1',
          customerId: '3',
          time: '2022-11-20 17:25:31',
          profit: '10',
          state: '已完成',
          shouldPay:'40',
          havePaid:'40'
        },
        {
          orderID: '3',
          userId: '2',
          customerId: '1',
          time: '2022-11-20 17:26:06',
          profit: '4',
          state: '已退款',
          shouldPay:'50',
          havePaid:'50'
        },
      ]
    }
  },
  create() {
    // request.get("http://localhost:9090/order/all").then(res=>{
    //   console.log(res)
    //   this.tableData = res.data;
    // })
  },
  methods:{
    load() {
      request.get("http://localhost:9090/order").then(res=>{
        console.log(res);
        this.tableData=res.data
      })
    },
    detail() {
      this.orderVisible = true;
    },
    searchById(){
      this.inputId=''
      this.tableData=[
        {
          orderID: '3',
          userId: '2',
          customerId: '1',
          time: '2022-11-20 17:26:06',
          profit: '4',
          state: '已退款',
          shouldPay:'50',
          havePaid:'50'
        }
      ]
      // request.get("http://localhost:9090/index/order/search",{
      //   params:{
      //     id:this.input
      //   }
      // }).then(res=>{
      //   console.log(res);
      //   this.tableData=res.data
      // })
    },
    confirmEvent(index) {
      this.tableData=[
        {
          orderID: '1',
          userId: '1',
          customerId: '2',
          time: '2022-11-20 17:25:08',
          profit: '3',
          state: '已退款',
          shouldPay:'30',
          havePaid:'30'
        },
        {
          orderID: '2',
          userId: '1',
          customerId: '3',
          time: '2022-11-20 17:25:31',
          profit: '10',
          state: '已完成',
          shouldPay:'40',
          havePaid:'40'
        },
        {
          orderID: '3',
          userId: '2',
          customerId: '1',
          time: '2022-11-20 17:26:06',
          profit: '4',
          state: '已退款',
          shouldPay:'50',
          havePaid:'50'
        },
      ]
      request.delete("http://localhost:9090/index/order/state", {
        params:{
          id:index+1
        }
      }).then()
      this.load()
    },
    handleMoney(){
      this.modifyMoneyVisible=true
    },
    modifyMoney(){
      this.inputMoney=''
      this.modifyMoneyVisible=false
      this.tableData=[
        {
          orderID: '1',
          userId: '1',
          customerId: '2',
          time: '2022-11-20 17:25:08',
          profit: '3',
          state: '未完成',
          shouldPay:'30',
          havePaid:'30'
        },
        {
          orderID: '2',
          userId: '1',
          customerId: '3',
          time: '2022-11-20 17:25:31',
          profit: '10',
          state: '已完成',
          shouldPay:'40',
          havePaid:'40'
        },
        {
          orderID: '3',
          userId: '2',
          customerId: '1',
          time: '2022-11-20 17:26:06',
          profit: '4',
          state: '已退款',
          shouldPay:'50',
          havePaid:'50'
        },
      ]
    },
    finish(){
      this.tableData=[
        {
          orderID: '1',
          userId: '1',
          customerId: '2',
          time: '2022-11-20 17:25:08',
          profit: '3',
          state: '已完成',
          shouldPay:'30',
          havePaid:'30'
        },
        {
          orderID: '2',
          userId: '1',
          customerId: '3',
          time: '2022-11-20 17:25:31',
          profit: '10',
          state: '已完成',
          shouldPay:'40',
          havePaid:'40'
        },
        {
          orderID: '3',
          userId: '2',
          customerId: '1',
          time: '2022-11-20 17:26:06',
          profit: '4',
          state: '已退款',
          shouldPay:'50',
          havePaid:'50'
        },
      ]
    }
  },

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
