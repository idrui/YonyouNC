/**
 * 
 */
package nc.ui.pu.m20.model;

import nc.ui.fa.newasset.view.newasset_config;
import nc.ui.pu.m20.action.arrange.PrayarrangeSaveRefreshAction;
import nc.ui.pubapp.uif2app.model.BatchBillTableModel;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pu.m20.entity.PrayarrangeViewVO;
import nc.vo.pubapp.AppContext;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 *
 * @version
 * @since
 * @author luojw
 * @time 2014-7-10 下午3:50:58
 */
public class PrayarrangeBatchBillTableModel extends BatchBillTableModel {
	PrayarrangeSaveRefreshAction agg = new PrayarrangeSaveRefreshAction();
	/* 
	 * 父类方法重写
	 *
	 * @see nc.ui.uif2.model.BatchBillTableModel#save()
	 */
	@Override
	public void save() throws Exception {

		BatchOperateVO vo = super.getCurrentSaveObject();
		//获取当前页面显示的采购员ID
		PrayarrangeViewVO emp =(PrayarrangeViewVO) this.getSelectedData();
		String employee = emp.getItem().getPk_employee();
		//如果采购员为空则直接返回
		if(employee != null){
//			vo.setUpdObjs(this.getSelectedOperaDatas());
			Object[] updObjs=vo.getUpdObjs();//获取当前页面所选取的数据
			PrayarrangeViewVO[] prayvos = new PrayarrangeViewVO[updObjs.length];
			for(int i=0;i<updObjs.length;i++){
				prayvos[i]=(PrayarrangeViewVO) updObjs[i];
				prayvos[i].setAttributeValue("sts_req", "02");//修改计划状态为已安排
				prayvos[i].setAttributeValue("tmstp_dispatch", AppContext.getInstance().getServerTime());//设置计划分配时间
			}
			//如果没有修改任何行，则直接返回
			if (updObjs.length > 0) {
				vo = super.getService().batchSave(vo);
				super.directSave(vo);
			} 
		}
	}
}
