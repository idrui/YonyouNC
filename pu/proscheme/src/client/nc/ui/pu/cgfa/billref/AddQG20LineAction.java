/**
 * 
 */
package nc.ui.pu.cgfa.billref;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.ui.ml.NCLangRes;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.actions.AbstractReferenceAction;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.pubapp.uif2app.view.util.BillRowNoUtils;
import nc.ui.so.m4331.billui.pub.calculator.DeliveryCardCalculator;
import nc.ui.uif2.model.BillManageModel;
import nc.vo.ml.AbstractNCLangRes;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pu.cgfa.AggCgfa;
import nc.vo.pu.cgfa.Cgfa;
import nc.vo.pu.cgfa.Cgfab;
import nc.vo.pub.IAttributeMeta;
import nc.vo.pub.IColumnMeta;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.meta.entity.bill.IBillMeta;
import nc.vo.scmpub.res.billtype.TOBillType;
import nc.vo.so.m4331.entity.DeliveryBVO;
import nc.vo.so.m4331.entity.DeliveryHVO;
import nc.vo.so.m4331.entity.DeliveryVO;
import nc.vo.trade.checkrule.VOChecker;

/**
 * @author wangzym
 * @version 2017��8��15�� ����10:23:05 �޸�ʱ�������ղ���
 */
