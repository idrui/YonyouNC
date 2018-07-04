package nc.bs.pu.mpp.strategy;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.scmpub.reference.uap.org.OrgUnitPubService;
import nc.vo.pl.tbb.PlBillBusiSysReg;
import nc.vo.pu.tbb.DocConst;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.tbb.TbbSplitParamvoUtil;
import nc.vo.scmpub.tbb.TbbTempTableSqlBuilder;
import nc.vo.tb.obj.NtbParamVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * @since 6.0
 * @version 2011-3-29 上午09:34:39
 * @author yinfy
 */

public abstract class AbstractBatchFetcher {
  /**
   * 取执行数
   * 
   * @param param
   * @return
   */
  public UFDouble[][] getExecDataBatch(NtbParamVO[] params, String type) {
    UFDouble[][] result = new UFDouble[params.length][4];
    TbbSplitParamvoUtil util = new TbbSplitParamvoUtil();
    Map<String, NtbParamVO[]> map = util.split(params);
    for (Map.Entry<String, NtbParamVO[]> ite : map.entrySet()) {
      NtbParamVO[] partParams = ite.getValue();
      UFDouble[][] part = this.getExecBatchByGroup(partParams, type);
      this.combineResult(params, partParams, result, part);
    }
    return result;
  }

  /**
   * 取预占数
   * 
   * @param param
   * @return
   */
  public UFDouble[][] getReadyDataBatch(NtbParamVO[] params, String type) {
    UFDouble[][] result = new UFDouble[params.length][4];
    TbbSplitParamvoUtil util = new TbbSplitParamvoUtil();
    Map<String, NtbParamVO[]> map = util.split(params);
    for (Map.Entry<String, NtbParamVO[]> ite : map.entrySet()) {
      NtbParamVO[] partParams = ite.getValue();
      UFDouble[][] part = this.getReadyBatchByGroup(partParams, type);
      this.combineResult(params, partParams, result, part);
    }
    return result;
  }

  /**
   * 合并查询结果
   * 
   * @param result
   * @param part
   */
  private void combineResult(NtbParamVO[] paras, NtbParamVO[] partParas,
      UFDouble[][] result, UFDouble[][] part) {
    if (part == null) {
      return;
    }
    // for (int i = 0; i < result.length; i++) {
    // for (int j = 0; j < 4; j++) {
    // result[i][j] = MathTool.add(result[i][j], part[i][j]);
    // }
    // }
    for (int i = 0; i < paras.length; ++i) {
      for (int j = 0; j < partParas.length; ++j) {
        if (paras[i].equals(partParas[j])) {
          for (int k = 0; k < 4; ++k) {
            result[i][k] = MathTool.add(result[i][k], part[j][k]);
          }
        }
      }
    }
  }

  /**
   * 处理需求公司查询条件，查询该需求公司下的所有库存组织，并将结果放到下级列表中
   * 
   * @param param
   * @return
   */
  private NtbParamVO doDataProcess(NtbParamVO param) {
    if (ArrayUtils.isEmpty(param.getBusiAttrs())) {
      return param;
    }
    for (int i = 0; i < param.getBusiAttrs().length; i++) {
      if (DocConst.CORPSTOCKORG.equals(param.getBusiAttrs()[i])) {
        String[] pk_stockorgs =
            OrgUnitPubService.getStockOrgsBycorp(param.getPkDim()[i]);
        if (!ArrayUtils.isEmpty(pk_stockorgs)) {
          param.getLowerArrays().put(DocConst.CORPSTOCKORG, pk_stockorgs);
        }
        else {
          param.getLowerArrays().put(DocConst.CORPSTOCKORG, new String[] {
            param.getPkDim()[i]
          });
        }
        param.getIncludelower()[i] = true;
      }
    }
    return param;
  }

  /**
   * 分组批量获取执行数
   * 
   * @param params
   * @param type
   * @return
   */
  protected UFDouble[][] getExecBatchByGroup(NtbParamVO[] params, String type) {
    NtbParamVO seed = null;
    for (int i = 0; i < params.length; i++) {
      NtbParamVO param = params[i];
      if (null != param) {
        if (!this.haveOtherDoc(param)) {
          seed = param;
        }
        else {
          break;
        }
      }
    }
    if (seed == null) {
      return null;
    }
    TbbTempTableSqlBuilder tbbuilder = new TbbTempTableSqlBuilder();
    String sql = this.getExecQuerySql(seed, params, type, tbbuilder);
    IRowSet rowset = new DataAccessUtils().query(sql);
    return this.getExecResult(rowset, params, type, tbbuilder);
  }

  /**
   * 获取执行数Sql
   * 
   * @param seed
   * @param params
   * @param type
   * @param tbbuilder
   * @return
   */
  protected abstract String getExecQuerySql(NtbParamVO seed,
      NtbParamVO[] params, String type, TbbTempTableSqlBuilder tbbuilder);

