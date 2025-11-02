<template>
  <div>
    <van-calendar v-model:show="showDateSel" type="range" @confirm="onDateConfirm" />
  </div>
</template>
 
<script setup>
import { storeToRefs } from 'pinia';
import useMainStore from '@/stores/modules/main';
import { formatMonthDay, dayDiff } from "@/utils/handleDate.js";
import { ref } from 'vue'

const mainStore = useMainStore()
/** 日期选择 */
const { showDateSel, startDate, endDate } = storeToRefs(mainStore);
// 共几晚
const totalDay = ref(1);
// 日历选择日期
const onDateConfirm = (values) => {
  const [start, end] = values;
  showDateSel.value = false;
  startDate.value = formatMonthDay(start);
  endDate.value = formatMonthDay(end);
  totalDay.value = dayDiff(start, end);
};
</script>
 
<style lang="less" scoped>
</style>