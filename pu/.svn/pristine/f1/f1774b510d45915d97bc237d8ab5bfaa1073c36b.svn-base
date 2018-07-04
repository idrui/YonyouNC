/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-15 下午03:36:13
 */
package nc.ui.pu.m27.match.view.query;

import java.util.Map;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>使用采购入库单查询模板查询其它单据的信息
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-9-15 下午03:36:13
 */
public class SettleQueryInfo {
  private Map<String, String> attrPathMap;

  private String beanID;

  /**
   * SettleQueryInfo 的构造子
   * 
   * @param attrPathMap
   *          入库单到其它待结算单据查询模板属性映射
   * @param beanID
   *          其它待结算单据元数据主实体ID
   */
  public SettleQueryInfo(String beanID, Map<String, String> attrPathMap) {
    super();
    this.attrPathMap = attrPathMap;
    this.beanID = beanID;
  }

  public Map<String, String> getAttrPathMap() {
    return this.attrPathMap;
  }

  public String getBeanID() {
    return this.beanID;
  }

}
