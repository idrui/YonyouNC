package nc.ui.pu.m20.editor.org;

import nc.ui.pu.m20.editor.card.pub.BodyLineDefaultValue;
import nc.ui.pu.m20.editor.card.pub.HeaderDefaultValue;
import nc.ui.pu.m20.editor.util.PraybillScaleUtil;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.org.IOrgChangedEventListener;
import nc.ui.pu.pub.util.PuVDefFilterUtil;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.UIState;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>库存组织的编辑后事件类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-3-04 上午11:32:22
 */
public class StockOrg implements IOrgChangedEventListener {
  @Override
  public void process(ShowUpableBillForm cardForm) {
    if (cardForm.getModel().getUiState() == UIState.ADD) {
      // 清除表单数据
      cardForm.addNew();

      CardEditorHelper helper =
          new CardEditorHelper(cardForm.getBillCardPanel());
      new HeaderDefaultValue(helper, cardForm.getModel().getContext())
          .setDefaultValue();

      // 设置表体默认值
      new BodyLineDefaultValue(helper, cardForm.getModel().getContext())
          .setDefaultValue(0);
    }

    // 设置精度
    new PraybillScaleUtil().orgChgScale(cardForm);
    new PuVDefFilterUtil().setOrgForDefRef(cardForm.getBillCardPanel(),
        cardForm.getModel().getContext());
  }

}
