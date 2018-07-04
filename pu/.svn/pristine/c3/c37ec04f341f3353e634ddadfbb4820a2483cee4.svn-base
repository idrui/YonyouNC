/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-19 上午10:22:13
 */
package nc.ui.pu.m422x.action.processor;

import nc.ui.pubapp.uif2app.actions.intf.ICopyActionProcessor;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.AppContext;
import nc.vo.uif2.LoginContext;

import org.apache.commons.lang.ArrayUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>复制处理
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author wuxla
 * @time 2010-7-19 上午10:22:13
 */
public class CopyActionProcessor<E extends StoreReqAppVO> implements
    ICopyActionProcessor<E> {

  @Override
  public void processVOAfterCopy(StoreReqAppVO billVO, LoginContext context) {
    this.setHeadValue(billVO, context);
    this.setItemValue(billVO);
  }

  /**
   * 方法功能描述：设置表体默认值
   * <p>
   * <b>参数说明</b>
   * 
   * @param itemVO <p>
   * @since 6.0
   * @author wuxla
   * @time 2010-8-7 下午03:59:40
   */
  private void setDelaultItemValue(StoreReqAppItemVO itemVO) {
    // 表体主键
    itemVO.setPk_storereq_b(null);
    // 表头主键
    itemVO.setPk_storereq(null);
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
    itemVO.setCfirstbid(null);
    itemVO.setCfirstid(null);
    itemVO.setCfirsttypecode(null);
    itemVO.setVfirstcode(null);
    itemVO.setVfirstrowno(null);
    itemVO.setVfirsttrantype(null);

    itemVO.setCsourcebid2(null);
    itemVO.setCsourceid2(null);
    itemVO.setCsourcetypecode2(null);
    itemVO.setVsourcecode2(null);
    itemVO.setVsourcerowno2(null);
    itemVO.setVsourcetrantype2(null);
    //itemVO.setSourcebts2(null);
    //itemVO.setSourcets2(null);
    itemVO.setCfirstbid2(null);
    itemVO.setCfirstid2(null);
    itemVO.setCfirsttypecode2(null);
    itemVO.setVfirstcode2(null);
    itemVO.setVfirstrowno2(null);
    itemVO.setVfirsttrantype2(null);
    
    //01 已平衡
    itemVO.setBendgather(null);
    //02 库存满足主数量
    itemVO.setNaccustornum(null);
    //03 转净需求主数量
    itemVO.setNnetnum(null);
    //10 汇总时间
    itemVO.setTgathertime(null);
    //11 汇总人
    itemVO.setCgatherpsnid(null);
    itemVO.setCgatherid(null);
    
    // 累计出库数量
    itemVO.setNaccuoutnum(null);
    // 累计申请出库主数量
    itemVO.setNaccuoutreqnum(null);
    // 累计请购主数量
    itemVO.setNaccumbuyreqnum(null);
    // 累计消减主数量
    itemVO.setNaccumminusnum(null);
    // 是否关闭
    itemVO.setBclose(UFBoolean.FALSE);
    // 需求日期
    UFDate busidate = AppContext.getInstance().getBusiDate().asLocalEnd();
    itemVO.setDreqdate(busidate);
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
   * @time 2010-8-7 下午03:32:13
   */
  private void setHeadValue(StoreReqAppVO billVO, LoginContext context) {
    if (null == billVO) {
      return;
    }

    StoreReqAppHeaderVO headVO = billVO.getHVO();
    if (null == headVO) {
      return;
    }

    // 主键
    headVO.setPk_storereq(null);
    // 申请单号
    headVO.setVbillcode(null);
    // 单据状态
    headVO.setFbillstatus((Integer) POEnumBillStatus.FREE.value());
    // 创建人
    headVO.setCreator(context.getPk_loginUser());
    // 制单人
    headVO.setBillmaker(context.getPk_loginUser());
    // 制单时间
    headVO.setCreationtime(null);
    // 最后修改人
    headVO.setModifier(null);
    // 最后修改时间
    headVO.setModifiedtime(null);
    // 审批人
    headVO.setApprover(null);
    // 审批时间
    headVO.setTaudittime(null);
    // 打印次数
    headVO.setIprintcount(Integer.valueOf(0));
    // 申请日期
    UFDate busidate = AppContext.getInstance().getBusiDate();
    headVO.setDbilldate(busidate);
    headVO.setTs(null);
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
   * @time 2010-8-7 下午03:32:17
   */
  private void setItemValue(StoreReqAppVO billVO) {
    if (null == billVO) {
      return;
    }

    StoreReqAppItemVO[] itemVOs = billVO.getBVO();
    if (ArrayUtils.isEmpty(itemVOs)) {
      return;
    }

    for (int i = 0; i < itemVOs.length; ++i) {
      this.setDelaultItemValue(itemVOs[i]);
    }
  }

}
