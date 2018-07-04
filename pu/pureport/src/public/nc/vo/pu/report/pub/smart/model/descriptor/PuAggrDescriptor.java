package nc.vo.pu.report.pub.smart.model.descriptor;

import nc.pub.smart.metadata.Field;
import nc.pub.smart.model.descriptor.AggrDescriptor;
import nc.pub.smart.model.descriptor.AggrItem;
import nc.pub.smart.model.descriptor.GroupItem;
import nc.vo.pu.report.queryinfo.PuQueryInfoPara;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-2-22 ����10:54:51
 * @author yinfy
 */

public class PuAggrDescriptor<T extends PuQueryInfoPara> extends AggrDescriptor {
  private static final long serialVersionUID = -4630965858093200366L;

  public PuAggrDescriptor(T para) {
    // ��Ӻϼ���
    String[] tolkeys = para.getTotalKeys();

    for (String key : tolkeys) {
      this.setAggrFields((AggrItem[]) ArrayUtils.add(this.getAggrFields(),
          new AggrItem(key, 0, key)));
    }
    // ��ӷ�������
    String[] gpkeys = para.getGroupKeys();

    for (String key : gpkeys) {
      Field field = new Field();
      field.setFldname(key);
      this.setGroupFields((GroupItem[]) ArrayUtils.add(this.getGroupFields(),
          new GroupItem(field)));
    }

  }
}
