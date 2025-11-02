<template>
  <div class="card" @click="$router.push(`/detail/${item.houseId}`)">
    <img :src="item.image?.url" alt="">
    <div class="info">
      <div class="location">
        <van-icon name="location" />
        {{ item.location }}
      </div>
      <div class="van-multi-ellipsis--l2">
        {{ item.houseName }}
      </div>
      <div class="summary">
        {{ item.summaryText }}
      </div>
      <div class="house-price">
        <span class="final-price">￥{{ item.finalPrice }}</span>
        <span class="price">￥{{ item.productPrice }}</span>
        <span class="cut-price" v-if="item.priceTipBadge">
          <van-icon name="volume" />
          {{ item.priceTipBadge.text }}
        </span>
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
</script>
 
<style lang="less" scoped>
.card {
  .info {
    .location {
      font-size: 12px;
      margin: 8px 0 4px;
      color: #666;
    }
    .summary {
      color: #666;
    }
    .house-price {
      .final-price {
        color: #ff9645;
      }
      .price {
        text-decoration: line-through;
        margin-left: 2px;
        font-size: 12px;
        color: #999;
      }
      .cut-price {
        color: #fff;
        font-size: 12px;
        background-image: linear-gradient(270deg,#FF6666,#FF9F9F);
        border-radius: 8px;
        padding: 0 4px;
        margin-left: 2px;
        .van-icon {
          font-size: 10px;
        }
      }
    }
  }
}
</style>