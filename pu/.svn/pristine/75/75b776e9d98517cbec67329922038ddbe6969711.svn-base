/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-19 下午04:52:50
 */
package nc.ui.pu.est.view;

import java.util.Map;

import nc.ui.pu.est.model.EstUIContext;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.util.ListPanelValueUtils;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.pu.est.enumeration.QueryEstType;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.pattern.pub.PubAppTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>从界面的数据到模型缓存数据的同步器
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-19 下午04:52:50
 */
public class EditorToModelValueSetter {
  private BillCardPanel bcp;

  private BillListPanel blp;

  private BillManageModel model;

  /**
   * EditorToModelValueSetter 的构造子
   * 
   * @param bcp
   * @param model
   */
  public EditorToModelValueSetter(BillCardPanel bcp, BillManageModel model) {
    super();
    this.bcp = bcp;
    this.model = model;
  }

  /**
   * EditorToModelValueSetter 的构造子
   * 
   * @param blp
   * @param billManageModel
   */
  public EditorToModelValueSetter(BillListPanel blp,
      BillManageModel billManageModel) {
    this.blp = blp;
    this.model = billManageModel;
  }

  /**
   * 方法功能描述：同步表体数据到model。
   * <p>
   * <b>参数说明</b>
   * 
   * @param row
   *          表头当前行
   *          <p>
   * @since 6.0
   * @author zhyhang
   * @time 2010-6-15 下午10:10:41
   */
  public void setModelBodyValue(int row) {
    if (0 > row) {
      return;
    }
    EstUIContext context = (EstUIContext) this.model.getContext();
    // 非暂估状态不处理
    if (QueryEstType.GOODS_EST != context.getEsttype()
        && QueryEstType.FEE_EST != context.getEsttype()) {
      return;
    }
    Map<String, Object>[] chgData =
        this.bcp.getBillModel().getBodyChangeValueByMetaData();
    // 没有改变不处理
    if (ArrayUtils.isEmpty(chgData)) {
      return;
    }
    AggregatedValueObject vo =
        (AggregatedValueObject) this.model.getData().get(row);
    String bodyClassName =
        this.bcp.getBillModel().getTabvo().getBillMetaDataBusinessEntity()
            .getFullClassName();
    CircularlyAccessibleValueObject[] feeVos =
        this.bcp.getBillModel().getBodyValueVOs(bodyClassName);
    vo.setChildrenVO(feeVos);
  }

  public void setModelHeadValue(int row) {
    String[] keys = EstVOUtil.getGoodsEstUpdateModelKeys();
    Object data = this.model.getData().get(row);
    if (null == data) {
      return;
    }
    this.setHeadValue((AggregatedValueObject) data, keys, row);
  }

  private void setHeadValue(AggregatedValueObject vo, String[] keys, int row) {
    CircularlyAccessibleValueObject head = vo.getParentVO();
    ListPanelValueUtils utils = new ListPanelValueUtils(this.blp);
    BillModel bm = this.blp.getHeadBillModel();
    for (String key : keys) {
      if (null == bm.getItemByKey(key)) {
        continue;
      }
      Object blpv = utils.getHeadValueAt(row, key);
      Object modelv = head.getAttributeValue(key);
      if (PubAppTool.isEqual(blpv, modelv)) {
        continue;
      }
      head.setAttributeValue(key, blpv);
    }
  }

}
