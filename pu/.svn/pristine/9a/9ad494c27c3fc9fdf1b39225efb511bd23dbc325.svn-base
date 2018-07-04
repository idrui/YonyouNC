package nc.pubimpl.pu.m25.pu.m21;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.pubift.pu.m25.pu.m21.IInvoiceQueryFor21;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * 为订单提供的查询服务实现类
 * 
 * @since 6.0
 * @version 2011-3-6 下午04:00:29
 * @author wuxla
 */

public class InvoiceQueryFor21Impl implements IInvoiceQueryFor21 {

  @Override
  public Map<String, String> queryOrderBidByInvoiceBid(String[] bids)
      throws BusinessException {
    try {
      IDExQueryBuilder builder =
          new IDExQueryBuilder(PUTempTable.tmp_po_25_11.name());
      String idCond =
          builder.buildSQL(" and " + InvoiceItemVO.PK_INVOICE_B, bids);
      SqlBuilder sql = new SqlBuilder();
      sql.append(idCond);
      sql.append(" and " + InvoiceItemVO.CFIRSTTYPECODE,
          POBillType.Order.getCode());

      VOQuery<InvoiceItemVO> query =
          new VOQuery<InvoiceItemVO>(InvoiceItemVO.class, new String[] {
            InvoiceItemVO.PK_INVOICE_B, InvoiceItemVO.VORDERCODE
          });
      InvoiceItemVO[] vos = query.query(sql.toString(), null);
      if (ArrayUtils.isEmpty(vos)) {
        return null;
      }

      Map<String, String> map = new HashMap<String, String>();
      for (InvoiceItemVO vo : vos) {
        map.put(vo.getPk_invoice_b(), vo.getVordercode());
      }
      return map;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

}
