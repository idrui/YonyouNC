/**
 * $文件说明$
 *
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-12 下午02:11:16
 */
package nc.ui.pu.costfactor.action;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.pu.costfactor.view.CostFactorListEditor;
import nc.ui.pu.costfactor.view.ListShowPanel;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.model.BillListModelDataManager;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pu.costfactor.entity.CostfactorItemVO;
import nc.vo.pu.costfactor.entity.CostfactorVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillToServer;
import nc.vo.scmpub.res.SCMActionCode;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>对成本要素的费用显示项进行排序
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-6-12 下午02:11:16
 */
public class CostShowOrderAction extends NCAction {

  // 对物料名称按照用户定义的顺序进行排序
  private static class CostfactorItemComparator implements Comparator<Object>,
      Serializable {
    private static final long serialVersionUID = -110911344991720517L;

    public CostfactorItemComparator() {
      //
    }

    @Override
    public int compare(Object o1, Object o2) {
      Integer nO1 = ((CostfactorItemVO) o1).getIshoworder();
      if (null == nO1) {
        nO1 = Integer.valueOf(Integer.MAX_VALUE);
      }
      Integer nO2 = ((CostfactorItemVO) o2).getIshoworder();
      if (null == nO2) {
        nO2 = Integer.valueOf(Integer.MAX_VALUE);
      }

      int nDis = nO1.intValue() - nO2.intValue();
      if (nDis > 0) {
        return 1;
      }
      else if (0 == nDis) {
        return 0;
      }
      else {
        return -1;
      }
    }
  }

  private static final long serialVersionUID = 7420330012206020912L;

  private CostFactorListEditor list;

  private List<CostfactorItemVO> listCostfactorItemVO =
      new ArrayList<CostfactorItemVO>();

  private List<String> listMaterialName = new ArrayList<String>();

  private Map<String, String> mapMaterial = new HashMap<String, String>();

  private AbstractAppModel model;

  private BillListModelDataManager modelDataManager = null;

  public CostShowOrderAction() {
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_FEESHOWORDER);
    // this.setBtnName("费用显示顺序");
    // this.setCode("costShowOrderAction");
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */

  @Override
  public void doAction(ActionEvent e) throws Exception {

    // 没有添加成本要素
    if (0 == this.list.getBillListPanel().getHeadBillModel().getRowCount()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004060_0", "04004060-0004")/*
                                                                   * @res
                                                                   * "请先增加成本要素!"
                                                                   */);
    }
    // 刷新数据
    this.getModelDataManager().initModel();
    // 处理表体项和物料名称，作为参数传递
    this.parseViewDataToList();

    ListShowPanel showPanel =
        new ListShowPanel(this.listCostfactorItemVO, this.listMaterialName);
    CostfactorVO[] selectObjects = this.getObjects();

