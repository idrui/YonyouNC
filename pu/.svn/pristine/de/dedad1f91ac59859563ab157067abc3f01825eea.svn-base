package nc.pubimpl.pu.tbb.strategy;

import java.util.Map;

import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.tbb.PuBillBusiSysReg;
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

public abstract class AbstractGetDataBatchDMO {
  
  /** 执行数 */
  public static final String UFIND = "UFIND";
  
  /** 预占数 */
  public static final String PREFIND = "PREFIND";
  
  /** 执行数的单据状态 */
  public static final int[] APPROVE = new int[]{POEnumBillStatus.APPROVE.toInt()};
  
  /** 预占数的单据状态 */
  public static final int[] NOAPPROVE = new int[]{
    POEnumBillStatus.FREE.toInt(), POEnumBillStatus.COMMIT.toInt(),
    POEnumBillStatus.APPROVING.toInt(), POEnumBillStatus.NOPASS.toInt()
    };

  /**
   * 取数
   * 
   * @param param
   * @return
   */
  public UFDouble[][] getExecDataBatch(NtbParamVO[] params, String type) {
    UFDouble[][] result = new UFDouble[params.length][4];
    TbbSplitParamvoUtil util = new TbbSplitParamvoUtil();
    Map<String, NtbParamVO[]> map = util.split(params);
    for (Map.Entry<String, NtbParamVO[]> ite : map.entrySet()) {
      UFDouble[][] part = this.getExecBatchByGroup(ite.getValue(), type);
      this.combineResult(result, part, params, ite.getValue());
    }
    return result;
  }

  private void combineResult(UFDouble[][] result, UFDouble[][] part,
      NtbParamVO[] params, NtbParamVO[] partParams) {
    if (part == null) {
      return;
    }

    for (int i = 0; i < partParams.length; ++i) {
      for (int j = 0; j < params.length; ++j) {
        if (partParams[i].equals(params[j])) {
          for (int k = 0; k < 4; k++) {
            result[j][k] = MathTool.add(result[j][k], part[i][k]);
          }
        }
      }
    }
  }

  protected UFDouble[][] getExecBatchByGroup(NtbParamVO[] params, String type) {
    NtbParamVO seed = this.getSeed(params);

    if (seed == null) {
      return null;
    }
    TbbTempTableSqlBuilder tbbuilder = new TbbTempTableSqlBuilder();
    String sql = this.getExecQuerySql(seed, params, type, tbbuilder);
    IRowSet rowset = new DataAccessUtils().query(sql);
    return this.getExecResult(rowset, params, type, tbbuilder);
  }

  protected abstract String getExecQuerySql(NtbParamVO seed,
      NtbParamVO[] params, String type, TbbTempTableSqlBuilder tbbuilder);

  protected UFDouble[][] getExecResult(IRowSet rowset, NtbParamVO[] params,
      String type, TbbTempTableSqlBuilder tbbuilder) {
    UFDouble[][] result = new UFDouble[params.length][4];
    while (rowset.next()) {
      for (int i = 0; i < params.length; i++) {
        if (null != params[i]) {
          if (tbbuilder.isMatch(rowset, params[i])) {
            if (PuBillBusiSysReg.NNUM.equals(type)) {
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

  protected NtbParamVO getSeed(NtbParamVO[] params) {
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

    return seed;
  }

  /**
   * 判断 参数中是否包括其他档案，如果包括true
   * 
   * @param param
   * @return
   */
  protected abstract boolean haveOtherDoc(NtbParamVO param);

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
