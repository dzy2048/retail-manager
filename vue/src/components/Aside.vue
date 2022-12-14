<template>
    <el-row class="tac">
        <el-col :span="18">
            <el-menu
                default-active="3"
                router
                class="el-menu-vertical-demo"
                @open="handleOpen"
                @close="handleClose">
                <el-menu-item index="/index/sales" :disabled="!isLogin">
                    <i class="el-icon-document-remove"></i>
                    <span slot="title">开销售单</span>
                </el-menu-item>
                <el-menu-item index="/index/order" :disabled="!isLogin">
                    <i class="el-icon-menu"></i>
                    <span slot="title">订单管理</span>
                </el-menu-item>
                <el-menu-item index="/index/customer" :disabled="!isLogin">
                    <i class="el-icon-message"></i>
                    <span slot="title">客户信息</span>
                </el-menu-item>
                <el-menu-item index="/index/good" :disabled="!isLogin">
                    <i class="el-icon-menu"></i>
                    <span slot="title">商品管理</span>
                </el-menu-item>
                <el-menu-item index="/index/repository" :disabled="!isLogin">
                    <i class="el-icon-document"></i>
                    <span slot="title">库存管理</span>
                </el-menu-item>
                <el-menu-item index="/index/dispatch" :disabled="!isLogin">
                    <i class="el-icon-setting"></i>
                    <span slot="title">库存调度</span>
                </el-menu-item>
            </el-menu>
        </el-col>
    </el-row>
</template>

<script>
export default {
    name: "Aside",
    props: ['isLogin'],
    data() {
        return  {
        }
    },
    methods: {
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        }
    },
    created() {

    }
}
</script>

<style scoped>
.el-menu {
    min-height: calc(100vh - 60px);
}

.router-link {
    text-decoration: none;
}

</style>
