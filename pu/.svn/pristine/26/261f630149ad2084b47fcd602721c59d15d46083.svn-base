package nc.ui.pu.m21.editor.card.afteredit.body;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pu.m21.editor.card.afteredit.PriceQuoterUtil;
import nc.ui.pu.m21.rule.ContractLinker;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.enumeration.PriceParam;
import nc.vo.scmpub.res.billtype.CTBillType;
import nc.vo.scmpub.util.ArrayUtil;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ObjectUtils;

/**
 * 特殊关联合同维度。
 * 当物料采购页签勾选了"严格按生产厂商询价"、"严格按质量等级询价"、"严格按收货地区询价"、"严格按运输方式询价"时，
 * 采购订单的上述字段也作为匹配合同的维度，在编辑后事件中触发匹配合同。
 * 此类仅处理生产厂商、质量等级和收货地区。运输方式在表头
 * 
 * @since 6.3
 * @version 2013-1-30 上午08:39:58
 * @author lixyp
 */
public class SpecContractLinkDim implements ICardBodyAfterEditEventListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    ContractLinker contractLinker = new ContractLinker(event);
    Integer[] ctparams =
        this.getContractlinkParams(event.getBillCardPanel(), event.getRow());
    if (!ArrayUtil.isEmpty(ctparams)) {
      contractLinker.contractLink(ctparams, false, true);
    }
    // mengjian 根据参数PO16自动询价条件自动询价
    this.setDefaultPrice(event);
  }

  /**
   * 获取需要关联合同的行
   * 
   * @param panel 卡片面板
   * @param rows 当前行
   * @return 如果当前行需要关联合同则返回，否则返回空。
   */
  private Integer[] getContractlinkParams(BillCardPanel panel, int row) {
    List<Integer> notCntIndex = new ArrayList<Integer>();

    String sourcetype =
        (String) panel.getBodyValueAt(row, OrderItemVO.CSOURCETYPECODE);
    if (!ObjectUtils.equals(CTBillType.PurDaily.getCode(), sourcetype)) {
      panel.setBodyValueAt(null, row, OrderItemVO.CCONTRACTID);
      panel.setBodyValueAt(null, row, OrderItemVO.CCONTRACTROWID);
      panel.setBodyValueAt(null, row, OrderItemVO.VCONTRACTCODE);
      notCntIndex.add(Integer.valueOf(row));
    }

    if (CollectionUtils.isEmpty(notCntIndex)) {
      return null;
    }
    return notCntIndex.toArray(new Integer[notCntIndex.size()]);
  }

  /**
   * 根据参数PO16自动询价条件自动询价
   * mengjian
   * 
   * @param event
   */
  @SuppressWarnings("restriction")
  private void setDefaultPrice(CardBodyAfterEditEvent event) {
    PriceParam pricceParam = PriceParam.ReceiveArea;
    String key = event.getKey();
    if (OrderItemVO.CDEVAREAID.equals(key)) {
      // 收货地区
      pricceParam = PriceParam.ReceiveArea;
    }
    else if (OrderItemVO.CQUALITYLEVELID.equals(key)) {
      // 质量等级
      pricceParam = PriceParam.Qualitylevel;
    }
    else if (OrderItemVO.CPRODUCTORID.equals(key)) {
      // 生产厂商
      pricceParam = PriceParam.Productor;
    }
    PriceQuoterUtil priceQuoterUtil = new PriceQuoterUtil();
    priceQuoterUtil.setDefaultPrice(event.getBillCardPanel(), pricceParam,
        new int[] {
          event.getRow()
        });
  }
}
