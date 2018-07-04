package nc.impl.pu.vat;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.itf.pu.vat.IVATInfoQuery;
import nc.itf.pu.vat.VATInfo;
import nc.itf.scmpub.reference.uap.bd.supplier.SupplierPubService;
import nc.itf.scmpub.reference.uap.bd.vat.BuySellFlagEnum;
import nc.itf.scmpub.reference.uap.bd.vat.CustSuppVATCodeQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.OppTaxFlagQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.OrgVATCodeQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.VATBDService;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoByTaxcodeQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoQueryVO;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoVO;
import nc.itf.scmpub.reference.uap.bd.vat.ZeroTaxCodeEnum;
import nc.itf.scmpub.reference.uap.org.FinanceOrgPubService;
import nc.vo.bd.supplier.SupplierVO;
import nc.vo.pu.pub.enumeration.EnumDiscounttaxtype;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.StringUtils;

/**
 * VAT查询实现类
 * 
 * @since 6.0
 * @version 2012-2-15 上午09:29:37
 * @author wuxla
 */
public class VATInfoQueryImpl implements IVATInfoQuery {

  @Override
  public VATInfoVO[] queryTaxInfo(VATInfoQueryVO[] queryVOs)
      throws BusinessException {
    try {
      VATInfoVO[] infovos = VATBDService.querySupplierVATInfo(queryVOs);
      this.setZeroTaxCode(infovos, queryVOs);
      return infovos;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public VATInfo queryTaxInfoAndOppTaxFlag(VATInfoQueryVO[] vatqueryVOs,
      OppTaxFlagQueryVO[] opptaxqueryvos,
      CustSuppVATCodeQueryVO[] custSuppVATCodeQueryVOs,
      OrgVATCodeQueryVO[] orgVATCodeQueryVOs) throws BusinessException {
    try {
      VATInfo info = new VATInfo();
      if (null != vatqueryVOs) {
        VATInfoVO[] vatinfovos = VATBDService.querySupplierVATInfo(vatqueryVOs);
        this.setZeroTaxCode(vatinfovos, vatqueryVOs);
        info.setVatinfovos(vatinfovos);
      }
      if (null != opptaxqueryvos) {
        UFBoolean[] opptaxflags = VATBDService.queryOppTaxFlag(opptaxqueryvos);
        info.setOpptaxflags(opptaxflags);
      }
      if (null != custSuppVATCodeQueryVOs) {
        String[] supvatcodes =
            VATBDService.querySupplierVATCode(custSuppVATCodeQueryVOs);
        info.setSupvatcodes(supvatcodes);
      }
      if (null != orgVATCodeQueryVOs) {
        String[] vatcodes = VATBDService.queryOrgVATCode(orgVATCodeQueryVOs);
        info.setVatcodes(vatcodes);
      }
      return info;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public VATInfoVO[] queryTaxInfoByTaxcode(VATInfoByTaxcodeQueryVO[] queryVOs)
      throws BusinessException {
    try {
      return VATBDService.queryVATInfo(queryVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  private Map<String, String> getSupplierMap(String[] pk_suppliers) {
    SupplierVO[] supvos =
        SupplierPubService.getSupplierVO(pk_suppliers, new String[] {
          SupplierVO.PK_SUPPLIER, SupplierVO.SUPPROP, SupplierVO.PK_FINANCEORG
        });
    Map<String, String> supMap = new HashMap<String, String>();
    for (SupplierVO supvo : supvos) {
      if (supvo.getSupprop() != null && supvo.getSupprop().intValue() == 1) {
        supMap.put(supvo.getPk_supplier(), supvo.getPk_financeorg());
      }
    }
    return supMap;
  }

  private void setZeroTaxcode(VATInfoVO[] infovos, VATInfoQueryVO[] queryVOs,
      Map<String, String> supMap, Map<String, String> financeorgMap) {
    for (int i = 0; i < queryVOs.length; ++i) {
      if (queryVOs[i] == null) {
        continue;
      }
      BuySellFlagEnum buysell = queryVOs[i].getFbuysellfalg();
      if (BuySellFlagEnum.NATIONAL_BUY == buysell
          && supMap.containsKey(queryVOs[i].getPk_custsupp())
          && queryVOs[i].getPk_financeorg() != null) {
        String supCorp =
            financeorgMap.get(supMap.get(queryVOs[i].getPk_custsupp()));
        String financeorgCorp =
            financeorgMap.get(queryVOs[i].getPk_financeorg());
        if (StringUtils.equals(supCorp, financeorgCorp)) {
          infovos[i] =
              new VATInfoVO(ZeroTaxCodeEnum.ZEROTAXCODE.getCode(),
                  EnumDiscounttaxtype.TAXOUT.toInteger(), UFDouble.ZERO_DBL,
                  UFDouble.ZERO_DBL);
        }
      }
    }
  }

  /**
   * 当购销类型=国内采购、且供应商为内部供应商、供应商对应的财务组织所属公司=结算财务组织所属公司时，直接取得平台内置的0税率税码；
   * <p>
   * 使用场景：
   * <ul>
   * <li>
   * </ul>
   * 
   * @param infovos
   * @param queryVOs
   */
  private void setZeroTaxCode(VATInfoVO[] infovos, VATInfoQueryVO[] queryVOs) {
    if (null == queryVOs) {
      return;
    }
    Set<String> supSet = new HashSet<String>();
    Set<String> financeorgSet = new HashSet<String>();
    for (int i = 0; i < queryVOs.length; ++i) {
      if (null == queryVOs[i]) {
        continue;
      }
      BuySellFlagEnum buysell = queryVOs[i].getFbuysellfalg();
      if (BuySellFlagEnum.NATIONAL_BUY == buysell) {
        if (queryVOs[i].getPk_custsupp() != null) {
          supSet.add(queryVOs[i].getPk_custsupp());
        }
        if (queryVOs[i].getPk_financeorg() != null) {
          financeorgSet.add(queryVOs[i].getPk_financeorg());
        }
      }
    }
    if (0 == supSet.size() || 0 == financeorgSet.size()) {
      return;
    }

    String[] pk_suppliers = supSet.toArray(new String[supSet.size()]);
    Map<String, String> supMap = this.getSupplierMap(pk_suppliers);
    if (supMap == null || 0 == supMap.size()) {
      return;
    }

    financeorgSet.addAll(supMap.values());
    String[] financeorgs =
        financeorgSet.toArray(new String[financeorgSet.size()]);
    Map<String, String> financeorgMap =
        FinanceOrgPubService.queryPKCorp(financeorgs);

    this.setZeroTaxcode(infovos, queryVOs, supMap, financeorgMap);
  }
}
