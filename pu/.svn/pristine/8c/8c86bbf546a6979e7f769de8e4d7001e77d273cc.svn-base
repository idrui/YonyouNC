package nc.ui.pu.para;

import java.util.Vector;

import nc.ui.dbcache.DBCacheFacade;
import nc.ui.pub.para.ICheckParaFinal;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.para.CheckParaVO;
import nc.vo.pub.para.SysInitVO;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

import org.apache.commons.lang.StringUtils;

/**
 * 采购期初日期 参数校验类
 * 
 * @since 6.0
 * @version 2011-9-14 下午06:10:19
 * @author 田锋涛
 */

public class CheckINI02 implements ICheckParaFinal {

  @Override
  public CheckParaVO paraBeforeSavingCheck(SysInitVO savingvo,
      SysInitVO[] wholeModuleParas) {
    CheckParaVO checkParaVO = new CheckParaVO();

    String value = savingvo.getValue();
    // 允许不输入值
    if (StringUtils.isEmpty(value)) {
      return checkParaVO;
    }
    try {
      new UFDate(value);
    }
    catch (Exception e) {
      checkParaVO.setLegal(false);
      checkParaVO.setErrMsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004000_0", "04004000-0121")/*
                                                    * @res
                                                    * "参数值日期格式不正确！"
                                                    */);
    }
    return checkParaVO;
  }

  @Override
  public CheckParaVO paraEditabilityCheck(SysInitVO edittingvo,
      SysInitVO[] wholeModuleParas) {
    CheckParaVO checkParaVO = new CheckParaVO();

    boolean beRefered = this.beReferenced(edittingvo);
    if (beRefered) {
      checkParaVO.setLegal(false);
      checkParaVO.setErrMsg(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004000_0", "04004000-0123")/*
                                                    * 参数已经被引用，不能修改
                                                    * ！
                                                    */);
    }
    return checkParaVO;
  }

  /**
   * 参数是否被期初暂估单和期初发票引用
   * 
   * @param vo
   * @return
   */
  private boolean beReferenced(SysInitVO vo) {
    String pk_org = vo.getPk_org();
    SqlBuilder sql = new SqlBuilder();
    sql.append(" select count(*) from PO_INITIALEST where dr=0 ");
    sql.append(" and pk_org ", pk_org);
    sql.append(" union all ");
    sql.append(" select count(*) from PO_INVOICE where dr=0 and isnull(binitial,'N')='Y' ");
    sql.append(" and pk_org ", pk_org);

    Vector<?> result = DBCacheFacade.getFromDBCache(sql.toString(), false);
    // 期初引用
    Object cnt = ((Vector<?>) result.get(0)).get(0);
    if (!"0".equals(cnt.toString())) {
      return true;
    }
    // 发票引用
    cnt = ((Vector<?>) result.get(1)).get(0);
    if (!"0".equals(cnt.toString())) {
      return true;
    }

    return false;
  }

}
