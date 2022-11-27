<template>
  <div class="rg_layout">
    <div class="rg_center">
      <h1>批发零售业务管理系统</h1>
      <el-form v-bind:model="registerForm" v-bind:rules="rules" ref="registerForm" status-icon label-width="100px" >
        <el-form-item label="用户名" prop="username">
          <el-col :span="22">
            <el-input type="text" v-model="registerForm.username" placeholder="请输入用户名" ></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-col :span="22">
            <el-input type="password" v-model="registerForm.password" placeholder="请输入密码" show-password ></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="确认密码" prop="password">
          <el-col :span="22">
            <el-input type="password" v-model="registerForm.checkPassword" placeholder="请再次输入密码" show-password ></el-input>
          </el-col>
        </el-form-item>
        <el-form-item>
          <el-row>
            <el-checkbox v-model="checked">管理员</el-checkbox>
            <el-button type="primary" @click="submitForm()">注册</el-button>
          </el-row>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Register",
  data() {
    return {
      checked: true,
      registerForm:{
        username: '',
        password: '',
        checkPassword: '',
        authority: ''
      },
      rules: {
        username: [
          {required:true, message:'用户名不能为空', trigger:'blur'}
        ],
        password: [
          {required:true, message:'密码不能为空', trigger:'blur'}
        ],
        checkPassword: [
          {required:true, message:'确认密码不能为空', trigger:'blur'}
        ]
      }
    }
  },
  methods: {
    submitForm: function(){
      if (this.checked==true)
      {
        this.registerForm.authority = '1'
      }
      else
      {
        this.registerForm.authority = '0'
      }
      console.log(this.registerForm.authority);
      request.post('http://localhost:9090/register',this.registerForm).then(res => {
        if (res.status === "ok")
        {
          this.$message.success("注册成功！")
          this.$router.push('/')
        }
        else
        {
          this.$message.error("用户名已被注册！")
        }
      })
    }
  }
}
</script>

<style scoped>
  .rg_layout
  {
    width: 900px;
    height: 500px;
    border: 5px solid #EEEEEE;
    background-color: white;
    /*水平居中*/
    margin: auto;
    margin-top: 15px;
  }
  .rg_center
  {
    width:450px;
    padding-left:22%;
    padding-top:10%;
  }
  .rg_layout
  {
    margin-top: 5%;
  }
  h1
  {
    padding-left: 80px;
  }
  label.el-checkbox
  {
    margin-left: 70px;
  }
  .el-button
  {
    margin-left: 50px;
  }
</style>