public class AddQG20LineAction extends AbstractReferenceAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7221707859974656216L;

	private final String[] checkkey = new String[] { "PK_ORG" };

	private ShowUpableBillForm editor;

	private BillManageModel model;

	public AddQG20LineAction() {
		super();
	}

	@Override
	public void doAction(ActionEvent e) throws Exception {
		Object obj = this.editor.getValue();
		AggCgfa oldVO = (AggCgfa) obj;

		// ��������ƽ̨�ṩ�Ĺ���ת������
		PfUtilClient.childButtonClickedWithBusi(this.getSourceBillType(), this
				.getModel().getContext().getPk_group(), this.getModel()
				.getContext().getPk_loginUser(), "JT01", this.getModel()
				.getContext().getEntranceUI(), null, null, this.getBusitypes());
		AggCgfa[] newvos = null;
		if (PfUtilClient.isCloseOK()) {
			newvos = (AggCgfa[]) PfUtilClient.getRetVos();
			if (VOChecker.isEmpty(newvos)) {
				return;
			}
			this.checkAddLineData(oldVO, newvos);
			// this.setDefaultDate(newvos);
			this.addLine(newvos);
			// this.afterLoadData();
		}
	}

	public ShowUpableBillForm getEditor() {
		return this.editor;
	}

	public BillManageModel getModel() {
		return this.model;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public void setEditor(ShowUpableBillForm view) {
		this.editor = view;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
		this.model.addAppEventListener(this);
	}


	private void addLine(AggCgfa[] newvos) {
		// ���������ɵ�vo���д���
		Cgfab[] bvos = null;
		for (int i = 0; i < newvos.length; i++) {
			bvos = (Cgfab[]) newvos[i].getChildrenVO();
			// ��õ�ǰ����ı�������
			int row = this.getEditor().getBillCardPanel().getRowCount();
			for (int n = 0; n < bvos.length; n++) {
				// �Զ�����
				this.getEditor().getBillCardPanel().addLine();
				// ������vo
				this.getEditor().getBillCardPanel().getBillModel()
						.setBodyRowVO(bvos[n], n + row);
				BillRowNoUtils.addLineRowNo(
						this.getEditor().getBillCardPanel(), "seq");
			}
			this.getEditor().getBillCardPanel().getBillModel()
					.loadLoadRelationItemValue();
		}
	}

	private void checkAddLineData(AggCgfa oldvo, AggCgfa[] newvos) {
		Cgfa oldhvo = oldvo.getParentVO();
		for (AggCgfa newvo : newvos) {

			Cgfa newhvo = newvo.getParentVO();
			IBillMeta billmeta = oldvo.getMetaData();
			IVOMeta vometa = billmeta.getParent();
			List<String> errornames = new ArrayList<String>();
			for (String key : this.checkkey) {
				if (null == oldhvo.getAttributeValue(key)
						&& null == newhvo.getAttributeValue(key)) {
					continue;
				} else if (null == oldhvo.getAttributeValue(key)) {
					IAttributeMeta attri = vometa.getAttribute(key);
					errornames.add(attri.getColumn().getLabel());
				} else {
					if (!oldhvo.getAttributeValue(key).equals(
							newhvo.getAttributeValue(key))) {
						IAttributeMeta attri = vometa.getAttribute(key);
						IColumnMeta column = attri.getColumn();
						errornames.add(column.getLabel());
					}
				}

			}
			if (errornames.size() > 0) {
				Set<String> vbillcodes = new HashSet<String>();
				Cgfab[] bvos = (Cgfab[]) newvo.getChildrenVO();
				for (Cgfab bvo : bvos) {
					vbillcodes.add((String) bvo.getAttributeValue("srcbillno"));
				}
				StringBuffer buffer = new StringBuffer();
				for (String errorkey : errornames) {
					buffer.append(NCLangRes.getInstance().getStrByID(
							"4006002_0", "04006002-0027", null,
							new String[] { errorkey })/* {0}�� */);
				}

				StringBuffer billcodebuffer = new StringBuffer();
				for (String billcode : vbillcodes) {
					billcodebuffer.append(billcode + ",");
				}

				String billcodes = NCLangRes.getInstance().getStrByID(
						"4006002_0",
						"04006002-0028",
						null,
						new String[] { billcodebuffer.substring(0,
								billcodebuffer.length() - 1) })/* ��{0}�� */;
				String keyNames = NCLangRes.getInstance()
						.getStrByID(
								"4006002_0",
								"04006002-0028",
								null,
								new String[] { buffer.substring(0,
										buffer.length() - 1) })/* ��{0}�� */;
				ExceptionUtils.wrappBusinessException(NCLangRes.getInstance()
						.getStrByID("4006008_0", "04006008-0084", null,
								new String[] { billcodes, keyNames })/*
																	 * ���ݺţ�{0}�����ֶ�
																	 * ��
																	 * {1}��ͬ�������������
																	 */);
			} else {
				Cgfab[] oldbvos = (Cgfab[]) oldvo.getChildrenVO();
				Cgfab[] newbvos = (Cgfab[]) newvo.getChildrenVO();
				for (Cgfab bvo : newbvos) {
					String srcbid = (String) bvo.getAttributeValue("csrcid");
					for (Cgfab obvo : oldbvos) {
						if (obvo.getAttributeValue("csrcid").equals(srcbid)) {
							AbstractNCLangRes nclangres = NCLangRes4VoTransl
									.getNCLangRes();
							ExceptionUtils
									.wrappBusinessException(nclangres
											.getStrByID("4006008_0",
													"04006008-0009")/*
																	 * @res
																	 * "ѡ��������Ѿ��ڿ�Ƭ�ϣ���������ʧ��"
																	 */);
						}
					}
				}
			}
		}
	}

	/*
	 * �������к����ݴ���
	 */
	private void afterLoadData() {
		if (this.getSourceBillType().equals(TOBillType.TransOrder.getCode())) {
			// ���Ե��������ķ��������֯������༭
			this.getEditor().getBillCardPanel()
					.getBodyItem(DeliveryBVO.CSENDSTOCKORGVID).setEdit(false);
			// ���Ե��������Ŀͻ�����Ϊ��
			this.getEditor().getBillCardPanel()
					.getBodyItem(DeliveryBVO.CORDERCUSTID).setNull(false);
		} else {
			// �������۶����ķ��������֯����༭
			this.getEditor().getBillCardPanel()
					.getBodyItem(DeliveryBVO.CSENDSTOCKORGVID).setEdit(true);
			// �������۶����Ŀͻ�������Ϊ��
			this.getEditor().getBillCardPanel()
					.getBodyItem(DeliveryBVO.CORDERCUSTID).setNull(true);
		}
		this.calculateNum();
	}

	// ת�������¼������������
	private void calculateNum() {
		DeliveryCardCalculator calculator = new DeliveryCardCalculator(this
				.getEditor().getBillCardPanel());
		int count = this.getEditor().getBillCardPanel().getBillModel()
				.getRowCount();
		int[] rows = new int[count];
		for (int i = 0; i < rows.length; i++) {
			rows[i] = i;
		}
		calculator.calculate(rows, DeliveryBVO.NNUM);
	}

	private void setDefaultDate(DeliveryVO[] newvos) {
		for (DeliveryVO vo : newvos) {
			DeliveryHVO hvo = vo.getParentVO();
			UFDate date = AppContext.getInstance().getBusiDate();
			hvo.setDbilldate(date);
			DeliveryBVO[] bvos = vo.getChildrenVO();
			for (DeliveryBVO bvo : bvos) {
				bvo.setDbilldate(date);
				UFDate sendDate = bvo.getDsenddate();
				UFDate receiveDate = bvo.getDreceivedate();
				if (null == sendDate || sendDate.before(date)) {
					bvo.setDsenddate(date.asLocalEnd());
				}
				if (null == receiveDate || receiveDate.before(date)) {
					bvo.setDreceivedate(date.asLocalEnd());
				}
			}
		}
	}
}
