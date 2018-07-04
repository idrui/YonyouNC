package nc.ui.pu.m23.editor.card.afteredit.body;

import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.ui.pu.m23.editor.card.utils.ChangeRateUtil;
import nc.ui.pu.m23.utils.ArriveClientUtil;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.rule.transfer.CalcValidDay;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>������༭�¼������࣬������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��յ�ǰ�е�������,������,����,����,���ȵ������Ϣ
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-22 ����01:25:25
 */
public class Material implements ICardBodyAfterEditEventListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent e) {
    BillCardPanel card = e.getBillCardPanel();
    // ��ձ���ĵ������������۸�ȡ�������ȵ�ֵ
    // this.clearRelateItemValues(card, e.getRow());

    // ��������
    String mrl =
        ArriveClientUtil.getStringCellValue(card, e.getRow(),
            ArriveItemVO.PK_MATERIAL);
    String org =
        ArriveClientUtil.getStringHeaderValue(card, ArriveItemVO.PK_ORG);
    if (StringUtils.isNotEmpty(mrl)) {
      // �����Ƿ�̶�������
      ChangeRateUtil.setChgRateAndFixedFlag(card, e.getRow());

      // ����Ĭ�ϵı���������
      this.setDefaultValidDay(e, card, mrl, org);
    }
  }

  private void clearRelateItemValues(BillCardPanel card, int rowNo) {

    String[] itemKeyArray = new String[16];
    // ��������������������
    itemKeyArray[0] = ArriveItemVO.NNUM;
    itemKeyArray[1] = ArriveItemVO.NASTNUM;
    // ������˰�����Һ�˰���
    itemKeyArray[2] = ArriveItemVO.NMNY;
    itemKeyArray[3] = ArriveItemVO.NTAXMNY;
    // ������˰���ۡ����Һ�˰����
    itemKeyArray[4] = ArriveItemVO.NPRICE;
    itemKeyArray[5] = ArriveItemVO.NTAXPRICE;
    // �ϸ����������ϸ�����
    itemKeyArray[6] = ArriveItemVO.NELIGNUM;
    itemKeyArray[7] = ArriveItemVO.NNOTELIGNUM;
    // ��Ʒ��������Ʒ������
    itemKeyArray[8] = ArriveItemVO.NPRESENTASTNUM;
    itemKeyArray[9] = ArriveItemVO.NPRESENTNUM;
    // ���κ�
    itemKeyArray[10] = ArriveItemVO.PK_BATCHCODE;
    // �������ڡ�������������ʧЧ����
    itemKeyArray[11] = ArriveItemVO.DPRODUCEDATE;
    itemKeyArray[12] = ArriveItemVO.IVALIDDAY;
    itemKeyArray[13] = ArriveItemVO.DINVALIDDATE;
    // �Ƿ�̶������ʡ�������
    itemKeyArray[14] = ArriveItemVO.BFIXEDRATE;
    itemKeyArray[15] = ArriveItemVO.VCHANGERATE;
    // // Ӧ��������Ӧ������
    // itemKeyArray[16] = ArriveItemVO.NPLANASTNUM;
    // itemKeyArray[17] = ArriveItemVO.NPLANNUM;

    ArriveClientUtil.clearBodyCellValues(card, rowNo, itemKeyArray);
    // ���������
    ArriveClientUtil.clearFreeCellValues(card, rowNo);
  }

  private void setDefaultValidDay(CardBodyAfterEditEvent e, BillCardPanel card,
      String mrl, String org) {
    CalcValidDay util = new CalcValidDay();
    String[] fields = new String[3];
    fields[0] = MaterialStockVO.QUALITYMANFLAG;
    fields[1] = MaterialStockVO.QUALITYUNIT;
    fields[2] = MaterialStockVO.QUALITYNUM;
    Map<String, MaterialStockVO> map = null;
    String[] mrls = new String[1];
    mrls[0] = mrl;
    map = MaterialPubService.queryMaterialStockInfo(mrls, org, fields);

    if (map != null && map.get(mrl) != null) {
      Integer validDay = util.calcValidDay(map.get(mrl));
      card.setBodyValueAt(validDay, e.getRow(), ArriveItemVO.IVALIDDAY);
    }
  }
}
