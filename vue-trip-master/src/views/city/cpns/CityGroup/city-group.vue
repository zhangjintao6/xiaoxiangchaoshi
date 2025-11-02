<template>
  <div class="city-group">
    <van-index-bar :index-list="indexList">
      <van-index-anchor index="热门"/>
      <div class="hot">
        <template v-for="item in groupData?.hotCities">
          <div class="hot-item" @click="cityClick(item)">{{ item.cityName }}</div>
        </template>
      </div>
      <template v-for="item in groupData?.cities">
        <van-index-anchor :index="item.group"/>
        <template v-for="item1 in item.cities">
          <van-cell :title="item1.cityName" @click="cityClick(item1)"></van-cell>
        </template>
      </template>
    </van-index-bar>
  </div>
</template>

<script setup>
import { computed } from 'vue'
import { useRouter} from 'vue-router';
import useCityStore from '@/stores/modules/city';

const router = useRouter()
const cityStore = useCityStore()

const props = defineProps({
groupData: {
  type: Object,
  default: () => ({})
}
})

// 动态索引
const indexList = computed(() => { 
const list = props.groupData.cities.map(item => item.group) 
  list.unshift('#')
  return list
})

// 点击选择城市
const cityClick = (city) => {
  cityStore.currentCity = city
  router.back()
}
</script>

<style lang="less" scoped>
.hot {
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
  padding: 10px;
  padding-right: 25px;
  .hot-item {
    width: 70px;
    height: 28px;
    line-height: 28px;
    text-align: center;
    border-radius: 14px;
    background-color: #fff4ec;
    margin: 5px 0;
  }
}
</style>