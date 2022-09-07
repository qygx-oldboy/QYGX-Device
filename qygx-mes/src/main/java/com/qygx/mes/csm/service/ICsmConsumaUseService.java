package com.qygx.mes.csm.service;

import java.util.List;
import com.qygx.mes.csm.domain.CsmConsumaUse;

/**
 * 在用备件Service接口
 * 
 * @author qygx
 * @date 2022-09-07
 */
public interface ICsmConsumaUseService 
{
    /**
     * 查询在用备件
     * 
     * @param consumaUseId 在用备件主键
     * @return 在用备件
     */
    public CsmConsumaUse selectCsmConsumaUseByConsumaUseId(Long consumaUseId);

    /**
     * 查询在用备件列表
     * 
     * @param csmConsumaUse 在用备件
     * @return 在用备件集合
     */
    public List<CsmConsumaUse> selectCsmConsumaUseList(CsmConsumaUse csmConsumaUse);

    /**
     * 新增在用备件
     * 
     * @param csmConsumaUse 在用备件
     * @return 结果
     */
    public int insertCsmConsumaUse(CsmConsumaUse csmConsumaUse);

    /**
     * 修改在用备件
     * 
     * @param csmConsumaUse 在用备件
     * @return 结果
     */
    public int updateCsmConsumaUse(CsmConsumaUse csmConsumaUse);

    /**
     * 批量删除在用备件
     * 
     * @param consumaUseIds 需要删除的在用备件主键集合
     * @return 结果
     */
    public int deleteCsmConsumaUseByConsumaUseIds(Long[] consumaUseIds);

    /**
     * 删除在用备件信息
     * 
     * @param consumaUseId 在用备件主键
     * @return 结果
     */
    public int deleteCsmConsumaUseByConsumaUseId(Long consumaUseId);
}
