<template>
    <div>
        <div class="left">
            <el-form :inline="true">
                <el-form-item label="仓库号">
                    <el-input type="text" v-model="repository_left"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="query(0)">查询</el-button>
                </el-form-item>
            </el-form>
            <el-table
                :data="tableData_left"
                height="500"
                border>
                <el-table-column
                    prop="goodName"
                    label="商品名称"
                    width="130">
                </el-table-column>
                <el-table-column
                    prop="number"
                    label="数量">
                </el-table-column>
                <el-table-column
                    fixed="right"
                    label="调度数量"
                    width="160">
                    <template slot-scope="scope">
                        <el-input-number
                            v-model="scope.row.selectNum"
                            @change=""
                            :min="0"
                            :max="scope.row.number"
                            size="small">
                        </el-input-number>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <!--中间区域-->
        <div class="center">
            <el-button type="primary" @click="dispatch(0)">&lt;</el-button>
            <el-button type="primary" @click="dispatch(1)">&gt;</el-button>
        </div>
        <div class="right">
            <el-form :inline="true">
                <el-form-item label="仓库号">
                    <el-input type="text" v-model="repository_right"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="query(1)">查询</el-button>
                </el-form-item>
            </el-form>
            <el-table
                :data="tableData_right"
                height="500"
                border>
                <el-table-column
                    prop="goodName"
                    label="商品名称"
                    width="130">
                </el-table-column>
                <el-table-column
                    prop="number"
                    label="数量">
                </el-table-column>
                <el-table-column
                    fixed="right"
                    label="调度数量"
                    width="160">
                    <template slot-scope="scope">
                        <el-input-number
                            v-model="scope.row.selectNum"
                            @change=""
                            :min="0"
                            :max="scope.row.number"
                            size="small">
                        </el-input-number>
                    </template>
                </el-table-column>
            </el-table>
        </div>
    </div>
</template>

<script>
import request from "@/utils/request";

export default {
    name: "Dispatch",
    data() {
        return {
            left: [],
            right: [],
            tableData_left: [],
            tableData_right: [],
            repository_left: '',
            repository_right: '',
            repo: '',
            good: {
                goodName: '',
                dispatchNum: ''
            },
            moveForm: {
                source_id: '',
                target_id: '',
                moveList: []
            }
        }
    },
    methods: {
        dispatch(place) {
            if (place === 1) {
                for (let i = 0; i < this.tableData_left.length; i++) {
                    if (this.tableData_left[i].selectNum != null && this.tableData_left[i].selectNum !== 0) {
                        let good = {
                            goodName: this.tableData_left[i].goodName,
                            dispatchNum: this.tableData_left[i].selectNum
                        }
                        this.left.splice(this.left.length,0,good);
                    }
                }
                this.moveForm.moveList = this.left;
                this.moveForm.source_id = this.repository_left;
                this.moveForm.target_id = this.repository_right;
            } else {
                for (let i = 0; i < this.tableData_right.length; i++) {
                    if (this.tableData_right[i].selectNum != null && this.tableData_right[i].selectNum !== 0) {
                        let good = {
                            goodName: this.tableData_right[i].goodName,
                            dispatchNum: this.tableData_right[i].selectNum
                        }
                        this.right.splice(this.right.length,0,good);
                    }
                }
                this.moveForm.moveList = this.right;
                this.moveForm.source_id = this.repository_right;
                this.moveForm.target_id = this.repository_left;
            }
            request.post('http://localhost:9090/index/dispatch/move', this.moveForm).then(res => {
                this.query(0);
                this.query(1);
                this.moveForm = {};
                this.left = [];
                this.right = [];
            })
        },
        query(place) {
            if (place == 0)
                this.repo = this.repository_left;
            else
                this.repo = this.repository_right;
            request.get('http://localhost:9090/index/dispatch/all', {
                params: {
                    id: this.repo
                }
            }).then(res => {
                if (place == 0)
                    this.tableData_left = res.data;
                else
                    this.tableData_right = res.data;
            })
        }
    }
}
</script>

<style scoped>
.left, .center, .right {
    float: left;
}

.center, .right {
    margin-left: 40px;
}

.center {
    padding-top: 20%;
}

.el-table {
    margin-top: 20px;
    width: 420px;
}
</style>
