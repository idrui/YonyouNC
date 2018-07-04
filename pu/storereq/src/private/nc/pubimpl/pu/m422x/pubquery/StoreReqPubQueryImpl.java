package nc.pubimpl.pu.m422x.pubquery;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.pubitf.pu.m422x.pubquery.IStoreReqPubQuery;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 物资需求申请单提供给外模块的公共查询服务实现类
 * 
 * @since 6.3
 * @version 2012-10-25 下午03:37:18
 * @author fanly3
 */
public class StoreReqPubQueryImpl implements IStoreReqPubQuery {

  @Override
  public StoreReqAppItemVO[] queryItemVOByBids(String[] bids)
      throws BusinessException {
    try {
      VOQuery<StoreReqAppItemVO> query =
          new VOQuery<StoreReqAppItemVO>(StoreReqAppItemVO.class);
      StoreReqAppItemVO[] items = query.query(bids);
      return items;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public StoreReqAppItemVO[] queryItemVOByCSourcebids(String[] bids,
      String[] queryFields) throws BusinessException {
    if (ArrayUtils.isEmpty(bids)) {
      return null;
    }
    try {
      VOQuery<StoreReqAppItemVO> query = null;
      new VOQuery<StoreReqAppItemVO>(StoreReqAppItemVO.class);
      if (ArrayUtils.isEmpty(queryFields)) {
        query = new VOQuery<StoreReqAppItemVO>(StoreReqAppItemVO.class);
      }
      else {
        query =
            new VOQuery<StoreReqAppItemVO>(StoreReqAppItemVO.class, queryFields);
      }
      StoreReqAppItemVO[] items = query.query(this.getSql(bids), null);
      return items;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  private String getSql(String[] bids) {
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_422X_04.name());
    return builder.buildSQL(" and " + StoreReqAppItemVO.CSOURCEBID, bids);
  }
}
