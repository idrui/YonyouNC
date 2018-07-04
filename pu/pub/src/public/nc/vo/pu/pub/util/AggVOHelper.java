package nc.vo.pu.pub.util;

import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.VOStatus;

/**
 * 该工具类主要用来操作AggregatedValueObject的VO，用来向VO上设置值以及从VO上取值
 * 只用于单子表AggregatedValueObject
 * 不做判断聚合VO是否为空，或者取子表数据判断所取行是否超出界限
 * 
 * @since 6.0
 * @version 2012-3-7 上午09:51:01
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
    // 只处理单子表结构
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
    // 不处理多子表
    this.setBodyValue(row, itemKey, value);
  }

  @Override
  public void setHeadValue(String itemKey, Object value) {
    this.vo.getParentVO().setAttributeValue(itemKey, value);
  }

}
