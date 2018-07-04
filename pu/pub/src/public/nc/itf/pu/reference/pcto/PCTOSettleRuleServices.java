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
 *              ���������ڲ����׽����ϵ�ṩ�o�ɹ��ķ���
 * @scene
 * @param
 * @since 6.36
 * @version 2014-11-10 ����2:02:08
 * @author mj423
 */
public class PCTOSettleRuleServices {

  /**
   * �ɹ���ⵥƥ�����������ڲ����׽������
   * 
   * @param vos
   * @return Map<MatchSettleRuleParaVO, PCTOSettleRuleAggVO>
   *         key:ƥ�����������VO
   *         value:�������Ľ������ۺ�VO
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
      // ��־�쳣
      Log.debug(e.getMessage());
      return null;
    }
  }
  
  /**
   * �ɹ���ⵥƥ�����������ڲ����׽������,���ض�Ӧ���߼�ֵ
   * 
   * @param vos
   * @return Map<MatchSettleRuleParaVO, UFBoolean>
   *         key:ƥ�����������VO
   *         value:�Ƿ�Ӱ���������Ĵ��ɱ�
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
      // ��־�쳣
      Log.debug(e.getMessage());
      return null;
    }
  }
}
