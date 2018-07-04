package nc.ui.pu.m21.rule;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderOnwayHeaderVO;
import nc.vo.pu.m21.entity.OrderOnwayItemVO;
import nc.vo.pubapp.scale.BillScaleProcessor;
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
public class OnwayScaleSetter {
  private String pk_group;

  public OnwayScaleSetter(String pk_group) {
    this.pk_group = pk_group;
  }

  /**
   * 方法功能描述：设置卡片界面精度
   * <p>
   * <b>参数说明</b>
   * 
   * @param panel
   *          卡片
   *          <p>
   * @since 6.0
   * @author wanghuid
   * @time 2010-8-19 下午03:35:01
   */
  public void setCardScale(BillCardPanel panel) {
    this.setBodyScale(new CardPaneScaleProcessor(this.pk_group, panel));
  }

  private void setBodyScale(BillScaleProcessor scale) {

    // // 本币金额
    // String[] mnykeys = new String[] {
    // OrderOnwayItemVO.NONWAYMNY, OrderOnwayItemVO.NSTATUSMNY
    // };
    // 原币金额
    String[] orgmnykeys = new String[] {
      OrderOnwayItemVO.NONWAYMNY, OrderOnwayItemVO.NSTATUSMNY
    };
    // 主数量
    String[] numkeys =
        new String[] {
          OrderOnwayItemVO.NACCUNUM, OrderOnwayItemVO.NNUM,
          OrderOnwayItemVO.NONWAYNUM, OrderOnwayItemVO.NSENDOUTNUM
        };
    // 价格
    String[] pricekeys = new String[] {
      OrderItemVO.NORIGNETPRICE
    };

    // 主单位数量精度
    scale.setNumCtlInfo(numkeys, PosEnum.body, null, OrderOnwayItemVO.CUNITID,
        PosEnum.body, null);
    // 本币金额精度
    // scale.setMnyCtlInfo(mnykeys, PosEnum.body, null,
    // OrderOnwayItemVO.CCURRENCYID, PosEnum.body, null);
    // 原币金额精度
    scale.setMnyCtlInfo(orgmnykeys, PosEnum.body, null,
        OrderOnwayHeaderVO.CORIGCURRENCYID, PosEnum.head, null);
    // 单价精度
    scale.setPriceCtlInfo(pricekeys, PosEnum.body, null,
        OrderOnwayHeaderVO.CORIGCURRENCYID, PosEnum.head, null);
    // 进行计算
    scale.process();
  }
}
