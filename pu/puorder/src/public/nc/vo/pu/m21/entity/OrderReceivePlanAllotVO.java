/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-21 下午06:46:41
 */
package nc.vo.pu.m21.entity;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ValidationException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货计划分货VO类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-21 下午06:46:41
 */
public class OrderReceivePlanAllotVO extends CircularlyAccessibleValueObject {

	public static final String NALLOTASTNUM = "nallotastnum";

	public static final String NALLOTNUM = "nallotnum";

	public static final String NALLOTRATE = "nallotrate";

	public static final String NNUM = "nnum";

	public static final String PK_ARRVSTOORG = "pk_arrvstoorg";

	public static final String PK_ARRVSTOORG_V = "pk_arrvstoorg_v";

	private static final long serialVersionUID = -4112460742457707134L;

	// 分配数量
	private UFDouble nallotnum;

	// 分配比例
	private UFDouble nallotrate;

	// 主数量
	private UFDouble nnum;

	// 收货库存组织
	private String pk_arrvstoorg;

	private String pk_arrvstoorg_v;

	public OrderReceivePlanAllotVO() {
		super();
	}

	/**
	 * 父类方法重写
	 * 
	 * @see nc.vo.pub.CircularlyAccessibleValueObject#getAttributeNames()
	 */
	@Override
	public String[] getAttributeNames() {
		return new String[] { OrderReceivePlanAllotVO.NALLOTNUM,
				OrderReceivePlanAllotVO.NALLOTRATE,
				OrderReceivePlanAllotVO.PK_ARRVSTOORG,
				OrderReceivePlanAllotVO.PK_ARRVSTOORG_V, OrderReceivePlanAllotVO.NNUM };
	}

	/**
	 * 父类方法重写
	 * 
	 * @see nc.vo.pub.CircularlyAccessibleValueObject#getAttributeValue(java.lang.String)
	 */
	@Override
	public Object getAttributeValue(String attributeName) {
		if (OrderReceivePlanAllotVO.NALLOTNUM.equals(attributeName)) {
			return this.nallotnum;
		} else if (OrderReceivePlanAllotVO.NALLOTRATE.equals(attributeName)) {
			return this.nallotrate;
		} else if (OrderReceivePlanAllotVO.PK_ARRVSTOORG.equals(attributeName)) {
			return this.pk_arrvstoorg;
		} else if (OrderReceivePlanAllotVO.NNUM.equals(attributeName)) {
			return this.nnum;
		} else if (OrderReceivePlanAllotVO.PK_ARRVSTOORG_V.equals(attributeName)) {
			return this.pk_arrvstoorg_v;
		}
		return null;
	}

	/**
	 * 父类方法重写
	 * 
	 * @see nc.vo.pub.ValueObject#getEntityName()
	 */
	@Override
	public String getEntityName() {
		return "orderbb1";
	}

	/**
	 * @return nallotnum
	 */
	public UFDouble getNallotnum() {
		return this.nallotnum;
	}

	/**
	 * @return nallotrate
	 */
	public UFDouble getNallotrate() {
		return this.nallotrate;
	}

	/**
	 * @return nnum
	 */
	public UFDouble getNnum() {
		return this.nnum;
	}

	/**
	 * @return pk_arrvstoorg
	 */
	public String getPk_arrvstoorg() {
		return this.pk_arrvstoorg;
	}

	public String getPk_arrvstoorg_v() {
		return this.pk_arrvstoorg_v;
	}

	/**
	 * 父类方法重写
	 * 
	 * @see nc.vo.pub.CircularlyAccessibleValueObject#setAttributeValue(java.lang.String,
	 *      java.lang.Object)
	 */
	@Override
	public void setAttributeValue(String name, Object value) {
		try {
			if (OrderReceivePlanAllotVO.NALLOTNUM.equals(name)) {
				this.nallotnum = (UFDouble) value;
			} else if (OrderReceivePlanAllotVO.NALLOTRATE.equals(name)) {
				this.nallotrate = (UFDouble) value;
			} else if (OrderReceivePlanAllotVO.PK_ARRVSTOORG.equals(name)) {
				this.pk_arrvstoorg = (String) value;
			} else if (OrderReceivePlanAllotVO.NNUM.equals(name)) {
				this.nnum = (UFDouble) value;
			} else if (OrderReceivePlanAllotVO.PK_ARRVSTOORG_V.equals(name)) {
				this.pk_arrvstoorg_v = (String) value;
			}
		} catch (ClassCastException e) {
			ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
					.getStrByID("4004030_0", "04004030-0297", null,
							new String[] { name, (String) value })/*
																										 * setAttributeValue方法中为 {0}
																										 * 赋值时类型转换错误！（值：{1}）
																										 */);
		}
	}

	/**
	 * @param nallotnum
	 *          要设置的 nallotnum
	 */
	public void setNallotnum(UFDouble nallotnum) {
		this.nallotnum = nallotnum;
	}

	/**
	 * @param nallotrate
	 *          要设置的 nallotrate
	 */
	public void setNallotrate(UFDouble nallotrate) {
		this.nallotrate = nallotrate;
	}

	/**
	 * @param nnum
	 *          要设置的 nnum
	 */
	public void setNnum(UFDouble nnum) {
		this.nnum = nnum;
	}

	/**
	 * @param pkArrvstoorg
	 *          要设置的 pk_arrvstoorg
	 */
	public void setPk_arrvstoorg(String pkArrvstoorg) {
		this.pk_arrvstoorg = pkArrvstoorg;
	}

	public void setPk_arrvstoorg_v(String pk_arrvstoorg_v) {
		this.pk_arrvstoorg_v = pk_arrvstoorg_v;
	}

	/**
	 * 父类方法重写
	 * 
	 * @see nc.vo.pub.ValueObject#validate()
	 */
	@Override
	public void validate() throws ValidationException {
		return;
	}

}
