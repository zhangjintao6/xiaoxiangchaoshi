<script setup>
import HelloWorld from './components/HelloWorld.vue'
import {localGet, pathMap} from "@/utils";
import router from "@/router";
import {reactive} from "vue";

const state = reactive({
    showMenu:false,
    currentPath: '/',
})


router.beforeEach((to, from, next) => {
    if (to.path == '/login') {
        // 如果路径是 /login 则正常执行
        state.showMenu = false;
        next();
    } else {
        if (localGet('token')){
            state.showMenu = true;
            next();
        }else{
            state.showMenu = false;
            router.push({path:'/login'});
        }
    }
    state.currentPath = to.path
    document.title = pathMap[to.name]
})

const go = (path) =>{
    router.push(path);
    //location.reload();
}
</script>

<template>

    <div class="layout">
        <el-container v-if="state.showMenu" class="container">
            <el-aside class="aside">
                <div class="head">
                    <div>
                        <img src="//s.weituibao.com/1582958061265/mlogo.png" alt="logo">
                        <span @click="go('/')">管理员欢迎你</span>
                    </div>
                </div>
                <div class="line" />
                <el-menu
                    background-color="#222832"
                    text-color="#fff"
                    :router="true"
                >
                    <el-sub-menu index="1">
                        <template #title>
                            <span>用户管理</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="/userMain">用户管理主页</el-menu-item>
                        </el-menu-item-group>
                    </el-sub-menu>
                    <el-sub-menu index="2">
                        <template #title>
                            <span>商品管理</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="/productMain">商品管理主页</el-menu-item>
                            <el-menu-item index="/addProduct">商品添加详情页</el-menu-item>
                        </el-menu-item-group>
                    </el-sub-menu>
                    <el-sub-menu index="3">
                        <template #title>
                            <span>标签管理</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="/labelMain">商品标签主页</el-menu-item>
                        </el-menu-item-group>
                    </el-sub-menu>
                    <el-sub-menu index="4">
                        <template #title>
                            <span>类型管理</span>
                        </template>
                        <el-menu-item-group>
                            <el-menu-item index="/typeMain">商品类型主页</el-menu-item>
                        </el-menu-item-group>
                    </el-sub-menu>
<!--                    <el-sub-menu index="5">-->
<!--                        <template #title>-->
<!--                            <span>促销活动管理</span>-->
<!--                        </template>-->
<!--                        <el-menu-item-group>-->
<!--                            <el-menu-item index="/promotionMain">促销活动主页</el-menu-item>-->
<!--                        </el-menu-item-group>-->
<!--                    </el-sub-menu>-->
                </el-menu>
            </el-aside>
            <el-container class="content">
                <Header />
                <div class="main">
                    <router-view />
                </div>
                <Footer />
            </el-container>
        </el-container>
        <el-container v-else class="container">
            <router-view/>
        </el-container>
    </div>
</template>

<style scoped>
.layout {
    min-height: 100vh;
    background-color: #ffffff;
}
.container {
    height: 100vh;
}
.aside {
    width: 200px!important;
    background-color: #222832;
}
.head {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 50px;
}
.head > div {
    display: flex;
    align-items: center;
}

.head img {
    width: 50px;
    height: 50px;
    margin-right: 10px;
}
.head span {
    font-size: 20px;
    color: #ffffff;
}
.line {
    border-top: 1px solid hsla(0,0%,100%,.05);
    border-bottom: 1px solid rgba(0,0,0,.2);
}
.content {
    display: flex;
    flex-direction: column;
    max-height: 100vh;
    overflow: hidden;
}
.main {
    height: calc(100vh - 100px);
    overflow: auto;
    padding: 10px;
}

body {
    padding: 0;
    margin: 0;
    box-sizing: border-box;
}
.el-menu {
    border-right: none!important;
}
.el-submenu {
    border-top: 1px solid hsla(0, 0%, 100%, .05);
    border-bottom: 1px solid rgba(0, 0, 0, .2);
}
.el-submenu:first-child {
    border-top: none;
}
.el-submenu [class^="el-icon-"] {
    vertical-align: -1px!important;
}

.logo {
  height: 6em;
  padding: 0;
  will-change: filter;
  transition: filter 300ms;
}
.logo:hover {
  filter: drop-shadow(0 0 2em #646cffaa);
}
.logo.vue:hover {
  filter: drop-shadow(0 0 2em #42b883aa);
}
</style>
