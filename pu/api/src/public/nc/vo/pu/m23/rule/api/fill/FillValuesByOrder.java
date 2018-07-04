package nc.vo.pu.m23.rule.api.fill;

import nc.bs.scmpub.query.SCMViewQuery;
import nc.impl.pubapp.bill.convertor.BillToViewConvertor;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.entity.OrderViewVO;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.entity.ArriveViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.scmpub.util.ViewToBillConvertor;
import nc.vo.scmpub.util.writeback.WriteBackPara;
import nc.vo.scmpub.util.writeback.WriteBackSourcePara;
import nc.vo.scmpub.util.writeback.WriteBackSourceProcess;

/**
 * 
 * @description
 *		模拟到货单参照采购订单制单，根据采购订单填充到货单字段信息，填充字段范围为采购订单到到货单的VO对照
 * @scene
 * 
 * @param
 * 
 * @functionName 
 *
 * @since 6.5
 * @version 2015-11-6 上午11:37:03
 * @author wandl
 */
public class FillValuesByOrder{

	public ArriveVO[] process(ArriveVO[] vos) throws BusinessException {
		// TODO Auto-generated method stub
		//组织到货单回写订单参数
		WriteBackSourcePara para = new WriteBackSourcePara();
		para.setSrcMainOrgfieldname(ArriveHeaderVO.PK_PURCHASEORG);
		para.setVsrccodefieldname(ArriveItemVO.VSOURCECODE);
		para.setVsrcrownofieldname(ArriveItemVO.VSOURCEROWNO);
		para.setHtablename("po_order");
		para.setBtablename("po_order_b");
		para.setSrcbillcodeFieldname("csourcetypecode");
		para.setDestbilltypecode("23");
		ArriveVO[] destvo = this.fillValues(para,vos);
		return destvo;
	}
	
	private void setNeedCoverField(WriteBackPara wbpara){
		String[] headneedcoverfields = new String[]{
				"pk_pupsndoc","fbillstatus","pk_dept","pk_dept_v",
				"pk_org"
		};
		String[] itemneedcoverfields = new String[]{
				"nnum","pk_org","nastnum","bpresent","bcloseorder"
		};
		wbpara.setHeadNeedCoverFields(headneedcoverfields);
		wbpara.setItemNeedCoverFields(itemneedcoverfields);
	}
	
	private ArriveVO[] fillValues(WriteBackSourcePara para,ArriveVO[] vos) throws BusinessException{	
  	WriteBackSourceProcess wbProcess = new WriteBackSourceProcess(para);
  	WriteBackPara wbpara = wbProcess.constractPara(vos);
  	this.setNeedCoverField(wbpara);
		SCMViewQuery<OrderViewVO> viewquery = new 
				SCMViewQuery<OrderViewVO>(OrderViewVO.class);
		/*
		 * para 需要传入当前单据主组织和单据号字段名称。主组织默认字段名称：pk_org , 单据号默认字段名称：vbillcode,
		 * 如果有业务单据字段名称与默认的不符合，需要手工set值
		 * 例如： para.setPk_orgfieldname("主组织字段名称");
		 */
		OrderViewVO[] views = null;
		views = viewquery.queryViewVOByWriteBackPara(wbpara);
		
		ViewToBillConvertor convertor = 
				new ViewToBillConvertor(OrderVO.class);
		OrderVO[] srcvos = (OrderVO[]) convertor.getBillsByViews(views);
		/*
		 * 设置可到货数量的值，可到货数量是采购订单的计算属性： 可到货数量 = 为订单数量-累计到货数量
		 */
		for (OrderVO vo : srcvos) {
      for (OrderItemVO item : vo.getBVO()) {
        // 可到货 订单数量-累计到货
        UFDouble totalnum = item.getNnum();
        UFDouble accnum = item.getNaccumarrvnum();
        UFDouble num = MathTool.sub(totalnum, accnum);
        item.setNcanarrivenum(num);
      }
    }
		ArriveVO[] dest23Vos = (ArriveVO[]) wbProcess.getDestVOs(srcvos, wbpara);
		BillToViewConvertor<ArriveVO, ArriveViewVO> arriveconvertor = 
				new BillToViewConvertor<ArriveVO, ArriveViewVO>(ArriveViewVO.class);
		//将到货单的单据VO转换为视图VO，方便后面用订单给到货单赋值
		ArriveViewVO[] arrviews = arriveconvertor.convert(vos);
		wbProcess.processDestVOs(dest23Vos,arrviews,wbpara);
		return dest23Vos;
	}
}
