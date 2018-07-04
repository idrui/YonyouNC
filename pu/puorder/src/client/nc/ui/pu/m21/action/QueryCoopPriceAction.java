/**
 * $文件说明$
 *
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-26 下午08:35:35
 */
package nc.ui.pu.m21.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m21.IOrderCoopPriceQuery;
import nc.itf.pu.reference.so.M30SOServices;
import nc.ui.pu.m21.editor.card.afteredit.RelationCalculate;
import nc.ui.pu.pub.editor.CardEditorHelper;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.query.price.CoopPriceQueryDetail;
import nc.vo.pu.m21.query.price.CoopPriceQueryParam;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>寻协同售价
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-10-26 下午08:35:35
 */
public class QueryCoopPriceAction extends NCAction {

  private static final long serialVersionUID = 5672279486198429982L;

  private ShowUpableBillForm billForm;

  public QueryCoopPriceAction() {
    SCMActionInitializer
        .initializeAction(this, SCMActionCode.PU_QUOTECOOPPRICE);
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {

    BillCardPanel panel = this.getBillForm().getBillCardPanel();

    this.checkSupplier(panel);
    this.checkTrantypeCode(panel);

    int[] rows = panel.getBillTable().getSelectedRows();
    if (ArrayUtils.isEmpty(rows)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0055")/*
                                                                   * @res
                                                                   * "请选择行"
                                                                   */);
    }

    List<Integer> list = this.getPriceRowList(panel);
    if (list == null || list.size() == 0) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0347")/*
                                                                   * @res
                                                                   * "询协同售价表体行为空"
                                                                   */);
      return;
    }

    // 准备询价参数
    CoopPriceQueryParam param = this.getParam(panel, list);

    IOrderCoopPriceQuery service =
        NCLocator.getInstance().lookup(IOrderCoopPriceQuery.class);

    CoopPriceQueryParam priceResult = service.queryCoopPrice(param);

    // 设置询价结果
    this.setPriceOnPanel(priceResult, panel);
  }

  /**
   * @return billForm
   */
  public ShowUpableBillForm getBillForm() {
    return this.billForm;
  }

  /**
   * @param billForm
   *          要设置的 billForm
   */
  public void setBillForm(ShowUpableBillForm billForm) {
    this.billForm = billForm;
    billForm.getModel().addAppEventListener(this);
  }

  private void checkSupplier(BillCardPanel panel) {
    Object pk_supplier =
        panel.getHeadItem(OrderHeaderVO.PK_SUPPLIER).getValueObject();
    if (null == pk_supplier) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0346")/*
                                                                   * @res
                                                                   * "询协同售价必须输入供应商"
                                                                   */);
    }
  }

  private void checkTrantypeCode(BillCardPanel panel) {
    Object vtrantypecode =
        panel.getHeadItem(OrderHeaderVO.VTRANTYPECODE).getValueObject();
    if (null == vtrantypecode) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004030_0", "04004030-0348")/*
                                                                   * @res
                                                                   * "询协同售价必须输入订单类型"
                                                                   */);
    }
  }

  private CoopPriceQueryParam getParam(BillCardPanel panel, List<Integer> list) {

    CoopPriceQueryParam param = new CoopPriceQueryParam();

    CardEditorHelper editor = new CardEditorHelper(panel);
    // 采购组织的OID
    param.setPurchaseOrg((String) editor.getHeadValue(OrderHeaderVO.PK_ORG));

    // 获得供应商ID
    param.setSupplier((String) editor.getHeadValue(OrderHeaderVO.PK_SUPPLIER));

    // 交易类型
    param
        .setTrasType((String) editor.getHeadValue(OrderHeaderVO.VTRANTYPECODE));
    param.setBillDate(editor.getHeadUFDateValue(OrderHeaderVO.DBILLDATE));

    int length = list.size();
    CoopPriceQueryDetail[] detail = new CoopPriceQueryDetail[length];
    String corigcurrencyid =
        (String) editor.getHeadValue(OrderHeaderVO.CORIGCURRENCYID);
    for (int i = 0; i < length; i++) {
      int row = list.get(i).intValue();
      detail[i] = new CoopPriceQueryDetail();
      detail[i].setRow(row);
      // 物料VID
      //补丁：改为 物料OID，VID询价时无法显示最新版本的物料的价格
      detail[i].setPk_material((String) editor.getBodyValue(row,
          OrderItemVO.PK_SRCMATERIAL));
      // 质量等级
      // detail[i].setPk_qualitylevel((String) editor.getBodyValue(rows[i],
      // OrderItemVO.CQUALITYLEVELID));
      // 收货地区
      detail[i].setPk_areacl((String) editor.getBodyValue(row,
          OrderItemVO.CDEVAREAID));
      // 数量
      detail[i].setNnum((UFDouble) editor.getBodyValue(row,
          OrderItemVO.NQTUNITNUM));
      // 币种
      detail[i].setPk_currtype(corigcurrencyid);
      // group
      detail[i].setPk_group((String) editor.getBodyValue(row,
          OrderItemVO.PK_GROUP));
      // 订单结算财务组织
      detail[i].setFinancialPK((String) editor.getBodyValue(row,
          OrderItemVO.PK_PSFINANCEORG));
      // 采购订单报价单位
      detail[i].setPk_unit((String) editor.getBodyValue(row,
          OrderItemVO.CQTUNITID));

      detail[i].setVfree1(editor.getBodyStringValue(row, OrderItemVO.VFREE1));
      detail[i].setVfree2(editor.getBodyStringValue(row, OrderItemVO.VFREE2));
      detail[i].setVfree3(editor.getBodyStringValue(row, OrderItemVO.VFREE3));
      detail[i].setVfree4(editor.getBodyStringValue(row, OrderItemVO.VFREE4));
      detail[i].setVfree5(editor.getBodyStringValue(row, OrderItemVO.VFREE5));
      detail[i].setVfree6(editor.getBodyStringValue(row, OrderItemVO.VFREE6));
      detail[i].setVfree7(editor.getBodyStringValue(row, OrderItemVO.VFREE7));
      detail[i].setVfree8(editor.getBodyStringValue(row, OrderItemVO.VFREE8));
      detail[i].setVfree9(editor.getBodyStringValue(row, OrderItemVO.VFREE9));
      detail[i].setVfree10(editor.getBodyStringValue(row, OrderItemVO.VFREE10));
    }

    param.setDetail(detail);

    return param;
  }

  private List<Integer> getPriceRowList(BillCardPanel panel) {
    int count = panel.getRowCount();
    if (count <= 0) {
      return null;
    }
    List<Integer> list = new ArrayList<Integer>();
    for (int i = 0; i < count; ++i) {
      Object pk_material = panel.getBodyValueAt(i, OrderItemVO.PK_MATERIAL);
      Object pk_psfinanceorg =
          panel.getBodyValueAt(i, OrderItemVO.PK_PSFINANCEORG);
      if (pk_material != null && pk_psfinanceorg != null) {
        list.add(Integer.valueOf(i));
      }
    }
    return list;
  }

  /**
   * 将询价结果放入面板
   * 
   * @param priceResult
   * @param param
   * @param panel
   */
  private void setPriceOnPanel(CoopPriceQueryParam param, BillCardPanel panel) {

    CardEditorHelper editor = new CardEditorHelper(panel);

    CoopPriceQueryDetail[] details = param.getDetail();

    // 销售价格是否含税的参数

    int len = details.length;
    RelationCalculate relcal = new RelationCalculate();
    String pricekey = null;
    int[] rows = new int[len];
    for (int i = 0; i < len; i++) {
      int row = details[i].getRow();
      // 销售价格是否含税的参数，要根据销售价格是否含税的参数来判断
      boolean isTaxPriorBySO23 = M30SOServices.isTaxPriorBySO23Para();

      if (isTaxPriorBySO23) {
        // 含税
        editor.setBodyValue(row, OrderItemVO.NQTORIGTAXPRICE,
            details[i].getPrice());
        editor.setBodyValue(row, OrderItemVO.NQTORIGTAXNETPRC,
            details[i].getNetPrice());
        pricekey = OrderItemVO.NQTORIGTAXNETPRC;
      }
      else {
        // 无税
        editor.setBodyValue(row, OrderItemVO.NQTORIGPRICE,
            details[i].getPrice());
        editor.setBodyValue(row, OrderItemVO.NQTORIGNETPRICE,
            details[i].getNetPrice());
        pricekey = OrderItemVO.NQTORIGNETPRICE;
      }

      rows[i] = row;
    }
    if (null != pricekey) {
      relcal.calculate(panel, rows, pricekey, null, false);
    }
  }
}
