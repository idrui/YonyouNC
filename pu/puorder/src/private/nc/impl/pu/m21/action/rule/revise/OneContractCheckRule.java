package nc.impl.pu.m21.action.rule.revise;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.ICompareRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;

/**
 * @description
 *              <ul>
 *              <li>采购订单修订时对来自合同的预付款的检查：修订前单据来自同一个合同，则不能将订单改为来自多个合同的订单
 *              <li>不是修订时不进行检查
 *              </ul>
 * @scene
 *        采购订单修订
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午10:35:56
 * @author luojw
 */
public class OneContractCheckRule implements ICompareRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos, OrderVO[] originVOs) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < vos.length; ++i) {
      // 判断旧订单是否来自同一合同，预付款是否有值
      if (!this.isFrmOneContract(originVOs[i])) {
        continue;
      }

      // 新旧订单是否来自同一合同
      if (!this.isAllFrmOne(vos[i], originVOs[i])) {
        sb.append(NCLangResOnserver.getInstance().getStrByID("4004030_0",
            "04004030-0288", null, new String[] {
              vos[i].getHVO().getVbillcode()
            })/* 原订单{0}原是有合同的订单，不能修订为无合同或非一张合同的订单！\n */);
      }
    }

    if (sb.length() > 0) {
      ExceptionUtils.wrappBusinessException(sb.toString());
    }
  }

  /**
   * 方法功能描述：判断新订单来自同一个合同或来自其他合同
   * <p>
   * <b>参数说明</b>
   * 
   * @param newVO
   * @param oldVO
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-5 上午08:30:24
   */
  private boolean isAllFrmOne(OrderVO newVO, OrderVO oldVO) {
    // 旧订单合同号
    String sOldCntId = oldVO.getBVO()[0].getCcontractid();

    // 判断新订单来自同一个合同或来自其他合同
    OrderItemVO[] itemVOs = newVO.getBVO();
    for (OrderItemVO itemVO : itemVOs) {
      if (!ObjectUtils.equals(itemVO.getCcontractid(), sOldCntId)) {
        return false;
      }
    }

    return true;
  }

  /**
   * 方法功能描述：判断该单据是否所有表体行均来自同一个合同单据。
   * <p>
   * <b>参数说明</b>
   * 
   * @param newVO
   * @param oldVO
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-3-21 下午03:53:15
   */
  private boolean isFrmOneContract(OrderVO vo) {
    if (null == vo || ArrayUtils.isEmpty(vo.getBVO())) {
      return false;
    }

    OrderItemVO[] itemVOs = vo.getBVO();
    String cntId = itemVOs[0].getCcontractid();

    // 如果第一个表体的合同为空，则肯定不会来自同一个合同
    if (ObjectUtil.isEmptyWithTrim(cntId)) {
      return false;
    }

    // 其余和第一个表体的合同比较
    for (int i = 1; i < itemVOs.length; ++i) {
      String nextCntId = itemVOs[i].getCcontractid();
      if (!ObjectUtils.equals(cntId, nextCntId)) {
        return false;
      }
    }

    return true;
  }

}
