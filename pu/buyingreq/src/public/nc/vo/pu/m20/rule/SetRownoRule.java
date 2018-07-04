package nc.vo.pu.m20.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;

/**
 * @description
 *              请购单补全行号规则
 * @scene
 *        计划订单推式保存请购单、生产订单推式保存请购单
 * @param 无
 * @since 6.3
 * @version 2014-10-21 下午2:04:55
 * @author yanxm5
 */
public class SetRownoRule implements IRule<PraybillVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(PraybillVO[] vos) {
    this.setRowno(vos);
  }

  private void setRowno(PraybillVO[] vos) {
    for (PraybillVO vo : vos) {
      PraybillItemVO[] items = vo.getBVO();

      for (int i = 0, len = items.length; i < len; i++) {
        items[i].setCrowno("" + (i + 1) * 10);
      }
    }
  }
}
