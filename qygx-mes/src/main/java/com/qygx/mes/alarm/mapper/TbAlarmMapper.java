package com.qygx.mes.alarm.mapper;

import java.util.List;
import com.qygx.mes.alarm.domain.TbAlarm;

/**
 * 告警管理Mapper接口
 * 
 * @author qygx
 * @date 2022-10-19
 */
public interface TbAlarmMapper 
{
    /**
     * 查询告警管理
     * 
     * @param id 告警管理主键
     * @return 告警管理
     */
    public TbAlarm selectTbAlarmById(Long id);

    /**
     * 查询告警管理列表
     * 
     * @param tbAlarm 告警管理
     * @return 告警管理集合
     */
    public List<TbAlarm> selectTbAlarmList(TbAlarm tbAlarm);

    /**
     * 新增告警管理
     * 
     * @param tbAlarm 告警管理
     * @return 结果
     */
    public int insertTbAlarm(TbAlarm tbAlarm);

    /**
     * 修改告警管理
     * 
     * @param tbAlarm 告警管理
     * @return 结果
     */
    public int updateTbAlarm(TbAlarm tbAlarm);

    /**
     * 删除告警管理
     * 
     * @param id 告警管理主键
     * @return 结果
     */
    public int deleteTbAlarmById(Long id);

    /**
     * 批量删除告警管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTbAlarmByIds(Long[] ids);
}
