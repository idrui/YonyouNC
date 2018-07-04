/**
 * $�ļ�˵��$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-12 ����02:31:33
 */
package nc.ui.pu.m21.action.orderclose;

import nc.ui.pu.m21.service.OrderCloseManageModel;
import nc.ui.pub.print.IMetaDataDataSource;
import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ������رմ�ӡ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-4-12 ����02:31:33
 */
public class ClosePrintAction extends MetaDataBasedPrintAction {

  /**
   * ��ӡ����Դ��
   * 
   * @since 6.0
   * @version 2010-11-4 ����12:01:57
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
