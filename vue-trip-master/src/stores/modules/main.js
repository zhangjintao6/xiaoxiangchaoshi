import { defineStore } from "pinia";
import { formatMonthDay, tomorrow } from "@/utils/handleDate"

const startDate = formatMonthDay(new Date())
const endDate = tomorrow(new Date())
const useMainStore = defineStore("main",{
  state: () => ({
    startDate: startDate,
    endDate: endDate,
    showDateSel: false,
    isLoading: false
  }),
  actions: {

  }
})

export default useMainStore