package nc.vo.pu.report.scale.m21;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.vo.pu.m21.entity.OrderItemVO;

/**
 * �ɹ�������;��ѯ���ȴ����ࡣ
 * 
 * @since 6.0
 * @version 2012-9-18 ����10:46:37
 * @author lixyp
 */
public class OrderOnwayScaleStrategy extends ICRptBaseScalePrcStrategy {

  // ����
  @Override
  protected String getCurrencyKey() {
    return OrderItemVO.CCURRENCYID;
  }

  // ���
  @Override
  protected String[] getMnyFields() {
    return new String[] {
      OrderItemVO.NTAXMNY
    };
  }

  // ������
  @Override
  protected String[] getNumFields() {
    return new String[] {
      OrderItemVO.NNUM
    };
  }

  // ����
  @Override
  protected String[] getPriceFields() {
    return new String[] {
      OrderItemVO.NQTTAXNETPRICE
    };
  }

  // ����λ
  @Override
  protected String getUnitKey() {
    return OrderItemVO.CUNITID;
  }
}
