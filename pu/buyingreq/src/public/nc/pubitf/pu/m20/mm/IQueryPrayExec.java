/**
 * $�ļ�˵��$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-28 ����10:25:14
 */
package nc.pubitf.pu.m20.mm;

import nc.pubitf.scmpub.scmpub.mmpps.calc.ISupplyResultForCalc;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ѯ�빺��ִ�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-6-28 ����10:25:14
 */
public interface IQueryPrayExec {

  /**
   * mrp/mps��ѯ�빺���Ĺ�����SQLƬ��<br>
   * 
   * @return mps��ѯ�빺���Ĺ�����ѯ����
   * @throws BusinessException
   */
  public ISupplyResultForCalc getSupplyResult() throws BusinessException;
}
