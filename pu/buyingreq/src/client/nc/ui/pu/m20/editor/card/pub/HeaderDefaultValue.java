/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-3 上午10:45:30
 */
package nc.ui.pu.m20.editor.card.pub;

import java.util.Map;

import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.enumeration.EnumPraySource;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.rule.SetPeptRule;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.uif2.LoginContext;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>完成新增操作时，表头表尾默认值设置
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-2-3 上午10:45:30
 */
public class HeaderDefaultValue {
	private final LoginContext ctx;

	private final IKeyValue keyValue;

	public HeaderDefaultValue(IKeyValue keyValue, LoginContext ctx) {
		this.keyValue = keyValue;
		this.ctx = ctx;
	}

	/**
	 * 方法功能描述：简要描述本方法的功能。
	 * <p>
	 * <ul>
	 * <li>设置集团、库存组织、制单人、单据日期、制单日期、最后修改人、最后修改时间、版本、单据状态
	 * </ul>
	 * <b>参数说明</b>
	 * <p>
	 * 
	 * @since 6.0
	 * @author linsf
	 * @time 2010-2-3 上午10:53:01
	 */
	public void setDefaultValue() {

		UFDate busidate = AppContext.getInstance().getBusiDate();
		this.keyValue.setHeadValue(PraybillHeaderVO.BILLMAKER,
				this.ctx.getPk_loginUser());
		this.keyValue.setHeadValue(PraybillHeaderVO.PK_GROUP,
				this.ctx.getPk_group());
		this.keyValue.setHeadValue(PraybillHeaderVO.PK_ORG, this.ctx.getPk_org());
		this.keyValue.setHeadValue(PraybillHeaderVO.DBILLDATE, busidate);
		this.keyValue.setHeadValue(PraybillHeaderVO.NVERSION, Integer.valueOf(1));
		this.keyValue.setHeadValue(PraybillHeaderVO.FBILLSTATUS,
				POEnumBillStatus.FREE.toInteger());
		this.keyValue.setHeadValue(PraybillHeaderVO.FPRAYSOURCE,
				EnumPraySource.MANUAL.toInteger());

		// 表头 本币币种：调整为取需求库存组织所属财务组织的本位币
		this.keyValue.setHeadValue(PraybillHeaderVO.CCURRENCYID, this.getOrgCurr());

		// 设置计划员和计划部门
		new SetPeptRule(this.keyValue, this.ctx, PraybillHeaderVO.PK_PLANPSN,
				PraybillHeaderVO.PK_PLANDEPT, PraybillHeaderVO.PK_PLANDEPT_V)
				.setPsnAndDept();

		// 设置主组织版本
		 this.setpkorgV();

	}

	private String getOrgCurr() {
		String pk_org = this.ctx.getPk_org();
		if (null == pk_org) {
			return null;
		}

		Map<String, String> finance = StockOrgPubService
				.queryFinanceOrgIDByStockOrgID(new String[] { pk_org });
		if (null != finance) {
			return OrgUnitPubService.queryOrgCurrByPk(finance.get(pk_org));
		}

		return null;
	}

	 private void setpkorgV() {

	    String pk_org = this.ctx.getPk_org();

	    if (!StringUtil.isEmptyWithTrim(pk_org)) {
	      this.keyValue.setHeadValue(PraybillHeaderVO.PK_ORG_V,
	          OrgUnitPubService.getNewVIDByOrgID(pk_org));
	    }
	  }
	
}
