package nc.pubitf.pu.m21.ec;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * @since 6.0
 * @version 2011-5-16 ����01:09:47
 * @author wuxla
 */

public class OrderReceivePlanPubedViewVO extends AbstractDataView {

  public static class OrderReceivePlanPubedViewVOMeta extends DataViewMeta {
    public OrderReceivePlanPubedViewVOMeta() {
      this.init();
    }

    private void init() {
      // �����ƻ�����,�����ƻ��š����������������š������кš���������������Ʒ����
      // ���ɸ�������id�����κš���λ�����������������������ڡ��ƻ�
      // �������ڡ��ջ���֯�������ջ���ַ����������
      // �������������ƺš���ϵ�ˡ���ϵ��ʽ����ע
      // ����ts,��������ts,�����ƻ�ts,��������,����λ
      this.add(OrderReceivePlanVO.class, new String[] {
        OrderReceivePlanVO.PK_ORDER_BB1, OrderReceivePlanVO.VBILLCODE,
        OrderReceivePlanVO.VECBILLCODE, OrderReceivePlanVO.PK_ORDER,
        OrderReceivePlanVO.CROWNOBB1, OrderReceivePlanVO.PK_ORDER_B,
        OrderReceivePlanVO.PK_MATERIAL, OrderReceivePlanVO.VFREE1,
        OrderReceivePlanVO.VFREE2, OrderReceivePlanVO.VFREE3,
        OrderReceivePlanVO.VFREE4, OrderReceivePlanVO.VFREE5,
        OrderReceivePlanVO.VFREE6, OrderReceivePlanVO.VFREE7,
        OrderReceivePlanVO.VFREE8, OrderReceivePlanVO.VFREE9,
        OrderReceivePlanVO.VFREE10, OrderReceivePlanVO.VBATCHCODE,
        OrderReceivePlanVO.CASTUNITID, OrderReceivePlanVO.NASTNUM,
        OrderReceivePlanVO.DSENDDATE, OrderReceivePlanVO.DPLANARRVDATE,
        OrderReceivePlanVO.PK_ARRVSTOORG, OrderReceivePlanVO.VCHANGERATE,
        OrderReceivePlanVO.VQTUNITRATE, OrderReceivePlanVO.PK_RECEIVEADDRESS,
        OrderReceivePlanVO.VEHICLETYPE, OrderReceivePlanVO.VEHICLELICENSE,
        OrderReceivePlanVO.VLINKPSN, OrderReceivePlanVO.VLINKTYPE,
        OrderReceivePlanVO.VMEMO, OrderReceivePlanVO.TS,
        OrderReceivePlanVO.PK_ORG, OrderReceivePlanVO.PK_ARRVSTOORG_V,
        OrderReceivePlanVO.PK_MATERIAL, OrderReceivePlanVO.PK_ORG_V,
        OrderReceivePlanVO.PK_SRCMATERIAL, OrderReceivePlanVO.NNUM,
        OrderReceivePlanVO.CUNITID, OrderReceivePlanVO.TS
      });
      this.add(OrderItemVO.class, new String[] {
        OrderItemVO.TS, OrderItemVO.CROWNO, OrderItemVO.CPRODUCTORID,
        OrderItemVO.PK_PSFINANCEORG, OrderItemVO.PK_PSFINANCEORG_V,
        OrderItemVO.PK_REQDEPT_V, OrderItemVO.PK_REQDEPT,
        OrderItemVO.PK_RECVSTORDOC
      });
      this.add(OrderHeaderVO.class, new String[] {
        OrderHeaderVO.TS, OrderHeaderVO.PK_DEPT_V, OrderHeaderVO.PK_DEPT
      });
      this.addRelation(OrderReceivePlanVO.class, OrderReceivePlanVO.PK_ORDER_B,
          OrderItemVO.class, OrderItemVO.PK_ORDER_B);
      // meta.add(OrderHeaderVO.class, new String[] {
      // OrderHeaderVO.DBILLDATE
      // });
      this.addRelation(OrderReceivePlanVO.class, OrderReceivePlanVO.PK_ORDER,
          OrderHeaderVO.class, OrderHeaderVO.PK_ORDER);
    }
  }

  private static final long serialVersionUID = -7137164073277805712L;

  private UFDateTime orderBTs;

  private UFDateTime orderTs;

  // �ɹ�����
  private String pk_dept;

  // �ɹ�����
  private String pk_dept_v;

  // ���������֯
  private String pk_psfinanceorg;

  // ���������֯
  private String pk_psfinanceorg_v;

  // �ջ��ֿ�
  private String pk_recvstordoc;

  // ������
  private String pk_reqdept;

  // ������
  private String pk_reqdept_v;

  private String vordercode;

