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
 * 结算时入库单查询的适配器，基于查询方案构造不同入库单的查询<br>
 * “其他入库单”查询调用库存的服务，其余采购查询自己的副本。
 * 
 * @since 6.0
 * @version 2011-3-30 下午04:09:12
 * @author 田锋涛
 */

public class StockQueryAdapter {

  /**
   * 查询方案
   */
  private IQueryScheme queryscheme;

  /**
   * 构造函数
   * 
   * @param queryscheme 查询方案
   */
  public StockQueryAdapter(IQueryScheme queryscheme) {
    this.queryscheme = queryscheme;
  }

  /**
   * 获取 查询方案
   * 
   * @return queryscheme 查询方案
   */
  public IQueryScheme getQueryscheme() {
    return this.queryscheme;
  }

  /**
   * 根据单据类型决定查询何种入库单据的入口方法
   * 
   * @param queryscheme
   * @param billType
   * @return
   */
  public List<StockSettleVO> queryStockByBillType(String billType) {
    // 查询采购入
    if (ICBillType.PurchaseIn.getCode().equals(billType)) {
      return this.queryM45();
    }
    // 查询委托加工入
    else if (ICBillType.SubContinIn.getCode().equals(billType)) {
      return this.queryM47();
    }
    // 查询其他入
    else if (ICBillType.GeneralIn.getCode().equals(billType)) {
      return this.queryM4A();
    }
    // 查询期暂估入
    else if (POBillType.InitEstimate.getCode().equals(billType)) {
      return this.queryM4T();
    }
    return null;
  }

  // public void setIsIT(UFBoolean isIT) {
  // this.isIT = isIT;
  // }

  /**
   * 设置查询方案
   * 
   * @param queryscheme
   */
  public void setQueryscheme(IQueryScheme queryscheme) {
    this.queryscheme = queryscheme;
  }

  /**
   * 是否确认成本和应付
   * 
   * @param apField
   * @param iaField
   * @return
   */
  private String getConfirmAPIAWhereFor45() {
    Map<String, QueryCondition> logicalConditionMap =
        (Map<String, QueryCondition>) this.getQueryscheme().get(
            QueryConstants.QUERY_CONDITION);
    // 是否确认成本和应付
    QueryCondition settledCond =
        logicalConditionMap.get(StockQryFieldCode.bconfirmed.code());
    if (settledCond == null) {
      return "";
    }
    String[] values = settledCond.getValues();
    SqlBuilder sql = new SqlBuilder();
    sql.append(" and (");
    // 确认了成本或应付
    if (UFBoolean.TRUE.toString().equals(values[0])) {
      sql.append(PurchaseinFIItemVO.FTOAPFLAG, "=",
          EnumToAPFlag.ConfirmToAP.toInt());
      sql.append(" or ");
      sql.append(PurchaseinFIItemVO.FTOIAFLAG, "=",
          EnumToIAFlag.ConfirmToIA.toInt());
    }
    // 未确认成本或应付
    else {
      // 包括未传应付或者暂估了应付
      sql.append(PurchaseinFIItemVO.FTOAPFLAG, new int[] {
        EnumToAPFlag.NotToAP.toInt(), EnumToAPFlag.EstimateToAP.toInt()
      });
      sql.append(" and ");
      // 包括未传成本或者暂估了成本
      sql.append(PurchaseinFIItemVO.FTOIAFLAG, new int[] {
        EnumToIAFlag.NotToIA.toInt(), EnumToIAFlag.EstimateToIA.toInt()
      });

    }
    sql.append(" )");

    return sql.toString();
  }

  /**
   * 是否费用结算
   * 
   * @param queryScheme
   * @param feeField
   * @return
   */
  private String getFeeSettledWhere(String feeField) {
    return this.getSettledWhere(feeField, true);
  }

  /**
   * 是否结算
   * 
   * @param queryScheme
   * @param feeField
   * @return
   */
  private String getGoodSettledWhere(String feeField) {
    return this.getSettledWhere(feeField, false);
  }

