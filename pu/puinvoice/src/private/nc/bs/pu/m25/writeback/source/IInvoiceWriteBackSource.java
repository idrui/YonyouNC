/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-11 上午10:35:55
 */
package nc.bs.pu.m25.writeback.source;

import java.util.List;

import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pu.pub.writeback.IWriteBackSource;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-11 上午10:35:55
 */
public interface IInvoiceWriteBackSource extends IWriteBackSource {

  /**
   * 方法功能描述：对上游单据进行真正的回写。
   * <p>
   * <b>参数说明</b>
   * 
   * @param rwParas 从钟老板框架生成的回写参数列表,可用于转换为上游的回写VO
   *          <p>
   *          且只针对一种上游单据类型
   *          <p>
   * @since 6.0
   * @author zhyhang
   * @time 2010-2-10 下午08:35:27
   */
  public void writeback(List<RewritePara> rwParas, InvoiceUIToBSEnv env);

}
