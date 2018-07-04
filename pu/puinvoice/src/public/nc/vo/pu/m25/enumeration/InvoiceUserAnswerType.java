/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-20 下午04:49:36
 */
package nc.vo.pu.m25.enumeration;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>提示异常后，用户确认的类型
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-4-20 下午04:49:36
 */
public enum InvoiceUserAnswerType {
  /** 用户取消 **/
  CANCEL, NO,
  /** 用户还未回答 **/
  NO_ANSWER, YES;
}
