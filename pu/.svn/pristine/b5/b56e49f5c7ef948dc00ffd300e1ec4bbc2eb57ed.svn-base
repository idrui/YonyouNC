/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-8-7 上午08:17:40
 */
package nc.vo.pu.m21.rule;

import nc.vo.pu.m21.entity.OrderReceivePlanVO;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.calculator.Calculator;
import nc.vo.pubapp.calculator.Condition;
import nc.vo.pubapp.calculator.data.IDataSetForCal;
import nc.vo.pubapp.calculator.data.IRelationForItems;
import nc.vo.pubapp.calculator.data.RelationItemForCal;
import nc.vo.pubapp.calculator.data.VODataSetForCal;
import nc.vo.pubapp.scale.ScaleUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-8-7 上午08:17:40
 */
public class RPRelationCalculate {
  private static class OrderRPDataSetForCal extends VODataSetForCal {
    public OrderRPDataSetForCal(CircularlyAccessibleValueObject currVO,
        IRelationForItems item) {
      super(currVO, item);
    }
  }

  public void calculate(OrderReceivePlanVO vo, String itemKey) {
    IRelationForItems item = new RelationItemForCal();
    ScaleUtils scale = new ScaleUtils(vo.getPk_group());
    // 创建数据集实例 初始化数据关系计算用的数据集
    IDataSetForCal data = new OrderRPDataSetForCal(vo, item);
    Calculator tool = new Calculator(data, scale);//
    // 创建参数实例，在计算的时候用来获得参数条件：是否含税优先等
    Condition cond = new Condition();// 创建参数实例
    // String material = vo.getPk_material();
    // String cunitid = vo.getCunitid();
    // String castunitid = vo.getCastunitid();
    // String cqtunitid = vo.getCqtunitid();
    // // 设置是否固定单位换算率
    // cond.setIsFixNchangerate(this.isFixUnitRate(material, cunitid,
    // castunitid));
    // // 是否固定报价单位换算率
    // cond.setIsFixNqtunitrate(this.isFixUnitRate(material, cunitid,
    // cqtunitid));

    // 设置是否固定单位换算率
    cond.setIsFixNchangerate(true);
    // 是否固定报价单位换算率
    cond.setIsFixNqtunitrate(true);
    // 两个参数 cond 为计算时的参数条件
    tool.calculate(cond, itemKey);
  }

  // private boolean isFixUnitRate(String material, String cunitid,
  // String castunitid) {
  // boolean flag = true;
  // if (material == null || cunitid == null || castunitid == null) {
  // return flag;
  // }
  // flag =
  // MaterialPubService.queryIsFixedRateByMaterialAndMeasdoc(material,
  // cunitid, castunitid);
  // return flag;
  // }
}
