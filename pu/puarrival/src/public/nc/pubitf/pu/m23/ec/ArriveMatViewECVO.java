package nc.pubitf.pu.m23.ec;

import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * @since 6.0
 * @version 2011-5-16 ����12:59:17
 * @author wuxla
 */

public class ArriveMatViewECVO extends AbstractDataView {

  public static class ArriveMatViewECVOMeta extends DataViewMeta {
    public ArriveMatViewECVOMeta() {
      this.init();
    }

    private void init() {
      // �������š���Ʒ���������ɸ�������id�����κš���λid������������
      // �ϸ����������ϸ����������ۡ���˰�ϼơ�˰�ʡ�����id��������
      // ��Ŀ���������̡��ͻ�
      this.add(ArriveItemVO.class, new String[] {
        ArriveItemVO.PK_MATERIAL, ArriveItemVO.VFREE1, ArriveItemVO.VFREE2,
        ArriveItemVO.VFREE3, ArriveItemVO.VFREE4, ArriveItemVO.VFREE5,
        ArriveItemVO.VFREE6, ArriveItemVO.VFREE7, ArriveItemVO.VFREE8,
        ArriveItemVO.VFREE9, ArriveItemVO.VFREE10, ArriveItemVO.VBATCHCODE,
        ArriveItemVO.CASTUNITID, ArriveItemVO.NASTNUM, ArriveItemVO.NELIGNUM,
        ArriveItemVO.NNOTELIGNUM, ArriveItemVO.NTAXPRICE,
        ArriveItemVO.NORIGTAXMNY, ArriveItemVO.NTAXRATE,
        ArriveItemVO.CORIGCURRENCYID, ArriveItemVO.VCHANGERATE,
        ArriveItemVO.CPROJECTID, ArriveItemVO.CPRODUCTORID,
        ArriveItemVO.CASSCUSTID
      });
      this.add(ArriveHeaderVO.class, new String[] {
        ArriveHeaderVO.VBILLCODE, ArriveHeaderVO.PK_PURCHASEORG,
        ArriveHeaderVO.PK_PURCHASEORG_V
      });
      this.addRelation(ArriveItemVO.class, ArriveItemVO.PK_ARRIVEORDER,
          ArriveHeaderVO.class, ArriveHeaderVO.PK_ARRIVEORDER);
    }
  }

  private static final long serialVersionUID = -5135348723310579394L;

  /** �ͻ� getter ���� */
  public String getCasscustid() {
    return (String) this.getAttributeValue(ArriveItemVO.CASSCUSTID);
  }

  /** ��λ getter ���� */
  public String getCastunitid() {
    return (String) this.getAttributeValue(ArriveItemVO.CASTUNITID);
  }

  /** ԭ�ұ��� getter ���� */
  public String getCorigcurrencyid() {
    return (String) this.getAttributeValue(ArriveItemVO.CORIGCURRENCYID);
  }

  /** �������� getter ���� */
  public String getCproductorid() {
    return (String) this.getAttributeValue(ArriveItemVO.CPRODUCTORID);
  }

  /** ��Ŀ getter ���� */
  public String getCprojectid() {
    return (String) this.getAttributeValue(ArriveItemVO.CPROJECTID);
  }

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getDataViewMeta(
        ArriveMatViewECVOMeta.class);
  }

  /** �������� getter ���� */
  public UFDouble getNastnum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NASTNUM);
  }

  /** �ϸ������� getter ���� */
  public UFDouble getNelignum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NELIGNUM);
  }

  /** ���ϸ������� getter ���� */
  public UFDouble getNnotelignum() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NNOTELIGNUM);
  }

  /** ԭ�Һ�˰��� getter ���� */
  public UFDouble getNorigtaxmny() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NORIGTAXMNY);
  }

  /** �����Һ�˰���� getter ���� */
  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NTAXPRICE);
  }

  /** ˰�� getter ���� */
  public UFDouble getNtaxrate() {
    return (UFDouble) this.getAttributeValue(ArriveItemVO.NTAXRATE);
  }

  /** ����VID getter ���� */
  public String getPk_material() {
    return (String) this.getAttributeValue(ArriveItemVO.PK_MATERIAL);
  }

  /** �ɹ���֯ getter ���� */
  public String getPk_purchaseorg() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_PURCHASEORG);
  }

  /** �ɹ���֯�汾 getter ���� */
  public String getPk_purchaseorg_v() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_PURCHASEORG_V);
  }

  /** ���κű��� getter ���� */
  public String getVbatchcode() {
    return (String) this.getAttributeValue(ArriveItemVO.VBATCHCODE);
  }

  /** �������� getter ���� */
  public String getVbillcode() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VBILLCODE);
  }

  /** ������ getter ���� */
  public String getVchangerate() {
    return (String) this.getAttributeValue(ArriveItemVO.VCHANGERATE);
  }

  /** ���ɸ�������1 getter ���� */
  public String getVfree1() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE1);
  }

  /** ���ɸ�������10 getter ���� */
  public String getVfree10() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE10);
  }

  /** ���ɸ�������2 getter ���� */
  public String getVfree2() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE2);
  }

  /** ���ɸ�������3 getter ���� */
  public String getVfree3() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE3);
  }

  /** ���ɸ�������4 getter ���� */
  public String getVfree4() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE4);
  }

  /** ���ɸ�������5 getter ���� */
  public String getVfree5() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE5);
  }

  /** ���ɸ�������6 getter ���� */
  public String getVfree6() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE6);
  }

  /** ���ɸ�������7 getter ���� */
  public String getVfree7() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE7);
  }

  /** ���ɸ�������8 getter ���� */
  public String getVfree8() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE8);
  }

  /** ���ɸ�������9 getter ���� */
  public String getVfree9() {
    return (String) this.getAttributeValue(ArriveItemVO.VFREE9);
  }
}
