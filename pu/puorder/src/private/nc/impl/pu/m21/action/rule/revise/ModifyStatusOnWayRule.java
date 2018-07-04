package nc.impl.pu.m21.action.rule.revise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.data.vo.VODelete;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.pubitf.pu.m21transtype.IPoTransTypeQuery;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.StatusOnWayItemVO;
import nc.vo.pu.m21.pub.TrantypeUtil;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pu.m21transtype.enumeration.OnwayStatus;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.bill.BillRowCompare;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              采购订单修订数量时,改变途状态表中数据
 * @scene
 *        采购订单修订
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午10:47:17
 * @author luojw
 */
public class ModifyStatusOnWayRule implements ICompareRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos, OrderVO[] originVOs) {
    if (ArrayUtils.isEmpty(vos) || ArrayUtils.isEmpty(originVOs)) {
      return;
    }
    BillRowCompare tool = new BillRowCompare(vos, originVOs);
    List<ISuperVO> insertList = tool.getInsertList();
    List<ISuperVO> deleteList = tool.getDeleteList();
    List<ISuperVO> updateList = tool.getUpdateList();
    List<ISuperVO> updateOriginList = tool.getUpdateOriginList();

    Map<String, PoTransTypeVO> trantypeMap = this.getTrantypeMap(vos);

    if (!CollectionUtils.isEmpty(insertList)) {
      this.insertStatus(insertList, trantypeMap);
    }

