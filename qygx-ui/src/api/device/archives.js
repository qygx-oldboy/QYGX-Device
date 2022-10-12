import request from '@/utils/request'

// 查询archives列表
export function listArchives(query) {
  return request({
    url: '/device/archives/list',
    method: 'get',
    params: query
  })
}

// 查询所有 使用的设备
export function listAllArchives() {
  return request({
    url: '/device/archives/listAll',
    method: 'get'
  })
}


// 查询archives详细
export function getArchives(deviceId) {
  return request({
    url: '/device/archives/' + deviceId,
    method: 'get'
  })
}

// 新增archives
export function addArchives(data) {
  return request({
    url: '/device/archives',
    method: 'post',
    data: data
  })
}

// 修改archives
export function updateArchives(data) {
  return request({
    url: '/device/archives',
    method: 'put',
    data: data
  })
}

// 删除archives
export function delArchives(deviceId) {
  return request({
    url: '/device/archives/' + deviceId,
    method: 'delete'
  })
}
