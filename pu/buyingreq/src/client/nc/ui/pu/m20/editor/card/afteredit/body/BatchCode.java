package nc.ui.pu.m20.editor.card.afteredit.body;

import java.util.HashMap;
import java.util.Map;

import nc.ui.pu.m20.editor.card.afteredit.RelationCalculate;
import nc.ui.pu.pub.editor.card.handler.PUBatchCode;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.ic.onhand.entity.OnhandDimVO;
import nc.vo.pu.m20.entity.PraybillItemVO;

public class BatchCode extends PUBatchCode {

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    super.afterEdit(event);
    for (int row : super.getRows()) {
      RelationCalculate rc = new RelationCalculate();
      rc.calculate(event.getBillCardPanel(), row, PraybillItemVO.NNUM);
    }
  }

  @Override
  protected Map<String, String> getBodyDims() {
    Map<String, String> bodyDims = new HashMap<String, String>();
    bodyDims.put(OnhandDimVO.PK_ORG, PraybillItemVO.PK_ORG);
    bodyDims.put(OnhandDimVO.CVENDORID, PraybillItemVO.PK_SUGGESTSUPPLIER);
    bodyDims.put(OnhandDimVO.CWAREHOUSEID, PraybillItemVO.PK_REQSTOR);
    return bodyDims;
  }

  @Override
  protected String[] getFieldsNotNeed() {
    return new String[] {
      PraybillItemVO.NNUM
    };
  }

  @Override
  protected Map<String, String> getHeadDims() {
    Map<String, String> headDims = new HashMap<String, String>();
    return headDims;
  }

}
