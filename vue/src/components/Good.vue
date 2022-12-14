<template>
    <div>
        <div class="top">
            <el-form :inline="true">
                <el-form-item>
                    <el-input v-model="name"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="query()">查询</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="dialogVisible_add=true">新增</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div class="bottom">
            <el-table :data="tableData" stripe style="width: 100%" height="500" border>
                <el-table-column prop="goodName" label="商品名称"/>
                <el-table-column prop="buyPrice" label="进货价格"/>
                <el-table-column prop="wholePrice" label="批发价格"/>
                <el-table-column prop="sellPrice" label="零售价格"/>
                <el-table-column prop="unit" label="单位"/>
                <el-table-column fixed="right" label="操作" width="100">
                    <template slot-scope="scope">
                        <el-button @click="changePrice(scope.row)" type="text" size="small" style="color: blue">修改价格</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <!--新增表单的弹窗-->
        <el-dialog title="新增" :visible.sync="dialogVisible_add" width="30%">
            <el-form v-bind:model="addForm" size="small" label-width="100px">
                <el-form-item label="商品名称">
                    <el-input v-model="addForm.goodName"></el-input>
                </el-form-item>
                <el-form-item label="进货价格">
                    <el-input v-model="addForm.buyPrice"></el-input>
                </el-form-item>
                <el-form-item label="批发价格">
                    <el-input v-model="addForm.wholePrice"></el-input>
                </el-form-item>
                <el-form-item label="零售价格">
                    <el-input v-model="addForm.sellPrice"></el-input>
                </el-form-item>
                <el-form-item label="单位">
                    <el-input v-model="addForm.unit"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible_add = false;clearForm(addForm)">取 消</el-button>
                <el-button type="primary" @click="add()">确 定</el-button>
            </span>
        </el-dialog>
        <!--修改表单的弹窗-->
        <el-dialog title="修改价格" :visible.sync="dialogVisible_update" width="30%">
            <el-form v-bind:model="updateForm" size="small" label-width="100px">
                <el-form-item label="批发价格">
                    <el-input v-model="updateForm.wholePrice"></el-input>
                </el-form-item>
                <el-form-item label="零售价格">
                    <el-input v-model="updateForm.sellPrice"></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible_update = false">取 消</el-button>
                <el-button type="primary" @click="update()">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
import request from "@/utils/request";
export default {
    name: "Good",
    data() {
        return {
            tableData: [],
            name: '',
            dialogVisible_add: false,
            dialogVisible_update: false,
            addForm: {
                goodName: '',
                buyPrice: '',
                wholePrice: '',
                sellPrice: '',
                unit: ''
            },
            updateForm: {
                goodId: '',
                wholePrice: '',
                sellPrice: ''
            }
        }
    },
    created() {
        this.getAll();
    },
    methods: {
        changePrice(row){
            this.dialogVisible_update=true
            this.updateForm.goodId = row.goodId
            this.updateForm.wholePrice = row.wholePrice
            this.updateForm.sellPrice = row.sellPrice
        },
        handleDelete(id) {
            this.$confirm('确定要删除吗？','提示',{
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(()=>{
                this.delete(id)
            })
        },
        getAll(){
            request.get('http://localhost:9090/good/all').then(res=>{
                this.tableData = res.data
            })
        },
        query(){
            request.get('http://localhost:9090/good/query',{
                params: {
                    name: this.name
                }
            }).then(res=>{
                this.tableData = res.data
                this.name = ''
            })
        },
        add(){
            for (let item in this.addForm)  //填写了部分项
            {
                if (this.addForm[item]==='')
                {
                    this.$message.warning('有未填项，请检查')
                    return
                }
            }
            if (parseFloat(this.addForm.wholePrice) < parseFloat(this.addForm.buyPrice)
                || parseFloat(this.addForm.sellPrice) < parseFloat(this.addForm.buyPrice))
            {
                this.$confirm('零售价或批发价低于进价，你确定吗？','提示',{type:"warning"}).then(() => {
                    request.post('http://localhost:9090/good/add',this.addForm).then(res=>{
                        this.getAll()
                        this.dialogVisible_add = false
                        this.clearForm(this.addForm)
                    })
                })
            }
            else
            {
                request.post('http://localhost:9090/good/add',this.addForm).then(res=>{
                    this.getAll()
                    this.dialogVisible_add = false
                    this.clearForm(this.addForm)
                })
            }
        },
        update(){
            for (let item in this.updateForm)  //填写了部分项
            {
                if (this.updateForm[item]==='')
                {
                    this.$message.warning('有未填项，请检查')
                    return
                }
            }
            request.put('http://localhost:9090/good/update',this.updateForm).then(res=>{
                this.getAll()
                this.dialogVisible_update = false
            })
        },
        delete(id){
            request.delete('http://localhost:9090/good/delete',{
                data: {
                    id: id
                }
            }).then(res=>{
                this.getAll()
            })
        },
        clearForm(form){
            for (let item in form)
            {
                form[item] = ''
            }
        }
    }
}
</script>

<style scoped>

</style>
