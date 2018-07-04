/**
 * $文件说明$
 * 
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-29 上午08:42:21
 */
package nc.vo.pu.m20.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              设置请购单上的日期默认值
 * @scene
 *        计划订单推式保存请购单、生产订单推式保存请购单
 * @param 无
 * @since 6.3
 * @version 2014-10-21 下午2:09:54
 * @author yanxm5
 */
public class CalculateDateRule implements IRule<PraybillVO> {

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
    this.setDates(vos);
  }

  private void setDates(PraybillVO[] vos) {

    UFDate prayDate = AppContext.getInstance().getBusiDate();

    for (PraybillVO vo : vos) {
      if (null == vo) {
        continue;
      }
      if (null == vo.getHVO().getDbilldate()) {
        vo.getHVO().setDbilldate(prayDate);
      }

      new CalculateBodyDays(false).setAdvDays(new BillHelper<PraybillVO>(vo));
    }
  }
}
