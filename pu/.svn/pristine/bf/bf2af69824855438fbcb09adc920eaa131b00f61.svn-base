package nc.ui.pu.m4t.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m4t.IInitialEstMaintain;
import nc.ui.scmbd.excelimport.action.ScmImportAction;
import nc.ui.trade.excelimport.ExcelImportInfo;
import nc.ui.trade.excelimport.vo.DataRowVO;
import nc.ui.uif2.editor.IBillCardPanelEditor;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ExtendedAggregatedValueObject;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmbd.excelimport.TransFieldType;

public class InitialEstImportAction extends ScmImportAction {

	private static final long serialVersionUID = 1L;

	@Override
	public void doImport(ExcelImportInfo importInfo) {

		List<ExtendedAggregatedValueObject> vos = importInfo.getVos();
		List<InitialEstVO> list = new ArrayList<InitialEstVO>();
		for (ExtendedAggregatedValueObject vo : vos) {
			InitialEstVO estVO = new InitialEstVO();
			InitialEstHeaderVO headerVO = new InitialEstHeaderVO();
			List<InitialEstItemVO> itemLists = new ArrayList<InitialEstItemVO>();
			DataRowVO pvo = (DataRowVO) vo.getParentVO();
			String[] arrs = pvo.getAttributeNames();
			for (String arr : arrs) {
				headerVO.setAttributeValue(arr, pvo.getAttributeValue(arr));
			}
			if (headerVO.getPk_group() == null) {
				// 补集团
				headerVO.setPk_group(this.getModel().getContext().getPk_group());
			}
			String[] tableCodes = vo.getTableCodes();
			for (String code : tableCodes) {
				CircularlyAccessibleValueObject[] vo2 = vo.getTableVO(code);
				for (CircularlyAccessibleValueObject itemvo : vo2) {
					InitialEstItemVO estItemVO = new InitialEstItemVO();
					String[] names = itemvo.getAttributeNames();
					for (String name : names) {
						estItemVO.setAttributeValue(name, itemvo.getAttributeValue(name));
					}
					itemLists.add(estItemVO);
				}
			}
			estVO.setParent(headerVO);
			estVO.setChildren(InitialEstItemVO.class,
					itemLists.toArray(new InitialEstItemVO[0]));
			list.add(estVO);
		}
		// 补充信息 挪到后台，在前台的话，当数据量大的时候，前台会死
		// this.fillInfo(list);
		InitialEstVO[] estVOs = list.toArray(new InitialEstVO[0]);
		IInitialEstMaintain maintain = NCLocator.getInstance().lookup(
				IInitialEstMaintain.class);
		try {
			maintain.importSave(estVOs);
		} catch (BusinessException e) {
			ExceptionUtils.wrappException(e);
		}
	}

	@Override
	public IBillCardPanelEditor getIBillCardPanelEditor() {
		return ((InitialEstImportablePanel) this.getImportableEditor())
				.getBillcardPanelEditor();
	}

	@Override
	public int getTransFieldType() {
		return TransFieldType.TransByCodeAndName;
	}
	
	public HashMap<String, String> getHeadarrOrg() {
		HashMap<String, String> map = new HashMap<String,String>();
		map.put(InitialEstHeaderVO.PK_DEPT, InitialEstHeaderVO.PK_PURCHASEORG);
		map.put(InitialEstHeaderVO.PK_DEPT_V, InitialEstHeaderVO.PK_PURCHASEORG);
		return map;
	}
	
}