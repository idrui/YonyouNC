package nc.vo.pu.m20.rule;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.util.BillHelper;

import org.apache.commons.lang.ArrayUtils;

/**
 * 设置物料基本信息上的主单位
 * 
 * @since 6.5
 * @version 2014-1-20 下午02:31:00
 * @author fanly3
 */
public class SetUnitRule implements IRule<PraybillVO> {

  @Override
  public void process(PraybillVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    // 查询所有物料pk
    Set<String> materialSet = new HashSet<String>();
    for (PraybillVO vo : vos) {
      PraybillItemVO[] itemVOs = vo.getBVO();
      BillHelper<PraybillVO> helper = new BillHelper<PraybillVO>(vo);
      for (int i = 0; i < itemVOs.length; i++) {
        String pk = (String) helper.getBodyValue(i, PraybillItemVO.PK_MATERIAL);
        if (!StringUtil.isEmptyWithTrim(pk)) {
          materialSet.add(pk);
        }
      }
    }

    if (materialSet.isEmpty()) {
      return;
    }
    // 根据物料的VID获取主计量单位
    Map<String, String> unitMap =
        MaterialPubService.queryMaterialMeasdoc(materialSet
            .toArray(new String[materialSet.size()]));

    // 设置主计量单位
    for (PraybillVO vo : vos) {
      PraybillItemVO[] itemVOs = vo.getBVO();
      BillHelper<PraybillVO> helper = new BillHelper<PraybillVO>(vo);
      for (int i = 0; i < itemVOs.length; i++) {
        String pkmaterial =
            (String) helper.getBodyValue(i, PraybillItemVO.PK_MATERIAL);
        String cunit = unitMap.get(pkmaterial);
        helper.setBodyValue(i, PraybillItemVO.CUNITID, cunit);
      }
    }

  }

}
