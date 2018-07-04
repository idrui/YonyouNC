package nc.bs.pu.m4203;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.pu.m27.entity.StockSettleVOUtil;
import nc.vo.pu.m27.entity.SubcontInSettleVO;
import nc.vo.pu.m4203.entity.SubcontinFIItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.res.Variable;
import nc.vo.scmpub.util.FromWhereParseUtil;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * ί�мӹ������Ĳ�ѯBP
 * 
 * @since 6.0
 * @version 2011-1-22 ����06:11:35
 * @author zhyhang
 */
public class SubcontinFIQueryBP {

  /**
   * ���ݴ���Ĳ�ѯ��������ѯ�ɽ����ί�мӹ������VO
   * 
   * @param frmWhr ��FROM��WHERE���ֵĲ�ѯSQL
   * @return
   */
  public SubcontInSettleVO[] settleQuery(String frmWhr) {
    if (StringUtils.isEmpty(frmWhr)) {
      return null;
    }
    String sql = this.getSettleQuerySql(frmWhr);
    DataAccessUtils dataAccessUtils = new DataAccessUtils();
    // ���ݲ�ѯ�����õ�����ID����
    String[] itemIDs = dataAccessUtils.query(sql).toOneDimensionStringArray();
    if (ArrayUtils.isEmpty(itemIDs)) {
      return null;
    }

    // �����󷵻�����
    int maxRow = Variable.getMaxQueryCount();
    if (itemIDs.length > maxRow) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("common", "04004000-0000", null, new String[] {
            String.valueOf(maxRow)
          })/* ��ѯ������ؼ�¼������{0}�������޸Ĳ�ѯ�������ٲ�ѯ��¼���� */);
    }

    // �����ӱ�ID��ѯ
    SubcontInSettleVO[] vos =
        new ViewQuery<SubcontInSettleVO>(SubcontInSettleVO.class)
            .query(itemIDs);
    // ����δ���������������ν���������
    StockSettleVOUtil.calcStockCanSettle(vos);
    return vos;
  }

  private String getSettleQuerySql(String frmWhr) {
    FromWhereParseUtil util = new FromWhereParseUtil(frmWhr);
    // String halias = util.getTableAlias(PUEntity.SUBCONTIN_H_TAB);
    String balias = util.getTableAlias(PUEntity.SUBCONTIN_B_TAB);
    SqlBuilder builder = new SqlBuilder();
    builder.append("select " + balias + "." + SubcontinFIItemVO.PK_STOCKPS_B);
    builder.append(" " + frmWhr);
    // builder.append(" and ");
    // builder.append(halias);
    // builder.append(".dr", 0);
    // builder.append(" and " + balias + ".dr", 0);
    return builder.toString();
  }

}
