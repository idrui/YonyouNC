package nc.ui.pu.m21.editor.card.afteredit.header;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pu.m21.editor.card.afteredit.PriceQuoterUtil;
import nc.ui.pu.m21.rule.ContractLinker;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.enumeration.PriceParam;
import nc.vo.scmpub.res.billtype.CTBillType;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;

/**
 * 特殊关联合同维度。
 * 当物料采购页签勾选了"严格按生产厂商询价"、"严格按质量等级询价"、"严格按收货地区询价"、"严格按运输方式询价"时，
 * 采购订单的上述字段也作为匹配合同的维度，在编辑后事件中触发匹配合同。
 * 此类仅处理运输方式，其余3个维度在表体。
 * 
 * @since 6.3
 * @version 2013-1-30 上午08:39:58
 * @author lixyp
 */
public class SpecContractLinkDim implements ICardHeadTailAfterEditEventListener {

  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {
  	event.getBillCardPanel().getBodyTabbedPane().setSelectedIndex(0);
    CardEditorHelper editor = new CardEditorHelper(event.getBillCardPanel());
    int[] rows = new int[editor.getItemCount()];
    for (int i = 0; i < editor.getItemCount(); ++i) {
      rows[i] = i;
    }
    ContractLinker contractLinker = new ContractLinker(event);
    Integer[] ctparams =
        this.getContractlinkParams(event.getBillCardPanel(), rows);
    contractLinker.contractLink(ctparams, false, true);
    // mengjian 根据参数PO16自动询价条件自动询价
    this.setDefaultPrice(event, rows);
  }

  /**
   * 获取关联合同的行
   * 
   * @param panel 卡片面板
   * @param rows 所有的表体行
   * @return 需要关联合同的行
   */
  private Integer[] getContractlinkParams(BillCardPanel panel, int[] rows) {
    List<Integer> notCntIndex = new ArrayList<Integer>();
    for (int row : rows) {
      String sourcetype =
          (String) panel.getBodyValueAt(row, OrderItemVO.CSOURCETYPECODE);
      if (!ObjectUtils.equals(CTBillType.PurDaily.getCode(), sourcetype)) {
        panel.setBodyValueAt(null, row, OrderItemVO.CCONTRACTID);
        panel.setBodyValueAt(null, row, OrderItemVO.CCONTRACTROWID);
        panel.setBodyValueAt(null, row, OrderItemVO.VCONTRACTCODE);
        notCntIndex.add(Integer.valueOf(row));
      }
    }
    if (CollectionUtils.isEmpty(notCntIndex)) {
      return null;
    }
    return notCntIndex.toArray(new Integer[notCntIndex.size()]);
  }

  /**
   * 自动询价
   * mengjian
   * 
   * @param event
   * @param rows
   */
  @SuppressWarnings("restriction")
  private void setDefaultPrice(CardHeadTailAfterEditEvent event, int[] rows) {
    PriceQuoterUtil priceQuoterUtil = new PriceQuoterUtil();
    priceQuoterUtil.setDefaultPrice(event.getBillCardPanel(),
        PriceParam.Vehicletype, rows);
  }
}
