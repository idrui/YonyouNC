/**
 * $文件说明$
 *
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-4 上午10:15:44
 */
package nc.ui.pu.m20.editor.card.afteredit.header;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.scmpub.reference.uap.pf.PfBillItfDefUtil;
import nc.itf.scmpub.reference.uap.pf.TransTypeMapping;
import nc.pubitf.scmf.pu.ordertranstype.pu.IQueryOrdertranstypeForPu;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.scmpub.ref.FilterTransTypeRefUtils;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 是否委外编辑后事件
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-2-4 上午10:15:44
 */
public class Bsctype implements ICardHeadTailAfterEditEventListener {

  @Override
  public void afterEdit(CardHeadTailAfterEditEvent event) {

    if (!this.canSelectBsctype(event)) {
      event.getBillCardPanel().getHeadItem(PraybillHeaderVO.BSCTYPE)
          .setValue(UFBoolean.FALSE);
      ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004020_0", "04004020-0020")/*
                                                                   * @res
                                                                   * "直运和委外互斥，不能同时选择。"
                                                                   */, event
          .getContext());
      return;
    }

    // 设置订单类型
    this.setTrantype(event.getBillCardPanel(),
        event.getContext().getPk_group(), event.getContext().getPk_org());

  }

  private boolean canSelectBsctype(CardHeadTailAfterEditEvent event) {
    Boolean bdirecttransit =
        (Boolean) event.getBillCardPanel()
            .getHeadItem(PraybillHeaderVO.BDIRECTTRANSIT).getValueObject();
    if (null != bdirecttransit && bdirecttransit.booleanValue()) {
      return false;
    }
    return true;
  }

  /**
   * 根据请购单表体的物料或者对应的物料分类+采购组织匹配物料订单类型设置中找到相应的采购订单交易类型，其中匹配时物料及物料分类按照明细优先的规则进行
   * <p>
   * <b>参数说明</b>
   * 
   * @param pk_group
   * @param card
   * @return <p>
   * @since 6.0
   * @author GGR
   * @time 2010-2-4 上午10:30:27
   */
  private Map<String, String> getTranstype(String pk_group, BillCardPanel card) {

    // 取得每行的物料PK和采购组织PK，并过滤重复数据
    HashSet<String> pks = new HashSet<String>(); // 过滤重复数据辅助
    ArrayList<String> pk_org = new ArrayList<String>();
    ArrayList<String> pk_srcmaterial = new ArrayList<String>();
    ArrayList<String> stockorgs = new ArrayList<String>();
    String stockorg =
        (String) card.getHeadItem(PraybillHeaderVO.PK_ORG).getValueObject();
    for (int i = 0, len = card.getRowCount(); i < len; i++) {
      Object opk_org = card.getBodyValueAt(i, PraybillItemVO.PK_PURCHASEORG);
      Object opk_srcmaterial =
          card.getBodyValueAt(i, PraybillItemVO.PK_SRCMATERIAL);
      if (null != opk_srcmaterial && null != opk_org) {
        if (!pks.contains(opk_org.toString() + opk_srcmaterial.toString())) {
          pks.add(opk_org.toString() + opk_srcmaterial.toString());
          pk_org.add(opk_org.toString());
          pk_srcmaterial.add(opk_srcmaterial.toString());
          stockorgs.add(stockorg);
        }
      }
    }

    if (pks.size() == 0) {
      return null;
    }

    String[] pk_orgs = pk_org.toArray(new String[pk_org.size()]);
    String[] pk_srcmaterials =
        pk_srcmaterial.toArray(new String[pk_srcmaterial.size()]);

    IQueryOrdertranstypeForPu service =
        NCLocator.getInstance().lookup(IQueryOrdertranstypeForPu.class);
    Map<String, String> map =
        service.getTranstypeIdByOrg(pk_group, pk_orgs, pk_srcmaterials,
            stockorgs.toArray(new String[stockorgs.size()]));

    return map;
  }

  /**
   * 匹配上下游单据接口关系定义。
   * <p>
   * <b>参数说明</b>
   * 
   * @param billtype
   *          下游单据类型
   * @param pk_group
   *          集团
   * @param card
   *          <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-14 上午09:59:55
   */
  private void setPFTranstype(String billtype, String pk_group,
      BillCardPanel card) {

    Object prayType =
        card.getHeadItem(PraybillHeaderVO.VTRANTYPECODE).getValueObject();

    if (null == prayType) {
      return;
    }

    TransTypeMapping mapping = new TransTypeMapping();
    mapping.setSrcBillType(POBillType.PrayBill.getCode());
    mapping.setSrcTransType(prayType.toString());
    mapping.setDestBillType(billtype);

    // 取得订单类型
    PfBillItfDefUtil.queryTransTypeMapping(pk_group, mapping);

    String orderType = mapping.getDestTransType();
    if (StringUtil.isEmptyWithTrim(orderType)) {
      return;
    }

    // 循环设置表体订单类型
    if (POBillType.Order.getCode().equals(billtype)) {
      for (int i = 0, len = card.getRowCount(); i < len; i++) {
        if (null == card.getBodyValueAt(i, PraybillItemVO.CORDERTRANTYPECODE)
            && null != card.getBodyValueAt(i, PraybillItemVO.PK_PURCHASEORG)) {
          card.setBodyValueAt(orderType, i, PraybillItemVO.CORDERTRANTYPECODE);
        }
      }
    }
    else {
      for (int i = 0, len = card.getRowCount(); i < len; i++) {
        card.setBodyValueAt(orderType, i, PraybillItemVO.CORDERTRANTYPECODE);
      }
    }
  }

  private void setTrantype(BillCardPanel card, String pk_group, String pk_org) {
    Boolean bsctype =
        (Boolean) card.getHeadItem(PraybillHeaderVO.BSCTYPE).getValueObject();
    FilterTransTypeRefUtils filter =
        new FilterTransTypeRefUtils(
            card.getBodyItem(PraybillItemVO.CORDERTRANTYPECODE), pk_org);

    String billtype;
    if (null != bsctype && bsctype.booleanValue()) {
      for (int i = 0, len = card.getRowCount(); i < len; i++) {
        card.setBodyValueAt(null, i, PraybillItemVO.CORDERTRANTYPECODE);
      }
      // 设置订单类型只能参照委外订单的交易类型
      billtype = "61";
      filter.filterTranType(new String[] {
        billtype
      });

    }
    else {
      // 设置订单交易类型参照过滤
      billtype = POBillType.Order.getCode();
      filter.filterTranType(new String[] {
        billtype
      });

      // 匹配物料订单类型设置
      Map<String, String> transtypes = this.getTranstype(pk_group, card);
      String pk_stockorg =
          (String) card.getHeadItem(PraybillHeaderVO.PK_ORG).getValueObject();
      if (null != transtypes) {
        for (int i = 0, len = card.getRowCount(); i < len; i++) {
          card.setBodyValueAt(null, i, PraybillItemVO.CORDERTRANTYPECODE);
          Object opk_org =
              card.getBodyValueAt(i, PraybillItemVO.PK_PURCHASEORG);
          Object opk_material =
              card.getBodyValueAt(i, PraybillItemVO.PK_MATERIAL);
          if (null != opk_material && null != opk_org) {
            String transtypecode =
                transtypes.get(opk_org.toString() + pk_stockorg
                    + opk_material.toString());
            if (!StringUtil.isEmptyWithTrim(transtypecode)) {
              card.setBodyValueAt(transtypecode, i,
                  PraybillItemVO.CORDERTRANTYPECODE);
            }

          }

        }

      }

    }
    // 匹配上下游单据接口关系定义
    this.setPFTranstype(billtype, pk_group, card);
  }
}
