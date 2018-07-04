package nc.ui.pu.m24.action;

import java.awt.event.ActionEvent;

import nc.bs.uif2.IActionCode;
import nc.ui.ml.NCLangRes;
import nc.ui.pu.m24.view.PricestlFormEditor;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.model.PFlowProxy;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.UIState;
import nc.ui.uif2.actions.ActionInitializer;
import nc.vo.pu.m24.entity.PricestlItemVO;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pu.m24.rule.PricestlSetDefaultRule;
import nc.vo.pu.m24.rule.PristlHphqRule;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.meta.entity.vo.PseudoColumnAttribute;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����������ⵥ���ɼ۸���㵥
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-8-2 ����09:09:31
 */
public class PricestlAddFromSourceAction extends
    nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction {

  private static final long serialVersionUID = 6488091921101067625L;

  private String sourceBillType;

  public PricestlAddFromSourceAction() {
    ActionInitializer.initializeAction(this, IActionCode.ADD);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    String pk_group = this.getModel().getContext().getPk_group();
    PfUtilClient.childButtonClicked(this.getSourceBillType(), pk_group, this
        .getModel().getContext().getPk_loginUser(),
        POBillType.PriceStl.getCode(), this.getModel().getContext()
            .getEntranceUI(), null, null);

    if (PfUtilClient.isCloseOK()) {
      PricestlVO[] vos = (PricestlVO[]) PfUtilClient.getRetVos();
      if (ArrayUtils.isEmpty(vos)) {
        ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004070_0", "04004070-0001")/*
                                                                     * @res
                                                                     * "��������0�ŵ���"
                                                                     */, this
            .getModel().getContext());
        return;
      }

      this.getFlowContext().clear();

      this.fillInformation(vos);

      this.setFakeRowNO(vos);

      // ��Ϊ���߽��棬�������ֶ�����
      this.setVOForLightProc(vos);

      // ����VO����
      ClientBillToServer<PricestlVO> tool =
          new ClientBillToServer<PricestlVO>();
      PricestlVO[] lightVOs = tool.constructInsert(vos);

      this.getFlowContext().setBillVos(lightVOs);
      Object[] retObj = PFlowProxy.runActionBatch(this.getFlowContext());

      new ClientBillCombinServer<PricestlVO>().combine(vos,
          (PricestlVO[]) retObj);
      if (vos == null || vos.length == 0) {
        if (PfUtilClient.isSuccess()) {
          this.model.setAppUiState(AppUiState.NOT_EDIT);
        }
        ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004070_0", "04004070-0001")/*
                                                                     * @res
                                                                     * "��������0�ŵ���"
                                                                     */, this
            .getModel().getContext());
        return;
      }

      // if (vos.length > 1) {
      // for (int i = 0, len = vos.length - 1; i < len; i++) {
      // this.model.getData().add(vos[i]);
      // }
      // }
      for (int i = 0, len = vos.length - 1; i <= len; i++) {
        this.model.directlyAdd(vos[i]);
      }

      this.model.setAppUiState(AppUiState.NOT_EDIT);
      int num = vos.length;
      ShowStatusBarMsgUtil.showStatusBarMsg(
          NCLangRes.getInstance().getStrByID("4004070_0", "04004070-0010",
              null, new String[] {
                String.valueOf(num)
              })/* ��������{0}�ŵ��� */, this.getModel().getContext());
    }
  }

  /**
   * �������������������Դ�������͡�
   * <p>
   * <b>����˵��</b>
   * 
   * @return ��Դ�������ͣ����Ƶ����뷵��nc.ui.pu.pub.action.ReferenceAction.MANUAL_CODE
   *         <p>
   * @since 6.0
   * @author GGR
   * @time 2010-8-2 ����09:09:31
   */
  public String getSourceBillType() {
    return this.sourceBillType;
  }

  public void setSourceBillType(String sourceBillType) {
    this.sourceBillType = sourceBillType;
    this.setCode(sourceBillType);
  }

  private PricestlVO[] fillInformation(PricestlVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return vos;
    }
    PricestlVO[] ret = vos;
    try {
      PristlHphqRule rule = new PristlHphqRule();

      ret = rule.getHqhpValue(ret);

      PricestlSetDefaultRule darule = new PricestlSetDefaultRule();

      return darule.setDefaultValue(ret, this.model.getContext()
          .getPk_loginUser());

    }
    catch (Exception ex) {
      ExceptionUtils.wrappException(ex);
    }
    return ret;
  }

  private void setFakeRowNO(PricestlVO[] voss) {
    for (PricestlVO value : voss) {

      AbstractBill bill = value;
      IVOMeta[] voMetas = bill.getMetaData().getChildren();
      if (voMetas == null) {
        return;
      }
      // ������ӱ�
      for (IVOMeta voMeta : voMetas) {
        ISuperVO[] vos = bill.getChildren(voMeta);
        if (vos != null) {
          for (int i = 0; i < vos.length; i++) {
            vos[i].setAttributeValue(PseudoColumnAttribute.PSEUDOCOLUMN,
                Integer.valueOf(i));
          }
        }
      }
    }
  }

  private void setVOAtt(SuperVO vo, BillItem[] bis) {
    for (BillItem bi : bis) {
      vo.setAttributeValue(bi.getKey(), vo.getAttributeValue(bi.getKey()));
    }
  }

  private void setVOForLightProc(PricestlVO[] vos) {
    for (PricestlVO vo : vos) {
      PricestlFormEditor billForm = (PricestlFormEditor) this.editor;
      BillCardPanel bcp = billForm.getBillCardPanel();
      BillItem[] bis = bcp.getHeadItems();
      this.setVOAtt(vo.getHVO(), bis);
      bis = bcp.getTailItems();
      this.setVOAtt(vo.getHVO(), bis);
      bis = bcp.getBodyItems();
      for (PricestlItemVO item : vo.getBVO()) {
        this.setVOAtt(item, bis);
      }
    }
  }

  @Override
  protected boolean isActionEnable() {
    return this.model.getUiState() == UIState.NOT_EDIT;
  }
}
