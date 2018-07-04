/**
 * $文件说明$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-20 上午08:59:40
 */
package nc.ui.pu.m21.action.processor;

import java.util.ArrayList;
import java.util.List;

import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction.IBeforePrintDataProcess;
import nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction.IDataSplit;
import nc.ui.uif2.model.AbstractAppModel;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.pub.util.ArrayUtil;
import nc.vo.pu.report.scale.PUPubMetaNameConst;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.scale.BillVOScaleProcessor;
import nc.vo.pubapp.scale.PosEnum;

/**
 * <p>
 * <b>本类主要完成以下功能：</b>
 * <ul>
 * <li>打印前打印数据的过滤及精度等处理
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-10-20 上午08:59:40
 */
public class OrderPrintProcessor implements IDataSplit, IBeforePrintDataProcess {

  private AbstractAppModel model = null;

  /**
   * @return model
   */
  public AbstractAppModel getModel() {
    return this.model;
  }

  @Override
  public Object[] processData(Object[] datas) {
    // 转化为订单vo
    OrderVO[] vos = (OrderVO[]) ArrayUtil.convertArrayType(datas);
    // 精度处理
    this.processScale(vos);
    return vos;
  }

  /**
   * 方法功能描述：获取非冻结的可以打印的vo
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          <p>
   * @since 6.0
   * @author guizhw
   * @time 2011-7-22 上午 11:13:24
   */
  public OrderVO[] processPrintData(OrderVO[] vos) {

    List<OrderVO> printlist = new ArrayList<OrderVO>();
    for (OrderVO vo : vos) {
      if (UFBoolean.FALSE.equals(vo.getHVO().getBfrozen())) {
        printlist.add(vo);
        continue;
      }
    }
    OrderVO[] printVOs = printlist.toArray(new OrderVO[printlist.size()]);
    return printVOs;
  }

  /**
   * @param model
   *          要设置的 model
   */
  public void setModel(AbstractAppModel model) {
    this.model = model;
  }

  /**
   * 父类方法重写
   * 
   * @see nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction.IDataSplit#splitData(java.lang.Object[])
   */
  @Override
  public Object[] splitData(Object[] datas) {
    // 转化为订单vo
    OrderVO[] vos = (OrderVO[]) ArrayUtil.convertArrayType(datas);
    // 冻结的行不能打印，过滤数据
    OrderVO[] printvos = this.processPrintData(vos);
    return printvos;
  }

  /**
   * 方法功能描述：处理精度
   * <p>
   * <b>参数说明</b>
   * 
   * @param vos
   *          <p>
   * @since 6.0
   * @author tianft
   * @time 2010-11-3 下午05:12:24
   */
  private void processScale(OrderVO[] vos) {
    BillVOScaleProcessor scale =
        new BillVOScaleProcessor(this.getModel().getContext().getPk_group(),
            vos);
    // 全局本位币金额
    String[] globalmnykeys = new String[] {
      OrderItemVO.NGLOBALMNY, OrderItemVO.NGLOBALTAXMNY
    };
    // 集团本位币金额
    String[] groupmnykeys = new String[] {
      OrderItemVO.NGROUPMNY, OrderItemVO.NGROUPTAXMNY
    };
    // 换算率
    String[] changeRates = new String[] {
      OrderItemVO.VCHANGERATE, OrderItemVO.VQTUNITRATE
    };
    // 本币金额
    String[] mnykeys =
        new String[] {
          OrderItemVO.NMNY, OrderItemVO.NTAXMNY, OrderItemVO.NTAX,
          OrderItemVO.NACCCANCELINVMNY, OrderItemVO.NACCUMINVOICEMNY,
          OrderItemVO.NFEEMNY, OrderItemVO.NCALTAXMNY, OrderItemVO.NNOSUBTAX,
          OrderItemVO.NCALCOSTMNY
        };
    // 业务单位数量
    String[] assistNumkeys = new String[] {
        OrderItemVO.NASTNUM
      };
    // 主数量
    String[] numkeys =
        new String[] {
          OrderItemVO.NACCUMARRVNUM, OrderItemVO.NACCUMDEVNUM,
          OrderItemVO.NACCUMINVOICENUM, OrderItemVO.NACCUMRPNUM,
          OrderItemVO.NACCUMSTORENUM, OrderItemVO.NACCUMWASTNUM,
          OrderItemVO.NBACKARRVNUM, OrderItemVO.NBACKSTORENUM,
          OrderItemVO.NCANARRIVENUM, OrderItemVO.NCANINVOICENUM,
          OrderItemVO.NCONFIRMNUM, OrderItemVO.NNUM, OrderItemVO.NCANINNUM
        };
    // 本币价格
    String[] CurrPricekeys =
        new String[] {
          OrderItemVO.NNETPRICE, OrderItemVO.NQTNETPRICE,
          OrderItemVO.NQTTAXNETPRICE, OrderItemVO.NTAXNETPRICE,
          OrderItemVO.NTAXPRICE, OrderItemVO.NPRICE, OrderItemVO.NQTPRICE,
          OrderItemVO.NQTTAXPRICE
        };
    // 原币价格
    String[] OcurrPricekeys =
        new String[] {
          OrderItemVO.NORIGNETPRICE, OrderItemVO.NORIGPRICE,
          OrderItemVO.NORIGTAXNETPRICE, OrderItemVO.NORIGTAXPRICE,
          OrderItemVO.NQTORIGNETPRICE, OrderItemVO.NQTORIGPRICE,
          OrderItemVO.NQTORIGTAXNETPRC, OrderItemVO.NQTORIGTAXPRICE
        };
    // 原币金额  
    // 可开票金额是订单到发票转单模板的新加字段，值为含税单价*可开票主数量
    String[] orgmnykeys = new String[] {
      OrderItemVO.NORIGMNY, OrderItemVO.NORIGTAXMNY, OrderItemVO.NNOPAYORGMNY, 
      PUPubMetaNameConst.NCANINVOICEMNY
    };
    
    // 全局本位币金额精度
    scale.setGlobalLocMnyCtlInfo(globalmnykeys, PosEnum.body, null);
    // 集团本位币金额精度
    scale.setGroupLocMnyCtlInfo(groupmnykeys, PosEnum.body, null);
    // 换算率精度
    scale.setHslCtlInfo(changeRates, PosEnum.body, null);
    // 业务单位数量精度
    scale.setNumCtlInfo(assistNumkeys, PosEnum.body, null, OrderItemVO.CASTUNITID,
        PosEnum.body, null);
    // 主单位数量精度
    scale.setNumCtlInfo(numkeys, PosEnum.body, null, OrderItemVO.CUNITID, PosEnum.body,
        null);
    // 本币金额精度
    scale.setMnyCtlInfo(mnykeys, PosEnum.body, null, OrderItemVO.CCURRENCYID,
        PosEnum.body, null);
    // 本币单价精度
    scale.setPriceCtlInfo(CurrPricekeys, PosEnum.body, null,
        OrderItemVO.CCURRENCYID, PosEnum.body, null);
    // 原币单价精度
    scale.setPriceCtlInfo(OcurrPricekeys, PosEnum.body, null,
        OrderHeaderVO.CORIGCURRENCYID, PosEnum.head, null);
    // 原币金额精度
    scale.setMnyCtlInfo(orgmnykeys, PosEnum.body, null,
        OrderHeaderVO.CORIGCURRENCYID, PosEnum.head, null);
    
    scale.process();
  }

}
