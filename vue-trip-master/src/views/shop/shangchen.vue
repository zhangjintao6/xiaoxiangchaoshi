<template>
    <div class="message">
        <div class="header">
        <van-nav-bar  title="" left-text="头像" >\
            <template v-slot:left>
                <div @click="gotoShopDetail('/cart')">
                    <img src="../../assets/img/type/定位.png">
                </div>
                <div>
                    都大酒店
                </div>
            </template>

            <template v-slot:right>
                <div>
                    <img src="../../assets/img/type/消息1.png">

                </div>
            </template>
        </van-nav-bar>


            <van-sticky :offset-top="20">
                <van-search
                    v-model="value"
                    shape="round"
                    background="#fff"

                    placeholder="请输入搜索关键词"
                    @click="gotoSearch('/typeSearch')"
                />
        </van-sticky>
        </div>



        <!--新人专享模块 -->
        <div class="xr">
            <div class="xr-a">

                <li>新人特享价</li>
                    <li>|</li>
                    <li>数量有限先到先得</li>
                    <a class="view-all">查看全部></a>

            </div>
            <div class="xr-hd" >
                <!--轮播图-->
                <van-swipe :loop="false" :width="100" :show-indicators="false"  style="width: 95%;">
                    <van-swipe-item v-for="(item, index) in data3" :key="index"  >
                        <span class="img"><img style="width:100px ;" :src="'src/assets/img/type/'+item.img+'.png'" alt=""  >
                        <div class="xr-hd_1">
                            <text>￥1</text>
                             <img src="../../../src/assets/img/type/加号.png" style="width: 20%;height: 20%;margin-left: 15px;margin-top: -3px">
                        </div>

                        </span>
                    </van-swipe-item>
                </van-swipe>

            </div>
            <div class="yhj">
                <div class="yhj-a">
                       <li>小象新人8元优惠卷</li>
                       <li>待使用</li>
                    <a class="view-all" style="margin-top:10px;">
                        过期></a>
                </div>
                <div class="yhj-hd">
                    <div style="background-color:#ffffff  ;margin-right: 10px; /* 添加右边距 */;">
                        <img src="../../../src/assets/img/type/8元优惠卷.png">
                    </div>
                    <div style="background-color:#ffffff ;/* 添加右边距 */">
                        <img src="../../../src/assets/img/type/10元优惠卷.png">
                    </div>
                </div>

            </div>
            <div>

            </div>

        </div>

        <!-- 图标模块-->
        <div class="tubiao">
            <div>
                <!-- xxx参照下图的格式 -->

                <img src="../../../src/assets/img/type/美团自营超市.png">
            </div>
            <div>
                <img src="../../../src/assets/img/type/最快30分钟送达.png">
            </div>
            <div>
                <img src="../../../src/assets/img/type/营业.png">
            </div>
        </div>
        <!--图标区域-->
        <div class="icons-container" >
        <div v-for="(item ,index) in data1" :key="index" class="item">
            <div class="item1">
                <span class="img"><img :src="'src/assets/img/type/'+item.img+'.jpg'" alt=""></span>
