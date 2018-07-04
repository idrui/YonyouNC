package nc.bs.pu.m25.upgrade.v61;

import java.util.HashMap;
import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.rule.upgrade.VatUpgradeUtil;
import nc.vo.scmpub.vatupdate.VatFieldEnum;
import nc.vo.scmpub.vatupdate.VatUpdateProcess;

/**
 * �ɹ���ƱΪ������v61������
 * 
 * @since 6.0
 * @version 2012-3-6 ����10:51:16
 * @author tianft
 */
public class M25UpgradeForV61 {

  public void doAfterUpdateDataFrom60To61() {
    // ��������
    this.doDataUpgrade();
  }

  /**
   * ��������
   */
  private void doDataUpgrade() {
    this.upgradeDataByVatProcessor();
    // this.upgradeDataByBillPage();
    // this.updateItemNull();
  }

  /**
   * �����ֶ�Ĭ��ΪNull�Ĳ�������Ϊ~
   * <p>
   * ʹ�ó�����
   * <ul>
   * <li>�����ֶ�Ĭ��ΪNull�Ĳ�������Ϊ~
   * </ul>
   */
  // private void updateItemNull() {
  // String[] arrs = new String[] {
  // InvoiceItemVO.CASSCUSTID, InvoiceItemVO.CPROJECTTASKID
  // };
  // new
  // VODefaultValueUpgrade<InvoiceItemVO>(InvoiceItemVO.class).upValue(arrs);
  // }

  // private void upgradeDataByBillPage() {
  // BillPageUpgrade<InvoiceVO> billUpgrade =
  // new BillPageUpgrade<InvoiceVO>(InvoiceVO.class);
  // List<IRule<InvoiceVO>> rules = new ArrayList<IRule<InvoiceVO>>();
  // // ��˰�������
  // rules.add(new TaxtypeFlagUpgradeRule<InvoiceVO>(
  // InvoiceHeaderVO.FTAXTYPEFLAGH, InvoiceHeaderVO.NTAXRATEH));
  // // VAT���˰������
  // rules.add(new VATValueUpgrade<InvoiceVO>());
  // // ����vo״̬���£�����������ϣ����򲻸��£�
  // rules.add(new VOStatusUpdateRule<InvoiceVO>());
  // billUpgrade.doUpgrade(" and dr = 0 ", rules);
  // }

  /**
   * ����vat�����������ң�˰�룬�������͵�
   */
  private void upgradeDataByVatProcessor() {
    VatUpdateProcess sqlUpdate = new VatUpdateProcess();
    // ��������-���ڲɹ�
    sqlUpdate.setExpressValue(VatFieldEnum.FBUYSELLFLAG,
        BuySellFlagEnum.NATIONAL_BUY.value().toString());
    Map<String, String> moreExpress = new HashMap<String, String>();
    moreExpress.put(InvoiceHeaderVO.CTRADEWORDID, "'~'");
    // ����ó�ף��������,������˰
    sqlUpdate.processVatUpdate(PUEntity.M25_H_TAB, new VatFieldEnum[] {
      VatFieldEnum.BTRIATRADEFLAG, VatFieldEnum.CSENDCOUNTRYID,
      VatFieldEnum.CTAXCOUNTRYID, VatFieldEnum.CRECECOUNTRYID,
      VatFieldEnum.BOPPTAXFLAG, VatFieldEnum.FBUYSELLFLAG
    }, moreExpress);

    VatFieldEnum[] bodyfieldEnums =
        new VatFieldEnum[] {
          VatFieldEnum.FTAXTYPEFLAG, VatFieldEnum.NNOSUBTAXRATE,
          VatFieldEnum.NNOSUBTAX, VatFieldEnum.NCALCOSTMNY,
          VatFieldEnum.NCALTAXMNY
        };

    VatUpdateProcess bodyPro = new VatUpdateProcess();
    VatUpgradeUtil taxtype = new VatUpgradeUtil();
    bodyPro.setExpressValue(VatFieldEnum.FTAXTYPEFLAG,
        taxtype.getTaxtypeFlagValue());
    bodyPro.setExpressValue(VatFieldEnum.NCALTAXMNY,
        taxtype.getNcaltaxmnyValue());
    // ������˰���
    bodyPro.setExpressValue(VatFieldEnum.NCALCOSTMNY, InvoiceItemVO.NMNY);
    Map<String, String> moreBodyExpress = new HashMap<String, String>();
    moreExpress.put(InvoiceItemVO.NTAXRATE, taxtype.getTaxrateValue());
    moreExpress.put(InvoiceItemVO.CASSCUSTID, "'~'");
    moreExpress.put(InvoiceItemVO.CPROJECTTASKID, "'~'");

    bodyPro.processVatUpdate(PUEntity.M25_B_TAB, bodyfieldEnums,
        moreBodyExpress);

    // ˰������
    sqlUpdate.processTaxCodeUpdate(PUEntity.M25_B_TAB,
        InvoiceItemVO.CTAXCODEID, InvoiceItemVO.PK_MATERIAL);
  }

}
