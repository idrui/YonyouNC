/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-9-9 下午04:10:38
 */
package nc.ui.pu.m4t.action.processor;

import java.util.ArrayList;
import java.util.List;

import nc.itf.pu.pub.IPURemoteCallCombinator;
import nc.ui.pu.pub.util.BusitypeSetter;
import nc.ui.pubapp.uif2app.actions.intf.ICopyActionProcessor;
import nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeFuncUtils;
import nc.vo.pu.m4t.entity.InitialEstHeaderVO;
import nc.vo.pu.m4t.entity.InitialEstItemVO;
import nc.vo.pu.m4t.entity.InitialEstVO;
import nc.vo.pu.m4t.enumeration.InitialEstStatus;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmpub.res.billtype.POBillType;
import nc.vo.uif2.LoginContext;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>期初暂估单复制处理类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-9-9 下午04:10:38
 */
public class CopyActionProcessor<E extends InitialEstVO> implements
    ICopyActionProcessor<E> {

  @Override
  public void processVOAfterCopy(InitialEstVO billVO, LoginContext context) {
    this.batchProc(billVO, context);
    this.setHeadValue(billVO, context);
    this.setItemValue(billVO);
  }

  private void batchProc(InitialEstVO vo, LoginContext context) {
    List<IPURemoteCallCombinator> rccRuleLst =
        new ArrayList<IPURemoteCallCombinator>();
    // 注册执行远程调用规则－交易类型及业务流程处理
    this.register_BizRule(rccRuleLst, vo, context);
    // 执行远程调用合并规则
    for (IPURemoteCallCombinator rccRule : rccRuleLst) {
      if (null != rccRule) {
        rccRule.process();
      }
    }
  }

  private void copyTranstype(InitialEstVO vo, String transtype,
      String pk_trantype) {
    // 业务流程，先清空，后续会补充
    vo.getHeader().setPk_busitype(null);
    if (StringUtils.isBlank(transtype)) {
      // 订单类型（交易类型）
      vo.getHeader().setVtrantypecode(null);
      // 订单类型
      vo.getHeader().setCtrantypeid(null);
    }
    else {
      // 订单类型（交易类型）
      vo.getHeader().setVtrantypecode(transtype);
      // 订单类型
      vo.getHeader().setCtrantypeid(pk_trantype);
    }
  }

  private void register_BizRule(List<IPURemoteCallCombinator> rccRuleLst,
      InitialEstVO vo, LoginContext context) {
    // 处理交易类型
    String transtype = TrantypeFuncUtils.getTrantype(context);
    String pk_trantype = TrantypeFuncUtils.getTrantypePk(context);
    this.copyTranstype(vo, transtype, pk_trantype);
    new BusitypeSetter(POBillType.InitEstimate,
        new BillHelper<InitialEstVO>(vo), context).copySet(rccRuleLst);
  }

  /**
   * 方法功能描述：设置某一表体的值
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVO <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-9 下午04:23:57
   */
  private void setDelaultItemValue(InitialEstItemVO itemVO) {
    // 表体主键
    itemVO.setPk_initialest_b(null);
    // 表头主键
    itemVO.setPk_initialest(null);
    // ts
    itemVO.setTs(null);

    // 来源
    itemVO.setCsourcebid(null);
    itemVO.setCsourceid(null);
    itemVO.setCsourcetypecode(null);
    itemVO.setVsourcecode(null);
    itemVO.setVsourcerowno(null);
    itemVO.setVsourcetrantype(null);
    itemVO.setSourcebts(null);
    itemVO.setSourcets(null);
    // 来源订单
    itemVO.setPk_order(null);
    itemVO.setPk_order_b(null);
    itemVO.setCorderrowno(null);
    itemVO.setVordercode(null);
    itemVO.setVordertrantype(null);

    // 累计数量
    itemVO.setNaccinvoicenum(null);
    itemVO.setNaccsettlenum(null);
    itemVO.setNaccwashmny(null);

    // 暂估应付标志
    itemVO.setBestimateap(UFBoolean.FALSE);
    // 特征码
//    itemVO.setCffileid(null);
  }

  /**
   * 方法功能描述：设置表头值
   * <p>
   * <b>参数说明</b>
   * 
   * @param billVO
   * @param context <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-9 下午04:12:47
   */
  private void setHeadValue(InitialEstVO billVO, LoginContext context) {
    if (null == billVO) {
      return;
    }

    InitialEstHeaderVO headVO = billVO.getHeader();
    if (null == headVO) {
      return;
    }

    // 主键
    headVO.setPk_initialest(null);
    // 申请单号
    headVO.setVbillcode(null);
    // 单据状态
    headVO.setFbillstatus((Integer) InitialEstStatus.FEE.value());
    // 创建人
    headVO.setCreator(context.getPk_loginUser());
    // 制单人
    headVO.setBillmaker(context.getPk_loginUser());
    // 制单时间
    headVO.setCreationtime(null);
    // 最后修改人
    headVO.setModifier(context.getPk_loginUser());
    // 最后修改时间
    headVO.setModifiedtime(null);
    // 审批人
    headVO.setApprover(null);
    // 审批时间
    headVO.setTaudittime(null);
    headVO.setTs(null);
    // 制单日期
    headVO.setDmakedate(null);
  }

  /**
   * 方法功能描述：设置表体值
   * <p>
   * <b>参数说明</b>
   * 
   * @param billVO <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-9-9 下午04:12:50
   */
  private void setItemValue(InitialEstVO billVO) {
    if (null == billVO) {
      return;
    }

    InitialEstItemVO[] itemVOs = billVO.getItems();
    if (ArrayUtils.isEmpty(itemVOs)) {
      return;
    }

    for (InitialEstItemVO itemVO : itemVOs) {
      // 去掉影响中心标志
      itemVO.setBaffectpccost(null);
      this.setDelaultItemValue(itemVO);
    }
  }
}
