package nc.vo.pu.m21.entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by shangguoqiang on 2017-03-06 09:06:22
 */
@XmlRootElement(name = "Message")//攀钢销售系统销售订单头
public class MsgXSAGHT0001Head {
    String msgId;              //消息ID
    String resourceId;         //ResourceID
    List<MsgXSAGHT0001> msgBody;    //所有消息

    @XmlElement(name = "DataRow")
    public List<MsgXSAGHT0001> getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(List<MsgXSAGHT0001> msgBody) {
        this.msgBody = msgBody;
    }

    @XmlAttribute(name = "MsgID")
    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    @XmlAttribute(name = "ResourceID")
    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

}
