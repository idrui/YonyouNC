/**
 * 
 */
package nc.vo.pu.cgfa;

import java.io.Serializable;

/**
 * @author  wangzym
 * @version 2017��5��9�� ����9:03:28
 */

/**
 * <p>
 * <b>������Ҫ������¹��ܣ� </b> ѯ���۵���Ĭ�ϻ�д��Ϣ
 * <ul>
 * <li>��дѮ���۵�VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author lizhengb
 * @time 2010-2-4 ����09:05:40
 */
public class BaseWriteBackVO implements Serializable {

	private static final long serialVersionUID = 5515463281294134474L;

	/** �빺������PK * */
	private String pk_equipment_id;
	
	/** �빺���ӱ�PK Դͷ����* */
	private String pk_praybill_b;

	private String pk_equipment_sub;

	/** �۸�����������PK * */
	private String pk_priceaudit;

	/** �۸��������ӱ�PK * */
	private String pk_priceaudit_b;
	private String operateFlag;

	/** ����ʱ��� * */
	private String strBTS;

	/** ��ͷʱ��� * */
	private String strHTS;

	/**
	 * ����ʱ���
	 * 
	 * @return strBTS
	 */
	public String getStrBTS() {
		return this.strBTS;
	}

	/**
	 * ��ͷʱ���
	 * 
	 * @return strHTS
	 */
	public String getStrHTS() {
		return this.strHTS;
	}

	/**
	 * @param pkDownbill
	 *            ���ε�������PK
	 */

	public void setPk_priceaudit(String pk_Askbill) {
		this.pk_equipment_id = pk_Askbill;
	}

	public void setPk_priceaudit_b(String pk_Askbill_b) {
		this.pk_equipment_sub = pk_Askbill_b;
	}

	/**
	 * @param strBTS
	 *            ����ʱ���
	 */
	public void setStrBTS(String strBTS) {
		this.strBTS = strBTS;
	}

	/**
	 * @param strHTS
	 *            ��ͷʱ���
	 */
	public void setStrHTS(String strHTS) {
		this.strHTS = strHTS;
	}

	/**
	 * @return pk_equipment_id
	 */
	public String getPk_equipment_id() {
		return pk_equipment_id;
	}

	/**
	 * @return pk_equipment_sub
	 */
	public String getPk_equipment_sub() {
		return pk_equipment_sub;
	}

	/**
	 * @return operateFlag
	 */
	public String getOperateFlag() {
		return operateFlag;
	}

	/**
	 * @param operateFlag
	 *            Ҫ���õ� operateFlag
	 */
	public void setOperateFlag(String operateFlag) {
		this.operateFlag = operateFlag;
	}

	/**
	 * @return pk_praybill_b
	 */
	public String getPk_praybill_b() {
		return pk_praybill_b;
	}

	/**
	 * @param pk_praybill_b Ҫ���õ� pk_praybill_b
	 */
	public void setPk_praybill_b(String pk_praybill_b) {
		this.pk_praybill_b = pk_praybill_b;
	}

}