<!--                <span class="typq">{{item.type}}</span>-->
            </div>
        </div>
        </div>

        <!--轮播图-->
        <van-swipe :loop="false" :width="100" :show-indicators="false"  style="margin-left: 10px;">
            <van-swipe-item v-for="(item, index) in data2" :key="index"  >
                <span class="img"><img style="width: 80px" :src="'src/assets/img/type/'+item.img+'.png'" alt="" />
                </span>
            </van-swipe-item>
        </van-swipe>
        <div class="body">
        <!--特价区-->
        <div class="zhc">
            <img src="../../../src/assets/img/type/主会场.png" style="width: 100%;height: 110%">

    </div>
        <div class="xs">
            <div class="xs-a">

                <li>限时秒杀</li>
                <li>15点场</li>
                <li>0：0：0</li>
                <a class="view-all-xs">低价爆品天天有></a>
            </div>
            <div class="container">
                <div class="column" >
                    <img src="../../../src/assets/img/type/牛肉.png" style="width: 100%;height: 80%">
                    <li>￥9.5</li>

                </div>
                <div class="column">
                    <img src="../../../src/assets/img/type/纯牛奶.png" style="width: 100%;height: 80%">
                    <li>￥38.9</li>
                </div>
                <div class="column">
                    <img src="../../../src/assets/img/type/虾.png" style="width: 100%;height: 80%">
                    <li>￥36.7</li>
                </div>
                <div class="column">
                    <img src="../../../src/assets/img/type/枣.png" style="width: 100%;height: 80%">
                    <li>￥13.86</li>
                </div>
            </div>

        </div>

        <!-- 热爱榜单,时令上新-->
        <div class="like">
            <div class="passionate">
                <img src="../../../src/assets/img/type/热爱榜单.png" style="width: 100%;height: 110%">
            </div>
            <div class="new">
                <img src="../../../src/assets/img/type/时令上新.png" style="width: 100%;height: 110%">
            </div>
        </div>

<div style="margin-top: 10px">
        <van-tabs v-model:active="active" @change="handleTabChange">
            <van-tab title="品牌甄选" class="hjzq">
                <img src="../../../src/assets/img/type/geren.png">

                <div v-for="item in huanjuItem">
                    <div  class="hjzq_1">
                        <div class="hjzq_1_img" >
                            <img :src="'src/assets/product-img/'+item.img" alt="" width="80" >
                        </div>
                        <div class="hjzq_1_2">
                            <li>{{item.description}}</li>
                            <li>{{item.product_name}}</li>
                        </div>
                        <div class="hjzq_1_3">
                            <table>￥{{item.price}}</table>
                            <img src="../../../src/assets/img/type/加号.png" style="width: 50px;height: 50px;margin-left: 50px">
                        </div>
                    </div>
                </div>

            </van-tab>

                <van-tab title="欢聚中秋" class="hjzq">
                    <div v-for="item in huanjuItem">
                        <div  class="hjzq_1">
                            <div class="hjzq_1_img" >
                                <img :src="'src/assets/product-img/'+item.img" alt="" width="80" >
                            </div>
                            <div class="hjzq_1_2">
                                <li>{{item.description}}</li>
                                <li>{{item.product_name}}</li>
                            </div>
                            <div class="hjzq_1_3">
                                <table>￥{{item.price}}</table>
                                <img src="../../../src/assets/img/type/加号.png" style="width: 50px;height: 50px;margin-left: 30px">
                            </div>
                        </div>

                    </div>
                </van-tab>

        </van-tabs>
        </div>

    </div>

    </div>
</template>
<script setup>
//iconfont
import {createApp, onMounted, ref} from "vue"
import {Col, Row, Sidebar, SidebarItem, Swipe, SwipeItem, Sticky,Tab,Tabs} from 'vant';
import axios from "axios";
import Bottom from "@/views/detail/cpns/Bottom/bottom.vue";
import {createRouter as $router} from "vue-router";
import router from "@/router";
const app = createApp();
app.use(Swipe);
app.use(SwipeItem);
app.use(Col);
app.use(Row);
app.use(Sidebar);
app.use(SidebarItem);
app.use(Sticky);
app.use(Tab);
app.use(Tabs);


const  data1=ref([
    {type:'蔬菜豆制品',img:"蔬菜豆制品"}, {type:'肉禽蛋',img:"肉禽蛋"},{type:'海鲜水产',img:"海鲜水产"}, {type:'水果鲜花',img:"水果鲜花"},{type:'乳品烘培',img:"乳品烘培"},
    {type:'酒水饮料',img:"酒水饮料"}, {type:'冻品面点',img:"冻品面点"}, {type:'粮油调味',img:"粮油调味"},{type:'休闲零食',img:"休闲零食"},{type:'熟食快手菜',img:"熟食快手菜"},

])

