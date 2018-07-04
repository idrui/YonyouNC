package nc.ui.pu.m20.billref.m52A1;

import nc.funcnode.ui.FuncletInitData;
import nc.ui.pu.m20.view.BillListView;
import nc.ui.pu.pub.view.PUBillForm;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener.IInitDataProcessor;
import nc.ui.uif2.UIState;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.pu.m20.entity.PraybillVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>计划订单 打开请购单界面 52A1
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author liuchx
 * @time 2010-5-24 上午11:01:08
 */
public class M52A1InitDataProcessor implements IInitDataProcessor {

  // <property name="model" ref="manageAppModel"/>
  // <property name="listview" ref="listView"/>
  // <property name="billFormEditor" ref="billFormEditor"/>
  private PUBillForm billFormEditor;

  private BillListView listview;

  private BillManageModel model;

  public PUBillForm getBillFormEditor() {
    return this.billFormEditor;
  }

  public BillListView getListview() {
    return this.listview;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  @Override
  public void process(FuncletInitData data) {
    PraybillVO[] vos = (PraybillVO[]) data.getInitData();
    this.getModel().initModel(vos);
    this.getModel().setUiState(UIState.NOT_EDIT);
    this.getListview().showMeUp();
  }

  public void setBillFormEditor(PUBillForm billFormEditor) {
    this.billFormEditor = billFormEditor;
  }

  public void setListview(BillListView listview) {
    this.listview = listview;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
  }

}
