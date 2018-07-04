/**
 * $文件说明$
 * 
 * @author GGR
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-6-10 下午08:33:16
 */
package nc.bs.pu.m20.maintain.rule.pub;

import java.util.List;
import java.util.Map;

import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.vo.pu.m20.entity.PraybillVO;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-6-10 下午08:33:16
 */
public interface IRewite {

  // /**
  // * 方法功能描述：设置上游单据VO的class。
  // * <p>
  // * <b>参数说明</b>
  // *
  // * @param tool
  // * @param billtype
  // * <p>
  // * @since 6.0
  // * @author GGR
  // * @time 2010-6-10 下午08:50:40
  // */
  // void addSRCClazz(BillRewriter tool, String billtype);

  /**
   * 调用实际回写类回写上游
   */
  void writeback(List<RewritePara> rwParas, Map<String, PraybillVO> bidVOMap);

  // /**
  // * 方法功能描述：调用实际回写类回写上游。
  // * <p>
  // * <b>参数说明</b>
  // *
  // * @param rwParas
  // * @param billtype
  // * <p>
  // * @since 6.0
  // * @author GGR
  // * @time 2010-6-10 下午08:50:49
  // */
  // void writeback(List<RewritePara> rwParas, String billtype, UFBoolean bsc);
}
