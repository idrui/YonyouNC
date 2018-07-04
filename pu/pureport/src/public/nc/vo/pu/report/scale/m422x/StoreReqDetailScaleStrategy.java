package nc.vo.pu.report.scale.m422x;

import java.util.ArrayList;

import nc.adapter.ic.icreport.base.ICRptBaseScalePrcStrategy;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.report.scale.PUPubMetaNameConst;

/**
 * ������������ִ�������ѯ
 * 
 * @since 6.32
 * @version 2014-5-25 ����06:35:05
 * @author zhangyhh
 */

public class StoreReqDetailScaleStrategy extends ICRptBaseScalePrcStrategy {
  // �������ֶ�
  @Override
  protected String[] getAssistnumFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();

    arrayListStr.add(StoreReqAppItemVO.NASTNUM);// nastnum ����
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // ��������λ
  @Override
  protected String getAstunitKey() {
    return PUPubMetaNameConst.CASTUNITID;
  }

  // ԭ�ұ���
  @Override
  protected String getCurrencyKey() {
    return PUPubMetaNameConst.CCURRENCYID;
  }

  /**
   * ���ҽ������ֶ�
   * 
   * @return
   */
  protected String[] getCurrencyMnyFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    arrayListStr.add(StoreReqAppItemVO.NTAXMNY);// ���Ҽ�˰�ϼ�
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // �������ֶ�
  @Override
  protected String[] getNumFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    arrayListStr.add(StoreReqAppItemVO.NNUM);
    arrayListStr.add(StoreReqAppItemVO.NACCCUSTORNUM);
    arrayListStr.add(StoreReqAppItemVO.NACCUMBUYREQNUM);
    arrayListStr.add(StoreReqAppItemVO.NACCUOUTNUM);
    arrayListStr.add(StoreReqAppItemVO.NACCUOUTREQNUM);
    arrayListStr.add(StoreReqAppItemVO.NNETNUM);

    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // �۸��ֶ�
  @Override
  protected String[] getPriceFields() {
    ArrayList<String> arrayListStr = new ArrayList<String>();
    arrayListStr.add(StoreReqAppItemVO.NTAXPRICE);// �����Һ�˰����
    return arrayListStr.toArray(new String[arrayListStr.size()]);
  }

  // ����λ
  @Override
  protected String getUnitKey() {
    return PUPubMetaNameConst.CUNITID;
  }

  @Override
  protected void init() {
    // �Ա��ҽ��ȵĴ���
    this.setMnyDigits(StoreReqAppItemVO.CCURRENCYID,
        this.getCurrencyMnyFields());
  }
}
