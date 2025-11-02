<script setup>


import router from "@/router";
import {ElMessage} from "element-plus";
import {localGet, uploadImgLabel} from "@/utils";
import {reactive, ref} from "vue";
import axios from "@/utils/axios";

const state = reactive({
    img:'',
    oriFileName:'',
})
const back = () => {
    router.back();
}

let addForm = ref({
    label_name:'',
    img:'',
})

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
    addForm.value.img = res.data;
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
    return "../../../public/labelImg/" + path
}

const doAdd = () =>{
    if (addForm.value.label_name === ''){
        ElMessage({
            message: "添加失败，标签名为空",
            showClose: true,
            duration: 2000,
            type: "error",
            onClose: () => {
                location.reload();
            }
        })
    }else{
        axios.post("/label/doAdd",addForm.value)
            .then(res=>{
                if (res){
                    ElMessage({
                        message: "添加成功",
                        showClose: true,
                        duration: 2000,
                        type: "success",
                        onClose: () => {
                            router.push("/labelMain")
                        }
                    })
                }else{
                    ElMessage({
                        message: "添加失败",
                        showClose: true,
                        duration: 2000,
                        type: "error",
                        onClose: () => {
                            location.reload();
                        }
                    })
                }
            })
    }

}
</script>

<template>、
    <h2>标签添加页</h2>
    <el-button type="warning" @click="back">返回</el-button>
    <el-card>
        <el-form>
            <el-form-item label="标签名">
                <el-input v-model="addForm.label_name"/>
            </el-form-item>
            <el-form-item>
                <el-upload v-model="state.img"
                           :before-upload="uploadBefore"
                           :action=uploadImgLabel
                           :headers=initToken
                           :on-success=success
                           :on-error=error
                           :show-file-list="false"
                >
                    <el-button type="success">选择图片</el-button>
                </el-upload>
                <img :src="check(state.img)" style="width: 200px">
            </el-form-item>
            <el-button type="primary" @click="doAdd">添加标签</el-button>
        </el-form>
    </el-card>
</template>

<style scoped>

</style>