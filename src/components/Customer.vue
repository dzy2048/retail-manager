<template>
  <div>
    <!--上方查询与新建区域-->
    <div class="top">
      <el-form :inline="true">
        <el-form-item label="用户名">
          <el-input type="text" v-model="username" ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary">查询</el-button>
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
            <el-button type="text" size="small" @click="dialogVisible_edit = true">编辑</el-button>
            <el-button type="text" size="small" @click="handleDelete(scope.$index,tableData)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!--删除的提示弹窗-->
    <el-dialog
      title="提示"
      :visible.sync="dialogVisible_delete"
      width="30%">
      <span>确认删除吗？</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible_delete = false">取 消</el-button>
        <el-button type="primary" @click="comitDelete = true;dialogVisible_delete = false">确 定</el-button>
      </span>
    </el-dialog>
    <!--编辑的弹窗-->
    <el-dialog
      title="编辑客户信息"
      :visible.sync="dialogVisible_edit"
      width="30%">
      <el-form>
        <el-form-item label="姓名">
          <el-col span="10">
            <el-input ></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="地址">
          <el-col span="20">
            <el-input ></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="联系方式">
          <el-col span="19">
            <el-input ></el-input>
          </el-col>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible_delete = false">取 消</el-button>
        <el-button type="primary" @click="dialogVisible_delete = false">确 定</el-button>
      </span>
    </el-dialog>
    <!--新建用户的弹窗-->
    <el-dialog
      title="新建"
      :visible.sync="dialogVisible_add"
      width="30%">
      <el-form>
        <el-form-item label="姓名">
          <el-col span="10">
            <el-input ></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="地址">
          <el-col span="20">
            <el-input ></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="联系方式">
          <el-col span="19">
            <el-input ></el-input>
          </el-col>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible_add = false">取 消</el-button>
        <el-button type="primary" @click="dialogVisible_add = false">确 定</el-button>
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
      tableData: []
    }
  },
  methods: {
    handleDelete(index, rows){
      this.$confirm('确定要删除吗？','提示',{
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        rows.splice(index,1);
      })
    }
  },
  created() {
    request.get('http://localhost:9090/customer').then(res => {
      this.tableData = res.tableData;
    })
    .catch(function(error){
      console.log(error);
    });
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
