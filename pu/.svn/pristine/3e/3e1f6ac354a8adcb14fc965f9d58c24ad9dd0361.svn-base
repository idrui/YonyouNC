package nc.impl.pu.m21.action;

import java.util.HashSet;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.ct.purdaily.ctpriceqry.CtQueryPriceParamVO;
import nc.pubitf.ct.purdaily.ctpriceqry.CtQueryPriceResultVO;
import nc.pubitf.ct.purdaily.puorder.IQryPriceForOrder;
import nc.pubitf.pp.m28.IPriceAuditPubQuery;
import nc.pubitf.pp.supplierprice.ISupplierPriceQuery;
import nc.vo.pp.supplierprice.entity.SupplierPrice;
import nc.vo.pp.supplierprice.entity.SupplierPriceQueryVO;
import nc.vo.pp.supplierprice.enumeration.QTSoruceType;
import nc.vo.pu.m21.entity.m20.QueryParaFor20;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 处理订单交易类型相关
 * 
 * @since 6.0
 * @version 2011-4-19 下午03:35:09
 * @author 田锋涛
 */

public class OrderTranTypeAction {

  /**
   * 返回符合以下条件的请购单行记录:
   * <ul>
   * <li>请购单行生成有价格审批单
   * <li>生成的价格审批单已经审批通过
   * </ul>
   * 
   * @param pk_praybill_b 请购单行
   * @return 请购单行记录
   */
  public Set<String> filterPrayItemsByM28(String[] pk_praybill_b) {
    try {
      return NCLocator.getInstance().lookup(IPriceAuditPubQuery.class)
          .queryAuditedBillByPrayid(pk_praybill_b);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return new HashSet<String>();
  }

  /**
   * 返回有供应商有效价格的请购单行记录
   * 
   * @param paras 询价参数
   * @return 请购单行记录
   */
  public Set<String> filterPrayItemsByVendorEffectPrc(QueryParaFor20[] paras) {
    Set<String> pk_praybill_b = new HashSet<String>();
    if (SysInitGroupQuery.isPPEnabled()) {
      // 1.供应商价目表
      pk_praybill_b.addAll(this.filterByPpPrice(paras));
    }

    if (SysInitGroupQuery.isCTEnabled()) {
      // 2.合同价格
      pk_praybill_b.addAll(this.filterByCtPrice(paras));
    }
    return pk_praybill_b;
  }

  private Set<String> filterByCtPrice(QueryParaFor20[] paras) {
    Set<String> pk_praybill_b = new HashSet<String>();
    try {
      CtQueryPriceParamVO[] ctParas = this.transParaForCt(paras);
      // 与询价参数一一对应
      CtQueryPriceResultVO[] ctPriceVos =
          NCLocator.getInstance().lookup(IQryPriceForOrder.class)
              .qryPrice(ctParas);
      for (int i = 0; i < paras.length; i++) {
        // 没有询到价格
        if (ctPriceVos[i] == null) {
          continue;
        }
        // 寻到了价格
        if (ctPriceVos[i].getNqtorigprice() != null
            || ctPriceVos[i].getNorigprice() != null
            || ctPriceVos[i].getNorigtaxprice() != null) {
          pk_praybill_b.add(paras[i].getPk_praybill_b());
        }
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return pk_praybill_b;
  }

  /**
   * @param paras
   * @return
   */
  private Set<String> filterByPpPrice(QueryParaFor20[] paras) {
    Set<String> pk_praybill_b = new HashSet<String>();
    try {
      SupplierPriceQueryVO[] pParas = this.transParaForPP(paras);
      // 与询价参数一一对应
      SupplierPrice[] supplierPrice =
          NCLocator.getInstance().lookup(ISupplierPriceQuery.class)
              .query(pParas, QTSoruceType.PRAY);
      for (int i = 0; i < paras.length; i++) {

        // 没有询到价格
        if (supplierPrice[i] == null) {
          continue;
        }
        // 寻到了价格

        pk_praybill_b.add(paras[i].getPk_praybill_b());

      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return pk_praybill_b;
  }

  /**
   * 合同询价参数转换
   * 
   * @param paras
   * @return
   */
  private CtQueryPriceParamVO[] transParaForCt(QueryParaFor20[] paras) {
    CtQueryPriceParamVO[] newParas = new CtQueryPriceParamVO[paras.length];
    for (int i = 0; i < paras.length; i++) {
      CtQueryPriceParamVO vo = new CtQueryPriceParamVO();
      vo.setPk_org(paras[i].getPk_org());// 采购组织
      vo.setPk_srcmaterial(paras[i].getPk_srcmaterial());// 物料
      vo.setBsc(paras[i].getBsc());// 委外
      vo.setCorigcurrencyid(paras[i].getCurrency());// 币种
      vo.setActualvalidate(paras[i].getDate());// 日期
      newParas[i] = vo;
    }
    return newParas;
  }

  /**
   * 供应商价目表参数转换
   * 
   * @param paras
   * @return
   */
  private SupplierPriceQueryVO[] transParaForPP(QueryParaFor20[] paras) {
    SupplierPriceQueryVO[] newParas = new SupplierPriceQueryVO[paras.length];
    // 匹配6维度
    for (int i = 0; i < paras.length; i++) {
      SupplierPriceQueryVO vo = new SupplierPriceQueryVO();
      vo.setCurrency(paras[i].getCurrency());// 币种（原币）
      vo.setDate(paras[i].getDate());// 发票日期
      vo.setPk_org(paras[i].getPk_org());// 采购组织
      vo.setPk_srcmaterial(paras[i].getPk_srcmaterial());// 物料oid
      vo.setBsc(paras[i].getBsc());
      newParas[i] = vo;
    }
    return newParas;
  }

}
