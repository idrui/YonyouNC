/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-22 上午11:11:38
 */
package nc.impl.pu.m25;

import nc.bs.pu.m25.query.FeeInvoiceQueryBP;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.itf.pu.m25.IInvoiceQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.enumeration.InvoicePuImportEnum;
import nc.vo.pu.m25.pub.InvoicePlanPriceSetter;
import nc.vo.pu.pub.rule.PUBillLazyQuery;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购发票查询操作实现组件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-22 上午11:11:38
 */
public class InvoiceQueryImpl implements IInvoiceQuery {

	/**
	 * 根据ids查询货物发票视图。 add by liangchen1 NC631
	 * 
	 * @param pids
	 * @return
	 * @throws BusinessException
	 */
	@Override
	public int getInvoiceTypeByIds(String[] ids) throws BusinessException {
		if (ArrayUtils.isEmpty(ids)) {
			return InvoicePuImportEnum.PUINVOICE.toIntValue();
		}
		InvoiceHeaderVO[] vos = null;
		try {
			VOQuery<InvoiceHeaderVO> query = new VOQuery<InvoiceHeaderVO>(
					InvoiceHeaderVO.class, new String[] { InvoiceHeaderVO.PK_INVOICE,
							InvoiceHeaderVO.FINVOICETYPE });
			vos = query.query(ids);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		if (vos == null) {
			return InvoicePuImportEnum.PUINVOICE.toIntValue();
		}
		return vos[0].getFinvoicetype().intValue();
	}

	@Override
	public InvoiceVO[] queryByQueryScheme(IQueryScheme queryScheme)
			throws BusinessException {
		try {
			QuerySchemeProcessor proceesor = new QuerySchemeProcessor(queryScheme);
			// 添加集团信息
			proceesor.appendCurrentGroup();
			proceesor.appendFuncPermissionOrgSql();
			// add by liangchen1 NC631需求，区分普通采购与进出口采购
			String where = " and " + " isnull(" + InvoiceHeaderVO.FINVOICETYPE + ", "
					+ InvoicePuImportEnum.PUINVOICE.toInt() + ") = "
					+ InvoicePuImportEnum.PUINVOICE.toInt();
			proceesor.appendWhere(where);

			InvoiceVO[] vos = (InvoiceVO[]) new PUBillLazyQuery<InvoiceVO>(
					InvoiceVO.class, POBillType.Invoice)
					.queryOrderByBillcode(queryScheme);
			// TODO 是否需要等回来确定
			new InvoicePlanPriceSetter().setPlanPrice(vos);
			return vos;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	@Override
	public InvoiceVO[] queryFee(String[] pids) throws BusinessException {
		try {
			return new FeeInvoiceQueryBP().query(pids);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	@Override
	public InvoiceVO[] queryFreeFee(String[] pids) throws BusinessException {
		try {
			return new FeeInvoiceQueryBP().queryFree(pids);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

}
