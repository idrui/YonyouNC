package nc.pubimpl.pu.position.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.itf.org.IOrgConst;
import nc.itf.scmpub.reference.uap.bd.material.MaterialStockClassPubService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;

/**
 * 生产制造：单据只有表头，没有表体
 * 
 * @since 6.0
 * @version 2010-11-9 下午03:00:02
 * @author wuxla
 */

public class SplitMMByPos extends AbstractSplitByPos {

  public SplitMMByPos(AggregatedValueObject vo, String[] keys, int pos) {
    this.setAggVO(vo);
    this.setFieldKeys(keys);
    this.setPositiontype(pos);
    this.init(vo, keys);
  }

  @Override
  public Map<String, String> getItemPostionMapByClass(
      Map<String, String> marclassMap, Map<String, String> positionMap) {
    if (null == this.getPk_orgs() || null == this.getPk_materials()) {
      return new HashMap<String, String>();
    }
    String pk_org = this.getPk_orgs()[0];
    String pk_material = this.getPk_materials()[0];

    String marclass = marclassMap.get(pk_material);
    if (null == marclass) {
      return new HashMap<String, String>();
    }

    String pk_position = positionMap.get(pk_org + "|" + marclass);
    if (pk_position != null && pk_material != null) {
      Map<String, String> map = new HashMap<String, String>();
      map.put(pk_org + "|" + pk_material, pk_position);
      return map;
    }
    return new HashMap<String, String>();
  }

  @Override
  public Map<String, String> getMarPurClassMap() {
    if (null == this.getPk_orgs() || null == this.getPk_materials()) {
      return null;
    }
    String pk_org = this.getPk_orgs()[0];
    // 采购组织必须同时是库存组织
    if (!OrgUnitPubService.isTypeOf(pk_org, IOrgConst.STOCKORGTYPE)) {
      return null;
    }
    String pk_material = this.getPk_materials()[0];

    Map<String, MaterialStockVO> map =
        MaterialStockClassPubService.queryMaterialStockInfoByPks(new String[] {
          pk_material
        }, pk_org, new String[] {
          MaterialStockVO.PK_MARPUCLASS
        });
    Map<String, String> marPuClassMap = new HashMap<String, String>();
    String pk_marpuclass = map.get(pk_material).getPk_marpuclass();
    marPuClassMap.put(pk_org + "|" + pk_material, pk_marpuclass);
    return marPuClassMap;

  }

  @Override
  public List<String> splitValue(Map<String, String> positionMap) {
    List<String> list = new ArrayList<String>();

    if (null == positionMap || null == this.getPk_orgs()
        || null == this.getPk_materials()) {
      list.add(AbstractSplitByPos.NULLKEY);
      return list;
    }

    String pk_org = this.getPk_orgs()[0];
    String pk_material = this.getPk_materials()[0];
    String position = positionMap.get(pk_org + "|" + pk_material);

    if (position != null) {
      list.add(position);
    }
    else {
      list.add(AbstractSplitByPos.NULLKEY);
    }

    return list;
  }

  private void init(AggregatedValueObject vo, String[] keys) {
    CircularlyAccessibleValueObject headerVO = vo.getParentVO();

    String pk_org = (String) headerVO.getAttributeValue(keys[0]);
    if (pk_org != null) {
      this.setPk_orgs(new String[] {
        pk_org
      });
    }

    String pk_material = (String) headerVO.getAttributeValue(keys[1]);
    if (pk_material != null) {
      this.setPk_materials(new String[] {
        pk_material
      });
    }
  }
}
