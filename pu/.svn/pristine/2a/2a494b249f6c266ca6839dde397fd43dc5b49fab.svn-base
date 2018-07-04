package nc.ui.pu.m20.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.ui.pu.m20.editor.util.PraybillScaleUtil;
import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.bill.BillListPanel;
import nc.vo.pu.m20.entity.PraybillHeaderVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>请购单修订历史对话框
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author GGR
 * @time 2010-6-30 下午04:20:18
 */
public class PrayHistoryDlg extends UIDialog {

	private static final long serialVersionUID = 2663716658550456949L;

	private UIButton back;

	private UIPanel buttonpanel;

	// --------------------------------------前台控件
	private BillListPanel listpanel;

	private JPanel pane;

	// --------------------------------------后台变量
	private String pk_org;

	private PraybillVO[] vos;

	/**
	 * PrayHistoryDlg.java:constructor
	 */
	public PrayHistoryDlg(Container parent, String pk_org, String title) {
		super(parent, title,true);
		this.setResizable(true);
		this.pk_org = pk_org;
		this.init();
	}

	/**
	 * @return vos
	 */
	public PraybillVO[] getVos() {
		return this.vos;
	}

	public void setVOs(PraybillVO[] pbvos) {
		this.vos = pbvos;

		int len = this.vos.length;
		PraybillHeaderVO[] hvos = new PraybillHeaderVO[len];
		for (int i = 0; i < len; i++) {
			hvos[i] = this.vos[i].getHVO();
		}

		this.getBillListPanel().getHeadBillModel().setBodyDataVO(hvos);
		this.getBillListPanel().getHeadBillModel().execLoadFormula();
		this.getBillListPanel().getHeadBillModel().loadLoadRelationItemValue();
		this.getBillListPanel().getHeadBillModel().updateValue();
		this.getBillListPanel().getBodyBillModel().clearBodyData();
		this.getBillListPanel().getBodyBillModel().updateValue();
		this.getBillListPanel().updateUI();
	}

	private UIPanel getButtonPanel() {
		if (this.buttonpanel == null) {
			this.buttonpanel = new UIPanel();
			this.buttonpanel.add(this.getUIButtonBack());
		}

		return this.buttonpanel;
	}

	private JPanel getPane() {
		if (this.pane == null) {
			this.pane = new JPanel();

			this.pane.add(this.getBillListPanel(), BorderLayout.NORTH);

			this.pane.add(this.getButtonPanel(), BorderLayout.SOUTH);
		}
		return this.pane;
	}

	private UIButton getUIButtonBack() {
		if (this.back == null) {
			this.back = new UIButton();
			this.back.setText(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
					.getStrByID("common", "UC001-0000038")/* @res "返回" */);
			this.back.setPreferredSize(new java.awt.Dimension(50, 22));
			this.back.setVisible(true);
			this.back.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					PrayHistoryDlg.this.closeOK();
				}
			});
		}
		return this.back;
	}

	private void init() {
		this.setResizable(true);
		this.setContentPane(this.getPane());
		this.setAllSize(this.getParent().getSize());
	}

	private void initListPanel() {

		// 版本号:小数位显示一位
		this.listpanel.getHeadItem("nversion").setDecimalDigits(1);

		// 合计行显示
		this.listpanel.getChildListPanel().setTotalRowShow(true);

		this.listpanel.getHeadTable().setCellSelectionEnabled(false);

		this.listpanel.getHeadTable().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);

		// 请购单修订精度问题
		String pk_group = AppContext.getInstance().getPkGroup();
		new PraybillScaleUtil().setListScale(this.listpanel, pk_group);

		this.listpanel.getHeadTable().getSelectionModel()
				.addListSelectionListener(new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						int selectRow = PrayHistoryDlg.this.getBillListPanel()
								.getHeadTable().getSelectedRow();
						if (selectRow != -1) {
							PrayHistoryDlg.this
									.getBillListPanel()
									.getBodyBillModel()
									.setBodyDataVO(
											PrayHistoryDlg.this.getVos()[selectRow]
													.getBVO());
						}
						PrayHistoryDlg.this.getBillListPanel()
								.getBodyBillModel().execLoadFormula();
						PrayHistoryDlg.this.getBillListPanel()
								.getBodyBillModel().loadLoadRelationItemValue();
						PrayHistoryDlg.this.getBillListPanel()
								.getBodyBillModel().updateValue();
						PrayHistoryDlg.this.getBillListPanel().updateUI();
					}
				});

	}

	BillListPanel getBillListPanel() {
		if (this.listpanel == null) {
			try {
				this.listpanel = new BillListPanel();
				try {
					this.listpanel.loadTemplet("2Q", null, WorkbenchEnvironment
							.getInstance().getLoginUser().getPrimaryKey(),
							this.pk_org);
				} catch (Exception e) {

					this.listpanel.loadTemplet("1004Z81000000000601U");
				}

				this.initListPanel();

			} catch (Exception e) {
				ExceptionUtils
						.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
								.getNCLangRes().getStrByID("4004020_0",
										"04004020-0072")/*
														 * @res "模板不存在！"
														 */);
				return null;
			}
		}

		return this.listpanel;
	}

	void setAllSize(Dimension dmn) {
		this.setSize(dmn);

		this.getBillListPanel().setPreferredSize(
				new Dimension(dmn.width, dmn.height - 80));
		this.getButtonPanel().setPreferredSize(new Dimension(dmn.width, 70));
	}

}
