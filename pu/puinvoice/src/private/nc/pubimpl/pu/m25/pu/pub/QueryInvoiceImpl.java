/**
 * $文件说明$
 * 
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-11-3 下午03:54:52
 */
package nc.pubimpl.pu.m25.pu.pub;

import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.view.EfficientViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.pubitf.pu.m25.pu.pub.IQueryInvoice;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.entity.InvoiceViewVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购发票查询类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-11-3 下午03:54:52
 */
public class QueryInvoiceImpl implements IQueryInvoice {

  /**
   * 父类方法重写
   * 
   * @see nc.pubitf.pu.m25.pu.pub.IQueryInvoice#checkTransTypeReference(java.lang.String[])
   */
  @Override
  public String[] checkTransTypeReference(String[] transType)
      throws BusinessException {
    try {
      SqlBuilder sqlbd = new SqlBuilder();
      sqlbd.append("select distinct ");
      sqlbd.append(InvoiceHeaderVO.VTRANTYPECODE);
      sqlbd.append(" from po_invoice where dr = 0 and ");
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
  public InvoiceVO[] queryInvoiceByOrderBid(String[] bids)
      throws BusinessException {
    try {
      IDExQueryBuilder builder =
          new IDExQueryBuilder(PUTempTable.tmp_po_25_12.name());
      String orderBidCond =
          builder.buildSQL(" and " + InvoiceItemVO.PK_ORDER_B, bids);
      EfficientViewQuery<InvoiceViewVO> query =
          new EfficientViewQuery<InvoiceViewVO>(InvoiceViewVO.class);
      InvoiceViewVO[] views = query.query(orderBidCond.toString());
      if (ArrayUtils.isEmpty(views)) {
        return null;
      }
      return InvoiceViewVO.getInvoiceVO(views);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

}
