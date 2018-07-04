package nc.pubitf.pu.m20.mmpps;

import nc.pubitf.mmpub.sdmanage.ISupplyResult;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.pub.constant.PUMDValue;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * 请购单给离散制造提供的供给单据信息
 * 
 * @since 6.3.1
 * @version 2013-8-2上午08:49:47
 * @author fanly3
 */
public class PrayBillSupplyMapVO implements ISupplyResult {

  @Override
  public String getBatchCode() {
    return "po_praybill_b." + PraybillItemVO.VBATCHCODE;
  }

  @Override
  public String getBoutendflag() {
    // 请购单没有出库关闭字段,请购单有行关闭字段，与制造Chenjij确认返回行关闭字段
    return "po_praybill_b." + PraybillItemVO.BROWCLOSE;
  }

  @Override
  public String getCastunitid() {
    return "po_praybill_b." + PraybillItemVO.CASTUNITID;
  }

  @Override
  public String getCcustmaterialid() {
    return null;
  }

  @Override
  public String getCffileid() {
    return "po_praybill_b." + PraybillItemVO.CFFILEID;
  }

  @Override
  public String getCunitid() {
    return "po_praybill_b." + PraybillItemVO.CUNITID;
  }

  @Override
  public String getCustomerid() {
    return "po_praybill_b." + PraybillItemVO.CASSCUSTID;
  }

  @Override
  public String getDbilldate() {
    return "po_praybill." + PraybillHeaderVO.DBILLDATE;
  }

  @Override
  public String getFirstBid() {
    return "po_praybill_b." + PraybillItemVO.CFIRSTBID;
  }

  @Override
  public String getFirstCode() {
    return "po_praybill_b." + PraybillItemVO.VFIRSTCODE;
  }

  @Override
  public String getFirstId() {
    return "po_praybill_b." + PraybillItemVO.CFIRSTID;
  }

  @Override
  public String getFirstRowNo() {
    return "po_praybill_b." + PraybillItemVO.VFIRSTROWNO;
  }

  @Override
  public String getFirstType() {
    return "po_praybill_b." + PraybillItemVO.CFIRSTTYPECODE;
  }

  @Override
  public String getFree1() {
    return "po_praybill_b." + PraybillItemVO.VFREE1;
  }

  @Override
  public String getFree10() {
    return "po_praybill_b." + PraybillItemVO.VFREE10;
  }

  @Override
  public String getFree2() {
    return "po_praybill_b." + PraybillItemVO.VFREE2;
  }

  @Override
  public String getFree3() {
    return "po_praybill_b." + PraybillItemVO.VFREE3;
  }

  @Override
  public String getFree4() {
    return "po_praybill_b." + PraybillItemVO.VFREE4;
  }

  @Override
  public String getFree5() {
    return "po_praybill_b." + PraybillItemVO.VFREE5;
  }

  @Override
  public String getFree6() {
    return "po_praybill_b." + PraybillItemVO.VFREE6;
  }

  @Override
  public String getFree7() {
    return "po_praybill_b." + PraybillItemVO.VFREE7;
  }

  @Override
  public String getFree8() {
    return "po_praybill_b." + PraybillItemVO.VFREE8;
  }

  @Override
  public String getFree9() {
    return "po_praybill_b." + PraybillItemVO.VFREE9;
  }

  @Override
  public String getFrom() {
    StringBuilder fromSql = new StringBuilder();
    fromSql.append(" po_praybill_b po_praybill_b ");
    fromSql
        .append(" inner join po_praybill po_praybill on po_praybill.pk_praybill = po_praybill_b.pk_praybill ");
    return fromSql.toString();
  }

  @Override
  public String getMaterialid() {
    return "po_praybill_b." + PraybillItemVO.PK_SRCMATERIAL;
  }

  @Override
  public String getMaterialvid() {
    return "po_praybill_b." + PraybillItemVO.PK_MATERIAL;
  }

  @Override
  public String getNastnum() {
    return "po_praybill_b." + PraybillItemVO.NASTNUM;
  }

