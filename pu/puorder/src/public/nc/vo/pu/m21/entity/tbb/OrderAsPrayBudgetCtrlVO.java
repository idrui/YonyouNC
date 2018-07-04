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
 * @version 2011-3-16 ����04:47:04
 * @author yinfy
 */

public class OrderAsPrayBudgetCtrlVO extends OrderBudgetCtrlVO {

  private static final long serialVersionUID = -259284537307109937L;

  /** ��Ӧ��Դ�빺��������ͼ */
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
      // �ɹ���֯
      return this.praybillview.getItem().getPk_purchaseorg();
    }
    else if (DocConst.STOCKORG.equals(attr)) {
      // �����֯
      return this.praybillview.getItem().getPk_org();
    }
    else if (DocConst.BDDEPT.equals(attr)) {
      // ����
      return this.praybillview.getItem().getPk_reqdept();
    }
    else if (DocConst.BDPROJECT.equals(attr)) {
      // ��Ŀ
      return this.praybillview.getItem().getCprojectid();
    }
    else if (DocConst.MATCLASS.equals(attr)) {
      // ���Ϸ���
      Map<String, String> matbasemap =
          MaterialPubService.queryMaterialBaseClassPk(new String[] {
            this.praybillview.getItem().getPk_material()
          });
      return matbasemap.get(this.praybillview.getItem().getPk_material());
    }
    else if (DocConst.MATERIAL.equals(attr)) {
      // ����
      return this.praybillview.getItem().getPk_material();
    }
    else if (DocConst.MATERIALOID.equals(attr)) {
      // ����OID
      return this.praybillview.getItem().getPk_srcmaterial();
    }

    else if (PraybillHeaderVO.TAUDITTIME.equals(attr)) {
      if (null == this.praybillview.getHead().getAttributeValue(attr)) {
        // Ӧ��ʹ��ҵ�����ڣ�����ʱ���õ�Ҳ��ҵ�����ڡ�
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
    // ȡ����
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
    // // ռ�ý��仯�� = 0 - �ɹ�����ɾ��ǰ�ļ�˰�ϼ�
    // data = MathTool.oppose(this.getItem().getNorigtaxmny());
    // }
    // else if (BillOperationEnum.MODIFY.getValue() == this.getOpstatus()) {
    // // ռ�ý��仯�� =�ɹ������޸ĺ�Ķ�����˰�ϼ� - �ɹ������޸�ǰ�Ķ�����˰�ϼ�
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
        // �빺���رգ������򿪣�Ԥռ��=�������
        // 0 �C(�빺�����-�ۼƶ������)
        // ע�⣺�˴���ΪҪת�����֣��������빺�������ں���
        data = this.getItem().getNorigtaxmny();
      }
      else if (BillOperationEnum.UNAPPROVE.getValue() == this.getOpstatus()) {
        // �빺���رգ����������Ԥռ=0
        // ռ�������仯�� = 0 - �����Ľ��
        data = MathTool.oppose(this.getItem().getNorigtaxmny());
      }
      else if (BillOperationEnum.REVISE.getValue() == this.getOpstatus()
          && this.needControl()) {
        // �빺���رգ������޶���Ԥռ=�޶�����
        // ռ�������仯��=�ɹ������޶�ǰ�Ķ������- �ɹ������޶���Ķ������
        data =
            MathTool.sub(this.getItem().getNorigtaxmny(), this.getOriginItem()
                .getNorigtaxmny());
      }
      else if (BillOperationEnum.CLOSE.getValue() == this.getOpstatus()) {
        // �빺���رգ������رգ�ԤռΪ0
        // ռ�������仯��=0 �C �����Ľ��
        data = MathTool.oppose(this.getItem().getNorigtaxmny());
      }
      else if (BillOperationEnum.OPEN.getValue() == this.getOpstatus()) {
        // �빺���رգ������򿪣�Ԥռ=�������
        // ռ�������仯��=�����Ľ��
        data = this.getItem().getNorigtaxmny();
      }
      if (BillOperationEnum.DELETE.getValue() == this.getOpstatus()) {
        // �빺���رգ�����ɾ����Ԥռ=0
        // ռ�������仯�� = 0 - �ɹ�����ɾ��ǰ�Ķ������
        data = MathTool.oppose(this.getItem().getNorigtaxmny());
      }
      else if (BillOperationEnum.MODIFY.getValue() == this.getOpstatus()) {
        // �빺���رգ������޸ģ�Ԥռ=�ɹ��������
        // ռ�������仯�� = �ɹ������޸ĺ�Ķ������ -�ɹ������޸�ǰ�Ķ������
        data =
            MathTool.sub(this.getItem().getNorigtaxmny(), this.getOriginItem()
                .getNorigtaxmny());
      }
    }
    else {
      if (BillOperationEnum.CLOSE.getValue() == this.getOpstatus()) {
        // �빺���򿪣������رգ�ԤռΪ�빺�����-�������
        // ռ�������仯��=0 �C �����Ľ��
        data = MathTool.oppose(this.getItem().getNorigtaxmny());
      }
      else if (BillOperationEnum.OPEN.getValue() == this.getOpstatus()) {
        // �빺���򿪣������򿪣�Ԥռ=�빺�����
        // ռ�������仯��=�����Ľ��
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
      // δ�ҵ�����������֯���޷�ת��Ϊ��Ӧ�ı��ֽ��
      if (null == finance
          || StringUtils.isEmpty(finance.get(this.praybillview.getItem()
              .getPk_org()))) {
        return null;
      }
      // ����Ϊ�빺������Ӧ�ı���
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
        // �빺���رգ������򿪣�Ԥռ��=��������
        // 0 �C(�빺������-�ۼƶ�������)
        data = MathTool.sub(this.getItem().getNnum(), npraynum);
      }
      else if (BillOperationEnum.UNAPPROVE.getValue() == this.getOpstatus()) {
        // �빺���رգ����������Ԥռ=0
        // ռ�������仯�� = 0 - ����������
        data = MathTool.oppose(this.getItem().getNnum());
      }
      else if (BillOperationEnum.REVISE.getValue() == this.getOpstatus()
          && this.needControl()) {
        // �빺���رգ������޶���Ԥռ=�޶�������
        // ռ�������仯��=�ɹ������޶�ǰ�Ķ�������- �ɹ������޶���Ķ�������
        data =
            MathTool.sub(this.getItem().getNnum(), this.getOriginItem()
                .getNnum());
      }
      else if (BillOperationEnum.CLOSE.getValue() == this.getOpstatus()) {
        // �빺���رգ������رգ�ԤռΪ0
        // ռ�������仯��=0 �C ����������
        data = MathTool.oppose(this.getItem().getNnum());
      }
      else if (BillOperationEnum.OPEN.getValue() == this.getOpstatus()) {
        // �빺���رգ������򿪣�Ԥռ=��������
        // ռ�������仯��=����������
        data = this.getItem().getNnum();
      }
      if (BillOperationEnum.DELETE.getValue() == this.getOpstatus()) {
        // �빺���رգ�����ɾ����Ԥռ=0
        // ռ�������仯�� = 0 - �ɹ�����ɾ��ǰ�Ķ�������
        data = MathTool.oppose(this.getItem().getNnum());
      }
      else if (BillOperationEnum.MODIFY.getValue() == this.getOpstatus()) {
        // �빺���رգ������޸ģ�Ԥռ=�ɹ���������
        // ռ�������仯�� = �ɹ������޸ĺ�Ķ������� -�ɹ������޸�ǰ�Ķ�������
        data =
            MathTool.sub(this.getItem().getNnum(), this.getOriginItem()
                .getNnum());
      }
    }
    else {
      // if (BillOperationEnum.APPROVE.getValue() == this.getOpstatus()) {
      // �빺���򿪣������򿪣�Ԥռ��=��������
      // 0 �C(�빺������-�ۼƶ�������)
      // data = MathTool.sub(this.getItem().getNnum(), npraynum);
      // }
      // else if (BillOperationEnum.UNAPPROVE.getValue() == this.getOpstatus())
      // {
      // �빺���򿪣����������Ԥռ=0
      // ռ�������仯�� = 0 - ����������
      // data = MathTool.oppose(this.getItem().getNnum());
      // }
      // else if (BillOperationEnum.REVISE.getValue() == this.getOpstatus()
      // && this.needControl()) {
      // �빺���򿪣������޶���Ԥռ=�޶�������
      // ռ�������仯��=�ɹ������޶�ǰ�Ķ�������- �ɹ������޶���Ķ�������
      // data =
      // MathTool.sub(this.getItem().getNnum(), this.getOriginItem()
      // .getNnum());
      // }
      // else
      if (BillOperationEnum.CLOSE.getValue() == this.getOpstatus()) {
        // �빺���򿪣������رգ�ԤռΪ�빺������-��������
        // ռ�������仯��=0 �C ����������
        data = MathTool.oppose(this.getItem().getNnum());
      }
      else if (BillOperationEnum.OPEN.getValue() == this.getOpstatus()) {
        // �빺���򿪣������򿪣�Ԥռ=�빺������
        // ռ�������仯��=����������
        data = this.getItem().getNnum();
      }
      // else if (BillOperationEnum.DELETE.getValue() == this.getOpstatus()) {
      // ռ�������仯�� = 0 - �ɹ�����ɾ��ǰ�Ķ�������
      // data = MathTool.oppose(this.getItem().getNnum());
      // }
      // else if (BillOperationEnum.MODIFY.getValue() == this.getOpstatus()) {
      // ռ�������仯�� = �ɹ������޸ĺ�Ķ������� -�ɹ������޸�ǰ�Ķ�������
      // data =
      // MathTool.sub(this.getItem().getNnum(), this.getOriginItem()
      // .getNnum());
      // }
    }

    return data;
  }
}
