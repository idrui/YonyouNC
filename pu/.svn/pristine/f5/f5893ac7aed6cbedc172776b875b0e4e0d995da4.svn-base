package nc.vo.pu.pub.sql;

/**
 * @since 6.0
 * @version 2011-6-10 ÉÏÎç01:51:25
 * @author yangb
 */

public class PUJoinInfo {

  private String attrPath;

  private boolean bleft = false;

  private String leftalias;

  private String leftfield;

  private String lefttable;

  private String rightalias;

  private String rightfield;

  private String righttable;

  public PUJoinInfo() {
    // TODO
  }

  public PUJoinInfo(String ltable, String lalias, String lfield, String rtable,
      String ralias, String rfield) {
    this.lefttable = ltable;
    this.leftalias = lalias;
    this.leftfield = lfield;

    this.righttable = rtable;
    this.rightalias = ralias;
    this.rightfield = rfield;
  }

  public String getAttrPath() {
    return this.attrPath;
  }

  public String getJoinString(boolean isincludeleft) {
    StringBuilder sql = new StringBuilder();
    if (isincludeleft) {
      sql.append(this.lefttable);
      sql.append(" " + this.leftalias);
    }
    if (this.bleft) {
      sql.append(" left join ");
    }
    else {
      sql.append(" inner join ");
    }
    sql.append(this.righttable);
    sql.append(" " + this.rightalias);
    sql.append(" on ( ");
    sql.append(this.leftalias + "." + this.leftfield);
    sql.append(" = ");
    sql.append(this.rightalias + "." + this.rightfield);
    sql.append(" ) ");
    return sql.toString();
  }

  public String getLeftalias() {
    return this.leftalias;
  }

  public String getLeftfield() {
    return this.leftfield;
  }

  public String getLefttable() {
    return this.lefttable;
  }

  public String getRightalias() {
    return this.rightalias;
  }

  public String getRightfield() {
    return this.rightfield;
  }

  public String getRighttable() {
    return this.righttable;
  }

  public boolean isBleft() {
    return this.bleft;
  }

  public void setAttrPath(String attrPath) {
    this.attrPath = attrPath;
  }

  public void setBleft(boolean bleft) {
    this.bleft = bleft;
  }

  public void setLeftalias(String leftalias) {
    this.leftalias = leftalias;
  }

  public void setLeftfield(String leftfield) {
    this.leftfield = leftfield;
  }

  public void setLefttable(String lefttable) {
    this.lefttable = lefttable;
  }

  public void setRightalias(String rightalias) {
    this.rightalias = rightalias;
  }

  public void setRightfield(String rightfield) {
    this.rightfield = rightfield;
  }

  public void setRighttable(String righttable) {
    this.righttable = righttable;
  }

}
