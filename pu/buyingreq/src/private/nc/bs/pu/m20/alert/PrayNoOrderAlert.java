package nc.bs.pu.m20.alert;

import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.pu.alert.PUAlertConst;
import nc.bs.pu.m20.query.logicutil.CondTOWhereUtil;
import nc.bs.pub.pa.IPreAlertPlugin;
import nc.bs.pub.pa.PreAlertContext;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.pa.PreAlertReturnType;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.pub.alert.PuAlertDataSource;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * ���鶩������δ�����빺��Ԥ����
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>���鶩������δ�����빺��Ԥ��
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-10-8 ����02:57:22
 */
public class PrayNoOrderAlert implements IPreAlertPlugin {
  static class PrayNoOrderAlertDataSource extends PuAlertDataSource {
    private static final long serialVersionUID = -5081346971196385797L;

    private Set<String> multiLangItems;

    public PrayNoOrderAlertDataSource() {
      super();
      this.multiLangItems = new HashSet<String>();
      this.multiLangItems.add(PraybillHeaderVO.PK_PLANPSN);
      this.multiLangItems.add(PrayNoOrderAlert.PK_MATERIAL_NAME);
      this.multiLangItems.add(PraybillItemVO.CUNITID);
    }

    @Override
    public String[] getAllDataItemExpress() {
      return new String[] {
        PraybillHeaderVO.VBILLCODE, PraybillHeaderVO.PK_PLANPSN,
        PraybillItemVO.PK_MATERIAL, PrayNoOrderAlert.PK_MATERIAL_NAME,
        PraybillItemVO.CUNITID, PraybillItemVO.NNUM,
        PrayNoOrderAlert.NCANPONUM, PraybillItemVO.DSUGGESTDATE,
        PrayNoOrderAlert.DDELAYDATE
      };
    }

    @Override
    protected Set<String> getMultiLangItems() {
      return this.multiLangItems;
    }
  }

  /**
   * ��������
   */
  public static final String DDELAYDATE = "ddelaydate";

  /**
   * δ���ɶ�������
   */
  public static final String NCANPONUM = "ncanponum";

  /**
   * ��������
   */
  public static final String PK_MATERIAL_NAME = "pk_material_name";

  @Override
  public PreAlertObject executeTask(PreAlertContext context)
      throws BusinessException {
    try {
      return this.getPreAlertObject(context);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  private String[][] getData(String[] pk_orgs, int iDays, UFDate dLoginDate) {
    StringBuffer sql = new StringBuffer("select distinct ");
    sql.append(" h.vbillcode,");
    sql.append("psn.name,psn.name2,psn.name3,psn.name4,psn.name5,psn.name6,");
    sql.append("invbas.code,");
    sql.append("invbas.name,invbas.name2,invbas.name3,invbas.name4,invbas.name5,invbas.name6,");
    sql.append("meas.name,meas.name2,meas.name3,meas.name4,meas.name5,meas.name6,");
    sql.append(" b.nnum,");
    sql.append(" coalesce(b.nnum,0) - coalesce(b.naccumulatenum,0), ");
    sql.append(" b.dsuggestdate, '' ");
    sql.append(" from po_praybill_b b inner join po_praybill h on h.pk_praybill = b.pk_praybill ");
    sql.append(" left outer join bd_psndoc psn on psn.pk_psndoc = h.pk_planpsn ");
    sql.append(" inner join bd_material invbas on invbas.pk_material = b.pk_material ");
    sql.append(" inner join bd_measdoc meas on meas.pk_measdoc = b.cunitid ");
    sql.append(" where ");
    // �ۼƶ������� < �빺����
    sql.append(" coalesce(b.nnum,0) - coalesce(b.naccumulatenum,0) > 0 ");
    //---�ϲ�ͨ�油��NCdp205495255   2015-10-14 zhangshqb---start
    // �����йرյ��빺��
    sql.append(" and  b.browclose = 'N' ");
    //---�ϲ�ͨ�油��NCdp205495255   2015-10-14 zhangshqb---end
    // �빺������ͨ��
    sql.append(" and h.fbillstatus = " + POEnumBillStatus.APPROVE.toIntValue());
    // �빺��λ����
    sql.append(" and h.dr = 0 and b.dr = 0 ");
    
    // ����
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_20_11.name());
    sql.append(builder.buildSQL(" and h.pk_org ", pk_orgs));
    // ���鶩������
    String sd = dLoginDate.getDateBefore(iDays).asEnd().toPersisted();
    sql.append(" and b.dsuggestdate <= '" + sd + "' ");
    // �빺���Ŵ���
    CondTOWhereUtil.buildIsarrange(sql, "h", "b");

    String[][] datas =
        new DataAccessUtils().query(sql.toString()).toTwoDimensionStringArray();

    TimeZone zone =
        TimeZone.getTimeZone(InvocationInfoProxy.getInstance().getTimeZone());
    for (String[] data : datas) {
      data[22] = new UFDate(data[22]).toStdString(zone);
      data[23] = String.valueOf(dLoginDate.getDaysAfter(new UFDate(data[22])));
    }

    return datas;
  }

  private String[][] getDatas(PreAlertContext context) {
    UFDate loginDate = AppContext.getInstance().getBusiDate();
    Object odays = context.getKeyMap().get(PUAlertConst.DAYS);
    String[] pk_orgs = context.getPk_orgs();

    if (ObjectUtil.isEmptyWithTrim(odays) || pk_orgs == null
        || pk_orgs.length == 0) {
      return null;
    }

    return this.getData(pk_orgs, Integer.parseInt(odays.toString()), loginDate);
  }

  private PrayNoOrderAlertDataSource getDataSource(String[][] values) {
    PrayNoOrderAlertDataSource datasource = new PrayNoOrderAlertDataSource();
    datasource.putDataByMuttiLangItems(values);
    return datasource;
  }

  private PreAlertObject getPreAlertObject(PreAlertContext context) {
    PreAlertObject retObj = new PreAlertObject();
    String[][] values = this.getDatas(context);
    if (null == values || values.length == 0) {
      retObj.setReturnType(PreAlertReturnType.RETURNNOTHING);
    }
    else {
      retObj.setReturnType(PreAlertReturnType.RETURNDATASOURCE);
      PrayNoOrderAlertDataSource datasource = this.getDataSource(values);
      retObj.setReturnObj(datasource);
    }
    return retObj;
  }

}
