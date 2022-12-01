package com.qygx.mes.csm.mapper;

import java.util.List;
import com.qygx.mes.csm.domain.CsmConsuma;

/**
 * 备件档案Mapper接口
 *
 * @author qygx
 * @date 2022-09-09
 */
public interface CsmConsumaMapper
{
    /**
     * 查询备件档案
     *
     * @param consumaId 备件档案主键
     * @return 备件档案
     */
    public CsmConsuma selectCsmConsumaByConsumaId(Long consumaId);

    /**
     * 查询备件档案列表
     *
     * @param csmConsuma 备件档案
     * @return 备件档案集合
     */
    public List<CsmConsuma> selectCsmConsumaList(CsmConsuma csmConsuma);

    /**
     * 新增备件档案
     *
     * @param csmConsuma 备件档案
     * @return 结果
     */
    public int insertCsmConsuma(CsmConsuma csmConsuma);

    /**
     * 修改备件档案
     *
     * @param csmConsuma 备件档案
     * @return 结果
     */
    public int updateCsmConsuma(CsmConsuma csmConsuma);

    /**
     * 删除备件档案
     *
     * @param consumaId 备件档案主键
     * @return 结果
     */
    public int deleteCsmConsumaByConsumaId(Long consumaId);

    /**
     * 批量删除备件档案
     *
     * @param consumaIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCsmConsumaByConsumaIds(Long[] consumaIds);

    /**
     * 查询备件档案
     *
     * @param consumaCode 备件编号
     * @return 备件档案
     */
    public CsmConsuma selectConsumaByCode(String consumaCode);



}
