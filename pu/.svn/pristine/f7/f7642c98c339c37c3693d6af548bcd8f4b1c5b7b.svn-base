package nc.ui.pu.est.action.m45;

import nc.ui.pub.print.IMetaDataDataSource;

/**
 * 暂估处理打印
 * 
 * @since 6.0
 * @version 2010-11-8 上午09:59:45
 * @author tianft
 */

public class PurInEstListPrintAction extends PurInEstCardPrintAction {

  /**
   * 打印数据获取类
   * 
   * @since 6.0
   * @version 2010-11-8 上午10:03:04
   * @author tianft
   */
  public class PurInEstPrintDataSource extends MetaDataSource {

    private static final long serialVersionUID = 648800490256459480L;

    @Override
    public Object[] getMDObjects() {
      return PurInEstListPrintAction.this.getBeforePrintDataProcess()
          .processData(PurInEstListPrintAction.this.getPurchaseInPrintDatas());
    }
  }

  private static final long serialVersionUID = 7042734595387239192L;

  @Override
  public IMetaDataDataSource getDataSource() {
    return new PurInEstPrintDataSource();
  }
}
