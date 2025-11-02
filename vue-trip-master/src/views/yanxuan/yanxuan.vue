<script setup>

import {onMounted, reactive, ref} from "vue";
import axios from "axios";
import router from "@/router";

let categories = ref([
//获取所有的分类信息
])

let labels = ref([
    '全部','小象独家','超值精选','进口好物'
])
//一些标签

let cType = ref([
    //子类型
])

const filterOptions = ['销量', '折扣', '价格']
const showImg = (image) => {
    return 'src/assets/product-img/' + image;
}
const params = reactive({
    pt_id:null,
    ct_id:null,
    label_ids:[],//严选
    sort:"",
    order:0,
    rate:null,
})
const status = reactive({
    sstatus:false,//是否选中（销量，折扣）
    selectedFilter:null,// 当前选中的过滤选项(价格)
    sortDirection:null,// 当前排序方向，'asc' 或 'desc'
    selectedCategory:null,//分类
    selectedLabel:null//标签
})
let products = ref([
    // ...商品数据
 ])
const gotoSearch=()=>{
    router.push('/yanxuanSearch')
}

const getPtype = async ()=> {
    await axios.post("http://localhost:88/product_types/getParentType")
        .then(res=>{
            categories.value = res.data;

        })
}

const getCtype=(tid)=> {
    axios.post("http://localhost:88/product_types/getChildType?parent_id="+tid)
        .then(res=>{
            cType.value = res.data
        })
}

//标签数据
const getLabel=()=> {
    axios.post("")
        .then(res=>{
            labels.value = res.data;
            console.log(labels.value)
        })
}

onMounted(async () => {
    //默认选中第一个分类初始化
    //先查询所有父类型，给categories数组初始化
    await getPtype();//
    console.log(categories.value);//
    //选中数组的第一个类型作为默认选中查询商品
    selectCategory(categories.value[0]);

    console.log(status.selectedCategory);

    axios.post("http://localhost:88/products/findProducts", params)
        .then(res => {
            products.value = res.data;
            console.log(products.value);

        });
})

// const filteredProducts=()=>{
//     let filtered = products;
//     if (selectedCategory) {
//     filtered = filtered.filter(product => product.categoryId === selectedCategory.id);
// }
// if (currentFilter === '销量') {
//     // 根据销量排序
//     filtered.sort((a, b) => b.sale_num - a.sale_num);
// } else if (currentFilter === '折扣') {
//     // 根据折扣排序
//     filtered.sort((a, b) => b.discount - a.discount);
// } else if (currentFilter === '价格') {
//     // 根据价格排序
//     filtered.sort((a, b) => a.price - b.price);
// }
// return filtered;
// }

const selectCategory=(category)=> {
    if (status.selectedCategory === null){
        status.selectedCategory = category;
        params.pt_id = category.type_id;
    }else {
        status.selectedCategory = null;
        params.pt_id = null;
    }

    axios.post("http://localhost:88/products/findProducts",params)
        .then(res=>{
            products.value = res.data
            getCtype(category.type_id)
            // console.log(status.selectedCategory)
        })
}

const setFilter=(option)=> {
    if (status.selectedFilter === null){
        status.selectedFilter = option;
    }else {
        status.selectedFilter = null;
    }
    status.sstatus = !status.sstatus;
    //只能判断两个状态，不适用于价格
    if (status.sstatus){
        if (option === "销量"){
            params.sort = "sale_num";
        }else if (option === "折扣"){
            params.rate = 0;
        }
    }else {
        params.sort = "";
        params.rate = null;
    }

    if (option === '价格') {
        params.sort = "price"
        if (status.sortDirection === 'asc') {
            status.sortDirection = 'desc'; // 从正序切换到倒序
            params.order = 1;
        } else if (status.sortDirection === 'desc') {
            status.sortDirection = null; // 从倒序切换到默认状态
            params.order = 0;
        } else {
            status.sortDirection = 'asc'; // 默认为正序
            params.order = null;
        }
        status.selectedFilter = status.sortDirection ? option : null; // 更新选中状态
    }
    axios.post("http://localhost:88/products/findProducts",params)
        .then(res=>{
            products.value = res.data
            console.log(products.value)
        })
}

const selectLabel=(label)=> {
    if (status.selectedLabel === null){
        status.selectedLabel = label;
        params.label_ids.push(label.label_id);
    }else {
        status.selectedLabel = null;
        params.label_ids = [];
    }

    axios.post("http://localhost:88/products/findProducts",params)
        .then(res=>{
            products.value = res.data
            console.log(products.value)
        })
}

