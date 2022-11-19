<<template>
  <div>
    <div class="top">
      <el-form :inline="true">
        <el-form-item label="商品名称">
          <el-input type="text" v-model="goodName" ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary">查询</el-button>
        </el-form-item>
        <el-form-item label="客户">
          <el-input type="text" v-model="goodName" ></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary">选择</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="foot">
      <el-table
        :data="tableData"
        height="400"
        border
        style="width: 100%;height: 100%">
        <el-table-column
          prop="goodName"
          label="商品名称"
          width="180">
        </el-table-column>
        <el-table-column
          prop="wholePrice"
          label="批发价格">
        </el-table-column>
        <el-table-column
          prop="sellPrice"
          label="零售价格">
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-input-number v-model="scope.row.cartNumber" @change="handleChange" :min="0"  label="描述文字"></el-input-number>
          </template>
        </el-table-column>
      </el-table>
      <el-button>售出 </el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "Sales",
  data() {
    return {
      goodName: '',
      tableData: [
        {
          goodName: '牙刷',
          wholePrice: '5',
          sellPrice: '10',
        },
        {
          goodName: '牙膏',
          wholePrice: '8',
          sellPrice: '18',
        },
      ]
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
