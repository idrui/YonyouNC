package nc.ui.pu.m4t.editor.body.after;

import java.util.HashMap;
import java.util.Map;

import nc.ui.pu.pub.editor.card.handler.PUBatchCode;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.ic.onhand.entity.OnhandDimVO;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;

public class BatchCode extends PUBatchCode {

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    super.afterEdit(event);
    RelationCalculate rc = new RelationCalculate();
    rc.calculate(event.getBillCardPanel(), super.getRows(),
        InitialEstItemVO.NNUM);
  }

  @Override
  protected Map<String, String> getBodyDims() {
    Map<String, String> bodyDims = new HashMap<String, String>();
    bodyDims.put(OnhandDimVO.PK_ORG, InitialEstItemVO.PK_ORG);
    return bodyDims;
  }

  @Override
  protected String[] getClearFields() {
    String[] subfields = new String[] {
      InitialEstItemVO.VCHANGERATE
    };
    String[] supfields = super.getClearFields();
    if (supfields != null && supfields.length != 0) {
      return this.combineArrays(subfields, supfields);
    }
    return subfields;
  }

  @Override
  protected String[] getFieldsNotNeed() {
    return new String[] {
      InitialEstItemVO.NNUM
    };
  }

  @Override
  protected Map<String, String> getHeadDims() {
    Map<String, String> headDims = new HashMap<String, String>();
    headDims.put(OnhandDimVO.CWAREHOUSEID, InitialEstHeaderVO.PK_STORDOC);
    headDims.put(OnhandDimVO.CWAREHOUSEID, InitialEstHeaderVO.PK_SUPPLIER);
    return headDims;
  }
}
