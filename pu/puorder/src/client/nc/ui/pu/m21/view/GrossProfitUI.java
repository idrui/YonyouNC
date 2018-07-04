/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-23 下午05:03:11
 */
package nc.ui.pu.m21.view;

import java.awt.Container;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.itf.org.IOrgConst;
import nc.itf.scmpub.reference.uap.bd.material.MaterialSalePubService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.pu.m21.entity.GroProHeaderVO;
import nc.vo.pu.m21.entity.GroProItemVO;
import nc.vo.pu.m21.entity.GroProVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.pubapp.scale.ScaleUtils;

/**
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
 * @author wuxla
 * @time 2010-10-23 下午05:03:11
 */
public class GrossProfitUI extends UIDialog {

  private static final long serialVersionUID = 3883540183319827375L;

  private GroProVO groProVO;

  private OrderVO orderVO;

  private BillCardPanel panel;

  private ScaleUtils scale = new ScaleUtils(InvocationInfoProxy.getInstance()
      .getGroupId());

  public GrossProfitUI(Container parent) {
    super(parent);
  }

  public GrossProfitUI(Container parent, OrderVO orderVO) {
    super(parent);
    this.orderVO = orderVO;
    this.initialize();
  }

  public GrossProfitUI(Container parent, String title) {
    super(parent, title);
  }

  public GrossProfitUI(Container parent, String title, OrderVO orderVO) {
    super(parent, title);
    this.orderVO = orderVO;
    this.initialize();
  }
  public GrossProfitUI(Container parent, String title, OrderVO orderVO,boolean reset) {
	    super(parent, title,reset);
	    this.orderVO = orderVO;
	    this.setResizable(true);
	    this.initialize();
 }
  public GrossProfitUI(Frame owner) {
    super(owner);
  }

  public GrossProfitUI(Frame owner, String title) {
    super(owner, title);
  }

  private void getGroProVO() {
    GroProHeaderVO headerVO = this.getHeadVO();
    GroProItemVO[] itemVOs = this.getItemVOs();
    this.groProVO = new GroProVO();
    this.groProVO.setParentVO(headerVO);
    this.groProVO.setChildrenVO(itemVOs);
  }

  private GroProHeaderVO getHeadVO() {
    GroProHeaderVO headerVO = new GroProHeaderVO();
    OrderHeaderVO orderHeaderVO = this.orderVO.getHVO();
    headerVO.setCorigcurrencyid(orderHeaderVO.getCorigcurrencyid());
    headerVO.setDbilldate(orderHeaderVO.getDbilldate());
    headerVO.setPk_order(orderHeaderVO.getPk_order());
    headerVO.setVbillcode(orderHeaderVO.getVbillcode());
    return headerVO;
  }

  private GroProItemVO getItemVOByOrder(OrderItemVO orderItemVO,
      Map<String, UFDouble> priceMap) {
    // String corigcurrencyid = this.orderVO.getHVO().getCorigcurrencyid();

    String currencyid = orderItemVO.getCcurrencyid();
    GroProItemVO itemVO = new GroProItemVO();
    itemVO.setCunitid(orderItemVO.getCunitid());
    itemVO.setNitemdiscountrate(orderItemVO.getNitemdiscountrate());
    itemVO.setNnum(this.scale.adjustNumScale(orderItemVO.getNnum(),
        orderItemVO.getCunitid()));

    // TODO 字段以后调整吧，目前先返回本币，GroProItemVO字段仍是原币，但是含义是本币
    itemVO.setNorigmny(this.scale.adjustMnyScale(orderItemVO.getNmny(),
        currencyid));
    itemVO.setNorignetprice(this.scale.adjustSoPuPriceScale(
        orderItemVO.getNnetprice(), orderItemVO.getCcurrencyid()));
    itemVO.setNorigprice(this.scale.adjustSoPuPriceScale(
        orderItemVO.getNprice(), orderItemVO.getCcurrencyid()));
    itemVO.setPk_material(orderItemVO.getPk_material());
    itemVO.setPk_order(orderItemVO.getPk_order());
    itemVO.setPk_order_b(orderItemVO.getPk_order_b());

    // 币种为空，设置销售数值为null
    if (null == currencyid || null == priceMap) {
      return itemVO;
    }

    UFDouble salePrice = priceMap.get(orderItemVO.getPk_material());
    if (null == salePrice || salePrice.equals(UFDouble.ZERO_DBL)) {
      return itemVO;
    }
    salePrice =
        this.scale
            .adjustSoPuPriceScale(salePrice, orderItemVO.getCcurrencyid());
    itemVO.setNsaleprice(salePrice);
    // 销售金额
    UFDouble nsalemny = null;
    if (orderItemVO.getNnum() != null) {
      nsalemny =
          this.scale.adjustMnyScale(salePrice.multiply(orderItemVO.getNnum()),
              currencyid);
      itemVO.setNsalemny(nsalemny);
    }
    // 毛利额 = 销售金额 - 订单金额
    UFDouble ngrossprofit = null;
    if (nsalemny != null && orderItemVO.getNmny() != null) {
      ngrossprofit =
          this.scale.adjustMnyScale(nsalemny.sub(orderItemVO.getNmny()),
              currencyid);
      itemVO.setNgrossprofit(ngrossprofit);
    }
    // 毛利率 = 毛利额/销售金额 * 100
    UFDouble ngrossprofitrate = null;
    if (nsalemny != null && ngrossprofit != null) {
      ngrossprofitrate = ngrossprofit.div(nsalemny).multiply(new UFDouble(100));
      ngrossprofitrate = ScaleUtils.adjustScale(ngrossprofitrate, 2);
      itemVO.setNgrossprofitrate(ngrossprofitrate);
    }
    return itemVO;
  }

