package nc.pubitf.pu.m27.ec;

import nc.vo.pu.m27.entity.SettleBillHeaderVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;

/**
 * @since 6.0
 * @version 2011-5-16 ����12:16:58
 * @author wuxla
 */

public class SettleBillViewECVO extends AbstractDataView {
  public static class SettleBillViewECVOMeta extends DataViewMeta {
    public SettleBillViewECVOMeta() {
      this.init();
    }

    private void init() {
      // ���㵥�š��������ڡ���ⵥ�š���Ʊ�š���λid���������������ۡ�
      // ���
      this.add(SettleBillItemVO.class, new String[] {
        SettleBillItemVO.PK_STOCKORDER_B, SettleBillItemVO.VSTOCKCODE,
        SettleBillItemVO.VINVOICECODE, SettleBillItemVO.CUNITID,
        SettleBillItemVO.NSETTLENUM, SettleBillItemVO.NPRICE,
        SettleBillItemVO.NMONEY
      });
      this.add(SettleBillHeaderVO.class, new String[] {
        SettleBillHeaderVO.VBILLCODE, SettleBillHeaderVO.DBILLDATE
      });
      this.addRelation(SettleBillItemVO.class, SettleBillItemVO.PK_SETTLEBILL,
          SettleBillHeaderVO.class, SettleBillHeaderVO.PK_SETTLEBILL);
    }
  }

  private static final long serialVersionUID = -7575084489897849592L;

  /** ����λ getter ���� */
  public String getCunitid() {
    return (String) this.getAttributeValue(SettleBillItemVO.CUNITID);
  }

  /** �������� **/
  public UFDate getDbilldate() {
    return (UFDate) this.getAttributeValue(SettleBillHeaderVO.DBILLDATE);
  }

  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getDataViewMeta(
        SettleBillViewECVOMeta.class);
  }

  /** ��� getter ���� */
  public UFDouble getNmoney() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NMONEY);
  }

  /** ���� getter ���� */
  public UFDouble getNprice() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NPRICE);
  }

  /** �������� getter ���� */
  public UFDouble getNsettlenum() {
    return (UFDouble) this.getAttributeValue(SettleBillItemVO.NSETTLENUM);
  }

  /** ��������pk getter ���� */
  public String getPk_stockorder_b() {
    return (String) this.getAttributeValue(SettleBillItemVO.PK_STOCKORDER_B);
  }

  /** ���㵥�� **/
  public String getVbillcode() {
    return (String) this.getAttributeValue(SettleBillHeaderVO.VBILLCODE);
  }

  /** ��Ʊ�� getter ���� */
  public String getVinvoicecode() {
    return (String) this.getAttributeValue(SettleBillItemVO.VINVOICECODE);
  }

  /** ��浥�ݺ� getter ���� */
  public String getVstockcode() {
    return (String) this.getAttributeValue(SettleBillItemVO.VSTOCKCODE);
  }
}
