<template>
  <div>
    <van-nav-bar title="民宿" v-show="!showSearchBar"></van-nav-bar>
    <!-- 顶部搜索栏 -->
    <search-bar v-show="showSearchBar"></search-bar>
    <!-- banner -->
    <div class="banner">
      <img src="@/assets/img/home/banner.webp" alt="">
    </div>
    <!-- 地址 -->
    <Position></Position>
    <!-- 日期选择 -->
    <date-sel></date-sel>
    <!-- 价格人数选择 -->
    <Select></Select>
    <!-- 搜索 -->
    <search-box></search-box>
    <!-- 分类 -->
    <!-- <Categories></Categories> -->
    <!-- 民宿列表 -->
    <List></List>
  </div>
</template>
<script setup>
import { watch, computed } from 'vue'
import Position from './cpns/Position/position.vue'
import DateSel from './cpns/DateSel/date-sel.vue'
import Select from './cpns/Select/select.vue'
import SearchBox from './cpns/SearchBox/search-box.vue'
import List from './cpns/List/list.vue'
import SearchBar from './cpns/SearchBar/search-bar.vue'
import useHomeStore from '@/stores/modules/home'
import useScroll from '@/hooks/useScroll'

const homeStore = useHomeStore()
homeStore.fetchHotSuggeetsData()
homeStore.fetchCategories()
homeStore.fetchHouseList()

const { isReachBottom, scrollTop } = useScroll()

/** 滚动到100显示搜索栏 */
const showSearchBar = computed(() => {
  return scrollTop.value >= 100
})

/** 滚动到底部加载更多 */
watch(isReachBottom, (newVal) => {
  if(newVal) {
    homeStore.fetchHouseList().then(() => {
      isReachBottom.value = false
    })
  }
})

</script>
<style lang="less" scoped>
:deep(.van-nav-bar) {
  .van-nav-bar__title {
    color: var(--primary-color);
  }
}
.banner {
  img {
    width: 100%;
  }
}
</style>