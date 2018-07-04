package nc.itf.pu.reference.pp;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.pp.m28.IReWrite28For21;
import nc.vo.pp.m28.entity.GenNumWriteBackVO;
import nc.vo.pp.m28.entity.PriceAuditItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购价格适配层
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author liuchx
 * @time 2010-5-25 下午08:16:28
 */
public class PPServices {

  public static String[][] queryPriceAuditItemByBids(String[] keys) {
    if (!SysInitGroupQuery.isPPEnabled()) {
      return null;
    }
    PriceAuditItemVO[] items =
        new VOQuery<PriceAuditItemVO>(PriceAuditItemVO.class).query(keys);

    String[][] results = new String[items.length][2];
    PriceAuditItemVO item = null;
    for (int i = 0; i < items.length; i++) {
      item = items[i];
      results[i] = new String[] {
        item.getVfirsttype(), item.getCfirstbid(), item.getPk_priceaudit_b()
      };
    }
    return results;
  }

  public static void writeback28For21(GenNumWriteBackVO[] paras) {
    if (!SysInitGroupQuery.isPPEnabled()) {
      return;
    }
    IReWrite28For21 sevice =
        (IReWrite28For21) NCLocator.getInstance().lookup(
            IReWrite28For21.class.getName());
    try {
      sevice.backWriteGenNum(paras);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
