package nc.vo.pu.m21.rule;

import nc.vo.ic.batchcode.AbstractBatchFieldMap;
import nc.vo.pu.m21.entity.OrderItemVO;

/**
 * @since 6.0
 * @version 2011-4-23 ÉÏÎç08:23:58
 * @author wuxla
 */

public class OrderBatchCodeFieldMap extends AbstractBatchFieldMap {

  @Override
  public String[] getBillFields() {
    String[] billFields = new String[38];
    billFields[0] = OrderItemVO.PK_GROUP;
    billFields[1] = OrderItemVO.PK_BATCHCODE;
    billFields[2] = OrderItemVO.PK_MATERIAL;
    billFields[3] = OrderItemVO.PK_SRCMATERIAL;
    billFields[4] = OrderItemVO.VBATCHCODE;
    billFields[5] = null;
    billFields[6] = null;
    billFields[7] = OrderItemVO.CQUALITYLEVELID;
    billFields[8] = null;
    billFields[9] = null;
    billFields[10] = null;
    billFields[11] = null;
    billFields[12] = null;
    billFields[13] = null;
    billFields[14] = null;
    billFields[15] = null;
    billFields[16] = null;
    billFields[17] = null;
    billFields[18] = null;
    billFields[19] = null;
    billFields[20] = null;
    billFields[21] = null;
    billFields[22] = null;
    billFields[23] = null;
    billFields[24] = null;
    billFields[25] = null;
    billFields[26] = null;
    billFields[27] = null;
    billFields[28] = null;
    billFields[29] = null;
    billFields[30] = null;
    billFields[31] = null;
    billFields[32] = null;
    billFields[33] = null;
    billFields[34] = null;
    billFields[35] = null;
    billFields[36] = null;
    billFields[37] = null;
    return billFields;
  }
}
