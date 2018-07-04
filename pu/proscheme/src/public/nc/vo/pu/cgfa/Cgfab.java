package nc.vo.pu.cgfa;

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
 *  ��������:2017-2-21
 * @author YONYOU NC
 * @version NCPrj ??
 */
 
public class Cgfab extends SuperVO {
	
/**
*�ϲ㵥������
*/
public static final String PK_EQUIPMENT_ID="pk_equipment_id";
/**
*ʱ���
*/
public static final String TS="ts";;
    
    
/**
* ���� �����ϲ�������Getter����.���������ϲ�����
*  ��������:2017-2-21
* @return String
*/
public String getPk_equipment_id(){
return (String)this.getAttributeValue(Cgfab.PK_EQUIPMENT_ID);
}
/**
* ���������ϲ�������Setter����.���������ϲ�����
* ��������:2017-2-21
* @param newPk_equipment_id String
*/
public void setPk_equipment_id(String pk_equipment_id){
this.setAttributeValue(Cgfab.PK_EQUIPMENT_ID,pk_equipment_id);
} 
/**
* ���� ����ʱ�����Getter����.��������ʱ���
*  ��������:2017-2-21
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return (UFDateTime)this.getAttributeValue(Cgfab.TS);
}
/**
* ��������ʱ�����Setter����.��������ʱ���
* ��������:2017-2-21
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.setAttributeValue(Cgfab.TS,ts);
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("pu.cgfab");
    }
   }
    