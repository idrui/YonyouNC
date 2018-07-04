package nc.bs.pu.est.rule.billfactory;

import nc.bs.arap.util.IArapBillTypeCons;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.m4201.entity.PurchaseinFIFeeVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4201.entity.PurchaseinFIVO;
import nc.vo.pub.lang.UFDouble;

public class PurchsInFeeEstPayGen extends PurchsInFeeEstGen {

  public PurchsInFeeEstPayGen(PurchaseinFIVO[] srcVos, PurchaseinFIFeeVO[] fees) {
    super(IArapBillTypeCons.C1, srcVos, fees);
  }

  @Override
  protected void fillSrcItemInfo(PurchaseinFIItemVO fiitem, FeeEstVO fee) {
    super.fillSrcItemInfo(fiitem, fee);
    
    fiitem.setPk_supplier(fee.getPk_supplier());// 供应商
    
    fiitem.setPk_material(fee.getPk_feematerial());// 物料
    fiitem.setPk_srcmaterial(fee.getPk_srcfeematerial());
    fiitem.setPk_estcurrency(fee.getPk_estcurrency());// 暂估币种
    fiitem.setNestexhgrate(fee.getNestexchgrate());// 汇率
    fiitem.setNesttaxrate(fee.getNesttaxrate());// 税率
    fiitem.setFesttaxtype(fee.getFtaxtypeflag());// 扣税类别 2012-06-19 tianft
    fiitem.setFtaxtypeflag(fee.getFtaxtypeflag());// 扣税类别 2012-06-19 tianft
    fiitem.setNesttotalmny(fee.getNesttotalmny());// 价税合计
    fiitem.setNesttaxmny(fee.getNesttaxmny());// 税额
    fiitem.setNestomny(fee.getNestomny());// 原币金额
    fiitem.setNestototalmny(fee.getNestototalmny());// 原币价税合计
    fiitem.setNestprice(UFDouble.ZERO_DBL);// 单价
    fiitem.setNesttaxprice(UFDouble.ZERO_DBL);// 含税单价
    fiitem.setNestoprice(UFDouble.ZERO_DBL);// 原币单价
    fiitem.setNestotaxprice(UFDouble.ZERO_DBL);// 原币含税单价
    fiitem.setPk_stockps_b(fee.getPk_stockps_fee());// 记录费用暂估的pk

    fiitem.setCesttaxcodeid(fee.getCtaxcodeid());// 税码
    fiitem.setNestnosubtaxrate(fee.getNnosubtaxrate());// 不可抵扣税率
    fiitem.setNestnosubtax(fee.getNnosubtax());// 不可抵扣税额
    fiitem.setNestcalcostmny(fee.getNcalcostmny());// 记成本金额
    fiitem.setNestcalcostprice(UFDouble.ZERO_DBL);
    fiitem.setNestcaltaxmny(fee.getNcaltaxmny());// 计税金额
    fiitem.setBopptaxflag(fee.getBopptaxflag());// 逆向征税
    // 国家、购销类型等已对表头处理
  }
}
