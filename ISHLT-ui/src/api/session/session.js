import request from '@/utils/request'

export function getSessionPage(query) {
  return request({
    url: 'http://127.0.0.1:8080/session/page',
    method: 'get',
    params: query
  })
}
