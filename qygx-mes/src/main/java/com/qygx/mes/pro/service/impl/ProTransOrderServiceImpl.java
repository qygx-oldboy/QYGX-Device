package com.qygx.mes.pro.service.impl;

import com.qygx.common.utils.DateUtils;
import com.qygx.mes.pro.domain.ProTransOrder;
import com.qygx.mes.pro.mapper.ProTransOrderMapper;
import com.qygx.mes.pro.service.IProTransOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 流转单Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-07-24
 */
@Service
public class ProTransOrderServiceImpl implements IProTransOrderService 
{
    @Autowired
    private ProTransOrderMapper proTransOrderMapper;

    /**
     * 查询流转单
     * 
     * @param transOrderId 流转单主键
     * @return 流转单
     */
    @Override
    public ProTransOrder selectProTransOrderByTransOrderId(Long transOrderId)
    {
        return proTransOrderMapper.selectProTransOrderByTransOrderId(transOrderId);
    }

    /**
     * 查询流转单列表
     * 
     * @param proTransOrder 流转单
     * @return 流转单
     */
    @Override
    public List<ProTransOrder> selectProTransOrderList(ProTransOrder proTransOrder)
    {
        return proTransOrderMapper.selectProTransOrderList(proTransOrder);
    }

    /**
     * 新增流转单
     * 
     * @param proTransOrder 流转单
     * @return 结果
     */
    @Override
    public int insertProTransOrder(ProTransOrder proTransOrder)
    {
        proTransOrder.setCreateTime(DateUtils.getNowDate());
        return proTransOrderMapper.insertProTransOrder(proTransOrder);
    }

    /**
     * 修改流转单
     * 
     * @param proTransOrder 流转单
     * @return 结果
     */
    @Override
    public int updateProTransOrder(ProTransOrder proTransOrder)
    {
        proTransOrder.setUpdateTime(DateUtils.getNowDate());
        return proTransOrderMapper.updateProTransOrder(proTransOrder);
    }

    /**
     * 批量删除流转单
     * 
     * @param transOrderIds 需要删除的流转单主键
     * @return 结果
     */
    @Override
    public int deleteProTransOrderByTransOrderIds(Long[] transOrderIds)
    {
        return proTransOrderMapper.deleteProTransOrderByTransOrderIds(transOrderIds);
    }

    /**
     * 删除流转单信息
     * 
     * @param transOrderId 流转单主键
     * @return 结果
     */
    @Override
    public int deleteProTransOrderByTransOrderId(Long transOrderId)
    {
        return proTransOrderMapper.deleteProTransOrderByTransOrderId(transOrderId);
    }
}