const data2 = ref([
    {type: 'yanxuan', img: "yanxuan"}, {type: 'huanju', img: "huanju"}, {type: 'riyong', img: "riyong"}, {
        type: 'geren',
        img: "geren"
    }, {type: 'xiaoxiang', img: "xiaoxiang"},
    {type: 'huoguo', img: "huoguo"}, {type: 'luying', img: "luying"}, {type: 'yanxuan', img: "tehui"}, {
        type: 'tehui',
        img: "tehui"
    },    {type: 'huoguo', img: "huoguo"}, {type: 'luying', img: "luying"}, {type: 'yanxuan', img: "tehui"}, {
        type: 'tehui',
        img: "tehui"
    },


]);
const  data3=ref([
    {type:'蔬菜豆制品',img:"炸鸡"}, {type:'肉禽蛋',img:"榴莲"},{type:'海鲜水产',img:"青鸟"}, {type:'水果鲜花',img:"青椒"},{type:'乳品烘培',img:"番茄"},
    {type:'酒水饮料',img:"生菜"}, {type:'冻品面点',img:"冬瓜"}, {type:'粮油调味',img:"娃娃菜"},{type:'休闲零食',img:"土豆"},{type:'熟食快手菜',img:"千张"},
    {type:'熟食快手菜',img:"查看全部"},

])

    // 定义响应式状态
    const huanjuItem = ref([]);
    const active = ref(0);
    // 定义函数
const gotoSearch = (url) => {
    router.push(url);//动态跳转地址
}


