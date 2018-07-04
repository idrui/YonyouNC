/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-8 上午10:24:57
 */
package nc.bs.pu.m4t.maintain.rule.maintain;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.pub.util.ApproveFlowUtil;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.util.AuditInfoUtils;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @description
 * 表尾审计填充：填充制单时间(创建时间)、最后修改时间、制单人、最后修改人、创建人
 * @scene
 * 期初暂估单保存
 * @param
 * 无
 *
 * @since 6.3
 * @version 2010-9-8 上午10:24:57
 * @author wuxla
 */

public class InitialEstTailItemFillRule implements IRule<InitialEstVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(InitialEstVO[] vos) {

    List<InitialEstVO> newlist = new ArrayList<InitialEstVO>();
    List<InitialEstVO> updlist = new ArrayList<InitialEstVO>();
    for (InitialEstVO vo : vos) {
      if (vo.getHeader().getStatus() == VOStatus.NEW
          || StringUtils.isEmpty(vo.getHeader().getPk_initialest())) {
        newlist.add(vo);
      }
      else {
        updlist.add(vo);
      }
    }
    InitialEstVO[] newVos = newlist.toArray(new InitialEstVO[newlist.size()]);
    AuditInfoUtils.setAddAuditInfo(newVos);
    ApproveFlowUtil.setBillMakeInfo(newVos);
    AuditInfoUtils.setUpdateAuditInfo(updlist.toArray(new InitialEstVO[updlist
        .size()]));
  }
}
