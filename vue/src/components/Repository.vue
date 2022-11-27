<template>
    <div>
        <div style="margin:10px">
            <el-form :inline="true">
                <el-input v-model="input" suffix-icon="el-icon-search" placeholder="请输入商品名称"
                          style="width:30%"></el-input>
                <el-button type="primary" @click="searchData" style="margin: 10px">搜索</el-button>
                <el-button type="primary" @click="handleAdd">新增</el-button>
            </el-form>
        </div>
        <div>
            <el-table :data="tableData" stripe style="width: 100%">
                <el-table-column prop="goodName" label="商品名称"/>
                <el-table-column prop="number" label="数量"/>
                <el-table-column prop="unit" label="单位"/>
                <el-table-column prop="repoId" label="仓库ID"/>
                <el-table-column fixed="right" label="操作" width="100">
                    <template slot-scope="scope">
                        <el-button @click="" type="text" size="small" style="color: green">修改数量</el-button>
                        <el-popconfirm title="确定删除吗？" @confirm="confirmEvent(scope.$index)">
                            <el-button type="text" slot="reference" size="small" style="color: red">删除</el-button>
                        </el-popconfirm>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <el-dialog title="修改价格" :visible.sync="modifyPriceVisible" size="small">
            <el-form :model="modifyPriceForm" label-width="120px" size="small">
                <el-form-item label="批发价格">
                    <el-input v-model="modifyPriceForm.wholePrice" autocomplete="off" style="width:60%"></el-input>
                </el-form-item>
                <el-form-item label="零售价格">
                    <el-input v-model="modifyPriceForm.sellPrice" autocomplete="off" style="width:60%"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="modifyPriceVisible = false">取 消</el-button>
                <el-button type="primary" @click="modifyPrice()">确 定</el-button>
            </div>
        </el-dialog>
        <el-dialog title="修改数量" :visible.sync="modifyNumVisible" size="small">
            <el-form :model="modifyNumForm" label-width="120px" size="small">
                <el-form-item label="数量">
                    <el-input v-model="modifyNumForm.number" autocomplete="off" style="width:60%"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="modifyNumVisible = false">取 消</el-button>
                <el-button type="primary" @click="modifyNum">确 定</el-button>
            </div>
        </el-dialog>
        <el-dialog title="新增商品" :visible.sync="addVisible" size="small">
            <el-form :model="addForm" label-width="120px" size="small">
                <el-form-item label="商品名称">
                    <el-input v-model="addForm.goodName" autocomplete="off" style="width:60%"></el-input>
                </el-form-item>
                <el-form-item label="进货价格">
                    <el-input v-model="addForm.buyPrice" autocomplete="off" style="width:60%"></el-input>
                </el-form-item>
                <el-form-item label="批发价格">
                    <el-input v-model="addForm.wholePrice" autocomplete="off" style="width:60%"></el-input>
                </el-form-item>
                <el-form-item label="零售价格">
                    <el-input v-model="addForm.sellPrice" autocomplete="off" style="width:60%"></el-input>
                </el-form-item>
                <el-form-item label="数量">
                    <el-input v-model="addForm.number" autocomplete="off" style="width:60%"></el-input>
                </el-form-item>
                <el-form-item label="单位">
                    <el-input v-model="addForm.unit" autocomplete="off" style="width:60%"></el-input>
                </el-form-item>
                <el-form-item label="仓库">
                    <el-input v-model="addForm.repository" autocomplete="off" style="width:60%"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="modifyPriceVisible = false">取 消</el-button>
                <el-button type="primary" @click="">确 定</el-button>
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
            modifyPriceVisible: false,
            addVisible: false,
            modifyNumVisible: false,
            tableData: [],
            input: '',
            modifyPriceForm: {
                id: '',
                wholePrice: '',
                sellPrice: ''
            },
            modifyNumForm: {
                id: '',
                number: ''
            },
            addForm: {
                goodName: '',
                buyPrice: '',
                wholePrice: '',
                sellPrice: '',
                unit: '',
                number: '',
                repository: ''
            }
        }
    },
    created() {
        this.getAll();
    },
    methods: {
        getAll(){
            request.get('http://localhost:9090/index/repo/all').then(res => {
                this.tableData = res.data;
            })
        }
    }
}
</script>
