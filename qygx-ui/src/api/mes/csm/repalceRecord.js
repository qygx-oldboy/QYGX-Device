import request from '@/utils/request'

// 查询备件更换记录列表
export function listRepalceRecord(query) {
  return request({
    url: '/mes/csm/repalceRecord/list',
    method: 'get',
    params: query
  })
}

// 查询备件更换记录详细
export function getRepalceRecord(recordId) {
  return request({
    url: '/mes/csm/repalceRecord/' + recordId,
    method: 'get'
  })
}

// 新增备件更换记录
export function addRepalceRecord(data) {
  return request({
    url: '/mes/csm/repalceRecord',
    method: 'post',
    data: data
  })
}

// 修改备件更换记录
export function updateRepalceRecord(data) {
  return request({
    url: '/mes/csm/repalceRecord',
    method: 'put',
    data: data
  })
}

// 删除备件更换记录
export function delRepalceRecord(recordId) {
  return request({
    url: '/mes/csm/repalceRecord/' + recordId,
    method: 'delete'
  })
}
