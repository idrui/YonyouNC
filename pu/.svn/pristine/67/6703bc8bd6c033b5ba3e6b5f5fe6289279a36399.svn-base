/**
 * 
 */
package nc.ui.pu.m25.editor.card.beforeedit;

import java.util.Map;

import nc.ui.pu.m25.editor.card.beforeedit.body.ApFinanceOrg;
import nc.ui.pu.m25.editor.card.beforeedit.body.Apliabcenter;
import nc.ui.pu.m25.editor.card.beforeedit.body.Cffileid;
import nc.ui.pu.m25.editor.card.beforeedit.body.ChangeRate;
import nc.ui.pu.m25.editor.card.beforeedit.body.InvoiceAstNum;
import nc.ui.pu.m25.editor.card.beforeedit.body.InvoiceAstUnit;
import nc.ui.pu.m25.editor.card.beforeedit.body.InvoiceNum;
import nc.ui.pu.m25.editor.card.beforeedit.body.Material;
import nc.ui.pu.m25.editor.card.beforeedit.body.Money;
import nc.ui.pu.m25.editor.card.beforeedit.body.Ncaltaxmny;
import nc.ui.pu.m25.editor.card.beforeedit.body.StoreHouse;
import nc.ui.pu.m25.editor.card.beforeedit.body.TaxCode;
import nc.ui.pu.m25.editor.card.beforeedit.body.VBatchCode;
import nc.ui.pu.pub.editor.card.beforeedit.Casscustid;
import nc.ui.pu.pub.editor.card.beforeedit.ProjectTaskId;
import nc.ui.pu.pub.editor.card.handler.AbstractCardBodyBeforeEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.vo.pu.m25.entity.InvoiceItemVO;

/**
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li></li>
 * </ul>
 * <p>
 * </p>
 * 
 * @author xiebo
 * @version 6.0
 * @see
 * @since
 * @time 2010-2-26 上午09:49:45
 */
public class CardBodyBeforeEditEventHandler extends
    AbstractCardBodyBeforeEditEventHandler {

  /*
   * (non-Javadoc)
   * @see
   * nc.ui.pu.pub.editor.card.handler.AbstractCardBodyBeforeEditEventHandler
   * #registerEventListener(java.util.Map)
   */
  @Override
  public void registerEventListener(
      Map<String, ICardBodyBeforeEditEventListener> listenerMap) {
    listenerMap.put(InvoiceItemVO.PK_MATERIAL, new Material());// 表体物料
    listenerMap.put(InvoiceItemVO.PK_STORDOC, new StoreHouse());// 表体仓库
    listenerMap.put(InvoiceItemVO.NASTNUM, new InvoiceAstNum());// 表体数量
    listenerMap.put(InvoiceItemVO.NNUM, new InvoiceNum());// 表体主数量
    listenerMap.put(InvoiceItemVO.NMNY, new Money());// 表体金额
    listenerMap.put(InvoiceItemVO.VCHANGERATE, new ChangeRate());// 表体换算率
    listenerMap.put(InvoiceItemVO.CASTUNITID, new InvoiceAstUnit());// 表体单位
    listenerMap.put(InvoiceItemVO.PK_APFINANCEORG_V, new ApFinanceOrg());// 表体应付组织
    listenerMap.put(InvoiceItemVO.VBATCHCODE, new VBatchCode());// 批次号
    listenerMap.put(InvoiceItemVO.CASSCUSTID, new Casscustid()); // 客户

    // 项目任务
    listenerMap.put(InvoiceItemVO.CPROJECTTASKID, new ProjectTaskId());

    listenerMap.put(InvoiceItemVO.NCALTAXMNY, new Ncaltaxmny());// 计税金额
    listenerMap.put(InvoiceItemVO.CTAXCODEID, new TaxCode());// 税码
    listenerMap.put(InvoiceItemVO.PK_APLIABCENTER_V, new Apliabcenter());// 利润中心
    // add by liangchen1 NC631进出口采购需求，为继承该类的子类提供规则扩展

    // 不允许编辑字段
//    NeverEditBodyItem neverEditItem = new NeverEditBodyItem();
    // 特征码
    listenerMap.put(InvoiceItemVO.CFFILEID, new Cffileid());

    this.registerExpandEventListener(listenerMap);
  }

  /**
   * 规则扩展
   * liangchen1
   */
  protected void registerExpandEventListener(
      Map<String, ICardBodyBeforeEditEventListener> listenerMap) {
    // do nothing
  }

}
