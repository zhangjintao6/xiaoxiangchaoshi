<template>
    <div class="login-container">
        <div class="form-container">
            <h1>登录</h1>
            <form @submit.prevent="login">
                <div class="form-group">
                    <label for="username">
                        <img src="../assets/img/utilImg/用户.png" alt="用户图标" class="icon" />
                        用户名:
                    </label>
                    <input type="text" id="username" v-model="user.username" />
                </div>
                <div class="form-group">
                    <label for="password">
                        <img src="../assets/img/utilImg/密码.png" alt="密码图标" class="icon" />
                        密码:
                    </label>
                    <input type="password" id="password" v-model="user.password" />
                </div>
                <button type="submit">登录</button>
                <button type="button" @click="goto('/register')" style="margin-top: 10px">去注册</button>
            </form>
        </div>
    </div>
</template>

<script setup>
import router from "@/router";
import {reactive} from "vue";
import axios from "axios";
import {localSet} from "@/utils";

const user = reactive({
    username: '',
    password: '',
})
const login = () => {
    // 在这里处理登录逻辑
    // console.log(user)
    axios.post("http://localhost:88/users/login", user).then(res=>{
        if (res.data.code === 200){
            localSet('token',res.data.data);
            const decoded = parseJwt(res.data.data);
            // 获取sub字段的值
            const sub = decoded.sub;
            const subObj = JSON.parse(sub.replace(/'/g, '"')); // 替换单引号为双引号
            console.log(subObj.role_id);
            if (subObj.role_id === 2){
                router.push('/shop');
            }
        }
    })
}
const goto = (url) => {
    router.push(url);
}

function parseJwt(token) {
    let base64Url = token.split('.')[1];
    let base64 = base64Url.replace('-', '+').replace('_', '/');
    let payload = decodeURIComponent(atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    return JSON.parse(payload);
}
</script>

<style scoped lang="less">
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-image: url('../assets/img/utilImg/th.jpg');
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
}

.form-container {
  background-color: rgba(255, 255, 255, 0.9);
  padding: 20px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

h1 {
  text-align: center;
  margin-bottom: 20px;
  font-size: 24px;
  font-weight: bold;
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: flex;
  align-items: center;
  margin-bottom: 5px;
  font-size: 16px;
}

input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 16px;
  background-color: #f5f5f5;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #5cb85c;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
  font-weight: bold;
}

button:hover {
  background-color: #4cae4c;
}

.icon {
  width: 20px;
  height: 20px;
  margin-right: 10px;
}
</style>
