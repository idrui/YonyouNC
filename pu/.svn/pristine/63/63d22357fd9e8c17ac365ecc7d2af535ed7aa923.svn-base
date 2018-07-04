package nc.vo.pu.m23.rule;

import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 检查到货单数量正负数是否正确。
 * 
 * @since 6.0
 * @version 2012-9-18 上午10:17:22
 * @author lixyp
 */
public class ArriveNumCheck {

  private IKeyValue keyValue = null;

  private UFDouble oldValue = null;

  public ArriveNumCheck(IKeyValue keyValue, UFDouble oldValue) {
    this.keyValue = keyValue;
    this.oldValue = oldValue;
  }

  /**
   * 检查。
   * 
   * @param row 当前所在行
   * @param itemKey 编辑字段
   */
  public void check(int row, String itemKey) {
    Boolean bisBack =
        (Boolean) this.keyValue.getHeadValue(ArriveHeaderVO.BISBACK);
    // 到货主数量、到货数量
    UFDouble nnum =
        (UFDouble) this.keyValue.getBodyValue(row, ArriveItemVO.NNUM);
    UFDouble nastnum =
        (UFDouble) this.keyValue.getBodyValue(row, ArriveItemVO.NASTNUM);

    // 途耗主数量、途耗数量
    UFDouble nwastnum =
        (UFDouble) this.keyValue.getBodyValue(row, ArriveItemVO.NWASTNUM);
    UFDouble nwastastnum =
        (UFDouble) this.keyValue.getBodyValue(row, ArriveItemVO.NWASTASTNUM);

    // 计划到货主数量、计划到货数量
    UFDouble nplannum =
        (UFDouble) this.keyValue.getBodyValue(row, ArriveItemVO.NPLANNUM);
    UFDouble nplanastnum =
        (UFDouble) this.keyValue.getBodyValue(row, ArriveItemVO.NPLANASTNUM);

    // 赠品主数量、赠品数量
    UFDouble npresentnum =
        (UFDouble) this.keyValue.getBodyValue(row, ArriveItemVO.NPRESENTNUM);
    UFDouble npresentastnum =
        (UFDouble) this.keyValue.getBodyValue(row, ArriveItemVO.NPRESENTASTNUM);

    if (Boolean.TRUE.equals(bisBack)) {
      if (MathTool.greaterThan(nnum, UFDouble.ZERO_DBL)
          || MathTool.greaterThan(nastnum, UFDouble.ZERO_DBL)
          || MathTool.greaterThan(nwastnum, UFDouble.ZERO_DBL)
          || MathTool.greaterThan(nwastastnum, UFDouble.ZERO_DBL)
          || MathTool.greaterThan(nplannum, UFDouble.ZERO_DBL)
          || MathTool.greaterThan(nplanastnum, UFDouble.ZERO_DBL)
          || MathTool.greaterThan(npresentnum, UFDouble.ZERO_DBL)
          || MathTool.greaterThan(npresentastnum, UFDouble.ZERO_DBL)) {
        this.keyValue.setBodyValue(row, itemKey, this.oldValue);
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004040_0", "04004040-0193")/*
                                                                     * @res
                                                                     * "退货单数量不能为正数"
                                                                     */);
      }
    }
    else {
      if (MathTool.lessThan(nnum, UFDouble.ZERO_DBL)
          || MathTool.lessThan(nastnum, UFDouble.ZERO_DBL)
          || MathTool.lessThan(nwastnum, UFDouble.ZERO_DBL)
          || MathTool.lessThan(nwastastnum, UFDouble.ZERO_DBL)
          || MathTool.lessThan(nplannum, UFDouble.ZERO_DBL)
          || MathTool.lessThan(nplanastnum, UFDouble.ZERO_DBL)
          || MathTool.lessThan(npresentnum, UFDouble.ZERO_DBL)
          || MathTool.lessThan(npresentastnum, UFDouble.ZERO_DBL)) {
        this.keyValue.setBodyValue(row, itemKey, this.oldValue);
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004040_0", "04004040-0194")/*
                                                                     * @res
                                                                     * "到货单数量不能为负数"
                                                                     */);
      }
    }
  }
}
