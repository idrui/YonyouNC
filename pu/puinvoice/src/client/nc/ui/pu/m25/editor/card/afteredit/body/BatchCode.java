package nc.ui.pu.m25.editor.card.afteredit.body;

import java.util.HashMap;
import java.util.Map;

import nc.ui.pu.m25.editor.utils.RelationCalculate;
import nc.ui.pu.pub.editor.card.handler.PUBatchCode;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.ic.onhand.entity.OnhandDimVO;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;

public class BatchCode extends PUBatchCode {

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    super.afterEdit(event);
    RelationCalculate rc = new RelationCalculate();
    rc.calculate(event.getBillCardPanel(), super.getRows(), InvoiceItemVO.NNUM);
  }

  @Override
  protected Map<String, String> getBodyDims() {
    Map<String, String> bodyDims = new HashMap<String, String>();
    bodyDims.put(OnhandDimVO.CVENDORID, InvoiceItemVO.PK_SUPPLIER);
    bodyDims.put(OnhandDimVO.CWAREHOUSEID, InvoiceItemVO.PK_STORDOC);
    return bodyDims;
  }

  @Override
  protected String[] getFieldsNotNeed() {
    return new String[] {
      InvoiceItemVO.NNUM
    };
  }

  @Override
  protected Map<String, String> getHeadDims() {
    Map<String, String> headDims = new HashMap<String, String>();
    headDims.put(OnhandDimVO.PK_ORG, InvoiceHeaderVO.PK_STOCKORG);
    return headDims;
  }

}
