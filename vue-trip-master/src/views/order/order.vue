<template>
  <div class="order">
    <van-nav-bar title="订单列表"></van-nav-bar>
    <van-tabs v-model:active="active" @change="fetchOrderData">
      <van-tab title="全部订单"></van-tab>
      <van-tab title="待支付"></van-tab>
      <van-tab title="进行中"></van-tab>
      <van-tab title="待评价"></van-tab>
    </van-tabs>
      <div class="empty" v-if="orderList.length === 0">
          <img src="@/assets/img/order/empty_order.png" alt="">
          <p>暂无订单数据</p>
      </div>
      <div class="order-data" v-else>
          <!-- 后端订单数据将存储在这里 -->
          <div v-for="order in orderList" :key="order.id">
              <div class="dd">
              <!-- 订单详情 -->
                  <div class="dd_1">
                      <span>{{ order.start_time }}</span>
                      <span>订单状态: {{ order.order_status_id}}</span>
                  </div>
                  <div class="dd_2">
                      <!--产品详情-->
                      <div STYLE="width: 100%;height: 100px;background-color: #fff;color: red">
                        <div v-for="ot in order.orderDetails"  style="display: flex;margin-top: 10px">
                            <div style="width: 80px;height: 80px">
                                <span v-if="ot != null">{{ot.products.product_name}}</span>
                            </div>
                        </div>
                      </div>
                      <div >
                          <div style="text-align: right" >
                              <span>实付:￥{{order.total_amount}}</span>
                          </div>
                          <div style="display: flex">
                          <div v-if="order.order_status_id === 0" style="display: flex;width: 230px;height: 40px"> <!-- 假设订单状态为1时表示待支付 -->
                              <button>取消订单</button>
                              <button>修改订单</button>
                          </div>
                          <div v-else>
                              <!-- 如果没有订单状态为待支付的订单，则显示其他内容 -->
                          </div>
                          <div style="display: flex;width: 230px;height: 40px" >
                              <button @click="deleteOrder(order.orderId)">删除订单</button>
                              <button>再来一单</button>
                          </div>
                          </div>
                      </div>
                  </div>
              <!-- 更多订单详情... -->
              </div>
          </div>
      </div>
  </div>
</template>
<script setup>
import {onMounted, ref} from "vue"
import axios from "axios";
import {localGet} from "@/utils";


const active = ref(0);
const orderList = ref([]); // 存储后端订单数据的响应式状态
const odt=ref([]);//存储后端订单详情的数据
// 假设这是一个函数，用于从后端获取订单数据
const fetchOrderData = async (index) => {
    if (index === 0)  {
        axios.get('http://localhost:88/orders/findOrdersWithProducts?userid='+subObj.value.user_id)
            .then(res => {
                // 处理后端数据
                odt.value = res.data;
                orderList.value = odt.value;
            }).catch(error => {
            console.error('Error fetching order details:', error);
        });


} else if (index === 1)  {

        orderList.value = [];

        for (let i = 0; i < odt.value.length; i++) {
            if (odt.value[i].order_status_id === 4) {
                orderList.value.push(odt.value[i]);
            }
        }

}else if (index === 2)  {
        orderList.value = [];

        for (let i = 0; i < odt.value.length; i++) {
            if (odt.value[i].order_status_id === 6) {
                orderList.value.push(odt.value[i]);
            }
        }

    }else if (index === 3)  {
        orderList.value = [];

        for (let i = 0; i < odt.value.length; i++) {
            if (odt.value[i].order_status_id === 7) {
                orderList.value.push(odt.value[i]);
            }
        }
    }
};

let subObj = ref(null);
const orderdt = async () => {
    let sub = localGet('token');
    const decoded = parseJwt(sub);
    const sub1 = decoded.sub;
    subObj.value = JSON.parse(sub1.replace(/'/g, '"')); // 替换单引号为双引号
    axios.get('http://localhost:88/orders/findOrdersWithProducts?userid='+subObj.value.user_id)
        .then(res => {
            // 处理后端数据
            odt.value = res.data;
        }).catch(error => {
        console.error('Error fetching order details:', error);
    });
};
function parseJwt(token) {
    let base64Url = token.split('.')[1];
    let base64 = base64Url.replace('-', '+').replace('_', '/');
    let payload = decodeURIComponent(atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    return JSON.parse(payload);
}
// 删除订单的方法
const deleteOrder = async (orderId) => {
    // 弹出确认对话框（这里使用window.confirm作为示例）
    if (window.confirm('您确定要删除此订单吗？')) {
        try {
            // 调用后端API删除订单
            await axios.post(`http://localhost:88/orders/deleteOrderByOrderId?orderId=${orderId}`);
            // 删除成功后，从列表中移除该订单
            orderList.value = orderList.value.filter(order => order.id !== orderId);
            console.log('订单删除成功');
        } catch (error) {
            console.error('删除订单时出错:', error);
        }
    }
};

onMounted(()=>{

    orderdt();
    fetchOrderData(0);
    orderList.value = odt.value;

});
</script>
<style lang="less" scoped>
.order {
  height: 100vh;
  background-color: #f7f9fb;
}
.empty {
  width: 100%;
  font-size: 18px;
  display: flex;
  flex-direction: column;
  align-items: center;
  img {
    width: 100%;
    height: 100%;
    margin-bottom: 20px;
  }
}
.dd{
  width: 90%;
  height: 180px;
  border-radius: 15px;
  padding: 10px;
  margin-left: 10px;
  margin-top: 20px;
  background-color: #ffffff;
  text-align: justify;
}

.dd_1 {
  display: flex;
  justify-content: space-between;
}
。dd_2 span{
  height: 50px;background-color: #17d2bc;
}
.dd_2 button{
  margin-right: 10px;
  margin-left: 10px;
  width: 100px;
  border-radius: 15px;
  background-color: #ffffff;
  font-size: 10px;
}
</style>