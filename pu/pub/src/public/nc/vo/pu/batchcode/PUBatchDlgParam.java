package nc.vo.pu.batchcode;

import nc.vo.ic.batchcode.BatchDlgParam;
import nc.vo.pub.lang.UFBoolean;

public class PUBatchDlgParam extends BatchDlgParam {

  private static final long serialVersionUID = 1L;

  /**
   * ʹ���µ����β���
   */
  private UFBoolean isNewBatchRef = UFBoolean.FALSE;

  /**
   * �����β�����չ�����ļ���������������Զ����������ļ�
   */
  private String pluginConfigPath = "nc/ui/pu/pub/batchcode/batchcode.xml";

  @Override
  public UFBoolean getIsNewBatchRef() {
    return this.isNewBatchRef;
  }

  @Override
  public String getPluginConfigPath() {
    return this.pluginConfigPath;
  }

  @Override
  public void setIsNewBatchRef(UFBoolean isNewBatchRef) {
    this.isNewBatchRef = isNewBatchRef;
  }

  @Override
  public void setPluginConfigPath(String pluginConfigPath) {
    this.pluginConfigPath = pluginConfigPath;
  }

}
