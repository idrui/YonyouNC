package nc.ui.pu.m23.editor.card.utils;

import nc.ui.pu.m23.utils.ArriveClientUtil;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.HslParseUtil;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>计算主数量，关系：主数量 = 数量 * 换算率
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-3-29 下午03:14:51
 */
public class CalculateMainNum {
  private BillCardPanel card;

  private int currRow;

  private String vchangerate;

  public CalculateMainNum(BillCardPanel card, int currRow) {
    this.card = card;
    this.currRow = currRow;

    String key = ArriveItemVO.VCHANGERATE;
    this.vchangerate = ArriveClientUtil.getStringCellValue(card, currRow, key);
  }

  public void calculateMainNum() {
    // 应到数量、应到主数量
    this.dealPlanNum();

    // 处理途耗数量、途耗主数量
    this.dealWastNum();

    // 处理赠品数量、赠品主数量
    this.dealPresentNum();
  }

  private void dealPlanNum() {
    String key = ArriveItemVO.NPLANASTNUM;
    UFDouble nastnum =
        ArriveClientUtil.getUFDoubleCellValue(this.card, this.currRow, key);
    if (nastnum == null) {
      this.card.setBodyValueAt(null, this.currRow, ArriveItemVO.NPLANNUM);
    }
    else if (!StringUtils.isEmpty(this.vchangerate)) {
      // 计划到货主数量 = 换算率 × 计划到货数量
      UFDouble nnum =
          HslParseUtil.hslMultiplyUFDouble(this.vchangerate, nastnum);
      this.card.setBodyValueAt(nnum, this.currRow, ArriveItemVO.NPLANNUM);
    }
  }

  private void dealPresentNum() {
    String key = ArriveItemVO.NPRESENTASTNUM;
    UFDouble nastnum =
        ArriveClientUtil.getUFDoubleCellValue(this.card, this.currRow, key);
    if (nastnum == null || StringUtils.isEmpty(this.vchangerate)) {
      return;
    }
    // 赠品主数量 = 换算率 × 赠品数量
    UFDouble nnum = HslParseUtil.hslMultiplyUFDouble(this.vchangerate, nastnum);
    this.card.setBodyValueAt(nnum, this.currRow, ArriveItemVO.NPRESENTNUM);
  }

  private void dealWastNum() {
    String key = ArriveItemVO.NWASTASTNUM;
    UFDouble nastnum =
        ArriveClientUtil.getUFDoubleCellValue(this.card, this.currRow, key);
    if (nastnum == null) {
      this.card.setBodyValueAt(null, this.currRow, ArriveItemVO.NWASTNUM);
    }
    else if (!StringUtils.isEmpty(this.vchangerate)) {
      // 途耗主数量 = 换算率 × 途耗数量
      UFDouble nnum =
          HslParseUtil.hslMultiplyUFDouble(this.vchangerate, nastnum);
      this.card.setBodyValueAt(nnum, this.currRow, ArriveItemVO.NWASTNUM);
    }
  }
}
