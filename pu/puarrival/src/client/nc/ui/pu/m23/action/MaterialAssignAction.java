package nc.ui.pu.m23.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.itf.pu.m23.fa.IArriveForFA;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.pu.m23.pubquery.IArrivePubQuery;
import nc.ui.pf.change.PfUtilUITools;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.linkoperate.ILinkType;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.BillForm;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.editor.BillListView;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.bd.material.IMaterialEnumConst;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.tool.BillIndex;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.sm.funcreg.FuncRegisterVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * 周转材直领
 * 
 * @since 6.0
 * @version 2010-12-20 下午03:04:05
 * @author wuxla
 */

public class MaterialAssignAction extends NCAction {

  private static final long serialVersionUID = 5118546673236093638L;

  private BillForm billForm;

  private BillListView list;

  private BillManageModel model;

  private int[] selectIndex;

  public MaterialAssignAction() {
    SCMActionInitializer.initializeAction(this,
        SCMActionCode.PU_TURNMARDIRECTTAKE);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {

    if (!SysInitGroupQuery.isRUMEnabled()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0185")/*
                                                                   * @res
                                                                   * "易耗品管理模块未启用,无法生成周转材!"
                                                                   */);
    }

    this.getSelectIndex();

    ArriveVO vo = this.getSelectedVO();
    if (null == vo) {
      return;
    }

    try {
      AggregatedValueObject[] srcVOs = new ArriveVO[] {
        vo
      };
      AggregatedValueObject[] destVOs =
          PfUtilUITools.runChangeDataAry(POBillType.Arrive.getCode(), "4A60",
              srcVOs);

      FuncletInitData initData = new FuncletInitData();
      initData.setInitType(ILinkType.LINK_TYPE_ADD);
      initData.setInitData(destVOs);

      FuncRegisterVO funvo =
          WorkbenchEnvironment.getInstance().getFuncRegisterVO("4580004005");
      if (null == funvo) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4004040_0", "04004040-0028")/*
                                                                     * @res
                                                                     * "资产管理未启用"
                                                                     */);
        return;
      }

      FuncletWindowLauncher.openFuncNodeDialog(WorkbenchEnvironment
          .getInstance().getWorkbench(), funvo, initData, null, true,true);

