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
          width="180">
        </el-table-column>
        <el-table-column
          prop="number"
          label="数量">
        </el-table-column>
        <el-table-column
          fixed="right"
          label="选择"
          width="60">
          <template slot-scope="scope">
            <el-checkbox :value="isChecked(scope.$index,0)" @change="setSelect(scope.$index,0)"></el-checkbox>
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
          width="180">
        </el-table-column>
        <el-table-column
          prop="number"
          label="数量">
        </el-table-column>
        <el-table-column
          fixed="right"
          label="选择"
          width="60">
          <template slot-scope="scope">
            <el-checkbox :value="isChecked(scope.$index,1)" @change="setSelect(scope.$index,1)"></el-checkbox>
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
      checked_index_left: [],
      checked_index_right: [],
      tableData_left: [],
      tableData_right: [],
      repository_left: '',
      repository_right: '',
      repo: '',
      moveForm: {
        source_id: '',
        target_id: '',
        moveList: []
      }
    }
  },
  methods: {
    isChecked(index,place) {
      if (place == 0)
      {
        if (this.checked_index_left.indexOf(index) == -1)
        {
          return false;
        }
        else
        {
          return true;
        }
      }
      else
      {
        if (this.checked_index_right.indexOf(index) == -1)
        {
          return false;
        }
        else
        {
          return true;
        }
      }
    },
    setSelect(index,place) {
      if (place == 0)
      {
        if (this.isChecked(index,0))
        {
          this.checked_index_left.splice(this.checked_index_left.indexOf(index),1);
        }
        else
        {
          this.checked_index_left.splice(0,0,index);
        }
      }
      else
      {
        if (this.isChecked(index,1))
        {
          this.checked_index_right.splice(this.checked_index_right.indexOf(index),1);
        }
        else
        {
          this.checked_index_right.splice(0,0,index);
        }
      }
    },
    dispatch(place) {
      if (place == 0)
      {
        this.moveForm.moveList = this.checked_index_right;
        this.moveForm.target_id = this.repository_left;
      }
      else
      {
        this.moveForm.moveList = this.checked_index_left;
        this.moveForm.target_id = this.repository_right;
      }
      request.post('http://localhost:9090/index/dispatch/move',this.moveForm).then(res=>{
        this.query(0);
        this.query(1);
      })
    },
    query(place) {
      if(place == 0)
        this.repo = this.repository_left;
      else
        this.repo = this.repository_right;
      request.get('http://localhost:9090/index/dispatch/all',{
        params: {
          id: this.repo
        }
      }).then(res=>{
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
  .left,.center,.right
  {
    float: left;
  }
  .center,.right
  {
    margin-left: 40px;
  }
  .center
  {
    padding-top: 20%;
  }
  .el-table
  {
    margin-top: 20px;
    width: 420px;
  }
</style>
