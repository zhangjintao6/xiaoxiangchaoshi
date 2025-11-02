import Request from "../request/index"

export function _getSearchTopData() {
  return Request.get({
    url: '/search/top'
  })
}