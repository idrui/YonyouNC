package nc.pubitf.pu.m21transtype;

import java.util.HashMap;
import java.util.Map;

import nc.vo.pu.m21transtype.entity.PoTransTypeVO;
import nc.vo.pub.BusinessException;

/**
 * �ɹ������������Ͳ�ѯ����
 * <ul>
 * <li>�ع�Ϊ��ǰ̨�����ʵ�֣�ʹ��UAP��AOP�������ɲμ�UPM������ by zhaoyha at 2011.12
 * </ul>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2009-7-6 ����11:02:58
 */
public interface IPoTransTypeQuery {

  /**
   * ����������������ù���ֱ������ת�ɹ�ҵ�����ݵ�SQLƬ��
   * <p>
   * <b>ʹ��ʾ����</b><br>
   * Ҫ��ù���ֱ������ת���͵Ĳɹ���ⵥ��SQLƬ�Σ���ѯ�ɹ���ⵥʱ�ı������Ϊb��
   * �ɹ���ⵥ�����¼�ɹ������������͵��ֶ���Ϊcfirsttranstype����<br>
   * IPoTransTypeQuery query =
   * NCLocator.getInstance().lookup(IPoTransTypeQuery.class);<br>
   * String whereSql = query.getDirectTransWhereString("b",
   * MetaNameConst.CFIRSTTRANSTYPE);<br>
   * ����whereSql���᷵���������µ��ַ����� <br>
   * inner join po_potrantype on po_potrantype.pk_group = b.pk_group and
   * po_potrantype.vtrantypecode = b.cfirsttranstype and po_potrantype.bdirect =
   * 'Y'
   * <p>
   * <b>����˵��</b>
   * 
   * @param tableName
   *          ���������߱�����
   * @param joinField
   *          �����ֶ�
   * @return SQLƬ��
   *         <p>
   * @since 6.0
   * @author duy
   * @time 2010-10-25 ����10:37:25
   */
  public String getDirectTransWhereString(String tableName, String joinField);

  /**
   * �����������������ݼ��źͽ������ͱ����ѯ�ɹ����������������á�
   * <p>
   * <b>ʹ��ʾ��:</b>
   * <p>
   * <b>����˵��</b>
   * 
   * @param transTypes
   *          ��������
   * @param sAttriName ��Ҫ��ѯ�����ԣ����Ϊnull������������
   * @return HashMap<transType, PoTransTypeVO>
   * @throws BusinessException
   *           <p>
   * @author wangyf
   * @time 2009-7-6 ����11:02:58
   */
  public HashMap<String, PoTransTypeVO> queryAttrByTypes(String[] transTypes,
      String[] sAttriName) throws BusinessException;

  /**
   * ����������ѯ����������������
   * 
   * @param ids ����
   * @return key������ value��VO
   * @throws BusinessException
   */
  Map<String, PoTransTypeVO> queryAttrByIDs(String[] ids)
      throws BusinessException;
}
