import request from '@/utils/request'

// 查询在用备件列表
export function listConsumause(query) {
  return request({
    url: '/mes/csm/consumause/list',
    method: 'get',
    params: query
  })
}

// 查询在用备件详细
export function getConsumause(consumaUseId) {
  return request({
    url: '/mes/csm/consumause/' + consumaUseId,
    method: 'get'
  })
}

// 新增在用备件
export function addConsumause(data) {
  return request({
    url: '/mes/csm/consumause',
    method: 'post',
    data: data
  })
}

// 修改在用备件
export function updateConsumause(data) {
  return request({
    url: '/mes/csm/consumause',
    method: 'put',
    data: data
  })
}

// 删除在用备件
export function delConsumause(consumaUseId) {
  return request({
    url: '/mes/csm/consumause/' + consumaUseId,
    method: 'delete'
  })
}
