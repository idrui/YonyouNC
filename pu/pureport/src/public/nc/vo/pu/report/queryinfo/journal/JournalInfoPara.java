package nc.vo.pu.report.queryinfo.journal;

import nc.vo.pu.report.queryinfo.PuQueryInfoPara;

public class JournalInfoPara extends PuQueryInfoPara {

  private static final long serialVersionUID = -1872828183104811729L;

  /**
   * ͳ������
   */
  private String[] billtypes;

  /**
   * �Ƿ�������չ��
   * 
   * @return
   */
  private boolean bShowByMar;

  /**
   * �Ƿ��չ�Ӧ��չ��
   * 
   * @return
   */
  private boolean bShowBySupplier;

  private Integer[] groups;

  public String[] getBilltypes() {
    return this.billtypes;
  }

  /**
   * ������������ȡ�����ֶ�
   * 
   * @return �����ֶ�
   */
  @Override
  public String[] getGroupKeys() {
    return null;
  }

  public Integer[] getGroups() {
    return this.groups;
  }

  /**
   * ������������ȡ�����ֶ�
   * 
   * @return �����ֶ�
   */
  @Override
  public String[] getHideKeys() {
    return JournalHiddenKeyUtils.getHiddenKeys(this);
  }

  public boolean isbShowByMar() {
    return this.bShowByMar;
  }

  public boolean isbShowBySupplier() {
    return this.bShowBySupplier;
  }

  public void setBilltypes(String[] billtypes) {
    this.billtypes = billtypes;
  }

  public void setbShowByMar(boolean bShowByMar) {
    this.bShowByMar = bShowByMar;
  }

  public void setbShowBySupplier(boolean bShowBySupplier) {
    this.bShowBySupplier = bShowBySupplier;
  }

  public void setGroups(Integer[] groups) {
    this.groups = groups;
  }

}
