package nc.vo.pu.ms;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by shangguoqiang on 2017-04-05 11:23:57
 */
@XmlRootElement(name = "datarow")//采购计划跟踪
@XmlType(propOrder = { "mrid" , "mrlineid" , "type" , "stateid" , "name" , "optime"  })//设置XML中属性顺序
public class MsgAGCG000001 implements java.io.Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String mrid;   //需求计划号  长度:64
    String mrlineid;   //需求计划行号  长度:64
    String type;   //业务节点  长度:10
    String stateid;   //状态  长度:10
    String name;   //业务员名称  长度:64
    String optime;   //操作时间  长度:14

    public MsgAGCG000001() {
    }

    public MsgAGCG000001(
            String mrid, String mrlineid, String type, String stateid, String name, String optime
    ) {
        this.mrid = mrid;
        this.mrlineid = mrlineid;
        this.type = type;
        this.stateid = stateid;
        this.name = name;
        this.optime = optime;


    }

    @XmlElement(name = "mrId")
    public String getMrid() {
        return mrid;
    }

    public void setMrid(String mrid) {
        this.mrid = mrid;
    }

    @XmlElement(name = "mrLineId")
    public String getMrlineid() {
        return mrlineid;
    }

    public void setMrlineid(String mrlineid) {
        this.mrlineid = mrlineid;
    }

    @XmlElement(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlElement(name = "stateId")
    public String getStateid() {
        return stateid;
    }

    public void setStateid(String stateid) {
        this.stateid = stateid;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "opTime")
    public String getOptime() {
        return optime;
    }

    public void setOptime(String optime) {
        this.optime = optime;
    }


}

