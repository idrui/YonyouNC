/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-13 下午01:18:52
 */
package nc.ui.pu.est.editor.head.after;

import java.util.Map;

import nc.ui.pu.est.model.EstUIContext;
import nc.ui.pu.pub.editor.list.listener.IListHeadAfterEditEventListener;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.ClientContext;
import nc.ui.pubapp.calculator.data.BillModelDataSet;
import nc.ui.pubapp.uif2app.event.list.ListHeadAfterEditEvent;
import nc.ui.pubapp.util.ListPanelValueUtils;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.util.EstRelationCalcItem;
import nc.vo.pu.est.util.EstRelationCalcUtil;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.uif2.LoginContext;

import org.apache.commons.collections.MapUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>货物暂估(表头编辑处理基类)
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-8-13 下午01:18:52
 */
public class EstHeadAfterEditHandler implements IListHeadAfterEditEventListener {

  @Override
  public void afterEdit(ListHeadAfterEditEvent event) {
    int row = event.getRow();
    if (row < 0) {
      return;
    }
    PricePriority pp = this.getPricePrior(event);
    EstRelationCalcUtil calUtil = this.getReCalcUtil();
    IDataSetForCal dataset = this.getCalDataSet(event, calUtil);
    // 子类去处理相应的逻辑
    this.realAfterEdit(event, pp, calUtil, dataset);
  }

  private IDataSetForCal getCalDataSet(ListHeadAfterEditEvent event,
      EstRelationCalcUtil calUtil) {
    return new BillModelDataSet(event.getBillListPanel().getHeadBillModel(),
        event.getRow(), calUtil.getRelaItems());
  }

  private PricePriority getPricePrior(ListHeadAfterEditEvent event) {
    BillListPanel blp = event.getBillListPanel();
    int row = event.getRow();
    PricePriority pp = PricePriority.PRICE_PRIOR_TO_TAXPRICE;
    LoginContext cntxt = event.getContext();
    if (!(cntxt instanceof EstUIContext)) {
      return pp;
    }
    ListPanelValueUtils lpvu = new ListPanelValueUtils(blp);
    String pk = (String) lpvu.getHeadValueAt(row, GoodsEstVO.PK_STOCKPS_B);
    Map<String, String> idpurogMap = ((EstUIContext) cntxt).getIdPurOrgMap();
    if (MapUtils.isEmpty(idpurogMap)) {
      return pp;
    }
    String pk_purorg = idpurogMap.get(pk);
    return EstVOUtil.getPO28(pk_purorg);
  }

  private EstRelationCalcUtil getReCalcUtil() {
    return new EstRelationCalcUtil(ClientContext.getInstance().getPk_group(),
        new EstRelationCalcItem());
  }

  /**
   * 方法功能描述：子类中真正的处理逻辑。
   * <p>
   * <b>参数说明</b>
   * 
   * @param event
   *          编辑后事件
   * @param pp
   *          含税优先还无税优先
   * @param calUtil
   *          联动计算工具
   * @param dataset
   *          联动计算的数据值
   *          <p>
   *          以上参数均已经准备好，子类可直接使用
   *          <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-8-13 下午02:57:36
   */
  protected void realAfterEdit(ListHeadAfterEditEvent event, PricePriority pp,
      EstRelationCalcUtil calUtil, IDataSetForCal dataset) {
    //
  }

}
