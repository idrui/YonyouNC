package nc.vo.pu.m27.entity;

import nc.vo.pu.m4201.entity.PurchaseinFIHeaderVO;
import nc.vo.pu.m4201.entity.PurchaseinFIItemVO;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;

public class PurchaseInSettleVOMeta extends DataViewMeta {
  public PurchaseInSettleVOMeta() {
    this.init();
  }

  private void init() {
    Class<? extends ISuperVO> headClazz = PurchaseinFIHeaderVO.class;
    Class<? extends ISuperVO> itemClazz = PurchaseinFIItemVO.class;
    this.add(itemClazz, new String[] {
      PurchaseinFIItemVO.DBIZDATE,// by 20141203 mengjian
      PurchaseinFIItemVO.BSETTLEFINISH,
      PurchaseinFIItemVO.CFIRSTBID,
      PurchaseinFIItemVO.CFIRSTID,
      PurchaseinFIItemVO.CFIRSTTYPECODE,
      PurchaseinFIItemVO.PK_STOCKPS,
      PurchaseinFIItemVO.CSOURCEBID,
      PurchaseinFIItemVO.CSOURCEID,
      PurchaseinFIItemVO.CSOURCETYPECODE,
      PurchaseinFIItemVO.FTOIAFLAG,
      PurchaseinFIItemVO.FTOAPFLAG,
      PurchaseinFIItemVO.BAFFECTCOST,
      PurchaseinFIItemVO.BAFFECTPCCOST,
      PurchaseinFIItemVO.NACCUMSETTLENUM,
      PurchaseinFIItemVO.NINNUM,
      PurchaseinFIItemVO.NINASSISTNUM,
      PurchaseinFIItemVO.NESTNUM,
      PurchaseinFIItemVO.NESTMNY,
      PurchaseinFIItemVO.NESTPRICE,
      PurchaseinFIItemVO.NACCESTCOSTSTTLNUM,
      PurchaseinFIItemVO.NACCESTCOSTWASHMNY,
      PurchaseinFIItemVO.NACCTOCOSTADJSTMNY,
      PurchaseinFIItemVO.NMNY,
      PurchaseinFIItemVO.NORIGTAXNETPRICE,
      PurchaseinFIItemVO.NACCTOAPADJSTOTMNY,

      PurchaseinFIItemVO.NORIGTAXMNY,
      PurchaseinFIItemVO.NNETPRICE,
      PurchaseinFIItemVO.PK_COSTREGION,
      PurchaseinFIItemVO.CORIGCURRENCYID,
      PurchaseinFIItemVO.CCURRENCYID,
      PurchaseinFIItemVO.PK_GROUP,
      PurchaseinFIItemVO.PK_MATERIAL,
      PurchaseinFIItemVO.CASTUNITID,
      PurchaseinFIItemVO.CUNITID,
      PurchaseinFIItemVO.PK_SRCMATERIAL,
      PurchaseinFIItemVO.PK_ORDER,
      PurchaseinFIItemVO.PK_ORDER_B,
      // added by wangzhqf
      PurchaseinFIItemVO.VORDERCODE,
      PurchaseinFIItemVO.VCTCODE,
      // added by wangzhqf
      PurchaseinFIItemVO.PK_STOCKPS_B, PurchaseinFIItemVO.VSOURCECODE,
      PurchaseinFIItemVO.VSOURCEROWNO, PurchaseinFIItemVO.VSOURCETRANTYPE,
      PurchaseinFIItemVO.VFIRSTROWNO, PurchaseinFIItemVO.VFIRSTTRANTYPE,
      PurchaseinFIItemVO.VFIRSTCODE, PurchaseinFIItemVO.PK_SUPPLIER,
      PurchaseinFIItemVO.VFREE1, PurchaseinFIItemVO.VFREE2,
      PurchaseinFIItemVO.VFREE3, PurchaseinFIItemVO.VFREE4,
      PurchaseinFIItemVO.VFREE5, PurchaseinFIItemVO.VFREE6,
      PurchaseinFIItemVO.VFREE7, PurchaseinFIItemVO.VFREE8,
      PurchaseinFIItemVO.VFREE9, PurchaseinFIItemVO.VFREE10,
      PurchaseinFIItemVO.VNOTEBODY, PurchaseinFIItemVO.VBATCHCODE,
      PurchaseinFIItemVO.VCHANGERATE, PurchaseinFIItemVO.NCOSTMNY,
      PurchaseinFIItemVO.NCOSTPRICE, PurchaseinFIItemVO.PK_FINANCEORG,
      PurchaseinFIItemVO.PK_FINANCEORG_V, PurchaseinFIItemVO.PK_APFINANCEORG,
      PurchaseinFIItemVO.PK_APFINANCEORG_V, PurchaseinFIItemVO.TS,
      PurchaseinFIItemVO.NACCPREESTSTTLMNY,
      PurchaseinFIItemVO.CPROJECTID,
      PurchaseinFIItemVO.CPRODUCTORID,
      PurchaseinFIItemVO.NACCGOODSSETTLEMNY
      // wuxla v61
      , PurchaseinFIItemVO.NESTCALCOSTMNY, PurchaseinFIItemVO.NCALCOSTMNY,
      PurchaseinFIItemVO.NESTCALCOSTPRICE, PurchaseinFIItemVO.NCALCOSTPRICE,
      PurchaseinFIItemVO.NORIGNETPRICE
    });
    // 视图vo还要包含主表vo
    this.add(headClazz, new String[] {
      PurchaseinFIHeaderVO.BNORMPUR, PurchaseinFIHeaderVO.PK_BUSITYPE,
      PurchaseinFIHeaderVO.PK_GROUP, PurchaseinFIHeaderVO.PK_ORG,
      PurchaseinFIHeaderVO.PK_ORG_V, PurchaseinFIHeaderVO.PK_STOCKPS,
      PurchaseinFIHeaderVO.PK_STORDOC, PurchaseinFIHeaderVO.VBILLCODE,
      PurchaseinFIHeaderVO.CBILLTYPECODE, PurchaseinFIHeaderVO.VTRANTYPECODE,
      PurchaseinFIHeaderVO.CTRANTYPEID, PurchaseinFIHeaderVO.PK_DEPT,
      PurchaseinFIHeaderVO.PK_DEPT_V, PurchaseinFIHeaderVO.PK_PSNDOC,
      PurchaseinFIHeaderVO.TS

    });
    // 设置关联字段

    this.addRelation(itemClazz, PurchaseinFIItemVO.PK_STOCKPS, headClazz,
        PurchaseinFIHeaderVO.PK_STOCKPS);
  }
}
