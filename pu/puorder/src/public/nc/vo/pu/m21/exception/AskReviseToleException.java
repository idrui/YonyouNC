package nc.vo.pu.m21.exception;

import nc.vo.pu.exception.AskYesNoException;

/**
 * �ɹ������޶��ݲ���
 * ����ʹ��AskNumException����Ϊ�빺���ݲ����Ѿ�����
 * 
 * @since 6.0
 * @version 2011-12-15 ����04:28:31
 * @author wuxla
 */
public class AskReviseToleException extends AskYesNoException {

  private static final long serialVersionUID = 891686642322825238L;

  public AskReviseToleException(String msg) {
    super(msg);
  }

}
