import request from '@/utils/request'

export function getDetailPage(query) {
  return request({
    url: 'http://127.0.0.1:8080/detail/page',
    method: 'get',
    params: query
  })
}
