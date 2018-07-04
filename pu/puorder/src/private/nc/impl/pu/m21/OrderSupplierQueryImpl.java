package nc.impl.pu.m21;

import java.util.List;
import java.util.Map;

import nc.itf.pu.m21.IOrderSupplierQuery;
import nc.itf.scmpub.reference.uap.bd.psn.PsndocPubService;
import nc.itf.scmpub.reference.uap.bd.supplier.SupplierPubService;
import nc.itf.scmpub.reference.uap.org.DeptPubService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.ui.pu.m21transtype.PoTransTypeService;
import nc.vo.bd.supplier.SupplierVO;
import nc.vo.bd.supplier.stock.SupStockVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.query.supplier.SupplierInfo;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>供应商信息的查询实现类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-3-27 下午08:47:13
 */
public class OrderSupplierQueryImpl implements IOrderSupplierQuery {

  @Override
  public SupplierInfo querySupplier(String pk_supplier, String pk_purchaseorg)
      throws BusinessException {
    try {
      // 默认发货地址
      String address =
          SupplierPubService.getDefaultConsignAddress(pk_supplier,
              pk_purchaseorg);
      // 组织信息，为了获取散户标识
      SupplierVO[] supplier = SupplierPubService.getSupplierVO(new String[] {
        pk_supplier
      }, new String[] {
        SupplierVO.ISFREECUST, SupplierVO.PK_COUNTRY
      });
      // 获得供应商采购组织页签
      SupStockVO[] supvo =
          SupplierPubService.getSupStockVO(new String[] {
            pk_supplier
          }, pk_purchaseorg, new String[] {
            SupStockVO.RESPDEPT, SupStockVO.RESPPERSON,
            SupStockVO.CURRENCYDEFAULT, SupStockVO.PAYTERMDEFAULT,
            SupStockVO.BILLINGSUP, SupStockVO.SHIPPINGTYPE,
            SupStockVO.ORDERTYPEDEFAULT
          });
      // 获得组织本位币
      String pk_currtype = OrgUnitPubService.queryOrgCurrByPk(pk_purchaseorg);

      SupplierInfo si = new SupplierInfo();
      if (supplier != null && supplier.length > 0) {
        si.setIsFreeCust(supplier[0].getIsfreecust());
        si.setPk_country(supplier[0].getPk_country());
      }
      if (supvo != null && supvo.length > 0) {
        String respdept = supvo[0].getRespdept();
        String resppsn = supvo[0].getRespperson();
        if (respdept == null) {
          respdept = this.getDeptByPsn(resppsn);
        }
        si.setRespDepartment(respdept);
        si.setRespDepartment_v(this.getRespDept_v(respdept));
        si.setRespPerson(supvo[0].getRespperson());
        si.setDefaultCurrency(supvo[0].getCurrencydefault());
        si.setDefaultPaymentTerm(supvo[0].getPaytermdefault());
        si.setBillingSupplier(supvo[0].getBillingsup());
        si.setTransportType(supvo[0].getShippingtype());
        si.setCtrantypeid(supvo[0].getOrdertypedefault());
        this.getOrderTypeCode(si, supvo[0].getOrdertypedefault());
      }
      si.setAddress(address);
      // 如果供应商默认交易币种为空，则取组织本位币
      if (si.getDefaultCurrency() == null) {
        si.setDefaultCurrency(pk_currtype);
        // duyong 此处需要补全开户银行信息
      }
      String defaultBankAcc = SupplierPubService.getDefaultBankAcc(pk_supplier);
      if (defaultBankAcc != null) {
        si.setBankAccount(defaultBankAcc);
      }
      return si;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  private void getOrderTypeCode(SupplierInfo si, String ordertypedefault) {
    if(ordertypedefault == null){
      return;
    }
    SqlBuilder sql = new SqlBuilder();
    sql.append(" and " + PoTransTypeVO.CTRANTYPEID, ordertypedefault);
    try {
      PoTransTypeVO[] vos = PoTransTypeService.queryTranstypeExtProp(sql.toString());
      si.setVtrantypecode(vos[0].getVtrantypecode());
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
  }

  private String getDeptByPsn(String resppsn) {
    if (StringUtil.isEmptyWithTrim(resppsn)) {
      return null;
    }
    Map<String, List<String>> map = null;
    map = PsndocPubService.queryDeptIDByPsndocIDs(new String[] {
      resppsn
    });
    List<String> psnlist = map.get(resppsn);
    if (psnlist != null && psnlist.size() > 0) {
      return psnlist.get(0);
    }
    return null;
  }

  private String getRespDept_v(String pk_dept) {
    if (StringUtil.isEmptyWithTrim(pk_dept)) {
      return null;
    }
    Map<String, String> map = null;
    map = DeptPubService.getLastVIDSByDeptIDS(new String[] {
      pk_dept
    });
    if (null == map) {
      return null;
    }
    return map.get(pk_dept);
  }
}
