package nc.bs.pu.cgfa.ace.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.cgfa.AggCgfa;
import nc.vo.pu.cgfa.Cgfa;
import nc.vo.pu.cgfa.Cgfab;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;

public class CalculateSum implements IRule<AggCgfa> {

	@Override
	public void process(AggCgfa[] vos) {
		Cgfa headvo = vos[0].getParentVO();
		Cgfab[] bodyvo = (Cgfab[]) vos[0].getChildrenVO();
		Integer sunnum = new Integer(0);
		UFDouble plan_priceb_sum = new UFDouble(0);
		int rows = 0;
		// ɾ��������Ȼ�ڴ���ʾ����������ɸѡ��
		for (int i = 0; i < bodyvo.length; i++) {

			if (bodyvo[i].getStatus() != 3) {
				rows+=1;

				if (bodyvo[i].getAttributeValue("plan_num") != null) {

					sunnum += (Integer) bodyvo[i].getAttributeValue("plan_num");
				}
				if (bodyvo[i].getAttributeValue("plansum_priceb") != null) {
					plan_priceb_sum = plan_priceb_sum.add((UFDouble) bodyvo[i]
							.getAttributeValue("plansum_priceb"));
				}

			}
		}
		headvo.setAttributeValue("sumnum", rows);

		headvo.setAttributeValue("hdef3",plan_priceb_sum.toString());
		
		//headvo.setAttributeValue("forecastprice", plan_pricea);
		headvo.setAttributeValue("sumplannum", sunnum);
		headvo.setStatus(VOStatus.UPDATED);
		headvo.setAttributeValue("dr", 0);

		// ==== lijj =====
		// Ԥ��� forecastprice
		// offer_type �������� ��˰ 1�� ����˰ 2
		/*UFDouble sumMny = UFDouble.ZERO_DBL;
		int offer_type = (int) headvo.getAttributeValue("offer_type");
		if (offer_type == 1) {
			for (int i = 0; i < bodyvo.length; i++) {
				sumMny = sumMny.add((UFDouble) bodyvo[i]
						.getAttributeValue("plansum_pricea"));
			}

		} else if (offer_type == 2) {
			for (int i = 0; i < bodyvo.length; i++) {
				sumMny = sumMny.add((UFDouble) bodyvo[i]
						.getAttributeValue("plansum_priceb"));
			}
		}

		headvo.setAttributeValue("forecastprice", sumMny);
		// ==== lijj =====
*/
	}

}