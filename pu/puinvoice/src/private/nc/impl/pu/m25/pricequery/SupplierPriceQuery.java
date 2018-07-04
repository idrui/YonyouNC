package nc.impl.pu.m25.pricequery;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m25.IPriceQuery;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.vo.pp.supplierprice.entity.SupplierPrice;
import nc.vo.pp.supplierprice.entity.SupplierPriceQueryVO;
import nc.vo.pp.supplierprice.enumeration.QTSoruceType;
import nc.vo.pu.m25.entity.InvoicePriceQueryVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 供应商价目表的询价
 * 
 * @since 6.0
 * @version 2011-7-29 下午02:31:49
 * @author 田锋涛
 */

public class SupplierPriceQuery implements IPriceQuery {

  public SupplierPriceQuery() {
    //
  }

  @Override
  public void query(InvoicePriceQueryVO[] para) {
    try {
      if (!SysInitGroupQuery.isPPEnabled() || ArrayUtils.isEmpty(para)) {
        return;
      }
      // 转换成供应商价目表的询价参数
      SupplierPriceQueryVO[] vos = this.getSuppPriceQueryVO(para);
      // 询供应商价目表,返回的是等长的数组，没寻到价格的视为null
      // 可能会出现supplierPrice[0]=null，supplierPrice[1]=null的情况
      SupplierPrice[] supplierPrice =
          NCLocator.getInstance()
              .lookup(nc.pubitf.pp.supplierprice.ISupplierPriceQuery.class)
              .query(vos, QTSoruceType.PI);
      if (ArrayUtils.isEmpty(supplierPrice)) {
        return;
      }
      for (int i = 0; i < supplierPrice.length; i++) {
        if (supplierPrice[i] == null) {
          continue;
        }
        // 取主单位的价格
        para[i].setNorigprice(supplierPrice[i].getNorigprice());
        para[i].setNorigtaxprice(supplierPrice[i].getNorigtaxprice());
        // 增加税率和扣税类别
        para[i].setNtaxrate(supplierPrice[i].getNtaxrate());
        para[i].setFtaxtypeflag(Integer.valueOf(supplierPrice[i]
            .getFtaxtypeflag()));
        para[i].setNNoSubTaxRate(supplierPrice[i].getNnosubtaxrate());
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 发票询价参数 向供应商价目表询价参数转换
   * 
   * @param para 发票询价参数
   * @return 供应商价目表询价参数
   */
  private SupplierPriceQueryVO[] getSuppPriceQueryVO(InvoicePriceQueryVO[] para) {
    SupplierPriceQueryVO[] vos = new SupplierPriceQueryVO[para.length];
    for (int i = 0; i < vos.length; i++) {
      // 询价参数：采购组织+供应商+物料+币种＋发票日期+单位
      vos[i] = new SupplierPriceQueryVO();
      vos[i].setCurrency(para[i].getCorigcurrencyid());// 币种（原币）
      vos[i].setDate(para[i].getDbilldate());// 发票日期
      vos[i].setCproductorid(para[i].getCproductorid());// 生产厂商
      vos[i].setPk_org(para[i].getPk_purchaseorg());// 采购组织
      vos[i].setPk_srcmaterial(para[i].getPk_srcmaterial());// 物料oid
      vos[i].setPk_supplier(para[i].getPk_supplier());// 供应商
      vos[i].setCastunitid(para[i].getCastunitid());// 单位
      vos[i].setCqualitylevelid(para[i].getCqualitylevelid());// 质量等级
    }
    return vos;
  }

}
