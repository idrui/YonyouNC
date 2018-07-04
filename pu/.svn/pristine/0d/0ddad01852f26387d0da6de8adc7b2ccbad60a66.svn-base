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
 * 期初暂估单迁移数据保存(新增)
 * <p>
 * 使用场景：
 * <ul>
 * <li>V57到V61数据迁移框架itf生成VO对象的保存
 * <li>迁移中可能需要档案参照
 * <li>逐个VO循环处理，有异常的VO返回日志信息，以保证数据迁移的连续性，不能异常中断
 * </ul>
 * 
 * @since 6.0
 * @version 2012-8-10 上午11:10:03
 * @author liuyand
 */
public class InitialEstMigMaintainImpl implements IInitialEstMigMaintain {

  /** 字段名-V57原已结算数量 */
  public static final String FD_NACCOUNTNUM1 = "naccountnum1";

  /** 组合key中的属性分隔符 */
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
   * 联动计算-以无税金额发起联动计算
   * 
   * @param vo
   */
  private void calByMny(InitialEstVO vo) {
    RelationCalculate calculate = new RelationCalculate();
    calculate.calcNum(vo, InitialEstItemVO.NNUM);
    calculate.calculate(vo, InitialEstItemVO.NORIGMNY);
  }

  /**
   * 计算换算率
   * 主计量单位和辅计量单位一样时，换算率为1/1，否则通过服务查询
   * 
   * @param item 表体VO（由平台基于ITF表信息生成的）
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
   * 补全单据信息
   * 平台基于ITF表信息生成的单据VO中，有部分信息为空或不正确，需要在Java代码中补全
   * 需要补全的信息如下（格式-属性名-属性编码-字段名-Vo中或itf表中的值）：
   * 集团本币-ccurrencyid-无-无
   * 价税合计-norigtaxmny-b_norigtaxmny-V57价税合计
   * 税额-ntax-b_ntax-空
   * 税率-ntaxrate-b_ntaxrate-空
   * 
   * @param bills 由平台基于ITF表信息生成的单据VO数组
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
      // 设置本币
      headerVO.setCcurrencyid(pkOrgCurrenMap.get(headerVO.getPk_org()));
      headerVO.setCorigcurrencyid(pkOrgCurrenMap.get(headerVO.getPk_org()));
      headerVO.setNexchangerate(UFDouble.ONE_DBL);
      this.setVatCode(bill);
      for (InitialEstItemVO item : itemVOs) {
        this.synHeadInfo(headerVO, item);
        // 设置税率
        // item.setNtaxrate(this.calcTaxRate(item, iDigit));
        // 设置主单位
        item.setCunitid(pkMaVidUnitIdMap.get(item.getPk_material()));
        // 设置单位-如果V57不是辅计量管理的话，取主单位。
        if (StringUtil.isEmptyWithTrim(item.getCastunitid())) {
          item.setCastunitid(pkMaVidUnitIdMap.get(item.getPk_material()));
        }
        // 设置换算率
        item.setVchangerate(this.calcChangeRate(item, maVidUnitCastMap));
      }
      // 联动计算-以无税金额发起(要放在设置表体金额字段之后)
      this.calByMny(bill);
      this.setCostregion(bill);
    }
    return bills;
  }

  /**
   * 获取物料的VID与主计量单位的Map
   * 
   * @param bills 单据数组
   * @return 物料的VID与主计量单位的Map。key-物料的VID， value-主计量单位
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
   * 获取财务组织和组织本位币的Map
   * 
   * @param bills 单据数组
   * @return 财务组织和组织本位币的Map。key-pk_org 财务组织PK， value-ccurrencyid 本位币
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
   * 设置成本域
   * 
   * @param bill 单据
   */
  private void setCostregion(InitialEstVO bill) {
    BillHelper<InitialEstVO> helper = new BillHelper<InitialEstVO>(bill);
    new CostregionSetter(helper).setCostregion();
  }

  /**
   * 设置档案的版本PK
   * 与档案最新版本的Pk相同
   * 
   * @param headerVO 表头VO
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
   * 设置税码
   * 
   * @param bill 单据
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
