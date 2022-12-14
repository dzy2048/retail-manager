<template>
    <div>
        <span class="title">批发零售管理系统</span>
        <img v-if="username!=null" src="../assets/user.png"  alt="user" width="25" height="25"/>
        <div v-if="username!=null" class="login" @mouseenter="enter()" @mouseleave="leave()">
            <span>用户名: {{username}}</span><br>
            <span>用户权限: {{auth}}</span>
        </div>
        <el-button v-else round size="small" @click="$router.push('/')">登录</el-button>
    </div>
</template>

<script>
export default {
    name: "Top",
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
        enter() {
            this.seen = true
        },
        leave() {
            this.seen = false
        }
    }
}
</script>

<style scoped>
span.title {
    margin-top: 0px;
    margin-left: 80%;
    font-size: larger;
    font-weight: bolder;
}
.el-button {
    margin-left: 2%;
    margin-bottom: 6px;
}
img {
    margin-left: 3%;
    cursor: pointer;
}
img:hover+.login{
    display: block;
}
.login {
    margin-left: 88%;
    background: #FFFFFF;
    border: 2px solid #B3C0D1;
    width: 140px;
    overflow: hidden;
    display: none;
    padding-left: 8px;
    position: absolute;
    z-index: 10;
}
</style>