  /** ��λ getter ���� */
  public String getCastunitid() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.CASTUNITID);
  }

  /** �������� getter ���� */
  public String getCproductorid() {
    return (String) this.getAttributeValue(OrderItemVO.CPRODUCTORID);
  }

  /** �����к� getter ���� */
  public String getCrownobb1() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.CROWNOBB1);
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
        OrderReceivePlanPubedViewVOMeta.class);
  }

  /** �������� getter ���� */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(OrderReceivePlanVO.NASTNUM);
  }

  /** ������ getter ���� */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(OrderReceivePlanVO.NNUM);
  }

  /** ��������ts getter ���� */
  public UFDateTime getOrderBTs() {
    return this.orderBTs;
  }

  /** �����ƻ�ts getter ���� */
  public UFDateTime getOrderReceivePlanTs() {
    return (UFDateTime) this.getAttributeValue(OrderReceivePlanVO.TS);
  }

  /** ����ts getter ���� */
  public UFDateTime getOrderTs() {
    return this.orderTs;
  }

  /** �ջ������֯ getter ���� */
  public String getPk_arrvstoorg() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_ARRVSTOORG);
  }

  /** �ջ������֯�汾��Ϣ getter ���� */
  public String getPk_arrvstoorg_v() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_ARRVSTOORG_V);
  }

  /**
   * �ɹ�����������Ϣ
   * 
   * @return
   */
  public String getPk_dept() {
    return this.pk_dept;
  }

  /**
   * �ɹ�����
   * 
   * @return
   */
  public String getPk_dept_v() {
    return this.pk_dept_v;
  }

  /** ���ϰ汾��Ϣ getter ���� */
  public String getPk_material() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_MATERIAL);
  }

  /** �ɹ����� getter ���� */
  public String getPk_order() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_ORDER);
  }

  /** �����ƻ����ӱ� getter ���� */
  public String getPk_order_b() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_ORDER_B);
  }

  /** �����ƻ� getter ���� */
  public String getPk_order_bb1() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_ORDER_BB1);
  }

  /** �ɹ���֯ԭʼ��Ϣ getter ���� */
  public String getPk_org() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_ORG);
  }

  /** �ɹ���֯ getter ���� */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_ORG_V);
  }

  /**
   * ���������֯������Ϣ
   * 
   * @return
   */
  public String getPk_psfinanceorg() {
    return this.pk_psfinanceorg;
  }

  /**
   * ���������֯
   * 
   * @return
   */
  public String getPk_psfinanceorg_v() {
    return this.pk_psfinanceorg_v;
  }

  /** �ջ���ַ getter ���� */
  public String getPk_receiveaddress() {
    return (String) this
        .getAttributeValue(OrderReceivePlanVO.PK_RECEIVEADDRESS);
  }

  /**
   * �ջ��ֿ�
   * 
   * @return
   */
  public String getPk_recvstordoc() {
    return this.pk_recvstordoc;
  }

  /**
   * ������
   * 
   * @return
   */
  public String getPk_reqdept() {
    return this.pk_reqdept;
  }

  /**
   * ������������Ϣ
   * 
   * @return
   */
  public String getPk_reqdept_v() {
    return this.pk_reqdept_v;
  }

  /** ������Ϣ getter ���� */
  public String getPk_srcmaterial() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.PK_SRCMATERIAL);
  }

  /** ts getter ���� */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(OrderReceivePlanVO.TS);
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
   * ��ȡEC��������
   * 
   * @return EC��������
   */
  public String getVecbillcode() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VECBILLCODE);
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

  /**
   * ������
   * 
   * @return
   */
  public String getVordercode() {
    return this.vordercode;
  }

  /** ���۵�λ������ getter ���� */
  public String getVqtunitrate() {
    return (String) this.getAttributeValue(OrderReceivePlanVO.VQTUNITRATE);
  }

  public void setOrderBTs(UFDateTime orderBTs) {
    this.orderBTs = orderBTs;
  }

  public void setOrderTs(UFDateTime orderTs) {
    this.orderTs = orderTs;
  }

  /**
   * �ɹ�����������Ϣ
   * 
   * @param pk_dept
   */
  public void setPk_dept(String pk_dept) {
    this.pk_dept = pk_dept;
  }

  /**
   * �ɹ�����������Ϣ
   * 
   * @param pk_dept_v
   */
  public void setPk_dept_v(String pk_dept_v) {
    this.pk_dept_v = pk_dept_v;
  }

  /** ���ϰ汾��Ϣ setter ���� */
  public void setPk_material(String pk_material) {
    this.setAttributeValue(OrderReceivePlanVO.PK_MATERIAL, pk_material);
  }

  /**
   * ���������֯������Ϣ
   * 
   * @param pk_psfinanceorg
   */
  public void setPk_psfinanceorg(String pk_psfinanceorg) {
    this.pk_psfinanceorg = pk_psfinanceorg;
  }

  /**
   * ���������֯
   * 
   * @param pk_psfinanceorg_v
   */
  public void setPk_psfinanceorg_v(String pk_psfinanceorg_v) {
    this.pk_psfinanceorg_v = pk_psfinanceorg_v;
  }

  /**
   * �ջ��ֿ�
   * 
   * @param pk_recvstordoc
   */
  public void setPk_recvstordoc(String pk_recvstordoc) {
    this.pk_recvstordoc = pk_recvstordoc;
  }

  /**
   * ������
   * 
   * @param pk_reqdept
   */
  public void setPk_reqdept(String pk_reqdept) {
    this.pk_reqdept = pk_reqdept;
  }

  /**
   * ������������Ϣ
   * 
   * @param pk_reqdept_v
   */
  public void setPk_reqdept_v(String pk_reqdept_v) {
    this.pk_reqdept_v = pk_reqdept_v;
  }

  public void setVordercode(String vordercode) {
    this.vordercode = vordercode;
  }
}
