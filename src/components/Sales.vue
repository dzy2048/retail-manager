<<template>
  <div>
    <div class="top">
      <el-form :inline="true">
        <el-form-item label="商品名称">
          <el-input type="text" v-model="goodName" ></el-input>
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
        <el-table-column prop="sellPrice" label="零售价格"></el-table-column>
        <el-table-column prop="number" label="数量"></el-table-column>
        <el-table-column prop="unit" label="单位"></el-table-column>
        <el-table-column prop="repository" label="仓库"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-input-number v-model="scope.row.selectNum" @change="handleChange()" :min="0"  label="描述文字"></el-input-number>
          </template>
        </el-table-column>
      </el-table>
      <p>总价：</p>
      <el-button @click="handleSell">售出 </el-button>
    </div>
    <el-dialog title="修改数量" :visible.sync="sellVisible" size="small" >
      <el-table :data="sellTable" height="400" border style="width: 100%;height: 100%">
        <el-table-column prop="goodName" label="商品名称" width="180"></el-table-column>
        <el-table-column prop="sellPrice" label="零售价格"></el-table-column>
        <el-table-column prop="number" label="数量"></el-table-column>
        <el-table-column prop="unit" label="单位"></el-table-column>
        <el-table-column prop="repository" label="仓库"></el-table-column>
      </el-table>
      <p>总价：29</p>
      <p>客户：{{customer}}</p>
      <div slot="footer" class="dialog-footer">
        <el-button @click="sellVisible = false">取 消</el-button>
        <el-button type="primary" @click="sell">确 定</el-button>
      </div>
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
      totalPrice: 0,
      tableData: [],
      selectData: [],
      sellTable:[
        {
          goodName: '牙膏',
          sellPrice:'4',
          number: '2',
          unit: '管',
          repository: '1'
        },
        {
          goodName: '牙刷',
          sellPrice:'7',
          number:'3',
          unit: '支',
          repository: '1'
        }
      ],
      sellVisible:false
    }
  },
  created() {
    this.load();
    let key = "selectNum"
    let value = 0
    for (let i=0 ;i<this.tableData.length ;i++)
    {

      this.tableData[i][key] = value;
    }
    console.log(this.tableData)
  },
  methods: {
    query() {
      request.get('http://localhost:9090/index/sales/findgood',{
        params:{
          goodName:this.goodName
        }
      }).then(res=>{
        this.tableData = res.data;
      })
    },
    handleChange(){
      // var temp = 0;
      // for (var i=0 ;i<this.tableData.length ;i++)
      // {
      //   temp += this.tableData[i].selectNum * this.tableData[i].sellPrice;
      // }
      // this.totalPrice = temp;
    },
    getCustomer() {
      this.$message.success("选择成功");
    },
    handleSell(){
      this.sellVisible=true;
      this.tableData=[]
    },
    sell(){
      this.sellVisible=false
      this.tableData=[
        {
          goodName: '牙膏',
          sellPrice:'4',
          number: '13',
          unit: '管',
          repository: '1'
        },
        {
          goodName: '牙刷',
          sellPrice:'7',
          number:'21',
          unit: '支',
          repository: '1'
        },
        {
          goodName: '苹果',
          sellPrice:'3',
          number:'500',
          unit:'个',
          repository: '3',
        }
      ]
    },
    load(){
      request.get('http://localhost:9090/index/sales').then(res=>{
        this.tableData = res.data;
      })
    },
    searchCus(){
      this.load()
    }
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
