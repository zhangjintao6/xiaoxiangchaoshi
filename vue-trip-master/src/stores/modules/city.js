import { defineStore } from "pinia";
import { _getCityAll } from '@/services'

const useCityStore = defineStore("city",{
  state: () => ({
    allCities: {},
    currentCity: {
      cityName: '广东'
    }
  }),
  actions: {
    async fetchAllCitiesData() {
      const res = await _getCityAll()
      this.allCities = res.data
    }
  }
})

export default useCityStore