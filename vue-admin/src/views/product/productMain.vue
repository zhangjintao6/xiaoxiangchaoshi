<script setup>

import {onMounted, reactive, ref} from "vue";
import axios from "@/utils/axios";
import {Plus} from "@element-plus/icons-vue";
import router from "@/router";
import {ElMessage} from "element-plus";
import {localGet, uploadImgProduct, uploadImgsServer, uploadImgType} from "@/utils";

const state = reactive({
    page:1,
    limit:5,
    total:0,
    tableData:[],
    imgDetail:[],
    loading:false,
    showImage:false,
    img:'',
    newImg:'',
    oriFileName:'',
    product_id:0,
    showUpdate:false,
    brandData:[],//所有品牌
    typeData:[],//所有类型
    mainImg:'',
    check:'',
})

onMounted(()=>{
    init();
    axios.get("/product_brand/getAllBrand")
        .then(res=>{
            state.brandData = res;
        })
    axios.get("/product_types/getAllType")
        .then(res=>{
            state.typeData = res;
        })
})

const init = () =>{
    axios.get("/products/findAllProducts",{
        params:{
            page:state.page,
            limit:state.limit,
            check:checkItem.value.check,
        }
    })
        .then(res=>{
            state.total = res.total;
            state.tableData = res.records;
            state.page = res.current;
            state.check = '';
        })
}

//分页操作
const changePage = (val) => {
    state.page = val
    init();
}

//修改表单
let updateForm = ref({
    product_id:0,
    product_name:'',
    description:'',
    price:0,
    stock:0,
    brand_id:0,
    type_id:0,
    img:'',
})

//修改商品的信息
const update = (product) =>{
    updateForm.value.product_id = product.product_id;
    updateForm.value.product_name = product.product_name;
    updateForm.value.description = product.description;
    updateForm.value.price = product.price;
    updateForm.value.stock = product.stock;
    updateForm.value.brand_id = product.brand_id;
    updateForm.value.type_id = product.type_id;
    updateForm.value.img = product.img;
    state.mainImg = product.img;
    state.showUpdate = true;
}

//查看图片详情
const checkImg = (pid) =>{
    state.product_id = pid;
    axios.get("/img_detail/getImageDetail?pid="+pid)
        .then(res=>{
            state.imgDetail = res;
            //console.log(res);
        })
    state.showImage = true;
}

//拼接路径
const check = (path) => {
    //console.log("../../../public/productImg/" + path.img)
    if (state.img){
        path.img = state.img;
        updateImg(path);
        state.img = '';
    }
    return "../../../public/productImg/" + path.img
}

const checkProduct = (path) => {
    //console.log("../../../public/productImg/" + path.img)
    return "../../../public/productImg/" + path
}

//将修改图片保存至数据库
const updateImg = (img_detail) =>{
    axios.post("/img_detail/updateImg",img_detail)
        .then(res=>{
            if (res){
                ElMessage({
                    message:"保存成功",
                    showClose:true,
                    duration:2000,
                    type:"success"
                })
            }else{
                ElMessage({
                    message:"保存失败",
                    showClose:true,
                    duration:2000,
                    type:"error"
                })
            }
        })
}

