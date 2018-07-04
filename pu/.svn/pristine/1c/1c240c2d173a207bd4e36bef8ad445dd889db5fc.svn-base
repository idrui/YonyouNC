/**
 * $文件说明$
 * 
 * @author zhaoyha
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-20 上午09:59:29
 */
package nc.pubimpl.pu.m25.pu.settle.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.pattern.rule.IFilterRule;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pu.m25.pub.InvoiceVOUtil;
import nc.vo.pu.m25trantype.entity.InvcTranTypeVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 根据交易类型过滤结算完毕自动传应付的发票
 * @scene
 * 结算(匹配)后自动传应付
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午3:47:58
 * @author zhangshqb
 */
public class SettleAutoSendAPFilterRule implements IFilterRule<InvoiceVO> {

  @Override
  public InvoiceVO[] process(InvoiceVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return vos;
    }
    Map<String, InvcTranTypeVO> ttcodeMap = InvoiceVOUtil.getTranstype(vos);
    if (MapUtils.isEmpty(ttcodeMap)) {
      return null;
    }
    List<InvoiceVO> filterVos = new ArrayList<InvoiceVO>();
    for (InvoiceVO vo : vos) {
      String ttcode = vo.getParentVO().getVtrantypecode();
      InvcTranTypeVO ttVo = ttcodeMap.get(ttcode);
      if (this.isCanAutoSendAP(vo, ttVo)) {
        filterVos.add(vo);
      }
    }
    return filterVos.toArray(new InvoiceVO[filterVos.size()]);
  }

  private boolean isCanAutoSendAP(InvoiceVO vo, InvcTranTypeVO ttVo) {
    // 如果是虚拟发票，则一定传应付，不管理交易类型
    if (ValueUtils.getBoolean(vo.getParentVO().getBvirtual())) {
      return true;
    }
    if (null == ttVo) {
      return false;
    }
    /**
     * 采购发票结算完毕是否自动传应付，由交易类型属性“结算完毕自动传应付”决定：如果勾选了，
     * 则采购发票所有表体行全部结算完毕，则自动传应付，
     * 如果没有勾选，则即使全部结算完毕也不自动传应付，需要通过传应付按钮手工触发传应付。
     */
    if (!ValueUtils.getBoolean(ttVo.getBsendpay())) {
      return false;
    }
    for (InvoiceItemVO item : vo.getChildrenVO()) {
      // wuxla v61
      // UFDouble mny = MathTool.nvl(item.getNmny());
      UFDouble ncalcostmny = MathTool.nvl(item.getNcalcostmny());
      UFDouble settleMny = MathTool.nvl(item.getNaccumsettmny());
      if (!ncalcostmny.equals(settleMny)) {
        return false;// 未结算完毕则不能传应付
      }
    }
    return true;
  }

}
