<template>
  <div class="select border-bottom">
    <div class="price">
      <van-field 
        v-model="price" 
        placeholder="价格不限" 
        readonly 
        @click-input="handlePriceClick"
      >
        <template #right-icon>
          <van-icon name="clear" @click="price = ''" v-show="!!price" />
        </template>
      </van-field>
    </div>
    <div class="num">
      <van-field 
        v-model="num" 
        placeholder="人数不限" 
        readonly 
        @click-input="showNumSel = true"
      >
        <template #right-icon>
          <van-icon name="clear" @click="num = ''" v-show="!!num" />
        </template>
      </van-field>
    </div>
    <!-- 价格选择器 -->
    <van-popup 
      v-model:show="showPriceSel" 
      position="bottom" 
      closeable 
      round 
      style="background-color: #f1f3f5;"
    >
      <div style="font-weight: 600;font-size: 18px;padding: 16px;">价格</div>
      <div class="price-popup-content">
        <div class="price-range">
          <div style="margin-bottom: 30px;">价格区间{{ priceRangeText }}</div>
          <van-slider v-model="priceRange" range bar-height="4" :min="0" :max="2000" @change="onSliderChange" />
          <div class="range-button">
            <template v-for="(item, index) in priceRangeList">
              <div class="range-button-item" :class="index === selectIndex ? 'active' : ''" @click="selectItem(index)">
                {{ item.text }}
              </div>
            </template>
          </div>
        </div>
        <div class="bottom-button">
          <van-button round style="width: 28%;" @click="restSel()">清空</van-button>
          <van-button type="primary" round style="width: 70%;" @click="priceConfirm()">确认</van-button>
        </div>
      </div>
    </van-popup>
    <!-- 人数选择器 -->
    <van-popup v-model:show="showNumSel" position="bottom">
      <van-picker title="选择入住人数" :columns="numColumns" @confirm="onNumConfirm" @cancel="showNumSel = false" />
    </van-popup>
  </div>
</template>
 
<script setup>
import { ref } from 'vue'

/** 价格选择 */
const price = ref("");
const priceRangeText = ref(`￥0 - 不限`);
const showPriceSel = ref(false)
// 打开弹出层
const handlePriceClick = () => {
  showPriceSel.value = true;
  restSel()
}
// 滑块范围
const priceRange = ref([0, 2000])
const onSliderChange = (value) => {
  if (value[0] == 2000) {
    priceRangeText.value = '￥2000以上'
  } else if (value[1] < 100) {
    priceRangeText.value = '￥100以下'
  }
  else {
    priceRangeText.value = `￥${value[0]}-￥${value[1]}`
  }
}
const priceRangeList = [
  {
    text: '￥100以下',
    value: [0, 100]
  },
  {
    text: '￥100-200',
    value: [100, 200]
  },
  {
    text: '￥200-300',
    value: [200, 300]
  },
  {
    text: '￥300-400',
    value: [300, 400]
  },
  {
    text: '￥400-600',
    value: [400, 600]
  },
  {
    text: '￥600-1000',
    value: [100, 200]
  },
  {
    text: '￥1000-2000',
    value: [1000, 2000]
  },
  {
    text: '￥2000以上',
    value: [2000, 2000]
  }

]
// 按钮列表选择
const selectIndex = ref("")
const selectItem = (index) => {
  selectIndex.value = index;
  priceRangeText.value = priceRangeList[index].text
  priceRange.value = priceRangeList[index].value
}
// 重置选择
const restSel = () => {
  priceRange.value = [0, 2000]
  selectIndex.value = ""
  priceRangeText.value = "￥0-不限"
}
// 确认
const priceConfirm = () => {
  if (priceRangeText.value == "￥0-不限") {
    price.value = '价格不限'
  } else {
    price.value = priceRangeText.value
  }
  showPriceSel.value = false
}

/** 人数选择 */
const num = ref("");
const showNumSel = ref(false)
// 选项列表
const list = []
for (let i = 1; i < 10; i++) {
  list.push({
    text: `${i}人`,
    value: i
  })
}
const numColumns = [
  ...list,
  {
    text: '10人+',
    value: 10
  },
  {
    text: '不限人数',
    value: 0
  }
]
// 确定
const onNumConfirm = ({ selectedOptions }) => {
  num.value = selectedOptions[0].text
  console.log(!!num);
  showNumSel.value = false
}

</script>
 
<style lang="less" scoped>
.select {
  display: flex;
  justify-content: space-between;
  height: 30px;
  line-height: 30px;
  color: #999;
  padding: 5px 0;
  margin: 0 20px;
  :deep(.van-field) {
    padding: inherit;
    .van-field__value {
      width: 100%;
    }
  }
  .price {
    width: 60%;
    display: flex;
    align-items: center;
  }
  .num {
    width: 25%;
    display: flex;
    align-items: center;
  }
  .price-popup-content {
    padding: 0 16px 44px;
    .price-range {
      border-radius: 5px;
      background-color: #fff;
      padding: 15px 12px;
      margin-bottom: 40px;
      .van-slider {
        margin: 20px auto;
        width: 95%;
      }
      .range-button {
        display: flex;
        flex-wrap: wrap;
        .range-button-item {
          padding: 0px 8px;
          margin: 5px;
          border-radius: 15px;
          background-color: #f8f8f8;
          border: 1px solid #f8f8f8;
        }
        .active {
          color: var(--primary-color);
          border-color: var(--primary-color);
          background-color: #fff7f0;
        }
      }
    }
    .bottom-button {
      display: flex;
      justify-content: space-between;
    }
  }
}
</style>