package nc.vo.pu.newoaryxx;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> �˴���Ҫ�������๦�� </b>
 * <p>
 *   �˴�����۵�������Ϣ
 * </p>
 *  ��������:2016-11-30
 * @author yonyouBQ
 * @version NCPrj ??
 */
 
public class NewoaryxxHeadVO extends SuperVO {
	
/**
*ʱ���
*/
public static final String TS="ts";;
    
    
/**
* ���� ����ʱ�����Getter����.��������ʱ���
*  ��������:2016-11-30
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return (UFDateTime)this.getAttributeValue(NewoaryxxHeadVO.TS);
}
/**
* ��������ʱ�����Setter����.��������ʱ���
* ��������:2016-11-30
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.setAttributeValue(NewoaryxxHeadVO.TS,ts);
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("pu.newoaryxx");
    }
   }
    