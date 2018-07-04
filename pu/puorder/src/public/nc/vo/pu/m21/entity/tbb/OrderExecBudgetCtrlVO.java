package nc.vo.pu.m21.entity.tbb;

import nc.itf.tb.control.IFormulaFuncName;
import nc.pubitf.uapbd.CurrencyRateUtil;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.tbb.BillOperationEnum;
import nc.vo.pu.tbb.PuBillBusiSysReg;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.scmpub.util.TimeUtils;

/**
 * @since 6.0
 * @version 2011-3-16 ����02:12:06
 * @author yinfy
 */

public class OrderExecBudgetCtrlVO extends OrderBudgetCtrlVO {
  private static final long serialVersionUID = -1427239373310437786L;

  /** ������дǰ���ۻ��������� */
  private UFDouble naccumarrvnum_pre;

  /** ��Ʊ��дǰ���ۻ���Ʊ��� */
  private UFDouble naccuminvoicemny_pre;

  /** ��Ʊ��дǰ���ۻ���Ʊ���� */
  private UFDouble naccuminvoicenum_pre;

  /** ����дǰ���ۻ�������� */
  private UFDouble naccumstorenum_pre;

  /**
   * @param head
   * @param item
   * @param opstatus
   */
  public OrderExecBudgetCtrlVO(OrderHeaderVO head, OrderItemVO item,
      int opstatus, String execBilltype) {
    super(head, item, opstatus, execBilltype);
  }

  @Override
  public String getBillType() {
    return this.getExecBilltype() + "-" + POBillType.Order.getCode();
  }

  @Override
  public String getDataType() {
    // ִ����
    return IFormulaFuncName.UFIND;

  }

