package nc.vo.pu.report.queryinfo.supplierest;

import nc.vo.pu.report.queryinfo.PuQueryInfoPara;
import nc.vo.pub.lang.UFDate;

/**
 * @since 6.0
 * @version 2011-3-22 ����11:35:54
 * @author yinfy
 */

public class PuSupplierEstQryInfoPara extends PuQueryInfoPara {
  private static final long serialVersionUID = 4261430916314384475L;

  /**
   * ��ʼ����
   */
  private UFDate begindate = null;

  /**
   * ͳ������,��ѡֵΪ���ɹ�����ݹ���ί�мӹ�����ݹ������Ļ����ݹ�
   */
  private String[] billtypes;

  /**
   * ��ȫ��������ⵥ�Ƿ�ͳ��
   */
  private boolean bincldfinish = true;

  /**
   * ��������
   */
  private UFDate enddate = null;

  /**
   * ��ѯģ���еĲ�ѯ����sql
   */
  private String wheresql = null;

  public UFDate getBegindate() {
    // ���ڱ��䣬���ﷵ��ֵ��ƴsql��ʱ�򲻿��ǿ��ж�
    if (this.begindate == null) {
      return new UFDate("1000-01-01");
    }
    return this.begindate;
  }

  public String[] getBilltypes() {
    return this.billtypes;
  }

  public UFDate getEnddate() {
    // ���ڱ��䣬���ﷵ��ֵ��ƴsql��ʱ�򲻿��ǿ��ж�
    if (this.enddate == null) {
      return new UFDate("9999-12-30");
    }
    return this.enddate;
  }

  public String getWheresql() {
    return this.wheresql;
  }

  public boolean isBincldfinish() {
    return this.bincldfinish;
  }

  public void setBegindate(UFDate begindate) {
    this.begindate = begindate;
  }

  public void setBilltypes(String[] billtypes) {
    this.billtypes = billtypes;
  }

  public void setBincldfinish(boolean bincldfinish) {
    this.bincldfinish = bincldfinish;
  }

  public void setEnddate(UFDate enddate) {
    this.enddate = enddate;
  }

  public void setWheresql(String wheresql) {
    this.wheresql = wheresql;
  }
}
