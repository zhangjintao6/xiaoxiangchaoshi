<script setup>
import {ref, onMounted, createApp} from 'vue';
import { AddressEdit, showToast } from 'vant';
import { areaList } from "@vant/area-data";
import axios from "axios";
import router from "@/router"; // 从 vue-router 导入 useRoute

const app = createApp();
app.use(AddressEdit);

const searchResult = ref([]);
let addressInfo = ref({}); // 使用 ref 创建响应式对象
const onClickLeft = () => history.back();
const onChangeDetail = (val) => {
    if (val) {
        searchResult.value = [
            {
                name: '黄龙万科中心',
                address: '杭州市西湖区',
            },
        ];
    } else {
        searchResult.value = [];
    }
};
let obj = {};
onMounted(() => {
    let value = localStorage.getItem('param');
    obj = JSON.parse(value);
    axios.get("http://localhost:88/user_addresses/findAddressById?id=" + obj.id).then(res => {
        console.log(res.data);
        const address = res.data;
        //修改后的内容
        addressInfo.value = {
            id: address.adress_id,
            name: address.other_name,
            tel: address.other_phone,
            province: address.province,
            city: address.city,
            county: address.county,
            addressDetail: address.address_name,
            isDefault: address.default_address === '1',
        };

    });
});
const onSave = (value) => {
    showToast('save');
    const addressData = {
        other_name: value.name,
        other_phone: value.tel,
        province: value.province,
        city: value.city,
        county: value.county,
        address_name: value.addressDetail,
        default_address: value.isDefault,
    };
    if (addressData.default_address) {
        addressData.default_address = 1;
        axios.post("http://localhost:88/user_addresses/setAddressDefault")
    } else {
        addressData.default_address = 0;
    }
    axios.post("http://localhost:88/user_addresses/del?id=" + obj.id).then(res=>{
        axios.post('http://localhost:88/user_addresses/addressAdd', addressData).then(res=>{
            router.push('cart');
        })
    })
}
const onDelete = (id) => {
    id = obj.id;
    showToast('delete');
    axios.post("http://localhost:88/user_addresses/del?id="+id).then(res => {
        // 处理响应
        router.push('/cart');
    });
};
</script>


<template>
    <van-nav-bar
            title="修改收货地址"
            left-text="返回"
            left-arrow
            @click-left="onClickLeft"
    />
    <van-address-edit
            :area-list="areaList"
            show-delete
            :address-info="addressInfo"
            show-set-default
            show-search-result
            :search-result="searchResult"
            :area-columns-placeholder="['请选择', '请选择', '请选择']"
            @save="onSave"
            @delete="onDelete"
            @change-detail="onChangeDetail"
    />
</template>

<style scoped lang="less">
::v-deep .van-nav-bar__title {
  font-size: 15px; /* 改变标题字体大小 */
  color: #333; /* 改变标题颜色 */
}

::v-deep .van-nav-bar .van-icon,
::v-deep .van-nav-bar__text {
  font-size: 16px; /* 改变左侧文字和箭头图标的大小 */
  color: #666; /* 改变左侧文字和箭头图标的颜色 */
}

</style>
