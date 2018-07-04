package nc.ui.pu.m21.editor.card.afteredit.body;

import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.itf.scmpub.reference.uap.bd.stordoc.StordocPubService;
import nc.pubitf.org.cache.IOrgUnitPubService_C;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.bd.stordoc.StordocVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.rule.OrganizationDefaultValue;
import nc.vo.pu.m21.rule.WarehouseDefaultValue;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>收货仓库的编辑后事件处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-31 下午01:21:16
 */
public class ReceiveWarehouse implements ICardBodyAfterEditEventListener {

  // 远程调用信息 by guoyk 2014年9月17日
  private Map<String, IPURemoteCallCombinator> remoteCaller =
      new HashMap<String, IPURemoteCallCombinator>();

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    CardEditorHelper helper = new CardEditorHelper(event.getBillCardPanel());

    // 设置收货仓库的默认值
    WarehouseDefaultValue addr = new WarehouseDefaultValue(helper);
    addr.setWarehouseDefaultValue(event.getRow());

    // 获取编辑行 by guoyk 2014年9月17日
    int[] rows = new int[1];
    rows[0] = event.getRow();
    // 注册远程调用 by guoyk 2014年9月17日
    this.registerRemoteCall(helper, rows);
    // 设置收货利润中心 by guoyk 2014年9月17日
    this.processDefaultArrliabCenterValue(event);

    // 执行公式，设置表体收货地址的值
    event.getBillCardPanel().getBillModel()
        .execLoadFormulaByRow(event.getRow());
  }

  // 设置收货利润中心 by ZJ 2014年10月26日
  private void processDefaultArrliabCenterValue(CardBodyAfterEditEvent event) {
    BillCardPanel panel = event.getBillCardPanel();
    // 物料的多选处理

    CardEditorHelper card = new CardEditorHelper(panel);

    int row = event.getRow();

    String pk_stordoc =
        (String) card.getBodyValue(row, OrderItemVO.PK_RECVSTORDOC);
    String[] fields = new String[] {
      StordocVO.PROFITCENTRE
    };
    // 如果清空收货仓库，收货利润中心不变
    if (pk_stordoc == null) {
      return;
    }
    StordocVO[] stcrdocs = StordocPubService.queryStordocByPks(new String[] {
      pk_stordoc
    }, fields);
    String pk_apliabcenter =
        (String) stcrdocs[0].getAttributeValue(StordocVO.PROFITCENTRE);
    
    // 如果收货仓库对应的利润中心为空，收货利润中心不变
    if (pk_apliabcenter == null) {
      return;
    }
    // 如果收货仓库对应的利润中心不为空，且收货利润中心为空，则将利润中心赋值
    if(card.getBodyStringValue(row, OrderItemVO.PK_ARRLIABCENTER) == null){
      card.setBodyValue(row, OrderItemVO.PK_ARRLIABCENTER, pk_apliabcenter);
    }
    // 根据PK_ORG 查找最新版本，设置值
    try {
      String[] strs = new String[] {
        pk_apliabcenter
      };
      Map<String, String> mapvid =
          NCLocator.getInstance().lookup(IOrgUnitPubService_C.class)
              .getNewVIDSByOrgIDS(strs);
      String newid = mapvid.get(pk_apliabcenter);
      card.setBodyValue(row, OrderItemVO.PK_ARRLIABCENTER_V, newid);
    }
    catch (BusinessException ex) {
      ExceptionUtils.wrappException(ex);
    }

  }

  // 设置收货利润中心 by guoyk 2014年9月16日
  // private void processDefaultArrliabCenterValue(int[] rows) {
  // OrganizationDefaultValue odv = (OrganizationDefaultValue) this.remoteCaller
  // .get(OrganizationDefaultValue.class.getName());
  // odv.setDefaultArrliabCenterValue(rows);
  // }
  //

  // 远程调用注册 by guoyk 2014年9月17日
  private void registerRemoteCall(CardEditorHelper card, int[] rows) {
    // 根据仓库和收货库存组织设置收货利润中心 by guoyk 2014年9月17日
    OrganizationDefaultValue odv = new OrganizationDefaultValue(card);
    odv.registerCombineRemoteCallForArrCenter(rows);
    this.remoteCaller.put(OrganizationDefaultValue.class.getName(), odv);
  }

}
