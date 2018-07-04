/**
 * $文件说明$
 * 
 * @author linsf
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-25 下午01:46:16
 */
package nc.bs.pu.m20.maintain.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.reference.ic.ATPServices;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * @description
 *              请购单可用量更新服务后置调用
 * @scene
 *        请购单审批、整单关闭、删除、整单打开、修改
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午8:56:24
 * @author yanxm5
 */
public class ATPAfterUpdateRule implements IRule<PraybillVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(PraybillVO[] vos) {
    ATPServices.modifyATPAfter(POBillType.PrayBill.getCode(), vos);

  }

}
