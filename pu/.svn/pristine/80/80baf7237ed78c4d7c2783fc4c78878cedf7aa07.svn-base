/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-3 下午03:46:57
 */
package nc.vo.pu.m25.pub;

import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.uif2.LoginContext;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购发票默认值处理器
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-2-3 下午03:46:57
 */
public class InvoiceDefaultValueSetter {

  public static void setDefaultValueForRef(LoginContext context, InvoiceVO[] vos) {
    for (InvoiceVO vo : vos) {
      // 当前用户
      vo.getParentVO().setBillmaker(context.getPk_loginUser());
      InvoiceItemVO[] items = vo.getChildrenVO();
      int itemLen = items.length;
      for (int i = 0; i < itemLen; ++i) {
        // 行号
        items[i].setCrowno(Integer.toString((i + 1) * 10));
        if (items[i].getFtaxtypeflag() == null) {
          items[i].setFtaxtypeflag(Integer.valueOf(1));
        }
      }
    }
  }

}
