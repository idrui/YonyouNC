package nc.vo.pu.m27.query;

import java.io.Serializable;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ɹ���������ѯ��ͬ��ⵥʱ������Ĳ�����(���ڽ����ѯ��ⵥ)
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-9-3 ����10:27:22
 */
public class M27StockQueryPara implements Serializable {

  private static final long serialVersionUID = 3243928097660806504L;

  // �ɹ����FROM��WHERE
  private String m45fandw;

  // ί�����FROM��WHERE
  private String m47fandw;

  // �������FROM��WHERE
  private String m4Afandw;

  // �ڳ��ݹ����FROM��WHERE
  private String m4Tfandw;

  public String getM45fandw() {
    return this.m45fandw;
  }

  public String getM47fandw() {
    return this.m47fandw;
  }

  public String getM4Afandw() {
    return this.m4Afandw;
  }

  public String getM4Tfandw() {
    return this.m4Tfandw;
  }

  public void setM45fandw(String m45fandw) {
    this.m45fandw = m45fandw;
  }

  public void setM47fandw(String m47fandw) {
    this.m47fandw = m47fandw;
  }

  public void setM4Afandw(String m4Afandw) {
    this.m4Afandw = m4Afandw;
  }

  public void setM4Tfandw(String m4Tfandw) {
    this.m4Tfandw = m4Tfandw;
  }
}
