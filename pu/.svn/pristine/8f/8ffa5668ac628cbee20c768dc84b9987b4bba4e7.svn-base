package nc.bs.pu.m21.writeback.ic.rule;

import java.util.Map;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.pu.m21.ic.reserve.IOrderWriteBackPara;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.pub.util.CirVOUtil;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>计算累计预留数量
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-10 下午03:07:25
 */
public class SupplyReserveNumCalRule implements IRule<OrderViewVO> {
  private IOrderWriteBackPara[] paras;

  public SupplyReserveNumCalRule(IOrderWriteBackPara[] paras) {
    this.paras = paras;
  }

  @Override
  public void process(OrderViewVO[] vos) {
    if (ArrayUtils.isEmpty(this.paras)) {
      return;
    }

    Map<String, OrderViewVO> viewMap = CirVOUtil.createKeyVOMap(vos);

    for (IOrderWriteBackPara para : this.paras) {
      OrderViewVO view = viewMap.get(para.getBID());
      UFDouble diffNum = para.getDiffNum();
      UFDouble newSuprsnum = MathTool.add(view.getNsuprsnum(), diffNum);
      view.setNsuprsnum(newSuprsnum);
    }
  }

}
