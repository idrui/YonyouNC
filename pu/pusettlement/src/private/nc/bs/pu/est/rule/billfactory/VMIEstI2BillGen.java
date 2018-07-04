/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-19 上午09:38:44
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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>4202->I2的生成器
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-19 上午09:38:44
 */
public class VMIEstI2BillGen extends AbstractEstBillGenerator<VmiFIVO> {

  public VMIEstI2BillGen(VmiFIVO[] srcVos) {
    super(POBillType.VoiConsumeFinance.getCode(), IABillType.CGRK.getCode(),
        srcVos);
  }

  @Override
  public I2BillVO[] genBill() {
    I2BillVO[] i2s = (I2BillVO[]) super.genBill();
    this.procAfterGen(i2s);// 对存货核算的单据进行再加工
    return i2s;
  }

  /**
   * 创建视图vo，主键为拉平的表体主键
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
    // 调整I2单据的单据精度为成本单价精度
    EstTOIAUtil.adjustI2Price(i2s);
    // 根据PO54补充入库类型
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
      // TODO wuxla tianft V61 走VO对照，不用设置主数量金额单价
      // item.setNnum(header.getNestnum());
      // item.setNmny(header.getNestmny());
      // item.setNprice(header.getNestprice());
      // 补充数量
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
    // 对分收集结的数据进行处理
    EstTOIAUtil.clearCenterSettleInfo(this.getSrcVos());
  }
}
