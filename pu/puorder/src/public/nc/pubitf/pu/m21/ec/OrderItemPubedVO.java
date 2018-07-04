package nc.pubitf.pu.m21.ec;

import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * @since 6.0
 * @version 2011-5-16 ����02:38:19
 * @author wuxla
 */

public class OrderItemPubedVO extends AbstractDataView {

  public static class OrderItemPubedVOMeta extends DataViewMeta {
    public OrderItemPubedVOMeta() {
      this.init();
    }

    private void init() {
      /**
       * ���������������ӱ���������ͬ�š�����id�����ɸ�������id�����κš�
       * ��λid������������ȷ���������������ۡ���˰���ۡ��ۿ���(%)������˰��
       * �ۡ���˰�ϼơ�˰�ʣ�%����˰�����id�������ƻ��������ڡ�ȷ�ϼƻ���
       * �����ڡ����䷽ʽid���ջ���ַid����������id�������ȼ�id
       * ԭʼ���������������ʡ�����λ��TS��������������λ������˰���ۡ�������
       * mengjian by 20150124 ���������������֯���ջ���֯���ջ��ֿ⣬������
       */
      // ��Ŀ���������̡��ͻ�
      this.add(OrderItemVO.class, new String[] {
        OrderItemVO.PK_ORDER, OrderItemVO.PK_ORDER_B,
        OrderItemVO.VCONTRACTCODE, OrderItemVO.PK_MATERIAL, OrderItemVO.VFREE1,
        OrderItemVO.VFREE2, OrderItemVO.VFREE3, OrderItemVO.VFREE4,
        OrderItemVO.VFREE5, OrderItemVO.VFREE6, OrderItemVO.VFREE7,
        OrderItemVO.VFREE8, OrderItemVO.VFREE9, OrderItemVO.VFREE10,
        OrderItemVO.VBATCHCODE, OrderItemVO.CQTUNITID, OrderItemVO.NQTUNITNUM,
        OrderItemVO.NQTORIGTAXPRICE, OrderItemVO.NITEMDISCOUNTRATE,
        OrderItemVO.NORIGORDERPRICE, OrderItemVO.NORIGTAXMNY,
        OrderItemVO.NTAXRATE, OrderItemVO.NTAX, OrderItemVO.CORIGCURRENCYID,
        OrderItemVO.DORIGPLANARRVDATE, OrderItemVO.DPLANARRVDATE,
        OrderItemVO.ISTORESTATUS, OrderItemVO.PK_RECEIVEADDRESS,
        OrderItemVO.CPRODUCTORID, OrderItemVO.CQUALITYLEVELID,
        OrderItemVO.NORIGORDERNUM, OrderItemVO.VCHANGERATE,
        OrderItemVO.CASTUNITID, OrderItemVO.TS, OrderItemVO.NASTNUM,
        OrderItemVO.CUNITID, OrderItemVO.NORIGTAXPRICE, OrderItemVO.NNUM,
        OrderItemVO.VQTUNITRATE, OrderItemVO.NQTORIGTAXNETPRC,
        OrderItemVO.CPROJECTID, OrderItemVO.CASSCUSTID,
        OrderItemVO.PK_PSFINANCEORG, OrderItemVO.PK_PSFINANCEORG_V,
        OrderItemVO.PK_RECVSTORDOC, OrderItemVO.PK_REQSTOORG_V,
        OrderItemVO.PK_REQSTOORG, OrderItemVO.PK_REQDEPT_V,
        OrderItemVO.PK_REQDEPT
      });
    }
  }

  private static final long serialVersionUID = -27159350147806840L;

  /** �ͻ� getter ���� */
  public String getCasscustid() {
    return (String) this.getAttributeValue(OrderItemVO.CASSCUSTID);
  }

  /** ��λ getter ���� */
  public String getCastunitid() {
    return (String) this.getAttributeValue(OrderItemVO.CASTUNITID);
  }

