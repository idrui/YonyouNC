package nc.ui.pu.m25.linkquery;

import java.awt.Component;
import java.awt.Dimension;

import nc.desktop.ui.WorkbenchEnvironment;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.ui.pub.linkoperate.ILinkQueryData;
import nc.ui.pub.linkoperate.ILinkType;
import nc.vo.pu.pub.enumeration.PuNodeCode;
import nc.vo.sm.funcreg.FuncRegisterVO;

/**
 * @since 6.0
 * @version 2011-7-1 下午02:24:11
 * @author 田锋涛
 */

public class InvoiceLinkQueryUtil {

  /**
   * 功能节点相关
   * 
   * @param srcBilltype
   * @param srcBillID
   * @return
   */
  public static FuncletInitData getFuncletInitData(final String srcBilltype,
      final String srcBillID) {
    FuncletInitData initData = new FuncletInitData();
    initData.setInitType(ILinkType.LINK_TYPE_QUERY);
    initData.setInitData(new ILinkQueryData() {

      @Override
      public String getBillID() {
        return srcBillID;
      }

      @Override
      public String getBillType() {
        return srcBilltype;
      }

      @Override
      public String getPkOrg() {
        return null;
      }

      @Override
      public Object getUserObject() {
        return null;
      }

    });

    return initData;

  }

  public static FuncRegisterVO getFuncRegisterVO() {
    WorkbenchEnvironment instance = WorkbenchEnvironment.getInstance();
    FuncRegisterVO funvo =
        instance.getFuncRegisterVO(PuNodeCode.n40041000.code());// 节点编码
    return funvo;
  }

  public static void openFuncNodeForceModalDialog(Component parent,
      FuncletInitData initData) {
    FuncletWindowLauncher.openFuncNodeForceModalDialog(parent,
        InvoiceLinkQueryUtil.getFuncRegisterVO(), initData, null, true,
        new Dimension(800, 600), null);
  }

}
