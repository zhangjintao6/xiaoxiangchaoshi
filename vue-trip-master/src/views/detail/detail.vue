<template>
  <div class="detail" ref="detailRef">
    <tab-control :titles="titles" v-if="showTabControl" @tabItemClick="tabItemClick" class="tab" ref="tabControlRef"/>
    <van-nav-bar title="房屋详情" left-arrow @click-left="$router.back()" ref="navbarRef"/>
    <div v-if="infos.mainPart" v-memo="[infos.mainPart]">
      <!-- 轮播图 -->
      <Swiper :picture="infos.mainPart.topModule.housePicture.housePics" :ref="getSectionRef" name="概览"></Swiper>
      <div class="banner" :style="{backgroundImage: `url(${infos.pricePart?.priceModule?.product?.promoTotalPriceIcon?.icon})`}">{{ infos.pricePart?.priceModule?.product?.promoTotalPriceIcon?.title }}</div>
      <!-- 房屋信息 -->
      <Info :topInfos="infos?.mainPart?.topModule"></Info>
      <!-- 房客点评 -->
      <Comment :info="infos.mainPart.dynamicModule.commentModule" :ref="getSectionRef" name="点评"></Comment>
      <!-- 房屋设施 -->
      <Facility :facility="infos.mainPart.dynamicModule.facilityModule.houseFacility" :ref="getSectionRef" name="设施"></Facility>
      <!-- 房东介绍 -->
      <landlord-info :info="infos.mainPart.dynamicModule.landlordModule" :ref="getSectionRef" name="房东"></landlord-info>
      <!-- 预订须知 -->
      <booking-terms :info="infos.mainPart.dynamicModule.rulesModule"></booking-terms>
      <!-- 位置周边 -->
      <Map :position="infos.mainPart.dynamicModule.positionModule" :ref="getSectionRef" name="周边"></Map>
    </div>
    <!-- 价格说明 -->
    <div class="price-introduce">
      <div class="title">价格说明</div>
      <van-text-ellipsis 
        class="content"
        rows="3"
        expand-text="展开"
        :content="infos.mainPart?.introductionModule?.introduction"
      ></van-text-ellipsis>
    </div>
    <!-- 底部信息 -->
    <div class="bottom-info">
      <img src="https://pic.tujia.com/upload/festatic/crn/tq-ensure-icon.png" alt="">
      <span>查看证照信息</span>
      <span>民宿预订频道由途家提供服务</span>
    </div>
    <Bottom :info="infos.pricePart?.priceModule?.product"></Bottom>
  </div>
</template>
 
<script setup>
import { useRoute } from "vue-router";
import useDetailStore from "@/stores/modules/detail";
import Swiper from "./cpns/Swiper/swiper.vue";
import Info from "./cpns/Info/info.vue"
import Facility from "./cpns/Facility/facility.vue"
import LandlordInfo from './cpns/LandlordInfo/landlord-info.vue'
import Comment from './cpns/Comment/comment.vue'
import Bottom from './cpns/Bottom/bottom.vue'
import BookingTerms from './cpns/BookingTerms/booking-terms.vue'
import Map from './cpns/Map/map.vue'
import TabControl from '@/components/TabControl/tab-control.vue'
import useScroll from "@/hooks/useScroll";
import { storeToRefs } from "pinia";
import { computed, ref, watch } from "vue";

const route = useRoute()
const { id } = route.params
const detailStore = useDetailStore() 
detailStore.fetchGetHouseDetail(id)
const { infos } = storeToRefs(detailStore)

// 控制tabcontrol
const detailRef = ref()
const { scrollTop } = useScroll(detailRef)
const showTabControl = computed(() => { return scrollTop.value >= 250 })

let sectionEls = ref({})
const getSectionRef = (value) => {
  if(!value) return
  const name = value.$el.getAttribute('name')
  sectionEls.value[name] = value.$el
}
const titles = computed(() => { return Object.keys(sectionEls.value) })

// 点击标签进行跳转时，取消滚动索引功能
let isClick = false
let currentDistance = -1

const tabItemClick = (index) => {
  const key = Object.keys(sectionEls.value)[index]
  const el = sectionEls.value[key]
  let distance = el.offsetTop
  if(index !== 0) distance = distance - 44
  detailRef.value.scrollTo({
    top: distance,
    behavior: 'smooth'
  })
  isClick= true
  currentDistance = distance
}


// 页面滚动匹配tab索引
const tabControlRef = ref()
watch(scrollTop, (newVal) => {
  // 点击tab进行切换时，滚动索引会导致tab出现跳动的问题
  // (比如从1点击跳转到3时，2的标签也会被激活一下)
  // 当滚动距离匹配到当前模块距离时，恢复滚动索引
  if(newVal == currentDistance) isClick = false
  if(isClick) return

  const els = Object.values(sectionEls.value)
  const values = els.map(item => item.offsetTop)
  
  // 最后一个元素的高度匹配不到，直接赋值
  let index = values.length - 1
  for(let i=0; i< values.length; i++) {
    if(newVal < values[i]-44) {
      index = i - 1
      break
    }
  }
  if(tabControlRef.value) tabControlRef.value.currentIndex = index
})
</script>
 
<style lang="less" scoped>
.detail {
  background-color: #f1f3f5;
  height: 100vh;
  overflow-y: auto;
}
.tab {
  position: fixed;
  top: 0;
  left: 0;
  z-index: 999;
}
.banner {
  width: 100%;
  height: 30px;
  line-height: 30px;
  color: #fff;
  font-size: 16px;
  font-weight: 600;
  text-align: center;
}
:deep(.van-nav-bar) {
  .van-nav-bar__arrow {
    font-size: 20px;
  }
  .van-nav-bar__title {
    font-weight: 400;
  }
}
.price-introduce {
  padding: 16px;
  background-color: #fff;
  .title {
    font-weight: 600;
    margin-bottom: 5px;
  }
  .content {
    font-size: 12px;
    color: #666;
  }
}
.bottom-info {
  padding: 40px 0 100px;
  display: flex;
  flex-direction: column;
  align-items: center;
  font-size: 12px;
  color: #7688a7;
  img {
    width: 123px;
    height: 17px;
    margin-bottom: 10px;
  }
  span {
    margin: 4px;
  }
}
</style>