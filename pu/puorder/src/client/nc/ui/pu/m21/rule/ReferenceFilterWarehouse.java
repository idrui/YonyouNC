package nc.ui.pu.m21.rule;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m21transtype.IPoTransTypeQuery;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.scmpub.ref.FilterWareHouseRefUtils;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.pub.DirectUtil;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>需求库存组织修改时引起的参照过滤
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-11 下午02:27:31
 */
public class ReferenceFilterWarehouse {
  private BillCardPanel panel;

  /** 库存组织字段 */
  private String storeItemKey;

  /** 仓库字段 */
  private String warehouseItemKey;

  public ReferenceFilterWarehouse(BillCardPanel panel, String storeItemKey,
      String warehouseItemKey) {
    this.panel = panel;
    this.storeItemKey = storeItemKey;
    this.warehouseItemKey = warehouseItemKey;
  }

  public void filter(int row) {
    // 过滤需求仓库的参照
    this.filterReqWarehouse(row);
  }

  public void reqFilter(int row) {
    Object storeOrg = this.panel.getBodyValueAt(row, this.storeItemKey);
    Object group = this.panel.getBodyValueAt(row, OrderItemVO.PK_GROUP);

    UIRefPane pane =
        (UIRefPane) this.panel.getBodyItem(this.warehouseItemKey)
            .getComponent();
    if (pane == null) {
      return;
    }

    FilterWareHouseRefUtils filter = new FilterWareHouseRefUtils(pane);
    filter.filterItemRefByGroup((String) group);
    filter.filterItemRefByOrg((String) storeOrg);
    filter.filterWasteStorc();
  }

  private void filterReqWarehouse(int row) {
    Object storeOrg = this.panel.getBodyValueAt(row, this.storeItemKey);
    Object group = this.panel.getBodyValueAt(row, OrderItemVO.PK_GROUP);
    Object profitcentre = this.panel.getBodyValueAt(row, OrderItemVO.PK_ARRLIABCENTER);

    String ctrantypeid =
        (String) this.panel.getHeadItem(OrderHeaderVO.CTRANTYPEID)
            .getValueObject();
    boolean isDirect;
    if (ctrantypeid != null) {
      isDirect = this.isTrantypeDirect(ctrantypeid);
    }
    else {
      CardEditorHelper card = new CardEditorHelper(this.panel);
      isDirect = DirectUtil.isDirect(card, row);
    }
    this.filter(storeOrg, group, profitcentre, isDirect);
  }

  /**
   * 过滤到货计划的收货仓库
   * 
   * @param vo
   */
  public void filterRPWarehouse(OrderVO vo, int row){
    Object storeOrg = this.panel.getBodyValueAt(row, this.storeItemKey);
    String group = vo.getHVO().getPk_group();
    String ctrantypeid = vo.getHVO().getCtrantypeid();
    boolean isDirect = this.isTrantypeDirect(ctrantypeid);
    Object pk_order = this.panel.getBodyValueAt(row, "crowno");
    String profitcentre = null;
    vo.getChildren(OrderItemVO.class);
    for(OrderItemVO item : vo.getBVO()){
      if(item.getPk_order_b().equals(pk_order)){
        profitcentre = item.getPk_arrliabcenter();
        break;
      }
    }
    this.filter(storeOrg, group, profitcentre, isDirect);
  }
  
  /**
   * 根据字段过滤仓库
   * 
   * @param storeOrg 库存组织
   * @param group 集团
   * @param profitcentre 收货利润中心
   * @param isDirect 是否直运
   */
  private void filter(Object storeOrg, Object group, Object profitcentre, boolean isDirect) {
    UIRefPane pane =
        (UIRefPane) this.panel.getBodyItem(this.warehouseItemKey)
            .getComponent();
    FilterWareHouseRefUtils filter = new FilterWareHouseRefUtils(pane);
    filter.filterItemRefByGroup(group.toString());
    filter.filterItemRefByOrg(storeOrg.toString());
    // 根据收货利润中心过滤
    if(profitcentre != null){
      filter.filterByLiabcenter(profitcentre);
    }
    filter.filterWasteStorc();
    if (isDirect) {
      filter.onlyDirectStorc();
    }
  }

  private boolean isTrantypeDirect(String ctrantypeid) {
    IPoTransTypeQuery service =
        NCLocator.getInstance().lookup(IPoTransTypeQuery.class);
    try {
      Map<String, PoTransTypeVO> map = service.queryAttrByIDs(new String[] {
        ctrantypeid
      });
      if (null == map) {
        return false;
      }
      PoTransTypeVO vo = map.get(ctrantypeid);
      if (null == vo || null == vo.getBdirect()) {
        return false;
      }
      return vo.getBdirect().booleanValue();
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return false;
  }
}
