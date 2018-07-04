package nc.pubitf.pu.m21.ec;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * @since 6.0
 * @version 2011-5-16 ����11:43:05
 * @author wuxla
 */

public class OrderReceivePlanECVO extends AbstractDataView {

  public static class OrderReceivePlanECVOMeta extends DataViewMeta {
    public OrderReceivePlanECVOMeta() {
      this.init();
    }

    private void init() {
      // �����ӱ������ƻ��š�����id�����ɸ�������id�����κš�
      // �ƻ��������ڡ��ջ������֯id���ջ���ַid��������ַid��
      // ����ʱ�䡢���乤��id�����ƺš���ϵ�����ơ���ϵ��ʽ����ע
      // ��λ������������λ�������������۵�λ��������������������
      this.add(OrderReceivePlanVO.class, new String[] {
        OrderReceivePlanVO.PK_ORDER_B, OrderReceivePlanVO.VBILLCODE,
        OrderReceivePlanVO.PK_MATERIAL, OrderReceivePlanVO.VFREE1,
        OrderReceivePlanVO.VFREE2, OrderReceivePlanVO.VFREE3,
        OrderReceivePlanVO.VFREE4, OrderReceivePlanVO.VFREE5,
        OrderReceivePlanVO.VFREE6, OrderReceivePlanVO.VFREE7,
        OrderReceivePlanVO.VFREE8, OrderReceivePlanVO.VFREE9,
        OrderReceivePlanVO.VFREE10, OrderReceivePlanVO.VBATCHCODE,
        OrderReceivePlanVO.CASTUNITID, OrderReceivePlanVO.DPLANARRVDATE,
        OrderReceivePlanVO.PK_ARRVSTOORG, OrderReceivePlanVO.PK_RECEIVEADDRESS,
        OrderReceivePlanVO.DSENDDATE, OrderReceivePlanVO.VEHICLETYPE,
        OrderReceivePlanVO.VEHICLELICENSE, OrderReceivePlanVO.VLINKPSN,
        OrderReceivePlanVO.VLINKTYPE, OrderReceivePlanVO.VMEMO,
        OrderReceivePlanVO.VCHANGERATE, OrderReceivePlanVO.CQTUNITID,
        OrderReceivePlanVO.CUNITID, OrderReceivePlanVO.NASTNUM,
        OrderReceivePlanVO.VQTUNITRATE, OrderReceivePlanVO.NQTUNITNUM,
        OrderReceivePlanVO.NNUM, OrderReceivePlanVO.PK_ORDER
      });
      this.add(OrderItemVO.class, new String[] {
        OrderItemVO.FISACTIVE,
      });
      this.add(OrderHeaderVO.class, new String[] {
        OrderHeaderVO.BFINALCLOSE
      });
      this.addRelation(OrderReceivePlanVO.class, OrderReceivePlanVO.PK_ORDER_B,
          OrderItemVO.class, OrderItemVO.PK_ORDER_B);
      this.addRelation(OrderReceivePlanVO.class, OrderReceivePlanVO.PK_ORDER,
          OrderHeaderVO.class, OrderHeaderVO.PK_ORDER);
    }
  }

  private static final long serialVersionUID = 5140784264143293098L;

  /** ��λ getter ���� */
  public String getCastunitid() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.CASTUNITID);
  }

  /** ���۵�λ getter ���� */
  public String getCqtunitid() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.CQTUNITID);
  }

  /** ����λ getter ���� */
  public String getCunitid() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.CUNITID);
  }

  /** �ƻ��������� getter ���� */
  public UFDate getDplanarrvdate() {
    return (UFDate) this.getAttributeValue(OrderReceivePlanVO.DPLANARRVDATE);
  }

  /**
   * ��ȡ��������
   * 
   * @return ��������
   */
  public UFDate getDsenddate() {
    return (UFDate) this.getAttributeValue(OrderReceivePlanVO.DSENDDATE);
  }

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getDataViewMeta(
        OrderReceivePlanECVOMeta.class);
  }

  /** ���� getter ���� */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(OrderReceivePlanVO.NASTNUM);
  }

  /** ������ getter ���� */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(OrderReceivePlanVO.NNUM);
  }

  /** �������� getter ���� */
  public UFDouble getNqtunitnum() {
    return (UFDouble) this.getAttributeValue(OrderReceivePlanVO.NQTUNITNUM);
  }

  /** �ջ������֯ getter ���� */
  public String getPk_arrvstoorg() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_ARRVSTOORG);
  }

  /** ���ϰ汾��Ϣ getter ���� */
  public String getPk_material() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_MATERIAL);
  }

  /** �������� getter ���� */
  public String getPk_order() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_ORDER);
  }

  /** �����ӱ�pk getter ���� */
  public String getPk_order_b() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_ORDER_B);
  }

  /** �ջ���ַ getter ���� */
  public String getPk_receiveaddress() {
    return (String) this
        .getAttributeValue(OrderReceivePlanVO.PK_RECEIVEADDRESS);
  }

  /** ���κ� getter ���� */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VBATCHCODE);
  }

  /** �����ƻ��� getter ���� */
  public String getVbillcode() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VBILLCODE);
  }

  /** ������ getter ���� */
  public String getVchangerate() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VCHANGERATE);
  }

  /**
   * ��ȡ���ƺ�
   * 
   * @return ���ƺ�
   */
  public String getVehiclelicense() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VEHICLELICENSE);
  }

  /**
   * ��ȡ���乤��
   * 
   * @return ���乤��
   */
  public String getVehicletype() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VEHICLETYPE);
  }

  /** ���ɸ�������1 getter ���� */
  public String getVfree1() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VFREE1);
  }

  /** ���ɸ�������10 getter ���� */
  public String getVfree10() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VFREE10);
  }

  /** ���ɸ�������2 getter ���� */
  public String getVfree2() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VFREE2);
  }

  /** ���ɸ�������3 getter ���� */
  public String getVfree3() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VFREE3);
  }

  /** ���ɸ�������4 getter ���� */
  public String getVfree4() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VFREE4);
  }

  /** ���ɸ�������5 getter ���� */
  public String getVfree5() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VFREE5);
  }

  /** ���ɸ�������6 getter ���� */
  public String getVfree6() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VFREE6);
  }

  /** ���ɸ�������7 getter ���� */
  public String getVfree7() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VFREE7);
  }

  /** ���ɸ�������8 getter ���� */
  public String getVfree8() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VFREE8);
  }

  /** ���ɸ�������9 getter ���� */
  public String getVfree9() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VFREE9);
  }

  /**
   * ��ȡ��ϵ��
   * 
   * @return ��ϵ��
   */
  public String getVlinkpsn() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VLINKPSN);
  }

  /**
   * ��ȡ��ϵ��ʽ
   * 
   * @return ��ϵ��ʽ
   */
  public String getVlinktype() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VLINKTYPE);
  }

  /** ��ע getter ���� */
  public String getVmemo() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VMEMO);
  }

  /** ���ۻ����� getter ���� */
  public String getVqtunitrate() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VQTUNITRATE);
  }

}
