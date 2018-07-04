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

import java.awt.Dimension;
import java.util.Map;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.pu.reference.ic.ATPServices;
import nc.itf.scmpub.reference.uap.bd.accesor.MeasAccessor;
import nc.ui.uif2.components.widget.WidgetInfo;
import nc.vo.bd.accessor.IBDData;
import nc.vo.ic.atp.entity.AtpVO;
import nc.vo.ic.atp.pub.AtpQryParamVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pub.lang.MultiLangText;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.MathTool;

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
public class SavingAmountForm extends OrderSideForm {

  private static final long serialVersionUID = 8604270506080939388L;
  
  private static String[] vaules = new String[]{
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
    .getStrByID("4004030_0", "04004030-0087")/* @res "查看当前存量" */,
        nc.vo.ml.NCLangRes4VoTransl.getNCLangRes()
    .getStrByID("4004030_0", "04004030-0088")/* @res "查看当前选中的行的现存量" */
    };

  public SavingAmountForm() {
    super(vaules);
  }

  @Override
  public WidgetInfo getWidgetInfo() {
    WidgetInfo info = new WidgetInfo();
    info.setTitle(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
        "4004030_0", "04004030-0086")/* @res "存量" */);
    return info;
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
        .getValueAtCurrentRow(OrderItemVO.PK_ARRVSTOORG));
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

  @Override
  protected void initUI() {
    this.setPreferredSize(new Dimension(150, 150));
    super.initUI();
  }

  @Override
  protected void notifyUpdateData() {

    // by luojw 存量和可用用量不区分物料版本，所以取oid
    String pk_mater =
        (String) this.sfMediator.getValueAtCurrentRow(OrderItemVO.PK_SRCMATERIAL);
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
