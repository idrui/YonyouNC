package nc.impl.pu.m25.action.rule.approve;

import java.util.ArrayList;
import java.util.List;

import nc.impl.pubapp.pattern.rule.IFilterRule;
import nc.vo.pu.m25.entity.InvoiceItemVO;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pubapp.pattern.pub.ListToArrayTool;
import nc.vo.scmpub.res.billtype.MMBillType;

import org.apache.commons.collections.CollectionUtils;

/**
 * 
 * @description
 * 过滤掉工序委外加工费结算单
 * @scene
 * 审批
 * @param
 * 无
 *
 * @since 6.3
 * @version 2014-10-22 下午2:49:36
 * @author zhangshqb
 */
public class VOFilterFor55E6Rule implements IFilterRule<InvoiceVO> {

  @Override
  public InvoiceVO[] process(InvoiceVO[] vos) {
    if (null == vos) {
      return null;
    }
    List<InvoiceVO> filterVoLst = new ArrayList<InvoiceVO>();
    for (InvoiceVO vo : vos) {
      InvoiceItemVO[] bvos = vo.getChildrenVO();
      if (!MMBillType.PscSettle.getCode().equals(bvos[0].getCsourcetypecode())) {
        filterVoLst.add(vo);
      }
    }
    if (CollectionUtils.isEmpty(filterVoLst)) {
      return null;
    }
    return new ListToArrayTool<InvoiceVO>().convertToArray(filterVoLst);
  }

}
