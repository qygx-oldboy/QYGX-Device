import request from '@/utils/request'

// 查询检验数和良率
export function chartGetShift(query) {
    return request({
        url: '/product/report/getShift',
        method: 'get',
        params: query
    })
}

// 查询基础信息
export function getInfo() {
    return request({
        url: '/product/report/info',
        method: 'get',
    })
}