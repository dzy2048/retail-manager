<template>
    <div>
        <el-card id="repo" class="box-card">
            <div slot="header" class="clearfix">
                <span>库存统计</span>
                <el-input v-model="input" placeholder="请输入仓库ID"></el-input>
                <el-button style="padding: 3px 0; margin-left: 3%" type="text" @click="query">查询</el-button>
            </div>
            <div  class="text item">
                <el-row :gutter="20">
                    <el-col :span="6">
                        <div style="margin-top: 15px">
                            <span>仓库ID：{{repoId}}</span>
                        </div>
                    </el-col>
                    <el-col :span="6">
                        <div>
                            <el-statistic group-separator="," :precision="2" :value="storage" title="库存积压量"></el-statistic>
                        </div>
                    </el-col>
                    <el-col :span="6">
                        <div>
                            <el-statistic group-separator="," :precision="2" :value="overstock" title="商品积压金额"></el-statistic>
                        </div>
                    </el-col>
                    <el-col :span="6">
                        <div style="margin-left: 30px">
                            <el-button type="text" @click="detailVisible=true">查看详情</el-button>
                        </div>
                    </el-col>
                </el-row>
            </div>
        </el-card>
        <el-card id="sale" class="box-card">
            <div slot="header" class="clearfix">
                <span>销售统计</span>
                <el-button style="float: right; padding: 3px 0" type="text" @click="saleVisible=true">查看全部</el-button>
            </div>
            <div class="text item">
                <p>热销TOP4</p>
                <span style="display: flex" v-if="salesTable.length>0">
                    <el-progress :text-inside="true" :stroke-width="26" :percentage="salesTable[0].percent"></el-progress>{{salesTable[0].goodName}}
                </span>
                <span style="display: flex" v-if="salesTable.length>1">
                    <el-progress :text-inside="true" :stroke-width="24" :percentage="salesTable[1].percent" status="success"></el-progress>{{salesTable[1].goodName}}
                </span>
                <span style="display: flex" v-if="salesTable.length>2">
                    <el-progress :text-inside="true" :stroke-width="22" :percentage="salesTable[2].percent" status="warning"></el-progress>{{salesTable[2].goodName}}
                </span>
                <span style="display: flex" v-if="salesTable.length>3">
                    <el-progress :text-inside="true" :stroke-width="20" :percentage="salesTable[3].percent" status="exception"></el-progress>{{salesTable[3].goodName}}
                </span>
            </div>
        </el-card>
        <el-dialog title="详情" :visible.sync="detailVisible" width="30%">
            <el-table :data="detailsTable" height="400">
                <el-table-column prop="goodName" label="商品名称"></el-table-column>
                <el-table-column prop="money" label="积压金额"></el-table-column>
            </el-table>
        </el-dialog>
        <el-dialog title="销量详情" :visible.sync="saleVisible" width="30%">
            <el-table :data="salesTable" height="400">
                <el-table-column prop="goodName" label="商品名称"></el-table-column>
                <el-table-column prop="total" label="销量"></el-table-column>
            </el-table>
        </el-dialog>
    </div>
</template>

<script>
import request from "@/utils/request";

export default {
    name: "Statistics",
    data() {
        return {
            input: '',
            repoId: '',
            storage: 0,
            overstock: 0,
            detailVisible: false,
            saleVisible: false,
            detailsTable: [],
            salesTable: []
        }
    },
    created() {
        this.load()
    },
    methods: {
        query() {
            request.get('http://localhost:9090/statistics/repo',{
                params: {
                    repoId: parseInt(this.input)
                }
            }).then(res => {
                this.repoId = this.input
                this.storage = res.data.storage
                this.overstock = res.data.overstock
                this.detailsTable = res.data.details
                this.input = ''
            })
        },
        load() {
            request.get('http://localhost:9090/statistics/sale').then(res => {
                this.salesTable = res.data
                this.sort()
            })
        },
        sort() {    //对销量表按销量排序（从高到低）
            for (let i=0 ;i<this.salesTable.length-1 ;i++)
            {
                let k = i
                for (let j=i+1 ;j<this.salesTable.length ;j++)
                {
                    if (this.salesTable[j].total > this.salesTable[k].total)
                        k = j
                }
                if (k !== i)
                {
                    let t = this.salesTable[i]
                    this.salesTable[i] = this.salesTable[k]
                    this.salesTable[k] = t
                }
            }
        }
    }
}
</script>

<style scoped>
.box-card {
    width: 50%;
}
#sale {
    margin-top: 5%;
}
.el-input {
    width: 20%;
    margin-left: 50%;
}
.el-progress {
    width: 60%;
    margin-bottom: 5px;
    margin-right: 10px;
}
</style>
