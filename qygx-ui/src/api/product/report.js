import request from '@/utils/request'

// 查询检验数和良率
export function chartInspect(query) {
    return request({
        url: '/product/report/getChart',
        method: 'get',
        params: query
    })
}

// 查询检验数和良率
export function breedList() {
    return request({
        url: '/product/report/getBreedList',
        method: 'get',
    })
}