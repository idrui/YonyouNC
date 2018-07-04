/**
 * $文件说明$
 *
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-18 上午09:33:34
 */
package nc.impl.pu.m21.action.rule.rp;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.bd.material.MaterialVO;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUParaValue.ctrltype;
import nc.vo.pu.pub.rule.ToleranceCalcRule;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>数量检查
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-4-18 上午09:33:34
 */
public class RPNumRule extends ToleranceCalcRule implements
    IRule<BatchOperateVO> {
  private UFBoolean confirm;

  public RPNumRule(UFBoolean confirm) {
    this.confirm = confirm;
  }

  @Override
  public String getBidFiled() {
    return OrderReceivePlanVO.PK_ORDER_BB1;
  }

  @Override
  public String getNumField() {
    return OrderReceivePlanVO.NNUM;
  }

  @Override
  public String getTable() {
    return PUEntity.M21_BB1_TABLE;
  }

  @Override
  public void process(BatchOperateVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    OrderReceivePlanVO[] rpVOs =
        ArrayUtil.convertArrayType(vos[0].getUpdObjs());
    if (ArrayUtils.isEmpty(rpVOs)) {
      return;
    }

    List<String> list = new ArrayList<String>();
    for (OrderReceivePlanVO rp : rpVOs) {
      this.checkNnum(rp);
      this.checkNastnum(rp);
      list.add(rp.getPk_order_bb1());
    }

    String pk_org = rpVOs[0].getPk_org();
    ctrltype po02 = PUSysParamUtil.getPO02(pk_org);
    ctrltype po03 = PUSysParamUtil.getPO03(pk_org);

    String[] bb1ids = list.toArray(new String[list.size()]);

    this.toleranceCompare(OrderReceivePlanVO.NACCUMARRVNUM, bb1ids,
        MaterialVO.INTOLERANCE, po02, this.confirm);

    this.toleranceCompare(OrderReceivePlanVO.NACCUMSTORENUM, bb1ids,
        MaterialVO.INTOLERANCE, po03, this.confirm);
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param rpVO
   * @param MathTool
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-18 上午09:46:01
   */
  private String checkNastnum(OrderReceivePlanVO rpVO) {
    if (MathTool.compareTo(rpVO.getNastnum(), UFDouble.ZERO_DBL) < 0) {
      return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
          "04004030-0163")/* @res "请输入大于0的数量" */;
    }
    return null;
  }

  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-18 上午09:38:40
   */
  private String checkNnum(OrderReceivePlanVO rpVO) {
    if (MathTool.compareTo(rpVO.getNnum(), UFDouble.ZERO_DBL) < 0) {
      return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004030_0",
          "04004030-0164")/* @res "请输入大于0的主数量" */;
    }
    return null;
  }
}
