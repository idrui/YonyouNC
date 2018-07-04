package nc.vo.pu.m25.vochange;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.itf.org.IOrgConst;
import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.pub.InvoiceVOUtil;
import nc.vo.pu.m25.pub.VORelationCalculate;
import nc.vo.pu.m25.rule.maintain.InvoiceVatDefaultValueFillRule;
import nc.vo.pu.m25.rule.maintain.InvoiceVatDefaultValueFillRule.ICountrySetter;
import nc.vo.pu.m25.vochange.processor.InvoiceExchangeProcessor;
import nc.vo.pu.m25.vochange.processor.VMIInvoiceCountrySetter;
import nc.vo.pu.pub.enumeration.PriceSource;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * vo����������������ǰ�ͽ�����Ĵ���
 * 
 * @since 6.0
 * @version 2011-4-18 ����05:10:42
 * @author �����
 */

public class ChangeVOAdjustor50To25 extends InvoiceChangeVOAdjustor {

  /**
   * ��Դ������Ϣ����
   * 
   * @param vos
   */
  private void setDefaultSourceInfo(InvoiceVO[] vos) {
    for (InvoiceVO vo : vos) {
      for (InvoiceItemVO item : vo.getChildrenVO()) {
        // ��λ������
        if (item.getCastunitid() == null
            || MathTool.equals(item.getNastnum(), UFDouble.ZERO_DBL)) {
          item.setCastunitid(item.getCunitid());
          item.setVchangerate("1.0000/1.0000");
          item.setNastnum(item.getNnum());
        }

      }
    }
  }

  /**
   * Ĭ��ֵ����
   */
  private void setDefaultValue(InvoiceVO[] vos) {
    this.setPurchaseOrg(vos);
    this.setDefaultSourceInfo(vos);

  }

  /**
   * @param vos
   */
  private void setPurchaseOrg(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    Set<String> orgSet = new HashSet<String>();
    for (InvoiceVO vo : vos) {
      if (vo == null || vo.getParentVO() == null) {
        continue;
      }
      if (orgSet.contains(vo.getParentVO().getPk_org())) {
        vo.getParentVO().setPk_purchaseorg(vo.getParentVO().getPk_org());
        vo.getParentVO().setPk_purchaseorg_v(vo.getParentVO().getPk_org_v());
      }
      else {
        // ���òɹ���֯
        if (OrgUnitPubService.isTypeOf(vo.getParentVO().getPk_org(),
            IOrgConst.PURCHASEORGTYPE)) {
          vo.getParentVO().setPk_purchaseorg(vo.getParentVO().getPk_org());
          vo.getParentVO().setPk_purchaseorg_v(vo.getParentVO().getPk_org_v());
          orgSet.add(vo.getParentVO().getPk_org());
        }
      }
    }
  }

  @Override
  protected InvoiceVO[] doDefaultAfterChange(InvoiceVO[] retvos) {
    // Ĭ��ֵ
    this.setDefaultValue(retvos);
    // ����ҵ������
    this.setBusiDate(retvos);
    // �����������½��м��㣬���ת����������ͬ
    InvoiceVOUtil.reCalculateBasedNum(retvos);
    // ���÷�Ʊ��VAT��Ϣ������˰�ȣ�
    this.setDefaultVatInfo(retvos);
    // ���ݶ���δ�ҵ����λ�Ļ���Ĭ��ȡ��Ӧ�̵�ֵ
    this.setPayUnit(retvos);
    // ��������б仯������ȡ
    new InvoiceExchangeProcessor().resetExchangeByQuery(retvos);
    // �ƻ���
    this.setPlanPrice(retvos);
    // ѯ��
    this.queryPriceForVOs(retvos);
    // add by liangchen1 NC631����������ͨ�ɹ�������ڲɹ�
    this.setInvoiceType(retvos);
    return retvos;
  }
  
  @Override
  protected void queryPriceForVOs(InvoiceVO[] retvos) {
    String fiorg = null;
    for (InvoiceVO vo : retvos) {
      fiorg = vo.getParentVO().getPk_org();
      if (fiorg != null) {
        break;
      }
    }
    PriceSource[] PO83 = PUSysParamUtil.getPO83(fiorg);
    // ��������ѯ�����ۻ���ɹ���ⵥ�ۣ�����ȥѯ�ۣ�ȡ���Ļ��ܵļ۸�
    if (PriceSource.OrderPice == PO83[0] || 
        PriceSource.PurchaseInPrice == PO83[0]) {
      return;
    }
    super.queryPriceForVOs(retvos);
  }

  @Override
  protected void setDefaultVatInfo(InvoiceVO[] retvos) {
    IKeyValue[] bills = InvoiceVOUtil.getBillHelper(retvos);
    List<ICountrySetter> csetterLst =
        new ArrayList<InvoiceVatDefaultValueFillRule.ICountrySetter>();
    csetterLst.add(new VMIInvoiceCountrySetter());
    IPURemoteCallCombinator rccrule =
        new InvoiceVatDefaultValueFillRule(bills, csetterLst);
    rccrule.prepare();
    rccrule.process();
    // ����˰���۷���һ���������㣬��֤�۸񲻱�
    VORelationCalculate calculate = new VORelationCalculate();
    for (int i = 0; i < retvos.length; i++) {
      InvoiceHeaderVO header = retvos[i].getParentVO();
      InvoiceItemVO[] items = retvos[i].getChildrenVO();
      for (InvoiceItemVO item : items) {
        // �ı�˰�����¼���
        calculate.calculate(header, item, InvoiceItemVO.NPRICE);
      }
    }

  }
}
