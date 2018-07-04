/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-6 下午02:12:24
 */
package nc.pubimpl.pu.m21.action.ct.mz2;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pu.m21.writeback.ct.OrderWriteBackForZ2FromPOBP;
import nc.pubitf.pu.m21.ct.mz2.IOrderWriteBackParaForZ2;

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
 * @author wuxla
 * @time 2010-7-6 下午02:12:24
 */
public class OrderWriteBackForZ2Action {

  public void writeback(IOrderWriteBackParaForZ2[] wbVos) {
    if (ArrayUtils.isEmpty(wbVos)) {
      return;
    }
    // 采购订单
    List<IOrderWriteBackParaForZ2> poVO =
        new ArrayList<IOrderWriteBackParaForZ2>();

    for (IOrderWriteBackParaForZ2 wbVo : wbVos) {
      // 价格审批单没有孙表了
      // 价格审批单、委外订单单独回写，不由采购订单回写
      if (null == wbVo.getSourceBBID()) {
        if (!wbVo.isSC()) {
          poVO.add(wbVo);
        }
      }
    }
    if (poVO.size() > 0) {
      new OrderWriteBackForZ2FromPOBP().writeback(poVO
          .toArray(new IOrderWriteBackParaForZ2[0]));
    }
  }
}
