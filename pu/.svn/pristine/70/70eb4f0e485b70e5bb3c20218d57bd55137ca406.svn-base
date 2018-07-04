package nc.ui.pu.m422x.editor.card.afteredit.body;

import java.util.HashMap;
import java.util.Map;

import nc.ui.pu.m422x.editor.card.afteredit.RelationCalculate;
import nc.ui.pu.pub.editor.card.handler.PUBatchCode;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.ic.onhand.entity.OnhandDimVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;

public class BatchCode extends PUBatchCode {

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    super.afterEdit(event);
    RelationCalculate rc = new RelationCalculate();
    rc.calculate(event.getBillCardPanel(), super.getRows(),
        StoreReqAppItemVO.NNUM);
  }

  @Override
  protected Map<String, String> getBodyDims() {
    Map<String, String> bodyDims = new HashMap<String, String>();
    bodyDims.put(OnhandDimVO.PK_ORG, StoreReqAppItemVO.PK_ORG);
    bodyDims.put(OnhandDimVO.CVENDORID, StoreReqAppItemVO.CVENDORID);
    bodyDims.put(OnhandDimVO.CWAREHOUSEID, StoreReqAppItemVO.PK_REQSTORDOC);
    return bodyDims;
  }

  @Override
  protected String[] getFieldsNotNeed() {
    return new String[] {
      StoreReqAppItemVO.NNUM
    };
  }

  @Override
  protected Map<String, String> getHeadDims() {
    Map<String, String> headDims = new HashMap<String, String>();
    return headDims;
  }

}
