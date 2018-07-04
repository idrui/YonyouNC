package nc.vo.pu.setup;

import nc.bs.ecpubapp.accountmanage.NewInstallAjust4PU;
import nc.bs.sm.accountmanage.INewInstallAdjust;

/**
 * �ɹ���װ����
 * 
 * @since 6.0
 * @version 2011-10-28 ����10:23:17
 * @author wuxla
 */

public class PuInstallAdjust implements INewInstallAdjust {

  @Override
  public void adjust(String newVersion) throws Exception {
    /**
     * �ڰ�װͬʱ��װ��EC��SCM��Ʒʱ����Ҫ
     * 1����EC��Ʒ�Ĳɹ��������ɹ�Э���ͬ���ɹ�������ѯ����ͬЭ�鱨�۲�ѯ���ɹ�Э���ͬ��ѯ������ܽڵ���Ϊ������
     * 2����SCM�Ĳɹ������еķ����������ˡ�����ʱ�䡢��Ӧ״̬����Ӧ�ˡ���Ӧʱ�䡢�ܾ�ԭ��7���ֶ���Ϊ��ʾ���ɱ༭״̬
     * 3����SCM�ĵ����ƻ���������ϵ�ˡ���ϵ��ʽ�����䷽ʽ�����ƺš���ע����ֶ���Ϊ��ʾ���ɱ༭״̬��
     */
    NewInstallAjust4PU ecinstall = new NewInstallAjust4PU();
    ecinstall.adjust(newVersion);
  }

}