  @Override
  public UFDouble[] getExeData(String direction, String obj, String extObj) {
    if (PuBillBusiSysReg.OCCUR.equals(obj)) {
      if (PuBillBusiSysReg.NNUM.equals(extObj)) {
        UFDouble data = this.getRunNumByOp();
        if (null == data) {
          return null;
        }
        return new UFDouble[] {
          data, data, data, data
        };
      }
      else if (PuBillBusiSysReg.NTAXMNY.equals(extObj)) {
        UFDouble data = this.getRunMnyByOp();
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

  public UFDouble getNaccumarrvnum_pre() {
    return this.naccumarrvnum_pre;
  }

  public UFDouble getNaccuminvoicemny_pre() {
    return this.naccuminvoicemny_pre;
  }

  public UFDouble getNaccuminvoicenum_pre() {
    return this.naccuminvoicenum_pre;
  }

  public UFDouble getNaccumstorenum_pre() {
    return this.naccumstorenum_pre;
  }

  public void setNaccumarrvnum_pre(UFDouble naccumarrvnum_pre) {
    this.naccumarrvnum_pre = naccumarrvnum_pre;
  }

  public void setNaccuminvoicemny_pre(UFDouble naccuminvoicemny_pre) {
    this.naccuminvoicemny_pre = naccuminvoicemny_pre;
  }

  public void setNaccuminvoicenum_pre(UFDouble naccuminvoicenum_pre) {
    this.naccuminvoicenum_pre = naccuminvoicenum_pre;
  }

  public void setNaccumstorenum_pre(UFDouble naccumstorenum_pre) {
    this.naccumstorenum_pre = naccumstorenum_pre;
  }

  private UFDouble getRunMnyByOp() {
    UFDouble data = null;
    if (BillOperationEnum.CLOSE.getValue() == this.getOpstatus()) {
      // ռ�ý��仯�� = �����ı�����
      data = this.getMnyByBilltype();
    }
    else if (BillOperationEnum.OPEN.getValue() == this.getOpstatus()) {
      // ִ�������仯��=0 - �ɹ������ۼƿ�Ʊ������0 - �ɹ������ۼƵ���������
      // 0 - �ɹ������ۼ����������0 - �ɹ������ۼƿ�Ʊ����
      data = MathTool.oppose(this.getMnyByBilltype());
    }
    return data;
  }

  private UFDouble getRunNumByOp() {
    UFDouble data = null;
    if (BillOperationEnum.CLOSE.getValue() == this.getOpstatus()) {
      // ռ�ý��仯�� = �����ı�����
      data = this.getNumByBilltype();
    }
    else if (BillOperationEnum.OPEN.getValue() == this.getOpstatus()) {
      // ִ�������仯��=0 - �ɹ������ۼƿ�Ʊ������0 - �ɹ������ۼƵ���������
      // 0 - �ɹ������ۼ����������0 - �ɹ������ۼƿ�Ʊ����
      data = MathTool.oppose(this.getNumByBilltype());
    }
    return data;
  }

  protected UFDouble getMnyByBilltype() {
    UFDouble data = null;
    if (POBillType.Order.getCode().equals(this.getExecBilltype())) {
      data = this.getItem().getNorigtaxmny();
    }
    else if (POBillType.Arrive.getCode().equals(this.getExecBilltype())) {
      if (BillOperationEnum.OPEN.getValue() == this.getOpstatus()) {
        data =
            this.getNaccumarrvnum_pre() == null ? this.getItem()
                .getNaccumarrvnum() : this.getNaccumarrvnum_pre();
      }
      else {
        data = this.getItem().getNaccumarrvnum();
      }
      if (MathTool.compareTo(this.getItem().getNnum(), data) == 0) {
        data = this.getItem().getNorigtaxmny();
      }
      else {
        data =
            MathTool.nvl(data).multiply(
                MathTool.nvl(this.getItem().getNorigtaxnetprice()));
      }

      ScaleUtils utils = new ScaleUtils(this.getHead().getPk_group());
      data = utils.adjustMnyScale(data, this.getHead().getCorigcurrencyid());
    }
    else if (POBillType.Invoice.getCode().equals(this.getExecBilltype())) {
      CurrencyRateUtil util =
          CurrencyRateUtil
              .getInstanceByOrg(this.getItem().getPk_psfinanceorg());
      if (BillOperationEnum.OPEN.getValue() == this.getOpstatus()) {
        data =
            this.getNaccuminvoicemny_pre() == null ? this.getItem()
                .getNaccuminvoicemny() : this.getNaccuminvoicemny_pre();
      }
      else {
        data = this.getItem().getNaccuminvoicemny();
      }
      try {
        data =
            MathTool.nvl(util.getAmountByOpp(this.getItem().getCcurrencyid(),
                this.getHead().getCorigcurrencyid(), data, null,
                TimeUtils.getsrvBaseDate()));
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }

    }
    else if (ICBillType.PurchaseIn.getCode().equals(this.getExecBilltype())) {
      if (BillOperationEnum.OPEN.getValue() == this.getOpstatus()) {
        data =
            this.getNaccumstorenum_pre() == null ? this.getItem()
                .getNaccumstorenum() : this.getNaccumstorenum_pre();

      }
      else {
        data = this.getItem().getNaccumstorenum();
      }
      // 2012-8-10��������ǿ��������ȷ�ϣ�����ۼ��������=������������һ��β�ֱ��ȡ�����ļ�˰�ϼ�
      if (MathTool.compareTo(this.getItem().getNnum(), data) == 0) {
        data = this.getItem().getNorigtaxmny();
      }
      else {
        data =
            MathTool.nvl(data).multiply(
                MathTool.nvl(this.getItem().getNorigtaxnetprice()));
      }
      ScaleUtils utils = new ScaleUtils(this.getHead().getPk_group());
      data = utils.adjustMnyScale(data, this.getHead().getCorigcurrencyid());
    }
    return data;
  }

  protected UFDouble getNumByBilltype() {
    UFDouble data = null;
    if (POBillType.Order.getCode().equals(this.getExecBilltype())) {
      data = this.getItem().getNnum();
    }
    else if (POBillType.Arrive.getCode().equals(this.getExecBilltype())) {
      if (BillOperationEnum.OPEN.getValue() == this.getOpstatus()) {
        data =
            this.getNaccumarrvnum_pre() == null ? this.getItem()
                .getNaccumarrvnum() : this.getNaccumarrvnum_pre();
      }
      else {
        data = this.getItem().getNaccumarrvnum();
      }
    }
    else if (POBillType.Invoice.getCode().equals(this.getExecBilltype())) {
      if (BillOperationEnum.OPEN.getValue() == this.getOpstatus()) {
        data =
            this.getNaccuminvoicenum_pre() == null ? this.getItem()
                .getNaccuminvoicenum() : this.getNaccuminvoicenum_pre();
      }
      else {
        data = this.getItem().getNaccuminvoicenum();
      }
    }
    else if (ICBillType.PurchaseIn.getCode().equals(this.getExecBilltype())) {
      if (BillOperationEnum.OPEN.getValue() == this.getOpstatus()) {
        data =
            this.getNaccumstorenum_pre() == null ? this.getItem()
                .getNaccumstorenum() : this.getNaccumstorenum_pre();
      }
      else {
        data = this.getItem().getNaccumstorenum();
      }
    }
    return data;
  }
}
