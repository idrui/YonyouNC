package nc.vo.pu.report.queryinfo.journal;

import nc.vo.pu.report.queryinfo.PuQueryInfoPara;

public class JournalInfoPara extends PuQueryInfoPara {

  private static final long serialVersionUID = -1872828183104811729L;

  /**
   * 统计内容
   */
  private String[] billtypes;

  /**
   * 是否按照物料展开
   * 
   * @return
   */
  private boolean bShowByMar;

  /**
   * 是否按照供应商展开
   * 
   * @return
   */
  private boolean bShowBySupplier;

  private Integer[] groups;

  public String[] getBilltypes() {
    return this.billtypes;
  }

  /**
   * 根据条件，获取分组字段
   * 
   * @return 分组字段
   */
  @Override
  public String[] getGroupKeys() {
    return null;
  }

  public Integer[] getGroups() {
    return this.groups;
  }

  /**
   * 根据条件，获取隐藏字段
   * 
   * @return 隐藏字段
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
