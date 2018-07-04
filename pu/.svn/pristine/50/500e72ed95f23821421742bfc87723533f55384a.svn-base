/**
 * $文件说明$
 *
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-4-11 下午02:29:00
 */
package nc.ui.pu.m25.action;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import nc.bs.framework.common.NCLocator;
import nc.bs.pf.pub.PfDataCache;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.pu.m25.IInvoiceQuery;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UITextField;
import nc.ui.pub.linkoperate.ILinkQueryData;
import nc.ui.scmpub.action.SCMActionInitializer;
import nc.ui.uap.sf.SFClientUtil;
import nc.ui.uif2.UIState;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m25.entity.InvoiceVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.res.SCMActionCode;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.ArrayUtils;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.swing.handler.mxRubberband;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>联查费用发票
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-4-11 下午02:29:00
 */
public class LinkQueryFeeInvoiceAction extends InvoiceNormalAction {

  private static class LQGraphComponent extends mxGraphComponent {

    private static final long serialVersionUID = 1568827153418207449L;

    public LQGraphComponent(mxGraph graph) {
      super(graph);
      // 双击监听器
      this.getGraphControl().addMouseListener(new MouseAdapter() {
        @Override
        public void mouseReleased(MouseEvent e) {
          if (2 != e.getClickCount()) {
            return;
          }
          Object cell = LQGraphComponent.this.getCellAt(e.getX(), e.getY());
          if (cell != null) {
            LQGraphComponent.this.openInvoiceView(cell);

          }
        }
      });
    }

    /**
     * 父类方法重写
     * 
     * @see com.mxgraph.swing.mxGraphComponent#createComponents(com.mxgraph.view.mxCellState)
     */
    @Override
    public Component[] createComponents(mxCellState state) {
      Object cell = state.getCell();
      if (!this.getGraph().getModel().isVertex(cell)) {
        return super.createComponents(state);
      }
      UIPanel panel = new UIPanel();
      panel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
      // panel.setBorder(BorderFactory.createRaisedBevelBorder());
      // panel.setBackground(Color.CYAN.darker());
      InvoiceVO vo = (InvoiceVO) this.getGraph().getModel().getValue(cell);
      panel.add(new UILabel(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
          .getStrByID("4004050_0", "04004050-0010")/* @res "发票号：" */));
      JTextField tf = new UITextField(12);
      tf.setText(vo.getParentVO().getVbillcode());
      tf.setBackground(panel.getBackground());
      tf.setEditable(false);
      panel.add(tf);
      return new Component[] {
        panel
      };
    }

