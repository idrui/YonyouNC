package nc.vo.pu.m21.rule;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.scale.ScaleUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-4-8 ����03:32:21
 */
public class UnitAndChangeRate {
  private IKeyValue keyValue;

  private ScaleUtils scale;

  public UnitAndChangeRate(IKeyValue keyValue) {
    this.keyValue = keyValue;
    this.scale = new ScaleUtils(AppContext.getInstance().getPkGroup());
  }

  /**
   * ������������������ҵ��λ�����ʺͱ��۵�λ�����۵�λ�����ʡ����ҵ��λ�仯����Ҫͬʱ�޸ı��۵�λ
   * <p>
   * <b>����˵��</b>
   * 
   * @param rows
   *          Ҫ���õĵ���������
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-4-8 ����03:35:04
   */
  public void setChangeRate(int[] rows) {
    for (int row : rows) {
      Object assistUnit =
          this.keyValue.getBodyValue(row, OrderItemVO.CASTUNITID);

      // ��ջ�����
      if (assistUnit == null) {
        this.keyValue.setBodyValue(row, OrderItemVO.VCHANGERATE, null);
        // ��ձ��۵�λ�ͱ��۵�λ������
        this.keyValue.setBodyValue(row, OrderItemVO.CQTUNITID, null);
        this.keyValue.setBodyValue(row, OrderItemVO.VQTUNITRATE, null);
        return;
      }

      // ��ѯ������
      String changeRate = this.getChangeRate(row, (String) assistUnit);
      this.keyValue.setBodyValue(row, OrderItemVO.VCHANGERATE, changeRate);

      // ͬ�����۵�λ�ͱ��۵�λ������
      this.keyValue.setBodyValue(row, OrderItemVO.CQTUNITID, assistUnit);
      this.keyValue.setBodyValue(row, OrderItemVO.VQTUNITRATE, changeRate);
    }
  }

  /**
   * ����ձ��ۻ�����
   * 
   * @param rows
   */
  public void setChangeRateNotChangeQt(int[] rows) {
    for (int row : rows) {
      Object assistUnit =
          this.keyValue.getBodyValue(row, OrderItemVO.CASTUNITID);

      // ��ջ�����
      if (assistUnit == null) {
        this.keyValue.setBodyValue(row, OrderItemVO.VCHANGERATE, null);
        return;
      }

      // ��ѯ������
      String changeRate = this.getChangeRate(row, (String) assistUnit);
      this.keyValue.setBodyValue(row, OrderItemVO.VCHANGERATE, changeRate);
    }
  }

  /**
   * �����������������ñ��۵�λ�����ʡ����۵�λ�仯�������޸�ҵ��λ��
   * <p>
   * <b>����˵��</b>
   * 
   * @param rows
   *          Ҫ���õĵ���������
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-4-8 ����03:35:28
   */
  public void setQtChangeRate(int[] rows) {
    for (int row : rows) {
      Object assistUnit =
          this.keyValue.getBodyValue(row, OrderItemVO.CQTUNITID);

      // ��ջ�����
      if (assistUnit == null) {
        this.keyValue.setBodyValue(row, OrderItemVO.VQTUNITRATE, null);
        return;
      }

      // ��ѯ������
      String changeRate = this.getChangeRate(row, (String) assistUnit);
      this.keyValue.setBodyValue(row, OrderItemVO.VQTUNITRATE, changeRate);

    }
  }

  private String getChangeRate(int row, String unit) {
    Object mainunit = this.keyValue.getBodyValue(row, OrderItemVO.CUNITID);
    String changeRate = "1/1";
    if (!unit.equals(mainunit)) {
      Object pk_material =
          this.keyValue.getBodyValue(row, OrderItemVO.PK_MATERIAL);
      changeRate =
          MaterialPubService.queryMainMeasRateByMaterialAndMeasdoc(
              (String) pk_material, unit);
    }
    return this.scale.adjustHslScale(changeRate);
  }
}
