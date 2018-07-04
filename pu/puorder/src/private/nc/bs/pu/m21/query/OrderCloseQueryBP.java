/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-11 上午11:24:26
 */
package nc.bs.pu.m21.query;

import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.vo.pu.m21.entity.OrderCloseVO;
import nc.vo.pubapp.pattern.pub.MathTool;

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
 * @author zhaoyha
 * @time 2010-1-11 上午11:24:26
 */
public class OrderCloseQueryBP {

  /**
   * 方法功能描述：查询订单关闭节点所用的VO。
   * <p>
   * <b>参数说明</b>
   * 
   * @param bodyIds
   * @return
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-1-11 上午11:34:07
   */
  public OrderCloseVO[] query(String[] bodyIds) {
    // 之后添加规则
    OrderCloseVO[] vos =
        new ViewQuery<OrderCloseVO>(OrderCloseVO.class).query(bodyIds);
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }
    this.calNumAndMny(vos);
    return vos;
  }

  private void calNumAndMny(OrderCloseVO[] vos) {
    for (OrderCloseVO vo : vos) {
      // 未付款金额（原币）
      if (vo.getNnopayorgmny() == null) {
        vo.setNnopayorgmny(MathTool.sub(vo.getNtaxmny(),
            vo.getNacccancelinvmny()));
      }
      // 可到货数量
      if (vo.getNcanarrivenum() == null) {
        vo.setNcanarrivenum(MathTool.sub(vo.getNnum(), vo.getNaccumarrvnum()));
      }
      // 可入库数量
      if (vo.getNcaninnum() == null) {
        vo.setNcaninnum(MathTool.sub(vo.getNnum(), vo.getNaccumstorenum()));
      }
      // 可开票数量
      if (vo.getNcaninvoicenum() == null) {
        vo.setNcaninvoicenum(MathTool.sub(vo.getNnum(),
            vo.getNaccuminvoicenum()));
      }
    }
  }
}
