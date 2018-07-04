package nc.impl.pu.m27.merge;

import java.util.ArrayList;
import java.util.List;

import nc.bs.ml.NCLangResOnserver;
import nc.bs.pu.m27.match.rule.AutoMatchFeeDistributeRule;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.ICostfactorDiscountUtil;
import nc.vo.pu.m27.entity.InvoiceSettleVOUtil;
import nc.vo.pu.m27.entity.InvoiceStockOptionableVO;
import nc.vo.pu.m27.entity.RBInvoiceOptionableVO;
import nc.vo.pu.m27.entity.RBStockOptionableVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.m27.rule.AutoMatchRule;
import nc.vo.pu.pub.util.ListUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.pattern.pub.PubAppTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>自动匹配</b>
 * <ul>
 * <li>功能条目1
 * <li>功能条目2
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author wangyf
 * @time 2010-3-25 上午10:26:02
 */
public class AutoMatchMerge extends SameMaterialMerge {

  /** 未模拟匹配的进口货物调整VO **/
  private FeeDiscountSettleVO[] origAdjustVos;

  /** 未模拟匹配的折扣发票VO **/
  private FeeDiscountSettleVO[] origDiscountVos;

  /** 未模拟匹配的费用发票VO **/
  private FeeDiscountSettleVO[] origFeeVos;

  /** 未模拟匹配的货物发票VO **/
  private InvoiceSettleVO[] origISVos;

  /** 未模拟匹配的库存结算VO **/
  private StockSettleVO[] origSSVos;

  public AutoMatchMerge(InvoiceSettleVO[] voaInvoice, StockSettleVO[] voaStock,
      FeeDiscountSettleVO[] voaFee, FeeDiscountSettleVO[] voaDiscount,
      FeeDiscountSettleVO[] adjustInvcVos, SettleEnvironment settleEnv) {
    super(voaInvoice, voaStock, voaFee, voaDiscount, adjustInvcVos, settleEnv);
    // 自动结算入库数量是否可超出发票数量
    this.getSettleEnv().setAllowStockBeyondInvoice(true);
    this.getSettleEnv().setStockHaveToMatchAll(false);
    // 将原始的结算信息保存,避免模拟结算破坏
    this.cloneOrigStockInvoiceVO();
  }

  public AutoMatchMerge(InvoiceSettleVO[] voaInvoice, StockSettleVO[] voaStock,
      FeeDiscountSettleVO[] voaFee, FeeDiscountSettleVO[] voaDiscount,
      SettleEnvironment settleEnv) {
    super(voaInvoice, voaStock, voaFee, voaDiscount, settleEnv);

    // 自动结算入库数量是否可超出发票数量
    this.getSettleEnv().setAllowStockBeyondInvoice(true);
    this.getSettleEnv().setStockHaveToMatchAll(false);
    // 将原始的结算信息保存,避免模拟结算破坏
    this.cloneOrigStockInvoiceVO();
  }

  private void cloneOrigStockInvoiceVO() {
    if (!this.isNeedMockMatch()) {
      return;
    }
    this.origSSVos = this.getStockVOs().clone();
    for (int i = 0; i < this.origSSVos.length; i++) {
      this.origSSVos[i] = (StockSettleVO) this.getStockVOs()[i].clone();
    }
    this.origISVos = this.getInvoiceVOs().clone();
    for (int i = 0; i < this.origISVos.length; i++) {
      this.origISVos[i] = (InvoiceSettleVO) this.getInvoiceVOs()[i].clone();
    }
    if (!ArrayUtils.isEmpty(this.getDiscountVOs())) {
      this.origDiscountVos = this.getDiscountVOs().clone();
      for (int i = 0; i < this.origDiscountVos.length; i++) {
        this.origDiscountVos[i] =
            (FeeDiscountSettleVO) this.getDiscountVOs()[i].clone();
      }
    }
    if (!ArrayUtils.isEmpty(this.getFeeVOs())) {
      this.origFeeVos = this.getFeeVOs().clone();
      for (int i = 0; i < this.origFeeVos.length; i++) {
        this.origFeeVos[i] = (FeeDiscountSettleVO) this.getFeeVOs()[i].clone();
      }
    }
    if (!ArrayUtils.isEmpty(this.getAdjustInvcVos())) {
      this.origAdjustVos = this.getAdjustInvcVos().clone();
      for (int i = 0; i < this.origAdjustVos.length; i++) {
        this.origAdjustVos[i] =
            (FeeDiscountSettleVO) this.getAdjustInvcVos()[i].clone();
      }
    }

  }

