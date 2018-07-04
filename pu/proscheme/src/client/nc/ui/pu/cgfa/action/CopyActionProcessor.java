package nc.ui.pu.cgfa.action;

import nc.ui.pubapp.uif2app.actions.intf.ICopyActionProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pu.cgfa.AggCgfa;
import nc.vo.uif2.LoginContext;

public class CopyActionProcessor implements
		ICopyActionProcessor<AggCgfa> {

	@Override
	public void processVOAfterCopy(AggCgfa paramT,
			LoginContext paramLoginContext) {
		paramT.getParentVO().setPrimaryKey(null);
//		paramT.getParentVO().setModifier(null);
//    	paramT.getParentVO().setModifiedtime(null);
//    	paramT.getParentVO().setCreator(null);
//    	paramT.getParentVO().setCreationtime(null);		
//    	paramT.getParentVO().setBillno(null);
//    	paramT.getParentVO().setAuditor(null);
//    	paramT.getParentVO().setAudittime(null);
		//TODO ������Ҫҵ���Լ����䴦�����
		String[] codes =paramT.getTableCodes();
		if (codes != null && codes.length>0) {
			for (int i = 0; i < codes.length; i++) {
				String tableCode = codes[i];
				 CircularlyAccessibleValueObject[] childVOs = 	paramT.getTableVO(tableCode);
				 for (CircularlyAccessibleValueObject childVO : childVOs) {
					 try {
						childVO.setPrimaryKey(null);
					} catch (BusinessException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}
				}
			}
		}
	}
}
