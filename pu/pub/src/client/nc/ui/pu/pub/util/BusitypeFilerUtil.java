package nc.ui.pu.pub.util;

import java.util.List;

import nc.ui.pubapp.billref.src.RefContext;
import nc.ui.querytemplate.querytree.FromWhereSQLImpl;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

import org.apache.commons.collections.CollectionUtils;

/**
 * ���̹��˹�����
 *
 * @since 6.0
 * @version 2011-7-21 ����11:17:27
 * @author wuxla
 */

public class BusitypeFilerUtil {

  /**
   * ƴ���̺ͽ������͹���
   *
   * @param qs
   * @param context
   */
  public static void appendWhr(IQueryScheme qs, RefContext context,
      boolean bizflow) {
    QuerySchemeProcessor qsp = new QuerySchemeProcessor(qs);
    String halias = qsp.getMainTableAlias();

    SqlBuilder newwhr = new SqlBuilder();
    BusitypeFilerUtil.appendTrantype(context, halias, newwhr);
    BusitypeFilerUtil.appendBusitype(context, halias, newwhr, bizflow);
    if (0 == newwhr.toString().length()) {
      return;
    }

    FromWhereSQLImpl fromWhereSQL =
        (FromWhereSQLImpl) qs.getTableJoinFromWhereSQL();
    String where = fromWhereSQL.getWhere();
    SqlBuilder whr = new SqlBuilder();
    whr.append(where);
    whr.append(newwhr.toString());
    fromWhereSQL.setWhere(whr.toString());
  }

  /**
   * ƴ���̹���
   *
   * @param context
   * @param halias
   * @param whr
   */
  private static void appendBusitype(RefContext context, String halias,
      SqlBuilder whr, boolean bizflow) {
    List<String> busiLst = context.getBillReferQuery().getBusitypes();
    if (CollectionUtils.isEmpty(busiLst)) {
      if (bizflow) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0","04004000-0049")/*@res "����δ��Ҫ��ѯ���ݵ�ҵ�����̴��ݣ����ܽ��в���������"*/);
      }
      return;
    }

    whr.append(" and ");
    whr.append(halias);
    whr.append(".");
    whr.append(OrderHeaderVO.PK_BUSITYPE, busiLst.toArray(new String[busiLst
        .size()]));
    whr.append(" ");
  }

  /**
   * ƴ��������
   *
   * @param context
   * @param halias
   * @param whr
   */
  private static void appendTrantype(RefContext context, String halias,
      SqlBuilder whr) {
    List<String> tranList = context.getBillReferQuery().getTranstypes();
    if (CollectionUtils.isEmpty(tranList)) {
      return;
    }

    whr.append(" and " + halias + "." + OrderHeaderVO.VTRANTYPECODE, tranList
        .toArray(new String[tranList.size()]));
    whr.append(" ");
  }
}