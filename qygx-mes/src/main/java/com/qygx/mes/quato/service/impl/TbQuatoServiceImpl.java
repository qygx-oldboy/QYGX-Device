package com.qygx.mes.quato.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.qygx.common.utils.StringUtils;
import com.qygx.framework.emq.EmqClient;
import com.qygx.mes.quato.domain.dto.DeviceDTO;
import com.qygx.mes.quato.domain.dto.DeviceInfoDTO;
import com.qygx.mes.quato.domain.dto.QuatoDTO;
import com.qygx.system.domain.DeviceArchives;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.qygx.mes.quato.mapper.TbQuatoMapper;
import com.qygx.mes.quato.domain.TbQuato;
import com.qygx.mes.quato.service.ITbQuatoService;

import javax.annotation.PostConstruct;

/**
 * 指标管理Service业务层处理
 *
 * @author qygx
 * @date 2022-10-18
 */
@Service
public class TbQuatoServiceImpl implements ITbQuatoService
{
    @Autowired
    private TbQuatoMapper tbQuatoMapper;


    @Autowired
    private EmqClient emqClient;

    /**
     * 查询指标管理
     *
     * @param id 指标管理主键
     * @return 指标管理
     */
    @Override
    public TbQuato selectTbQuatoById(Long id)
    {
        return tbQuatoMapper.selectTbQuatoById(id);
    }

    /**
     * 查询指标管理列表
     *
     * @param tbQuato 指标管理
     * @return 指标管理
     */
    @Override
    public List<TbQuato> selectTbQuatoList(TbQuato tbQuato)
    {
        return tbQuatoMapper.selectTbQuatoList(tbQuato);
    }

    /**
     * 新增指标管理
     *
     * @param tbQuato 指标管理
     * @return 结果
     */
    @Override
    public int insertTbQuato(TbQuato tbQuato)
    {
        return tbQuatoMapper.insertTbQuato(tbQuato);
    }

    /**
     * 修改指标管理
     *
     * @param tbQuato 指标管理
     * @return 结果
     */
    @Override
    public int updateTbQuato(TbQuato tbQuato)
    {
        return tbQuatoMapper.updateTbQuato(tbQuato);
    }

    /**
     * 批量删除指标管理
     *
     * @param ids 需要删除的指标管理主键
     * @return 结果
     */
    @Override
    public int deleteTbQuatoByIds(Long[] ids)
    {
        return tbQuatoMapper.deleteTbQuatoByIds(ids);
    }

    /**
     * 删除指标管理信息
     *
     * @param id 指标管理主键
     * @return 结果
     */
    @Override
    public int deleteTbQuatoById(Long id)
    {
        return tbQuatoMapper.deleteTbQuatoById(id);
    }

    @Override
    public DeviceInfoDTO analysis(String topic, Map<String, Object> payloadMap) {
        //1.获取主题相关的指标配置
        TbQuato tbQuato = new TbQuato();
        tbQuato.setSubject(topic);
        List<TbQuato> quatoList = tbQuatoMapper.selectTbQuatoList(tbQuato); //获取列表配置
        if(quatoList.size() == 0)   return null;

        //2.封装设备信息
        String snKey = quatoList.get(0).getSnKey();
        if(StringUtils.isEmpty(snKey)) return null;

        String deviceCode = (String)payloadMap.get(snKey); //设备编号
        if(StringUtils.isEmpty(deviceCode)) return null;

        DeviceDTO device = new DeviceDTO();
        device.setDeviceCode(deviceCode);

        //3.封装指标列表信息
        List<QuatoDTO> quatoDTOList = new ArrayList<>();

        for (TbQuato quato : quatoList) {
            String quatoKey = quato.getValueKey(); //指标key
            if(payloadMap.containsKey(quatoKey)){
                QuatoDTO quatoDTO = new QuatoDTO();
                BeanUtils.copyProperties(quato,quatoDTO);
                quatoDTO.setQuatoName(quato.getName());

                if("String".equals(quatoDTO.getValueType()) || "Boolean".equals(quatoDTO.getValueType())){
                    quatoDTO.setStringValue((String)payloadMap.get(quatoKey));
                    quatoDTO.setValue(0d);
                }else{  //如果是数值
                    if(payloadMap.get(quatoKey) instanceof String){
                        quatoDTO.setValue(Double.valueOf((String) payloadMap.get(quatoKey)));
                        quatoDTO.setStringValue((String) payloadMap.get(quatoKey));
                    }else{
                        quatoDTO.setValue(Double.valueOf(payloadMap.get(quatoKey)+""));
                        quatoDTO.setStringValue(quatoDTO.getValue()+"");
                    }
                    quatoDTO.setDeviceCode(deviceCode);
                }

                quatoDTOList.add(quatoDTO);
            }
        }
        DeviceInfoDTO deviceInfoDTO = new DeviceInfoDTO();
        deviceInfoDTO.setDevice(device);
        deviceInfoDTO.setQuatoList(quatoDTOList);
        return deviceInfoDTO;
    }


    @PostConstruct   //程序启动时调用
    public void init(){
        System.out.println("初始化方法, EMQ订阅主题");
        emqClient.connect();
        tbQuatoMapper.selectAllProject().forEach( s ->{
            try {
                emqClient.subscribe("$queue/"+s,1);
            } catch (MqttException e) {
                e.printStackTrace();
            }
        });
    }
}
