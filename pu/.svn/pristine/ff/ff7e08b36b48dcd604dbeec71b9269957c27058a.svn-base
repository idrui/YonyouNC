
package nc.vo.pu.m4t.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.scmpub.util.VOFieldLengthChecker;

/**
 * 
 * @description
 * 数值型属性极限值检查:各个数量金额字段是否超最大值
 * @scene
 * 期初暂估单保存
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-9-8 上午10:05:35
 * @author wuxla
 */

public class InitialEstNumValueLmtChkRule implements IRule<InitialEstVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(InitialEstVO[] vos) {
    VOFieldLengthChecker.checkVOFieldsLength(vos);
  }

}
