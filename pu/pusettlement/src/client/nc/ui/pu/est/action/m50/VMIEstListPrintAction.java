package nc.ui.pu.est.action.m50;

import nc.ui.pub.print.IMetaDataDataSource;

/**
 * 暂估处理打印
 * 
 * @since 6.0
 * @version 2010-11-8 上午09:59:45
 * @author tianft
 */

public class VMIEstListPrintAction extends VMIEstCardPrintAction {

  /**
   * 打印数据获取类
   * 
   * @since 6.0
   * @version 2010-11-8 上午10:03:04
   * @author tianft
   */
  public class VmiEstPrintDataSource extends MetaDataSource {

    private static final long serialVersionUID = 648800490256459480L;

    @Override
    public Object[] getMDObjects() {
      return VMIEstListPrintAction.this.getBeforePrintDataProcess()
          .processData(VMIEstListPrintAction.this.getVMIPrintDatas());
    };
  }

  private static final long serialVersionUID = 7042734595387239192L;

  @Override
  public IMetaDataDataSource getDataSource() {
    return new VmiEstPrintDataSource();
  }
}
