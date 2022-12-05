<template>
    <div>
        <div class="top">
            <el-form :inline="true">
                <el-form-item label="订单号">
                    <el-input type="text" v-model="inputId"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="searchById()">查询</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div class="foot">
            <el-table :data="tableData" height="400" border style="width: 100%;height: 100%">
                <el-table-column prop="orderId" label="订单号" width="140"></el-table-column>
                <el-table-column prop="userId" label="用户ID"></el-table-column>
                <el-table-column prop="customerId" label="客户ID"></el-table-column>
                <el-table-column prop="time" label="时间"></el-table-column>
                <el-table-column prop="shouldPay" label="应付金额"></el-table-column>
                <el-table-column prop="havePaid" label="已付金额"></el-table-column>
                <el-table-column prop="profit" label="利润"></el-table-column>
                <el-table-column prop="state" label="状态"></el-table-column>
                <el-table-column label="操作" height="100">
                    <template slot-scope="scope">
                        <el-popconfirm title="确定退款吗？" @confirm="refund(scope.row.orderId)">
                            <el-button type="text" slot="reference" size="small" style="color:red">退款</el-button>
                        </el-popconfirm>
                        <el-button type="text" slot="reference" size="small" @click="handleModify(scope.row)" style="color: brown">修改金额</el-button>
                        <el-button type="text" slot="reference" size="small" @click="detail(scope.row.orderId)">查看</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <el-dialog title="修改金额" :visible.sync="modifyMoneyVisible" size="small">
            <el-form :model="modifyMoneyForm" label-width="120px" size="small">
                <el-form-item label="金额">
                    <el-input v-model="inputMoney" autocomplete="off" style="width:60%"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="modifyMoneyVisible = false">取 消</el-button>
                <el-button type="primary" @click="modifyMoney()">确 定</el-button>
            </div>
        </el-dialog>
        <el-dialog title="订单详情" :visible.sync="orderVisible" size="small">
            <el-table :data="orderData" height="400" border style="width: 100%;height: 100%">
                <el-table-column prop="goodName" label="商品名称" width="180"></el-table-column>
                <el-table-column prop="wholePrice" label="批发价格"></el-table-column>
                <el-table-column prop="number" label="数量"></el-table-column>
            </el-table>
            <p>总价：{{totalPrice}}</p>
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
            inputId: '',
            inputMoney: '',
            modifyMoneyForm: '',
            modifyMoneyVisible: false,
            orderVisible: false,
            totalPrice: '',
            orderData: [],
            tableData: [],
            changeForm: {}
        }
    },
    created() {
        this.load();
    },
    methods: {
        load() {
            request.get('http://localhost:9090/order/all').then(res => {
                this.tableData = res.data;
            })
        },
        detail(id) {
            request.get('http://localhost:9090/order/detail',{
                params: {
                    orderId: id
                }
            }).then(res => {
                this.orderData = res.data.orderData;
                this.totalPrice = res.data.totalPrice;
            })
            this.orderVisible = true;
        },
        searchById() {
            request.get('http://localhost:9090/order/query',{
                params:{
                    id: this.inputId
                }
            }).then(res => {
                this.tableData = res.data;
                this.inputId = '';
            })
        },
        refund(id) {
            this.changeForm.id = id;
            request.put('http://localhost:9090/order/refund',this.changeForm).then(res => {
                this.load();
            })
            this.changeForm = {};
        },
        handleModify(row){
            if (row.state === '已退款')
                this.$message.error('该订单已退款');
            else if (row.state === '已完成')
                this.$message.error('该订单已完成');
            else
            {
                this.modifyMoneyVisible = true;
                this.changeForm.paid = row.havePaid;
                this.changeForm.id = row.orderId;
            }
        },
        modifyMoney() {
            if (this.inputMoney <= this.changeForm.paid)
            {
                this.changeForm = {};
                this.$message.error('请输入正确的金额!');
            }
            this.changeForm.paid = this.inputMoney
            request.put('http://localhost:9090/order/change',this.changeForm).then(res=> {
                this.modifyMoneyVisible = false;
                this.load();
            })
            this.changeForm = {};
            this.inputMoney = '';
        }
    }
}
</script>

<style scoped>
.top {
    margin-top: 5%;
}

.foot {
    margin-top: 3%;
}
</style>
