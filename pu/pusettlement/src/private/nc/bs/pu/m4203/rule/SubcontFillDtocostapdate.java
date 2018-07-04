package nc.bs.pu.m4203.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m4203.entity.SubcontinFIItemVO;
import nc.vo.pu.m4203.entity.SubcontinFIVO;
import nc.vo.pub.lang.ICalendar;
import nc.vo.pub.lang.UFDateTime;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-3-22 ÏÂÎç07:47:50
 * @author yinfy
 */

public class SubcontFillDtocostapdate implements IRule<SubcontinFIVO> {

  @Override
  public void process(SubcontinFIVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    for (SubcontinFIVO vo : vos) {
      for (SubcontinFIItemVO item : vo.getChildrenVO()) {
        item.setDtocostapdate(new UFDateTime(item.getDbizdate().toString(),
            ICalendar.BASE_TIMEZONE));
      }
    }
  }
}
