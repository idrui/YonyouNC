package nc.bs.pu.m20.maintain.rule.insert;

import java.util.ArrayList;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.uap.IUAPQueryBS;
import nc.vo.arap.uforeport.SqlBuffer;
import nc.vo.lm.erpcgjhjk.ErpcgjhjkHVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;

import org.apache.commons.lang.ArrayUtils;

public class RwritegfjktdataRule implements IRule<PraybillVO> {
	/**
	 * ��������ͷ��Դ�����������¹ɷݲɹ��ƻ��ӿ��м�� ����ע��msginfo��,��Ϣ״̬��msgflag���ֶ�
	 */
	@Override
	public void process(PraybillVO[] vos) {

		IUAPQueryBS qbs = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		BaseDAO dao = new BaseDAO();
		if (ArrayUtils.isEmpty(vos)) {
			return;
		}

		for (PraybillVO aggvo : vos) {
			PraybillItemVO[] bvos = (PraybillItemVO[]) aggvo
					.getChildren(PraybillItemVO.class);
			for (PraybillItemVO bvo : bvos) {
				SqlBuffer sqlWhere = new SqlBuffer();
				// String csourceid=bvo.getCsourceid();//��Դ��������
				sqlWhere.append("nvl(dr,0)  =0 \n ");
				sqlWhere.append("and pk_erpcgjhjk = '");
				sqlWhere.append(bvo.getCsourceid());
				sqlWhere.append("' ");
				try {
					ArrayList<ErpcgjhjkHVO> gfhvos = (ArrayList<ErpcgjhjkHVO>) qbs
							.retrieveByClause(ErpcgjhjkHVO.class,
									sqlWhere.toString());
					if (gfhvos != null && gfhvos.size() > 0) {
						for (int i = 0; i < gfhvos.size(); i++) {
							ErpcgjhjkHVO cgahvo = gfhvos.get(i);

							cgahvo.setMsginfo("����ɹ�");
							cgahvo.setMsgflag("2");
						}
						// �����ʸֲɹ��ƻ��ӿ��м��
						dao.updateVOArray(gfhvos.toArray(new ErpcgjhjkHVO[0]),
								new String[] { "msginfo", "msgflag" });

					}
				} catch (BusinessException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}

			}

		}

	}

}
