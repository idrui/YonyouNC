/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-31 下午03:10:01
 */
package nc.bs.pu.m21.writeback.source;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.itf.pu.reference.ct.Z2CTServices;
import nc.vo.ct.entity.CtWriteBackForOrderVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pu.m21.context.OrderContext;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.bill.BillRowCompare;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.ObjectUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>当关联合同时回写合同
 * <li>和5系列区别，不需要处理赠品，不管是不是赠品，全部回写。
 * <li>采购合同对补货订单的控制规则,参照退货单或退库单生成补货订单时:
 * <li>如果退货单或退库单是参照退货订单生成的，则补货订单回写其关联的合同并受合同控制；
 * <li>如果退货单或退库单是参照无退货标志的采购订单生成的，则补货订单不回写其关联的合同；
 * <li>如果退库单是手工自制的，则补货订单回写其关联的合同并受合同控制
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-31 下午03:10:01
 */
public class WriteBackZ2 {

  private OrderContext ctx;

  public WriteBackZ2(OrderContext ctx) {
    this.ctx = ctx;
  }

  /**
   * 方法功能描述：当关联合同或者编辑合同信息（合同号）时回写合同
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @param orgingVOs <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-31 下午03:12:24
   */
  public void writeback(OrderVO[] vos, OrderVO[] originVOs) {

    if (ArrayUtils.isEmpty(vos)) {// 删除
      this.writebackWhenDelete(originVOs);
    }
    else if (ArrayUtils.isEmpty(originVOs)) {// 新增
      this.writebackWhenInsert(vos);
    }
    else {
      this.writebackWhenUpdate(vos, originVOs);
    }
  }

  /**
   * 方法功能描述：处理删除的行
   * <p>
   * <b>参数说明</b>
   * 
   * @param deleteSet
   * @param itemVO <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-31 下午04:10:46
   */
  private void processDeleteRow(Set<CtWriteBackForOrderVO> deleteSet,
      OrderItemVO itemVO) {
    String ccontractrowid = itemVO.getCcontractrowid();
    if (StringUtil.isEmptyWithTrim(ccontractrowid)) {
      return;
    }
    CtWriteBackForOrderVO writebackVO = new CtWriteBackForOrderVO();
    writebackVO.setPk_ctpu(itemVO.getCcontractid());
    writebackVO.setPk_ctpu_b(ccontractrowid);
    writebackVO.setUserConfirm(UFBoolean.TRUE.equals(this.ctx
        .getContractConfirm()));
    writebackVO.setcRowNum(itemVO.getCrowno());
    writebackVO.setPrice(itemVO.getNorigtaxprice());
    writebackVO.setNum(MathTool.oppose(itemVO.getNnum()));
    writebackVO.setPriceNum(MathTool.oppose(itemVO.getNorigtaxmny()));
    writebackVO.setCvendorid(itemVO.getPk_supplier());
    writebackVO.setCorigcurrencyid(itemVO.getCorigcurrencyid());
    writebackVO.setPk_org(itemVO.getPk_org());
    deleteSet.add(writebackVO);
  }

  /**
   * 方法功能描述：处理新增的行
   * <p>
   * <b>参数说明</b>
   * 
   * @param insetSet
   * @param itemVO <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-31 下午04:06:41
   */
  private void processInsertRow(Set<CtWriteBackForOrderVO> insetSet,
      OrderItemVO itemVO) {
    String ccontractrowid = itemVO.getCcontractrowid();
    if (StringUtil.isEmptyWithTrim(ccontractrowid)) {
      return;
    }

    CtWriteBackForOrderVO writebackVO = new CtWriteBackForOrderVO();
    writebackVO.setPk_ctpu(itemVO.getCcontractid());
    writebackVO.setPk_ctpu_b(ccontractrowid);
    writebackVO.setUserConfirm(UFBoolean.TRUE.equals(this.ctx
        .getContractConfirm()));
    writebackVO.setNum(itemVO.getNnum());
    writebackVO.setcRowNum(itemVO.getCrowno());
    writebackVO.setPrice(itemVO.getNorigtaxprice());
    writebackVO.setPriceNum(itemVO.getNorigtaxmny());
    writebackVO.setCvendorid(itemVO.getPk_supplier());
    writebackVO.setCorigcurrencyid(itemVO.getCorigcurrencyid());
    writebackVO.setPk_org(itemVO.getPk_org());
    insetSet.add(writebackVO);
  }

