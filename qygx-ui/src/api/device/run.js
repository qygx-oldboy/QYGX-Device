import request from '@/utils/request'

// 查询运行记录列表
export function listRun(query) {
  return request({
    url: '/device/run/list',
    method: 'get',
    params: query
  })
}

// 查询运行记录详细
export function getRun(recordId) {
  return request({
    url: '/device/run/' + recordId,
    method: 'get'
  })
}

// 新增运行记录
export function addRun(data) {
  return request({
    url: '/device/run',
    method: 'post',
    data: data
  })
}

// 修改运行记录
export function updateRun(data) {
  return request({
    url: '/device/run',
    method: 'put',
    data: data
  })
}

// 删除运行记录
export function delRun(recordId) {
  return request({
    url: '/device/run/' + recordId,
    method: 'delete'
  })
}
