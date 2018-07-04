package nc.bs.pu.m21.maintain.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * @description
 *              采购订单单价、数量的合法性校验
 * @scene
 *        采购订单保存修改
 * @param 无
 * @since 6.3
 * @version 2014-10-22 上午11:26:38
 * @author luojw
 */
public class NumAndPriceCheckRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    StringBuilder sb = new StringBuilder();
    for (OrderVO vo : vos) {
      String err = this.check(vo);
      if (err.length() > 0) {
        sb.append("\n").append(err);
      }
    }
    if (sb.length() > 0) {
      sb.insert(
          0,
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
              "04004030-0127")/* @res "订单数量或单价不正确：" */);
      ExceptionUtils.wrappBusinessException(sb.toString());
    }
  }

  /**
   * 方法功能描述：订单VO检查：1、退货订单数量不能为正；2、订货订单数量不能为负；3、单价不能为负（赠品行、劳务或折扣类物料的单价允许为0）。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vo 要检查的订单VO
   * @return 如果检查未通过，返回错误信息字符串，如果检查通过，返回空字符串
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-4 下午06:24:26
   */
  private String check(OrderVO vo) {
    OrderItemVO[] items = vo.getBVO();
    StringBuilder sb = new StringBuilder();
    boolean isReturn = UFBoolean.TRUE.equals(vo.getHVO().getBreturn());
    for (OrderItemVO item : items) {
    	if(item.getStatus()==3){
    		continue;
    	}
      // 记住当前buffer的大小，如果本次循环有异常，会在这个位置插入 第{0}行：
      int offset = sb.length();

      if (isReturn && UFDouble.ZERO_DBL.compareTo(item.getNnum()) < 0) {
        sb.append(
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
                "04004030-0128")/* @res "[退货订单数量不能为正]" */).append(",");
      }
      else if (!isReturn && UFDouble.ZERO_DBL.compareTo(item.getNnum()) > 0) {
        sb.append(
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
                "04004030-0129")/* @res "[订货订单数量不能为负]" */).append(",");
      }
      int sign =
          UFDouble.ZERO_DBL
              .compareTo(item.getNorigtaxprice() == null ? UFDouble.ZERO_DBL
                  : item.getNorigtaxprice());
      int signNet =
          UFDouble.ZERO_DBL
              .compareTo(item.getNorigtaxnetprice() == null ? UFDouble.ZERO_DBL
                  : item.getNorigtaxnetprice());
      if (sign > 0 || signNet > 0) {
        sb.append(
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
                "04004030-0130")/* @res "[单价不能为负]" */).append(",");
      }
      else if ((sign == 0 || signNet == 0)
          && UFBoolean.FALSE.equals(item.getBlargess())) {
        sb.append(
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
                "04004030-0131")/* @res "[单价不能为零]" */).append(",");
      }

      sign =
          UFDouble.ZERO_DBL
              .compareTo(item.getNqtorigtaxprice() == null ? UFDouble.ZERO_DBL
                  : item.getNqtorigtaxprice());
      signNet =
          UFDouble.ZERO_DBL
              .compareTo(item.getNqtorigtaxnetprc() == null ? UFDouble.ZERO_DBL
                  : item.getNqtorigtaxnetprc());
      if (sign > 0 || signNet > 0) {
        sb.append(
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
                "04004030-0130")/* @res "[单价不能为负]" */).append(",");
      }
      else if ((sign == 0 || signNet == 0)
          && UFBoolean.FALSE.equals(item.getBlargess())) {
        sb.append(
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
                "04004030-0131")/* @res "[单价不能为零]" */).append(",");
      }

      sign = MathTool.compareTo(UFDouble.ZERO_DBL, item.getNtaxrate());
      if (sign > 0) {
        sb.append(
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
                "04004030-0132")/* @res "[税率不能为负]" */).append(",");
      }

      sign = MathTool.compareTo(UFDouble.ZERO_DBL, item.getNexchangerate());
      if (sign >= 0) {
        sb.append(
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
                "04004030-0133")/* @res "[折本汇率不能为0或者负]" */).append(",");
      }

      if (sb.length() > 1) {
        // 本次循环中发现异常，在异常提示前插入 第{0}行： 字样。
        sb.insert(offset, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
            .getStrByID("4004030_0", "04004030-0361", null, new String[] {
              item.getCrowno()
            })/* @res "第{0}行：" */);

        // 删除最后的逗号
        sb.deleteCharAt(sb.length() - 1);
        sb.append("\n");
      }
    }

    return sb.toString();
  }

}
