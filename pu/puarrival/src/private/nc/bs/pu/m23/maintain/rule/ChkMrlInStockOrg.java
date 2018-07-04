package nc.bs.pu.m23.maintain.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.AggregatedValueObject;

/**
 * 
 * @description
 * 新增保存、修改保存都会用到此规则,本类主要完成以下功能：
 * 检查物料与库存组织是否匹配
 * @scene
 * 
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-1-14 下午03:42:46
 * @author hanbin
 */

public class ChkMrlInStockOrg implements IRule<ArriveVO> {

  @Override
  public void process(ArriveVO[] voArray) {
    for (ArriveVO aggVO : voArray) {
      // 检查物料与库存组织是否匹配
      this.chkMrlInStockOrg(aggVO);
    }
  }

  /**
   * 方法功能描述：检查物料与库存组织是否匹配
   * <p>
   * <b>参数说明</b>
   * 
   * @param aggVO
   * @return <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-1-18 下午09:44:46
   */
  private void chkMrlInStockOrg(ArriveVO aggVO) {

    // 物料主键数组
    AggregatedValueObject[] vos = new AggregatedValueObject[1];
    vos[0] = aggVO;
    String[] mrls =
        (String[]) AggVOUtil.getDistinctItemFieldArray(vos,
            ArriveItemVO.PK_MATERIAL, String.class);
    // 库存组织
    String org = aggVO.getHVO().getPk_org();
    MaterialPubService.checkMaterialVisiabilityInStorckOrg(org, mrls);
  }
}
