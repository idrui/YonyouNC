package nc.ui.pu.m20.action.revise;

import java.awt.event.ActionEvent;

import nc.itf.pubapp.pub.exception.IResumeException;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction;
import nc.vo.pu.exception.AskYesNoException;
import nc.vo.pu.m20.context.PraybillContext;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pflow.PfUserObject;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�빺�����������޸ı���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-1-27 ����07:40:16
 */
public class PraybillRSaveAction extends SaveScriptAction {

  private static final long serialVersionUID = 6556301184427090719L;

  private boolean hasAsk = false;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    // ����������ڣ����鶩�����ڣ��빺����
    if (!this.checkDate((PraybillVO) this.editor.getValue())) {
      return;
    }
    super.doAction(e);
  }

  /**
   * ������������:����������ڣ����鶩�����ڣ��빺���ڡ�
   * <p>
   * <b>����˵��</b>
   * 
   * @param card
   *          <p>
   * @since 6.0
   * @author GGR
   * @time 2010-3-30 ����06:03:19
   */
  private boolean checkDate(PraybillVO vo) {
    if (!this.hasAsk) {
      UFDate billDate = vo.getHVO().getDbilldate();
      if (null != billDate && null != vo.getBVO()) {
        // ûͨ���빺�����뽨�鶩�����ڡ���������У����кš�
        StringBuffer billDateWarnRows = new StringBuffer();
        // ûͨ�����鶩�����ں���������У����кš�
        StringBuffer sugDateErrRows = new StringBuffer();

        for (PraybillItemVO item : vo.getBVO()) {
          UFDate reqDate =
              item.getDreqdate() == null ? null : item.getDreqdate().asBegin();
          UFDate suggestdate =
              item.getDsuggestdate() == null ? null : item.getDsuggestdate()
                  .asBegin();

          // У���빺�����뽨�鶩�����ڡ��������ڵĹ�ϵ��
          if (null != reqDate
              && null != suggestdate
              && (reqDate.beforeDate(billDate) || suggestdate
                  .beforeDate(billDate))) {
            if (billDateWarnRows.length() > 0) {
              billDateWarnRows.append(",");
            }
            billDateWarnRows.append(item.getCrowno());
          }

          // У�齨�鶩�����ں��������ڵĹ�ϵ��
          if (null != reqDate && null != suggestdate
              && suggestdate.after(reqDate)) {
            sugDateErrRows.append(item.getCrowno());
          }
        }

        // ƴװ������Ϣ��
        String warning = "";
        if (billDateWarnRows.length() > 0) {
          warning +=
              NCLangRes.getInstance().getStrByID("4004020_0", "04004020-0086",
                  null, new String[] {
                    billDateWarnRows.toString()
                  })/* ��{0}�У��빺���ڴ��ڽ��鶩�����ڻ��������ڡ� */;
        }
        if (sugDateErrRows.length() > 0) {
          if (warning != "") {
            warning += "\n";
          }
          warning +=
              NCLangRes.getInstance().getStrByID("4004020_0", "04004020-0102",
                  null, new String[] {
                    sugDateErrRows.toString()
                  })/* ��{0}�У����鶩�����ڴ����������ڡ� */;
        }

        if (!StringUtils.isEmpty(warning)) {
          warning +=
              "\n"
                  + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                      "4004020_0", "04004020-0103");/*
                                                     * @res
                                                     * "�Ƿ�������棿"
                                                     */
          if (MessageDialog.showYesNoDlg(null, nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4004020_0", "04004020-0015")/*
                                                                       * @res
                                                                       * "��ʾ"
                                                                       */,
              warning) == UIDialog.ID_NO) {
            return false;
          }
        }
      }
    }
    else {
      this.hasAsk = false;
    }
    return true;
  }

  @Override
  protected boolean isResume(IResumeException resumeInfo) {
    int answer =
        MessageDialog.showYesNoDlg(this.getFlowContext().getParent(), null,
            ((Exception) resumeInfo).getMessage());

    if (UIDialog.ID_YES != answer) {
      return false;
    }

    PraybillContext ptx = new PraybillContext();
    PfUserObject pfuo = this.getFlowContext().getUserObj();
    if (null == pfuo) {
      ptx = new PraybillContext();
      pfuo = new PfUserObject();
    }
    else {
      ptx = (PraybillContext) pfuo.getUserObject();
    }

    if (resumeInfo instanceof AskYesNoException) {

      if (ptx == null) {
        ptx = new PraybillContext();
      }
      ptx.setPrayNumToleranceConfirm(UFBoolean.TRUE);

    }
    pfuo.setUserObject(ptx);
    this.getFlowContext().setUserObj(pfuo);
    return true;

  }
}
