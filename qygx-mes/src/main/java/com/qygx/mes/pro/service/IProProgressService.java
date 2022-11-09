package com.qygx.mes.pro.service;

import java.util.List;
import com.qygx.mes.pro.domain.ProProgress;

/**
 * 生产进度管理Service接口
 *
 * @author qygx
 * @date 2022-10-25
 */
public interface IProProgressService
{
    /**
     * 查询生产进度管理
     *
     * @param id 生产进度管理主键
     * @return 生产进度管理
     */
    public ProProgress selectProProgressById(String id);

    /**
     * 查询生产进度管理列表
     *
     * @param proProgress 生产进度管理
     * @return 生产进度管理集合
     */
    public List<ProProgress> selectProProgressList(ProProgress proProgress);

    /**
     * 新增生产进度管理
     *
     * @param proProgress 生产进度管理
     * @return 结果
     */
    public int insertProProgress(ProProgress proProgress);

    /**
     * 修改生产进度管理
     *
     * @param proProgress 生产进度管理
     * @return 结果
     */
    public int updateProProgress(ProProgress proProgress);

    /**
     * 批量删除生产进度管理
     *
     * @param ids 需要删除的生产进度管理主键集合
     * @return 结果
     */
    public int deleteProProgressByIds(String[] ids);

    /**
     * 删除生产进度管理信息
     *
     * @param id 生产进度管理主键
     * @return 结果
     */
    public int deleteProProgressById(String id);


    /**
     * 查询条形图 数据
     */
    public List<ProProgress> selectBarData(ProProgress proProgress);
}
