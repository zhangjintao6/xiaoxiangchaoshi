<script setup>
import router from "@/router";
import {onMounted, reactive, ref} from "vue";
import axios from "axios";

const param = reactive({
    keyword: '',
    label_ids: [],
    brand_id: null,
    low: null,
    high: null,
    ct_id: null,
    rate: null,
    sort: '',
    order: null,
});
const baseUrl = 'src/assets/product-img/';
const value = ref('');
const go = (url) => {
    router.push(url);//动态跳转地址
}
let products = ref({});
onMounted(()=>{
    param.keyword = localStorage.getItem("product");
    console.log(param);
    search();
})
const search = () => {
    axios.post("http://localhost:88/products/searchProducts",param).then(res=>{
        products.value = res.data;
        value.value = '';
        console.log(products.value);
    })
}

const goShop = (product) => {
    console.log(product);
    localStorage.setItem('param1', JSON.stringify(product));
    router.push('/ShopDetail');
}

const gosearch = () => {
    param.keyword = value.value;
    search();
}

</script>

<template>
    <div class="top" style="background-color: #ecf5f2">
        <div  @click="go('/sort')">
            <svg t="1726223096064" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1187" width="20" height="20" style="margin-top: 10px;margin-left: 10px"><path d="M710.153924 8.980397L266.007127 460.692524a81.118646 81.118646 0 0 0 0.861532 114.476097l446.192936 441.050666a26.922883 26.922883 0 0 0 37.853573-38.284339L304.722232 536.884282a27.27288 27.27288 0 0 1-0.323074-38.445877L748.545955 46.726278A26.922883 26.922883 0 1 0 710.180847 9.00732z" fill="#222222" p-id="1188"></path></svg>
        </div>
        <van-search
            v-model="value"
            show-action
            style="background-color: #ffffff;height: 40px;border-radius: 15px"
            placeholder="请输入搜索关键词"
        >
            <template #action>
                <div style=" display: flex">
                    <div @click="onClickButton" style="color:green;">
                        <img src="../../../assets/img/utilImg/shufengexian.png" style="height: 20px; margin-top: 10px" alt="图片加载失败"/>
                        <span @click="gosearch()">搜索</span>
                    </div>
                </div>
            </template>
        </van-search>
        <!--        购物车图标-->
        <img src="../../../assets/img/utilImg/gouwuche.png" @click="go('/cart')" style="width: 20px;height: 20px;margin-top: 10px;margin-left: 20px" alt="图片加载失败"/>
    </div>
  <div class="container">
      <van-card
          style="margin-top: 10px;overflow: hidden"
          v-for="(i, index) in products"
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
  </div>
</template>

<style scoped lang="less">
.top{
  display: flex;
  position: fixed;
}
.container{
    padding-top: 50px;
    display: grid;
    grid-template-columns: repeat(2,10fr);
    gap: 4px;
}
</style>