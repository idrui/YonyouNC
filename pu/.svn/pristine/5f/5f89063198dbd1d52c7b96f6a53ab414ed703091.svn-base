/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-1 下午08:02:50
 */
package nc.vo.pu.m21.rule;

import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.pubapp.scale.ScaleUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>根据主数量和换算率计算辅数量
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-6-1 下午08:02:50
 */
public class NumValueSetter {
  private IKeyValue keyValue;

  public NumValueSetter(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 方法功能描述：根据主数量和换算率计算数量和报价数量
   * <p>
   * <b>参数说明</b>
   * 
   * @param rows
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-1 下午08:04:33
   */
  public void setNastnum(int[] rows) {
    if (ArrayUtils.isEmpty(rows)) {
      return;
    }
    ScaleUtils scale =
        new ScaleUtils(
            (String) this.keyValue.getHeadValue(OrderHeaderVO.PK_GROUP));
    for (int row : rows) {
      // 数量
      String vchangerate =
          (String) this.keyValue.getBodyValue(row, OrderItemVO.VCHANGERATE);
      String castunitid =
          (String) this.keyValue.getBodyValue(row, OrderItemVO.CASTUNITID);
      UFDouble nastnum = null;
      if (vchangerate != null) {
        nastnum =
            HslParseUtil.hslDivUFDouble(scale.adjustHslScale(vchangerate),
                (UFDouble) this.keyValue.getBodyValue(row, OrderItemVO.NNUM));
      }
      else {
        nastnum =
            HslParseUtil.hslDivUFDouble(scale.adjustHslScale("1.000/1.000"),
                (UFDouble) this.keyValue.getBodyValue(row, OrderItemVO.NNUM));
      }
      nastnum = scale.adjustNumScale(nastnum, castunitid);
      this.keyValue.setBodyValue(row, OrderItemVO.NASTNUM, nastnum);

      // 报价数量
      String vqtunitrate =
          (String) this.keyValue.getBodyValue(row, OrderItemVO.VQTUNITRATE);
      String cqtunitid =
          (String) this.keyValue.getBodyValue(row, OrderItemVO.CQTUNITID);
      UFDouble nqtunitnum = null;
      if (vqtunitrate != null) {
        nqtunitnum =
            HslParseUtil.hslDivUFDouble(scale.adjustHslScale(vqtunitrate),
                (UFDouble) this.keyValue.getBodyValue(row, OrderItemVO.NNUM));

      }
      else {
        nqtunitnum =
            HslParseUtil.hslDivUFDouble(scale.adjustHslScale("1.000/1.000"),
                (UFDouble) this.keyValue.getBodyValue(row, OrderItemVO.NNUM));
      }
      nqtunitnum = scale.adjustNumScale(nqtunitnum, cqtunitid);
      this.keyValue.setBodyValue(row, OrderItemVO.NQTUNITNUM, nqtunitnum);
    }
  }
}
