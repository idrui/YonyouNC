/**
 * $文件说明$
 *
 * @author gaogr
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-15 下午02:29:21
 */
package nc.bs.pu.m20.alert;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.pub.pa.IPreAlertPlugin;
import nc.bs.pub.pa.PreAlertContext;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.pa.PreAlertReturnType;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDExQueryBuilder;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.ml.LanguageVO;
import nc.vo.ml.MultiLangContext;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.enumeration.EnumPraySource;
import nc.vo.pu.pub.alert.PuAlertDataSource;
import nc.vo.pu.pub.constant.PUTempTable;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.util.ObjectUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>已审批完成请购单预警类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-10-15 下午02:29:21
 */
public class PrayAudittedNotCreateOrderAlart implements IPreAlertPlugin {
  static class PrayAudittedNotCreateOrderDataSource extends PuAlertDataSource {
    private static final long serialVersionUID = -5081346971196385797L;

    private Set<String> multiLangItems;

    public PrayAudittedNotCreateOrderDataSource() {
      super();
      this.multiLangItems = new HashSet<String>();
      this.multiLangItems.add(PraybillHeaderVO.CTRANTYPEID);
      this.multiLangItems.add(PraybillHeaderVO.PK_PLANPSN);
      this.multiLangItems.add(PraybillHeaderVO.PK_PLANDEPT);
      this.multiLangItems.add(PraybillHeaderVO.PK_ORG);
      this.multiLangItems.add(PraybillHeaderVO.BILLMAKER);
      this.multiLangItems.add(PraybillHeaderVO.APPROVER);
    }

    @Override
    public String[] getAllDataItemExpress() {
      return new String[] {
        PrayAudittedNotCreateOrderAlart.ROWNO, PraybillHeaderVO.VBILLCODE,
        PraybillHeaderVO.DBILLDATE, PraybillHeaderVO.FPRAYSOURCE,
        PraybillHeaderVO.CTRANTYPEID, PraybillHeaderVO.PK_PLANPSN,
        PraybillHeaderVO.PK_PLANDEPT, PraybillHeaderVO.PK_ORG,
        PraybillHeaderVO.VMEMO, PraybillHeaderVO.BILLMAKER,
        PraybillHeaderVO.DMAKEDATE, PraybillHeaderVO.APPROVER,
        PraybillHeaderVO.TAUDITTIME
      };
    }

    @Override
    protected Set<String> getMultiLangItems() {
      return this.multiLangItems;
    }
  }

  /**
   * 序号
   */
  public static final String ROWNO = "rowno";

  /**
   * 父类方法重写
   * 
   * @see nc.bs.pub.pa.IPreAlertPlugin#executeTask(nc.bs.pub.pa.PreAlertContext)
   */
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

