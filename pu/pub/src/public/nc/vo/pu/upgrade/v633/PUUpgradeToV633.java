package nc.vo.pu.upgrade.v633;

import nc.bs.logging.Logger;
import nc.bs.sm.accountmanage.IUpdateAccount;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

/**
 * �ɹ�����v633����������
 * 
 * @version 6.33
 * @since 6.33
 * @author mengjian
 * @time 2014-09-24 ����09:35:29
 */
public class PUUpgradeToV633 implements IUpdateAccount {

  // private static final String NC63 = "6.3";
  //
  // private static final String NC633 = "6.33";

  @Override
  public void doAfterUpdateData(String oldVersion, String newVersion)
      throws Exception {
    if (this.isVersionEmpty(oldVersion, newVersion)) {
      // �ɰ汾���°汾��֪���Ļ����޷�������������
      return;
    }
    // if (oldVersion.startsWith(PUUpgradeToV633.NC63)
    // && newVersion.startsWith(PUUpgradeToV633.NC633)) {
    this.doAfterUpdateDataFrom63To633();
    // }
  }

  @Override
  public void doBeforeUpdateData(String oldVersion, String newVersion)
      throws Exception {
    if (this.isVersionEmpty(oldVersion, newVersion)) {
      // �ɰ汾���°汾��֪���Ļ����޷�������������
      return;
    }
    // if (oldVersion.startsWith(PUUpgradeToV633.NC63)
    // && newVersion.startsWith(PUUpgradeToV633.NC633)) {
    this.doBeforeUpdateDataFrom63To633();
    // }
  }

  @Override
  public void doBeforeUpdateDB(String oldVersion, String newVersion)
      throws Exception {
    if (this.isVersionEmpty(oldVersion, newVersion)) {
      // �ɰ汾���°汾��֪���Ļ����޷�������������
      return;
    }
    // if (oldVersion.startsWith(PUUpgradeToV633.NC63)
    // && newVersion.startsWith(PUUpgradeToV633.NC633)) {
    this.doBeforeUpdateDBFrom63To633();
    // }
  }

  private boolean isEmpty(String str) {
    return str == null || str.length() == 0;
  }

  private boolean isVersionEmpty(String oldVersion, String newVersion) {
    return this.isEmpty(oldVersion) || this.isEmpty(newVersion);
  }

  /**
   * ����po_invoice�����¼��ֶ� finvoicetype�ֶ� Ϊ0
   */
  private void upInvoiceColumnData() {
    Logger.debug("�ɹ���Ʊ��Ʊ�����ֶ�������ʼ������������");
    SqlBuilder sql = new SqlBuilder();
    sql.append(" update po_invoice set finvoicetype=0 where finvoicetype is null ");
    DataAccessUtils util = new DataAccessUtils();
    util.update(sql.toString());
    Logger.debug("�ɹ���Ʊ��Ʊ�����ֶ���������������������");

  }

  /**
   * ����po_settlebill���� �¼��ֶ�fsettletype�ֶ� Ϊ0 ,0��ʾΪ�ɹ����㵥
   */
  private void upSettleBillColumnData() {
    SqlBuilder sql = new SqlBuilder();
    sql.append(" update po_settlebill set fsettletype=0 ");
    DataAccessUtils util = new DataAccessUtils();
    util.update(sql.toString());

  }

  /**
   * ���������������
   * 
   * @throws BusinessException
   */
  protected void doAfterUpdateDataFrom63To633() throws BusinessException {
    // ������Ҫ�����ิд
    // ���㵥ͷ ����������� Ĭ�� ����Ϊ0 ���ɹ����㣩
    this.upSettleBillColumnData();

    this.upInvoiceColumnData();
  }

  /**
   * ������������֮ǰ����
   * 
   * @throws BusinessException
   */
  protected void doBeforeUpdateDataFrom63To633() throws BusinessException {
    // ������Ҫ�����ิд
  }

  /**
   * �������ݿ�ṹ����֮ǰ����
   * 
   * @throws BusinessException
   */
  protected void doBeforeUpdateDBFrom63To633() throws BusinessException {
    // ������Ҫ�����ิд

  }
}
