/**
 * $文件说明$
 * 
 * @author wuxla
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-7-27 下午07:23:34
 */
package nc.bs.pu.m422x.maintain.rule.save;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.vo.pu.m422x.entity.StoreReqAppHeaderVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppVO;
import nc.vo.pu.pub.rule.NumAndOrigmnySum;
import nc.vo.pu.pub.util.ApproveFlowUtil;
import nc.vo.pu.pub.util.BillHelper;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.util.AuditInfoUtils;
import nc.vo.scmpub.res.billtype.POBillType;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @description
 *            物资需求申请单保存时，填充必要信息
 * @scene
 *       物资需求申请单保存
 * @param
 * 
 *
 * @since 6.0
 * @version 2010-7-27 下午07:23:34
 * @author wuxla
 */
public class NecessaryFillRule implements IRule<StoreReqAppVO> {

  /**
   * 父类方法重写
   * 
   * @see nc.impl.pubapp.pattern.rule.IRule#process(E[])
   */
  @Override
  public void process(StoreReqAppVO[] vos) {
    this.setInfo(vos);
    for (StoreReqAppVO vo : vos) {
      this.setTrantypeCode(vo);
      if (vo.getHVO().getStatus() == VOStatus.NEW) {
        this.setDefaultValueForNew(vo);
      }
      else {
        this.setDefaultValueForUpdate(vo);
      }
      // 计算价税合计
      this.setTotalNumAndOrigmny(vo);
    }
  }

  private void setDefaultValueForNew(StoreReqAppVO vo) {
    // BSContext ctx = BSContext.getInstance();

    StoreReqAppHeaderVO header = vo.getHVO();
    UFDate dbilldate = header.getDbilldate();

    // // 审计信息
    // header.setCreator(ctx.getUserID());
    // header.setCreationtime(ctx.getTime());

    StoreReqAppItemVO[] items = vo.getBVO();
    for (StoreReqAppItemVO item : items) {
      // 单据行的激活状态
      item.setBclose(UFBoolean.FALSE);
      // 冗余字段的处理
      item.setDbilldate(dbilldate);
      item.setPk_group(header.getPk_group());
      item.setPk_org(header.getPk_org());
      item.setPk_org_v(header.getPk_org_v());
    }
  }

  private void setDefaultValueForUpdate(StoreReqAppVO vo) {
    // BSContext ctx = BSContext.getInstance();

    StoreReqAppHeaderVO header = vo.getHVO();
    UFDate dbilldate = header.getDbilldate();

    // // 审计信息
    // header.setModifier(ctx.getUserID());
    // header.setModifiedtime(ctx.getTime());

    StoreReqAppItemVO[] items = vo.getBVO();
    for (StoreReqAppItemVO item : items) {
      if (item.getBclose() == null) {
        // 单据行的激活状态
        item.setBclose(UFBoolean.FALSE);
      }
      // 冗余字段的处理
      item.setDbilldate(dbilldate);
      item.setPk_group(header.getPk_group());
      item.setPk_org(header.getPk_org());
      item.setPk_org_v(header.getPk_org_v());
    }
  }

  private void setInfo(StoreReqAppVO[] vos) {
    if (vos[0].getHVO().getStatus() == VOStatus.NEW) {
      AuditInfoUtils.setAddAuditInfo(vos);
      ApproveFlowUtil.setBillMakeInfo(vos);
    }
    else {
      AuditInfoUtils.setUpdateAuditInfo(vos);
    }
  }

  private void setTotalNumAndOrigmny(StoreReqAppVO vo) {
    BillHelper<StoreReqAppVO> bill = new BillHelper<StoreReqAppVO>(vo);
    NumAndOrigmnySum sum = new NumAndOrigmnySum(bill);
    sum.setItemMnyField(StoreReqAppItemVO.NTAXMNY);
    sum.setBlargessField(null);
    sum.sum();
  }

  /**
   * 单据类型补充
   * 
   * @param vo
   */
  private void setTrantypeCode(StoreReqAppVO vo) {
    if (StringUtils.isEmpty(vo.getHVO().getVtrantypecode())) {
      vo.getHVO().setVtrantypecode(POBillType.MRBill.getCode());
    }
  }

}
