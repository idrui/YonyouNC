package nc.ui.pu.m21.action;

import nc.ui.pu.m21.service.OrderReceivePlanModel;
import nc.ui.pub.print.IMetaDataDataSource;
import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction;

/**
 * @since 6.0
 * @version 2011-4-16 ÏÂÎç01:31:43
 * @author wuxla
 */

public class ReceivePlanPrintAction extends MetaDataBasedPrintAction {
  public class ReceivePlanPrintDataSource extends MetaDataSource {

    private static final long serialVersionUID = 3291938107416825406L;

    @Override
    public Object[] getMDObjects() {
      Object[] datas =
          ((OrderReceivePlanModel) ReceivePlanPrintAction.this.getModel())
              .getPrintData();
      if (ReceivePlanPrintAction.this.getBeforePrintDataProcess() != null) {
        datas =
            ReceivePlanPrintAction.this.getBeforePrintDataProcess()
                .processData(datas);
      }
      return datas;
    }
  }

  private static final long serialVersionUID = 5105848664598815193L;

  @Override
  public IMetaDataDataSource getDataSource() {
    return new ReceivePlanPrintDataSource();
  }

}
