/**
 * $�ļ�˵��$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-10 ����02:17:09
 */
package nc.vo.pu.m25.pub;

import nc.ui.pub.bill.IBillItem;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.pub.rule.IScaleProcessor;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.FieldInfo;
import nc.vo.pubapp.scale.PosEnum;
import nc.vo.pubapp.scale.TotalValueScale;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ͷ\����ľ��ȴ�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-7-10 ����02:17:09
 */
public class InvoiceScaleProcessor implements IScaleProcessor {

  @Override
  public void setScale(BillScaleProcessor scale, TotalValueScale totalScale) {
    this.setScale(scale, totalScale, PosEnum.body, false);
  }

  public void setScaleForCheck(BillScaleProcessor scale) {
    this.setScale(scale, null, PosEnum.body, true);
  }

  @Override
  public void setScaleForSingleTable(BillScaleProcessor scale,
      TotalValueScale totalScale) {
    this.setScale(scale, totalScale, PosEnum.head, false);
  }

  private void setGlobalExchange(BillScaleProcessor scale) {
    FieldInfo rate =
        new FieldInfo(InvoiceHeaderVO.NGLOBALEXCHGRATE, IBillItem.HEAD, null);
    FieldInfo orgOrigCurr =
        new FieldInfo(InvoiceHeaderVO.CORIGCURRENCYID, IBillItem.HEAD, null);
    FieldInfo orgLocCurr =
        new FieldInfo(InvoiceHeaderVO.CCURRENCYID, IBillItem.HEAD, null);
    scale.setGlobalExchangeCtlInfo(rate, orgOrigCurr, orgLocCurr);
  }

  private void setGroupExchange(BillScaleProcessor scale) {
    FieldInfo rate =
        new FieldInfo(InvoiceHeaderVO.NGROUPEXCHGRATE, IBillItem.HEAD, null);
    FieldInfo orgOrigCurr =
        new FieldInfo(InvoiceHeaderVO.CORIGCURRENCYID, IBillItem.HEAD, null);
    FieldInfo orgLocCurr =
        new FieldInfo(InvoiceHeaderVO.CCURRENCYID, IBillItem.HEAD, null);
    scale.setGroupExchangeCtlInfo(rate, orgOrigCurr, orgLocCurr);
  }

  private void setOrgExchange(BillScaleProcessor scale) {
    FieldInfo rate =
        new FieldInfo(InvoiceHeaderVO.NEXCHANGERATE, IBillItem.HEAD, null);
    FieldInfo srcCurr =
        new FieldInfo(InvoiceHeaderVO.CORIGCURRENCYID, IBillItem.HEAD, null);
    FieldInfo destCurr =
        new FieldInfo(InvoiceHeaderVO.CCURRENCYID, IBillItem.HEAD, null);
    FieldInfo org = new FieldInfo(InvoiceHeaderVO.PK_ORG, IBillItem.HEAD, null);
    scale.setOrgExchangeCtlInfo(rate, srcCurr, destCurr, org);
  }

