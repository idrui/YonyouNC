/**
 * $文件说明$
 * 
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-27 上午11:12:35
 */
package nc.bs.pu.m20.maintain.rule.pub;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.rule.SetPeptRule;
import nc.vo.pu.pub.util.BillHelper;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              请购单设置计划员和计划部门规则
 * @scene
 *        计划订单推式保存请购单、生产订单推式保存请购单
 * @param 无
 * @since 6.3
 * @version 2014-10-21 下午2:04:29
 * @author yanxm5
 */
public class SetPsnAndDeptRule implements IRule<PraybillVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(PraybillVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    this.setPsnAndDept(vos);
  }

  private void setPsnAndDept(PraybillVO[] vos) {
    String userid = InvocationInfoProxy.getInstance().getUserId();

    for (PraybillVO vo : vos) {
      new SetPeptRule(new BillHelper<PraybillVO>(vo),
          PraybillHeaderVO.PK_PLANPSN, PraybillHeaderVO.PK_PLANDEPT,
          PraybillHeaderVO.PK_PLANDEPT_V, userid, vo.getHVO().getPk_org())
          .setPsnAndDept();
    }
  }
}
