import { defineStore } from "pinia";
import { _getSearchTopData } from "@/services";

const useSearchStore = defineStore("search", {
  state: () => ({
    searchTopData: []
  }),
  actions: {
    // 搜索页
    async fetchSearchTopData() {
      const res = await _getSearchTopData()
      this.searchTopData = res.data.data.allConditions
    }
  }
})

export default useSearchStore