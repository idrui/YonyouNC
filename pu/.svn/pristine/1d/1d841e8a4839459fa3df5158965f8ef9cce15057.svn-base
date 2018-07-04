package nc.ui.pu.m21.view;

import nc.ui.pu.uif2.PUTemplateContainer;
import nc.ui.pu.uif2.PUUif2Utils;
import nc.vo.pu.pub.enumeration.PuNodeCode;
import nc.vo.sm.funcreg.FuncRegisterVO;

import org.apache.commons.lang.StringUtils;

/**
 * 采购订单维护到货计划界面的单据模板加载器
 * 
 * @since 6.0
 * @version 2011-12-9 上午8:48:32
 * @author zhaoyha
 */
public class RevPlanTemplateContainer extends PUTemplateContainer {

  @Override
  public void load() {
    super.load();
    // 这里将采购订单（外围界面）的节点号写入，为了支持依据订单节点分配到货计划模板
    // 不能直接写死采购订单节点号40040400，因为采购订单节点可能是交易类型发布的节点
    this.usePoFuncode();
  }

  private void usePoFuncode() {
    String fc = PuNodeCode.n40040400.code();// 如果为空，则取采购订单节点号
    FuncRegisterVO frvo = PUUif2Utils.getFuncRegisterVO(this.getContext());
    if (null != frvo) {
      String pocode =
          (String) PUUif2Utils.getUsrObjFromFRVO(frvo,
              PUUif2Utils.PUFRVOAttName.sourrounding_funcode.name());
      fc = StringUtils.isBlank(pocode) ? fc : pocode;
    }
    this.setFuncode(fc);
  }

}
