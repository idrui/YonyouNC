package nc.vo.pu.ms;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by shangguoqiang on 2017-04-05 11:23:57
 */
@XmlRootElement(name = "Message")//采购计划跟踪头
public class MsgAGCG000001Head implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String msgId;              //消息ID
    String resourceId;         //ResourceID
    List<MsgAGCG000001> msgBody;    //所有消息

    @XmlElement(name = "DataRow")
    public List<MsgAGCG000001> getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(List<MsgAGCG000001> msgBody) {
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
