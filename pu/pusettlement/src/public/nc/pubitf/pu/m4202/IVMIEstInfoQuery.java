/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-25 ����02:09:05
 */
package nc.pubitf.pu.m4202;

import java.util.Map;

import nc.vo.pu.m4202.entity.outsrv.VMIEstInfoVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>Ϊ������Ļ��ܵ��ݹ���Ϣ��ѯ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-9-25 ����02:09:05
 */
public interface IVMIEstInfoQuery {

  /**
   * ����������������ѯ���Ļ��ܵ��ݹ���Ϣ��Ϊ������ʾ����ʹ�á�
   * <p>
   * <b>����˵��</b>
   * 
   * @param vmiIDs
   *          ���Ļ��ܱ�ͷ��������
   * @return Map(���Ļ��ܱ�ͷ����,VMIEstInfoVO)������Ϊnull
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-9-25 ����02:20:43
   */
  public Map<String, VMIEstInfoVO> queryEstInfo(String[] vmiIDs)
      throws BusinessException;
}
