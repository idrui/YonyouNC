package nc.impl.pu.m21.action.rule.maintain;

import java.util.Map;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.vo.pu.m21.entity.ApDataVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.scale.ScaleUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-3-9 ����12:28:55
 * @author wuxla
 */

public class ApDataValueSetter {

  private Map<String, UFDouble> creditboundMap;

  /**
   * ������Ӧ�Ĳɹ���Ʊ��Ӧ��Ӧ�������Ѻ������ҽ��
   */
  private UFDouble nInvoiceVerifyMny;

  /**
   * ��������������������ۼ��Ѻ������Ҹ�����
   */
  private UFDouble nOrderVerifyMny;

  /**
   * �������������ۼ����������������˰����
   */
  private UFDouble nPUOrderOperMny;

  /**
   * �����������������ۼƱ��Ҹ����ܶ�
   */
  private UFDouble nPUOrderPayMny;

  /**
   * �����������ı��Ҽ�˰�ϼ�
   */
  private UFDouble nPUOrdertaxmny;

  /**
   * ������ί�ⶩ�����ۼ����������������˰����
   */
  private UFDouble nSCOperMny;

  /**
   * ������ί�ⶩ���ı��Ҽ�˰�ϼ�
   */
  private UFDouble nSCTaxmny;

  /**
   * �òɹ�������ͷ��Ӧ������Ӧ�������������ݹ�Ӧ������δ������
   */
  private UFDouble nUnPayMny;

  private Map<String, UFDouble[]> orderApDataMap;

  private Map<String, UFDouble> orderPayMnyMap;

  private Map<String, UFDouble> payVerifyMap;

  private String pk_apfinanceorg;

  private ScaleUtils scale = new ScaleUtils(InvocationInfoProxy.getInstance()
      .getGroupId());

  private Map<String, UFDouble[]> scApDataMap;

  private Map<String, UFDouble> unPayMnyMap;

  private Map<String, UFDouble> verifyMnyMap;

  public Map<String, UFDouble> getCreditboundMap() {
    return this.creditboundMap;
  }

  public Map<String, UFDouble[]> getOrderApDataMap() {
    return this.orderApDataMap;
  }

  public Map<String, UFDouble> getOrderPayMnyMap() {
    return this.orderPayMnyMap;
  }

  public Map<String, UFDouble> getPayVerifyMap() {
    return this.payVerifyMap;
  }

  public Map<String, UFDouble[]> getScApDataMap() {
    return this.scApDataMap;
  }

  public Map<String, UFDouble> getUnPayMnyMap() {
    return this.unPayMnyMap;
  }

  public Map<String, UFDouble> getVerifyMnyMap() {
    return this.verifyMnyMap;
  }

  public void setApData(ApDataVO vo) {
    this.init(vo);

    String ccurrencyid = vo.getCcurrencyid();

    // ����Ӧ�������òɹ�������ͷ��Ӧ�̵Ķ���δ����������㷨Ϊ�ù�Ӧ���ڵ�ǰ�����Ĳɹ���֯��Χ�еģ�
    // ���У������������ı��Ҽ�˰�ϼƣ��ö�����Ӧ�Ĳɹ���Ʊ��Ӧ��Ӧ�������Ѻ������ҽ���
    // ���Ʋɹ���ⵥ���ɵķ�Ʊ�������Ʋɹ���Ʊ�γɵ�Ӧ�����Ͳɹ���ⵥȷ��Ӧ���γɵ�Ӧ�������Լ�����Ӧ�������Ҽ�˰�ϼƲ������붩��Ӧ����
    UFDouble nOrderApMny = MathTool.add(this.nPUOrdertaxmny, this.nSCTaxmny);
    nOrderApMny = MathTool.sub(nOrderApMny, this.nInvoiceVerifyMny);
    nOrderApMny = this.scale.adjustMnyScale(nOrderApMny, ccurrencyid);
    vo.setNorderap(nOrderApMny);
    // ҵ��Ӧ�����òɹ�������ͷ��Ӧ�̵����ж�������������γɵ�δ����������㷨Ϊ�ù�Ӧ���ڵ�ǰ�����Ĳɹ���֯��Χ�еģ�
    // ���У��������������ۼ����������������˰���ۣ��ö�����Ӧ�Ĳɹ���Ʊ��Ӧ��Ӧ�������Ѻ������ҽ���
    // ���Ʋɹ���Ʊ�γɵ�Ӧ���Լ�����Ӧ�������Ҽ�˰�ϼƲ�������ҵ��Ӧ����
    UFDouble nOperMny = MathTool.add(this.nPUOrderOperMny, this.nSCOperMny);
    nOperMny = MathTool.sub(nOperMny, this.nInvoiceVerifyMny);
    nOperMny = this.scale.adjustMnyScale(nOperMny, ccurrencyid);
    vo.setNoperationap(nOperMny);
    // ����Ӧ�����òɹ�������ͷ��Ӧ������Ӧ�������������ݹ�Ӧ������δ�����
    // �����㷨Ϊ�òɹ���������Ӧ����֯��Χ�ڵĸù�Ӧ�̵�Ӧ�����ı��Ҵ�����
    // ���Ʋɹ���ⵥ���ɵķ�Ʊ�������Ʋɹ���Ʊ�Լ�����Ӧ�������Ҽ�˰�ϼ�Ҫ�������Ӧ����
    this.nUnPayMny = this.scale.adjustMnyScale(this.nUnPayMny, ccurrencyid);
    vo.setNfinanceap(this.nUnPayMny);
    // ��������òɹ�������ͷ��Ӧ�̵����ж����Ѹ����
    // �����㷨Ϊ�ù�Ӧ���ڵ�ǰ�����Ĳɹ���֯��Χ�еģ�����������������ͷ���ۼƱ��Ҹ����ܶ
    this.nPUOrderPayMny =
        this.scale.adjustMnyScale(this.nPUOrderPayMny, ccurrencyid);
    vo.setNorderpaymny(this.nPUOrderPayMny);

    // ����δ�������ù�Ӧ�̵����ж����Ѹ���δ������
    // �����㷨Ϊ�ù�Ӧ���ڵ�ǰ�����Ĳɹ���֯��Χ�еģ�������ͷ�ۼƱ��Ҹ����ܶ��������������������ۼ��Ѻ������Ҹ����
    UFDouble nunverifymny =
        MathTool.sub(this.nPUOrderPayMny, this.nOrderVerifyMny);
    nunverifymny = this.scale.adjustMnyScale(nunverifymny, ccurrencyid);
    vo.setNunverifymny(nunverifymny);

    // ��Ӧ�����ã�ȡ�òɹ�������ͷ�ڹ�Ӧ�̵���������֯ҳǩ�����ö�ȡ�
    UFDouble ncreditbound = this.creditboundMap.get(this.pk_apfinanceorg);
    vo.setNcreditbound(ncreditbound);
  }

