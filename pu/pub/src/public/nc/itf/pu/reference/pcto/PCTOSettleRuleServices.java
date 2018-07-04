package nc.itf.pu.reference.pcto;

import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pcto.settlerule.pu.ISettleRuleMatchForPUService;
import nc.pubitf.pcto.settlerule.pub.IMatchSettleruleServiceForPub;
import nc.vo.pcto.settlerule.entity.PCTOSettleRuleAggVO;
import nc.vo.pcto.settlerule.para.MatchSettleRuleParaVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.log.Log;

/**
 * @description
 *              利润中心内部交易结算关系提供給采购的服务
 * @scene
 * @param
 * @since 6.36
 * @version 2014-11-10 下午2:02:08
 * @author mj423
 */
public class PCTOSettleRuleServices {

  /**
   * 采购入库单匹配利润中心内部交易结算规则
   * 
   * @param vos
   * @return Map<MatchSettleRuleParaVO, PCTOSettleRuleAggVO>
   *         key:匹配结算规则参数VO
   *         value:利润中心结算规则聚合VO
   * @throws BusinessException
   */
  public static Map<MatchSettleRuleParaVO, PCTOSettleRuleAggVO> getMathPCTOSettleRule(
      MatchSettleRuleParaVO[] matchParaList) {
    IMatchSettleruleServiceForPub srv =
        NCLocator.getInstance().lookup(IMatchSettleruleServiceForPub.class);
    try {
      return srv.matchSettleruleForPu(matchParaList);
    }
    catch (BusinessException e) {
      // 日志异常
      Log.debug(e.getMessage());
      return null;
    }
  }
  
  /**
   * 采购入库单匹配利润中心内部交易结算规则,返回对应的逻辑值
   * 
   * @param vos
   * @return Map<MatchSettleRuleParaVO, UFBoolean>
   *         key:匹配结算规则参数VO
   *         value:是否影响利润中心传成本
   * @throws BusinessException
   */
  public static Map<MatchSettleRuleParaVO, UFBoolean> getMathPCTOSettleMap(
      MatchSettleRuleParaVO[] matchParaVOs) {
    ISettleRuleMatchForPUService srv =
        NCLocator.getInstance().lookup(ISettleRuleMatchForPUService.class);
    try {
      return srv.matchSettleRuleForPU(matchParaVOs);
    }
    catch (BusinessException e) {
      // 日志异常
      Log.debug(e.getMessage());
      return null;
    }
  }
}
