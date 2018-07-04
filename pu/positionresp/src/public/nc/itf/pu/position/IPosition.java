package nc.itf.pu.position;

import java.util.List;

import nc.vo.pu.position.entity.PositionVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>岗位设置增删改查
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-1-27 上午08:50:57
 */
public interface IPosition {

  /**
   * 方法功能描述：删除保存岗位设置。
   * <p>
   * <b>参数说明</b>
   * 
   * @param Vos
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-1-27 下午08:02:05
   */
  void delete(PositionVO[] Vos) throws BusinessException;

  /**
   * 方法功能描述：新增保存岗位设置。
   * <p>
   * <b>参数说明</b>
   * 
   * @param Vos
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-1-27 上午08:51:38
   */
  PositionVO[] insert(PositionVO[] Vos) throws BusinessException;

  /**
   * 监听基本档案数据的层级发生变化
   * 
   * @param list
   * @param bdUpdateInfo 采购岗信息
   * @throws BusinessException
   */
  void listenBDDataLevelUpdated(List<Object[]> list) throws BusinessException;

  /**
   * 方法功能描述：查询岗位设置。
   * <p>
   * <b>参数说明</b>
   * 
   * @param sql
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-1-28 下午04:25:07
   */
  PositionVO[] queryPositions(String nodecode, String sql)
      throws BusinessException;

  /**
   * 方法功能描述：修改保存岗位设置。
   * <p>
   * <b>参数说明</b>
   * 
   * @param Vos
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-1-27 下午08:02:14
   */
  PositionVO[] update(PositionVO[] Vos) throws BusinessException;
}