    if (!CollectionUtils.isEmpty(deleteList)) {
      this.deleteStatus(deleteList);
    }
    if (!CollectionUtils.isEmpty(updateList)
        && !CollectionUtils.isEmpty(updateOriginList)) {
      this.updateStatus(updateList, updateOriginList);
    }
  }

  private void deleteStatus(List<ISuperVO> deleteList) {
    List<String> list = new ArrayList<String>();
    for (ISuperVO superVO : deleteList) {
      OrderItemVO itemVO = (OrderItemVO) superVO;
      list.add(itemVO.getPk_order_b());
    }

    String[] bids = list.toArray(new String[list.size()]);
    VOQuery<StatusOnWayItemVO> query =
        new VOQuery<StatusOnWayItemVO>(StatusOnWayItemVO.class);
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_12.name());
    String cond =
        builder.buildSQL(" and " + StatusOnWayItemVO.PK_ORDER_B, bids);
    StatusOnWayItemVO[] vos = query.query(cond, null);
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    VODelete<StatusOnWayItemVO> delete = new VODelete<StatusOnWayItemVO>();
    delete.delete(vos);
  }

  private void getStatusOnWay(OrderItemVO itemVO, List<StatusOnWayItemVO> list,
      Map<String, PoTransTypeVO> trantypeMap) {
    String pk_order = itemVO.getPk_order();
    PoTransTypeVO transTypeVO = trantypeMap.get(pk_order);
    if (transTypeVO == null) {
      ExceptionUtils.wrappBusinessException(NCLangResOnserver.getInstance()
          .getStrByID("4004030_0", "04004030-0234", null, new String[] {})/*
                                                                           * 采购订单交易类型
                                                                           * {0}
                                                                           * 扩展表没有数据
                                                                           * ，
                                                                           * 请检查
                                                                           */);
      return;
    }
    Integer iapproveaft =
        TrantypeUtil.getInstance().getNextStatus(
            OnwayStatus.STATUS_AUDIT.toInt(), transTypeVO);

    // 如果下一个订单状态为空，不插入数据
    if (iapproveaft == null) {
      return;
    }

    StatusOnWayItemVO statusOnWayItemVO = new StatusOnWayItemVO();
    // 此处是设置在途单据日期
    // statusOnWayItemVO.setDbilldate(headerVO.getDbilldate());
    statusOnWayItemVO.setDplanarrvdate(itemVO.getDplanarrvdate());
    statusOnWayItemVO.setFonwaystatus(iapproveaft);
    statusOnWayItemVO.setIsoperated(UFBoolean.FALSE);
    statusOnWayItemVO.setNonwaynum(itemVO.getNnum());
    // 主数量为最大可操作数量
    statusOnWayItemVO.setNmaxhandlenum(itemVO.getNnum());
    statusOnWayItemVO.setPk_group(itemVO.getPk_group());
    statusOnWayItemVO.setPk_order(itemVO.getPk_order());
    statusOnWayItemVO.setPk_order_b(itemVO.getPk_order_b());
    statusOnWayItemVO.setPk_org(itemVO.getPk_org());
    statusOnWayItemVO.setPk_org_v(itemVO.getPk_org_v());
    // 此处是设置在途单据号
    list.add(statusOnWayItemVO);
  }

  private Map<String, PoTransTypeVO> getTrantypeMap(OrderVO[] vos) {
    Set<String> set = new HashSet<String>();
    for (OrderVO vo : vos) {
      set.add(vo.getHVO().getCtrantypeid());
    }

    Map<String, PoTransTypeVO> trantypeMap =
        new HashMap<String, PoTransTypeVO>();
    String[] ids = set.toArray(new String[set.size()]);
    IPoTransTypeQuery service =
        NCLocator.getInstance().lookup(IPoTransTypeQuery.class);
    try {
      Map<String, PoTransTypeVO> map = service.queryAttrByIDs(ids);
      if (map == null) {
        return trantypeMap;
      }
      for (OrderVO vo : vos) {
        String ctrantypeid = vo.getHVO().getCtrantypeid();
        trantypeMap.put(vo.getHVO().getPk_order(), map.get(ctrantypeid));
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return trantypeMap;
  }

  private void insertStatus(List<ISuperVO> insertList,
      Map<String, PoTransTypeVO> trantypeMap) {
    List<StatusOnWayItemVO> list = new ArrayList<StatusOnWayItemVO>();
    for (ISuperVO vo : insertList) {
      OrderItemVO itemVO = (OrderItemVO) vo;
      this.getStatusOnWay(itemVO, list, trantypeMap);
    }
    if (0 == list.size()) {
      return;
    }

    VOInsert<StatusOnWayItemVO> insertService =
        new VOInsert<StatusOnWayItemVO>();
    insertService.insert(list.toArray(new StatusOnWayItemVO[list.size()]));
  }

  private void updateStatus(List<ISuperVO> updateList,
      List<ISuperVO> updateOriginList) {
    Map<String, UFDouble> map = new HashMap<String, UFDouble>();
    for (int i = 0; i < updateList.size(); ++i) {
      OrderItemVO itemVO = (OrderItemVO) updateList.get(i);
      OrderItemVO orgItemVO = (OrderItemVO) updateOriginList.get(i);
      if (MathTool.compareTo(itemVO.getNnum(), orgItemVO.getNnum()) != 0) {
        map.put(itemVO.getPk_order_b(), itemVO.getNnum());
      }
    }

    if (0 == map.size()) {
      return;
    }

    Set<String> bidSet = map.keySet();
    String[] bids = bidSet.toArray(new String[bidSet.size()]);
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_21_13.name());
    String bidsql =
        builder.buildSQL(" and " + StatusOnWayItemVO.PK_ORDER_B, bids);

    VOQuery<StatusOnWayItemVO> query =
        new VOQuery<StatusOnWayItemVO>(StatusOnWayItemVO.class);
    StatusOnWayItemVO[] vos = query.query(bidsql, null);
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    for (StatusOnWayItemVO vo : vos) {
      String pk_order_b = vo.getPk_order_b();
      UFDouble nnum = map.get(pk_order_b);
      vo.setNonwaynum(nnum);
      vo.setNmaxhandlenum(nnum);
    }

    VOUpdate<StatusOnWayItemVO> updateService =
        new VOUpdate<StatusOnWayItemVO>();
    updateService.update(vos, new String[] {
      StatusOnWayItemVO.NONWAYNUM, StatusOnWayItemVO.NMAXHANDLENUM
    });
  }

}
