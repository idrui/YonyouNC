package nc.ui.pu.m21.billref.ic.m45;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m21.ic.m45.IOrderQueryFor45;
import nc.ui.pu.m21.view.OrderQueryDLGInitializer;
import nc.ui.pu.pub.billref.PuRefQueryService;
import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.RefInfo;
import nc.ui.pubapp.uif2app.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单到入库单的转单信息注册类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-5-25 下午06:12:48
 */
public class OrderRefInfoFor45 extends RefInfo {
  @Override
  public IQueryService getQueryService() {
    if (super.getQueryService() == null) {
      this.setQueryService();
    }
    return super.getQueryService();
  }

  private void setQueryService() {
    BillSourceVar var = this.getBillSrcVar();
    // 如果UserObject不为空，则为退货
    IQueryService service = null;
    if (var.getUserObj() != null) {
      service = new PuRefQueryService() {
        @Override
        public Object[] queryByQueryScheme(IQueryScheme queryScheme)
            throws Exception {
          this.checkQueryCond(queryScheme);
          IOrderQueryFor45 queryService =
              NCLocator.getInstance().lookup(IOrderQueryFor45.class);
          return queryService.queryFor45Return(queryScheme);
        }

        @Override
        protected String getRefOrgFieldCode() {
          return OrderQueryDLGInitializer.PK_ORDER_B_PK_ARRVSTOORG;
        }
      };
    }
    // 如果UserObject为空，则为正向单据
    else {
      service = new PuRefQueryService() {

        @Override
        public Object[] queryByQueryScheme(IQueryScheme queryScheme)
            throws Exception {
          this.checkQueryCond(queryScheme);
          IOrderQueryFor45 queryService =
              NCLocator.getInstance().lookup(IOrderQueryFor45.class);
          return queryService.queryFor45Pull(queryScheme);
        }

        @Override
        protected String getRefOrgFieldCode() {
          return OrderQueryDLGInitializer.PK_ORDER_B_PK_ARRVSTOORG;
        }
      };
    }
    this.setQueryService(service);
  }

}
