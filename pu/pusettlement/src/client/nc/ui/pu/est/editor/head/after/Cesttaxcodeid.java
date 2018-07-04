package nc.ui.pu.est.editor.head.after;

import nc.itf.scmpub.reference.uap.bd.vat.VATBDService;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoByTaxcodeQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoVO;
import nc.ui.pu.est.editor.relacalc.GoodsEstRelationCal;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.uif2app.event.list.ListHeadAfterEditEvent;
import nc.ui.pubapp.util.ListPanelValueUtils;
import nc.vo.pu.est.entity.GoodsEstVO;
import nc.vo.pu.est.util.EstRelationCalcUtil;
import nc.vo.pu.pub.enumeration.PricePriority;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.calculator.data.IDataSetForCal;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 税码
 * 
 * @since 6.0
 * @version 2012-2-17 下午12:38:28
 * @author wuxla
 */
public class Cesttaxcodeid extends GoodsEstRelationCal {
  private String chgAttName;

  @Override
  public void afterEdit(ListHeadAfterEditEvent event) {
    this.setVatInfo(event);
    super.afterEdit(event);
    this.chgAttName = null;
  }

  private void setVatInfo(ListHeadAfterEditEvent event) {
    int row = event.getRow();
    if (row < 0) {
      return;
    }
    BillListPanel blp = event.getBillListPanel();
    ListPanelValueUtils lpvu = new ListPanelValueUtils(blp);
    String cesttaxcodeid =
        (String) lpvu.getHeadValueAt(row, GoodsEstVO.CESTTAXCODEID);
    if (StringUtils.isEmpty(cesttaxcodeid)) {
      return;
    }
    UFDate dbilldate = AppContext.getInstance().getBusiDate();
    if (null == cesttaxcodeid || null == dbilldate) {
      return;
    }
    VATInfoByTaxcodeQueryVO[] queryvos = new VATInfoByTaxcodeQueryVO[] {
      new VATInfoByTaxcodeQueryVO(cesttaxcodeid, dbilldate)
    };
    VATInfoVO[] vatinfos = VATBDService.queryVATInfo(queryvos);
    this.setVatTaxInfo(vatinfos, lpvu, row);
  }

  private void setVatTaxInfo(VATInfoVO[] vatinfos, ListPanelValueUtils lpvu,
      int row) {
    if (ArrayUtils.isEmpty(vatinfos) || vatinfos[0] == null) {
      lpvu.setHeadValueAt(null, row, GoodsEstVO.FESTTAXTYPE);
      lpvu.setHeadValueAt(null, row, GoodsEstVO.NESTNOSUBTAXRATE);
      lpvu.setHeadValueAt(null, row, GoodsEstVO.NESTTAXRATE);
      lpvu.setHeadValueAt(null, row, GoodsEstVO.NESTNOSUBTAX);
      return;
    }
    lpvu.setHeadValueAt(vatinfos[0].getCtaxcodeid(), row,
        GoodsEstVO.CESTTAXCODEID);
    // 扣税类别
    Integer oldttype =
        (Integer) lpvu.getHeadValueAt(row, GoodsEstVO.FESTTAXTYPE);
    if (!ObjectUtils.equals(vatinfos[0].getFtaxtypeflag(), oldttype)) {
      this.chgAttName = GoodsEstVO.FESTTAXTYPE;
    }
    lpvu.setHeadValueAt(vatinfos[0].getFtaxtypeflag(), row,
        GoodsEstVO.FESTTAXTYPE);
    // 不可抵扣税率
    UFDouble oldnosubtaxrate =
        lpvu.getHeadUFDoubleValueAt(row, GoodsEstVO.NESTNOSUBTAXRATE);
    if (!ObjectUtils.equals(vatinfos[0].getNnosubtaxrate(), oldnosubtaxrate)) {
      this.chgAttName = GoodsEstVO.NESTNOSUBTAXRATE;
    }
    lpvu.setHeadValueAt(vatinfos[0].getNnosubtaxrate(), row,
        GoodsEstVO.NESTNOSUBTAXRATE);
    // 税率
    UFDouble oldtaxrate =
        lpvu.getHeadUFDoubleValueAt(row, GoodsEstVO.NESTTAXRATE);
    if (!ObjectUtils.equals(vatinfos[0].getNtaxrate(), oldtaxrate)) {
      this.chgAttName = GoodsEstVO.NESTTAXRATE;
    }
    lpvu.setHeadValueAt(vatinfos[0].getNtaxrate(), row, GoodsEstVO.NESTTAXRATE);
  }

  @Override
  protected void realAfterEdit(ListHeadAfterEditEvent event, PricePriority pp,
      EstRelationCalcUtil calUtil, IDataSetForCal dataset) {
    if (null == this.chgAttName) {
      return;
    }
    calUtil.calcDataSet(dataset, this.chgAttName, pp);
  }
}
