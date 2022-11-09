package com.qygx.mes.alarm.service;

import java.util.List;
import com.qygx.mes.alarm.domain.TbAlarm;
import com.qygx.mes.quato.domain.dto.DeviceInfoDTO;
import com.qygx.mes.quato.domain.dto.QuatoDTO;

/**
 * 告警管理Service接口
 *
 * @author qygx
 * @date 2022-10-19
 */
public interface ITbAlarmService
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
     * 批量删除告警管理
     *
     * @param ids 需要删除的告警管理主键集合
     * @return 结果
     */
    public int deleteTbAlarmByIds(Long[] ids);

    /**
     * 删除告警管理信息
     *
     * @param id 告警管理主键
     * @return 结果
     */
    public int deleteTbAlarmById(Long id);

    /**
     *  根据指标判断告警信息
     * @param quatoDTO
     * @return
     */
    public TbAlarm verifyQuato(QuatoDTO quatoDTO);

    /**
     * 告警信息封装
     * @param deviceInfoDTO
     * @return
     */
    public DeviceInfoDTO verifyDeviceInfo(DeviceInfoDTO deviceInfoDTO);
}
