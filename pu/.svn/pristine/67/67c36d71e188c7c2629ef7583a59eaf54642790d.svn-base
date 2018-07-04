package nc.bs.pu.m21.writeback;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.constant.PUParaValue.ctrltype;
import nc.vo.pu.pub.rule.ToleranceCalcRule;
import nc.vo.pub.lang.UFBoolean;

/**
 * @description
 *              到货计划容差
 * @scene
 *        采购入库单回写到货计划
 * @param String tolerFiled 容差属性 ctrltype tolerPara 容差参数
 *          String wbNumField 回写数量参数 UFBoolean isUserConfirm 用户确认选项
 * @since 6.3
 * @version 2014-10-22 上午10:02:30
 * @author luojw
 */

public class ReceivePlanTolerRule extends ToleranceCalcRule implements
    IRule<OrderReceivePlanVO> {
  private UFBoolean isUserConfirm;

  private String tolerFiled;

  private ctrltype tolerPara;

  private String wbNumField;

  public ReceivePlanTolerRule(String tolerFiled, ctrltype tolerPara,
      String wbNumField, UFBoolean isUserConfirm) {
    super();
    this.tolerFiled = tolerFiled;
    this.tolerPara = tolerPara;
    this.wbNumField = wbNumField;
    this.isUserConfirm = isUserConfirm;
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
  public void process(OrderReceivePlanVO[] vos) {
    List<String> srcBidList = new ArrayList<String>();
    for (OrderReceivePlanVO vo : vos) {
      srcBidList.add(vo.getPk_order_bb1());
    }
    this.toleranceCompare(this.wbNumField,
        srcBidList.toArray(new String[srcBidList.size()]), this.tolerFiled,
        this.tolerPara, this.isUserConfirm);
  }

}
