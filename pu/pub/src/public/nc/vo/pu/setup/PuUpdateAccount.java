package nc.vo.pu.setup;

import nc.bs.ecpubapp.accountmanage.UpdateAccount4PU;
import nc.bs.sm.accountmanage.IUpdateAccount;

/**
 * �ɹ�����
 * 
 * @since 6.0
 * @version 2011-10-28 ����10:14:32
 * @author wuxla
 */

public class PuUpdateAccount implements IUpdateAccount {

  @Override
  public void doAfterUpdateData(String oldVersion, String newVersion)
      throws Exception {
    /**
     * �ڰ�װͬʱ��װ��EC��SCM��Ʒʱ����Ҫ
     * 1����EC��Ʒ�Ĳɹ��������ɹ�Э���ͬ���ɹ�������ѯ����ͬЭ�鱨�۲�ѯ���ɹ�Э���ͬ��ѯ������ܽڵ���Ϊ������
     * 2����SCM�Ĳɹ������еķ����������ˡ�����ʱ�䡢��Ӧ״̬����Ӧ�ˡ���Ӧʱ�䡢�ܾ�ԭ��7���ֶ���Ϊ��ʾ���ɱ༭״̬
     * 3����SCM�ĵ����ƻ���������ϵ�ˡ���ϵ��ʽ�����䷽ʽ�����ƺš���ע����ֶ���Ϊ��ʾ���ɱ༭״̬��
     */
    UpdateAccount4PU ecupdate = new UpdateAccount4PU();
    ecupdate.doAfterUpdateData(oldVersion, newVersion);

  }

  @Override
  public void doBeforeUpdateData(String oldVersion, String newVersion)
      throws Exception {
    UpdateAccount4PU ecupdate = new UpdateAccount4PU();
    ecupdate.doBeforeUpdateData(oldVersion, newVersion);
  }

  @Override
  public void doBeforeUpdateDB(String oldVersion, String newVersion)
      throws Exception {
    UpdateAccount4PU ecupdate = new UpdateAccount4PU();
    ecupdate.doBeforeUpdateDB(oldVersion, newVersion);
  }

}
