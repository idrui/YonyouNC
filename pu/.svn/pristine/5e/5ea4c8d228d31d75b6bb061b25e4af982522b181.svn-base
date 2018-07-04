package nc.pubimpl.pu.m20.pub;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.view.EfficientViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.pubitf.pu.m20.pub.IQueryPrayBill;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.entity.PraybillViewVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-11-3 下午03:25:07
 */
public class QueryPrayBillImpl implements IQueryPrayBill {

  @Override
  public String[] checkOrderTransTypeReference(String[] ordertransType)
      throws BusinessException {
    try {
      SqlBuilder sql = new SqlBuilder();
      sql.append("select ");
      sql.append(PraybillItemVO.CORDERTRANTYPECODE);
      sql.append(" from po_praybill_b where ");
      sql.append(" dr ", 0);
      sql.append(" and " + PraybillItemVO.CORDERTRANTYPECODE, ordertransType);
      sql.append(" and " + PraybillItemVO.PK_GROUP, AppContext.getInstance()
          .getPkGroup());

      DataAccessUtils utils = new DataAccessUtils();
      IRowSet rowset = utils.query(sql.toString());
      String[] ordertrantypeids = rowset.toOneDimensionStringArray();
      return ordertrantypeids;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.pubitf.pu.m20.pub.IQueryPrayBill#checkTransTypeReference(java.lang.String[])
   */
  @Override
  public String[] checkTransTypeReference(String[] transType)
      throws BusinessException {
    try {
      SqlBuilder sqlbd = new SqlBuilder();
      sqlbd.append("select ");
      sqlbd.append(PraybillHeaderVO.VTRANTYPECODE);
      sqlbd.append(" from po_praybill where dr = 0 and ");
      sqlbd.append(PraybillHeaderVO.VTRANTYPECODE, transType);
      sqlbd.append(" and " + PraybillHeaderVO.PK_GROUP, BSContext.getInstance()
          .getGroupID());

      DataAccessUtils utils = new DataAccessUtils();
      IRowSet rowset = utils.query(sqlbd.toString());
      String[] trantypecodes = rowset.toOneDimensionStringArray();

      return trantypecodes;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public Map<String, UFBoolean> isDirect(String[] pk_praybills)
      throws BusinessException {
    try {
      VOQuery<PraybillHeaderVO> query =
          new VOQuery<PraybillHeaderVO>(PraybillHeaderVO.class, new String[] {
            PraybillHeaderVO.PK_PRAYBILL, PraybillHeaderVO.BDIRECTTRANSIT
          });
      PraybillHeaderVO[] vos = query.query(pk_praybills);
      Map<String, UFBoolean> map = new HashMap<String, UFBoolean>();
      for (PraybillHeaderVO vo : vos) {
        map.put(vo.getPk_praybill(),
            ValueUtils.getUFBoolean(vo.getBdirecttransit()));
      }
      return map;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public Map<String, String> queryDirectSourceBidMap(String[] bids)
      throws BusinessException {
    try {
      ViewQuery<PraybillViewVO> query =
          new ViewQuery<PraybillViewVO>(PraybillViewVO.class);
      PraybillViewVO[] views = query.query(bids);
      if (ArrayUtils.isEmpty(views)) {
        return null;
      }
      Map<String, String> map = new HashMap<String, String>();
      for (PraybillViewVO view : views) {
        UFBoolean bdirect =
            (UFBoolean) view.getAttributeValue(PraybillHeaderVO.BDIRECTTRANSIT);
        if (UFBoolean.TRUE.equals(bdirect)) {
          String csourcebid =
              (String) view.getAttributeValue(PraybillItemVO.CSOURCEBID);
          map.put(view.getPk_praybill_b(), csourcebid);
        }
      }

      return map;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public Map<String, PraybillItemVO> queryItemByPK(String[] pkPraybillBs,
      String[] arrs) throws BusinessException {
    Map<String, PraybillItemVO> map = new HashMap<String, PraybillItemVO>();
    try {
      VOQuery<PraybillItemVO> query =
          new VOQuery<PraybillItemVO>(PraybillItemVO.class, arrs);
      PraybillItemVO[] vos = query.query(pkPraybillBs);
      for (PraybillItemVO vo : vos) {
        map.put(vo.getPk_praybill_b(), vo);
      }

      return map;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return map;
  }

  @Override
  public Map<String, UFDouble> queryPriceByItemPK(String[] pk_praybill_bs)
      throws BusinessException {
    Map<String, UFDouble> ret = new HashMap<String, UFDouble>();
    try {

      if (ArrayUtils.isEmpty(pk_praybill_bs)) {
        return ret;
      }

      PraybillItemVO[] items =
          new VOQuery<PraybillItemVO>(PraybillItemVO.class)
              .query(pk_praybill_bs);
      if (ArrayUtils.isEmpty(items)) {
        return ret;
      }

      for (PraybillItemVO vo : items) {
        ret.put(vo.getPk_praybill_b(), vo.getNtaxprice());
      }

    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return ret;
  }

  @Override
  public Map<String, PraybillViewVO> queryViewByItemPK(String[] pkPraybillBs,
      String[] arrs) throws BusinessException {
    Map<String, PraybillViewVO> map = new HashMap<String, PraybillViewVO>();
    try {
      SqlBuilder sql = new SqlBuilder();
      sql.append(" and ");
      // 临时表
      IDExQueryBuilder builder =
          new IDExQueryBuilder(PUTempTable.tmp_po_20_08.name());
      sql.append(builder.buildSQL(PraybillItemVO.PK_PRAYBILL_B, pkPraybillBs));

      EfficientViewQuery<PraybillViewVO> query =
          new EfficientViewQuery<PraybillViewVO>(PraybillViewVO.class, arrs);
      PraybillViewVO[] views = query.query(sql.toString());
      for (PraybillViewVO view : views) {
        map.put(view.getItem().getPk_praybill_b(), view);
      }

      return map;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return map;
  }

  @Override
  public PraybillVO[] queryVOByBids(String[] bids) throws BusinessException {
    try {
      ViewQuery<PraybillViewVO> query =
          new ViewQuery<PraybillViewVO>(PraybillViewVO.class);
      PraybillViewVO[] views = query.query(bids);
      return PraybillViewVO.getPraybillVO(views);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
