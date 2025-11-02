import Request from '../request/index'

// 收藏列表
export function  _getFavorList() {
  return Request.get({
    url: '/favor/list'
  })
}
// 浏览历史
export function  _getHistoryList() {
  return Request.get({
    url: '/favor/history'
  })
}