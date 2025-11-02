<script setup>

import {Plus} from "@element-plus/icons-vue";
import {onMounted, reactive, ref} from "vue";
import axios from "@/utils/axios";
import {localGet, uploadImgLabel, uploadImgType} from "@/utils";
import {ElMessage} from "element-plus";
import router from "@/router";

const state = reactive({
    page:1,
    limit:5,
    total:0,
    tableData:[],
    loading:false,
    showUpdate:false,
    parentType:[],
    is_parent:1,
    img:'',
    check:'',
})

const updateForm = ref({
    type_id:0,
    type_name:'',
    is_parent:0,
    parent_id:0,
    img:'',
})

const init = () => {
    axios.get("/product_types/findAllTypes",{
        params:{
            page:state.page,
            limit:state.limit,
            check:checkItem.value.check
        }
    }).then(res=>{
        state.page = res.current;
        state.total = res.total;
        state.tableData = res.records;
        state.check = '';
    })
}

onMounted(() => {
    init();
    axios.get("/product_types/getParentType")
        .then(res=>{
            state.parentType = res;
        })
})

const changePage = (val) => {
    state.page = val
    init();
}

const tableRef = ref();

const update = (type) =>{
    updateForm.value.type_id = type.type_id;
    updateForm.value.type_name = type.type_name;
    //updateForm.value.is_parent = type.is_parent;
    updateForm.value.parent_id = type.parent_id;
    updateForm.value.img = type.img;
    state.img = type.img;
    state.is_parent = type.is_parent;
    state.showUpdate = true;
}

//上传图片之前的函数，用于检查图片格式
const uploadBefore = (file) =>{
    // console.log("handleBeforeUpload");
    //保存上传的文件名
    state.oriFileName = file.name;
    //console.log(state.oriFileName);
    const isJPEG = file.name.split('.')[1] === 'jpeg';
    const isJPG = file.name.split('.')[1] === 'jpg';
    const isPNG = file.name.split('.')[1] === 'png';
    const isWEBP = file.name.split('.')[1] === 'webp';
    const isGIF = file.name.split('.')[1] === 'gif';
    const isLt500K = file.size / 1024 / 1024 / 1024 / 1024 < 4;
    if (!isJPG && !isJPEG && !isPNG && !isWEBP && !isGIF) {
        ElMessage({
            message: '上传图片只能是 JPEG/JPG/PNG 格式!',
            type: 'warning',
            plain: true,
        })
    }
    if (!isLt500K) {
        ElMessage({
            message: '单张图片大小不能超过 4mb!',
            type: 'warning',
            plain: true,
        })
    }
    return (isJPEG || isJPG || isPNG || isWEBP || isGIF) && isLt500K;
}

const initToken = reactive({
    token: localGet('token'),
})

const success = (res) => {
    state.img = res.data;
    updateForm.value.img = res.data;
}

const error = () => {
    ElMessage({
        message:"失败",
        showClose:true,
        duration:2000,
        type:"error",
        onClose:() => {
            location.reload();
        }
    })
}

const check = (path) => {
    return "../../../public/typeImg/" + path
}

const doUpdate = () => {
    updateForm.value.is_parent = state.is_parent;
    axios.post("/product_types/doUpdate",updateForm.value)
        .then(res=>{
            if (res){
                ElMessage({
                    message:"修改成功",
                    showClose:true,
                    duration:2000,
                    type:"success",
                    onClose:()=>{
                        location.reload();
                    }
                })
            }else{
                ElMessage({
                    message:"修改失败",
                    showClose:true,
                    duration:2000,
                    type:"error"
                })
            }
            state.showUpdate = false;
        })
}

const addType = () =>{
    router.push("/addType");
}

const deleteType = () =>{
    //getSelectionRows 获取选中行记录
    const selectAry = tableRef.value.getSelectionRows();
    //筛选字段，只取商品id字段
    let ids = selectAry.map(item => item.type_id);
    //console.log(ids);
    axios.post("/product_types/delByIds",ids)
        .then(res=>{
            if (res){
                ElMessage({
                    message:"删除成功",
                    showClose:true,
                    duration:2000,
                    type:"success",
                    onClose:()=>{
                        location.reload();
                    }
                })
            }else{
                ElMessage({
                    message:"删除失败",
                    showClose:true,
                    duration:2000,
                    type:"error",
                    onClose:()=>{
                        location.reload();
                    }
                })
            }
        })
}

let checkItem = ref({
    check:'',
});

const find = () => {
    checkItem.value.check = state.check;
    init();
}

</script>

<template>
  <h2>类型详情主页</h2>
    <el-card class="good-container">
        <template #header>
            <div class="header">
                <el-form>
                    <el-input v-model="state.check" placeholder="根据类型名查询" style="width:20%"/>
                    <el-button type="primary" @click="find">搜索</el-button>
                    <el-button type="primary" :icon="Plus" @click="addType">添加类型</el-button>
                    <el-button type="danger" @click="deleteType">删除选中类型</el-button>
                </el-form>
            </div>
        </template>
        <el-table ref="tableRef" :load="state.loading" :data="state.tableData" tooltip-effect="dark" style="width: 100%">
            <el-table-column type="selection" width="50px" fixed="left"></el-table-column>
            <el-table-column prop="type_id" label="类型id"/>
            <el-table-column prop="type_name" label="类型名"/>
            <el-table-column prop="parentType.type_name" label="父类型名"/>
            <el-table-column label="图片">
                <template #default="scope">
                    <img :src="check(scope.row.img)" style="width: 80px">
                </template>
            </el-table-column>
            <el-table-column label="操作">
                <template #default="scope">
                    <el-button type="success" @click="update(scope.row)">修改</el-button>
                </template>
            </el-table-column>
        </el-table>
        <!--总数超过一页，再展示分页器-->
        <el-pagination
                background
                layout="prev, pager, next"
                :total="state.total"
                :page-size="state.limit"
                :current-page="state.page"
                @current-change="changePage"
        />
    </el-card>
    <!--修改对话框-->
    <el-dialog v-model="state.showUpdate">
        <el-form>
            <el-form-item label="类型名">
                <el-input v-model="updateForm.type_name"/>
            </el-form-item>
            <el-form-item label="是否为父类型">
                <el-select v-model="state.is_parent">
                    <el-option label="是" :value="1"/>
                    <el-option label="否" :value="0"/>
                </el-select>
            </el-form-item>
            <el-form-item label="所属父类" v-if="state.is_parent === 0">
                <el-select v-model="updateForm.parent_id">
                    <el-option
                        v-for="type in state.parentType"
                        :label="type.type_name"
                        :value="type.type_id"
                    />
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-upload v-model="state.img"
                           :before-upload="uploadBefore"
                           :action=uploadImgType
                           :headers=initToken
                           :on-success=success
                           :on-error=error
                           :show-file-list="false"
                >
                    <el-button type="success">更改图片</el-button>
                </el-upload>
                <img :src="check(state.img)" style="width: 200px">
            </el-form-item>
            <el-button type="primary" @click="doUpdate">保存修改</el-button>
        </el-form>
    </el-dialog>
</template>

<style scoped>

</style>