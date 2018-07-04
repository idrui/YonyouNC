package nc.impl.pu.m20;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.bs.pu.m20.maintain.PraybillQueryBP;
import nc.bs.pu.m20.maintain.rule.pub.ChangeRateRule;
import nc.bs.pu.m20.maintain.rule.pub.SetPraytypeRule;
import nc.bs.pu.m20.maintain.rule.pub.SetPsnAndDeptRule;
import nc.bs.pu.m20.maintain.rule.pub.SetSctypeRule;
import nc.impl.pu.m20.action.PraybillCloseAction;
import nc.impl.pu.m20.action.PraybillDeleteAction;
import nc.impl.pu.m20.action.PraybillInsertAction;
import nc.impl.pu.m20.action.PraybillUpdateAction;
import nc.impl.pubapp.pattern.data.bill.tool.BillTransferTool;
import nc.itf.pu.m20.IPraybillMaintain;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.m20.rule.BDirectRule;
import nc.vo.pu.m20.rule.CalculateDateRule;
import nc.vo.pu.m20.rule.CalculateNumRule;
import nc.vo.pu.m20.rule.SetAstunitRule;
import nc.vo.pu.m20.rule.SetDefaultValueRule;
import nc.vo.pu.m20.rule.SetEmployeeRule;
import nc.vo.pu.m20.rule.SetOrdertrantypeRule;
import nc.vo.pu.m20.rule.SetOrgCurrRule;
import nc.vo.pu.m20.rule.SetOrgNewVersionRule;
import nc.vo.pu.m20.rule.SetPriceRule;
import nc.vo.pu.m20.rule.SetPurchaseorgRule;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

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
 * @author linsf
 * @time 2010-1-27 下午08:35:13
 */
public class PraybillMaintainImpl implements IPraybillMaintain {

