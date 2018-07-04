package nc.ui.pu.m21.editor.card.afteredit.body;

import java.util.HashMap;
import java.util.Map;

import nc.ui.pu.m21.editor.card.afteredit.RelationCalculate;
import nc.ui.pu.pub.editor.card.handler.PUBatchCode;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.vo.ic.onhand.entity.OnhandDimVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;

public class BatchCode extends PUBatchCode {

  @Override
  public void afterEdit(CardBodyAfterEditEvent event) {
    super.afterEdit(event);
    RelationCalculate rc = new RelationCalculate();
    rc.calculate(event.getBillCardPanel(), super.getRows(), OrderItemVO.NNUM);
  }

  @Override
  protected Map<String, String> getBodyDims() {
    Map<String, String> bodyDims = new HashMap<String, String>();
    bodyDims.put(OnhandDimVO.PK_ORG, OrderItemVO.PK_ARRVSTOORG);
    bodyDims.put(OnhandDimVO.CVENDORID, OrderItemVO.CPRODUCTORID);
    bodyDims.put(OnhandDimVO.CWAREHOUSEID, OrderItemVO.PK_RECVSTORDOC);
    return bodyDims;
  }

  @Override
  protected String[] getClearFields() {
    String[] subfields = new String[] {
      OrderItemVO.VCHANGERATE, OrderItemVO.VQTUNITRATE
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
      OrderItemVO.NNUM
    };
  }

  @Override
  protected Map<String, String> getHeadDims() {
    Map<String, String> headDims = new HashMap<String, String>();
    headDims.put(OnhandDimVO.CVENDORID, OrderHeaderVO.PK_SUPPLIER);
    return headDims;
  }

}
