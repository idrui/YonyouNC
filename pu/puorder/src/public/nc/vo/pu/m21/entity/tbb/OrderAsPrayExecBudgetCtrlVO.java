package nc.vo.pu.m21.entity.tbb;

import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.pubitf.uapbd.CurrencyRateUtil;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillViewVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.tbb.DocConst;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.tbb.TbbTempTableSqlBuilder;
import nc.vo.scmpub.util.TimeUtils;

import org.apache.commons.lang.StringUtils;

/**
 * @since 6.0
 * @version 2011-3-16 下午04:12:03
 * @author yinfy
 */

public class OrderAsPrayExecBudgetCtrlVO extends OrderExecBudgetCtrlVO {
  private static final long serialVersionUID = -2623857233931113480L;

  /** 对应来源请购单表体视图 */
  PraybillViewVO praybillview;

  /**
   * @param head
   * @param item
   * @param opstatus
   * @param execBilltype
   */
  public OrderAsPrayExecBudgetCtrlVO(OrderHeaderVO head, OrderItemVO item,
      int opstatus, String execBilltype, PraybillViewVO praybillview) {
    super(head, item, opstatus, execBilltype);
    this.praybillview = praybillview;
  }

  @Override
  public String getAttributesValue(String attr) {
    if (DocConst.CORPSTOCKORG.equals(attr)) {
      return StockOrgPubService.queryCorpIDByStockOrgID(this.praybillview
          .getItem().getPk_org());
    }
    else if (DocConst.PURCHASEORG.equals(attr)) {
      // 采购组织
      return this.praybillview.getItem().getPk_purchaseorg();
    }
    else if (DocConst.STOCKORG.equals(attr)) {
      // 库存组织
      return this.praybillview.getItem().getPk_org();
    }
    else if (DocConst.BDDEPT.equals(attr)) {
      // 部门
      return this.praybillview.getItem().getPk_reqdept();
    }
    else if (DocConst.BDPROJECT.equals(attr)) {
      // 项目
      return this.praybillview.getItem().getCprojectid();
    }
    else if (DocConst.MATCLASS.equals(attr)) {
      // 物料分类
      Map<String, String> matbasemap =
          MaterialPubService.queryMaterialBaseClassPk(new String[] {
            this.praybillview.getItem().getPk_material()
          });
      return matbasemap.get(this.praybillview.getItem().getPk_material());
    }
    else if (DocConst.MATERIAL.equals(attr)) {
      // 物料
      return this.praybillview.getItem().getPk_material();
    }
    else if (DocConst.MATERIALOID.equals(attr)) {
      // 物料oid
      return this.praybillview.getItem().getPk_srcmaterial();
    }
    else if (PraybillHeaderVO.TAUDITTIME.equals(attr)) {
      if (null == this.praybillview.getHead().getAttributeValue(attr)) {
        // 应该使用业务日期，审批时间用的也是业务日期。
        return AppContext.getInstance().getBusiDate().toString();
      }
      return this.praybillview.getHead().getAttributeValue(attr).toString();
    }
    else if (TbbTempTableSqlBuilder.CURRENCY.equals(attr)) {
      return this.praybillview.getCcurrencyid();
    }
    return null;
  }

  @Override
  public String getBillType() {
    return this.getExecBilltype() + "-" + POBillType.PrayBill.getCode();
  }

  @Override
  public String getBusiDate() {
    return this.praybillview.getHead().getTaudittime().toString();
  }

  @Override
  public String getCurrency() {
    // 取本币
    return this.praybillview.getHead().getCcurrencyid();
  }

  private UFDouble toPrayBillMny(UFDouble data) {
    UFDouble result = null;
    try {
      Map<String, String> finance =
          StockOrgPubService.queryFinanceOrgIDByStockOrgID(new String[] {
            this.praybillview.getItem().getPk_org()
          });
      // 未找到所属财务组织，无法转换为对应的币种金额
      if (null == finance
          || StringUtils.isEmpty(finance.get(this.praybillview.getItem()
              .getPk_org()))) {
        return null;
      }
      // 折算为请购单所对应的币种
      CurrencyRateUtil util =
          CurrencyRateUtil.getInstanceByOrg(finance.get(this.praybillview
              .getItem().getPk_org()));
      result =
          MathTool.nvl(util.getAmountByOpp(this.getHead().getCorigcurrencyid(),
              this.praybillview.getHead().getCcurrencyid(), data, null,
              TimeUtils.getsrvBaseDate()));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return result;
  }

  @Override
  protected UFDouble getMnyByBilltype() {
    UFDouble data = super.getMnyByBilltype();
    return this.toPrayBillMny(data);
  }

  // @Override
  // protected UFDouble getReadyMnyByOp() {
  // UFDouble data = super.getMnyByBilltype();
  // return this.toPrayBillMny(data);
  // }
}
