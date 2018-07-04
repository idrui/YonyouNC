package nc.bs.pu.m21.writeback;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.pub.constant.PUParaValue.ctrltype;
import nc.vo.pu.pub.rule.ToleranceCalcRule;
import nc.vo.pub.lang.UFBoolean;

/**
 * @description
 *              采购订单订单回写容差规则
 * @scene
 *        采购入库单回写订单
 * @param String tolerFiled 容差字段名（例：入库容差：tolerFiled="intolerance"）
 *          ctrltype tolerPara 容差参数（例：PO02：tolerPara="严格控制"）
 *          String wbNumField 回写的字段名（例：到货单回写订单：wbNumField="naccumarrvnum"）
 *          UFBoolean isUserConfirm 用户确认选项
 * @since 6.3
 * @version 2014-10-22 下午3:27:10
 * @author chendb
 */
public class OrderWritebackTolerRule extends ToleranceCalcRule implements
    IRule<OrderViewVO> {
  private UFBoolean isUserConfirm;

  private String tolerFiled;

  private ctrltype tolerPara;

  private String wbNumField;

  /**
   * OrderWbTolerRule 的构造子
   * 
   * @param wbNumFiled 回写的字段名（例：到货单回写订单：wbNumField="naccumarrvnum"）
   * @param tolerFiled 容差字段名（例：入库容差：tolerFiled="intolerance"）
   * @param tolerPara 容差参数（例：PO02：tolerPara="严格控制"）
   */
  public OrderWritebackTolerRule(String tolerFiled, ctrltype tolerPara,
      String wbNumField, UFBoolean isUserConfirm) {
    super();
    this.tolerFiled = tolerFiled;
    this.tolerPara = tolerPara;
    this.wbNumField = wbNumField;
    this.isUserConfirm = isUserConfirm;
  }

  @Override
  public String getBidFiled() {
    return "pk_order_b";
  }

  @Override
  public String getNumField() {
    return "nnum";
  }

  @Override
  public String getTable() {
    return "PO_ORDER_B";
  }

  @Override
  public void process(OrderViewVO[] ordervos) {
    List<String> srcBidList = new ArrayList<String>();
    for (OrderViewVO vo : ordervos) {
      srcBidList.add(vo.getPk_order_b());
    }
    this.toleranceCompare(this.wbNumField,
        srcBidList.toArray(new String[srcBidList.size()]), this.tolerFiled,
        this.tolerPara, this.isUserConfirm);
  }
}
