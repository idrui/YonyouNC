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
 * 定时任务：根据请购单表体的计划状态，时间传送到中间表，和NC 攀钢计划状态跟踪节点
 */
	@Override
	public PreAlertObject executeTask(BgWorkingContext bgwc)
			throws BusinessException {
		PreAlertObject alert = new PreAlertObject();
		alert.setReturnType(PreAlertReturnType.RETURNNOTHING);

		UFDateTime time = new UFDateTime();// 调用定时任务当前时间
		String sql = "select * from po_praybill_b where csourcetypecode ='0001ZZ1000000003B4FG' and nvl(dr,0)=0 and  Sts_req is not null";// 查询拉攀钢采购计划的所有表体数据// and tmstp_dispatch <> '~' 
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
						.getAttributeValue("bmodifiedtime");// 最后修改时间
				UFDateTime transtime = bvo.getTranstime();// 传递时间
				String mrid = (String) bvo.getAttributeValue("mrid");// 计划需求号
				String mrlineid = (String) bvo.getAttributeValue("mrlineid");// 需求计划行号
				String sts_req = bvo.getSts_req();// 计划状态
				String pk_praybill_b = bvo.getPk_praybill_b();// 请购单主键
				String name = this.getUname(pk_praybill_b);

				/**
				 * .调用定时服务
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
						pzhgzhvo.setMrid(mrid);// 需求计划号
						pzhgzhvo.setMrlineid(mrlineid);// 需求计划行号
						pzhgzhvo.setStateid(sts_req);// 计划状态
						pzhgzhvo.setOptime(time.toString().substring(0, 11));
						pzhgzhvo.setDbilldate(new UFDate());
						aggjhvo.setParent(pzhgzhvo);
						aggpgjhgzhvolists.add(aggjhvo);

						/**
						 * 以电文的形式通过接口请购单计划状态相关数据发送数据
						 */
						msgbvo.setName(name);// 业务员名称
						msgbvo.setMrid(mrid);
						msgbvo.setMrlineid(mrlineid);
						msgbvo.setOptime(time.toString().substring(0, 11));
						switch (sts_req) {
						case "01":// 计划录入
							msgbvo.setType("PP");
							msgbvo.setStateid("create");
							break;
						case "10":// 计划生效
							msgbvo.setType("PP");
							msgbvo.setStateid("active");
							break;
						case "03":// 方案制定
							msgbvo.setType("PK");
							msgbvo.setStateid("create");
							break;
						case "11":// 方案生成
							msgbvo.setType("PK");
							msgbvo.setStateid("active");
							break;
						case "05":// 合同制定
							msgbvo.setType("CT");
							msgbvo.setStateid("create");
							break;
						case "12":// 合同生效
							msgbvo.setType("CT");
							msgbvo.setStateid("active");
							break;
						case "09":// 拒绝
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
							pzhgzhvo.setMrid(mrid);// 需求计划号
							pzhgzhvo.setMrlineid(mrlineid);// 需求计划行号
							pzhgzhvo.setStateid(sts_req);// 计划状态
							pzhgzhvo.setOptime(time.toString().substring(0, 11));
							pzhgzhvo.setDbilldate(new UFDate());
							aggjhvo.setParent(pzhgzhvo);
							aggpgjhgzhvolists.add(aggjhvo);
							/**
							 * 以电文的形式通过接口请购单计划状态相关数据发送数据
							 */
							msgbvo.setName(name);// 业务员名称
							msgbvo.setMrid(mrid);
							msgbvo.setMrlineid(mrlineid);
							msgbvo.setOptime(time.toString().substring(0, 11));
							switch (sts_req) {
							case "01":// 计划录入
								msgbvo.setType("PP");
								msgbvo.setStateid("create");
								break;
							case "10":// 计划生效
								msgbvo.setType("PP");
								msgbvo.setStateid("active");
								break;
							case "03":// 方案制定
								msgbvo.setType("PK");
								msgbvo.setStateid("create");
								break;
							case "11":// 方案生成
								msgbvo.setType("PK");
								msgbvo.setStateid("active");
								break;
							case "05":// 合同制定
								msgbvo.setType("CT");
								msgbvo.setStateid("create");
								break;
							case "12":// 合同生效
								msgbvo.setType("CT");
								msgbvo.setStateid("active");
								break;
							case "09":// 拒绝
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
			// 将数据新增到中间表：攀钢计划跟踪节点
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
				// 批量回写请购单表体相关时间数据
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
	 * 根据当前登录人用户主键获取人员名称
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
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return name;

	}

}
