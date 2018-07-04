/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-1 上午08:13:17
 */
package nc.vo.pu.pub.writeback;

import nc.impl.pubapp.bill.rewrite.BillRewriter;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>给BillRewriter添加来源单据类型对应的VO类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-1 上午08:13:17
 */
public interface IWriteBackAddSRCClazz {
  /**
   * 方法功能描述：给BillRewriter添加来源单据类型对应的VO类
   * <p>
   * <b>参数说明</b>
   * 
   * @param billRewriter
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-1 上午09:32:58
   */
  public void addSRCClazz(BillRewriter billRewriter);
}
