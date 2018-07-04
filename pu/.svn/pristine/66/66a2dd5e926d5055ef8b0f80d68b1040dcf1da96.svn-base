/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-30 下午05:14:03
 */
package nc.bs.pu.m21.writeback.source;

import nc.vo.pu.m21.context.OrderContext;
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
 * @author wuxla
 * @time 2010-6-30 下午05:14:03
 */
public class WriteBackUtil {

  public static WriteBackUtil instance = new WriteBackUtil();

  private WriteBackUtil() {
    // 私有
  }

  public static WriteBackUtil getInstance() {
    return WriteBackUtil.instance;
  }

  public IWriteBackSource getWriteBack20(OrderContext ctx) {
    return new WriteBack20(ctx);
  }

  public IWriteBackSource getWriteBack23() {
    return new WriteBack23();
  }

  public IWriteBackSource getWriteBack30() {
    return new WriteBack30();
  }

  public IWriteBackSource getWriteBack45() {
    return new WriteBack45();
  }

  public IWriteBackSource getWriteBack49() {
    return new WriteBack49();
  }

  /**
   * 库存计划-计划订单回写器
   * 
   * @return
   */
  public IWriteBackSource getWriteBack4F() {
    return new WriteBack4F();
  }

  public IWriteBackSource getWriteBack55A2() {
    return new WriteBack55A2();
  }

  public IWriteBackSource getWriteBack55B4() {
    return new WriteBack55B4();
  }

  /**
   * 离散生产订单回写器
   * 
   * @return
   */
  public IWriteBackSource getWriteBack55C2() {
    return new WriteBack55C2();
  }

  public IWriteBackSource getWriteBack5X() {
    return new WriteBack5X();
  }

  public IWriteBackSource getWriteBackET() {
    return new WriteBackET();
  }
}
