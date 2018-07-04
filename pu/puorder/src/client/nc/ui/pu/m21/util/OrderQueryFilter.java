package nc.ui.pu.m21.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ic.general.define.MetaNameConst;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderOnwayItemVO;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

/**
 * 
 * @description 订单分页查询时非元数据查询条件处理
 * @scene 订单查询
 * @param
 * 
 * @functionName
 * 
 * @since 6.5
 * @version 2015-12-15 下午2:59:51
 * @author zhangshqb
 */
public class OrderQueryFilter {
	private IQueryScheme queryScheme;

	public OrderQueryFilter(IQueryScheme queryScheme) {
		this.queryScheme = queryScheme;
	}

	public void filter() {
		QuerySchemeProcessor qrySchemeProcessor = new QuerySchemeProcessor(
				queryScheme);
		qrySchemeProcessor.appendWhere(this.getOnwaystatusSql(qrySchemeProcessor));
	}

	private String getOnwaystatusSql(QuerySchemeProcessor qrySchemeProcessor) {
		QueryCondition cond = qrySchemeProcessor
				.getQueryCondition(OrderOnwayItemVO.FONWAYSTATUS);
		if (null == cond || null == cond.getValues() || cond.getValues().length < 1) {
			return "";
		}
		SqlBuilder sql = new SqlBuilder();
		Set<String> splitValueSet = new HashSet<String>(Arrays.asList(cond
				.getValues()));
		sql.append("(");
		// 如果选择了审批
		if (splitValueSet.contains(OnwayStatus.STATUS_AUDIT.toInteger().toString())) {
			this.appOnwayAuditSql(qrySchemeProcessor, sql);
		}
		// 如果选择了到货
		if (splitValueSet
				.contains(OnwayStatus.STATUS_ARRIVE.toInteger().toString())) {
			this.appOnwayArriveSql(qrySchemeProcessor, sql);
		}
		// 如果选择了入库
		if (splitValueSet.contains(OnwayStatus.STATUS_STORE.toInteger().toString())) {
			this.appOnwayStoreSql(qrySchemeProcessor, sql);
		}
		splitValueSet.remove(OnwayStatus.STATUS_AUDIT.toInteger().toString());
		splitValueSet.remove(OnwayStatus.STATUS_STORE.toInteger().toString());
		splitValueSet.remove(OnwayStatus.STATUS_ARRIVE.toInteger().toString());
		// 如果选择了其它在在途状态
		if (!splitValueSet.isEmpty()) {
			this.appOnwayStatusSql(qrySchemeProcessor, cond, sql);
		}
		sql.append(")");
		return " and " + sql.toString();
	}

	private void appOnwayStoreSql(QuerySchemeProcessor qrySchemeProcessor,
			SqlBuilder sql) {
		String btab = qrySchemeProcessor
				.getTableAliasOfAttribute(OrderItemVO.PK_ORDER_B + "."
						+ OrderItemVO.PK_ORDER_B);
		String storeSql = "exists (select 1 from ic_purchasein_b icb where icb.dr=0"
				+ " and icb."
				+ MetaNameConst.CFIRSTBILLBID
				+ "="
				+ btab
				+ "."
				+ OrderItemVO.PK_ORDER_B + ")";
		this.appendOrSql(sql, storeSql);
	}

	private void appOnwayArriveSql(QuerySchemeProcessor qrySchemeProcessor,
			SqlBuilder sql) {
		String btab = qrySchemeProcessor
				.getTableAliasOfAttribute(OrderItemVO.PK_ORDER_B + "."
						+ OrderItemVO.PK_ORDER_B);
		String arriveSql = "abs(" + btab + "." + OrderItemVO.NACCUMARRVNUM + ")>0";
		this.appendOrSql(sql, arriveSql);
	}

	private void appOnwayAuditSql(QuerySchemeProcessor qrySchemeProcessor,
			SqlBuilder sql) {
		String htab = qrySchemeProcessor.getMainTableAlias();
		String approveSql = htab + "." + OrderHeaderVO.FORDERSTATUS + "="
				+ POEnumBillStatus.APPROVE.toInteger();
		this.appendOrSql(sql, approveSql);
	}

	private void appendOrSql(SqlBuilder sql, String appSql) {
		if (sql.toString().equals("(")) {
			sql.append(appSql);
		} else {
			sql.append(" or ");
			sql.append(appSql);
		}
	}

	private void appOnwayStatusSql(QuerySchemeProcessor qrySchemeProcessor,
			QueryCondition cond, SqlBuilder sql) {
		String btab = qrySchemeProcessor
				.getTableAliasOfAttribute(OrderItemVO.PK_ORDER_B + "."
						+ OrderItemVO.PK_ORDER_B);
		SqlBuilder inSql = new SqlBuilder();
		inSql.append("", cond.getValues());
		String onwaySql = "exists (select 1 from " + PUEntity.M21_BB_TABLE
				+ " existsbb  where existsbb.dr=0 and existsbb."
				+ OrderOnwayItemVO.PK_ORDER_B + "=" + btab + "."
				+ OrderItemVO.PK_ORDER_B + " and existsbb."
				+ OrderOnwayItemVO.FONWAYSTATUS + " " + inSql.toString()
				+ " and existsbb.isoperated='Y')";
		this.appendOrSql(sql, onwaySql);
	}
}
