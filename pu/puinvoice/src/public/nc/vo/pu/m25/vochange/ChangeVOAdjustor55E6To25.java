package nc.vo.pu.m25.vochange;

import java.util.ArrayList;
import java.util.List;

import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m25.entity.InvoiceHeaderVO;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.margin.InvoiceMuiltyWordMarginJudgement;
import nc.vo.pu.m25.vochange.processor.InvoiceExchangeProcessor;
import nc.vo.pu.pub.util.PUSysParamUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.calculator.CalculatorUtil;
import nc.vo.pubapp.margin.BillMarginContext;
import nc.vo.pubapp.margin.MarginContextFactory;
import nc.vo.pubapp.margin.MarginEntry;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.billtype.MMBillType;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.StringUtils;

/**
 * ��������ί��
 * 
 * @since 6.5
 * @version 2014-3-24 ����04:47:30
 * @author mengjian
 */
public class ChangeVOAdjustor55E6To25 extends InvoiceChangeVOAdjustor {
  /**
   * ����֮�������һ�¼Ƴɱ�����Ծ�����֤��������һ�µ�
   * 
   * @param vos
   */
  private void adjustCalCostMny(InvoiceVO[] vos) {
    for (InvoiceVO vo : vos) {
      for (InvoiceItemVO item : vo.getChildrenVO()) {
        // �Ƴɱ����=������˰���+���ɵֿ�˰��ӷ�����Ӱ�쾫�ȣ�
        UFDouble calcostMny =
            CalculatorUtil.add(item.getNmny(), item.getNnosubtax());
        item.setNcalcostmny(calcostMny);
      }
    }
  }

  private InvoiceVO filterByOrder(InvoiceVO vo) {
    List<InvoiceItemVO> items = new ArrayList<InvoiceItemVO>();
    for (InvoiceItemVO item : vo.getChildrenVO()) {
      if (StringUtils.isNotBlank(item.getPk_order_b())) {
        items.add(item);
      }
    }
    if (items.size() == 0) {
      return null;
    }
    InvoiceVO newVO = new InvoiceVO();
    newVO.setParent(vo.getParentVO());
    newVO.setChildrenVO(items.toArray(new InvoiceItemVO[items.size()]));

    return newVO;
  }

  /**
   * ��������
   * 
   * @param vos
   */
  private void processMargin(InvoiceVO[] vos) {
    List<InvoiceVO> noTaxPriorVOs = new ArrayList<InvoiceVO>();
    List<InvoiceVO> taxPriorVOs = new ArrayList<InvoiceVO>();
    for (InvoiceVO vo : vos) {
      InvoiceVO filterVO = this.filterByOrder(vo);
      if (filterVO == null) {
        continue;
      }
      String pk_org = filterVO.getParentVO().getPk_purchaseorg();
      if (StringUtils.isBlank(pk_org)) {
        noTaxPriorVOs.add(filterVO);
      }
      else {
        if (PUSysParamUtil.getPO28For25(pk_org)) {
          taxPriorVOs.add(filterVO);
        }
        else {
          noTaxPriorVOs.add(filterVO);
        }
      }
    }
    // ��˰����
    if (noTaxPriorVOs.size() > 0) {
      this.processMargin(
          noTaxPriorVOs.toArray(new InvoiceVO[noTaxPriorVOs.size()]), false);
    }
    // ��˰����
    if (taxPriorVOs.size() > 0) {
      this.processMargin(
          taxPriorVOs.toArray(new InvoiceVO[taxPriorVOs.size()]), true);
    }
  }

