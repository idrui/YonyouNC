package nc.vo.pu.m23.rule;

import nc.ui.pub.bill.IBillItem;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.pub.rule.IScaleProcessor;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.FieldInfo;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.pubapp.scale.TotalValueScale;

/**
 * ���������ȴ�����
 * 
 * @since 6.0
 * @version 2010-11-4 ����08:12:53
 * @author tianft
 */
public class ArriveScaleRule implements IScaleProcessor {

  public void setGeneralScale(BillScaleProcessor scale) {
    this.setGeneralScale(scale, PosEnum.body);
  }

  /**
   * ��ͨ�ľ��ȴ���
   * 
   * @param scale - ��ͨ�ľ��ȴ�����
   */
  public void setGeneralScale(BillScaleProcessor scale, PosEnum posEnum) {
    // ������
    String[] changeRates = new String[] {
      ArriveItemVO.VCHANGERATE
    };
    // ���
    String[] mnykeys = new String[] {
      ArriveItemVO.NMNY, ArriveItemVO.NTAXMNY, ArriveItemVO.NTAX
    };
    // ԭ�ҽ��
    String[] orgmnykeys = new String[] {
      ArriveItemVO.NORIGTAXMNY, ArriveItemVO.NORIGMNY,
    };
    // ��ͷ�ϼƽ�����ı��ֲ�һ�����Ա�ͷ�ϼƲ��ٴ����ȣ�����wangyf2011.6.26ȷ�ϣ�
    // ��ͷ�ϼƽ����ݹ�������2011.9.7��ģ�徫��
    // ҵ��λ����
    String[] assistNumkeys =
        new String[] {
          ArriveItemVO.NASTNUM, ArriveItemVO.NPLANASTNUM,
          ArriveItemVO.NWASTASTNUM, ArriveItemVO.NPRESENTASTNUM
        };
    // ������
    String[] numkeys =
        new String[] {
          ArriveItemVO.NACCUMCHECKNUM, ArriveItemVO.NACCUMLETGONUM,
          ArriveItemVO.NACCUMREPLNUM, ArriveItemVO.NACCUMSTORENUM,
          ArriveItemVO.NNUM, ArriveItemVO.NPLANNUM, ArriveItemVO.NWASTNUM,
          ArriveItemVO.NPRESENTNUM, ArriveItemVO.NELIGNUM,
          ArriveItemVO.NNOTELIGNUM, ArriveItemVO.NCANSTORENUM,
          ArriveItemVO.NCANARRIVENUM, ArriveItemVO.NCHECKNUM,
          ArriveItemVO.NWILLELIGNUM, ArriveItemVO.NWILLNOTELIGNUM,
          ArriveItemVO.NACCUMBACKNUM
        };
    // ԭ�Ҽ۸�
    String[] OcurrPricekeys = new String[] {
      ArriveItemVO.NORIGPRICE, ArriveItemVO.NORIGTAXPRICE
    };
    // ���Ҽ۸�
    String[] CurrPricekeys = new String[] {
      ArriveItemVO.NPRICE, ArriveItemVO.NTAXPRICE
    };
    // ����˰��
    String[] taxRateKey_B = new String[] {
      ArriveItemVO.NTAXRATE
    };
    // ����˰��
    scale.setTaxRateCtlInfo(taxRateKey_B, posEnum, null);
    // ������
    scale.setHslCtlInfo(changeRates, posEnum, null);
    // ҵ��λ��������
    scale.setNumCtlInfo(assistNumkeys, posEnum, null, ArriveItemVO.CASTUNITID,
        posEnum, null);
    // ����λ��������
    scale.setNumCtlInfo(numkeys, posEnum, null, ArriveItemVO.CUNITID, posEnum,
        null);
    // ����
    scale.setMnyCtlInfo(mnykeys, posEnum, null, ArriveItemVO.CCURRENCYID,
        posEnum, null);
    // ԭ�ҽ���
    scale.setMnyCtlInfo(orgmnykeys, posEnum, null,
        ArriveItemVO.CORIGCURRENCYID, posEnum, null);
    // ԭ�ҵ��۾���
    scale.setPriceCtlInfo(OcurrPricekeys, posEnum, null,
        ArriveItemVO.CORIGCURRENCYID, posEnum, null);
    // ���ҵ��۾���
    scale.setPriceCtlInfo(CurrPricekeys, posEnum, null,
        ArriveItemVO.CCURRENCYID, posEnum, null);
    // �۱����ʾ���
    this.setOrgExchange(scale, PosEnum.body.equals(posEnum) ? IBillItem.BODY
        : IBillItem.HEAD);
    // ���м���
    scale.process();
  }

  /**
   * ��ͷ�ϼƾ��ȴ���
   * 
   * @param totalScale �ϼƵľ��ȴ�����
   */
  public void setHeaderScale(TotalValueScale totalScale) {
    totalScale.setHeadTailKeys(new String[] {
      ArriveHeaderVO.NTOTALASTNUM, ArriveHeaderVO.NTOTALTAXMNY
    });
  }

  @Override
  public void setScale(BillScaleProcessor scale, TotalValueScale totalScale) {
    if (scale != null) {
      this.setGeneralScale(scale);
    }
    if (totalScale != null) {
      this.setHeaderScale(totalScale);
    }
  }

  @Override
  public void setScaleForSingleTable(BillScaleProcessor scale,
      TotalValueScale totalScale) {
    if (scale != null) {
      this.setGeneralScale(scale, PosEnum.head);
    }
    if (totalScale != null) {
      this.setHeaderScale(totalScale);
    }
  }

  private void setOrgExchange(BillScaleProcessor scale, int pos) {
    FieldInfo rate = new FieldInfo(ArriveItemVO.NEXCHANGERATE, pos, null);
    FieldInfo srcCurr = new FieldInfo(ArriveItemVO.CORIGCURRENCYID, pos, null);
    FieldInfo destCurr = new FieldInfo(ArriveItemVO.CCURRENCYID, pos, null);
    FieldInfo org = new FieldInfo(ArriveItemVO.PK_PSFINANCEORG, pos, null);
    scale.setOrgExchangeCtlInfo(rate, srcCurr, destCurr, org);
  }

}
