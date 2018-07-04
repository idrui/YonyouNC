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
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单的新增、修改保存
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author linsf
 * @time 2010-1-27 下午07:40:16
 */
public class PraybillRSaveAction extends SaveScriptAction {

  private static final long serialVersionUID = 6556301184427090719L;

  private boolean hasAsk = false;

  @Override
  public void doAction(ActionEvent e) throws Exception {
    // 检查需求日期，建议订货日期，请购日期
    if (!this.checkDate((PraybillVO) this.editor.getValue())) {
      return;
    }
    super.doAction(e);
  }

  /**
   * 方法功能描述:检查需求日期，建议订货日期，请购日期。
   * <p>
   * <b>参数说明</b>
   * 
   * @param card
   *          <p>
   * @since 6.0
   * @author GGR
   * @time 2010-3-30 下午06:03:19
   */
  private boolean checkDate(PraybillVO vo) {
    if (!this.hasAsk) {
      UFDate billDate = vo.getHVO().getDbilldate();
      if (null != billDate && null != vo.getBVO()) {
        // 没通过请购日期与建议订货日期、需求日期校验的行号。
        StringBuffer billDateWarnRows = new StringBuffer();
        // 没通过建议订货日期和需求日期校验的行号。
        StringBuffer sugDateErrRows = new StringBuffer();

        for (PraybillItemVO item : vo.getBVO()) {
          UFDate reqDate =
              item.getDreqdate() == null ? null : item.getDreqdate().asBegin();
          UFDate suggestdate =
              item.getDsuggestdate() == null ? null : item.getDsuggestdate()
                  .asBegin();

          // 校验请购日期与建议订货日期、需求日期的关系。
          if (null != reqDate
              && null != suggestdate
              && (reqDate.beforeDate(billDate) || suggestdate
                  .beforeDate(billDate))) {
            if (billDateWarnRows.length() > 0) {
              billDateWarnRows.append(",");
            }
            billDateWarnRows.append(item.getCrowno());
          }

          // 校验建议订货日期和需求日期的关系。
          if (null != reqDate && null != suggestdate
              && suggestdate.after(reqDate)) {
            sugDateErrRows.append(item.getCrowno());
          }
        }

        // 拼装警告信息。
        String warning = "";
        if (billDateWarnRows.length() > 0) {
          warning +=
              NCLangRes.getInstance().getStrByID("4004020_0", "04004020-0086",
                  null, new String[] {
                    billDateWarnRows.toString()
                  })/* 第{0}行：请购日期大于建议订货日期或需求日期。 */;
        }
        if (sugDateErrRows.length() > 0) {
          if (warning != "") {
            warning += "\n";
          }
          warning +=
              NCLangRes.getInstance().getStrByID("4004020_0", "04004020-0102",
                  null, new String[] {
                    sugDateErrRows.toString()
                  })/* 第{0}行：建议订货日期大于需求日期。 */;
        }

        if (!StringUtils.isEmpty(warning)) {
          warning +=
              "\n"
                  + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                      "4004020_0", "04004020-0103");/*
                                                     * @res
                                                     * "是否继续保存？"
                                                     */
          if (MessageDialog.showYesNoDlg(null, nc.vo.ml.NCLangRes4VoTransl
              .getNCLangRes().getStrByID("4004020_0", "04004020-0015")/*
                                                                       * @res
                                                                       * "提示"
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
