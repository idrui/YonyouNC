package nc.vo.pu.m27.entity;

import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;

public class InitialEstSettleVOMeta extends DataViewMeta {
  public InitialEstSettleVOMeta() {
    this.init();
  }

  private void init() {
    Class<? extends ISuperVO> headClazz = InitialEstHeaderVO.class;
    Class<? extends ISuperVO> itemClazz = InitialEstItemVO.class;
    String[] headAttrs =
        {
          InitialEstHeaderVO.DBILLDATE,// by 20141203 mengjian
          InitialEstHeaderVO.BNORMPUR, InitialEstHeaderVO.PK_GROUP,
          InitialEstHeaderVO.PK_ORG, InitialEstHeaderVO.PK_ORG_V,
          InitialEstHeaderVO.PK_INITIALEST, InitialEstHeaderVO.PK_STOCKORG,
          InitialEstHeaderVO.PK_STOCKORG_V, InitialEstHeaderVO.VBILLCODE,
          InitialEstHeaderVO.PK_DEPT, InitialEstHeaderVO.VTRANTYPECODE,
          InitialEstHeaderVO.CTRANTYPEID, InitialEstHeaderVO.PK_DEPT_V,
          InitialEstHeaderVO.PK_BUSITYPE, InitialEstHeaderVO.CCURRENCYID,
          InitialEstHeaderVO.CORIGCURRENCYID, InitialEstHeaderVO.PK_COSTREGION,
          InitialEstHeaderVO.PK_SUPPLIER, InitialEstHeaderVO.PK_STORDOC,
          InitialEstHeaderVO.PK_BIZPSN, InitialEstHeaderVO.TS
        };
    String[] itemAttrs =
        {
          InitialEstItemVO.BSETTLEFINISH, InitialEstItemVO.BESTIMATEAP,
          InitialEstItemVO.CSOURCEBID, InitialEstItemVO.CSOURCEID,
          InitialEstItemVO.CSOURCETYPECODE, InitialEstItemVO.NNUM,
          InitialEstItemVO.NASTNUM, InitialEstItemVO.NMNY,
          InitialEstItemVO.NORIGTAXMNY, InitialEstItemVO.NPRICE,
          InitialEstItemVO.NORIGTAXMNY, InitialEstItemVO.PK_MATERIAL,
          InitialEstItemVO.CASTUNITID, InitialEstItemVO.CUNITID,
          InitialEstItemVO.PK_SRCMATERIAL, InitialEstItemVO.PK_ORDER,
          InitialEstItemVO.PK_ORDER_B, InitialEstItemVO.CORDERROWNO,
          InitialEstItemVO.VORDERCODE, InitialEstItemVO.VORDERTRANTYPE,
          InitialEstItemVO.PK_INITIALEST_B, InitialEstItemVO.VSOURCECODE,
          InitialEstItemVO.VSOURCEROWNO, InitialEstItemVO.VSOURCETRANTYPE,
          InitialEstItemVO.VFREE1, InitialEstItemVO.VFREE2,
          InitialEstItemVO.VFREE3, InitialEstItemVO.VFREE4,
          InitialEstItemVO.VFREE5, InitialEstItemVO.VFREE6,
          InitialEstItemVO.VFREE7, InitialEstItemVO.VFREE8,
          InitialEstItemVO.VFREE9, InitialEstItemVO.VFREE10,
          InitialEstItemVO.VBMEMO, InitialEstItemVO.VBATCHCODE,
          InitialEstItemVO.VCHANGERATE, InitialEstItemVO.PK_APFINANCEORG,
          InitialEstItemVO.PK_APFINANCEORG_V, InitialEstItemVO.NACCSETTLENUM,
          InitialEstItemVO.NACCWASHMNY, InitialEstItemVO.TS,
          InitialEstItemVO.CPROJECTID, InitialEstItemVO.CPRODUCTORID,
          InitialEstItemVO.NACCGOODSSETTLEMNY,
          InitialEstItemVO.BAFFECTCOST,
          InitialEstItemVO.BAFFECTPCCOST
          // wuxla V61
          , InitialEstItemVO.NCALCOSTMNY, InitialEstItemVO.NORIGPRICE,
          InitialEstItemVO.NESTCALCOSTPRICE
        };

    this.add(itemClazz, itemAttrs);
    this.add(headClazz, headAttrs);
    this.addRelation(itemClazz, InitialEstItemVO.PK_INITIALEST, headClazz,
        InitialEstItemVO.PK_INITIALEST);

  }
}
