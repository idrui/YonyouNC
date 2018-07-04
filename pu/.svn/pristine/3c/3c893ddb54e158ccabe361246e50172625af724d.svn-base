/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-28 下午07:33:53
 */
package nc.bs.pu.m422x.state.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IFilterRule;
import nc.vo.pu.m422x.entity.StoreReqAppCloseVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pub.lang.UFBoolean;

/**
 * 
 * @description
 *            物资需求申请单行打开/关闭时，过滤掉已经是当前关闭/打开状态VO
 * @scene
 *      物资需求申请单行打开/关闭
 * @param
 * 
 *
 * @since 6.0
 * @version 2010-7-28 下午07:33:53
 * @author wuxla
 */
public class UnChgStateRowFilterRule implements IFilterRule<StoreReqAppCloseVO> {

  private UFBoolean state;

  public UnChgStateRowFilterRule(UFBoolean state) {
    this.state = state;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IFilterRule#process(E[])
   */
  @Override
  public StoreReqAppCloseVO[] process(StoreReqAppCloseVO[] vos) {
    List<StoreReqAppCloseVO> filterVOs = new ArrayList<StoreReqAppCloseVO>();

    for (StoreReqAppCloseVO vo : vos) {
      if (!this.state.equals(vo.getAttributeValue(StoreReqAppItemVO.BCLOSE))) {
        filterVOs.add(vo);
      }
    }

    return filterVOs.toArray(new StoreReqAppCloseVO[filterVOs.size()]);
  }

}
