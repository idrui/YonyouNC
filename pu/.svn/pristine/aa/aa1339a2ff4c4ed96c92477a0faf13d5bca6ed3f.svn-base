package nc.vo.pu.batchcode;

import nc.vo.ic.batchcode.BatchDlgParam;
import nc.vo.pub.lang.UFBoolean;

public class PUBatchDlgParam extends BatchDlgParam {

  private static final long serialVersionUID = 1L;

  /**
   * 使用新的批次参照
   */
  private UFBoolean isNewBatchRef = UFBoolean.FALSE;

  /**
   * 新批次参照扩展配置文件，各个领域可以自定定义配置文件
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
