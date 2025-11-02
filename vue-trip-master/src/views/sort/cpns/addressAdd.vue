<script setup>
import {createApp, ref} from 'vue';
import {AddressEdit, showToast} from 'vant';
import {areaList} from "@vant/area-data";
import axios from "axios";
import router from "@/router";


const app = createApp();
app.use(AddressEdit);

const searchResult = ref([]);

const onSave = (value) => {
    console.log(value)
    const addressData = {
        other_name: value.name,
        other_phone: value.tel,
        province: value.province,
        city: value.city,
        county: value.county,
        address_name: value.addressDetail,
        default_address: value.isDefault,
    }
    if (addressData.default_address) {
        addressData.default_address = 1;
        axios.post("http://localhost:88/user_addresses/setAddressDefault");
    } else {
        addressData.default_address = 0;
    }

    axios.post('http://localhost:88/user_addresses/addressAdd', addressData)
        .then(response => {
            // 处理响应
            console.log('地址信息已发送到后端：', response.data);
            router.push('/cart');
        })
        .catch(error => {
            // 处理错误
            console.error('发送地址信息时出错：', error);
        });
};
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
</script>

<template>
    <van-nav-bar
            title="新增收货地址"
            left-text="返回"
            left-arrow
            @click-left="onClickLeft"
    />
    <van-address-edit
            :area-list="areaList"
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