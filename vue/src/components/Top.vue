<template>
    <div>
        <span class="title">批发零售管理系统</span>
        <img v-if="isLogin" src="../assets/user.png"  alt="user" width="25" height="25"/>
        <div v-if="isLogin" class="userinfo">
            <span class="infoItem">用户名: {{username}}</span>
            <span class="infoItem">用户权限: {{auth}}</span>
            <el-button id="logout" type="danger" round size="small" @click="logout()">退出</el-button>
        </div>
        <el-button class="login" v-else round size="small" @click="$router.push('/')">登录</el-button>
    </div>
</template>

<script>
import {EventBus} from "../utils/event-bus";

export default {
    name: "Top",
    props: ['isLogin'],
    data() {
        return {
            username: '',
            auth: '',
        }
    },
    created() {
        if (sessionStorage.getItem('authority') === '1')
            this.auth = '管理员'
        else
            this.auth = '非管理员'
        this.username = sessionStorage.getItem('userName')
    },
    methods: {
        logout() {
            sessionStorage.clear()
            EventBus.$emit("resetStatus",{
                isLogin : false
            })
            this.$router.push('/index')
        }
    }
}
</script>

<style scoped>
span.title {
    margin-top: 0;
    margin-left: 75%;
    font-size: larger;
    font-weight: bolder;
}
.login {
    margin-left: 9%;
    margin-bottom: 6px;
}
img {
    margin-left: 10%;
    cursor: pointer;
    position: absolute;
    top: 15px;
    z-index: 11;
}
img:hover+.userinfo{
    display: block;
}
.userinfo:hover{
    display: block;
}
.userinfo {
    margin-left: 88%;
    background: #FFFFFF;
    border: 2px solid #B3C0D1;
    border-radius: 5px;
    width: 140px;
    overflow: hidden;
    /*display: block;*/
    display: none;
    padding-left: 8px;
    position: absolute;
    top: 32px;
    z-index: 10;
}
.infoItem {         /*解决el-header的line-height问题*/
    display: block;
    line-height: 40px;
}
#logout {
    margin-left: 50%;
}
</style>
