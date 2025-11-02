<script lang="ts" setup>
import { ref, onMounted } from 'vue';
import { ElForm, ElFormItem } from 'element-plus';
import { ElButton, ElPopconfirm } from 'element-plus';
import axios from 'axios';
import {localGet} from "../utils"
const showInput = ref(false)
const initialLocation = ref('小象超市'); // 初始地点的文本内容
const time=ref("30分钟");
// 假设 orders 是一个响应式引用，它包含了所有订单的数据
const orders = ref([]);
const padding = ref([]);
const end = ref([]);
const fina = ref([]);
// 假设 fetchOrders 是一个函数，用于从后端获取订单数据
const fetchOrders = async () => {
    try {
        const response = await axios.get('http://localhost:88/orders/ordersList');
        orders.value = response.data;
    } catch (error) {
        console.error('There was an error!', error);
    }
};

// 在组件挂载时获取数据
fetchOrders();

const finall = async () => {
    try {
        const response = await axios.get('http://localhost:88/orders/overList');
        fina.value = response.data;
    } catch (error) {
        console.error('There was an error!', error);
    }
};

// 在组件挂载时获取数据
finall();
const endOrders = async () => {
    try {
        const response = await axios.get('http://localhost:88/orders/endList');
        end.value = response.data;
    } catch (error) {
        console.error('There was an error!', error);
    }
};

// 在组件挂载时获取数据
endOrders();

const paddingorder = async () => {
    try {
        const response = await axios.get('http://localhost:88/orders/pendingList');
        padding.value = response.data;
    } catch (error) {
        console.error('There was an error!', error);
    }
};

// 在组件挂载时获取数据
paddingorder();

