package nc.impl.pu.m422x.upgrade;

import nc.bs.pu.m422x.upgrade.v61.M422XUpgradeFor61;
import nc.itf.pu.m422x.upgrade.IM422xUpgradeToV61;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 物资需求申请升级程序
 * 
 * @since 6.1
 * @version 2012-02-13 上午11:25:32
 * @author yangtian
 */
public class M422xUpgradeToV61Impl implements IM422xUpgradeToV61 {

  @Override
  public void doAfterUpdateDataFrom60To61() throws BusinessException {
    try {
      new M422XUpgradeFor61().doAfterUpdateDataFrom60To61();
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
