<template>
  <van-tabbar v-model="currentIndex" route>
  <van-tabbar-item 
    replace
    :to="item.path"
    v-for="item in tabbarData">
    <template #icon="props">
      <svg-icon :iconClass="item.icon"></svg-icon>
    </template>
    {{ item.title }}
  </van-tabbar-item>
  </van-tabbar>
</template>
<script setup>
import { ref } from 'vue';

const tabbarData = [
    // {
    //   title:'首页',
    //   icon: 'shouye',
    //   path: '/home'
    // },
    // {
    //   title:'收藏',
    //   icon: 'shoucang',
    //   path: '/favor'
    // },
    // {
    //   title:'订单',
    //   icon: 'dingdan',
    //   path: '/order'
    // },
    // {
    //   title:'消息',
    //   icon: 'xiaoxi',
    //   path: '/message'
    // },
    {
        title: '商城',
        icon: '首页',
        path: '/shop'
    },
    {
        title: '分类',
        icon: '分类',
        path: '/sort'
    },
    {
        title: '严选',
        icon: '严选产品',
        path: '/yanxuan'
    },
    {
        title: '购物车',
        icon: '购物车',
        path: '/cart'
    },
    {
        title: '我的',
        icon: 'myde',
        path: '/zuye'
    },

];
  const currentIndex = ref(0)
  const itemClick = (index) => {
    currentIndex.value = index
  }
</script>
<style lang="less" scoped>

</style>