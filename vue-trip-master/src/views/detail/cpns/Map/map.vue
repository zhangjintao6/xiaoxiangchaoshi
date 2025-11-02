<template>
  <div class="map">
    <detail-section title="位置周边" moreText="查看更多周边信息">
      <div ref="mapRef" class="map-content"></div>
      <van-tabs v-model:active="active" type="card" class="tabs">
        <van-tab :title="item.groupName" v-for="item in position.unitGeoPositions">
          <div class="tab-content">
            <div class="tab-content-item" v-for="(value, index) in item.geoPositionList" v-show="index < 3">
              <div>{{ value.title }}</div>
              <div class="description">{{ value.description }}</div>
            </div>
          </div>
        </van-tab>
      </van-tabs>
    </detail-section>
  </div>
</template>
 
<script setup>
import DetailSection from "@/components/DetailSection/detail-section.vue";
import { onMounted, ref } from "vue";

const props = defineProps({
  position: {
    type: Object,
    default: () => ({})
  }
})
const mapRef = ref()
onMounted(() => {
  // mounted后div才渲染完成
  let map = new BMapGL.Map(mapRef.value); // 创建地图实例
  const point = new BMapGL.Point(props.position.longitude, props.position.latitude); // 创建坐标
  map.centerAndZoom(point, 15); 
  const marker = new BMapGL.Marker(point);        // 创建标注   
  map.addOverlay(marker);                     // 将标注添加到地图中
})

const active = ref(0)
</script>
 
<style lang="less" scoped>
.map-content {
  width: 100%;
  height: 150px;
}
:deep(.tabs) {
  margin-top: 10px;
  .van-tabs__wrap {
    margin-bottom: 10px;
  }
  .van-tabs__nav {
    margin: 0;
    border-radius: 4px;
    border-color: #f5f6f7;
    .van-tab--active {
      color: #fff !important;
      background-color: var(--primary-color) !important;
      border-radius: 4px;
    }
    .van-tab--card {
      background-color: #f5f6f7;
      color: #999;
      border-color: #f5f6f7;
    }
  }
  .tab-content {
    display: flex;
    flex-direction: column;
    font-size: 12px;
    margin-top: 5px;
    .tab-content-item {
      width: 100%;
      display: flex;
      justify-content: space-between;
      margin-bottom: 8px;
      .description {
        color: #999;
      }
    }
  }
}
</style>