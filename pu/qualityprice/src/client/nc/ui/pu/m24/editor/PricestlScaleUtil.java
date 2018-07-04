package nc.ui.pu.m24.editor;

import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.ui.pubapp.scale.CardPaneScaleProcessor;
import nc.ui.pubapp.scale.ListPaneScaleProcessor;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.vo.pu.m24.entity.PricestlHeaderVO;
import nc.vo.pu.m24.entity.PricestlItemBVO;
import nc.vo.pu.m24.entity.PricestlItemVO;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pubapp.scale.BillScaleProcessor;
import nc.vo.pubapp.scale.BillVOScaleProcessor;
import nc.vo.pubapp.scale.PosEnum;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�۸���㵥��������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-8-3 ����10:21:15
 */
public class PricestlScaleUtil {

  /**
   * ����������������Ƭ�������á�
   * <p>
   * <b>����˵��</b>
   * 
   * @param billform <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-6 ����09:07:30
   */
  public void orgChgScale(ShowUpableBillForm billform) {
    String pk_group = billform.getModel().getContext().getPk_group();
    BillCardPanel bcp = billform.getBillCardPanel();
    this.setScale(new CardPaneScaleProcessor(pk_group, bcp));
  }

  /**
   * ���������������б������á�
   * <p>
   * <b>����˵��</b>
   * 
   * @param billform <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-6 ����09:07:30
   */
  public void setListScale(BillListPanel blp, String pk_group) {
    this.setScale(new ListPaneScaleProcessor(pk_group, blp));
  }

  /**
   * ��������������VO�������á�
   * <p>
   * <b>����˵��</b>
   * 
   * @param pk_group
   * @param bills <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-8-6 ����09:08:04
   */
  public void setVOScale(String pk_group, PricestlVO[] bills) {
    this.setScale(new BillVOScaleProcessor(pk_group, bills));
  }

  private void setScale(BillScaleProcessor scale) {

    // ���Ҽ�˰�ϼ�
    String[] mnykeys = new String[] {
      PricestlItemVO.NTAXMNY
    };
    // ������
    String[] standCalValue = new String[] {
        PricestlItemBVO.NSTANDCALVALUE
    };
    // �ܼ�����
    String[] calValue = new String[] {
        PricestlItemVO.NSCHEMECALVALUE
    };
    // ҵ��λ����
    String[] assistNumkeys =
        new String[] {
          PricestlItemVO.NASTARRVNUM, PricestlItemVO.NASTINNUM,
          PricestlItemVO.NASTSHOULDINNUM
        };
    // ������
    String[] numkeys =
        new String[] {
          PricestlItemVO.NARRVNUM, PricestlItemVO.NINNUM,
          PricestlItemVO.NSHOULDINNUM
        };
    // �۸�
    String[] pricekeys =
        new String[] {
          PricestlItemVO.NTAXPRICE, PricestlItemVO.NBASEPRICE,
          PricestlItemVO.NBASETAXPRICE, PricestlItemVO.NCALQUALPRICE,
          PricestlItemVO.NPRICE, PricestlItemVO.DBASEPRICE
        };
    // ��β�۸�
    String[] tailPriceKeys = new String[] {
      PricestlItemVO.DBASEPRICE
    };

    // ������
    String[] changeRates = new String[] {
      PricestlItemVO.VCHANGERATE,
    };
    // ����˰��
    String[] taxRateKey_B = new String[] {
      PricestlItemVO.NTAXRATE
    };
    // ����˰��
    scale.setTaxRateCtlInfo(taxRateKey_B, PosEnum.body, null);
    // �����ʾ���
    scale.setHslCtlInfo(changeRates, PosEnum.body, null);
    // ҵ��λ��������
    scale.setNumCtlInfo(assistNumkeys, PosEnum.body, null,
        PricestlItemVO.CASTUNITID, PosEnum.body, null);
    // ����λ��������
    scale.setNumCtlInfo(numkeys, PosEnum.body, null, PricestlItemVO.CUNITID,
        PosEnum.body, null);
    // ���ҽ���
    scale.setMnyCtlInfo(mnykeys, PosEnum.body, null,
        PricestlHeaderVO.CCURRENCYID, PosEnum.head, null);
    // ���۾���
    scale.setPriceCtlInfo(pricekeys, PosEnum.body, null,
        PricestlHeaderVO.CCURRENCYID, PosEnum.head, null);
    // ��β�۸񾫶�
    scale.setPriceCtlInfo(tailPriceKeys, PosEnum.tail, null,
        PricestlHeaderVO.CCURRENCYID, PosEnum.tail, null);
    // ������
    scale.setPriceCtlInfo(standCalValue, PosEnum.body, StockOrg.BODY_HQHP,
        PricestlHeaderVO.CCURRENCYID, PosEnum.head, null);
    // �ܼ�����
    scale.setPriceCtlInfo(calValue, PosEnum.tail, null,
        PricestlHeaderVO.CCURRENCYID, PosEnum.head, null);
    // ���м���
    scale.process();
  }

}
