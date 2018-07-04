package nc.bs.pu.m23.maintain.api;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.ISuperVO;

public class ArriveApproveAPIBP {

	public ArriveApproveAPIBP() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 到货单API导入，如果导入了审批态的到货单需要在保存以后恢复到货单审批人审批日期和单据状态信息
	 * @param vos
	 * @param orivos
	 * @return
	 */
	public ArriveVO[] importApproveBill(ArriveVO[] vos,ArriveVO[] orivos){
		String[] fields = new String[]{
				ArriveHeaderVO.APPROVER,ArriveHeaderVO.TAUDITTIME,
				ArriveHeaderVO.FBILLSTATUS
		};
		Map<String, ArriveVO> map = new HashMap<String, ArriveVO>();
    for (ArriveVO orivo : orivos) {
    	ISuperVO head = orivo.getParent();
      ISuperVO item = (ISuperVO) orivo.getChildrenVO()[0];
      String srcpk_org =
          (String) head.getAttributeValue(ArriveHeaderVO.PK_PURCHASEORG_V);
      String vsrcbillcode =
          (String) item.getAttributeValue(ArriveItemVO.VSOURCECODE);
      String crowno =
          (String) item.getAttributeValue(ArriveItemVO.VSOURCEROWNO);
      String key = srcpk_org + vsrcbillcode + crowno;
      if (!StringUtil.isEmptyWithTrim(key)){
      	map.put(key, orivo);
      }  
    }
    for (ArriveVO vo : vos) {
      ISuperVO head = vo.getParent();
      ISuperVO item = (ISuperVO) vo.getChildrenVO()[0];
      String srcorg =
          (String) head.getAttributeValue(ArriveHeaderVO.PK_PURCHASEORG_V);
      if(ArrayUtils.isEmpty(fields)){
      	return vos;
      }
      String srcbillcode = (String) item
          .getAttributeValue(ArriveItemVO.VSOURCECODE);
      String srcrowno =
          (String) item.getAttributeValue(ArriveItemVO.VSOURCEROWNO);
      String key = srcorg + srcbillcode + srcrowno;
      ArriveVO orivo = map.get(key);
      ArriveHeaderVO orihead = (ArriveHeaderVO) orivo.getParent();
      for (String field : fields) {
        if (orihead.getAttributeValue(field) != null) {
          head.setAttributeValue(field,
          		orihead.getAttributeValue(field));
        }
      }   
    }
		return vos;	
	}
}
