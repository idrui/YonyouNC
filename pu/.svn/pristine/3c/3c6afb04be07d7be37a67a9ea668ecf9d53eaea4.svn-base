package nc.vo.pu.report.convertor;

import java.io.Serializable;

import nc.itf.iufo.freereport.extend.IBusiFormat;
import nc.itf.iufo.freereport.extend.IQueryCondition;
import nc.itf.iufo.freereport.extend.IReportAdjustor;
import nc.pub.smart.model.descriptor.Descriptor;
import nc.pub.smart.model.descriptor.FilterDescriptor;
import nc.pub.smart.model.descriptor.FilterItem;
import nc.pubimpl.ic.icreport.adjustor.ICRptDefaultAdjustor;
import nc.pubitf.accperiod.AccountCalendar;
import nc.vo.pu.report.adjustor.PuReportAdjustor;
import nc.vo.pu.report.enumeration.EstMonthStatQryFieldCode;
import nc.vo.pu.report.pub.PuReportSubConvertor;
import nc.vo.pu.report.pub.smart.model.descriptor.PuAggrDescriptor;
import nc.vo.pu.report.pub.smart.model.descriptor.PuSortDescriptor;
import nc.vo.pu.report.queryinfo.PuQueryInfoPara;
import nc.vo.pu.report.queryinfo.eststat.PuEstStatQryInfoPara;
import nc.vo.pu.report.scale.estmonthstat.EstMonthScaleStrategy;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.query.ConditionVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.StringUtils;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.base.BaseQueryCondition;

/**
 * 暂估月统计
 * 
 * @since 6.0
 * @version 2011-8-2 下午02:17:03
 * @author 田锋涛
 */

