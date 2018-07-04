package nc.vo.pu.pub.rule.vat;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.itf.pu.vat.IVATInfoQuery;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoByTaxcodeQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoVO;
import nc.pubimpl.bdlayer.cache.CacheEnvUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.util.remotecallcombination.RemoteCallCombinatorEx;
import nc.vo.util.remotecallcombination.Token;

import org.apache.commons.lang.ArrayUtils;

/**
 * 税码编辑后取税率，主要是根据税码和日期取税率
 * 
 * @since 6.0
 * @version 2012-2-24 下午04:45:22
 * @author wuxla
 */
public class TaxValueByTaxCode implements IPURemoteCallCombinator {
  private VATInfoByTaxcodeQueryVO[] queryVOs;

  private Token token = null;

  private VATInfoVO[] vatinfos;

  public TaxValueByTaxCode(VATInfoByTaxcodeQueryVO[] queryVOs) {
    this.queryVOs = queryVOs;
  }

  public VATInfoVO[] getVatinfos() {
    return this.vatinfos;
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
        this.vatinfos =
            (VATInfoVO[]) RemoteCallCombinatorEx.getInstance().getResult(
                this.token);
        return;
      }
      catch (BusinessException e) {
        ExceptionUtils.wrappException(e);
      }
    }

    IVATInfoQuery service = NCLocator.getInstance().lookup(IVATInfoQuery.class);
    try {
      this.vatinfos = service.queryTaxInfoByTaxcode(this.queryVOs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private boolean doInvoke(RemoteCallCombinatorEx rcc) {
    IVATInfoQuery service = rcc.getService(IVATInfoQuery.class);
    try {
      if (ArrayUtils.isEmpty(this.queryVOs)) {
        return false;
      }

      this.vatinfos = service.queryTaxInfoByTaxcode(this.queryVOs);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return true;
  }

}
