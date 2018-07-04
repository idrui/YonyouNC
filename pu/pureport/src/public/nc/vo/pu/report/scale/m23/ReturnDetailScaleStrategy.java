package nc.vo.pu.report.scale.m23;

import java.util.ArrayList;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.report.scale.PUPubMetaNameConst;

/**
 * �ɹ������˻���ϸ
 * 
 * @since 6.0
 * @version 2011-9-7 ����01:49:24
 * @author �����
 */

public class ReturnDetailScaleStrategy extends ICRptBaseScalePrcStrategy {
  // �������ֶ�
  @Override
  protected String[] getAssistnumFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    // �ɹ������˻���ϸ��ѯ
    arrayListStr.add(PUPubMetaNameConst.NASTNUM);// NASTNUM �˻�����
    arrayListStr.add(PUPubMetaNameConst.RETURNENUM);// RETURNENUM �˻�����
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // ��������λ
  @Override
  protected String getAstunitKey() {
    return PUPubMetaNameConst.CASTUNITID;
  }

  // ����
  @Override
  protected String getCurrencyKey() {
    return PUPubMetaNameConst.CCURRENCYID;
  }

  // ����ֶ�
  @Override
  protected String[] getMnyFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    arrayListStr.add(OrderItemVO.NTAXMNY);// �˻����
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // �������ֶ�
  @Override
  protected String[] getNumFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    arrayListStr.add(PUPubMetaNameConst.NACCUMREPLNUM);// NACCUMREPLNUM ����������
    arrayListStr.add(OrderItemVO.NNUM);// �˻�������
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // ����λ
  @Override
  protected String getUnitKey() {
    return PUPubMetaNameConst.CUNITID;
  }

  @Override
  protected void init() {
    //
  }
}
