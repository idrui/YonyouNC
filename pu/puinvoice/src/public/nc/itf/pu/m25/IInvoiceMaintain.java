/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-22 上午10:49:24
 */
package nc.itf.pu.m25;

import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.env.InvoiceUIToBSEnv;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>发票维护操作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-22 上午10:49:24
 */
public interface IInvoiceMaintain {
  public void delete(InvoiceVO[] vos, InvoiceUIToBSEnv[] envs)
      throws BusinessException;

  /**
   * 基于前台全vo数组的冻结
   * 
   * @param clientVos 前台全vo数组
   * @param origVos 原始vo数组
   * @return 全vo
   * @throws BusinessException
   */
  public InvoiceVO[] freeze(InvoiceVO[] clientVos, InvoiceVO[] origVos)
      throws BusinessException;

  public InvoiceVO[] freezeByLightVOs(InvoiceVO[] vos) throws BusinessException;

  /**
   * 新增保存
   * 
   * @param vos 要保存的发票VO，必须为全VO
   * @param env 环境信息
   * @return 返回一定是全VO
   * @throws BusinessException
   */
  public InvoiceVO[] insert(InvoiceVO[] vos, InvoiceUIToBSEnv env)
      throws BusinessException;

  /**
   * 采购发票保存（新增和修改）
   * <p>
   * 使用场景：
   * <ul>
   * <li>从流程平台脚本调用保存
   * <li>前台调整保存（保证必须全VO）
   * </ul>
   * 
   * @param vos 要保存的发票VO，必须为全VO
   * @param envs 环境信息
   * @return 返回一定是全VO
   * @throws BusinessException
   */
  public InvoiceVO[] save(InvoiceVO[] vos, InvoiceUIToBSEnv[] envs)
      throws BusinessException;

  /**
   * 基于前台全vo数组的解冻
   * 
   * @param clientVos 前台全vo数组
   * @param origVos 原始全vo数组
   * @return 全vo
   * @throws BusinessException
   */
  public InvoiceVO[] unFreeze(InvoiceVO[] clientVos, InvoiceVO[] origVos)
      throws BusinessException;

  public InvoiceVO[] unFreezeByLightVOs(InvoiceVO[] vos)
      throws BusinessException;

  /**
   * 修改保存
   * 
   * @param vos 要保存的发票VO，必须为全VO
   * @param env 环境信息
   * @return 返回一定是全VO
   * @throws BusinessException
   */
  public InvoiceVO[] update(InvoiceVO[] vos, InvoiceUIToBSEnv env)
      throws BusinessException;
}
