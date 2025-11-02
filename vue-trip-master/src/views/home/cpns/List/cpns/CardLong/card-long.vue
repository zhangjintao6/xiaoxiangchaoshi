<template>
  <div class="card" @click="$router.push(`/detail/${item.houseId}`)">
    <img :src="item.image?.url" alt="">
    <div class="info">
      <div class="summary">
        {{ item.summaryText }}
      </div>
      <div class="van-multi-ellipsis--l2">
        {{ item.houseName }}
      </div>
      <div class="price">
        <van-rate 
          v-model="item.commentScore" 
          size="8" 
          color="#fff" 
          readonly 
          allow-half 
        />
        ￥{{ item.productPrice }}
      </div>
    </div>
  </div>
</template>
 
<script setup>
const props = defineProps({
  item: {
    type: Object,
    default: () => ({})
  }
})
props.item.commentScore = Number(props.item.commentScore)
</script>
 
<style lang="less" scoped>
.card {
  img {
    width: 100%;
  }

  .info {
    color: #fff;
    position: absolute;
    bottom: 10px;

    .price {
      font-size: 16px;
      display: flex;
      justify-content: space-between;
      align-items: center;
    }
  }
}
</style>