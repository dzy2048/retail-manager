<template>
    <div>
        <!--上方查询与新建区域-->
        <div class="top">
            <el-form :inline="true">
                <el-form-item label="用户名">
                    <el-input type="text" v-model="username"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="query()">查询</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="dialogVisible_add=true">新增</el-button>
                </el-form-item>
            </el-form>
        </div>
        <!--下方表格区域-->
        <div class="foot">
            <!--表格-->
            <el-table
                :data="tableData"
                height="400"
                border
                style="width: 90%">
                <el-table-column type="index"></el-table-column>
                <el-table-column prop="customerId" label="客户ID" width="90"></el-table-column>
                <el-table-column
                    prop="name"
                    label="姓名"
                    width="180">
                </el-table-column>
                <el-table-column
                    prop="address"
                    label="地址">
                </el-table-column>
                <el-table-column
                    prop="phone"
                    label="联系方式">
                </el-table-column>
                <el-table-column
                    label="操作"
                    width="100">
                    <template slot-scope="scope">
                        <el-button type="text" size="small" @click="edit(scope.row);">编辑
                        </el-button>
                        <el-button type="text" size="small" @click="handleDelete(scope.row.customerId)"
                                   style="color:red">删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <!--编辑的弹窗-->
        <el-dialog
            title="编辑客户信息"
            :visible.sync="dialogVisible_edit"
            width="30%">
            <el-form v-model="updateForm">
                <el-form-item label="姓名">
                    <el-col span="10">
                        <el-input v-model="updateForm.name"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="地址">
                    <el-col span="20">
                        <el-input v-model="updateForm.address"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="联系方式">
                    <el-col span="19">
                        <el-input v-model="updateForm.phone"></el-input>
                    </el-col>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible_delete = false">取 消</el-button>
        <el-button type="primary" @click="update();">确 定</el-button>
      </span>
        </el-dialog>
        <!--新建用户的弹窗-->
        <el-dialog
            title="新建"
            :visible.sync="dialogVisible_add"
            width="30%">
            <el-form v-bind:model="addForm">
                <el-form-item label="姓名" prop="name">
                    <el-col span="10">
                        <el-input v-model="addForm.name"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="地址">
                    <el-col span="20">
                        <el-input v-model="addForm.address"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="联系方式">
                    <el-col span="19">
                        <el-input v-model="addForm.phone"></el-input>
                    </el-col>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible_add = false">取 消</el-button>
        <el-button type="primary" @click="add();dialogVisible_add = false">确 定</el-button>
      </span>
        </el-dialog>
    </div>
</template>

<script>
import request from "@/utils/request";

export default {
    name: "Customer",
    data() {
        return {
            username: '',
            dialogVisible_add: false,
            dialogVisible_delete: false,
            dialogVisible_edit: false,
            comitDelete: false,
            tableData: [],
            addForm: {
                name: '',
                address: '',
                phone: ''
            },
            updateForm: {
                customerId: '',
                name: '',
                address: '',
                phone: ''
            }
        }
    },
    methods: {
        handleDelete(id) {
            this.$confirm('确定要删除吗？', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                request.delete('http://localhost:9090/customer/delete', {
                    params: {
                        id: id
                    }
                }).then(res => {
                    this.getAll();
                })
            })
        },
        query() {
            request.get('http://localhost:9090/customer/find', {
                params: {
                    username: this.username
                }
            }).then(res => {
                this.tableData = res.data;
                this.username = '';
            })
        },
        add() {
            request.post('http://localhost:9090/customer/add', this.addForm).then(res => {
                this.getAll();
            })
        },
        update() {
            request.put('http://localhost:9090/customer/update', this.updateForm).then(res => {
                this.dialogVisible_edit = false;
                this.getAll();
            })
        },
        getAll() {
            request.get('http://localhost:9090/customer/all').then(res => {
                this.tableData = res.tableData;
            }).catch(function (error) {
                    console.log(error);
                });
        },
        edit(row) {
            this.dialogVisible_edit = true;
            this.updateForm.customerId = row.customerId;
            this.updateForm.name = row.name;
            this.updateForm.address = row.address;
            this.updateForm.phone = row.phone;
        }
    },
    created() {
        this.getAll();
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
