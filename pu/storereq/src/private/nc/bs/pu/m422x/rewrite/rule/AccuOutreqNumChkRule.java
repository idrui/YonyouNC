package nc.bs.pu.m422x.rewrite.rule;

import nc.bs.ml.NCLangResOnserver;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m422x.entity.StoreReqAppViewVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

/**
 * 
 * @description
 *            �������뵥�����д�����������뵥���ۼ�����������������
 * @scene
 *      �������뵥����
 * @param
 * 
 *
 * @since 6.0
 * @version 2010-12-16 ����03:24:20
 * @author wuxla
 */
public class AccuOutreqNumChkRule implements IRule<StoreReqAppViewVO> {
  @Override
  public void process(StoreReqAppViewVO[] views) {
    StringBuilder sb = new StringBuilder();
    for (StoreReqAppViewVO view : views) {
      if (MathTool.compareTo(view.getNaccuoutreqnum(), UFDouble.ZERO_DBL) < 0) {
        sb.append(NCLangResOnserver.getInstance().getStrByID("4004010_0", "04004010-0018", null, new String[]{view.getVbillcode(),view.getCrowno()})/*�����������뵥{0}�ϵ�{1}�е��ۼƳ�����������������С���㣡�����������������\n*/);
        continue;
      }

      if (MathTool.compareTo(view.getNaccuoutreqnum(), view.getNnum()) > 0) {
        sb.append(NCLangResOnserver.getInstance().getStrByID("4004010_0", "04004010-0019", null, new String[]{view.getVbillcode(),view.getCrowno()})/*������������{0}�ϵ�{1}�е��ۼƳ���������������������Χ�����޸ĳ�������������\n*/);
      }
    }

    if (sb.length() > 0) {
      ExceptionUtils.wrappBusinessException(sb.toString());
    }
  }

}
