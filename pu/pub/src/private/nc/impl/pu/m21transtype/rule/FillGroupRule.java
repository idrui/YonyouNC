package nc.impl.pu.m21transtype.rule;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>给集团赋值规则
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-7-22 上午11:01:26
 */
public class FillGroupRule implements IRule<PoTransTypeVO> {

  @Override
  public void process(PoTransTypeVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    String pk_group = BSContext.getInstance().getGroupID();
    for (PoTransTypeVO vo : vos) {
      if (StringUtil.isEmptyWithTrim(vo.getPk_group())) {
        vo.setPk_group(pk_group);
      }
    }

  }
}
