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
 *		ģ�⵽�������ղɹ������Ƶ������ݲɹ�������䵽�����ֶ���Ϣ������ֶη�ΧΪ�ɹ���������������VO����
 * @scene
 * 
 * @param
 * 
 * @functionName 
 *
 * @since 6.5
 * @version 2015-11-6 ����11:37:03
 * @author wandl
 */
public class FillValuesByOrder{

	public ArriveVO[] process(ArriveVO[] vos) throws BusinessException {
		// TODO Auto-generated method stub
		//��֯��������д��������
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
		 * para ��Ҫ���뵱ǰ��������֯�͵��ݺ��ֶ����ơ�����֯Ĭ���ֶ����ƣ�pk_org , ���ݺ�Ĭ���ֶ����ƣ�vbillcode,
		 * �����ҵ�񵥾��ֶ�������Ĭ�ϵĲ����ϣ���Ҫ�ֹ�setֵ
		 * ���磺 para.setPk_orgfieldname("����֯�ֶ�����");
		 */
		OrderViewVO[] views = null;
		views = viewquery.queryViewVOByWriteBackPara(wbpara);
		
		ViewToBillConvertor convertor = 
				new ViewToBillConvertor(OrderVO.class);
		OrderVO[] srcvos = (OrderVO[]) convertor.getBillsByViews(views);
		/*
		 * ���ÿɵ���������ֵ���ɵ��������ǲɹ������ļ������ԣ� �ɵ������� = Ϊ��������-�ۼƵ�������
		 */
		for (OrderVO vo : srcvos) {
      for (OrderItemVO item : vo.getBVO()) {
        // �ɵ��� ��������-�ۼƵ���
        UFDouble totalnum = item.getNnum();
        UFDouble accnum = item.getNaccumarrvnum();
        UFDouble num = MathTool.sub(totalnum, accnum);
        item.setNcanarrivenum(num);
      }
    }
		ArriveVO[] dest23Vos = (ArriveVO[]) wbProcess.getDestVOs(srcvos, wbpara);
		BillToViewConvertor<ArriveVO, ArriveViewVO> arriveconvertor = 
				new BillToViewConvertor<ArriveVO, ArriveViewVO>(ArriveViewVO.class);
		//���������ĵ���VOת��Ϊ��ͼVO����������ö�������������ֵ
		ArriveViewVO[] arrviews = arriveconvertor.convert(vos);
		wbProcess.processDestVOs(dest23Vos,arrviews,wbpara);
		return dest23Vos;
	}
}
