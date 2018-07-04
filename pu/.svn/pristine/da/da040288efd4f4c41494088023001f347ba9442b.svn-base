package nc.pubimpl.pu.position.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.itf.org.IOrgConst;
import nc.itf.scmpub.reference.uap.bd.material.MaterialStockClassPubService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.pattern.pub.MapList;

/**
 * 组织在表头
 * 
 * @since 6.0
 * @version 2010-11-9 下午02:59:30
 * @author wuxla
 */

public class SplitHorgByPos extends AbstractSplitByPos {

  public SplitHorgByPos(AggregatedValueObject vo, String[] keys, int pos) {
    this.setAggVO(vo);
    this.setFieldKeys(keys);
    this.setPositiontype(pos);
    this.init(vo, keys);
  }

  @Override
  public Map<String, String> getItemPostionMapByClass(
      Map<String, String> marclassMap, Map<String, String> positionMap) {
    if (null == this.getPk_orgs()) {
      return null;
    }
    String pk_org = this.getPk_orgs()[0];

    Map<String, String> map = new HashMap<String, String>();
    for (CircularlyAccessibleValueObject itemVO : this.getAggVO()
        .getChildrenVO()) {
      String marclass = null;
      String pk_material =
          (String) itemVO.getAttributeValue(this.getFieldKeys()[1]);
      if (pk_material != null) {
        marclass = marclassMap.get(pk_material);
      }

      if (null == marclass) {
        String pk_marclass =
            (String) itemVO.getAttributeValue(this.getFieldKeys()[2]);
        marclass = pk_marclass;
      }

      if (null == marclass) {
        continue;
      }
      String pk_position = positionMap.get(pk_org + "|" + marclass);
      if (pk_position != null && pk_material != null) {
        map.put(pk_org + "|" + pk_material, pk_position);
      }
      // 合同为物料分类控制
      else if (pk_position != null) {
        map.put(pk_org + "|" + marclass, pk_position);
      }
    }
    return map;
  }

  @Override
  public Map<String, String> getMarPurClassMap() {
    if (null == this.getPk_orgs()) {
      return null;
    }

    String pk_org = this.getPk_orgs()[0];
    // 采购组织必须同时是库存组织
    if (!OrgUnitPubService.isTypeOf(pk_org, IOrgConst.STOCKORGTYPE)) {
      return null;
    }
    MapList<String, String> mapList = new MapList<String, String>();
    for (CircularlyAccessibleValueObject itemVO : this.getAggVO()
        .getChildrenVO()) {
      String pk_material =
          (String) itemVO.getAttributeValue(this.getFieldKeys()[1]);
      if (pk_material != null) {
        mapList.put(pk_org, pk_material);
      }
    }

    if (0 == mapList.size()) {
      return null;
    }

    Map<String, String> marPuClassMap = new HashMap<String, String>();
    for (Map.Entry<String, List<String>> entry : mapList.toMap().entrySet()) {
      String pk_stockorg = entry.getKey();
      List<String> value = entry.getValue();
      String[] materials = value.toArray(new String[value.size()]);
      Map<String, MaterialStockVO> map =
          MaterialStockClassPubService.queryMaterialStockInfoByPks(materials,
              pk_stockorg, new String[] {
                MaterialStockVO.PK_MARPUCLASS
              });

      for (String material : materials) {
        String pk_marpuclass = map.get(material).getPk_marpuclass();
        marPuClassMap.put(pk_stockorg + "|" + material, pk_marpuclass);
      }
    }

    return marPuClassMap;

  }

  @Override
  public List<String> splitValue(Map<String, String> positionMap) {
    List<String> list = new ArrayList<String>();

    if (null == positionMap || null == this.getPk_orgs()) {
      int size = this.getAggVO().getChildrenVO().length;
      for (int i = 0; i < size; ++i) {
        list.add(AbstractSplitByPos.NULLKEY);
      }
      return list;
    }

    String pk_org = this.getPk_orgs()[0];
    for (CircularlyAccessibleValueObject itemVO : this.getAggVO()
        .getChildrenVO()) {
      String position = null;
      String pk_material =
          (String) itemVO.getAttributeValue(this.getFieldKeys()[1]);
      String pk_marclass = null;
      if (this.getFieldKeys()[2] != null) {
        pk_marclass = (String) itemVO.getAttributeValue(this.getFieldKeys()[2]);
      }

      if (pk_material != null) {
        position = positionMap.get(pk_org + "|" + pk_material);
      }
      else if (pk_marclass != null) {
        // 合同为物料分类控制时，物料为空，直接取物料基本分类
        position = positionMap.get(pk_org + "|" + pk_marclass);
      }

      if (position != null) {
        list.add(position);
      }
      else {
        list.add(AbstractSplitByPos.NULLKEY);
      }
    }
    return list;
  }

  private void init(AggregatedValueObject vo, String[] keys) {
    String pk_org = (String) vo.getParentVO().getAttributeValue(keys[0]);
    if (pk_org != null) {
      this.setPk_orgs(new String[] {
        pk_org
      });
    }

    Set<String> materialSet = new HashSet<String>();
    for (CircularlyAccessibleValueObject itemVO : vo.getChildrenVO()) {
      String pk_material = (String) itemVO.getAttributeValue(keys[1]);
      if (pk_material != null) {
        materialSet.add(pk_material);
      }
    }
    if (materialSet.size() > 0) {
      String[] materials = materialSet.toArray(new String[materialSet.size()]);
      this.setPk_materials(materials);
    }
  }

}
