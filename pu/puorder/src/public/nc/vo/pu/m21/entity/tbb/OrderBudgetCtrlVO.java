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
 * ������Ԥ����ƽӿ�ʵ��
 * 
 * @since 6.0
 * @version 2011-3-16 ����10:59:57
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
      // �ɹ���֯
      return this.item.getPk_org();
    }
    else if (DocConst.STOCKORG.equals(attr)) {
      // �����֯
      return this.item.getPk_reqstoorg();
    }
    else if (DocConst.BDDEPT.equals(attr)) {
      // ����
      return this.item.getPk_reqdept();
    }
    else if (DocConst.BDPROJECT.equals(attr)) {
      // ��Ŀ
      return this.item.getCprojectid();
    }
    else if (DocConst.MATCLASS.equals(attr)) {
      // ���Ϸ���
      Map<String, String> matbasemap =
          MaterialPubService.queryMaterialBaseClassPk(new String[] {
            this.item.getPk_material()
          });
      return matbasemap.get(this.item.getPk_material());
    }
    else if (DocConst.MATERIAL.equals(attr)) {
      // ����
      return this.item.getPk_material();
    }
    else if (DocConst.MATERIALOID.equals(attr)) {
      // ����
      return this.item.getPk_srcmaterial();
    }
    else if (OrderHeaderVO.TAUDITTIME.equals(attr)) {
      if (null == this.head.getAttributeValue(attr)) {
        // Ӧ��ʹ��ҵ�����ڣ�����ʱ���õ�Ҳ��ҵ�����ڡ�
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
    // ȡԭ��
    return this.item.getCorigcurrencyid();
  }

  @Override
  public String getDataType() {
    // Ԥռ��
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
      // ռ�ý��仯�� = �����ı�����
      data = this.item.getNorigtaxmny();
    }
    else if (BillOperationEnum.UNAPPROVE.getValue() == this.opstatus) {
      // ռ�ý��仯�� = 0 - �����ı�����
      data = MathTool.oppose(this.item.getNorigtaxmny());
    }
    else if (BillOperationEnum.REVISE.getValue() == this.opstatus
        && this.needControl()) {
      // ռ�ý��仯�� =�ɹ������޶�ǰ�ļ�˰�ϼ�- �ɹ������޶���ļ�˰�ϼ�
      data =
          MathTool.sub(this.item.getNorigtaxmny(),
              this.originItem.getNorigtaxmny());
    }
    else if (BillOperationEnum.CLOSE.getValue() == this.opstatus) {
      // ռ��ռ�ý��仯��=0 �C �����ı�����
      data = MathTool.oppose(this.item.getNorigtaxmny());
    }
    else if (BillOperationEnum.OPEN.getValue() == this.opstatus) {
      // ռ�ý��仯�� = �����ı�����
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
      // ռ�������仯�� = 0 - ����������
      data = MathTool.oppose(this.item.getNnum());
    }
    else if (BillOperationEnum.REVISE.getValue() == this.opstatus
        && this.needControl()) {
      // ռ�������仯�� =�ɹ������޶�ǰ�Ķ�������- �ɹ������޶���Ķ�������
      data = MathTool.sub(this.item.getNnum(), this.originItem.getNnum());
    }
    else if (BillOperationEnum.CLOSE.getValue() == this.opstatus) {
      // ռ�������仯��=0 �C ����������
      data = MathTool.oppose(this.item.getNnum());
    }
    else if (BillOperationEnum.OPEN.getValue() == this.opstatus) {
      // ռ�������仯��=����������
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
