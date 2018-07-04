package nc.vo.pu.report.scale.m21;

import nc.scmmm.pub.scmpub.report.scale.SCMRptAbsScalePrcStrategy;
import nc.scmmm.vo.scmpub.report.entity.scale.SCMReportScaleMetaRegister;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.report.enumeration.OrderSummaryDynamicFieldCode;

public class OrderSummaryScalePrcStrategy extends SCMRptAbsScalePrcStrategy {

  private static final long serialVersionUID = -7494849866994352343L;

  @Override
  protected void registerScaleProcess(
      SCMReportScaleMetaRegister scmRptScaleRegister) {
    // ��������������������;�������� ���������������Ʊ���������˻���������
    scmRptScaleRegister.setNumDigits(OrderItemVO.CUNITID, new String[] {
      OrderItemVO.NNUM, OrderItemVO.NACCUMARRVNUM, OrderItemVO.NACCUMWASTNUM,
      OrderItemVO.NACCUMSTORENUM, OrderItemVO.NACCUMINVOICENUM,
      OrderItemVO.NBACKARRVNUM
    });

    // ������˰�����Ҽ�˰�ϼƣ�����˰� �˻���
    scmRptScaleRegister.setMnyDigits(OrderItemVO.CCURRENCYID, new String[] {
      OrderItemVO.NMNY, OrderItemVO.NTAXMNY, OrderItemVO.NTAX,
      OrderSummaryDynamicFieldCode.returnmny.getCode()
    });
  }
}
