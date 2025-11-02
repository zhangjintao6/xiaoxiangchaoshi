<template>
    <div class="body">
        <!-- 图片容器，设置为相对定位 -->
        <div class="image-container">
            <img src="../../../assets/img/utilImg/返回.png" class="top-image" @click="goto('/sort')"/>
            <img src="../../../assets/img/utilImg/搜素.png" class="top-image1" @click="goto('/typeSearch')"/>
        </div>
        <!-- 轮播图，设置为绝对定位 -->
        <van-swipe v-if="obj != null" class="my-swipe" :autoplay="3000" indicator-color="white">
            <template v-for="(item,index) in state.tableData">
                <van-swipe-item>
                    <img :src="showImg(item.img)" style="width: 500px;"/>
                </van-swipe-item>
            </template>
<!--            <van-swipe-item >-->
<!--               -->
<!--            </van-swipe-item>-->
<!--            <van-swipe-item>-->
<!--                <img :src="showImg(obj.img)" style="width: 500px;"/>-->
<!--            </van-swipe-item>-->
<!--            <van-swipe-item>-->
<!--                <img :src="showImg(obj.img)" style="width: 500px;"/>-->
<!--            </van-swipe-item>-->
<!--            <van-swipe-item>-->
<!--                <img :src="showImg(obj.img)" style="width: 500px;"/>-->
<!--            </van-swipe-item>-->
        </van-swipe>
        <!-- 内容区域 -->
        <div class="xq" >
            <span v-if="obj != null">{{obj.product_name}}({{obj.description}})</span>
        </div>
        <div class="yh">
            <img src="../../../assets/img/utilImg/youhuiquan.png" style="border-radius: 15px"/>
        </div>
    </div>
    <div class="foot">
        <img src="../../../assets/img/utilImg/gouwuche.png" class="cart-image" @click="goto('/cart')"/>
        <div class="gwc" @click="addCart()">+ 加入购物车</div>
    </div>
</template>



<script setup>
import {createApp, onMounted, reactive, ref} from 'vue';
import router from "@/router";
import {localGet} from "@/utils";
import axios from "axios";

const state = reactive({
    tableData:[],
})

const showImg = (image) => {
    return 'src/assets/product-img/' + image;
}

const goto = (url) =>{
    router.push(url);
}
let obj = ref(null);
onMounted(()=>{
    let value = localStorage.getItem('param1');
    obj.value = JSON.parse(value);
    console.log(obj.value)
    axios.get("http://localhost:88/img_detail/getImageDetail?pid="+obj.value.product_id)
        .then(res=>{
            state.tableData = res.data;
        })
})

let subObj = ref(null);
const param = reactive({
    user_id: 0,
    product_id: 0,
    quantity: 0,
});
const addCart = () => {
    console.log(obj.value.product_id);
    let sub = localGet('token');
    const decoded = parseJwt(sub);
    const sub1 = decoded.sub;
    subObj.value = JSON.parse(sub1.replace(/'/g, '"')); // 替换单引号为双引号
    console.log(subObj.value);
    param.quantity = 1;
    param.user_id = subObj.value.user_id;
    param.product_id = obj.value.product_id;
    axios.post("http://localhost:88/carts/addCart", param).then(res => {

    });
    console.log(param)
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

<style scoped>
/* 在这里添加你的样式 */
.img1 {
    background-color: #fa5741;
    border-radius: 10px;
    color: white;
    align-content: center;
    font-size: 15px;
    width: 100px;
    margin-left: 200px;
}
.image-container {
    position: absolute; /* 设置为绝对定位，使其位于轮播图之上 */
    top: 0; /* 调整位置 */
    left: 0; /* 调整位置 */
    width: 50%; /* 宽度与轮播图相同 */
    z-index: 10; /* 确保图片容器在轮播图之上 */
    border-radius: 5px;

}

.top-image {
    width: 20px;
    height: 20px;
    /* 添加一些边距 */
    margin: 15px 10px 10px;

}
.top-image1 {
    width: 20px;
    height: 20px;
    /* 添加一些边距 */
    margin-left: 320px;
    margin-top: -28px;

}

.my-swipe {
    position: relative; /* 设置为相对定位，确保轮播图在其父元素中 */
    z-index: 1; /* 确保轮播图在图片之下 */
    margin-top: 0; /* 根据图片大小调整轮播图的顶部边距 */
}

.cart-image {
    width: 20px;
    height: 20px;
    margin-top: 30px;
}
.body{
    background-color: #f5f6f7;
    position: relative; /* 设置为相对定位，为绝对定位的子元素提供参考 */
}
.my-swipe .van-swipe-item {
    margin-top: 10px;
    color: #fff;
    font-size: 20px;
    line-height: 300px;
    text-align: center;
    background-color: #39a9ed;
}
.xq{
    display: flex;
    width: 90%;
    height: 200px;
    border-radius: 15px;
    background-color: #ffffff;
    padding: 10px;
    margin-left: 10px;
    margin-top: 20px;
}
.yh{
    display: flex; /* 使用flex布局 */
    flex-direction: column; /* 子元素垂直排列 */
    justify-content: center; /* 垂直居中子元素，如果需要的话 */
    width: 90%;
    height: 50px;
    border-radius: 15px;
    padding: 10px;
    margin-left: 10px;
    margin-top: 20px;
}
.foot {
    display: flex;
    //position: fixed;
}
.gwc{
    display: flex;
    width: 300px;
    height: 20px;
    border-radius: 15px;
    background-color: #01cf22;
    padding: 10px;
    margin-left: 10px;
    margin-top: 20px;
    color: white;
    align-items: center; /* 垂直居中子元素 */
    justify-content: center; /* 如果也需要水平居中 */
}
</style>
