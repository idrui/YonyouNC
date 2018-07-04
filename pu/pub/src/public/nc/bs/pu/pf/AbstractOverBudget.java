package nc.bs.pu.pf;

import nc.itf.mpp.reference.tbb.TbbCtrlServices;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.itf.tb.control.IAccessableBusiVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.tb.control.NtbCtlInfoVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * 超预算
 * 
 * @since 6.0
 * @version 2011-4-22 下午02:25:14
 * @author wuxla
 */

public abstract class AbstractOverBudget {

  /**
   * 超预算
   * 
   * @param aggVO 请购单或者采购订单
   * @return 超预算金额或者数量
   * @throws BusinessException
   */
  public UFDouble getOverBudget(AggregatedValueObject aggVO)
      throws BusinessException {
    try {
      if (!SysInitGroupQuery.isMPPEnabled()) {
        return null;
      }
      IAccessableBusiVO[] busivo = this.getBusivo(aggVO);
      NtbCtlInfoVO ctlInfoVO = TbbCtrlServices.getCheckInfo(busivo);
      if (null == ctlInfoVO) {
        return null;
      }

      // 计划数据
      UFDouble[] plandata = ctlInfoVO.getPlanData();
      if (ArrayUtils.isEmpty(plandata)) {
        return null;
      }
      return plandata[0];

    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

  protected abstract IAccessableBusiVO[] getBusivo(AggregatedValueObject aggVO);
}
