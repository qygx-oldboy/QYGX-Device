import request from '@/utils/request'

// 查询inspect列表
export function listInspect(query) {
  return request({
    url: '/product/report/list',
    method: 'get',
    params: query
  })
}

// 查询inspect详细
export function getInspect(id) {
  return request({
    url: '/product/report/' + id,
    method: 'get'
  })
}

// 新增inspect
export function addInspect(data) {
  return request({
    url: '/product/report',
    method: 'post',
    data: data
  })
}

// 修改inspect
export function updateInspect(data) {
  return request({
    url: '/product/report',
    method: 'put',
    data: data
  })
}

// 删除inspect
export function delInspect(id) {
  return request({
    url: '/product/report/' + id,
    method: 'delete'
  })
}
