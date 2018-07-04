/**
 * $文件说明$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-11-3 下午04:16:03
 */
package nc.vo.pu.m27.rule;

import nc.vo.pu.m27.entity.SettleBillHeaderVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.pub.rule.IScaleProcessor;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.pubapp.scale.TotalValueScale;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>结算单精度处理类（vo&UI）
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-11-3 下午04:16:03
 */
public class SettleBillScaleRule implements IScaleProcessor {

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param scale
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-10-20 下午03:54:28
   */
  public void setBodyScale(BillScaleProcessor scale) {
    this.setScale(scale, PosEnum.body);
  }

  public void setScale(BillScaleProcessor scale, PosEnum posEnum) {
    // 单价
    String[] pricekeys =
        new String[] {
          SettleBillItemVO.NGOODSPRICE, SettleBillItemVO.NPRICE,
          SettleBillItemVO.NREASONALWASTPRICE
        };
    // 本币金额
    String[] mnykeys =
        new String[] {
          SettleBillItemVO.NMONEY, SettleBillItemVO.NCOSTFACTOR1,
          SettleBillItemVO.NCOSTFACTOR2, SettleBillItemVO.NCOSTFACTOR3,
          SettleBillItemVO.NCOSTFACTOR4, SettleBillItemVO.NCOSTFACTOR5,
          SettleBillItemVO.NCOSTFACTOR6, SettleBillItemVO.NCOSTFACTOR7,
          SettleBillItemVO.NCOSTFACTOR8, SettleBillItemVO.NDISCOUNT,
          SettleBillItemVO.NREASONALWASTMNY, SettleBillItemVO.NADJUSTMNY
        };
    // 主数量
    String[] numkeys = new String[] {
      SettleBillItemVO.NREASONALWASTNUM, SettleBillItemVO.NSETTLENUM
    };

    // 单价精度
    scale.setPriceCtlInfo(pricekeys, posEnum, null,
        SettleBillHeaderVO.CCURRENCYID, PosEnum.head, null);
    // 主单位数量精度
    scale.setNumCtlInfo(numkeys, posEnum, null, SettleBillItemVO.CUNITID,
        posEnum, null);
    // 本币金额精度
    scale.setMnyCtlInfo(mnykeys, posEnum, null, SettleBillHeaderVO.CCURRENCYID,
        PosEnum.head, null);

    // 进行计算
    scale.process();
  }

  /**
   * 父类方法重写
   * 
   * @see nc.vo.pu.pub.rule.IScaleProcessor#setScale(nc.vo.scmpub.scale.BillScaleProcessor,
   *      nc.vo.scmpub.scale.TotalValueScale)
   */
  @Override
  public void setScale(BillScaleProcessor scale, TotalValueScale totalScale) {
    // 表头合计不需处理
    if (scale != null) {
      this.setBodyScale(scale);
    }
  }

  @Override
  public void setScaleForSingleTable(BillScaleProcessor scale,
      TotalValueScale totalScale) {
    // 表头合计不需处理
    if (scale != null) {
      this.setScale(scale, PosEnum.head);
    }
  }
}
