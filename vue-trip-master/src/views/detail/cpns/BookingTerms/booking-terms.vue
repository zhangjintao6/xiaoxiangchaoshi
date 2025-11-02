<template>
  <div class="booking-terms">
    <detail-section title="预订须知">
      <div class="terms-list">
        <div class="terms-list-item" v-for="term in info.orderRules">
          <div class="title">{{ term.title }}</div>
          <div>{{ term.introduction }}</div>
        </div>
        <div class="terms-list-item">
          <div class="title">{{ info.checkInRules[0].title }}</div>
          <div class="content">
            <div class="content-item" v-for="item in info.checkInRules[0].items">
              <van-icon name="passed" color="#17d2bc"/>
              {{ item.introduction }}
            </div>
          </div>
        </div>
      </div>
    </detail-section>
  </div>
</template>
 
<script setup>
import DetailSection from "@/components/DetailSection/detail-section.vue";

defineProps({
  info: {
    type: Object,
    default: () => ({})
  }
})
</script>
 
<style lang="less" scoped>
.terms-list {
  display: flex;
  flex-direction: column;
  font-size: 12px;
  .terms-list-item {
    display: flex;
    margin-bottom: 20px;
    .title {
      color: #999;
      margin-right: 15px;
    }
    .content {
      display: flex;
      flex-wrap: wrap;
      flex: 1;
      .content-item {
        margin-bottom: 5px;
        .van-icon {
          margin-right: 5px;
        }
        display: flex;
        width: 50%;
      }
    }
  }
}
</style>