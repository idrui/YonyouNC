/**
 * $文件说明$
 * 
 * @author zhyhang
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-15 下午01:42:20
 */
package nc.vo.pu.est.util;

import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>设置暂估VO的默认值
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhyhang
 * @time 2010-6-15 下午01:42:20
 */
public class EstVODefualtValueUtil {

  public static void setFeeEstDefValue(FeeEstVO feevo, GoodsEstVO head,
      String pk_user) {
    feevo.setPk_stockps(head.getPk_stockps());
    feevo.setPk_stockps_b(head.getPk_stockps_b());
    feevo.setPk_estpsn(pk_user);
    feevo.setDestdate(AppContext.getInstance().getBusiDate());
    feevo.setPk_estcurrency(head.getCcurrencyid());
    feevo.setPk_localcurrency(head.getCcurrencyid());
    feevo.setNestexchgrate(UFDouble.ONE_DBL);
    feevo.setPk_financeorg(head.getPk_financeorg());
    feevo.setPk_group(head.getPk_group());
    feevo.setBtoap(UFBoolean.FALSE);
    feevo.setBtoia(UFBoolean.FALSE);
  }
}
