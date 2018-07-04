/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-19 ����09:38:44
 */
package nc.bs.pu.est.rule.billfactory;

import java.util.HashMap;
import java.util.Map;

import nc.vo.ia.mi2.entity.I2BillVO;
import nc.vo.ia.mi2.entity.I2ItemVO;
import nc.vo.pu.est.util.EstTOIAUtil;
import nc.vo.pu.m4202.entity.VmiFIHeaderVO;
import nc.vo.pu.m4202.entity.VmiFIVO;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.scmpub.res.billtype.IABillType;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>4202->I2��������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-19 ����09:38:44
 */
public class VMIEstI2BillGen extends AbstractEstBillGenerator<VmiFIVO> {

  public VMIEstI2BillGen(VmiFIVO[] srcVos) {
    super(POBillType.VoiConsumeFinance.getCode(), IABillType.CGRK.getCode(),
        srcVos);
  }

  @Override
  public I2BillVO[] genBill() {
    I2BillVO[] i2s = (I2BillVO[]) super.genBill();
    this.procAfterGen(i2s);// �Դ������ĵ��ݽ����ټӹ�
    return i2s;
  }

  /**
   * ������ͼvo������Ϊ��ƽ�ı�������
   * 
   * @return
   */
  private Map<String, VmiFIVO> creatViewMap() {
    Map<String, VmiFIVO> map = new HashMap<String, VmiFIVO>();
    VmiFIVO[] vos = this.getSrcVos();
    for (VmiFIVO vo : vos) {
      map.put(vo.getParentVO().getPk_stockps_b(), vo);
    }
    return map;
  }

  private void procAfterGen(I2BillVO[] i2s) {
    // ����I2���ݵĵ��ݾ���Ϊ�ɱ����۾���
    EstTOIAUtil.adjustI2Price(i2s);
    // ����PO54�����������
    EstTOIAUtil.setStockTranTypeForVMI(i2s, this.getSrcVos());

  }

  @Override
  protected void fillItemInfo(CircularlyAccessibleValueObject[] items) {
    I2ItemVO[] i2items = (I2ItemVO[]) items;
    Map<String, VmiFIVO> vmifiMap = this.creatViewMap();
    for (I2ItemVO item : i2items) {
      String bid = item.getCsrcbid();
      if (!vmifiMap.containsKey(bid)) {
        ExceptionUtils
            .wrappBusinessException("Served error,possiblly 4202-->I2 vo change lost important info!");
      }
      VmiFIVO vmiFiVO = vmifiMap.get(bid);
      VmiFIHeaderVO header = vmiFiVO.getParentVO();
      // TODO wuxla tianft V61 ��VO���գ�������������������
      // item.setNnum(header.getNestnum());
      // item.setNmny(header.getNestmny());
      // item.setNprice(header.getNestprice());
      // ��������
      if (header.getNestnum().equals(header.getNinnum())) {
        item.setNastnum(header.getNinassistnum());
      }
      else {
        UFDouble newastnum =
            ScaleUtils.getScaleUtilAtBS().adjustNumScale(
                HslParseUtil.hslDivUFDouble(header.getVchangerate(),
                    header.getNestnum()), header.getCastunitid());
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
