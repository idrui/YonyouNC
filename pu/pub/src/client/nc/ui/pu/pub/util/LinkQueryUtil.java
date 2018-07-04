package nc.ui.pu.pub.util;

import nc.bs.logging.Logger;
import nc.bs.pf.pub.PfDataCache;
import nc.desktop.ui.Workbench;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.ui.pf.change.PfUtilUITools;
import nc.ui.pub.linkoperate.ILinkQueryData;
import nc.ui.pub.msg.PfLinkData;
import nc.ui.uap.sf.SFClientUtil;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.pub.pf.workflow.IPFActionName;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>联查单据的工具类
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-10-2 上午12:12:55
 */
public class LinkQueryUtil {
  /**
   * 方法功能描述：
   * <p>
   * <b>参数说明</b>
   * 
   * @param billType
   * @param billId
   * @param org
   *          <p>
   * @since 6.0
   * @author duy
   * @time 2010-10-2 上午12:15:34
   */
  public void linkQuery(final String billType, final String billId,
      final String org) {
    BilltypeVO resultVO = PfDataCache.getBillType(billType);

    PfLinkData pflink = new PfLinkData();
    pflink.setBillID(billId);
    pflink.setBillType(billType);
    pflink.setUserObject(IPFActionName.APPROVE);//
    String customNode =
        PfUtilUITools.findCustomNodeOfBilltype(resultVO, pflink);
    Logger.debug("::查找单据类型注册的节点 findCustomNodeOfBilltype=" + customNode);
    String nodecode = customNode;
    if (StringUtil.isEmptyWithTrim(customNode)) {
      nodecode = resultVO.getNodecode();
    }

    Workbench bench = WorkbenchEnvironment.getInstance().getWorkbench();
    // 回到制单节点，修单任务
    // 优先使用插件返回的节点号，如果没有，则使用bd_billtype.nodecode
    if (StringUtil.isEmptyWithTrim(nodecode)) {
      ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
          .getNCLangRes().getStrByID("4004000_0", "04004000-0050")/*
                                                                   * @res
                                                                   * "单据类型没有注册nodecode字段，请修改！"
                                                                   */);
    }

    // --END
    SFClientUtil.openLinkedQueryDialog(nodecode, bench, new ILinkQueryData() {
      @Override
      public String getBillID() {
        return billId;
      }

      @Override
      public String getBillType() {
        return billType;
      }

      @Override
      public String getPkOrg() {
        return org;
      }

      @Override
      public Object getUserObject() {
        return null;
      }
    });
  }
}
