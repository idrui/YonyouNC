/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-25 下午02:09:05
 */
package nc.pubitf.pu.m4202;

import java.util.Map;

import nc.vo.pu.m4202.entity.outsrv.VMIEstInfoVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>为库存消耗汇总的暂估信息查询服务
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-9-25 下午02:09:05
 */
public interface IVMIEstInfoQuery {

  /**
   * 方法功能描述：查询消耗汇总的暂估信息，为汇总显示数据使用。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vmiIDs
   *          消耗汇总表头主键数组
   * @return Map(消耗汇总表头主键,VMIEstInfoVO)，永不为null
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-9-25 下午02:20:43
   */
  public Map<String, VMIEstInfoVO> queryEstInfo(String[] vmiIDs)
      throws BusinessException;
}
