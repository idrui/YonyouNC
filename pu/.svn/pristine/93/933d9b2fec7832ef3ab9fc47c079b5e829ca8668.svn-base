/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-3-31 下午04:50:45
 */
package nc.impl.pu.m21.action.rule.revise;

import java.util.ArrayList;
import java.util.List;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.CloseCheck;
import nc.vo.pu.m21.rule.DownFlowCheck;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * @description
 *              采购订单已关闭的行，所有后续操作的行，不能删除
 * @scene
 *        采购订单保存修改
 * @param 无
 * @since 6.3
 * @version 2014-10-22 下午1:46:20
 * @author luojw
 */
public class DeleteRule implements IRule<OrderVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    StringBuilder sb = new StringBuilder();

    for (OrderVO vo : vos) {
      if (null == vo || ArrayUtils.isEmpty(vo.getBVO())) {
        continue;
      }

      // 要删除的行
      List<OrderItemVO> itemVOList = new ArrayList<OrderItemVO>();
      for (OrderItemVO itemVO : vo.getBVO()) {
        if (itemVO != null && VOStatus.DELETED == itemVO.getStatus()) {
          itemVOList.add(itemVO);
        }
      }
      if (itemVOList.isEmpty()) {
        continue;
      }

      OrderItemVO[] itemVOs =
          new ListToArrayTool<OrderItemVO>().convertToArray(itemVOList);
      String vbillcode = vo.getHVO().getVbillcode();

      // 判断是否关闭
      this.closeCheck(itemVOs, sb, vbillcode);

      // 判断是否有后续单据
      this.hasDownFlow(itemVOs, sb, vbillcode);
    }

    if (sb.length() > 0) {
      ExceptionUtils.wrappBusinessException(sb.toString()
          + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
              "04004030-0038")/* @res "不能删除" */);
    }
  }

  /**
   * 方法功能描述：判断是否关闭
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVOs
   * @param sb
   * @param vbillcode
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-16 上午09:34:27
   */
  private void closeCheck(OrderItemVO[] itemVOs, StringBuilder sb,
      String vbillcode) {
    String isClose = new CloseCheck().closeCheck(itemVOs);
    if (!StringUtil.isEmptyWithTrim(isClose)) {
      sb.append(NCLangResOnserver.getInstance().getStrByID("4004030_0",
          "04004030-0285", null, new String[] {
            vbillcode
          })/* 订单{0}：\n */);
      sb.append(isClose);
    }
  }

  /**
   * 方法功能描述：判断是否有后续单据
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVOs
   * @param sb
   * @param vbillcode
   *          <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-5-16 上午09:36:14
   */
  private void hasDownFlow(OrderItemVO[] itemVOs, StringBuilder sb,
      String vbillcode) {
    String hasDownFlow = new DownFlowCheck().hasDownFlow(itemVOs);
    if (!StringUtil.isEmptyWithTrim(hasDownFlow)) {
      sb.append(NCLangResOnserver.getInstance().getStrByID("4004030_0",
          "04004030-0285", null, new String[] {
            vbillcode
          })/* 订单{0}：\n */);
      sb.append(hasDownFlow);
    }
  }
}
