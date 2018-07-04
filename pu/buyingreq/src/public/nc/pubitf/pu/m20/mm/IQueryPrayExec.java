/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-28 上午10:25:14
 */
package nc.pubitf.pu.m20.mm;

import nc.pubitf.scmpub.scmpub.mmpps.calc.ISupplyResultForCalc;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>查询请购单执行情况
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-6-28 上午10:25:14
 */
public interface IQueryPrayExec {

  /**
   * mrp/mps查询请购单的供给的SQL片段<br>
   * 
   * @return mps查询请购单的供给查询数据
   * @throws BusinessException
   */
  public ISupplyResultForCalc getSupplyResult() throws BusinessException;
}
