package com.qygx.mes.quato.mapper;

import java.util.List;
import com.qygx.mes.quato.domain.TbQuato;

/**
 * 指标管理Mapper接口
 *
 * @author qygx
 * @date 2022-10-18
 */
public interface TbQuatoMapper
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
     * 删除指标管理
     *
     * @param id 指标管理主键
     * @return 结果
     */
    public int deleteTbQuatoById(Long id);

    /**
     * 批量删除指标管理
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbQuatoByIds(Long[] ids);

    /**
     * 获取所有主题
     * @return
     */
    public List<String> selectAllProject();
}
