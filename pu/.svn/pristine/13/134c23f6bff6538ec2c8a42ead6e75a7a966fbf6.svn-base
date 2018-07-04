package nc.bs.pu.m27.settlebill.rule;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;

/**
 * 
 * @description
 * 补充物料的主单位
 * @scene
 * 费用结算,结算完毕保存结算单
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午4:13:27
 * @author zhangshqb
 */
public class FillUnitidRule implements IRule<SettleBillVO> {

  @Override
  public void process(SettleBillVO[] vos) {
    Map<String, MaterialVO> materials = this.getMaterialVos(vos);
    for (SettleBillVO vo : vos) {
      SettleBillItemVO[] items = vo.getChildrenVO();
      for (SettleBillItemVO item : items) {
        String pk = item.getPk_material();
        MaterialVO material = materials.get(pk);
        if (material != null) {
          item.setCunitid(material.getPk_measdoc());
        }
      }
    }
  }

  private Map<String, MaterialVO> getMaterialVos(SettleBillVO[] vos) {
    Set<String> pks = new HashSet<String>();
    for (SettleBillVO vo : vos) {
      SettleBillItemVO[] items = vo.getChildrenVO();
      for (SettleBillItemVO item : items) {
        pks.add(item.getPk_material());
      }
    }

    Map<String, MaterialVO> materials = null;
    String[] fields = new String[] {
      MaterialVO.PK_MATERIAL, MaterialVO.PK_MEASDOC
    };
    materials =
        MaterialPubService.queryMaterialBaseInfo(
            pks.toArray(new String[pks.size()]), fields);
    return materials;
  }
}
