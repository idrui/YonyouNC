package nc.bs.pu.m27.settlebill.rule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.m27.entity.SettleBillVO;
import nc.vo.pu.m27.enumeration.EnumMatchRowType;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmpub.res.billtype.ICBillType;
import nc.vo.scmpub.res.billtype.POBillType;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPCCostPubService;
import nc.itf.scmpub.reference.uap.org.PCCostRegionPubService;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.rule.IRule;

/**
 * @description
 *              参考成本：利润中心成本域页签<br>
 *              系统回写规则:<br>
 *              采购结算时根据结算单价回写对应利润中心成本域页签的参考成本
 * @scene
 *        结算完毕保存结算单
 * @since 6.36
 * @version 2014-11-07 上午00:20:14
 * @author mengjian
 */

public class PCIARefCostPriceUpdateRule implements IRule<SettleBillVO> {
	
	//合并通版补丁NCdp205443983  zhangshqb 2015-09-23
	private boolean isToPCIA = true;
 
 public PCIARefCostPriceUpdateRule(boolean isToPCIA) {
	    this.isToPCIA = isToPCIA;
 }

  @Override
	public void process(SettleBillVO[] vos) {
		if (isToPCIA) {
			// 更新成本域的参考成本
			// by mengjian 20141106 更新利润中心成本域参考成本
			this.updatePCIACostRegionPrice(vos);
		}
	}

  /**
   * <入库单据表体ID,结算单表体VO>
   * 
   * @param vos
   * @return
   */
  private MapList<String, SettleBillItemVO> getItemMap(SettleBillVO[] vos) {
    MapList<String, SettleBillItemVO> itemMap =
        new MapList<String, SettleBillItemVO>();
    for (SettleBillVO vo : vos) {
      for (SettleBillItemVO item : vo.getChildrenVO()) {
        // 先只处理入库单发票结算行，如有业务要求，请需求确认
        if (EnumMatchRowType.StockInvoiceMatch.toInteger().equals(
            item.getFrowtype())) {
          itemMap.put(item.getPk_stock_b(), item);
        }
      }
    }
    return itemMap;
  }

  /**
   * 按利润中心成本域分组的结算单表体VO
   * 
   * @param vos
   * @return <利润中心成本域，结算单表体VOs>
   */
  private MapList<String, SettleBillItemVO> getPCIACostRegItemMap(
      SettleBillVO[] vos) {
    // 结果map
    MapList<String, SettleBillItemVO> crItemMap =
        new MapList<String, SettleBillItemVO>();
    // <入库单表体id,结算单表体vos>
    MapList<String, SettleBillItemVO> itemMap = this.getItemMap(vos);
    // 入库单表体id
    List<String> pk_stock_bs = this.getPk_stock_bs(vos);
    if (pk_stock_bs.size() == 0) {
      return crItemMap;
    }
    // 入库单类型
    String stockType = vos[0].getChildrenVO()[0].getVstockbilltype();
    // <入库单表体id,利润中心ID>
    Map<String, String> liabcenterMap =
        this.getPk_liabcenterMap(pk_stock_bs, stockType);
    Collection<String> pk_apliabcenters = liabcenterMap.values();
    Set<String> set_apliabcenters = new HashSet<String>();
    for (String pk_apliabcenter : pk_apliabcenters) {
      set_apliabcenters.add(pk_apliabcenter);
    }
    // 利润中心ids
    // this.getPk_apliabcenters(pk_stock_bs, stockType);
    Map<String, String> liatcostrgPks =
        PCCostRegionPubService
            .queryLiatcostrgPkByLiabilityCenterPks(set_apliabcenters
                .toArray(new String[0]));
    if (null == liatcostrgPks) {
      return crItemMap;
    }
    for (Entry<String, List<SettleBillItemVO>> entry : itemMap.entrySet()) {
      // 入库单主键-》利润中心-》利润中心成本域
      String liatcostrgPk =
          liatcostrgPks.get(liabcenterMap.get(entry.getKey()));
      for (SettleBillItemVO itemvo : entry.getValue()) {
        crItemMap.put(liatcostrgPk, itemvo);
      }
    }
    return crItemMap;
  }

  /**
   * @param pk_stock_bs
   * @param stockType
   * @return 利润中心ids
   */
  private Set<String> getPk_apliabcenters(List<String> pk_stock_bs,
      String stockType) {
    Set<String> pk_apliabcenters = new HashSet<String>();
    if (POBillType.InitEstimate.getCode().equals(stockType)) {
      VOQuery<InitialEstItemVO> query =
          new VOQuery<InitialEstItemVO>(InitialEstItemVO.class, new String[] {
            InitialEstItemVO.PK_APLIABCENTER, InitialEstItemVO.PK_INITIALEST_B
          });
      InitialEstItemVO[] puritemvos =
          query.query(pk_stock_bs.toArray(new String[0]));
      for (InitialEstItemVO itemvo : puritemvos) {
        String pk_apliabcenter = itemvo.getPk_apliabcenter();
        if (null != pk_apliabcenter) {
          pk_apliabcenters.add(pk_apliabcenter);
        }
      }
    }
    if (ICBillType.PurchaseIn.getCode().equals(stockType)) {
      VOQuery<PurchaseinFIItemVO> query =
          new VOQuery<PurchaseinFIItemVO>(PurchaseinFIItemVO.class,
              new String[] {
                PurchaseinFIItemVO.PK_APLIABCENTER,
                PurchaseinFIItemVO.PK_ARRLIABCENTER,
                PurchaseinFIItemVO.PK_STOCKPS_B
              });
      PurchaseinFIItemVO[] puritemvos =
          query.query(pk_stock_bs.toArray(new String[0]));
      for (PurchaseinFIItemVO itemvo : puritemvos) {
        String pk_apliabcenter = itemvo.getPk_apliabcenter();
        if (null != pk_apliabcenter) {
          pk_apliabcenters.add(pk_apliabcenter);
        }
        else {
          pk_apliabcenters.add(itemvo.getPk_arrliabcenter());
        }
      }
    }
    return pk_apliabcenters;
  }

