/**
 * $�ļ�˵��$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-1-11 ����11:24:26
 */
package nc.bs.pu.m21.query;

import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.vo.pu.m21.entity.OrderCloseVO;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author zhaoyha
 * @time 2010-1-11 ����11:24:26
 */
public class OrderCloseQueryBP {

  /**
   * ����������������ѯ�����رսڵ����õ�VO��
   * <p>
   * <b>����˵��</b>
   * 
   * @param bodyIds
   * @return
   *         <p>
   * @since 6.0
   * @author zhaoyha
   * @time 2010-1-11 ����11:34:07
   */
  public OrderCloseVO[] query(String[] bodyIds) {
    // ֮����ӹ���
    OrderCloseVO[] vos =
        new ViewQuery<OrderCloseVO>(OrderCloseVO.class).query(bodyIds);
    if (ArrayUtils.isEmpty(vos)) {
      return null;
    }
    this.calNumAndMny(vos);
    return vos;
  }

  private void calNumAndMny(OrderCloseVO[] vos) {
    for (OrderCloseVO vo : vos) {
      // δ�����ԭ�ң�
      if (vo.getNnopayorgmny() == null) {
        vo.setNnopayorgmny(MathTool.sub(vo.getNtaxmny(),
            vo.getNacccancelinvmny()));
      }
      // �ɵ�������
      if (vo.getNcanarrivenum() == null) {
        vo.setNcanarrivenum(MathTool.sub(vo.getNnum(), vo.getNaccumarrvnum()));
      }
      // ���������
      if (vo.getNcaninnum() == null) {
        vo.setNcaninnum(MathTool.sub(vo.getNnum(), vo.getNaccumstorenum()));
      }
      // �ɿ�Ʊ����
      if (vo.getNcaninvoicenum() == null) {
        vo.setNcaninvoicenum(MathTool.sub(vo.getNnum(),
            vo.getNaccuminvoicenum()));
      }
    }
  }
}
