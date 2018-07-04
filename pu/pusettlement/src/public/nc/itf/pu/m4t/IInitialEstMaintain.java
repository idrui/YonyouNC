/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-26 上午09:26:12
 */
package nc.itf.pu.m4t;

import nc.vo.pu.m4t.entity.InitialEstContext;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>期初暂估单的维护操作组件
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-3-26 上午09:26:12
 */
public interface IInitialEstMaintain {
  /**
   * 方法功能描述：删除。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @param context
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-3-26 上午09:44:18
   */
  void delete(InitialEstVO[] vos, InitialEstContext context)
      throws BusinessException;

  /**
   * 期初暂估单导入保存
   * <p>
   * 使用场景：
   * <ul>
   * <li>期初暂估单导入excel保存
   * </ul>
   * 
   * @param vos
   * @return
   * @throws BusinessException
   */
  InitialEstVO[] importSave(InitialEstVO[] vos) throws BusinessException;

  /**
   * 期初暂估单保存(新增和修改)
   * <p>
   * 使用场景：
   * <ul>
   * <li>从流程平台脚本调用保存
   * <li>前台调整保存（保证必须全VO）
   * </ul>
   * 
   * @param vos要保存的期初暂估单VO，必须为全VO
   * @param context环境信息
   * @return 返回的一定是全vo
   * @throws BusinessException
   */
  InitialEstVO[] save(InitialEstVO[] vos, InitialEstContext context)
      throws BusinessException;
}
