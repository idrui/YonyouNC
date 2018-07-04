package nc.pubimpl.pu.m422x.invp.inv9;

import java.util.HashMap;
import java.util.Map;

import nc.bs.pu.m422x.query.FilterForPlanPosUtil;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.TempTableDefine;
import nc.pubitf.pu.m422x.invp.inv9.IStorereqQueryForInv9;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppViewVO;
import nc.vo.pu.pub.constant.PUEntity;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.NonMetaQuerySchemeProcessor;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 物资需求申请单作为库存计划的需求而提供的查询服务实现类
 * 
 * @since 6.0
 * @version 2010-12-14 下午06:21:00
 * @author duy
 */
public class StorereqQueryForInv9Impl implements IStorereqQueryForInv9 {

  /**
   * 查询物资需求申请单是否已审批
   * 
   * @param pk_req
   * @return Map<String, UFBoolean> Key:表头PK ;Value:是否已审批，true为已审批
   * @throws BusinessException
   */
  @Override
  public Map<String, UFBoolean> getIsApproved(String[] pk_req)
      throws BusinessException {
    try {
      VOQuery<StoreReqAppHeaderVO> query =
          new VOQuery<StoreReqAppHeaderVO>(StoreReqAppHeaderVO.class,
              new String[] {
                StoreReqAppHeaderVO.PK_STOREREQ,
                StoreReqAppHeaderVO.FBILLSTATUS
              });
      StoreReqAppHeaderVO[] vos = query.query(pk_req);

      Map<String, UFBoolean> map = new HashMap<String, UFBoolean>();
      for (StoreReqAppHeaderVO vo : vos) {
        if (POEnumBillStatus.FREE.toInt() != vo.getFbillstatus().intValue()) {
          map.put(vo.getPk_storereq(), UFBoolean.TRUE);
        }
        else {
          map.put(vo.getPk_storereq(), UFBoolean.FALSE);
        }
      }
      return map;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 查询物资需求申请单预计出
   * 
   * @param pk_req
   * @return Map<String, UFDouble>
   *         Key:库存组织+物料
   *         Value: 物资需求申请预计出（本次平衡库存组织所有已平衡、
   *         未关闭的物资需求申请单的（主数量－累计出库数量）合计）
   * @throws BusinessException
   */
  @Override
  public Map<String, UFDouble> getPlanOut(String[] pk_StoreOrgs,
      String[] pk_materials) throws BusinessException {
    try {
      String tempTname = new TempTableDefine().get(pk_StoreOrgs, pk_materials);
      String tabname = " temp_pubapp_2ID";
      SqlBuilder sql = new SqlBuilder();
      sql.append("select " + tabname + ".id1," + tabname + ".id2,");
      sql.append("sum(isnull(b.nnum,0)-isnull(b.naccuoutnum,0))");
      sql.append(" from " + tempTname + " " + tabname);
      sql.append(" left join  " + PUEntity.M422X_B_TABLE + " b ");
      sql.append(" on b." + StoreReqAppItemVO.PK_NEXTBALANCEORG + " = "
          + tabname + ".id1");
      sql.append(" and b." + StoreReqAppItemVO.PK_SRCMATERIAL + " = "
          + tabname + ".id2");
      sql.append(" and b.dr", 0);
      sql.append(" and b." + StoreReqAppItemVO.BENDGATHER, UFBoolean.TRUE);
      sql.append(" and b." + StoreReqAppItemVO.BCLOSE, UFBoolean.FALSE);
      sql.append(" group by " + tabname + ".id1," + tabname + ".id2");

      DataAccessUtils util = new DataAccessUtils();
      String[][] values =
          util.query(sql.toString()).toTwoDimensionStringArray();
      Map<String, UFDouble> map = new HashMap<String, UFDouble>();
      for (String[] value : values) {
        map.put(value[0] + value[1], new UFDouble(value[2]));
      }
      return map;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 物资需求平衡查询物资需求申请单
   * 
   * @param conMap<Map<String key,UFDouble value>>
   * @return StoreReqAppViewVO[]
   */
  @SuppressWarnings("unchecked")
  @Override
  public StoreReqAppViewVO[] getReq(IQueryScheme queryScheme)
      throws BusinessException {

    // 需要转换为实际表的查询
    NonMetaQuerySchemeProcessor processor =
        new NonMetaQuerySchemeProcessor(StoreReqAppHeaderVO.class,
            (Map<String, String>) queryScheme.get("422X"), queryScheme);

    StringBuilder fromWhere = new StringBuilder();
    fromWhere.append(processor.getFinalFromWhere());
    fromWhere.append(" and po_storereq.fbillstatus = '"
        + POEnumBillStatus.APPROVE.value() + "' ");
    DataAccessUtils dataAccessUtils = new DataAccessUtils();
    String sql = this.getQuerySql(fromWhere.toString(), new QuerySchemeProcessor(queryScheme));
    // 根据查询条件得到主表ID数组
    String[] itemIDs = dataAccessUtils.query(sql).toOneDimensionStringArray();
    if (ArrayUtils.isEmpty(itemIDs)) {
      return null;
    }
    StoreReqAppViewVO[] views =
        new ViewQuery<StoreReqAppViewVO>(StoreReqAppViewVO.class)
            .query(itemIDs);

    if (ArrayUtils.isEmpty(views)) {
      return null;
    }
    return views;
  }

  private String getQuerySql(String sqlWhere,  QuerySchemeProcessor pro) {
    if (StringUtils.isEmpty(sqlWhere)) {
      return null;
    }
    String alias = "po_storereq_b";
    String bd = "bd_material";
    // 加入物料表连接
    String joinMaterial =
        "inner join bd_material " + bd + " on " + alias + "."
            + StoreReqAppItemVO.PK_MATERIAL + " = " + bd + "."
            + MaterialVO.PK_MATERIAL;
    String newSqlWhere =
        sqlWhere.substring(0, sqlWhere.indexOf("where"))
            + joinMaterial
            + sqlWhere.substring(sqlWhere.indexOf("where") - 1,
                sqlWhere.length());
    SqlBuilder builder = new SqlBuilder();
    builder.append("select " + alias + ".pk_storereq_b ");
    builder.append(newSqlWhere);
    builder.append(" and " + alias + ".dr", 0);
    builder.append(" and isnull(" + alias + ".nnum,0) > 0 ");
    builder.append(" and isnull(" + alias + ".bclose,'N')", "N");
    
    // 按计划岗过滤物料
    FilterForPlanPosUtil.filterMaterialByPos(pro, builder);
    
    // 应该按照物料编码+需求日期+库存组织升序排序，请检查
    builder.append(" order by ");
    builder.append(bd + "." + MaterialVO.CODE);
    builder.append(" , ");
    builder.append(alias + "." + StoreReqAppItemVO.DREQDATE);
    builder.append(" , ");
    builder.append(alias + "." + StoreReqAppItemVO.PK_ORG);
    builder.append(" asc");
    return builder.toString();
  }

}
