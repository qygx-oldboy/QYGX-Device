import request from '@/utils/request'

// 查询班次设置列表
export function listShift(query) {
  return request({
    url: '/mes/cal/shift/list',
    method: 'get',
    params: query
  })
}

// 查询班次设置详细
export function getShift(shiftId) {
  return request({
    url: '/mes/cal/shift/' + shiftId,
    method: 'get'
  })
}

// 新增班次设置
export function addShift(data) {
  return request({
    url: '/mes/cal/shift',
    method: 'post',
    data: data
  })
}

// 修改班次设置
export function updateShift(data) {
  return request({
    url: '/mes/cal/shift',
    method: 'put',
    data: data
  })
}

// 删除班次设置
export function delShift(shiftId) {
  return request({
    url: '/mes/cal/shift/' + shiftId,
    method: 'delete'
  })
}
