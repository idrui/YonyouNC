package nc.pubimpl.pu.m23.pubquery;

import java.util.HashSet;
import java.util.Set;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.pubitf.pu.m23.pubquery.IArrivePubQuery;
import nc.vo.pu.m23.entity.ArriveBbVO;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.utils.ArriveTrans45QueryUtil;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.BillComposite;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * 到货单提供给外模块的公共查询服务实现类
 * 
 * @since 6.0
 * @version 2010-11-11 上午09:24:42
 * @author hanbin
 */
public class ArrivePubQueryImpl implements IArrivePubQuery {

  @Override
  public ArriveVO[] queryAggVOByBids(String[] bids) throws BusinessException {
    if (ArrayUtils.isEmpty(bids)) {
      return null;
    }
    try {
      VOQuery<ArriveItemVO> itemQuery =
          new VOQuery<ArriveItemVO>(ArriveItemVO.class);
      ArriveItemVO[] itemVOs = itemQuery.query(bids);
      if (ArrayUtils.isEmpty(itemVOs)) {
        return null;
      }

      Set<String> hidSet = new HashSet<String>();
      for (ArriveItemVO itemVO : itemVOs) {
        hidSet.add(itemVO.getPk_arriveorder());
      }
      String[] hids = hidSet.toArray(new String[hidSet.size()]);
      VOQuery<ArriveHeaderVO> query =
          new VOQuery<ArriveHeaderVO>(ArriveHeaderVO.class);
      ArriveHeaderVO[] headerVOs = query.query(hids);
      if (ArrayUtils.isEmpty(headerVOs)) {
        return null;
      }

      BillComposite<ArriveVO> bc = new BillComposite<ArriveVO>(ArriveVO.class);
      bc.append(headerVOs[0].getMetaData(), headerVOs);
      bc.append(itemVOs[0].getMetaData(), itemVOs);
      return bc.composite();
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

  @Override
  public ArriveVO[] queryAggVOByBidsAndBBids(String[] bids, String[] bbids)
      throws BusinessException {
    if (ArrayUtils.isEmpty(bids)) {
      return null;
    }
    try {
      VOQuery<ArriveItemVO> itemQuery =
          new VOQuery<ArriveItemVO>(ArriveItemVO.class);
      ArriveItemVO[] itemVOs = itemQuery.query(bids);
      if (ArrayUtils.isEmpty(itemVOs)) {
        return null;
      }

      Set<String> hidSet = new HashSet<String>();
      for (ArriveItemVO itemVO : itemVOs) {
        hidSet.add(itemVO.getPk_arriveorder());
      }
      String[] hids = hidSet.toArray(new String[hidSet.size()]);
      VOQuery<ArriveHeaderVO> query =
          new VOQuery<ArriveHeaderVO>(ArriveHeaderVO.class);
      ArriveHeaderVO[] headerVOs = query.query(hids);
      if (ArrayUtils.isEmpty(headerVOs)) {
        return null;
      }

      ArriveBbVO[] bbVOs = null;
      if (!ArrayUtils.isEmpty(bbids)) {
        VOQuery<ArriveBbVO> bbQuery = new VOQuery<ArriveBbVO>(ArriveBbVO.class);
        bbVOs = bbQuery.query(bbids);
      }

      ArriveTrans45QueryUtil tranQueryUtil =
          new ArriveTrans45QueryUtil(headerVOs, itemVOs, bbVOs);
      return tranQueryUtil.combine();
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public ArriveVO queryAggVOByHid(String hid) throws BusinessException {
    try {
      String[] hids = new String[1];
      hids[0] = hid;
      ArriveVO[] vos = this.queryAggVOByHids(hids);
      if (ArrayUtils.isEmpty(vos)) {
        return null;
      }
      return vos[0];
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public ArriveVO[] queryAggVOByHids(String[] hids) throws BusinessException {
    try {
      // 查询VO
      BillQuery<ArriveVO> query = new BillQuery<ArriveVO>(ArriveVO.class);
      ArriveVO[] bills = query.query(hids);
      return bills;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public ArriveVO[] queryAggVOByOrderBids(String[] bids)
      throws BusinessException {
    try {
      VOQuery<ArriveItemVO> query =
          new VOQuery<ArriveItemVO>(ArriveItemVO.class);
      IDExQueryBuilder builder =
          new IDExQueryBuilder(PUTempTable.tmp_po_23_12.name());
      String orderBidCond =
          builder.buildSQL(" and " + ArriveItemVO.PK_ORDER_B, bids);
      ArriveItemVO[] itemVOs = query.query(orderBidCond.toString(), null);
      if (ArrayUtils.isEmpty(itemVOs)) {
        return null;
      }
      Set<String> hidSet = new HashSet<String>();
      for (ArriveItemVO itemVO : itemVOs) {
        hidSet.add(itemVO.getPk_arriveorder());
      }
      String[] hids = hidSet.toArray(new String[hidSet.size()]);
      VOQuery<ArriveHeaderVO> headquery =
          new VOQuery<ArriveHeaderVO>(ArriveHeaderVO.class);
      ArriveHeaderVO[] headerVOs = headquery.query(hids);
      if (ArrayUtils.isEmpty(headerVOs)) {
        return null;
      }

      BillComposite<ArriveVO> bc = new BillComposite<ArriveVO>(ArriveVO.class);
      bc.append(headerVOs[0].getMetaData(), headerVOs);
      bc.append(itemVOs[0].getMetaData(), itemVOs);
      return bc.composite();
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public ArriveVO[] queryAggVOIncludeBBVOByBids(String[] bids)
      throws BusinessException {
    if (ArrayUtils.isEmpty(bids)) {
      return null;
    }
    try {
      VOQuery<ArriveItemVO> itemQuery =
          new VOQuery<ArriveItemVO>(ArriveItemVO.class);
      ArriveItemVO[] itemVOs = itemQuery.query(bids);
      if (ArrayUtils.isEmpty(itemVOs)) {
        return null;
      }

      Set<String> hidSet = new HashSet<String>();
      for (ArriveItemVO itemVO : itemVOs) {
        hidSet.add(itemVO.getPk_arriveorder());
      }
      String[] hids = hidSet.toArray(new String[hidSet.size()]);
      VOQuery<ArriveHeaderVO> query =
          new VOQuery<ArriveHeaderVO>(ArriveHeaderVO.class);
      ArriveHeaderVO[] headerVOs = query.query(hids);
      if (ArrayUtils.isEmpty(headerVOs)) {
        return null;
      }

      SqlBuilder cond = new SqlBuilder();
      // 临时表
      IDExQueryBuilder builder =
          new IDExQueryBuilder(PUTempTable.tmp_po_23_18.name());
      cond.append(builder.buildSQL(ArriveBbVO.PK_ARRIVEORDER_B, bids));
      VOQuery<ArriveBbVO> bbQuery = new VOQuery<ArriveBbVO>(ArriveBbVO.class);
      ArriveBbVO[] bbVOs = bbQuery.query(" and " + cond, null);

      ArriveTrans45QueryUtil tranQueryUtil =
          new ArriveTrans45QueryUtil(headerVOs, itemVOs, bbVOs);
      return tranQueryUtil.combine();
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

  @Override
  public ArriveHeaderVO queryHeadVOByHid(String hid) throws BusinessException {
    try {
      String[] hids = new String[1];
      hids[0] = hid;
      ArriveHeaderVO[] heads = this.queryHeadVOByHids(hids);
      if (ArrayUtils.isEmpty(heads)) {
        return null;
      }
      return heads[0];
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public ArriveHeaderVO[] queryHeadVOByHids(String[] hids)
      throws BusinessException {
    try {
      VOQuery<ArriveHeaderVO> query =
          new VOQuery<ArriveHeaderVO>(ArriveHeaderVO.class);
      ArriveHeaderVO[] heads = query.query(hids);
      return heads;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public ArriveItemVO queryItemVOByBid(String bid) throws BusinessException {
    try {
      String[] bids = new String[1];
      bids[0] = bid;
      ArriveItemVO[] items = this.queryItemVOByBids(bids);
      if (ArrayUtils.isEmpty(items)) {
        return null;
      }
      return items[0];
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public ArriveItemVO[] queryItemVOByBids(String[] bids)
      throws BusinessException {
    try {
      VOQuery<ArriveItemVO> query =
          new VOQuery<ArriveItemVO>(ArriveItemVO.class);
      ArriveItemVO[] items = query.query(bids);
      return items;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }
}