    void openInvoiceView(Object cell) {
      final InvoiceVO vo =
          (InvoiceVO) this.getGraph().getModel().getValue(cell);
      BilltypeVO bvo = PfDataCache.getBillType(POBillType.Invoice.getCode());
      String funcode = bvo.getNodecode();
      if (StringUtil.isEmptyWithTrim(funcode)) {
        ExceptionUtils.wrappBusinessException(POBillType.Invoice.getCode()
            + nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                "4004050_0", "04004050-0011")/* @res "类型单据未注册节点号!" */);
      }
      ILinkQueryData data = new ILinkQueryData() {

        @Override
        public String getBillID() {
          return vo.getParentVO().getPk_invoice();
        }

        @Override
        public String getBillType() {
          return POBillType.Invoice.getCode();
        }

        @Override
        public String getPkOrg() {
          return vo.getParentVO().getPk_org();
        }

        @Override
        public Object getUserObject() {
          return vo;
        }
      };
      SFClientUtil.openLinkedQueryDialog(funcode, WorkbenchEnvironment
          .getInstance().getWorkbench(), data);
    }
  }

  private static final long serialVersionUID = -4075835748710745407L;

  private List<Object> allInvoiceCell = new ArrayList<Object>();

  public LinkQueryFeeInvoiceAction() {
    SCMActionInitializer.initializeAction(this,
        SCMActionCode.PU_LINKQUERYFEEINVOICE);
    // this.setBtnName("联查费用发票");
    // this.setCode("btnQueryFeeInvoice");
    // this.putValue(Action.SHORT_DESCRIPTION, this.getBtnName());

  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#doAction(java.awt.event.ActionEvent)
   */
  @Override
  public void doAction(ActionEvent e) throws Exception {
    InvoiceVO[] feeVos = this.queryFeeInvoices();
    if (ArrayUtils.isEmpty(feeVos)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004050_0", "04004050-0012")/*
                                                                   * @res
                                                                   * "所选发票没有关联费用发票!"
                                                                   */);
    }
    UIDialog dialog = this.getShowDialog();
    mxGraphComponent graphComponent =
        this.getGraphComp(this.getSelectInvoice(), feeVos);
    dialog.add(graphComponent, BorderLayout.CENTER);
    this.centerGraph(dialog, graphComponent.getGraph());
    dialog.showModal();
  }

  private void centerGraph(UIDialog parent, mxGraph graph) {
    int pcx = parent.getWidth() / 2;
    int pcy = parent.getHeight() / 2;
    int gcx = (int) graph.getGraphBounds().getCenterX();
    int gcy = (int) graph.getGraphBounds().getCenterY();
    if (pcy - gcy < 0) {
      graph.moveCells(this.allInvoiceCell.toArray(), pcx - gcx, 0);
    }
    else {
      graph.moveCells(this.allInvoiceCell.toArray(), pcx - gcx, pcy - gcy);
    }

  }

  private Object createInvoiceRelation(mxGraph graph, Object src, Object tag) {
    Object parent = graph.getDefaultParent();
    return graph.insertEdge(parent, null, "Edge", src, tag);
  }

  private Object createInvoiceView(mxGraph graph, InvoiceVO vo) {
    Object parent = graph.getDefaultParent();
    String billcode = vo.getParentVO().getVbillcode();
    Object cell = graph.insertVertex(parent, null, billcode, 20, 20, 150, 60);
    graph.getModel().setValue(cell, vo);
    this.allInvoiceCell.add(cell);
    return cell;
  }

  private mxGraphComponent getGraphComp(InvoiceVO normVo, InvoiceVO[] feeVos) {
    mxGraph graph = new mxGraph();
    graph.setCellsCloneable(false);
    graph.setCellsEditable(false);
    graph.setAllowDanglingEdges(false);
    graph.setLabelsVisible(false);
    graph.getModel().beginUpdate();
    Object normView = this.createInvoiceView(graph, normVo);
    for (InvoiceVO vo : feeVos) {
      Object feeView = this.createInvoiceView(graph, vo);

      this.createInvoiceRelation(graph, normView, feeView);
    }
    graph.getModel().endUpdate();
    mxHierarchicalLayout layout =
        new mxHierarchicalLayout(graph, SwingConstants.WEST);
    layout.setIntraCellSpacing(75);
    layout.setInterRankCellSpacing(180);
    layout.execute(graph.getDefaultParent());
    mxGraphComponent graphComponent = new LQGraphComponent(graph);
    graphComponent.setConnectable(false);
    new mxRubberband(graphComponent);
    return graphComponent;
  }

  private InvoiceVO getSelectInvoice() {
    return (InvoiceVO) this.getModel().getSelectedData();
  }

  private UIDialog getShowDialog() {
    UIDialog dialog =
        new UIDialog(WorkbenchEnvironment.getInstance().getWorkbench());
    dialog.setLayout(new BorderLayout());
    dialog.setTitle(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004050_0", "04004050-0013")/* @res "联查费用发票" */);
    dialog.setSize(800, 600);
    dialog.setResizable(true);
    return dialog;
  }

  private InvoiceVO[] queryFeeInvoices() {
    InvoiceVO normVo = this.getSelectInvoice();
    IInvoiceQuery srv = NCLocator.getInstance().lookup(IInvoiceQuery.class);
    try {
      return srv.queryFee(new String[] {
        normVo.getParentVO().getPk_invoice()
      });
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
    }
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.uif2.NCAction#isActionEnable()
   */
  @Override
  protected boolean isActionEnable() {
    if (UIState.NOT_EDIT != this.getModel().getUiState()) {
      return false;
    }
    if (null == this.getSelectInvoice()) {
      return false;
    }
    if (this.getSelectInvoice().getParentVO().getBfee().booleanValue()) {
      return false;
    }
    return true;
  }

}
