/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-16 ����03:57:56
 */
package nc.bs.pu.est.rule.billfactory;

import java.util.Map;

import nc.vo.ia.mi2.entity.I2BillVO;
import nc.vo.ia.mi2.entity.I2HeadVO;
import nc.vo.ia.mi2.entity.I2ItemVO;
import nc.vo.pu.est.util.EstTOIAUtil;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4201.entity.PurchaseinFIVO;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.scmpub.res.billtype.IABillType;

public class PurchsInEstI2BillGen extends
    AbstractEstBillGenerator<PurchaseinFIVO> {

  public PurchsInEstI2BillGen(String srcBilltype, PurchaseinFIVO[] srcVos) {
    super(srcBilltype, IABillType.CGRK.getCode(), srcVos);
  }

  @Override
  public I2BillVO[] genBill() {
    I2BillVO[] i2s = (I2BillVO[]) super.genBill();
    this.procAfterGen(i2s);// �Դ������ĵ��ݽ����ټӹ�
    return i2s;
  }

  private void procAfterGen(I2BillVO[] i2s) {
    // ����I2���ݵĵ��ݾ���Ϊ�ɱ����۾���
    EstTOIAUtil.adjustI2Price(i2s);
  }

  @Override
  protected void fillHeadInfo(CircularlyAccessibleValueObject head) {
    I2HeadVO i2head = (I2HeadVO) head;
    i2head.setBestimateflag(UFBoolean.TRUE);
  }

  @Override
  protected void fillItemInfo(CircularlyAccessibleValueObject[] items) {
    I2ItemVO[] i2items = (I2ItemVO[]) items;
    Map<String, PurchaseinFIItemVO> stockItemMap = this.getItemMap();
    for (I2ItemVO item : i2items) {
      String bid = item.getCsrcbid();
      if (!stockItemMap.containsKey(bid)) {
        ExceptionUtils
            .wrappBusinessException("Served error,possiblly 4201-->I2 vo change lost important info!");
      }
      PurchaseinFIItemVO stockItem = stockItemMap.get(bid);
      item.setNnum(stockItem.getNestnum());

      // wuxla V61 ���ɱ��üǳɱ����͵��ۣ��ڵ����ֶ��ϼ�
      // item.setNmny(stockItem.getNestmny());
      // item.setNprice(stockItem.getNestprice());
      item.setNmny(stockItem.getNestcalcostmny());
      item.setNprice(stockItem.getNestcalcostprice());

      if (stockItem.getNestnum().equals(stockItem.getNinnum())) {
        item.setNastnum(stockItem.getNinassistnum());
      }
      else {
        UFDouble newastnum =
            ScaleUtils.getScaleUtilAtBS().adjustNumScale(
                HslParseUtil.hslDivUFDouble(stockItem.getVchangerate(),
                    stockItem.getNestnum()), stockItem.getCastunitid());
        item.setNastnum(newastnum);
      }
    }
  }

  @Override
  protected void processSrcVO() {
    super.processSrcVO();
    // �Է��ռ�������ݽ��д���
    EstTOIAUtil.clearCenterSettleInfo(this.getSrcVos());
  }
}
