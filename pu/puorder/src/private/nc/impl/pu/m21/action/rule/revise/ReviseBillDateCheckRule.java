package nc.impl.pu.m21.action.rule.revise;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.util.TimeUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�޶����ڲ����ڶ�����������
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-3-21 ����11:12:25
 */
public class ReviseBillDateCheckRule implements IRule<OrderVO> {

  @Override
  public void process(OrderVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }

    StringBuilder sb = new StringBuilder();
    for (OrderVO vo : vos) {
      if (null == vo) {
        continue;
      }

      UFDate audittime = vo.getHVO().getTaudittime();
      UFDate revisiontime = vo.getHVO().getTrevisiontime();
      if (TimeUtils.before(revisiontime, audittime)) {
        sb.append(NCLangResOnserver.getInstance().getStrByID("4004030_0", "04004030-0289", null, new String[]{vo.getHVO().getVbillcode()})/*����{0}���޶����ڲ������ڶ�����������*/);
      }
    }

    if (sb.length() > 0) {
      ExceptionUtils.wrappBusinessException(sb.toString());
    }
  }

}
