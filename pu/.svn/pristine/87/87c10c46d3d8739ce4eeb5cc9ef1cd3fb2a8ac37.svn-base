package nc.itf.pu.upgrade;

import nc.vo.pub.BusinessException;

/**
 * 采购升级到v65接口
 * 
 * @since 6.5
 * @version 2014-1-23 下午02:59:48
 * @author fanly3
 */
public interface IPUUpgradeToV636 {

  /**
   * 从nc63升级nc65时,在账套升级后调用
   * 
   * @throws BusinessException
   */
  void doAfterUpdateDataFrom63To636() throws BusinessException;

  /**
   * 从nc63升级nc65时，进行数据升级之前调用
   * 
   * @throws BusinessException
   */
  void doBeforeUpdateDataFrom63To636() throws BusinessException;

  /**
   * 从nc63升级nc65时,进行数据库结构升级之前调用
   * 
   * @throws BusinessException
   */
  void doBeforeUpdateDBFrom63To636() throws BusinessException;
}
