import request from '@/utils/request'

// 查询保养计划列表
export function listPlan(query) {
  return request({
    url: '/device/plan/list',
    method: 'get',
    params: query
  })
}

// 查询保养计划详细
export function getPlan(planId) {
  return request({
    url: '/device/plan/' + planId,
    method: 'get'
  })
}

// 新增保养计划
export function addPlan(data) {
  return request({
    url: '/device/plan',
    method: 'post',
    data: data
  })
}

// 修改保养计划
export function updatePlan(data) {
  return request({
    url: '/device/plan',
    method: 'put',
    data: data
  })
}

// 删除保养计划
export function delPlan(planId) {
  return request({
    url: '/device/plan/' + planId,
    method: 'delete'
  })
}

// 保养人选项
export function optionUser() {
  return request({
    url: '/device/plan/getUserOptions',
    method: 'get'
  })
}

// // 设备树选项
// export function deviceTreeselect() {
//   return request({
//     url: '/device/plan/getDeviceOptions',
//     method: 'get'
//   })
// }

// // 根据计划ID查询设备树结构
// export function planDeviceTreeselect(planId) {
//   return request({
//     url: '/device/plan/planDeviceTreeselect/' + planId,
//     method: 'get'
//   })
// }

// 计划状态修改
export function changePlanStatus(planId, useState,deviceType,level) {
  const data = {
    planId,
    useState,
    deviceType,
    level
  }
  return request({
    url: '/device/plan/changeStatus',
    method: 'put',
    data: data
  })
}