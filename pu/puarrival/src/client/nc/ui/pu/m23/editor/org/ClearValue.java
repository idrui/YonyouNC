package nc.ui.pu.m23.editor.org;

import nc.ui.pu.m23.utils.ArriveClientUtil;
import nc.ui.pu.pub.editor.org.IOrgChangedEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>主组织改变时，清空表头、表体中，相关字段的值
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-29 下午01:43:36
 */
public class ClearValue implements IOrgChangedEventListener {

  @Override
  public void process(ShowUpableBillForm cardForm) {

    BillCardPanel card = cardForm.getBillCardPanel();
    // 清空表头字段：部门、业务员
    String[] clearHeaderItemArray = new String[2];
    clearHeaderItemArray[0] = ArriveHeaderVO.PK_DEPT;
    clearHeaderItemArray[1] = ArriveHeaderVO.PK_PUPSNDOC;
    ArriveClientUtil.clearHeaderItemValues(card, clearHeaderItemArray);

    // 清空表体字段：收货仓库
    String[] clearBodyItemArray = new String[1];
    clearBodyItemArray[0] = ArriveItemVO.PK_RECEIVESTORE;

    ArriveClientUtil.clearBodyColoumValues(card, clearBodyItemArray);
  }
}
