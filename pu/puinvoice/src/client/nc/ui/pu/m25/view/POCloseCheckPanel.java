package nc.ui.pu.m25.view;

import nc.ui.org.closeaccbook.check.AbstractCloseCheckPanel;

/**
 * �������ǰ�˼��panel��
 * 
 * @since 6.36
 * @version 2015-5-27 ����3:20:04
 * @author luojw
 */

public class POCloseCheckPanel extends AbstractCloseCheckPanel {

  public POCloseCheckPanel(String title, String funCode, String configFilePath) {
    super(title, funCode, configFilePath);
  }

  @Override
  public String getCheckActionName() {
    return "checkAction";
  }

  @Override
  public String getCheckModelName() {
    return null;
  }

  @Override
  public String getExportActionName() {
    return "outputAction";
  }

  @Override
  public String getPreviewActionName() {
    return "preViewAction";
  }

  @Override
  public String getPrintActionName() {
    return "printAction";
  }
}
