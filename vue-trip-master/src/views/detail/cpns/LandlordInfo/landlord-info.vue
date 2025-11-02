<template>
  <div>
    <detail-section title="房东介绍" moreText="查看房东主页">
      <div class="title"></div>
      <div class="info">
        <img :src="info.hotelLogo" class="avatar" />
        <div class="name-info">
          <div class="name van-multi-ellipsis--l2">{{ info.hotelName }}</div>
          <div class="tags">
            <div class="tags-item" v-for="item in info.hotelTags">{{ item.tagText.text }}</div>
          </div>
        </div>
        <div class="button">联系房东</div>
      </div>
      <div class="comment">

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
  .title {
    width: 100%;
    height: 30px;
    background-image: url("https://pic.tujia.com/upload/festatic/crn/v4landlord.png");
    background-size: 100% 100%;
    border-radius: 4px;
    margin-top: 12px;
  }
.info {
  padding: 16px 0;
  display: flex;
  align-items: center;
  font-size: 12px;
  .avatar {
    width: 54px;
    height: 54px;
    border-radius: 50%;
  }
  .name-info {
    flex: 1;
    margin: 0 8px;
    .name {
      font-size: 16px;
      font-weight: 600;
    }
    .tags {
      display: flex;
      color: #999;
      position: relative;
      margin-top: 4px;
      :not(:last-child) {
        &::after {
          content: '';
          border-right: 1px solid #c4c4c4;
          margin: 0 4px;
        }
      }
    }
  }
  .button {
    color: #fff;
    height: 24px;
    line-height: 24px;
    padding: 0 12px;
    border-radius: 4px;
    background: linear-gradient(90deg,#fa8c1d,#fcaf3f);
  }
}
.comment {
  display: flex;
  justify-content: space-between;
  // border-bottom: 1px solid #eee;
}
</style>