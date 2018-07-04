package nc.pubimpl.pu.m4t.pub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m4t.IInitialEstMaintain;
import nc.itf.scmpub.reference.uap.bd.material.MaterialPubService;
import nc.itf.scmpub.reference.uap.bd.vat.VATBDService;
import nc.itf.scmpub.reference.uap.bd.vat.VATInfoQueryVO;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.pubitf.pu.m4t.pub.IInitialEstMigMaintain;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.m4t.rule.CostregionSetter;
import nc.vo.pu.m4t.rule.InitialEstVatValue;
import nc.vo.pu.m4t.rule.RelationCalculate;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * �ڳ��ݹ���Ǩ�����ݱ���(����)
 * <p>
 * ʹ�ó�����
 * <ul>
 * <li>V57��V61����Ǩ�ƿ��itf����VO����ı���
 * <li>Ǩ���п�����Ҫ��������
 * <li>���VOѭ���������쳣��VO������־��Ϣ���Ա�֤����Ǩ�Ƶ������ԣ������쳣�ж�
 * </ul>
 * 
 * @since 6.0
 * @version 2012-8-10 ����11:10:03
 * @author liuyand
 */
public class InitialEstMigMaintainImpl implements IInitialEstMigMaintain {

  /** �ֶ���-V57ԭ�ѽ������� */
  public static final String FD_NACCOUNTNUM1 = "naccountnum1";

  /** ���key�е����Էָ��� */
  public static final String KEY_SEPAR = "_";

