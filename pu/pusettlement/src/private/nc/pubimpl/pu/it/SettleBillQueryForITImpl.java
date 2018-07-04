package nc.pubimpl.pu.it;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.ml.NCLangResOnserver;
import nc.pubitf.pu.it.ISettleBillQueryForIT;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m27.entity.SettleBillHeaderVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.enumeration.EnumSettleOrderType;
import nc.vo.pu.m4201.enumeration.StockQryFieldCode;
import nc.vo.pu.pub.rule.PUBillLazyQuery;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QueryConstants;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.pubapp.res.Variable;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

public class SettleBillQueryForITImpl implements ISettleBillQueryForIT {

  @Override
  public SettleBillVO[] query4IT(IQueryScheme queryscheme)
      throws BusinessException {
    try {
      QuerySchemeProcessor qsProc = new QuerySchemeProcessor(queryscheme);

      qsProc.appendCurrentGroup();
      String mainTableAlias = qsProc.getMainTableAlias();
      qsProc.appendWhere(this.appendWhereSql(mainTableAlias));
      return (SettleBillVO[]) new PUBillLazyQuery<SettleBillVO>(
          SettleBillVO.class, POBillType.SettleBill)
          .queryOrderByBillcode(queryscheme);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  @Override
  public StockSettleVO[] queryStockByQueryScheme4IT(IQueryScheme queryscheme)
      throws BusinessException {
    try {
      // �����صĲ�ѯ������
      StockQueryAdapterForIT queryAdapter =
          new StockQueryAdapterForIT(queryscheme);
      List<StockSettleVO> stocks = new ArrayList<StockSettleVO>();
      // �û���������в�ѯ����
      Map<String, QueryCondition> logicalConditionMap =
          (Map<String, QueryCondition>) queryscheme
              .get(QueryConstants.QUERY_CONDITION);
      // ��ѯ�ĵ�������
      QueryCondition queryBillTypes =
          logicalConditionMap.get(StockQryFieldCode.stockbilltype.code());
      // �Ƿ��к�ͬ��
      boolean hasvctcode =
          logicalConditionMap.containsKey(StockQryFieldCode.vctcode.code());

      // ÿ�ֵ������ͻ���Ϊһ��Ԫ�طŵ����飬��Ϊ��ѯʱ�������ͱ��䣬���ﲻ�����ǿ��ж�
      Object[] values = queryBillTypes.getValues();
      // ����4��
      for (Object value : values) {
        // ��ѯ�ɹ���
        if (ICBillType.PurchaseIn.getCode().equals(value)) {
          stocks.addAll(queryAdapter.queryStockByBillType(ICBillType.PurchaseIn
              .getCode()));
        }
        // added by wangzhqf 2014��5��20��13:48:27 ��ѯ������
        else if (ICBillType.GeneralIn.getCode().equals(value) && !hasvctcode) {
          stocks.addAll(queryAdapter.queryStockByBillType(ICBillType.GeneralIn
              .getCode()));
        }
      }
      // �����󷵻�����
      int maxRow = Variable.getMaxQueryCount();
      if (stocks.size() > maxRow) {
        ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
            .getStrByID("common", "04004000-0000", null, new String[] {
              String.valueOf(maxRow)
            })/* ��ѯ������ؼ�¼������{0}�������޸Ĳ�ѯ�������ٲ�ѯ��¼���� */);
      }
      if (stocks.size() > 0) {
        return stocks.toArray(new StockSettleVO[0]);
      }
      return null;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  private String appendWhereSql(String mainTableAlias) {
    SqlBuilder sb = new SqlBuilder();
    sb.append(" and ");
    sb.append(mainTableAlias + "." + SettleBillHeaderVO.FSETTLETYPE,
        EnumSettleOrderType.IT.toInt());
    return sb.toString();
  }
}
