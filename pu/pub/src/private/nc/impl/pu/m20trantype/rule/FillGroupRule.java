/**
 * 
 */
package nc.impl.pu.m20trantype.rule;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m20trantype.entity.BuyrTranTypeVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version
 * @since
 * @author luojw
 * @time 2014-6-18 ����8:51:08
 */
public class FillGroupRule implements IRule<BuyrTranTypeVO> {

  /*
   * ���෽����д
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(BuyrTranTypeVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    String pk_group = BSContext.getInstance().getGroupID();
    for (BuyrTranTypeVO vo : vos) {
      if (StringUtil.isEmptyWithTrim(vo.getPk_group())) {
        vo.setPk_group(pk_group);
      }
    }
  }

}
