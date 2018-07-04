package nc.vo.pu.pub.rule;

import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pu.pub.util.IKeyValue.RowStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���ݱ�ͷ���������ͼ�˰�ϼƵļ�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-26 ����09:46:26
 */
public class NumAndOrigmnySum {
  private String blargessField;

  private String headerMnyField = "ntotalorigmny";

  private String headerNumField = "ntotalastnum";

  private String itemMnyField = "norigtaxmny";

  private String itemNumField = "nastnum";

  private IKeyValue keyValue;

  public NumAndOrigmnySum(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * ���������������Ƿ��ų�ĳ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param row
   *          ��
   * @return ����ų������򷵻�true�����ų�������false
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-26 ����10:51:40
   */
  public boolean excludeRow(int row) {
    if (row >= 0) {
      return false;
    }
    return true;
  }

  /**
   * ��������������������Ʒ�ֶ���
   * <p>
   * <b>����˵��</b>
   * 
   * @param blargessField
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-26 ����10:41:11
   */
  public void setBlargessField(String blargessField) {
    this.blargessField = blargessField;
  }

  /**
   * �����������������ñ�ͷ��˰�ϼ��ֶ���
   * <p>
   * <b>����˵��</b>
   * 
   * @param headerMnyField
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-26 ����10:40:21
   */
  public void setHeaderMnyField(String headerMnyField) {
    this.headerMnyField = headerMnyField;
  }

  /**
   * �����������������ñ�ͷ�������ֶ���
   * <p>
   * <b>����˵��</b>
   * 
   * @param headerNumField
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-26 ����10:39:48
   */
  public void setHeaderNumField(String headerNumField) {
    this.headerNumField = headerNumField;
  }

  /**
   * �����������������ñ����˰�ϼ��ֶ���
   * <p>
   * <b>����˵��</b>
   * 
   * @param itemMnyField
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-26 ����10:40:54
   */
  public void setItemMnyField(String itemMnyField) {
    this.itemMnyField = itemMnyField;
  }

  /**
   * �����������������ñ����������ֶ���
   * <p>
   * <b>����˵��</b>
   * 
   * @param itemNumField
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-3-26 ����10:40:35
   */
  public void setItemNumField(String itemNumField) {
    this.itemNumField = itemNumField;
  }

  /**
   * ���������������������������ͼ�˰�ϼ�
   * <p>
   * <b>����˵��</b>
   * <p>
   * 
   * @since 6.0
   * @author duy
   * @time 2010-3-26 ����09:47:56
   */
  public void sum() {
    int rows = this.keyValue.getItemCount();
    UFDouble nnum = UFDouble.ZERO_DBL;
    UFDouble nmny = UFDouble.ZERO_DBL;
    for (int i = 0; i < rows; i++) {
      // ɾ���в�����
      if (this.keyValue.getRowStatus(i) == RowStatus.DELETED) {
        continue;
      }
      // ��Ʒ�м���������
      nnum =
          MathTool.add(nnum,
              (UFDouble) this.keyValue.getBodyValue(i, this.itemNumField));
      // ��Ʒ�в������ܽ��
      if (this.blargessField != null) {
        Object l = this.keyValue.getBodyValue(i, this.blargessField);
        if (UFBoolean.TRUE.equals(l)) {
          continue;
        }
      }
      // �����ų����в�����
      if (this.excludeRow(i)) {
        continue;
      }

      nmny =
          MathTool.add(nmny,
              (UFDouble) this.keyValue.getBodyValue(i, this.itemMnyField));
    }

    this.keyValue.setHeadValue(this.headerNumField, nnum);
    this.keyValue.setHeadValue(this.headerMnyField, nmny);
  }
}
