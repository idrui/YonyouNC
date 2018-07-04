package nc.vo.pu.m23.rule;

import nc.vo.ic.batch.AbstractBillItemAdapter;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����ṩ���ε�������ӿ���Ҫ��������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-5-27 ����09:56:14
 */
public class BatchCodeItemAdapter extends AbstractBillItemAdapter {

  private static final long serialVersionUID = -4358502750674828419L;

  public BatchCodeItemAdapter() {
    super();
  }

  public BatchCodeItemAdapter(ArriveItemVO vo) {
    super(vo);
  }

  @Override
  public String getBid() {
    return this.getArriveItemVO().getPk_arriveorder_b();
  }

  @Override
  public String getBillType() {
    return POBillType.Arrive.getCode();
  }

  @Override
  public String getCmaterialvid() {
    return this.getArriveItemVO().getPk_material();
  }

  @Override
  public String getCrowNo() {
    return this.getArriveItemVO().getCrowno();
  }

  @Override
  public String getHid() {
    return this.getArriveItemVO().getPk_arriveorder();
  }

  @Override
  public String getTableName() {
    return "po_arriveorder_b";
  }

  @Override
  public String getVbillcode() {
    return (String) this.getArriveItemVO().getAttributeValue(
        ArriveHeaderVO.VBILLCODE);
  }

  @Override
  public void setBid(String arg0) {
    return;
  }

  @Override
  public void setBillType(String arg0) {
    return;
  }

  @Override
  public void setCmaterialvid(String arg0) {
    return;
  }

  @Override
  public void setCrowNo(String arg0) {
    return;
  }

  @Override
  public void setHid(String arg0) {
    return;
  }

  @Override
  public void setVbillcode(String arg0) {
    return;
  }

  private ArriveItemVO getArriveItemVO() {
    if (this.getVo() != null && this.getVo() instanceof ArriveItemVO) {
      return (ArriveItemVO) this.getVo();
    }
    return null;
  }
}
