package nc.itf.pu.upgrade;

import nc.vo.pub.BusinessException;

/**
 * 采购升级到v63接口
 * 
 * @since 6.3
 * @version 2012-10-17 上午09:57:53
 * @author fanly3
 */
public interface IPUUpgradeToV63 {
  /**
   * 从nc61升级nc63时,在账套升级后调用
   * 
   * @throws BusinessException
   */
  void doAfterUpdateDataFrom61To63() throws BusinessException;

  /**
   * 从nc61升级nc63时，进行数据升级之前调用
   * 
   * @throws BusinessException
   */
  void doBeforeUpdateDataFrom61To63() throws BusinessException;

  /**
   * 从nc61升级nc63时,进行数据库结构升级之前调用
   * 
   * @throws BusinessException
   */
  void doBeforeUpdateDBFrom61To63() throws BusinessException;
}