  private void setScale(BillScaleProcessor scale, TotalValueScale totalScale,
      PosEnum posEnum, boolean forScaleCheck) {
    //
    // ȫ�ֱ�λ�ҽ��
    String[] globalmnykeys = new String[] {
      InvoiceItemVO.NGLOBALMNY, InvoiceItemVO.NGLOBALTAXMNY
    };
    // ���ű�λ�ҽ��
    String[] groupmnykeys = new String[] {
      InvoiceItemVO.NGROUPMNY, InvoiceItemVO.NGROUPTAXMNY
    };
    // ԭ�ҽ��
    String[] orgmnykeys = new String[] {
      InvoiceItemVO.NORIGMNY, InvoiceItemVO.NORIGTAXMNY
    };
    // ��ͷ�ϼƽ����ݹ�������2011.9.7��ģ�徫��
    String[] headMnykeys = new String[] {
      InvoiceHeaderVO.NTOTALORIGMNY
    };

    // ���ҽ��
    String[] mnykeys =
        new String[] {
          InvoiceItemVO.NMNY, InvoiceItemVO.NTAXMNY, InvoiceItemVO.NTAX,
          InvoiceItemVO.NACCUMSETTMNY, InvoiceItemVO.NCALTAXMNY,
          InvoiceItemVO.NNOSUBTAX, InvoiceItemVO.NCALCOSTMNY
        };
    // ҵ��λ����
    String[] assistNumkeys = new String[] {
      InvoiceItemVO.NASTNUM
    };
    // ������
    String[] numkeys =
        new String[] {
          InvoiceItemVO.NNUM, InvoiceItemVO.NACCUMSETTNUM,
          InvoiceItemVO.NREASONWASTENUM
        };
    // ���Ҽ۸�
    String[] CurrPricekeys =
        new String[] {
          InvoiceItemVO.NPRICE, InvoiceItemVO.NTAXPRICE,
          InvoiceItemVO.NASTPRICE, InvoiceItemVO.NASTTAXPRICE
        };
    // ԭ�Ҽ۸�
    String[] OcurrPricekeys =
        new String[] {
          InvoiceItemVO.NORIGPRICE, InvoiceItemVO.NORIGTAXPRICE,
          InvoiceItemVO.NASTORIGPRICE, InvoiceItemVO.NASTORIGTAXPRICE,
          InvoiceItemVO.NPLANPRICE
        };
    // ����˰��
    String[] taxRateKey_B = new String[] {
      InvoiceItemVO.NTAXRATE, InvoiceItemVO.NNOSUBTAXRATE
    };
    // ��ͷ˰��
    String[] taxRateKey_H = new String[] {
      InvoiceHeaderVO.NTAXRATEH
    };
    // ������
    String[] changeRates = new String[] {
      InvoiceItemVO.VCHANGERATE
    };
    // ҵ��λ��������
    scale.setNumCtlInfo(assistNumkeys, posEnum, null, InvoiceItemVO.CASTUNITID,
        posEnum, null);
    // ����λ��������
    scale.setNumCtlInfo(numkeys, posEnum, null, InvoiceItemVO.CUNITID, posEnum,
        null);
    // ���ҵ��۾���
    scale.setPriceCtlInfo(CurrPricekeys, posEnum, null,
        InvoiceHeaderVO.CCURRENCYID, PosEnum.head, null);
    // ԭ�ҵ��۾���
    scale.setPriceCtlInfo(OcurrPricekeys, posEnum, null,
        InvoiceHeaderVO.CORIGCURRENCYID, PosEnum.head, null);
    // ���ҽ���
    scale.setMnyCtlInfo(mnykeys, posEnum, null, InvoiceHeaderVO.CCURRENCYID,
        PosEnum.head, null);

    // ԭ�ҽ���
    scale.setMnyCtlInfo(orgmnykeys, posEnum, null,
        InvoiceHeaderVO.CORIGCURRENCYID, PosEnum.head, null);
    // �˴��ž���У�鲻��Ҫ����Ĳ���,����У��ֻ��Լ򵥵ĵ��ۡ���������
    if (!forScaleCheck) {
      // ����˰��
      scale.setTaxRateCtlInfo(taxRateKey_B, posEnum, null);
      // ��ͷ����˰��
      scale.setTaxRateCtlInfo(taxRateKey_H, PosEnum.head, null);
      // ȫ�ֱ�λ�ҽ���
      scale.setGlobalLocMnyCtlInfo(globalmnykeys, posEnum, null);
      // ���ű�λ�ҽ���
      scale.setGroupLocMnyCtlInfo(groupmnykeys, posEnum, null);
      // ������
      scale.setHslCtlInfo(changeRates, PosEnum.body, null);
      // ���ʾ��ȴ���
      this.setOrgExchange(scale);
      this.setGroupExchange(scale);
      this.setGlobalExchange(scale);

      // ��ͷ�ϼƽ����ݹ�������2011.9.7��ģ�徫��
      // ��ͷ�ϼ������ľ������ϴ���ﱦǰ�Ѿ�ȷ��Ĩ�㴦�����ϼ�һ�㶼�ᰴ���ִ���������ҪĨ��������ȷ�ϣ���
      scale.setMnyCtlInfo(headMnykeys, PosEnum.head, null,
          InvoiceHeaderVO.CORIGCURRENCYID, PosEnum.head, null);
    }
    // ���м���
    scale.process();
    if (totalScale != null) {
      // ��ͷ�ϼ�
      String[] totalKeys = new String[] {
        InvoiceHeaderVO.NTOTALASTNUM
      };
      totalScale.setHeadTailKeys(totalKeys);
    }
  }
}
