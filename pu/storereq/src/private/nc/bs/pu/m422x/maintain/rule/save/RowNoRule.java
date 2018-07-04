/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-5 下午08:24:28
 */
package nc.bs.pu.m422x.maintain.rule.save;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.pub.rule.RowNoCheckRule;

/**
 * 
 * @description
 *            物资需求申请单，行号检查
 * @scene
 *      物资需求申请单保存
 * @param
 * 
 *
 * @since 6.0
 * @version 2010-8-5 下午08:24:28
 * @author wuxla
 */
public class RowNoRule implements IRule<StoreReqAppVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(StoreReqAppVO[] vos) {
    RowNoCheckRule rule = new RowNoCheckRule();
    rule.checkRowNo(vos, StoreReqAppItemVO.CROWNO);
  }

}
