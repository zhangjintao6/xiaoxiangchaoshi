<template>
  <div class="search-top">
    <div class="search-bar">
      <van-icon name="arrow-left" @click="$router.back()"></van-icon>
      <search-field size="mini"></search-field>
    </div>
    <van-dropdown-menu>
      <!-- 位置 -->
      <van-dropdown-item title="位置" ref="dropdownPositionMenu">
        <van-tree-select
          v-model:main-active-index="positionActiveIndex"
          :items="positionItems"
          @click-nav="positionSelIndex=null"
        >
          <template #content>
            <div 
              v-for="(item, index) in positionItems[positionActiveIndex]?.items" 
              class="position-content" 
              :class="positionSelIndex==index?'position-content_active':''"
              @click="positionSel(item, index)"
            >
              <div class="label">
                {{ item.label }}
              </div>
              <div class="percentageUser">
                {{ item.percentageUser }}
              </div>
            </div>
          </template>
        </van-tree-select>
        <div class="dropdown-menu-bottom">
          <van-button round>清空</van-button>
          <van-button type="primary" round @click="$refs.dropdownPositionMenu.toggle()">查看房屋</van-button>
        </div>
      </van-dropdown-item>
      <!-- 价格/人数 -->
      <van-dropdown-item title="价格/人数">
      </van-dropdown-item>
      <!-- 排序 -->
      <van-dropdown-item  v-model="sortValue" :options="sortList"></van-dropdown-item>
      <!-- 筛选 -->
      <van-dropdown-item title="筛选" ref="dropdownFilterMenu">
        <van-tree-select v-model:main-active-index="filterActiveIndex" :items="filterItems">
          <template #content>
            <div v-for="(item, index) in filterItems[filterActiveIndex]?.items" class="filter-content">
              <div class="filter-title">
                {{ filterItems[filterActiveIndex].text }}
              </div>
              <div class="filter-desc">
                {{ item.label }}
              </div>
              <div class="filter-item">
                <div 
                  class="filter-label" 
                  v-for="value in item.items" 
                  @click="filterSel(value)"
                  :class="filterSelIndex?.includes(value.value) ? 'filter-label_active' : ''"
                >
                  {{ value.label }}
                  <svg-icon iconClass="mark" v-show="filterSelIndex?.includes(value.value)"></svg-icon>
                </div>
              </div>
            </div>
          </template>
        </van-tree-select>
        <div class="dropdown-menu-bottom">
          <van-button round>清空</van-button>
          <van-button type="primary" round @click="$refs.dropdownFilterMenu.toggle()">查看房屋</van-button>
        </div>
      </van-dropdown-item>
    </van-dropdown-menu>
    <date-picker></date-picker>
  </div>
</template>

<script setup>
import DatePicker from '@/components/DatePicker/date-picker.vue';
import SearchField from '@/components/SearchField/search-field.vue';
import useSearchStore from '@/stores/modules/search';
import { ref, watch } from 'vue';
import { storeToRefs } from 'pinia';

const searchStore = useSearchStore()
const { searchTopData } = storeToRefs(searchStore)

/** 位置 */
// 左侧选中
const positionActiveIndex = ref(0);
let positionItems = ref([]);
// 右侧选中
let positionSelIndex = ref(null)
const positionSel = (item, index) => {
  positionSelIndex.value = index
}

/** 排序 */
const sortValue = ref("1")
const sortList = ref([])


/** 筛选 */
const filterActiveIndex = ref(0)
let filterItems = ref([])
// 右侧多选选中
let filterSelIndex = ref([])
const filterSel = (value) => {
  if(filterSelIndex.value.includes(value.value)) {
    filterSelIndex.value.splice(filterSelIndex.value.indexOf(value.value),1)
  } else {
    filterSelIndex.value.push(value.value)
  }
}

watch(searchTopData,
  (newVal) => {
    // 位置
    newVal[0].subGroups.forEach(item => {
      const list = {
        text: item.label,
        items: item.items.length!=0 ? item.items : item.subGroups
      }
      positionItems.value.push(list)
    })
    // 排序
    newVal[1].items.forEach(item => {
      const list = {
        text: item.label,
        value: item.value
      }
      sortList.value.push(list)
    })
    // 筛选
    newVal[2].subGroups.forEach(item => {
      const list = {
        text: item.label,
        items: item.items.length!=0 ? item.items : item.subGroups
      }
      filterItems.value.push(list)
    })
  }
)
</script>

<style lang="less" scoped>
.search-top {
  position: fixed;
  top: 0;
  left: 0;
  z-index: 999;
  width: 100%;
  background-color: #fff;
}
.search-bar {
  display: flex;
  align-items: center;
  height: 35px;
  padding: 15px;
  .van-icon {
    font-size: 24px;
    color: #999;
    margin-right: 10px;
  }
}
:deep(.van-dropdown-menu) {
  .van-dropdown-menu__bar {
    box-shadow: none;
    .van-dropdown-menu__title {
      font-size: 12px;
    }
  }
  .van-tree-select__content {
    flex: 3;
  }
  .position-content {
    font-size: 12px;
    height: 48px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    padding: 0 40px 0 20px;
    &_active {
      background-color: #fffcf5;
      .label {
        color: var(--primary-color)
      }
    }
    .percentageUser {
      font-size: 12px;
      color: #999;
    }
  }
  .filter-content {
    padding: 15px 15px 6px;
    .filter-title {
      font-size: 16px;
      color: var(--primary-color);
      font-weight: 600;
    }
    .filter-desc {
      font-size: 12px;
      color: #999;
      margin: 6px 0;
    }
    .filter-item {
      display: flex;
      flex-wrap: wrap;
      justify-content: space-between;
      .filter-label {
        width: 31%;
        margin-bottom: 5px;
        background-color: #f4f6f9;
        border: 1px solid #f4f6f9;
        border-radius: 4px;
        min-height: 40px;
        display: flex;
        justify-content: center;
        align-items: center;
        font-size: 12px;
        position: relative;
        overflow: hidden;
        &_active {
          border-color: var(--primary-color);
          color: var(--primary-color);
          background-color: rgba(255,150,69,0.08);
        }
        .svg-icon {
          position: absolute;
          right: 0;
          bottom: 0;
        }
      }
      &::after {
        content: '';
        width: 30%;
        min-height: 40px;
        border: 1px solid transparent;
      }
    }
  }
  .dropdown-menu-bottom {
    height: 40px;
    padding: 10px 20px;
    display: flex;
    justify-content: space-around;
    .van-button--default {
      width: 85px;
      margin: 0 10px;
    }
    .van-button--primary {
      flex: 1;
      margin: 0 10px;
    }
  }
}
</style>
