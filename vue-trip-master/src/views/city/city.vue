<template>
  <div class="city">
    <div class="top">
      <van-search 
        v-model="searchValue" 
        placeholder="城市/区域/位置" 
        shape="round"
        show-action
        @search="onSearch"
        @cancel="onCancel">
      </van-search>
      <van-tabs v-model:active="tabActive">
        <template v-for="(value, key) in allCities">
          <van-tab :title="value.title" :name="key"></van-tab>
        </template>
      </van-tabs>
    </div>
    <div class="content">
      <template v-for="(value, key) in allCities">
        <city-group :groupData="value" v-show="tabActive === key"></city-group>
      </template>
    </div>
  </div>
</template>
<script setup>
import { ref, computed } from 'vue';
import { useRouter } from 'vue-router';
import useCityStore from '@/stores/modules/city'
import { storeToRefs } from 'pinia';
import CityGroup from './cpns/CityGroup/city-group.vue'
const router = useRouter()

const searchValue = ref()
const onSearch = () => {

}
const onCancel = () => {
  router.back()
}

const tabActive = ref()
// 将调用接口的方法封装到pinia中
const cityStore = useCityStore()
// 调用action中的方法
cityStore.fetchAllCitiesData()
// 将数据改为响应式
const { allCities } = storeToRefs(cityStore)
// 当前标签对应城市
// 将其变为计算属性，使其成为响应式数据
const currentGroup= computed(() => allCities.value[tabActive.value])
</script>
<style lang="less" scoped>
.city {
  .content {
    height: calc(100vh - 98px);
    overflow: auto;
  }

}
</style>