package nc.ui.pu.m23.action;

import java.awt.event.ActionEvent;

import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.ui.pu.m23.view.ArriveCardForm;
import nc.ui.pu.m23.view.quickarr.QuickArrDlg;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.pubapp.uif2app.event.OrgChangedEvent;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.rule.transfer.AfterTransferUtil;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.meta.entity.vo.PseudoColumnAttribute;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;
import nc.vo.pubapp.util.VORowNoUtils;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.uif2.LoginContext;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单 快速收货 按钮处理类Action
 * </ul>
 * <p>
 * <p>
 *
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-12 下午02:15:12
 */
public class QuickArrUIAction extends AbstractReferenceAction {

  // 默认 普通到货
  public static final String NORMALARRIRE = "23-01";

  private static final long serialVersionUID = 7831180720611056274L;

  private ArriveCardForm billForm;

  private QuickArrDlg quickArrDlg;

  public QuickArrUIAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_QUICKRECEIVE);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {

    this.getQuickArrDlg().showModal();
    if (UIDialog.ID_OK != this.quickArrDlg.getResult()) {
      return;
    }

    if (StringUtils.isEmpty(this.quickArrDlg.getBillCodeValue())) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0036")/*@res "单据号为空，请输入单据号!"*/);
    }

    ArriveVO[] retVOArray = this.quickArrDlg.getResultVOArray();
    if (ArrayUtils.isEmpty(retVOArray)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0037")/*@res "没有符合条件的采购订单！"*/);
      return;
    }

    // 转单后续处理
    retVOArray = new AfterTransferUtil(retVOArray).process();

    this.saveVOArray(retVOArray, this.quickArrDlg.isBrowserBeforeSave());

    LoginContext context = this.billForm.getModel().getContext();
    ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0","04004040-0038")/*@res "快速收货完成"*/, context);
  }

  public ArriveCardForm getBillForm() {
    return this.billForm;
  }

  public void setBillForm(ArriveCardForm billForm) {
    this.billForm = billForm;
  }

  private ArriveVO[] fillTransType(ArriveVO[] aggVOArray) {
    for (ArriveVO vo : aggVOArray) {
      // 找流程中的交易类型已经在VO交换时处理
      if (StringUtils.isEmpty(vo.getHVO().getVtrantypecode())) {
        vo.getHVO().setVtrantypecode(QuickArrUIAction.NORMALARRIRE);
        String ctrantypeid = PfServiceScmUtil.getTrantypeidByCode(new String[] {
          QuickArrUIAction.NORMALARRIRE
        }).get(QuickArrUIAction.NORMALARRIRE);
        vo.getHVO().setCtrantypeid(ctrantypeid);
      }
    }
    return aggVOArray;
  }

  private QuickArrDlg getQuickArrDlg() {
    if (this.quickArrDlg == null) {
      this.quickArrDlg = new QuickArrDlg(this.billForm);
    }

    return this.quickArrDlg;
  }

  private void saveVOArray(ArriveVO[] voArray, boolean browserBeforeSave) {
    if (browserBeforeSave) {
      // 按照拉单处理
      this.getTransferViewProcessor().processBillTransfer(voArray);

      // 触发编辑事件
      String stockOrg = voArray[0].getHVO().getPk_org();
      this.getBillForm().getModel()
          .fireEvent(new OrgChangedEvent(null, stockOrg));
    }
    else {
      // 生成差异VO
      ClientBillToServer<ArriveVO> tool1 = new ClientBillToServer<ArriveVO>();
      ArriveVO[] voArray2 = tool1.constructInsert(voArray);

      // 确定交易类型
      this.fillTransType(voArray2);

      // 设置表体行伪列值
      this.setPseudoColumn(voArray2);

      // 设置行号
      VORowNoUtils.setVOsRowNoByRule(voArray2, ArriveItemVO.CROWNO);

      ArriveVO[] retVOs = new ArriveVO[voArray2.length];
      for (int i = 0, len = voArray2.length; i < len; i++) {
        // 调脚本后台保存,获得返回差异值
        String transType = voArray2[i].getHVO().getVtrantypecode();
        // 模拟从模板取数据,set一下,SuperVO中valueIndex才有数据,才能保证单据号从后台传到前台
        this.setVOForLightProc(voArray2[i]);
        try {
          retVOs[i] =
              ((ArriveVO[]) PfUtilClient.runAction(null, "SAVEBASE", transType,
                  voArray2[i], null, null, null, null))[0];
        }
        catch (Exception ex) {
          ExceptionUtils.wrappException(ex);
        }
      }
      // 与界面合并成全VO
      ClientBillCombinServer<ArriveVO> tool2 =
          new ClientBillCombinServer<ArriveVO>();
      tool2.combine(voArray2, retVOs);

      for (int i = 0, len = voArray2.length; i < len; i++) {
        this.getBillForm().getModel().directlyAdd(voArray2[i]);
      }
    }
  }

  private ArriveVO[] setPseudoColumn(ArriveVO[] voArray) {
    for (ArriveVO vo : voArray) {
      ArriveItemVO[] items = vo.getBVO();
      for (int i = 0, len = items.length; i < len; i++) {
        String key = PseudoColumnAttribute.PSEUDOCOLUMN;
        vo.getBVO()[i].setAttributeValue(key, Integer.valueOf(i));
      }
    }
    return voArray;
  }

  private void setVOAtt(SuperVO vo, BillItem[] bis) {
    for (BillItem bi : bis) {
      vo.setAttributeValue(bi.getKey(), vo.getAttributeValue(bi.getKey()));
    }
  }

  private void setVOForLightProc(ArriveVO vo) {
    BillCardPanel bcp = this.billForm.getBillCardPanel();
    BillItem[] bis = bcp.getHeadItems();
    this.setVOAtt(vo.getHVO(), bis);
    bis = bcp.getTailItems();
    this.setVOAtt(vo.getHVO(), bis);
    bis = bcp.getBodyItems();
    for (ArriveItemVO item : vo.getBVO()) {
      this.setVOAtt(item, bis);
    }
  }
}