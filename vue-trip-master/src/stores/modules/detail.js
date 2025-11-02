import { defineStore } from "pinia";
import { _getHouseDetail } from "@/services";

const useDetailStore = defineStore('detail',{
  state: () => ({
    infos: {},
  }),
  actions: {
    async fetchGetHouseDetail(id) {
      const res = await _getHouseDetail(id)
      this.infos = res.data
    }
  }
})
export default useDetailStore