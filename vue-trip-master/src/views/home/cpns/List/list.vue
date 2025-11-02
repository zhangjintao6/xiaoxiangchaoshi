<template>
  <div class="list">
    <div class="title">热门精选</div>
    <div class="content">
      <template v-for="item in houseList">
        <card-long :item="item.data" v-if="item.data.image.height == 480"></card-long>
        <card-short :item="item.data" v-else></card-short>
      </template>
    </div>
  </div>
</template>
 
<script setup>
import useHomeStore from "@/stores/modules/home";
import CardShort from './cpns/CardShort/card-short.vue'
import CardLong from './cpns/CardLong/card-long.vue'
import { storeToRefs } from "pinia";

const homeStore = useHomeStore()
const { houseList } = storeToRefs(homeStore)
 
</script>
 
<style lang="less" scoped>
.list {
  margin-bottom: 50px;
}
.title {
  height: 48px;
  line-height: 48px;
  width: 80px;
  font-size: 18px;
  margin-left: 16px;
  background: url('@/assets/img/home/title.png') no-repeat;
  background-size: 50% 50%;
  background-position: center;
}
.content {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
  padding: 0 10px;
  background: no-repeat;
  background-color: #f5f7f9;
  background-image: linear-gradient(180deg,#fff,#f5f7f9);
  background-size: 100% 100px;
  :deep(.card) {
    width: calc(50% - 10px);
    border-radius: 10px;
    overflow: hidden;
    margin-bottom: 10px;
    background-color: #fff;
    position: relative;
    img {
      width: 100%;
    }
    .info {
      padding: 0 10px;
      .summary {
        font-size: 12px;
      }
    }
  }
}
</style>