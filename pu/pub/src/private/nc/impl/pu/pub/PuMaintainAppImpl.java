package nc.impl.pu.pub;

import nc.bs.scmpub.page.ApproveBillFilter;
import nc.bs.scmpub.page.BillPageLazyQuery;
import nc.bs.scmpub.page.IBillFilter;
import nc.bs.scmpub.tool.SchemeAppendCondition;
import nc.bs.scmpub.tool.SchemeFixCondition;
import nc.itf.pu.pub.IPuMaintainApp;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.pu.pub.enumeration.POEnumBillStatus;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.scmpub.page.PageQueryVO;
import nc.vo.scmpub.res.billtype.IBillType;
import nc.vo.to.pub.TOQueryApproveUtil;

public class PuMaintainAppImpl<E extends IBill> implements IPuMaintainApp<E> {

  @Override
  public PageQueryVO queryPuApp(IQueryScheme scheme, Class<E> clazz,
      String bislatest, String status, String villcode, IBillType billtype)
      throws BusinessException {

    // ���Ӷ���Ĺ̶���ѯ��������ѯ���°汾�ĵ�������
    SchemeAppendCondition condition = new SchemeAppendCondition(scheme);
    if (bislatest != null) {
      condition.appendHead(bislatest, UFBoolean.TRUE);
    }

    SchemeFixCondition tool = new SchemeFixCondition(scheme);
    boolean flag = tool.getBoolean(TOQueryApproveUtil.BISAPPROVING);
    IBillFilter filter = null;
    if (flag) {
      // ��������ͨ���ĵ���
      Integer value = (Integer) POEnumBillStatus.APPROVE.value();
      condition.appendHeadNot(status, value);
      // ���Ӵ��������ݵĹ���
      filter = new ApproveBillFilter(clazz, billtype);
    }
    // ���Ӽ��ź�����֯����
    condition.appendPermission();

    // ������������
    // String orderby = condition.appendHead(PraybillHeaderVO.VBILLCODE);

    BillPageLazyQuery<E> query = new BillPageLazyQuery<E>(clazz, filter);
    PageQueryVO page = null;
    try {
      query.addHeadOrder(villcode);
//      query.addChildOrder(PraybillItemVO.class, PraybillItemVO.CROWNO);
      page = query.query(scheme);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return page;

  }

  @Override
  public IBill[] queryPuApp(String[] ids, Class<E> clazz)
      throws BusinessException {
    BillPageLazyQuery<E> query = new BillPageLazyQuery<E>(clazz);
    E[] bills = null;
    try {
      bills = query.queryPageBills(ids);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return bills;
  }

}
