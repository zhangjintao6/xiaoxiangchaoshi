<script setup>

import {reactive} from "vue";
import axios from "@/utils/axios";
import {localSet} from "@/utils";
import router from "@/router";
import {ElMessage} from "element-plus";

const state = reactive({
    username:'',
    password:'',
})

const login = () =>{
    axios.post('users/login',{
        username:state.username,
        password:state.password,
    }).then(res=>{
        if (res.code == 200){
            localSet('token',res.data);
            const decoded = parseJwt(res.data);
            // 获取sub字段的值
            const sub = decoded.sub;
            const subObj = JSON.parse(sub.replace(/'/g, '"')); // 替换单引号为双引号
            if (subObj.role_id == 1){
                router.push('/');
            }else{
                ElMessage({
                    showClose:true,
                    message:'身份不匹配或者账户已被禁用',
                    onClose:(()=>{
                        location.reload();
                    })
                })
            }
        }else{
            ElMessage({
                showClose:true,
                message: res.data,
                onClose:(()=>{
                    location.reload();
                })
            })
        }
    })
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

<template>
  <div class="loginBackground">
      <div class="login">
          <div style="width: 100%;height: 20%;">
              <span style="color:white;text-align: center;font-size: 28px;">
                  管理员登录
              </span>
          </div>
          <div style="width: 80%;height: 60%">
              <el-form>
                  <el-form-item label="账号:">
                      <el-input v-model="state.username"/>
                  </el-form-item>
                  <el-form-item label="密码:">
                      <el-input type="password" v-model="state.password"/>
                  </el-form-item>
                  <el-button type="primary" @click="login">登录</el-button>
              </el-form>
          </div>
      </div>
  </div>
</template>

<style scoped>
  .loginBackground {
      justify-content: center; /* 水平居中 */
      align-items: center;     /* 垂直居中 */
      background-image: url('../../public/userImg/login.webp'); /* 设置背景图片 */
      background-size: cover; /* 图片覆盖整个div，可能会裁剪图片 */
      background-repeat: no-repeat; /* 图片不重复 */
      background-position: center; /* 可选，使图片从中心开始覆盖 */
      width: 100%;
      height: 100vh; /* 或者你想要的任何高度 */
      position: relative;

  }
  .login{
      width: 20%;
      height: 40%;
      position: absolute;
      top: 30%;
      left: 10%;
      background-color: rgba(45,142,189, 0); /* 半透明的白色背景 */
      display: flex;
      justify-content: center; /* 水平居中 */
      align-items: center;     /* 垂直居中 */
      display: flex;
      flex-direction: column; /* 设置主轴方向为垂直 */
      //padding: 50px 0;
  }
</style>