  private String[][] getData(String[] pk_orgs) {
    StringBuffer sbSql = new StringBuffer("select distinct '' rowno, ");
    sbSql.append("h.vbillcode,h.dbilldate,h.fpraysource,");
    sbSql
        .append("bd_billtype.billtypename,bd_billtype.billtypename2,bd_billtype.billtypename3,bd_billtype.billtypename4,bd_billtype.billtypename5,bd_billtype.billtypename6,");
    sbSql
        .append("bd_psndoc.name,bd_psndoc.name2,bd_psndoc.name3,bd_psndoc.name4,bd_psndoc.name5,bd_psndoc.name6,");
    sbSql
        .append("org_dept.name,org_dept.name2,org_dept.name3,org_dept.name4,org_dept.name5,org_dept.name6,");
    sbSql.append("org.name,org.name2,org.name3,org.name4,org.name5,org.name6,");
    sbSql.append("h.vmemo,");
    sbSql
        .append("sm_user1.user_name,sm_user1.user_name2,sm_user1.user_name3,sm_user1.user_name4,sm_user1.user_name5,sm_user1.user_name6,");
    sbSql.append("h.creationtime, ");
    sbSql
        .append("sm_user2.user_name,sm_user2.user_name2,sm_user2.user_name3,sm_user2.user_name4,sm_user2.user_name5,sm_user2.user_name6");
    sbSql.append(",h.taudittime ");
    // 子表
    sbSql
        .append("from po_praybill h inner join po_praybill_b b on h.pk_praybill = b.pk_praybill ");
    sbSql
        .append(" inner join bd_billtype bd_billtype on h.ctrantypeid = bd_billtype.pk_billtypeid ");
    // 请购人
    sbSql
        .append("LEFT OUTER JOIN bd_psndoc ON h.pk_planpsn = bd_psndoc.pk_psndoc ");
    // 请购部门
    sbSql
        .append("LEFT OUTER JOIN org_dept ON h.pk_plandept = org_dept.pk_dept ");
    // 库存组织
    sbSql
        .append("LEFT OUTER JOIN org_stockorg org ON h.pk_org = org.pk_stockorg ");
    // 制单人
    sbSql
        .append("inner join sm_user  sm_user1 ON h.billmaker = sm_user1.cuserid ");
    // 审批人
    sbSql
        .append("inner join sm_user  sm_user2 ON h.approver = sm_user2.cuserid ");
    // 未生成订单
    sbSql.append("where coalesce(b.naccumulatenum,0) = 0 ");
    // 库存组织
    IDExQueryBuilder builder =
        new IDExQueryBuilder(PUTempTable.tmp_po_20_12.name());
    sbSql.append(builder.buildSQL(" and h.pk_org ", pk_orgs));
    // 请购单审批通过
    sbSql.append("and h.fbillstatus = " + POEnumBillStatus.APPROVE.toInt());
    // 请购单未作废
    sbSql.append(" and h.dr = 0 and b.dr = 0 ");
    // 排除已经生成过订单的请购单
    sbSql.append(" and h.pk_praybill not in ");
    sbSql.append(" (select distinct h.pk_praybill ");
    sbSql.append(" from po_praybill h inner join po_praybill_b b ");
    sbSql.append(" on b.pk_praybill = h.pk_praybill ");

    sbSql.append(" where h.fbillstatus = " + POEnumBillStatus.APPROVE.toInt());
    IDExQueryBuilder builder2 =
        new IDExQueryBuilder(PUTempTable.tmp_po_20_13.name());
    sbSql.append(builder2.buildSQL(" and h.pk_org ", pk_orgs));
    sbSql
        .append(" and h.dr = 0 and coalesce(b.naccumulatenum,0) > 0 and b.dr = 0) ");

    String[][] datas =
        new DataAccessUtils().query(sbSql.toString())
            .toTwoDimensionStringArray();

    if (ArrayUtils.isEmpty(datas)) {
      return null;
    }

    TimeZone tz =
        TimeZone.getTimeZone(InvocationInfoProxy.getInstance().getTimeZone());
    for (int i = 0, len = datas.length; i < len; i++) {
      datas[i][0] = String.valueOf(i);
      datas[i][2] = new UFDate(datas[i][2]).toStdString(tz);
      datas[i][35] = new UFDate(datas[i][35]).toStdString(tz);
      datas[i][42] = new UFDate(datas[i][42]).toStdString(tz);
    }
    return datas;
  }

  private String[][] getDatas(PreAlertContext context) {
    UFDate loginDate = AppContext.getInstance().getBusiDate();
    String[] pk_orgs = context.getPk_orgs();

    if (ObjectUtil.isEmptyWithTrim(loginDate) || pk_orgs == null
        || pk_orgs.length == 0) {
      return null;
    }
    return this.getData(pk_orgs);
  }

  private PrayAudittedNotCreateOrderDataSource getDataSource(String[][] values) {
    PrayAudittedNotCreateOrderDataSource datasource =
        new PrayAudittedNotCreateOrderDataSource();
    datasource.putDataByMuttiLangItems(values);
    return datasource;
  }

