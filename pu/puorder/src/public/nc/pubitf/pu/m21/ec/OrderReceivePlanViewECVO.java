package nc.pubitf.pu.m21.ec;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * @since 6.0
 * @version 2011-5-16 ����01:05:55
 * @author wuxla
 */

public class OrderReceivePlanViewECVO extends AbstractDataView {
  public static class OrderReceivePlanViewECVOMeta extends DataViewMeta {
    public OrderReceivePlanViewECVOMeta() {
      this.init();
    }

    private void init() {
      // �����ƻ�����,�����ƻ��š����������������š������кš���������������Ʒ����
      // ���ɸ�������id�����κš���λ�����������������������ڡ��ƻ�
      // �������ڡ��ջ���֯�������ջ���ַ����������
      // �������������ƺš���ϵ�ˡ���ϵ��ʽ����ע
      // ����ts,��������ts,�����ƻ�ts
      // �޸ĳɶ���������Ϣ
      // ���������������š������кš����������������κš���Ʒ���� �������ۼƵ����ƻ�������,�ջ���֯�������ջ���ַ����
      // mengjian by 20150124 ���������������֯���ջ��ֿ⣬������
      this.add(OrderItemVO.class, new String[] {
        OrderItemVO.PK_ORDER, OrderItemVO.CROWNO, OrderItemVO.PK_ORDER_B,
        OrderItemVO.PK_MATERIAL, OrderItemVO.PK_ARRVSTOORG, OrderItemVO.VFREE1,
        OrderItemVO.VFREE2, OrderItemVO.VFREE3, OrderItemVO.VFREE4,
        OrderItemVO.VFREE5, OrderItemVO.VFREE6, OrderItemVO.VFREE7,
        OrderItemVO.VFREE8, OrderItemVO.VFREE9, OrderItemVO.VFREE10,
        OrderItemVO.VBATCHCODE, OrderItemVO.CUNITID, OrderItemVO.CASTUNITID,
        OrderItemVO.NNUM, OrderItemVO.NACCUMRPNUM, OrderItemVO.PK_ARRVSTOORG,
        OrderItemVO.PK_RECEIVEADDRESS, OrderItemVO.TS, OrderItemVO.PK_ORG,
        OrderItemVO.VCHANGERATE, OrderItemVO.CPRODUCTORID,
        OrderItemVO.CPROJECTID, OrderItemVO.PK_ARRVSTOORG_V,
        OrderItemVO.PK_ORG_V, OrderItemVO.PK_SRCMATERIAL,
        OrderItemVO.VQTUNITRATE, OrderItemVO.CQTUNITID, OrderItemVO.NASTNUM,
        OrderItemVO.PK_PSFINANCEORG, OrderItemVO.PK_PSFINANCEORG_V,
        OrderItemVO.PK_REQDEPT_V, OrderItemVO.PK_REQDEPT,
        OrderItemVO.PK_RECVSTORDOC
      });
      this.add(OrderHeaderVO.class, new String[] {
        OrderHeaderVO.TS, OrderHeaderVO.PK_DEPT_V, OrderHeaderVO.PK_DEPT
      });
      this.addRelation(OrderItemVO.class, OrderItemVO.PK_ORDER,
          OrderHeaderVO.class, OrderHeaderVO.PK_ORDER);
    }
  }

  private static final long serialVersionUID = -131675506592535226L;

  private UFDateTime orderTs;

  private String vordercode;

  /** ����λ getter ���� */
  public String getCastunitid() {
    return (String) this.getAttributeValue(OrderItemVO.CASTUNITID);
  }

  /** �������� getter ���� */
  public String getCproductorid() {
    return (String) this.getAttributeValue(OrderItemVO.CPRODUCTORID);
  }

  /** ��Ŀ getter ���� */
  public String getCprojectid() {
    return (String) this.getAttributeValue(OrderItemVO.CPROJECTID);
  }

  /** ���۵�λ getter ���� */
  public String getCqtunitid() {
    return (String) this.getAttributeValue(OrderItemVO.CQTUNITID);
  }

  /** �����к� getter ���� */
  public String getCrowno() {
    return (String) this.getAttributeValue(OrderItemVO.CROWNO);
  }

