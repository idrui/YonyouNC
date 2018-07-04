package nc.impl.pu.settle.upgrade;

import nc.bs.pu.est.upgrade.v61.EstUpgradeFor61;
import nc.bs.pu.m27.update.v61.M27UpgradeFor61;
import nc.bs.pu.m4t.upgrade.v61.M4TUpgradeFor61;
import nc.itf.pu.settle.upgrade.ISettleUpgradeToV61;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 期初暂估单、暂估、结算V61升级实现类
 * 
 * @since 6.0
 * @version 2012-3-29 上午09:31:49
 * @author wuxla
 */
public class SettleUpgradeToV61Impl implements ISettleUpgradeToV61 {

  @Override
  public void doAfterUpdateDataFrom60To61() throws BusinessException {
    try {
      // v61期初暂估单升级
      new M4TUpgradeFor61().doAfterUpdateDataFrom60To61();
      // v61暂估升级
      new EstUpgradeFor61().doAfterUpdateDataFrom60To61();
      // v61结算升级
      new M27UpgradeFor61().doAfterUpdateDataFrom60To61();
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public void doBeforeUpdateDataFrom60To61() throws BusinessException {
  }

  @Override
  public void doBeforeUpdateDBFrom60To61() throws BusinessException {
  }

}
