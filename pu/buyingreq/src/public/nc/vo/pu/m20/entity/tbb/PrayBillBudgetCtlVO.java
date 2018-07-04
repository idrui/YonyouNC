package nc.vo.pu.m20.entity.tbb;

import java.io.Serializable;
import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.org.StockOrgPubService;
import nc.itf.tb.control.IAccessableBusiVO;
import nc.itf.tb.control.IFormulaFuncName;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.tbb.BillOperationEnum;
import nc.vo.pu.tbb.DocConst;
import nc.vo.pu.tbb.PuBillBusiSysReg;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.tbb.TbbTempTableSqlBuilder;

/**
 * 请购单的预算控制接口实现
 * 
 * @since 6.0
 * @version 2011-3-15 上午08:53:40
 * @author yinfy
 */

public class PrayBillBudgetCtlVO implements IAccessableBusiVO, Serializable {

  private static final long serialVersionUID = -8317609785412090372L;

  private String execBilltype;

  private PraybillHeaderVO head;

  private PraybillItemVO item;

  private int opstatus;

  private UFDouble ordermny = UFDouble.ZERO_DBL;

  private PraybillItemVO originItem;

  public PrayBillBudgetCtlVO(PraybillHeaderVO head, PraybillItemVO item,
      int opstatus, String execBilltype) {
    this(head, item, opstatus, execBilltype, null);

  }

  public PrayBillBudgetCtlVO(PraybillHeaderVO head, PraybillItemVO item,
      int opstatus, String execBilltype, PraybillItemVO originItem) {
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
      // 库存组织所属公司
      return StockOrgPubService.queryCorpIDByStockOrgID(this.item.getPk_org());
    }
    else if (DocConst.PURCHASEORG.equals(attr)) {
      // 采购组织
      return this.item.getPk_purchaseorg();
    }
    else if (DocConst.STOCKORG.equals(attr)) {
      // 库存组织
      return this.item.getPk_org();
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
      return this.item.getPk_srcmaterial();
    }
    else if (PraybillHeaderVO.TAUDITTIME.equals(attr)) {
      if (null == this.head.getAttributeValue(attr)) {
        // 应该使用业务日期，审批时间用的也是业务日期。
        return AppContext.getInstance().getBusiDate().toString();
      }
      return this.head.getAttributeValue(attr).toString();
    }
    else if (TbbTempTableSqlBuilder.CURRENCY.equals(attr)) {
      return this.head.getCcurrencyid();
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
    return POBillType.PrayBill.getCode() + "-" + this.execBilltype;
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
    // 取库存组织所属财务组织的本位币种
    return this.head.getCcurrencyid();
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
          UFDouble.ZERO_DBL, UFDouble.ZERO_DBL, data, data
        };
      }
    }
    return null;
  }

  @Override
  public String[] getHasLevelFlds() {
    return null;
  }

  public PraybillItemVO getItem() {
    return this.item;
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

  public void setOrdermny(UFDouble ordermny) {
    this.ordermny = ordermny;
  }

  private UFDouble getReadyMnyByOp() {
    UFDouble data = null;
    if (BillOperationEnum.APPROVE.getValue() == this.opstatus) {
      // 占用金额变化量 = 请购单的表体金额
      data = this.item.getNtaxmny();
    }
    else if (BillOperationEnum.UNAPPROVE.getValue() == this.opstatus) {
      // 占用金额变化量 = 0 - 请购单的表体金额
      data = MathTool.oppose(this.item.getNtaxmny());
    }
    else if (BillOperationEnum.REVISE.getValue() == this.opstatus) {
      // 占用金额变化量 = 请购单修订后表体金额 - 请购单修订前表体金额
      data = MathTool.sub(this.item.getNtaxmny(), this.originItem.getNtaxmny());
    }
    else if (BillOperationEnum.CLOSE.getValue() == this.opstatus) {
      // 占用金额变化量 = 0 - （请购单表体含税金额 - 请购单累计订单表体金额）
      data =
          MathTool.oppose(MathTool.sub(this.item.getNtaxmny(),
              MathTool.nvl(this.ordermny)));
    }
    else if (BillOperationEnum.OPEN.getValue() == this.opstatus) {
      // 占用金额变化量 = 请购单表体含税金额 - 请购单累计订单表体金额
      data = MathTool.sub(this.item.getNtaxmny(), MathTool.nvl(this.ordermny));
    }
    return data;
  }

  private UFDouble getReadyNumByOp() {
    UFDouble data = null;
    if (BillOperationEnum.APPROVE.getValue() == this.opstatus) {
      data = this.item.getNnum();
    }
    else if (BillOperationEnum.UNAPPROVE.getValue() == this.opstatus) {
      // 占用数量变化量 = 0 - 请购单的请购数量
      data = MathTool.oppose(this.item.getNnum());
    }
    else if (BillOperationEnum.REVISE.getValue() == this.opstatus) {
      // 占用数量变化量 = 请购单修订后的请购数量 - 请购单修订前的请购数量
      data = MathTool.sub(this.item.getNnum(), this.originItem.getNnum());
    }
    else if (BillOperationEnum.CLOSE.getValue() == this.opstatus) {
      // 占用数量变化量 = 0 – (请购单的请购数量 -请购单累计生成订单数量)
      data =
          MathTool.oppose(MathTool.sub(this.item.getNnum(),
              this.item.getNaccumulatenum()));
    }
    else if (BillOperationEnum.OPEN.getValue() == this.opstatus) {
      // 占用数量变化量 = 请购单的请购数量 - 请购单累计订单数量
      data = MathTool.sub(this.item.getNnum(), this.item.getNaccumulatenum());
    }
    return data;

  }

}
