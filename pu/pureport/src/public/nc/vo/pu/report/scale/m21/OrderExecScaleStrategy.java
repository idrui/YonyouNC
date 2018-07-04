package nc.vo.pu.report.scale.m21;

import java.util.ArrayList;
import java.util.List;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.report.scale.PUPubMetaNameConst;
import nc.vo.pu.report.view.order.OrderExecDetailViewMeta;

/**
 * @since 6.0
 * @version 2011-9-7 ����01:35:05
 * @author �����
 */

public class OrderExecScaleStrategy extends ICRptBaseScalePrcStrategy {

  // ����
  @Override
  protected String getCurrencyKey() {
    return PUPubMetaNameConst.CORIGCURRENCYID;
  }

  /**
   * ���ҽ������ֶ�
   * 
   * @return
   */
  protected String[] getCurrencyMnyFields() {
    List<String> arrayListStr = new ArrayList<String>();
    // ������˰���
    arrayListStr.add(OrderItemVO.NMNY);
    // ���Ҽ�˰�ϼ�
    arrayListStr.add(OrderItemVO.NTAXMNY);
    // ����˰��
    arrayListStr.add(OrderItemVO.NTAX);
    arrayListStr.add(OrderExecDetailViewMeta.INVOICE_NTAXMNY);
    arrayListStr.add(OrderExecDetailViewMeta.NACCCANCELINVMNY);
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // ����ֶ�
  @Override
  protected String[] getMnyFields() {
    List<String> arrayListStr = new ArrayList<String>();
    // norigmny ��˰���
    arrayListStr.add(OrderItemVO.NORIGMNY);
    // norigtaxmny ��˰�ϼ�
    arrayListStr.add(OrderItemVO.NORIGTAXMNY);
    // // norigtax ˰��
    // arrayListStr.add(OrderItemVO.NORIGTAX);
    // �����
    arrayListStr.add(OrderExecDetailViewMeta.IC_NORIGMNY);
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // �������ֶ�
  @Override
  protected String[] getNumFields() {
    List<String> arrayListStr = new ArrayList<String>();
    // ������
    arrayListStr.add(OrderItemVO.NNUM);
    arrayListStr.add(OrderExecDetailViewMeta.CONFIRM_NNUM);
    arrayListStr.add(OrderExecDetailViewMeta.OUTPUT_NNUM);
    // ����������
    arrayListStr.add(OrderExecDetailViewMeta.ARRIVE_NNUM);
    // �˻�������
    arrayListStr.add(OrderExecDetailViewMeta.NBACKARRVNUM);
    arrayListStr.add(OrderExecDetailViewMeta.QC_NNUM);
    arrayListStr.add(OrderExecDetailViewMeta.ARRIVE_BLARGESSNUM);
    arrayListStr.add(OrderExecDetailViewMeta.NELIGNUM);
    // ��Ʊ������
    arrayListStr.add(OrderExecDetailViewMeta.INVOICE_NNUM);

    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // �۸��ֶ�
  @Override
  protected String[] getPriceFields() {
    List<String> arrayListStr = new ArrayList<String>();
    // �ɹ�����ִ�в�ѯ
    // ��˰����
    arrayListStr.add(OrderItemVO.NQTORIGPRICE);
    // ��˰����
    arrayListStr.add(OrderItemVO.NQTORIGTAXPRICE);
    // ��˰����
    arrayListStr.add(OrderItemVO.NQTORIGNETPRICE);
    // ��˰����
    arrayListStr.add(OrderItemVO.NQTORIGTAXNETPRC);
    // ����˰����
    arrayListStr.add(OrderItemVO.NORIGNETPRICE);
    // ����˰����
    arrayListStr.add(OrderItemVO.NORIGTAXNETPRICE);
    // ��������˰����
    arrayListStr.add(OrderItemVO.NNETPRICE);
    // �����Һ�˰����
    arrayListStr.add(OrderItemVO.NTAXNETPRICE);
    // ��ⵥ��
    arrayListStr.add(OrderExecDetailViewMeta.IC_NORIGPRICE);
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  protected String[] getStockNumFields() {
    List<String> arrayListStr = new ArrayList<String>();
    // ���������
    arrayListStr.add(OrderExecDetailViewMeta.IC_BLARGESSNUM);
    arrayListStr.add(OrderExecDetailViewMeta.IC_NNUM);
    arrayListStr.add(OrderExecDetailViewMeta.NBACKSTORENUM);
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // ˰���ֶ�
  @Override
  protected String[] getTaxRateStrFields() {
    return new String[] {
      PUPubMetaNameConst.NTAXRATE
    };
  }

  // ����λ
  @Override
  protected String getUnitKey() {
    return PUPubMetaNameConst.CUNITID;
  }

  @Override
  protected void init() {
    // ����������
    this.setNumDigits(OrderExecDetailViewMeta.IC_CUNITID,
        this.getStockNumFields());
    // �Ա��ҽ��ȵĴ���
    this.setMnyDigits(OrderItemVO.CCURRENCYID, this.getCurrencyMnyFields());

    this.getReportScaleProcess().setConstantDigits(new String[] {
      OrderItemVO.NITEMDISCOUNTRATE
    }, 2);
  }
}
