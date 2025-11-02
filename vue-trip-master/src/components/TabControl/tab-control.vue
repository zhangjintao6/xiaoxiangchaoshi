<template>
  <div class="tab-control">
    <template v-for="(item, index) in titles">
      <div class="tab-control-item" :class="{ active: index == currentIndex }" @click="itemClick(index)">
        <span>{{ item }}</span>
      </div>
    </template>
  </div>
</template>
 
<script setup>
import { ref, defineEmits } from 'vue';

defineProps({
  titles: {
    type: Array,
    default: () => []
  },

})
const currentIndex = ref(0)

const emit = defineEmits(['tabItemClick'])

const itemClick = (index) => {
  currentIndex.value = index
  emit('tabItemClick', index)
}

defineExpose({
  currentIndex
})
</script>
 
<style lang="less" scoped>
.tab-control {
  display: flex;
  height: 44px;
  width: 100%;
  line-height: 44px;
  text-align: center;
  background-color: #fff;
}
.tab-control-item {
  flex: 1;
  &.active {
    color: var(--primary-color);
    font-weight: 600;
    span {
      border-bottom: 2px solid var(--primary-color);
      padding: 8px;
    }
  }
}
</style>