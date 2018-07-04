package nc.itf.pu.m23.qc;

import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>到货单的针对质量管理的接口定义,本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单的质检
 * <li>到货单的反检
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-11 下午01:40:39
 */
public interface IArriveForQC {

  /**
   * 方法功能描述：>到货单的反检
   * <p>
   * <b>参数说明</b>
   * 
   * @param aggVO
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-13 上午09:43:38
   */
  public ArriveItemVO[] antiQualityCheck(ArriveItemVO[] bills) throws BusinessException;

  /**
   * 方法功能描述：到货单的质检
   * <p>
   * <b>参数说明</b>
   * 
   * @param aggVO
   * @param isCheck 免检存货是否报检
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-13 上午09:43:38
   */
  public Object[] qualityCheck(ArriveVO[] bills, boolean isCheck)
      throws BusinessException;
}
