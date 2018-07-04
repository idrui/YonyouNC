/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-3 下午06:02:12
 */
package nc.ui.pu.m422x.rule;

import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.scmpub.ref.FilterPsndocRefUtils;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>人员过滤
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-8-3 下午06:02:12
 */
public class ReferenceFilterApppsn {
  private String deptkey;

  private BillCardPanel panel;

  private String psnkey;

  public ReferenceFilterApppsn(BillCardPanel panel, String deptkey,
      String psnkey) {
    this.panel = panel;
    this.deptkey = deptkey;
    this.psnkey = psnkey;
  }

  public void filterPsn() {
    Object org =
        this.panel.getHeadItem(StoreReqAppHeaderVO.PK_ORG).getValueObject();
    Object cdeptid =
        this.panel.getHeadItem(StoreReqAppHeaderVO.PK_APPDEPTH)
            .getValueObject();

    UIRefPane pane =
        (UIRefPane) this.panel.getHeadItem(this.psnkey).getComponent();
    if (pane == null) {
      return;
    }
    FilterPsndocRefUtils filter =
        FilterPsndocRefUtils.createFilterPsndocRefUtilsOfIC(pane);
    filter.filterItemRefByOrg((String) org);
    filter.filterPsndocRefByDept((String) cdeptid);

  }

  public void filterPsn(int row) {
    Object cdeptid = this.panel.getBodyValueAt(row, this.deptkey);
    Object org =
        this.panel.getHeadItem(StoreReqAppHeaderVO.PK_ORG).getValueObject();

    UIRefPane pane =
        (UIRefPane) this.panel.getBodyItem(this.psnkey).getComponent();
    if (pane == null) {
      return;
    }
    FilterPsndocRefUtils filter =
        FilterPsndocRefUtils.createFilterPsndocRefUtilsOfIC(pane);
    filter.filterItemRefByOrg((String) org);
    filter.filterPsndocRefByDept((String) cdeptid);

  }
}