  /**
   * 获取执行数结果
   * 
   * @param rowset
   * @param params
   * @param type
   * @param tbbuilder
   * @return
   */
  protected UFDouble[][] getExecResult(IRowSet rowset, NtbParamVO[] params,
      String type, TbbTempTableSqlBuilder tbbuilder) {
    UFDouble[][] result = new UFDouble[params.length][4];
    while (rowset.next()) {
      for (int i = 0; i < params.length; i++) {
        if (null != params[i]) {
          if (tbbuilder.isMatch(rowset, params[i])) {
            if (PlBillBusiSysReg.NNUM.equals(type)) {
              result[i][0] = rowset.getUFDouble(0);
              result[i][1] = rowset.getUFDouble(0);
              result[i][2] = rowset.getUFDouble(0);
              result[i][3] = rowset.getUFDouble(0);
            }
            else {
              result[i][0] = rowset.getUFDouble(0);
              result[i][1] = rowset.getUFDouble(1);
              result[i][2] = rowset.getUFDouble(2);
              result[i][3] = rowset.getUFDouble(3);
            }
          }
        }
      }
    }
    return result;
  }

  protected abstract Map<String, String> getFieldMap(NtbParamVO seed);

  /**
   * 批量获取预占数
   * 
   * @param params
   * @param type
   * @return
   */
  protected UFDouble[][] getReadyBatchByGroup(NtbParamVO[] params, String type) {
    NtbParamVO seed = null;
    for (int i = 0; i < params.length; i++) {
      NtbParamVO param = params[i];
      if (null != param) {
        if (!this.haveOtherDoc(param)) {
          seed = this.doDataProcess(param);
        }
        else {
          break;
        }
      }
    }
    if (seed == null) {
      return null;
    }
    TbbTempTableSqlBuilder tbbuilder = new TbbTempTableSqlBuilder();
    String sql = this.getReadyQuerySql(seed, params, type, tbbuilder);
    IRowSet rowset = new DataAccessUtils().query(sql);
    return this.getReadyResult(rowset, params, type, tbbuilder);
  }

  /**
   * 获取预占数Sql
   * 
   * @param seed
   * @param params
   * @param type
   * @param tbbuilder
   * @return
   */
  protected abstract String getReadyQuerySql(NtbParamVO seed,
      NtbParamVO[] params, String type, TbbTempTableSqlBuilder tbbuilder);

  /**
   * 获取预占数结果
   * 
   * @param rowset
   * @param params
   * @param type
   * @param tbbuilder
   * @return
   */
  protected UFDouble[][] getReadyResult(IRowSet rowset, NtbParamVO[] params,
      String type, TbbTempTableSqlBuilder tbbuilder) {
    UFDouble[][] result = new UFDouble[params.length][4];
    while (rowset.next()) {
      for (int i = 0; i < params.length; i++) {
        if (null != params[i]) {
          if (tbbuilder.isMatch(rowset, params[i])) {
            if (PlBillBusiSysReg.NNUM.equals(type)) {
              result[i][0] = rowset.getUFDouble(0);
              result[i][1] = rowset.getUFDouble(0);
              result[i][2] = rowset.getUFDouble(0);
              result[i][3] = rowset.getUFDouble(0);
            }
            else {
              result[i][0] = rowset.getUFDouble(0);
              result[i][1] = rowset.getUFDouble(1);
              result[i][2] = rowset.getUFDouble(2);
              result[i][3] = rowset.getUFDouble(3);
            }
          }
        }
      }
    }
    return result;
  }

  /**
   * 判断 参数中是否包括其他档案，如果包括true
   * 
   * @param param
   * @return
   */
  protected boolean haveOtherDoc(NtbParamVO param) {
    String[] docs =
        new String[] {
          DocConst.CORPSTOCKORG, DocConst.STOCKORG, DocConst.PURCHASEORG,
          DocConst.BDDEPT, DocConst.MATCLASS, DocConst.MATERIAL,
          DocConst.MATERIALOID, DocConst.BDPROJECT, DocConst.FINANCEORG,
          TbbTempTableSqlBuilder.CURRENCY
        };

    Set<String> set = new HashSet<String>();
    for (String doc : docs) {
      set.add(doc);
    }
    String[] busiAttrs = param.getBusiAttrs();
    for (String busiAttr : busiAttrs) {
      if (!set.contains(busiAttr)) {
        return true;
      }
    }
    return false;
  }

  /**
   * NtbParamVO是否包含field指定的查询档案
   * 
   * @param seed
   * @param field
   * @return
   */
  protected boolean isIncludeField(NtbParamVO seed, String field) {
    if (ArrayUtils.isEmpty(seed.getBusiAttrs())) {
      return false;
    }
    for (int i = 0; i < seed.getBusiAttrs().length; i++) {
      if (field.equals(seed.getBusiAttrs()[i])) {
        return true;
      }
    }
    return false;
  }
}
