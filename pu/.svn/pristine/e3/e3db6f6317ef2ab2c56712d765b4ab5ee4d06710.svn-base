package nc.impl.pu.m20.upgrade;

import nc.bs.pu.m20.upgrade.v61.M20UpgradeFor61;
import nc.itf.pu.m20.upgrade.IM20UpgradeToV61;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 请购单升级实现类
 * 
 * @since 6.0
 * @version 2012-4-11 上午09:19:43
 * @author wuxla
 */
public class M20UpgradeToV61Impl implements IM20UpgradeToV61 {

  @Override
  public void doAfterUpdateDataFrom60To61() throws BusinessException {
    try {
      new M20UpgradeFor61().doAfterUpdateDataFrom60To61();
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
