/**
 * $�ļ�˵��$
 * 
 * @author tianft
 * @version 6.0
 * @see
 * @since 6.0
 * @time 2010-10-20 ����08:59:40
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
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>��ӡǰ��ӡ���ݵĹ��˼����ȵȴ���
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author tianft
 * @time 2010-10-20 ����08:59:40
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
    // ת��Ϊ����vo
    OrderVO[] vos = (OrderVO[]) ArrayUtil.convertArrayType(datas);
    // ���ȴ���
    this.processScale(vos);
    return vos;
  }

  /**
   * ����������������ȡ�Ƕ���Ŀ��Դ�ӡ��vo
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   *          <p>
   * @since 6.0
   * @author guizhw
   * @time 2011-7-22 ���� 11:13:24
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
   *          Ҫ���õ� model
   */
  public void setModel(AbstractAppModel model) {
    this.model = model;
  }

  /**
   * ���෽����д
   * 
   * @see nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction.IDataSplit#splitData(java.lang.Object[])
   */
  @Override
  public Object[] splitData(Object[] datas) {
    // ת��Ϊ����vo
    OrderVO[] vos = (OrderVO[]) ArrayUtil.convertArrayType(datas);
    // ������в��ܴ�ӡ����������
    OrderVO[] printvos = this.processPrintData(vos);
    return printvos;
  }

  /**
   * ��������������������
   * <p>
   * <b>����˵��</b>
   * 
   * @param vos
   *          <p>
   * @since 6.0
   * @author tianft
   * @time 2010-11-3 ����05:12:24
   */
  private void processScale(OrderVO[] vos) {
    BillVOScaleProcessor scale =
        new BillVOScaleProcessor(this.getModel().getContext().getPk_group(),
            vos);
    // ȫ�ֱ�λ�ҽ��
    String[] globalmnykeys = new String[] {
      OrderItemVO.NGLOBALMNY, OrderItemVO.NGLOBALTAXMNY
    };
    // ���ű�λ�ҽ��
    String[] groupmnykeys = new String[] {
      OrderItemVO.NGROUPMNY, OrderItemVO.NGROUPTAXMNY
    };
    // ������
    String[] changeRates = new String[] {
      OrderItemVO.VCHANGERATE, OrderItemVO.VQTUNITRATE
    };
    // ���ҽ��
    String[] mnykeys =
        new String[] {
          OrderItemVO.NMNY, OrderItemVO.NTAXMNY, OrderItemVO.NTAX,
          OrderItemVO.NACCCANCELINVMNY, OrderItemVO.NACCUMINVOICEMNY,
          OrderItemVO.NFEEMNY, OrderItemVO.NCALTAXMNY, OrderItemVO.NNOSUBTAX,
          OrderItemVO.NCALCOSTMNY
        };
    // ҵ��λ����
    String[] assistNumkeys = new String[] {
        OrderItemVO.NASTNUM
      };
    // ������
    String[] numkeys =
        new String[] {
          OrderItemVO.NACCUMARRVNUM, OrderItemVO.NACCUMDEVNUM,
          OrderItemVO.NACCUMINVOICENUM, OrderItemVO.NACCUMRPNUM,
          OrderItemVO.NACCUMSTORENUM, OrderItemVO.NACCUMWASTNUM,
          OrderItemVO.NBACKARRVNUM, OrderItemVO.NBACKSTORENUM,
          OrderItemVO.NCANARRIVENUM, OrderItemVO.NCANINVOICENUM,
          OrderItemVO.NCONFIRMNUM, OrderItemVO.NNUM, OrderItemVO.NCANINNUM
        };
    // ���Ҽ۸�
    String[] CurrPricekeys =
        new String[] {
          OrderItemVO.NNETPRICE, OrderItemVO.NQTNETPRICE,
          OrderItemVO.NQTTAXNETPRICE, OrderItemVO.NTAXNETPRICE,
          OrderItemVO.NTAXPRICE, OrderItemVO.NPRICE, OrderItemVO.NQTPRICE,
          OrderItemVO.NQTTAXPRICE
        };
    // ԭ�Ҽ۸�
    String[] OcurrPricekeys =
        new String[] {
          OrderItemVO.NORIGNETPRICE, OrderItemVO.NORIGPRICE,
          OrderItemVO.NORIGTAXNETPRICE, OrderItemVO.NORIGTAXPRICE,
          OrderItemVO.NQTORIGNETPRICE, OrderItemVO.NQTORIGPRICE,
          OrderItemVO.NQTORIGTAXNETPRC, OrderItemVO.NQTORIGTAXPRICE
        };
    // ԭ�ҽ��  
    // �ɿ�Ʊ����Ƕ�������Ʊת��ģ����¼��ֶΣ�ֵΪ��˰����*�ɿ�Ʊ������
    String[] orgmnykeys = new String[] {
      OrderItemVO.NORIGMNY, OrderItemVO.NORIGTAXMNY, OrderItemVO.NNOPAYORGMNY, 
      PUPubMetaNameConst.NCANINVOICEMNY
    };
    
    // ȫ�ֱ�λ�ҽ���
    scale.setGlobalLocMnyCtlInfo(globalmnykeys, PosEnum.body, null);
    // ���ű�λ�ҽ���
    scale.setGroupLocMnyCtlInfo(groupmnykeys, PosEnum.body, null);
    // �����ʾ���
    scale.setHslCtlInfo(changeRates, PosEnum.body, null);
    // ҵ��λ��������
    scale.setNumCtlInfo(assistNumkeys, PosEnum.body, null, OrderItemVO.CASTUNITID,
        PosEnum.body, null);
    // ����λ��������
    scale.setNumCtlInfo(numkeys, PosEnum.body, null, OrderItemVO.CUNITID, PosEnum.body,
        null);
    // ���ҽ���
    scale.setMnyCtlInfo(mnykeys, PosEnum.body, null, OrderItemVO.CCURRENCYID,
        PosEnum.body, null);
    // ���ҵ��۾���
    scale.setPriceCtlInfo(CurrPricekeys, PosEnum.body, null,
        OrderItemVO.CCURRENCYID, PosEnum.body, null);
    // ԭ�ҵ��۾���
    scale.setPriceCtlInfo(OcurrPricekeys, PosEnum.body, null,
        OrderHeaderVO.CORIGCURRENCYID, PosEnum.head, null);
    // ԭ�ҽ���
    scale.setMnyCtlInfo(orgmnykeys, PosEnum.body, null,
        OrderHeaderVO.CORIGCURRENCYID, PosEnum.head, null);
    
    scale.process();
  }

}
