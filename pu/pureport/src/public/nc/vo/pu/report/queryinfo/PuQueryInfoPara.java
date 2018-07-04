package nc.vo.pu.report.queryinfo;

import java.io.Serializable;

/**
 * 采购自由报表查询参数类
 * 
 * @since 6.0
 * @version 2011-2-22 上午09:58:19
 * @author yinfy
 */

public abstract class PuQueryInfoPara implements Serializable {

  public static final String QUERY_PARA = "#queryPara#";
  
  // 查询条件
  public static final String QUERY_CONDS = "#query_conds#";

  private static final long serialVersionUID = 3694910497899283742L;

  /** 汇总口径 **/
  private String groupcondtion;

  public String getGroupcondtion() {
    return this.groupcondtion;
  }

  /**
   * 根据条件，获取分组字段
   * 
   * @return 分组字段
   */
  public String[] getGroupKeys() {
    return null;
  }

  /**
   * 根据条件，获取隐藏字段
   * 
   * @return 隐藏字段
   */
  public String[] getHideKeys() {
    return null;
  }

  /**
   * 根据条件，获取排序字段
   * 
   * @return
   */
  public String[] getSortKeys() {
    return null;
  }

  /**
   * 根据条件，获取合计字段
   * 
   * @return 合计字段
   */
  public String[] getTotalKeys() {
    return null;
  }

  public void setGroupcondtion(String groupenum) {
    this.groupcondtion = groupenum;
  }
}
