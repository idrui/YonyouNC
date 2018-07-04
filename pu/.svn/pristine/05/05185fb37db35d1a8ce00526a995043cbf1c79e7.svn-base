/**
 * $文件说明$
 * 
 * @author lixyp
 * @version 6.1
 * @see
 * @since 6.1
 * @time 2011-12-28 上午10:02:27
 */
package nc.vo.pu.m20.rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;

/**
 * 
 * @description
 * 请购单设置默认采购员
 * @scene
 * 计划订单推式保存请购单、生产订单推式保存请购单
 * @param
 * 
 *
 * @since 6.3
 * @version 2014-10-21 下午2:05:46
 * @author luojw
 */
public class SetEmployeeRule implements IRule<PraybillVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(PraybillVO[] vos) {
    if (ArrayUtils.isEmpty(vos)) {
      return;
    }
    this.setEmployee(vos);
  }

  /**
   * 方法功能描述：询采购岗设置采购员。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          <p>
   * @since 6.1
   * @author lixyp
   * @time 2011-12-28 上午10:13:27
   */
  public void setEmployee(PraybillVO[] vos) {
    Map<String, String> employees = this.getPurchaser(vos);
    if (null == employees || employees.size() == 0) {
      return;
    }

    for (PraybillVO vo : vos) {
      PraybillItemVO[] items = vo.getBVO();
      if (ArrayUtils.isEmpty(items)) {
        continue;
      }
      for (int i = 0, len = items.length; i < len; i++) {
        String pk_org = items[i].getPk_purchaseorg();
        String pk_material = items[i].getPk_material();

        if (null != pk_org && null != pk_material) {
          items[i].setPk_employee(employees.get(pk_org + pk_material));
        }
      }
    }
  }

  /**
   * 根据采购组织、库存组织和物料查询采购岗物料设置。
   * <p>
   * <b>参数说明<b>
   * 
   * @param vos 请购单vo数组
   * @return
   *         key为采购组织+物料pk，value为采购员
   */
  private Map<String, String> getPurchaser(PraybillVO[] vos) {
    List<String> pk_orgs = new ArrayList<String>();
    List<String> pk_materials = new ArrayList<String>();
    List<String> pk_stocks = new ArrayList<String>();
    // 加上一个参数，去除重复值。
    List<String> keyList = new ArrayList<>();
    for (PraybillVO vo : vos) {
      PraybillItemVO[] items = vo.getBVO();
      String pk_stock = vo.getHVO().getPk_org();
      if (ArrayUtils.isEmpty(items)) {
        continue;
      }
      for (int i = 0, len = items.length; i < len; i++) {
        // 采购组织
        String pk_org = items[i].getPk_purchaseorg();
        String pk_material = items[i].getPk_material();
        String key = pk_org + pk_material;
        if(keyList.contains(key)){
          continue;
        }
        keyList.add(key);
        if (null != pk_org && null != pk_material) {
          pk_orgs.add(pk_org);
          pk_materials.add(pk_material);
          pk_stocks.add(pk_stock);
        }
      }
    }
    try {
      return NCLocator
          .getInstance()
          .lookup(nc.pubitf.pu.position.IQueryPosition.class)
          .getPurchaser(pk_orgs.toArray(new String[pk_orgs.size()]),
              pk_stocks.toArray(new String[pk_stocks.size()]),
              pk_materials.toArray(new String[pk_materials.size()]));
    }
    catch (BusinessException e) {
      // 日志异常
      ExceptionUtils.wrappException(e);
      return null;
    }
  }
}
