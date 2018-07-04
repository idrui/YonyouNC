/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-5-31 上午10:10:42
 */
package nc.ui.pu.est.action.m45;

import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m24.IQueryPricestl;
import nc.ui.pu.est.model.EstUIContext;
import nc.ui.pu.est.view.EditorToModelValueSetter;
import nc.ui.pu.est.view.EstEditor;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.calculator.data.BillModelDataSet;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.util.ListPanelValueUtils;
import nc.ui.scmbd.tpa.SCMTpaAction;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.entity.m45.PurchaseInEstVO;
import nc.vo.pu.est.util.EstRelationCalcItem;
import nc.vo.pu.est.util.EstRelationCalcUtil;
import nc.vo.pu.est.util.EstVOUtil;
import nc.vo.pu.m24.entity.PricParaVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.res.SCMActionCode;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>采购入库单暂优质优价动作
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-5-31 上午10:10:42
 */
public class PurchaseInEstHQHPAction extends SCMTpaAction {

  private static final long serialVersionUID = -8647094801639403082L;

  private EstEditor editor;

  private BillManageModel model;

  public PurchaseInEstHQHPAction() {
    // this.setBtnName("优质优价");
    // this.setCode("purchaseinhqhp");
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName());
    SCMActionInitializer.initializeAction(this, SCMActionCode.PU_HQHP);
    this.setEnabled(false);
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    String[] bids = this.getSelectPurchsInBID();
    if (ArrayUtils.isEmpty(bids)) {
      return;
    }
    Map<String, PricParaVO> hqhpMap = this.getHqhpPrice(bids);
    if (MapUtils.isEmpty(hqhpMap)) {
      return;
    }
    // 用优质优价设置暂估价
    this.setHqhpPrice(hqhpMap);
  }

  /**
   * @return editor
   */
  public EstEditor getEditor() {
    return this.editor;
  }

  /**
   * @return model
   */
  public BillManageModel getModel() {
    return this.model;
  }

  /**
   * @param editor
   *          要设置的 editor
   */
  public void setEditor(EstEditor editor) {
    this.editor = editor;
  }

  /**
   * @param model
   *          要设置的 model
   */
  public void setModel(BillManageModel model) {
    this.model = model;
    this.setContext(this.model.getContext());
    this.model.addAppEventListener(this);
  }

  private void calculateRow(Integer row, PricePriority pp) {
    BillModelDataSet dataset =
        new BillModelDataSet(this.getBlp().getHeadBillModel(), row.intValue(),
            new EstRelationCalcItem());
    String chgKey =
        PricePriority.TAXPRICE_PRIOR_TO_PRICE == pp ? GoodsEstVO.NESTTAXPRICE
            : GoodsEstVO.NESTPRICE;
    EstRelationCalcUtil calcUtil =
        new EstRelationCalcUtil(AppContext.getInstance().getPkGroup());
    calcUtil.calcDataSet(dataset, chgKey, pp);
  }

  private BillListPanel getBlp() {
    return this.editor.getBillListPanel();
  }

  private Map<String, PricParaVO> getHqhpPrice(String[] bids)
      throws BusinessException {
    IQueryPricestl hqhpSrv =
        NCLocator.getInstance().lookup(IQueryPricestl.class);
    PricParaVO[] pricVos = hqhpSrv.queryPricStlPrices(bids);
    if (ArrayUtils.isEmpty(pricVos)) {
      return null;
    }
    Map<String, PricParaVO> retMap = new HashMap<String, PricParaVO>();
    for (PricParaVO pricParaVO : pricVos) {
      retMap.put(pricParaVO.getCgeneralbid(), pricParaVO);
    }
    return retMap;
  }

  private PricePriority getPricePrior(String pk_stockps_b) {
    Map<String, String> purOrgMap =
        ((EstUIContext) this.getModel().getContext()).getIdPurOrgMap();
    if (MapUtils.isEmpty(purOrgMap)) {
      return PricePriority.PRICE_PRIOR_TO_TAXPRICE;
    }
    String pk_purorg = purOrgMap.get(pk_stockps_b);
    return EstVOUtil.getPO28(pk_purorg);
  }

  private String[] getSelectPurchsInBID() {
    Object[] datas = this.getModel().getSelectedOperaDatas();
    if (ArrayUtils.isEmpty(datas)) {
      return null;
    }
    ListToArrayTool<Object> tool = new ListToArrayTool<Object>();
    PurchaseInEstVO[] vos =
        (PurchaseInEstVO[]) tool.convertToArray(Arrays.asList(datas));

    String[] bids =
        (String[]) AggVOUtil.getDistinctHeadFieldArray(vos,
            PurchaseinFIItemVO.PK_STOCKPS_B, String.class);
    return bids;
  }

  /** 计算每一行的优质估价 **/
  private void setHqhpPrice(Map<String, PricParaVO> hqhpMap) {
    Integer[] selecRows = this.getModel().getSelectedOperaRows();
    for (Integer i : selecRows) {
      this.setRowHqhpPrice(hqhpMap, i);
    }
  }

  private void setRowHqhpPrice(Map<String, PricParaVO> hqhpMap, Integer row) {
    ListPanelValueUtils blpUtil = new ListPanelValueUtils(this.getBlp());
    String pk_stockps_b =
        blpUtil.getHeadStringValueAt(row.intValue(),
            PurchaseinFIItemVO.PK_STOCKPS_B);
    PricParaVO pvo = hqhpMap.get(pk_stockps_b);
    if (null == pvo) {
      return;
    }
    UFDouble price = MathTool.nvl(pvo.getNprice());
    UFDouble taxprice = MathTool.nvl(pvo.getNtaxprice());
    if (UFDouble.ZERO_DBL.equals(price) || UFDouble.ZERO_DBL.equals(taxprice)) {
      return;
    }
    blpUtil.setHeadValueAt(price, row.intValue(), GoodsEstVO.NESTPRICE);
    blpUtil.setHeadValueAt(taxprice, row.intValue(), GoodsEstVO.NESTTAXPRICE);
    PricePriority pp = PricePriority.PRICE_PRIOR_TO_TAXPRICE;
    if (UFDouble.ZERO_DBL.equals(price)) {
      pp = PricePriority.TAXPRICE_PRIOR_TO_PRICE;
    }
    else if (UFDouble.ZERO_DBL.equals(taxprice)) {
      pp = PricePriority.PRICE_PRIOR_TO_TAXPRICE;
    }
    else {
      pp = this.getPricePrior(pk_stockps_b);
    }
    this.calculateRow(row, pp);
    this.synModelValue(row);
  }

  private void synModelValue(Integer row) {
    EditorToModelValueSetter setter =
        new EditorToModelValueSetter(this.getBlp(), this.getModel());
    setter.setModelHeadValue(row.intValue());
  }

  @Override
  protected boolean isActionEnable() {
    return !ArrayUtils.isEmpty(this.getModel().getSelectedOperaDatas());
  }

}
