package nc.vo.pu.pub.util;

import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.VOStatus;

/**
 * �ù�������Ҫ��������AggregatedValueObject��VO��������VO������ֵ�Լ���VO��ȡֵ
 * ֻ���ڵ��ӱ�AggregatedValueObject
 * �����жϾۺ�VO�Ƿ�Ϊ�գ�����ȡ�ӱ������ж���ȡ���Ƿ񳬳�����
 * 
 * @since 6.0
 * @version 2012-3-7 ����09:51:01
 * @author wuxla
 */
public class AggVOHelper<E extends AggregatedValueObject> implements IKeyValue {
  private E vo;

  public AggVOHelper(E vo) {
    this.vo = vo;
  }

  @Override
  public Object getBodyValue(int row, String itemKey) {
    return this.vo.getChildrenVO()[row].getAttributeValue(itemKey);
  }

  @Override
  public Object getHeadValue(String itemKey) {
    return this.vo.getParentVO().getAttributeValue(itemKey);
  }

  @Override
  public int getItemCount() {
    return this.vo.getChildrenVO() == null ? 0 : this.vo.getChildrenVO().length;
  }

  @Override
  public int getItemCount(String tableCode) {
    // ֻ�����ӱ�ṹ
    return this.getItemCount();
  }

  @Override
  public RowStatus getRowStatus(int row) {
    int vostatus = this.vo.getChildrenVO()[row].getStatus();
    if (vostatus == VOStatus.UNCHANGED) {
      return RowStatus.UNCHANGED;
    }
    else if (vostatus == VOStatus.UPDATED) {
      return RowStatus.UPDATED;
    }
    else if (vostatus == VOStatus.NEW) {
      return RowStatus.NEW;
    }
    else if (vostatus == VOStatus.DELETED) {
      return RowStatus.DELETED;
    }
    return RowStatus.UNCHANGED;
  }

  @Override
  public void setBodyValue(int row, String itemKey, Object value) {
    this.vo.getChildrenVO()[row].setAttributeValue(itemKey, value);
  }

  @Override
  public void setBodyValue(int row, String itemKey, Object value,
      String tableCode) {
    // ��������ӱ�
    this.setBodyValue(row, itemKey, value);
  }

  @Override
  public void setHeadValue(String itemKey, Object value) {
    this.vo.getParentVO().setAttributeValue(itemKey, value);
  }

}
