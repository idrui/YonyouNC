package nc.pubimpl.pu.it.merge;

import nc.bs.pu.it.AutoMatchFeeDistributeRuleForIT;
import nc.impl.pu.m27.merge.AutoMatchMerge;
import nc.pubimpl.pu.it.rule.MatchMergeFeeDiscountAdjustRule;
import nc.vo.pu.m25.settle.FeeDiscountSettleVO;
import nc.vo.pu.m25.settle.InvoiceSettleVO;
import nc.vo.pu.m27.entity.InvoiceSettleVOUtil;
import nc.vo.pu.m27.entity.InvoiceStockOptionableVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.entity.StockSettleVO;
import nc.vo.pu.m27.entity.StockSettleVOUtil;
import nc.vo.pu.m27.enumeration.EnumSettleOrderType;
import nc.vo.pu.m27.pub.SettleEnvironment;
import nc.vo.pu.m4201.enumeration.EnumToAPFlag;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;

/**
 * 进口界面自动结算匹配规则
 * 
 * @since 6.31
 * @version 2013-9-17 下午02:21:48
 * @author mengjian
 */
public class AutoMatchMergeForIT extends AutoMatchMerge {

  public AutoMatchMergeForIT(InvoiceSettleVO[] voaInvoice,
      StockSettleVO[] voaStock, FeeDiscountSettleVO[] voaFee,
      FeeDiscountSettleVO[] voaDiscount, FeeDiscountSettleVO[] adjustInvcVos,
      SettleEnvironment settleEnv) {
    super(voaInvoice, voaStock, voaFee, voaDiscount, adjustInvcVos, settleEnv);
  }

  private boolean canMatchForIT(InvoiceSettleVO voInvoice, StockSettleVO voStock) {
    // 某一方结算完成不再结算
    if (InvoiceSettleVOUtil.isCurrentSettleFinished(voInvoice)
        || StockSettleVOUtil.isCurrentSettleFinished(voStock)) {
      return false;
    }

    // 自动结算、异物料结算、同物料结算共用合并算法
    // ----采购入库单已作过暂估应付或者已确认应付，则不允许该采购入库单与已传应付的采购发票进行结算；
    // ----采购入库单已确认应付，则不允许与币种与采购入库单币种不同的采购发票进行结算。

    // 订单分状态关闭影响货票结算相关规则：
    // 26. 参照订单或者非自制的采购入库单生成的采购发票不允许与其他采购订单的入库单进行结算；
    // 27. 自制的采购发票或者参照自制的采购入库单生成的采购发票可以与任意的采购入库单进行结算。

    // 发票是订单的发票，则只能匹配此订单的入库单
    // if (!PubAppTool.isEqual(voInvoice.getPk_order_b(), null)
    // && !PubAppTool.isEqual(voInvoice.getPk_order_b(),
    // voStock.getPk_order_b())) {
    // return false;
    // }

    // 采购入库单已确认应付，则不允许与币种与采购入库单币种不同的采购发票进行结算。
    if (EnumToAPFlag.ConfirmToAP.value().equals(voStock.getFdirtoaptype())
        && !PubAppTool.isEqual(voStock.getCorigcurrencyid(),
            voInvoice.getCorigcurrencyid())) {
      return false;
    }

    // 采购入库单已作过暂估应付或者已确认应付，则不允许该采购入库单与已传应付的采购发票进行结算；
    if (!EnumToAPFlag.NotToAP.value().equals(voStock.getFdirtoaptype())
        && ValueUtils.getBoolean(voInvoice.getBapflag())) {
      return false;
    }

    return true;
  }

  /**
   * 进口自动结算发票与入库单匹配
   * 系统默认必须的条件为：来源同一进口明细单行、财务组织相同，这些条件默认选中，并不可编辑；
   * 没有用户可选的发票与入库单自动结算的其他条件
   */
  @Override
  protected boolean canMatch(InvoiceSettleVO voInvoice, StockSettleVO voStock) {

    if (this.getAddedMergeCondition() == this.AddedMergeCondition_ByOrder) {
      // 与来源同一进口明细单行的库存单据结算
      if (!InvoiceSettleVOUtil.isOrderRelated(voInvoice)) {
        return false;
      }
      if (!PubAppTool.isEqual(voInvoice.getPk_order_b(),
          voStock.getPk_order_b())) {
        return false;
      }
      return true;
    }

    if (!this.canMatchForIT(voInvoice, voStock)) {
      return false;
    }

    // □√ 部门相同 □ 业务员相同　　□批次相同
    // □ 主无税单价相同
    // □ 发票和入库单数量相同
    // □生产厂商相同　□项目相同
    // □ 自由辅助属性相同
    // □ 同一订单
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
      voFixedRule.getBdetailbidsame().booleanValue(),// 来源同一进口明细单行
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
            voInvoice.getPk_order_b()
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
            voStock.getPk_order_b()
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

  /**
   * 进口自动结算发票与入库单匹配
   * 1.发票与来源于同一明细单下的入库单结算;
   * 2.满足自动结算条件的其他发票与入库单结算
   * 
   * @return int[]
   */
  @Override
  protected int[] getSpecialCondition() {

    final int[] specialCondition = new int[] {
      this.AddedMergeCondition_ByOrder, this.AddedMergeCondition_ByStock
    };
    return specialCondition;
  }

  @Override
  protected StockSettleVO[] processDistFeeDiscount(StockSettleVO[] origSSVos,
      SettleBillVO[] vos) {
    // add by liangchen1 费用第一次分摊
    AutoMatchFeeDistributeRuleForIT rule =
        new AutoMatchFeeDistributeRuleForIT(origSSVos);
    return rule.process(vos);

  }

  @Override
  protected void recordFeeDiscountInfo(SettleBillItemVO[] voaItem,
      StockSettleVO[] m_voaStock) {
    new MatchMergeFeeDiscountAdjustRule(this.getSettleEnv(), m_voaStock)
        .process(voaItem);
  }

  /**
   * //设置结算类型 0：采购 ；1：进口
   * 
   * @param splitVos
   */
  @Override
  protected void setFsettletypes(SettleBillVO[] splitVos) {
    for (SettleBillVO bill : splitVos) {
      // 设置结算类型 0：采购 ；1：进口
      bill.getParentVO().setFsettletype(EnumSettleOrderType.IT.toInteger());
    }
  }
}
