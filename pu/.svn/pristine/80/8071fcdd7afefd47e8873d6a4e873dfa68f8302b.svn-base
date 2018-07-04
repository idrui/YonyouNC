package nc.vo.pu.m27.entity;

import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pu.m25.settle.ICostfactorDiscount;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

public class ICostfactorDiscountUtil {

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @param i 下标。从0开始，0即成本要素1。
   * @return <p>
   * @since 6.0
   * @author wangyf
   * @time 2010-7-5 上午09:39:35
   */
  public static UFDouble getNcostfactor(ICostfactorDiscount vo, int i) {
    if (i == 0) {
      return vo.getNcostfactor1();
    }
    else if (i == 1) {
      return vo.getNcostfactor2();
    }
    else if (i == 2) {
      return vo.getNcostfactor3();
    }
    else if (i == 3) {
      return vo.getNcostfactor4();
    }
    else if (i == 4) {
      return vo.getNcostfactor5();
    }
    else if (i == 5) {
      return vo.getNcostfactor6();
    }
    else if (i == 6) {
      return vo.getNcostfactor7();
    }
    else if (i == 7) {
      return vo.getNcostfactor8();
    }

    return null;
  }

  /**
   * 是否进行过费用或折扣分摊
   * 
   * @param vo
   * @return
   */
  public static boolean isAllot(ICostfactorDiscount vo) {
    for (int i = 0; i < CostfactorVO.MAX_NUM; i++) {
      UFDouble cf = ICostfactorDiscountUtil.getNcostfactor(vo, i);
      if (!MathTool.isZero(cf)) {
        return true;
      }
    }
    if (!MathTool.isZero(vo.getNdiscount())) {
      return true;
    }
    // mengjian
    if (!MathTool.isZero(vo.getNadjustmny())) {
      return true;
    }
    return false;

  }

  /**
   * 给一个劳务折扣VO设置成本要素值
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @param i 下标。从0开始，0即成本要素1。
   * @param dfactor
   * @return <p>
   * @since 6.0
   * @author wangyf
   * @time 2010-6-3 下午03:24:43
   */
  public static UFDouble setNcostfactor(ICostfactorDiscount vo, int i,
      UFDouble dfactor) {
    if (i == 0) {
      vo.setNcostfactor1(dfactor);
    }
    else if (i == 1) {
      vo.setNcostfactor2(dfactor);
    }
    else if (i == 2) {
      vo.setNcostfactor3(dfactor);
    }
    else if (i == 3) {
      vo.setNcostfactor4(dfactor);
    }
    else if (i == 4) {
      vo.setNcostfactor5(dfactor);
    }
    else if (i == 5) {
      vo.setNcostfactor6(dfactor);
    }
    else if (i == 6) {
      vo.setNcostfactor7(dfactor);
    }
    else if (i == 7) {
      vo.setNcostfactor8(dfactor);
    }

    return null;
  }

  /**
   * 对一个VO设置成本要素
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo
   * @param daFactor <p>
   * @since 6.0
   * @author wangyf
   * @time 2010-7-5 上午09:40:27
   */
  public static void setNcostfactor(ICostfactorDiscount vo, UFDouble[] daFactor) {
    for (int i = 0; i < CostfactorVO.MAX_NUM; i++) {
      ICostfactorDiscountUtil.setNcostfactor(vo, i, daFactor[i]);
    }
  }
}
