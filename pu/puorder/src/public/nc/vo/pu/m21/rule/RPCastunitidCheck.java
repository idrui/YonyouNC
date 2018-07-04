/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-17 下午02:35:58
 */
package nc.vo.pu.m21.rule;

import nc.vo.jcom.lang.StringUtil;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.BillIndex;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>检查辅计量管理但未确认辅单位的物料
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-17 下午02:35:58
 */
public class RPCastunitidCheck {

  /**
   * 方法功能描述：检查辅计量管理但未确认辅单位的物料
   * <p>
   * <b>参数说明</b>
   * 
   * @param rpVOs
   * @param orderVO
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-17 下午02:37:48
   */
  public void castunitidCheck(OrderReceivePlanVO[] rpVOs, OrderVO orderVO) {
    if (ArrayUtils.isEmpty(rpVOs) || (null == orderVO)) {
      return;
    }

    StringBuilder sb = new StringBuilder();
    // Map<String, OrderItemVO> map = AggVOUtil.createItemMap(new OrderVO[] {
    // orderVO
    // });
    BillIndex index = new BillIndex(new OrderVO[] {
      orderVO
    });
    IVOMeta meta = orderVO.getMetaData().getVOMeta(OrderItemVO.class);
    for (OrderReceivePlanVO rpVO : rpVOs) {
      // OrderItemVO itemVO = map.get(rpVO.getPk_order_b());
      OrderItemVO itemVO = (OrderItemVO) index.get(meta, rpVO.getPk_order_b());
      if (null == itemVO) {
        continue;
      }

      String itemCastunitid = itemVO.getCastunitid();
      String rpCastunitid = rpVO.getCastunitid();
      if (!StringUtil.isEmptyWithTrim(itemCastunitid)
          && StringUtil.isEmptyWithTrim(rpCastunitid)) {
        sb.append(NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0", "04004030-0331")/*存在是辅计量管理但未确认单位的物料，请检查\n*/);
      }
    }

    if (sb.length() > 0) {
      ExceptionUtils.wrappBusinessException(sb.toString());
    }

  }
}
