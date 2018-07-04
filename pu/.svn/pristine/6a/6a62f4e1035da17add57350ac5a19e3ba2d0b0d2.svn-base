/**
 * $文件说明$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-12 下午02:31:33
 */
package nc.ui.pu.m21.action.orderclose;

import nc.ui.pu.m21.service.OrderCloseManageModel;
import nc.ui.pub.print.IMetaDataDataSource;
import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购订单关闭打印
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-4-12 下午02:31:33
 */
public class ClosePrintAction extends MetaDataBasedPrintAction {

  /**
   * 打印数据源类
   * 
   * @since 6.0
   * @version 2010-11-4 下午12:01:57
   * @author tianft
   */
  public class ClosePrintDataSource extends MetaDataSource {

    private static final long serialVersionUID = 4276399317438592814L;

    @Override
    public Object[] getMDObjects() {
      Object[] datas =
          ((OrderCloseManageModel) ClosePrintAction.this.getModel())
              .getPrintData();
      if (ClosePrintAction.this.getBeforePrintDataProcess() != null) {
        datas =
            ClosePrintAction.this.getBeforePrintDataProcess()
                .processData(datas);
      }
      return datas;
    }
  }

  private static final long serialVersionUID = -6281375434636374892L;

  @Override
  public IMetaDataDataSource getDataSource() {
    return new ClosePrintDataSource();
  }

}
