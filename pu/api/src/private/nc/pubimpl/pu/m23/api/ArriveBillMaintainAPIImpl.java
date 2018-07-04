package nc.pubimpl.pu.m23.api;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;

import nc.bs.pu.m23.maintain.api.ArriveInsertAPIBP;
import nc.bs.pu.pub.VOQryUtil;
import nc.impl.pu.m23.maintain.action.ArriveDeleteAction;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.pubitf.pu.m23.api.IArriveBillMaintainAPI;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * @description
 *		到货单持久化服务实现
 * @scene
 * 
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-10-26 下午8:14:42
 * @author wandl
 */
public class ArriveBillMaintainAPIImpl implements IArriveBillMaintainAPI{

	@Override
	public ArriveVO[] insertBills(ArriveVO[] bills) throws BusinessException {   
		//ArriveVO[] orivos = bills.clone();
		/*
		 * 调用到货单保存BP
		 * 到货单保存校验
		 * 回写
		 */
		ArriveInsertAPIBP bp = new ArriveInsertAPIBP();
    ArriveVO[] saveret = bp.insertArrive(bills);
    //processer.after(ret);
    /*
     * 审批态的单据导入需要做特殊处理：
     * 1、更新审批状态和审批人、审核时间信息（由于审批态的单据在上一步导入的时候需要清空审批信息，所以在此重新填上）
     */
    /*ArriveApproveAPIBP approvebp = new ArriveApproveAPIBP();
    ArriveVO[] approveret = approvebp.importApproveBill(saveret, orivos);*/
		return saveret;
	}

	@Override
	public void deleteBillsByIDs(String[] ids) throws BusinessException {
		BillQuery<ArriveVO> query = new BillQuery<ArriveVO>(ArriveVO.class);
		if(ArrayUtils.isEmpty(ids)){
			return;
		}
		ArriveVO[] delvo = query.query(ids);
    try {
      ArriveDeleteAction action = new ArriveDeleteAction();
      action.deleteArrive(delvo);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
	}

	@Override
	public void deleteBillsBySourceIDs(String[] srcids) throws BusinessException {
		VOQryUtil<ArriveItemVO> util = new VOQryUtil<ArriveItemVO>(ArriveItemVO.class);
		String[] qryfields = new String[]{ArriveItemVO.PK_ARRIVEORDER};
		//根据到货单来源ID查询到货单主表主键
		ArriveItemVO[] items = util.qryBySpecField(ArriveItemVO.CSOURCEBID, srcids, qryfields);
		Set<String> pks = new HashSet<String>();
		for(ArriveItemVO item : items){
			pks.add(item.getPk_arriveorder());
		}
		this.deleteBillsByIDs(pks.toArray(new String[0]));
	}

	@Override
	public ArriveVO[] approve(ArriveVO[] bills) throws BusinessException {

		return null;
	}

	@Override
	public ArriveVO[] unApprove(ArriveVO[] bills) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
