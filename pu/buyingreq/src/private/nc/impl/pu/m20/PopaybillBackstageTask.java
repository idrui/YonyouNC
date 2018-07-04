package nc.impl.pu.m20;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.pa.PreAlertReturnType;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.itf.lm.IPgjhgzMaintain;
import nc.itf.pu.m20.IParybillstaus;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.vo.lm.pgjhgz.AggPgjhgzHVO;
import nc.vo.lm.pgjhgz.PgjhgzHVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.ms.MsgAGCG000001;
import nc.vo.pu.ms.MsgAGCG000001Head;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.AppContext;

public class PopaybillBackstageTask implements IBackgroundWorkPlugin {
/**
 * ��ʱ���񣺸����빺������ļƻ�״̬��ʱ�䴫�͵��м����NC �ʸּƻ�״̬���ٽڵ�
 */
	@Override
	public PreAlertObject executeTask(BgWorkingContext bgwc)
			throws BusinessException {
		PreAlertObject alert = new PreAlertObject();
		alert.setReturnType(PreAlertReturnType.RETURNNOTHING);

		UFDateTime time = new UFDateTime();// ���ö�ʱ����ǰʱ��
		String sql = "select * from po_praybill_b where csourcetypecode ='0001ZZ1000000003B4FG' and nvl(dr,0)=0 and  Sts_req is not null";// ��ѯ���ʸֲɹ��ƻ������б�������// and tmstp_dispatch <> '~' 
		List<PraybillItemVO> bvos = (List<PraybillItemVO>) getService()
				.executeQuery(sql, new BeanListProcessor(PraybillItemVO.class));
		List<AggPgjhgzHVO> aggpgjhgzhvolists = new ArrayList<AggPgjhgzHVO>();
		IPgjhgzMaintain service = NCLocator.getInstance().lookup(
				IPgjhgzMaintain.class);
		IParybillstaus ws = NCLocator.getInstance()
				.lookup(IParybillstaus.class);
		if (bvos != null && bvos.size() > 0) {

			for (PraybillItemVO bvo : bvos) {
				MsgAGCG000001Head msghvo = new MsgAGCG000001Head();
				List<MsgAGCG000001> list = new ArrayList<MsgAGCG000001>();
				MsgAGCG000001 msgbvo = new MsgAGCG000001();
				UFDateTime bmodifiedtime = (UFDateTime) bvo
						.getAttributeValue("bmodifiedtime");// ����޸�ʱ��
				UFDateTime transtime = bvo.getTranstime();// ����ʱ��
				String mrid = (String) bvo.getAttributeValue("mrid");// �ƻ������
				String mrlineid = (String) bvo.getAttributeValue("mrlineid");// ����ƻ��к�
				String sts_req = bvo.getSts_req();// �ƻ�״̬
				String pk_praybill_b = bvo.getPk_praybill_b();// �빺������
				String name = this.getUname(pk_praybill_b);

				/**
				 * .���ö�ʱ����
				 */
				if (bmodifiedtime == null) {
					if (sts_req.equals("01") || sts_req.equals("10")
							|| sts_req.equals("03") || sts_req.equals("11")
							|| sts_req.equals("05") || sts_req.equals("12")
							|| sts_req.equals("09")) {
						AggPgjhgzHVO aggjhvo = new AggPgjhgzHVO();
						PgjhgzHVO pzhgzhvo = new PgjhgzHVO();
						pzhgzhvo.setPk_group("0001N610000000000IT0");
						pzhgzhvo.setPk_org("GLOBLE00000000000000");
						pzhgzhvo.setMrid(mrid);// ����ƻ���
						pzhgzhvo.setMrlineid(mrlineid);// ����ƻ��к�
						pzhgzhvo.setStateid(sts_req);// �ƻ�״̬
						pzhgzhvo.setOptime(time.toString().substring(0, 11));
						pzhgzhvo.setDbilldate(new UFDate());
						aggjhvo.setParent(pzhgzhvo);
						aggpgjhgzhvolists.add(aggjhvo);

						/**
						 * �Ե��ĵ���ʽͨ���ӿ��빺���ƻ�״̬������ݷ�������
						 */
						msgbvo.setName(name);// ҵ��Ա����
						msgbvo.setMrid(mrid);
						msgbvo.setMrlineid(mrlineid);
						msgbvo.setOptime(time.toString().substring(0, 11));
						switch (sts_req) {
						case "01":// �ƻ�¼��
							msgbvo.setType("PP");
							msgbvo.setStateid("create");
							break;
						case "10":// �ƻ���Ч
							msgbvo.setType("PP");
							msgbvo.setStateid("active");
							break;
						case "03":// �����ƶ�
							msgbvo.setType("PK");
							msgbvo.setStateid("create");
							break;
						case "11":// ��������
							msgbvo.setType("PK");
							msgbvo.setStateid("active");
							break;
						case "05":// ��ͬ�ƶ�
							msgbvo.setType("CT");
							msgbvo.setStateid("create");
							break;
						case "12":// ��ͬ��Ч
							msgbvo.setType("CT");
							msgbvo.setStateid("active");
							break;
						case "09":// �ܾ�
							msgbvo.setType("MR");
							msgbvo.setStateid("back");
							break;
						default:
							msgbvo.setType(null);
							msgbvo.setStateid(null);
						}
						list.add(msgbvo);
						msghvo.setMsgBody(list);
						msghvo.setMsgId("MsgAGCG000001");
						msghvo.setResourceId("MsgAGCG");
						String result = ws.MsgAGXSHT0001(msghvo);
					}
					/**
					 * end
					 */
				} else {

					if ((transtime != bmodifiedtime && bmodifiedtime
							.after(transtime))) {
						if (sts_req.equals("01") || sts_req.equals("10")
								|| sts_req.equals("03") || sts_req.equals("11")
								|| sts_req.equals("05") || sts_req.equals("12")
								|| sts_req.equals("09")) {
							AggPgjhgzHVO aggjhvo = new AggPgjhgzHVO();
							PgjhgzHVO pzhgzhvo = new PgjhgzHVO();
							pzhgzhvo.setPk_group("0001N610000000000IT0");
							pzhgzhvo.setPk_org("GLOBLE00000000000000");
							pzhgzhvo.setMrid(mrid);// ����ƻ���
							pzhgzhvo.setMrlineid(mrlineid);// ����ƻ��к�
							pzhgzhvo.setStateid(sts_req);// �ƻ�״̬
							pzhgzhvo.setOptime(time.toString().substring(0, 11));
							pzhgzhvo.setDbilldate(new UFDate());
							aggjhvo.setParent(pzhgzhvo);
							aggpgjhgzhvolists.add(aggjhvo);
							/**
							 * �Ե��ĵ���ʽͨ���ӿ��빺���ƻ�״̬������ݷ�������
							 */
							msgbvo.setName(name);// ҵ��Ա����
							msgbvo.setMrid(mrid);
							msgbvo.setMrlineid(mrlineid);
							msgbvo.setOptime(time.toString().substring(0, 11));
							switch (sts_req) {
							case "01":// �ƻ�¼��
								msgbvo.setType("PP");
								msgbvo.setStateid("create");
								break;
							case "10":// �ƻ���Ч
								msgbvo.setType("PP");
								msgbvo.setStateid("active");
								break;
							case "03":// �����ƶ�
								msgbvo.setType("PK");
								msgbvo.setStateid("create");
								break;
							case "11":// ��������
								msgbvo.setType("PK");
								msgbvo.setStateid("active");
								break;
							case "05":// ��ͬ�ƶ�
								msgbvo.setType("CT");
								msgbvo.setStateid("create");
								break;
							case "12":// ��ͬ��Ч
								msgbvo.setType("CT");
								msgbvo.setStateid("active");
								break;
							case "09":// �ܾ�
								msgbvo.setType("MR");
								msgbvo.setStateid("back");
								break;
							default:
								msgbvo.setType(null);
								msgbvo.setStateid(null);
							}
							list.add(msgbvo);
							msghvo.setMsgBody(list);
							msghvo.setMsgId("MsgAGCG000001");
							msghvo.setResourceId("MsgAGCG");
							String result = ws.MsgAGXSHT0001(msghvo);
							/**
							 * end
							 */
						}
					}
				}

			}
			// �������������м���ʸּƻ����ٽڵ�
			if (aggpgjhgzhvolists.size() > 0 && aggpgjhgzhvolists != null) {
				try {
					service.insert(
							aggpgjhgzhvolists.toArray(new AggPgjhgzHVO[0]),
							aggpgjhgzhvolists.toArray(new AggPgjhgzHVO[0]));
				} catch (BusinessException e) {
					e.printStackTrace();
				}
			}
			/**
			 * end
			 */
			if (bvos.size() > 0 && bvos != null) {
				// ������д�빺���������ʱ������
				PraybillItemVO[] lbvos = bvos.toArray(new PraybillItemVO[bvos
						.size()]);
				for (PraybillItemVO bvo : lbvos) {
					bvo.setAttributeValue("transtime", time);
					bvo.setAttributeValue("bmodifiedtime", time);

				}
				VOUpdate<PraybillItemVO> update = new VOUpdate<PraybillItemVO>();
				String[] rrr = new String[] { "transtime", "bmodifiedtime" };
				update.update(lbvos, rrr);
				// end
			}
		}

		return alert;
	}

	private IUAPQueryBS service = null;

	private IUAPQueryBS getService() {
		if (service == null) {
			service = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		}
		return service;
	}

	/**
	 * ���ݵ�ǰ��¼���û�������ȡ��Ա����
	 * 
	 * @param pk_user
	 * @return
	 */
	private String getUname(String pk_praybill_b) {
		String name = null;
		String sql = "select h.creator from po_praybill_b b left join po_praybill h on b.pk_praybill=h.pk_praybill where b.pk_praybill_b='"
				+ pk_praybill_b + "'";
		try {
			String creator = (String) getService().executeQuery(sql,
					new ColumnProcessor());
			String sql2 = "select name from bd_psndoc where   pk_psndoc ='"
					+ creator + "'";
			name = (String) getService().executeQuery(sql2,
					new ColumnProcessor());

		} catch (BusinessException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return name;

	}

}
