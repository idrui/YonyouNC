package nc.bs.pu.est.rule.pricequery;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.pp.supplierprice.ISupplierPriceQuery;
import nc.vo.pp.supplierprice.entity.SupplierPrice;
import nc.vo.pp.supplierprice.entity.SupplierPriceQueryVO;
import nc.vo.pp.supplierprice.enumeration.QTSoruceType;
import nc.vo.pu.est.rule.IEstPriceQueryInfoProvide;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 暂估时询供应商价目表策略
 * 
 * @since 6.0
 * @version 2010-11-25 下午02:35:08
 * @author zhaoyha
 */

public class EstSupplierPriceQryStrategy extends AbstractEstPriceQueryStrategy {

  private ISupplierPriceQuery getQueryService() {
    return NCLocator.getInstance().lookup(ISupplierPriceQuery.class);
  }

  private SupplierPriceQueryVO[] getSPQVO(IEstPriceQueryInfoProvide[] pqinfo) {
    SupplierPriceQueryVO[] spqVos = new SupplierPriceQueryVO[pqinfo.length];
    for (int i = 0; i < spqVos.length; i++) {
      SupplierPriceQueryVO spqvo = new SupplierPriceQueryVO();
      IEstPriceQueryInfoProvide pqi = pqinfo[i];
      spqvo.setPk_org(pqi.getPk_purchaseOrg());
      spqvo.setPk_srcmaterial(pqi.getPk_srcmaterial());
      spqvo.setCurrency(pqi.getorigcurr());
      spqvo.setDate(AppContext.getInstance().getBusiDate());
      spqvo.setPk_supplier(pqi.getBillSupplier());
      spqVos[i] = spqvo;
    }
    return spqVos;
  }

  @Override
  protected void procResltData(EstPriceQryResltData[] resltData,
      IEstPriceQueryInfoProvide[] pqinfo) {

    if (!SysInitGroupQuery.isPPEnabled()) {
      return;
    }

    SupplierPriceQueryVO[] spqVos = this.getSPQVO(pqinfo);
    try {
      SupplierPrice[] sps =
          this.getQueryService().query(spqVos, QTSoruceType.EST);
      for (int i = 0; i < resltData.length; i++) {
        if (null == sps[i]) {
          continue;
        }
        resltData[i].setOrigTaxPrice(sps[i].getNorigtaxprice());
        resltData[i].setOrigPrice(sps[i].getNorigprice());
        // 补充税率和扣税类别
        resltData[i].setTaxRate(sps[i].getNtaxrate());
        resltData[i].setTaxTypeFlag(Integer.valueOf(sps[i].getFtaxtypeflag()));
        // V61 wuxla 不可抵扣税率
        resltData[i].setNosubTaxRate(sps[i].getNnosubtaxrate());
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
