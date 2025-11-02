import Request from "../request/index";

export function _getCityAll() {
  return Request.get({
    url: '/city/all'
  })
}