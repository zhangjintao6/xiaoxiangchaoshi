<script setup>

import {Plus} from "@element-plus/icons-vue";
import {onMounted, reactive, ref} from "vue";
import axios from "@/utils/axios";
import {localGet, uploadImgLabel, uploadImgsServer} from "@/utils";
import {ElMessage} from "element-plus";
import router from "@/router";

const state = reactive(({
    page:1,
    limit:5,
    total:0,
    tableData:[],
    loading:false,
    showUpdate:false,
    img:'',
    check:'',

}))

let checkItem = ref({
    check:'',
});

const init = () =>{
    axios.get("/label/findAllLabel",{
        params:{
            page:state.page,
            limit:state.limit,
            check:checkItem.value.check,
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
})

const changePage = (val) => {
    state.page = val
    init();
}

let updateForm = ref({
    label_id:0,
    label_name:'',
    img:'',
});

const check = (path) => {
    return "../../../public/labelImg/" + path
}

const update = (label) => {
    updateForm.value.label_id = label.label_id;
    updateForm.value.label_name = label.label_name;
    updateForm.value.img = label.img;
    state.img = label.img
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

//保存更新修改
const doUpdate = () => {
    axios.post("/label/updateLabel",updateForm.value)
        .then(res=>{
            if (res){
                ElMessage({
                    message:"修改成功",
                    showClose:true,
                    duration:2000,
                    type:"success",
                    onClose:() => {
                        state.showUpdate = false;
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

//获取选中的行
const tableRef = ref();

//添加标签
const addLabel = () =>{
    router.push("/addLabel")
}

const deleteLabel = () =>{
    //getSelectionRows 获取选中行记录
    const selectAry = tableRef.value.getSelectionRows();
    //筛选字段，只取商品id字段
    let ids = selectAry.map(item => item.label_id);
    //console.log(ids);
    axios.post("/label/delByIds",ids)
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

const find = () => {
    checkItem.value.check = state.check;
    init();
}
</script>

<template>
  <h2>标签详情主页</h2>
  <el-card class="good-container">
      <template #header>
          <div class="header">
              <el-form>
                  <el-input v-model="state.check" placeholder="根据标签名查询" style="width:20%"/>
                  <el-button type="primary" @click="find">搜索</el-button>
                  <el-button type="primary" :icon="Plus" @click="addLabel">添加标签</el-button>
                  <el-button type="danger" @click="deleteLabel">删除选中标签</el-button>
              </el-form>
          </div>
      </template>
      <el-table ref="tableRef" :load="state.loading" :data="state.tableData" tooltip-effect="dark" style="width: 100%">
          <el-table-column type="selection" width="50px" fixed="left"></el-table-column>
          <el-table-column prop="label_id" label="标签id"/>
          <el-table-column prop="label_name" label="标签名"/>
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

    <!--标签修改界面-->
    <el-dialog title="修改标签" v-model="state.showUpdate">
        <el-form>
            <el-form-item label="标签名">
                <el-input v-model="updateForm.label_name"/>
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