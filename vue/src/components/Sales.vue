<
<template>
    <div>
        <div class="top">
            <el-form :inline="true">
                <el-form-item label="商品名称">
                    <el-input type="text" v-model="goodName"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="query()">查询</el-button>
                </el-form-item>
                <el-form-item label="客户">
                    <el-input type="text" v-model="customer" @click="searchCus"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="getCustomer()">选择</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div class="foot">
            <el-table :data="tableData" height="400" border style="width: 100%;height: 100%">
                <el-table-column prop="goodName" label="商品名称" width="180"></el-table-column>
                <el-table-column prop="price" label="批发价格"></el-table-column>
                <el-table-column prop="number" label="数量"></el-table-column>
                <el-table-column prop="unit" label="单位"></el-table-column>
                <el-table-column prop="repoId" label="仓库"></el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-input-number v-model="scope.row.selectNum" @change="handleChange()" :min="0"
                                         label="描述文字"></el-input-number>
                    </template>
                </el-table-column>
            </el-table>
            <span>总价：{{ totalPrice }}</span>
            <el-button @click="handleSell()" style="margin-left: 10%">售出</el-button>
        </div>
        <el-dialog title="销售单" :visible.sync="sellVisible" size="small">
            <el-table :data="sellTable" height="400" border style="width: 100%;height: 100%">
                <el-table-column prop="goodName" label="商品名称" width="180"></el-table-column>
                <el-table-column prop="price" label="批发价格"></el-table-column>
                <el-table-column prop="number" label="数量"></el-table-column>
                <el-table-column prop="unit" label="单位"></el-table-column>
                <el-table-column prop="repoId" label="仓库"></el-table-column>
            </el-table>
            <p>总价：{{ totalPrice }}</p>
            <p>客户：{{ customer }}</p>
            <div slot="footer" class="dialog-footer">
                <el-button @click="handleCancel()">取 消</el-button>
                <el-button type="primary" @click="sell()">确 定</el-button>
            </div>
        </el-dialog>
        <!--客户选择对话框-->
        <el-dialog title="选择客户" :visible.sync="customerVisible">
            <el-table :data="customerTable" height="400" border style="width: 100%;height: 100%">
                <el-table-column prop="customerId" label="客户ID"></el-table-column>
                <el-table-column prop="name" label="姓名"></el-table-column>
                <el-table-column prop="phone" label="联系方式"></el-table-column>
                <el-table-column label="操作">
                    <template slot-scope="scope">
                        <el-button type="primary" round @click="setCustomer(scope.row)">选择</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-dialog>
    </div>
</template>

<script>
import request from "@/utils/request";

export default {
    name: "Sales",
    data() {
        return {
            goodName: '',
            customer: '',
            customerId: 0,
            totalPrice: 0,
            tableData: [],
            selectData: [],
            customerTable: [],
            sellTable: [],
            sellVisible: false,
            customerVisible: false,
            sellForm: {}
        }
    },
    created() {
        this.load();
    },
    methods: {
        query() {
            request.get('http://localhost:9090/sales/query', {
                params: {
                    goodName: this.goodName
                }
            }).then(res => {
                this.tableData = res.data;
            })
        },
        handleChange() {
            let temp = 0;
            for (let i = 0; i < this.tableData.length; i++) {
                if (this.tableData[i].selectNum != null && this.tableData[i].selectNum !== 0)
                    temp += this.tableData[i].selectNum * this.tableData[i].price;
            }
            this.totalPrice = temp;
        },
        getCustomer() {
            request.get('http://localhost:9090/customer/all').then(res => {
                this.customerTable = res.tableData;
                this.customerVisible = true;
            })
        },
        setCustomer(row) {
            this.customer = row.name;
            this.customerId = row.customerId;
            this.customerVisible = false;
        },
        handleSell() {
            if (this.customer === '')
                this.$message.error('请选择客户!');
            else
            {
                for (let i=0 ;i<this.tableData.length ;i++)
                {
                    if (this.tableData[i].selectNum != null && this.tableData[i].selectNum !== 0)
                    {
                        this.sellTable.splice(0,0,this.tableData[i]);
                        this.sellTable[0].number = this.tableData[i].selectNum;
                    }
                }
                this.sellVisible = true;
            }
        },
        sell() {
            this.sellVisible = false;
            this.sellForm.items = this.sellTable;
            this.sellForm.price = this.totalPrice;
            this.sellForm.userId = sessionStorage.getItem('userId');
            this.sellForm.customerId = this.customerId;
            request.post('http://localhost:9090/sales/add',this.sellForm).then(res => {
                if (res.code === '0')
                    this.$message.success('成功售出');
                else
                    this.$message.error('发生了未知错误');
                this.load();
            })
        },
        load() {
            request.get('http://localhost:9090/sales/all').then(res => {
                this.tableData = res.data;
                this.totalPrice = 0;
            })
        },
        handleCancel(){
            this.sellVisible = false;
            this.load();
            this.sellTable = [];
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
