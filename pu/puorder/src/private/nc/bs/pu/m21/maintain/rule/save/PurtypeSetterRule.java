package nc.bs.pu.m21.maintain.rule.save;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.pu.reference.ct.Z2CTServices;
import nc.pubitf.scmbd.vrm.vm.IVendorMaterialQuery;
import nc.pubitf.scmf.pu.cenpurule.pu.ICenPuRuleQueryForPu;
import nc.vo.ct.purdaily.entity.CtPuVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.scmf.pu.cenpurule.entity.CenPuRuleItemVO;
import nc.vo.scmf.pu.cenpurule.entity.ControlType;

/**
 * @description
 *              ���ö�������Ĳɹ����͡�����ǰ����
 * @scene
 *        �ɹ����������޸�
 * @param ��
 * @since 6.3
 * @version 2014-10-22 ����2:48:32
 * @author luojw
 */
public class PurtypeSetterRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    Map<String, CenPuRuleItemVO> cenpuMap = this.getCenpuRuleMap(vos);
    if (null == cenpuMap || cenpuMap.size() == 0) {
      for (OrderVO vo : vos) {
        for (OrderItemVO itemVO : vo.getBVO()) {
          itemVO.setFneedpurtype(null);
          itemVO.setFactpurtype(null);
          itemVO.setPk_cenpurule_b(null);
        }
      }
      return;
    }

    this.setPurtype(vos, cenpuMap);
  }

  /**
   * ���ݲɹ���������VO�ĺ�ͬ��Ϣ���˲ɹ���������VO�б�
   * �������ͬΪ�գ�������ʵִ�ɹ�����Ϊ��ֵ�����򽫶�������VO������������з��ء�
   * 
   * @param ctList ������ɹ���������VO��
   * @return key-��ͬID��value-list�д�ɹ���������VO
   */
  private MapList<String, OrderItemVO> filterOrderItemVOByCt(
      List<OrderItemVO> ctList) {
    MapList<String, OrderItemVO> maplist = new MapList<String, OrderItemVO>();
    for (OrderItemVO itemVO : ctList) {
      String ccontractid = itemVO.getCcontractid();
      if (null == ccontractid) {
        itemVO.setFactpurtype(null);
      }
      else {
        maplist.put(ccontractid, itemVO);
      }
    }
    return maplist;
  }

  /**
   * ��ȡ���ɿ��ƹ��򼯺�
   * ���ݲɹ���֯������OID����������֯������ϸ����ԭ���ѯ���ɿ��ƹ���
   * 
   * @param vos �ɹ���������
   * @return
   */
  private Map<String, CenPuRuleItemVO> getCenpuRuleMap(OrderVO[] vos) {
    Set<String> puorgSet = new HashSet<String>();
    List<String> matList = new ArrayList<String>();
    List<String> stockorgList = new ArrayList<String>();
    for (OrderVO vo : vos) {
      String pk_org = vo.getHVO().getPk_org();
      for (OrderItemVO itemVO : vo.getBVO()) {
        String pk_reqstoorg = itemVO.getPk_reqstoorg();
        // �ӱ���������֯Ϊ�ղ�����
        if (null == pk_reqstoorg) {
          continue;
        }
        stockorgList.add(pk_reqstoorg);
        puorgSet.add(pk_org);
        matList.add(itemVO.getPk_srcmaterial());
      }
    }
    // ���вɹ�������������֯Ϊ��ֱ�ӷ���
    if (stockorgList.size() == 0) {
      return null;
    }

    String[] puorgs = puorgSet.toArray(new String[puorgSet.size()]);
    String[] mats = matList.toArray(new String[matList.size()]);
    String[] stockorgs = stockorgList.toArray(new String[stockorgList.size()]);
    ICenPuRuleQueryForPu service =
        NCLocator.getInstance().lookup(ICenPuRuleQueryForPu.class);
    try {
      return service.queryCenpuruleItem(puorgs, mats, stockorgs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  /**
   * ��ȡ��Ӧ�����Ϲ�ϵ
   * 
   * @param vrmList Ӧִ�ɹ������ǹ�Ӧ����¼�Ĳɹ���������VO�б�
   * @param cenpuMap ���ɿ��ƹ�����ϸ���ϡ�key-���ɿ��ƹ�����ϸ������value-���ɿ��ƹ�����ϸVO
   * @return ��Ӧ�����Ϲ�ϵ���ϣ�value-�ɹ���֯�����ϡ���Ӧ��
   */
  private Set<String> getvrmset(List<OrderItemVO> vrmList,
      Map<String, CenPuRuleItemVO> cenpuMap) {
    // ��Ӧ��
    Set<String> supset = new HashSet<String>();
    // �ɹ���֯
    Set<String> orgset = new HashSet<String>();
    // ����
    Set<String> matset = new HashSet<String>();
    for (OrderItemVO itemVO : vrmList) {
      supset.add(itemVO.getPk_supplier());
      CenPuRuleItemVO ruleitemvo = cenpuMap.get(itemVO.getPk_cenpurule_b());
      if (ruleitemvo != null) {
        orgset.add(ruleitemvo.getPk_org());
      }
      matset.add(itemVO.getPk_srcmaterial());
    }
    String[] sups = supset.toArray(new String[supset.size()]);
    String[] orgs = orgset.toArray(new String[orgset.size()]);
    String[] mats = matset.toArray(new String[matset.size()]);
    // ���ݹ�Ӧ��id��ѯ��Ӧ�����Ϲ�ϵ������ϵΪ�գ�ֱ�ӷ���
    MapList<String, String> vrmMap = null;
    IVendorMaterialQuery service =
        NCLocator.getInstance().lookup(IVendorMaterialQuery.class);
    try {
      vrmMap = service.queryByVendorMaterialOrgIds(sups, mats, orgs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    if (vrmMap == null || vrmMap.toMap().isEmpty()) {
      return null;
    }
    Set<String> set = new HashSet<String>();
    for (Entry<String, List<String>> en : vrmMap.entrySet()) {
      for (String pk_supplier : en.getValue()) {
        set.add(en.getKey() + pk_supplier);
      }
    }
    return set;
  }

  /**
   * ����Ӧִ�ɹ������Ǽ��ɺ�ͬ�Ĳɹ����������ʵִ�ɹ�����
   * ����ͬ�Ĳɹ���֯�ͼ��ɿ��ƹ����Ӧ�Ĳɹ���֯��ȣ����Ҽ��ɿ��ƹ���ĺ�ͬ����Ϊ�ջ����ͬ�Ľ���������ȣ�
   * ������ʵִ�ɹ�����Ϊ���ɺ�ͬ������Ϊ�ա�
   * 
   * @param ctList Ӧִ�ɹ������Ǽ��ɺ�ͬ�Ĳɹ���������VO�б�
   * @param ruleitemmap ���ɿ��ƹ�����ϸ���ϡ�key-���ɿ��ƹ�����ϸ������value-���ɿ��ƹ�����ϸVO
   */
  private void setCtActtype(List<OrderItemVO> ctList,
      Map<String, CenPuRuleItemVO> ruleitemmap) {
    if (ctList.size() == 0) {
      return;
    }
    // ��ȡ���ݲɹ���������VO�ĺ�ͬ��Ϣ���˲ɹ���������VO�б�
    MapList<String, OrderItemVO> maplist = this.filterOrderItemVOByCt(ctList);
    if (maplist.size() == 0) {
      return;
    }

    // ��ȡ�ɹ���ͬ�б�
    String[] ccontractids =
        maplist.keySet().toArray(new String[maplist.keySet().size()]);
    Map<String, CtPuVO> ctmap = Z2CTServices.queryOrgTypeByPks(ccontractids);

    for (Entry<String, List<OrderItemVO>> entry : maplist.entrySet()) {
      // ��ͬID
      String key = entry.getKey();
      List<OrderItemVO> valuelist = entry.getValue();
      // ��ͬVO
      CtPuVO ctpuvo = ctmap.get(key);
      // ��ͬ�еĲɹ���֯
      String pk_org = ctpuvo.getPk_org();
      // ��ͬ�еĽ�������
      String ctrantypeid = ctpuvo.getCtrantypeid();
      for (OrderItemVO orderitemvo : valuelist) {
        CenPuRuleItemVO ruleitemvo =
            ruleitemmap.get(orderitemvo.getPk_cenpurule_b());
        // ���ɿ��ƹ����еĲɹ���֯
        String rulepkorg = ruleitemvo.getPk_org();
        // ���ɿ��ƹ����еĺ�ͬ����
        String ruletrantypeid = ruleitemvo.getCtrantypeid();
        if (pk_org.equals(rulepkorg)
            && (ruletrantypeid == null || ctrantypeid.equals(ruletrantypeid))) {

          orderitemvo.setFactpurtype(ControlType.CONTRACT.toInteger());
        }
        else {
          orderitemvo.setFactpurtype(null);
        }
      }

    }
  }

  /**
   * ���òɹ���������VO�Ĳɹ�����
   * 
   * @param vos
   * @param cenpuMap
   */
  private void setPurtype(OrderVO[] vos, Map<String, CenPuRuleItemVO> cenpuMap) {
    // �ɹ�������ϸ�б�-Ӧִ�ɹ�����-���ɺ�ͬ
    List<OrderItemVO> ctList = new ArrayList<OrderItemVO>();
    // �ɹ�������ϸ�б�-Ӧִ�ɹ�����-��Ӧ����¼
    List<OrderItemVO> vrmList = new ArrayList<OrderItemVO>();
    for (OrderVO vo : vos) {
      String pk_org = vo.getHVO().getPk_org();
      for (OrderItemVO itemVO : vo.getBVO()) {
        String pk_reqstoorg = itemVO.getPk_reqstoorg();
        // �ӱ���������֯Ϊ�ղ�����
        if (null == pk_reqstoorg) {
          continue;
        }
        String pk_srcmaterial = itemVO.getPk_srcmaterial();
        // ȡ��ǰ�ɹ��ӱ��¼��Ӧ�ļ��ɿ��ƹ�����û�ж�Ӧ���򣬲�����
        CenPuRuleItemVO cenpuvo =
            cenpuMap.get(pk_org + pk_srcmaterial + pk_reqstoorg);
        if (null == cenpuvo) {
          itemVO.setFneedpurtype(null);
          itemVO.setFactpurtype(null);
          itemVO.setPk_cenpurule_b(null);
          continue;
        }
        Integer fctrltype = cenpuvo.getFctrltype();
        // ����Ӧִ�ɹ�����
        itemVO.setFneedpurtype(fctrltype);
        // �ɹ������ӱ��¼��Ӧ�ļ��ɿ��ƹ�������
        itemVO.setPk_cenpurule_b(cenpuvo.getPk_cenpurule_b());

        if (ControlType.CONTRACT.toInt() == fctrltype.intValue()) {
          ctList.add(itemVO);
        }
        else {
          vrmList.add(itemVO);
        }
      }
    }

    // ���켯�ɿ��ƹ�����ϸ���ϡ�key-���ɿ��ƹ�����ϸ������value-���ɿ��ƹ�����ϸVO
    CenPuRuleItemVO[] ruleitemvos =
        cenpuMap.values()
            .toArray(new CenPuRuleItemVO[cenpuMap.values().size()]);
    Map<String, CenPuRuleItemVO> ruleitemmap =
        new HashMap<String, CenPuRuleItemVO>();
    for (CenPuRuleItemVO ruleitemvo : ruleitemvos) {
      ruleitemmap.put(ruleitemvo.getPk_cenpurule_b(), ruleitemvo);
    }
    this.setCtActtype(ctList, ruleitemmap);
    this.setVrmActtype(vrmList, ruleitemmap);
  }

  /**
   * ����Ӧִ�ɹ������ǹ�Ӧ����¼�Ĳɹ����������ʵִ�ɹ�����
   * ����ɹ���������VO�͹�Ӧ�����Ϲ�ϵ��ƥ�䣬������ʵִ�ɹ�����Ϊ��Ӧ�̣���������Ϊ�ա�
   * 
   * @param vrmList Ӧִ�ɹ������ǹ�Ӧ����¼�Ĳɹ���������VO�б�
   * @param cenpuMap ���ɿ��ƹ�����ϸ���ϡ�key-���ɿ��ƹ�����ϸ������value-���ɿ��ƹ�����ϸVO
   */
  private void setVrmActtype(List<OrderItemVO> vrmList,
      Map<String, CenPuRuleItemVO> cenpuMap) {
    if (vrmList.size() == 0) {
      return;
    }
    Set<String> set = this.getvrmset(vrmList, cenpuMap);
    if (null == set || set.size() == 0) {
      for (OrderItemVO itemVO : vrmList) {
        itemVO.setFactpurtype(null);
      }
      return;
    }
    // �����ɹ���������VO������͹�Ӧ�����Ϲ�ϵ��ƥ�䣬������ʵִ�ɹ�����Ϊ��Ӧ�̣���������Ϊ��
    for (OrderItemVO itemVO : vrmList) {
      CenPuRuleItemVO ruleitemvo = cenpuMap.get(itemVO.getPk_cenpurule_b());
      String pk_org = ruleitemvo != null ? ruleitemvo.getPk_org() : null;
      String pk_srcmaterial = itemVO.getPk_srcmaterial();
      String pk_supplier = itemVO.getPk_supplier();
      if (set.contains(pk_srcmaterial + pk_org + pk_supplier)) {
        itemVO.setFactpurtype(ControlType.VENDORLIST.toInteger());
      }
      else {
        itemVO.setFactpurtype(null);
      }
    }
  }

}
