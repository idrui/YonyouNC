package nc.pubitf.pu.m20.mmpps.lotreq;

import nc.pubitf.mmpub.sdmanage.IBillTraceResult;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.scmpub.res.billtype.POBillType;

/**
 * �빺���ṩ��"����Ԥ��ά��"�ڵ�ĵ���׷�ݷ�����Ϣ
 * 
 * @since 6.3.1
 * @version 2013-7-29����03:47:47
 * @author fanly3
 */
public class PrayBillTraceResult implements IBillTraceResult {

  private String fromSql;

  private String whereSql;

  public void setFromSql(String fromSql) {
    this.fromSql = fromSql;
  }

  public void setWhereSql(String whereSql) {
    this.whereSql = whereSql;
  }

  @Override
  public String getFrom() {
    return this.fromSql;
  }

  @Override
  public String getWhere() {
    return this.whereSql;
  }

  @Override
  public String getSrcBid() {
    return "po_praybill_b." + PraybillItemVO.CSOURCEBID;
  }

  @Override
  public String getSrcId() {
    return "po_praybill_b." + PraybillItemVO.CSOURCEID;
  }

  @Override
  public String getSrcType() {
    return "po_praybill_b." + PraybillItemVO.CSOURCETYPECODE;
  }

  @Override
  public String getBillBid() {
    return "po_praybill_b." + PraybillItemVO.PK_PRAYBILL_B;
  }

  @Override
  public String getBillId() {
    return "po_praybill." + PraybillHeaderVO.PK_PRAYBILL;
  }

  @Override
  public String getBillType() {
    return POBillType.PrayBill.getCode();
  }

}
