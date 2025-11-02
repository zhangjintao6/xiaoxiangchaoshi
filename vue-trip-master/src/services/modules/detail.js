import Request from '../request/index'

export function _getHouseDetail(houseId) {
  return Request.get({
    url: '/detail/infos',
    params: {
      houseId
    }
  })
}