/**
 * 
 */
package nc.ui.pu.m20.view;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pu.m20.editor.util.PraybillScaleUtil;
import nc.ui.pub.bill.BillData;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.pubapp.scale.TotalValueScaleProcessor;
import nc.ui.pubapp.uif2app.view.BatchBillTable;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.bill.BillTempletBodyVO;
import nc.vo.pub.bill.BillTempletVO;
import nc.vo.pubapp.AppContext;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version
 * @since
 * @author luojw
 * @time 2014-6-25 下午4:44:03
 */
public class PrayarrangeBillForm extends BatchBillTable {

  /**
   * serialVersionUID
   */
  private static final long serialVersionUID = -3891000747877349910L;

  /*
   * 父类方法重写
   * @see nc.ui.pubapp.uif2app.view.BatchBillTable#getEditingLineVO(int)
   */
  @Override
  public Object getEditingLineVO(int rowIndex) {
    Object obj =
        this.getBillCardPanel().getBillModel()
            .getBodyValueRowVO(rowIndex, this.getVoClassName());
    return obj;
  }

  /*
   * 父类方法重写
   * @see nc.ui.pubapp.uif2app.view.BatchBillTable#initUI()
   */
  @Override
  public void initUI() {
    super.initUI();
    // 处理精度
    this.setScale();
  }

  private void setScale() {
    PraybillScaleUtil util = new PraybillScaleUtil();
    util.setScale(new CardPaneScaleProcessor(AppContext.getInstance()
        .getPkGroup(), this.billCardPanel), new TotalValueScaleProcessor(
        this.billCardPanel));
  }

  /*
   * 父类方法重写
   * @see
   * nc.ui.uif2.editor.BatchBillTable#processTemplateData2ModeData(java.lang
   * .Object, java.lang.Object)
   */
  @Override
  protected void processTemplateData2ModeData(Object templateData,
      Object modelData) {
    CircularlyAccessibleValueObject templatecvo =
        (CircularlyAccessibleValueObject) templateData;
    CircularlyAccessibleValueObject modelcvo =
        (CircularlyAccessibleValueObject) modelData;
    String[] names = templatecvo.getAttributeNames();
    if (names != null && names.length > 0) {
      for (String name : names) {
        Object templateDefValue = templatecvo.getAttributeValue(name);
        Object modelDefValue = modelcvo.getAttributeValue(name);
        if (modelDefValue == null && templateDefValue != null) {
          modelcvo.setAttributeValue(name, templateDefValue);
        }
      }
    }
  }

  /**
   * 用于精度处理，设置表体billitem
   */
  @Override
  protected void setBillData(BillTempletVO template) {
    this.processTemplateVO(template);
    BillData billdata = new BillData(template, this.getBillStatus());
    List<BillItem> billitems = new ArrayList<>();
    for (BillTempletBodyVO bodyVO : template.getBodyVO()) {
      BillItem item = new BillItem(bodyVO, bodyVO.getCardflag().booleanValue());
      billitems.add(item);
    }
    billdata.setBodyItems(billitems.toArray(new BillItem[billitems.size()]));
    if (this.getUserdefitemPreparator() != null) {
      this.getUserdefitemPreparator().prepareBillData(billdata);
    }
    this.processBillData(billdata);
    super.billCardPanel.setBillData(billdata);
  }

  /*
   * 父类方法重写
   * @see nc.ui.uif2.editor.BatchBillTable#setTemplateData(int,
   * java.lang.Object)
   */
  @Override
  protected void setTemplateData(int index, Object obj) {
    if (obj != null && obj.getClass().isArray()) {
      Object[] objs = (Object[]) obj;
      this.getBillCardPanel().getBillModel()
          .setBodyRowVO((CircularlyAccessibleValueObject) objs[0], index);
      this.getBillCardPanel().getBillModel().loadLoadRelationItemValue(index);
    }
    else {
      this.getBillCardPanel().getBillModel()
          .setBodyRowVO((CircularlyAccessibleValueObject) obj, index);
      this.getBillCardPanel().getBillModel().loadLoadRelationItemValue(index);
    }
  }

}
