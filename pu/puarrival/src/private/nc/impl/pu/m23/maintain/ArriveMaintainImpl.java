package nc.impl.pu.m23.maintain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.impl.pu.m23.maintain.action.ArriveDeleteAction;
import nc.impl.pu.m23.maintain.action.ArriveInsertAction;
import nc.impl.pu.m23.maintain.action.ArriveQueryAction;
import nc.impl.pu.m23.maintain.action.ArriveUpdateAction;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.pu.m23.maintain.IArriveMaintain;
import nc.itf.scmpub.reference.uap.bd.material.MaterialStockClassPubService;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.bd.material.stock.MaterialStockVO;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.entity.ArriveViewVO;
import nc.vo.pu.m23.enumeration.POCheckStatus;
import nc.vo.pu.m23.env.ArrivalUIToBSEnv;
import nc.vo.pu.pub.util.VOSortUtils;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.qc.pub.util.QCSysParamUtil;
import nc.vo.scmpub.util.FromWhereParseUtil;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>到货单的基本操作实现类，本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单的新增保存
 * <li>到货单的修改保存
 * <li>到货单的删除
 * <li>到货单的查询
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-11 下午02:23:08
 */

public class ArriveMaintainImpl implements IArriveMaintain {

  @Override
  public void deleteArrive(ArriveVO[] aggVOArray, ArrivalUIToBSEnv env)
      throws BusinessException {
    try {
      // 该版本暂不支持前后台问答方式,下版支持,代码保留,直接不检查ATP(ATPService中处理)
      // this.examATPCheck(env);
      ArriveDeleteAction action = new ArriveDeleteAction();
      action.deleteArrive(aggVOArray);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

  @Override
  public ArriveVO[] insert(ArriveVO[] aggVOArray, ArrivalUIToBSEnv env)
      throws BusinessException {
    try {
      ArriveInsertAction action = new ArriveInsertAction();

      return action.insertArrive(aggVOArray, env);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public ArriveVO[] queryArrive(IQueryScheme queryScheme)
      throws BusinessException {
    try {
      ArriveQueryAction action = new ArriveQueryAction();
      ArriveVO[] vos = action.lazyQuery(this.getWholeQueryScheme(queryScheme));
      return vos;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  /**
   * modify by liugxa
   * 2011年7月7日11:27:41
   */
  @Override
  public ArriveViewVO[] queryCheckArrive(IQueryScheme queryScheme)
      throws BusinessException {
    try {
      List<String> itemids = new ArrayList<String>();
      DataAccessUtils utils = new DataAccessUtils();
      IRowSet rowset = utils.query(this.createArriveCheckKeysSql(queryScheme));
      // 免检的存货不查询出来
      if (this.getIsCheck(queryScheme)) {
        itemids = this.getBids(rowset);
      }
      // 免检的存货查询出来
      else {
        while (rowset.next()) {
          String bid = rowset.getString(0);
          if (StringUtils.isNotEmpty(bid) && !itemids.contains(bid)) {
            itemids.add(bid);
          }
        }
      }
      if (itemids.size() == 0) {
        return null;
      }
      // 查询到货单的视图VO
      String[] bodyIds = itemids.toArray(new String[0]);
      ArriveViewVO[] arriveviews =
          new ViewQuery<ArriveViewVO>(ArriveViewVO.class).query(bodyIds);
      String pk_org = arriveviews[0].getHVO().getPk_org();
      if (!SysInitGroupQuery.isQCEnabled()
          || UFBoolean.FALSE.equals(ValueUtils.getUFBoolean(QCSysParamUtil
              .getINI01(pk_org)))) {
        UFDouble nchecknum = null;
        for (int i = 0; i < arriveviews.length; i++) {
          ArriveItemVO itemVo = arriveviews[i].getBVO();
          nchecknum =
              MathTool.sub(
                  MathTool.sub(itemVo.getNnum(), itemVo.getNaccumchecknum()),
                  itemVo.getNaccumbacknum());
          itemVo.setNchecknum(nchecknum);
          itemVo.setNwillelignum(nchecknum);
          itemVo.setNwillnotelignum(UFDouble.ZERO_DBL);
        }
      }
      VOSortUtils.sortVOs(arriveviews, ArriveHeaderVO.VBILLCODE);
      return arriveviews;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public ArriveViewVO[] queryCheckArrive(String whereSql)
      throws BusinessException {
    try {
      FromWhereParseUtil util = new FromWhereParseUtil(whereSql);
      String btalbe = util.getTableAlias("po_arriveorder_b");
      StringBuilder sql = new StringBuilder();
      sql.append(" select distinct(");
      sql.append(btalbe);
      sql.append(".pk_arriveorder_b) ");
      sql.append(whereSql);

      List<String> itemids = new ArrayList<String>();
      DataAccessUtils utils = new DataAccessUtils();
      IRowSet rowset = utils.query(sql.toString());
      while (rowset.next()) {
        String bid = rowset.getString(0);
        if (StringUtils.isNotEmpty(bid) && !itemids.contains(bid)) {
          itemids.add(bid);
        }
      }
      if (itemids.size() == 0) {
        return null;
      }
      // 查询到货单的视图VO
      String[] bodyIds = itemids.toArray(new String[0]);
      ArriveViewVO[] arriveviews =
          new ViewQuery<ArriveViewVO>(ArriveViewVO.class).query(bodyIds);
      VOSortUtils.sortVOs(arriveviews, ArriveHeaderVO.VBILLCODE);
      return arriveviews;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

  @Override
  public ArriveVO[] refreshItems(ArriveVO[] vos) throws BusinessException {
    try {
      return new ArriveQueryAction().refreshQuery(vos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public ArriveVO[] saveBase(ArriveVO[] aggVOArray, ArrivalUIToBSEnv[] envs)
      throws BusinessException {
    try {
      List<ArriveVO> insertVOList = new ArrayList<ArriveVO>();
      List<ArriveVO> updateVOList = new ArrayList<ArriveVO>();
      ArrivalUIToBSEnv env =
          ArrayUtils.isEmpty(envs) ? new ArrivalUIToBSEnv() : envs[0];
      // 筛选出新增、修改的单据
      for (int i = 0, len = aggVOArray.length; i < len; i++) {
        if (StringUtils.isEmpty(aggVOArray[i].getPrimaryKey())) {
          insertVOList.add(aggVOArray[i]);
        }
        else {
          updateVOList.add(aggVOArray[i]);
        }
      }
      if (insertVOList.size() != 0) {
        return NCLocator.getInstance().lookup(IArriveMaintain.class)
            .insert(insertVOList.toArray(new ArriveVO[0]), env);
      }
      if (updateVOList.size() != 0) {
        return NCLocator.getInstance().lookup(IArriveMaintain.class)
            .update(updateVOList.toArray(new ArriveVO[0]), env);
      }
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public ArriveVO[] update(ArriveVO[] aggVOArray, ArrivalUIToBSEnv env)
      throws BusinessException {
    try {
      ArriveUpdateAction action = new ArriveUpdateAction();
      return action.updateArrive(aggVOArray, env);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  private String createArriveCheckKeysSql(IQueryScheme queryScheme) {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    String tableAlias =
        qrySchemeProcessor
            .getTableAliasOfAttribute("pk_arriveorder_b.pk_arriveorder_b");
    StringBuilder wholeSql = new StringBuilder();
    wholeSql.append("select distinct " + tableAlias + ".pk_arriveorder_b ");
    if (this.getIsCheck(queryScheme)) {
      wholeSql.append("," + tableAlias + ".pk_material ");
      wholeSql.append("," + tableAlias + ".pk_org ");
    }
    String pk_group = new BSContext().getGroupID();
    qrySchemeProcessor.appendWhere(" and " + tableAlias + ".pk_group = '"
        + pk_group + "' ");
    this.getlogicWhr(qrySchemeProcessor, wholeSql);

    return wholeSql.toString();
  }

  // private void examATPCheck(ArrivalUIToBSEnv env) {
  // // 不做ATP检查
  // if (UFBoolean.TRUE.equals(env.getbConfirm())) {
  // ATPUpdate bo = NCLocator.getInstance().lookup(ATPUpdate.class);
  // bo.abandonATPCheck();
  // }
  // }

  /**
   * 免检的存货不查询出来
   * 
   * @param itemids
   * @param rowset
   * @author liugxa
   */
  private List<String> getBids(IRowSet rowset) {
    List<String> itemids = new ArrayList<String>();
    String[][] rs = rowset.toTwoDimensionStringArray();
    String[] mars = new String[rs.length];
    String[] orgs = new String[rs.length];
    Set<String> orgset = new HashSet<String>();
    Set<String> marset = new HashSet<String>();

    if (rs.length > 0) {
      for (int i = 0; i < rs.length; i++) {
        // mars[i] = rs[i][1];
        // orgs[i] = rs[i][2];
        marset.add(rs[i][1]);
        orgset.add(rs[i][2]);
      }
      mars = marset.toArray(new String[marset.size()]);
      orgs = orgset.toArray(new String[orgset.size()]);
    }
    // 查询是否免检
    Map<String, MaterialStockVO> map =
        MaterialStockClassPubService.queryMaterialStockInfoByPks(mars, orgs,
            new String[] {
              MaterialStockVO.CHKFREEFLAG
            });
    for (int i = 0; i < rs.length; i++) {
      String mar = rs[i][1];
      String org = rs[i][2];
      // 免检的存货不查询出来
      UFBoolean chkfreeflag =
          map.get(mar + org) == null ? null : map.get(mar + org)
              .getChkfreeflag();
      if (!(chkfreeflag == null ? false : chkfreeflag.booleanValue())) {
        itemids.add(rs[i][0]);
      }
    }

    return itemids;
  }

  /**
   * @param queryScheme
   * @return 免检存货是否报检 为否时，免检的存货不查询出来,返回true，这样写主要是为了后面的逻辑判断清晰
   */
  private boolean getIsCheck(IQueryScheme queryScheme) {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    QueryCondition qc = qrySchemeProcessor.getQueryCondition("isCheck");
    String[] chk = qc == null ? null : qc.getValues();
    return chk != null && chk.length != 0 && chk[0].equals("N");
  }

  /**
   * @param qrySchemeProcessor
   * @param sbr
   * @author liugxa
   *         根据逻辑条件 重现组织 sql 见需求 查询条件检验状态项
   */
  private void getlogicWhr(QuerySchemeProcessor qrySchemeProcessor,
      StringBuilder wholeSql) {
    String tableAlias =
        qrySchemeProcessor
            .getTableAliasOfAttribute("pk_arriveorder_b.pk_arriveorder_b");
    QueryCondition qc = qrySchemeProcessor.getQueryCondition("checkStatus");
    String[] status = qc == null ? null : qc.getValues();
    QueryCondition orgqc = qrySchemeProcessor.getQueryCondition("pk_org");
    String[] pk_orgvals = orgqc == null ? null : orgqc.getValues();
    String pk_org = "";
    if (pk_orgvals != null && pk_orgvals.length != 0) {
      pk_org = pk_orgvals[0];
    }
    StringBuilder sbr = new StringBuilder();
    wholeSql.append(qrySchemeProcessor.getFinalFromWhere());
    if (status != null && status.length != 0) {
      int whr = wholeSql.indexOf("where");
      wholeSql.insert(whr,
          " LEFT JOIN po_arriveorder_bb ON po_arriveorder_bb.pk_arriveorder_b = "
              + tableAlias + ".pk_arriveorder_b ");

      sbr.append(" and (");
      for (String statu : status) {
        sbr.append(" or ");
        if (Integer.parseInt(statu) == POCheckStatus.CHECKED.toInt()) {
          // 检验完成：指已检数量不为0的记录
          if (qc.getOperator().equals("<>")) {
            sbr.append(" isnull(po_arriveorder_bb.nnum,0) =0");
          }
          else {
            sbr.append(" isnull(po_arriveorder_bb.nnum,0) !=0");
          }

        }
        else if (Integer.parseInt(statu) == POCheckStatus.CHECKING.toInt()) {
          // 已报检未返回结算：启用质量检验，则指报检数量不为0，已检数量为0的记录；未启用质量检验，选择该选项将查不出任何记录
          if (this.isQCEnabled(pk_org)) {
            if (qc.getOperator().equals("<>")) {
              sbr.append(" (isnull("
                  + tableAlias
                  + ".naccumchecknum,0)=0 or isnull(po_arriveorder_bb.nnum,0) !=0 )");
            }
            else {
              sbr.append(" (isnull("
                  + tableAlias
                  + ".naccumchecknum,0)!=0 and isnull(po_arriveorder_bb.nnum,0) =0) ");
            }
          }
          else {
            sbr.append(" 1=2 ");
          }
        }
        else {
          // 未报检：启用质量检验，则指报检数量为0的记录；未启用质量检验，则指已检数量为0的记录
          if (this.isQCEnabled(pk_org)) {
            if (qc.getOperator().equals("<>")) {
              sbr.append(" isnull(" + tableAlias + ".naccumchecknum,0) !=0 ");
            }
            else {
              sbr.append(" isnull(" + tableAlias + ".naccumchecknum,0) =0 ");
            }
          }
          else {
            if (qc.getOperator().equals("<>")) {
              sbr.append(" isnull(" + tableAlias + ".nnum,0)!=0 ");
            }
            else {
              sbr.append(" " + tableAlias + ".nnum-isnull(" + tableAlias
                  + ".naccumchecknum,0)-isnull(" + tableAlias
                  + ".naccumbacknum,0)>0  ");
            }
          }
        }
      }
      sbr.append(") ");
      wholeSql.append(sbr.toString().replaceFirst("or", ""));
      wholeSql.append(" and po_arriveorder_b.nnum > 0");
    }
  }

  private IQueryScheme getWholeQueryScheme(IQueryScheme queryScheme) {
    QuerySchemeProcessor qrySchemeProcessor =
        new QuerySchemeProcessor(queryScheme);
    String mainTableAlias = qrySchemeProcessor.getMainTableAlias();
    String pk_group = new BSContext().getGroupID();
    qrySchemeProcessor.appendWhere(" and " + mainTableAlias + ".dr = 0 and "
        + mainTableAlias + ".pk_group='" + pk_group + "' ");
    qrySchemeProcessor.appendFuncPermissionOrgSql();
    return queryScheme;
  }

  // private ArriveVO[] insertArrive(ArriveVO[] aggVOArray, ArrivalUIToBSEnv
  // env)
  // throws BusinessException {
  // try {
  // ArriveInsertAction action = new ArriveInsertAction();
  //
  // return action.insertArrive(aggVOArray, env);
  // }
  // catch (Exception e) {
  // ExceptionUtils.marsh(e);
  // }
  // return null;
  // }

  /**
   * @param pk_org
   * @return
   *         2011-9-14
   *         wangljc
   */

  private boolean isQCEnabled(String pk_org) {
    return SysInitGroupQuery.isQCEnabled()
        && UFBoolean.TRUE.equals(ValueUtils.getUFBoolean(QCSysParamUtil
            .getINI01(pk_org)));
  }

  // private ArriveVO[] updateArrive(ArriveVO[] aggVOArray, ArrivalUIToBSEnv
  // env)
  // throws BusinessException {
  // try {
  // ArriveUpdateAction action = new ArriveUpdateAction();
  // return action.updateArrive(aggVOArray, env);
  // }
  // catch (Exception e) {
  // ExceptionUtils.marsh(e);
  // }
  // return null;
  // }
}
