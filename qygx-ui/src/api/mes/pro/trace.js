import request from '@/utils/request'

// 查询生产追溯列表
export function listTrace(query) {
  return request({
    url: '/mes/pro/trace/list',
    method: 'get',
    params: query
  })
}

// 查询生产追溯详细
export function getTrace(traceId) {
  return request({
    url: '/mes/pro/trace/' + traceId,
    method: 'get'
  })
}

// 新增生产追溯
export function addTrace(data) {
  return request({
    url: '/mes/pro/trace',
    method: 'post',
    data: data
  })
}

// 修改生产追溯
export function updateTrace(data) {
  return request({
    url: '/mes/pro/trace',
    method: 'put',
    data: data
  })
}

// 删除生产追溯
export function delTrace(traceId) {
  return request({
    url: '/mes/pro/trace/' + traceId,
    method: 'delete'
  })
}

// 查询生产追溯列表
export function TraceHistory(query) {
  return request({
    url: '/mes/pro/trace/history',
    method: 'get',
    params: query
  })
}