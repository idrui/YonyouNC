/**
 * $�ļ�˵��$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-10 ����08:33:16
 */
package nc.bs.pu.m20.maintain.rule.pub;

import java.util.List;
import java.util.Map;

import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.vo.pu.m20.entity.PraybillVO;

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
 * @author GGR
 * @time 2010-6-10 ����08:33:16
 */
public interface IRewite {

  // /**
  // * ���������������������ε���VO��class��
  // * <p>
  // * <b>����˵��</b>
  // *
  // * @param tool
  // * @param billtype
  // * <p>
  // * @since 6.0
  // * @author GGR
  // * @time 2010-6-10 ����08:50:40
  // */
  // void addSRCClazz(BillRewriter tool, String billtype);

  /**
   * ����ʵ�ʻ�д���д����
   */
  void writeback(List<RewritePara> rwParas, Map<String, PraybillVO> bidVOMap);

  // /**
  // * ������������������ʵ�ʻ�д���д���Ρ�
  // * <p>
  // * <b>����˵��</b>
  // *
  // * @param rwParas
  // * @param billtype
  // * <p>
  // * @since 6.0
  // * @author GGR
  // * @time 2010-6-10 ����08:50:49
  // */
  // void writeback(List<RewritePara> rwParas, String billtype, UFBoolean bsc);
}
