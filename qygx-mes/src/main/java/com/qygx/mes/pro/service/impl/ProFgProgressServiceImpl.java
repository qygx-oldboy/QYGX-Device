package com.qygx.mes.pro.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qygx.mes.pro.mapper.ProFgProgressMapper;
import com.qygx.mes.pro.domain.ProFgProgress;
import com.qygx.mes.pro.service.IProFgProgressService;

/**
 * 返工进度Service业务层处理
 * 
 * @author qygx
 * @date 2022-10-27
 */
@Service
public class ProFgProgressServiceImpl implements IProFgProgressService 
{
    @Autowired
    private ProFgProgressMapper proFgProgressMapper;

    /**
     * 查询返工进度
     * 
     * @param id 返工进度主键
     * @return 返工进度
     */
    @Override
    public ProFgProgress selectProFgProgressById(String id)
    {
        return proFgProgressMapper.selectProFgProgressById(id);
    }

    /**
     * 查询返工进度列表
     * 
     * @param proFgProgress 返工进度
     * @return 返工进度
     */
    @Override
    public List<ProFgProgress> selectProFgProgressList(ProFgProgress proFgProgress)
    {
        return proFgProgressMapper.selectProFgProgressList(proFgProgress);
    }

    /**
     * 新增返工进度
     * 
     * @param proFgProgress 返工进度
     * @return 结果
     */
    @Override
    public int insertProFgProgress(ProFgProgress proFgProgress)
    {
        return proFgProgressMapper.insertProFgProgress(proFgProgress);
    }

    /**
     * 修改返工进度
     * 
     * @param proFgProgress 返工进度
     * @return 结果
     */
    @Override
    public int updateProFgProgress(ProFgProgress proFgProgress)
    {
        return proFgProgressMapper.updateProFgProgress(proFgProgress);
    }

    /**
     * 批量删除返工进度
     * 
     * @param ids 需要删除的返工进度主键
     * @return 结果
     */
    @Override
    public int deleteProFgProgressByIds(String[] ids)
    {
        return proFgProgressMapper.deleteProFgProgressByIds(ids);
    }

    /**
     * 删除返工进度信息
     * 
     * @param id 返工进度主键
     * @return 结果
     */
    @Override
    public int deleteProFgProgressById(String id)
    {
        return proFgProgressMapper.deleteProFgProgressById(id);
    }
}
