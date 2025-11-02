<template>
  <div class="favor">
    <top-nav></top-nav>
    <!-- 房东列表 -->
    <landlord-list v-if="headerActive=='collect' && active=='landlord'"></landlord-list>
    <!-- 房屋列表 -->
    <house-list :data="dataList" v-else></house-list>
  </div>
</template>
<script setup>
import TopNav from './cpns/TopNav/top-nav.vue';
import HouseList from '@/components/HouseList/house-list.vue';
import LandlordList from './cpns/LandlordList/landlord-list.vue';
import useFavorStore from '@/stores/modules/favor';
import { storeToRefs } from 'pinia';
import { watch } from 'vue'

const favorStore = useFavorStore()
const { dataList, headerActive, active } = storeToRefs(favorStore)

watch( 
  [ headerActive, active ],
  () => {
    // 我的收藏-房屋
    if(headerActive.value == 'collect' && active.value == 'house') {
      favorStore.fetchGetFavorList()
    } else if(headerActive.value == 'history') {
      favorStore.fetchGetHistoryList()
    }
  },
  { immediate: true }
)
 
</script>
<style lang="less" scoped>
.favor {
  display: flex;
  flex-direction: column;
  height: calc(100vh - 50px);
}
</style>