package nc.vo.pu.pub.util;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m21.IOrderSupplierQuery;
import nc.vo.pu.m21.query.supplier.SupplierInfo;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 *		根据供应商主键获取供应商信息
 * @scene
 * 
 * @param
 * 
 * @functionName 
 *		获取供应商信息
 * @since 6.5
 * @version 2015-12-7 下午12:57:42
 * @author wandl
 */
public class SupplierInfoUtil {

	private SupplierInfo supplier;
	
  private String suppliername = "pk_supplier";
  
  private String orgname = "pk_org";
	
	public SupplierInfoUtil() {
		//
	}

	public String getSuppliername(){
		return this.suppliername;
	}
	
	public String getOrgname(){
		return this.orgname;
	}
	
	/**
   * 根据供应商主键获取供应商信息
   * 
   * @param keyValue
   * @return
   */
  public SupplierInfo getSupplierInfo(IKeyValue keyValue) {
    if (null != this.supplier) {
      return this.supplier;
    }
    String pk_supplier = (String) keyValue.getHeadValue(this.getSuppliername());
    if (pk_supplier == null) {
      return null;
    }
    String pk_purchaseorg = (String) keyValue.getHeadValue(this.getOrgname());
    try {
      this.supplier =
          NCLocator.getInstance().lookup(IOrderSupplierQuery.class)
              .querySupplier(pk_supplier, pk_purchaseorg);
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return this.supplier;
  }
	
}
