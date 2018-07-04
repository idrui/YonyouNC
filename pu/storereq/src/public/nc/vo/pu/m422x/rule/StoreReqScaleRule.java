/**
 * $文件说明$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-23 上午10:09:53
 */
package nc.vo.pu.m422x.rule;

import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.pub.rule.IScaleProcessor;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.pubapp.scale.TotalValueScale;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>物资需求精度处理类，ui和vo的精度处理都继承此类。
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-10-23 上午10:09:53
 */
public class StoreReqScaleRule implements IScaleProcessor {

  /**
   * 方法功能描述：设置表头表体精度
   * <p>
   * <b>参数说明</b>
   * 
   * @param scale - 表体精度处理器
   * @param total -表头精度处理器
   *          <p>
   * @since 6.0
   * @author tianft
   * @time 2010-10-23 上午10:34:23
   */
  @Override
  public void setScale(BillScaleProcessor scale, TotalValueScale totalScale) {
    if (scale != null) {
      this.setBodyScale(scale);
    }
    if (totalScale != null) {
      this.setHeadScale(totalScale);
    }
  }

  @Override
  public void setScaleForSingleTable(BillScaleProcessor scale,
      TotalValueScale totalScale) {
    if (scale != null) {
      this.setScale(scale, PosEnum.head);
    }
    if (totalScale != null) {
      this.setHeadScale(totalScale);
    }

  }

  /**
   * 方法功能描述：设置表体精度
   * <p>
   * <b>参数说明</b>
   * 
   * @param scale - 精度处理器
   *          <p>
   * @since 6.0
   * @author tianft
   * @time 2010-10-23 上午10:30:19
   */
  private void setBodyScale(BillScaleProcessor scale) {
    this.setScale(scale, PosEnum.body);
  }

  /**
   * 方法功能描述：设置表头精度
   * <p>
   * <b>参数说明</b>
   * 
   * @param scale -精度处理器
   *          <p>
   * @since 6.0
   * @author tianft
   * @time 2010-10-23 上午10:30:28
   */
  private void setHeadScale(TotalValueScale scale) {
    scale.setHeadTailKeys(new String[] {
      StoreReqAppHeaderVO.NTOTALASTNUM,
    });
  }

  /**
   * @param scale
   * @param body
   */
  private void setScale(BillScaleProcessor scale, PosEnum posEnum) {
    // 换算率
    String[] changeRates = new String[] {
      StoreReqAppItemVO.VCHANGERATE
    };
    // 本币金额
    String[] mnykeys = new String[] {
      StoreReqAppItemVO.NTAXMNY
    };
    // 表头合计金额－根据公共需求2011.9.7走模板精度
    String[] headMnykeys = new String[] {
      StoreReqAppHeaderVO.NTOTALORIGMNY
    };
    // 业务单位数量
    String[] assistNumkeys = new String[] {
      StoreReqAppItemVO.NASTNUM
    };
    // 主数量
    String[] numkeys =
        new String[] {
          StoreReqAppItemVO.NACCUOUTNUM, StoreReqAppItemVO.NNUM,
          StoreReqAppItemVO.NACCUOUTREQNUM, StoreReqAppItemVO.NACCUMBUYREQNUM,
          StoreReqAppItemVO.NCANBUYREQNNUM, StoreReqAppItemVO.NACCCUSTORNUM,
          StoreReqAppItemVO.NNETNUM,StoreReqAppItemVO.NACCUMMINUSNUM
        };
    // 价格
    String[] pricekeys = new String[] {
      StoreReqAppItemVO.NTAXPRICE
    };

    // 换算率精度
    scale.setHslCtlInfo(changeRates, posEnum, null);
    // 业务单位数量精度
    scale.setNumCtlInfo(assistNumkeys, posEnum, null,
        StoreReqAppItemVO.CASTUNITID, posEnum, null);
    // 主单位数量精度
    scale.setNumCtlInfo(numkeys, posEnum, null, StoreReqAppItemVO.CUNITID,
        posEnum, null);
    // 表头合计金额－根据公共需求2011.9.7走模板精度
    scale.setOrgLocMnyCtlInfo(headMnykeys, PosEnum.head, null);
    // 本币金额精度
    scale.setMnyCtlInfo(mnykeys, posEnum, null, StoreReqAppItemVO.CCURRENCYID,
        posEnum, null);
    // 单价精度
    scale.setPriceCtlInfo(pricekeys, posEnum, null,
        StoreReqAppItemVO.CCURRENCYID, posEnum, null);
    // 进行计算
    scale.process();
  }
}
