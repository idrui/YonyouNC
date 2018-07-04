package nc.vo.pu.report.convertor;

import nc.itf.iufo.freereport.extend.IBusiFormat;
import nc.itf.iufo.freereport.extend.IQueryCondition;
import nc.ui.querytemplate.operator.IOperatorConstants;
import nc.vo.pu.report.enumeration.SupplierEstQryFieldCode;
import nc.vo.pu.report.pub.PuReportSubConvertor;
import nc.vo.pu.report.queryinfo.PuQueryInfoPara;
import nc.vo.pu.report.queryinfo.supplierest.PuSupplierEstQryInfoPara;
import nc.vo.pu.report.scale.supplierest.SupplierEstBalScaleStrategy;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.query.ConditionVO;

import org.apache.commons.lang.StringUtils;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.base.BaseQueryCondition;

/**
 * 供应商暂估余额报表
 * 
 * @since 6.0
 * @version 2011-8-2 下午02:17:03
 * @author 田锋涛
 */

public class SupplierEstConvertor extends PuReportSubConvertor {

  private String[] getBillTypes(ConditionVO vo) {
    String value = vo.getValue();
    value = StringUtils.replace(value, "(", "");
    value = StringUtils.replace(value, ")", "");
    value = StringUtils.replace(value, "'", "");
    String[] bts = value.split(",");
    return bts;
  }

  /**
   * 起始日期处理
   * 
   * @param vo
   * @param para
   */
  private void setPeriodOfPara(ConditionVO vo, PuSupplierEstQryInfoPara para) {
    String[] dates = vo.getValue().split(",");
    if (!IOperatorConstants.ISNULL_INCLUDE_SPACE.equals(dates[0])
        && StringUtils.isNotBlank(dates[0])) {
      para.setBegindate(new UFDate(dates[0]).asBegin());
    }
    if (!IOperatorConstants.ISNULL_INCLUDE_SPACE.equals(dates[1])
        && StringUtils.isNotBlank(dates[1])) {
      para.setEnddate(new UFDate(dates[1]).asEnd());
    }
  }

  @Override
  protected void doBusinessProcess(IQueryCondition condition, IContext context) {
    super.doBusinessProcess(condition, context);
    this.setQueryResult(condition, context);
  }

  @Override
  protected IBusiFormat getBusiFormat() {
    // 合计精度处理
    return new SupplierEstBalScaleStrategy().getReportScaleProcess();
  }

  @Override
  protected boolean isNeedDoubleEngine() {
    ConditionVO material =
        this.getTranMap().getConditionVO(
            SupplierEstQryFieldCode.pk_srcmaterial.code());
    ConditionVO materialClass =
        this.getTranMap().getConditionVO(
            SupplierEstQryFieldCode.pk_marbasclass.code());
    // 不输入物料编码时，如果物料分类为空，或者物料分类多于1个，要走双引擎
    if (material == null) {
      if (materialClass == null) {
        return true;
      }
      String[] values = materialClass.getValue().split(",");
      if (IOperatorConstants.ISNULL_INCLUDE_SPACE.equals(values[0])
          || values.length > 1) {
        return true;
      }
    }

    // 查询日期，必输项
    ConditionVO fperiod =
        this.getTranMap()
            .getConditionVO(SupplierEstQryFieldCode.fperiod.code());
    String[] dates = fperiod.getValue().split(",");
    UFDate begindate = new UFDate(dates[0]).asBegin();
    UFDate enddate = new UFDate(dates[1]).asEnd();
    // 查询日期间隔超出31天，走双引擎
    if (UFDate.getDaysBetween(begindate, enddate) > 31) {
      return true;
    }

    return false;
  }

  /**
   * 自己处理where条件，传到后台用
   * 
   * @param condition
   * @param context
   */
  protected void setQueryResult(IQueryCondition condition, IContext context) {
    if (condition == null || !(condition instanceof BaseQueryCondition)) {
      return;
    }
    PuSupplierEstQryInfoPara para = new PuSupplierEstQryInfoPara();
    ConditionVO[] conds = this.getTranMap().getConditionVOs();
    String wherePart = this.getTranMap().getWherePart();
    for (ConditionVO ite : conds) {
      // 已全部结算入库单是否统计
      if (ite.getFieldCode()
          .equals(SupplierEstQryFieldCode.bincldfinish.code())) {
        para.setBincldfinish(UFBoolean.valueOf(ite.getValue()).booleanValue());
      }
      // 统计内容，可选值为：采购入库暂估、委托加工入库暂估、消耗汇总暂估
      else if (ite.getFieldCode().equals(
          SupplierEstQryFieldCode.qrycontent.code())) {
        para.setBilltypes(this.getBillTypes(ite));
      }
      // 日期区间
      else if (ite.getFieldCode()
          .equals(SupplierEstQryFieldCode.fperiod.code())) {
        this.setPeriodOfPara(ite, para);
      }
    }
    para.setWheresql(wherePart);
    context.setAttribute(PuQueryInfoPara.QUERY_PARA, para);
    // 后续联查用
    this.getTranMap().put(PuQueryInfoPara.QUERY_PARA, para);
    this.setCondOrgAttribute(context,
        SupplierEstQryFieldCode.pk_financeorg.code());
  }
}
