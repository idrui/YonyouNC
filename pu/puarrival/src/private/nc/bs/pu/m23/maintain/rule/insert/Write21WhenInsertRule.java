package nc.bs.pu.m23.maintain.rule.insert;

import java.util.List;
import java.util.Map;

import nc.bs.pu.m23.writeback.source.AbstractWrite21;
import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.env.ArrivalUIToBSEnv;
import nc.vo.pu.m23.utils.ArrivePublicUtil;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * ����������õ��˹��򣬱�����Ҫ������¹��ܣ�
 * ��д�ɹ��������ۼƵ���������;������
 * @scene
 * ��������������
 * @param
 * env
 *
 * @since 6.3
 * @version 2010-1-14 ����03:42:46
 * @author hanbin
 */

public class Write21WhenInsertRule extends AbstractWrite21 implements
    IRule<ArriveVO> {

  /**
   * @param env
   */
  public Write21WhenInsertRule(ArrivalUIToBSEnv env) {
    super(env);
  }

  @Override
  public void process(ArriveVO[] vos) {
    if (ArrayUtils.isEmpty(vos) || ArrivePublicUtil.isArriveFromSC(vos)) {
      return;
    }
    BillRewriter tool = this.getBillRewriter(this.getItemKeyMapping());
    Map<String, List<RewritePara>> rwParaMap = tool.splitForInsert(vos);
    List<RewritePara> paras = rwParaMap.get(POBillType.Order.getCode());

    // ��д�ۼƵ�����������д�ۼ�;������
    this.writeback(paras, vos);
  }

}
