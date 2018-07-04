package nc.ui.pu.m4t.action;

import nc.itf.scmpub.reference.uap.bd.currency.CurrencyRate;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

public class InitialEstImportSaveAction extends InitialEstSaveAction {

  private static final long serialVersionUID = 4230743818527819508L;

  @Override
  public void doBeforAction() {
    this.fillInfo();
    super.doBeforAction();
  }

  private void fillInfo() {
    InitialEstVO vo = (InitialEstVO) this.editor.getValue();
    InitialEstHeaderVO headvo = vo.getHeader();
    if (headvo.getPk_group() == null) {
      // ²¹¼¯ÍÅ
      headvo.setPk_group(this.getModel().getContext().getPk_group());
    }
    if (headvo.getCcurrencyid() == null) {
      String ccurrencyid =
          OrgUnitPubService.queryOrgCurrByPk(headvo.getPk_org());
      headvo.setCcurrencyid(ccurrencyid);
    }
    if (headvo.getNexchangerate() == null) {
      String corigcurrencyid = headvo.getCorigcurrencyid();
      String ccurrencyid = headvo.getCcurrencyid();
      UFDouble rate =
          CurrencyRate.getCurrencySellRateByOrg(headvo.getPk_org(),
              corigcurrencyid, ccurrencyid, headvo.getDbilldate());
      headvo.setNexchangerate(rate);
    }
    this.editor.setValue(vo);
  }

  @Override
  protected void processReturnObj(Object[] pretObj) throws Exception {
    this.model.setAppUiState(AppUiState.ADD);
    super.processReturnObj(pretObj);
  }

  @Override
  protected AbstractBill[] produceLightVO(AbstractBill[] newVO) {
    return super.produceLightVO(newVO);
  }
}
