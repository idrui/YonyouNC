package nc.vo.pu.m27.enumeration;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>����ķ�ʽ���Զ����㡢ͬ���Ͻ��㡢�����Ͻ��㡢�޷�Ʊ���㡢���ý���ȣ�
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-9-30 ����02:32:15
 */
public enum EnumSettleType {
  /** �����Ͻ��� */
  DIFFERENT_MATERIAL,

  /** ���ý��� */
  FEE,

  /** ��Ʊ�Զ����� */
  INVOICE_AUTO,

  /** ���������Ͻ��� */
  IT_DIFFERENT_MATERIAL,

  /** ���ڷ��ý��� */
  IT_FEE,

  /** ���ڷ�Ʊ�Զ����� */
  IT_INVOICE_AUTO,

  /** ����ͬ���Ͻ��� */
  IT_SAME_MATERIAL,

  /** ���ڽ����Զ����� */
  IT_UI_AUTO,

  /** �����޷�Ʊ���� */
  IT_WITHOUT_INVOICE,

  /** ͬ���Ͻ��� */
  SAME_MATERIAL,

  /** �����Զ����� */
  UI_AUTO,

  /** VMI���� */
  VOI_CONSUME,

  /** VMI���ý��� */
  VOI_CONSUME_FEE,

  /** �޷�Ʊ���� */
  WITHOUT_INVOICE

}
