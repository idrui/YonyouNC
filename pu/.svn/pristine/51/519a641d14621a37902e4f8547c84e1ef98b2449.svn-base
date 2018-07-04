package nc.impl.pu.m21.upgrade;

import nc.bs.pu.m21.upgrade.v61.M21UpgradeToV61;
import nc.itf.pu.m21.upgrade.IM21UpgradeToV61;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class M21UpgradeToV61Impl implements IM21UpgradeToV61 {

  @Override
  public void doAfterUpdateDataFrom60To61() throws BusinessException {
    try {
      new M21UpgradeToV61().doAfterUpdateDataFrom60To61();
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
