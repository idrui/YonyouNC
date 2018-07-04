/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-17 上午10:03:56
 */
package nc.bs.pu.est.rule.billfactory;

import java.util.ArrayList;
import java.util.List;

import nc.bs.arap.util.IArapBillTypeCons;
import nc.vo.pu.m4202.entity.VmiFIFeeVO;
import nc.vo.pu.m4202.entity.VmiFIHeaderVO;
import nc.vo.pu.m4202.entity.VmiFIVO;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.lang.UFDouble;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>消耗汇总暂估应付单据生成器(费用暂估)
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-17 上午10:03:56
 */
public class VMIFeeEstPayGen extends AbstractEstBillGenerator<VmiFIVO> {

  public VMIFeeEstPayGen(VmiFIVO[] srcVos) {
    super(POBillType.VoiConsumeFinance.getCode(), IArapBillTypeCons.C1, srcVos);
  }

  private VmiFIVO convertItemToHead(VmiFIFeeVO fee, VmiFIVO fivo) {
    VmiFIVO cfivo = new VmiFIVO();
    cfivo.setParentVO((CircularlyAccessibleValueObject) fivo.getParentVO()
        .clone());
    this.fillSrcItemInfo(cfivo, fee);
    return cfivo;
  }

  protected void fillSrcItemInfo(VmiFIVO fivo, VmiFIFeeVO fee) {
    VmiFIHeaderVO head = fivo.getParentVO();
    head.setPk_material(fee.getPk_feematerial());// 物料
    head.setPk_srcmaterial(fee.getPk_srcfeematerial());
    head.setDtocostapdate(fee.getDestdate());// 暂估日期
    head.setPk_supplier(fee.getPk_supplier());// 供应商
    head.setPk_costappsn(fee.getPk_estpsn());// 暂估人
    // wuxla 应付单不变，仍使用暂估金额
    head.setNestmny(fee.getNestmny());// 暂估金额
    head.setNestnum(UFDouble.ZERO_DBL);// 暂估数量为0
    head.setPk_estcurrency(fee.getPk_estcurrency());// 暂估币种
    head.setNestexhgrate(fee.getNestexchgrate());// 汇率
    head.setNesttaxrate(fee.getNesttaxrate());// 税率
    head.setFesttaxtype(fee.getFtaxtypeflag());// 扣税类别 2012-06-19 tianft
    head.setNesttotalmny(fee.getNesttotalmny());// 价税合计
    head.setNesttaxmny(fee.getNesttaxmny());// 税额
    head.setNestomny(fee.getNestomny());// 原币金额
    head.setNestototalmny(fee.getNestototalmny());// 原币价税合计
    // head.setNestotaxmny(fee.getNestotaxmny());// 原币税额
    head.setNestprice(UFDouble.ZERO_DBL);// 单价
    head.setNesttaxprice(UFDouble.ZERO_DBL);// 含税单价
    head.setNestoprice(UFDouble.ZERO_DBL);// 原币单价
    head.setNestotaxprice(UFDouble.ZERO_DBL);// 原币含税单价
    head.setPk_stockps_b(fee.getPk_stockps_fee());// 记录费用暂估的pk
    // 2012-05-09 tianft 添加vat相关
    head.setCsendcountryid(fee.getCsendcountryid());// 发送国
    head.setCtaxcountryid(fee.getCtaxcountryid());// 报税国
    head.setCrececountryid(fee.getCrececountryid());// 收货国
    head.setFbuysellflag(fee.getFbuysellflag());// 三角贸易
    head.setBtriatradeflag(fee.getBtriatradeflag());// 购销类型
    head.setCesttaxcodeid(fee.getCtaxcodeid());// 税码
    head.setNestnosubtax(fee.getNnosubtax());// 不可抵扣税额
    head.setNestnosubtaxrate(fee.getNnosubtaxrate());// 不可抵扣税率
    head.setNestcalcostmny(fee.getNcalcostmny());// 计成本金额
    head.setNestcaltaxmny(fee.getNcaltaxmny());// 计税金额
    head.setBopptaxflag(fee.getBopptaxflag());// 逆向征税
  }

  @Override
  protected void processSrcVO() {
    super.processSrcVO();
    VmiFIVO[] fivos = this.getSrcVos();
    List<VmiFIVO> newFiVos = new ArrayList<VmiFIVO>();
    for (VmiFIVO fivo : fivos) {
      for (VmiFIFeeVO fee : fivo.getChildrenVO()) {
        VmiFIVO newFivo = this.convertItemToHead(fee, fivo);
        newFiVos.add(newFivo);
      }
    }
    this.setSrcVos(newFiVos.toArray(new VmiFIVO[newFiVos.size()]));
  }

}