  /**
   * 方法功能描述：处理更新的行
   * <p>
   * <b>参数说明</b>
   * 
   * @param updateSet
   * @param itemVO
   * @param originItemVO <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-31 下午04:14:28
   */
  private void processUpdateRow(Set<CtWriteBackForOrderVO> updateSet,
      OrderItemVO itemVO, OrderItemVO originItemVO) {
    String ccontractrowid = itemVO.getCcontractrowid();
    String coldcontractrowid = originItemVO.getCcontractrowid();

    if (StringUtil.isEmptyWithTrim(ccontractrowid)
        && StringUtil.isEmptyWithTrim(coldcontractrowid)) {
      return;
    }

    if (ObjectUtils.equals(ccontractrowid, coldcontractrowid)) {
      UFDouble nnum = MathTool.sub(itemVO.getNnum(), originItemVO.getNnum());
      UFDouble pricenum =
          MathTool.sub(itemVO.getNorigtaxmny(), originItemVO.getNorigtaxmny());
      UFDouble norigtaxprice =
          MathTool.sub(itemVO.getNorigtaxprice(),
              originItemVO.getNorigtaxprice());

      // NCdp204548593
      // 是采购在回写的时候判断如果价税合计和数量没有改变，就不会回写。因为价税合计精度较小，所以单价改动小的话，价税合计体现不出来。
      if (0 == MathTool.compareTo(nnum, UFDouble.ZERO_DBL)
          && 0 == MathTool.compareTo(pricenum, UFDouble.ZERO_DBL)
          && MathTool.compareTo(norigtaxprice, UFDouble.ZERO_DBL) == 0) {
        return;
      }

      CtWriteBackForOrderVO writebackVO = new CtWriteBackForOrderVO();
      writebackVO.setPk_ctpu(itemVO.getCcontractid());
      writebackVO.setPk_ctpu_b(ccontractrowid);
      writebackVO.setUserConfirm(UFBoolean.TRUE.equals(this.ctx
          .getContractConfirm()));
      writebackVO.setNum(nnum);
      writebackVO.setcRowNum(itemVO.getCrowno());
      writebackVO.setPrice(itemVO.getNorigtaxprice());
      writebackVO.setPriceNum(pricenum);
      writebackVO.setCvendorid(itemVO.getPk_supplier());
      writebackVO.setCorigcurrencyid(itemVO.getCorigcurrencyid());
      writebackVO.setPk_org(itemVO.getPk_org());
      updateSet.add(writebackVO);
    }
    else {
      if (!StringUtil.isEmptyWithTrim(ccontractrowid)) {
        CtWriteBackForOrderVO writebackVO = new CtWriteBackForOrderVO();
        writebackVO.setPk_ctpu(itemVO.getCcontractid());
        writebackVO.setPk_ctpu_b(ccontractrowid);
        writebackVO.setUserConfirm(UFBoolean.TRUE.equals(this.ctx
            .getContractConfirm()));
        writebackVO.setNum(itemVO.getNnum());
        writebackVO.setPriceNum(itemVO.getNorigtaxmny());
        writebackVO.setPrice(itemVO.getNorigtaxprice());
        writebackVO.setcRowNum(itemVO.getCrowno());
        writebackVO.setCvendorid(itemVO.getPk_supplier());
        writebackVO.setCorigcurrencyid(itemVO.getCorigcurrencyid());
        writebackVO.setPk_org(itemVO.getPk_org());
        updateSet.add(writebackVO);
      }

      if (!StringUtil.isEmptyWithTrim(coldcontractrowid)) {
        CtWriteBackForOrderVO writebackVO = new CtWriteBackForOrderVO();
        writebackVO.setPk_ctpu(originItemVO.getCcontractid());
        writebackVO.setPk_ctpu_b(coldcontractrowid);
        writebackVO.setUserConfirm(UFBoolean.TRUE.equals(this.ctx
            .getContractConfirm()));
        writebackVO.setNum(MathTool.oppose(originItemVO.getNnum()));
        writebackVO.setPriceNum(MathTool.oppose(originItemVO.getNorigtaxmny()));
        writebackVO.setPrice(originItemVO.getNorigtaxprice());
        writebackVO.setcRowNum(originItemVO.getCrowno());
        writebackVO.setCvendorid(originItemVO.getPk_supplier());
        writebackVO.setCorigcurrencyid(originItemVO.getCorigcurrencyid());
        writebackVO.setPk_org(itemVO.getPk_org());
        updateSet.add(writebackVO);
      }
    }
  }

