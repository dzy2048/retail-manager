<template>
    <div class="wrapper">
        <div style="margin: 200px auto;background-color: aliceblue;width: 350px;height: 300px;padding: 20px;border-radius: 10px">
            <div style="margin: 20px 0;text-align: center;font-size: 24px">
                <b>登录</b>
            </div>
            <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" v-model="userForm.username" placeholder="请输入用户名"></el-input>
            <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password v-model="userForm.password" placeholder="请输入密码"></el-input>
            <div style="margin: 20px 0;text-align: right">
                <el-button type="primary" size="small" autocomlpete="off" @click="submitForm">登录</el-button>
                <el-button type="warning" size="small" autocomlpete="off" @click="$router.push('/register')">注册</el-button>
            </div>
        </div>
    </div>
</template>

<script>
import request from "@/utils/request";
export default {
    name: "Login",
    data() {
        return {
            userForm: {
                username: '',
                password: ''
            },
            rules: {
                username: [
                    {required: true, message: '用户名不能为空', trigger: 'blur'}
                ],
                password: [
                    {required: true, message: '密码不能为空', trigger: 'blur'}
                ]
            }
        }
    },
    methods: {
        submitForm: function () {
            request.post("http://localhost:9090/login", this.userForm).then(res => {
                if (res.code === "0") {
                    this.$message.success('登录成功')
                    sessionStorage.clear()
                    sessionStorage.setItem('userName',res.data.username)
                    sessionStorage.setItem('userId',res.data.userId)
                    sessionStorage.setItem('authority',res.data.authority)
                    this.$router.push('/index')
                } else {
                    this.$message.error("用户名或密码错误！")
                }
                console.log(res);
            })
        }
    }
}
</script>

<style scoped>
.wrapper{
    height: 100vh;
    background-image: linear-gradient(to bottom right,#FC466B, #3F5EFB);
    overflow: hidden;
}
</style>
