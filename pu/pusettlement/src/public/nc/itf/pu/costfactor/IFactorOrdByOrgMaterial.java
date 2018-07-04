/**
 * $�ļ�˵��$
 * 
 * @author chenlla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-22 ����10:26:34
 */
package nc.itf.pu.costfactor;

import nc.vo.pu.costfactor.entity.CostfactorViewVO;
import nc.vo.pub.BusinessException;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������֯������ID��ѯ�ɱ�Ҫ��VO
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author chenlla
 * @time 2010-6-22 ����10:26:34
 */
public interface IFactorOrdByOrgMaterial {

  /**
   * ��������������������֯������ID��ѯ�ɱ�Ҫ��VO(��������κζ���Ĺ�������)(���ý���ʱʹ��)
   * <p>
   * <b>����˵��</b>
   * 
   * @param pkOrg
   * @param pkSrcmaterials
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-9-1 ����03:49:41
   */
  public CostfactorViewVO[] queryFactorViewVOByOrgMaterial(String pkOrg,
      String[] pkSrcmaterials) throws BusinessException;

  /**
   * ��������������������֯������ID��ѯ�ɱ�Ҫ��VO(�Ƿ�������ɱ�=Y)(�����ݹ�ʱʹ��)
   * <p>
   * <b>����˵��</b>
   * 
   * @param pkOrg
   * @param pkSrcmaterials
   * @return
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author hanbin
   * @time 2010-9-1 ����03:50:26
   */
  public CostfactorViewVO[] queryVOByOrgMaterial(String pkOrg,
      String[] pkSrcmaterials) throws BusinessException;
}
