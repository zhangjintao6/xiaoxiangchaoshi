<template>
  <div class="bottom">
    <div class="chat">
      <svg-icon iconClass="xiaoxi"></svg-icon>
      聊天
    </div>
    <div class="price">
      <div class="price-top">
        <span class="final-price">
          ￥<span style="font-size: 20px;font-weight: 500;">{{ info.finalPrice }}</span>
        </span>/晚
        <span style="text-decoration: line-through;">￥{{ info.productPrice }}</span>
      </div>
      <div class="price-bottom">
        <div class="reduce">{{ info.promoTotalPriceIcon?.title }}</div>
      </div>  
    </div>
    <div class="button">优选价预定</div>
  </div>
</template>
 
<script setup>
defineProps({
  info: {
    type: Object,
    default: () => ({})
  }
})
</script>
 
<style lang="less" scoped>
.bottom {
  height: 60px;
  width: 100%;
  display: flex;
  align-items: center;
  position: fixed;
  bottom: 0;
  background-color: #fff;
}
.chat {
  padding: 0 15px;
  display: flex;
  align-content: center;
  flex-direction: column;
  position: relative;
  .svg-icon {
    font-size: 20px;
    margin: 0 auto 5px;
  }
  &:after {
    content: '';
    width: 1px;
    height: 40px;
    background-color: #eee;
    position: absolute;
    right: 0;
  }
}
.price {
  padding: 10px;
  flex: 1;
  .price-top {
    color: #999;
    font-variant-numeric: proportional-nums;
    .final-price {
      color: #ff6e16;
    }
  }
  .price-bottom {
    color: #fa5741;
    .reduce {
      display: inline-block;
      background-color: rgba(250, 87, 65, 0.08);
      padding: 4px;
      border-radius: 6px;
      font-size: 12px;
    }
  }
}
.button {
  width: 130px;
  height: 60px;
  line-height: 60px;
  text-align: center;
  color: #fff;
  font-size: 18px;
  background: linear-gradient(90deg,#fa8c1d,#fcaf3f);
}
</style>