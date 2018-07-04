/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-21 ����09:14:58
 */
package nc.ui.pu.m422x.editor.org;

import nc.ui.pu.m422x.rule.ReferenceFilterByOrg;
import nc.ui.pu.m422x.rule.ScaleSetter;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pu.pub.editor.org.IOrgChangedEventListener;
import nc.ui.pu.pub.util.BusiDateSetter;
import nc.ui.pu.pub.util.PuVDefFilterUtil;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.model.IAppModelEx;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.rule.HeadDefaultValue;
import nc.vo.pu.pub.context.IContext;
import nc.vo.pu.pub.rule.SetPeptRule;
import nc.vo.pubapp.AppContext;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-21 ����09:14:58
 */
public class StockOrganization implements IOrgChangedEventListener {

  @Override
  public void process(ShowUpableBillForm cardForm) {
    if (((IAppModelEx) cardForm.getModel()).getAppUiState() == AppUiState.ADD) {
      CardEditorHelper helper =
          new CardEditorHelper(cardForm.getBillCardPanel());
      // ���������
      cardForm.addNew();

      // ���ñ�ͷĬ��ֵ
      new HeadDefaultValue(helper, (IContext) cardForm.getModel().getContext())
          .setDefaultValue();

      // ������������
      new BusiDateSetter().setBusiDate(helper);

      // ����������,���벿��
      new SetPeptRule(helper, cardForm.getModel().getContext(),
          StoreReqAppHeaderVO.PK_APPPSNH, StoreReqAppHeaderVO.PK_APPDEPTH,
          StoreReqAppHeaderVO.PK_APPDEPTH_V).setPsnAndDept();

    }
    // ����
    new ReferenceFilterByOrg(cardForm).filter();
    new PuVDefFilterUtil().setOrgForDefRef(cardForm.getBillCardPanel(),
        cardForm.getModel().getContext());
    new ScaleSetter(AppContext.getInstance().getPkGroup())
        .setCardScale(cardForm.getBillCardPanel());
  }

}
