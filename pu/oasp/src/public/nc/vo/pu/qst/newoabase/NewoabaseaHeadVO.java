package nc.vo.pu.qst.newoabase;

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
 *  ��������:2016-11-17
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class NewoabaseaHeadVO extends SuperVO {
	
/**
*ʱ���
*/
public static final String TS="ts";;
    
    
/**
* ���� ����ʱ�����Getter����.��������ʱ���
*  ��������:2016-11-17
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return (UFDateTime)this.getAttributeValue(NewoabaseaHeadVO.TS);
}
/**
* ��������ʱ�����Setter����.��������ʱ���
* ��������:2016-11-17
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.setAttributeValue(NewoabaseaHeadVO.TS,ts);
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("pu.newoabase_a");
    }
   }
    