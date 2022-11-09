package com.qygx.framework.emq;

import com.qygx.common.utils.uuid.UUID;
import com.qygx.framework.config.EmqConfig;
import org.eclipse.paho.mqttv5.client.MqttClient;
import org.eclipse.paho.mqttv5.client.persist.MemoryPersistence;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmqClient {


    @Autowired
    private EmqConfig emqConfig;

    private MqttClient mqttClient;  //客户端连接

    @Autowired
    private EmqMsgProcess emqMsgProcess;

    /**
     * 连接emq
     */
    public void connect(){
        try {

            mqttClient = new MqttClient(emqConfig.getMqttServerUrl(),"monitor."+ UUID.randomUUID(),new MemoryPersistence());
            mqttClient.setCallback(emqMsgProcess);
            mqttClient.connect();

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送消息
     * @param topic 主题
     * @param msg 消息内同
     */
    public void publish(String topic,String msg){
        MqttMessage mqttMessage = new MqttMessage(msg.getBytes());
        try {
            mqttClient.getTopic(topic).publish(mqttMessage);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void subscribe(String topicFilter, int qos) throws MqttException {
        mqttClient.subscribe(topicFilter,qos);
    }

    public void close() {
        try {
            mqttClient.disconnect();
            mqttClient.close();
        } catch (MqttException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
