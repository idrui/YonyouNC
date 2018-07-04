/**
 * 
 */
package nc.vo.pu.cgfa;

import java.io.Serializable;

/**
 * @author  wangzym
 * @version 2017年5月9日 上午9:03:28
 */

/**
 * <p>
 * <b>本类主要完成以下功能： </b> 询报价单单默认回写信息
 * <ul>
 * <li>回写旬报价单VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author lizhengb
 * @time 2010-2-4 上午09:05:40
 */
public class BaseWriteBackVO implements Serializable {

	private static final long serialVersionUID = 5515463281294134474L;

	/** 请购单主表PK * */
	private String pk_equipment_id;
	
	/** 请购单子表PK 源头主键* */
	private String pk_praybill_b;

	private String pk_equipment_sub;

	/** 价格审批单主表PK * */
	private String pk_priceaudit;

	/** 价格审批单子表PK * */
	private String pk_priceaudit_b;
	private String operateFlag;

	/** 表体时间戳 * */
	private String strBTS;

	/** 表头时间戳 * */
	private String strHTS;

	/**
	 * 表体时间戳
	 * 
	 * @return strBTS
	 */
	public String getStrBTS() {
		return this.strBTS;
	}

	/**
	 * 表头时间戳
	 * 
	 * @return strHTS
	 */
	public String getStrHTS() {
		return this.strHTS;
	}

	/**
	 * @param pkDownbill
	 *            下游单据主表PK
	 */

	public void setPk_priceaudit(String pk_Askbill) {
		this.pk_equipment_id = pk_Askbill;
	}

	public void setPk_priceaudit_b(String pk_Askbill_b) {
		this.pk_equipment_sub = pk_Askbill_b;
	}

	/**
	 * @param strBTS
	 *            表体时间戳
	 */
	public void setStrBTS(String strBTS) {
		this.strBTS = strBTS;
	}

	/**
	 * @param strHTS
	 *            表头时间戳
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
	 *            要设置的 operateFlag
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
	 * @param pk_praybill_b 要设置的 pk_praybill_b
	 */
	public void setPk_praybill_b(String pk_praybill_b) {
		this.pk_praybill_b = pk_praybill_b;
	}

}