    // 没有进成本的费用项
    if (ArrayUtils.isEmpty(selectObjects)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004060_0", "04004060-0005")/*
                                                                   * @res
                                                                   * "请先选择进成本的费用项!"
                                                                   */);
    }
    CostfactorVO[] oldVOs = new CostfactorVO[selectObjects.length];
    for (int i = 0; i < selectObjects.length; ++i) {
      oldVOs[i] = (CostfactorVO) selectObjects[i].clone();
    }

    if (UIDialog.ID_OK == showPanel.showModal()) {

      CostfactorVO[] objs = this.getObjects();

      if (!ArrayUtils.isEmpty(objs)) {

        // 生成差异VO
        ClientBillToServer<CostfactorVO> tool =
            new ClientBillToServer<CostfactorVO>();

        // 返回VO
        CostfactorVO[] retVOs = null;
        CostfactorVO[] updateObjs = tool.construct(oldVOs, objs);
        retVOs =
            (CostfactorVO[]) ((BillManageModel) this.getModel()).getService()
                .update(updateObjs);

        // 补全VO
        ClientBillCombinServer<CostfactorVO> tool1 =
            new ClientBillCombinServer<CostfactorVO>();
        tool1.combine(objs, retVOs);

        // 更新model
        this.getModel().directlyUpdate(objs);
        // 选中第一个
        ((BillManageModel) this.getModel()).setSelectedRow(0);
      }
    }
  }

  public CostFactorListEditor getList() {
    return this.list;
  }

  public AbstractAppModel getModel() {
    return this.model;
  }

  /**
   * @return modelDataManager
   */
  public BillListModelDataManager getModelDataManager() {
    return this.modelDataManager;
  }

  public void setList(CostFactorListEditor list) {
    this.list = list;
  }

  public void setModel(AbstractAppModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  /**
   * @param modelDataManager
   *          要设置的 modelDataManager
   */
  public void setModelDataManager(BillListModelDataManager modelDataManager) {
    this.modelDataManager = modelDataManager;
  }

  // 根据用户定义的顺序获得物料名称
  private List<String> getMaterialName(
      List<CostfactorItemVO> listCostfactorItemVOs, Map<String, String> map) {

    List<String> listStr = new ArrayList<String>();
    listStr.clear();
    for (CostfactorItemVO vo : listCostfactorItemVOs) {
      listStr.add(map.get(vo.getPk_material()));
    }
    return listStr;

  }

  private CostfactorVO[] getObjects() {
    List<CostfactorVO> lisVOs = new ArrayList<CostfactorVO>();
    BillModel bm = this.list.getBillListPanel().getHeadBillModel();
    int rowCount = bm.getRowCount();
    // 选择进入存货成本已勾选的行
    for (int num = 0; num < rowCount; num++) {
      if (UFBoolean.valueOf(bm.getValueAt(num, "bentercost").toString())
          .booleanValue()) {
        ((BillManageModel) this.model).setSelectedRow(num);
        lisVOs.add((CostfactorVO) this.model.getSelectedData());
      }
    }
    if (lisVOs.size() > 0) {
      return lisVOs.toArray(new CostfactorVO[lisVOs.size()]);
    }
    return null;
  }

  @Override
  protected boolean isActionEnable() {
    BillModel bm = this.list.getBillListPanel().getHeadBillModel();
    int rowCount = bm.getRowCount();
    if (rowCount == 0) {
      return false;
    }
    for (int num = 0; num < rowCount; num++) {
      if (ValueUtils.getBoolean(bm.getValueAt(num, "bentercost"))) {
        return true;
      }
    }
    return false;
  }

  // 将model中的数据解析到List中，包括VO和物料名称
  protected void parseViewDataToList() {

    this.mapMaterial.clear();
    // 清空物料名称
    this.listMaterialName.clear();

    // 清空表头项
    this.listCostfactorItemVO.clear();

    // model中的数据 对应的VO为CostfatorVO
    BillModel bm = this.list.getBillListPanel().getHeadBillModel();
    int rowCount = bm.getRowCount();
    BillModel bmBody = null;
    int selectedRowNum = ((BillManageModel) this.model).getSelectedRow();
    for (int num = 0; num < rowCount; num++) {
      if (ValueUtils.getBoolean(bm.getValueAt(num, "bentercost"))) {
        ((BillManageModel) this.model).setSelectedRow(num);
        bmBody = this.list.getBillListPanel().getBodyBillModel();
        CostfactorItemVO[] bodyVOs = null;
        bodyVOs = ((CostfactorVO) this.model.getSelectedData()).getChildrenVO();
        for (int iRow = 0; iRow < bmBody.getRowCount(); iRow++) {
          this.mapMaterial.put(bodyVOs[iRow].getPk_material(), bmBody
              .getValueAt(iRow, "pk_material.name").toString());
        }
        if (bodyVOs != null) {
          this.listCostfactorItemVO.addAll(Arrays.asList(bodyVOs));
        }
      }
    }
    CostfactorItemVO[] arrayCostfactorItemVOs =
        this.listCostfactorItemVO
            .toArray(new CostfactorItemVO[this.listCostfactorItemVO.size()]);
    Arrays.sort(arrayCostfactorItemVOs, new CostfactorItemComparator());
    this.listCostfactorItemVO.clear();

    this.listCostfactorItemVO.addAll(Arrays.asList(arrayCostfactorItemVOs));
    this.listMaterialName =
        this.getMaterialName(this.listCostfactorItemVO, this.mapMaterial);

    ((BillManageModel) this.model).setSelectedRow(selectedRowNum);
  }

}
