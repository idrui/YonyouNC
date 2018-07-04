package nc.bs.pu.m20.maintain.rule.insert;

import java.util.Set;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pubapp.pattern.pub.MapSet;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>保存后进行库存组织与物料的匹配检查
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author liuchx
 * @time 2010-8-2 下午03:03:48
 */
public class MaterialInStorckOrgRule implements IRule<PraybillVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(PraybillVO[] vos) {
    MapSet<String, String> mapset = this.getMapSet(vos);

    if (0 == mapset.size()) {
      return;
    }

    Set<String> orgs = mapset.keySet();
    for (String org : orgs) {
      Set<String> set = mapset.get(org);
      MaterialPubService.checkMaterialVisiabilityInStorckOrg(org,
          set.toArray(new String[set.size()]));
    }

  }

  /**
   * 方法功能描述：把表体物料按照收货库存组织分组
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-2 下午03:28:01
   */
  private MapSet<String, String> getMapSet(PraybillVO[] vos) {
    MapSet<String, String> mapset = new MapSet<String, String>();

    for (PraybillVO vo : vos) {
      PraybillItemVO[] itemVOs = vo.getBVO();
      for (PraybillItemVO itemVO : itemVOs) {
        String pk_arrvstoreorg = itemVO.getPk_org();
        if (StringUtil.isEmptyWithTrim(pk_arrvstoreorg)) {
          continue;
        }

        mapset.put(pk_arrvstoreorg, itemVO.getPk_material());
      }
    }

    return mapset;
  }
}
