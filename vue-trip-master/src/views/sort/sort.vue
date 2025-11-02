<script setup>
import {createApp, onMounted, reactive, ref} from 'vue';
import {Card, Col, Dialog, DropdownItem, DropdownMenu, Row, Sidebar, SidebarItem, Swipe, SwipeItem} from 'vant';
import axios from "axios";
import router from "@/router";

const app = createApp();
app.use(Swipe);
app.use(SwipeItem);
app.use(Col);
app.use(Row);
app.use(Sidebar);
app.use(SidebarItem);
app.use(Dialog);
app.use(DropdownMenu);
app.use(DropdownItem);
app.use(Card);
const active = ref(0);
const activeKey = ref(0);
const show = ref(true);
let menuItems = ref([]);
let typeItems = ref([]);

const images = [
    'src/assets/img/type-img/huanju.png',
    'src/assets/img/type-img/huiyuan.png',
    'src/assets/img/type-img/tehui.png',
    'src/assets/img/type-img/xiaoxiang.png',
    'src/assets/img/type-img/yanxuan.png',
];

const baseUrl = 'src/assets/product-img/'

const gotoSearch = (url) => {
    router.push(url);//动态跳转地址
}

const change = (type_id) => {
    axios.get("http://localhost:88/product_types/getChildType?parent_id="+type_id).then(res=>{
        menuItems.value = res.data;
        console.log("aaa:", menuItems.value);
    })
}

const productItem = ref([]);
const showProduct = (id) => {
    axios.get("http://localhost:88/product_types/getProductBytypeId?id=" + id).then(res=>{
        productItem.value = res.data;
        console.log(productItem.value);
        console.log(res.data)
    })
}

onMounted(()=>{
    axios.get("http://localhost:88/product_types/getParentType").then(res=>{
        typeItems.value = res.data;
        console.log(typeItems.value);
    })
    change(1);
})

const goShop = (product) => {
    console.log(product);
    localStorage.setItem('param1', JSON.stringify(product));
    router.push('/ShopDetail');
}

const check = (path) =>{
    return 'src/assets/img/type-img/' + path;
}
</script>

<template>
    <div class="custom-container">
        <van-row style="position: relative">
            <van-col span="24" class="header">
                <van-search
                    v-model="value"
                    shape="round"
                    background="#4fc08d"
                    placeholder="请输入搜索关键词"
                    @click="gotoSearch('/typeSearch')"
                />
                <!--轮播图-->
                <van-swipe  :loop="false" width="100" :show-indicators="false">
                    <van-swipe-item v-for="image in images" :key="image">
                        <img :src="image" alt="Type Image" style="width: 60px;" @click="change()" class="colored-border"/>
                    </van-swipe-item>
                </van-swipe>
                <van-swipe  :loop="false" width="100" :show-indicators="false">
                    <van-swipe-item v-for="item in typeItems" :key="item" style="background-color: #ffffff">
                        <img :src="check(item.img)" alt="Type Image" style="width: 60px;" @click="change(item.type_id)" class="colored-border"/>
                    </van-swipe-item>
                </van-swipe>
<!--                展开 未实现-->
            </van-col>
        </van-row>
        <van-row>
            <div class="container">
                <van-sidebar v-model="activeKey" class="sidebar" style="position: fixed">
                    <van-sidebar-item
                        style="font-size: 13px"
                        v-for="(item, index) in menuItems"
                        :key="index"
                        :title="item.type_name"
                        @click="showProduct(item.type_id)"
                    />
                </van-sidebar>
                <div class="content"  style=" overflow:scroll !important;">
                    <div style="position:relative;">
                        <div v-for="(item, index) in productItem" :key="index" v-show="activeKey === index">
                            <!-- 显示对应的内容 -->
                            <van-card
                                style="margin-top: 10px;overflow: hidden"
                                v-for="(i, index) in item.products"
                                :key="index"
                                :num="i.stock"
                                :price="i.price"
                                :desc="i.description"
                                :title="i.product_name"
                                :thumb="baseUrl+i.img"
                                @click="goShop(i)"
                            >
                                <template #tags>
                                    <van-tag plain type="primary">标签</van-tag>
                                    <van-tag plain type="primary">标签</van-tag>
                                </template>
                                <template #footer>
                                    <van-button size="mini" style="background-color: #fa5741;color: white;border-radius: 15px;width: 50px;">立即抢</van-button>
                                </template>
                            </van-card>

<!--                            </div>-->
                        </div>
                    </div>
                </div>
            </div>
        </van-row>
    </div>




</template>

<style scoped lang="less">
.dropdown-menu{
    position: absolute;
    top: 30px;
    right: 0;
    background-color: white;
}
.colored-border:active {
    border: 2px solid #17d2bc;
}

.container {
    display: flex;
    padding-top: 200px;
}
.sidebar {
    flex: none;
    width: 80px; /* 根据需要调整宽度 */
}
.content {
    flex: 1;
    padding: 10px;
    margin-left: 80px;
    overflow: hidden;
    text-overflow: ellipsis;
    width: 250px;
    height: 500px;
}

.custom-container {
    padding: 0;
    width: 100%;
    height: 100%;
}
.header {
    position: fixed;
    text-align: center;
    padding: 0;
    background-color: #f8f8f8;
}
.footer {
    text-align: center;
    padding: 0;
    background-color: #f8f8f8;
}
.main {
    margin-top: 10px;
    margin-bottom: 10px;
    padding: 1px;
    background-color: #17d2bc;
}
.type{
    position: relative;
}

</style>