package nc.vo.pu.m21.entity.tbb;

import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.pubitf.uapbd.CurrencyRateUtil;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillViewVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.tbb.BillOperationEnum;
import nc.vo.pu.tbb.DocConst;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
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
 * @version 2011-3-16 下午04:47:04
 * @author yinfy
 */

public class OrderAsPrayBudgetCtrlVO extends OrderBudgetCtrlVO {

  private static final long serialVersionUID = -259284537307109937L;

  /** 对应来源请购单表体视图 */
  PraybillViewVO praybillview;

  /**
   * @param head
   * @param item
   * @param opstatus
   */
  public OrderAsPrayBudgetCtrlVO(OrderHeaderVO head, OrderItemVO item,
      int opstatus, String execBilltype, PraybillViewVO praybillview) {
    this(head, item, opstatus, execBilltype, praybillview, null);
  }

  public OrderAsPrayBudgetCtrlVO(OrderHeaderVO head, OrderItemVO item,
      int opstatus, String execBilltype, PraybillViewVO praybillview,
      OrderItemVO originitem) {
    super(head, item, opstatus, execBilltype, originitem);
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
      // 物料OID
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
    return POBillType.PrayBill.getCode() + "-" + this.getExecBilltype();
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

  @Override
  public String getPKOrg() {
    return this.praybillview.getItem().getPk_org();
  }

  @Override
  protected UFDouble getReadyMnyByOp() {
    UFDouble data = null;
    // if (BillOperationEnum.DELETE.getValue() == this.getOpstatus()) {
    // // 占用金额变化量 = 0 - 采购订单删除前的价税合计
    // data = MathTool.oppose(this.getItem().getNorigtaxmny());
    // }
    // else if (BillOperationEnum.MODIFY.getValue() == this.getOpstatus()) {
    // // 占用金额变化量 =采购订单修改后的订单价税合计 - 采购订单修改前的订单价税合计
    // data =
    // MathTool.sub(this.getItem().getNorigtaxmny(), this.getOriginItem()
    // .getNorigtaxmny());
    // }
    // else {
    // data = super.getReadyMnyByOp();
    // }

    UFBoolean bprayclose = this.praybillview.getBrowclose();

    if (UFBoolean.TRUE.equals(bprayclose)) {
      if (BillOperationEnum.APPROVE.getValue() == this.getOpstatus()) {
        // 请购单关闭，订单打开：预占数=订单金额
        // 0 –(请购单金额-累计订单金额)
        // 注意：此处因为要转换币种，所以增请购单金额放在后面
        data = this.getItem().getNorigtaxmny();
      }
      else if (BillOperationEnum.UNAPPROVE.getValue() == this.getOpstatus()) {
        // 请购单关闭，订单弃审后，预占=0
        // 占用数量变化量 = 0 - 订单的金额
        data = MathTool.oppose(this.getItem().getNorigtaxmny());
      }
      else if (BillOperationEnum.REVISE.getValue() == this.getOpstatus()
          && this.needControl()) {
        // 请购单关闭，订单修订，预占=修订后金额
        // 占用数量变化量=采购订单修订前的订单金额- 采购订单修订后的订单金额
        data =
            MathTool.sub(this.getItem().getNorigtaxmny(), this.getOriginItem()
                .getNorigtaxmny());
      }
      else if (BillOperationEnum.CLOSE.getValue() == this.getOpstatus()) {
        // 请购单关闭，订单关闭，预占为0
        // 占用数量变化量=0 – 订单的金额
        data = MathTool.oppose(this.getItem().getNorigtaxmny());
      }
      else if (BillOperationEnum.OPEN.getValue() == this.getOpstatus()) {
        // 请购单关闭，订单打开，预占=订单金额
        // 占用数量变化量=订单的金额
        data = this.getItem().getNorigtaxmny();
      }
      if (BillOperationEnum.DELETE.getValue() == this.getOpstatus()) {
        // 请购单关闭，订单删除，预占=0
        // 占用数量变化量 = 0 - 采购订单删除前的订单金额
        data = MathTool.oppose(this.getItem().getNorigtaxmny());
      }
      else if (BillOperationEnum.MODIFY.getValue() == this.getOpstatus()) {
        // 请购单关闭，订单修改，预占=采购订单金额
        // 占用数量变化量 = 采购订单修改后的订单金额 -采购订单修改前的订单金额
        data =
            MathTool.sub(this.getItem().getNorigtaxmny(), this.getOriginItem()
                .getNorigtaxmny());
      }
    }
    else {
      if (BillOperationEnum.CLOSE.getValue() == this.getOpstatus()) {
        // 请购单打开，订单关闭，预占为请购单金额-订单金额
        // 占用数量变化量=0 – 订单的金额
        data = MathTool.oppose(this.getItem().getNorigtaxmny());
      }
      else if (BillOperationEnum.OPEN.getValue() == this.getOpstatus()) {
        // 请购单打开，订单打开，预占=请购单金额
        // 占用数量变化量=订单的金额
        data = this.getItem().getNorigtaxmny();
      }
    }

    if (null == data) {
      return data;
    }
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
      data =
          MathTool.nvl(util.getAmountByOpp(this.getHead().getCorigcurrencyid(),
              this.praybillview.getHead().getCcurrencyid(), data, null,
              TimeUtils.getsrvBaseDate()));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    UFDouble npraymny = this.praybillview.getNtaxmny();
    if (UFBoolean.TRUE.equals(bprayclose)
        && BillOperationEnum.APPROVE.getValue() == this.getOpstatus()) {
      data = MathTool.sub(data, npraymny);
    }
    return data;
  }

  @Override
  protected UFDouble getReadyNumByOp() {
    UFDouble data = null;
    UFBoolean bprayclose = this.praybillview.getBrowclose();
    UFDouble npraynum = this.praybillview.getNnum();
    if (UFBoolean.TRUE.equals(bprayclose)) {
      if (BillOperationEnum.APPROVE.getValue() == this.getOpstatus()) {
        // 请购单关闭，订单打开：预占数=订单数量
        // 0 –(请购单数量-累计订单数量)
        data = MathTool.sub(this.getItem().getNnum(), npraynum);
      }
      else if (BillOperationEnum.UNAPPROVE.getValue() == this.getOpstatus()) {
        // 请购单关闭，订单弃审后，预占=0
        // 占用数量变化量 = 0 - 订单的数量
        data = MathTool.oppose(this.getItem().getNnum());
      }
      else if (BillOperationEnum.REVISE.getValue() == this.getOpstatus()
          && this.needControl()) {
        // 请购单关闭，订单修订，预占=修订后数量
        // 占用数量变化量=采购订单修订前的订单数量- 采购订单修订后的订单数量
        data =
            MathTool.sub(this.getItem().getNnum(), this.getOriginItem()
                .getNnum());
      }
      else if (BillOperationEnum.CLOSE.getValue() == this.getOpstatus()) {
        // 请购单关闭，订单关闭，预占为0
        // 占用数量变化量=0 – 订单的数量
        data = MathTool.oppose(this.getItem().getNnum());
      }
      else if (BillOperationEnum.OPEN.getValue() == this.getOpstatus()) {
        // 请购单关闭，订单打开，预占=订单数量
        // 占用数量变化量=订单的数量
        data = this.getItem().getNnum();
      }
      if (BillOperationEnum.DELETE.getValue() == this.getOpstatus()) {
        // 请购单关闭，订单删除，预占=0
        // 占用数量变化量 = 0 - 采购订单删除前的订单数量
        data = MathTool.oppose(this.getItem().getNnum());
      }
      else if (BillOperationEnum.MODIFY.getValue() == this.getOpstatus()) {
        // 请购单关闭，订单修改，预占=采购订单数量
        // 占用数量变化量 = 采购订单修改后的订单数量 -采购订单修改前的订单数量
        data =
            MathTool.sub(this.getItem().getNnum(), this.getOriginItem()
                .getNnum());
      }
    }
    else {
      // if (BillOperationEnum.APPROVE.getValue() == this.getOpstatus()) {
      // 请购单打开，订单打开：预占数=订单数量
      // 0 –(请购单数量-累计订单数量)
      // data = MathTool.sub(this.getItem().getNnum(), npraynum);
      // }
      // else if (BillOperationEnum.UNAPPROVE.getValue() == this.getOpstatus())
      // {
      // 请购单打开，订单弃审后，预占=0
      // 占用数量变化量 = 0 - 订单的数量
      // data = MathTool.oppose(this.getItem().getNnum());
      // }
      // else if (BillOperationEnum.REVISE.getValue() == this.getOpstatus()
      // && this.needControl()) {
      // 请购单打开，订单修订，预占=修订后数量
      // 占用数量变化量=采购订单修订前的订单数量- 采购订单修订后的订单数量
      // data =
      // MathTool.sub(this.getItem().getNnum(), this.getOriginItem()
      // .getNnum());
      // }
      // else
      if (BillOperationEnum.CLOSE.getValue() == this.getOpstatus()) {
        // 请购单打开，订单关闭，预占为请购单数量-订单数量
        // 占用数量变化量=0 – 订单的数量
        data = MathTool.oppose(this.getItem().getNnum());
      }
      else if (BillOperationEnum.OPEN.getValue() == this.getOpstatus()) {
        // 请购单打开，订单打开，预占=请购单数量
        // 占用数量变化量=订单的数量
        data = this.getItem().getNnum();
      }
      // else if (BillOperationEnum.DELETE.getValue() == this.getOpstatus()) {
      // 占用数量变化量 = 0 - 采购订单删除前的订单数量
      // data = MathTool.oppose(this.getItem().getNnum());
      // }
      // else if (BillOperationEnum.MODIFY.getValue() == this.getOpstatus()) {
      // 占用数量变化量 = 采购订单修改后的订单数量 -采购订单修改前的订单数量
      // data =
      // MathTool.sub(this.getItem().getNnum(), this.getOriginItem()
      // .getNnum());
      // }
    }

    return data;
  }
}
