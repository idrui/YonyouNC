package nc.pubitf.pu.m23.ec;

import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * @since 6.0
 * @version 2011-5-16 ����01:13:25
 * @author wuxla
 */

public class ArrivePublishedViewVO extends AbstractDataView {

  public static class ArrivePublishedViewVOMeta extends DataViewMeta {
    public ArrivePublishedViewVOMeta() {
      this.init();
    }

    private void init() {
      // ����������,�������š��������ڡ��ɹ���֯id���ɹ�����id���ջ���id��
      // �Ƿ��˻����˻����ɡ���ע
      this.add(ArriveHeaderVO.class, new String[] {
        ArriveHeaderVO.PK_ORG, ArriveHeaderVO.PK_ORG_V,
        ArriveHeaderVO.PK_ARRIVEORDER, ArriveHeaderVO.VBILLCODE,
        ArriveHeaderVO.DBILLDATE, ArriveHeaderVO.PK_PURCHASEORG,
        ArriveHeaderVO.PK_PURCHASEORG_V, ArriveHeaderVO.PK_DEPT,
        ArriveHeaderVO.PK_DEPT_V, ArriveHeaderVO.PK_RECEIVEPSNDOC,
        ArriveHeaderVO.BISBACK, ArriveHeaderVO.VBACKREASON,
        ArriveHeaderVO.VMEMO
      });

    }
  }

  private static final long serialVersionUID = -8671305191028684658L;

  /** �˻� getter ���� */
  public UFBoolean getBisback() {
    return (UFBoolean) this.getAttributeValue(ArriveHeaderVO.BISBACK);
  }

  /** �������� getter ���� */
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(ArriveHeaderVO.DBILLDATE);
  }

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getDataViewMeta(
        ArrivePublishedViewVOMeta.class);

  }

  /** ������ getter ���� */
  public String getPk_arriveorder() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_ARRIVEORDER);
  }

  /** �ɹ����� getter ���� */
  public String getPk_dept() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_DEPT);
  }

  /** �ɹ����Ű汾 getter ���� */
  public String getPk_dept_v() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_DEPT_V);
  }

  /** �ɹ���֯getter ���� */
  public String getPk_org() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_ORG);
  }

  /** �����֯�汾 getter ���� */
  public String getPk_org_v() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_ORG_V);
  }

  /** �ɹ���֯ getter ���� */
  public String getPk_purchaseorg() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_PURCHASEORG);
  }

  /** �ɹ���֯�汾 getter ���� */
  public String getPk_purchaseorg_v() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_PURCHASEORG_V);
  }

  /** �ջ��� getter ���� */
  public String getPk_receivepsndoc() {
    return (String) this.getAttributeValue(ArriveHeaderVO.PK_RECEIVEPSNDOC);
  }

  /** �˻����� getter ���� */
  public String getVbackreason() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VBACKREASON);
  }

  /** �������� getter ���� */
  public String getVbillcode() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VBILLCODE);
  }

  /** ��ע getter ���� */
  public String getVmemo() {
    return (String) this.getAttributeValue(ArriveHeaderVO.VMEMO);
  }

}
