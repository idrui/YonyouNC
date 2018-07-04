/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-1 下午03:18:46
 */
package nc.ui.pu.m21transtype;

import nc.ui.pub.beans.constenum.IConstEnum;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-7-1 下午03:18:46
 */
public class StatusItem implements IConstEnum {

  private String name;

  private Integer value;

  public StatusItem(String name, Integer value) {
    this.name = name;
    this.value = value;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof IConstEnum) {
      IConstEnum em = (IConstEnum) obj;
      if ((null != em.getName()) && em.getName().equals(this.name)
          && (null != em.getValue()) && em.getValue().equals(this.value)) {
        return true;
      }

      return false;
    }

    return super.equals(obj);
  }

  @Override
  public String getName() {

    return this.name;
  }

  @Override
  public Object getValue() {

    return this.value;
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }

  @Override
  public String toString() {
    return this.name;
  }
}
