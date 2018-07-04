package nc.vo.pu.m21.entity.tbb;

import java.io.Serializable;
import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.itf.tb.control.IAccessableBusiVO;
import nc.itf.tb.control.IFormulaFuncName;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.enumeration.EnumActive;
import nc.vo.pu.tbb.BillOperationEnum;
import nc.vo.pu.tbb.DocConst;
import nc.vo.pu.tbb.PuBillBusiSysReg;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.tbb.TbbTempTableSqlBuilder;

/**
 * 订单的预算控制接口实现
 * 
 * @since 6.0
 * @version 2011-3-16 上午10:59:57
 * @author yinfy
 */

public class OrderBudgetCtrlVO implements IAccessableBusiVO, Serializable {

  private static final long serialVersionUID = -8940672791167717613L;

  private String execBilltype;

  private OrderHeaderVO head;

  private OrderItemVO item;

  private int opstatus;

  private OrderItemVO originItem;

  public OrderBudgetCtrlVO(OrderHeaderVO head, OrderItemVO item, int opstatus,
      String execBilltype) {
    this(head, item, opstatus, execBilltype, null);
  }

  public OrderBudgetCtrlVO(OrderHeaderVO head, OrderItemVO item, int opstatus,
      String execBilltype, OrderItemVO originItem) {
    this.head = head;
    this.item = item;
    this.opstatus = opstatus;
    this.originItem = originItem;
    this.execBilltype = execBilltype;
  }

  @Override
  public String[] getAllUpLevels(String fieldname, String pk) throws Exception {
    return null;
  }

  @Override
  public String getAttributesValue(String attr) {
    if (DocConst.CORPSTOCKORG.equals(attr)) {
      return StockOrgPubService.queryCorpIDByStockOrgID(this.item
          .getPk_reqstoorg());
    }
    else if (DocConst.PURCHASEORG.equals(attr)) {
      // 采购组织
      return this.item.getPk_org();
    }
    else if (DocConst.STOCKORG.equals(attr)) {
      // 库存组织
      return this.item.getPk_reqstoorg();
    }
    else if (DocConst.BDDEPT.equals(attr)) {
      // 部门
      return this.item.getPk_reqdept();
    }
    else if (DocConst.BDPROJECT.equals(attr)) {
      // 项目
      return this.item.getCprojectid();
    }
    else if (DocConst.MATCLASS.equals(attr)) {
      // 物料分类
      Map<String, String> matbasemap =
          MaterialPubService.queryMaterialBaseClassPk(new String[] {
            this.item.getPk_material()
          });
      return matbasemap.get(this.item.getPk_material());
    }
    else if (DocConst.MATERIAL.equals(attr)) {
      // 物料
      return this.item.getPk_material();
    }
    else if (DocConst.MATERIALOID.equals(attr)) {
      // 物料
      return this.item.getPk_srcmaterial();
    }
    else if (OrderHeaderVO.TAUDITTIME.equals(attr)) {
      if (null == this.head.getAttributeValue(attr)) {
        // 应该使用业务日期，审批时间用的也是业务日期。
        return AppContext.getInstance().getBusiDate().toString();
      }
      return this.head.getAttributeValue(attr).toString();
    }
    else if (TbbTempTableSqlBuilder.CURRENCY.equals(attr)) {
      return this.head.getCorigcurrencyid();
    }
    return null;
  }

  @Override
  public String[] getAttributesValue(String[] attrs) {
    String[] values = new String[attrs.length];
    for (int i = 0; i < attrs.length; i++) {
      String attr = attrs[i];
      values[i] = this.getAttributesValue(attr);
    }
    return values;
  }

  @Override
  public String getBillType() {
    return POBillType.Order.getCode() + "-" + this.execBilltype;
  }

  @Override
  public String getBusiDate() {
    return this.head.getTaudittime().toString();
  }

  @Override
  public String getBusiSys() {
    return "MPP";
  }

  @Override
  public String getBusiType() {
    return null;
  }

  @Override
  public String getCurrency() {
    // 取原币
    return this.item.getCorigcurrencyid();
  }

  @Override
  public String getDataType() {
    // 预占数
    return IFormulaFuncName.PREFIND;

  }

  @Override
  public String getDateType() {
    return PuBillBusiSysReg.DAUDITDATT;
  }

  public String getExecBilltype() {
    return this.execBilltype;
  }

