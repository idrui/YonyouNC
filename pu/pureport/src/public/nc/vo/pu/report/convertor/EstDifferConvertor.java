package nc.vo.pu.report.convertor;

import nc.itf.iufo.freereport.extend.IBusiFormat;
import nc.itf.iufo.freereport.extend.IQueryCondition;
import nc.pub.smart.model.descriptor.Descriptor;
import nc.pub.smart.model.descriptor.FilterDescriptor;
import nc.pub.smart.model.descriptor.FilterItem;
import nc.vo.pu.report.adjustor.PuReportAdjustor;
import nc.vo.pu.report.enumeration.EstDifferQryFieldCode;
import nc.vo.pu.report.pub.smart.model.descriptor.PuAggrDescriptor;
import nc.vo.pu.report.pub.smart.model.descriptor.PuSortDescriptor;
import nc.vo.pu.report.queryinfo.PuQueryInfoPara;
import nc.vo.pu.report.queryinfo.estdiffer.PuEstDifferQryInfoPara;
import nc.vo.pu.report.queryinfo.eststat.PuEstStatQryInfoPara;
import nc.vo.pu.report.scale.estdiffer.EstDifferScaleStrategy;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.query.ConditionVO;

import org.apache.commons.lang.StringUtils;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.base.BaseQueryCondition;

/**
 * 暂估差异表
 * 
 * @since 6.1
 * @version 2012-8-17 下午04:45:51
 * @author tianft
 */
public class EstDifferConvertor extends EstMonthStatConvertor {

  private static final long serialVersionUID = 1L;

  @Override
  protected IBusiFormat getBusiFormat() {
    EstDifferScaleStrategy scalestragegy = new EstDifferScaleStrategy();
    return scalestragegy.getReportScaleProcess();
  }

  @Override
  protected void processParaDateSql(PuEstStatQryInfoPara para, ConditionVO ite) {
    PuEstDifferQryInfoPara estDifferpara = (PuEstDifferQryInfoPara) para;
    UFDate[] dates = this.getPeriodBoundary(ite.getValue());
    estDifferpara.setBeginDate(dates[0]);
    estDifferpara.setEndDate(dates[1]);

    StringBuilder dateBuilder = new StringBuilder();
    // 本月前暂估本月结算
    dateBuilder.append(" DTOCOSTAPDATE<'").append(dates[0].toString())
        .append("' and DSETTLEDATE>='").append(dates[0].toString())
        .append("' and DSETTLEDATE<='").append(dates[1].toString())
        .append("' ");
    estDifferpara.setPreEstCurSettleDateSql(dateBuilder.toString());

    dateBuilder = new StringBuilder();
    // 本月暂估本月结算
    dateBuilder.append(" DTOCOSTAPDATE>='").append(dates[0].toString())
        .append("' and DTOCOSTAPDATE<='").append(dates[1].toString())
        .append("' and DSETTLEDATE>='").append(dates[0].toString())
        .append("' and DSETTLEDATE<='").append(dates[1].toString())
        .append("' ");
    estDifferpara.setCurEstCurSettleDateSql(dateBuilder.toString());
  }

  @Override
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
    PuEstDifferQryInfoPara para = new PuEstDifferQryInfoPara();
    para.setDlgWherePart(wherePart);
    for (ConditionVO ite : conds) {
      if (ite.getFieldCode().equals(EstDifferQryFieldCode.qrycontent.code())) { // 统计内容
        para.setQueryTypes(ite.getValue());
      }
      else if (ite.getFieldCode().equals(EstDifferQryFieldCode.groupon.code())) { // 统计项目
        String grpstr = StringUtils.remove(ite.getValue(), "(");
        grpstr = StringUtils.remove(grpstr, "'");
        grpstr = StringUtils.remove(grpstr, ")");
        // 汇总查询项目
        para.setGroupcondtion(grpstr);
      }
      else if (ite.getFieldCode().equals(EstDifferQryFieldCode.fperiod.code())) { // 会计期间
        this.processParaDateSql(para, ite);
      }
      else if (ite.getFieldCode().equals(
          EstDifferQryFieldCode.boverDifferRate.code())) { // 只查询差异率超过报警标准的记录
        para.setOnlyQueryOverDifferRate(UFBoolean.TRUE.toString().equals(
            ite.getValue()) ? true : false);
      }
      else if (ite.getFieldCode().equals(
          EstDifferQryFieldCode.ndifferRate.code())) { // 差异率报警标准
        para.setDifferRateStdValue(ite.getValue());
      }

    }
    // 排序处理
    PuSortDescriptor<PuQueryInfoPara> sortDescriprot =
        new PuSortDescriptor<PuQueryInfoPara>(para, false);
    result.setDescriptors(new Descriptor[] {
      filter, new PuAggrDescriptor<PuEstDifferQryInfoPara>(para),
      sortDescriprot
    });
    context.setAttribute(PuQueryInfoPara.QUERY_PARA, para);
    this.getTranMap().put(PuQueryInfoPara.QUERY_PARA, para);
    result.setRoportAdjustor(new PuReportAdjustor());
    this.setCondOrgAttribute(context,
        EstDifferQryFieldCode.pk_financeorg.code());
  }
}
