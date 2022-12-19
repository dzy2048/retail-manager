<template>
    <div class="wrapper">
        <div style="margin: 200px auto;background-color: aliceblue;width: 350px;height: 300px;padding: 20px;border-radius: 10px">
            <div style="margin: 20px 0;text-align: center;font-size: 24px">
                <b>注册</b>
            </div>
            <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" v-model="registerForm.username" placeholder="请输入用户名"></el-input>
            <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password v-model="registerForm.password" placeholder="请输入密码"></el-input>
            <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" v-model="registerForm.checkPassword" placeholder="请再次输入密码"></el-input>
            <el-radio-group v-model="auth" style="margin-left: 28%">
                <el-radio label="1">管理员</el-radio>
                <el-radio label="0">店员</el-radio>
            </el-radio-group>
            <div style="margin: 20px 0;text-align: right">
                <el-button type="primary" size="small" autocomlpete="off" @click="$router.push('/')">去登录</el-button>
                <el-button type="warning" size="small" autocomlpete="off" @click="submitForm()">注册</el-button>
            </div>
        </div>
    </div>
</template>

<script>
import request from "@/utils/request";

export default {
    name: "Login",
    data(){
        return {
            auth: '0',
            registerForm: {
                username: '',
                password: '',
                checkPassword: '',
                authority: ''
            },
            rules: {
                username: [
                    {required: true, message: '用户名不能为空', trigger: 'blur'}
                ],
                password: [
                    {required: true, message: '密码不能为空', trigger: 'blur'}
                ],
                checkPassword: [
                    {required: true, message: '确认密码不能为空', trigger: 'blur'}
                ]
            }
        }
    },
    methods: {
        submitForm: function () {
            this.registerForm.authority = this.auth
            request.post('http://localhost:9090/register', this.registerForm).then(res => {
                if (res.status === "ok") {
                    this.$message.success("注册成功！")
                    this.$router.push('/')
                } else {
                    this.$message.error("用户名已被注册！")
                }
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
