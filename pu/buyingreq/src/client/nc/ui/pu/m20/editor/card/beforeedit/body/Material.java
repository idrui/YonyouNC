package nc.ui.pu.m20.editor.card.beforeedit.body;

import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.scmpub.ref.FilterMaterialRefUtils;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.pub.enumeration.PuRefBillTypeIdEnum;
import nc.vo.pub.lang.UFBoolean;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>物料编辑前事件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-4 下午06:16:10
 */
public class Material implements ICardBodyBeforeEditEventListener {

  @Override
  public void beforeEdit(CardBodyBeforeEditEvent event) {
    BillCardPanel billCardPanel = event.getBillCardPanel();
    String csourceTypeCode =
        (String) billCardPanel.getBodyValueAt(event.getRow(),
            StoreReqAppItemVO.CSOURCETYPECODE);
    // 来源于资产配置申请(1001Z91000000001U0LZ)/维修计划(1001Z900000000002213)的请购单，物料不让编辑
    if (PuRefBillTypeIdEnum.M4A08.getBillTypeId().equals(csourceTypeCode)
        || PuRefBillTypeIdEnum.M4B32.getBillTypeId().equals(csourceTypeCode)) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }

    UIRefPane panel =
        (UIRefPane) event.getBillCardPanel()
            .getBodyItem(PraybillItemVO.PK_MATERIAL).getComponent();
    panel.setMultiSelectedEnabled(true);

    // 参照的范围为请购单表头库存组织可见的物料
    String org = event.getContext().getPk_org();
    if (null != org) {
      FilterMaterialRefUtils filter =
          new FilterMaterialRefUtils((UIRefPane) event.getBillCardPanel()
              .getBodyItem(PraybillItemVO.PK_MATERIAL).getComponent());

      filter.filterItemRefByOrg(org);
      filter.filterIsSealedShow(UFBoolean.FALSE);
      // 2012-8-10 王印芬、孙聪、田锋涛、吴小亮，请购单上过滤了折扣，但未过滤劳务，劳务是应该支持的。
      // 请购单的主组织是库存组织，指的是需求方。工厂也需要维修啊，也需要服务，可以申请。
      // filter.filterRefByFeeflag(UFBoolean.FALSE);
      filter.filterRefByDiscountflag(UFBoolean.FALSE);
    }

    String sc =
        (String) event.getBillCardPanel().getBodyValueAt(event.getRow(),
            PraybillItemVO.CSOURCETYPECODE);
    if (!StringUtil.isEmptyWithTrim(sc)) {
      event.setReturnValue(Boolean.FALSE);
      return;
    }

    event.setReturnValue(Boolean.TRUE);
  }
}