//跳转添加页面
const addProduct = () => {
    router.push("/addProduct");
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


//上传图片成功的回调函数
const success = (res) => {
    state.img = res.data;
}

const su = (res) =>{
    state.newImg = res.data;
    axios.post("/img_detail/addImg",{
        product_id:state.product_id,
        img:state.newImg,
    }).then(res=>{
        if (res){
            checkImg(state.product_id)
        }
    })
}

const success3 = (res) =>{
    updateForm.value.img = res.data;
    state.mainImg = res.data;
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

const delImg = (detail) =>{
    //console.log(detail);
    axios.post("/img_detail/delImg",detail)
        .then(res=>{
            if (res){
                checkImg(detail.product_id);
            }
        })
}

const props = {
    label:'type_name',
    children:'childType',
}

const doUpdate = () =>{
    console.log(updateForm.value)
    axios.post("/products/doUpdate",updateForm.value)
        .then(res=>{
            if (res){
                ElMessage({
                    message:"修改成功",
                    showClose:true,
                    duration:2000,
                    type:"success"
                })
            }else{
                ElMessage({
                    message:"修改失败",
                    showClose:true,
                    duration:2000,
                    type:"error"
                })
            }
            location.reload();
        })
}

//获取选中的行
const tableRef = ref();

const deletePro = () =>{
    //getSelectionRows 获取选中行记录
    const selectAry = tableRef.value.getSelectionRows();
    //筛选字段，只取商品id字段
    let ids = selectAry.map(item => item.product_id);
    //console.log(ids);
    axios.post("/products/delByIds",ids)
        .then(res=>{
            if (res){
                ElMessage({
                    message:"删除成功",
                    showClose:true,
                    duration:2000,
                    type:"success"
                })
            }else{
                ElMessage({
                    message:"删除失败",
                    showClose:true,
                    duration:2000,
                    type:"error"
                })
            }
            location.reload();
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
  <h2>商品管理主页</h2>
  <el-card class="good-container">
      <template #header>
          <div class="header">
              <el-form>
                  <el-input v-model="state.check" placeholder="根据商品查询" style="width:20%"/>
                  <el-button type="primary" @click="find">搜索</el-button>
                  <el-button type="primary" :icon="Plus" @click="addProduct">添加商品</el-button>
                  <el-button type="danger" @click="deletePro">删除选中商品</el-button>
              </el-form>
          </div>
      </template>
      <el-table ref="tableRef" :load="state.loading" :data="state.tableData" tooltip-effect="dark" style="width: 100%">
          <el-table-column type="selection" width="50px" fixed="left"></el-table-column>
          <el-table-column prop="product_id" label="商品id" width="80px"/>
          <el-table-column prop="product_name" label="商品名" @click=""/>
          <el-table-column prop="description" label="描述"/>
          <el-table-column prop="price" label="价格"/>
          <el-table-column prop="sale_num" label="销量"/>
          <el-table-column prop="stock" label="库存"/>
          <el-table-column prop="brand.brand_name" label="品牌"/>
          <el-table-column prop="types.type_name" label="类型"/>
          <el-table-column prop="create_time" label="上架时间"/>
          <el-table-column label="图片">
              <template #default="scope">
                  <img :src="checkProduct(scope.row.img)" style="width: 80px">
              </template>
          </el-table-column>
          <el-table-column label="图片详情" width="100">
              <template #default="scope">
                  <el-button type="primary" @click="checkImg(scope.row.product_id)">查看</el-button>
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
    <!--展示图片详情对话框-->
    <el-dialog v-model="state.showImage">
        <template v-for="(item,index) in state.imgDetail" >
            <el-upload v-model="state.img"
                       :before-upload="uploadBefore"
                       :action=uploadImgsServer
                       :headers=initToken
                       :data="item"
                       :on-success=success
                       :on-error=error
                       :show-file-list="false"
            >
            <img :src="check(item)" width="200px">
            </el-upload>
            <el-button type="danger" @click="delImg(item)">删除</el-button>
            <br>
            <br>
        </template>
        <el-upload v-model="state.newImg"
                   :before-upload="uploadBefore"
                   :action=uploadImgsServer
                   :headers=initToken
                   :on-success=su
                   :on-error=error
                   :show-file-list="false"
        >
            <el-button type="success">添加图片</el-button>
        </el-upload>
    </el-dialog>

    <!--修改对话框-->
    <el-dialog title="修改商品" v-model="state.showUpdate">
        <el-form>
            <el-form-item label="商品名">
                <el-input v-model="updateForm.product_name"/>
            </el-form-item>
            <el-form-item label="商品描述">
                <el-input v-model="updateForm.description"/>
            </el-form-item>
            <el-form-item label="价格">
                <el-input-number v-model="updateForm.price"/>
            </el-form-item>
            <el-form-item label="库存">
                <el-input-number v-model="updateForm.stock"/>
            </el-form-item>
            <el-form-item label="品牌">
                <el-select v-model="updateForm.brand_id">
                    <el-option
                        v-for="brand in state.brandData"
                        :label="brand.brand_name"
                        :value="brand.brand_id"
                    />
                </el-select>
            </el-form-item>
            <el-form-item label="类型">
                <el-tree-select
                    v-model="updateForm.type_id"
                    :props="props"
                    :data="state.typeData"
                    node-key="type_id"
                    :render-after-expand="false"
                    style="width: 240px"
                />
            </el-form-item>
            <el-form-item label="图片">
                <img :src="checkProduct(state.mainImg)" style="width: 300px">
                <el-upload v-model="state.mainImg"
                           :before-upload="uploadBefore"
                           :action=uploadImgProduct
                           :headers=initToken
                           :on-success=success3
                           :on-error=error
                           :show-file-list="false"
                >
                    <el-button type="success">修改图片</el-button>
                </el-upload>
            </el-form-item>
            <el-button type="primary" @click="doUpdate">保存修改</el-button>
        </el-form>
    </el-dialog>
</template>

<style scoped>

</style>