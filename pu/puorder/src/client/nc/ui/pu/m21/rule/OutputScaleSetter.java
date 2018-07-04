package nc.ui.pu.m21.rule;

import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.IBillItem;
import nc.ui.pubapp.scale.ListPaneScaleProcessor;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.FieldInfo;
import nc.vo.pubapp.scale.PosEnum;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>设置卡片精度
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-8-19 下午03:35:01
 */
public class OutputScaleSetter {
  private String pk_group;

  public OutputScaleSetter(String pk_group) {
    this.pk_group = pk_group;
  }

  /**
   * 方法功能描述：设置列表界面精度
   * <p>
   * <b>参数说明</b>
   * 
   * @param panel
   *          列表
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-5-17 下午03:35:17
   */
  public void setListScale(BillListPanel panel) {
    this.setBodyScale(new ListPaneScaleProcessor(this.pk_group, panel));
  }

  private void setBodyScale(BillScaleProcessor scale) {

    // 原币金额
    String[] orgmnykeys = new String[] {
      OrderItemVO.NORIGMNY, OrderItemVO.NORIGTAXMNY
    };
    // 主数量
    String[] numkeys = new String[] {
      OrderItemVO.NNUM
    };
    // 价格
    String[] pricekeys = new String[] {
      OrderItemVO.NORIGNETPRICE
    };
    // 表体税率
    String[] taxRateKey_B = new String[] {
      OrderItemVO.NTAXRATE, OrderItemVO.NNOSUBTAXRATE
    };

    // 主单位数量精度
    scale.setNumCtlInfo(numkeys, PosEnum.body, null, OrderItemVO.CUNITID,
        PosEnum.body, null);

    // 原币金额精度
    scale.setMnyCtlInfo(orgmnykeys, PosEnum.body, null,
        OrderItemVO.CORIGCURRENCYID, PosEnum.head, null);

    // 单价精度
    scale.setPriceCtlInfo(pricekeys, PosEnum.body, null,
        OrderItemVO.CORIGCURRENCYID, PosEnum.head, null);

    // 税率
    scale.setTaxRateCtlInfo(taxRateKey_B, PosEnum.body, null);

    // 进行计算
    scale.process();
    // 汇率精度
    this.setOrgExchange(scale, IBillItem.BODY);
  }

  private void setOrgExchange(BillScaleProcessor scale, int pos) {
    FieldInfo rate = new FieldInfo(OrderItemVO.NEXCHANGERATE, pos, null);
    FieldInfo srcCurr =
        new FieldInfo(OrderHeaderVO.CORIGCURRENCYID, IBillItem.HEAD, null);
    FieldInfo destCurr = new FieldInfo(OrderItemVO.CCURRENCYID, pos, null);
    FieldInfo org = new FieldInfo(OrderItemVO.PK_PSFINANCEORG, pos, null);
    scale.setOrgExchangeCtlInfo(rate, srcCurr, destCurr, org);
  }
}
