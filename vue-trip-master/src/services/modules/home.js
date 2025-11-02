import Request from "../request/index"

// 热门标签
export function _getHotSuggests() {
  return Request.get({
    url: '/home/hotSuggests'
  })
}

// 分类
export function _getHomeCategories() {
  return Request.get({
    url: '/home/categories'
  })
} 

// 民宿列表
export function _getHouseList(page) {
  return Request.get({
    url:'/home/houselist',
    params: {
      page: page
    }
  })
}