  @Override
  public String getNexenum() {
    return "po_praybill_b." + PraybillItemVO.NACCUMULATENUM;
  }

  @Override
  public String getNnum() {
    return "po_praybill_b." + PraybillItemVO.NNUM;
  }

  @Override
  public String getPk_BatchCode() {
    return "po_praybill_b." + PraybillItemVO.PK_BATCHCODE;
  }

  @Override
  public String getPprojectid() {
    return "po_praybill_b." + PraybillItemVO.CPROJECTID;
  }

  @Override
  public String getProductorid() {
    return "po_praybill_b." + PraybillItemVO.CPRODUCTORID;
  }

  @Override
  public String getReservatioNnum() {
    return null;
  }

  @Override
  public String getSrcBid() {
    return "po_praybill_b." + PraybillItemVO.CSOURCEBID;
  }

  @Override
  public String getSrcCode() {
    return "po_praybill_b." + PraybillItemVO.VSOURCECODE;
  }

  @Override
  public String getSrcId() {
    return "po_praybill_b." + PraybillItemVO.CSOURCEID;
  }

  @Override
  public String getSrcRowNo() {
    return "po_praybill_b." + PraybillItemVO.VSOURCEROWNO;
  }

  @Override
  public String getSrcType() {
    return "po_praybill_b." + PraybillItemVO.CSOURCETYPECODE;
  }

  @Override
  public String getSupplybid() {
    return "po_praybill_b." + PraybillItemVO.PK_PRAYBILL_B;
  }

  @Override
  public String getSupplyCode() {
    return "po_praybill." + PraybillHeaderVO.VBILLCODE;
  }

  @Override
  public String getSupplyDate() {
    return "po_praybill_b." + PraybillItemVO.DREQDATE;
  }

  @Override
  public String getSupplyid() {
    return "po_praybill." + PraybillHeaderVO.PK_PRAYBILL;
  }

  @Override
  public String getSupplyNnum() {
    String nnumExpress =
        "(isnull(" + "po_praybill_b." + PraybillItemVO.NNUM + ", 0) - isnull("
            + "po_praybill_b." + PraybillItemVO.NACCUMULATENUM + ", 0))";
    return nnumExpress;
  }

  @Override
  public String getSupplyOrgid() {
    return "po_praybill_b." + PraybillItemVO.PK_ORG;
  }

  @Override
  public String getSupplyOrgvid() {
    return "po_praybill_b." + PraybillItemVO.PK_ORG_V;
  }

  @Override
  public String getSupplyRowNo() {
    return "po_praybill_b." + PraybillItemVO.CROWNO;
  }

  @Override
  public String getSupplyTypeCodeValue() {
    return POBillType.PrayBill.getCode();
  }

  @Override
  public String getSupplyTypeIdValue() {
    return POBillType.PrayBill.getCode();
  }

  @Override
  public String getTranType() {
    return "po_praybill." + PraybillHeaderVO.CTRANTYPEID;
  }

  @Override
  public String getVBillStatus() {
    return "po_praybill." + PraybillHeaderVO.FBILLSTATUS;
  }

  @Override
  public String getVBillStatusEnumID() {
    return PUMDValue.PrayBillStatus.value();
  }

  @Override
  public String getVchangerate() {
    return "po_praybill_b." + PraybillItemVO.VCHANGERATE;
  }

  @Override
  public String getVendorid() {
    return null;
  }

  @Override
  public String getWhere() {
    // 主数量表达式
    String nnumExpress =
        "(isnull(" + "po_praybill_b." + PraybillItemVO.NNUM + ", 0) - isnull("
            + "po_praybill_b." + PraybillItemVO.NACCUMULATENUM + ", 0))";
    StringBuilder whereSql = new StringBuilder();
    whereSql.append(" po_praybill.dr = 0 and po_praybill_b.dr = 0 ");
    whereSql.append(" and po_praybill.bislatest = 'Y' ");
    whereSql.append(" and po_praybill_b.browclose = 'N' ");
    whereSql.append(" and " + nnumExpress + " > 0 ");
    return whereSql.toString();
  }

}
