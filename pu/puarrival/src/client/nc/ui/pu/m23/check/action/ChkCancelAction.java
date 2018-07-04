package nc.ui.pu.m23.check.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Action;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m23.qc.IArriveForQC;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.query2.model.ModelDataManager;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.entity.ArriveViewVO;
import nc.vo.pu.m23.utils.ArrivePublicUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.tool.BillComposite;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.qc.pub.util.QCSysParamUtil;
import nc.vo.scmpub.res.SCMActionCode;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单 反检 按钮处理类Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.5
 * @since 6.5
 * @author zhangshqb
 * @time 2014-9-23 下午15:00:12
 */
public class ChkCancelAction extends NCAction {

  private static final long serialVersionUID = -6111031157531226639L;

  private ModelDataManager dataManager;

  private BillManageModel model;

  public ChkCancelAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_VERIFY);
    this.putValue(Action.SHORT_DESCRIPTION, nc.vo.ml.NCLangRes4VoTransl
        .getNCLangRes().getStrByID("4004040_0", "04004040-0215")/* 反检 */);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    Object[] objs = this.model.getSelectedOperaDatas();
    List<ArriveItemVO> list = new ArrayList<ArriveItemVO>();
    // 累计入库数量
    List<ArriveViewVO> Naccumstorenum = new ArrayList<ArriveViewVO>();
    // 累计报检数量
    List<ArriveViewVO> Naccumchecknum = new ArrayList<ArriveViewVO>();
    // 资产卡片
    List<ArriveViewVO> Bfaflag = new ArrayList<ArriveViewVO>();
    for (Object obj : objs) {
      ArriveViewVO avo = (ArriveViewVO) obj;
      ArriveItemVO bvo = avo.getBVO();
      // 存在累计入库数量，不能反检
      if (bvo.getNaccumstorenum() != null
          && bvo.getNaccumstorenum().doubleValue() > 0) {
        Naccumstorenum.add(avo);
        continue;
      }
      // 累计报检数量为零，不能反检
      if (bvo.getNaccumchecknum() == null
          || bvo.getNaccumchecknum().doubleValue() == 0) {
        Naccumchecknum.add(avo);
        continue;
      }
      // 已生成资产卡片，不能反检
      if (bvo.getBfaflag() != null && bvo.getBfaflag().booleanValue()) {
        Bfaflag.add(avo);
        continue;
      }
      list.add(bvo);
    }
    if (Naccumstorenum.size() == 0 && Naccumchecknum.size() == 0
        && Bfaflag.size() == 0) {
//      ArriveVO[] vos = this.getArriveVOs(list.toArray(new ArriveViewVO[0]));

      ArriveItemVO[] avo =
          NCLocator.getInstance().lookup(IArriveForQC.class)
              .antiQualityCheck(list.toArray(new ArriveItemVO[list.size()]));
      // 清空累计合格主数量、累计报检数量、累计不合格数量
      if (null == avo) {
        ShowStatusBarMsgUtil.showStatusBarMsg(
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
                "04004040-0216")/* 反检失败！ */, this.model.getContext());
      }
      else {
        ShowStatusBarMsgUtil.showStatusBarMsg(
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
                "04004040-0217")/* 反检成功！ */, this.model.getContext());
      }
//      new ClientBillCombinServer<ArriveVO>().combine(vos, avo);
//      ArriveViewVO[] newViews = ArrivePublicUtil.convertAggToViewVO(vos);
//      this.model.directlyUpdate(newViews);
      this.dataManager.refresh();
    }
    else {
      String strErr = "";
      if (Naccumstorenum.size() != 0) {
        strErr +=
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
                "04004040-0218")/* 以下到货单号已经全部或者部分入库(即累计入库数量不为零)，不能反检！ */;
        for (ArriveViewVO storenum : Naccumstorenum) {
          String vbillcode = storenum.getVbillcode();
          strErr += vbillcode + " ";
        }
      }
      strErr += "\r";
      if (Naccumchecknum.size() != 0) {
        strErr +=
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
                "04004040-0219")/* 以下到货单号累计报检数量为零，不能反检！ */;
        for (ArriveViewVO storenum : Naccumchecknum) {
          String vbillcode = storenum.getVbillcode();
          strErr += vbillcode + " ";
        }
      }
      strErr += "\r";
      if (Bfaflag.size() != 0) {
        strErr +=
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
                "04004040-0220")/* 以下到货单号已生成资产卡片，不能反检: */;
        for (ArriveViewVO storenum : Bfaflag) {
          String vbillcode = storenum.getVbillcode();
          strErr += vbillcode + " ";
        }
      }
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0216")/* 反检失败！ */
          + strErr);
    }
  }

  public ModelDataManager getDataManager() {
    return this.dataManager;
  }

  public void setDataManager(ModelDataManager dataManager) {
    this.dataManager = dataManager;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  private ArriveVO[] getArriveVOs(ArriveViewVO[] views) {
    List<ArriveHeaderVO> headers = new ArrayList<ArriveHeaderVO>();
    List<ArriveItemVO> items = new ArrayList<ArriveItemVO>();
    for (AbstractDataView view : views) {
      headers.add((ArriveHeaderVO) view.getVO(ArriveHeaderVO.class));
      items.add((ArriveItemVO) view.getVO(ArriveItemVO.class));
    }

    BillComposite<ArriveVO> bc = new BillComposite<ArriveVO>(ArriveVO.class);
    ArriveVO tempVO = new ArriveVO();
    bc.append(tempVO.getMetaData().getParent(),
        headers.toArray(new ArriveHeaderVO[headers.size()]));
    bc.append(tempVO.getMetaData().getVOMeta(ArriveItemVO.class),
        items.toArray(new ArriveItemVO[items.size()]));
    return bc.composite();
  }

  @Override
  protected boolean isActionEnable() {
    if (this.model.getAppUiState() == AppUiState.EDIT
        || this.model.getSelectedData() == null) {
      return false;
    }
    Object[] objs = this.model.getSelectedOperaDatas();
    // 获取所选择行并且判断是否存在行满足条件(累计不合格主数量或者累计合格主数量或者累计报检数量不为null)
    if (objs == null || objs.length == 0) {
      return false;
    }
    ArriveViewVO vo = (ArriveViewVO) objs[0];
    String pk_org = vo.getHVO().getPk_org();
    // 判断是否启用质检模块
    if (SysInitGroupQuery.isQCEnabled()
        && UFBoolean.TRUE.equals(ValueUtils.getUFBoolean(QCSysParamUtil
            .getINI01(pk_org)))) {
      return false;
    }
    for (Object obj : objs) {
      ArriveViewVO avo = (ArriveViewVO) obj;
      ArriveItemVO bvo = avo.getBVO();
      // 累计不合格主数量
      UFDouble Nnotlignum = bvo.getNnotelignum();
      // 累计合格主数量
      UFDouble Nelignum = bvo.getNelignum();
      // 累计报检主数量
      UFDouble Naccumchecknum = bvo.getNaccumchecknum();
      if (Nnotlignum != null && Nnotlignum.doubleValue() > 0
          || Nelignum != null && Nelignum.doubleValue() > 0
          || Naccumchecknum != null && Naccumchecknum.doubleValue() > 0) {
        return true;
      }
    }
    return false;
  }
}
