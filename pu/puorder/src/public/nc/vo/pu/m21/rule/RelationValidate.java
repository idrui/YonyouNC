/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-5 下午06:36:27
 */
package nc.vo.pu.m21.rule;

import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.BillIndex;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>关系检查
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-5 下午06:36:27
 */
public class RelationValidate {

  private BillIndex index;

  // private Map<String, OrderItemVO> map;

  private IVOMeta meta;

  private ScaleUtils util = ScaleUtils.getScaleUtilAtBS();

  public RelationValidate(OrderVO vo) {
    OrderVO orderVO = (OrderVO) vo.clone();

    RelationCalculate calc = new RelationCalculate();
    calc.calculate(orderVO, OrderItemVO.NORIGTAXMNY);
    // this.map = AggVOUtil.createItemMap(new OrderVO[] {
    // orderVO
    // });
    this.index = new BillIndex(new OrderVO[] {
      orderVO
    });
    this.meta = orderVO.getMetaData().getVOMeta(OrderItemVO.class);
  }

  public void relationValidate(OrderItemVO itemVO) {
    // 主数量、数量、换算率关系
    this.checkAssistRelation(itemVO);
    // 主数量、报价数量、报价换算率关系
    this.checkQtAssistRelation(itemVO);
    // 主数量、单价、金额关系
    // this.checkNumPriceMoneyRelation(itemVO, this.map
    // .get(itemVO.getPk_order_b()));
    this.checkNumPriceMoneyRelation(itemVO,
        (OrderItemVO) this.index.get(this.meta, itemVO.getPk_order_b()));
    // 报价数量、单价、金额关系
    // this.checkQtNumPriceMoneyRelation(itemVO, this.map.get(itemVO
    // .getPk_order_b()));
    this.checkQtNumPriceMoneyRelation(itemVO,
        (OrderItemVO) this.index.get(this.meta, itemVO.getPk_order_b()));
  }

  /**
   * 方法功能描述：主数量、数量、换算率关系
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-3 上午10:12:47
   */
  private void checkAssistRelation(OrderItemVO itemVO) {
    UFDouble nnum = itemVO.getNnum();
    UFDouble nastnum = itemVO.getNastnum();
    String vchangerate = itemVO.getVchangerate();
    String cunitid = itemVO.getCunitid();
    String castunitid = itemVO.getCastunitid();

    if (nastnum != null && vchangerate != null) {
      UFDouble newnum =
          this.util.adjustNumScale(
              HslParseUtil.hslMultiplyUFDouble(vchangerate, nastnum), cunitid);
      UFDouble newastnum =
          this.util.adjustNumScale(
              HslParseUtil.hslDivUFDouble(vchangerate, nnum), castunitid);
      String newHsl =
          this.util.adjustHslScale(HslParseUtil.buildHslString(nnum, nastnum));
      if (!(newnum.equals(nnum) || newastnum.equals(nastnum) || newHsl
          .equals(vchangerate))) {
        ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
            .getStrByID(
                "4004030_0",
                "04004030-0327",
                null,
                new String[] {
                  itemVO.getCrowno(), String.valueOf(nnum),
                  String.valueOf(nastnum), vchangerate
                })/* 第{0}行主数量、数量、换算率关系不正确-主数量：{1}，数量：{2}，换算率：{3} */);
      }
    }
  }

  /**
   * 方法功能描述：主数量、单价、金额关系
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-3 上午10:16:19
   */
  private void checkNumPriceMoneyRelation(OrderItemVO itemVO,
      OrderItemVO itemVOCal) {
    UFDouble norigtaxmny = itemVO.getNorigtaxmny();
    UFDouble norigtaxmnyCal = itemVOCal.getNorigtaxmny();

    UFDouble norigtaxprice = itemVO.getNorigtaxprice();
    UFDouble norigtaxpriceCal = itemVOCal.getNorigtaxprice();

    UFDouble nnum = itemVO.getNnum();
    UFDouble nnumCal = itemVO.getNnum();

    if (!(MathTool.equals(norigtaxprice, norigtaxpriceCal)
        || MathTool.equals(norigtaxmny, norigtaxmnyCal) || MathTool.equals(
        nnum, nnumCal))) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID(
              "4004030_0",
              "04004030-0328",
              null,
              new String[] {
                itemVO.getCrowno(), String.valueOf(nnum),
                String.valueOf(norigtaxprice), String.valueOf(norigtaxmny)
              })/* 第{0}行主数量、主含税单价、金额关系不正确-主数量：{1}，主含税单价：{2}，价税合计：{3} */);
    }
  }

  /**
   * 方法功能描述：主数量、报价数量、报价换算率关系
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-3 上午10:13:33
   */
  private void checkQtAssistRelation(OrderItemVO itemVO) {
    UFDouble nnum = itemVO.getNnum();
    UFDouble nqtunitnum = itemVO.getNqtunitnum();
    String vqtunitrate = itemVO.getVqtunitrate();
    String cunitid = itemVO.getCunitid();
    String cqtunitid = itemVO.getCqtunitid();

    if (nqtunitnum != null && vqtunitrate != null) {
      UFDouble newnum =
          this.util.adjustNumScale(
              HslParseUtil.hslMultiplyUFDouble(vqtunitrate, nqtunitnum),
              cunitid);
      UFDouble newastnum =
          this.util.adjustNumScale(
              HslParseUtil.hslDivUFDouble(vqtunitrate, nnum), cqtunitid);
      String newHsl =
          this.util.adjustHslScale(HslParseUtil
              .buildHslString(nnum, nqtunitnum));
      if (!(newnum.equals(nnum) || newastnum.equals(nqtunitnum) || newHsl
          .equals(vqtunitrate))) {
        ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
            .getStrByID(
                "4004030_0",
                "04004030-0329",
                null,
                new String[] {
                  itemVO.getCrowno(), String.valueOf(nnum),
                  String.valueOf(nqtunitnum), vqtunitrate
                })/* 第{0}行主数量、报价数量、换算率关系不正确-主数量：{1}，数量：{2}，换算率：{3} */);
      }
    }
  }

  /**
   * 方法功能描述：报价数量、单价、金额关系
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-3 上午10:16:41
   */
  private void checkQtNumPriceMoneyRelation(OrderItemVO itemVO,
      OrderItemVO itemVOCal) {
    UFDouble nqtorigtaxprice = itemVO.getNqtorigtaxprice();
    UFDouble nqtorigtaxpriceCal = itemVOCal.getNqtorigtaxprice();
    UFDouble norigtaxmny = itemVO.getNorigtaxmny();
    UFDouble norigtaxmnyCal = itemVOCal.getNorigtaxmny();
    UFDouble nqtunitnum = itemVO.getNqtunitnum();
    UFDouble nqtunitnumCal = itemVOCal.getNqtunitnum();

    if (!(MathTool.equals(nqtorigtaxprice, nqtorigtaxpriceCal)
        || MathTool.equals(norigtaxmny, norigtaxmnyCal) || MathTool.equals(
        nqtunitnum, nqtunitnumCal))) {
      ExceptionUtils.wrappBusinessException(NCLangRes4VoTransl.getNCLangRes()
          .getStrByID(
              "4004030_0",
              "04004030-0330",
              null,
              new String[] {
                itemVO.getCrowno(), String.valueOf(nqtunitnum),
                String.valueOf(nqtorigtaxprice), String.valueOf(norigtaxmny)
              })/* 第{0}行报价数量、含税单价、金额关系不正确-报价数量：{1}，含税单价：{2}，价税合计：{3} */);
    }
  }
}
