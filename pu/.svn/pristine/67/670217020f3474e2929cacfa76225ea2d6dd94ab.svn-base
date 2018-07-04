package nc.impl.pu.m422x.upgrade;

import nc.bs.pu.m422x.upgrade.v65.M422XUpgradeFor65;
import nc.itf.pu.m422x.upgrade.IM422xUpgradeToV636;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 物资需求申请单升级实现类
 * 
 * @since 6.5
 * @version 2014-1-23 下午03:04:32
 * @author fanly3
 */
public class M422xUpgradeToV636Impl implements IM422xUpgradeToV636 {

  @Override
  public void doAfterUpdateDataFrom63To636() throws BusinessException {
    try {
      // 物资需求申请类型默认设置成普通物资需求申请
      new M422XUpgradeFor65().doAfterUpdateDataFrom61To63();
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
  }

  @Override
  public void doBeforeUpdateDataFrom63To636() throws BusinessException {

  }

  @Override
  public void doBeforeUpdateDBFrom63To636() throws BusinessException {

  }

}
