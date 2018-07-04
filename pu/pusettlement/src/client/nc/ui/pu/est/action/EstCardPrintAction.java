package nc.ui.pu.est.action;

import java.util.HashMap;
import java.util.Map;

import nc.itf.scmpub.reference.uap.bd.currency.CurrencyInfo;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.bd.supplier.SupplierPubService;
import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.vo.bd.material.MaterialVO;
import nc.vo.bd.supplier.SupplierVO;
import nc.vo.pu.est.entity.EstVO;
import nc.vo.pu.est.entity.FeeEstVO;
import nc.vo.pubapp.pattern.data.ValueUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 暂估处理打印,主要处理表体费用项
 * 
 * @since 6.0
 * @version 2010-11-8 上午09:59:45
 * @author tianft
 */

public class EstCardPrintAction extends MetaDataBasedPrintAction {

  /**
   * 打印数据获取类
   * 
   * @since 6.0
   * @version 2010-11-8 上午10:03:04
   * @author tianft
   */
  public class EstPrintDataSource extends MetaDataSource {

    private static final long serialVersionUID = 648800490256459480L;

    private EstVO estVO;

    public EstPrintDataSource(Object printData, EstVO estVO) {
      super(new Object[] {
        printData
      });
      this.estVO = estVO;
    }

    @Override
    public String[] getItemValuesByExpress(String itemExpress) {
      if (EstCardPrintAction.this.getVarMap().containsKey(itemExpress)) {
        if (ArrayUtils.isEmpty(this.estVO.getChildrenVO())) {
          return null;
        }
        String[] itemValues = new String[this.estVO.getChildrenVO().length];

        for (int i = 0; i < this.estVO.getChildrenVO().length; i++) {
          FeeEstVO item = this.estVO.getChildrenVO()[i];
          itemValues[i] =
              ValueUtils.getString(item
                  .getAttributeValue(EstCardPrintAction.this.getVarMap().get(
                      itemExpress)));
        }
        // 显示名称的需要特殊处理
        // 费用物料名称
        if (EstCardPrintAction.this.feeMaterialName.equals(itemExpress)) {
          return EstCardPrintAction.this.getMaterialName(itemValues);
        }
        // 供应商名称
        else if (EstCardPrintAction.this.feeSupplierName.equals(itemExpress)) {
          return EstCardPrintAction.this.getSupplierName(itemValues);
        }
        // 币种名称
        else if (EstCardPrintAction.this.feeCurrecy.equals(itemExpress)) {
          return EstCardPrintAction.this.getCurrencyName(itemValues);
        }
        else {
          return itemValues;
        }
      }
      return super.getItemValuesByExpress(itemExpress);
    }

  }

  private static final long serialVersionUID = 7042734595387239192L;

  /**
   * 费用项：币种
   */
  public final String feeCurrecy = "feeCurrecy";

  /**
   * 费用项：折本汇率
   */
  public final String feeExchangerate = "feeExchangerate";

  /**
   * 费用项：费用物料
   */
  public final String feeMaterialName = "feeMaterialName";

  /**
   * 费用项：本币无税金额
   */
  public final String feeMny = "feeMny";

  /**
   * 费用项：原币无数金额
   */
  public final String feeOriMny = "feeOriMny";

  /**
   * 费用项：原币价税合计
   */
  public final String feeOriTaxMny = "feeOriTaxMny";

  /**
   * 费用项：供应商
   */
  public final String feeSupplierName = "feeSupplierName";

  /**
   * 费用项：本币价税合计
   */
  public final String feeTaxMny = "feeTaxMny";

  /**
   * 费用项：税率
   */
  public final String feeTaxrate = "feeTaxrate";

  /**
   * 费用项：税额
   */
  public final String feeNestTaxMny = "feeNestTaxMny";

  /**
   * 费用项：不可抵扣税额
   */
  public final String feeNnoSubTax = "feeNnoSubTax";

  /**
   * 费用项：计成本金额
   */
  public final String feeNcalcostMny = "feeNcalcostMny";

  /**
   * 查询币种名称
   * 
   * @param pk_currency
   * @return
   */
  protected String[] getCurrencyName(String[] pk_currency) {
    String[] names = new String[pk_currency.length];
    Map<String, String> nameMap = new HashMap<String, String>();
    for (String id : pk_currency) {
      if (!nameMap.containsKey(id)) {
        CurrencyInfo.getName(id);
      }
    }
    for (int i = 0; i < names.length; i++) {
      names[i] = nameMap.get(pk_currency[i]);
    }
    return names;
  }

  /**
   * 查询物料名称
   * 
   * @param pk_material
   * @return
   */
  protected String[] getMaterialName(String[] pk_material) {
    Map<String, MaterialVO> materialMap =
        MaterialPubService.queryMaterialBaseInfo(pk_material, new String[] {
          MaterialVO.NAME
        });
    String[] names = new String[pk_material.length];
    for (int i = 0; i < names.length; i++) {
      names[i] = materialMap.get(pk_material[i]).getName();
    }
    return names;
  }

  /**
   * 查询供应商名称
   * 
   * @param pk_supplier
   * @return
   */
  protected String[] getSupplierName(String[] pk_supplier) {
    SupplierVO[] supplierVOs =
        SupplierPubService.getSupplierVO(pk_supplier, new String[] {
          SupplierVO.NAME
        });

    Map<String, SupplierVO> voMap = new HashMap<String, SupplierVO>();
    for (SupplierVO vo : supplierVOs) {
      voMap.put(vo.getPk_supplier(), vo);
    }
    String[] names = new String[pk_supplier.length];
    for (int i = 0; i < names.length; i++) {
      names[i] = voMap.get(pk_supplier[i]).getName();
    }
    return names;
  }

  /**
   * 打印变量与vo字段对应
   * 
   * @return
   */
  protected Map<String, String> getVarMap() {
    Map<String, String> varMap = new HashMap<String, String>();
    varMap.put(this.feeCurrecy, FeeEstVO.PK_ESTCURRENCY);
    varMap.put(this.feeTaxrate, FeeEstVO.NESTTAXRATE);
    varMap.put(this.feeTaxMny, FeeEstVO.NESTTOTALMNY);
    varMap.put(this.feeSupplierName, FeeEstVO.PK_SUPPLIER);
    varMap.put(this.feeOriTaxMny, FeeEstVO.NESTOTOTALMNY);
    varMap.put(this.feeOriMny, FeeEstVO.NESTOMNY);
    varMap.put(this.feeMny, FeeEstVO.NESTMNY);
    varMap.put(this.feeMaterialName, FeeEstVO.PK_FEEMATERIAL);
    varMap.put(this.feeExchangerate, FeeEstVO.NESTEXCHGRATE);
    varMap.put(this.feeNestTaxMny, FeeEstVO.NESTTAXMNY);
    varMap.put(this.feeNnoSubTax, FeeEstVO.NNOSUBTAX);
    varMap.put(this.feeNcalcostMny, FeeEstVO.NCALCOSTMNY);
    return varMap;

  }

  @Override
  protected boolean isActionEnable() {
    BillManageModel estModel = (BillManageModel) this.getModel();
    Object[] data = estModel.getSelectedOperaDatas();
    return !ArrayUtils.isEmpty(data);
  }

}