const addToCart=(product)=> {
        // 处理加入购物车的逻辑
}


</script>

<template>
    <div class="container">
        <!-- 导航栏 -->
        <header class="header">
            <div class="logo">严选 | 乐享品质生活</div>
            <input type="text" class="search-input"  @click="gotoSearch()" placeholder="搜索严选商品">
        </header>

<!--        &lt;!&ndash; 商品分类 &ndash;&gt;-->
<!--        <nav class="category-nav">-->
<!--            <ul class="category-list">-->
<!--                <li v-for="category in categories" :key="category.id" @click="selectCategory(category)">-->
<!--                    {{ category }}-->
<!--                </li>-->
<!--            </ul>-->
<!--        </nav>-->

        <!-- 可滑动的商品分类区域 -->
        <div class="category-slider">
            <div class="category"
                 :class="{ 'selected': category === status.selectedCategory }"
                 v-for="category in categories" :key="category"
                 @click="selectCategory(category)">
                {{ category.type_name }}
            </div>
        </div>

        <div class="pro">
            <!-- 商品筛选 -->
            <div class="filter-section">
                <a class="option"
                   :class="{ 'selected': option === status.selectedFilter }"
                   v-for="option in filterOptions" :key="option"
                   @click="setFilter(option)">
                    {{ option }}
                    <span v-if="option === '价格'" class="arrow up" :class="{ 'selected': status.sortDirection === 'asc' }" @click.stop="setFilter(option)">&#9650;</span>
                    <span v-if="option === '价格'" class="arrow down" :class="{ 'selected': status.sortDirection === 'desc' }" @click.stop="setFilter(option)">&#9660;</span>
                </a>
            </div>

            <div class="label-slider">
                <div class="label"
                     v-for="label in cType" :key="label"
                     :class="{ 'selected': label === status.selectedLabel }"
                     @click="selectLabel(label)">
                    {{ label.type_name }}
                </div>
            </div>

            <!-- 商品列表 -->
            <div class="product-list" >
                <template v-for="product in products" :key="product">
                    <div class="product-item">

                        <img :src="showImg(product.img)" :alt="product.product_name">
                        <p>{{ product.description }}</p>
                        <h3>{{ product.product_name }}</h3>
                        <span>￥{{ product.price }}</span>
                        <img src="../../../src/assets/img/type/加号.png" style="width: 46px;height: 40px;margin-left: 50px">
                        <!--                        <del v-if="product.originalPrice">￥{{ product.originalPrice }}</del>-->

                        <!--                    <button @click="addToCart(product)">加入购物车</button>-->
                    </div>
                </template>
            </div>


        </div>

    </div>
</template>

<style scoped>
.header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px;
    background-color: #D2EADD;
}
.logo {
    font-size: 17px;
    font-weight: bold;
    padding-left: 30px;
}
.search-input {
    padding: 8px;
    border-radius: 19px;
    height: 10px;
    width: 130px;
    border: 1px solid #ccc;
}

.category-slider {
    display: flex;
    overflow-x: auto;
    white-space: nowrap;
    padding: 10px;
    background-color: #F0FDF6;
}

.category {
    display: inline-block;
    margin-right: 10px;
    padding: 5px 10px;
    background-color: white;
    color: black;
    border-radius: 15px;
}
.label-slider {
    display: flex;
    overflow-x: auto;
    white-space: nowrap;
    padding: 10px;
    background-color:#FBFBFB;
}

.label {
    display: inline-block;
    margin-right: 10px;
    padding: 5px 10px;
    background-color: white;
    color: black;
    border-radius: 15px;
}
.pro {
    background-color: #FBFBFB;
    padding:8px;
    border: 1px solid pink;
}
.filter-section {
    border: 1px solid black;
    padding: 10px;
}
.label-slider {
    border: 1px solid green;
}
.option {
    margin-left: 10%;
    margin-right: 10%;
    position: relative;
    padding-right: 10px;
}

.arrow {
    position: absolute;
    left: 33px;
    cursor: pointer;
    color: #7F7F7F;
}
.up {
    top: -6px;
}
.down {
    bottom:-7px;
}
.category.selected {
    color: red; /* 选中时的颜色 */
}
.option.selected {
    color: red; /* 选中时的颜色 */
}
.arrow.selected {
    color: red; /* 选中时的颜色 */
}
.product-list {
    border: 1px solid blue;
}
.product-item{
    border: 1px solid red;
    float: left;
    margin: 12px;
    width: 42%;
    height: 200px;
    background-color: #ffffff;
    border-radius: 15px;
}
.product-item img {
    width: 100%;
    height: 120px;
}
</style>