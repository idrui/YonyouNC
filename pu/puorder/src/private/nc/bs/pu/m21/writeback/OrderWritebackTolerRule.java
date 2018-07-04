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
 *              �ɹ�����������д�ݲ����
 * @scene
 *        �ɹ���ⵥ��д����
 * @param String tolerFiled �ݲ��ֶ�������������ݲtolerFiled="intolerance"��
 *          ctrltype tolerPara �ݲ����������PO02��tolerPara="�ϸ����"��
 *          String wbNumField ��д���ֶ�����������������д������wbNumField="naccumarrvnum"��
 *          UFBoolean isUserConfirm �û�ȷ��ѡ��
 * @since 6.3
 * @version 2014-10-22 ����3:27:10
 * @author chendb
 */
public class OrderWritebackTolerRule extends ToleranceCalcRule implements
    IRule<OrderViewVO> {
  private UFBoolean isUserConfirm;

  private String tolerFiled;

  private ctrltype tolerPara;

  private String wbNumField;

  /**
   * OrderWbTolerRule �Ĺ�����
   * 
   * @param wbNumFiled ��д���ֶ�����������������д������wbNumField="naccumarrvnum"��
   * @param tolerFiled �ݲ��ֶ�������������ݲtolerFiled="intolerance"��
   * @param tolerPara �ݲ����������PO02��tolerPara="�ϸ����"��
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
