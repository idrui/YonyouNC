package nc.ui.pu.m24.view;

import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import nc.ui.pu.pub.view.PUBillForm;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.card.CardBodyRowChangedEvent;
import nc.ui.scmpub.util.BillCardPanelUtils;
import nc.ui.uif2.AppEvent;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m24.entity.PricestlItemBVO;
import nc.vo.pu.m24.entity.PricestlItemVO;
import nc.vo.pu.m24.entity.PricestlVO;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�۸���㵥��Ƭ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-7-22 ����02:35:41
 */
public class PricestlFormEditor extends PUBillForm {

	private static final long serialVersionUID = 3119515225644633125L;

	@Override
	public void handleEvent(AppEvent event) {
		super.handleEvent(event);

		this.getModel().getSelectedData();
		if (event instanceof CardBodyRowChangedEvent) {
			// �иı�ʱ���������ż���Ϣ
			this.setHqhpInfo((CardBodyRowChangedEvent) event);
		}
	}

	@Override
	public void initUI() {
		super.initUI();

		// �����βҳǩ�л�����
		this.getBillCardPanel().getTailTabbedPane()
				.addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent e) {
						int currentTailIndex = PricestlFormEditor.this.getBillCardPanel()
								.getTailTabbedPane().getSelectedIndex();
						int bodyIndex = 0;
						// ��β�л���"�����ż������Ϣ"ҳǩʱ�������л�����Ӧ��"�����ż������Ϣ"ҳǩ
						if (currentTailIndex == 2) {
							bodyIndex = 1;
						}
						// ��β"�����Ϣ"ҳǩ����������ҳǩ�ı�
						if (currentTailIndex == 1) {
							return;
						}
						// �л�������Ϣ
						PricestlFormEditor.this.getBillCardPanel().getBodyTabbedPane()
								.setSelectedIndex(bodyIndex);
					}
				});

		// �������ҳǩ�л�����
		this.getBillCardPanel().getBodyTabbedPane()
				.addChangeListener(new ChangeListener() {
					@Override
					public void stateChanged(ChangeEvent e) {
						int currentBodyIndex = PricestlFormEditor.this.getBillCardPanel()
								.getBodyTabbedPane().getSelectedIndex();
						int tailIndex = currentBodyIndex == 1 ? currentBodyIndex + 1
								: currentBodyIndex;
						// �л���β��Ϣ
						PricestlFormEditor.this.getBillCardPanel().getTailTabbedPane()
								.setSelectedIndex(tailIndex);
					}
				});
	}

	/**
	 * ��������������ȡ�ñ����ж�Ӧ�������ż���Ϣ��
	 * <p>
	 * <b>����˵��</b>
	 * 
	 * @param bitems
	 * @param pk_base
	 * @return <p>
	 * @since 6.0
	 * @author gaogr
	 * @time 2010-8-12 ����12:46:58
	 */
	private PricestlItemBVO[] getBitem(PricestlItemBVO[] bitems, String pk_base) {
		if (StringUtil.isEmptyWithTrim(pk_base)) {
			return null;
		}

		ArrayList<PricestlItemBVO> ret = new ArrayList<PricestlItemBVO>();
		for (PricestlItemBVO item : bitems) {
			if (pk_base.equals(item.getCqpschemeid())) {
				ret.add(item);
			}
		}

		return ret.toArray(new PricestlItemBVO[ret.size()]);
	}

	private PricestlItemVO[] getItem(PricestlItemVO[] items, String pk_base) {
		if (StringUtil.isEmptyWithTrim(pk_base)) {
			return null;
		}

		ArrayList<PricestlItemVO> ret = new ArrayList<PricestlItemVO>();
		for (PricestlItemVO item : items) {
			if (pk_base.equals(item.getCqpschemeid())) {
				ret.add(item);
			}
		}

		return ret.toArray(new PricestlItemVO[ret.size()]);
	}

	/**
	 * ���������������иı�ʱ���������ż���Ϣ��
	 * <p>
	 * <b>����˵��</b>
	 * 
	 * @param event
	 *          <p>
	 * @since 6.0
	 * @author gaogr
	 * @time 2010-8-12 ����12:46:41
	 */
	private void setHqhpInfo(CardBodyRowChangedEvent event) {
		int i = this.getBillCardPanel().getBodyTabbedPane().getSelectedIndex();
		if (i == 1) {
			int row = event.getRow();
			PricestlVO aggvo = (PricestlVO) this.getModel().getSelectedData();
			PricestlItemBVO[] bitems = aggvo.getBBVO();
			PricestlItemVO[] items = aggvo.getBVO();
			BillModel bm = this.getBillCardPanel().getBillData()
					.getBillModel("body_hqhp");
			PricestlItemBVO[] setbitems = null;
			if (row >= 0 && null != this.getModel().getSelectedData()) {
				// PricestlItemBVO[] bitems =
				// ((PricestlVO) this.getModel().getSelectedData()).getBBVO();
				String pk_base = (String) this.getBillCardPanel().getBodyValueAt(row,
						PricestlItemVO.CQPSCHEMEID);
				setbitems = this.getBitem(bitems, pk_base);
				items = this.getItem(items, pk_base);
				if (!ArrayUtils.isEmpty(items)) {

					Object baseprice = items[0].getDbaseprice();
					Object nschemecalvalue = items[0].getNschemecalvalue();
					Object vschemefrmlname = items[0].getVschemefrmlname();

					this.getBillCardPanel().getTailItem(PricestlItemVO.DBASEPRICE)
							.setValue(baseprice);
					this.getBillCardPanel().getTailItem(PricestlItemVO.NSCHEMECALVALUE)
							.setValue(nschemecalvalue);
					this.getBillCardPanel().getTailItem(PricestlItemVO.VSCHEMEFRMLNAME)
							.setValue(vschemefrmlname);
				}

			}
			bm.setBodyDataVO(setbitems);
			bm.loadLoadRelationItemValue();
			bm.execLoadFormula();
		}
	}

	@Override
	protected void enableFillableItems() {
		super.enableFillableItems();
		String[] enableItems = new String[] { PricestlItemVO.DBASEPRICE,
				PricestlItemVO.NBASEPRICE, PricestlItemVO.NBASETAXPRICE,
				PricestlItemVO.NCALQUALPRICE, PricestlItemVO.NPRICE,
				PricestlItemVO.NTAXPRICE };
		BillCardPanelUtils cardUtils = new BillCardPanelUtils(
				this.getBillCardPanel());
		// �ſ��ֶε�����
		cardUtils.enableItemsFill(enableItems);
	}

}
