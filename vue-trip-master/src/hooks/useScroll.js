import { onMounted, onUnmounted, ref } from "vue"
import { throttle } from 'lodash'

export default function useScroll(elRef) {
  let el = window

  const isReachBottom = ref(false)
  const scrollTop = ref(0)
  const scrollHeight = ref(0)
  const clientHeight = ref(0)

  const scrollListenerHandler = throttle(() => {
    if(el == window) {
      scrollTop.value = document.documentElement.scrollTop
      scrollHeight.value = document.documentElement.scrollHeight
      clientHeight.value = document.documentElement.clientHeight
    } else {
      scrollTop.value = el.scrollTop
      scrollHeight.value = el.scrollHeight
      clientHeight.value = el.clientHeight
    }
    // 滚动到底部
    if(Math.round(scrollTop.value + clientHeight.value) == scrollHeight.value) {
      isReachBottom.value = true
    }
  },100)
  
  onMounted(() => {
    if(elRef) {
      el = elRef.value
    }
    el.addEventListener('scroll', scrollListenerHandler)
  })
  
  onUnmounted(() => {
    el.removeEventListener('scroll', scrollListenerHandler)
  })
  return { isReachBottom, scrollTop, scrollHeight, clientHeight }
}