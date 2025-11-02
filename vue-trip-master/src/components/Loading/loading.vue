<template>
  <van-overlay :show="mainStore.isLoading" @click="mainStore.isLoading = false">
    <div class="img">
      <img src="@/assets/img/home/full-screen-loading.gif" alt="">
    </div>
  </van-overlay>
</template>
 
<script setup>
import useMainStore from '@/stores/modules/main';

const mainStore = useMainStore()
 
</script>
 
<style lang="less" scoped>
.van-overlay {
  display: flex;
  justify-content: center;
  align-items: center;
  background: rgab(0,0,0,.3);
  height: 100vh;
  z-index: 999;
  .img {
    padding: 10px;
    background-color: #fff;
    border-radius: 10px;
    img {
      width: 80px;
      height: 80px;
    }
  }
}
</style>