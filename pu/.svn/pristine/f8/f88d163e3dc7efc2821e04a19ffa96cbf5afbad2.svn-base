package nc.ui.pu.m23.editor.card.afteredit.body;

import java.util.Map;

import nc.impl.ic.util.DateCalUtil;
import nc.itf.scmpub.reference.uap.bd.material.MaterialStockClassPubService;
import nc.ui.pu.m23.utils.ArriveClientUtil;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;

/**
 * <p>
 * <b>"生产日期"、“失效日期”的编辑事件处理类，本类主要完成以下功能：</b>
 * <ul>
 * <li>编辑后：根据编辑后的保质期天数，维护关系：失效日期 = 生产日期 + 保质期天数
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-22 下午01:25:25
 */
public class ProduceDate implements ICardBodyAfterEditEventListener {

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    BillCardPanel card = event.getBillCardPanel();

    // 生产日期、保质期天数
    UFDate produceDate =
        ArriveClientUtil.getUFDateCellValue(card, event.getRow(),
            ArriveItemVO.DPRODUCEDATE);
    if (produceDate == null) {
      // 清空失效日期
      card.getBillModel().setValueAt(null, event.getRow(),
          ArriveItemVO.DINVALIDDATE);
    }

    String pk_material =
        ArriveClientUtil.getStringCellValue(card, event.getRow(),
            ArriveItemVO.PK_MATERIAL);
    String pk_stockorg =
        ArriveClientUtil.getStringHeaderValue(card, ArriveHeaderVO.PK_ORG);
    if (pk_material == null || pk_stockorg == null) {
      return;
    }

    Map<String, MaterialStockVO> materialstockMap =
        MaterialStockClassPubService.queryMaterialStockInfoByPks(new String[] {
          pk_material
        }, pk_stockorg, new String[] {
          MaterialStockVO.WHOLEMANAFLAG, MaterialStockVO.QUALITYMANFLAG,
          MaterialStockVO.QUALITYNUM, MaterialStockVO.QUALITYUNIT
        });

    if (materialstockMap == null || !materialstockMap.containsKey(pk_material)) {
      return;
    }

    MaterialStockVO materialStockVO = materialstockMap.get(pk_material);

    if (UFBoolean.FALSE.equals(materialStockVO.getWholemanaflag())
        || UFBoolean.FALSE.equals(materialStockVO.getQualitymanflag())) {
      return;
    }
    Integer qualityUnit = materialStockVO.getQualityunit();
    Integer qualityNum = materialStockVO.getQualitynum();
    card.setBodyValueAt(
        DateCalUtil.calDvalidate(produceDate, qualityNum, qualityUnit),
        event.getRow(), ArriveItemVO.DINVALIDDATE);
  }

  public void beforeEdit(CardBodyBeforeEditEvent event) {
    event.setReturnValue(Boolean.TRUE);
  }
}
