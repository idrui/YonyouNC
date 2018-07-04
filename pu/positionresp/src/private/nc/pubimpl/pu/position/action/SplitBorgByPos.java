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
import nc.vo.pu.pub.constant.PUParaValue.po85;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.pattern.pub.MapList;

/**
 * 组织在表体
 * 
 * @since 6.0
 * @version 2010-11-9 下午03:00:36
 * @author wuxla
 */

public class SplitBorgByPos extends AbstractSplitByPos {

  public SplitBorgByPos(AggregatedValueObject vo, String[] keys, int pos) {
    this.setAggVO(vo);
    this.setFieldKeys(keys);
    this.setPositiontype(pos);
    this.init(vo, keys);
  }

  @Override
  public Map<String, String> getItemPostionMapByClass(
      Map<String, String> marclassMap, Map<String, String> positionMap) {
    Map<String, String> map = new HashMap<String, String>();
    for (CircularlyAccessibleValueObject itemVO : this.getAggVO()
        .getChildrenVO()) {
      String pk_org = (String) itemVO.getAttributeValue(this.getFieldKeys()[0]);
      if (null == pk_org) {
        continue;
      }
      String marclass = null;
      String pk_material =
          (String) itemVO.getAttributeValue(this.getFieldKeys()[1]);
      if (pk_material != null) {
        // modified by liuchx 因为根据参数的不同marclassMap的key
        if (po85.base_marclass == this.getPO85()) {
          marclass = marclassMap.get(pk_material);
        }
        else {
          marclass = marclassMap.get(pk_org + "|" + pk_material);
        }

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

    MapList<String, String> mapList = new MapList<String, String>();
    for (CircularlyAccessibleValueObject itemVO : this.getAggVO()
        .getChildrenVO()) {
      String pk_org = (String) itemVO.getAttributeValue(this.getFieldKeys()[0]);
      if (null == pk_org) {
        continue;
      }
      // 采购组织必须同时是库存组织
      if (!OrgUnitPubService.isTypeOf(pk_org, IOrgConst.STOCKORGTYPE)) {
        continue;
      }
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

    if (null == positionMap) {
      int size = this.getAggVO().getChildrenVO().length;
      for (int i = 0; i < size; ++i) {
        list.add(AbstractSplitByPos.NULLKEY);
      }
      return list;
    }

    for (CircularlyAccessibleValueObject itemVO : this.getAggVO()
        .getChildrenVO()) {
      String position = null;
      String pk_org = (String) itemVO.getAttributeValue(this.getFieldKeys()[0]);
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
    Set<String> orgSet = new HashSet<String>();
    Set<String> materialSet = new HashSet<String>();
    for (CircularlyAccessibleValueObject itemVO : vo.getChildrenVO()) {
      String pk_org = (String) itemVO.getAttributeValue(keys[0]);
      if (pk_org != null) {
        orgSet.add(pk_org);
      }
      String pk_material = (String) itemVO.getAttributeValue(keys[1]);
      if (pk_material != null) {
        materialSet.add(pk_material);
      }
    }
    if (orgSet.size() > 0) {
      String[] orgs = orgSet.toArray(new String[orgSet.size()]);
      this.setPk_orgs(orgs);
    }
    if (materialSet.size() > 0) {
      String[] materials = materialSet.toArray(new String[materialSet.size()]);
      this.setPk_materials(materials);
    }
  }

}
