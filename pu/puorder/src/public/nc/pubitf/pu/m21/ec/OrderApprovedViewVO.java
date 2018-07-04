package nc.pubitf.pu.m21.ec;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * @since 6.0
 * @version 2011-5-16 ����11:19:53
 * @author wuxla
 */

public class OrderApprovedViewVO extends AbstractDataView {

  public static class OrderApprovedViewVOMeta extends DataViewMeta {
    public OrderApprovedViewVOMeta() {
      init();
    }

    private void init() {
      // �����������ɹ������š��ɹ���֯id���ɹ�����id���ɹ�Աid����������
      // ��Ӧ�̷�����ַid����Ʊ��id������Э��id����ע,���䷽ʽ
      add(OrderHeaderVO.class, new String[] {
        OrderHeaderVO.PK_ORDER, OrderHeaderVO.VBILLCODE, OrderHeaderVO.PK_ORG,
        OrderHeaderVO.PK_DEPT, OrderHeaderVO.CEMPLOYEEID,
        OrderHeaderVO.DBILLDATE, OrderHeaderVO.PK_DELIVERADD,
        OrderHeaderVO.PK_INVCSUPLLIER, OrderHeaderVO.PK_PAYTERM,
        OrderHeaderVO.VMEMO, OrderHeaderVO.PK_TRANSPORTTYPE, OrderHeaderVO.TS
      });
    }
  }

  private static final long serialVersionUID = -6829820471510269930L;

  /** �ɹ�Ա getter ���� */
  public String getCemployeeid() {
    return (String) this.getAttributeValue(OrderHeaderVO.CEMPLOYEEID);
  }

  /** �������� getter ���� */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(OrderHeaderVO.DBILLDATE);
  }

  @Override
  public IDataViewMeta getMetaData() {

    return DataViewMetaFactory.getInstance().getDataViewMeta(
        OrderApprovedViewVOMeta.class);
  }

  /** ��Ӧ�̷�����ַ getter ���� */
  public String getPk_deliveradd() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_DELIVERADD);
  }

  /** �ɹ��������°汾 getter ���� */
  public String getPk_dept() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_DEPT);
  }

  /** ��Ʊ��Ӧ�� getter ���� */
  public String getPk_invcsupllier() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_INVCSUPLLIER);
  }

  /** �ɹ����� getter ���� */
  public String getPk_order() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_ORDER);
  }

  /** �ɹ���֯�汾��Ϣ getter ���� */
  public String getPk_org() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_ORG);
  }

  /** ����Э�� getter ���� */
  public String getPk_payterm() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_PAYTERM);
  }

  /** ���䷽ʽ getter ���� */
  public String getPk_transporttype() {
    return (String) this.getAttributeValue(OrderHeaderVO.PK_TRANSPORTTYPE);
  }

  /** ts getter ���� */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(OrderHeaderVO.TS);
  }

  /** ������� getter ���� */
  public String getVbillcode() {
    return (String) this.getAttributeValue(OrderHeaderVO.VBILLCODE);
  }

  /** ��ע getter ���� */
  public String getVmemo() {
    return (String) this.getAttributeValue(OrderHeaderVO.VMEMO);
  }
}
