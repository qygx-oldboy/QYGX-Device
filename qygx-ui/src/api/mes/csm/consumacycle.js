import request from '@/utils/request'

// 查询耗材周期列表
export function listConsumacycle(query) {
  return request({
    url: '/mes/csm/consumacycle/list',
    method: 'get',
    params: query
  })
}

// 查询耗材周期详细
export function getConsumacycle(consumaCycleId) {
  return request({
    url: '/mes/csm/consumacycle/' + consumaCycleId,
    method: 'get'
  })
}

// 新增耗材周期
export function addConsumacycle(data) {
  return request({
    url: '/mes/csm/consumacycle',
    method: 'post',
    data: data
  })
}

// 修改耗材周期
export function updateConsumacycle(data) {
  return request({
    url: '/mes/csm/consumacycle',
    method: 'put',
    data: data
  })
}

// 删除耗材周期
export function delConsumacycle(consumaCycleId) {
  return request({
    url: '/mes/csm/consumacycle/' + consumaCycleId,
    method: 'delete'
  })
}
