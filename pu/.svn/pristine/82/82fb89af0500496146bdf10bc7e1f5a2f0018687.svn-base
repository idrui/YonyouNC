package nc.pubimpl.pu.m21.cmp.m36d1.handler;

import java.util.List;
import java.util.Map;

import nc.impl.pubapp.bill.rewrite.BillRewriter;
import nc.impl.pubapp.bill.rewrite.ItemKeyMapping;
import nc.impl.pubapp.bill.rewrite.RewritePara;
import nc.vo.cmp.apply.ApplyBVO;
import nc.vo.cmp.apply.ApplyVO;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;

public class PayPlanBillRewriter extends BillRewriter {

	public PayPlanBillRewriter(ItemKeyMapping mapping) {
		super(mapping);
	}

	@Override
	public Map<String, List<RewritePara>> splitForInsert(IBill[] bills) {
		Map<String, List<RewritePara>> map = super.splitForInsert(bills);
		IVOMeta metaData = bills[0].getMetaData().getChildren()[0];
		for (IBill bill : bills) {
			ApplyVO vo = (ApplyVO) bill.getParent();
			ISuperVO[] vos = bill.getChildren(metaData);
			String typecode = (String) vos[0]
					.getAttributeValue(ApplyBVO.PK_SRCBILLTYPEID);
			List<RewritePara> list = map.get(typecode);
			for (RewritePara para : list) {
				para.setSrcbTS(vo.getSrcts());
			}
		}
		return map;
	}

}
