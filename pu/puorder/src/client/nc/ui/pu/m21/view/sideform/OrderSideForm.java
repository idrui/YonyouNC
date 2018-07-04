/**
 * $文件说明$
 *
 * @author wanghuid
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-21 上午11:35:51
 */
package nc.ui.pu.m21.view.sideform;

import java.awt.Component;
import java.awt.Insets;
import java.util.Map;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.pu.reference.ic.ATPServices;
import nc.itf.scmpub.reference.uap.bd.accesor.MeasAccessor;
import nc.ui.pub.beans.UIPanel;
import nc.ui.queryarea.component.QueryAreaHyperlinkButton;
import nc.ui.uif2.components.widget.IBesideWidgetlet;
import nc.vo.bd.accessor.IBDData;
import nc.vo.ic.atp.entity.AtpVO;
import nc.vo.ic.atp.pub.AtpQryParamVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pub.lang.MultiLangText;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.uif2.LoginContext;

import org.apache.commons.lang.StringUtils;

import ChartDirector.ChartViewer;
import ChartDirector.XYChart;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>存量form
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wanghuid
 * @time 2010-10-21 上午11:35:51
 */
public abstract class OrderSideForm extends UIPanel implements IBesideWidgetlet,
    ISideFormDisplay {

  private static final long serialVersionUID = 8604270506080939388L;

  private LoginContext context;

  protected OrderSideFormMediator sfMediator;
  
  private String[] values;

  public OrderSideForm(String[] values) {
    super();
    this.values = values;
    this.initUI();
  }

  @Override
  public void display(OrderSideFormMediator mediator) {
    this.sfMediator = mediator;
    this.removeAll();
    this.notifyUpdateData();
  }

  @Override
  public Insets getComponentInsets() {
    return null;
  }

  @Override
  public Component getContentComponent() {
    return this;
  }

  public LoginContext getContext() {
    return this.context;
  }

  public OrderSideFormMediator getSfMediator() {
    return this.sfMediator;
  }

  @Override
  public void reset(OrderSideFormMediator mediator) {
    this.sfMediator = mediator;
    this.resetUI();
  }

  public void resetUI() {
    this.removeAll();
    this.initUI();
  }

  public void setContext(LoginContext context) {
    this.context = context;
  }

  public void setSfMediator(OrderSideFormMediator sfMediator) {
    this.sfMediator = sfMediator;
  }

  @Override
  public void setWidgetState(int state) {
    // this.state = state;
    // 暂时没有操作，主要是释放内存等操作
  }

  /**
   * 生成现存量柱状图
   * <p>
   * <b>参数说明</b>
   * 
   * @param cunitname
   *          单位
   * @param data
   *          生成柱状图的坐标数据
   * @return
   *         <p>
   * @author xihy1
   * @time 2011-4-20 上午08:33:38
   */
  private void createAmountBar(String cunitname, double[] data) {
    ChartViewer viewer = new ChartViewer();
    this.createChart(viewer, data, cunitname);
    this.add(viewer);
    this.updateUI();
  }

  /**
   * 绘制现存量柱状图
   * <p>
   * <b>参数说明</b>
   * 
   * @param viewer
   *          ChartViewer对象
   * @param data
   *          生成柱状图的坐标数据
   * @param cunitname
   *          单位
   * @return
   *         <p>
   * @author xihy1
   * @time 2011-4-20 上午08:33:38
   */
  private void createChart(ChartViewer viewer, double[] data, String cunitname) {
    String[] labels =
        {
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
              "UC000-0002918")/* @res "现存量" */,
          nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("common",
              "UC000-0001084")
        /* @res "可用量" */
        };
    int addLen = this.getAutoAddLen(data);
    XYChart chart = new XYChart(250 + addLen * 5, 300);

    chart.addBarLayer(data).set3D(10);
    chart.setPlotArea(50 + addLen * 5, 30, 150, 80);

    chart.xAxis().setLabels(labels);
    chart.yAxis().setTitle(cunitname);
    chart.setDefaultFonts(WorkbenchEnvironment.getClientApplet().getFont()
        .getFontName(), WorkbenchEnvironment.getClientApplet().getFont()
        .getFontName(), "");
    viewer.setImage(chart.makeImage());

    viewer.setImageMap(chart.getHTMLImageMap("clickable", "",
        "title='{xLabel}: {value} " + cunitname + "'"));
  }

  /**
   * 获得需要动态添加的长度
   * 
   * 由于现存量或可用量的数值又大有小，如果固定存量查询框的大小，可能会出现不能显示全的问题
   * 
   * @param data
   * @return  取两个数量大的整数部分作为返回值
   */
  private int getAutoAddLen(double[] data) {
    int addLen = 0;
    for(int i = 0; i < 2; i++){
      int tempLen ;
      String str = Double.toString(data[i]);
      // 如果数值过大，会用科学计数法。需要判断是否有E
      int index = str.indexOf("E");
      if(index == -1){
        tempLen = str.split("\\.")[0].length();
      }else{
        tempLen = Integer.valueOf(str.substring(index + 1)).intValue();
      }
      if(tempLen > addLen){
        addLen = tempLen;
      }
    }
    return addLen;
  }

  private AtpQryParamVO[] getParamVOs() {
    AtpQryParamVO paramVO = new AtpQryParamVO();
    paramVO.setCasscustid((String) this.sfMediator
        .getValueAtCurrentRow(OrderItemVO.CASSCUSTID));
    paramVO.setCmaterialoid((String) this.sfMediator
        .getValueAtCurrentRow(OrderItemVO.PK_SRCMATERIAL));
    paramVO.setCproductorid((String) this.sfMediator
        .getValueAtCurrentRow(OrderItemVO.CPRODUCTORID));
    paramVO.setCprojectid((String) this.sfMediator
        .getValueAtCurrentRow(OrderItemVO.CPROJECTID));
    paramVO.setCvendorid((String) this.sfMediator
        .getValueAtCurrentRow(OrderItemVO.PK_SUPPLIER));
    paramVO.setCwarehouseid((String) this.sfMediator
        .getValueAtCurrentRow(OrderItemVO.PK_RECVSTORDOC));
    paramVO.setDplandate((UFDate) this.sfMediator
        .getValueAtCurrentRow(OrderItemVO.DPLANARRVDATE));
    paramVO.setPk_group((String) this.sfMediator
        .getValueAtCurrentRow(OrderItemVO.PK_GROUP));
    paramVO.setPk_org((String) this.sfMediator
        .getValueAtCurrentRow(OrderItemVO.PK_ORG));
    paramVO.setVbatchcode((String) this.sfMediator
        .getValueAtCurrentRow(OrderItemVO.VBATCHCODE));
    paramVO.setVfree1((String) this.sfMediator
        .getValueAtCurrentRow(OrderItemVO.VFREE1));
    paramVO.setVfree2((String) this.sfMediator
        .getValueAtCurrentRow(OrderItemVO.VFREE2));
    paramVO.setVfree3((String) this.sfMediator
        .getValueAtCurrentRow(OrderItemVO.VFREE3));
    paramVO.setVfree4((String) this.sfMediator
        .getValueAtCurrentRow(OrderItemVO.VFREE4));
    paramVO.setVfree5((String) this.sfMediator
        .getValueAtCurrentRow(OrderItemVO.VFREE5));
    paramVO.setVfree6((String) this.sfMediator
        .getValueAtCurrentRow(OrderItemVO.VFREE6));
    paramVO.setVfree7((String) this.sfMediator
        .getValueAtCurrentRow(OrderItemVO.VFREE7));
    paramVO.setVfree8((String) this.sfMediator
        .getValueAtCurrentRow(OrderItemVO.VFREE8));
    paramVO.setVfree9((String) this.sfMediator
        .getValueAtCurrentRow(OrderItemVO.VFREE9));
    paramVO.setVfree10((String) this.sfMediator
        .getValueAtCurrentRow(OrderItemVO.VFREE10));
    return new AtpQryParamVO[] {
      paramVO
    };
  }

  protected void initUI() {
    OrderSideFormAction action = new OrderSideFormAction(this, this.context, this.values);
    this.add(new QueryAreaHyperlinkButton(action));
    this.updateUI();
  }

  /**
   * 显示更新图形界面的数据
   * <p>
   * 
   * @return
   *         <p>
   * @author xihy1
   * @time 2011-4-20 上午08:33:38
   */
  protected void notifyUpdateData() {

    String pk_mater =
        (String) this.sfMediator.getValueAtCurrentRow(OrderItemVO.PK_MATERIAL);
    String calBody =
        (String) this.sfMediator.getValueAtCurrentRow(OrderItemVO.PK_REQSTOORG);
    // 取得现存量和可用量
    if (StringUtils.isEmpty(pk_mater) || StringUtils.isEmpty(calBody)) {
      this.createAmountBar("", new double[] {
        0, 0
      });
      return;
    }
    String cunitid =
        (String) this.sfMediator.getValueAtCurrentRow(OrderItemVO.CUNITID);
    IBDData meadata = MeasAccessor.getDocByPk(cunitid);
    MultiLangText cunittext = meadata.getName();
    String cunitname = cunittext.getText(cunittext.getCurrLangIndex());
    // Map<String, AtpVO> atpMap = ATPServices.queryAtpAndOnhandNum(new String[]
    // {
    // calBody
    // }, new String[] {
    // pk_mater
    // }, new UFDate[1]);
    Map<String, AtpVO> atpMap =
        ATPServices.queryAtpAndOnhandNum(this.getParamVOs());

    AtpVO atpVO = atpMap.get(pk_mater);

    UFDouble atp = atpVO.getNatpnum();
    UFDouble currAmount = atpVO.getNonhandnum();

    double[] data = {
      MathTool.nvl(currAmount).doubleValue(), MathTool.nvl(atp).doubleValue()
    };
    this.createAmountBar(cunitname, data);
  }

}
