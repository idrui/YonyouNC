package nc.ui.pu.pub.beans;

import nc.ui.bd.ref.AbstractRefModel;

public class PoFreeCustBankRefModel extends AbstractRefModel {

  private String m_cfreecustid = null;

  /**
   * UserDefRefModel ������ע�⡣
   */
  public PoFreeCustBankRefModel(String cfreecustid) {
    super();
    this.m_cfreecustid = cfreecustid;
  }

  /**
   * getDefaultFieldCount ����ע�⡣
   */
  @Override
  public int getDefaultFieldCount() {
    return 4;
  }

  /**
   * getFieldCode ����ע�⡣
   */
  @Override
  public String[] getFieldCode() {
    return new String[] {
      "code", "name", "bankaccount", "bankaddress"
    };
  }

  /**
   * getFieldName ����ע�⡣
   */
  @Override
  public java.lang.String[] getFieldName() {
    return new String[] {
      nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
          "04004000-0126")/* @res "���б���" */,
      nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
          "04004000-0127")/* @res "��������" */,
      nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
          "04004000-0037")/* @res "�����ʺ�" */,
      nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
          "04004000-0038")
    /* @res "���е�ַ" */

    };
  }

  @Override
  public String[] getHiddenFieldCode() {
    return new String[] {
      "pk_freecustom"

    };
  }

  /**
   * �������ݿ�������ͼ��
   * �������ڣ�(01-4-4 0:57:23)
   * 
   * @return java.lang.String
   */
  @Override
  public String getPkFieldCode() {
    // wuxla 57������������������
    return "pk_freecustom";
  }

  @Override
  public String[] getPkValues() {
    return new String[] {
      this.m_cfreecustid
    };
  }

  @Override
  public String getRefCodeField() {
    return "code";
  }

  @Override
  public String getRefNameField() {
    return "name";
  }

  /**
   * getRefTitle ����ע�⡣
   */
  @Override
  public String getRefTitle() {
    return nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("4004000_0",
        "04004000-0039")/* @res "ɢ����������" */;
  }

  @Override
  public int[] getShownColumns() {
    return new int[] {
      0, 1, 2, 3
    };
  }

  /**
   * getTableName ����ע�⡣
   */
  @Override
  public String getTableName() {
    return "(SELECT  C.pk_freecustom,C.bank,C.bankaccount,C.bankaddress,A.code,A.name "
        + "    FROM bd_bankdoc A ,bd_freecustom C "
        + "   where A.pk_bankdoc=isnull(C.bank,'~')) bd_freecustom ";
  }

  /**
   * �˴����뷽��˵����
   * �������ڣ�(01-4-18 20:05:25)
   * 
   * @return java.lang.String
   */
  @Override
  public String getWherePart() {
    String sql = null;
    if (this.m_cfreecustid != null && !this.m_cfreecustid.trim().equals("")) {
      sql = "pk_freecustom = '" + this.m_cfreecustid + "'";
    }
    else {
      sql = "1=1";
    }
    return sql;
  }

  @Override
  public boolean isCacheEnabled() {
    return false;
  }

  public void setCfreecustid(String cfreecustid) {
    this.m_cfreecustid = cfreecustid;
  }

  @Override
  public void setRefNodeName(String newRefNodeName) {
    super.setRefNodeName(newRefNodeName);
  }

}
