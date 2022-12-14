<template>
    <div id="index">
        <el-container>
            <el-header>
                <Top :isLogin="isLogin"></Top>
            </el-header>
            <el-container>
                <el-aside width="200px">
                    <Aside :isLogin="isLogin"></Aside>
                </el-aside>
                <el-main style="min-height:calc(100vh - 60px)">
                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
import Top from '../components/Top'
import Aside from '../components/Aside'
import {EventBus} from "../utils/event-bus"

export default {
    name: "Index",
    components: {
        Top,
        Aside
    },
    data() {
        return {
            isLogin: false
        }
    },
    created() {
        if (sessionStorage.getItem('userName') != null)
            this.isLogin = true
    },
    mounted() {
        EventBus.$on("resetStatus",({isLogin}) => {
            this.isLogin = isLogin
        })
    }
}
</script>

<style type="text/css">
.el-header {
    background-color: #5F9EA0;
    color: #333;
    line-height: 60px;
}

.el-aside {
    color: #333;
    height: 100%;
}

html, body, .el-container, #index {
    padding: 0px;
    margin: 0px;
    height: 100%;
}
</style>
