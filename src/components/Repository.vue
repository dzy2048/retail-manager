<template>
  <div>
    <div>
      <el-input v-model="input" suffix-icon="el-icon-search" placeholder="请输入商品名称" style="width:30%" ></el-input>
      <el-button @click="searchData" style="margin: 10px" >搜索</el-button>
    </div>
    <div style="margin:10px">
      <el-button @click="handleAdd">新增</el-button>
    </div>
    <div>
      <el-table :data="tableData" stripe style="width: 100%">
        <el-table-column prop="goodName" label="商品名称"/>
        <el-table-column prop="buyPrice" label="进货价格" />
        <el-table-column prop="wholePrice" label="批发价格" />
        <el-table-column prop="sellPrice" label="零售价格" />
        <el-table-column prop="number" label="数量" />
        <el-table-column prop="unit" label="单位" />
        <el-table-column prop="repository" label="仓库" />
        <el-table-column fixed="right" label="操作" width="100">
          <template slot-scope="scope">
            <el-button @click="handlePrice(scope.$index)" type="text" size="small" style="color: blue">修改价格</el-button>
            <el-button @click="handleNum(scope.$index)" type="text" size="small" style="color: green">修改数量</el-button>
            <el-popconfirm title="确定删除吗？" @confirm="confirmEvent(scope.$index)">
              <el-button type="text" slot="reference" size="small" style="color: red">删除</el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <el-dialog title="修改价格" :visible.sync="modifyPriceVisible" size="small" >
      <el-form :model="modifyPriceForm" label-width="120px" size="small">
        <el-form-item label="批发价格" >
          <el-input v-model="modifyPriceForm.wholePrice" autocomplete="off" style="width:60%"></el-input>
        </el-form-item>
        <el-form-item label="零售价格" >
          <el-input v-model="modifyPriceForm.sellPrice" autocomplete="off" style="width:60%"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="modifyPriceVisible = false">取 消</el-button>
        <el-button type="primary" @click="modifyPrice()">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="修改数量" :visible.sync="modifyNumVisible" size="small" >
      <el-form :model="modifyNumForm" label-width="120px" size="small">
        <el-form-item label="数量" >
          <el-input v-model="modifyNumForm.number" autocomplete="off" style="width:60%"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="modifyNumVisible = false">取 消</el-button>
        <el-button type="primary" @click="modifyNum">确 定</el-button>
      </div>
    </el-dialog>
    <el-dialog title="新增商品" :visible.sync="addVisible" size="small" >
      <el-form :model="addForm" label-width="120px" size="small">
        <el-form-item label="商品名称" >
          <el-input v-model="addForm.goodName" autocomplete="off" style="width:60%"></el-input>
        </el-form-item>
        <el-form-item label="进货价格" >
          <el-input v-model="addForm.buyPrice" autocomplete="off" style="width:60%"></el-input>
        </el-form-item>
        <el-form-item label="批发价格" >
          <el-input v-model="addForm.wholePrice" autocomplete="off" style="width:60%"></el-input>
        </el-form-item>
        <el-form-item label="零售价格" >
          <el-input v-model="addForm.sellPrice" autocomplete="off" style="width:60%"></el-input>
        </el-form-item>
        <el-form-item label="数量" >
          <el-input v-model="addForm.number" autocomplete="off" style="width:60%"></el-input>
        </el-form-item>
        <el-form-item label="单位" >
          <el-input v-model="addForm.unit" autocomplete="off" style="width:60%"></el-input>
        </el-form-item>
        <el-form-item label="仓库" >
          <el-input v-model="addForm.repository" autocomplete="off" style="width:60%"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="modifyPriceVisible = false">取 消</el-button>
        <el-button type="primary" @click="addData">确 定</el-button>
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
      addVisible:false,
      modifyNumVisible:false,
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
    request.get('http://localhost:9090/index/repo/all').then(res=>{
      this.tableData = res.data;
    })
  },
  methods:{
    searchData(){
      request.get('http://localhost:9090/index/repo/find',{
        params:{
          goodName: this.input
        }
      }).then(res=>{
        this.tableData = res.data;
      })
    },
    confirmEvent(index){
      let temp = index + 1;
      request.delete('http://localhost:9090/index/repo/delete',{
        params:{
          id: temp
        }
      }).then(res=>{
        location.reload();
      })
    },
    handleAdd(){
      this.addVisible=true
      this.addForm={}
    },
    addData(){
       request.post('http://localhost:9090/index/repo/add',this.addForm).then(res=>{
        if(res){
           this.$message.success("保存成功")
         }
         else{
           this.$message.error("保存失败")
         }
         location.reload()
       })
    },
    handleNum(index){
      this.modifyNumVisible=true
      this.modifyNumForm.id = index + 1;
    },
    modifyNum(){
      request.put('http://localhost:9090/index/repo/updatenumber',this.modifyNumForm).then(res=>{
        location.reload();
      })
    },
    handlePrice(index){
      this.modifyPriceVisible = true
      this.modifyPriceForm.id = index + 1;
    },
    modifyPrice(){
      request.put('http://localhost:9090/index/repo/updateprice',this.modifyPriceForm).then(res=>{
        location.reload();
      })
    }
  }
}
</script>
