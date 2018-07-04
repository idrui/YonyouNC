package nc.ui.pu.m23.action.processor;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pubapp.uif2app.actions.intf.ICopyActionProcessor;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pu.m23.rule.transfer.AfterTransferUtil;
import nc.vo.pu.m23.rule.transfer.NumAndPrice;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.uif2.LoginContext;

/**
 * 基于到货单的退货复制处理器。
 * 
 * @since 6.0
 * @version 2012-8-8 下午03:23:48
 * @author lixyp
 */
public class BackFromArriveCopyProcessor<E extends ArriveVO> implements
    ICopyActionProcessor<E> {

  private boolean canRelationCalc = true;

  private List<ArriveItemVO> relationCalcList = null;

  @Override
  public void processVOAfterCopy(E billVO, LoginContext context) {
    this.canRelationCalc = true;

    // 由于在表体设置值的时候，需要表头主键，而该值在处理表头时会被清空，所以先处理表体，再处理表头。
    this.setItemValue(billVO);
    this.setHeadValue(billVO, context);

    // 此处主要为解决一次转单数量被反算的问题，因为基于来源到货单退货没有走在vo交换处理类的尾差处理（退货单来源记的订单，在这里也不好处理尾差。）
    // 所以造成在AfterTransferUtil中联动计算会出现尾差。
    // 如果一个vo中存在某些行一次转单（有可能同时某些行不是一次转单）。一次转单的行直接取反，不是的行单独联动计算。
    // 如果一个vo中全都不是一次转单，则可以走AfterTransferUtil的联动计算。
    if (!this.canRelationCalc) {
      ArriveVO vo = (ArriveVO) billVO.clone();
      vo.setBVO(this.getRelationCalcList().toArray(
          new ArriveItemVO[this.getRelationCalcList().size()]));

      new NumAndPrice(new ArriveVO[] {
        vo
      }).dealNumAndPrice();
    }

    // 重用vo交换后的处理，对数量等字段进行赋值。
    new AfterTransferUtil(new ArriveVO[] {
      billVO
    }, this.canRelationCalc).process();

    this.relationCalcList = null;
  }

  private List<ArriveItemVO> getRelationCalcList() {
    if (this.relationCalcList == null) {
      this.relationCalcList = new ArrayList<ArriveItemVO>();
    }
    return this.relationCalcList;
  }

  /**
   * 设置表头字段值。
   * 
   * @param vo 到货单VO
   * @param context 上下文
   */
  private void setHeadValue(ArriveVO vo, LoginContext context) {
    ArriveHeaderVO headerVo = vo.getHVO();
    UFDate busidate = AppContext.getInstance().getBusiDate();

    // 到货单主键
    headerVo.setPk_arriveorder(null);
    // 表头时间戳
    headerVo.setTs(null);
    // 到货单单据号
    headerVo.setVbillcode(null);
    // 到货单状态
    headerVo.setFbillstatus((Integer) POEnumBillStatus.FREE.value());
    // 单据日期，取业务日期
    headerVo.setDbilldate(busidate);
    // 退货标志
    headerVo.setBisback(UFBoolean.TRUE);
    // 创建时间
    headerVo.setCreationtime(null);
    // 制单人，取当前用户
    headerVo.setBillmaker(context.getPk_loginUser());
    // 制单日期
    headerVo.setDmakedate(null);
    // 审批日期
    headerVo.setTaudittime(null);
    // 审批人
    headerVo.setApprover(null);
    // 修改时间
    headerVo.setModifiedtime(null);
    // 最后修改人
    headerVo.setModifier(null);
    // 创建人？？
    headerVo.setCreator(null);
    // 总数量
    headerVo.setNtotalastnum(null);
    // 本币价税合计
    headerVo.setNtotaltaxmny(null);
  }

  /**
   * 设置表体字段值。
   * 
   * @param vo 到货单VO
   */
  private void setItemValue(ArriveVO vo) {
    ArriveItemVO[] itemVos = vo.getBVO();
    List<ArriveItemVO> itemList = new ArrayList<ArriveItemVO>();

    for (ArriveItemVO itemVo : itemVos) {
      // 累计退货主数量如果为0或空，需单独将数量和金额取反，并不走联动计算，否则可能会反算数量造成尾差。
      // 应到数量在AfterTransferUtil中会处理成数量一样，在这里不用处理。
      if (MathTool.isZero(itemVo.getNaccumbacknum())
          || itemVo.getNaccumbacknum() == null) {
        this.canRelationCalc = false;

        itemVo.setNnum(MathTool.oppose(itemVo.getNnum()));
        itemVo.setNastnum(MathTool.oppose(itemVo.getNastnum()));
        itemVo.setNplannum(MathTool.oppose(itemVo.getNplannum()));
        itemVo.setNplanastnum(MathTool.oppose(itemVo.getNplanastnum()));
        itemVo.setNorigmny(MathTool.oppose(itemVo.getNorigmny()));
        itemVo.setNorigtaxmny(MathTool.oppose(itemVo.getNorigtaxmny()));
        itemVo.setNtax(MathTool.oppose(itemVo.getNtax()));
        itemVo.setNtaxmny(MathTool.oppose(itemVo.getNtaxmny()));
        itemVo.setNmny(MathTool.oppose(itemVo.getNmny()));
        itemVo.setNwastastnum(MathTool.oppose(itemVo.getNwastastnum()));
        itemVo.setNwastnum(MathTool.oppose(itemVo.getNwastnum()));
      }
      else {
        // 主数量 = 主数量 - 累计退货主数量
        UFDouble nnum =
            MathTool.oppose(MathTool.sub(itemVo.getNnum(),
                itemVo.getNaccumbacknum()));
        if (!MathTool.isZero(itemVo.getNnum())) {
          // 此种情况需要单独处理联动计算。
          this.getRelationCalcList().add(itemVo);

          // 设置主数量
          itemVo.setNnum(nnum);
          // 设置应到主数量
          itemVo.setNplannum(nnum);
          // 设置可入库主数量。
          itemVo.setNcanstorenum(nnum);
        }
      }

      if (!MathTool.isZero(itemVo.getNnum())) {
        itemList.add(itemVo);

        // 清空标志字段
        itemVo.setBletgostate(null);
        itemVo.setBtransasset(null);

        // 清空累计数量
        itemVo.setNaccumbacknum(null);
        itemVo.setNaccumchecknum(null);
        itemVo.setNaccumletgoinnum(null);
        itemVo.setNaccumreplnum(null);
        itemVo.setNaccumstorenum(null);

        // 来源到货单ID
        itemVo.setCsourcearriveid(vo.getHVO().getPk_arriveorder());
        // 来源到货单明细ID
        itemVo.setCsourcearrivebid(itemVo.getPk_arriveorder_b());
        // 来源TS
        itemVo.setSourcets(vo.getHVO().getTs());
        // 来源行TS
        itemVo.setSourcebts(itemVo.getTs());

        // 清空表体ID
        itemVo.setPk_arriveorder_b(null);
        // 清空TS
        itemVo.setTs(null);
      }
    }

    vo.setBVO(itemList.toArray(new ArriveItemVO[itemList.size()]));
  }
}
