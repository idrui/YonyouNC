package nc.vo.pu.report.scale.m27;

import java.util.ArrayList;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.vo.pu.m27.entity.SettleBillItemVO;
import nc.vo.pu.report.scale.PUPubMetaNameConst;

/**
 * ���㵥��ѯ
 * 
 * @since 6.0
 * @version 2011-9-7 ����01:42:21
 * @author �����
 */

public class SettleBillQueryScaleStrategy extends ICRptBaseScalePrcStrategy {

  // ����
  @Override
  protected String getCurrencyKey() {
    return PUPubMetaNameConst.CCURRENCYID;
  }

  // ����ֶ�
  @Override
  protected String[] getMnyFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    // �ɹ����㵥��ѯ
    arrayListStr.add(SettleBillItemVO.NGOODSMONEY);// ngoodsmoney ���������
    arrayListStr.add(SettleBillItemVO.NREASONALWASTMNY);// ������Ľ��
    arrayListStr.add(SettleBillItemVO.NCOSTFACTOR1);// ���ҳɱ�Ҫ��1
    arrayListStr.add(SettleBillItemVO.NCOSTFACTOR2);
    arrayListStr.add(SettleBillItemVO.NCOSTFACTOR3);
    arrayListStr.add(SettleBillItemVO.NCOSTFACTOR4);
    arrayListStr.add(SettleBillItemVO.NCOSTFACTOR5);
    arrayListStr.add(SettleBillItemVO.NCOSTFACTOR6);
    arrayListStr.add(SettleBillItemVO.NCOSTFACTOR7);
    arrayListStr.add(SettleBillItemVO.NCOSTFACTOR8);// ���ҳɱ�Ҫ��8
    arrayListStr.add(SettleBillItemVO.NDISCOUNT);
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // �������ֶ�
  @Override
  protected String[] getNumFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    // �ɹ����㵥��ѯ
    arrayListStr.add(SettleBillItemVO.NSETTLENUM);// ��������
    arrayListStr.add(SettleBillItemVO.NREASONALWASTNUM);// �����������
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // �۸��ֶ�
  @Override
  protected String[] getPriceFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    arrayListStr.add(SettleBillItemVO.NGOODSPRICE); // ngoodsprice ������㵥��
    arrayListStr.add(SettleBillItemVO.NREASONALWASTPRICE); // nreasonalwastprice������ĵ���
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
