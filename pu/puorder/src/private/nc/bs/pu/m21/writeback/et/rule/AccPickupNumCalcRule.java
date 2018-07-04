package nc.bs.pu.m21.writeback.et.rule;

import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m21.et.IOrderWriteBackParaForET;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 *            ���ں�ͬ��д�ɹ�����ʱ�������ۼƼ��������
 * @scene
 *      ���ں�ͬ��д�ɹ�����
 * @param
 * 
 *
 * @since 6.0
 * @version 2013-10-21 ����09:31:12
 * @author zhangyhh
 */
public class AccPickupNumCalcRule implements IRule<OrderViewVO> {

  private IOrderWriteBackParaForET[] wbVos;

  public AccPickupNumCalcRule(IOrderWriteBackParaForET[] wbVos) {
    this.wbVos = wbVos;
  }

  @Override
  public void process(OrderViewVO[] views) {
    if (ArrayUtils.isEmpty(views)) {
      return;
    }
    Map<String, OrderViewVO> viewMap = CirVOUtil.createKeyVOMap(views);
    for (IOrderWriteBackParaForET vo : this.wbVos) {
      OrderViewVO view = viewMap.get(vo.getBID());
      UFDouble diffNum = vo.getDiffNum();
      UFDouble pickupnum = view.getNaccumpickupnum();
      pickupnum = MathTool.add(pickupnum, diffNum);
      view.setNaccumpickupnum(pickupnum);
    }
  }
}
