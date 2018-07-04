/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-27 上午10:05:00
 */
package nc.bs.pu.est.rule.pricequery;

import nc.vo.pu.est.rule.IEstPriceQueryInfoProvide;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购暂估的询价策略抽象类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-27 上午10:05:00
 */
public abstract class AbstractEstPriceQueryStrategy {

  /**
   * 方法功能描述：根据询价参数信息，进行询价。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pqinfo　询价信息提供接口
   * @return　返回询价后的结果集 保证结果集与pqinfo参数大小与顺序一致
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-5-27 上午11:04:30
   */
  public EstPriceQryResltData[] queryPrice(IEstPriceQueryInfoProvide[] pqinfo) {
    EstPriceQryResltData[] resltData = this.genResltData(pqinfo);
    this.procResltData(resltData, pqinfo);
    return resltData;
  }

  /**
   * 方法功能描述：生成空的询价结果数组。
   * <p>
   * <b>参数说明</b>
   * 
   * @param pqinfo 询价信息提供接口
   * @return 空的询价结果数组
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-5-27 上午09:24:22
   */
  protected EstPriceQryResltData[] genResltData(
      IEstPriceQueryInfoProvide[] pqinfo) {
    EstPriceQryResltData[] rsltData = new EstPriceQryResltData[pqinfo.length];
    for (int i = 0; i < rsltData.length; i++) {
      String hid = pqinfo[i].getHID();
      String bid = pqinfo[i].getBID();
      rsltData[i] = new EstPriceQryResltData(hid, bid);
    }
    return rsltData;
  }

  /**
   * 方法功能描述：根据询价信息，和初始化的询价结算信息，开始处理询价(真正的询价处理)。
   * <p>
   * <b>参数说明</b>
   * 
   * @param resltData
   * @param pqinfo
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-5-27 上午09:24:06
   */
  protected abstract void procResltData(EstPriceQryResltData[] resltData,
      IEstPriceQueryInfoProvide[] pqinfo);

}
