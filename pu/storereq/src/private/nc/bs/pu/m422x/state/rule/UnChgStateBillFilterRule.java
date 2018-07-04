package nc.bs.pu.m422x.state.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IFilterRule;
import nc.vo.pu.m422x.entity.StoreReqAppVO;

/**
 * 
 * @description
 *            �����������뵥�ر�/��ʱ�����˵��Ѿ��ǵ�ǰ�ر�/��״̬��VO
 * @scene
 *      �����������뵥�ر�/��
 * @param
 * 
 *
 * @since 6.0
 * @version 2010-7-28 ����08:59:10
 * @author wuxla
 */
public class UnChgStateBillFilterRule implements IFilterRule<StoreReqAppVO> {

  private Integer state;

  public UnChgStateBillFilterRule(Integer state) {
    this.state = state;
  }

  /**
   * ���෽����д
   * 
   * @see nc.impl.pubapp.pattern.rule.IFilterRule#process(E[])
   */
  @Override
  public StoreReqAppVO[] process(StoreReqAppVO[] vos) {
    List<StoreReqAppVO> filterVOs = new ArrayList<StoreReqAppVO>();

    for (StoreReqAppVO vo : vos) {
      if (!this.state.equals(vo.getHVO().getFbillstatus())) {
        filterVOs.add(vo);
      }
    }

    return filterVOs.toArray(new StoreReqAppVO[filterVOs.size()]);
  }

}
