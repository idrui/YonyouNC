/**
 * $文件说明$
 * 
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-10 下午08:34:03
 */
package nc.vo.pu.pub.writeback;

import java.util.List;

import nc.impl.pubapp.bill.rewrite.RewritePara;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购回写上游的统一接口
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhyhang
 * @time 2010-2-10 下午08:34:03
 */
public interface IWriteBackSource {
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
  public void writeback(List<RewritePara> rwParas);
}
