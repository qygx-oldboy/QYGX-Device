import request from '@/utils/request'

// 查询备件档案列表
export function listConsuma(query) {
  return request({
    url: '/mes/csm/consuma/list',
    method: 'get',
    params: query
  })
}

// 查询备件档案详细
export function getConsuma(consumaId) {
  return request({
    url: '/mes/csm/consuma/' + consumaId,
    method: 'get'
  })
}

// 新增备件档案
export function addConsuma(data) {
  return request({
    url: '/mes/csm/consuma',
    method: 'post',
    data: data
  })
}

// 修改备件档案
export function updateConsuma(data) {
  return request({
    url: '/mes/csm/consuma',
    method: 'put',
    data: data
  })
}

// 删除备件档案
export function delConsuma(consumaId) {
  return request({
    url: '/mes/csm/consuma/' + consumaId,
    method: 'delete'
  })
}