  /** ���� getter ���� */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(OrderItemVO.CORIGCURRENCYID);
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

  /** �����ȼ� getter ���� */
  public String getCqualitylevelid() {
    return (String) this.getAttributeValue(OrderItemVO.CQUALITYLEVELID);
  }

  /** ����λ getter ���� */
  public String getCunitid() {
    return (String) this.getAttributeValue(OrderItemVO.CUNITID);
  }

  /** ԭʼ�ƻ��������� getter ���� */
  public UFDate getDorigplanarrvdate() {
    return (UFDate) this.getAttributeValue(OrderItemVO.DORIGPLANARRVDATE);
  }

  /** �ƻ��������� getter ���� */
  public UFDate getDplanarrvdate() {
    return (UFDate) this.getAttributeValue(OrderItemVO.DPLANARRVDATE);
  }

  /** ��Ӧ�̽���״̬ getter ���� */
  public Integer getIstorestatus() {
    return (Integer) this.getAttributeValue(OrderItemVO.ISTORESTATUS);
  }

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getDataViewMeta(
        OrderItemPubedVOMeta.class);
  }

  /** ���� getter ���� */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NASTNUM);
  }

  /** �ۿ� getter ���� */
  public UFDouble getNitemdiscountrate() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NITEMDISCOUNTRATE);
  }

  /** ������ getter ���� */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NNUM);
  }

  /** ԭʼ�������� getter ���� */
  public UFDouble getNorigordernum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NORIGORDERNUM);
  }

  /** ԭʼ��������˰���� getter ���� */
  public UFDouble getNorigorderprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NORIGORDERPRICE);
  }

  /** ��˰�ϼ� getter ���� */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NORIGTAXMNY);
  }

  /** ����˰���� getter ���� */
  public UFDouble getNorigtaxprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NORIGTAXPRICE);
  }

  /** ���ۺ�˰���� getter ���� */
  public UFDouble getNqtorigtaxnetprc() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NQTORIGTAXNETPRC);
  }

  /** ��˰���� getter ���� */
  public UFDouble getNqtorigtaxprice() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NQTORIGTAXPRICE);
  }

  /** �������� getter ���� */
  public UFDouble getNqtunitnum() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NQTUNITNUM);
  }

  /** ˰�� getter ���� */
  public UFDouble getNtax() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NTAX);
  }

  /** ˰�� getter ���� */
  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(OrderItemVO.NTAXRATE);
  }

  /** �ջ������֯�汾��Ϣ getter ���� */
  public String getPk_arrvstoorg() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ARRVSTOORG);
  }

  /** �ջ������֯ getter ���� */
  public String getPk_arrvstoorg_v() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ARRVSTOORG_V);
  }

  /** ���ϰ汾��Ϣ getter ���� */
  public String getPk_material() {
    return (String) this.getAttributeValue(OrderItemVO.PK_MATERIAL);
  }

  /** �ɹ�������ϸ getter ���� */
  public String getPk_order() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ORDER);
  }

  /** �ɹ�������ϸ getter ���� */
  public String getPk_order_b() {
    return (String) this.getAttributeValue(OrderItemVO.PK_ORDER_B);
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

  /** ts getter ���� */
  public UFDateTime getTs() {
    return (UFDateTime) this.getAttributeValue(OrderItemVO.TS);
  }

  /** ���κ� getter ���� */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(OrderItemVO.VBATCHCODE);
  }

  /** ������ getter ���� */
  public String getVchangerate() {
    return (String) this.getAttributeValue(OrderItemVO.VCHANGERATE);
  }

  /** ��ͬ�� getter ���� */
  public String getVcontractcode() {
    return (String) this.getAttributeValue(OrderItemVO.VCONTRACTCODE);
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

  /** ���ۻ����� getter ���� */
  public String getVqtunitrate() {
    return (String) this.getAttributeValue(OrderItemVO.VQTUNITRATE);
  }
}
