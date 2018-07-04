package nc.ui.pu.m23.check.action;

import java.util.List;

import javax.swing.Action;

import nc.bs.uif2.IActionCode;
import nc.ui.pu.m23.model.ArriveCheckManageModel;
import nc.ui.pub.print.IMetaDataDataSource;
import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction;
import nc.ui.uif2.actions.ActionInitializer;

public class PrintUIAction extends MetaDataBasedPrintAction {

  /**
   * 打印数据源类
   * 
   * @since 6.0
   * @version 2010-11-4 下午12:01:57
   * @author tianft
   */
  public class ArriveCheckDataSource extends MetaDataSource {

    private static final long serialVersionUID = 4276399317438592814L;

    @Override
    public Object[] getMDObjects() {
      List datalist =
          ((ArriveCheckManageModel) PrintUIAction.this.getModel()).getData();
      Object[] datas = datalist.toArray(new Object[datalist.size()]);
      if (PrintUIAction.this.getBeforePrintDataProcess() != null) {
        datas =
            PrintUIAction.this.getBeforePrintDataProcess().processData(datas);
      }
      return datas;
    }
  }

  private static final long serialVersionUID = 2061080088740164264L;

  public PrintUIAction() {
    ActionInitializer.initializeAction(this, IActionCode.PRINT);
    this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName());
  }

  @Override
  public IMetaDataDataSource getDataSource() {
    return new ArriveCheckDataSource();
  }
}
