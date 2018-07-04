package nc.vo.pu.pub.rule.vat;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.itf.pu.vat.IVATInfoQuery;
import nc.itf.pu.vat.VATInfo;
import nc.itf.scmpub.reference.uap.bd.vat.CustSuppVATCodeQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.OppTaxFlagQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.OrgVATCodeQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoVO;
import nc.pubimpl.bdlayer.cache.CacheEnvUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.util.remotecallcombination.RemoteCallCombinatorEx;
import nc.vo.util.remotecallcombination.Token;

import org.apache.commons.lang.ArrayUtils;

/**
 * ��ѯ˰��������˰�����Ϣ������˰���˰�ʡ�������˰��ǡ�VATע����
 * 
 * @since 6.0
 * @version 2012-2-15 ����01:49:35
 * @author wuxla
 */
public class VATCodeTaxValue implements IPURemoteCallCombinator {

  private CustSuppVATCodeQueryVO[] custSuppVATCodeQueryVOs;

  private OppTaxFlagQueryVO[] opptaxqueryvos;

  private OrgVATCodeQueryVO[] orgVATCodeQueryVOs;

  private Token token = null;

  private VATInfo vatinfo;

  private VATInfoQueryVO[] vatqueryVOs;

  public VATCodeTaxValue(VATInfoQueryVO[] vatqueryVOs) {
    this.vatqueryVOs = vatqueryVOs;
  }

  public VATCodeTaxValue(VATInfoQueryVO[] vatqueryVOs,
      OppTaxFlagQueryVO[] opptaxqueryvos) {
    this(vatqueryVOs);
    this.opptaxqueryvos = opptaxqueryvos;
  }

  public VATCodeTaxValue(VATInfoQueryVO[] vatqueryVOs,
      OppTaxFlagQueryVO[] opptaxqueryvos,
      CustSuppVATCodeQueryVO[] custSuppVATCodeQueryVOs,
      OrgVATCodeQueryVO[] orgVATCodeQueryVOs) {
    this(vatqueryVOs, opptaxqueryvos);
    this.custSuppVATCodeQueryVOs = custSuppVATCodeQueryVOs;
    this.orgVATCodeQueryVOs = orgVATCodeQueryVOs;
  }

  /**
   * ��ȡ������˰
   * <p>
   * ʹ�ó�����
   * <ul>
   * <li>
   * </ul>
   * 
   * @return
   */
  public UFBoolean[] getOpptaxflags() {
    if (this.vatinfo != null) {
      return this.vatinfo.getOpptaxflags();
    }
    return null;
  }

  /**
   * ��ȡ��Ӧ��ע����
   * <p>
   * ʹ�ó�����
   * <ul>
   * <li>
   * </ul>
   * 
   * @return
   */
  public String[] getSupVatCodes() {
    if (this.vatinfo != null) {
      return this.vatinfo.getSupvatcodes();
    }
    return null;
  }

  /**
   * ��ȡVATע����
   * <p>
   * ʹ�ó�����
   * <ul>
   * <li>
   * </ul>
   * 
   * @return
   */
  public String[] getVatCodes() {
    if (this.vatinfo != null) {
      return this.vatinfo.getVatcodes();
    }
    return null;
  }

  /**
   * ��ȡ˰��˰����Ϣ
   * <p>
   * ʹ�ó�����
   * <ul>
   * <li>
   * </ul>
   * 
   * @return
   */
  public VATInfoVO[] getVatinfos() {
    if (this.vatinfo != null) {
      return this.vatinfo.getVatinfovos();
    }
    return null;
  }

  @Override
  public void prepare() {
    if (CacheEnvUtil.isRunnigInServer()) {
      return;
    }
    RemoteCallCombinatorEx rcc = RemoteCallCombinatorEx.getInstance();
    if (this.token != null) {
      rcc.update(this.token);
      this.doInvoke(rcc);
    }
    else {
      if (this.doInvoke(rcc)) {
        this.token = rcc.getToken();
      }
    }
  }

  @Override
  public void process() {
    if (null != this.token) {
      try {
        this.vatinfo =
            (VATInfo) RemoteCallCombinatorEx.getInstance()
                .getResult(this.token);
        return;
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }

    IVATInfoQuery service = NCLocator.getInstance().lookup(IVATInfoQuery.class);
    try {
      this.vatinfo =
          service.queryTaxInfoAndOppTaxFlag(this.vatqueryVOs,
              this.opptaxqueryvos, this.custSuppVATCodeQueryVOs,
              this.orgVATCodeQueryVOs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private boolean doInvoke(RemoteCallCombinatorEx rcc) {
    IVATInfoQuery service = rcc.getService(IVATInfoQuery.class);
    try {
      if (ArrayUtils.isEmpty(this.vatqueryVOs)
          && ArrayUtils.isEmpty(this.opptaxqueryvos)
          && ArrayUtils.isEmpty(this.custSuppVATCodeQueryVOs)
          && ArrayUtils.isEmpty(this.opptaxqueryvos)) {
        return false;
      }
      this.vatinfo =
          service.queryTaxInfoAndOppTaxFlag(this.vatqueryVOs,
              this.opptaxqueryvos, this.custSuppVATCodeQueryVOs,
              this.orgVATCodeQueryVOs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return true;

  }
}
