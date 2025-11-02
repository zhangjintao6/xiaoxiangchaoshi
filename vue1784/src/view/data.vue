<script setup>

import * as echarts from 'echarts';

const myCanvas = ref(null);

const orders = [
    // 假设这是你的订单数据，你需要根据实际情况来定义
    // ...
];

const initChart = () => {
    const chartDom = myCanvas.value;
    const myChart = echarts.init(chartDom, 'dark');
    const option = {
        legend: {},
        tooltip: {
            trigger: 'axis',
            showContent: false
        },
        dataset: {
            source: [
                ['product', '2012', '2013', '2014', '2015', '2016', '2017'],
                ['Milk Tea', 56.5, 82.1, 88.7, 70.1, 53.4, 85.1],
                ['Matcha Latte', 51.1, 51.4, 55.1, 53.3, 73.8, 68.7],
                ['Cheese Cocoa', 40.1, 62.2, 69.5, 36.4, 45.2, 32.5],
                ['Walnut Brownie', 25.2, 37.1, 41.2, 18, 33.9, 49.1]
            ]
        },
        xAxis: { type: 'category' },
        yAxis: { gridIndex: 0 },
        grid: { top: '55%' },
        series: [
            {
                type: 'line',
                smooth: true,
                seriesLayoutBy: 'row',
                emphasis: { focus: 'series' }
            },
            {
                type: 'line',
                smooth: true,
                seriesLayoutBy: 'row',
                emphasis: { focus: 'series' }
            },
            {
                type: 'line',
                smooth: true,
                seriesLayoutBy: 'row',
                emphasis: { focus: 'series' }
            },
            {
                type: 'line',
                smooth: true,
                seriesLayoutBy: 'row',
                emphasis: { focus: 'series' }
            },
            {
                type: 'pie',
                id: 'pie',
                radius: '30%',
                center: ['50%', '25%'],
                emphasis: {
                    focus: 'self'
                },
                label: {
                    formatter: '{b}: {@2012} ({d}%)'
                },
                encode: {
                    itemName: 'product',
                    value: '2012',
                    tooltip: '2012'
                }
            }
        ]
    };

    myChart.on('updateAxisPointer', function (event) {
        const xAxisInfo = event.axesInfo[0];
        if (xAxisInfo) {
            const dimension = xAxisInfo.value + 1;
            myChart.setOption({
                series: {
                    id: 'pie',
                    label: {
                        formatter: '{b}: {@[' + dimension + ']} ({d}%)'
                    },
                    encode: {
                        value: dimension,
                        tooltip: dimension
                    }
                }
            });
        }
    });

    myChart.setOption(option);
};

