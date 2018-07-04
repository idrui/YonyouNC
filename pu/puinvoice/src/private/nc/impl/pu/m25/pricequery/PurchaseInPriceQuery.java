package nc.impl.pu.m25.pricequery;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m25.IPriceQuery;
import nc.pubitf.ic.m45.PurchaseInPriceParam;
import nc.pubitf.ic.m45.m21.IPurchaseInQueryFor21;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoicePriceQueryVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.ICBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * 入库单价查询
 * 
 * @since 6.0
 * @version 2011-7-29 下午02:29:14
 * @author 田锋涛
 */

public class PurchaseInPriceQuery implements IPriceQuery {

  public PurchaseInPriceQuery() {
    //
  }

  @Override
  public void query(InvoicePriceQueryVO[] para) {
    Set<String> idSet = this.getPurinItemID(para);
    if (0 == idSet.size()) {
      return;
    }
    IPurchaseInQueryFor21 purinsrv =
        NCLocator.getInstance().lookup(IPurchaseInQueryFor21.class);
    PurchaseInPriceParam[] purinPrice = null;
    try {
      purinPrice =
          purinsrv.queryPriceInfo(idSet.toArray(new String[idSet.size()]));
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);

    }
    if (ArrayUtils.isEmpty(purinPrice)) {
      return;
    }
    Map<String, ArrayList<InvoicePriceQueryVO>> ipvoMap =
        CirVOUtil.getFieldVOList(para, InvoiceItemVO.CSOURCEBID);
    if (purinPrice == null) {
      return;
    }
    for (PurchaseInPriceParam param : purinPrice) {
      ArrayList<InvoicePriceQueryVO> purinItemList =
          ipvoMap.get(param.getCgeneralbid());
      if (null == purinItemList || 0 == purinItemList.size()) {
        continue;
      }
      for (InvoicePriceQueryVO ipvo : purinItemList) {
        String invccrrcy = ipvo.getCorigcurrencyid();
        String picrrcy = param.getCorigcurrencyid();
        if (invccrrcy.equals(picrrcy)) {
          ipvo.setNorigprice(param.getNorigprice());
          ipvo.setNorigtaxprice(param.getNorigtaxprice());
        }
        else {
          ipvo.setNprice(param.getNprice());
          ipvo.setNtaxprice(param.getNtaxprice());
        }
      }
    }
  }

  private Set<String> getPurinItemID(InvoicePriceQueryVO[] para) {
    Set<String> ids = new HashSet<String>();
    for (InvoicePriceQueryVO vo : para) {
      if (ICBillType.PurchaseIn.getCode().equals(vo.getCsourcetypecode())) {
        String purinbid = vo.getCsourcebid();
        if (!StringUtil.isEmptyWithTrim(purinbid)) {
          ids.add(purinbid);
        }
      }
    }
    return ids;
  }

}
