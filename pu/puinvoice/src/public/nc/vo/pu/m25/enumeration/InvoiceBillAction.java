/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-19 ����04:31:45
 */
package nc.vo.pu.m25.enumeration;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��Ʊ������ƽ̨��������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-4-19 ����04:31:45
 */
public enum InvoiceBillAction {
  /** ���� **/
  APPROVE,
  /** ȡ����Ӧ�� **/
  CANCELSENDAP,
  /** ɾ�� **/
  DISCARD,
  /** ���ɹ���ⵥ��ʽ���� **/
  M45PUSHSAVEM25,
  /** ���� **/
  SAVE,
  /** ���� **/
  SAVEBASE,
  /** ��Ӧ�� **/
  SENDAP,
  /** ���� **/
  UNAPPROVE;
}
