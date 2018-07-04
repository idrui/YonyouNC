package nc.vo.pu.m27.entity;

import nc.vo.pu.m4203.entity.SubcontinFIHeaderVO;
import nc.vo.pu.m4203.entity.SubcontinFIItemVO;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;

public class SubcontInSettleVOMeta extends DataViewMeta {
  public SubcontInSettleVOMeta() {
    this.init();
  }

  private void init() {
    Class<? extends ISuperVO> headClazz = SubcontinFIHeaderVO.class;
    Class<? extends ISuperVO> itemClazz = SubcontinFIItemVO.class;
    this.add(itemClazz, new String[] {
      SubcontinFIItemVO.DBIZDATE,// by 20141203 mengjian
      SubcontinFIItemVO.BSETTLEFINISH, SubcontinFIItemVO.CFIRSTBID,
      SubcontinFIItemVO.CFIRSTID, SubcontinFIItemVO.CFIRSTTYPECODE,
      SubcontinFIItemVO.PK_STOCKPS, SubcontinFIItemVO.CSOURCEBID,
      SubcontinFIItemVO.CSOURCEID, SubcontinFIItemVO.CSOURCETYPECODE,
      SubcontinFIItemVO.FTOIAFLAG, SubcontinFIItemVO.BAFFECTCOST,
      SubcontinFIItemVO.NACCUMSETTLENUM, SubcontinFIItemVO.NINNUM,
      SubcontinFIItemVO.NINASSISTNUM, SubcontinFIItemVO.NACCESTCOSTWASHMNY,
      SubcontinFIItemVO.PK_COSTREGION, SubcontinFIItemVO.CCURRENCYID,
      SubcontinFIItemVO.PK_GROUP, SubcontinFIItemVO.PK_MATERIAL,
      SubcontinFIItemVO.CASTUNITID, SubcontinFIItemVO.CUNITID,
      SubcontinFIItemVO.PK_SRCMATERIAL, SubcontinFIItemVO.PK_ORDER,
      SubcontinFIItemVO.PK_ORDER_B, SubcontinFIItemVO.PK_STOCKPS_B,
      SubcontinFIItemVO.VSOURCECODE, SubcontinFIItemVO.VSOURCEROWNO,
      SubcontinFIItemVO.VSOURCETRANTYPE, SubcontinFIItemVO.VFIRSTROWNO,
      SubcontinFIItemVO.VFIRSTTRANTYPE, SubcontinFIItemVO.VFIRSTCODE,
      SubcontinFIItemVO.PK_SUPPLIER, SubcontinFIItemVO.VFREE1,
      SubcontinFIItemVO.VFREE2, SubcontinFIItemVO.VFREE3,
      SubcontinFIItemVO.VFREE4, SubcontinFIItemVO.VFREE5,
      SubcontinFIItemVO.VFREE6, SubcontinFIItemVO.VFREE7,
      SubcontinFIItemVO.VFREE8, SubcontinFIItemVO.VFREE9,
      SubcontinFIItemVO.VFREE10, SubcontinFIItemVO.VNOTEBODY,
      SubcontinFIItemVO.VBATCHCODE, SubcontinFIItemVO.VCHANGERATE,
      SubcontinFIItemVO.NCOSTMNY, SubcontinFIItemVO.NCOSTPRICE,
      SubcontinFIItemVO.PK_FINANCEORG, SubcontinFIItemVO.PK_FINANCEORG_V,
      SubcontinFIItemVO.PK_APFINANCEORG, SubcontinFIItemVO.PK_APFINANCEORG_V,
      SubcontinFIItemVO.TS, SubcontinFIItemVO.CPROJECTID,
      SubcontinFIItemVO.CPRODUCTORID, SubcontinFIItemVO.NACCGOODSSETTLEMNY
    });
    this.add(headClazz, new String[] {
      SubcontinFIHeaderVO.PK_BUSITYPE, SubcontinFIHeaderVO.PK_GROUP,
      SubcontinFIHeaderVO.PK_ORG, SubcontinFIHeaderVO.PK_ORG_V,
      SubcontinFIHeaderVO.PK_STOCKPS, SubcontinFIHeaderVO.PK_STORDOC,
      SubcontinFIHeaderVO.VBILLCODE, SubcontinFIHeaderVO.CBILLTYPECODE,
      SubcontinFIHeaderVO.VTRANTYPECODE, SubcontinFIHeaderVO.CTRANTYPEID,
      SubcontinFIHeaderVO.PK_DEPT, SubcontinFIHeaderVO.PK_DEPT_V,
      SubcontinFIHeaderVO.PK_PSNDOC, SubcontinFIHeaderVO.TS

    });
    this.addRelation(itemClazz, SubcontinFIItemVO.PK_STOCKPS, headClazz,
        SubcontinFIHeaderVO.PK_STOCKPS);
  }
}