  /** ����λ getter ���� */
  public String getCunitid() {
    return (String) this.getAttributeValue(OrderItemVO.CUNITID);
  }

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getDataViewMeta(
        OrderReceivePlanViewECVOMeta.class);
  }

  /** �ۼƵ����ƻ������� getter ���� */
  public UFDouble getNaccumrpnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NACCUMRPNUM);
  }

  /** ���� getter ���� */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NASTNUM);
  }

  /** ������ getter ���� */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NNUM);
  }

  /** ��������ts getter ���� */
  public UFDateTime getOrderBTs() {
    return (UFDateTime) this.getAttributeValue(OrderItemVO.TS);
  }

  /** ����ts getter ���� */
  public UFDateTime getOrderTs() {
    return this.orderTs;
  }

  /** �ջ������֯ getter ���� */
  public String getPk_arrvstoorg() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ARRVSTOORG);
  }

  /** �ջ������֯�汾��Ϣ getter ���� */
  public String getPk_arrvstoorg_v() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ARRVSTOORG_V);
  }

  /** �ɹ����� getter ���� */
  public String getPk_dept() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_DEPT);
  }

  /** �ɹ����Ű汾��Ϣ getter ���� */
  public String getPk_dept_v() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_DEPT_V);
  }

  /** ���ϰ汾��Ϣ getter ���� */
  public String getPk_material() {
    return (String) this.getAttributeValue(OrderItemVO.PK_MATERIAL);
  }

  /** �ɹ����� getter ���� */
  public String getPk_order() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ORDER);
  }

  /** ������������ getter ���� */
  public String getPk_order_b() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ORDER_B);
  }

  /** �ɹ���֯�汾��Ϣ getter ���� */
  public String getPk_org() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ORG);
  }

  /** �ɹ���֯ getter ���� */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ORG_V);
  }

  /** ������֯ getter ���� */
  public String getPk_psfinanceorg() {
    return (String) this.getAttributeValue(OrderItemVO.PK_PSFINANCEORG);
  }

  /** ������֯�汾��Ϣ getter ���� */
  public String getPk_psfinanceorg_v() {
    return (String) this.getAttributeValue(OrderItemVO.PK_PSFINANCEORG_V);
  }

  /** �ջ���ַ getter ���� */
  public String getPk_receiveaddress() {
    return (String) this.getAttributeValue(OrderItemVO.PK_RECEIVEADDRESS);
  }

  /** �ջ��ֿ� getter ���� */
  public String getPk_recvstordoc() {
    return (String) this.getAttributeValue(OrderItemVO.PK_RECVSTORDOC);
  }

  /** ������ getter ���� */
  public String getPk_reqdept() {
    return (String) this.getAttributeValue(OrderItemVO.PK_REQDEPT);
  }

  /** �����Ű汾��Ϣ getter ���� */
  public String getPk_reqdept_v() {
    return (String) this.getAttributeValue(OrderItemVO.PK_REQDEPT_V);
  }

  /** ������Ϣ getter ���� */
  public String getPk_srcmaterial() {
    return (String) this.getAttributeValue(OrderItemVO.PK_SRCMATERIAL);
  }

  /** ���κ� getter ���� */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(OrderItemVO.VBATCHCODE);
  }

  /** ������ getter ���� */
  public String getVchangerate() {
    return (String) this.getAttributeValue(OrderItemVO.VCHANGERATE);
  }

  /** ���ɸ�������1 getter ���� */
  public String getVfree1() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE1);
  }

  /** ���ɸ�������10 getter ���� */
  public String getVfree10() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE10);
  }

  /** ���ɸ�������2 getter ���� */
  public String getVfree2() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE2);
  }

  /** ���ɸ�������3 getter ���� */
  public String getVfree3() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE3);
  }

  /** ���ɸ�������4 getter ���� */
  public String getVfree4() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE4);
  }

  /** ���ɸ�������5 getter ���� */
  public String getVfree5() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE5);
  }

  /** ���ɸ�������6 getter ���� */
  public String getVfree6() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE6);
  }

  /** ���ɸ�������7 getter ���� */
  public String getVfree7() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE7);
  }

  /** ���ɸ�������8 getter ���� */
  public String getVfree8() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE8);
  }

  /** ���ɸ�������9 getter ���� */
  public String getVfree9() {
    return (String) this.getAttributeValue(OrderItemVO.VFREE9);
  }

  /**
   * ������
   * 
   * @return
   */
  public String getVordercode() {
    return this.vordercode;
  }

  /** ���ۻ����� getter ���� */
  public String getVqtunitrate() {
    return (String) this.getAttributeValue(OrderItemVO.VQTUNITRATE);
  }

  public void setOrderTs(UFDateTime orderTs) {
    this.orderTs = orderTs;
  }

  public void setVordercode(String vordercode) {
    this.vordercode = vordercode;
  }

}
