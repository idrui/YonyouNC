package nc.impl.pu.m27;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.itf.pu.m4201.IStockFinanceQuery;
import nc.itf.pu.m4203.ISubcontinFIQuery;
import nc.pubitf.ic.m4a.IGeneralInServiceForPuFeeSettle;
import nc.pubitf.pu.m4t.pu.settle.IInitialEstSettleQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ic.m4a.entity.GeneralInBodyVO;
import nc.vo.ic.m4a.entity.GeneralInHeadVO;
import nc.vo.pu.m27.entity.GeneralInSettleVO;
import nc.vo.pu.m27.entity.InitialEstSettleVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4201.enumeration.EnumToAPFlag;
import nc.vo.pu.m4201.enumeration.EnumToIAFlag;
import nc.vo.pu.m4201.enumeration.StockQryFieldCode;
import nc.vo.pu.m4203.entity.SubcontinFIHeaderVO;
import nc.vo.pu.m4203.entity.SubcontinFIItemVO;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.NonMetaQuerySchemeProcessor;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QueryConstants;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * ����ʱ��ⵥ��ѯ�������������ڲ�ѯ�������첻ͬ��ⵥ�Ĳ�ѯ<br>
 * ��������ⵥ����ѯ���ÿ��ķ�������ɹ���ѯ�Լ��ĸ�����
 * 
 * @since 6.0
 * @version 2011-3-30 ����04:09:12
 * @author �����
 */

public class StockQueryAdapter {

  /**
   * ��ѯ����
   */
  private IQueryScheme queryscheme;

  /**
   * ���캯��
   * 
   * @param queryscheme ��ѯ����
   */
  public StockQueryAdapter(IQueryScheme queryscheme) {
    this.queryscheme = queryscheme;
  }

  /**
   * ��ȡ ��ѯ����
   * 
   * @return queryscheme ��ѯ����
   */
  public IQueryScheme getQueryscheme() {
    return this.queryscheme;
  }

  /**
   * ���ݵ������;�����ѯ������ⵥ�ݵ���ڷ���
   * 
   * @param queryscheme
   * @param billType
   * @return
   */
  public List<StockSettleVO> queryStockByBillType(String billType) {
    // ��ѯ�ɹ���
    if (ICBillType.PurchaseIn.getCode().equals(billType)) {
      return this.queryM45();
    }
    // ��ѯί�мӹ���
    else if (ICBillType.SubContinIn.getCode().equals(billType)) {
      return this.queryM47();
    }
    // ��ѯ������
    else if (ICBillType.GeneralIn.getCode().equals(billType)) {
      return this.queryM4A();
    }
    // ��ѯ���ݹ���
    else if (POBillType.InitEstimate.getCode().equals(billType)) {
      return this.queryM4T();
    }
    return null;
  }

  // public void setIsIT(UFBoolean isIT) {
  // this.isIT = isIT;
  // }

  /**
   * ���ò�ѯ����
   * 
   * @param queryscheme
   */
  public void setQueryscheme(IQueryScheme queryscheme) {
    this.queryscheme = queryscheme;
  }

  /**
   * �Ƿ�ȷ�ϳɱ���Ӧ��
   * 
   * @param apField
   * @param iaField
   * @return
   */
  private String getConfirmAPIAWhereFor45() {
    Map<String, QueryCondition> logicalConditionMap =
        (Map<String, QueryCondition>) this.getQueryscheme().get(
            QueryConstants.QUERY_CONDITION);
    // �Ƿ�ȷ�ϳɱ���Ӧ��
    QueryCondition settledCond =
        logicalConditionMap.get(StockQryFieldCode.bconfirmed.code());
    if (settledCond == null) {
      return "";
    }
    String[] values = settledCond.getValues();
    SqlBuilder sql = new SqlBuilder();
    sql.append(" and (");
    // ȷ���˳ɱ���Ӧ��
    if (UFBoolean.TRUE.toString().equals(values[0])) {
      sql.append(PurchaseinFIItemVO.FTOAPFLAG, "=",
          EnumToAPFlag.ConfirmToAP.toInt());
      sql.append(" or ");
      sql.append(PurchaseinFIItemVO.FTOIAFLAG, "=",
          EnumToIAFlag.ConfirmToIA.toInt());
    }
    // δȷ�ϳɱ���Ӧ��
    else {
      // ����δ��Ӧ�������ݹ���Ӧ��
      sql.append(PurchaseinFIItemVO.FTOAPFLAG, new int[] {
        EnumToAPFlag.NotToAP.toInt(), EnumToAPFlag.EstimateToAP.toInt()
      });
      sql.append(" and ");
      // ����δ���ɱ������ݹ��˳ɱ�
      sql.append(PurchaseinFIItemVO.FTOIAFLAG, new int[] {
        EnumToIAFlag.NotToIA.toInt(), EnumToIAFlag.EstimateToIA.toInt()
      });

    }
    sql.append(" )");

    return sql.toString();
  }

