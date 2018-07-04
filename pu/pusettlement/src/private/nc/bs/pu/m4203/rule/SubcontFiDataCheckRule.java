package nc.bs.pu.m4203.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m4203.entity.SubcontinFIItemVO;
import nc.vo.pu.m4203.entity.SubcontinFIVO;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 副本保存时数据校验，保证数据的完整性
 * 
 * @since 6.0
 * @version 2011-8-28 下午02:13:11
 * @author 田锋涛
 */
public class SubcontFiDataCheckRule implements IRule<SubcontinFIVO> {

  @Override
  public void process(SubcontinFIVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    for (SubcontinFIVO vo : vos) {
      for (SubcontinFIItemVO item : vo.getChildrenVO()) {
        if (StringUtils.isEmpty(item.getPk_apfinanceorg())) {
          //
        }
      }
    }
  }
}
