package nc.impl.pu.m23.upgrade;

import nc.bs.pu.m23.upgrade.v61.M23UpgradeFor61;
import nc.itf.pu.m23.upgrade.IM23UpgradeToV61;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 到货单v61升级实现类。
 * 
 * @since 6.0
 * @version 2012-3-29 上午09:43:18
 * @author lixyp
 */
public class M23UpgradeToV61Impl implements IM23UpgradeToV61 {

  @Override
  public void doAfterUpdateDataFrom60To61() throws BusinessException {
    try {
      new M23UpgradeFor61().process();
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public void doBeforeUpdateDataFrom60To61() throws BusinessException {
    // do nothing.
  }

  @Override
  public void doBeforeUpdateDBFrom60To61() throws BusinessException {
    // do nothing.
  }

}
