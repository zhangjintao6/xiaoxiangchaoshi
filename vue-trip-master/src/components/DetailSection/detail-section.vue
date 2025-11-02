<template>
  <div class="detail-section">
    <div class="header" v-show="title">
      {{ title }}
    </div>
    <div class="content">
      <slot>
        默认内容
      </slot> 
    </div>
    <div class="footer" v-show="moreText">
      {{ moreText }}
      <van-icon name="arrow" />
    </div>
  </div>
</template>
 
<script setup>
defineProps({
  title: {
    type: String,
    default: ''
  },
  moreText: {
    type: String,
    default: ''
  }
})
</script>
 
<style lang="less" scoped>
.detail-section {
  margin: 10px 0;
  background-color: #fff;
}
.header {
  padding: 14px;
  font-size: 20px;
  font-weight: 600;
  border-bottom: 1px solid #eee;
}
.content {
  margin: 15px 16px 0;
}
.footer {
  font-weight: 600;
  padding: 15px 16px;
  text-align: right;
  color: var(--primary-color);
}
</style>