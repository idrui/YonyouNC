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
 * �ݹ������ӡ,��Ҫ������������
 * 
 * @since 6.0
 * @version 2010-11-8 ����09:59:45
 * @author tianft
 */

public class EstCardPrintAction extends MetaDataBasedPrintAction {

  /**
   * ��ӡ���ݻ�ȡ��
   * 
   * @since 6.0
   * @version 2010-11-8 ����10:03:04
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
        // ��ʾ���Ƶ���Ҫ���⴦��
        // ������������
        if (EstCardPrintAction.this.feeMaterialName.equals(itemExpress)) {
          return EstCardPrintAction.this.getMaterialName(itemValues);
        }
        // ��Ӧ������
        else if (EstCardPrintAction.this.feeSupplierName.equals(itemExpress)) {
          return EstCardPrintAction.this.getSupplierName(itemValues);
        }
        // ��������
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
   * ���������
   */
  public final String feeCurrecy = "feeCurrecy";

  /**
   * ������۱�����
   */
  public final String feeExchangerate = "feeExchangerate";

  /**
   * �������������
   */
  public final String feeMaterialName = "feeMaterialName";

  /**
   * �����������˰���
   */
  public final String feeMny = "feeMny";

  /**
   * �����ԭ���������
   */
  public final String feeOriMny = "feeOriMny";

  /**
   * �����ԭ�Ҽ�˰�ϼ�
   */
  public final String feeOriTaxMny = "feeOriTaxMny";

  /**
   * �������Ӧ��
   */
  public final String feeSupplierName = "feeSupplierName";

  /**
   * ��������Ҽ�˰�ϼ�
   */
  public final String feeTaxMny = "feeTaxMny";

  /**
   * �����˰��
   */
  public final String feeTaxrate = "feeTaxrate";

  /**
   * �����˰��
   */
  public final String feeNestTaxMny = "feeNestTaxMny";

  /**
   * ��������ɵֿ�˰��
   */
  public final String feeNnoSubTax = "feeNnoSubTax";

  /**
   * ������Ƴɱ����
   */
  public final String feeNcalcostMny = "feeNcalcostMny";

  /**
   * ��ѯ��������
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
   * ��ѯ��������
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
   * ��ѯ��Ӧ������
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
   * ��ӡ������vo�ֶζ�Ӧ
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
