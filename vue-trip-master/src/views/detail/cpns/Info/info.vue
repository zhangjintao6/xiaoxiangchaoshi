<template>
  <div class="info">
    <div class="name">{{ topInfos.houseName }}</div>
    <div class="tags">
      <div 
        class="tags-item" 
        v-for="item in topInfos.houseTags"
        :style="{ color:item.tagText.color,background:item.tagText.background.color }"
      >
        {{ item.tagText.text }}
      </div>
    </div>
    <div class="comment">
      <div class="comment-left">
        <span>
         <span style="font-size: 18px;">{{ topInfos.commentBrief?.overall }}</span>分
        </span>
        <span style="color: #999;">
          {{ topInfos.commentBrief?.totalCount }}条
          <van-icon name="arrow" style="position: relative; left: -4px;"></van-icon>
        </span>
        <div class="van-multi-ellipsis--l2" style="margin-top: 5px;">"{{ topInfos.commentBrief?.commentBrief }}"</div>
      </div>
      <div class="comment-right">
        <div class="comment-right-infos">
          <div class="van-ellipsis">{{ topInfos.nearByPosition?.address }}</div>
          <div style="font-size: 12px;margin-top: 5px;" class="van-multi-ellipsis--l2">{{ topInfos.nearByPosition?.nearByPosition[0].title }}</div>
        </div>
        <div class="comment-right-icon">
          <div class="icon">
            <van-icon name="location" />
          </div>
          地图/周边
        </div>
      </div>
    </div>
  </div>
</template>
 
<script setup>
defineProps({
  topInfos: {
    type: Object, 
    default: () => ({})
  }
})
</script>
 
<style lang="less" scoped>
.info {
  padding: 12px 16px;
  background-color: #fff;
  .name {
    font-weight: 600;
    font-size: 16px;
    margin-bottom: 8px;
  }
  .tags {
    display: flex;
    .tags-item {
      padding: 2px 4px;
      margin: 0 4px 12px 0;
    }
  }
  .comment {
    display: flex;
    justify-content: space-between;
    .comment-left {
      background-color: #f7f8fc;
      border-radius: 4px;
      width: 26%;
      padding: 8px;
      font-size: 12px;
    }
    .comment-right {
      background-color: #f7f8fc;
      border-radius: 4px;
      width: 62%;
      padding: 8px;
      background-image: url('@/assets/img/detail/map_bg.png');
      background-size: 100% 100%;
      display: flex;
      justify-content: space-between;
      .comment-right-infos {
        width: 70%;
        margin-right: 5px;
      }
      .comment-right-icon {
        color: #7688a7;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        font-size: 12px;
        .icon {
          border-radius: 50%;
          background-color: #7688a7;
          width: 24px;
          height: 24px;
          color: #fff;
          display: flex;
          justify-content: center;
          align-items: center;
          font-size: 16px;
          margin-bottom: 5px;
        }
      }
    }
  }
}
</style>