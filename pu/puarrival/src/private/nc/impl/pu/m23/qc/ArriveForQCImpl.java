package nc.impl.pu.m23.qc;

import nc.impl.pu.m23.qc.action.AntiQualityCheckAction;
import nc.impl.pu.m23.qc.action.QualityCheckAction;
import nc.itf.pu.m23.qc.IArriveForQC;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
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
 * @time 2010-9-28 下午01:26:15
 */
public class ArriveForQCImpl implements IArriveForQC {

  @Override
  public ArriveItemVO[] antiQualityCheck(ArriveItemVO[] bills) throws BusinessException {
    try {
      AntiQualityCheckAction action = new AntiQualityCheckAction();
      return action.antiQualityCheck(bills);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public Object[] qualityCheck(ArriveVO[] bills, boolean isCheck)
      throws BusinessException {
    try {
      QualityCheckAction action = new QualityCheckAction();
      return action.qualityCheck(bills, isCheck);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }
}