public class EstMonthStatConvertor extends PuReportSubConvertor implements
    Serializable {

  private static final long serialVersionUID = 1L;

  @Override
  protected void doBusinessProcess(IQueryCondition condition, IContext context) {
    super.doBusinessProcess(condition, context);
    this.setQueryResult(condition, context);
  }

  @Override
  protected IReportAdjustor getAdjustor(BaseQueryCondition condition,
      IContext context) {
    ICRptDefaultAdjustor adjustor = new ICRptDefaultAdjustor() {
      private static final long serialVersionUID = 2393970047298235689L;

      @Override
      protected String[] getHidenFields() {
        PuQueryInfoPara para =
            (PuQueryInfoPara) this.getTranMap().get(PuQueryInfoPara.QUERY_PARA);
        if (para != null) {
          return para.getHideKeys();
        }
        return null;
      }
    };
    adjustor.setHiddenRelatedField(true);
    return adjustor;
  }

  @Override
  protected IBusiFormat getBusiFormat() {
    EstMonthScaleStrategy scalestragegy = new EstMonthScaleStrategy();
    return scalestragegy.getReportScaleProcess();
  }

  protected String getMonth(String period) {
    int index = period.indexOf("-") + 1;
    String month = period.substring(index);
    return month;
  }

  /**
   * 获取指定会计期间的边界日期
   * 
   * @param strPeriod
   * @return UFDate[] UFDate[0]:起始日期,UFDate[1]:截止日期
   */
  protected UFDate[] getPeriodBoundary(String strPeriod) {
    AccountCalendar calendar = AccountCalendar.getInstance();
    String year = this.getYear(strPeriod);
    String month = this.getMonth(strPeriod);
    try {
      calendar.set(year, month);
    }
    catch (Exception e) {
      ExceptionUtils.wrappException(e);
    }
    UFDate[] dates = new UFDate[2];
    dates[0] = calendar.getMonthVO().getBegindate();
    dates[1] = calendar.getMonthVO().getEnddate();
    return dates;
  }

  protected String getYear(String period) {
    int index = period.indexOf("-");
    String year = period.substring(0, index);
    return year;
  }

  /**
   * 日期处理，放入参数中用
   * 
   * @param para
   * @param ite
   */
  protected void processParaDateSql(PuEstStatQryInfoPara para, ConditionVO ite) {
    UFDate[] dates = this.getPeriodBoundary(ite.getValue());
    para.setBeginDate(dates[0]);
    para.setEndDate(dates[1]);

    StringBuilder dateBuilder = new StringBuilder();
    // 本月入库本月暂估
    dateBuilder.append(" DBILLDATE>='").append(dates[0].toString())
        .append("'  and DBILLDATE<='").append(dates[1].toString())
        .append("'  and DTOCOSTAPDATE>='").append(dates[0].toString())
        .append("'  and DTOCOSTAPDATE<='").append(dates[1].toString())
        .append("' ");
    para.setCurStockCurEstDateSql(dateBuilder.toString());

    dateBuilder = new StringBuilder();
    // 本月入库本月结算
    dateBuilder.append(" DBILLDATE>='").append(dates[0].toString())
        .append("' and DBILLDATE<='").append(dates[1].toString())
        .append("'  and DSETTLEDATE>='").append(dates[0].toString())
        .append("' and DSETTLEDATE<='").append(dates[1].toString())
        .append("' ");
    para.setCurStockCurSettleDateSql(dateBuilder.toString());

    dateBuilder = new StringBuilder();
    // 本月前暂估本月未结算
    dateBuilder.append(" DTOCOSTAPDATE<'").append(dates[0].toString())
        .append("' ");
    para.setPreEstCurNoSettleDateSql(dateBuilder.toString());

    dateBuilder = new StringBuilder();
    // 本月前暂估本月结算
    dateBuilder.append(" DTOCOSTAPDATE<'").append(dates[0].toString())
        .append("'  and DSETTLEDATE>='").append(dates[0].toString())
        .append("' and DSETTLEDATE<='").append(dates[1].toString())
        .append("' ");
    para.setPreEstCurSettleDateSql(dateBuilder.toString());
  }

  /**
   * 自己处理的where条件，供后台用
   * 
   * @param condition
   * @param context
   */
  protected void setQueryResult(IQueryCondition condition, IContext context) {
    if (condition == null || !(condition instanceof BaseQueryCondition)) {
      return;
    }
    BaseQueryCondition result = (BaseQueryCondition) condition;
    // 获取查询模板条件

    // where条件直接处理成筛选描述器
    FilterItem item = new FilterItem();
    item.setManualExpression(true);
    FilterDescriptor filter = new FilterDescriptor();
    filter.addFilter(item);
    ConditionVO[] conds = this.getTranMap().getConditionVOs();
    String wherePart = this.getTranMap().getWherePart();
    PuEstStatQryInfoPara para = new PuEstStatQryInfoPara();
    para.setDlgWherePart(wherePart);
    for (ConditionVO ite : conds) {
      if (ite.getFieldCode().equals(EstMonthStatQryFieldCode.qrycontent.code())) { // 统计内容
        para.setQueryTypes(ite.getValue());
      }
      else if (ite.getFieldCode().equals(
          EstMonthStatQryFieldCode.groupon.code())) { // 统计项目
        String grpstr = StringUtils.remove(ite.getValue(), "(");
        grpstr = StringUtils.remove(grpstr, "'");
        grpstr = StringUtils.remove(grpstr, ")");
        // 汇总查询项目
        para.setGroupcondtion(grpstr);
      }
      else if (ite.getFieldCode().equals(
          EstMonthStatQryFieldCode.fperiod.code())) { // 会计期间
        this.processParaDateSql(para, ite);
      }

    }
    // 排序处理
    PuSortDescriptor<PuQueryInfoPara> sortDescriprot =
        new PuSortDescriptor<PuQueryInfoPara>(para, false);
    result.setDescriptors(new Descriptor[] {
      filter, new PuAggrDescriptor<PuEstStatQryInfoPara>(para), sortDescriprot
    });
    context.setAttribute(PuQueryInfoPara.QUERY_PARA, para);
    this.getTranMap().put(PuQueryInfoPara.QUERY_PARA, para);
    result.setRoportAdjustor(new PuReportAdjustor());
    this.setCondOrgAttribute(context,
        EstMonthStatQryFieldCode.pk_financeorg.code());
  }
}
