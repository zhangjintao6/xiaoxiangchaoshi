<script setup>
import {onMounted, reactive, ref} from 'vue'
import {localGet} from "@/utils";
import router from "@/router";

const state = reactive({
    tableData:[],
})


</script>

<template>
    <h2>用户管理首页</h2><br>
    <el-table :data="state.tableData" tooltip-effect="dark" style="width:100%">
        <el-table-column prop="id" label="用户编号"/>
        <el-table-column prop="username" label="用户名"/>
        <el-table-column prop="password" label="密码"/>
        <el-table-column prop="sex" label="性别"/>
        <el-table-column prop="age" label="年龄"/>
        <el-table-column prop="birthday" label="生日"/>
        <el-table-column prop="nickname" label="昵称"/>
        <el-table-column prop="phone" label="昵称"/>
        <el-table-column prop="img" label="头像"/>
        <el-table-column prop="account" label="钱包余额"/>
        <el-table-column prop="integeral" label="积分"/>
        <el-table-column prop="last_login_time" label="最后登录时间"/>
        <el-table-column prop="role.role_name" label="身份"/>
    </el-table>

</template>

<style scoped>

</style>