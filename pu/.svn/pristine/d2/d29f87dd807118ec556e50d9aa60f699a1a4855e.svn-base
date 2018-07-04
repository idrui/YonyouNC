package nc.ui.pu.m23.check.action;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.pu.m23.qc.IArriveForQC;
import nc.itf.scmpub.reference.uap.group.SysInitGroupQuery;
import nc.pubitf.pu.m23.pubquery.IArrivePubQuery;
import nc.pubitf.qc.c001.pu.ReturnObjectFor23;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.query2.model.ModelDataManager;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.entity.ArriveViewVO;
import nc.vo.pu.m23.utils.ArrivePublicUtil;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pu.pub.util.AggVOUtil;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.data.ValueUtils;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.view.AbstractDataView;
import nc.vo.pubapp.pattern.model.tool.BillComposite;
import nc.vo.pubapp.pattern.model.transfer.bill.ClientBillCombinServer;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.pubapp.query2.sql.process.QueryCondition;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;
import nc.vo.qc.pub.enumeration.StrictLevelEnum;
import nc.vo.qc.pub.util.QCSysParamUtil;
import nc.vo.scmpub.res.SCMActionCode;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单 检验 按钮处理类Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-12 下午02:15:12
 */
public class QCUIAction extends NCAction {

	public static final String ISCHECK = "isCheck";

	private static final long serialVersionUID = 7105286499110737794L;

	private ModelDataManager dataManager = null;

	private BillManageModel model;

