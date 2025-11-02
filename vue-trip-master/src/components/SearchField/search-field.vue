<template>
  <div class="box" :style="{height: sizeMini?'35px':'45px'}">
    <div class="position" v-if="sizeMini">
      {{ currentCity.cityName }}
      <div class="divide"></div>
    </div>
    <div class="date" @click=" showDateSel = true ">
      <div class="item">
        <span style="color: #999;">住</span>
        <span class="time">{{ formatDate(startDate) }}</span>
      </div>
      <div class="item">
        <span style="color: #999;">离</span>
        <span class="time"> {{ formatDate(endDate) }} </span>
      </div>
      <div class="tri"></div>
    </div>
    <div class="divide"></div>
    <div class="keyword" @click="$router.push('/search')" v-if="!sizeMini">
      关键字/位置/民宿
      <van-icon name="search" ></van-icon>
    </div>
    <div class="keyword" v-else>
      <van-field placeholder="搜索南京的景点、地标、房源"></van-field>
    </div>
  </div>
</template>

<script setup>
import useMainStore from '@/stores/modules/main';
import { storeToRefs } from "pinia";
import { computed } from 'vue'
import useCityStore from '@/stores/modules/city';

const props = defineProps({
  size: {
    type: String
  }
})
const sizeMini = computed(() => props.size == 'mini')

const cityStore = useCityStore()
const { currentCity } = storeToRefs(cityStore)

const mainStore = useMainStore()
const { showDateSel, startDate, endDate } = storeToRefs(mainStore);

// 改成mm.dd
const formatDate = (value) =>{
  return value.replace('月','.').replace('日','')
}
</script>

<style lang="less" scoped>
.box {
  flex: 1;
  border-radius: 10px;
  padding: 0 10px;
  background-color: #f2f4f6;
  display: flex;
  align-items: center;
  .position {
    height: 100%;
    display: flex;
    align-items: center;
  }
  .date {
    display: flex;
    flex-direction: column;
    padding: 0 10px 0 5px;
    position: relative;
    .item {
      font-size: 12px;
      .time {
        margin: 0 2px;
      }
    }
    .tri {
      width: 0;
      height: 0;
      border-right: 5px solid #000;
      border-top: 5px solid transparent;
      position: absolute;
      bottom: 0;
      right: 0;
    }
  }
  .divide {
    height: 100%;
    width: 1px;
    background-color: #fff;
    margin: 0 5px;
  }
  .keyword {
    flex: 1;
    padding: 0 10px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    color: #999;
    .van-icon {
      font-size: 24px;
      color: #666;
    }
    .van-field {
      background-color: transparent;
      padding: 0;
      font-size: 12px;
    }
  }
}
</style>
