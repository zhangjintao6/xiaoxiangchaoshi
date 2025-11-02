<script setup>
import {createApp, ref} from 'vue';
import {Search, showToast} from 'vant';
import router from "@/router";

const app = createApp();
app.use(Search);
const value = ref('');
const onClickButton = () => showToast(value.value);
const item = ref([{history:'蔬菜'},{history:'纸巾'},{history:'牛奶月饼礼盒'}]);
const findItem = ref([{title: '海鲜真五折'}, {title: '买1赠1起'}, {title: '牛奶月饼礼盒'}]);
const footItem = ref([{title:'1'}]);
const go = (url) => {
    router.push(url);//动态跳转地址
}
const searchProduct = () => {
    localStorage.setItem("product", value.value);
    router.push("/search-product")
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
                        <img src="../../../assets/img/utilImg/shufengexian.png" style="height: 20px; margin-top: 5px" alt="图片加载失败"/>
                        <span @click="searchProduct()">搜索</span>
                    </div>
                </div>
            </template>
        </van-search>
<!--        购物车图标-->
        <img src="../../../assets/img/utilImg/gouwuche.png" @click="go('/cart')" style="width: 20px;height: 20px;margin-top: 10px;margin-left: 20px" alt="图片加载失败"/>
    </div>
    <div class="container" style="background-color: #ecf5f2;height: 800px">
        <div class="history">
            <!--          删除图标和历史搜索-->
            <div style="display: flex;justify-content: space-between;padding: 20px 10px 10px;">
                <span>搜索历史</span>
                <img src="../../../assets/img/utilImg/垃圾箱.png" style="width: 15px;height: 15px;" alt="图片加载失败"/>
            </div>
            <!--          搜索历史记录-->
            <div style="display: flex;">
                <div v-for="i in item" style="margin-left: 30px">
                    <div style="border-radius:10px;height:20px;text-align: center;background-color: #ffffff">
                        <span style="margin: 10px;line-height: 150%;font-size: 12px">{{i.history}}</span>
                    </div>
                </div>
            </div>
        </div>
        <!--        搜索发现-->
        <div class="find" style="padding-top: 30px">
            <span style="margin-left: 10px">搜索发现</span>
            <div style="display: flex;margin-top: 10px">
                <div v-for="i in findItem" style="margin-left: 30px">
                    <div style="border-radius:10px;height:20px;text-align: center;background-color: #ffffff">
                        <img src="../../../assets/img/utilImg/火.png" style="width: 15px;" alt="图片加载失败"/>
                        <span style="margin: 10px;line-height: 150%;font-size: 12px">{{i.title}}</span>
                    </div>
                </div>
            </div>
        </div>
        <!--        轮播图-->
        <div class="container1">
            <div class="slider-item" id="item1">
                <img style="height: 25px;margin-top: 10px;" src="../../../assets/img/utilImg/特惠尝鲜.png" alt="图片加载失败"/>
                <div style="border-radius: 15px;background-color: white;margin: 10px 10px 10px 15px;width: 250px;height: 500px">
                    <img src="../../../assets/img/type/小象特惠.jpg" style="width: 250px"/>
                </div>
            </div>
            <div class="slider-item" id="item2">
                <img style="height: 25px;margin-top: 10px;" src="../../../assets/img/utilImg/小象热卖榜.png" alt="图片加载失败"/>
                <div style="border-radius: 15px;background-color: white;margin: 10px 10px 10px 15px;width: 250px;height: 500px">
                    <img src="../../../assets/img/type/小象热卖.jpg" style="width: 250px"/>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped lang="less">
.top{
  display: flex;
  //position: fixed;
}
/*轮播图的样式*/
.van-style {
  margin-top: 40px;
}
.container1 {
  overflow-x: auto;
  white-space: nowrap;
  height: 500px;
  margin-top: 60px;
  width: 100%;
  scrollbar-width: none; /* 对于 Firefox 隐藏滚动条 */
}

.slider-item {
  display: inline-block;
  width: 250px;
  height: 100px;
  margin-right: 10px;
  border-radius: 15px;
  background-color: #f0f0f0;
}

.container1::-webkit-scrollbar {
  height: 8px;
}

.container1::-webkit-scrollbar-thumb {
  background-color: darkgrey;
  border-radius: 10px;
}
#item1{
  background-color: #fee7ed;
  height: 500px;
}
#item2{
  background-color: #ffece5;
  height: 500px;
}
</style>