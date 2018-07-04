package nc.vo.pu.report.scale.estmonthstat;

import java.util.ArrayList;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.vo.pu.report.scale.PUPubMetaNameConst;

/**
 * @since 6.0
 * @version 2011-9-6 ����03:16:44
 * @author �����
 */

public class EstMonthScaleStrategy extends ICRptBaseScalePrcStrategy {

  // ����
  @Override
  protected String getCurrencyKey() {
    return PUPubMetaNameConst.CCURRENCYID;
  }

  /**
   * ���ҽ������ֶ�
   * 
   * @return
   */
  @Override
  protected String[] getMnyFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    // �ݹ���ͳ��
    arrayListStr.add(PUPubMetaNameConst.CURRUNESTMNY);// CURRUNESTMNY ����δ�����ݹ����
    arrayListStr.add(PUPubMetaNameConst.PRENCLASHESTMONEY);// PRENCLASHESTMONEY
                                                           // ����ǰ�����ݹ����
    arrayListStr.add(PUPubMetaNameConst.PREUNESTMNY);// PREUNESTMNY ����ǰδ�����ݹ����
    arrayListStr.add(PUPubMetaNameConst.PRENGOODSMONEY);// PRENGOODSMONEY
                                                        // ����ǰ������
    arrayListStr.add(PUPubMetaNameConst.CURRNGOODSMONEY);// CURRNGOODSMONEY
                                                         // ���½�����
    arrayListStr.add(PUPubMetaNameConst.CURRNCLASHESTMONEY);// CURRNCLASHESTMONEY
                                                            // ���³����ݹ����
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // �������ֶ�
  @Override
  protected String[] getNumFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    // �ݹ���ͳ��
    arrayListStr.add(PUPubMetaNameConst.CURRNSETTLENUM);// CURRNSETTLENUM ���½�������
    arrayListStr.add(PUPubMetaNameConst.CURRUNESTNUM);// CURRUNESTNUM ����δ�����ݹ�����
    arrayListStr.add(PUPubMetaNameConst.PREUNESTNUM);// PREUNESTNUM ����ǰδ�����ݹ�����
    arrayListStr.add(PUPubMetaNameConst.PRENSETTLENUM);// PRENSETTLENUM ����ǰ��������

    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // ����λ
  @Override
  protected String getUnitKey() {
    return PUPubMetaNameConst.CUNITID;
    // return "PK_MATERIAL.pk_measdoc";
  }

  @Override
  protected void init() {

    // �ϼƲ����ִ�Сд��˴���ȥ��
    String[] totalFields = new String[] {
      "CURRNSETTLENUM",// CURRNSETTLENUM ���½�������
      "CURRUNESTNUM",// CURRUNESTNUM ����δ�����ݹ�����
      "PREUNESTNUM",// PREUNESTNUM ����ǰδ�����ݹ�����
      "PRENSETTLENUM",// PRENSETTLENUM ����ǰ��������
      "CURRUNESTMNY",// CURRUNESTMNY ����δ�����ݹ����
      "PRENCLASHESTMONEY",// PRENCLASHESTMONEY ����ǰ�����ݹ����
      "PREUNESTMNY",// PREUNESTMNY ����ǰδ�����ݹ����
      "PRENGOODSMONEY",// PRENGOODSMONEY ����ǰ������
      "CURRNGOODSMONEY",// CURRNGOODSMONEY ���½�����
      "CURRNCLASHESTMONEY"// CURRNCLASHESTMONEY ���³����ݹ����
    };
    this.getReportScaleProcess().setTotalFields(totalFields);
  }
}
