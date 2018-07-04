/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-1 下午01:45:22
 */
package nc.ui.pu.position.model;

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
 * @author GGR
 * @time 2010-7-1 下午01:45:22
 */
public class PositionModelDataManager extends
    nc.ui.pubapp.uif2app.model.BaseBillModelDataManager {

  @Override
  public void initModel() {
    if (null != this.getModel().getContext().getPk_org()) {
      String where =
          " and pk_org='" + this.getModel().getContext().getPk_org() + "'";
      this.initModelByWhereSql(where);
    }
    else {
      this.getModel().initModel(null);
    }
  }
}
