/**
 * 
 */
package nc.ui.pu.m23.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.Action;

import nc.bs.framework.core.LightContainer;
import nc.bs.framework.core.LightContainerFactory;
import nc.bs.framework.light.LightContainerFactoryImpl;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.application.bcprint.pub.BarCodePrintBaseParam;
import nc.pubitf.application.bcprint.pub.IBCPrintBasePrint;
import nc.ui.pu.m23.view.ArriveCardForm;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.BillListView;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.uif2.annoations.MethodType;
import nc.uif2.annoations.ModelMethod;
import nc.uif2.annoations.ModelType;
import nc.vo.application.bcprint.entity.BCPrintVO;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.report.scale.PUPubMetaNameConst;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>条码打印
 * </ul>
 * <p>
 * <p>
 * 
 * @version
 * @since
 * @author luojw
 * @time 2014-6-16 上午9:21:18
 */
public class BarcodeAction extends NCAction {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = -1169726895809372977L;

  /** 是否直接打印 */
  private boolean direct;

  private ArriveCardForm form;

  private BillListView list;

  private BillManageModel model = null;

  /*
   * 父类方法重写
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (!SysInitGroupQuery.isBCEnabled()) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004040_0", "04004040-0210")/*
                                                                   * @res
                                                                   * "条码管理模块未安装或未启用，此功能不可用！"
                                                                   */);
    }
    ArriveVO billvo = (ArriveVO) this.model.getSelectedData();
    if (billvo == null) {
      return;
    }
    this.processPrintBarCode();

  }

  /**
   * @return the editor
   */
  public ArriveCardForm getForm() {
    return this.form;
  }

  public BillListView getList() {
    return this.list;
  }

  /**
   * @return the model
   */
  @ModelMethod(modelType = ModelType.AbstractUIAppModel, methodType = MethodType.GETTER)
  public BillManageModel getModel() {
    return this.model;
  }

  /**
   * @param direct
   *          the direct to set
   */
  public void setDirect(boolean direct) {
    this.direct = direct;
    if (direct) {
      this.setBtnname("04004040-0208");
      this.setCode("printBarcode");
    }
    else {
      this.setBtnname("04004040-0209");
      this.setCode("directPrintBarcode");
    }
  }

  public void setForm(ArriveCardForm form) {
    this.form = form;
  }

  public void setList(BillListView list) {
    this.list = list;
  }

  /**
   * @param model
   *          the model to set
   */
  @ModelMethod(modelType = ModelType.AbstractUIAppModel, methodType = MethodType.SETTER)
  public void setModel(BillManageModel model) {
    this.model = model;
    model.addAppEventListener(this);
  }

  private Set<Integer> convertIntToSet(int[] values) {
    if (ArrayUtils.isEmpty(values)) {
      return null;
    }
    Set<Integer> set = new HashSet<Integer>();
    for (int value : values) {
      set.add(Integer.valueOf(value));
    }
    return set;
  }

  private BarCodePrintBaseParam getBaseparam(ArriveHeaderVO header,
      ArriveItemVO body) {
    BarCodePrintBaseParam baseparam = new BarCodePrintBaseParam();
    BCPrintVO printvo = new BCPrintVO();

    // 集团（需求上没有要求传递集团，但是不传递会报错）
    printvo.setPk_group(header.getPk_group());
    // keyString.append(header.getPk_group());
    // 组织
    printvo.setPk_org(header.getPk_org());
    // keyString.append(header.getPk_org());
    // 供应商
    printvo.setCvendorid(header.getPk_supplier());
    // keyString.append(header.getPk_supplier());
    // 物料oid
    printvo.setCmaterialoid(body.getPk_srcmaterial());
    // keyString.append(body.getPk_srcmaterial());
    // 物料vid
    printvo.setCmaterialvid(body.getPk_material());
    // keyString.append(body.getPk_material());
    // 自由辅助属性
    this.setVFreeItems(body, printvo);
    // 主单位
    printvo.setCunitid(body.getCunitid());
    // keyString.append(body.getCunitid());
    // 单位
    printvo.setCastunitid(body.getCastunitid());
    // keyString.append(body.getCastunitid());
    // 主数量（绝对值）
    printvo.setNnum(MathTool.nvl(body.getNnum()).abs());
    // keyString.append(MathTool.nvl(body.getNnum()).abs());
    // 数量（绝对值）
    printvo.setNassistnum(MathTool.nvl(body.getNastnum()).abs());
    // keyString.append(MathTool.nvl(body.getNastnum()).abs());
    // 批次主键
    printvo.setPk_batchcode(body.getPk_batchcode());
    // keyString.append(body.getPk_batchcode());
    // 批次号
    printvo.setVbatchcode(body.getVbatchcode());
    // keyString.append(body.getVbatchcode());
    // 客户
    printvo.setCasscustid(body.getCasscustid());
    // keyString.append(body.getCasscustid());
    // 生产厂商
    printvo.setCproductorid(body.getCproductorid());
    // keyString.append(body.getCproductorid());
    // 项目
    printvo.setCprojectid(body.getCprojectid());
    // keyString.append(body.getCprojectid());
    // 质量等级
    printvo.setCqualitylevelid(body.getCqualitylevelid());
    // keyString.append(body.getCqualitylevelid());
    // 特征码
    printvo.setCcfileid(body.getCffileid());
    // keyString.append(body.getCffileid());
    baseparam.setPrintvo(printvo);
    return baseparam;
  }

  private List<BarCodePrintBaseParam> getPrintParamMap(ArriveVO billvo) {
    // Map<String, BarCodePrintBaseParam> printParamMap =
    // new HashMap<String, BarCodePrintBaseParam>();
    List<BarCodePrintBaseParam> params = new ArrayList<>();
    int[] selrows = null;
    ArriveHeaderVO header = billvo.getHVO();
    ArriveItemVO[] bodys = billvo.getBVO();
    if (this.list.isShowing()) {
      // 列表下，按选中行打印
      selrows = this.list.getBillListPanel().getBodyTable().getSelectedRows();
    }
    else if (this.form.isShowing()) {
      // 卡片下，按选中行打印
      selrows = this.form.getBillCardPanel().getBillTable().getSelectedRows();
    }
    Set<Integer> selrowset = this.convertIntToSet(selrows);
    // 如果未选中行（selrowset为空），则整单打印
    // StringBuilder keyString = new StringBuilder();
    for (int i = 0; i < bodys.length; i++) {
      if (selrowset != null && selrowset.size() > 0
          && !selrowset.contains(Integer.valueOf(i))) {
        continue;
      }
      // ArriveItemVO body = bodys[i];
      // BarCodePrintBaseParam baseparam =
      // this.getBaseparam(header, body, keyString);
      params.add(this.getBaseparam(header, bodys[i]));
      // printParamMap.put(keyString.toString(), baseparam);
    }
    return params;
  }

  private IBCPrintBasePrint getService() {
    Class<IBCPrintBasePrint> itf = IBCPrintBasePrint.class;
    LightContainer container =
        LightContainerFactory.getInstance().newLightContainer(
            "bc",
            new String[] {
              LightContainerFactoryImpl.SCHEMA_CP
                  + "nc/ui/bc/service/config/bc_uiejb.xml"
            }, itf.getClassLoader());
    return (IBCPrintBasePrint) container.getContext().lookup(itf.getName());
  }

  private void processPrintBarCode() {
    Object data = this.model.getSelectedData();
    if (null == data) {
      return;
    }
    List<BarCodePrintBaseParam> params = this.getPrintParamMap((ArriveVO) data);
    BarCodePrintBaseParam[] printParams =
        params.toArray(new BarCodePrintBaseParam[params.size()]);
    try {
      // 是否直接打印
      if (this.direct) {
        this.getService().doAction4CantPrintSingleBC(false, printParams);
      }
      else {
        this.getService().openNodePrint4CantPrintSingleBC(false, printParams);
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private void setBtnname(String strID) {
    String name =
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004040_0",
            strID);
    this.putValue(Action.NAME, name);
  }

  private void setVFreeItems(ArriveItemVO body, BCPrintVO printvo) {
    for (int i = 0; i < PUPubMetaNameConst.VFREEITEMS.length; i++) {
      printvo.setAttributeValue(PUPubMetaNameConst.VFREEITEMS[i],
          body.getAttributeValue(PUPubMetaNameConst.VFREEITEMS[i]));
    }
  }

  /*
   * 父类方法重写
   * @see nc.ui.uif2.NCAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    if (this.model.getUiState() != UIState.NOT_EDIT) {
      return false;
    }
    if (this.model.getSelectedData() == null) {
      return false;
    }
    Integer[] rows = this.model.getSelectedOperaRows();
    if (rows == null || rows.length > 1) {
      return false;
    }
    return true;
  }

}
