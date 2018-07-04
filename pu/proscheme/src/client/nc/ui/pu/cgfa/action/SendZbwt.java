package nc.ui.pu.cgfa.action;

import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import nc.ui.ls.MessageBox;
import nc.ui.pubapp.uif2app.view.ShowUpableBillListView;
import nc.bs.framework.common.NCLocator;
import nc.bs.pub.pf.PfUtilTools;
import nc.bs.trade.business.HYPubBO;
import nc.md.data.access.NCObject;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.MDPersistenceService;
import nc.pub.pushlet.util.Log;
import nc.ui.pubapp.uif2app.query2.model.ModelDataManager;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.trade.business.HYPubBO_Client;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.model.BillManageModel;
import nc.uif.pub.exception.UifException;
import nc.vo.lm.pgcgdhxx.AggAgxsht001HVO;
import nc.vo.lm.zbwtxxjk.AggZbwtxxjkHVO;
import nc.vo.lm.zbwtxxjk.ZbwtxxbjBVO;
import nc.vo.lm.zbwtxxjk.ZbwtxxgysBVO;
import nc.vo.pu.cgfa.AggCgfa;
import nc.vo.pu.cgfa.Cgfa;
import nc.vo.pu.cgfa.Cgfab;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.ws.vo.BidDelgMain;
import nc.vo.ws.vo.BidDelgSub;
import nc.vo.ws.vo.BidDelgSupplier;

