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
 * <b>自由项编辑事件处理类，本类主要完成以下功能：</b>
 * <ul>
 * <li>清空当前行的自由项,辅数量,数量,单价,金额等到相关信息
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-22 下午01:25:25
 */
public class Material implements ICardBodyAfterEditEventListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent e) {
    BillCardPanel card = e.getBillCardPanel();
    // 清空表体的到货数量、金额、价格等、自由项等的值
    // this.clearRelateItemValues(card, e.getRow());

    // 物料主键
    String mrl =
        ArriveClientUtil.getStringCellValue(card, e.getRow(),
            ArriveItemVO.PK_MATERIAL);
    String org =
        ArriveClientUtil.getStringHeaderValue(card, ArriveItemVO.PK_ORG);
    if (StringUtils.isNotEmpty(mrl)) {
      // 设置是否固定换算率
      ChangeRateUtil.setChgRateAndFixedFlag(card, e.getRow());

      // 设置默认的保质期天数
      this.setDefaultValidDay(e, card, mrl, org);
    }
  }

  private void clearRelateItemValues(BillCardPanel card, int rowNo) {

    String[] itemKeyArray = new String[16];
    // 到货数量、到货主数量
    itemKeyArray[0] = ArriveItemVO.NNUM;
    itemKeyArray[1] = ArriveItemVO.NASTNUM;
    // 本币无税金额、本币含税金额
    itemKeyArray[2] = ArriveItemVO.NMNY;
    itemKeyArray[3] = ArriveItemVO.NTAXMNY;
    // 本币无税单价、本币含税单价
    itemKeyArray[4] = ArriveItemVO.NPRICE;
    itemKeyArray[5] = ArriveItemVO.NTAXPRICE;
    // 合格数量、不合格数量
    itemKeyArray[6] = ArriveItemVO.NELIGNUM;
    itemKeyArray[7] = ArriveItemVO.NNOTELIGNUM;
    // 赠品数量、赠品主数量
    itemKeyArray[8] = ArriveItemVO.NPRESENTASTNUM;
    itemKeyArray[9] = ArriveItemVO.NPRESENTNUM;
    // 批次号
    itemKeyArray[10] = ArriveItemVO.PK_BATCHCODE;
    // 生产日期、保质期天数、失效日期
    itemKeyArray[11] = ArriveItemVO.DPRODUCEDATE;
    itemKeyArray[12] = ArriveItemVO.IVALIDDAY;
    itemKeyArray[13] = ArriveItemVO.DINVALIDDATE;
    // 是否固定换算率、换算率
    itemKeyArray[14] = ArriveItemVO.BFIXEDRATE;
    itemKeyArray[15] = ArriveItemVO.VCHANGERATE;
    // // 应到数量、应到数量
    // itemKeyArray[16] = ArriveItemVO.NPLANASTNUM;
    // itemKeyArray[17] = ArriveItemVO.NPLANNUM;

    ArriveClientUtil.clearBodyCellValues(card, rowNo, itemKeyArray);
    // 清空自由项
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
