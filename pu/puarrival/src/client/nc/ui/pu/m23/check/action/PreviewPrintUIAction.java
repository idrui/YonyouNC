package nc.ui.pu.m23.check.action;

import java.util.List;

import nc.bs.uif2.IActionCode;
import nc.ui.pu.m23.model.ArriveCheckManageModel;
import nc.ui.pub.print.IMetaDataDataSource;
import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction;
import nc.ui.scmpub.action.SCMActionInitializer;

public class PreviewPrintUIAction extends MetaDataBasedPrintAction {

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
          ((ArriveCheckManageModel) PreviewPrintUIAction.this.getModel())
              .getData();
      Object[] datas = datalist.toArray(new Object[datalist.size()]);
      if (PreviewPrintUIAction.this.getBeforePrintDataProcess() != null) {
        datas =
            PreviewPrintUIAction.this.getBeforePrintDataProcess().processData(
                datas);
      }
      return datas;
    }
  }

  private static final long serialVersionUID = -4392288420244111903L;

  public PreviewPrintUIAction() {
    SCMActionInitializer.initializeAction(this, IActionCode.PREVIEW);
  }

  @Override
  public String getBtnName() {
    return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
        "UC001-0000112")/* @res "预览" */;
  }

  @Override
  public IMetaDataDataSource getDataSource() {
    return new ArriveCheckDataSource();
  }
}
