package nc.vo.pu.scale;

import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.scale.ScaleUtils;

/**
 * 采购精度处理工具类
 * 
 * @since 6.0
 * @version 2012-4-5 下午01:15:42
 * @author tianft
 */
public class PuScaleUtils extends ScaleUtils {

  private static final int DEFAULTDIGIT = 2;

  public PuScaleUtils(String pk_group) {
    super(pk_group);
  }

  /*
   * 处理折扣率精度,按固定2位处理
   */
  @Override
  public UFDouble adjustDiscountRateScale(UFDouble value) {
    return ScaleUtils.adjustScale(value, PuScaleUtils.DEFAULTDIGIT);
  }
}
