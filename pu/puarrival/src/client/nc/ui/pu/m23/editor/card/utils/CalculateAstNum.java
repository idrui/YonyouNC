package nc.ui.pu.m23.editor.card.utils;

import nc.ui.pu.m23.utils.ArriveClientUtil;
import nc.ui.pub.bill.BillCardPanel;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.HslParseUtil;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������������ϵ������ = ������ / ������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-3-29 ����03:14:10
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
    // Ӧ��������Ӧ��������
    this.dealPlanNum();

    // ����;��������;��������
    this.dealWastNum();

    // ������Ʒ��������Ʒ������
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
      // �ƻ��������� = �ƻ����������� / ������
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
    // ��Ʒ���� = ��Ʒ������ / ������
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
      // ;������ = ;�������� / ������
      UFDouble nastnum = HslParseUtil.hslDivUFDouble(this.vchangerate, nnum);
      this.card.setBodyValueAt(nastnum, this.currRow, ArriveItemVO.NWASTASTNUM);
    }
  }
}
