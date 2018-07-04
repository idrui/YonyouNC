/**
 * $文件说明$
 *
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-29 上午10:22:20
 */
package nc.bs.pu.m20.maintain.rule.pub;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              请购单检查表体数量
 * @scene
 *        请购单新增、修改
 * @param 无
 * @since 6.3
 * @version 2014-10-21 上午9:47:51
 * @author yanxm5
 */
public class NumCheckRule implements IRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] voArray) {
    if (ArrayUtils.isEmpty(voArray)) {
      return;
    }
    for (PraybillVO aggVO : voArray) {
      // 检查表体数量
      this.checknum(aggVO);
    }
  }

  private void checknum(PraybillVO vo) {
    PraybillItemVO[] items = vo.getBVO();
    if (ArrayUtils.isEmpty(items)) {
      return;
    }
    for (PraybillItemVO item : items) {

      // 数量不为空，不等0
    	
      /*
       * if (item.getNastnum() == null || item.getNastnum().doubleValue() <= 0) {
       * add by wandl 项目问题合并（已做需求变更），支持请购单录入负数
       */
    	if (item.getNastnum() == null || item.getNastnum().doubleValue() == 0) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004020_0", "04004020-0028")/*
                                                                     * @res
                                                                     * "数量不能为空，或等于零"
                                                                     */);
      }
    }

  }

}