  private void distFeeDiscountAfterMock(SettleBillVO[] vos) {
    if (!this.isNeedMockMatch()) {
      return;
    }
    this.origSSVos = this.processDistFeeDiscount(this.origSSVos, vos);
  }

  /**
   * 是否需要模拟结算
   * 
   * @return
   */
  private boolean isNeedMockMatch() {
    if (ArrayUtils.isEmpty(this.getDiscountVOs())
        && ArrayUtils.isEmpty(this.getFeeVOs())
        && ArrayUtils.isEmpty(this.getAdjustInvcVos())) {
      return false;
    }
    if (ArrayUtils.isEmpty(this.getStockVOs())) {
      return false;
    }
    for (StockSettleVO ssVo : this.getStockVOs()) {
      if (ICostfactorDiscountUtil.isAllot(ssVo)) {
        return false;
      }
    }
    return true;
  }

  private SettleBillVO[] realMatchAfterMock(SettleBillVO[] vos) {
    // 根据模拟结算的进行费用折扣分摊（放到原始的库存结算VO上）
    this.distFeeDiscountAfterMock(vos);
    // 执行正式结算
    this.setStockVOs(this.origSSVos);
    this.setInvoiceVOs(this.origISVos);
    this.setFeeVOs(this.origFeeVos);
    this.setDiscountVOs(this.origDiscountVos);
    this.setAdjustInvcVos(this.origAdjustVos);
    try {
      return this.merge();
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
      return null;
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  protected boolean canMatch(InvoiceSettleVO voRed, InvoiceSettleVO voBlue) {

    if (!super.canMatch(voRed, voBlue)) {
      return false;
    }
    // ------固定条件
    String[] saFixedRule = AutoMatchRule.getRBInvoiceFixedRule();
    for (int i = 0; i < saFixedRule.length; i++) {
      Comparable<Object> redAttr = (Comparable<Object>) voRed.getAttributeValue(saFixedRule[i]);
      Comparable<Object> blueAttr =
          (Comparable<Object>) voBlue.getAttributeValue(saFixedRule[i]);
      if (redAttr == null && blueAttr != null || redAttr != null
          && redAttr.compareTo(blueAttr) != 0) {
        return false;
      }
    }

    // ------可选条件
    RBInvoiceOptionableVO voFixedRule =
        this.getSettleEnv().getAutoMatchRBInvoiceOptionableVO();

    // /** 部门相同 */
    // /** 采购员相同 */
    // /** 批次相同 */
    // /** 来源同一订单 */
    // /** 生产厂商相同 */
    // /** 项目相同 */
    boolean[] baRule =
        new boolean[] {
          voFixedRule.getBdeptsame().booleanValue(),
          voFixedRule.getBbuyersame().booleanValue(),
          voFixedRule.getBbatchcodesame().booleanValue(),
          voFixedRule.getBordersame().booleanValue(),
          voFixedRule.getBproductorsame().booleanValue(),
          voFixedRule.getBprojectsame().booleanValue()
        };
    String[] saField =
        new String[] {
          InvoiceSettleVO.PK_DEPT, InvoiceSettleVO.PK_BIZPSN,
          InvoiceSettleVO.VBATCHCODE, InvoiceSettleVO.PK_ORDER_B,
          InvoiceSettleVO.CPRODUCTORID, InvoiceSettleVO.CPROJECTID
        };
    for (int i = 0; i < baRule.length; i++) {
      if (baRule[i]
          && !PubAppTool.isEqual(voRed.getAttributeValue(saField[i]),
              voBlue.getAttributeValue(saField[i]))) {
        return false;
      }
    }
    // /** 红蓝发票数量绝对值相同符号相反相同 */
    if (voFixedRule.getBnumabssame().booleanValue()) {
      if (MathTool.absCompareTo(voRed.getNnum(), voBlue.getNnum()) != 0) {
        return false;
      }
    }
    // 自由辅助属性相同
    if (voFixedRule.getBfreeitemsame().booleanValue()) {
      String[] saFree =
          new String[] {
            InvoiceSettleVO.VFREE1, InvoiceSettleVO.VFREE2,
            InvoiceSettleVO.VFREE3, InvoiceSettleVO.VFREE4,
            InvoiceSettleVO.VFREE5, InvoiceSettleVO.VFREE6,
            InvoiceSettleVO.VFREE7, InvoiceSettleVO.VFREE8,
            InvoiceSettleVO.VFREE9, InvoiceSettleVO.VFREE10
          };
      for (int i = 0; i < saFree.length; i++) {
        if (!PubAppTool.isEqual(voRed.getAttributeValue(saFree[i]),
            voBlue.getAttributeValue(saFree[i]))) {
          return false;
        }
      }
    }

    return true;
  }

  @Override
  protected boolean canMatch(InvoiceSettleVO voInvoice, StockSettleVO voStock) {

    // 特殊条件：库存单据、订单
    /**
     * 根据V61架构师李杰提议，需求王印芬以及吴小亮、田锋涛、赵玉行一起确认修改自动结算规则。<br>
     * 如果发票与入库单来源于相同订单，或发票与来源入库单自动结算（包括发票审批自动结算和结算界面的自动结算）<br>
     * 则直接结算，不再走自动结算规则的配置。
     * 原因：有些项目上订单供应商与开票供应商不一致，而入库单记供应商、发票记开票供应商，在此种情况下如果<br>
     * 走自动结算规则，则无法自动结算（因供应商相同是必选条件）。
     * by zhaoyha at 2012.7.21
     */
    if (this.getAddedMergeCondition() == this.AddedMergeCondition_ByStock) {
      // 与来源库存单据结算
      if (PubAppTool.isNull(voInvoice.getCsourcebid())) {
        return false;
      }
      if (!PubAppTool.isEqual(voInvoice.getCsourcebid(),
          voStock.getPk_stockps_b())) {
        return false;
      }
      return true;
    }
    else if (this.getAddedMergeCondition() == this.AddedMergeCondition_ByOrder) {
      // 与来源相同订单的库存单据结算
      if (!InvoiceSettleVOUtil.isOrderRelated(voInvoice)) {
        return false;
      }
      if (!PubAppTool.isEqual(voInvoice.getPk_order_b(),
          voStock.getPk_order_b())) {
        return false;
      }
      return true;
    }

    if (!super.canMatch(voInvoice, voStock)) {
      return false;
    }

    // □√ 部门相同 □ 业务员相同　　□批次相同
    // □ 主无税单价相同
    // □ 发票和入库单数量相同
    // □生产厂商相同　□项目相同
    // □ 自由辅助属性相同
    InvoiceStockOptionableVO voFixedRule =
        this.getSettleEnv().getAutoMatchInvoiceStockOptionableVO();

    boolean[] baRule = new boolean[] {
      true, // 财务组织
      true, // 供应商
      true, // 物料
      voFixedRule.getBdeptsame().booleanValue(), // 部门
      voFixedRule.getBbuyersame().booleanValue(), // 业务员
      voFixedRule.getBbatchcodesame().booleanValue(), // 批次
      voFixedRule.getBorigpricesame().booleanValue(), // 主无税单价
      voFixedRule.getBnumsame().booleanValue(), // 发票和入库单数量
      voFixedRule.getBproductorsame().booleanValue(), // 生产厂商
      voFixedRule.getBprojectsame().booleanValue(), // 项目
      voFixedRule.getBfreeitemsame().booleanValue()
    // 自由辅助属性
        };
    Object[][] oaInvoiceValue =
        new Object[][] {
          new Object[] {
            voInvoice.getPk_org()
          },
          new Object[] {
            voInvoice.getPk_supplier()
          },
          new Object[] {
            voInvoice.getPk_srcmaterial()
          },
          new Object[] {
            voInvoice.getPk_dept()
          },
          new Object[] {
            voInvoice.getPk_bizpsn()
          },
          new Object[] {
            voInvoice.getVproducenum()
          },
          new Object[] {
            voInvoice.getCorigcurrencyid(), voInvoice.getNprice()
          },
          new Object[] {
            voInvoice.getNnum()
          },
          new Object[] {
            voInvoice.getCproductorid()
          },
          new Object[] {
            voInvoice.getCprojectid()
          },
          new Object[] {
            voInvoice.getVfree1(), voInvoice.getVfree2(),
            voInvoice.getVfree3(), voInvoice.getVfree4(),
            voInvoice.getVfree5(), voInvoice.getVfree6(),
            voInvoice.getVfree7(), voInvoice.getVfree8(),
            voInvoice.getVfree9(), voInvoice.getVfree10()
          }
        };
    Object[][] oaStockValue =
        new Object[][] {
          new Object[] {
            voStock.getPk_financeorg()
          },
          new Object[] {
            voStock.getPk_supplier()
          },
          new Object[] {
            voStock.getPk_srcmaterial()
          },
          new Object[] {
            voStock.getPk_dept()
          },
          new Object[] {
            voStock.getPk_psndoc()
          },
          new Object[] {
            voStock.getVbatchcode()
          },
          new Object[] {
            voStock.getCorigcurrencyid(), voStock.getNnetprice()
          },
          new Object[] {
            voStock.getNinnum()
          },
          new Object[] {
            voStock.getCproductorid()
          },
          new Object[] {
            voStock.getCprojectid()
          },
          new Object[] {
            voStock.getVfree1(), voStock.getVfree2(), voStock.getVfree3(),
            voStock.getVfree4(), voStock.getVfree5(), voStock.getVfree6(),
            voStock.getVfree7(), voStock.getVfree8(), voStock.getVfree9(),
            voStock.getVfree10()
          }
        };

    for (int i = 0; i < baRule.length; i++) {
      if (baRule[i]) {
        for (int j = 0; j < oaInvoiceValue[i].length; j++) {
          if (!PubAppTool.isEqual(oaInvoiceValue[i][j], oaStockValue[i][j])) {
            return false;
          }
        }

      }
    }

    return true;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected boolean canMatch(StockSettleVO voRed, StockSettleVO voBlue) {
    if (!super.canMatch(voRed, voBlue)) {
      return false;
    }
    // ------固定条件
    String[] saFixedRule = AutoMatchRule.getRBStockFixedRule();
    for (int i = 0; i < saFixedRule.length; i++) {
      Comparable<Object> redAttr = (Comparable<Object>) voRed.getAttributeValue(saFixedRule[i]);
      Comparable<Object> blueAttr =
          (Comparable<Object>) voBlue.getAttributeValue(saFixedRule[i]);
      if (redAttr == null && blueAttr != null || redAttr != null
          && redAttr.compareTo(blueAttr) != 0) {

        return false;
      }
    }

    // ------可选条件
    // □√ 供应商相同
    // □√ 部门相同 □ 业务员相同　　　□批次相同
    // □ 来源同一订单 □ 入库类型相同
    // □生产厂商相同　□项目相同

    RBStockOptionableVO voFixedRule =
        this.getSettleEnv().getAutoMatchRBStockOptionableVO();
    boolean[] baRule =
        new boolean[] {
          voFixedRule.getBsuppliersame().booleanValue(),
          voFixedRule.getBdeptsame().booleanValue(),
          voFixedRule.getBbuyersame().booleanValue(),
          voFixedRule.getBbatchcodesame().booleanValue(),
          voFixedRule.getBordersame().booleanValue(),
          voFixedRule.getBtrantypesame().booleanValue(),
          voFixedRule.getBproductorsame().booleanValue(),
          voFixedRule.getBprojectsame().booleanValue()
        };
    Object[] oaRed =
        new Object[] {
          voRed.getPk_supplier(), voRed.getPk_dept(), voRed.getPk_psndoc(),
          voRed.getVbatchcode(), voRed.getPk_order_b(),
          voRed.getVtrantypecode(), voRed.getCproductorid(),
          voRed.getCprojectid()
        };
    Object[] oaBlue =
        new Object[] {
          voBlue.getPk_supplier(), voBlue.getPk_dept(), voBlue.getPk_psndoc(),
          voBlue.getVbatchcode(), voBlue.getPk_order_b(),
          voBlue.getVtrantypecode(), voBlue.getCproductorid(),
          voBlue.getCprojectid()
        };
    for (int i = 0; i < baRule.length; i++) {
      if (baRule[i] && !PubAppTool.isEqual(oaRed[i], oaBlue[i])) {
        return false;
      }
    }

    // 红蓝入库比较主无税净价
    // □ 原币无税单价相同
    // 原币无税单价相同 演变为：原币相同、原币无税单价相同
    if (voFixedRule.getBorigpricesame().booleanValue()) {
      if (!PubAppTool.isEqual(voRed.getCorigcurrencyid(),
          voBlue.getCorigcurrencyid())) {
        return false;
      }
      if (MathTool.compareTo(voRed.getNorignetprice(),
          voBlue.getNorignetprice()) != 0) {
        return false;
      }
    }

    // □ 红蓝入库单数量绝对值相同正负相反
    if (voFixedRule.getBnumabssame().booleanValue()) {
      if (MathTool.absCompareTo(voRed.getNinnum(), voBlue.getNinnum()) != 0) {
        return false;
      }
    }
    // 自由辅助属性相同
    if (voFixedRule.getBfreeitemsame().booleanValue()) {
      Object[] oaRedFree =
          new Object[] {
            voRed.getVfree1(), voRed.getVfree2(), voRed.getVfree3(),
            voRed.getVfree4(), voRed.getVfree5(), voRed.getVfree6(),
            voRed.getVfree7(), voRed.getVfree8(), voRed.getVfree9(),
            voRed.getVfree10()
          };
      Object[] oaBlueFree =
          new Object[] {
            voBlue.getVfree1(), voBlue.getVfree2(), voBlue.getVfree3(),
            voBlue.getVfree4(), voBlue.getVfree5(), voBlue.getVfree6(),
            voBlue.getVfree7(), voBlue.getVfree8(), voBlue.getVfree9(),
            voBlue.getVfree10()
          };
      for (int i = 0; i < oaBlueFree.length; i++) {
        if (!PubAppTool.isEqual(oaBlueFree[i], oaRedFree[i])) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pu.m27.merge.SameMaterialMerge#checkAfter()
   */
  @Override
  protected void checkAfter() throws BusinessException {
    // 自动结算不需要检查
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pu.m27.merge.SameMaterialMerge#checkBefore()
   */
  @Override
  protected void checkBefore() throws BusinessException {
    StringBuilder vbillcodes = new StringBuilder();
    for (int i = 0; i < this.getDataClassify().length; i++) {
      DataClassify curData = this.getDataClassify()[i];
      List<InvoiceSettleVO> voList = curData.getOrigInvoices();
      if(voList == null){
        continue;
      }
      for(InvoiceSettleVO vo : voList){
        if(vo.getBapflag().booleanValue()){
          vbillcodes.append(vo.getVbillcode()).append(",");
        }
      }
    }
    if(vbillcodes.length() != 0){
      throw new BusinessException(NCLangResOnserver.getInstance().getStrByID(
          "4004060_0",
          "04004060-0215",
          null,
          new String[]{vbillcodes.substring(0, vbillcodes.length()-1)})
          /* 单据号为{0}的发票已经传应付，不能进行自动结算！ */);
    }
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pu.m27.merge.SameMaterialMerge#formSettleBillItems()
   */
  @Override
  protected SettleBillItemVO[] formSettleBillItems() throws BusinessException {

    // 两次结算，第一次模拟，以确定多少用于结算，以确定分摊的依据；
    // 之后分摊。
    // 然后才是真正的结算。

    ArrayList<SettleBillItemVO> listItemVO = this.mergeProcess();

    // else {
    // // 原始数据CLONE下来，以备真正的结算
    // InvoiceSettleVO[] voaCloneInvoice = null;
    // StockSettleVO[] voaCloneStock = null;
    // if (this.getInvoiceVOs() != null) {
    // voaCloneInvoice = new InvoiceSettleVO[this.getInvoiceVOs().length];
    // for (int i = 0; i < this.getInvoiceVOs().length; i++) {
    // voaCloneInvoice[i] =
    // (InvoiceSettleVO) this.getInvoiceVOs()[i].clone();
    // }
    // }
    // if (this.getStockVOs() != null) {
    // voaCloneStock = new StockSettleVO[this.getStockVOs().length];
    // for (int i = 0; i < this.getStockVOs().length; i++) {
    // voaCloneStock[i] = (StockSettleVO) this.getStockVOs()[i].clone();
    // }
    // }
    //
    // // 1--------模拟
    // listItemVO = this.mergeProcess();
    //
    // if (listItemVO.size() != 0) {
    // // 2--------修正真正需要结算的发票
    // HashMap<String, InvoiceSettleVO> hmapAllInvoice =
    // new HashMap<String, InvoiceSettleVO>();
    // if (voaCloneInvoice != null) {
    // for (int i = 0; i < voaCloneInvoice.length; i++) {
    // hmapAllInvoice.put(voaCloneInvoice[i].getPk_invoice_b(),
    // voaCloneInvoice[i]);
    // }
    // }
    // // 统计发票真正结算的数量、金额等：1)已结算完的所有发票；2)部分结算的发票。
    // ArrayList<InvoiceSettleVO> listRealInvoice =
    // new ArrayList<InvoiceSettleVO>();
    // String key = null;
    // if (!ListUtil.isEmpty(this.getTotalFinishedInvoices())) {
    // for (int i = 0; i < this.getTotalFinishedInvoices().size(); i++) {
    // // InvoiceSettleVO isVo =
    // key = this.getTotalFinishedInvoices().get(i).getPk_invoice_b();
    // if (!hmapAllInvoice.containsKey(key)) {
    // continue;
    // }
    //
    // listRealInvoice.add(hmapAllInvoice.get(key));
    // hmapAllInvoice.remove(key);
    // }
    // }
    // if (!ListUtil.isEmpty(this.getTotalUnfinishedInvoices())) {
    // InvoiceSettleVO voCloneInvoice = null;
    // InvoiceSettleVO voSettledInvoice = null;
    // for (int i = 0; i < this.getTotalUnfinishedInvoices().size(); i++) {
    // voSettledInvoice = this.getTotalUnfinishedInvoices().get(i);
    // // 该行根本未结算
    // if (MathTool.nvl(voSettledInvoice.getNcurrentaccumsettlenum())
    // .compareTo(UFDouble.ZERO_DBL) == 0) {
    // continue;
    // }
    //
    // key = this.getTotalUnfinishedInvoices().get(i).getPk_invoice_b();
    // voCloneInvoice = hmapAllInvoice.get(key);
    // // 本次结算数量、本次结算金额、本次发票结算金额
    // voCloneInvoice.setNcurrentsettlenum(voSettledInvoice
    // .getNcurrentaccumsettlenum());
    // voCloneInvoice.setNcurrentinvoicesettlemny(voSettledInvoice
    // .getNcurrentaccuminvoicesettlemny());
    // voCloneInvoice.setNcurrentotalsettlemny(voSettledInvoice
    // .getNcurrentaccumtotalsettlemny());
    // listRealInvoice.add(voCloneInvoice);
    // hmapAllInvoice.remove(key);
    // }
    // }
    //
    // AutoMatchMerge realMerge =
    // new AutoMatchMerge(listRealInvoice
    // .toArray(new InvoiceSettleVO[listRealInvoice.size()]),
    // voaCloneStock, this.getFeeVOs(), this.getDiscountVOs(), this
    // .getSettleEnv());
    // // new FeeAllot(realMerge.getInvoiceVOs(), realMerge.getFeeVOs(),
    // // realMerge.getDiscountVOs(), this.getSettleEnv()).allot();
    //
    // // 真正的结算
    // listItemVO = realMerge.mergeProcess();
    //
    if (!ArrayUtils.isEmpty(this.getStockVOs())) {
      // 劳务、折扣
      ArrayList<SettleBillItemVO> listTempItemVO = this.mergeFeeDiscount();
      if (listTempItemVO != null) {
        listItemVO.addAll(listTempItemVO);
      }
    }

    return ListUtil.isEmpty(listItemVO) ? null : listItemVO
        .toArray(new SettleBillItemVO[listItemVO.size()]);
  }

  /**
   * // 1发票与来源入库单结算
   * // 2发票与来源于同一订单下的入库单结算
   * // 3满足自动结算条件的其他发票与入库单结算
   * 
   * @return
   */
  protected int[] getSpecialCondition() {

    final int[] specialCondition =
        new int[] {
          this.AddedMergeCondition_ByStock, this.AddedMergeCondition_ByOrder,
          this.AddedMergeCondition_Null
        };
    return specialCondition;
  }

  /**
   * 方法功能描述：简要描述本方法的功能。
   * <p>
   * <b>使用示例:</b>
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @author wangyf
   * @time 2010-3-10 下午03:09:32
   */
  protected ArrayList<SettleBillItemVO> mergeProcess(){

    // -----------3---------------
    // 存放所有合并完的结算体
    ArrayList<SettleBillItemVO> listItemVO = new ArrayList<SettleBillItemVO>();
    ArrayList<SettleBillItemVO> listTempItemVO = null;
    for (int i = 0; i < this.getDataClassify().length; i++) {
      // 红兰库存对冲
      listTempItemVO = this.mergeRBStock(this.getDataClassify()[i]);
      if (listTempItemVO != null) {
        listItemVO.addAll(listTempItemVO);
      }

      // 红兰发票对冲
      listTempItemVO = this.mergeRBInvoice(this.getDataClassify()[i]);
      if (listTempItemVO != null) {
        listItemVO.addAll(listTempItemVO);
      }

      final int[] specialCondition = this.getSpecialCondition();
      for (int j = 0; j < specialCondition.length; j++) {
        this.setAddedMergeCondition(specialCondition[j]);
        // 负结算
        listTempItemVO =
            this.mergeInvoiceStock(this.getDataClassify()[i],
                GoodsMatchMerge.CombineType_MinusInvoiceStock);
        if (listTempItemVO != null) {
          listItemVO.addAll(listTempItemVO);
        }

        // 正结算
        listTempItemVO =
            this.mergeInvoiceStock(this.getDataClassify()[i],
                GoodsMatchMerge.CombineType_PlusInvoiceStock);
        if (listTempItemVO != null) {
          listItemVO.addAll(listTempItemVO);
        }
      }

      this.totalFinishedAndUnFinished(i);
    }

    return listItemVO;

  }

  /**
   * @author mengjian
   * @param origSSVos
   * @param vos
   * @return
   */
  protected StockSettleVO[] processDistFeeDiscount(StockSettleVO[] origSVos,
      SettleBillVO[] vos) {
    AutoMatchFeeDistributeRule rule = new AutoMatchFeeDistributeRule(origSVos);
    return rule.process(vos);

  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pu.m27.merge.SameMaterialMerge#splitSettleBills(nc.vo.pu.m27.entity.SettleBillItemVO[])
   */
  @Override
  protected SettleBillVO[] splitSettleBills(SettleBillItemVO[] voaOrgItem)
      throws BusinessException {
    // 分单
    if (ArrayUtils.isEmpty(voaOrgItem)) {
      return null;
    }

    SettleBillVO[] vos = this.splitBill(voaOrgItem);

    if (!this.isNeedMockMatch()) {
      return vos;
    }
    // 模拟结算后进行真正的结算
    return this.realMatchAfterMock(vos);
  }

  /**
   * 把结算完成的和结算未完成的记录下来
   * 
   * @param i
   */
  protected void totalFinishedAndUnFinished(int i) {
    // 把结算完成的和结算未完成的记录下来
    this.getDataClassify()[i].sumupResidualBill();
    if (this.getDataClassify()[i].getFinishedInvoices() != null) {
      this.getTotalFinishedInvoices().addAll(
          this.getDataClassify()[i].getFinishedInvoices());
    }
    if (this.getDataClassify()[i].getUnfinishedInvoices() != null) {
      this.getTotalUnfinishedInvoices().addAll(
          this.getDataClassify()[i].getUnfinishedInvoices());
    }
  }

}
