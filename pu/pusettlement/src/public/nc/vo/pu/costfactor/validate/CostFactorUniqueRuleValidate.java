/**
 * $文件说明$
 *
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-12 上午11:13:49
 */
package nc.vo.pu.costfactor.validate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.md.model.IBean;
import nc.vo.bd.config.BDUniqueruleItemVO;
import nc.vo.bd.config.BDUniqueruleVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.costfactor.entity.CostfactorHeaderVO;
import nc.vo.pub.SuperVO;
import nc.vo.util.BDUniqueRuleValidate;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>继承uap的唯一性校验，但是批量更新的时候，uap不区分组织。这里自己重写，按组织校验
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-8-12 上午11:13:49
 */
public class CostFactorUniqueRuleValidate extends BDUniqueRuleValidate {

  private static final long serialVersionUID = -413492929255047802L;

  /**
   * 父类方法重写
   *
   * @see nc.vo.util.AbstractUniqueRuleValidate#getBreakRuleofVosSelf(nc.vo.pub.SuperVO[],
   *      java.util.Collection, nc.md.model.IBean)
   */
  @Override
  protected Map<BDUniqueruleVO, List<SuperVO>> getBreakRuleofVosSelf(
      SuperVO[] vos, Collection<BDUniqueruleVO> rules, IBean beanObject) {
    Map<BDUniqueruleVO, List<SuperVO>> result =
        new HashMap<BDUniqueruleVO, List<SuperVO>>();
    for (BDUniqueruleVO rule : rules) {
      List<SuperVO> list = new ArrayList<SuperVO>();
      Map<String, SuperVO> map = new HashMap<String, SuperVO>();
      for (SuperVO vo : vos) {
        BDUniqueruleItemVO[] items = rule.getRuleitems();
        if ((items == null) || (items.length == 0)) {
          throw new RuntimeException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004060_0","04004060-0119")/*@res "唯一性规则设置错误，唯一性规则必须设置唯一性项！"*/);
        }

        StringBuilder itemKeys = new StringBuilder();
        itemKeys.append(((CostfactorHeaderVO) vo).getPk_org());
        boolean hasNullValue = false;
        for (BDUniqueruleItemVO item : items) {
          Object value =
              vo.getAttributeValue(beanObject.getAttributeByID(
                  item.getMdcolumnid()).getName());
          if ((value == null)
              || ((value instanceof String) && StringUtil
                  .isEmptyWithTrim((String) value))) {
            hasNullValue = true;
            break;
          }
          itemKeys.append(value.toString());
        }

        if (hasNullValue) {
          continue;
        }

        if (map.containsKey(itemKeys.toString())
            && !list.contains(map.get(itemKeys.toString()))) {
          list.add(map.get(itemKeys.toString()));
          list.add(vo);
        }
        else if (map.containsKey(itemKeys.toString())) {
          list.add(vo);
        }
        else {
          map.put(itemKeys.toString(), vo);
        }
      }

      if (!list.isEmpty()) {
        result.put(rule, list);
      }
    }
    if (result.isEmpty()) {
      return null;
    }
    return result;
  }

}