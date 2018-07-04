/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-8 下午07:56:23
 */
package nc.bs.pu.m20.maintain.rule.pub;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.lang.UFDouble;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              请购单设置累计订单数量和生成下游单据次数默认值为（0）
 * @scene
 *        请购单新增、修改
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午9:47:04
 * @author yanxm5
 */
public class FillDownNumRule implements IRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] voArray) {
    if (ArrayUtils.isEmpty(voArray)) {
      return;
    }
    for (PraybillVO aggVO : voArray) {
      // 设置表体数量
      this.setDownNum(aggVO);
    }
  }

  private void setDownNum(PraybillVO vo) {
    PraybillItemVO[] items = vo.getBVO();
    if (ArrayUtils.isEmpty(items)) {
      return;
    }
    for (PraybillItemVO item : items) {

      // 累计订货数量
      if (item.getNaccumulatenum() == null) {
        item.setNaccumulatenum(new UFDouble(0));
      }

      // 生成合同次数
      if (item.getNgenct() == null) {
        item.setNgenct(Integer.valueOf(0));
      }
      // 生成价格审批单次数
      if (item.getNpriceauditbill() == null) {
        item.setNpriceauditbill(Integer.valueOf(0));
      }
      // 生成询报价单次数
      if (item.getNquotebill() == null) {
        item.setNquotebill(Integer.valueOf(0));
      }
    }

  }
}
