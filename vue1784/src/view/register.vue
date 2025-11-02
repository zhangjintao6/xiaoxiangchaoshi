<template>
    <img src="../view/public/img/返回.png" @click="goBack()" style="width: 20px;margin-top: 10px"/>
    <div class="registration-container">
        <div class="form-container">
            <h1>注册</h1>
            <form>
                <div class="form-group">
                    <label for="username">用户名:</label>
                    <input type="text" id="username" v-model="user.username" required />
                </div>
                <div class="form-group">
                    <label for="password">密码:</label>
                    <input type="password" id="password" v-model="user.password" required />
                </div>
                <div class="form-group">
                    <label for="sex">性别:</label>
                    <select id="sex" v-model="user.sex">
                        <option value="男">男</option>
                        <option value="女">女</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="age">年龄:</label>
                    <input type="number" id="age" v-model="user.age" />
                </div>
                <div class="form-group">
                    <label for="birthday">生日:</label>
                    <input type="date" id="birthday" v-model="user.birthday" />
                </div>
                <div class="form-group">
                    <label for="nickname">昵称:</label>
                    <input type="text" id="nickname" v-model="user.nickname" />
                </div>
                <div class="form-group">
                    <label for="phone">电话:</label>
                    <input type="tel" id="phone" v-model="user.phone" />
                </div>
                <button type="button" @click="register()">注册</button>
            </form>
        </div>
    </div>
</template>

<script setup>
import axios from "axios";
import {reactive} from "vue";
import router from "@/router";

const goBack = () => history.back();
const user = reactive({
    username: '',
    password: '',
    sex: '',
    age: '',
    birthday: '',
    nickname: '',
    phone: ''
});
const register = () => {
    console.log(user);
    axios.post("http://localhost:88/users/register", user).then(res=>{
        router.push('/login');
    })
}
</script>

<style scoped lang="less">
.registration-container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 120vh; /* 视口高度的120% */
    background-image: url('../view/public/img/login.jpg'); /* 替换为正确的路径 */
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
    overflow-y: scroll; /* 始终显示滚动条 */
    -webkit-overflow-scrolling: touch; /* 移动设备上的滚动优化 */
}

.form-container {
    background-color: rgba(255, 255, 255, 0.9);
    padding: 36px;
    border-radius: 8px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    width: 100%; /* 或者您想要的宽度 */
    max-width: 400px; /* 限制最大宽度，根据需要调整 */
    box-sizing: border-box; /* 确保内边距和边框包含在宽度内 */
    overflow-y: scroll; /* 始终显示滚动条 */
    -webkit-overflow-scrolling: touch; /* 移动设备上的滚动优化 */
    height: 983px;
}

h1 {
    width: 100%; /* 或者您想要的宽度 */
    max-width: 400px; /* 限制最大宽度，根据需要调整 */
    padding: 20px; /* 添加一些内边距 */
    box-sizing: border-box; /* 确保内边距和边框包含在宽度内 */
    overflow-y: scroll; /* 始终显示滚动条 */
    -webkit-overflow-scrolling: touch; /* 移动设备上的滚动优化 */
}

.form-group {  margin-bottom: 15px;
}

label {
    display: flex;
    align-items: center;
    margin-bottom: 5px;
    font-size: 16px;
}

input, select {
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

