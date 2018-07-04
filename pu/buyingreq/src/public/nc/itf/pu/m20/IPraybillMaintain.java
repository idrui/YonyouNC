/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-27 上午08:50:57
 */
package nc.itf.pu.m20;

import java.util.Set;

import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单增删改查
 * <li>请购单整单打开、整单关闭
 * <li>请购单行打开、行关闭
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-1-27 上午08:50:57
 */
public interface IPraybillMaintain {

  /**
   * 关闭请购单。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          需要关闭的请购单
   * @return 关闭后的请购单
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-15 上午10:17:07
   */
  PraybillVO[] closeBill(PraybillVO[] vos) throws BusinessException;

  /**
   * 为了记录日志获取全vo才加的方法
   * 
   * @param Vos 输入全vo
   * @return 返回也是全vo
   * @throws BusinessException
   */
  PraybillVO[] closeBillByFullVO(PraybillVO[] Vos) throws BusinessException;

  /**
   * 关闭请购单行。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          需要关闭的请购单行
   * @return 行关闭后的请购单
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-15 上午10:17:07
   */
  PraybillVO[] closeBillRow(PraybillVO[] vos) throws BusinessException;

  /**
   * 为了记录日志获取全vo才加的方法
   * 
   * @param Vos 输入全vo
   * @return 返回也是全vo
   * @throws BusinessException
   */
  PraybillVO[] closeBillRowByFullVO(PraybillVO[] Vos, Set<String> closePks)
      throws BusinessException;

  /**
   * 方法功能描述：简要描述本方法的功能。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-1-27 下午08:02:05
   */
  void delete(PraybillVO[] vos) throws BusinessException;

  /**
   * 方法功能描述：新增保存请购单。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return 保存后的请购单
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-1-27 上午08:51:38
   */
  PraybillVO[] insert(PraybillVO[] vos) throws BusinessException;

  /**
   * 打开请购单。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          需要打开的请购单
   * @return 打开后的请购单
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-15 上午10:17:07
   */
  PraybillVO[] openBill(PraybillVO[] vos) throws BusinessException;

  /**
   * 为了记录日志获取全vo才加的方法
   * 
   * @param Vos 输入全vo
   * @return 返回也是全vo
   * @throws BusinessException
   */

  PraybillVO[] openBillByFullVO(PraybillVO[] Vos) throws BusinessException;

  /**
   * 打开请购单行。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          需要打开的请购单行
   * @return 行打开后的请购单
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-4-15 上午10:17:07
   */
  PraybillVO[] openBillRow(PraybillVO[] vos) throws BusinessException;

  /**
   * 为了记录日志获取全vo才加的方法
   * 
   * @param Vos 输入全vo
   * @return 返回也是全vo
   * @throws BusinessException
   */
  PraybillVO[] openBillRowByFullVO(PraybillVO[] Vos, Set<String> openPks)
      throws BusinessException;

  /**
   * 方法功能描述：查询请购单。
   * <p>
   * <b>参数说明</b>
   * 
   * @param queryScheme
   * @return 请购单
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-1-28 下午04:25:07
   */
  PraybillVO[] queryPrayBills(IQueryScheme queryScheme)
      throws BusinessException;

  /**
   * 方法功能描述：保存统一入口。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return 保存后的请购单
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-1-28 下午08:45:33
   */
  PraybillVO[] saveBase(PraybillVO[] vos) throws BusinessException;

  /**
   * 前台推式生成请购单设置默认值。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          需要推式生成的请购单
   * @return 设置默认值后的请购单
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-6-07 上午10:17:07
   */
  PraybillVO[] setDefaultValue(PraybillVO[] vos) throws BusinessException;

  /**
   * 方法功能描述：请购单更新。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return 更新后的请购单
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-1-27 下午08:02:14
   */
  PraybillVO[] update(PraybillVO[] vos) throws BusinessException;

  /**
   * 资产维修计划推式生成请购单时，给请购单设置默认值
   * 
   * @param vos 需要推式生成的请购单
   * @return 更新后的请购单
   * @throws BusinessException
   */
  PraybillVO[] setDefaultValueForM4B32(PraybillVO[] vos)
      throws BusinessException;
}
