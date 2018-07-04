package nc.pubitf.pu.m23.qc;

import nc.vo.pu.m23.entity.ArriveBbVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单提供给质检报告的回写服务接口类
 * <li>保存到货单的质检结果
 * <li>删除到货单的质检结果
 * <li>删除到货单的累计报检数量
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-8 下午04:14:51
 */
public interface IWriteback23ForC003 {

  /**
   * 方法功能描述：删除到货单的质检结果，用于质检报告弃审时
   * <p>
   * <b>参数说明</b>
   * 
   * @param 到货单子表id数组
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-8 下午04:34:40
   */
  public void delQCResultWhenReportUnApp(ArriveBbVO[] paras)
      throws BusinessException;

  /**
   * 方法功能描述：保存到货单的质检结果，用于质检报告审核时
   * <p>
   * <b>参数说明</b>
   * 
   * @param bbVO
   *          子子表VO
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-8 下午04:34:40
   */
  public void saveQCResultWhenReportApp(ArriveBbVO[] paras)
      throws BusinessException;
}
