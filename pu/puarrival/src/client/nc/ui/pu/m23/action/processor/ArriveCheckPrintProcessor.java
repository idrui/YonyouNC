/**
 * $文件说明$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-26 下午03:52:43
 */
package nc.ui.pu.m23.action.processor;

import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction.IBeforePrintDataProcess;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.entity.ArriveViewVO;
import nc.vo.pu.m23.rule.ArriveScaleRule;
import nc.vo.pu.m23.utils.ArrivePublicUtil;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pubapp.scale.BillVOScaleProcessor;
import nc.vo.pubapp.scale.TotalValueVOScaleProcessor;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>到货单打印前处理，如精度等
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-10-26 下午03:52:43
 */
public class ArriveCheckPrintProcessor implements IBeforePrintDataProcess {

  private AbstractAppModel model = null;

  public AbstractAppModel getModel() {
    return this.model;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction.IBeforePrintDataProcess#processData(java.lang.Object[])
   */
  @Override
  public Object[] processData(Object[] datas) {
    // 转化为到货单vo
    ArriveViewVO[] views = (ArriveViewVO[]) ArrayUtil.convertArrayType(datas);
    ArriveVO[] vos = ArrivePublicUtil.convertViewToAggVO(views);
    // 精度处理
    this.processScale(vos);
    return vos;
  }

  public void setModel(AbstractAppModel model) {
    this.model = model;
  }

  /**
   * 精度处理
   * 
   * @param vos
   */
  private void processScale(ArriveVO[] vos) {
    BillVOScaleProcessor scale =
        new BillVOScaleProcessor(this.getModel().getContext().getPk_group(),
            vos);
    TotalValueVOScaleProcessor totalScale = new TotalValueVOScaleProcessor(vos);
    new ArriveScaleRule().setScale(scale, totalScale);
  }
}