	public QCUIAction() {
		super();
		SCMActionInitializer.initializeAction(this, SCMActionCode.SCM_VERIFY);
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		// 获得所选择的视图VO，并转换为聚合VO
		Object[] objs = this.model.getSelectedOperaDatas();
		if (ArrayUtils.isEmpty(objs)) {
			ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
					.getNCLangRes().getStrByID("4004040_0", "04004040-0051")/*
																																	 * @res
																																	 * "没有选择到货单"
																																	 */);
		}
		ArriveViewVO viewVo = (ArriveViewVO) this.model.getSelectedData();
		String pk_org = viewVo.getHVO().getPk_org();
		ArriveItemVO item = viewVo.getBVO();
		if (SysInitGroupQuery.isQCEnabled()
				&& UFBoolean.TRUE.equals(ValueUtils.getUFBoolean(QCSysParamUtil
						.getINI01(pk_org)))) {
			// 非紧急放行入库数量>0,即根据质检入库
			if (MathTool.compareTo(
					MathTool.sub(item.getNaccumstorenum(), item.getNaccumletgoinnum()),
					UFDouble.ZERO_DBL) > 0) {
				return;
			}
		}
		ArriveViewVO[] views = new ArriveViewVO[objs.length];
		System.arraycopy(objs, 0, views, 0, objs.length);
		// ArriveVO[] vos = ArrivePublicUtil.convertViewToAggVO(views);
		ArriveVO[] vos = this.getArriveVOs(views);
		for (ArriveVO vo : vos) {
			ArriveItemVO[] bvos = vo.getBVO();
			for (ArriveItemVO bvo : bvos) {
				UFDouble nchecknum = bvo.getNchecknum();
				UFDouble nwillelignum = bvo.getNwillelignum();
				if (nchecknum != null && nwillelignum != null) {
					if (nchecknum.compareTo(UFDouble.ZERO_DBL) < 0
							|| nwillelignum.compareTo(UFDouble.ZERO_DBL) < 0) {
						ExceptionUtils
								.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
										.getNCLangRes().getStrByID("4004040_0", "04004040-0213")/* 报检数量和合格主数量不允许为负数 */);
					}
				}
			}
		}
		// 2012-06-29 tianft ：此处不需要判断质检模块是否启用，不启用质检的情况下是可以输入合格数量信息的
		// // 先判断质检模块是否启用,再判断对应库存组织是否质检启用
		// if (!SysInitGroupQuery.isQCEnabled()
		// || UFBoolean.FALSE.equals(ValueUtils.getUFBoolean(QCSysParamUtil
		// .getINI01(vos[0].getHVO().getPk_org())))) {
		//
		// ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
		// .getNCLangRes().getStrByID("4004040_0", "04004040-0030")/*
		// * @res
		// * "库存组织 {0} 没有启用质检模块，无法质检。"
		// */);
		// }

		if (UIDialog.ID_YES == this.showConfirmCheckDialog(this.model.getContext()
				.getEntranceUI())) {
			ArriveVO[] returnVos = this.check(vos, this.isCheck());
			if (null == returnVos) {
				this.showFailedInfo();
			} else {
				this.showSuccessInfo();
			}
			// 把后台返回的轻量级聚合VO转换为视图VO
			new ClientBillCombinServer<ArriveVO>().combine(vos, returnVos);
			ArriveViewVO[] newViews = ArrivePublicUtil.convertAggToViewVO(vos);
			this.model.directlyUpdate(newViews);
			this.getDataManager().refresh();
		}
	}

	public ModelDataManager getDataManager() {
		return this.dataManager;
	}

	public void setDataManager(ModelDataManager dataManager) {
		this.dataManager = dataManager;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
		model.addAppEventListener(this);
	}

	/**
	 * 调用后台接口进行检验
	 * 
	 * @param vos
	 *          到货单vo
	 * @param isCheck
	 * @return 检验结果数组
	 * @throws Exception
	 */
	private ArriveVO[] check(ArriveVO[] vos, boolean isCheck) throws Exception {
		Object[] objs = NCLocator.getInstance().lookup(IArriveForQC.class)
				.qualityCheck(vos, isCheck);
		ArriveVO[] result = (ArriveVO[]) objs[0];
		ReturnObjectFor23 rof = (ReturnObjectFor23) objs[1];
		// 得到质检模块的提示信息
		if (rof != null) {
			Map<String, Integer> strictMap = rof.getCsourcebid_strictlevel();
			Set<String> keySet = strictMap.keySet();
			IArrivePubQuery arriveQuery = NCLocator.getInstance().lookup(
					IArrivePubQuery.class);
			ArriveItemVO[] items = arriveQuery.queryItemVOByBids(keySet
					.toArray(new String[keySet.size()]));

			String[] bpks = AggVOUtil.getPrimaryKeys(items);
			if (strictMap.size() > 0) {
				for (Map.Entry<String, Integer> entry : strictMap.entrySet()) {
					String itempk = entry.getKey();
					int dji = entry.getValue().intValue();
					if (StrictLevelEnum.FREE.getReturnType() == dji) {
						int i = Arrays.binarySearch(bpks, itempk);
						items[i].getCrowno();
						ExceptionUtils.wrappBusinessException(NCLangRes.getInstance()
								.getStrByID("4004040_0", "04004040-0169", null,
										new String[] { items[i].getCrowno() })/*
																													 * 第{0}行为质检连续批的严格程度为免检，
																													 * 不需要生成报检单！
																													 */);
					} else if (StrictLevelEnum.PAUSE.getReturnType() == dji) {
						int i = Arrays.binarySearch(bpks, itempk);
						items[i].getCrowno();
						ExceptionUtils.wrappBusinessException(NCLangRes.getInstance()
								.getStrByID("4004040_0", "04004040-0170", null,
										new String[] { items[i].getCrowno() })/*
																													 * 第{0}行为质检连续批的严格程度为暂停，
																													 * 不能生成报检单！
																													 */);
					}
				}
			}
		}
		if (!ArrayUtils.isEmpty(result)) {
			return result;
		}
		return null;
	}

	private ArriveVO[] getArriveVOs(ArriveViewVO[] views) {
		List<ArriveHeaderVO> headers = new ArrayList<ArriveHeaderVO>();
		List<ArriveItemVO> items = new ArrayList<ArriveItemVO>();
		for (AbstractDataView view : views) {
			headers.add((ArriveHeaderVO) view.getVO(ArriveHeaderVO.class));
			items.add((ArriveItemVO) view.getVO(ArriveItemVO.class));
		}

		BillComposite<ArriveVO> bc = new BillComposite<ArriveVO>(ArriveVO.class);
		ArriveVO tempVO = new ArriveVO();
		bc.append(tempVO.getMetaData().getParent(),
				headers.toArray(new ArriveHeaderVO[headers.size()]));
		bc.append(tempVO.getMetaData().getVOMeta(ArriveItemVO.class),
				items.toArray(new ArriveItemVO[items.size()]));
		return bc.composite();
	}

	/**
	 * 查询条件中的检验状态，需要传到后台。
	 * 
	 * @return
	 */
	private boolean isCheck() {
		IQueryScheme queryScheme = this.getDataManager().getQueryScheme();
		QuerySchemeProcessor qrySchemeProcessor = new QuerySchemeProcessor(
				queryScheme);
		QueryCondition isCheckCond = qrySchemeProcessor
				.getLogicalCondition(QCUIAction.ISCHECK);
		if (isCheckCond == null) {
			return true;
		}
		Object[] values = isCheckCond.getValues();
		UFBoolean isCheck = UFBoolean.valueOf((String) values[0]);
		return isCheck.booleanValue();
	}

	/**
	 * 是否有vo可检验，在isActionEnable中被调用，一个vo可检验，则action可用。
	 * 
	 * @return
	 */
	private boolean isOneVOEnable(ArriveViewVO vo) {
		if (!POEnumBillStatus.APPROVE.value().equals(vo.getHVO().getFbillstatus())) {
			return false;
		}

		ArriveItemVO item = vo.getBVO();
		// 报检,并且未返回质检结果
		if (MathTool.nvl(item.getNaccumchecknum()).doubleValue() > 0
				&& MathTool.compareTo(
						MathTool.add(item.getNelignum(), item.getNnotelignum()),
						UFDouble.ZERO_DBL) == 0) {
			return false;
		}
		// 已经传设备卡片
		if (ValueUtils.getBoolean(item.getBfaflag())) {
			return false;
		}
		String pk_org = vo.getHVO().getPk_org();

		// 先判断质检模块是否启用,再判断对应库存组织是否质检启用
		if (SysInitGroupQuery.isQCEnabled()
				&& UFBoolean.TRUE.equals(ValueUtils.getUFBoolean(QCSysParamUtil
						.getINI01(pk_org)))) {
			// 非紧急放行入库数量>0,即根据质检入库
			if (MathTool.compareTo(
					MathTool.sub(item.getNaccumstorenum(), item.getNaccumletgoinnum()),
					UFDouble.ZERO_DBL) > 0) {
				return false;
			}
		}
		return true;
	}

	private int showConfirmCheckDialog(Container parent) {
		String TITLE = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
				"4004040_0", "04004040-0053")/* @res "确认报检" */;
		String QUESTION = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
				"4004040_0", "04004040-0054")/* @res "您确定要报检所选数据吗?" */;
		return MessageDialog.showYesNoDlg(parent, TITLE, QUESTION, UIDialog.ID_NO);
	}

	private void showFailedInfo() {
		ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl
				.getNCLangRes().getStrByID("4004040_0", "04004040-0055")/*
																																 * @res
																																 * "到货单检验失败！"
																																 */, this.model
				.getContext());
	}

	private void showSuccessInfo() {
		ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl
				.getNCLangRes().getStrByID("4004040_0", "04004040-0056")/*
																																 * @res
																																 * "到货单检验成功！"
																																 */, this.model
				.getContext());
	}

	@Override
	protected boolean isActionEnable() {
		if (this.model.getAppUiState() == AppUiState.EDIT
				|| this.model.getSelectedData() == null) {
			return false;
		}

		Object[] objs = this.model.getSelectedOperaDatas();

		if (this.model.getSelectedData() != null && ArrayUtils.isEmpty(objs)) {
			return this.isOneVOEnable((ArriveViewVO) this.model.getSelectedData());
		}
		return this.isOneVOEnable((ArriveViewVO) objs[0]);
	}
}
