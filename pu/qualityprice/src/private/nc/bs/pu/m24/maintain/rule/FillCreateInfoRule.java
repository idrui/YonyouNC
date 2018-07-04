package nc.bs.pu.m24.maintain.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m24.entity.PricestlVO;
import nc.vo.pubapp.util.SetAddAuditInfoRule;

/**
 * <p>
 * <b>本类主要完成以下功能： </b> 填充制单时间(创建时间)、制单人 最后修改人，最后修改时间
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author liuchx
 * @time 2010-5-18 下午04:26:30
 */

public class FillCreateInfoRule implements IRule<PricestlVO> {

  @Override
  public void process(PricestlVO[] vos) {

    /**
     * 设置新增时的审计信息
     */
    new SetAddAuditInfoRule<PricestlVO>().process(vos);

  }

}