      // 重新查询
      IArrivePubQuery query =
          NCLocator.getInstance().lookup(IArrivePubQuery.class);
      ArriveVO newVO = query.queryAggVOByHid(vo.getHVO().getPk_arriveorder());
      this.getModel().directlyUpdate(newVO);
    }
    catch (Exception e1) {
      ExceptionUtils.wrappException(e1);
    }
  }

  public BillForm getBillForm() {
    return this.billForm;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  /**
   * 方法功能描述：得到选择的行
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-4-10 上午10:23:23
   */
  public int[] getSelectIndex() {
    // 卡片界面
    if (((ShowUpableBillForm) this.billForm).isComponentVisible()) {
      BillCardPanel panel = this.billForm.getBillCardPanel();
      this.selectIndex = panel.getBodyPanel().getTable().getSelectedRows();
    }
    else {// 列表界面
      BillListPanel panel = this.list.getBillListPanel();
      this.selectIndex = panel.getBodyTable().getSelectedRows();
    }
    return this.selectIndex;
  }

  /**
   * @param billForm
   *          要设置的 billForm
   */
  public void setBillForm(BillForm billForm) {
    this.billForm = billForm;
    this.getAppModel().addAppEventListener(this);
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
   * 方法功能描述：得到model
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-6 下午04:16:31
   */
  private AbstractAppModel getAppModel() {
    return this.billForm.getModel();
  }

  /**
   * 方法功能描述：得到Panel
   * <p>
   * <b>参数说明</b>
   * 
   * @return <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-4-10 上午10:21:05
   */
  private UIPanel getPanel() {
    UIPanel panel = null;

    return panel;
  }

  /**
   * 可直领的周转材料到货单记录
   * 
   * @return 可直领的周转材料到货单记录
   */
  private ArriveVO getSelectedVO() {
    ArriveVO vo = (ArriveVO) this.getModel().getSelectedData();
    if (null == vo) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0021")/*
                                                                   * @res
                                                                   * "请选择数据"
                                                                   */);
      return null;
    }

    if (!POEnumBillStatus.APPROVE.value().equals(vo.getHVO().getFbillstatus())) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0007")/*
                                                                   * @res
                                                                   * "到货单必须审核"
                                                                   */);
      return null;
    }

    int[] rows = this.getSelectIndex();
    if (ArrayUtils.isEmpty(rows)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0021")/*
                                                                   * @res
                                                                   * "请选择数据"
                                                                   */);
      return null;
    }
    vo = this.getVOByRow(vo, rows);
    if (ArrayUtils.isEmpty(vo.getBVO())) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0029")/*
                                                                   * @res
                                                                   * "无符合条件的到货单行"
                                                                   */);
      return null;
    }
    IArriveForFA service = NCLocator.getInstance().lookup(IArriveForFA.class);
    ArriveVO[] vos = new ArriveVO[] {
      vo
    };
    vos = new ClientBillToServer<ArriveVO>().construct(vos, vos);
    try {
      ArriveVO[] newVOs = service.queryArriveFor4A60(vos);
      if (!ArrayUtils.isEmpty(newVOs)) {
        return newVOs[0];
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  private Map<String, UFBoolean> getTurnoverMap(ArriveVO vo) {
    Map<String, UFBoolean> map = new HashMap<String, UFBoolean>();
    if (ArrayUtils.isEmpty(vo.getBVO())) {
      return map;
    }
    Set<String> materialSet = new HashSet<String>();
    for (ArriveItemVO item : vo.getBVO()) {
      materialSet.add(item.getPk_material());
    }
    Map<String, MaterialVO> result =
        MaterialPubService.queryMaterialBaseInfo(
            materialSet.toArray(new String[materialSet.size()]), new String[] {
              MaterialVO.MATERIALMGT
            });
    if (result == null) {
      return map;
    }
    for (Map.Entry<String, MaterialVO> ite : result.entrySet()) {
      UFBoolean isTurnover =
          ite.getValue().getMaterialmgt() == null ? UFBoolean.FALSE
              : UFBoolean
                  .valueOf(ite.getValue().getMaterialmgt().intValue() == IMaterialEnumConst.MATERIALMGT_TURNOVER);
      map.put(ite.getKey(), isTurnover);
    }
    return map;
  }

  /**
   * 选择表体数据，并根据物料“周转材料管理”属性过滤
   * 
   * @param vo
   * @param rows
   * @return
   */
  private ArriveVO getVOByRow(ArriveVO vo, int[] rows) {
    BillIndex index = new BillIndex(new ArriveVO[] {
      vo
    });
    Map<String, UFBoolean> matmap = this.getTurnoverMap(vo);
    IVOMeta meta = vo.getMetaData().getVOMeta(ArriveItemVO.class);
    List<ArriveItemVO> list = new ArrayList<ArriveItemVO>();
    for (int row : rows) {
      String pk_arriveorder_b = null;
      // 卡片界面
      if (((ShowUpableBillForm) this.billForm).isComponentVisible()) {
        pk_arriveorder_b =
            (String) this.billForm.getBillCardPanel().getBodyValueAt(row,
                ArriveItemVO.PK_ARRIVEORDER_B);
      }
      else {// 列表界面
        pk_arriveorder_b =
            (String) this.list.getBillListPanel().getBodyBillModel()
                .getValueAt(row, ArriveItemVO.PK_ARRIVEORDER_B);
      }

      ArriveItemVO itemVO = (ArriveItemVO) index.get(meta, pk_arriveorder_b);
      if (itemVO != null
          && UFBoolean.TRUE.equals(matmap.get(itemVO.getPk_material()))) {
        list.add(itemVO);
      }
    }
    ArriveItemVO[] itemVOs = list.toArray(new ArriveItemVO[list.size()]);
    ArriveVO newVO = (ArriveVO) vo.clone();
    newVO.setBVO(itemVOs);
    return newVO;
  }

  @Override
  protected boolean isActionEnable() {
    if (AppUiState.EDIT == this.getModel().getAppUiState()) {
      return false;
    }

    if (ArrayUtils.isEmpty(this.getSelectIndex())) {
      return false;
    }

    ArriveVO vo = (ArriveVO) this.getModel().getSelectedData();
    return vo != null
        && vo.getHVO() != null
        && POEnumBillStatus.APPROVE.value()
            .equals(vo.getHVO().getFbillstatus());
  }
}
