package com.qygx.mes.pro.service;

import java.util.List;
import com.qygx.mes.pro.domain.ProFgProgress;

/**
 * 返工进度Service接口
 * 
 * @author qygx
 * @date 2022-10-27
 */
public interface IProFgProgressService 
{
    /**
     * 查询返工进度
     * 
     * @param id 返工进度主键
     * @return 返工进度
     */
    public ProFgProgress selectProFgProgressById(String id);

    /**
     * 查询返工进度列表
     * 
     * @param proFgProgress 返工进度
     * @return 返工进度集合
     */
    public List<ProFgProgress> selectProFgProgressList(ProFgProgress proFgProgress);

    /**
     * 新增返工进度
     * 
     * @param proFgProgress 返工进度
     * @return 结果
     */
    public int insertProFgProgress(ProFgProgress proFgProgress);

    /**
     * 修改返工进度
     * 
     * @param proFgProgress 返工进度
     * @return 结果
     */
    public int updateProFgProgress(ProFgProgress proFgProgress);

    /**
     * 批量删除返工进度
     * 
     * @param ids 需要删除的返工进度主键集合
     * @return 结果
     */
    public int deleteProFgProgressByIds(String[] ids);

    /**
     * 删除返工进度信息
     * 
     * @param id 返工进度主键
     * @return 结果
     */
    public int deleteProFgProgressById(String id);
}
