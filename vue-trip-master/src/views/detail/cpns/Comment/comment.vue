<template>
  <div>
    <detail-section title="房客点评" :moreText="`全部${info.totalCount}条`">
      <div class="overall">
        <div class="rate">
          <div class="total-point">{{ info.overall }}</div>
          <div>
            <div>{{ info.scoreTitle }}</div>
            <div>{{ info.totalCount }}条</div>
          </div>
        </div>
        <div class="detail">
          <div class="detail-item" v-for="item in info.subScoresFocus">
            {{ item.text.substring(0, 4) }}
            <span class="point">{{ item.focusText }}</span>
          </div>
        </div>
      </div>
      <div class="tags">
        <div class="tag" v-for="(item, index) in info.commentTagVo" v-show="index < 3">
          {{ item.text }}
        </div>
      </div>
      <div class="comment-detail">
        <div class="guest-info">
          <img :src="info.comment?.userAvatars" alt="">
          <div class="guest-detail-info">
            <div class="name">{{ info.comment?.userName }}</div>
            <div>
              <span>{{ info.comment?.checkInDate }}入住</span>
            </div>
          </div>
        </div>
        <div class="comment-content">
          <div class="comment-tags">
            <div v-for="item in info.comment?.scoreTags">{{ item.title }}</div>
          </div>
          <div class="van-multi-ellipsis--l2">
            {{ info.comment?.commentDetail }}
          </div>
          <div class="comment-img">
            <img :src="item.url" alt="" v-for="item in info.comment?.pictureList">
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
.overall {
  margin: 16px 0;
  padding: 10px;
  border-radius: 6px;
  background-color: #f8fafc;
  display: flex;
  justify-content: space-between;
  .rate {
    display: flex;
    align-items: center;
    .total-point {
      font-size: 48px;
      font-weight: 700;
      margin-right: 4px;
    }
  }
  .detail {
    display: flex;
    flex: 1;
    flex-wrap: wrap;
    justify-content: flex-end;
    align-items: center;
    .detail-item {
      color: #999;
      font-size: 12px;
      .point {
        color: #333;
        font-weight: 600;
      }
    }
    :nth-child(odd) {
      margin-right: 5px;
    }
  }
}
.tags {
  display: flex;
  justify-content: space-around;
  :not(:last-child) {
    margin-right: 8px;
  }
  .tag {
    flex: 1;
    background-color: #f5f5f5;
    color: #666;
    padding: 8px 0;
    text-align: center;
    border-radius: 6px;
  }
}
.comment-detail {
  margin: 16px 0 0;
  padding: 10px;
  border-radius: 6px;
  background-color: #f8fafc;
  .guest-info {
    display: flex;
    img {
      width: 32px;
      height: 32px;
      border-radius: 50%;
      margin-right: 4px;
    }
    .guest-detail-info {
      display: flex;
      flex-direction: column;
      justify-content: center;
      font-size: 12px;
      color: #999;
      .name {
        color: #333;
        font-weight: 600;
      }
    }
  }
  .comment-content {
    display: flex;
    flex-direction: column;
    font-size: 12px;
    margin-top: 20px;
    .comment-tags {
      display: flex;
      margin-bottom: 8px;
      :not(:last-child) {
        margin-right: 8px;
      }
    }
    .comment-img {
      margin: 10px 0;
      display: flex;
      :not(:last-child) {
        margin-right: 4px;
      }
      img {
        width: 75px;
        height: 75px;
        border-radius: 4px;
      }
    }
  }
}
</style>