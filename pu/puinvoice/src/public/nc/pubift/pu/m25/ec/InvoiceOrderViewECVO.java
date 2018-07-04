package nc.pubift.pu.m25.ec;

import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * @since 6.0
 * @version 2011-5-16 ����12:09:48
 * @author wuxla
 */

public class InvoiceOrderViewECVO extends AbstractDataView {
  public static class InvoiceOrderViewECVOMeta extends DataViewMeta {
    public InvoiceOrderViewECVOMeta() {
      this.init();
    }

    private void init() {
      // �����ӱ�pk����Ʊ�š���Ʊ���ڡ�Ʊ�����ڡ�����id������
      // ����id�����ɸ�������id�����κš�(����)��λ��(����)�������ۼƽ���������
      // �ۼƽ����(����)�����ʡ�˰�ʡ����Ҽ�˰�ϼơ�˰�롢��˰�ϼ�
      // ����λ��������
      // ��Ŀ���������̡��ͻ�	
      this.add(InvoiceItemVO.class, new String[] {
        InvoiceItemVO.PK_ORDER_B, InvoiceItemVO.PK_MATERIAL,
        InvoiceItemVO.VFREE1, InvoiceItemVO.VFREE2, InvoiceItemVO.VFREE3,
        InvoiceItemVO.VFREE4, InvoiceItemVO.VFREE5, InvoiceItemVO.VFREE6,
        InvoiceItemVO.VFREE7, InvoiceItemVO.VFREE8, InvoiceItemVO.VFREE9,
        InvoiceItemVO.VFREE10, InvoiceItemVO.VBATCHCODE,
        InvoiceItemVO.CASTUNITID, InvoiceItemVO.NASTNUM,
        InvoiceItemVO.NACCUMSETTNUM, InvoiceItemVO.NACCUMSETTMNY,
        InvoiceItemVO.VCHANGERATE, InvoiceItemVO.NTAXRATE,
        InvoiceItemVO.NTAXMNY, InvoiceItemVO.CTAXCODEID,
        InvoiceItemVO.NORIGTAXMNY, InvoiceItemVO.CUNITID, InvoiceItemVO.NNUM,
        InvoiceItemVO.CPROJECTID, InvoiceItemVO.CPRODUCTORID, InvoiceItemVO.CASSCUSTID
      });
      this.add(InvoiceHeaderVO.class, new String[] {
        InvoiceHeaderVO.VBILLCODE, InvoiceHeaderVO.DBILLDATE,
        InvoiceHeaderVO.DARRIVEDATE, InvoiceHeaderVO.CORIGCURRENCYID,
        InvoiceHeaderVO.NEXCHANGERATE
      });
      this.addRelation(InvoiceItemVO.class, InvoiceItemVO.PK_INVOICE,
          InvoiceHeaderVO.class, InvoiceHeaderVO.PK_INVOICE);
    }
  }

  private static final long serialVersionUID = -1124444198235770366L;

  /** �����ۣ���λ getter ���� */
  public String getCastunitid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CASTUNITID);
  }

  /** ���� getter ���� */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.CORIGCURRENCYID);
  }

  /** ˰�� getter ���� */
  public String getCtaxcodeid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CTAXCODEID);
  }

  /** ����λ getter ���� */
  public String getCunitid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CUNITID);
  }

  /** Ʊ������ getter ���� */
  public UFDate getDarrivedate() {
    return (UFDate) this.getAttributeValue(InvoiceHeaderVO.DARRIVEDATE);
  }

  /** ��Ʊ���� getter ���� */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(InvoiceHeaderVO.DBILLDATE);
  }

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getDataViewMeta(
        InvoiceOrderViewECVOMeta.class);
  }

  /** �ۼƱ��ҽ����� getter ���� */
  public UFDouble getNaccumsettmny() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NACCUMSETTMNY);
  }

  /** �ۼƽ��������� getter ���� */
  public UFDouble getNaccumsettnum() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NACCUMSETTNUM);
  }

  /** �����ۣ����� getter ���� */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NASTNUM);
  }

  /** ���� getter ���� */
  public UFDouble getNexchangerate() {
    return (UFDouble) this.getAttributeValue(InvoiceHeaderVO.NEXCHANGERATE);
  }

  /** ������ getter ���� */
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NNUM);
  }

  /** ��˰�ϼ� getter ���� */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NORIGTAXMNY);
  }

  /** ���Ҽ�˰�ϼ� getter ���� */
  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NTAXMNY);
  }

  /** ˰�� getter ���� */
  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(InvoiceItemVO.NTAXRATE);
  }

  /** ����(VID) getter ���� */
  public String getPk_material() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_MATERIAL);
  }

  /** �����ӱ�pk getter ���� */
  public String getPk_order_b() {
    return (String) this.getAttributeValue(InvoiceItemVO.PK_ORDER_B);
  }

  /** ���κ� getter ���� */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(InvoiceItemVO.VBATCHCODE);
  }

  /** ��Ʊ�� getter ���� */
  public String getVbillcode() {
    return (String) this.getAttributeValue(InvoiceHeaderVO.VBILLCODE);
  }

  /** (����)������ getter ���� */
  public String getVchangerate() {
    return (String) this.getAttributeValue(InvoiceItemVO.VCHANGERATE);
  }

  /** ���ɸ�������1 getter ���� */
  public String getVfree1() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE1);
  }

  /** ���ɸ�������10 getter ���� */
  public String getVfree10() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE10);
  }

  /** ���ɸ�������2 getter ���� */
  public String getVfree2() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE2);
  }

  /** ���ɸ�������3 getter ���� */
  public String getVfree3() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE3);
  }

  /** ���ɸ�������4 getter ���� */
  public String getVfree4() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE4);
  }

  /** ���ɸ�������5 getter ���� */
  public String getVfree5() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE5);
  }

  /** ���ɸ�������6 getter ���� */
  public String getVfree6() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE6);
  }

  /** ���ɸ�������7 getter ���� */
  public String getVfree7() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE7);
  }

  /** ���ɸ�������8 getter ���� */
  public String getVfree8() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE8);
  }

  /** ���ɸ�������9 getter ���� */
  public String getVfree9() {
    return (String) this.getAttributeValue(InvoiceItemVO.VFREE9);
  }
  
  /** ��Ŀ getter ���� */
  public String getCprojectid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CPROJECTID);
  }
  
  /** �������� getter ���� */
  public String getCproductorid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CPRODUCTORID);
  }
  
  /** �ͻ� getter ���� */
  public String getCasscustid() {
    return (String) this.getAttributeValue(InvoiceItemVO.CASSCUSTID);
  }
}
