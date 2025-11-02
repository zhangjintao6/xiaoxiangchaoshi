<template>
  <div>
    <detail-section title="房屋设施" moreText="查看更多设施">
      <div class="content">
        <template v-for="(item, index) in facility.houseFacilitys" :key="index">
          <div class="item" v-if="facility.facilitySort?.includes(index)">
            <div class="title">
              <img :src="item.icon" alt="" class="title-img">
              {{ item.groupName }}
            </div>
            <div class="module">
              <template v-for="(value, index) in item.facilitys.slice(0, 4)" :key="index">
                <div class="module-item">
                  <van-icon name="passed" />
                  {{ value.name }}
                </div>
              </template>
            </div>
          </div>
        </template>
      </div>
    </detail-section>

  </div>
</template>
 
<script setup>
import DetailSection from "@/components/DetailSection/detail-section.vue";
defineProps({
  facility: {
    type: Object,
    default: () => ({})
  }
})
</script>
 
<style lang="less" scoped>
.content {
  background-color: #f7f9fb;
  padding: 20px 16px;
  .item {
    display: flex;
    align-items: center;
    &:nth-of-type(2),
    &:nth-of-type(3) {
      margin-top: 20px;
    }
    .title {
      display: flex;
      flex-direction: column;
      align-items: center;
      font-size: 12px;
      font-weight: 600;
      margin-right: 20px;
      .title-img {
        height: 20px;
        width: 20px;
        margin-bottom: 5px;
      }
    }
    .module {
      flex: 1;
      display: flex;
      flex-wrap: wrap;
      .module-item {
        width: 50%;
        font-size: 12px;
        margin-top: 7px;
        .van-icon {
          color: #17d2bc;
        }
      }
    }
  }
}
</style>