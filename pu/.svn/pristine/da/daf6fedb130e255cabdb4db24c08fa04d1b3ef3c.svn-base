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
 * <li>计算数量，关系：数量 = 主数量 / 换算率
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-3-29 下午03:14:10
 */
public class CalculateAstNum {
  private BillCardPanel card;

  private int currRow;

  private String vchangerate;

  public CalculateAstNum(BillCardPanel card, int currRow) {
    this.card = card;
    this.currRow = currRow;

    String key = ArriveItemVO.VCHANGERATE;
    this.vchangerate = ArriveClientUtil.getStringCellValue(card, currRow, key);
  }

  public void calculateAstNum() {
    // 应到数量、应到主数量
    this.dealPlanNum();

    // 处理途耗数量、途耗主数量
    this.dealWastNum();

    // 处理赠品数量、赠品主数量
    this.dealPresentNum();
  }

  private void dealPlanNum() {
    String key = ArriveItemVO.NPLANNUM;
    UFDouble nnum =
        ArriveClientUtil.getUFDoubleCellValue(this.card, this.currRow, key);
    if (nnum == null) {
      this.card.setBodyValueAt(null, this.currRow, ArriveItemVO.NPLANASTNUM);
    }
    else if (!StringUtils.isEmpty(this.vchangerate)) {
      // 计划到货数量 = 计划到货主数量 / 换算率
      UFDouble nastnum = HslParseUtil.hslDivUFDouble(this.vchangerate, nnum);
      this.card.setBodyValueAt(nastnum, this.currRow, ArriveItemVO.NPLANASTNUM);
    }
  }

  private void dealPresentNum() {
    String key = ArriveItemVO.NPRESENTNUM;
    UFDouble nnum =
        ArriveClientUtil.getUFDoubleCellValue(this.card, this.currRow, key);
    if (nnum == null || StringUtils.isEmpty(this.vchangerate)) {
      return;
    }
    // 赠品数量 = 赠品主数量 / 换算率
    UFDouble nastnum = HslParseUtil.hslDivUFDouble(this.vchangerate, nnum);
    this.card
        .setBodyValueAt(nastnum, this.currRow, ArriveItemVO.NPRESENTASTNUM);
  }

  private void dealWastNum() {
    String key = ArriveItemVO.NWASTNUM;
    UFDouble nnum =
        ArriveClientUtil.getUFDoubleCellValue(this.card, this.currRow, key);
    if (nnum == null) {
      this.card.setBodyValueAt(null, this.currRow, ArriveItemVO.NWASTASTNUM);
    }
    else if (!StringUtils.isEmpty(this.vchangerate)) {
      // 途耗数量 = 途耗主数量 / 换算率
      UFDouble nastnum = HslParseUtil.hslDivUFDouble(this.vchangerate, nnum);
      this.card.setBodyValueAt(nastnum, this.currRow, ArriveItemVO.NWASTASTNUM);
    }
  }
}
