package nc.ui.pu.m21transtype;

import nc.bs.framework.common.NCLocator;
import nc.itf.pubapp.pub.smart.ISmartService;
import nc.vo.pu.m21transtype.entity.PoTransTypeVO;

/**
 * �ɹ������������ͷ�����
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ѯ�ɹ�������������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-1-9 ����10:48:51
 */
public class PoTransTypeService {
  private static ISmartService service = (ISmartService) NCLocator
      .getInstance().lookup(ISmartService.class.getName());

  /**
   * ����������������ѯ�ɹ������������͡�
   * <p>
   * <b>����˵��</b>
   * 
   * @param wherePart
   *          ��ѯ����
   * @return �ɹ�������������
   * @throws Exception
   *           <p>
   * @since 6.0
   * @author GGR
   * @time 2010-1-9 ����10:48:55
   */
  public static PoTransTypeVO[] queryTranstypeExtProp(String wherePart)
      throws Exception {
    return (PoTransTypeVO[]) PoTransTypeService.service.selectByWhereSql(
        wherePart, PoTransTypeVO.class);
  }
}
