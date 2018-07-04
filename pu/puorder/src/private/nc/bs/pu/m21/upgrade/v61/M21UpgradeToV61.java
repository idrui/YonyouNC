package nc.bs.pu.m21.upgrade.v61;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.pu.m21.alert.PriceCalculateTaskReg;
import nc.bs.pu.m21.maintain.rule.save.OrderDirectPurchaseUpdateRule;
import nc.bs.pu.m21.upgrade.v61.rule.OrderVoStatusUpdateRule;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.scmmm.upgrade.scmpub.BillPageUpgrade;
import nc.scmmm.upgrade.scmpub.VODefaultValueUpgrade;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.rule.upgrade.TaxtypeFlagUpgradeRule;
import nc.vo.pu.pub.rule.upgrade.VATValueUpgrade;
import nc.vo.pubapp.AppContext;
import nc.vo.scmpub.vatupdate.VatFieldEnum;
import nc.vo.scmpub.vatupdate.VatUpdateProcess;

/**
 * �ɹ�����v61���������
 * 
 * @since 6.0
 * @version 2012-2-29 ����04:44:26
 * @author tianft
 */
public class M21UpgradeToV61 {

  public void doAfterUpdateDataFrom60To61() {
    // ��������
    this.doDataUpgrade();
    // �ɹ�����ѯ��Ԥ����������
    this.RegPriceCalculateTask();
  }

  /**
   * ��������
   */
  private void doDataUpgrade() {
    // ����vat�����������ң�˰�룬�������͵�
    this.upgradeDataByVatProcessor();
    // vo���ķ�ҳ��������
    this.upgradeDataByBillPage();
    // ����ͷĬ��Ϊnull�Ĳ�������Ϊ~���������upgradeDataByVatProcessor����
    this.updateHeadNull();
  }

  private void RegPriceCalculateTask() {

    // ʹ�ó������������ɹ�ģ���ʱ��ע��ѯ����ص�Ԥ����Ϣ����Ϊ��Щϵͳ��6.1֮ǰ�ģ����ɹ�ѯ�۵��㷨��6.1�ų�����
    // ����Ҫ������Щ��ϵͳ֧�ֲɹ�ѯ�۵����㷨������Ҫ���������֧��
    PriceCalculateTaskReg priceCalculateTaskReg =
        new PriceCalculateTaskReg(AppContext.getInstance().getPkGroup());

    priceCalculateTaskReg.regMonthlyAlert();
    priceCalculateTaskReg.regDailyAlert();
  }

  /**
   * ����ͷĬ��Ϊnull�Ĳ�������Ϊ~
   * <p>
   * ʹ�ó�����
   * <ul>
   * <li>
   * </ul>
   */
  private void updateHeadNull() {
    String[] arrs = new String[] {
      OrderHeaderVO.CTRADEWORDID
    };
    new VODefaultValueUpgrade<OrderHeaderVO>(OrderHeaderVO.class).upValue(arrs);
  }

  /**
   * vo���ķ�ҳ��������
   */
  private void upgradeDataByBillPage() {
    BillPageUpgrade<OrderVO> billUpgrade =
        new BillPageUpgrade<OrderVO>(OrderVO.class);
    List<IRule<OrderVO>> rules = new ArrayList<IRule<OrderVO>>();
    // ��˰�������
    rules.add(new TaxtypeFlagUpgradeRule<OrderVO>(OrderHeaderVO.FHTAXTYPEFLAG,
        OrderHeaderVO.NHTAXRATE));
    // VAT���˰������
    rules.add(new VATValueUpgrade<OrderVO>());
    // 2. ֱ������
    rules.add(new OrderDirectPurchaseUpdateRule());
    // 3.����vo״̬����
    rules.add(new OrderVoStatusUpdateRule());
    billUpgrade.doUpgrade(null, rules);
  }

  /**
   * ����vat�����������ң�˰�룬�������͵�
   */
  private void upgradeDataByVatProcessor() {
    VatUpdateProcess sqlUpdate = new VatUpdateProcess();
    // ��������-���ڲɹ�
    sqlUpdate.setExpressValue(VatFieldEnum.FBUYSELLFLAG,
        BuySellFlagEnum.NATIONAL_BUY.value().toString());
    Map<String, String> moreExpress = new HashMap<String, String>();
    // �壺��Ҫ���³�~
    // corigcountryidԭ����
    // corigareaidԭ������
    // cdesticountryidĿ�ĵع�
    // cdestiareaidĿ�ĵص���
    // casscustid�ͻ�
    // cprojecttaskid��Ŀ����
    moreExpress.put(OrderItemVO.CORIGCOUNTRYID, "'~'");
    moreExpress.put(OrderItemVO.CORIGAREAID, "'~'");
    moreExpress.put(OrderItemVO.CDESTICOUNTRYID, "'~'");
    moreExpress.put(OrderItemVO.CDESTIAREAID, "'~'");
    moreExpress.put(OrderItemVO.CASSCUSTID, "'~'");
    moreExpress.put(OrderItemVO.CPROJECTTASKID, "'~'");

    // ����ó�ף��������
    sqlUpdate.processVatUpdate(PUEntity.M21_B_TABLE, new VatFieldEnum[] {
      VatFieldEnum.BTRIATRADEFLAG, VatFieldEnum.CSENDCOUNTRYID,
      VatFieldEnum.CTAXCOUNTRYID, VatFieldEnum.CRECECOUNTRYID,
      VatFieldEnum.FBUYSELLFLAG
    }, moreExpress);
    // ˰������
    sqlUpdate.processTaxCodeUpdate(PUEntity.M21_B_TABLE,
        OrderItemVO.CTAXCODEID, OrderItemVO.PK_MATERIAL);
  }
}
