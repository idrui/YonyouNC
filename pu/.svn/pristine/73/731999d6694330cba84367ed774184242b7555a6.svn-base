package nc.pubimpl.pu.m25.pu.settle;

import java.util.HashMap;
import java.util.Map;

import nc.bs.pu.m25.query.pu.InvoiceQueryForSettleBP;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.pubitf.pu.m25.pu.settle.IInvoiceSettleQuery;
import nc.pubitf.pu.m25.pu.settle.InvoiceQueryResultFor27;
import nc.ui.querytemplate.querytree.FromWhereSQL;
import nc.ui.querytemplate.querytree.FromWhereSQLImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.querytemplate.querytree.QueryScheme;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.enumeration.InvoiceQryFieldCode;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QueryConstants;
import nc.vo.pubapp.util.MetaUtils;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>为采购结算提供的查询服务实现
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-19 下午05:01:59
 */
public class InvoiceSettleQueryImpl implements IInvoiceSettleQuery {

  @Override
  public boolean isExistsSentAP(String[] invoiceHIDs) throws BusinessException {
    if (ArrayUtils.isEmpty(invoiceHIDs)) {
      return false;
    }
    try {
      VOQuery<InvoiceHeaderVO> query =
          new VOQuery<InvoiceHeaderVO>(InvoiceHeaderVO.class, new String[] {
            InvoiceHeaderVO.BAPFLAG, InvoiceHeaderVO.PK_INVOICE
          });
      InvoiceHeaderVO[] heads = query.query(invoiceHIDs);
      if (ArrayUtils.isEmpty(heads)) {
        return false;
      }
      for (InvoiceHeaderVO head : heads) {
        if (ValueUtils.getBoolean(head.getBapflag())) {
          return true;
        }
      }
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return false;
  }

  @Override
  public InvoiceQueryResultFor27 queryByWhereSql(IQueryScheme queryScheme)
      throws BusinessException {
    try {
      return new InvoiceQueryForSettleBP().queryByWhereSql(queryScheme);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public InvoiceQueryResultFor27 queryGoodsAndRelaFeeByHID(String invoiceHId,
      String queryType) throws BusinessException {
    try {
      // 模拟创建一个前台查询条件
      QueryScheme qs = this.createQueryScheme(invoiceHId, queryType);
      return new InvoiceQueryForSettleBP().queryByWhereSql(qs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
      return null;
    }
  }

  /**
   * 父类方法重写
   * 
   * @see nc.pubitf.pu.m25.pu.settle.IInvoiceSettleQuery#queryPrice(java.lang.String[])
   */
  @Override
  public Map<String, UFDouble> queryPrice(String[] bids)
      throws BusinessException {
    Map<String, UFDouble> retMap = new HashMap<String, UFDouble>();
    if (ArrayUtils.isEmpty(bids)) {
      return retMap;
    }
    try {
      VOQuery<InvoiceItemVO> query =
          new VOQuery<InvoiceItemVO>(InvoiceItemVO.class, new String[] {
            InvoiceItemVO.PK_INVOICE_B, InvoiceItemVO.NPRICE
          });
      InvoiceItemVO[] items = query.query(bids);
      if (ArrayUtils.isEmpty(items)) {
        return retMap;
      }
      for (InvoiceItemVO item : items) {
        retMap.put(item.getPk_invoice_b(), item.getNprice());
      }
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return retMap;
  }

  @Override
  public Map<String, UFDouble> queryTaxPrice(String[] bids)
      throws BusinessException {
    Map<String, UFDouble> retMap = new HashMap<String, UFDouble>();
    if (ArrayUtils.isEmpty(bids)) {
      return retMap;
    }
    try {
      VOQuery<InvoiceItemVO> query =
          new VOQuery<InvoiceItemVO>(InvoiceItemVO.class, new String[] {
            InvoiceItemVO.PK_INVOICE_B, InvoiceItemVO.NTAXPRICE
          });
      InvoiceItemVO[] items = query.query(bids);
      if (ArrayUtils.isEmpty(items)) {
        return retMap;
      }
      for (InvoiceItemVO item : items) {
        retMap.put(item.getPk_invoice_b(), item.getNtaxprice());
      }
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return retMap;
  }

  private QueryScheme createQueryScheme(String invoiceHId, String queryType)
      throws BusinessException {
    SqlBuilder from = new SqlBuilder();
    from.append(PUEntity.M25_H_TAB + " " + PUEntity.M25_H_TAB);
    SqlBuilder where = new SqlBuilder();
    where.append(PUEntity.M25_H_TAB + "." + InvoiceHeaderVO.PK_INVOICE,
        invoiceHId);
    FromWhereSQLImpl fwsql =
        new FromWhereSQLImpl(from.toString(), where.toString());
    Map<String, String> attAlsMap = new HashMap<String, String>();
    attAlsMap.put(FromWhereSQL.DEFAULT_ATTRPATH, PUEntity.M25_H_TAB);
    fwsql.setAttrpath_alias_map(attAlsMap);
    String invcbeanid =
        MetaUtils.getBusinessEntityByBillType(POBillType.Invoice.getCode())
            .getID();
    String[] incRelaFee = {
      UFBoolean.TRUE.toString()
    };
    QueryCondition qc =
        new QueryCondition(InvoiceQryFieldCode.brelafeeinclude.code(), "=",
            incRelaFee);
    Map<String, QueryCondition> qcMap = new HashMap<String, QueryCondition>();
    qcMap.put(qc.getFieldCode(), qc);
    QueryScheme qs = new QueryScheme();
    qs.putTableJoinFromWhereSQL(fwsql);
    qs.put(QueryConstants.BEAN_ID, invcbeanid);
    qs.put(QueryConstants.QUERY_CONDITION, qcMap);
    qs.put(IInvoiceSettleQuery.QRY_TYPE_KEY, queryType);
    return qs;
  }

}
