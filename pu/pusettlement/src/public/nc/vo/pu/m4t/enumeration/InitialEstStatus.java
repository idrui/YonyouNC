package nc.vo.pu.m4t.enumeration;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;
import nc.vo.pubapp.enumeration.NCMDEnum;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>期初暂估单状态
 * </ul>
 * <p>
 * ${tags}
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-03-25 20:14:35
 */
public class InitialEstStatus extends NCMDEnum {

  // 审批
  public static final InitialEstStatus APPROVED = MDEnum.valueOf(
      InitialEstStatus.class, Integer.valueOf(3));

  // 自由
  public static final InitialEstStatus FEE = MDEnum.valueOf(
      InitialEstStatus.class, Integer.valueOf(0));

  public InitialEstStatus(IEnumValue enumvalue) {
    super(enumvalue);
  }

}
