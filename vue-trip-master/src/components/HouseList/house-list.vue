<template>
  <div class="favor-list" v-if="data">
    <van-pull-refresh v-model="loading" @refresh="onRefresh">  
      <template v-for="item in data.items">
        <div class="item">
          <div class="img">
            <img :src="item.defaultPicture" alt="" style="width: 100%;">
            <div class="right-info">
              <img :src="item.landlordHeadStyle.head" alt="" class="landlord">
              <van-icon name="like" style="color:#ff6666"/>
              <div class="comment">
                <van-icon name="comment" />
                {{ item.commentBrief.totalCount }}
              </div>
            </div>
            <div class="bottom-info">
              <div class="score">{{ item.commentBrief.overall }}分</div>
              <div style="color: #666;">{{ item.commentBrief.scoreTitle }}</div>
            </div>
          </div>
          <div class="unit-summeries">
            <template v-for="summery in item.unitSummeries">
              <div class="unit-summeries-item">{{ summery.text }}</div>
            </template>
          </div>
          <div class="name">{{ item.unitName }}</div>
          <div class="house-tags">
            <template v-for="tag in item.houseTags">
              <div class="house-tags-item">
                {{ tag.text }}
              </div>
            </template>
          </div>
          <div class="price">
            <div class="final-price">￥{{ item.finalPrice }}</div>
            <div class="product-price">￥{{ item.productPrice }}</div>
            <div class="price-tip-badge" v-if="item.priceTipBadge">
              <van-icon name="volume" />
              {{ item.priceTipBadge?.text }}
            </div>
          </div>
        </div>
      </template>
    </van-pull-refresh>
  </div>
  <div class="empty" v-else>
    <img src="@/assets/img/favor/empty_favorite.png" alt="">
    <div class="title">暂无收藏</div>
    <div class="desc">点击<van-icon name="like" />即可收藏对应的房屋</div>
    <van-button type="primary" round @click="$router.push('/home')">随便去逛逛</van-button>
  </div>
</template>
 
<script setup>
import { ref } from 'vue'

const props = defineProps({
  data: {
    type: Object
  }
})

// 下拉刷新
const loading = ref(false);
const onRefresh = () => {
  setTimeout(() => {
    loading.value = false;
  }, 1000);
};
</script>
 
<style lang="less" scoped>
.favor-list {
  flex: 1;
  overflow-y: auto;
  .item {
    border-radius: 10px;
    padding: 20px;
    .img {
      border-radius: 5px;
      width: 100%;
      overflow: hidden;
      position: relative;
    }
    .right-info {
      position: absolute;
      right: 10px;
      top: 10px;
      display: flex;
      flex-direction: column;
      align-items: center;
      .landlord {
        width: 40px;
        height: 40px;
        border-radius: 50%;
        margin-bottom: 20px;
      }
      .van-icon-like {
        font-size: 24px;
        margin-bottom: 20px;
      }
      .comment {
        color: #fff;
        display: flex;
        flex-direction: column;
        align-items: center;
        .van-icon-comment {
          font-size: 24px;
        }
      }
    }
    .bottom-info {
      position: absolute;
      left: 10px;
      bottom: 10px;
      background-color: #fff;
      padding: 2px 8px;
      border-radius: 10px;
      display: flex;
      font-size: 12px;
      .score {
        border-right: 1px solid #999;
        padding-right: 5px;
        margin-right: 5px;
        font-weight: 600;
      }
    }
    .unit-summeries {
      display: flex;
      font-size: 12px;
      color: #666;
      margin-top: 8px;
      .unit-summeries-item:nth-child(1),.unit-summeries-item:nth-child(2){
        &::after {
          content: '·';
          margin: 0 2px;
        }
      }
    }
    .name {
      color: #333;
      font-size: 18px;
      margin-top: 8px;
    }
    .house-tags {
      display: flex;
      margin-top: 8px;
      .house-tags-item {
        font-size: 12px;
        color: #ff6666;
        background-color: rgba(255, 102, 102, 0.1);
        padding: 0 4px;
        margin-right: 5px; 
      }
    }
    .price {
      display: flex;
      margin-top: 8px;
      align-items: center;
      .final-price{
        font-size: 20px;
        color: #ff9645;
      }
      .product-price {
        text-decoration: line-through;
        color: #999;
        font-size: 12px;
        margin-left: 5px;
      }
      .price-tip-badge {
        color: #fff;
        font-size: 12px;
        margin-left: 5px;
        padding: 1px 5px;
        border-radius: 8px;
        background-image: linear-gradient(-270deg, rgb(251, 74, 74) 1%, rgb(255, 102, 102) 99%);
      }
    }
  }
}
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