/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-12 ����01:58:12
 */
package nc.vo.pu.est.entity.m45;

import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pu.m4201.entity.PurchaseinFIVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
import nc.vo.scmpub.res.billtype.ICBillType;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ���ⵥ�ݹ�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-12 ����01:58:12
 */
public class PurchaseInEstHeaderVO extends GoodsEstVO {

  /** �Ƿ������� **/
  public static final String BITINBILL = "bitinbill";

  /** ����ѡ�� **/
  public static final String ONEBILLSELECT = "onebillselect";

  /**
   * 
   */
  private static final long serialVersionUID = 1176572503272304224L;

  /** �Զ��������־ **/
  public UFBoolean getBautotofi() {
    return (UFBoolean) this.getAttributeValue(PurchaseinFIHeaderVO.BAUTOTOFI);
  }

  @Override
  public String getBillType() {
    return ICBillType.PurchaseIn.getCode();
  }

  /** �Ƿ������� **/
  public UFBoolean getBitinbill() {
    return (UFBoolean) this.getAttributeValue(PurchaseinFIHeaderVO.BITINBILL);
  }

  /**
   * ���෽����д
   * 
   * @see nc.vo.pubapp.pattern.model.entity.view.AbstractDataView#getMetaData()
   */
  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getBillViewMeta(
        PurchaseinFIVO.class);
  }

  /** �Զ��������־ **/
  public void setBautotofi(UFBoolean bautotofi) {
    this.setAttributeValue(PurchaseinFIHeaderVO.BAUTOTOFI, bautotofi);
  }

  /** �Ƿ������� **/
  public void setBitinbill(UFBoolean bitinbill) {
    this.setAttributeValue(PurchaseinFIHeaderVO.BITINBILL, bitinbill);
  }

}
