import dayjs from 'dayjs'

export function formatMonthDay(date) {
  return dayjs(date).format('MM月DD日')
}

export function tomorrow(date) {
  return dayjs(date).add(1,'day').format('MM月DD日')
}
export function dayDiff(start, end) {
  return dayjs(end).diff(dayjs(start), 'day')
}