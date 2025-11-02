<template>
  <div class="search-box">
    <!-- 关键字搜索 -->
    <div class="input border-bottom">
      <van-field v-model="keyword" placeholder="关键字/位置/民宿" style="padding: 0;"></van-field>
    </div>
    <!-- 热门标签 -->
    <div class="hot-suggests">
      <template v-for="item in hotSuggests">
        <div class="hot-suggests-item">
          {{ item.tagText.text }}
        </div>
      </template>
    </div>
    <!-- 搜索按钮 -->
    <div class="search-button" @click="$router.push('/search')">开始搜索</div>


    </div>
  </template>

<script setup>
import { ref } from "vue";
import { storeToRefs } from "pinia";
import { useRouter } from "vue-router";
import useHomeStore from "@/stores/modules/home";

const homeStore = useHomeStore()
const router = useRouter()

/** 关键字搜索 */
const keyword = ref("")

/** 热门建议 */
const { hotSuggests } = storeToRefs(homeStore)

</script>

<style lang="less" scoped>
.search-box {
  padding-bottom: 20px;
  margin-bottom: 20px;
  box-shadow: 0px 20px 20px -5px rgba(0, 0, 0, 0.1);
}
.input {
  height: 30px;
  line-height: 30px;
  color: #999;
  padding: 5px 0;
  margin: 0 20px;
}
.hot-suggests {
  display: flex;
  flex-wrap: wrap;
  padding: 5px 0;
  margin: 0 20px;
  .hot-suggests-item {
    font-size: 12px;
    padding: 4px 8px;
    margin: 2px;
    color: #3f4954;
    background-color: #f1f3f5;
    border-radius: 10px;
  }
}
.search-button {
  border-radius: 20px;
  width: 320px;
  margin: 6px auto;
  text-align: center;
  height: 38px;
  line-height: 40px;
  background-image: linear-gradient(90deg,#fa8c1d,#fcaf3f);
  color: #fff;
  font-size: 18px;
  font-weight: 500;
}

</style>
