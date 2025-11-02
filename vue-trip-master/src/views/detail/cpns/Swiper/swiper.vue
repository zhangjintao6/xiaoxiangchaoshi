<template>
  <div class="swiper">
    <van-swipe>
      <van-swipe-item v-for="item in picture">
        <img :src="item.url" alt="" class="swiper-img">
      </van-swipe-item>
      <template #indicator="{ active, total }">
        <div class="custom-indicator">
          {{ active + 1 }}/{{ total }}
        </div>
      </template>
    </van-swipe>
  </div>
</template>
 
<script setup>
defineProps({
  picture: {
    type: Array,
    default: () => []
  }
})
</script>
 
<style lang="less" scoped>
.swiper {
  width: 100%;
  height: 250px;
  position: relative;
  .van-swipe {
    height: 100%;
    .swiper-img {
      height: 100%;
    }
  }
  .custom-indicator {
    position: absolute;
    right: 10px;
    bottom: 10px;
    background-color: rgba(0,0,0,.8);
    border-radius: 8px;
    padding: 0 8px;
    color: #fff;
  }
}
</style>