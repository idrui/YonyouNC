package nc.impl.pu.m25.upgrade;

import nc.bs.pu.m25.upgrade.v61.M25UpgradeForV61;
import nc.itf.pu.m25.upgrade.IM25UpgradeToV61;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class M25UpgradeToV61Impl implements IM25UpgradeToV61 {

  @Override
  public void doAfterUpdateDataFrom60To61() throws BusinessException {
    try {
      new M25UpgradeForV61().doAfterUpdateDataFrom60To61();
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
