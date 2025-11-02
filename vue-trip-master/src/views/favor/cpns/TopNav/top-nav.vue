<template>
  <div class="top-nav">
    <div class="header border-bottom">
      <van-tabs v-model:active="headerActive" type="card">
        <van-tab title="我的收藏" name="collect"></van-tab>
        <van-tab title="浏览历史" name="history"></van-tab>
      </van-tabs>
    </div>
    <template v-if="headerActive=='collect'">
      <div class="tab">
        <van-tabs v-model:active="active">
          <van-tab title="房屋" name="house"></van-tab>
          <van-tab title="房东" name="landlord"></van-tab>
        </van-tabs>
      </div>
      <div class="filter bottom-shadow" @click="showDateSel = true" v-show="active=='house'">
        <span class="date">
          {{ formatDate(startDate) }}-{{ formatDate(endDate) }}
          <div class="tri"></div>
        </span>
      </div>
    </template>
    <date-picker></date-picker>
  </div>
</template>
 
<script setup>
import DatePicker from '@/components/DatePicker/date-picker.vue'; 
import useMainStore from '@/stores/modules/main';
import useFavorStore from '@/stores/modules/favor';
import { storeToRefs } from 'pinia';


const favorStore = useFavorStore()
const { headerActive, active } = storeToRefs(favorStore)

const mainStore = useMainStore()
const { showDateSel, startDate, endDate } = storeToRefs(mainStore);
// 改成mm.dd
const formatDate = (value) =>{
  return value.replace('月','.').replace('日','')
}

</script>
 
<style lang="less" scoped>
.top-nav {
  :deep(.header) {
    width: 100%;
    height: 44px;
    display: flex;
    justify-content: center;
    align-items: center;
    .van-tabs__nav--card {
      border-radius: 5px;
    }
    .van-tab {
      padding: 0 10px;
    }
  }
  .filter {
    height: 37px;
    line-height: 37px;
    padding: 0 20px;
    .date {
      position: relative;
      .tri {
        width: 0;
        height: 0;
        border-right: 5px solid #000;
        border-top: 5px solid transparent;
        position: absolute;
        bottom: 0;
        right: -10px;
      }
    }
  }
}
</style>