  public void setCreditboundMap(Map<String, UFDouble> creditboundMap) {
    this.creditboundMap = creditboundMap;
  }

  public void setOrderApDataMap(Map<String, UFDouble[]> orderApDataMap) {
    this.orderApDataMap = orderApDataMap;
  }

  public void setOrderPayMnyMap(Map<String, UFDouble> orderPayMnyMap) {
    this.orderPayMnyMap = orderPayMnyMap;
  }

  public void setPayVerifyMap(Map<String, UFDouble> payVerifyMap) {
    this.payVerifyMap = payVerifyMap;
  }

  public void setScApDataMap(Map<String, UFDouble[]> scApDataMap) {
    this.scApDataMap = scApDataMap;
  }

  public void setUnPayMnyMap(Map<String, UFDouble> unPayMnyMap) {
    this.unPayMnyMap = unPayMnyMap;
  }

  public void setVerifyMnyMap(Map<String, UFDouble> verifyMnyMap) {
    this.verifyMnyMap = verifyMnyMap;
  }

  private void init(ApDataVO vo) {
    this.pk_apfinanceorg = vo.getPk_apfinanceorg();
    this.initOrderApData();
    this.initSCApData();
    this.initOrderPayData();
    this.initInvoiceVerifyData();
    this.initUnPayMny();
    this.initOrderVerifyMny();
  }

  private void initInvoiceVerifyData() {
    if (null == this.verifyMnyMap) {
      return;
    }
    this.nInvoiceVerifyMny = this.verifyMnyMap.get(this.pk_apfinanceorg);
  }

  private void initOrderApData() {
    if (null == this.orderApDataMap) {
      return;
    }
    UFDouble[] orderApData = this.orderApDataMap.get(this.pk_apfinanceorg);
    if (ArrayUtils.isEmpty(orderApData)) {
      return;
    }
    this.nPUOrdertaxmny = orderApData[0];
    this.nPUOrderOperMny = orderApData[1];

    // this.nOrderVerifyMny = orderApData[2];
  }

  private void initOrderPayData() {
    if (null == this.orderPayMnyMap) {
      return;
    }
    this.nPUOrderPayMny = this.orderPayMnyMap.get(this.pk_apfinanceorg);
  }

  private void initOrderVerifyMny() {
    if (null == this.payVerifyMap) {
      return;
    }
    this.nOrderVerifyMny = this.payVerifyMap.get(this.pk_apfinanceorg);
  }

  private void initSCApData() {
    if (null == this.scApDataMap) {
      return;
    }

    UFDouble[] scApData = this.scApDataMap.get(this.pk_apfinanceorg);
    if (ArrayUtils.isEmpty(scApData)) {
      return;
    }

    this.nSCTaxmny = scApData[0];
    this.nSCOperMny = scApData[1];
  }

  private void initUnPayMny() {
    if (null == this.unPayMnyMap) {
      this.nUnPayMny = UFDouble.ZERO_DBL;
      return;
    }
    this.nUnPayMny = MathTool.nvl(this.unPayMnyMap.get(this.pk_apfinanceorg));
  }

}