  /**
   * �Ƿ���ý���
   * 
   * @param queryScheme
   * @param feeField
   * @return
   */
  private String getFeeSettledWhere(String feeField) {
    return this.getSettledWhere(feeField, true);
  }

  /**
   * �Ƿ����
   * 
   * @param queryScheme
   * @param feeField
   * @return
   */
  private String getGoodSettledWhere(String feeField) {
    return this.getSettledWhere(feeField, false);
  }

  /**
   * �������Ϲ���
   * 
   * @param queryScheme
   * @param materialField �������������ֶ�
   * @return
   */
  private String getMaterialFilterWhere(String materialField) {
    Set<String> materialIds =
        (Set<String>) this.getQueryscheme().get(
            StockQryFieldCode.invoice_matrial_id.code());
    if (materialIds.size() == 0) {
      return "";
    }
    String[] invoiceMaterialIDs =
        materialIds.toArray(new String[materialIds.size()]);
    String marInWhr =
        new IDExQueryBuilder(PUTempTable.tmp_po_27_09.name()).buildSQL(
            materialField, invoiceMaterialIDs);
    return " and " + marInWhr;
  }

  /**
   * �Ƿ��Ѿ�����
   * 
   * @param queryScheme
   * @param feeField �ж��ֶΣ����ۼƽ������
   * @param feeSettle �Ƿ��ý��㻹�ǻ������
   * @return
   */
  private String getSettledWhere(String feeField, boolean feeSettle) {
    // �û���������в�ѯ����
    Map<String, QueryCondition> logicalConditionMap =
        (Map<String, QueryCondition>) this.getQueryscheme().get(
            QueryConstants.QUERY_CONDITION);
    String condtionField =
        feeSettle ? StockQryFieldCode.bfeesettled.code()
            : StockQryFieldCode.bsettled.code();
    // �Ƿ��Ѳ���ķ�Ʊ����
    QueryCondition settledCond = logicalConditionMap.get(condtionField);
    if (settledCond == null) {
      return "";
    }
    String[] values = settledCond.getValues();
    // �ѽ��㣬�ۼƽ�����������0
    if (UFBoolean.valueOf(values[0]).booleanValue()) {
      return " and " + feeField + " <> 0 ";
    }
    // δ���㣬�ۼƽ�����������0

    return " and isnull(" + feeField + ",0) = 0 ";

  }

  private Map<String, GeneralInHeadVO> queryGeneralInHeadVO(
      GeneralInSettleVO[] m4avos) {
    Set<String> hids = new HashSet<String>();
    for (GeneralInSettleVO vo : m4avos) {
      GeneralInHeadVO header =
          (GeneralInHeadVO) vo.getVO(GeneralInHeadVO.class);
      hids.add(header.getPrimaryKey());
    }
    VOQuery<GeneralInHeadVO> voquery =
        new VOQuery<GeneralInHeadVO>(GeneralInHeadVO.class);
    GeneralInHeadVO[] headers =
        voquery.query(hids.toArray(new String[hids.size()]));
    Map<String, GeneralInHeadVO> headerMap =
        new HashMap<String, GeneralInHeadVO>();
    for (GeneralInHeadVO vo : headers) {
      headerMap.put(vo.getPrimaryKey(), vo);
    }

    return headerMap;
  }

