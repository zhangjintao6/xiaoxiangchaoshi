<script setup>

import router from "@/router";
import {localGet, uploadImgProduct} from "@/utils";
import {onMounted, reactive, ref} from "vue";
import axios from "@/utils/axios";
import {ElMessage} from "element-plus";

const state = reactive({
    brandData:[],
    typeData:[],
})

const back = () => {
    router.back();
}

onMounted(()=>{
    axios.get("/product_brand/getAllBrand")
        .then(res=>{
            state.brandData = res;
        })
    axios.get("/product_types/getAllType")
        .then(res=>{
            state.typeData = res;
        })
})

let addForm = ref({
    product_id:0,
    product_name:'',
    description:'',
    price:0,
    stock:0,
    brand_id:0,
    type_id:0,
    img:'',
})

const checkProduct = (path) => {
    return "../../../public/productImg/" + path
}

const props = {
    label:'type_name',
    children:'childType',
}

const success = (res) =>{
    addForm.value.img = res.data;
}

//上传图片失败的回调函数
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

//上传图片之前的函数，用于检查图片格式
const uploadBefore = (file) =>{
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

const doAdd = () =>{
    axios.post("/products/doAdd",addForm.value)
        .then(res=>{
            if (res){
                ElMessage({
                    message:"添加成功",
                    showClose:true,
                    duration:2000,
                    type:"success",
                    onClose:() => {
                        router.push("/productMain");
                        location.reload();
                    }
                })
            }else{
                ElMessage({
                    message:"添加失败",
                    showClose:true,
                    duration:2000,
                    type:"error",
                    onClose:() => {
                        location.reload();
                    }
                })
            }
        })
}

</script>

<template>
  <h2>商品添加详情页</h2>
  <el-button type="warning" @click="back">返回</el-button>
    <el-card>
        <el-form style="width: 60%;align-items: center;justify-self: center">
            <el-form-item label="商品名">
                <el-input v-model="addForm.product_name"/>
            </el-form-item>
            <el-form-item label="商品描述">
                <el-input v-model="addForm.description"/>
            </el-form-item>
            <el-form-item label="价格">
                <el-input-number v-model="addForm.price"/>
            </el-form-item>
            <el-form-item label="库存">
                <el-input-number v-model="addForm.stock"/>
            </el-form-item>
            <el-form-item label="品牌">
                <el-select v-model="addForm.brand_id">
                    <el-option
                            v-for="brand in state.brandData"
                            :label="brand.brand_name"
                            :value="brand.brand_id"
                    />
                </el-select>
            </el-form-item>
            <el-form-item label="类型">
                <el-tree-select
                        v-model="addForm.type_id"
                        :props="props"
                        :data="state.typeData"
                        node-key="type_id"
                        :render-after-expand="false"
                        style="width: 240px"
                />
            </el-form-item>
            <el-form-item label="图片">
                <img :src="checkProduct(addForm.img)" style="width: 300px">
                <el-upload v-model="addForm.img"
                           :before-upload="uploadBefore"
                           :action=uploadImgProduct
                           :headers=initToken
                           :on-success=success
                           :on-error=error
                           :show-file-list="false"
                >
                    <el-button type="success">选择图片</el-button>
                </el-upload>
            </el-form-item>
            <el-button type="primary" @click="doAdd">添加商品</el-button>
        </el-form>
    </el-card>
</template>

<style scoped>

</style>