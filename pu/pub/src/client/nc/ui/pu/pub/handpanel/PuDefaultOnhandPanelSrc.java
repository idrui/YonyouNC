/**
 * $文件说明$
 * 
 * @author gaogr
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-31 上午10:32:03
 */
package nc.ui.pu.pub.handpanel;

import java.util.HashMap;
import java.util.Map;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyRowChangedEvent;
import nc.ui.pubapp.uif2app.model.IAppModelEx;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.scmf.ic.onhand.OnhandPanelSrc;
import nc.vo.scmf.ic.onhand.OnhandDimParamVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>现存量面板采购默认实现接口
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-8-31 上午10:32:03
 */
public class PuDefaultOnhandPanelSrc implements OnhandPanelSrc {
  /** 卡片界面 **/
  private BillForm billForm;

  /** 单据字段与存量VO字段对应关系 **/
  private Map<String, String> keyTOOnhandVOKey;

  /** 事件模型 **/
  private IAppModelEx model;

  /**
   * 父类方法重写
   * 
   * @see nc.ui.scmf.ic.onhand.OnhandPanelSrc#addCardBodyRowChangedEvent(nc.ui.pubapp.uif2app.event.IAppEventHandler)
   */
  @Override
  public void addCardBodyRowChangedEvent(
      IAppEventHandler<CardBodyRowChangedEvent> l) {
    this.getModel().addAppEventListener(CardBodyRowChangedEvent.class, l);
  }

  /**
   * 卡片界面
   * 
   * @return billForm
   */
  public BillForm getBillForm() {
    return this.billForm;
  }

  /** 单据字段与存量VO字段对应关系 **/
  public Map<String, String> getKeyTOOnhandVOKey() {
    if (null == this.keyTOOnhandVOKey) {
      this.initKeyTOOnhandVOKey();
    }
    return this.keyTOOnhandVOKey;
  }

  /** 取得事件模型 **/
  public IAppModelEx getModel() {
    return this.model;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.scmf.ic.onhand.OnhandPanelSrc#getQryOnhandDim(int)
   */
  @Override
  public OnhandDimParamVO getQryOnhandDim(int row) {
    OnhandDimParamVO dimvo = new OnhandDimParamVO();
    BillCardPanel bcp = this.getBillForm().getBillCardPanel();
    for (Map.Entry<String, String> field : this.getKeyTOOnhandVOKey()
        .entrySet()) {
      dimvo.setAttributeValue(field.getKey(),
          bcp.getBodyValueAt(row, field.getValue()));
    }
    return dimvo;
  }

  public void initKeyTOOnhandVOKey() {
    this.keyTOOnhandVOKey = new HashMap<String, String>();
  }

  /**
   * 卡片界面
   * 
   * @param billForm
   *          要设置的 卡片界面
   */
  public void setBillForm(BillForm billForm) {
    this.billForm = billForm;
  }

  /** 单据字段与存量VO字段对应关系 **/
  public void setKeyTOOnhandVOKey(Map<String, String> keyTOOnhandVOKey) {
    this.keyTOOnhandVOKey = keyTOOnhandVOKey;
  }

  /** 设置事件模型 **/
  public void setModel(IAppModelEx model) {
    this.model = model;
  }

}