  /**
   * 过滤掉 影响利润中心成本标志=false(即,不影响利润中心成本的)的入库单
   * 
   * @param pk_stock_bs
   * @param stockType
   * @return <入库单表体id，利润中心ID>
   */
  private Map<String, String> getPk_liabcenterMap(List<String> pk_stock_bs,
      String stockType) {
    Map<String, String> liabcenterMap = new HashMap<String, String>();
    if (POBillType.InitEstimate.getCode().equals(stockType)) {
      VOQuery<InitialEstItemVO> query =
          new VOQuery<InitialEstItemVO>(InitialEstItemVO.class, new String[] {
            InitialEstItemVO.PK_APLIABCENTER, InitialEstItemVO.PK_INITIALEST_B,
            InitialEstItemVO.BAFFECTPCCOST
          });
      InitialEstItemVO[] puritemvos =
          query.query(pk_stock_bs.toArray(new String[0]));
      if (null == puritemvos) {
        return liabcenterMap;
      }
      for (InitialEstItemVO itemvo : puritemvos) {
        String pk_apliabcenter = itemvo.getPk_apliabcenter();
        UFBoolean baffectpccost = itemvo.getBaffectpccost();
        if (baffectpccost != null && baffectpccost.booleanValue()
            && null != pk_apliabcenter) {
          liabcenterMap.put(itemvo.getPk_initialest_b(), pk_apliabcenter);
        }
      }
    }
    if (ICBillType.PurchaseIn.getCode().equals(stockType)) {
      VOQuery<PurchaseinFIItemVO> query =
          new VOQuery<PurchaseinFIItemVO>(PurchaseinFIItemVO.class,
              new String[] {
                PurchaseinFIItemVO.PK_APLIABCENTER,
                PurchaseinFIItemVO.PK_ARRLIABCENTER,
                PurchaseinFIItemVO.PK_STOCKPS_B,
                PurchaseinFIItemVO.BAFFECTPCCOST
              });
      PurchaseinFIItemVO[] puritemvos =
          query.query(pk_stock_bs.toArray(new String[0]));
      if (null == puritemvos) {
        return liabcenterMap;
      }
      for (PurchaseinFIItemVO itemvo : puritemvos) {
        String pk_apliabcenter = itemvo.getPk_apliabcenter();
        UFBoolean baffectpccost = itemvo.getBaffectpccost();
        if (baffectpccost != null && baffectpccost.booleanValue()) {
          if (null != pk_apliabcenter) {
            liabcenterMap.put(itemvo.getPk_stockps_b(), pk_apliabcenter);
          }
          else {
            liabcenterMap.put(itemvo.getPk_stockps_b(),
                itemvo.getPk_arrliabcenter());
          }
        }
      }
    }
    return liabcenterMap;
  }

  /**
   * 获得所有入库表体ID
   * 
   * @param vos
   * @return
   */
  private List<String> getPk_stock_bs(SettleBillVO[] vos) {
    List<String> pk_stock_bs = new ArrayList<String>();
    for (SettleBillVO vo : vos) {
      for (SettleBillItemVO item : vo.getChildrenVO()) {
        // 先只处理入库单发票结算行，如有业务要求，请需求确认
        if (EnumMatchRowType.StockInvoiceMatch.toInteger().equals(
            item.getFrowtype())) {
          pk_stock_bs.add(item.getPk_stock_b());
        }
      }
    }
    return pk_stock_bs;
  }

  /**
   * 更新利润中心成本域参考成本
   * 
   * @param vos
   */
  private void updatePCIACostRegionPrice(SettleBillVO[] vos) {
    MapList<String, SettleBillItemVO> crItemMap =
        this.getPCIACostRegItemMap(vos);
    for (Entry<String, List<SettleBillItemVO>> entry : crItemMap.entrySet()) {
      String pk_apliabcentercost = entry.getKey();
      List<UFDouble> costPriceLst = new ArrayList<UFDouble>();
      List<String> moidLst = new ArrayList<String>();
      for (SettleBillItemVO item : entry.getValue()) {
        costPriceLst.add(item.getNgoodsprice());
        moidLst.add(item.getPk_srcmaterial());
      }
      String[] pk_materials = moidLst.toArray(new String[moidLst.size()]);
      // 更新利润中心成本域的参考成本
      if (StringUtils.isNotEmpty(pk_apliabcentercost)) {
        MaterialPCCostPubService.updateCostPriceByPks(pk_materials,
            pk_apliabcentercost,
            costPriceLst.toArray(new UFDouble[costPriceLst.size()]));
      }
    }

  }

}
