/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version
 * @see
 * @since
 * @time 2009-6-2 上午10:44:13
 */
package nc.ui.pu.costfactor.view;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.costfactor.ICostFactorQueryService;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.view.handler.DefaultBillListEventHandler;
import nc.vo.pu.costfactor.entity.CostfactorHeaderVO;
import nc.vo.pu.costfactor.entity.CostfactorItemVO;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>功能条目1
 * <li>功能条目2
 * <li>...
 * </ul>
 * <p>
 * <b>变更历史（可选）：</b>
 * <p>
 * XXX版本增加XXX的支持。
 * <p>
 * <p>
 * 
 * @version v60
 * @since v60
 * @author zhaoyha
 * @time 2009-6-2 上午10:44:13
 */
public class CostFactorEventHandler extends DefaultBillListEventHandler {

  private ICostFactorQueryService costQuerySrv = null;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.view.handler.DefaultBillListEventHandler#afterHeadEdit(nc.ui.pub.bill.BillListPanel,
   *      nc.ui.pub.bill.BillEditEvent)
   */
  @Override
  public void afterHeadEdit(BillListPanel listPanel, BillEditEvent e) {
    // 组织改变，清空费用信息
    if ("pk_org".equals(e.getKey())) {
      BillItem[] items = listPanel.getBillListData().getBodyItems();
      for (int i = 0; i < listPanel.getBodyBillModel().getRowCount(); i++) {
        for (BillItem item : items) {
          if ("pk_material.name".equals(item.getKey()) || item.isShow()
              && item.isEdit()) {
            listPanel.getBodyBillModel().setValueAt(null, i, item.getKey());
          }
        }
      }
    }
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.view.handler.DefaultBillListEventHandler#beforeBodyEdit(nc.ui.pub.bill.BillListPanel,
   *      nc.ui.pub.bill.BillEditEvent)
   */
  @Override
  public boolean beforeBodyEdit(BillListPanel listPanel, BillEditEvent e) {
    this.filterAccountFactor(listPanel, e);// 过滤初级核算要素
    UIRefPane refMaterial =
        (UIRefPane) listPanel.getBodyItem(CostfactorItemVO.PK_MATERIAL)
            .getComponent();
    CostfactorHeaderVO head =
        ((CostfactorVO) this.getEditor().getValue()).getParentVO();
    ((CostfactorVO) this.getEditor().getValue()).getParentVO();
    // 主组织为空，不让编辑
    if (StringUtils.isEmpty(head.getPk_org())) {
      return false;
    }
    boolean referenced =
        this.beReferenced(listPanel, e.getRow(), head.getPk_org(),
            head.getPk_group());
    if (referenced) {
      return false;
    }
    // 根据主组织过滤物料，只允许录入主组织下的费用物料
    refMaterial.getRefModel().setPk_org(head.getPk_org());
    refMaterial.getRefModel().setPk_group(head.getPk_group());
    // 根据需求znl意见，既是费用又是折扣的参照不到
    refMaterial.getRefModel()
        .addWherePart(" and fee='Y' and discountflag='N' ");

    return true;

  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-8-11 上午11:19:20
   */
  private boolean beReferenced(BillListPanel listPanel, int row, String pk_org,
      String pk_group) {
    int nState = listPanel.getBodyBillModel().getRowState(row);
    // 新增行，修改行不判断
    if (nState == BillModel.ADD || nState == BillModel.MODIFICATION) {
      return false;
    }
    Object pk_srcmaterial =
        listPanel.getBodyBillModel().getValueAt(row,
            CostfactorItemVO.PK_SRCMATERIAL);
    // 物料空不控制
    if (pk_srcmaterial == null
        || StringUtils.isEmpty(pk_srcmaterial.toString())) {
      return false;
    }
    try {
      return this.getCostQuerySrv().beReferenced(pk_group, pk_org,
          pk_srcmaterial.toString());
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);

    }
    return false;
  }

  private void filterAccountFactor(BillListPanel listPanel, BillEditEvent e) {
    if (!CostfactorItemVO.PK_ACCOUNTFACTOR.equals(e.getKey())) {
      return;
    }
    UIRefPane refpane =
        (UIRefPane) listPanel.getBodyItem(CostfactorItemVO.PK_ACCOUNTFACTOR)
            .getComponent();
    if (null == refpane) {
      return;
    }
    // FilterFactorRefUtils refUtils = new FilterFactorRefUtils(refpane);
    // refUtils.setGroup(ClientContext.getInstance().getPk_group());
    // refUtils.setOrg(ClientContext.getInstance().getPk_group());
    // refUtils.filterByFactorcls(new Integer[] {
    // Integer.valueOf(0)
    // });// 过滤初级核算要素
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author tianft
   * @time 2010-8-11 上午11:19:27
   */
  private ICostFactorQueryService getCostQuerySrv() {
    if (this.costQuerySrv == null) {
      this.costQuerySrv =
          NCLocator.getInstance().lookup(ICostFactorQueryService.class);
    }
    return this.costQuerySrv;
  }

}
