<template>
  <div class="landlord-list">
  </div>
  <div class="empty">
    <img src="@/assets/img/favor/empty_favorite.png" alt="">
    <div class="title">暂无收藏</div>
    <div class="desc">点击<van-icon name="like" />即可收藏对应的房东</div>
    <van-button type="primary" round @click="$router.push('/home')">随便去逛逛</van-button>
  </div>
</template>
 
<script setup>
 
</script>
 
<style lang="less" scoped>
.empty {
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 20px;
  img {
    width: 320px;
  }
  .title {
    font-size: 18px;
    color: #333;
    margin-top: 20px;
  }
  .desc{
    font-size: 14px;
    color: #666;
    margin-top: 10px;
  }
  .van-button {
    margin-top: 20px;
    padding: 0 40px;
    font-size: 16px;
  }
}
</style>
