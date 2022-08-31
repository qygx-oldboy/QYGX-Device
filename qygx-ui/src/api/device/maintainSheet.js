import request from '@/utils/request'

// 查询保养记录列表
export function listMaintainSheet(query) {
  return request({
    url: '/device/maintainSheet/list',
    method: 'get',
    params: query
  })
}

// 查询保养记录详细
export function getMaintainSheet(maintainSheetId) {
  return request({
    url: '/device/maintainSheet/' + maintainSheetId,
    method: 'get'
  })
}

// 新增保养记录
export function addMaintainSheet(data) {
  return request({
    url: '/device/maintainSheet',
    method: 'post',
    data: data
  })
}

// 修改保养记录
export function updateMaintainSheet(data) {
  return request({
    url: '/device/maintainSheet',
    method: 'put',
    data: data
  })
}

// 删除保养记录
export function delMaintainSheet(maintainSheetId) {
  return request({
    url: '/device/maintainSheet/' + maintainSheetId,
    method: 'delete'
  })
}


// 删除保养记录
export function submitMaintainSheet(maintainSheetId) {
  return request({
    url: '/device/maintainSheet/submit/' + maintainSheetId,
    method: 'put'
  })
}