  /**
   * ���ݺ�˰���Ȼ�����˰���ȴ���β�������
   * 
   * @param vos
   * @param taxPrior true-��˰���ȣ�false-��˰����
   */
  private void processMargin(InvoiceVO[] vos, boolean taxPrior) {
    // ֻ�����Ƿ�Դͷ�Ƕ����ж��ǹ����У����Ҳ����κ��ж�
    BillMarginContext context;
    String[] compareFields = null;
    // ���Һ�ԭ����صĽ�Ҫ����
    String[] mnyFields =
        new String[] {
          InvoiceItemVO.NORIGTAXMNY, InvoiceItemVO.NORIGMNY,
          InvoiceItemVO.NMNY, InvoiceItemVO.NTAXMNY, InvoiceItemVO.NTAX
        // 2012-8-17 wuxla��tianft��suncong
        // ��Ҫ����˰�������˰����˰�ϼơ�˰���ϵ�����
        // 2012-8-19 ��ϡ���ӡ�ҡ�������������Ρ���С���������ǳɱ����
        // InvoiceItemVO.NCALCOSTMNY, InvoiceItemVO.NCALTAXMNY
        };
    // ��˰����
    if (taxPrior) {
      compareFields = new String[] {
        InvoiceHeaderVO.CORIGCURRENCYID, InvoiceItemVO.NORIGTAXPRICE,
        // ����ֻ�ȽϷ��ţ����ȽϾ���ֵ
        InvoiceItemVO.NNUM
      };
    }
    // ��˰����
    else {
      compareFields = new String[] {
        InvoiceHeaderVO.CORIGCURRENCYID, InvoiceItemVO.NORIGPRICE,
        // ����ֻ�ȽϷ��ţ����ȽϾ���ֵ
        InvoiceItemVO.NNUM
      };
    }

    try {
      context =
          MarginContextFactory.getInstance().produceMarginContext(
              vos,
              MMBillType.PscSettle.getCode(),
              OrderItemVO.NNUM,
              mnyFields,
              POBillType.Invoice.getCode(),
              InvoiceItemVO.NNUM,
              mnyFields,
              InvoiceItemVO.CSOURCEBID,
              InvoiceItemVO.CSOURCEID,
              new InvoiceMuiltyWordMarginJudgement(compareFields,
                  compareFields, vos));
      MarginEntry.getInstance().process(context);
      // ����֮�������һ�¼Ƴɱ�����Ծ�����֤��������һ�µ�
      this.adjustCalCostMny(vos);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * ת�����Ĭ�ϴ���������������Ը�д�˷���
   * 
   * @param resultVOs
   * @return
   */
  @Override
  protected InvoiceVO[] doDefaultAfterChange(InvoiceVO[] resultVOs) {
    // ����ҵ������
    this.setBusiDate(resultVOs);
    // ת��ʱ����������
    this.reCalculateFromSource(resultVOs);
    // ��������,���ݲɹ�����������Ҫ��֤�����һ���������ұ��ֺͼ۸��붩����ͬ
    this.processMargin(resultVOs);
    // ��������б仯������ȡ
    new InvoiceExchangeProcessor().resetExchangeByQuery(resultVOs);
    // �����Դ����Դͷ�вɹ���������Ӧ�̺͸��λȡ�����ϵ���Ϣ
    this.getInvoiceOrderInfoProcessor().fillDataByOrder(resultVOs);
    // ���ݶ���δ�ҵ����λ�Ļ���Ĭ��ȡ��Ӧ�̵�ֵ
    this.setPayUnit(resultVOs);
    // �ƻ���
    this.setPlanPrice(resultVOs);
    // ѯ�۴���
    this.queryPriceForVOs(resultVOs);
    // ����VAT�����Ϣ
    this.setDefaultVatInfo(resultVOs);
    // ���������˻����˺���Ϣ��
    this.setBankAccInfo(resultVOs);

    // ����ҵ������ ���ڼӹ��ѽ��㵥�Ƿ�ҵ�����̣���������ʱ�޷������δ���
    PfServiceScmUtil.setBusiType(resultVOs, POBillType.Invoice.getCode());
    
    // add by liangchen1 NC631����������ͨ�ɹ�������ڲɹ�
    this.setInvoiceType(resultVOs);
    return resultVOs;
  }
}