  @Override
  public PraybillVO[] closeBill(PraybillVO[] Vos) throws BusinessException {
    try {
      // 传入的是差异VO但为了记日志的需要，在元数据里注册的是closeBillByFullVO方法，而不是closeBill方法
      // 因为传入的vo必须是全vo才能记录全日志
      BillTransferTool<PraybillVO> tool = new BillTransferTool<PraybillVO>(Vos);
      PraybillVO[] fullVOs = tool.getClientFullInfoBill();
      PraybillVO[] returnVOs =
          NCLocator.getInstance().lookup(IPraybillMaintain.class)
              .closeBillByFullVO(fullVOs);
      return tool.getBillForToClient(returnVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

  @Override
  public PraybillVO[] closeBillByFullVO(PraybillVO[] Vos)
      throws BusinessException {
    try {
      PraybillVO[] returnVos = new PraybillCloseAction().closeBill(Vos);
      return returnVos;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

  @Override
  public PraybillVO[] closeBillRow(PraybillVO[] Vos) throws BusinessException {
    try {
      // 需要关闭的行。
      Set<String> closePks = new HashSet<String>();
      for (PraybillVO vo : Vos) {
        for (PraybillItemVO itemVo : vo.getBVO()) {
          closePks.add(itemVo.getPk_praybill_b());
        }
      }

      // 传入的是差异VO但为了记日志的需要，在元数据里注册的是closeBillByFullVO方法，而不是closeBill方法
      // 因为传入的vo必须是全vo才能记录全日志
      BillTransferTool<PraybillVO> tool = new BillTransferTool<PraybillVO>(Vos);
      PraybillVO[] fullVOs = tool.getClientFullInfoBill();
      PraybillVO[] returnVOs =
          NCLocator.getInstance().lookup(IPraybillMaintain.class)
              .closeBillRowByFullVO(fullVOs, closePks);
      return tool.getBillForToClient(returnVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

  @Override
  public PraybillVO[] closeBillRowByFullVO(PraybillVO[] Vos,
      Set<String> closePks) throws BusinessException {
    try {
      PraybillVO[] returnVos =
          new PraybillCloseAction().closeBillRow(Vos, closePks);
      return returnVos;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m20.IPraybillMaintain#delete(nc.vo.pu.m20.entity.PraybillVO[])
   */
  @Override
  public void delete(PraybillVO[] Vos) throws BusinessException {
    try {
      new PraybillDeleteAction().delete(Vos);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m20.IPraybillMaintain#insert(nc.vo.pu.m20.entity.PraybillVO[])
   */
  @Override
  public PraybillVO[] insert(PraybillVO[] Vos) throws BusinessException {
    PraybillVO[] ret = null;
    try {
      PraybillInsertAction action = new PraybillInsertAction();

      ret = action.insert(Vos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return ret;
  }

  @Override
  public PraybillVO[] openBill(PraybillVO[] Vos) throws BusinessException {
    try {
      // 传入的是差异VO但为了记日志的需要，在元数据里注册的是closeBillByFullVO方法，而不是closeBill方法
      // 因为传入的vo必须是全vo才能记录全日志
      BillTransferTool<PraybillVO> tool = new BillTransferTool<PraybillVO>(Vos);
      PraybillVO[] fullVOs = tool.getClientFullInfoBill();
      PraybillVO[] returnVOs =
          NCLocator.getInstance().lookup(IPraybillMaintain.class)
              .openBillByFullVO(fullVOs);
      return tool.getBillForToClient(returnVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

  @Override
  public PraybillVO[] openBillByFullVO(PraybillVO[] Vos)
      throws BusinessException {
    try {
      PraybillVO[] returnVos = new PraybillCloseAction().openBill(Vos);
      return returnVos;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

  @Override
  public PraybillVO[] openBillRow(PraybillVO[] Vos) throws BusinessException {
    try {
      Set<String> openPks = new HashSet<String>();
      for (PraybillVO vo : Vos) {
        for (PraybillItemVO itemVo : vo.getBVO()) {
          openPks.add(itemVo.getPk_praybill_b());
        }
      }

      // 传入的是差异VO但为了记日志的需要，在元数据里注册的是closeBillByFullVO方法，而不是closeBill方法
      // 因为传入的vo必须是全vo才能记录全日志
      BillTransferTool<PraybillVO> tool = new BillTransferTool<PraybillVO>(Vos);
      PraybillVO[] fullVOs = tool.getClientFullInfoBill();
      PraybillVO[] returnVOs =
          NCLocator.getInstance().lookup(IPraybillMaintain.class)
              .openBillRowByFullVO(fullVOs, openPks);
      return tool.getBillForToClient(returnVOs);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

  @Override
  public PraybillVO[] openBillRowByFullVO(PraybillVO[] Vos, Set<String> openPks)
      throws BusinessException {
    try {
      PraybillVO[] returnVos =
          new PraybillCloseAction().openBillRow(Vos, openPks);
      return returnVos;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }

    return null;
  }

  @Override
  public PraybillVO[] queryPrayBills(IQueryScheme queryScheme)
      throws BusinessException {
    try {
      return new PraybillQueryBP().query(queryScheme);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m20.IPraybillMaintain#saveBase(nc.vo.pu.m20.entity.PraybillVO[])
   */
  @Override
  public PraybillVO[] saveBase(PraybillVO[] Vos) throws BusinessException {
    PraybillVO[] savedVos = null;
    try {
      List<PraybillVO> insertVOList = new ArrayList<PraybillVO>();
      List<PraybillVO> updateVOList = new ArrayList<PraybillVO>();
			for (int i = 0, len = Vos.length; i < len; i++) {
				if (VOStatus.NEW == (Vos[i].getHVO().getStatus())) {
					insertVOList.add(Vos[i]);
				} else {
					updateVOList.add(Vos[i]);
				}
			}
      if (insertVOList.size() != 0) {
        // 记录日志用此形式
        return NCLocator.getInstance().lookup(IPraybillMaintain.class)
            .insert(insertVOList.toArray(new PraybillVO[insertVOList.size()]));
      }
      if (updateVOList.size() != 0) {
        // 记录日志用此形式
        return NCLocator.getInstance().lookup(IPraybillMaintain.class)
            .update(updateVOList.toArray(new PraybillVO[updateVOList.size()]));
      }
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return savedVos;
  }

  @Override
  public PraybillVO[] setDefaultValue(PraybillVO[] vos)
      throws BusinessException {
    try {
      // 设置组织最新版本
      new SetOrgNewVersionRule().process(vos);
      // 直运处理
      new BDirectRule().process(vos);
      // 补全是否委外 并分单
      PraybillVO[] afterSplitVos = new SetSctypeRule().process(vos);
      // 默认值
      new SetDefaultValueRule().process(afterSplitVos);
      // 补全本币币种
      new SetOrgCurrRule().process(afterSplitVos);
      // 补全请购类型
      new SetPraytypeRule().process(afterSplitVos);
      // 补全计划员和计划部门
      new SetPsnAndDeptRule().process(afterSplitVos);

      // 补全采购组织
      new SetPurchaseorgRule().process(afterSplitVos);
      // 补全采购员
      new SetEmployeeRule().process(afterSplitVos);
      // 补全订单类型
      new SetOrdertrantypeRule().process(afterSplitVos);
      // 补全单位
      new SetAstunitRule().process(afterSplitVos);
      // 补全换算率
      new ChangeRateRule().process(afterSplitVos);
      // 补全数量
      new CalculateNumRule().process(afterSplitVos);
      // 补全需求日期和建议订货日期
      new CalculateDateRule().process(afterSplitVos);
      // 补全含税单价和价税合计
      new SetPriceRule().process(afterSplitVos);

      this.setUFDouble(afterSplitVos);

      return afterSplitVos;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.itf.pu.m20.IPraybillMaintain#update(nc.vo.pu.m20.entity.PraybillVO[])
   */
  @Override
  public PraybillVO[] update(PraybillVO[] Vos) throws BusinessException {
    PraybillVO[] ret = null;
    try {
      PraybillUpdateAction action = new PraybillUpdateAction();

      ret = action.update(Vos);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return ret;
  }

  private void setUFDouble(PraybillVO[] vos) {
    // 设置默认精度，否则可能在往前台传数据序列化时报错
    for (PraybillVO vo : vos) {
      PraybillItemVO[] items = vo.getBVO();
      if (ArrayUtils.isEmpty(items)) {
        continue;
      }

      for (PraybillItemVO item : items) {
        UFDouble f = item.getNnum();
        if (null != f) {
          f = f.setScale(UFDouble.DEFAULT_POWER, UFDouble.ROUND_HALF_UP);
          item.setNnum(f);
        }

        f = item.getNastnum();
        if (null != f) {
          f = f.setScale(UFDouble.DEFAULT_POWER, UFDouble.ROUND_HALF_UP);
          item.setNastnum(f);
        }
      }
    }
  }

  @Override
  public PraybillVO[] setDefaultValueForM4B32(PraybillVO[] vos)
      throws BusinessException {
    try {
      // 补全本位币
      new SetOrgCurrRule().process(vos);
      // 补全表体采购组织
      new SetPurchaseorgRule().process(vos);
      // 补全采购员
      new SetEmployeeRule().process(vos);
      // 设置单位，换算率，固定换算率
      new SetAstunitRule().process(vos);
      // 设置数量
      new CalculateNumRule().process(vos);
      // 补全需求日期和建议订货日期
      new CalculateDateRule().process(vos);
      // 补全含税单价和价税合计
      new SetPriceRule().process(vos);
      return vos;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
}
