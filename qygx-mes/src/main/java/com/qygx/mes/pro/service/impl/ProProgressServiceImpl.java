package com.qygx.mes.pro.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qygx.mes.pro.mapper.ProProgressMapper;
import com.qygx.mes.pro.domain.ProProgress;
import com.qygx.mes.pro.service.IProProgressService;

/**
 * 生产进度管理Service业务层处理
 *
 * @author qygx
 * @date 2022-10-25
 */
@Service
public class ProProgressServiceImpl implements IProProgressService
{
    @Autowired
    private ProProgressMapper proProgressMapper;

    /**
     * 查询生产进度管理
     *
     * @param id 生产进度管理主键
     * @return 生产进度管理
     */
    @Override
    public ProProgress selectProProgressById(String id)
    {
        return proProgressMapper.selectProProgressById(id);
    }

    /**
     * 查询生产进度管理列表
     *
     * @param proProgress 生产进度管理
     * @return 生产进度管理
     */
    @Override
    public List<ProProgress> selectProProgressList(ProProgress proProgress)
    {
        return proProgressMapper.selectProProgressList(proProgress);
    }

    /**
     * 新增生产进度管理
     *
     * @param proProgress 生产进度管理
     * @return 结果
     */
    @Override
    public int insertProProgress(ProProgress proProgress)
    {
        return proProgressMapper.insertProProgress(proProgress);
    }

    /**
     * 修改生产进度管理
     *
     * @param proProgress 生产进度管理
     * @return 结果
     */
    @Override
    public int updateProProgress(ProProgress proProgress)
    {
        return proProgressMapper.updateProProgress(proProgress);
    }

    /**
     * 批量删除生产进度管理
     *
     * @param ids 需要删除的生产进度管理主键
     * @return 结果
     */
    @Override
    public int deleteProProgressByIds(String[] ids)
    {
        return proProgressMapper.deleteProProgressByIds(ids);
    }

    /**
     * 删除生产进度管理信息
     *
     * @param id 生产进度管理主键
     * @return 结果
     */
    @Override
    public int deleteProProgressById(String id)
    {
        return proProgressMapper.deleteProProgressById(id);
    }

    @Override
    public List<ProProgress> selectBarData(ProProgress proProgress) {
        List<ProProgress> progressList = proProgressMapper.selectProProgressList(proProgress);
        return progressList;
    }
}
