package nc.ui.pu.pub.combineshow;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.IBillData;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare;
import nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator;
import nc.ui.uif2.editor.UserdefQueryParam;
import nc.ui.uif2.editor.UserdefitemContainerPreparator;
import nc.ui.uif2.userdefitem.QueryParam;
import nc.ui.uif2.userdefitem.UserDefItemContainer;

/**
 * 合并显示卡片界面自由辅助属性和自定义项显示问题
 * 
 * @since 6.31
 * @version 2013-11-14 下午04:15:36
 * @author mengjian
 */
public class UserDefAndMarAsstProc {

  private final static String VBDEF = "vbdef";

  private final static String VDEF = "vdef";

  private final static String VFREE = "vfree";

  /** 辅料字段 **/
  private String materialField;

  private BillManageModel model;

  /** 表体 **/
  private String tableB;

  /** 表头 **/
  private String tableH;

  public String getMaterialField() {
    return this.materialField;
  }

  public BillManageModel getModel() {
    return this.model;
  }

  public String getTableB() {
    return this.tableB;
  }

  public String getTableH() {
    return this.tableH;
  }

  @SuppressWarnings("restriction")
  public void prosUserDefItem(BillCardPanel cardPanel) {
    UserDefItemContainer userDefItemContainer = new UserDefItemContainer();
    userDefItemContainer.setContext(this.getModel().getContext());
    List<QueryParam> params = new ArrayList<QueryParam>();
    QueryParam queryParam1 = new QueryParam();
    queryParam1.setMdfullname(this.getTableH());
    params.add(queryParam1);
    QueryParam queryParam2 = new QueryParam();
    queryParam2.setMdfullname(this.getTableB());
    params.add(queryParam2);
    QueryParam queryParam3 = new QueryParam();
    queryParam3.setRulecode("materialassistant");
    params.add(queryParam3);
    userDefItemContainer.setParams(params);

    // 物料自由辅助属性
    MarAsstPreparator marAsstPreparator = new MarAsstPreparator();
    marAsstPreparator.setModel(this.getModel());
    marAsstPreparator.setContainer(userDefItemContainer);
    marAsstPreparator.setPrefix(UserDefAndMarAsstProc.VFREE);
    marAsstPreparator.setMaterialField(this.getMaterialField());

    // 用户自定义项
    UserdefitemContainerPreparator userdefitemContainerPreparator =
        new UserdefitemContainerPreparator();
    userdefitemContainerPreparator.setContainer(userDefItemContainer);
    UserdefQueryParam userdefQueryParam1 = new UserdefQueryParam();
    userdefQueryParam1.setPos(0);
    userdefQueryParam1.setPrefix(UserDefAndMarAsstProc.VDEF);
    userdefQueryParam1.setMdfullname(this.getTableH());
    UserdefQueryParam userdefQueryParam2 = new UserdefQueryParam();
    userdefQueryParam2.setPos(1);
    userdefQueryParam2.setPrefix(UserDefAndMarAsstProc.VBDEF);
    userdefQueryParam2.setMdfullname(this.getTableB());
    List<UserdefQueryParam> userdefQueryParams =
        new ArrayList<UserdefQueryParam>();
    userdefQueryParams.add(userdefQueryParam1);
    userdefQueryParams.add(userdefQueryParam2);
    userdefitemContainerPreparator.setParams(userdefQueryParams);

    CompositeBillDataPrepare compositeBillDataPrepare =
        new CompositeBillDataPrepare();
    List<IBillData> billDataPrepares = new ArrayList<IBillData>();
    billDataPrepares.add(marAsstPreparator);
    billDataPrepares.add(userdefitemContainerPreparator);
    compositeBillDataPrepare.setBillDataPrepares(billDataPrepares);

    compositeBillDataPrepare.prepareBillData(cardPanel.getBillData());
    cardPanel.setBillData(cardPanel.getBillData());
    cardPanel.getBillData().setEnabled(false);
  }

  /** 辅料字段 **/
  public void setMaterialField(String materialField) {
    this.materialField = materialField;
  }

  public void setModel(BillManageModel model) {
    this.model = model;
  }

  public void setTableB(String tableB) {
    this.tableB = tableB;
  }

  public void setTableH(String tableH) {
    this.tableH = tableH;
  }
}
