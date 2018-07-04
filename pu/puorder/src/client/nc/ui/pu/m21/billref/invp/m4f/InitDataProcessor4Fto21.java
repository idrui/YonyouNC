package nc.ui.pu.m21.billref.invp.m4f;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.funcnode.ui.FuncletInitData;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.ui.pubapp.billref.dest.TransferViewProcessor;
import nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener.IInitDataProcessor;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.CurrencyAndExchangerate;
import nc.vo.pu.m21.rule.vat.OrderVatInfoSrcFillRule;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.scmpub.enumeration.EnumDiscounttaxtype;

import org.apache.commons.lang.StringUtils;

/**
 * ���ƻ��ƻ��������ɹ������򿪽��洦��
 * 
 * @since 6.0
 * @version 2011-12-8 ����04:16:08
 * @author �����
 */

public class InitDataProcessor4Fto21 implements IInitDataProcessor {

  private TransferViewProcessor transferProcessor;

  public TransferViewProcessor getTransferProcessor() {
    return this.transferProcessor;
  }

  @Override
  public void process(FuncletInitData data) {
    this.getTransferProcessor().getBillForm().getModel().initModel(null);
    OrderVO[] orderVOs = (OrderVO[]) data.getInitData();
    // ��ȫ��λ���۵�λ�������ȣ��ƻ���������ʱ��ЩĬ�ϲ�����vo����
    this.setDefaultNumerInfo(orderVOs);
    // vat����
    new OrderVatInfoSrcFillRule().process(orderVOs);
    // �������
    this.fillCurrenyInfo(orderVOs);

    this.getTransferProcessor().processBillTransfer(orderVOs);
  }

  public void setTransferProcessor(TransferViewProcessor transferProcessor) {
    this.transferProcessor = transferProcessor;
  }

  /**
   * ���������Ϣ
   * 
   * @param orderVOs
   */
  private void fillCurrenyInfo(OrderVO[] orderVOs) {
    this.fillMainOrgCurrenyInfo(orderVOs);
    for (OrderVO vo : orderVOs) {
      BillHelper<OrderVO> bill = new BillHelper<OrderVO>(vo);
      new CurrencyAndExchangerate(bill).setCurrencyAndExchangeRate();
    }
  }

  /**
   * ��������֯������Ϣ
   * 
   * @param orderVOs
   */
  private void fillMainOrgCurrenyInfo(OrderVO[] orderVOs) {
    Set<String> orgs = new HashSet<String>();
    for (OrderVO vo : orderVOs) {
      orgs.add(vo.getHVO().getPk_org());
    }
    if (orgs.size() == 0) {
      return;
    }
    Map<String, String> orgCurry =
        OrgUnitPubService
            .queryOrgCurrByPk(orgs.toArray(new String[orgs.size()]));
    for (OrderVO vo : orderVOs) {
      String currency = orgCurry.get(vo.getHVO().getPk_org());
      vo.getHVO().setCorigcurrencyid(currency);
    }
  }

  private void setDefaultNumerInfo(OrderVO[] orderVOs) {
    String changeRate = "1.0000/1.0000";
    for (OrderVO vo : orderVOs) {
      for (OrderItemVO item : vo.getBVO()) {
        if (StringUtils.isEmpty(item.getCastunitid())) {
          item.setCastunitid(item.getCunitid());
          item.setNastnum(item.getNnum());
          item.setVchangerate(changeRate);
          item.setVqtunitrate(changeRate);
          item.setCqtunitid(item.getCunitid());
          item.setNqtunitnum(item.getNnum());
        }
        if (item.getFtaxtypeflag() == null) {
          item.setFtaxtypeflag(Integer.valueOf(EnumDiscounttaxtype.TAXOUT
              .toIntValue()));
        }
      }
    }
  }

}
