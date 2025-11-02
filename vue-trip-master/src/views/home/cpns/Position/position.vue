<template>
  <div class="position border-bottom">
    <div class="city" @click="$router.push('/city')">{{ currentCity.cityName }}</div>
    <div class="location" @click="positionClick">
      <span style="color:#666;font-size: 12px;">我的位置</span>
      <svg-icon iconClass="location" className="icon"></svg-icon>
    </div>
  </div>
</template>
 
<script setup>
import useCityStore from "@/stores/modules/city";
import { storeToRefs } from "pinia";

const cityStore = useCityStore()
const { currentCity } = storeToRefs(cityStore)
// 获取地址  
const positionClick = () => {
  navigator.geolocation.getCurrentPosition(res => {
    console.log(res,'获取位置成功');
  },err => {
    console.log(err,'获取位置失败');
  },{
    enableHighAccuracy: true,
    timeout: 1000,
    maximumAge: 0
  })
}
</script>
 
<style lang="less" scoped>
.position {
  display: flex;
  height: 30px;
  align-items: center;
  padding: 5px 0;
  margin: 0 20px;
  .city {
    flex: 1;
  }
  .location {
    .icon {
      fill: var(--primary-color);
      margin-left: 5px;
      font-size: 18px;
    }
  }
}
</style>