package com.qygx.mes.alarm.service.impl;

import java.util.Collections;
import java.util.List;

import com.qygx.common.utils.StringUtils;
import com.qygx.mes.quato.domain.dto.DeviceDTO;
import com.qygx.mes.quato.domain.dto.DeviceInfoDTO;
import com.qygx.mes.quato.domain.dto.QuatoDTO;
import com.qygx.system.domain.DeviceArchives;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qygx.mes.alarm.mapper.TbAlarmMapper;
import com.qygx.mes.alarm.domain.TbAlarm;
import com.qygx.mes.alarm.service.ITbAlarmService;

/**
 * 告警管理Service业务层处理
 *
 * @author qygx
 * @date 2022-10-19
 */
@Service
public class TbAlarmServiceImpl implements ITbAlarmService
{
    @Autowired
    private TbAlarmMapper tbAlarmMapper;

    /**
     * 查询告警管理
     *
     * @param id 告警管理主键
     * @return 告警管理
     */
    @Override
    public TbAlarm selectTbAlarmById(Long id)
    {
        return tbAlarmMapper.selectTbAlarmById(id);
    }

    /**
     * 查询告警管理列表
     *
     * @param tbAlarm 告警管理
     * @return 告警管理
     */
    @Override
    public List<TbAlarm> selectTbAlarmList(TbAlarm tbAlarm)
    {
        return tbAlarmMapper.selectTbAlarmList(tbAlarm);
    }

    /**
     * 新增告警管理
     *
     * @param tbAlarm 告警管理
     * @return 结果
     */
    @Override
    public int insertTbAlarm(TbAlarm tbAlarm)
    {
        return tbAlarmMapper.insertTbAlarm(tbAlarm);
    }

    /**
     * 修改告警管理
     *
     * @param tbAlarm 告警管理
     * @return 结果
     */
    @Override
    public int updateTbAlarm(TbAlarm tbAlarm)
    {
        return tbAlarmMapper.updateTbAlarm(tbAlarm);
    }

    /**
     * 批量删除告警管理
     *
     * @param ids 需要删除的告警管理主键
     * @return 结果
     */
    @Override
    public int deleteTbAlarmByIds(Long[] ids)
    {
        return tbAlarmMapper.deleteTbAlarmByIds(ids);
    }

    /**
     * 删除告警管理信息
     *
     * @param id 告警管理主键
     * @return 结果
     */
    @Override
    public int deleteTbAlarmById(Long id)
    {
        return tbAlarmMapper.deleteTbAlarmById(id);
    }

    @Override
    public TbAlarm verifyQuato(QuatoDTO quatoDTO) {

        //根据指标id查询判断告警规则列表
        TbAlarm tbAlarm = new TbAlarm();
        tbAlarm.setQuotaId(quatoDTO.getId());
        List<TbAlarm> alarmList = tbAlarmMapper.selectTbAlarmList(tbAlarm);
        alarmList.sort((TbAlarm a, TbAlarm b) -> a.getLevel().compareTo(b.getLevel()));

        TbAlarm alarm = null;
        for(TbAlarm alarmEntity : alarmList){

            if("String".equals(quatoDTO.getValueType()) || "Boolean".equals(quatoDTO.getValueType())){

                if(alarmEntity.getOperator().equals("=") && quatoDTO.getStringValue().equals(alarmEntity.getThreshold()) ){
                    alarm = alarmEntity;
                    break;
                }

            }else{  //数值
                //判断操作符 与指标对比
                if(alarmEntity.getOperator().equals(">") && quatoDTO.getValue() > alarmEntity.getThreshold()){
                    alarm = alarmEntity;
                    break;
                }

                if(alarmEntity.getOperator().equals("<") && quatoDTO.getValue() < alarmEntity.getThreshold()){
                    alarm = alarmEntity;
                    break;
                }

                if(alarmEntity.getOperator().equals("=") && quatoDTO.getValue().equals(alarmEntity.getThreshold()) ){
                    alarm = alarmEntity;
                    break;
                }
            }
        }
        return alarm;
    }

    @Override
    public DeviceInfoDTO verifyDeviceInfo(DeviceInfoDTO deviceInfoDTO) {

        //封装指标的告警   封装设备的告警
        DeviceDTO deviceDTO = deviceInfoDTO.getDevice();
        deviceDTO.setLevel((long)0); //假设不告警
        deviceDTO.setAlarm(false);
        deviceDTO.setAlarmName("正常");


        for(QuatoDTO quatoDTO: deviceInfoDTO.getQuatoList()){
            TbAlarm alarm = verifyQuato(quatoDTO);  //根据指标得到告警信息
            if(StringUtils.isNotNull(alarm)){ //如果指标存在告警
                quatoDTO.setAlarm("1");
                quatoDTO.setAlarmName(alarm.getName());
                quatoDTO.setLevel(alarm.getLevel()+"");
                quatoDTO.setWebhook(alarm.getWebhook());
                quatoDTO.setCycle(alarm.getCycle());

                if(alarm.getLevel().intValue() > deviceDTO.getLevel().intValue()){
                    deviceDTO.setLevel(alarm.getLevel());
                    deviceDTO.setAlarm(true);
                    deviceDTO.setAlarmName(alarm.getName());
                }

            }else{ //如果指标不存在告警
                quatoDTO.setAlarm("0");
                quatoDTO.setAlarmName("正常");
                quatoDTO.setLevel("0");
                quatoDTO.setWebhook("");
                quatoDTO.setCycle((long)0);
            }
        }

        return deviceInfoDTO;
    }
}
