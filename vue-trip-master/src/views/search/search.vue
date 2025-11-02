<template>
  <div class="search">
    <search-top></search-top>
    <house-list :data="dataList" class="house-list"></house-list>
  </div>
</template>
 
<script setup>
import searchTop from './cpns/SearchTop/search-top.vue';
import HouseList from '@/components/HouseList/house-list.vue';
import useSearchStore from '@/stores/modules/search';
import useFavorStore from '@/stores/modules/favor';
import { storeToRefs } from 'pinia';


const searchStore = useSearchStore()
searchStore.fetchSearchTopData()


const favorStore = useFavorStore()
favorStore.fetchGetFavorList()
const { dataList } = storeToRefs(favorStore)
</script>
 
<style lang="less" scoped>
.house-list {
  margin-top: 113px;
  box-shadow: 0 5px 5px 0 rgba(0,0,0,.06) inset;
}
</style>