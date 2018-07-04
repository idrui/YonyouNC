package nc.vo.pu.report.queryinfo.estdiffer;

import nc.vo.pu.report.queryinfo.eststat.PuEstStatQryInfoPara;

/**
 * �ݹ�������ѯ�������
 * 
 * @since 6.1
 * @version 2012-8-17 ����04:02:52
 * @author tianft
 */
public class PuEstDifferQryInfoPara extends PuEstStatQryInfoPara {

  private static final long serialVersionUID = 4385620516185743141L;

  /** �����ݹ����½��� -����sql */
  private String curEstCurSettleDateSql;

  /** �����ʱ�����׼ */
  private String differRateStdValue;

  /** ֻ��ѯ�����ʳ���������׼�ļ�¼ */
  private boolean onlyQueryOverDifferRate = false;

  public String getCurEstCurSettleDateSql() {
    return this.curEstCurSettleDateSql;
  }

  @Override
  public String getCurStockCurSettleDateSql() {
    return this.getCurEstCurSettleDateSql();
  }

  public String getDifferRateStdValue() {
    return this.differRateStdValue;
  }

  @Override
  public String[] getGroupKeys() {
    // ��ƽ̨�ķ�����ƻ���ɼ�������ʲ���ȷ�����Բ�ʹ��ƽ̨�ķ�����ơ�
    return new String[0];
  }

  @Override
  public String[] getTotalKeys() {
    // �˴���Ӻϼ��ֶλ�ʹ��Ӧ�̡����ϵȷ����ֶ��޷���ѯ������������ݲ���ȷ��
    // ���ؿջ���ɿ�ָ�룬�ʷ��ؿ����顣
    return new String[0];
  }

  public boolean isOnlyQueryOverDifferRate() {
    return this.onlyQueryOverDifferRate;
  }

  public void setCurEstCurSettleDateSql(String curEstCurSettleDateSql) {
    this.curEstCurSettleDateSql = curEstCurSettleDateSql;
  }

  public void setDifferRateStdValue(String differRateStdValue) {
    this.differRateStdValue = differRateStdValue;
  }

  public void setOnlyQueryOverDifferRate(boolean onlyQueryOverDifferRate) {
    this.onlyQueryOverDifferRate = onlyQueryOverDifferRate;
  }

}