public class SendZbwt extends NCAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3678026089572123911L;
	private ShowUpableBillForm editor;
	private BillManageModel model;
	private ModelDataManager dataManager;
	
	
	public SendZbwt(){
		this.setBtnName("传招标委托");
		this.setCode("czbwt");
	}
	public ShowUpableBillForm getEditor() {
		return editor;
	}
	public void setEditor(ShowUpableBillForm editor) {
		this.editor = editor;
	}
	public BillManageModel getModel() {
		return model;
	}
	public void setModel(BillManageModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}

	public ModelDataManager getDataManager() {
		return dataManager;
	}
	public void setDataManager(ModelDataManager dataManager) {
		this.dataManager = dataManager;
	}
	
	
	public void doAction(ActionEvent e) throws Exception {
		/*// TODO 自动生成的方法存根
		Object[] obj = model.getSelectedOperaDatas();
//		billlist.getBillListPanel().getBillValueVO(3, AggCgfa.class.getName(), Cgfa.class.getName(), Cgfab.class.getName());
//		billlist.getBillListPanel().getMultiSelectedVOs( AggCgfa.class.getName(), Cgfa.class.getName(), Cgfab.class.getName());
		List<AggCgfa> AggCgfa = new ArrayList<AggCgfa>();
		NCObject[] ncObjects;
		for(int i=0;i<obj.length;i++){
			AggCgfa aggvos = (AggCgfa) obj[i];
			String pkid=(String) aggvos.getParent().getAttributeValue("pk_equipment_id");
			ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond
					(Cgfab.class,"pk_equipment_id='"+pkid+"' and dr=0",false);
			Cgfab[] cgfabvo = new Cgfab[ncObjects.length];
			if(ncObjects!=null){
				for(int j=0;j<ncObjects.length;j++){
					cgfabvo[j]=(Cgfab) ncObjects[j].getContainmentObject();
				}
				aggvos.setChildren(Cgfab.class, cgfabvo);
			}
			AggCgfa.add(aggvos);
		}
		AggCgfa aggvo;
		BidDelgMain bidDelgMain = new BidDelgMain();
		List<BidDelgSub> bidDelgSubList = new ArrayList<BidDelgSub>();
		List<BidDelgSupplier> bidDelgSupplierList = new ArrayList<BidDelgSupplier>();
		List<AggZbwtxxjkHVO> aggZbwtxxjkHVOList = new ArrayList<AggZbwtxxjkHVO>();
		
		String objid="";
		SimpleDateFormat dr = new SimpleDateFormat("yyyyMMdd");//设置日期格式
		String datetime = dr.format(new Date());
		if(selectmax!=null&&!selectmax.equals("")){
			if(selectmax.substring(2, 10).equals(datetime)){
				int sum = Integer.parseInt(selectmax.substring(10, 13))+1+1000;
				objid ="GM"+datetime+String.valueOf(sum).substring(1, 4);
			}else{
				objid ="GM"+datetime+"001";
			}
		}else{
			objid ="GM"+datetime+"001";
		}
		
		aggvo = (AggCgfa) obj[0];
		Cgfa cgfa = aggvo.getParentVO();
		bidDelgMain.setObjid(objid);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		//委托时间
		bidDelgMain.setAuthTime(df.format(new Date()));
		//采购方案号
		bidDelgMain.setPurchasecode(null == cgfa.getAttributeValue("billno")? "":cgfa.getAttributeValue("billno").toString());
		//委托项目名称
		bidDelgMain.setNameAuthItem(null == cgfa.getAttributeValue("project_name")? "":cgfa.getAttributeValue("project_name").toString());
		//委托人
//		String code = iMsgBidDelg.selectname(AppContext.getInstance().getPkUser());
		String pk_psndoc = (String) new HYPubBO_Client().findColValue("sm_user", "pk_psndoc","cuserid='"+AppContext.getInstance().getPkUser()+"'");//当前用户
		String code = iMsgBidDelg.selectname(pk_psndoc);
		bidDelgMain.setNameAuth(code);
//		bidDelgMain.setNameAuth(code);
//		if(cgfa.getAttributeValue("salesman")!=null){
//			String code = iMsgBidDelg.selectname(cgfa.getAttributeValue("salesman").toString());
////			String code = iMsgBidDelg.selectname(AppContext.getInstance().getPkUser());
//			bidDelgMain.setNameAuth(code);
//		}
//		bidDelgMain.setNameAuth(null == cgfa.getAttributeValue("salesman")? "":cgfa.getAttributeValue("salesman").toString());
		//报价类型
		if(cgfa.getAttributeValue("offer_type")!=null){
			if(cgfa.getAttributeValue("offer_type").toString().equals("1")){
				bidDelgMain.setQuotationType("含税");
			}else{
				bidDelgMain.setQuotationType("不含税");
			}
		}
//		bidDelgMain.setQuotationType(null == cgfa.getAttributeValue("offer_type")? "":cgfa.getAttributeValue("offer_type").toString());
		//税率
		UFDouble rate= (UFDouble) (null == cgfa.getAttributeValue("rate")? new UFDouble(0):(UFDouble) cgfa.getAttributeValue("rate"));
		bidDelgMain.setTaxRate(BigDecimal.valueOf(rate.intValue()));
		//采购方式对应招标公司的招标方式
		String typebuy = null == cgfa.getAttributeValue("typebuy")? "":cgfa.getAttributeValue("typebuy").toString();
		if(typebuy.equals("01")){
			bidDelgMain.setTenderMode("公开招标");
		}else if(typebuy.equals("02")){
			bidDelgMain.setTenderMode("邀请招标");
		}else if(typebuy.equals("03")){
			bidDelgMain.setTenderMode("公开竞价");
		}else if(typebuy.equals("04")){
			bidDelgMain.setTenderMode("邀请竞价");
		}else if(typebuy.equals("05")){
			bidDelgMain.setTenderMode("竞争性谈判");
		}else if(typebuy.equals("06")){
			bidDelgMain.setTenderMode("单一来源");
		}
		//资质审核方式
		bidDelgMain.setQualVerifyMode(null == cgfa.getAttributeValue("qualification_way")? "":cgfa.getAttributeValue("qualification_way").toString());
		//评标方式
		if(cgfa.getAttributeValue("bid_evaluation")!=null){
			if(cgfa.getAttributeValue("bid_evaluation").toString().equals("01")){
				bidDelgMain.setBidEvalMode("经评审的最低投标价法");
			}else{
				bidDelgMain.setBidEvalMode("综合评估法");
			}
		}
		//付款方式描述
		bidDelgMain.setPayMode(null == cgfa.getAttributeValue("payment")? "":cgfa.getAttributeValue("payment").toString());
		//业务类型
		//bidDelgMain.setClassBusiness(null == cgfa.getAttributeValue("business_types")? "":cgfa.getAttributeValue("business_types").toString());
		bidDelgMain.setClassBusiness("设备备件");
		//招标属性
		if(cgfa.getAttributeValue("tenderprop")!=null){
			if(cgfa.getAttributeValue("tenderprop").toString().equals("1")){
				bidDelgMain.setTenderProp("日常标");
			}else{
				bidDelgMain.setTenderProp("协议标");
			}
		}
		//是否有标底
		String isBaseTender= (String) (null == cgfa.getAttributeValue("isbasetender")? "0":(String)cgfa.getAttributeValue("isbasetender"));
		bidDelgMain.setIsBaseTender(BigDecimal.valueOf(Integer.valueOf(isBaseTender)));
		//标底合计20170808修改将ufdouble改变成string
		UFDouble baseTenderSum= (UFDouble) (null == cgfa.getAttributeValue("basetendersum")? new UFDouble(0):cgfa.getAttributeValue("basetendersum"));
		bidDelgMain.setBaseTenderSum(BigDecimal.valueOf(baseTenderSum.doubleValue()));
		//交货期限签约后
		bidDelgMain.setLeadTime(null == cgfa.getAttributeValue("leadtime")? "":cgfa.getAttributeValue("leadtime").toString());
		//采购要求
		bidDelgMain.setPurchaseReq(null == cgfa.getAttributeValue("procurementplan")? "":cgfa.getAttributeValue("procurementplan").toString());
		//供应商资质要求
		bidDelgMain.setQualReq(null == cgfa.getAttributeValue("supplier_requirements")? "":cgfa.getAttributeValue("supplier_requirements").toString());
		//是否组合标，需要转换，nc系统内，代码为01,01，传招标中心，应为1或0
//		String combination_standard= (String) (null == cgfa.getAttributeValue("combination_standard")? "0":cgfa.getAttributeValue("combination_standard"));
//		bidDelgMain.setIsCombination(BigDecimal.valueOf(Integer.valueOf(combination_standard)));
		if(cgfa.getAttributeValue("combination_standard")!=null){
			if("01".equals(cgfa.getAttributeValue("combination_standard").toString())){
				bidDelgMain.setIsCombination(BigDecimal.valueOf(1));
			}else if("02".equals(cgfa.getAttributeValue("combination_standard").toString())){
				bidDelgMain.setIsCombination(BigDecimal.valueOf(0));	
			}
		}
		//报价响应率20170808修改将ufdouble改变成string
		UFDouble ratereply= (UFDouble) (null == cgfa.getAttributeValue("ratereply")?new UFDouble(0):cgfa.getAttributeValue("ratereply"));
		bidDelgMain.setResponseRate(BigDecimal.valueOf(ratereply.doubleValue()));
//		String ratereply= (String) (null == cgfa.getAttributeValue("ratereply")?"":cgfa.getAttributeValue("ratereply"));
//		bidDelgMain.setResponseRate(new BigDecimal(ratereply));
		//最高限价合计
		UFDouble limitedPriceTop= (UFDouble) (null == cgfa.getAttributeValue("limitedpricetop")? new UFDouble(0):cgfa.getAttributeValue("limitedpricetop"));
		bidDelgMain.setLimitedPriceTop(BigDecimal.valueOf(limitedPriceTop.doubleValue()));			
		//预测价
		UFDouble forecastPrice= (UFDouble) (null == cgfa.getAttributeValue("forecastprice")? new UFDouble(0):cgfa.getAttributeValue("forecastprice"));
		bidDelgMain.setForecastPrice(BigDecimal.valueOf(forecastPrice.doubleValue()));
		//标底
		bidDelgMain.setForecastDesc(null == cgfa.getAttributeValue("estimate")? "":cgfa.getAttributeValue("estimate").toString());
		//最高限价标准
		bidDelgMain.setLimitedPriceTopStd(null == cgfa.getAttributeValue("limitedpricetopstd")? "":cgfa.getAttributeValue("limitedpricetopstd").toString());
		//供应商
		int sumgys=0;
		for(int i=0;i<AggCgfa.size();i++){
			aggvo = (AggCgfa) AggCgfa.get(i);
			Cgfa cgfas = aggvo.getParentVO();
			String supplier = null == cgfas.getAttributeValue("suppliercode")? "":cgfas.getAttributeValue("suppliercode").toString().trim();//suppliercode 供应商编码
			String[] suppliers =supplier.split(",");
			if(suppliers[0]!=null&&!suppliers[0].toString().equals("")){
				sumgys+=suppliers.length;
				for(int k=0;k<suppliers.length;k++){
					BidDelgSupplier bidDelgSupplier = new BidDelgSupplier();
					bidDelgSupplier.setObjid(objid);
					bidDelgSupplier.setIdSupplier(suppliers[k].trim());
					bidDelgSupplierList.add(bidDelgSupplier);
				}
			}
			Cgfab[] cgfab = (Cgfab[]) aggvo.getChildrenVO();
			for(int j=0;j<cgfab.length;j++){
				BidDelgSub bidDelgSub = new BidDelgSub();
				bidDelgSub.setObjid(objid);
				//bidDelgSub.setNumberInt(null == cgfab[j].getAttributeValue("pk_equipment_sub")? "":cgfab[j].getAttributeValue("pk_equipment_sub").toString());
				bidDelgSub.setNumberInt(null == cgfab[j].getAttributeValue("number_code")? "":cgfab[j].getAttributeValue("number_code").toString());
				//2017-06-28修改物料编码物料名称及物料描述
				String selectwlname = iMsgBidDelg.selectwlname(null == cgfab[j].getAttributeValue("materialcode")? "":cgfab[j].getAttributeValue("materialcode").toString());
				String selectwlcode = iMsgBidDelg.selectwlcode(null == cgfab[j].getAttributeValue("materialcode")? "":cgfab[j].getAttributeValue("materialcode").toString());
				String selectwlgg = iMsgBidDelg.selectwlgg(null == cgfab[j].getAttributeValue("materialcode")? "":cgfab[j].getAttributeValue("materialcode").toString());
				bidDelgSub.setProductinfo(null == cgfab[j].getAttributeValue("materialname")? "":cgfab[j].getAttributeValue("materialname").toString());
				if(selectwlcode!=null){
					bidDelgSub.setClasscode(selectwlcode);
				}
				if(selectwlname!=null){
					bidDelgSub.setProjectname(selectwlname);
				}
				if(selectwlgg!=null){
					bidDelgSub.setAmoldtype(selectwlgg);
				}
				Integer plan_num= (Integer) (null == cgfab[j].getAttributeValue("plan_num")? "":cgfab[j].getAttributeValue("plan_num"));
				bidDelgSub.setProductcount(BigDecimal.valueOf(plan_num));
				String unit = iMsgBidDelg.selectunit(null == cgfab[j].getAttributeValue("unit")? "":cgfab[j].getAttributeValue("unit").toString());
				bidDelgSub.setUnit(unit);
				int offer_type =Integer.parseInt(null == cgfa.getAttributeValue("offer_type")?"1":cgfa.getAttributeValue("offer_type").toString());
				//2017-6-28参考单价(元)修改，根据主表报价类型是否含税，判断计划单价（不含税取plan_pricea、含税取plan_priceb）  
//				UFDouble plan_priceb= (UFDouble) (null == cgfab[j].getAttributeValue("plan_priceb")?new UFDouble(0):cgfab[j].getAttributeValue("plan_priceb"));
//				UFDouble plan_pricea= (UFDouble) (null == cgfab[j].getAttributeValue("plan_pricea")?new UFDouble(0):cgfab[j].getAttributeValue("plan_pricea"));
//				if(offer_type==1){
//					bidDelgSub.setPrice(BigDecimal.valueOf(plan_priceb.doubleValue()));
//				}else if(offer_type==2){
//					bidDelgSub.setPrice(BigDecimal.valueOf(plan_pricea.doubleValue()));
//				}
				//20171214修改计划单价
//				String vbdef1= (String) (null == cgfab[j].getAttributeValue("vbdef1")?new UFDouble(0):cgfab[j].getAttributeValue("vbdef1"));
				//20180123修改计划单价
				String vbdef1= null == cgfab[j].getAttributeValue("vbdef1")?"0":cgfab[j].getAttributeValue("vbdef1").toString();
				bidDelgSub.setPrice(BigDecimal.valueOf(Double.parseDouble(vbdef1)));
	
				//2017-6-28总价(元) 修改，根据主表报价类型是否含税，判断计划总价价（不含税取plansum_priceb、含税取plansum_pricea）
//				UFDouble plansum_pricea= (UFDouble) (null == cgfab[j].getAttributeValue("plansum_pricea")?new UFDouble(0):cgfab[j].getAttributeValue("plansum_pricea"));
//				UFDouble plansum_priceb= (UFDouble) (null == cgfab[j].getAttributeValue("plansum_priceb")?new UFDouble(0):cgfab[j].getAttributeValue("plansum_priceb"));
//				if(offer_type==1){
//					bidDelgSub.setSubprice(BigDecimal.valueOf(plansum_pricea.doubleValue()));
//				}else if(offer_type==2){
//					bidDelgSub.setSubprice(BigDecimal.valueOf(plansum_priceb.doubleValue()));
//				}
				//20171214修改计划单价
//				String vbdef2= (String) (null == cgfab[j].getAttributeValue("vbdef2")?new UFDouble(0):cgfab[j].getAttributeValue("vbdef2"));
				//20180123修改计划单价
				String vbdef2= null == cgfab[j].getAttributeValue("vbdef2")?"0":cgfab[j].getAttributeValue("vbdef2").toString();
				bidDelgSub.setSubprice(BigDecimal.valueOf(Double.parseDouble(vbdef2)));
				//工作地点
				bidDelgSub.setWorkplace(null == cgfab[j].getAttributeValue("workplace")? "":cgfab[j].getAttributeValue("workplace").toString());
				//中文货名物料描述对应招标中心物料详细描述，应取规格型号
				//规格型号或图号
				//String specifications_rule = null == cgfab[j].getAttributeValue("specifications_rule")? "":cgfab[j].getAttributeValue("specifications_rule").toString();		
				//bidDelgSub.setProductdepict(null == cgfab[j].getAttributeValue("materialname")? "":cgfab[j].getAttributeValue("materialname").toString());
				//17-07-23修改
				bidDelgSub.setProductdepict(null == cgfab[j].getAttributeValue("specifications_rule")? "":cgfab[j].getAttributeValue("specifications_rule").toString());
				//材质
				bidDelgSub.setMaterial(null == cgfab[j].getAttributeValue("material")? "":cgfab[j].getAttributeValue("material").toString());
				
				//bidDelgSub.setBuildstandard(null == cgfab[j].getAttributeValue("manufacturer")? "":cgfab[j].getAttributeValue("manufacturer").toString());//原始制造商需转换
				//使用寿命
				bidDelgSub.setZzField2(null == cgfab[j].getAttributeValue("rated_life")? "":cgfab[j].getAttributeValue("rated_life").toString());
				bidDelgSub.setCreatdate(df.format(new Date()));
				//备注，此处取NC内的备件编码及代码传递到对方备注内，（修改）
				//bidDelgSub.setRemark(null == cgfab[j].getAttributeValue("fobprice")? "":cgfab[j].getAttributeValue("fobprice").toString());
				bidDelgSub.setRemark(null == cgfab[j].getAttributeValue("number_code")? "":cgfab[j].getAttributeValue("number_code").toString());
				//2017-6-28制造标准 修改,生产商名称对应招标公司的制造标准
				bidDelgSub.setBuildstandard(null == cgfab[j].getAttributeValue("manufacturer")? "":cgfab[j].getAttributeValue("manufacturer").toString());
				//2017-6-28交货期   修改（NC内使用单位请求交货日期）
//				String request_date = null == cgfab[j].getAttributeValue("request_date")? "":cgfab[j].getAttributeValue("request_date").toString();
//				if(!request_date.equals("")){
//					request_date=request_date.replace("-",""); 
//					request_date=request_date.substring(0, 8);
//					bidDelgSub.setDeliverydate(request_date);
//				}
				//2017-7-26工厂代码和名称
//				String factory_code = null == cgfab[j].getAttributeValue("factory_code")? "":cgfab[j].getAttributeValue("factory_code").toString();
//				bidDelgSub.setZzField1(factory_code);
				//20180226修改工厂代码和名称
				String factory_name = null == cgfab[j].getAttributeValue("factory_name")? "":cgfab[j].getAttributeValue("factory_name").toString();
				bidDelgSub.setZzField1(factory_name);
				//20180223 factory_plan放进子表中
				bidDelgSub.setProductinfo(null == cgfab[j].getAttributeValue("factory_plan")? "":cgfab[j].getAttributeValue("factory_plan").toString());
				bidDelgSubList.add(bidDelgSub);
			}
			
			AggregatedValueObject[] srcVosAfterFilter = new AggregatedValueObject[] {
					aggvo //中间表
					    };
	    	//中间表AGGVO包装类名	nc.vo.lm.zbwtxxjk.AggZbwtxxjkHVO
			AggZbwtxxjkHVO aggZbwtxxjkHVO;
			aggZbwtxxjkHVO = (AggZbwtxxjkHVO) PfUtilTools.runChangeData("JT01", "DW89", srcVosAfterFilter[0]);//5720-Cxx-pgdh是出口合同（上游）单据交易类型，DW40是下游单据类型
			aggZbwtxxjkHVO.getParent().setStatus(VOStatus.NEW);
			aggZbwtxxjkHVO.getParent().setAttributeValue("objid", objid);
			aggZbwtxxjkHVOList.add(aggZbwtxxjkHVO);
			
		} 
		ZbwtxxgysBVO[] zbwtxxgysBVO = new ZbwtxxgysBVO[sumgys];
		int mxgys=0;
		for(int i=0;i<AggCgfa.size();i++){
			aggvo = (AggCgfa) AggCgfa.get(i);
			Cgfa cgfas = aggvo.getParentVO();
			String supplier = null == cgfas.getAttributeValue("suppliercode")? "":cgfas.getAttributeValue("suppliercode").toString().trim();//suppliercode 供应商编码
			String[] suppliers =supplier.split(",");
			if(suppliers[0]!=null&&!suppliers[0].toString().equals("")){
				sumgys+=suppliers.length;
				for(int k=0;k<suppliers.length;k++){
					ZbwtxxgysBVO bidDelgSupplier = new ZbwtxxgysBVO();
					bidDelgSupplier.setObjid(objid);
					bidDelgSupplier.setId_supplier(suppliers[k].trim());
					bidDelgSupplier.setStatus(2);
					zbwtxxgysBVO[mxgys+k]=bidDelgSupplier;
				}
				mxgys+=suppliers.length;
			}
		}
		bidDelgMain.setBidDelgSubList(bidDelgSubList);
		bidDelgMain.setBidDelgSupplierList(bidDelgSupplierList);
//		MessageBox.showErrorDialog("error", new Throwable());
		String result=iMsgBidDelg.IMsgBidDelg(bidDelgMain);
//		MessageBox.showErrorDialog("error", new Throwable(result));
		AggZbwtxxjkHVO aggZbwtxxjk= new AggZbwtxxjkHVO();
//		String code = iMsgBidDelg.selectname(AppContext.getInstance().getPkUser());
		aggZbwtxxjkHVOList.get(0).getParent().setAttributeValue("name_auth", code);
//		if(cgfa.getAttributeValue("salesman")!=null){
//			String code = iMsgBidDelg.selectname(cgfa.getAttributeValue("salesman").toString());
////			String code = iMsgBidDelg.selectname(AppContext.getInstance().getPkUser());
//			aggZbwtxxjkHVOList.get(0).getParent().setAttributeValue("name_auth", code);
//		}
		
		aggZbwtxxjk.setParent(aggZbwtxxjkHVOList.get(0).getParent());
		int summx=0;
		for(int l=0;l<aggZbwtxxjkHVOList.size();l++){
			AggZbwtxxjkHVO aggZbwtxxjkHVO=aggZbwtxxjkHVOList.get(l);
			if(aggZbwtxxjkHVO.getChildren(ZbwtxxbjBVO.class)!=null){
				summx+= aggZbwtxxjkHVO.getChildren(ZbwtxxbjBVO.class).length;
			}
		}
		if(summx!=0){
			ZbwtxxbjBVO[] zbwtxxbjBVO = new ZbwtxxbjBVO[summx];
			int mxwz=0;
	
			for(int l=0;l<aggZbwtxxjkHVOList.size();l++){
				AggZbwtxxjkHVO aggZbwtxxjkHVO=aggZbwtxxjkHVOList.get(l);
				ZbwtxxbjBVO[] zbwtxxbj = new ZbwtxxbjBVO[aggZbwtxxjkHVO.getChildren(ZbwtxxbjBVO.class).length];
				zbwtxxbj=(ZbwtxxbjBVO[]) aggZbwtxxjkHVO.getChildren(ZbwtxxbjBVO.class);
				for(int n=0;n<zbwtxxbj.length;n++){
					zbwtxxbj[n].setObjid(objid);
					zbwtxxbjBVO[mxwz+n] = zbwtxxbj[n];
				}
				mxwz+=zbwtxxbj.length;
			}
			for (ZbwtxxbjBVO zbwtxxbjBVO2 : zbwtxxbjBVO) {
				zbwtxxbjBVO2.setStatus(2);
			}
			aggZbwtxxjk.setChildren(ZbwtxxbjBVO.class, zbwtxxbjBVO);
		}
		aggZbwtxxjk.setChildren(ZbwtxxgysBVO.class, zbwtxxgysBVO);
		aggZbwtxxjk.getParent().setStatus(VOStatus.NEW);
		if ("1".equals(result)){
			aggZbwtxxjk.getParent().setAttributeValue("msgflag", 2);
			aggZbwtxxjk.getParent().setAttributeValue("dr", 0);
			MessageBox.showErrorDialog("error", new Throwable("发送失败"));
		}else{
			aggZbwtxxjk.getParent().setAttributeValue("msgflag", 1);
			aggZbwtxxjk.getParent().setAttributeValue("dr", 0);
			MessageBox.showMessageDialog("ok", "发送成功!");
		}
		HYPubBO_Client bo = new HYPubBO_Client();
		NCObject objs = NCObject.newInstance(aggZbwtxxjk);
		MDPersistenceService.lookupPersistenceService().saveBill(objs);
		try {
			bo.saveBill(aggZbwtxxjk);
		} catch (UifException e2) {
			// TODO 自动生成的 catch 块
			e2.printStackTrace();
		}
		for(int m=0;m<AggCgfa.size();m++){
			AggCgfa aggvocgfa=(AggCgfa) AggCgfa.get(m);
			aggvocgfa.getParent().setAttributeValue("seq_bj", objid);
			aggvocgfa.getParent().setAttributeValue("fsbz", "Y");
			aggvocgfa.getParent().setAttributeValue("hdef6", new UFDate(new Date()));
			aggvocgfa.getParent().setStatus(VOStatus.UPDATED);
			aggvocgfa.getAllChildrenVO();
			HYPubBO bos = new HYPubBO();
			try {
				bos.saveBill(aggvocgfa);
			} catch (UifException e2) {
				// TODO 自动生成的 catch 块
				e2.printStackTrace();
			}
		}
		int selectRow = model.getSelectedRow();
		this.getDataManager().refresh();
		model.setSelectedRow(selectRow);
		editor.setValue(model.getSelectedData());*/
	}
	@SuppressWarnings({ "unused", "null" })
	@Override
	protected boolean isActionEnable() {
		
		Object[] obj = model.getSelectedOperaDatas();
		NCObject[] ncObjects = null ;
		boolean isEnable = super.isActionEnable();
		if (isEnable && this.getModel().getSelectedOperaDatas() != null) {
			for(int i=0;i<obj.length;i++){
				AggCgfa aggvos = (AggCgfa) obj[i];
				String pkid=(String) aggvos.getParent().getAttributeValue("pk_equipment_id");
				try {
					ncObjects = MDPersistenceService.lookupPersistenceQueryService().queryBillOfNCObjectByCond
							(Cgfab.class,"pk_equipment_id='"+pkid+"' and dr=0",false);
				} catch (MetaDataException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				if(ncObjects==null){
					return false;
				}
			}
		}
		return true;
	}
	/* (non Javadoc) 
	 * @Title: doAction
	 * @Description: TODO
	 * @param e
	 * @throws Exception 
	 * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent) 
	 */
}
