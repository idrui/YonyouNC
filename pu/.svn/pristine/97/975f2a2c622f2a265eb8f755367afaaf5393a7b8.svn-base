package nc.ui.pu.m23.view.quickarr;

import nc.bs.framework.common.NCLocator;
import nc.itf.org.IOrgConst;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.itf.scmpub.reference.uap.setting.defaultdata.DefaultDataSettingAccessor;
import nc.pubitf.pu.m21.pu.m23.IOrderQueryFor23;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.StringUtils;

public class QuickArrEditor {

  public ArriveVO[] getResultVOArray(String orderCode) {

    ArriveVO[] arrVOArray = null;
    try {
      StringBuilder condition = new StringBuilder();
      // 收货库存组织、单据号为条件
      String defaultOrg = this.getDefaultStockOrg();
      condition.append(" and po_order.vbillcode = '" + orderCode + "'");
      condition.append(" and ");
      condition.append(" po_order_b.pk_arrvstoorg = '" + defaultOrg + "'");

      IOrderQueryFor23 queryService =
          NCLocator.getInstance().lookup(IOrderQueryFor23.class);
      OrderVO[] orderVOArray =
          queryService.queryFor23QuickArrive(condition.toString());
      if (orderVOArray == null) {
        return null;
      }
      // 交换前处理
      this.changeBefore(orderVOArray);

      arrVOArray =
          PfServiceScmUtil.exeVOChangeByBizFlow(POBillType.Order.getCode(),
              POBillType.Arrive.getCode(), orderVOArray);

      // 交换后处理
      this.changeAfter(arrVOArray);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }
    return arrVOArray;
  }

  private void changeAfter(ArriveVO[] arrVOArray) {
    if (arrVOArray == null) {
      return;
    }
    // TODO hanbin 交换后处理
  }

  private void changeBefore(OrderVO[] orderVOArray) {
    if (orderVOArray == null) {
      return;
    }
    // TODO hanbin 交换前处理
  }

  private String getDefaultStockOrg() {
    String defaultOrg = null;
    try {
      defaultOrg = DefaultDataSettingAccessor.getDefaultOrgUnit();
    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
    // 需要判断defaultOrg是否为库存组织,如果不是库存组织，也不能进行快速收货
    if (StringUtils.isEmpty(defaultOrg)
        || !OrgUnitPubService.isTypeOf(defaultOrg, IOrgConst.STOCKORGTYPE)) {
      ExceptionUtils
          .wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
              .getStrByID("4004040_0", "04004040-0065")/*@res "默认主组织为空，不能进行快速收货!"*/);
    }

    return defaultOrg;
  }
}