function submitForm() {
    console.log('确认按钮被点击，初始地点是：', initialLocation.value);
    // 在这里添加确认按钮点击后的逻辑，例如发送请求等
}
const handleClick = (tab) => {
    // 根据点击的标签页进行路由跳转
    if (tab.name === 'first') {
        this.$router.push('/orders');
    } else if (tab.name === 'second') {
        // 可以根据需要添加其他路由跳转
    } else if (tab.name === 'third') {
        // ...
    } else if (tab.name === 'fourth') {
        // ...
    }
}
const activeName = ref('first')
const count = ref(0)
const load = () => {
    count.value += 2
}
const acceptOrder = (order_id) => {
    let rid=localGet('token')
    const decoded = parseJwt(rid);
    // 获取sub字段的值
    const sub = decoded.sub;
    const subObj = JSON.parse(sub.replace(/'/g, '"')); // 替换单引号为双引号
    if (order_id) {
        // 使用 Axios 发送 POST 请求
        axios.get('http://localhost:88/orders/ordersPickup?order_id='+order_id+"&&user_id="+subObj.user_id)
            .then(response => {
                console.log('Order accepted successfully:', response.data);
                // 处理成功响应
                // 刷新页面
                window.location.reload();
                // 显示窗口提示
                const alertElement = document.createElement('div');
                alertElement.style.position = 'fixed';
                alertElement.style.top = '50%';
                alertElement.style.left = '50%';
                alertElement.style.transform = 'translate(-50%, -50%)';
                alertElement.style.backgroundColor = 'white';
                alertElement.style.padding = '20px';
                alertElement.style.border = '1px solid black';
                alertElement.style.boxShadow = '0 0 10px rgba(0, 0, 0, 0.5)';
                alertElement.style.zIndex = '1000';
                alertElement.innerText = '接单成功';
                document.body.appendChild(alertElement);
                // 等待 1 秒后移除提示
                setTimeout(() => {
                    document.body.removeChild(alertElement);
                }, 1000);})

            .catch(error => {
                console.error('Error accepting order:', error);
                // 处理错误情况
            });
    } else {
        console.error('Order ID not found');
    }
};
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

const endOrder = (order_id) => {
    let rid=localGet('token')
    const decoded = parseJwt(rid);
    // 获取sub字段的值
    const sub = decoded.sub;
    const subObj = JSON.parse(sub.replace(/'/g, '"')); // 替换单引号为双引号
    if (order_id) {
        // 使用 Axios 发送 POST 请求
        axios.get('http://localhost:88/orders/overOrders?order_id='+order_id+"&&user_id="+subObj.user_id)
            .then(response => {
                console.log('Order accepted successfully:', response.data);
                // 处理成功响应
                // 刷新页面
                window.location.reload();
                // 显示窗口提示
                const alertElement = document.createElement('div');
                alertElement.style.position = 'fixed';
                alertElement.style.top = '50%';
                alertElement.style.left = '50%';
                alertElement.style.transform = 'translate(-50%, -50%)';
                alertElement.style.backgroundColor = 'white';
                alertElement.style.padding = '20px';
                alertElement.style.border = '1px solid black';
                alertElement.style.boxShadow = '0 0 10px rgba(0, 0, 0, 0.5)';
                alertElement.style.zIndex = '1000';
                alertElement.innerText = '接单成功';
                document.body.appendChild(alertElement);
                // 等待 1 秒后移除提示
                setTimeout(() => {
                    document.body.removeChild(alertElement);
                }, 1000);})

            .catch(error => {
                console.error('Error accepting order:', error);
                // 处理错误情况
            });
    } else {
        console.error('Order ID not found');
    }
};

const sendOrders = () => {
    if (padding.value.length > 0) {
        // 使用 Axios 发送 POST 请求
        axios.post('http://localhost:88/orders/acceptOrders', padding.value, {
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => {
                console.log('Orders sent successfully:', response.data);
                // 处理成功响应
                // 刷新页面
                window.location.reload();
            })
            .catch(error => {
                console.error('Error sending orders:', error);
                // 处理错误情况
            });
    } else {
        console.error('No orders found');
    }
};



// 存储时间差，单位为分钟
const timeDifference = ref(0);

// 方法：计算时间差
const calculateTimeDifference = (order) => {
    const startTime = new Date(order.start_time);
    const currentTime = new Date();
    const differenceInMilliseconds = currentTime - startTime;
    timeDifference.value = Math.floor(differenceInMilliseconds / 60000);
};

// 组件挂载时获取每个订单的时间并计算时间差
onMounted(() => {
    orders.value.forEach((order) => {
        calculateTimeDifference(order);
    });
});
</script>

<template>
    <div class=".el-tabs">
    <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick">
        <el-tab-pane label="抢单大厅" name="first" to="orders" >
            <ul v-infinite-scroll="load" class="infinite-list" style="overflow: auto;height: 439px;" >
                <li v-for="order in orders" :key="order.id" class="infinite-list-item">
                    <div id="distance">
                        <div id="d1">
                            23m
                        </div>
                        <svg id="d2" t="1726641249745" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="41644" width="15" height="15"><path d="M528 32c-202.944 0-368 164.8-368 367.36 0 75.2 22.656 147.584 65.024 208.48 2.112 3.648 4.256 7.168 6.784 10.592l268.608 353.472C507.712 980 517.504 984.48 528 984.48c10.368 0 20.224-4.512 28.768-14.08l267.36-352c2.624-3.52 4.896-7.36 6.112-9.6A364.864 364.864 0 0 0 896 399.36C896 196.8 730.912 32 528 32z m0 498.72a131.52 131.52 0 0 1-131.456-131.232 131.488 131.488 0 0 1 262.88 0A131.52 131.52 0 0 1 528 530.688z" p-id="41645"></path></svg>
                    <div id="d3">
                        {{order.distance}}m
                    </div>
                    </div>
                    <div id="aname">
                        小象网上超市
                    </div>
                    <svg id="n1" t="1726650742676" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4335" width="30" height="30"><path d="M622.976 1024v-179.392h208.128v-123.712H622.976v-106.88h208.128V490.24H658.56L897.92 55.872H701.12L560.448 382.144c-11.648 27.52-20.224 48.512-25.6 63.104h-2.56L362.368 55.872H154.88L387.328 490.24H211.072v123.776h215.04v106.88h-215.04v123.712h215.04V1024h196.864z" fill="#FF8000" p-id="4336"></path></svg>
                    <div id="bname">
                        <p id="pname">{{order.user_addresses.address_name}}</p>
                    </div>
                    <div id="n2">
                        <p>{{order.price}}</p>
                    </div>
                    <div id="sw">
                        <el-switch
                            v-model="order.showInput"
                            size="small"
                            active-text="抢单"
                            inactive-text="不抢"
                        />
                        <el-dialog v-model="order.showInput" title="订单详情" :width="320" :height="600">
                            <el-form style="width: 100%" label-width="80">
                                <el-form-item label="初始地点:">
                                    <el-textarea>
                                       小象超市
                                    </el-textarea>
                                </el-form-item>
                                <el-form-item label="送单时间:">
                                    30分钟
                                </el-form-item>
                                <el-form-item label="最终地点:">
                                    <el-textarea>
                                        {{order.user_addresses.province}}
                                        {{order.user_addresses.city}}
                                        {{order.user_addresses.county}}
                                        {{order.user_addresses.address_name}}
                                    </el-textarea>
                                </el-form-item>
                                <el-form-item label="联系人:">
                                    <el-textarea>
                                        {{order.user_addresses.other_name}}
                                    </el-textarea>
                                </el-form-item>
                                <el-form-item label="用户电话:">
                                    <el-textarea>
                                        {{order.user_addresses.other_phone}}
                                    </el-textarea>
                                </el-form-item>
                                <el-form-item>
                                    <el-button @click="acceptOrder(order.order_id)">确认接单</el-button>
                                </el-form-item>
                            </el-form>
                        </el-dialog>
                    </div>
                </li>
            </ul>
        </el-tab-pane>
        <el-tab-pane label="待取货" name="second">
            <ul v-infinite-scroll="load" class="infinite-list" style="overflow: auto; height: 439px;">
                <li v-for="order in padding" :key="order.id"  class="infinite-list-item">
                    <div id="distance">
                        <div id="d1">
                            23m
                        </div>
                        <svg id="d2" t="1726641249745" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="41644" width="15" height="15"><path d="M528 32c-202.944 0-368 164.8-368 367.36 0 75.2 22.656 147.584 65.024 208.48 2.112 3.648 4.256 7.168 6.784 10.592l268.608 353.472C507.712 980 517.504 984.48 528 984.48c10.368 0 20.224-4.512 28.768-14.08l267.36-352c2.624-3.52 4.896-7.36 6.112-9.6A364.864 364.864 0 0 0 896 399.36C896 196.8 730.912 32 528 32z m0 498.72a131.52 131.52 0 0 1-131.456-131.232 131.488 131.488 0 0 1 262.88 0A131.52 131.52 0 0 1 528 530.688z" p-id="41645"></path></svg>
                        <div id="d3">
                            {{order.distance}}m
                        </div>
                    </div>
                    <div id="aname">
                        小象网上超市
                    </div>
                    <svg id="n1" t="1726650742676" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4335" width="30" height="30"><path d="M622.976 1024v-179.392h208.128v-123.712H622.976v-106.88h208.128V490.24H658.56L897.92 55.872H701.12L560.448 382.144c-11.648 27.52-20.224 48.512-25.6 63.104h-2.56L362.368 55.872H154.88L387.328 490.24H211.072v123.776h215.04v106.88h-215.04v123.712h215.04V1024h196.864z" fill="#FF8000" p-id="4336"></path></svg>
                    <div id="bname">
                        <p id="pname">{{order.user_addresses.address_name}}</p>
                    </div>
                    <div id="n2">
                        <p>{{order.price}}</p>
                    </div>
                </li>
                <el-button type="warning" round class="big-button"@click="sendOrders">立即配送</el-button>
            </ul>
        </el-tab-pane>
        <el-tab-pane label="配送中" name="third">
            <ul v-infinite-scroll="load" class="infinite-list" style="overflow: auto; height: 439px;">
                <li v-for="order in end" :key="order.id" class="infinite-list-item">
                    <div id="distance">
                        <div id="d1">
                            23m
                        </div>
                        <svg id="d2" t="1726641249745" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="41644" width="15" height="15"><path d="M528 32c-202.944 0-368 164.8-368 367.36 0 75.2 22.656 147.584 65.024 208.48 2.112 3.648 4.256 7.168 6.784 10.592l268.608 353.472C507.712 980 517.504 984.48 528 984.48c10.368 0 20.224-4.512 28.768-14.08l267.36-352c2.624-3.52 4.896-7.36 6.112-9.6A364.864 364.864 0 0 0 896 399.36C896 196.8 730.912 32 528 32z m0 498.72a131.52 131.52 0 0 1-131.456-131.232 131.488 131.488 0 0 1 262.88 0A131.52 131.52 0 0 1 528 530.688z" p-id="41645"></path></svg>
                        <div id="d3">
                            {{order.distance}}m
                        </div>
                    </div>
                    <div id="aname">
                        小象网上超市
                    </div>
                    <svg id="n1" t="1726650742676" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4335" width="30" height="30"><path d="M622.976 1024v-179.392h208.128v-123.712H622.976v-106.88h208.128V490.24H658.56L897.92 55.872H701.12L560.448 382.144c-11.648 27.52-20.224 48.512-25.6 63.104h-2.56L362.368 55.872H154.88L387.328 490.24H211.072v123.776h215.04v106.88h-215.04v123.712h215.04V1024h196.864z" fill="#FF8000" p-id="4336"></path></svg>
                    <div id="bname">
                        <p id="pname">{{order.user_addresses.address_name}}</p>
                    </div>
                    <div id="n2">
                        <p>{{order.price}}</p>
                    </div>
                    <div id="sw">
                        <el-switch
                            v-model="order.showInput"
                            size="small"
                            active-text="未送达"
                            inactive-text="已送达"
                        />
                        <el-dialog v-model="order.showInput" title="订单详情" :width="320" :height="600">
                            <el-form style="width: 100%" label-width="80">
                                <el-form-item label="初始地点:">
                                    <el-textarea>
                                        小象超市
                                    </el-textarea>
                                </el-form-item>
                                <el-form-item label="送单时间:">
                                   30分钟
                                </el-form-item>
                                <el-form-item label="最终地点:">
                                    <el-textarea>
                                        {{order.user_addresses.province}}
                                        {{order.user_addresses.city}}
                                        {{order.user_addresses.county}}
                                        {{order.user_addresses.address_name}}
                                    </el-textarea>
                                </el-form-item>
                                <el-form-item label="联系人:">
                                    <el-textarea>
                                        {{order.user_addresses.other_name}}

                                    </el-textarea>
                                </el-form-item>
                                <el-form-item label="用户电话:">
                                    <el-textarea>
                                        {{order.user_addresses.other_phone}}
                                    </el-textarea>
                                </el-form-item>
                                <el-form-item>
                                    <el-button @click="endOrder(order.order_id)">配送完成</el-button>
                                </el-form-item>
                            </el-form>
                        </el-dialog>
                    </div>
                </li>
            </ul>
        </el-tab-pane>
        <el-tab-pane label="已完成订单" name="fourth">
            <ul v-infinite-scroll="load" class="infinite-list" style="overflow: auto;height: 439px;">
                <li v-for="order in fina" :key="order.id" class="infinite-list-item">
                    <div id="distance">
                        <div id="d1">
                            23m
                        </div>
                        <svg id="d2" t="1726641249745" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="41644" width="15" height="15"><path d="M528 32c-202.944 0-368 164.8-368 367.36 0 75.2 22.656 147.584 65.024 208.48 2.112 3.648 4.256 7.168 6.784 10.592l268.608 353.472C507.712 980 517.504 984.48 528 984.48c10.368 0 20.224-4.512 28.768-14.08l267.36-352c2.624-3.52 4.896-7.36 6.112-9.6A364.864 364.864 0 0 0 896 399.36C896 196.8 730.912 32 528 32z m0 498.72a131.52 131.52 0 0 1-131.456-131.232 131.488 131.488 0 0 1 262.88 0A131.52 131.52 0 0 1 528 530.688z" p-id="41645"></path></svg>
                        <div id="d3">
                            {{order.distance}}m
                        </div>
                    </div>
                    <div id="aname">
                        小象网上超市
                    </div>
                    <svg id="n1" t="1726650742676" class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="4335" width="30" height="30"><path d="M622.976 1024v-179.392h208.128v-123.712H622.976v-106.88h208.128V490.24H658.56L897.92 55.872H701.12L560.448 382.144c-11.648 27.52-20.224 48.512-25.6 63.104h-2.56L362.368 55.872H154.88L387.328 490.24H211.072v123.776h215.04v106.88h-215.04v123.712h215.04V1024h196.864z" fill="#FF8000" p-id="4336"></path></svg>
                    <div id="bname">
                        <p id="pname">{{order.user_addresses.address_name}}</p>
                    </div>
                    <div id="n2">
                        <p>{{order.price}}</p>
                    </div>
                </li>
            </ul>
        </el-tab-pane>
    </el-tabs>
    </div>

</template>

<style scoped>

.big-button {
    font-size: 67px;
    padding: 36px 20px;
    height: 52px;
}
.infinite-list {
    height: 413px;
    padding: 0;
    margin: 0;
    list-style: none;
}
.infinite-list .infinite-list-item {
        position: relative; /* 使父元素可以包含绝对定位的子元素 */
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100px;
    background: var(--el-color-primary-light-9);
    margin: 10px;
    color: var(--el-color-primary);
}
.infinite-list .infinite-list-item + .list-item {
    margin-top: 10px;
}

.demo-tabs {
    background-color: #f5f5f5;
    width: 103%;
    height: 100%;
    margin-top: -5px;
    margin-left: -5px;
    margin-right: 0px;
    margin-bottom: 0;
    padding: 0;
}
html, body {
    height: 100%;
    margin: 0;
    padding: 0;
}


.el-tab-pane {
    height: 440px;
}
.infinite-list-fixed-height {
    overflow: auto;
    height: 439px;
}
#d1 {
    position: relative;
    font-size: 13px;
    line-height: 1.5;
    left: 9px;
    top: 0;
}
#d2 {
    position: relative;
    left: 12px;
    top: 9px;
}
#d3 {
    position: relative;
    font-size: 13px;
    line-height: 1.5;
    left: 6px;
    top: 15px;
}
#aname {
    /* 根据需要设置样式，以下为示例 */
    display: inline-block; /* 使盒子与其他内容在同一行显示 */
    margin-left: 10px; /* 与其他内容保持一定距离 */
    /* 其他样式，如颜色、字体大小等 */
    position: relative;
    left:5px;
    top: -18px;
    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 4; /* 限制为2行 */
    overflow: hidden;
    flex-grow: 1; /* 使 #aname 容器能够适应其内容 */
    text-overflow: ellipsis; /* 显示省略号以指示文本被截断 */
    width: 230px;
}
#bname {
    display: inline-block;
    margin-left: 10px;
    position: relative;
    left: -112px;
    top: 33px;
    overflow: hidden; /* 隐藏超出宽度的内容 */
    flex-grow: 1; /* 使 #bname 容器能够适应其内容 */
    width: 230px; /* 假设这是您希望的最大宽度 */
    white-space: nowrap; /* 防止文本换行 */
    text-overflow: ellipsis; /* 显示省略号以指示文本被截断 */
}
#sw {
    width: 10px;
    height: 10px;
    position: relative;
    left: -110px;
    top: 20px;
}
#n1 {
    position: relative;
    left: 76px;
    top: -35px;
}
#n2 {
    width: 62px;
    display: inline-block;
    margin-left: 10px;
    position: relative;
    overflow: hidden;
    flex-grow: 1;
    white-space: nowrap;
    text-overflow: ellipsis;
    font-size: 17px;
    line-height: 1.5;
    left: -25px;
    top: -33px;

}
#s1{
    position: relative;
    left: 10px;
    top: 20px;
}
</style>