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
 *              �ο��ɱ����������ĳɱ���ҳǩ<br>
 *              ϵͳ��д����:<br>
 *              �ɹ�����ʱ���ݽ��㵥�ۻ�д��Ӧ�������ĳɱ���ҳǩ�Ĳο��ɱ�
 * @scene
 *        ������ϱ�����㵥
 * @since 6.36
 * @version 2014-11-07 ����00:20:14
 * @author mengjian
 */

public class PCIARefCostPriceUpdateRule implements IRule<SettleBillVO> {
	
	//�ϲ�ͨ�油��NCdp205443983  zhangshqb 2015-09-23
	private boolean isToPCIA = true;
 
 public PCIARefCostPriceUpdateRule(boolean isToPCIA) {
	    this.isToPCIA = isToPCIA;
 }

  @Override
	public void process(SettleBillVO[] vos) {
		if (isToPCIA) {
			// ���³ɱ���Ĳο��ɱ�
			// by mengjian 20141106 �����������ĳɱ���ο��ɱ�
			this.updatePCIACostRegionPrice(vos);
		}
	}

  /**
   * <��ⵥ�ݱ���ID,���㵥����VO>
   * 
   * @param vos
   * @return
   */
  private MapList<String, SettleBillItemVO> getItemMap(SettleBillVO[] vos) {
    MapList<String, SettleBillItemVO> itemMap =
        new MapList<String, SettleBillItemVO>();
    for (SettleBillVO vo : vos) {
      for (SettleBillItemVO item : vo.getChildrenVO()) {
        // ��ֻ������ⵥ��Ʊ�����У�����ҵ��Ҫ��������ȷ��
        if (EnumMatchRowType.StockInvoiceMatch.toInteger().equals(
            item.getFrowtype())) {
          itemMap.put(item.getPk_stock_b(), item);
        }
      }
    }
    return itemMap;
  }

  /**
   * ���������ĳɱ������Ľ��㵥����VO
   * 
   * @param vos
   * @return <�������ĳɱ��򣬽��㵥����VOs>
   */
  private MapList<String, SettleBillItemVO> getPCIACostRegItemMap(
      SettleBillVO[] vos) {
    // ���map
    MapList<String, SettleBillItemVO> crItemMap =
        new MapList<String, SettleBillItemVO>();
    // <��ⵥ����id,���㵥����vos>
    MapList<String, SettleBillItemVO> itemMap = this.getItemMap(vos);
    // ��ⵥ����id
    List<String> pk_stock_bs = this.getPk_stock_bs(vos);
    if (pk_stock_bs.size() == 0) {
      return crItemMap;
    }
    // ��ⵥ����
    String stockType = vos[0].getChildrenVO()[0].getVstockbilltype();
    // <��ⵥ����id,��������ID>
    Map<String, String> liabcenterMap =
        this.getPk_liabcenterMap(pk_stock_bs, stockType);
    Collection<String> pk_apliabcenters = liabcenterMap.values();
    Set<String> set_apliabcenters = new HashSet<String>();
    for (String pk_apliabcenter : pk_apliabcenters) {
      set_apliabcenters.add(pk_apliabcenter);
    }
    // ��������ids
    // this.getPk_apliabcenters(pk_stock_bs, stockType);
    Map<String, String> liatcostrgPks =
        PCCostRegionPubService
            .queryLiatcostrgPkByLiabilityCenterPks(set_apliabcenters
                .toArray(new String[0]));
    if (null == liatcostrgPks) {
      return crItemMap;
    }
    for (Entry<String, List<SettleBillItemVO>> entry : itemMap.entrySet()) {
      // ��ⵥ����-����������-���������ĳɱ���
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
   * @return ��������ids
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
   * ���˵� Ӱ���������ĳɱ���־=false(��,��Ӱ���������ĳɱ���)����ⵥ
   * 
   * @param pk_stock_bs
   * @param stockType
   * @return <��ⵥ����id����������ID>
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
   * �������������ID
   * 
   * @param vos
   * @return
   */
  private List<String> getPk_stock_bs(SettleBillVO[] vos) {
    List<String> pk_stock_bs = new ArrayList<String>();
    for (SettleBillVO vo : vos) {
      for (SettleBillItemVO item : vo.getChildrenVO()) {
        // ��ֻ������ⵥ��Ʊ�����У�����ҵ��Ҫ��������ȷ��
        if (EnumMatchRowType.StockInvoiceMatch.toInteger().equals(
            item.getFrowtype())) {
          pk_stock_bs.add(item.getPk_stock_b());
        }
      }
    }
    return pk_stock_bs;
  }

  /**
   * �����������ĳɱ���ο��ɱ�
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
      // �����������ĳɱ���Ĳο��ɱ�
      if (StringUtils.isNotEmpty(pk_apliabcentercost)) {
        MaterialPCCostPubService.updateCostPriceByPks(pk_materials,
            pk_apliabcentercost,
            costPriceLst.toArray(new UFDouble[costPriceLst.size()]));
      }
    }

  }

}
