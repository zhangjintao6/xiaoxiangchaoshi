import { defineStore } from "pinia";
import { _getHotSuggests, _getHomeCategories, _getHouseList } from '@/services'

const useHomeStore = defineStore("home",{
  state: () => ({
    hotSuggests: [],
    categories: [],
    houseList: [],
    currentPage: 1
  }),
  actions: {
    // 热门标签
    async fetchHotSuggeetsData() {
      const res = await _getHotSuggests()
      this.hotSuggests = res.data
    },
    // 分类
    async fetchCategories() {
      const res = await _getHomeCategories()
      this.categories = res.data
    },
    // 民宿列表
    async fetchHouseList() {
      const res = await _getHouseList(this.currentPage)
      this.houseList.push(...res.data)
      this.currentPage++
    }
  }
})

export default useHomeStore