//定义一个函数来处理切换事件
const handleTabChange = (index) => {
    if (index === 1) { // 判断是否切换到 "欢聚中秋" 标签
        axios.get('http://localhost:88/products/listByLabelId?LabelId=3')
            .then(res => {
                // 处理后端数据
                huanjuItem.value = res.data;
                console.log(huanjuItem);
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    }
    else if (index === 0) { // 判断是否切换到 "欢聚中秋" 标签
        axios.get('http://localhost:88/products/listByLabelId?LabelId=1')
            .then(res => {
                // 处理后端数据
                huanjuItem.value = res.data;
                console.log(huanjuItem);
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    }
};

const gotoShopDetail = (url) => {
    router.push(url);//动态跳转地址
}
onMounted(()=>{
    handleTabChange(0);
});
</script>

<style lang="less" scoped>
.message {

  height: 100vh;
}
.body{
    width:100%;
}
.header{
 background-color:  RGB(245,246,250);
}
:deep(.van-nav-bar) {
  .van-icon {
    font-size: 18px;
  }
    .van-nav-bar__content{
        background-color: RGB(247,248,250);
    }
    .van-nav-bar__right{
        right: -7vw;
    }

    .van-nav-bar__right div{
        margin-right: 8px;

    }
    .van-nav-bar__right img{
        width: 50px;
        height: 50px;
        background-color: RGB(247,248,250);
    }
    .van-nav-bar__left img{
        width: 30px;
        height: 30px;
        background-color: RGB(247,248,250);
        margin-top: -8px;
    }
}
.system {
  margin-top: 10px;
  height: 60px;
  padding: 0 20px;
  background-color: #fff;
  display: flex;
  align-items: center;
  font-size: 16px;
  border-top: 1px solid #e9e9e9;
  border-bottom: 1px solid #e9e9e9;
  .svg-icon {
    font-size: 40px;
    margin-right: 10px;
  }
}
.empty {
  display: flex;
  flex-direction: column;
  align-items: center;
  color: #999;
  img {
    width: 100%;
  }
}
.body{
    background-color: #f5f6f7;
}
.xr{
    width: 90%;
    height: 250px;
    border-radius: 15px;
    background-color: red;
    padding: 10px;
    color: white;
    margin-left: 10px;
}
.xr-a{
    display: flex;
    justify-content: space-between;
}
.xr-a li{
    margin-right: 5px;
    margin-bottom: 10px; /* 添加底部外边距 */
}
.xr-hd{
    width: 100%;
    height: 29vw;
    border-radius: 15px;
    background-color: #ffffff;
}
van-swipe-item{
    width: 90%;
}
.xr-hd span img{
     width: 80px;
     height: 80px;
    margin-left: 40px;
 }
.xr-hd_1{
    text-align: center;
}
.xr-hd_1 text{
   color: RGB(255,88,25);
    margin-left: 30px;
}
.yhj-a{
    display: flex;
    justify-content: space-between;

}
.yhj-a li {
    margin-right: 5px;
    margin-bottom: 10px; /* 添加底部外边距 */
    margin-top: 10px;
}
.yhj-hd{
    display: flex;
    margin-right: 20px; /* 添加右边距 */
}
.yhj-hd img{
width: 160px;
    height: 80px;
}
.view-all {
    margin-left: auto;
    color: white;
    margin-right: 10px;


}

.tubiao{
    display: flex;
    margin-top: 10px;
    margin-left: 10px;

}
.tubiao div{
    margin-right: 25px
}
.tubiao img{
    width: 100px;
    height: 15px;
}
.icons-container {
    display: grid;
    grid-template-columns: repeat(5, 0fr);
    gap: 1px;
    padding: 1px;
    width: 100%;

}
.icons-container img{
    width: 75px;
}

.van-swipe {
    width: 100%; /* 轮播图宽度设置为100% */

    /* ...其他样式... */
}

.van-swipe-item {
    display: flex;
    justify-content: center; /* 图片在轮播项中水平居中 */
    align-items: center; /* 图片在轮播项中垂直居中 */
}

.img {
    display: inline-block; /* 确保图片元素是内联块级元素 */
}
.zhc{
    width: 90%;
    height: 110px;
    border-radius: 15px;
    padding: 10px;
    margin-left: 10px;
    margin-top: 20px;
}

.xs{
    width: 90%;
    height: 100px;
    border-radius: 15px;
    background-color: #ffffff;
    padding: 10px;
    margin-left: 10px;
    margin-top: 20px;
}
.xs-a{
    display: flex;
    justify-content: space-between;
}
.xs-a li{
    margin-right: 5px;
    margin-bottom: 10px; /* 添加底部外边距 */
}
.container {
    display: flex;
    justify-content: space-between;
}
.column {
    flex: 1; /* 每个子元素占据相同的空间 */
    margin: 0 10px; /* 每个子元素之间的间距 */
}
.column li{
    color: red;
}
.view-all-xs {
    margin-left: auto;
    color: #999999;
    margin-right: 10px;


}

.like{
    width: 90%;
    height: 100px;
    border-radius: 15px;
    background-color: #ffffff;
    padding: 10px;
    margin-left: 10px;
    margin-top: 10px;
    display: flex;
}
.like div{
    width: 180px;
    height: 100px;
    background-color:#ffffff ;
    margin-right: 10px;
    border-radius: 15px;
}
.passionate li{
    margin-top: 10px;
    margin-left: 5px;
}
.passionate{
    display: flex;
}
.new{
    display: flex;
}
.new li{
    margin-top: 10px;
    margin-left: 5px;
}
.hjzq{
    display: grid;
    grid-template-columns: repeat(2, 10fr);
    gap: 5px;
    padding: 20px;

}
.hjzq_1{
    width: 150px;
    height: 200px;
    background-color: #ffffff;
    border-radius: 15px;
    padding: 6px;

}
.hjzq_1 table{
    margin-top: 20px;
    color: red;
    font-size: 16px;
}
.hjzq_1_img img{
    width: 100%;
    height: 120px;
}
.hjzq_1_3{
    display: flex;
}

</style>


