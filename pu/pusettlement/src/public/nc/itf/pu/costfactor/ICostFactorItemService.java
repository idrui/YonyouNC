/**
 * $文件说明$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-19 上午11:19:43
 */
package nc.itf.pu.costfactor;

import nc.vo.pu.costfactor.entity.CostfactorItemVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>暂估费用项显示顺序更新
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-6-19 上午11:19:43
 */
public interface ICostFactorItemService {
  /**
   * 方法功能描述：成本要素定义更新操作。
   * <p>
   * <b>参数说明</b>
   * 
   * @param newVo
   *          需要更新到数据库的暂估费用项。
   * @return 保存后的成本要素定义
   * @throws BusinessException
   * @since 6.0
   */
  CostfactorItemVO update(CostfactorItemVO newVo) throws BusinessException;

  /**
   * 方法功能描述：成本要素定义更新操作。
   * <p>
   * <b>参数说明</b>
   * 
   * @param newVos
   *          需要更新到数据库的暂估费用项数组。
   * @return 保存后的成本要素定义数组
   * @throws BusinessException
   * @since 6.0
   */
  CostfactorItemVO[] update(CostfactorItemVO[] newVos) throws BusinessException;
}
