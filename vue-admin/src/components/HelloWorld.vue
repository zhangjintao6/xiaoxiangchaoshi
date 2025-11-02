<script setup>
import { ref } from 'vue'
import router from "@/router";
import {localRemove} from "@/utils";

defineProps({
  msg: String,
})

const count = ref(0)

const go = (path) => {
    router.push(path);
}

const exit = () =>{
    localRemove('token');
    go('/login')
}
</script>

<template>
  <div class="mainBack">
      <h1 style="margin-top: 0">欢迎使用管理员系统</h1>
      <p>
          <br>
          <el-button type="primary" @click="go('/userMain')">用户主页</el-button>
          <el-button type="warning" @click="go('/productMain')">商品主页</el-button>
          <el-button type="success" @click="go('/typeMain')">类型主页</el-button>
          <el-button type="info" @click="go('/labelMain')">标签主页</el-button>
          <el-button type="primary" @click="go('/promotionMain')">活动主页</el-button>
      </p>
      <p>
          <el-button type="danger" @click="exit">退出登录</el-button>
      </p>
  </div>
</template>

<style scoped>
.read-the-docs {
  color: #888;
}
.mainBack{
    width: 100%;
    height: 100%;
    padding: 0;
    background-image: url('../../public/userImg/mainBackground.jpg');
}
</style>
