<template>
    <div>
        <div style="margin:10px">
            <el-form :inline="true">
                <el-input v-model="goodName" suffix-icon="el-icon-search" placeholder="请输入商品名称"
                          style="width:30%"></el-input>
                <el-button type="primary" @click="query()" style="margin: 10px">搜索</el-button>
                <el-button type="primary" @click="importVisible=true">进货</el-button>
            </el-form>
        </div>
        <div>
            <el-table :data="tableData" stripe style="width: 100%">
                <el-table-column prop="goodName" label="商品名称"/>
                <el-table-column prop="number" label="数量"/>
                <el-table-column prop="unit" label="单位"/>
                <el-table-column prop="repoId" label="仓库ID"/>
            </el-table>
        </div>
        <el-dialog title="进货" :visible.sync="importVisible" size="small">
            <el-form :inline="true">
                <el-form-item>
                    <el-input v-model="goodName"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="">查询</el-button>
                </el-form-item>
                <el-form-item label="目标仓库ID">
                    <el-input v-model="inputId"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button @click="repoId=inputId;inputId=''">确定</el-button>
                </el-form-item>
            </el-form>
            <el-table :data="importTable">
                <el-table-column prop="goodName" label="商品名称"/>
                <el-table-column prop="buyPrice" label="进货价格"/>
                <el-table-column prop="unit" label="单位"/>
                <el-table-column label="进货数量">
                    <template slot-scope="scope">
                        <el-input-number v-model="scope.row.selectNum" @change="" :min="0"  size="small"></el-input-number>
                    </template>
                </el-table-column>
            </el-table>
            <p>仓库ID: {{repoId}}</p>
            <div slot="footer" class="dialog-footer">
                <el-button @click="importVisible = false">取 消</el-button>
                <el-button type="primary" @click="add()">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>

import request from "@/utils/request";

export default {
    name: 'Repository',
    data() {
        return {
            importVisible: false,
            addVisible: false,
            modifyNumVisible: false,
            tableData: [],
            importTable: [],
            input: '',
            goodName: '',
            repoId: '',
            inputId: '',
            modifyNumForm: {
                id: '',
                number: ''
            },
            addForm: {
                addTable: [],
                repoId: ''
            }
        }
    },
    created() {
        this.getAll();
    },
    methods: {
        getAll(){
            request.get('http://localhost:9090/good/all').then(res => {
                this.importTable = res.data;
            })
            request.get('http://localhost:9090/repo/all').then(res =>{
                this.tableData = res.data;
            })
        },
        add(){
            this.importVisible = false;
            this.addForm.addTable = this.importTable
            this.addForm.repoId = this.repoId
            request.post('http://localhost:9090/repo/add',this.addForm).then(res=>{
                this.$message.success("添加成功")
                this.getAll()
            })
        },
        query(){
            request.get('http://localhost:9090/repo/query',{
                params: {
                    goodName: this.goodName
                }
            }).then(res => {
                this.tableData = res.data;
            })
        }
    }
}
</script>
