<template>
  <div class="rg_layout">
    <div class="rg_center">
      <h1>批发零售业务管理系统</h1>
      <el-form v-bind:model="userForm" v-bind:rules="rules" ref="userForm" status-icon label-width="100px" >
        <el-form-item label="用户名" prop="username">
          <el-col :span="22">
            <el-input type="text" v-model="userForm.username" placeholder="请输入用户名" ></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-col :span="22">
            <el-input type="password" v-model="userForm.password" placeholder="请输入密码" show-password ></el-input>
          </el-col>
        </el-form-item>
        <el-row>
          <el-button type="primary" @click="submitForm('userForm')">登录</el-button>
          <router-link to="/register" class="link">注册</router-link>
        </el-row>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      userForm:{
        username: '',
        password: ''
      },
      rules: {
        username: [
          {required:true, message:'用户名不能为空', trigger:'blur'}
        ],
        password: [
          {required:true, message:'密码不能为空', trigger:'blur'}
        ]
      }
    }
  },
  methods: {
    submitForm: function(formName){
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!');
        } else {
          console.log('error submit!');
          return false;
        }
      });
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
  .link
  {
    margin-left:50px;
  }
  .el-form
  {
    margin-top:10%;
  }
  .rg_layout
  {
    margin-top: 5%;
  }
</style>