  /**
   * 方法功能描述：回写
   * <p>
   * <b>参数说明</b>
   * 
   * @param backVO <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-31 下午04:52:07
   */
  private void rewriteBackZ2(CtWriteBackForOrderVO[] backVO) {
    try {
      Z2CTServices.rewriteBackZ2(backVO);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 方法功能描述：删除回写
   * <p>
   * <b>参数说明</b>
   * 
   * @param originVOs <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-31 下午04:47:19
   */
  private void writebackWhenDelete(OrderVO[] originVOs) {
    Set<CtWriteBackForOrderVO> deleteSet = new HashSet<CtWriteBackForOrderVO>();
    for (OrderVO originVO : originVOs) {
      OrderItemVO[] itemVOs = originVO.getBVO();
      if (ArrayUtils.isEmpty(itemVOs)) {
        continue;
      }

      for (OrderItemVO itemVO : itemVOs) {
        this.processDeleteRow(deleteSet, itemVO);
      }
    }

    if (deleteSet.size() > 0) {
      this.rewriteBackZ2(deleteSet.toArray(new CtWriteBackForOrderVO[deleteSet
          .size()]));
    }
  }

  /**
   * 方法功能描述：新增回写
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-31 下午04:47:16
   */
  private void writebackWhenInsert(OrderVO[] vos) {
    Set<CtWriteBackForOrderVO> insertSet = new HashSet<CtWriteBackForOrderVO>();
    for (OrderVO vo : vos) {
      OrderItemVO[] itemVOs = vo.getBVO();
      if (ArrayUtils.isEmpty(itemVOs)) {
        continue;
      }

      for (OrderItemVO itemVO : itemVOs) {
        this.processInsertRow(insertSet, itemVO);
      }
    }

    if (insertSet.size() > 0) {
      this.rewriteBackZ2(insertSet.toArray(new CtWriteBackForOrderVO[insertSet
          .size()]));
    }
  }

  /**
   * 方法功能描述：更新回写
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   * @param originVOs <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-7-31 下午04:06:48
   */
  private void writebackWhenUpdate(OrderVO[] vos, OrderVO[] originVOs) {
    BillRowCompare tool = new BillRowCompare(vos, originVOs);
    List<ISuperVO> insertList = tool.getInsertList();
    List<ISuperVO> deleteList = tool.getDeleteList();
    List<ISuperVO> updateList = tool.getUpdateList();
    List<ISuperVO> updateOriginList = tool.getUpdateOriginList();

    Set<CtWriteBackForOrderVO> updateSet = new HashSet<CtWriteBackForOrderVO>();

    if (insertList.size() > 0) {
      for (ISuperVO vo : insertList) {
        this.processInsertRow(updateSet, (OrderItemVO) vo);
      }
    }

    if (deleteList.size() > 0) {
      for (ISuperVO vo : deleteList) {
        this.processDeleteRow(updateSet, (OrderItemVO) vo);
      }
    }

    if (updateList.size() > 0) {
      int size = updateList.size();
      for (int i = 0; i < size; i++) {
        this.processUpdateRow(updateSet, (OrderItemVO) updateList.get(i),
            (OrderItemVO) updateOriginList.get(i));
      }
    }

    if (updateSet.size() > 0) {
      this.rewriteBackZ2(updateSet.toArray(new CtWriteBackForOrderVO[updateSet
          .size()]));
    }
  }
}
