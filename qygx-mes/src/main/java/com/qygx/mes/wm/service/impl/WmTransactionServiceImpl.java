package com.qygx.mes.wm.service.impl;

import com.qygx.common.exception.BussinessException;
import com.qygx.common.utils.DateUtils;
import com.qygx.common.utils.StringUtils;
import com.qygx.mes.md.domain.MdItem;
import com.qygx.mes.md.mapper.MdItemMapper;
import com.qygx.mes.wm.domain.WmMaterialStock;
import com.qygx.mes.wm.domain.WmTransaction;
import com.qygx.mes.wm.mapper.WmMaterialStockMapper;
import com.qygx.mes.wm.mapper.WmTransactionMapper;
import com.qygx.mes.wm.service.IWmTransactionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 库存事务Service业务层处理
 * 
 * @author yinjinlu
 * @date 2022-05-24
 */
@Service
public class WmTransactionServiceImpl implements IWmTransactionService 
{
    @Autowired
    private WmTransactionMapper wmTransactionMapper;

    @Autowired
    private WmMaterialStockMapper wmMaterialStockMapper;

    @Autowired
    private MdItemMapper mdItemMapper;

    @Override
    public synchronized WmTransaction processTransaction(WmTransaction wmTransaction) {
        WmMaterialStock stock = new WmMaterialStock();

        validate(wmTransaction);
        initStock(wmTransaction,stock);

        WmMaterialStock ms =wmMaterialStockMapper.loadMaterialStock(stock);
        BigDecimal quantity = wmTransaction.getTransactionQuantity().multiply(new BigDecimal(wmTransaction.getTransactionFlag()));
        if(StringUtils.isNotNull(ms)){
            //MS已存在
            BigDecimal resultQuantity =ms.getQuantityOnhand().add(quantity);
            if(resultQuantity.compareTo(new BigDecimal(0))<0){
                throw new BussinessException("库存数量不足！");
            }
            stock.setQuantityOnhand(resultQuantity);
            stock.setMaterialStockId(ms.getMaterialStockId());
            wmMaterialStockMapper.updateWmMaterialStock(stock);
        }else {
            //MS不存在
            stock.setQuantityOnhand(quantity);
            wmMaterialStockMapper.insertWmMaterialStock(stock);
        }
        wmTransaction.setMaterialStockId(stock.getMaterialStockId());
        wmTransaction.setTransactionQuantity(quantity);
        wmTransactionMapper.insertWmTransaction(wmTransaction);
        return wmTransaction;
    }


    private void validate(WmTransaction transaction){
        if(StringUtils.isNull(transaction.getTransactionType())){
            throw new BussinessException("库存事务不能为空");
        }

        if(StringUtils.isNull(transaction.getTransactionQuantity())){
            throw new BussinessException("事务数量不能为空");
        }

        if(StringUtils.isNull(transaction.getSourceDocCode())){
            throw new BussinessException("来源单据号不能为空");
        }

        if(StringUtils.isNull(transaction.getSourceDocLineId())){
            throw new BussinessException("来源单据行ID不能为空");
        }

        if(StringUtils.isNull(transaction.getTransactionDate())){
            transaction.setTransactionDate(new Date());
        }
    }

    public void initStock(WmTransaction transaction,WmMaterialStock stock){

        if(StringUtils.isNotNull(transaction.getMaterialStockId())){
            WmMaterialStock st = wmMaterialStockMapper.selectWmMaterialStockByMaterialStockId(transaction.getMaterialStockId());
            BeanUtils.copyProperties(st,stock);
        }else{
            MdItem item =mdItemMapper.selectMdItemById(transaction.getItemId());
            stock.setItemTypeId(item.getItemTypeId());
            stock.setItemId(transaction.getItemId());
            stock.setItemCode(transaction.getItemCode());
            stock.setItemName(transaction.getItemName());
            stock.setSpecification(transaction.getSpecification());
            stock.setUnitOfMeasure(transaction.getUnitOfMeasure());
            stock.setBatchCode(transaction.getBatchCode());
            stock.setWarehouseId(transaction.getWarehouseId());
            stock.setWarehouseCode(transaction.getWarehouseCode());
            stock.setWarehouseName(transaction.getWarehouseName());
            stock.setLocationId(transaction.getLocationId());
            stock.setLocationCode(transaction.getLocationCode());
            stock.setLocationName(transaction.getLocationName());
            if(StringUtils.isNotNull(transaction.getAreaId())){
                stock.setAreaId(transaction.getAreaId());
                stock.setAreaCode(transaction.getAreaCode());
                stock.setAreaName(transaction.getAreaName());
            }
            if(StringUtils.isNotNull(transaction.getVendorId())){
                stock.setVendorId(transaction.getVendorId());
                stock.setVendorCode(transaction.getVendorCode());
                stock.setVendorName(transaction.getVendorName());
                stock.setVendorNick(transaction.getVendorNick());
            }
            stock.setExpireDate(transaction.getExpireDate());
        }
    }


    /**
     * 查询库存事务
     * 
     * @param transactionId 库存事务主键
     * @return 库存事务
     */
    @Override
    public WmTransaction selectWmTransactionByTransactionId(Long transactionId)
    {
        return wmTransactionMapper.selectWmTransactionByTransactionId(transactionId);
    }

    /**
     * 查询库存事务列表
     * 
     * @param wmTransaction 库存事务
     * @return 库存事务
     */
    @Override
    public List<WmTransaction> selectWmTransactionList(WmTransaction wmTransaction)
    {
        return wmTransactionMapper.selectWmTransactionList(wmTransaction);
    }

    /**
     * 新增库存事务
     * 
     * @param wmTransaction 库存事务
     * @return 结果
     */
    @Override
    public int insertWmTransaction(WmTransaction wmTransaction)
    {
        wmTransaction.setCreateTime(DateUtils.getNowDate());
        return wmTransactionMapper.insertWmTransaction(wmTransaction);
    }

    /**
     * 修改库存事务
     * 
     * @param wmTransaction 库存事务
     * @return 结果
     */
    @Override
    public int updateWmTransaction(WmTransaction wmTransaction)
    {
        wmTransaction.setUpdateTime(DateUtils.getNowDate());
        return wmTransactionMapper.updateWmTransaction(wmTransaction);
    }

    /**
     * 批量删除库存事务
     * 
     * @param transactionIds 需要删除的库存事务主键
     * @return 结果
     */
    @Override
    public int deleteWmTransactionByTransactionIds(Long[] transactionIds)
    {
        return wmTransactionMapper.deleteWmTransactionByTransactionIds(transactionIds);
    }

    /**
     * 删除库存事务信息
     * 
     * @param transactionId 库存事务主键
     * @return 结果
     */
    @Override
    public int deleteWmTransactionByTransactionId(Long transactionId)
    {
        return wmTransactionMapper.deleteWmTransactionByTransactionId(transactionId);
    }
}
