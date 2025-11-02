<template>
  <div class="date-range border-bottom">
    <div class="date" @click="showDateSel = true">
      <div class="title">入住</div>
      <div>{{ startDate }}</div>
    </div>
    <div class="total">共{{ totalDay }}晚</div>
    <div class="date" @click="showDateSel = true">
      <div class="title" style="padding-left: 20px;">离店</div>
      <div style="padding-left: 20px;">{{ endDate }}</div>
    </div>
    <!-- 日期选择器 -->
    <date-picker></date-picker>
  </div>
</template>
 
<script setup>
import useMainStore from "@/stores/modules/main";
import { ref } from 'vue'
import { storeToRefs } from "pinia";
import DatePicker from '@/components/DatePicker/date-picker.vue';

const mainStore = useMainStore()
const { showDateSel, startDate, endDate } = storeToRefs(mainStore);
// 共几晚
const totalDay = ref(1);
 
</script>
 
<style lang="less" scoped>
.date-range {
  display: flex;
  height: 36px;
  justify-content: space-between;
  padding: 5px 0;
  margin: 0 20px;
  .date {
    width: 30%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    font-size: 15px;
    .title {
      color: #999;
      font-size: 12px;
    }
  }
  .total {
    text-align: center;
    line-height: 36px;
    color: #666;
  }
}
</style>