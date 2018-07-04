package nc.vo.pu.pub.util;

import nc.vo.pub.IAttributeMeta;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>该工具类主要用来操作IBill的VO，用来向VO上设置值以及从VO上取值
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-2-2 下午07:50:27
 */
public class BillHelper<E extends IBill> implements IKeyValue {
  private E vo;

  public BillHelper(E vo) {
    this.vo = vo;
  }

  /**
   * 由VO数组生成BillHelper数组的代理方法
   * 
   * @param vos 要处理的单据VO数组
   * @return BillHelper数组
   */
  public static <E extends IBill> BillHelper<E>[] declareArray(E[] vos) {
    BillHelper<E>[] bhs = new BillHelper[vos.length];
    for (int i = 0; i < vos.length; i++) {
      bhs[i] = new BillHelper<E>(vos[i]);
    }
    return bhs;
  }

  @Override
  public Object getBodyValue(int row, String itemKey) {
    IVOMeta[] metas = this.vo.getMetaData().getChildren();
    for (IVOMeta meta : metas) {
      IAttributeMeta ameta = meta.getAttribute(itemKey);
      if (ameta != null) {
        return this.vo.getChildren(meta)[row].getAttributeValue(itemKey);
      }
    }
    return null;
  }

  @Override
  public Object getHeadValue(String itemKey) {
    return this.vo.getParent().getAttributeValue(itemKey);
  }

  @Override
  public int getItemCount() {
    IVOMeta[] metas = this.vo.getMetaData().getChildren();
    return this.vo.getChildren(metas[0]) == null ? 0 : this.vo
        .getChildren(metas[0]).length;
  }

  @Override
  public int getItemCount(String tableCode) {
    IVOMeta[] metas = this.vo.getMetaData().getChildren();
    for (IVOMeta meta : metas) {
      if (meta.getLabel().equals(tableCode)) {
        return this.vo.getChildren(meta).length;
      }
    }
    return 0;
  }

  @Override
  public RowStatus getRowStatus(int row) {
    IVOMeta[] metas = this.vo.getMetaData().getChildren();
    for (IVOMeta meta : metas) {
      int vostatus = this.vo.getChildren(meta)[row].getStatus();
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
    }
    return RowStatus.UNCHANGED;
  }

  @Override
  public void setBodyValue(int row, String itemKey, Object value) {
    IVOMeta[] metas = this.vo.getMetaData().getChildren();
    for (IVOMeta meta : metas) {
      IAttributeMeta ameta = meta.getAttribute(itemKey);
      if (ameta != null) {
        this.setNewValue(this.vo.getChildren(meta)[row], itemKey, value);
        break;
      }
    }
  }

  @Override
  public void setBodyValue(int row, String itemKey, Object value,
      String tableCode) {
    IVOMeta[] metas = this.vo.getMetaData().getChildren();
    for (IVOMeta meta : metas) {
      if (meta.getLabel().equals(tableCode)) {
        this.setNewValue(this.vo.getChildren(meta)[row], itemKey, value);
        break;
      }
    }
  }

  @Override
  public void setHeadValue(String itemKey, Object value) {
    this.setNewValue(this.vo.getParent(), itemKey, value);
  }

  private void setNewValue(ISuperVO vo, String itemKey, Object value) {
    Object old = vo.getAttributeValue(itemKey);
    if (old == null && value == null) {
      return;
    }
    if (old != null && old.equals(value)) {
      return;
    }
    vo.setAttributeValue(itemKey, value);
    if (vo.getStatus() == VOStatus.UNCHANGED) {
      vo.setStatus(VOStatus.UPDATED);
    }
  }
  
  public void setBodyVOs(ISuperVO[] vos,Class<? extends ISuperVO> clazz){
  	this.vo.setChildren(clazz, vos);
  }
  
}
