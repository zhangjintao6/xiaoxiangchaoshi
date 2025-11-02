import { defineStore } from "pinia";
import { _getFavorList, _getHistoryList } from "@/services";

const useFavorStore = defineStore('favor', {
  state: () => ({
    dataList: [],
    headerActive: 'collect',
    active: 'house'
  }),
  actions: {
    // 收藏列表
    async fetchGetFavorList() {
      const res = await _getFavorList()
      this.dataList = res.data.data
    },
    // 浏览记录
    async fetchGetHistoryList() {
      const res = await _getHistoryList()
      this.dataList = res.data.data
    },
  }
})

export default useFavorStore