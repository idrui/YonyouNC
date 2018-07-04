/**
 * $文件说明$
 * 
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-22 下午09:23:00
 */
package nc.bs.pu.m4201.rule;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IFilterRule;
import nc.vo.ic.m45.entity.PurchaseInBodyVO;
import nc.vo.ic.m45.entity.PurchaseInVO;
import nc.vo.jcom.lang.StringUtil;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>过滤掉VMI的入库单行
 * <li>采购结算和暂估不处理这些行
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhyhang
 * @time 2010-5-22 下午09:23:00
 */
public class FilterVMIRowRule implements IFilterRule<PurchaseInVO> {
  @Override
  public PurchaseInVO[] process(PurchaseInVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return vos;
    }
    List<PurchaseInVO> voList = new ArrayList<PurchaseInVO>();
    for (PurchaseInVO vo : vos) {
      List<PurchaseInBodyVO> bodyList = new ArrayList<PurchaseInBodyVO>();
      PurchaseInBodyVO[] bodys = vo.getBodys();
      if (ArrayUtils.isEmpty(bodys)) {
        continue;
      }
      for (PurchaseInBodyVO body : bodys) {
        if (StringUtil.isEmptyWithTrim(body.getCvmivenderid())) {
          bodyList.add(body);
        }
      }
      if (0 == bodyList.size()) {
        continue;
      }
      vo.setChildrenVO(bodyList.toArray(new PurchaseInBodyVO[bodyList.size()]));
      voList.add(vo);
    }
    return voList.toArray(new PurchaseInVO[voList.size()]);
  }
}
