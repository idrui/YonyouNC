package nc.impl.pu.position;

import java.util.List;

import nc.impl.pu.position.action.DeleteAction;
import nc.impl.pu.position.action.InsertAction;
import nc.impl.pu.position.action.ListenAction;
import nc.impl.pu.position.action.QueryAction;
import nc.impl.pu.position.action.UpdateAction;
import nc.itf.pu.position.IPosition;
import nc.vo.pu.position.entity.PositionVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

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
public class PositionImpl implements IPosition {

  /**
   * 方法功能描述：删除保存岗位设置。
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
  @Override
  public void delete(PositionVO[] vos) throws BusinessException {
    try {
      new DeleteAction().delete(vos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  /**
   * 方法功能描述：新增保存岗位设置。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-1-27 上午08:51:38
   */
  @Override
  public PositionVO[] insert(PositionVO[] vos) throws BusinessException {
    try {
      return new InsertAction().insert(vos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public void listenBDDataLevelUpdated(List<Object[]> list)
      throws BusinessException {
    try {
      new ListenAction().updates();
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

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
  @Override
  public PositionVO[] queryPositions(String nodecode, String sql)
      throws BusinessException {
    try {
      return new QueryAction().queryMain(nodecode, sql);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  /**
   * 方法功能描述：修改保存岗位设置。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-1-27 下午08:02:14
   */
  @Override
  public PositionVO[] update(PositionVO[] vos) throws BusinessException {
    try {
      return new UpdateAction().update(vos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

}
