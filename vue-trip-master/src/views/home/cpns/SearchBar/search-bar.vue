<template>
  <div class="search-bar bottom-shadow">
    <search-field></search-field>
  </div>
</template>

<script setup>
import SearchField from '@/components/SearchField/search-field.vue';
</script>

<style lang="less" scoped>
.search-bar {
  position: fixed;
  top: 0;
  left: 0;
  z-index: 999;
  width: 100%;
  background-color: #fff;
  .box {
    margin: 15px;
  }
}
</style>
