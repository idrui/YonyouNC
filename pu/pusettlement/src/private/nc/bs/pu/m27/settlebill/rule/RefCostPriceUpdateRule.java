package nc.bs.pu.m27.settlebill.rule;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.accesor.CostRegionAccessor;
import nc.itf.scmpub.reference.uap.bd.accesor.MaterialAccessor;
import nc.itf.scmpub.reference.uap.bd.material.MaterialCostPubService;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.bd.accessor.IBDData;
import nc.vo.bd.material.cost.MaterialCostVO;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 参考成本：财务组织、成本域页签均有<br>
 * 采购发票不再回写参考成本，改为采购结算时再回写<br>
 * 系统自动回写，也可编辑
 * 系统回写规则:<br>
 * 采购结算时根据结算单价回写对应成本域及成本域所属财务组织页签的参考成本
 * @scene
 * 结算完毕保存结算单
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午4:20:14
 * @author zhangshqb
 */

public class RefCostPriceUpdateRule implements IRule<SettleBillVO> {
	
	// 合并通版补丁NCdp205443983  zhangshqb 2015-09-23
	private boolean isToIA = true;
  
  public RefCostPriceUpdateRule(boolean isToIA) {
	    this.isToIA = isToIA;
  }
	

  @Override
	public void process(SettleBillVO[] vos) {
  	// 无存货核算不更新成本域参考成本
		if (isToIA) {
			// 更新成本域的参考成本
			this.updateCostRegionPrice(vos);
		}
		// 更新财务组织的参考成本
		this.updateFIOrgCostPrice(vos);
	}

  /**
   * 检查物料是否在成本域里
   * 
   * @param pk_materials
   * @param pk_costregion
   */
  private void checkMaterialAssignToCostRegion(String[] pk_materials,
      String pk_costregion) {
    MaterialCostVO[] costVOs =
        MaterialPubService.queryMaterialCostInfoByPks(pk_materials,
            pk_costregion, new String[] {
              MaterialCostVO.PK_MATERIAL
            });
    if (ArrayUtils.isEmpty(costVOs)) {
      this.throwNoMatchErrorMesg(pk_materials, pk_costregion);
    }

    Set<String> matchIds = new HashSet<String>();
    Set<String> noMatchIDs = new HashSet<String>();
    for (MaterialCostVO vo : costVOs) {
      matchIds.add(vo.getPk_material());
    }
    for (String id : pk_materials) {
      if (!matchIds.contains(id)) {
        noMatchIDs.add(id);
      }
    }
    if (noMatchIDs.size() > 0) {
      this.throwNoMatchErrorMesg(
          noMatchIDs.toArray(new String[noMatchIDs.size()]), pk_costregion);
    }

  }

  private MapList<String, SettleBillItemVO> getCostRegItemMap(SettleBillVO[] vos) {
    return this.getOrgItemMap(vos, SettleBillItemVO.PK_COSTREGION);
  }

  private MapList<String, SettleBillItemVO> getFIOrgItemMap(SettleBillVO[] vos) {
    return this.getOrgItemMap(vos, SettleBillItemVO.PK_ORG);
  }

  private MapList<String, SettleBillItemVO> getOrgItemMap(SettleBillVO[] vos,
      String orgKey) {
    MapList<String, SettleBillItemVO> crItemMap =
        new MapList<String, SettleBillItemVO>();
    for (SettleBillVO vo : vos) {
      for (SettleBillItemVO item : vo.getChildrenVO()) {
        // 先只处理入库单发票结算行，如有业务要求，请需求确认
        if (EnumMatchRowType.StockInvoiceMatch.toInteger().equals(
            item.getFrowtype())) {
          crItemMap.put((String) item.getAttributeValue(orgKey), item);
        }
      }
    }
    return crItemMap;
  }

  private void throwNoMatchErrorMesg(String[] pk_materials, String pk_costregion) {

    IBDData[] data = MaterialAccessor.getDocbyPks(pk_materials);
    IBDData costdata = CostRegionAccessor.getDocByPk(pk_costregion);
    StringBuilder msg = new StringBuilder();
    for (int i = 0; i < pk_materials.length; i++) {
      msg.append(NCLangResOnserver.getInstance().getStrByID(
          "4004060_0",
          "04004060-0216",
          null,
          new String[] {
            data[i].getCode(), data[i].getName().toString(),
            costdata.getCode(), costdata.getName().toString()
          })/* 物料【编码：{0},名称：{1}】在成本域【编码：{2},名称：{3}】没有定义!\n */);

    }
    ExceptionUtils.wrappBusinessException(msg.toString());

  }

  private void updateCostRegionPrice(SettleBillVO[] vos) {
    MapList<String, SettleBillItemVO> crItemMap = this.getCostRegItemMap(vos);
    for (Entry<String, List<SettleBillItemVO>> entry : crItemMap.entrySet()) {
      String pk_costregion = entry.getKey();
      List<UFDouble> costPriceLst = new ArrayList<UFDouble>();
      List<String> moidLst = new ArrayList<String>();
      for (SettleBillItemVO item : entry.getValue()) {
        costPriceLst.add(item.getNgoodsprice());
        moidLst.add(item.getPk_srcmaterial());
      }
      String[] pk_materials = moidLst.toArray(new String[moidLst.size()]);
      // 检查物料是否在成本域里
      this.checkMaterialAssignToCostRegion(pk_materials, pk_costregion);
      // 更新成本域的参考成本
      MaterialCostPubService.updateCostPriceByPks(pk_materials, pk_costregion,
          costPriceLst.toArray(new UFDouble[costPriceLst.size()]));
    }
  }

  private void updateFIOrgCostPrice(SettleBillVO[] vos) {
    MapList<String, SettleBillItemVO> crItemMap = this.getFIOrgItemMap(vos);
    for (Entry<String, List<SettleBillItemVO>> entry : crItemMap.entrySet()) {
      String pk_org = entry.getKey();
      List<UFDouble> costPriceLst = new ArrayList<UFDouble>();
      List<String> mvidLst = new ArrayList<String>();
      for (SettleBillItemVO item : entry.getValue()) {
        costPriceLst.add(item.getNgoodsprice());
        mvidLst.add(item.getPk_material());
      }
      // 更新财务组织的参考成本
      MaterialCostPubService.updateCostPriceInFIOrgByPks(
          mvidLst.toArray(new String[mvidLst.size()]), pk_org,
          costPriceLst.toArray(new UFDouble[costPriceLst.size()]));
    }
  }

}
