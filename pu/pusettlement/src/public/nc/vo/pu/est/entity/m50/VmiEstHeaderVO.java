/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-6 ����11:03:19
 */
package nc.vo.pu.est.entity.m50;

import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.m4202.entity.VmiFIHeaderVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
import nc.vo.scmpub.res.billtype.ICBillType;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���Ļ����ݹ���ͷ(�����ݹ�)VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-6 ����11:03:19
 */
public class VmiEstHeaderVO extends GoodsEstVO {

  /**
   * 
   */
  private static final long serialVersionUID = 5958714811810362082L;

  @Override
  public String getBillType() {
    return ICBillType.VmiSum.getCode();
  }

  /** ԭ�ұ��� -- ���Ļ���ȡ���ң���֧����� **/
  @Override
  public String getCorigcurrencyid() {
    return super.getCcurrencyid();
  }

  @Override
  public Integer getFtaxtypeflag() {
    return super.getFtaxtypeflag();
  }

  @Override
  public IDataViewMeta getMetaData() {
    return new DataViewMeta(VmiFIHeaderVO.class);
  }

  @Override
  public UFDouble getNchangestdrate() {
    // ���Ļ�������ң��۱����ʷ���1
    return UFDouble.ONE_DBL;
  }

  @Override
  public UFDouble getNorignetprice() {
    // ���Ļ����ݲ�֧����ң�����ȡ����
    return super.getNnetprice();
  }

  @Override
  public UFDouble getNorigprice() {
    // �뾻��һ��
    return this.getNorignetprice();
  }

  @Override
  public UFDouble getNorigtaxnetprice() {
    // ���Ļ����ݲ�֧����ң�����ȡ����
    return super.getNtaxnetprice();
  }

  @Override
  public UFDouble getNorigtaxprice() {
    // �뺬˰����һ��
    return this.getNorigtaxnetprice();
  }

  @Override
  public UFDouble getNprice() {
    // �뱾�Ҿ���һ��
    return super.getNnetprice();
  }

  @Override
  public UFDouble getNtaxprice() {
    // �뱾�Һ�����һ��
    return super.getNtaxnetprice();
  }

  /** Ӧ��������֯ -- ���Ļ���ȡ���������֯ **/
  @Override
  public String getPk_apfinanceorg() {
    return super.getPk_financeorg();
  }

  @Override
  public String getPk_order() {
    return null;
  }

  @Override
  public String getPk_order_b() {
    return null;
  }

  @Override
  public String getPk_purchaseOrg() {
    return null;
  }

  /**
   * ��ȡ�����֯
   * wuxla V61
   * <p>
   * ʹ�ó�����
   * <ul>
   * <li>
   * </ul>
   * 
   * @return
   */
  public String getPk_storeorg() {
    return (String) this.getAttributeValue(VmiFIHeaderVO.PK_STOREORG);
  }

  /** Ӧ��������֯ -- ���Ļ��ܺ��� **/
  @Override
  public void setPk_apfinanceorg(String pk_apfinanceorg) {
    //
  }

}
