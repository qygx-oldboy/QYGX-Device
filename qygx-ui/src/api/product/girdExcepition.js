import request from '@/utils/request'

// 查询异常inspect列表
export function unusualListInspect(query) {
  return request({
    url: '/product/report/unusualList',
    method: 'get',
    params: query
  })
}