package nc.ui.pu.m21.billref.pu.m23;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m21.pu.m23.IOrderQueryFor23;
import nc.ui.pu.m21.view.OrderQueryDLGInitializer;
import nc.ui.pu.pub.billref.PuRefQueryService;
import nc.ui.pub.pf.BillSourceVar;
import nc.ui.pubapp.billref.src.RefInfo;
import nc.ui.pubapp.uif2app.model.IQueryService;
import nc.ui.querytemplate.querytree.IQueryScheme;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ���������������ת����Ϣע����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-5-25 ����06:12:48
 */
public class OrderRefInfoFor23 extends RefInfo {
  @Override
  public IQueryService getQueryService() {
    if (super.getQueryService() == null) {
      this.setQueryService();
    }
    return super.getQueryService();
  }

  private void setQueryService() {
    BillSourceVar var = this.getBillSrcVar();
    // ���UserObject��Ϊ�գ���Ϊ�˻�
    IQueryService service = null;
    if (var.getUserObj() != null) {
      service = new PuRefQueryService() {
        @Override
        public Object[] queryByQueryScheme(IQueryScheme queryScheme)
            throws Exception {
          this.checkQueryCond(queryScheme);
          IOrderQueryFor23 queryService =
              NCLocator.getInstance().lookup(IOrderQueryFor23.class);
          return queryService.queryFor23Return(queryScheme);
        }

        @Override
        protected String getRefOrgFieldCode() {
          return OrderQueryDLGInitializer.PK_ORDER_B_PK_ARRVSTOORG;
        }
      };
    }
    // ���UserObjectΪ�գ���Ϊ���򵥾�
    else {
      service = new PuRefQueryService() {
        @Override
        public Object[] queryByQueryScheme(IQueryScheme queryScheme)
            throws Exception {
          this.checkQueryCond(queryScheme);
          IOrderQueryFor23 queryService =
              NCLocator.getInstance().lookup(IOrderQueryFor23.class);
          return queryService.queryFor23(queryScheme);
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
