package nc.ui.pu.m23.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.pu.m23.pubquery.IArrivePubQuery;
import nc.ui.ml.NCLangRes;
import nc.ui.pu.m23.view.ArriveCardForm;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.utils.ArrivePublicUtil;
import nc.vo.pu.m23.utils.BatchSynchronizerM23;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.HslParseUtil;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.qc.pub.util.QCSysParamUtil;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.sm.funcreg.FuncRegisterVO;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单 紧急放行 按钮处理类Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-12 下午02:15:12
 */
public class UrgentLetGoUIAction extends NCAction {

  private static final long serialVersionUID = 6457241642041653036L;

  private ArriveCardForm form;

  private ShowUpableBillListView list;

  private BillManageModel model;

  public UrgentLetGoUIAction() {
    super();
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_URGENCYPASS);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    ArriveVO[] vos = new ArriveVO[1];
    vos[0] = this.getSelectedRowData();
    if (vos[0] == null) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0045")/*
                                                                   * @res
                                                                   * "请选择表体行！"
                                                                   */);
      return;
    }
    String pk_org = vos[0].getHVO().getPk_org();
    if (!SysInitGroupQuery.isQCEnabled()
        || UFBoolean.FALSE.equals(ValueUtils.getUFBoolean(QCSysParamUtil
            .getINI01(pk_org)))) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0044")/*
                                                                   * @res
                                                                   * "质检模块未启用,请检查!"
                                                                   */);
    }

    // 检查是否可以进行紧急放行
    this.checkCondition(vos);

    ArriveVO[] newvos = this.filterItemByStorecheck(vos);

    // 打开紧急放行单节点
    FuncletInitData initData = new FuncletInitData();
    initData.setInitType(Integer.valueOf(POBillType.Arrive.getCode())
        .intValue());
    initData.setInitData(newvos);
    WorkbenchEnvironment instance = WorkbenchEnvironment.getInstance();
    FuncRegisterVO funvo = instance.getFuncRegisterVO("C08005");// 节点编码
    FuncletWindowLauncher.openFuncNodeDialog(this.form, funvo, initData, null,
        true, true);

    this.refreshModel(newvos);
  }

  public ArriveCardForm getForm() {
    return this.form;
  }

  public ShowUpableBillListView getList() {
    return this.list;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public int[] getSelectedRows() {
    if (this.getForm().isShowing()) {
      return this.getForm().getBillCardPanel().getBillTable().getSelectedRows();
    }
    return this.getList().getBillListPanel().getBodyTable().getSelectedRows();
  }

  public void setForm(ArriveCardForm form) {
    this.form = form;
  }

  public void setList(ShowUpableBillListView list) {
    this.list = list;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  private void checkCondition(ArriveVO[] vos) {
    if (vos == null || vos.length == 0) {
      return;
    }
    for (ArriveVO vo : vos) {
      String code = vo.getHVO().getVbillcode();
      boolean isback = ValueUtils.getBoolean(vo.getHVO().getBisback());
      if (isback) {
        // 退货单不允许生成紧急放行单
        String msg =
            code
                + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                    "4004040_0", "04004040-0046")/* @res "退货单不可以进行紧急放行！" */;
        ExceptionUtils.wrappBusinessException(msg);
      }
      Object approvecode = POEnumBillStatus.APPROVE.value();
      if (!approvecode.equals(vo.getHVO().getFbillstatus())) {
        // 非审批通过的到货单可以进行紧急放行单!
        String msg =
            code
                + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                    "4004040_0", "04004040-0047")/* @res "非审批通过的到货单不可以进行紧急放行单!" */;
        ExceptionUtils.wrappBusinessException(msg);
      }

      for (ArriveItemVO item : vo.getBVO()) {
        String rowno = item.getCrowno();
        UFDouble naccumletgonum = MathTool.nvl(item.getNaccumletgonum());
        if (naccumletgonum.doubleValue() > 0
            || StringUtils.isNotEmpty(item.getPk_passbill())) {
          // 到货单行只支持一次紧急放行
          String msg =
              code
                  + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                      "4004040_0", "04004040-0009")/* @res "到货单行" */
                  + rowno
                  + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                      "4004040_0", "04004040-0048")/* @res "只支持一次紧急放行!" */;
          ExceptionUtils.wrappBusinessException(msg);
        }
        // 合格数量
        UFDouble nelignum = item.getNelignum();
        // 不合格数量
        UFDouble nnotelignum = item.getNnotelignum();
        if (MathTool.add(nelignum, nnotelignum).compareTo(UFDouble.ZERO_DBL) > 0) {
          // 如果表体行已经生成质检单,则不能进行紧急放行
          String msg =
              NCLangRes.getInstance().getStrByID("4004040_0", "04004040-0168",
                  null, new String[] {
                    code, rowno
                  })/* {0}到货单行{1}的质检结果已经返回，无法进行紧急放行! */;
          ExceptionUtils.wrappBusinessException(msg);
        }

        UFDouble narrnum = MathTool.nvl(item.getNnum());
        UFDouble naccumstorenum = MathTool.nvl(item.getNaccumstorenum());
        if (MathTool.compareTo(narrnum, naccumstorenum) == 0) {
          // 存在可以进行紧急放行的数量(到货主数量-累计入库数量>0)
          String msg =
              code
                  + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                      "4004040_0", "04004040-0009")/* @res "到货单行" */
                  + rowno
                  + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                      "4004040_0", "04004040-0049")/* @res "已经全部入库，不可以再紧急放行!" */;
          ExceptionUtils.wrappBusinessException(msg);
        }
      }
    }

  }

  private ArriveVO[] filterItemByStorecheck(ArriveVO[] vos) {
    if (vos == null || vos.length == 0) {
      return null;
    }
    // 批量查询到货单表体的物料所对应的库存组织页签的属性（根据检验结果入库、是否免检）
    ArriveItemVO[] items = AggVOUtil.getCombinItemVOs(vos);
    Map<String, MaterialStockVO> bidMrlMap = null;
    bidMrlMap = ArrivePublicUtil.queryMaterialStockInfo(items);
    List<ArriveVO> canLetgoVos = new ArrayList<ArriveVO>();
    for (ArriveVO vo : vos) {
      List<ArriveItemVO> canLetgoItems = new ArrayList<ArriveItemVO>();
      for (ArriveItemVO item : vo.getBVO()) {
        // 物料的属性“是否根据检验结果入库=是”的物料才可以生成紧急放行单。
        boolean bstockbycheck = false;
        // 是否免检
        boolean chkfreeflag = false;
        MaterialStockVO mrlvo = bidMrlMap.get(item.getPk_arriveorder_b());
        if (mrlvo != null) {
          bstockbycheck = ValueUtils.getBoolean(mrlvo.getStockbycheck());
          chkfreeflag = ValueUtils.getBoolean(mrlvo.getChkfreeflag());
        }

        UFDouble nnum = MathTool.sub(item.getNnum(), item.getNaccumbacknum());
        // 按检验结果入库并且非免检，且没有完全基于到货单退货的，可以紧急放行
        if (bstockbycheck && !chkfreeflag
            && MathTool.greaterThan(nnum, UFDouble.ZERO_DBL)) {
          canLetgoItems.add(item);

          // nnum如果和item.getNnum()不等，说明有退货的情况，否则不需要重新设置和反算数量
          if (!MathTool.equals(nnum, item.getNnum())) {
            // 主数量 = 主数量 - 累计退货主数量
            item.setNnum(nnum);

            // 折算数量
            item.setNastnum(HslParseUtil.hslDivUFDouble(item.getVchangerate(),
                item.getNnum()));
          }
        }
      }
      if (canLetgoItems.size() == 0) {
        // 非审批通过的到货单可以进行紧急放行单!
        String msg =
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
                "04004040-0050")/*
                                 * @res
                                 * "表体不存在根据检验结果入库的物料行，或已全部基于到货单退货，无法进行紧急放行!"
                                 */;
        ExceptionUtils.wrappBusinessException(msg);
      }
      ArriveVO newvo = new ArriveVO();
      newvo.setHVO(vo.getHVO());
      newvo.setBVO(canLetgoItems.toArray(new ArriveItemVO[0]));
      canLetgoVos.add(newvo);
    }
    return canLetgoVos.toArray(new ArriveVO[0]);
  }

  /**
   * 获取选中紧急放行的行数据
   * 
   * @return
   */
  private ArriveVO getSelectedRowData() {
    Object oldData = this.getModel().getSelectedData();

    if (oldData == null) {
      return null;
    }
    int[] selectedRows = this.getSelectedRows();
    if (selectedRows.length == 0) {
      return null;
    }
    ArriveVO newVO = new ArriveVO();
    newVO.setHVO(((ArriveVO) oldData).getHVO());
    ArriveItemVO[] orgItems = ((ArriveVO) oldData).getBVO();
    ArriveItemVO[] items = new ArriveItemVO[selectedRows.length];
    for (int i = 0; i < items.length; i++) {
      items[i] = orgItems[selectedRows[i]];
    }
    newVO.setBVO(items);
    // data.setChildrenVO(items);
    // return data;
    return newVO;
  }

  private boolean isOneVOEnable(ArriveVO vo) {
    if (vo == null) {
      return false;
    }
    if (!POEnumBillStatus.APPROVE.value().equals(vo.getHVO().getFbillstatus())) {
      return false;
    }
    ArriveItemVO[] bvos = vo.getBVO();
    if (ArrayUtils.isEmpty(bvos)) {
      return false;
    }
    return true;
  }

  // 刷新模型数据，主要因为紧急放行回写紧急放行数量后，界面没有刷新，导致紧急放行只有一次的校验失效。
  private void refreshModel(ArriveVO[] vos) throws BusinessException {
    List<String> arriveHIDs = new ArrayList<String>();
    String hid = "";
    for (ArriveVO vo : vos) {
      hid = vo.getHVO().getPk_arriveorder();
      arriveHIDs.add(hid);
    }

    IArrivePubQuery arrivePubQuery =
        NCLocator.getInstance().lookup(IArrivePubQuery.class);
    ArriveVO[] arriveVos =
        arrivePubQuery.queryAggVOByHids(arriveHIDs
            .toArray(new String[arriveHIDs.size()]));

    // 同步批次信息
    BatchSynchronizerM23.synchBatchCodeData(arriveVos);
    this.getModel().directlyUpdate(arriveVos);
  }

  @Override
  protected boolean isActionEnable() {
    BillManageModel model = (BillManageModel) this.form.getModel();
    if (model.getUiState() == UIState.EDIT || model.getUiState() == UIState.ADD
        || model.getSelectedData() == null) {
      return false;
    }

    Object[] objs = model.getSelectedOperaDatas();

    if (this.model.getSelectedData() != null && ArrayUtils.isEmpty(objs)) {
      return this.isOneVOEnable((ArriveVO) this.model.getSelectedData());
    }

    if (objs.length > 1) {
      return false;
    }
    return this.isOneVOEnable((ArriveVO) objs[0]);
  }
}