onMounted(() => {
    // 确保 myCanvas 已经被挂载
    if (myCanvas.value) {
        // 显示 canvas 元素，使用 !important 提升优先级
        myCanvas.value.style.display = 'block !important';

        // 设置 canvas 的内部尺寸（绘图区域）
        myCanvas.value.width = 318;
        myCanvas.value.height = 340;

        // 设置 canvas 的样式尺寸（显示尺寸）
        // 注意：这里对 width 和 height 都使用了 !important
        myCanvas.value.style.width = '400px !important';
        myCanvas.value.style.height = '340px !important';

        // 初始化图表
        initChart();
    }
});
import { ref, onMounted } from 'vue';
import axios from "axios";
import {localGet} from "@/utils/index.js";
const users = ref([]);
const fetchOrders = async () => {
    let rid=localGet('token')
    const decoded = parseJwt(rid);
    // 获取sub字段的值
    const sub = decoded.sub;
    const subObj = JSON.parse(sub.replace(/'/g, '"')); // 替换单引号为双引号
    try {
        const response = await axios.get('http://localhost:88/users/selectUsers?user_id='+subObj.user_id);
        users.value = response.data;
        console.log("hdhwdh",users.value)
    } catch (error) {
        console.error('There was an error!', error);
    }
};

// 在组件挂载时获取数据
fetchOrders();
function parseJwt(token) {


    // 使用.split()方法将JWT分割成三个部分
    let base64Url = token.split('.')[1];

    // 将URL安全的Base64字符替换为标准Base64字符
    let base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');

    // 计算并处理padding
    const padding = base64.length % 4;
    if (padding) {
        base64 += '='.repeat(4 - padding);
    }

    // 使用atob解码Base64字符串，并处理unicode字符
    let payload = decodeURIComponent(atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(payload);
}

</script>

<template>
    <div class="common-layout">
        <el-container>
            <el-header style="height: 28px; background: rgba(5, 5, 5, .06);">
                <div style="display: flex; justify-content: center; align-items: center; height: 100%;">
                    <!-- 使用 <router-link> 组件来创建导航链接 -->
                    <router-link to="/data" tag="el-button" type="success" round style="width: 50px; height: 22px; margin: 0 10px;">
                        <svg t="1726213889481" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1292" width="40" height="26"><path d="M788.48 143.36h-20.48c-16.9984 0-30.72 13.7216-30.72 30.72s13.7216 30.72 30.72 30.72h20.48a51.2 51.2 0 0 1 51.2 51.2V286.72h-122.88V133.12c0-16.9984-13.7216-30.72-30.72-30.72S655.36 116.1216 655.36 133.12V286.72H368.64V133.12c0-16.9984-13.7216-30.72-30.72-30.72S307.2 116.1216 307.2 133.12V286.72H184.32v-30.72a51.2 51.2 0 0 1 51.2-51.2h20.48c16.9984 0 30.72-13.7216 30.72-30.72S272.9984 143.36 256 143.36h-20.48A112.8448 112.8448 0 0 0 122.88 256v471.04c0 107.3152 87.2448 194.56 194.56 194.56h389.12c107.3152 0 194.56-87.2448 194.56-194.56v-471.04C901.12 193.9456 850.5344 143.36 788.48 143.36z m-81.92 716.8h-389.12C244.1216 860.16 184.32 800.3584 184.32 727.04V348.16h655.36v378.88c0 73.3184-59.8016 133.12-133.12 133.12z" fill="#1afa29" p-id="1293"></path><path d="M419.84 204.8h184.32c16.9984 0 30.72-13.7216 30.72-30.72S621.1584 143.36 604.16 143.36h-184.32c-16.9984 0-30.72 13.7216-30.72 30.72s13.7216 30.72 30.72 30.72zM609.28 604.3648H399.7696a15.99488 15.99488 0 0 0 0 31.9488h183.0912c7.5776 0 11.6736 8.3968 7.3728 14.5408-20.6848 29.4912-51.2 53.6576-92.16 72.704-10.24 4.7104-13.312 18.2272-6.3488 27.2384a18.432 18.432 0 0 0 21.9136 5.5296c58.368-27.4432 98.9184-65.3312 121.0368-113.4592 3.4816-7.5776 2.8672-16.5888-1.024-23.9616-5.12-9.0112-14.336-14.5408-24.3712-14.5408zM533.504 585.728c7.7824 5.5296 18.6368 3.4816 23.7568-4.5056 4.7104-7.5776 2.8672-17.6128-4.5056-22.528-18.2272-12.288-37.888-23.3472-58.5728-32.9728a15.5648 15.5648 0 0 0-19.2512 5.5296l-0.8192 1.2288c-5.5296 7.9872-2.2528 18.6368 6.5536 22.9376 18.0224 7.9872 35.4304 18.2272 52.8384 30.3104z" fill="#1afa29" p-id="1294"></path><path d="M663.1424 538.4192c-48.5376-25.8048-92.16-58.7776-130.6624-99.328-5.3248-5.7344-12.6976-9.0112-20.48-9.0112s-15.1552 3.2768-20.48 9.0112c-36.2496 38.5024-79.6672 71.68-130.6624 99.1232-9.0112 4.9152-12.4928 15.9744-7.5776 24.9856 4.9152 8.8064 16.1792 12.0832 24.9856 7.168 48.9472-27.8528 89.9072-59.8016 122.6752-95.6416 5.9392-6.5536 15.9744-6.5536 22.1184 0 35.0208 38.0928 75.9808 69.8368 122.88 95.6416 8.6016 4.7104 19.6608 1.4336 24.576-7.168 5.12-8.8064 1.6384-20.0704-7.3728-24.7808z" fill="#1afa29" p-id="1295"></path></svg>
                    </router-link>
                    <router-link to="/month" tag="el-button" type="success" round style="width: 50px; height: 22px; margin: 0 10px;">
                        <svg t="1726213936688" class="icon" viewBox="0 0 1249 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1436" width="30" height="26"><path d="M947.867622 861.974684h134.617661v55.518072h-134.617661zM947.867622 657.237151h200.442886v55.83041h-200.442886zM947.867622 757.809974h194.664634v55.83041h-194.664634z" fill="#13CE66" p-id="1437"></path><path d="M1201.486045 924.442275a39.588836 39.588836 0 0 1-39.042244 39.042245h-230.349246a39.510752 39.510752 0 0 1-39.042244-39.042245v-273.295714a39.042245 39.042245 0 0 1 39.042244-39.042245h230.349246a39.042245 39.042245 0 0 1 39.042244 39.042245v273.295714z m-11.009913-361.21885h-287.585176a59.188043 59.188043 0 0 0-58.875705 58.875706v331.780997a59.031874 59.031874 0 0 0 58.875705 58.875705h287.585176a59.031874 59.031874 0 0 0 58.875706-58.875705V622.099131a59.188043 59.188043 0 0 0-58.875706-58.875706z" fill="#13CE66" p-id="1438"></path><path d="M956.456916 512.078084v2.498704l67.465-1.249352v-1.249352a511.921916 511.921916 0 1 0-228.084795 425.950893l-47.787708-49.427482a444.613085 444.613085 0 1 1 208.407503-376.523411z" fill="#13CE66" p-id="1439"></path><path d="M386.362056 544.561232c2.26445-31.233796 3.435718-64.029282 3.591886-97.293274h276.34101v97.293274zM390.422449 289.615373h275.872503v96.278176H390.422449V289.615373z m237.845356 445.081592c-20.380052 0-48.568553-1.249352-83.940826-3.591886l-11.47842-0.780845 2.342534 11.322251c3.82614 18.896447 6.715266 35.840781 8.667379 49.661735l1.015098 7.808449h7.418027c36.075034 0.780845 65.981394 1.249352 88.782065 1.249352 59.500381 0 90.890346-33.7325 90.890346-97.527528V227.147781H324.753393v216.059783a907.810279 907.810279 0 0 1-13.196278 175.29968 317.647705 317.647705 0 0 1-73.633674 145.705658l-6.324844 6.94952 7.261857 5.856337a511.29724 511.29724 0 0 1 39.510752 35.294189l6.871435 6.94952 6.090591-7.808449a411.8176 411.8176 0 0 0 88.625896-205.752631h286.335824v90.656093c0 27.173402-11.47842 38.651822-38.417569 38.651822z" fill="#13CE66" p-id="1440"></path></svg>
                    </router-link>
                    <router-link to="/last-seven-days" tag="el-button" type="success" round style="width: 50px; height: 22px; margin: 0 10px;">
                        <svg t="1726213968971" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1581" width="40" height="26"><path d="M826.56 912H198.08A70.048 70.048 0 0 1 128 841.92V385.6h768v456.32a69.728 69.728 0 0 1-69.44 70.08z m-232.32-426.56h-164.48a26.56 26.56 0 1 0 0 53.12h131.84L512 778.56a27.52 27.52 0 0 0 55.04 0L622.08 512a27.104 27.104 0 0 0-27.52-26.56h-0.32zM128 252.48a70.4 70.4 0 0 1 69.76-70.4h34.88V147.2a34.88 34.88 0 1 1 69.76 0v34.88h419.2V147.2a34.624 34.624 0 0 1 34.88-35.2 34.624 34.624 0 0 1 34.88 35.2v34.88h35.2A69.792 69.792 0 0 1 896 252.48v70.08H128V252.48z" fill="#3FDD82" p-id="1582"></path></svg>
                    </router-link>
                    <router-link to="/last-six-months" tag="el-button" type="success" round style="width: 50px; height: 22px; margin: 0 10px;">
                        <svg t="1726213986707" class="icon" viewBox="0 0 1901 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="1723" width="40" height="26"><path d="M146.285714 0h1609.142857a146.285714 146.285714 0 0 1 146.285715 146.285714v731.428572a146.285714 146.285714 0 0 1-146.285715 146.285714H146.285714a146.285714 146.285714 0 0 1-146.285714-146.285714V146.285714a146.285714 146.285714 0 0 1 146.285714-146.285714z m204.068572 252.196571c-53.321143 0-95.451429 24.356571-126.390857 73.728-29.622857 46.738286-44.105143 107.958857-44.105143 183.003429 0 71.753143 13.165714 127.707429 40.813714 167.862857 28.964571 42.130286 71.753143 63.853714 129.024 63.853714 46.08 0 83.602286-15.798857 113.883429-46.08 29.622857-30.281143 44.763429-69.12 44.763428-115.2s-13.824-82.944-40.813714-111.908571c-27.648-28.964571-63.853714-43.446857-108.617143-43.446857-27.648 0-52.004571 6.582857-73.069714 20.406857-22.381714 13.165714-38.838857 32.256-50.688 56.612571h-2.633143c-0.658286-8.557714-0.658286-14.482286-0.658286-17.115428 0-53.321143 9.874286-97.426286 30.939429-131.657143 21.065143-36.864 50.029714-54.637714 86.893714-54.637714 54.637714 0 86.893714 26.331429 97.426286 80.310857h53.321143c-13.165714-84.260571-63.195429-125.732571-150.089143-125.732572z m-1.974857 216.576c32.256 0 57.929143 9.874286 77.677714 30.939429 19.090286 19.748571 28.964571 46.08 28.964571 79.652571 0 33.572571-9.874286 61.220571-29.622857 83.602286-19.748571 21.065143-45.421714 32.256-77.019428 32.256-32.914286 0-58.587429-11.190857-78.336-33.572571-19.748571-21.065143-28.964571-48.054857-28.964572-80.969143 0-32.914286 9.874286-59.245714 30.281143-80.310857 20.406857-21.065143 46.08-31.597714 77.019429-31.597715z m497.664-80.310857v408.137143h48.713142v-408.137143h-48.713142z m3.291428-194.194285c-66.486857 98.742857-161.28 179.712-284.379428 242.249142l28.306285 42.788572A745.325714 745.325714 0 0 0 870.4 238.372571c60.562286 94.793143 153.380571 173.787429 277.138286 238.299429l28.306285-42.788571c-134.290286-69.12-229.083429-148.772571-284.379428-239.616h-42.130286z m556.251429 230.4h281.088v118.491428h-281.088v-118.491428z m281.088-44.105143h-281.088V265.362286h281.088v115.2z m-283.721143 206.701714h283.721143V718.262857c0 19.090286-7.899429 28.964571-23.04 28.964572-25.014857 0-52.662857-1.316571-82.944-2.633143l13.165714 46.738285h84.918857c36.864 0 55.954286-19.090286 55.954286-56.612571V219.940571H1357.531429v321.243429c-1.316571 90.843429-23.04 165.888-66.486858 225.133714l36.205715 32.914286c42.788571-55.954286 67.803429-126.390857 75.702857-211.968z" fill="#1afa29" p-id="1724"></path></svg>
                    </router-link>
                </div>
            </el-header>
            <el-main style="height: 466px">
                <div class="c1" >
                 <div class="c1-son2">
                     <h5>总单数</h5>
                 </div>
                    <div class="c1-son1" v-for="user in users" :key="user.id">
                        <h2 style="font-size-adjust: 0.90;line-height: 1.5;">{{user.count}}</h2>
                    </div>

                </div>
                <div class="c1-son3"></div>
                <div class="c1-son4">
                    <h5>总趟数</h5>
                </div>
                <div class="c1-son5">
                    <h5>排名</h5>
                </div>
                <div class="c1-son6" v-for="user in users" :key="user.id">
                    <h5>{{user.count}}</h5>
                </div>
                <div class="c1-son7">
                    <h5>1</h5>
                </div>
                <div id="main" style="">
                    <canvas ref="myCanvas"></canvas>
                </div>
            </el-main>
        </el-container>
    </div>
</template>

<style scoped>
.common-layout {
    margin-top: -5px;
    margin-left: -5px;
    width: 318px;
    margin-right: -11px;
}
.c1 {
    position: relative; /* 设置父元素的相对定位 */
    background: rgb(38 193 28);
    height: 125px;
    width: 317px;
    margin-left: -20px;
    margin-right: -20px;
    display: grid;
    place-items: center;
    margin-top: -19px;
}
.c1-son1 {

    color: white; /* 设置字体颜色为白色 */
    display: flex;
    justify-content: center; /* 水平居中 */
    align-items: center;
    height: 50px;
    width: 60px;
    line-height: 1.5;
    margin-top: -10px;
    position: absolute; /* 设置子元素的绝对定位 */
    top: 45px; /* 子元素距离父元素顶部10px */
    left: 135px; /* 子元素距离父元素左侧10px */
    /* 其他样式 */
}
.c1-son2 {
    width: 30px;
    color: white; /* 设置字体颜色为白色 */
    height: 5px;
    line-height: 3.2;
    font-size: 12px; /* 设置字体大小为 12 像素 */
    position: relative; /* 设置子元素的绝对定位 */
    top: -67px; /* 子元素距离父元素顶部10px */
    left: 7px; /* 子元素距离父元素左侧10px */
    /* 其他样式 */
}
.c1-son3 {
    width: 1px;
    height: 35px;
    background: white;
    position: relative; /* 设置为绝对定位 */
    top: -35px; /* 设置距离父元素顶部 10 像素 */
    left: 144px; /* 设置距离父元素左侧 0 像素 */

}
.c1-son4 {
    width: 30px;
    color: white; /* 设置字体颜色为白色 */
    height: 5px;
    line-height: 3.2;
    position: relative; /* 设置为绝对定位 */
    top: -102px; /* 设置距离父元素顶部 10 像素 */
    left: 47px; /* 设置距离父元素左侧 0 像素 */
    font-size: 10px; /* 设置字体大小为 12 像素 */
}
.c1-son5 {
    width: 30px;
    color: white; /* 设置字体颜色为白色 */
    height: 5px;
    line-height: 3.2;
    position: relative; /* 设置为绝对定位 */
    top:-120px; /* 设置距离父元素顶部 10 像素 */
    left: 212px; /* 设置距离父元素左侧 0 像素 */
    font-size: 10px; /* 设置字体大小为 12 像素 */
}
.c1-son6 {
    width: 30px;
    color: white;
    height: 5px;
    line-height: 3.2;
    position: relative;
    top: -118px;
    left: 54px;
    font-size: 11px;
}
.c1-son7 {
    width: 30px;
    color: white;
    height: 5px;
    line-height: 3.2;
    position: relative;
    top: -138px;
    left: 218px;
    font-size: 11px;
}
.my-custom-class {
    position: absolute;
    left: 0px;
    top: 0px;
    width: 315px;
    height: 315px;
    user-select: none;
    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
    padding: 0px;
    margin: 0px;
    border-width: 0px;
    margin-left: -20px;
}
#main {
    width: 315px;
    height:340px;
    margin-left: -20px;
    margin-right: -110px;
    margin-top: -113px;
}

</style>
