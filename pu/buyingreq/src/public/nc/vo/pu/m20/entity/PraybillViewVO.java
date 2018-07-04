/**
 * $�ļ�˵��$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-4 ����10:24:37
 */
package nc.vo.pu.m20.entity;

import java.util.ArrayList;
import java.util.List;

import nc.vo.it.m5805.entity.DetailBVO;
import nc.vo.it.m5805.entity.DetailVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMetaFactory;
import nc.vo.pubapp.pattern.model.meta.entity.view.IDataViewMeta;
import nc.vo.pubapp.pattern.model.tool.BillComposite;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-2-4 ����10:24:37
 */
public class PraybillViewVO extends AbstractDataView {

  /**
   * 
   */
  private static final long serialVersionUID = -3352088347886056536L;

  /** ����ͼVO�õ��빺��VO **/
  public static PraybillVO[] getPraybillVO(AbstractDataView[] views) {
    if (ArrayUtils.isEmpty(views)) {
      return null;
    }
    List<PraybillHeaderVO> headers = new ArrayList<PraybillHeaderVO>();
    List<PraybillItemVO> items = new ArrayList<PraybillItemVO>();
    for (AbstractDataView view : views) {
      headers.add((PraybillHeaderVO) view.getVO(PraybillHeaderVO.class));
      items.add((PraybillItemVO) view.getVO(PraybillItemVO.class));
    }

    BillComposite<PraybillVO> bc =
        new BillComposite<PraybillVO>(PraybillVO.class);
    PraybillVO tempVO = new PraybillVO();
    bc.append(tempVO.getMetaData().getParent(),
        headers.toArray(new PraybillHeaderVO[headers.size()]));
    bc.append(tempVO.getMetaData().getVOMeta(PraybillItemVO.class),
        items.toArray(new PraybillItemVO[items.size()]));
    return bc.composite();

    // return (PraybillVO[]) AggVOUtil.createBillVO(headers
    // .toArray(new PraybillHeaderVO[headers.size()]), items
    // .toArray(new PraybillItemVO[items.size()]), PraybillVO.class);
  }

  /** �йر� **/
  public UFBoolean getBrowclose() {
    return (UFBoolean) this.getAttributeValue(PraybillItemVO.BROWCLOSE);
  }

  /** ���ұ��� **/
  public String getCcurrencyid() {
    return (String) this.getAttributeValue(PraybillHeaderVO.CCURRENCYID);
  }

  public PraybillHeaderVO getHead() {
    return (PraybillHeaderVO) this.getVO(PraybillHeaderVO.class);
  }

  public PraybillItemVO getItem() {
    return (PraybillItemVO) this.getVO(PraybillItemVO.class);
  }

  /**
   * ���෽����д
   * 
   * @see nc.vo.pubapp.pattern.model.entity.view.AbstractDataView#getMetaData()
   */
  @Override
  public IDataViewMeta getMetaData() {
    return DataViewMetaFactory.getInstance().getBillViewMeta(PraybillVO.class);
  }

  /** �ۼƶ������� **/
  public UFDouble getNaccumulatenum() {
    return (UFDouble) this.getAttributeValue(PraybillItemVO.NACCUMULATENUM);
  }

  /** ���� **/
  public UFDouble getNassistnum() {
    return (UFDouble) this.getAttributeValue(PraybillItemVO.NASTNUM);
  }

  /** ���ɺ�ͬ���� **/
  public Integer getNgenct() {
    return (Integer) this.getAttributeValue(PraybillItemVO.NGENCT);
  }

  /** ������ **/
  public UFDouble getNnum() {
    return (UFDouble) this.getAttributeValue(PraybillItemVO.NNUM);
  }

  /** ���ɼ۸����������� **/
  public Integer getNpriceauditbill() {
    return (Integer) this.getAttributeValue(PraybillItemVO.NPRICEAUDITBILL);
  }

  /** ����ѯ���۵����� **/
  public Integer getNquotebill() {
    return (Integer) this.getAttributeValue(PraybillItemVO.NQUOTEBILL);
  }

  /** ���Ҽ�˰�ϼ� **/
  public UFDouble getNtaxmny() {
    return (UFDouble) this.getAttributeValue(PraybillItemVO.NTAXMNY);
  }

  /** �����Һ�˰���� **/
  public UFDouble getNtaxprice() {
    return (UFDouble) this.getAttributeValue(PraybillItemVO.NTAXPRICE);
  }

  /** �빺����ID **/
  public String getPk_praybill_b() {
    return (String) this.getAttributeValue(PraybillItemVO.PK_PRAYBILL_B);
  }

  /** �ɹ���֯ **/
  public String getPk_purchaseorg() {
    return (String) this.getAttributeValue(PraybillItemVO.PK_PURCHASEORG);
  }

  public void setHead(PraybillHeaderVO head) {
    this.setVO(head);
  }

  public void setItem(PraybillItemVO item) {
    this.setVO(item);
  }

  /** �ۼƶ������� **/
  public void setNaccumulatenum(UFDouble naccumulatenum) {
    this.setAttributeValue(PraybillItemVO.NACCUMULATENUM, naccumulatenum);
  }

  /** ���� **/
  public void setNassistnum(UFDouble nassistnum) {
    this.setAttributeValue(PraybillItemVO.NASTNUM, nassistnum);
  }

  /** ���ɺ�ͬ���� **/
  public void setNgenct(Integer ngenct) {
    this.setAttributeValue(PraybillItemVO.NGENCT, ngenct);
  }

  /** ������ **/
  public void setNnum(UFDouble nnum) {
    this.setAttributeValue(PraybillItemVO.NNUM, nnum);
  }

  /** ���ɼ۸����������� **/
  public void setNpriceauditbill(Integer npriceauditbill) {
    this.setAttributeValue(PraybillItemVO.NPRICEAUDITBILL, npriceauditbill);
  }

  /** ����ѯ���۵����� **/
  public void setNquotebill(Integer nquotebill) {
    this.setAttributeValue(PraybillItemVO.NQUOTEBILL, nquotebill);
  }

  /** ���Ҽ�˰�ϼ� **/
  public void setNtaxmny(UFDouble ntaxmny) {
    this.setAttributeValue(PraybillItemVO.NTAXMNY, ntaxmny);
  }

  /** �����Һ�˰���� **/
  public void setNtaxprice(UFDouble ntaxprice) {
    this.setAttributeValue(PraybillItemVO.NTAXPRICE, ntaxprice);
  }
  /**
   * Add By Wangzym 20170227
   * @return
   */
  public PraybillVO changeTOBill()
     {
	  PraybillVO billVO = new PraybillVO();
       billVO.setParent(getHead());
       PraybillItemVO[] items = { getItem() };
       
   
       billVO.setBVO(items);
       return billVO;
     }

}