  @Override
  public UFDouble[] getExeData(String direction, String obj, String extObj) {
    if (PuBillBusiSysReg.OCCUR.equals(obj)) {
      if (PuBillBusiSysReg.NNUM.equals(extObj)) {
        UFDouble data = this.getReadyNumByOp();
        if (null == data) {
          return null;
        }
        return new UFDouble[] {
          data, data, data, data
        };
      }
      else if (PuBillBusiSysReg.NTAXMNY.equals(extObj)) {
        UFDouble data = this.getReadyMnyByOp();
        if (null == data) {
          return null;
        }
        return new UFDouble[] {
          data, data, data, data
        };
      }
    }
    return null;
  }

  @Override
  public String[] getHasLevelFlds() {
    return null;
  }

  public OrderHeaderVO getHead() {
    return this.head;
  }

  public OrderItemVO getItem() {
    return this.item;
  }

  public int getOpstatus() {
    return this.opstatus;
  }

  public OrderItemVO getOriginItem() {
    return this.originItem;
  }

  @Override
  public String getPKGroup() {
    return this.head.getPk_group();
  }

  @Override
  public String getPkNcEntity() {
    return null;
  }

  @Override
  public String getPKOrg() {
    return this.head.getPk_org();
  }

  @Override
  public boolean isUnInure() {
    return false;
  }

  protected UFDouble getReadyMnyByOp() {
    UFDouble data = null;
    if (BillOperationEnum.APPROVE.getValue() == this.opstatus) {
      // 占用金额变化量 = 订单的表体金额
      data = this.item.getNorigtaxmny();
    }
    else if (BillOperationEnum.UNAPPROVE.getValue() == this.opstatus) {
      // 占用金额变化量 = 0 - 订单的表体金额
      data = MathTool.oppose(this.item.getNorigtaxmny());
    }
    else if (BillOperationEnum.REVISE.getValue() == this.opstatus
        && this.needControl()) {
      // 占用金额变化量 =采购订单修订前的价税合计- 采购订单修订后的价税合计
      data =
          MathTool.sub(this.item.getNorigtaxmny(),
              this.originItem.getNorigtaxmny());
    }
    else if (BillOperationEnum.CLOSE.getValue() == this.opstatus) {
      // 占用占用金额变化量=0 – 订单的表体金额
      data = MathTool.oppose(this.item.getNorigtaxmny());
    }
    else if (BillOperationEnum.OPEN.getValue() == this.opstatus) {
      // 占用金额变化量 = 订单的表体金额
      data = this.item.getNorigtaxmny();
    }
    return data;
  }

  protected UFDouble getReadyNumByOp() {
    UFDouble data = null;
    if (BillOperationEnum.APPROVE.getValue() == this.opstatus) {
      data = this.item.getNnum();
    }
    else if (BillOperationEnum.UNAPPROVE.getValue() == this.opstatus) {
      // 占用数量变化量 = 0 - 订单的数量
      data = MathTool.oppose(this.item.getNnum());
    }
    else if (BillOperationEnum.REVISE.getValue() == this.opstatus
        && this.needControl()) {
      // 占用数量变化量 =采购订单修订前的订单数量- 采购订单修订后的订单数量
      data = MathTool.sub(this.item.getNnum(), this.originItem.getNnum());
    }
    else if (BillOperationEnum.CLOSE.getValue() == this.opstatus) {
      // 占用数量变化量=0 – 订单的数量
      data = MathTool.oppose(this.item.getNnum());
    }
    else if (BillOperationEnum.OPEN.getValue() == this.opstatus) {
      // 占用数量变化量=订单的数量
      data = this.item.getNnum();
    }
    return data;
  }

  protected boolean needControl() {
    if (POBillType.Order.getCode().equals(this.execBilltype)
        && EnumActive.CLOSE.value().equals(this.originItem.getFisactive())) {
      return false;
    }

    if (POBillType.Arrive.getCode().equals(this.execBilltype)
        && UFBoolean.TRUE.equals(this.originItem.getBarriveclose())) {
      return false;
    }

    if (POBillType.Invoice.getCode().equals(this.execBilltype)
        && UFBoolean.TRUE.equals(this.originItem.getBinvoiceclose())) {
      return false;
    }

    if (ICBillType.PurchaseIn.getCode().equals(this.execBilltype)
        && UFBoolean.TRUE.equals(this.originItem.getBstockclose())) {
      return false;
    }

    return true;
  }

}
