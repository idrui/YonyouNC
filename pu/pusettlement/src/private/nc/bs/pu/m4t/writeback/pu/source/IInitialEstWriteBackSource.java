/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-15 下午12:02:03
 */
package nc.bs.pu.m4t.writeback.pu.source;

import java.util.List;

import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.vo.pu.m4t.entity.InitialEstContext;
import nc.vo.pu.pub.writeback.IWriteBackSource;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>回写接口
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-15 下午12:02:03
 */
public interface IInitialEstWriteBackSource extends IWriteBackSource {

  /**
   * 方法功能描述：回写上游单据
   * <p>
   * <b>参数说明</b>
   * 
   * @param rwParas
   * @param context
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-15 下午12:02:53
   */
  public void writeback(List<RewritePara> rwParas, InitialEstContext context);
}