  /**
   * ��ѯ�ɹ����
   * 
   * @param m45fandw from+where
   * @return
   */
  private List<StockSettleVO> queryM45(String m45fandw) {
    List<StockSettleVO> stocks = new ArrayList<StockSettleVO>();
    if (StringUtils.isEmpty(m45fandw)) {
      return stocks;
    }
    StockSettleVO[] m45vos = null;
    try {
      // ��ѯ�ɹ�������
      IStockFinanceQuery serv =
          NCLocator.getInstance().lookup(IStockFinanceQuery.class);
      m45vos = serv.queryPurchaseInByWhereSql(m45fandw);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    if (m45vos == null || m45vos.length == 0) {
      return stocks;
    }
    return Arrays.asList(m45vos);
  }

  /**
   * ��ѯί��ӹ���ⵥ
   * 
   * @return
   */
  private List<StockSettleVO> queryM47() {
    // ��Ҫת��Ϊʵ�ʱ�Ĳ�ѯ
    NonMetaQuerySchemeProcessor processor =
        new NonMetaQuerySchemeProcessor(SubcontinFIHeaderVO.class,
            (Map<String, String>) this.getQueryscheme().get(
                ICBillType.SubContinIn.getCode()), this.getQueryscheme());
    StringBuilder fromWhere = new StringBuilder();
    fromWhere.append(processor.getFinalFromWhere());
    String itemTable = "po_subcontinfi_b";
    // ��������
    fromWhere.append(this.getMaterialFilterWhere(itemTable + "."
        + SubcontinFIItemVO.PK_MATERIAL));
    // �Ƿ��ѽ���
    fromWhere.append(this
        .getGoodSettledWhere(SubcontinFIItemVO.NACCUMSETTLENUM));
    // �Ƿ��Ѿ����ý���
    fromWhere.append(this
        .getFeeSettledWhere(SubcontinFIItemVO.NACCFEESETTLEMNY));
    // ���˵�0������
    fromWhere.append(" and isnull(" + SubcontinFIItemVO.NINNUM + ",0)<>0 ");
    // ����������Ʒ
    fromWhere.append(" and " + itemTable + "." + SubcontinFIItemVO.FOUTPUTTYPE + " = 1 ");
    return this.queryM47(fromWhere.toString());
  }

  /**
   * ��ѯί��ӹ���ⵥ
   * 
   * @param m47fandw
   * @return
   */
  private List<StockSettleVO> queryM47(String m47fandw) {
    try {
      // ��ѯί�мӹ����������
      ISubcontinFIQuery serv =
          NCLocator.getInstance().lookup(ISubcontinFIQuery.class);
      StockSettleVO[] vos = serv.settleQuery(m47fandw);
      return ArrayUtils.isEmpty(vos) ? new ArrayList<StockSettleVO>() : Arrays
          .asList(vos);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  /**
   * ��ѯ�ڳ��ݹ���ⵥ
   * 
   * @return
   */
  private List<StockSettleVO> queryM4T() {
    // ��Ҫת��Ϊʵ�ʱ�Ĳ�ѯ
    NonMetaQuerySchemeProcessor processor =
        new NonMetaQuerySchemeProcessor(InitialEstHeaderVO.class,
            (Map<String, String>) this.getQueryscheme().get(
                POBillType.InitEstimate.getCode()), this.getQueryscheme());

    StringBuilder fromWhere = new StringBuilder();
    fromWhere.append(processor.getFinalFromWhere());
    String itemTable = "po_initialest_b";
    // ��������
    fromWhere.append(this.getMaterialFilterWhere(itemTable + "."
        + InitialEstItemVO.PK_MATERIAL));
    // �Ƿ��ѽ���
    fromWhere.append(this.getGoodSettledWhere(InitialEstItemVO.NACCSETTLENUM));
    // �Ƿ��Ѿ����ý���
    fromWhere
        .append(this.getFeeSettledWhere(InitialEstItemVO.NACCFEESETTLEMNY));
    fromWhere.append(" and isnull(" + InitialEstItemVO.NNUM + ",0)<>0 ");// ���˵�0������

    return this.queryM4T(fromWhere.toString());
  }

  /**
   * ��ѯ�ڳ��ݹ���ⵥ
   * 
   * @param m4Tfandw from+where
   * @return
   */
  private List<StockSettleVO> queryM4T(String m4Tfandw) {
    List<StockSettleVO> stocks = new ArrayList<StockSettleVO>();
    if (StringUtils.isEmpty(m4Tfandw)) {
      return stocks;
    }
    InitialEstSettleVO[] m4avos = null;
    try {
      // ��ѯ�ڳ��ݹ���ⵥ
      IInitialEstSettleQuery serv =
          NCLocator.getInstance().lookup(IInitialEstSettleQuery.class);
      m4avos = serv.queryByCond(m4Tfandw);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    if (m4avos == null || m4avos.length == 0) {
      return stocks;
    }

    for (InitialEstSettleVO vo : m4avos) {
      stocks.add(vo);
    }
    return stocks;
  }

  private void setFinanceOrg(GeneralInSettleVO[] vos) {
    QuerySchemeProcessor processor =
        new QuerySchemeProcessor(this.getQueryscheme());
    QueryCondition condition =
        processor.getQueryCondition(StockQryFieldCode.financeorgbody.code());
    String[] financeOrg = condition.getValues();
    for (GeneralInSettleVO vo : vos) {
      vo.setPk_financeorg(financeOrg[0]);
    }
  }

  protected Object getPUorITWhere(String field) {
    return " and  isnull(" + field + ", 'N') = 'N'";
  }

  /**
   * ���ڲ�ѯ��������ò�ͬ�Ŀ��ӿ�
   * 
   * @return
   * @throws BusinessException
   */
  protected GeneralInSettleVO[] queryGeneralInSettleVOs()
      throws BusinessException {
    GeneralInSettleVO[] m4avos;
    // ��ѯ������ⵥ
    IGeneralInServiceForPuFeeSettle serv =
        NCLocator.getInstance().lookup(IGeneralInServiceForPuFeeSettle.class);
    m4avos =
        serv.queryGeneralInSettleVOsByScheme(
            this.getQueryscheme(),
            (Map<String, String>) this.getQueryscheme().get(
                ICBillType.GeneralIn.getCode()));
    return m4avos;
  }

  /**
   * ��ѯ�ɹ����
   * 
   * @return
   */
  protected List<StockSettleVO> queryM45() {

    // ��Ҫת��Ϊʵ�ʱ�Ĳ�ѯ
    NonMetaQuerySchemeProcessor processor =
        new NonMetaQuerySchemeProcessor(PurchaseinFIHeaderVO.class,
            (Map<String, String>) this.getQueryscheme().get(
                ICBillType.PurchaseIn.getCode()), this.getQueryscheme());

    StringBuilder fromWhere = new StringBuilder();
    fromWhere.append(processor.getFinalFromWhere());
    String itemTable = "po_purchaseinfi_b";
    String headTable = "po_purchaseinfi";
    // ��������
    fromWhere.append(this.getMaterialFilterWhere(itemTable + "."
        + PurchaseinFIItemVO.PK_MATERIAL));
    // �Ƿ��ѽ���
    fromWhere.append(this
        .getGoodSettledWhere(PurchaseinFIItemVO.NACCUMSETTLENUM));
    // �Ƿ��Ѿ����ý���
    fromWhere.append(this
        .getFeeSettledWhere(PurchaseinFIItemVO.NACCFEESETTLEMNY));
    // ȷ�ϳɱ���Ӧ��
    fromWhere.append(this.getConfirmAPIAWhereFor45());
    fromWhere.append(" and isnull(" + PurchaseinFIItemVO.NINNUM + ",0)<>0 ");// ���˵�0������
    // mengjian ���ֽ��ڲ�ѯ�Ͳɹ���ѯ
    fromWhere.append(this.getPUorITWhere(headTable + "."
        + PurchaseinFIHeaderVO.BITINBILL));
    return this.queryM45(fromWhere.toString());
  }

  /**
   * ��ѯ������ⵥ
   * 
   * @return
   */
  protected List<StockSettleVO> queryM4A() {
    List<StockSettleVO> stocks = new ArrayList<StockSettleVO>();

    GeneralInSettleVO[] m4avos = null;
    try {
      m4avos = this.queryGeneralInSettleVOs();
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    if (m4avos == null || m4avos.length == 0) {
      return stocks;
    }

    Map<String, GeneralInHeadVO> headerMap = this.queryGeneralInHeadVO(m4avos);
    Set<String> materialIds =
        (Set<String>) this.getQueryscheme().get(
            StockQryFieldCode.invoice_matrial_id.code());
    for (GeneralInSettleVO vo : m4avos) {
      GeneralInBodyVO bodyVO =
          (GeneralInBodyVO) vo.getVO(GeneralInBodyVO.class);
      // Ҫ���ݷ�Ʊ���Ϲ������
      if (materialIds.size() > 0
          && !materialIds.contains(bodyVO.getCmaterialvid())) {
        continue;
      }
      // Ϊ��ά�����ڵ�settlevo���������д��벻����
      GeneralInHeadVO hvo = (GeneralInHeadVO) vo.getVO(GeneralInHeadVO.class);
      // TODO tianft ��ȫts��ic��ѯ��ͷts������
      hvo.setTs(headerMap.get(hvo.getPrimaryKey()).getTs());
      vo.setHeadvo(hvo);
      vo.setBodyvo(bodyVO);
      stocks.add(vo);
    }

    this.setFinanceOrg(m4avos);

    return stocks;
  }
}
