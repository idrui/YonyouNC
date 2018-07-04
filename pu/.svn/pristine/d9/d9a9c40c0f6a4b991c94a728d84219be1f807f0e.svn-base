/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-2-2 上午09:31:46
 */
package nc.ui.pu.m25.editor.utils;

import nc.ui.pub.bill.BillCardPanel;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>新增(插入)行时设置默认值
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-2-2 上午09:31:46
 */
public class NewBodyRowDefaultValueHandler {

  public static void setDefaultRowValue(BillCardPanel bcp, InvoiceItemVO vo) {
    String pk_org =
        (String) bcp.getHeadItem(InvoiceHeaderVO.PK_ORG).getValueObject();
    String pk_org_v =
        (String) bcp.getHeadItem(InvoiceHeaderVO.PK_ORG_V).getValueObject();
    String pk_group =
        (String) bcp.getHeadItem(InvoiceHeaderVO.PK_GROUP).getValueObject();

    if (StringUtil.isEmptyWithTrim(vo.getPk_group())) {
      vo.setPk_group(pk_group);
    }
    if (StringUtil.isEmptyWithTrim(vo.getPk_org())) {
      vo.setPk_org(pk_org);
    }
    if (StringUtil.isEmptyWithTrim(vo.getPk_org_v())) {
      vo.setPk_org_v(pk_org_v);
    }
    if (StringUtil.isEmptyWithTrim(vo.getPk_apfinanceorg())) {
      vo.setPk_apfinanceorg(pk_org);
    }
    if (StringUtil.isEmptyWithTrim(vo.getPk_apfinanceorg_v())) {
      vo.setPk_apfinanceorg_v(pk_org_v);
    }
  }
}
