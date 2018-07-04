package nc.impl.ws.pu.m20;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.bs.ws.pu.m20.MaterialValidate;
import nc.bs.ws.pu.m20.MeasDocValidate;
import nc.itf.uap.pf.IPFBusiAction;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.ws.entity.MsgCGAG000001;
import nc.vo.pu.m20.ws.entity.MsgCGAG000001Head;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.wsitf.pu.m20.IMsgCGAG000001;

/**
 * <p>本类主要实现功能：</p>
 *
 * <li>接收攀钢采购计划信息 服务接口实现</li>
 * 
 * @author lyw
 * @version 6.5
 * @time 2017年3月16日 下午2:08:06
 */
public class MsgCGAG000001Impl implements IMsgCGAG000001 {
	//接口执行失败信息
	List<String[]> list_err = new ArrayList<String[]>();
	//接口执行成功信息
	List<String[]> list_sucess = new ArrayList<String[]>();
	
	IPFBusiAction pf = (IPFBusiAction) NCLocator.getInstance().lookup(IPFBusiAction.class.getName());


	/* （非 Javadoc）
	 * @see nc.wsitf.pu.m20.IMsgCGAG000001#receiveMsgCGAG000001(nc.vo.pu.m20.ws.entity.MsgCGAG000001Head)
	 */
	@Override
	public String receiveMsgCGAG000001(MsgCGAG000001Head msgHead) {
		// TODO 自动生成的方法存根
		//String pk_group = AppContext.getInstance().getPkGroup();
		String pk_group = "0001N610000000000IT0";
		//鞍钢国贸攀枝花有限公司
		String pk_org = "0001N610000000002JW4";
		String pk_user = AppContext.getInstance().getPkUser();
		UFDate ddate = AppContext.getInstance().getBusiDate();
		UFDateTime ddatetime = AppContext.getInstance().getServerTime();
		
		List<MsgCGAG000001> msg = msgHead.getMsgBody();
		List<MsgCGAG000001> tmsg = msgHead.getMsgBody();

		//数据验证
		for ( int i = 0; i <msg.size(); i++) {
			String materialname = msg.get(i).getItemName();
			String materialspec = msg.get(i).getItemSpec();
			String materialtype = msg.get(i).getItemType();
			String marbaseclass = "1001N610000000000480";
			String graphid = msg.get(i).getItemDrawnumber();
			String measdoc = msg.get(i).getUom();
			String pk_measdoc = MeasDocValidate.measDocPKByName(measdoc);
			if (pk_measdoc == null) {
				pk_measdoc = MeasDocValidate.generateMeasDoc(pk_group,pk_org,pk_user,measdoc,ddate,ddatetime);
			}
			//组成攀钢物料编码，用于NC物料档案编码
			String materialcode = "PG" + msg.get(i).getItemId();
			String pk_material = MaterialValidate.getPKByName(materialcode);
			Integer version = 1;
			if (pk_material == null || pk_material.isEmpty()) {
				pk_material = MaterialValidate.generateMaterialDoc(pk_group,pk_org,pk_user,materialcode,
						materialname,pk_measdoc,materialspec,materialtype,marbaseclass,graphid,ddate,ddatetime,version);
			}
			//验证物料信息是否相同
			pk_material = MaterialValidate.checkuniformity(materialcode, materialspec, materialtype, pk_measdoc);
			if (pk_material.isEmpty() || pk_material == null) {
				version = MaterialValidate.getVersion(materialcode)+1;
				//删除bd_material_v中的物料信息
				MaterialValidate.deleteMaterial(materialcode);
				pk_material = MaterialValidate.generateMaterialDoc(pk_group, pk_org, pk_user, materialcode, materialname, 
						pk_measdoc, materialspec, materialtype, marbaseclass, graphid, ddate, ddatetime, version);
			};
			String seq_fac_plan = msg.get(i).getMrId();
			//请购单VO
			PraybillVO prayvo = new PraybillVO() ;
			PraybillHeaderVO hvo = prayvo.getHVO();
			PraybillItemVO[] bvo =prayvo.getBVO();
			hvo.setCreationtime(ddatetime);
			hvo.setCreator(pk_user);
			hvo.setBillmaker(pk_user);
			hvo.setDbilldate(ddate);
			hvo.setPk_planpsn(pk_user);
			//hvo.setPk_plandept(pk_plandept);
			hvo.setVtrantypecode("20-Cxx-02");
			hvo.setPk_group(pk_group);
			hvo.setDmakedate(ddate);
			hvo.setPk_org(pk_org);
			//hvo.setPk_org_v(pk_org_v);
			//hvo.setReq_type(req_type);
			hvo.setFbillstatus(0);
			prayvo.setHVO(hvo);
			for ( int j = i; j < tmsg.size(); j++ ) {
				String tplanno = tmsg.get(j).getMrId();
				//判定是否同一需求计划信息明细
				if (seq_fac_plan.equals(tplanno)) {
					String dreqdate = msg.get(i).getDeliveryDate();
					String seq_fac_no = msg.get(i).getMrLineId();
					String vbmemo = msg.get(i).getMrRemark();
					String filelist = msg.get(i).getFileList();
					BigDecimal pric_req = msg.get(i).getItemPlanprice();
					BigDecimal nastnum = msg.get(i).getQty();
					String name_fact = msg.get(i).getOrignFactoryName();
					String ywy = msg.get(i).getBuyerCode();
					String req_unit = msg.get(i).getCompanyName();
					String cunitid = msg.get(i).getUom();
					String material_quality = msg.get(j).getItemMaterial();
					Integer rowno = (j+1)*10;
					bvo[j].setCrowno(rowno.toString());
					bvo[j].setSeq_fac_no(seq_fac_no);
					bvo[j].setSeq_fac_plan(seq_fac_plan);
					bvo[j].setPk_material(pk_material);
					bvo[j].setNastnum(new UFDouble(nastnum));
					bvo[j].setPric_req(new UFDouble(pric_req));
					bvo[j].setCunitid(cunitid);
					bvo[j].setBrowclose(UFBoolean.FALSE);
					bvo[j].setName_fact(name_fact);
					bvo[j].setDreqdate(new UFDate(dreqdate));
					bvo[j].setVbmemo(vbmemo);
					bvo[j].setMaterial_quality(material_quality);
					bvo[j].setPk_org(pk_org);
					//bvo[j].setPk_org_v(pk_org_v);
					bvo[j].setPk_group(pk_group);
					bvo[j].setPk_reqdept(req_unit);
					//bvo[j].setPk_reqdept_v(req_unit_v);
					bvo[j].setPk_employee(ywy); 
				} 
				prayvo.setBVO(bvo);
				this.receiveMsg(prayvo);
			}
		}
		if ( list_err.size() > 0 ) {
			return list_err.get(0).toString();
		}
		return list_sucess.get(0).toString();
	}

	public void receiveMsg(PraybillVO prayvo) {
		try {
			pf.processAction("SAVEBASE", "20", null, prayvo, null, null);
			list_sucess.add(new String[] {"采购计划","接收成功"});
		} catch (BusinessException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			list_err.add(new String[] {"采购计划",e.toString()});
		}

	}
}
