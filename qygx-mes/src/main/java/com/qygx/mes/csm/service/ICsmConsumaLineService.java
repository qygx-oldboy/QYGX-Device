package com.qygx.mes.csm.service;

import java.util.List;
import com.qygx.mes.csm.domain.CsmConsumaLine;

/**
 * 备件周期项Service接口
 *
 * @author qygx
 * @date 2022-11-25
 */
public interface ICsmConsumaLineService
{
    /**
     * 查询备件周期项
     *
     * @param lineId 备件周期项主键
     * @return 备件周期项
     */
    public CsmConsumaLine selectCsmConsumaLineByLineId(Long lineId);

    /**
     * 查询备件周期项列表
     *
     * @param csmConsumaLine 备件周期项
     * @return 备件周期项集合
     */
    public List<CsmConsumaLine> selectCsmConsumaLineList(CsmConsumaLine csmConsumaLine);

    /**
     * 新增备件周期项
     *
     * @param csmConsumaLine 备件周期项
     * @return 结果
     */
    public int insertCsmConsumaLine(CsmConsumaLine csmConsumaLine);

    /**
     * 修改备件周期项
     *
     * @param csmConsumaLine 备件周期项
     * @return 结果
     */
    public int updateCsmConsumaLine(CsmConsumaLine csmConsumaLine);

    /**
     * 批量删除备件周期项
     *
     * @param lineIds 需要删除的备件周期项主键集合
     * @return 结果
     */
    public int deleteCsmConsumaLineByLineIds(Long[] lineIds);

    /**
     * 删除备件周期项信息
     *
     * @param lineId 备件周期项主键
     * @return 结果
     */
    public int deleteCsmConsumaLineByLineId(Long lineId);


    /**
     * 查询备件周期项
     *
     * @param consumaId 备件主键
     * @return 备件周期项
     */
    public List<CsmConsumaLine> selectLineByConsumaId(Long consumaId);
}
