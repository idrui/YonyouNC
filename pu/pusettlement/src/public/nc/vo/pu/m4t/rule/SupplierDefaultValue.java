/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-11 上午11:08:50
 */
package nc.vo.pu.m4t.rule;

import nc.itf.scmpub.reference.uap.bd.supplier.SupplierPubService;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.bd.supplier.finance.SupFinanceVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.pub.util.IKeyValue;
import nc.vo.pu.pub.util.ObjectUtil;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>供应商默认值
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-11 上午11:08:50
 */
public class SupplierDefaultValue {
  private IKeyValue keyValue;

  public SupplierDefaultValue(IKeyValue keyValue) {
    this.keyValue = keyValue;
  }

  /**
   * 方法功能描述：设置供应商默认值
   * <p>
   * <b>参数说明</b>
   * <p>
   * 
   * @since 6.0
   * @author wuxla
   * @time 2010-9-11 下午01:21:40
   */
  public void setDefaultValue() {
    String pk_supplier =
        (String) this.keyValue.getHeadValue(InitialEstHeaderVO.PK_SUPPLIER);
    if (StringUtil.isEmptyWithTrim(pk_supplier)) {
      return;
    }
    SupFinanceVO supFinanceVO = this.getSupFinanceVO();
    if (null == supFinanceVO) {
      return;
    }

    String pk_currency = supFinanceVO.getPk_currtype();
    if (StringUtil.isEmptyWithTrim(pk_currency)) {
      pk_currency = this.getCurrencyByOrg();
    }
    this.keyValue.setHeadValue(InitialEstHeaderVO.CORIGCURRENCYID, pk_currency);

    Object pk_bizpsn = this.keyValue.getHeadValue(InitialEstHeaderVO.PK_BIZPSN);
    if (ObjectUtil.isEmptyWithTrim(pk_bizpsn)) {
      this.keyValue.setHeadValue(InitialEstHeaderVO.PK_BIZPSN,
          supFinanceVO.getPk_resppsn());
    }

    Object pk_dept_v = this.keyValue.getHeadValue(InitialEstHeaderVO.PK_DEPT_V);
    if (ObjectUtil.isEmptyWithTrim(pk_dept_v)) {
      this.keyValue.setHeadValue(InitialEstHeaderVO.PK_DEPT_V,
          supFinanceVO.getPk_respdept());
    }
  }

  private String getCurrencyByOrg() {
    String pk_org =
        (String) this.keyValue.getHeadValue(InitialEstHeaderVO.PK_ORG);
    if (!StringUtil.isEmptyWithTrim(pk_org)) {
      return OrgUnitPubService.queryOrgCurrByPk(pk_org);
    }

    return null;
  }

  private SupFinanceVO getSupFinanceVO() {
    String pk_supplier =
        (String) this.keyValue.getHeadValue(InitialEstHeaderVO.PK_SUPPLIER);
    String pk_org =
        (String) this.keyValue.getHeadValue(InitialEstHeaderVO.PK_ORG);
    if (StringUtil.isEmptyWithTrim(pk_supplier)
        || StringUtil.isEmptyWithTrim(pk_org)) {
      return null;
    }

    SupFinanceVO[] supFinanceVOs =
        SupplierPubService.getSupFinanceVO(new String[] {
          pk_supplier
        }, pk_org, new String[] {
          SupFinanceVO.PK_CURRTYPE, SupFinanceVO.PK_RESPDEPT,
          SupFinanceVO.PK_RESPPSN
        });

    if (ArrayUtils.isEmpty(supFinanceVOs)) {
      return null;
    }

    return supFinanceVOs[0];

  }
}