  @Override
  public String[] migSave(InitialEstVO[] bills) throws BusinessException {
    try {
      NCLocator.getInstance().lookup(IInitialEstMaintain.class)
          .save(this.fillVal(bills), null);
      return null;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * ��������-����˰������������
   * 
   * @param vo
   */
  private void calByMny(InitialEstVO vo) {
    RelationCalculate calculate = new RelationCalculate();
    calculate.calcNum(vo, InitialEstItemVO.NNUM);
    calculate.calculate(vo, InitialEstItemVO.NORIGMNY);
  }

  /**
   * ���㻻����
   * ��������λ�͸�������λһ��ʱ��������Ϊ1/1������ͨ�������ѯ
   * 
   * @param item ����VO����ƽ̨����ITF����Ϣ���ɵģ�
   * @param maVidUnitCastMap
   * @return
   */
  private String calcChangeRate(InitialEstItemVO item,
      Map<String, String> maVidUnitCastMap) {
    String strChangeRate = null;
    if (null == item.getCunitid()) {
      return "1/1";
    }
    if (item.getCunitid().equals(item.getCastunitid())) {
      strChangeRate = MaterialPubService.CHANGERATE_ONE;
    }
    else {
      String key =
          item.getPk_material() + InitialEstMigMaintainImpl.KEY_SEPAR
              + item.getCunitid();
      if (maVidUnitCastMap != null && maVidUnitCastMap.containsKey(key)) {
        strChangeRate = maVidUnitCastMap.get(key);
      }
      else {
        strChangeRate =
            MaterialPubService.queryMainMeasRateByMaterialAndMeasdoc(
                item.getPk_material(), item.getCunitid(), item.getCastunitid());
        if (maVidUnitCastMap != null) {
          maVidUnitCastMap.put(key, strChangeRate);
        }
      }
    }
    return strChangeRate;
  }

  /**
   * ��ȫ������Ϣ
   * ƽ̨����ITF����Ϣ���ɵĵ���VO�У��в�����ϢΪ�ջ���ȷ����Ҫ��Java�����в�ȫ
   * ��Ҫ��ȫ����Ϣ���£���ʽ-������-���Ա���-�ֶ���-Vo�л�itf���е�ֵ����
   * ���ű���-ccurrencyid-��-��
   * ��˰�ϼ�-norigtaxmny-b_norigtaxmny-V57��˰�ϼ�
   * ˰��-ntax-b_ntax-��
   * ˰��-ntaxrate-b_ntaxrate-��
   * 
   * @param bills ��ƽ̨����ITF����Ϣ���ɵĵ���VO����
   * @return
   */
  private InitialEstVO[] fillVal(InitialEstVO[] bills) {
    Map<String, String> pkOrgCurrenMap = this.getPkOrgCurrenMap(bills);
    Map<String, String> pkMaVidUnitIdMap = this.getPkMaVidUnitIdMap(bills);
    Map<String, String> maVidUnitCastMap = new HashMap<String, String>();
    // int iDigit =
    // new ScaleObjectFactory(AppContext.getInstance().getPkGroup())
    // .getTaxRateScaleObject().getDigit();
    // String transType =
    // TrantypeFuncUtils.getTrantype(AppContext.getInstance());
    for (InitialEstVO bill : bills) {
      InitialEstHeaderVO headerVO = bill.getHeader();
      InitialEstItemVO[] itemVOs = bill.getItems();
      headerVO.setPk_group(AppContext.getInstance().getPkGroup());
      this.setHeaderVal(headerVO);
      // ���ñ���
      headerVO.setCcurrencyid(pkOrgCurrenMap.get(headerVO.getPk_org()));
      headerVO.setCorigcurrencyid(pkOrgCurrenMap.get(headerVO.getPk_org()));
      headerVO.setNexchangerate(UFDouble.ONE_DBL);
      this.setVatCode(bill);
      for (InitialEstItemVO item : itemVOs) {
        this.synHeadInfo(headerVO, item);
        // ����˰��
        // item.setNtaxrate(this.calcTaxRate(item, iDigit));
        // ��������λ
        item.setCunitid(pkMaVidUnitIdMap.get(item.getPk_material()));
        // ���õ�λ-���V57���Ǹ���������Ļ���ȡ����λ��
        if (StringUtil.isEmptyWithTrim(item.getCastunitid())) {
          item.setCastunitid(pkMaVidUnitIdMap.get(item.getPk_material()));
        }
        // ���û�����
        item.setVchangerate(this.calcChangeRate(item, maVidUnitCastMap));
      }
      // ��������-����˰����(Ҫ�������ñ������ֶ�֮��)
      this.calByMny(bill);
      this.setCostregion(bill);
    }
    return bills;
  }

  /**
   * ��ȡ���ϵ�VID����������λ��Map
   * 
   * @param bills ��������
   * @return ���ϵ�VID����������λ��Map��key-���ϵ�VID�� value-��������λ
   */
  private Map<String, String> getPkMaVidUnitIdMap(InitialEstVO[] bills) {
    Set<String> pkMaVidSet = new HashSet<String>();
    String strPkMaVid = null;
    for (InitialEstVO bill : bills) {
      for (InitialEstItemVO item : bill.getItems()) {
        strPkMaVid = item.getPk_material();
        if (!pkMaVidSet.contains(strPkMaVid)) {
          pkMaVidSet.add(strPkMaVid);
        }
      }

    }
    if (pkMaVidSet.size() > 0) {
      return MaterialPubService.queryMaterialMeasdoc(pkMaVidSet
          .toArray(new String[pkMaVidSet.size()]));
    }

    return null;
  }

  /**
   * ��ȡ������֯����֯��λ�ҵ�Map
   * 
   * @param bills ��������
   * @return ������֯����֯��λ�ҵ�Map��key-pk_org ������֯PK�� value-ccurrencyid ��λ��
   */
  private Map<String, String> getPkOrgCurrenMap(InitialEstVO[] bills) {
    Set<String> pkOrgSet = new HashSet<String>();
    String strPkOrg = null;
    for (InitialEstVO bill : bills) {
      strPkOrg = bill.getHeader().getPk_org();
      if (!pkOrgSet.contains(strPkOrg)) {
        pkOrgSet.add(strPkOrg);
      }
    }
    if (pkOrgSet.size() > 0) {
      return OrgUnitPubService.queryOrgCurrByPk(pkOrgSet
          .toArray(new String[pkOrgSet.size()]));
    }

    return null;
  }

  /**
   * ���óɱ���
   * 
   * @param bill ����
   */
  private void setCostregion(InitialEstVO bill) {
    BillHelper<InitialEstVO> helper = new BillHelper<InitialEstVO>(bill);
    new CostregionSetter(helper).setCostregion();
  }

  /**
   * ���õ����İ汾PK
   * �뵵�����°汾��Pk��ͬ
   * 
   * @param headerVO ��ͷVO
   */
  private void setHeaderVal(InitialEstHeaderVO headerVO) {
    String vtransTypeDefCode = "4T-01";
    Map<String, String> trantypeidByCode =
        PfServiceScmUtil.getTrantypeidByCode(new String[] {
          vtransTypeDefCode
        });
    headerVO.setCtrantypeid(null != trantypeidByCode ? trantypeidByCode
        .get(vtransTypeDefCode) : null);
    headerVO.setVtrantypecode(null != trantypeidByCode ? vtransTypeDefCode
        : null);
    headerVO.setPk_org_v(OrgUnitPubService.getNewVIDByOrgID(headerVO
        .getPk_org()));
    headerVO.setPk_stockorg_v(OrgUnitPubService.getNewVIDByOrgID(headerVO
        .getPk_stockorg()));
    headerVO.setPk_purchaseorg_v(OrgUnitPubService.getNewVIDByOrgID(headerVO
        .getPk_purchaseorg()));
    headerVO.setPk_dept_v(OrgUnitPubService.getNewVIDByOrgID(headerVO
        .getPk_dept()));
  }

  /**
   * ����˰��
   * 
   * @param bill ����
   */
  private void setVatCode(InitialEstVO bill) {
    List<VATInfoQueryVO> vatqvoList = new ArrayList<VATInfoQueryVO>();
    BillHelper<InitialEstVO> helper = new BillHelper<InitialEstVO>(bill);
    InitialEstItemVO[] itemVOs = bill.getItems();
    for (int i = 0; i < itemVOs.length; ++i) {
      vatqvoList.add(InitialEstVatValue.getTaxQueryVO(helper, i));
    }
    String[] taxCodes =
        VATBDService.querySupplierTaxCode(vatqvoList
            .toArray(new VATInfoQueryVO[vatqvoList.size()]));
    for (int i = 0; i < itemVOs.length; ++i) {
      itemVOs[i].setCtaxcodeid(taxCodes[i]);
    }

  }

  private void synHeadInfo(InitialEstHeaderVO head, InitialEstItemVO item) {
    item.setPk_group(head.getPk_group());
    item.setPk_org(head.getPk_org());
    item.setPk_org_v(head.getPk_org_v());
    item.setPk_apfinanceorg(head.getPk_org());
    item.setPk_apfinanceorg_v(head.getPk_org_v());
  }
}
