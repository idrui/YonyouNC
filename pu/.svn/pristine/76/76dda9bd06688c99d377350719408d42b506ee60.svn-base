package nc.bs.pu.m422x.rewrite;

import java.util.ArrayList;
import java.util.List;

import nc.bs.pu.m422x.plugin.StoreReqAppPluginPoint;
import nc.bs.pu.m422x.rewrite.rule.AccOrderNumCalcRule;
import nc.bs.pu.m422x.rewrite.rule.AutoOutCloseRule;
import nc.bs.pu.m422x.rewrite.rule.StoreReqWriteBackTolerRule;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.data.view.ViewUpdate;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.bd.material.MaterialVO;
import nc.vo.pu.m422x.entity.StoreReqAppItemVO;
import nc.vo.pu.m422x.entity.StoreReqAppViewVO;
import nc.vo.pu.m422x.entity.WriteBack422XVO;
import nc.vo.pu.pub.constant.PUParaValue.ctrltype;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.scmf.pub.util.SCMSysParamUtil;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>材料出库单回写BP
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author gaogr
 * @time 2010-9-21 上午09:10:11
 */
public class ReWrite422xFor4dBP {

  /**
   * 方法功能描述：回写数量。
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos <p>
   * @since 6.0
   * @author gaogr
   * @time 2010-9-21 上午09:10:22
   */
  public void backWriteNum(WriteBack422XVO[] vos) {

    String[] bids = this.getBidsFromWBVos(vos);
    // 查询更新前的请购单数据
    StoreReqAppViewVO[] views =
        new ViewQuery<StoreReqAppViewVO>(StoreReqAppViewVO.class).query(bids);
    AroundProcesser<StoreReqAppViewVO> processer =
        new AroundProcesser<StoreReqAppViewVO>(
            StoreReqAppPluginPoint.WRITEBACK_4D);

    this.addRule(processer, vos, views);
    processer.before(views);
    String[] wbNames = new String[] {
      StoreReqAppItemVO.NACCUOUTNUM
    };
    ViewUpdate<StoreReqAppViewVO> bo = new ViewUpdate<StoreReqAppViewVO>();
    views = bo.update(views, StoreReqAppItemVO.class, wbNames);
    processer.after(views);
  }

  private void addRule(AroundProcesser<StoreReqAppViewVO> processer,
      WriteBack422XVO[] vos, StoreReqAppViewVO[] views) {
    processer.addBeforeRule(new AccOrderNumCalcRule(vos));
    // 因为参数就只有不保存和不控制，所以直接赋值为false
    // 如果以后改参数，需要将UFBoolean.FALSE改为UFBoolean.valueof(bIsUserConfirm)
    processer.addAfterRule(new StoreReqWriteBackTolerRule(
        MaterialVO.OUTTOLERANCE,
        SCMSysParamUtil.SCM09_NOT_SAVE.equals(SCMSysParamUtil.getSCM09(views[0]
            .getPk_org())) ? ctrltype.not_save : ctrltype.not_control,
        StoreReqAppItemVO.NACCUOUTNUM, UFBoolean.FALSE));
    // 关闭
    processer.addBeforeRule(new AutoOutCloseRule());
  }

  private String[] getBidsFromWBVos(WriteBack422XVO[] vos) {
    List<String> retVal = new ArrayList<String>();
    for (WriteBack422XVO vo : vos) {
      retVal.add(vo.getPk_storereq_b());
    }
    return retVal.toArray(new String[retVal.size()]);
  }

}