  /**
   * 根据语种获取请购来源的多语内容
   * 
   * @param praysource
   * @param langCode
   * @return
   */
  private String getPraySource(String praysource, String langCode) {
    int ipraysource = Integer.valueOf(praysource).intValue();
    if (EnumPraySource.MANUAL.toInt() == ipraysource) {
      return nc.bs.ml.NCLangResOnserver.getInstance().getString(langCode,
          "4004020_2", null, "2400402001-0044");
    }
    else if (EnumPraySource.SO.toInt() == ipraysource) {
      return nc.bs.ml.NCLangResOnserver.getInstance().getString(langCode,
          "4004020_2", null, "2400402001-0052");
    }
    else if (EnumPraySource.M5X.toInt() == ipraysource) {
      return nc.bs.ml.NCLangResOnserver.getInstance().getString(langCode,
          "4004020_2", null, "2400402001-0051");
    }
    else if (EnumPraySource.MPO.toInt() == ipraysource) {
      return nc.bs.ml.NCLangResOnserver.getInstance().getString(langCode,
          "4004020_2", null, "2400402001-0000");
    }
    else if (EnumPraySource.MPS.toInt() == ipraysource) {
      return nc.bs.ml.NCLangResOnserver.getInstance().getString(langCode,
          "4004020_2", null, "2400402001-0050");
    }
    else if (EnumPraySource.MO.toInt() == ipraysource) {
      return nc.bs.ml.NCLangResOnserver.getInstance().getString(langCode,
          "common_ncmd", null, "2UC000-000625");
    }
    else if (EnumPraySource.ICPO.toInt() == ipraysource) {
      return nc.bs.ml.NCLangResOnserver.getInstance().getString(langCode,
          "4004020_2", null, "2400402001-0057");
    }
    else if (EnumPraySource.M4B36.toInt() == ipraysource) {
      return nc.bs.ml.NCLangResOnserver.getInstance().getString(langCode,
          "4004020_2", null, "2400402001-0003");
    }
    else if (EnumPraySource.M20.toInt() == ipraysource) {
      return nc.bs.ml.NCLangResOnserver.getInstance().getString(langCode,
          "4004020_2", null, "2400402001-0065");
    }

    return null;
  }

  /**
   * 请购来源多语处理，根据语言设置返回各个语言的内容
   * 
   * @param praysources 请购来源存储值
   * @return map结构：key-语种序号，对应唯一语种；value-语种下的多语内容
   */
  private Map<Integer, Map<String, String>> getPraySourceMap(
      String[] praysources) {
    // 当前环境所有语种
    LanguageVO[] langvos = MultiLangContext.getInstance().getEnableLangVOs();
    Map<Integer, Map<String, String>> multiLangValue =
        new HashMap<Integer, Map<String, String>>();
    for (LanguageVO vo : langvos) {
      Map<String, String> map = new HashMap<String, String>();
      // 按语种存放多语内容
      if (vo.getLangseq() != null) {
        for (String praysource : praysources) {
          map.put(praysource, this.getPraySource(praysource, vo.getLangcode()));
        }
        multiLangValue.put(vo.getLangseq(), map);
      }
    }
    return multiLangValue;
  }

  private PreAlertObject getPreAlertObject(PreAlertContext context) {
    PreAlertObject retObj = new PreAlertObject();
    String[][] values = this.getDatas(context);
    if (null == values || values.length == 0) {
      retObj.setReturnType(PreAlertReturnType.RETURNNOTHING);
    }
    else {
      retObj.setReturnType(PreAlertReturnType.RETURNDATASOURCE);
      PrayAudittedNotCreateOrderDataSource datasource =
          this.getDataSource(values);
      this.setPraySource(datasource, values);
      retObj.setReturnObj(datasource);
    }
    return retObj;
  }

  /**
   * 请购来源多语处理
   * 
   * @param datasource
   * @param datas
   */
  private void setPraySource(PrayAudittedNotCreateOrderDataSource datasource,
      String[][] datas) {
    Set<String> set = new HashSet<String>();
    for (String[] data : datas) {
      if (!StringUtil.isEmptyWithTrim(data[3])) {
        set.add(data[3]);
      }
    }
    if (set.size() == 0) {
      return;
    }

    String[] praysources = set.toArray(new String[set.size()]);
    // 请购来源多语
    Map<Integer, Map<String, String>> praysourceMap =
        this.getPraySourceMap(praysources);

    int length = datas.length;
    for (int i = 0; i < length; ++i) {
      if (datas[i][3] != null) {// 对应请购来源
        for (Entry<Integer, Map<String, String>> entry : praysourceMap
            .entrySet()) {
          datasource.putDataByLangSeqNo(entry.getKey(),
              PraybillHeaderVO.FPRAYSOURCE, entry.getValue().get(datas[i][3]),
              i);
        }
      }
    }

  }

}
