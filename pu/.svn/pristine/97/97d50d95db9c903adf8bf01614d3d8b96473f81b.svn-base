package nc.vo.pu.report.pub;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import nc.itf.iufo.freereport.extend.IBusiFormat;
import nc.itf.iufo.freereport.extend.IQueryCondition;
import nc.itf.iufo.freereport.extend.IReportAdjustor;
import nc.pub.smart.model.descriptor.Descriptor;
import nc.pubimpl.ic.icreport.adjustor.ICRptDefaultAdjustor;
import nc.vo.pu.report.enumeration.GroupEnum;
import nc.vo.pu.report.pub.smart.model.descriptor.PuAggrDescriptor;
import nc.vo.pu.report.queryinfo.PuQueryInfoPara;
import nc.vo.pu.report.queryinfo.journal.JournalInfoPara;
import nc.vo.pu.report.scale.journal.JournalQueryScaleStrategy;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.query.ConditionVO;

import com.ufida.dataset.IContext;
import com.ufida.report.anareport.base.BaseQueryCondition;

/**
 * 综合日报 查询条件处理类
 * 
 * @since 6.0
 * @version 2011-7-19 下午03:06:16
 * @author liuchx
 */
public class JournalConvertor extends PuReportSubConvertor implements
    Serializable {

  public static final String GROUP_TYPE = "grouptype";

  public static final String ORDER_TYPE = "ordertype";

  public static final String PK_ORG = "pk_org";

  public static final String SHOW_MAR = "bShowByMar";

  public static final String SHOW_SUPPLIER = "bShowBySupplier";

  public static final String WHERE_CONDS = "whereConds";

  public static final String WHERE_SQL = "wheresql";

  private static final long serialVersionUID = 7375449836540952276L;

  private void setJournalPara(ConditionVO[] conds, JournalInfoPara para) {
    Set<Integer> groups = new HashSet<Integer>();
    for (ConditionVO cond : conds) {
      // 汇总依据
      if (cond.getFieldCode() != null
          && cond.getFieldCode().equals(JournalConvertor.GROUP_TYPE)) {
        groups.add(Integer.valueOf(cond.getValue()));
        para.setGroupcondtion(cond.getValue());
      }
      // 统计内容
      if (cond.getFieldCode() != null
          && cond.getFieldCode().equals(JournalConvertor.ORDER_TYPE)) {
        String groupValue = cond.getValue();
        int endIndex = groupValue.indexOf(")");
        if (endIndex == -1) {
          // 没有括号,只选中一种统计内容 变成 '1'形式
          groupValue = "'" + groupValue + "'";
          para.setBilltypes(new String[] {
            groupValue
          });
        }
        else {
          String values = groupValue.substring(1, endIndex);
          String[] billtypes = values.split(",");
          para.setBilltypes(billtypes);
        }
      }
      if (cond.getFieldCode() != null
          && cond.getFieldCode().equals(JournalConvertor.SHOW_MAR)) {
        if (UFBoolean.valueOf(cond.getValue()).booleanValue()) {
          groups.add(GroupEnum.MAR.toInteger());
          para.setbShowByMar(true);
        }

      }
      if (cond.getFieldCode() != null
          && cond.getFieldCode().equals(JournalConvertor.SHOW_SUPPLIER)) {
        if (UFBoolean.valueOf(cond.getValue()).booleanValue()) {
          groups.add(GroupEnum.SUPPLIER.toInteger());
          para.setbShowBySupplier(true);
        }

      }
    }
    para.setGroups(groups.toArray(new Integer[groups.size()]));
  }

  /**
   * 自己的业务处理
   * 
   * @param condition
   * @param context
   */
  @Override
  protected void doBusinessProcess(IQueryCondition condition, IContext context) {
    if (condition == null || !(condition instanceof BaseQueryCondition)) {
      return;
    }
    BaseQueryCondition result = (BaseQueryCondition) condition;
    ConditionVO[] conds = this.getTranMap().getSelectConditionVOs();

    // 设置 分组，汇总 字段
    JournalInfoPara para = new JournalInfoPara();
    ConditionVO[] whereConds = this.getTranMap().getGeneralConditionVOs();
    context.setAttribute(JournalConvertor.WHERE_CONDS, whereConds);
    String wheresql = new ConditionVO().getWhereSQL(whereConds);
    context.setAttribute(JournalConvertor.WHERE_SQL, wheresql);
    this.setJournalPara(conds, para);
    context.setAttribute(PuQueryInfoPara.QUERY_PARA, para);
    this.getTranMap().put(PuQueryInfoPara.QUERY_PARA, para);
    this.setCondOrgAttribute(context, JournalConvertor.PK_ORG);

    if (para.getGroupKeys() != null && para.getTotalKeys() != null) {
      result.setDescriptors(new Descriptor[] {
        new PuAggrDescriptor<JournalInfoPara>(para)
      });
    }
    else {
      result.setDescriptors(null);
    }
    return;
  }

  /**
   * 获得设计报表调试器（用于动态列控制),可复写
   * 
   * @param condition
   * @param context
   * @return
   */
  @Override
  protected IReportAdjustor getAdjustor(BaseQueryCondition condition,
      IContext context) {

    ICRptDefaultAdjustor adjustor = new ICRptDefaultAdjustor() {
      private static final long serialVersionUID = 2393970047298235689L;

      @Override
      protected String[] getHidenFields() {
        JournalInfoPara para =
            (JournalInfoPara) this.getTranMap().get(PuQueryInfoPara.QUERY_PARA);
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
    // 精度处理
    return new JournalQueryScaleStrategy().getReportScaleProcess();
  }

}
