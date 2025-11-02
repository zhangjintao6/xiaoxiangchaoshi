<script setup>
import {createRouter as $router} from "vue-router";
import { createApp,ref,reactive,onMounted,onUnmounted } from 'vue';
import { Col, Row } from 'vant';
import axios from "axios";
import router from "@/router";
import {localGet, localSet} from "@/utils";
const app = createApp();
app.use(Col);
app.use(Row);
let  timer=null;
const list1 = ref([]);
const huanjuItem = ref([]);
let n=ref(0);//初始化
const bannerlist=reactive([
    '1.png','2.png','3.png',
])
const gotoorder = (url) => {
    router.push(url);//动态跳转地址
}
onMounted(()=>{//挂载
    timer=setInterval(autoPlay,2000)
    handleTabChange(0);
})
onUnmounted(()=>{//销毁
    clearInterval(timer);
})
const autoPlay =() =>{
    n.value++;
    if (n.value>=bannerlist.length){
        n.value=0
    }
}

const onLoad = () => {
    // 异步更新数据
    // setTimeout 仅做示例，真实场景中一般为 ajax 请求
    setTimeout(() => {
        for (let i = 0; i < 10; i++) {
            list1.value.push(list1.value.length + 1);
        }

        // 加载状态结束
        // loading.value = false;

        // 数据全部加载完成
        if (list1.value.length >= 40) {
            finished.value = true;
        }
    }, 1000);
};
const handleTabChange = (index) => {
    if (index === 0) { // 判断是否切换到 "欢聚中秋" 标签
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
};
let subObj = ref(null);
onMounted(()=>{
    let sub = localGet('token');
    const decoded = parseJwt(sub);
    const sub1 = decoded.sub;
    subObj.value = JSON.parse(sub1.replace(/'/g, '"')); // 替换单引号为双引号
})
function parseJwt(token) {
    let base64Url = token.split('.')[1];
    let base64 = base64Url.replace('-', '+').replace('_', '/');
    let payload = decodeURIComponent(atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    return JSON.parse(payload);
}
</script>

<template>
    <div class="message">
        <van-nav-bar  title="" left-text="头像" >
            <template v-slot:left>
                <div>
                    <img src="../../assets/img/utilImg/用户头像.png" style="width: 50px;">
                </div>
                <p style="margin-left: 20px" v-if="subObj != null">
                    {{subObj.nickname}}
                </p>
            </template>

            <template v-slot:right>
                <div>
                    <img src="../../assets/img/type/客服.png">
                </div>
                <div>
                    <img src="../../assets/img/type/设置.png">
                </div>
            </template>
        </van-nav-bar>

        <div class="xr-hd" >
            <div>
                <li>2</li>
                <li>优惠卷</li>
            </div>
            <div>
                <li>0.00</li>
                <li>买菜币</li>
            </div>
            <div>
                <li>0.00</li>
                <li>我的钱包</li>
            </div>

        </div>

        <div class="like1" @click="gotoorder('/order')">
            <div >
                <img src="../../assets/img/type/待支付.png">
            </div>
            <div><img src="../../assets/img/type/进行中.png"></div>
            <div><img src="../../assets/img/type/待评价.png"></div>
            <div><img src="../../assets/img/type/退款.png"></div>
            <div><img src="../../assets/img/type/订单.png"></div>
        </div>

        <div class="like2" >
            <div class="like2_1">
                <div>
                <li>邀请好礼</li>
                <li>最高30元</li></div>
                <div>
                    <img src="../../assets/img/type/开.png">
                </div>
            </div>
            <div class="like2_2">
                <div>
                <li>省钱卡</li>
                <li>多省多用</li></div>
            <div>
                <img src="../../assets/img/type/省钱.png">
            </div>
            </div>
        </div>
        <div class="like3" >
            <div class="like3_1">
                <div><img src="../../assets/img/type/关注.png"></div>
                <div><img src="../../assets/img/type/卷.png"></div>
                <div><img src="../../assets/img/type/免费.png"></div>
                <div><img src="../../assets/img/type/礼品.png"></div>
                <div><img src="../../assets/img/type/菜谱.png"></div>
            </div>
            <div class="like3_2">
                <div><img src="../../assets/img/type/扫一扫.png"></div>
                <div><img src="../../assets/img/type/买菜.png"></div>
                <div><img src="../../assets/img/type/收货.png"></div>
                <div><img src="../../assets/img/type/发票.png"></div>
                <div><img src="../../assets/img/type/合作商招募.png"></div>
            </div>
        </div>
        <!-- 创建外部展示容器 -->
        <div class="banner">
            <!-- 轮播图图片容器-->
            <img v-for="(v,i) in bannerlist"   :src="'src/assets/img/type/'+v+''" v-show="n===i"/>
        </div>

        <div class="container-list">
            <van-divider
                :style="{ color: 'black', borderColor: '#7b7b7b', padding: '0 80px' }"
            >
                <svg t="1726281430614" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1306" width="20" height="20"><path d="M513.224386 238.541885l-0.122797 0c-61.96733 0-114.88761 60.600193-135.953392 145.867182l272.027533 0C628.166231 299.142078 575.314513 238.541885 513.224386 238.541885z" fill="#d81e06" p-id="1307"></path><path d="M513.284761 3.069919C232.88863 3.069919 5.660418 230.361575 5.660418 510.694261c0 280.331663 227.228211 507.624343 507.624343 507.624343 280.397155 0 507.624343-227.291656 507.624343-507.624343C1020.909104 230.361575 793.680892 3.069919 513.284761 3.069919zM196.01993 859.68363l51.123354-475.274564 69.77004 0c26.894534-129.263016 104.601336-222.705198 196.311061-222.705198 91.829452 0 169.537278 93.442182 196.430788 222.705198l46.658669 0 74.236771 475.274564L196.01993 859.68363z" fill="#d81e06" p-id="1308"></path></svg>
                <p style="margin-left: 10px;color: #333333">品牌甄选</p>
            </van-divider>
            <van-back-top />
        </div>



        <div title="欢聚中秋" class="hjzq">
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
        </div>




    </div>

</template>

<style scoped lang="less">
.message {
  height: 100vh;
  background-color:  RGB(245,246,250);
}
.xr-hd{
  width: 95%;
  height: 100px;
  border-radius: 15px;
  background-color: white;
  margin-top:10px;
  margin-left: 10px;
  margin-right: 10px;
  margin-right: 10px;
    display: flex;

}
.xr-hd div{
    width: 120px;
}
.xr-hd li{
    text-align: center;
    margin-top: 4.6vw;
}
.van-nav-bar{
    background-color: RGB(235,247,238);
}
.van-nav-bar__right{
    right: 5px;
}

.van-nav-bar__right div{
    margin-right: 8px;

}
.van-nav-bar__right img{
    width: 50px;
    height: 50px;
}
.like1{
  width: 90%;
  height: 80px;
  border-radius: 15px;
  background-color: white;
  margin-top:10px;
  margin-left: 10px;
  margin-right: 10px;
  display: flex;
  padding: 10px;
}

.like1 img{
    width: 70px;
    height: 70px;
}
.like2{
  width: 95%;
  height: 100px;
  border-radius: 15px;
  margin-top:10px;
  margin-left: 10px;
  margin-right: 10px;
  display: flex;
  justify-content: space-between; /* 添加这一行 */
}
.like2 li{
    margin-top: 10px;
    margin-left: 10px;
}
.like2 img{
    margin-left: 9vw;
    width: 16.33333vw;
    height:16.33333vw;
}
.like2_1 {
  width: 48%;
  height: 80px;
  border-radius: 15px;
  background-color: white;
    display: flex;
}
.like2_2 {
    width: 48%;
    height: 80px;
    border-radius: 15px;
    background-color: white;
    display: flex;
}
.like3{
  width: 95%;
  height: 200px;
  border-radius: 15px;
  background-color: white;
  margin-left: 10px;
  margin-right: 10px;
}
.like3 img{
    width: 18.33333vw;
    height:18.33333vw;

}
.like3_1{
  display: flex;

}
.like3_1 div{
  width: 80px;
  height: 80px;
  margin-top: 10px;
}
.like3_2{
  display: flex;
}
.like3_2 div{
  width: 80px;
  height: 80px;
  margin-top: 10px;
}
.banner img{
    width: 100%;
    height: 100px;
}
.hjzq{
    display: grid;
    grid-template-columns: repeat(2, 10fr);
    gap: 5px;
    padding: 20px;
    background-color: RGB(245,246,250)
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