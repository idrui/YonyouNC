/**
 * $�ļ�˵��$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-5 ����04:52:21
 */
package nc.bs.pu.m21.query;

import java.util.HashMap;
import java.util.Map;

import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�������������˿ⵥ���˻�����ѯ�����Ҫƴ�ӵ���䡣
 * <li>�ɹ�������ͷ���ԡ��˻�/���Ƿ����ԭ���������������Ϊ�ǣ�
 * <li>����ڸòɹ��������ɵ��˻��������˿ⵥ����ʱ���ո�����д��Ӧ�������ۼƵ����������ۼ����������
 * <li>���ڲ��������ڵ���ղ�ѯ�˻������˿ⵥʱ�����˻������˿ⵥ��ѯ������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-6-5 ����04:52:21
 */
public class OrderQueryBrefBP {

  /**
   * �����������������ݶ������������ѯ�����ġ��˻�/�����ԭ��������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param ids
   * @return key:�������� value:��������
   * @throws BusinessException
   *           <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-6-5 ����04:42:03
   */
  public Map<String, UFBoolean> getBrefwhenreturnMap(String[] ids) {
    if (ArrayUtils.isEmpty(ids)) {
      return null;
    }

    VOQuery<OrderHeaderVO> query =
        new VOQuery<OrderHeaderVO>(OrderHeaderVO.class, new String[] {
          OrderHeaderVO.PK_ORDER, OrderHeaderVO.BREFWHENRETURN
        });

    OrderHeaderVO[] headerVOs = query.query(ids);
    if (ArrayUtils.isEmpty(headerVOs)) {
      return null;
    }

    Map<String, UFBoolean> retMap = new HashMap<String, UFBoolean>();
    for (OrderHeaderVO headerVO : headerVOs) {
      retMap.put(headerVO.getPk_order(), headerVO.getBrefwhenreturn());
    }
    return retMap;
  }
}