  private GroProItemVO[] getItemVOs() {

    OrderItemVO[] orderItemVOs = this.orderVO.getBVO();

    MapList<String, OrderItemVO> psfinanceorgItemMapList =
        new MapList<String, OrderItemVO>();
    for (OrderItemVO orderItemVO : orderItemVOs) {
      psfinanceorgItemMapList
          .put(orderItemVO.getPk_psfinanceorg(), orderItemVO);
    }

    List<GroProItemVO> groProItemList = new ArrayList<GroProItemVO>();
    for (Entry<String, List<OrderItemVO>> entry : psfinanceorgItemMapList
        .entrySet()) {
      String psfinanceorg = entry.getKey();
      List<OrderItemVO> orderitemList = entry.getValue();
      Map<String, UFDouble> priceMap =
          this.getPriceMap(psfinanceorg, orderitemList);
      for (OrderItemVO orderItemVO : orderitemList) {
        GroProItemVO groproItemVO =
            this.getItemVOByOrder(orderItemVO, priceMap);
        groProItemList.add(groproItemVO);
      }
    }

    return groProItemList.toArray(new GroProItemVO[groProItemList.size()]);
    // GroProItemVO[] itemVOs = new GroProItemVO[orderItemVOs.length];
    // for (int i = 0; i < orderItemVOs.length; ++i) {
    // itemVOs[i] = this.getItemVOByOrder(orderItemVOs[i], priceMap);
    // }
    // return itemVOs;
  }

  /**
   * 直接取物料档案销售组织页签的参考售价，其中采购组织同时勾选了销售组织时，则取该参考售价，如果采购组织未勾选销售组织则返回空值
   * 
   * @return
   */
  private Map<String, UFDouble> getPriceMap(String psfinanceorg,
      List<OrderItemVO> itemVOs) {
    if (!OrgUnitPubService.isTypeOf(psfinanceorg, IOrgConst.SALEORGTYPE)) {
      return null;
    }
    List<String> pkList = new ArrayList<String>();
    for (OrderItemVO itemVO : itemVOs) {
      if (itemVO.getPk_material() != null) {
        pkList.add(itemVO.getPk_material());
      }
    }
    if (0 == pkList.size()) {
      return null;
    }
    return MaterialSalePubService.queryResaleprice(
        pkList.toArray(new String[pkList.size()]), psfinanceorg);
  }

  private void initialize() {
    this.setSize(800, 600);
    this.getGroProVO();
    this.panel = new BillCardPanel();
    // pub_billtemplet pk_billtypecode=40040420
    // pk_billtemplet=1002Z810000000015QBI
    this.panel.loadTemplet("1002Z810000000015QBI");
    this.panel.setEnabled(false);
    this.panel.setBillValueVO(this.groProVO);

    // TODO 代码控制编辑性
    this.panel.hideHeadItem(new String[] {
      "corigcurrencyid"
    });

    this.add(this.panel);

    this.panel.getBillModel().loadLoadRelationItemValue();
  }

}
