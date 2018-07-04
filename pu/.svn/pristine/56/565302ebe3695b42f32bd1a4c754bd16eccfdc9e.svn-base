package nc.vo.pu.m27.entity;

import java.util.ArrayList;
import java.util.List;

import nc.vo.ic.general.define.MetaNameConst;
import nc.vo.ic.m4a.entity.GeneralInBodyVO;
import nc.vo.ic.m4a.entity.GeneralInHeadVO;
import nc.vo.ic.pub.define.ICPubMetaNameConst;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.model.meta.entity.view.DataViewMeta;

public class GeneralInSettleVOMeta extends DataViewMeta {
  public GeneralInSettleVOMeta() {
    this.init();
  }

  private void init() {
    Class<? extends ISuperVO> headClazz = GeneralInHeadVO.class;
    Class<? extends ISuperVO> itemClazz = GeneralInBodyVO.class;
    List<String> headKeys = new ArrayList<String>();
    headKeys.add(ICPubMetaNameConst.PK_GROUP);
    headKeys.add(ICPubMetaNameConst.PK_ORG);
    headKeys.add(ICPubMetaNameConst.PK_ORG_V);
    headKeys.add(MetaNameConst.CGENERALHID);
    headKeys.add(ICPubMetaNameConst.CWAREHOUSEID);
    headKeys.add(ICPubMetaNameConst.VBILLCODE);
    headKeys.add(ICPubMetaNameConst.CDPTID);
    headKeys.add(ICPubMetaNameConst.CDPTVID);
    headKeys.add(ICPubMetaNameConst.CBIZID);
    headKeys.add(ICPubMetaNameConst.CTRANTYPEID);
    headKeys.add(ICPubMetaNameConst.VTRANTYPECODE);
    headKeys.add(ICPubMetaNameConst.TS);

    List<String> bodyKeys = new ArrayList<String>();
    bodyKeys.add(MetaNameConst.CGENERALBID);
    bodyKeys.add(MetaNameConst.CFIRSTBILLHID);
    bodyKeys.add(MetaNameConst.CFIRSTBILLBID);
    bodyKeys.add(MetaNameConst.CFIRSTTYPE);
    bodyKeys.add(MetaNameConst.CFIRSTTRANSTYPE);
    bodyKeys.add(MetaNameConst.VFIRSTBILLCODE);
    bodyKeys.add(MetaNameConst.VFIRSTROWNO);
    bodyKeys.add(MetaNameConst.CSOURCEBILLHID);
    bodyKeys.add(MetaNameConst.CSOURCEBILLBID);
    bodyKeys.add(MetaNameConst.CSOURCETYPE);
    bodyKeys.add(MetaNameConst.CSOURCETRANSTYPE);
    bodyKeys.add(MetaNameConst.VSOURCEBILLCODE);
    bodyKeys.add(MetaNameConst.VSOURCEROWNO);
    bodyKeys.add(ICPubMetaNameConst.NASSISTNUM);
    bodyKeys.add(ICPubMetaNameConst.NNUM);
    bodyKeys.add(ICPubMetaNameConst.NCOSTMNY);
    bodyKeys.add(ICPubMetaNameConst.NCOSTPRICE);
    bodyKeys.add(ICPubMetaNameConst.CMATERIALOID);
    bodyKeys.add(ICPubMetaNameConst.CMATERIALVID);
    bodyKeys.add(ICPubMetaNameConst.CASTUNITID);
    bodyKeys.add(ICPubMetaNameConst.CUNITID);
    bodyKeys.add(ICPubMetaNameConst.VFREE1);
    bodyKeys.add(ICPubMetaNameConst.VFREE2);
    bodyKeys.add(ICPubMetaNameConst.VFREE3);
    bodyKeys.add(ICPubMetaNameConst.VFREE4);
    bodyKeys.add(ICPubMetaNameConst.VFREE5);
    bodyKeys.add(ICPubMetaNameConst.VFREE6);
    bodyKeys.add(ICPubMetaNameConst.VFREE7);
    bodyKeys.add(ICPubMetaNameConst.VFREE8);
    bodyKeys.add(ICPubMetaNameConst.VFREE9);
    bodyKeys.add(ICPubMetaNameConst.VFREE10);
    bodyKeys.add(ICPubMetaNameConst.VNOTEBODY);
    bodyKeys.add(ICPubMetaNameConst.VBATCHCODE);
    bodyKeys.add(ICPubMetaNameConst.VCHANGERATE);
    bodyKeys.add(ICPubMetaNameConst.TS);
    bodyKeys.add(ICPubMetaNameConst.CPROJECTID);
    bodyKeys.add(ICPubMetaNameConst.CPRODUCTORID);
    bodyKeys.add(ICPubMetaNameConst.CVENDORID);
    bodyKeys.add(MetaNameConst.DBIZDATE);// by 20141203 mengjian
    this.add(itemClazz, bodyKeys.toArray(new String[bodyKeys.size()]));
    this.add(headClazz, headKeys.toArray(new String[headKeys.size()]));
    this.addRelation(itemClazz, MetaNameConst.CGENERALHID, headClazz,
        MetaNameConst.CGENERALHID);
    this.setAttributeAliasName(headClazz, ICPubMetaNameConst.TS, "hts");
    this.setAttributeAliasName(itemClazz, ICPubMetaNameConst.TS, "bts");
  }
}
