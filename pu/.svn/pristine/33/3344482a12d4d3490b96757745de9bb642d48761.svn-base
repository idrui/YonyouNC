package nc.itf.pu.upgrade;

import nc.vo.pub.BusinessException;

/**
 * 采购升级到v61接口
 * 
 * @since 6.0
 * @version 2012-3-29 上午10:10:42
 * @author tianft
 */
public interface IPUUpgradeToV61 {

  /**
   * 从nc60升级nc61时,在账套升级后调用
   * 
   * @throws BusinessException
   */
  void doAfterUpdateDataFrom60To61() throws BusinessException;

  /**
   * 从nc60升级nc61时，进行数据升级之前调用
   * 
   * @throws BusinessException
   */
  void doBeforeUpdateDataFrom60To61() throws BusinessException;

  /**
   * 从nc60升级nc61时,进行数据库结构升级之前调用
   * 
   * @throws BusinessException
   */
  void doBeforeUpdateDBFrom60To61() throws BusinessException;
}
