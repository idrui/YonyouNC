package nc.vo.pu.report.scale.m21;

import java.util.ArrayList;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.vo.pu.report.scale.PUPubMetaNameConst;

/**
 * �ɹ���������ִ�����
 * 
 * @since 6.0
 * @version 2011-9-7 ����01:35:05
 * @author �����
 */

public class OrderPayExecScaleStrategy extends ICRptBaseScalePrcStrategy {

  // ����
  @Override
  protected String getCurrencyKey() {
    return PUPubMetaNameConst.CCURRENCYID;
  }

  // ����ֶ�
  @Override
  protected String[] getMnyFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    arrayListStr.add(PUPubMetaNameConst.NORDERMNY);// �������ҽ��
    arrayListStr.add(PUPubMetaNameConst.NINVOICEMNY);// ��Ʊ���ҽ��
    arrayListStr.add(PUPubMetaNameConst.NPAYMNY);// ����������
    arrayListStr.add(PUPubMetaNameConst.NUNVERIFYMNY);// δ����������
    arrayListStr.add(PUPubMetaNameConst.NINVOICEBALANCE);// ��ƱӦ�����
    arrayListStr.add(PUPubMetaNameConst.NORDERBALANCE);// ����Ӧ�����

    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  @Override
  protected void init() {
    //
  }
}
