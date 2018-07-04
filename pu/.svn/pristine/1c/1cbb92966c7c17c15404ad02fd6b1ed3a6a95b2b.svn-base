package nc.ui.pu.pub.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.ic.onhand.OnhandDialog;
import nc.ui.pu.pub.view.PUBillForm;
import nc.ui.pub.beans.constenum.DefaultConstEnum;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.BillListView;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.vo.ic.onhand.entity.OnhandDimVO;
import nc.vo.ic.pub.util.NCBaseTypeUtils;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.onhand.entity.OnhandDlgPUHeaderVO;
import nc.vo.pu.report.scale.PUPubMetaNameConst;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.view.IDataView;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 存量查拣action
 * 
 * @author wangceb
 */
public class QueryOnhandAction extends NCAction {

  private static final String path = "nc/ui/pu/pub/action/queryonhand.xml";

  private static final long serialVersionUID = -7228894679966512759L;

  private OnhandDialog dlg;

  private PUBillForm form;

  private BillListView list;

  private BillManageModel model;

  public QueryOnhandAction() {
    SCMActionInitializer
        .initializeAction(this, SCMActionCode.IC_ONHANDDISPHIDE);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {

    this.dlg =
        new OnhandDialog(this.getModel().getContext().getEntranceUI(), true);

    // 获得表体维度，以便从数据中取值
    Map<String, String> bodyDims = this.getBodyDims();

    List<IDataView> headVOs = new ArrayList<IDataView>();
    int rowcount = -1;
    BillModel bm = null;
    if (this.form.isShowing()) {
      rowcount = this.form.getBillCardPanel().getRowCount();
      bm = this.form.getBillCardPanel().getBillModel();
    }
    else if (this.list.isShowing()) {
      rowcount =
          this.list.getBillListPanel().getChildListPanel().getTable()
              .getRowCount();
      bm = this.list.getBillListPanel().getBodyBillModel();
    }
    if (rowcount != -1 && bm != null) {
      this.setDialogData(headVOs, bodyDims, rowcount, bm);
    }
    FuncletInitData initData = new FuncletInitData();

    initData.setInitData(headVOs.toArray(new IDataView[headVOs.size()]));

    this.dlg.initUI(this.getModel().getContext(), QueryOnhandAction.path,
        initData, this.form.isEditable());
    this.dlg.showModal();
  }

  /**
   * 获得存量维度
   * 
   * @return
   */
  private Map<String, String> getBodyDims() {
    Map<String, String> bodyDims = new HashMap<String,String>();

    bodyDims.put(OnhandDimVO.PK_GROUP, PUPubMetaNameConst.PK_GROUP);
    bodyDims.put(OnhandDimVO.PK_ORG, PUPubMetaNameConst.PK_ORG);
    bodyDims.put(OnhandDimVO.CMATERIALOID, PUPubMetaNameConst.PK_SRCMATERIAL);
    bodyDims.put(OnhandDimVO.CMATERIALVID, PUPubMetaNameConst.PK_MATERIAL);
    bodyDims.put(OnhandDimVO.CASTUNITID, PUPubMetaNameConst.CASTUNITID);
    bodyDims.put(OnhandDimVO.VCHANGERATE, PUPubMetaNameConst.VCHANGERATE);
    bodyDims.put(OnhandDimVO.VBATCHCODE, PUPubMetaNameConst.VBATCHCODE);
    bodyDims.put(PUPubMetaNameConst.CUNITID, PUPubMetaNameConst.CUNITID);
    // 固定辅助属性
    bodyDims.put(OnhandDimVO.CPROJECTID, PUPubMetaNameConst.CPROJECTID);
    bodyDims.put(OnhandDimVO.CASSCUSTID, PUPubMetaNameConst.CASSCUSTID);
    bodyDims.put(OnhandDimVO.CPRODUCTORID, PUPubMetaNameConst.CPRODUCTORID);
    bodyDims.put(OnhandDimVO.CFFILEID, PUPubMetaNameConst.CFFILEID);
    // 物料自由辅助属性
    for (int i = 1; i < 11; i++) {
      bodyDims.put("vfree" + i, "vfree" + i);
    }

    String type = this.getModel().getBillType();
    // 仓库和供应商字段不一致
    if (POBillType.MRBill.getCode().equals(type)) {
      bodyDims.put(OnhandDimVO.CVENDORID, StoreReqAppItemVO.CVENDORID);
      bodyDims.put(OnhandDimVO.CWAREHOUSEID, StoreReqAppItemVO.PK_REQSTORDOC);
    }
    else if (POBillType.PrayBill.getCode().equals(type)) {
      bodyDims.put(OnhandDimVO.CVENDORID, PraybillItemVO.PK_SUGGESTSUPPLIER);
      bodyDims.put(OnhandDimVO.CWAREHOUSEID, PraybillItemVO.PK_REQSTOR);
    }
    return bodyDims;
  }

  public PUBillForm getForm() {
    return this.form;
  }

  public BillListView getList() {
    return this.list;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public void setForm(PUBillForm form) {
    this.form = form;
  }

  public void setList(BillListView list) {
    this.list = list;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
  }

  /**
   * 设置数据
   * 
   * @param headVOs
   * @param bodyDims
   * @param dimvo
   */
  private void setDialogData(List<IDataView> headVOs, Map<String, String> bodyDims,
      int rowcount, BillModel bm) {
    for (int i = 0; i < rowcount; i++) {
      BillItem item = bm.getItemByKey(PUPubMetaNameConst.PK_MATERIAL);
      if (item == null) {
        continue;
      }
      if (NCBaseTypeUtils.isNull(bm.getValueAt(i,
          PUPubMetaNameConst.PK_MATERIAL))) {
        continue;
      }
      OnhandDimVO dimvo = new OnhandDimVO();
      for (Map.Entry<String, String> entry : bodyDims.entrySet()) {
        String value = entry.getValue();
        BillItem bodyitem = bm.getItemByKey(value);
        if (bodyitem == null) {
          continue;
        }
        Object dimValue = bm.getValueObjectAt(i, value);// 默认现存量维度字段同表体字段相同
        if (dimValue instanceof DefaultConstEnum) {
          dimValue = ((DefaultConstEnum) dimValue).getValue();
        }
        dimvo.setAttributeValue(entry.getKey(), dimValue);
      }
      OnhandDlgPUHeaderVO headVO = new OnhandDlgPUHeaderVO();
      DataViewMeta dataViewMeta = new DataViewMeta(dimvo.getClass());
      headVO.setDataViewMeta(dataViewMeta);
      headVO.setVO(dimvo);
      // 主单位
      Object dimValue = bm.getValueObjectAt(i, PUPubMetaNameConst.CUNITID);
      if (dimValue instanceof DefaultConstEnum) {
        dimValue = ((DefaultConstEnum) dimValue).getValue();
      }
      headVO.setCunitid(dimValue.toString());
      headVO.setCrowno((String) bm.getValueAt(i, PUPubMetaNameConst.CROWNO));
      headVO.setOnhandshouldnum((UFDouble) bm.getValueAt(i,
          PUPubMetaNameConst.NNUM));
      headVO.setOnhandshouldassnum((UFDouble) bm.getValueAt(i,
          PUPubMetaNameConst.NASTNUM));
      headVOs.add(headVO);
    }

  }

}