  /**
   * 根据物料过滤
   * 
   * @param queryScheme
   * @param materialField 带别名的物料字段
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
   * 是否已经结算
   * 
   * @param queryScheme
   * @param feeField 判断字段，如累计结算金额等
   * @param feeSettle 是费用结算还是货物结算
   * @return
   */
  private String getSettledWhere(String feeField, boolean feeSettle) {
    // 用户输入的所有查询条件
    Map<String, QueryCondition> logicalConditionMap =
        (Map<String, QueryCondition>) this.getQueryscheme().get(
            QueryConstants.QUERY_CONDITION);
    String condtionField =
        feeSettle ? StockQryFieldCode.bfeesettled.code()
            : StockQryFieldCode.bsettled.code();
    // 是否按已查出的发票过滤
    QueryCondition settledCond = logicalConditionMap.get(condtionField);
    if (settledCond == null) {
      return "";
    }
    String[] values = settledCond.getValues();
    // 已结算，累计结算数量大于0
    if (UFBoolean.valueOf(values[0]).booleanValue()) {
      return " and " + feeField + " <> 0 ";
    }
    // 未结算，累计结算数量等于0

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
   * 查询采购入库
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
      // 查询采购财务入
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
   * 查询委外加工入库单
   * 
   * @return
   */
  private List<StockSettleVO> queryM47() {
    // 需要转换为实际表的查询
    NonMetaQuerySchemeProcessor processor =
        new NonMetaQuerySchemeProcessor(SubcontinFIHeaderVO.class,
            (Map<String, String>) this.getQueryscheme().get(
                ICBillType.SubContinIn.getCode()), this.getQueryscheme());
    StringBuilder fromWhere = new StringBuilder();
    fromWhere.append(processor.getFinalFromWhere());
    String itemTable = "po_subcontinfi_b";
    // 过滤物料
    fromWhere.append(this.getMaterialFilterWhere(itemTable + "."
        + SubcontinFIItemVO.PK_MATERIAL));
    // 是否已结算
    fromWhere.append(this
        .getGoodSettledWhere(SubcontinFIItemVO.NACCUMSETTLENUM));
    // 是否已经费用结算
    fromWhere.append(this
        .getFeeSettledWhere(SubcontinFIItemVO.NACCFEESETTLEMNY));
    // 过滤等0数量行
    fromWhere.append(" and isnull(" + SubcontinFIItemVO.NINNUM + ",0)<>0 ");
    // 过滤联副产品
    fromWhere.append(" and " + itemTable + "." + SubcontinFIItemVO.FOUTPUTTYPE + " = 1 ");
    return this.queryM47(fromWhere.toString());
  }

  /**
   * 查询委外加工入库单
   * 
   * @param m47fandw
   * @return
   */
  private List<StockSettleVO> queryM47(String m47fandw) {
    try {
      // 查询委托加工入结算数据
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
   * 查询期初暂估入库单
   * 
   * @return
   */
  private List<StockSettleVO> queryM4T() {
    // 需要转换为实际表的查询
    NonMetaQuerySchemeProcessor processor =
        new NonMetaQuerySchemeProcessor(InitialEstHeaderVO.class,
            (Map<String, String>) this.getQueryscheme().get(
                POBillType.InitEstimate.getCode()), this.getQueryscheme());

    StringBuilder fromWhere = new StringBuilder();
    fromWhere.append(processor.getFinalFromWhere());
    String itemTable = "po_initialest_b";
    // 过滤物料
    fromWhere.append(this.getMaterialFilterWhere(itemTable + "."
        + InitialEstItemVO.PK_MATERIAL));
    // 是否已结算
    fromWhere.append(this.getGoodSettledWhere(InitialEstItemVO.NACCSETTLENUM));
    // 是否已经费用结算
    fromWhere
        .append(this.getFeeSettledWhere(InitialEstItemVO.NACCFEESETTLEMNY));
    fromWhere.append(" and isnull(" + InitialEstItemVO.NNUM + ",0)<>0 ");// 过滤等0数量行

    return this.queryM4T(fromWhere.toString());
  }

  /**
   * 查询期初暂估入库单
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
      // 查询期初暂估入库单
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
   * 进口查询其他入调用不同的库存接口
   * 
   * @return
   * @throws BusinessException
   */
  protected GeneralInSettleVO[] queryGeneralInSettleVOs()
      throws BusinessException {
    GeneralInSettleVO[] m4avos;
    // 查询其他入库单
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
   * 查询采购入库
   * 
   * @return
   */
  protected List<StockSettleVO> queryM45() {

    // 需要转换为实际表的查询
    NonMetaQuerySchemeProcessor processor =
        new NonMetaQuerySchemeProcessor(PurchaseinFIHeaderVO.class,
            (Map<String, String>) this.getQueryscheme().get(
                ICBillType.PurchaseIn.getCode()), this.getQueryscheme());

    StringBuilder fromWhere = new StringBuilder();
    fromWhere.append(processor.getFinalFromWhere());
    String itemTable = "po_purchaseinfi_b";
    String headTable = "po_purchaseinfi";
    // 过滤物料
    fromWhere.append(this.getMaterialFilterWhere(itemTable + "."
        + PurchaseinFIItemVO.PK_MATERIAL));
    // 是否已结算
    fromWhere.append(this
        .getGoodSettledWhere(PurchaseinFIItemVO.NACCUMSETTLENUM));
    // 是否已经费用结算
    fromWhere.append(this
        .getFeeSettledWhere(PurchaseinFIItemVO.NACCFEESETTLEMNY));
    // 确认成本和应付
    fromWhere.append(this.getConfirmAPIAWhereFor45());
    fromWhere.append(" and isnull(" + PurchaseinFIItemVO.NINNUM + ",0)<>0 ");// 过滤等0数量行
    // mengjian 区分进口查询和采购查询
    fromWhere.append(this.getPUorITWhere(headTable + "."
        + PurchaseinFIHeaderVO.BITINBILL));
    return this.queryM45(fromWhere.toString());
  }

  /**
   * 查询其他入库单
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
      // 要根据发票物料过滤入库
      if (materialIds.size() > 0
          && !materialIds.contains(bodyVO.getCmaterialvid())) {
        continue;
      }
      // 为了维持现在的settlevo，以下两行代码不可少
      GeneralInHeadVO hvo = (GeneralInHeadVO) vo.getVO(GeneralInHeadVO.class);
      // TODO tianft 补全ts，ic查询表头ts有问题
      hvo.setTs(headerMap.get(hvo.getPrimaryKey()).getTs());
      vo.setHeadvo(hvo);
      vo.setBodyvo(bodyVO);
      stocks.add(vo);
    }

    this.setFinanceOrg(m4avos);

    return stocks;
  }
}
