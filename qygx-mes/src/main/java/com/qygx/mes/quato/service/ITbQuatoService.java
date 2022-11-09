package com.qygx.mes.quato.service;

import java.util.List;
import java.util.Map;

import com.qygx.mes.quato.domain.TbQuato;
import com.qygx.mes.quato.domain.dto.DeviceInfoDTO;

/**
 * 指标管理Service接口
 *
 * @author qygx
 * @date 2022-10-18
 */
public interface ITbQuatoService
{
    /**
     * 查询指标管理
     *
     * @param id 指标管理主键
     * @return 指标管理
     */
    public TbQuato selectTbQuatoById(Long id);

    /**
     * 查询指标管理列表
     *
     * @param tbQuato 指标管理
     * @return 指标管理集合
     */
    public List<TbQuato> selectTbQuatoList(TbQuato tbQuato);

    /**
     * 新增指标管理
     *
     * @param tbQuato 指标管理
     * @return 结果
     */
    public int insertTbQuato(TbQuato tbQuato);

    /**
     * 修改指标管理
     *
     * @param tbQuato 指标管理
     * @return 结果
     */
    public int updateTbQuato(TbQuato tbQuato);

    /**
     * 批量删除指标管理
     *
     * @param ids 需要删除的指标管理主键集合
     * @return 结果
     */
    public int deleteTbQuatoByIds(Long[] ids);

    /**
     * 删除指标管理信息
     *
     * @param id 指标管理主键
     * @return 结果
     */
    public int deleteTbQuatoById(Long id);

    /**
     * 解析报文
     * @param topic 主题
     * @param payloadMap 报文内容
     * @return
     */
    public DeviceInfoDTO analysis(String topic, Map<String,Object> payloadMap);
}
