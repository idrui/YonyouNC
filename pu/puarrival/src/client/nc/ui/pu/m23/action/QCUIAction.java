package nc.ui.pu.m23.action;

import java.awt.event.ActionEvent;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m23.qc.IArriveForQC;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.qc.c001.pu.ReturnObjectFor23;
import nc.ui.pu.m23.view.ArriveCardForm;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.editor.BillListView;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;
import nc.vo.qc.pub.enumeration.StrictLevelEnum;
import nc.vo.qc.pub.util.QCSysParamUtil;
import nc.vo.scmpub.res.SCMActionCode;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单 检验 按钮处理类Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-12 下午02:15:12
 */
public class QCUIAction extends NCAction {

  private static final long serialVersionUID = 7105286499110737794L;

  private ArriveCardForm form;

  private BillListView list;

  private BillManageModel model;

  public QCUIAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_VERIFY);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    ArriveVO vo = (ArriveVO) this.model.getSelectedData();
    String pk_org = vo.getHVO().getPk_org();
    // 先判断质检模块是否启用,再判断对应库存组织是否质检启用
    if (!SysInitGroupQuery.isQCEnabled()
        || UFBoolean.FALSE.equals(ValueUtils.getUFBoolean(QCSysParamUtil
            .getINI01(pk_org)))) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0030")/*
                                                                   * @res
                                                                   * "质检模块未启用,无法报检!"
                                                                   */);
    }

    ArriveItemVO[] bvos = this.getBVOs(vo);
    if (bvos.length == 0 || bvos[0] == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0005")/*
                                                                   * @res
                                                                   * "请选中表体行"
                                                                   */);
    }
    for (ArriveItemVO itemVO : bvos) {
      UFDouble naccumchecknum = itemVO.getNaccumchecknum();
      if (naccumchecknum != null) {
        if (naccumchecknum.compareTo(itemVO.getNnum()) == 0) {
          UFDouble naccumstorenum = itemVO.getNaccumstorenum();
          if (naccumstorenum != null
              && naccumstorenum.compareTo(UFDouble.ZERO_DBL) > 0) {
            ShowStatusBarMsgUtil.showStatusBarMsg(
                nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                    "4004040_0", "04004040-0214", null, new String[] {
                      itemVO.getCrowno()
                    })/* 第{0}行已经完全入库，不能生成报检单 */, this.model.getContext());
            return;
          }
        }
      }
    }
    ArriveVO newvo = (ArriveVO) vo.clone();
    newvo.setBVO(bvos);
    ArriveVO[] newvos = new ArriveVO[] {
      newvo
    };
    ArriveVO[] orgivos = new ArriveVO[] {
      vo
    };
    ClientBillToServer<ArriveVO> tool = new ClientBillToServer<ArriveVO>();
    ArriveVO[] lightVOs = tool.construct(newvos, newvos);

    Object[] objects =
        NCLocator.getInstance().lookup(IArriveForQC.class)
            .qualityCheck(lightVOs, false);
    ArriveVO[] returnVos = (ArriveVO[]) objects[0];
    ReturnObjectFor23 rof = (ReturnObjectFor23) objects[1];
    ShowStatusBarMsgUtil.showStatusBarMsg(
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
            "04004040-0034")/* @res "检验成功" */, this.getModel().getContext());
    // 得到质检模块的提示信息
    if (rof != null) {
      Map<String, Integer> strictMap = rof.getCsourcebid_strictlevel();
      for (ArriveVO hvo : newvos) {
        ArriveItemVO[] bvs = hvo.getBVO();
        for (ArriveItemVO bvo : bvs) {
          String bid = bvo.getPk_arriveorder_b();
          UFDouble naccumstorenum = bvo.getNaccumstorenum();
          if (naccumstorenum != null
              && naccumstorenum
                  .compareTo(bvo.getNnum() == null ? UFDouble.ZERO_DBL : bvo
                      .getNnum()) >= 0) {
          }
          if (strictMap.containsKey(bid)) {
            if (StrictLevelEnum.FREE.value().equals(strictMap.get(bid))) {
              ShowStatusBarMsgUtil.showStatusBarMsg(
                  nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                      "4004040_0", "04004040-0032", null, new String[] {
                        bvo.getCrowno()
                      })/*
                         * @res
                         * "第{0}行为质检连续批的严格程度为免检，不需要生成报检单！"
                         */, this.model.getContext());
            }
            else if (StrictLevelEnum.PAUSE.value().equals(strictMap.get(bid))) {
              ShowStatusBarMsgUtil.showStatusBarMsg(
                  nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                      "4004040_0", "04004040-0033", null, new String[] {
                        bvo.getCrowno()
                      })/*
                         * @res
                         * "第{0}行为质检连续批的严格程度为暂停，不能生成报检单！"
                         */, this.model.getContext());
            }
          }
        }
      }
    }
    new ClientBillCombinServer<ArriveVO>().combine(orgivos, returnVos);
    this.model.directlyUpdate(orgivos);
  }

  public ArriveCardForm getForm() {
    return this.form;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public void setForm(ArriveCardForm form) {
    this.form = form;
  }

  /**
   * @param list
   *          要设置的 list
   */
  public void setList(BillListView list) {
    this.list = list;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  /**
   * 得到选中的bvo
   * 
   * @param vo
   */
  private ArriveItemVO[] getBVOs(ArriveVO vo) {
    int[] rows = null;
    // 卡片界面
    if (((ShowUpableBillForm) this.form).isComponentVisible()) {
      BillCardPanel panel = this.form.getBillCardPanel();
      rows = panel.getBodyPanel().getTable().getSelectedRows();
    }
    else {
      // 列表界面
      BillListPanel panel = this.list.getBillListPanel();
      rows = panel.getBodyTable().getSelectedRows();
    }

    ArriveItemVO[] bvotmps = (ArriveItemVO[]) vo.getChildrenVO();
    ArriveItemVO[] bvos = new ArriveItemVO[rows.length];
    // Map<String, MaterialStockVO> bidMrlMap =
    // ArrivePublicUtil.queryMaterialStockInfo(bvotmps);
    for (int i = 0; i < rows.length; i++) {
      // UFDouble naccumstorenum = null;
      // UFDouble naccumletgoinnum = null;
      // UFDouble nelignum = null;
      // UFDouble nnotelignum = null;
      String pk_arriveorder_b = null;
      // String crowno = null;
      // 卡片界面
      if (((ShowUpableBillForm) this.form).isComponentVisible()) {
        pk_arriveorder_b =
            (String) this.form.getBillCardPanel().getBodyValueAt(rows[i],
                ArriveItemVO.PK_ARRIVEORDER_B);
        // naccumstorenum =
        // (UFDouble) this.form.getBillCardPanel().getBodyValueAt(rows[i],
        // ArriveItemVO.NACCUMSTORENUM);
        // naccumletgoinnum =
        // (UFDouble) this.form.getBillCardPanel().getBodyValueAt(rows[i],
        // ArriveItemVO.NACCUMLETGOINNUM);
        // crowno =
        // (String) this.form.getBillCardPanel().getBodyValueAt(rows[i],
        // ArriveItemVO.CROWNO);
        // nelignum =
        // (UFDouble) this.form.getBillCardPanel().getBodyValueAt(rows[i],
        // ArriveItemVO.NELIGNUM);
        // nnotelignum =
        // (UFDouble) this.form.getBillCardPanel().getBodyValueAt(rows[i],
        // ArriveItemVO.NNOTELIGNUM);

      }
      else {// 列表界面
        pk_arriveorder_b =
            (String) this.list.getBillListPanel().getBodyBillModel()
                .getValueAt(rows[i], ArriveItemVO.PK_ARRIVEORDER_B);
        // naccumstorenum =
        // (UFDouble) this.list.getBillListPanel().getBodyBillModel()
        // .getValueAt(rows[i], ArriveItemVO.NACCUMSTORENUM);
        // naccumletgoinnum =
        // (UFDouble) this.list.getBillListPanel().getBodyBillModel()
        // .getValueAt(rows[i], ArriveItemVO.NACCUMLETGOINNUM);
        // crowno =
        // (String) this.list.getBillListPanel().getBodyBillModel()
        // .getValueAt(rows[i], ArriveItemVO.CROWNO);
        // nelignum =
        // (UFDouble) this.list.getBillListPanel().getBodyBillModel()
        // .getValueAt(rows[i], ArriveItemVO.NELIGNUM);
        // nnotelignum =
        // (UFDouble) this.list.getBillListPanel().getBodyBillModel()
        // .getValueAt(rows[i], ArriveItemVO.NNOTELIGNUM);
      }
      // MaterialStockVO materialvo = bidMrlMap.get(pk_arriveorder_b);
      // boolean bstockbycheck =
      // ValueUtils.getBoolean(materialvo.getStockbycheck());
      // boolean chkfreeflag =
      // ValueUtils.getBoolean(materialvo.getChkfreeflag());
      // 按质检结果入库,非免检物料入库需要校验检验
      // if (bstockbycheck && !chkfreeflag) {
      // // 合格+不合格>0 质检报告已生成,累计入库-累计紧急放行入库>0,说明质检报告已入库,需限制检验
      // // 如果是紧急放行入库,则不用限制
      // if (MathTool.compareTo(MathTool.add(nelignum, nnotelignum),
      // UFDouble.ZERO_DBL) > 0
      // && MathTool.compareTo(
      // MathTool.sub(naccumstorenum, naccumletgoinnum),
      // UFDouble.ZERO_DBL) > 0) {
      // ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
      // .getNCLangRes().getStrByID("4004040_0", "04004040-0035", null,
      // new String[] {
      // crowno
      // })/* @res "第{0}行已根据质检报告结果入库，不允许检验！" */);
      // }
      // }
      for (int j = 0; j < bvotmps.length; j++) {
        if (bvotmps[j].getPk_arriveorder_b().equals(pk_arriveorder_b)) {
          bvos[i] = bvotmps[j];
          break;
        }
      }
    }
    return bvos;
  }

  private boolean isOneVOEnable(ArriveVO vo) {
    if (!POEnumBillStatus.APPROVE.value().equals(vo.getHVO().getFbillstatus())) {
      return false;
    }
    int[] selectedRows = null;
    // 卡片界面
    if (((ShowUpableBillForm) this.form).isComponentVisible()) {
      selectedRows =
          this.form.getBillCardPanel().getBodyPanel().getTable()
              .getSelectedRows();
    }
    else {
      // 列表界面
      selectedRows =
          this.list.getBillListPanel().getBodyTable().getSelectedRows();
    }
    if (selectedRows == null || selectedRows.length == 0) {
      return false;
    }
    ArriveItemVO[] orgItems = vo.getBVO();
    if (orgItems == null) {
      return false;
    }
    return true;
  }

  @Override
  protected boolean isActionEnable() {
    if (this.getModel().getAppUiState() == AppUiState.EDIT
        || this.getModel().getSelectedData() == null) {
      return false;
    }

    Object[] objs = this.getModel().getSelectedOperaDatas();

    if (this.model.getSelectedData() != null && ArrayUtils.isEmpty(objs)) {
      return this.isOneVOEnable((ArriveVO) this.model.getSelectedData());
    }
    if (objs.length > 1) {
      return false;
    }
    return this.isOneVOEnable((ArriveVO) objs[0]);
  }
}
