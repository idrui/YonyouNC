package nc.vo.pu.report.pub;

import nc.pub.smart.context.SmartContext;
import nc.pub.smart.model.descriptor.ConfigDescriptor;

import com.ufida.report.anareport.FreeReportContextKey;

/**
 * @since 6.0
 * @version 2011-9-20 上午09:04:20
 * @author wuxla
 */

public class RptMaxRowUtil {

  /**
   * 获取报表最大行数
   * 
   * @param context
   * @return
   */
  public static int getMaxRow(SmartContext context) {
    int max = 5000;
    Object obj =
        context.getAttribute(FreeReportContextKey.KEY_SMART_CONFIG_DESC);
    if (obj != null) {
      ConfigDescriptor config = (ConfigDescriptor) obj;
      max = config.getMaxRows();
    }
    return max;
  }
}
