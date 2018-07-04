package nc.ui.pu.m20.billref;

import nc.ui.pu.m20.editor.util.PraybillScaleUtil;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.ClientContext;
import nc.ui.pubapp.billref.src.IRefPanelInit;

/**
 * @since 6.0
 * @version 2011-4-26 ÏÂÎç05:49:09
 * @author Ìï·æÌÎ
 */

public class M20RefUIInit implements IRefPanelInit {

  @Override
  public void refMasterPanelInit(BillListPanel masterPanel) {
    new PraybillScaleUtil().setListScale(masterPanel, ClientContext
        .getInstance().getPk_group());
  }

  @Override
  public void refSinglePanelInit(BillListPanel singlePanel) {
    new PraybillScaleUtil().setSingleTableScale(singlePanel, ClientContext
        .getInstance().getPk_group());
  }

}
