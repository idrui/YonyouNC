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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>������ ���� ��ť������Action
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author hanbin
 * @time 2010-1-12 ����02:15:12
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
		// �����ѡ�����ͼVO����ת��Ϊ�ۺ�VO
		Object[] objs = this.model.getSelectedOperaDatas();
		if (ArrayUtils.isEmpty(objs)) {
			ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
					.getNCLangRes().getStrByID("4004040_0", "04004040-0051")/*
																																	 * @res
																																	 * "û��ѡ�񵽻���"
																																	 */);
		}
		ArriveViewVO viewVo = (ArriveViewVO) this.model.getSelectedData();
		String pk_org = viewVo.getHVO().getPk_org();
		ArriveItemVO item = viewVo.getBVO();
		if (SysInitGroupQuery.isQCEnabled()
				&& UFBoolean.TRUE.equals(ValueUtils.getUFBoolean(QCSysParamUtil
						.getINI01(pk_org)))) {
			// �ǽ��������������>0,�������ʼ����
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
										.getNCLangRes().getStrByID("4004040_0", "04004040-0213")/* ���������ͺϸ�������������Ϊ���� */);
					}
				}
			}
		}
		// 2012-06-29 tianft ���˴�����Ҫ�ж��ʼ�ģ���Ƿ����ã��������ʼ��������ǿ�������ϸ�������Ϣ��
		// // ���ж��ʼ�ģ���Ƿ�����,���ж϶�Ӧ�����֯�Ƿ��ʼ�����
		// if (!SysInitGroupQuery.isQCEnabled()
		// || UFBoolean.FALSE.equals(ValueUtils.getUFBoolean(QCSysParamUtil
		// .getINI01(vos[0].getHVO().getPk_org())))) {
		//
		// ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
		// .getNCLangRes().getStrByID("4004040_0", "04004040-0030")/*
		// * @res
		// * "�����֯ {0} û�������ʼ�ģ�飬�޷��ʼ졣"
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
			// �Ѻ�̨���ص��������ۺ�VOת��Ϊ��ͼVO
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
	 * ���ú�̨�ӿڽ��м���
	 * 
	 * @param vos
	 *          ������vo
	 * @param isCheck
	 * @return ����������
	 * @throws Exception
	 */
	private ArriveVO[] check(ArriveVO[] vos, boolean isCheck) throws Exception {
		Object[] objs = NCLocator.getInstance().lookup(IArriveForQC.class)
				.qualityCheck(vos, isCheck);
		ArriveVO[] result = (ArriveVO[]) objs[0];
		ReturnObjectFor23 rof = (ReturnObjectFor23) objs[1];
		// �õ��ʼ�ģ�����ʾ��Ϣ
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
																													 * ��{0}��Ϊ�ʼ����������ϸ�̶�Ϊ��죬
																													 * ����Ҫ���ɱ��쵥��
																													 */);
					} else if (StrictLevelEnum.PAUSE.getReturnType() == dji) {
						int i = Arrays.binarySearch(bpks, itempk);
						items[i].getCrowno();
						ExceptionUtils.wrappBusinessException(NCLangRes.getInstance()
								.getStrByID("4004040_0", "04004040-0170", null,
										new String[] { items[i].getCrowno() })/*
																													 * ��{0}��Ϊ�ʼ����������ϸ�̶�Ϊ��ͣ��
																													 * �������ɱ��쵥��
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
	 * ��ѯ�����еļ���״̬����Ҫ������̨��
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
	 * �Ƿ���vo�ɼ��飬��isActionEnable�б����ã�һ��vo�ɼ��飬��action���á�
	 * 
	 * @return
	 */
	private boolean isOneVOEnable(ArriveViewVO vo) {
		if (!POEnumBillStatus.APPROVE.value().equals(vo.getHVO().getFbillstatus())) {
			return false;
		}

		ArriveItemVO item = vo.getBVO();
		// ����,����δ�����ʼ���
		if (MathTool.nvl(item.getNaccumchecknum()).doubleValue() > 0
				&& MathTool.compareTo(
						MathTool.add(item.getNelignum(), item.getNnotelignum()),
						UFDouble.ZERO_DBL) == 0) {
			return false;
		}
		// �Ѿ����豸��Ƭ
		if (ValueUtils.getBoolean(item.getBfaflag())) {
			return false;
		}
		String pk_org = vo.getHVO().getPk_org();

		// ���ж��ʼ�ģ���Ƿ�����,���ж϶�Ӧ�����֯�Ƿ��ʼ�����
		if (SysInitGroupQuery.isQCEnabled()
				&& UFBoolean.TRUE.equals(ValueUtils.getUFBoolean(QCSysParamUtil
						.getINI01(pk_org)))) {
			// �ǽ��������������>0,�������ʼ����
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
				"4004040_0", "04004040-0053")/* @res "ȷ�ϱ���" */;
		String QUESTION = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
				"4004040_0", "04004040-0054")/* @res "��ȷ��Ҫ������ѡ������?" */;
		return MessageDialog.showYesNoDlg(parent, TITLE, QUESTION, UIDialog.ID_NO);
	}

	private void showFailedInfo() {
		ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl
				.getNCLangRes().getStrByID("4004040_0", "04004040-0055")/*
																																 * @res
																																 * "����������ʧ�ܣ�"
																																 */, this.model
				.getContext());
	}

	private void showSuccessInfo() {
		ShowStatusBarMsgUtil.showStatusBarMsg(nc.vo.ml.NCLangRes4VoTransl
				.getNCLangRes().getStrByID("4004040_0", "04004040-0056")/*
																																 * @res
																																 * "����������ɹ���"
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
