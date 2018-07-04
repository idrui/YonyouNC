package nc.bs.pu.m23.maintain.rule.insert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.buzimsg.itf.IMsgresRcvConfManageService;
import nc.buzimsg.vo.MsgresRcvConfVO;
import nc.buzimsg.vo.MsgresRcvVO;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.IDQueryBuilder;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubitf.bdlayer.msg.ScmSendBuziMsgPara;
import nc.pubitf.pu.m23.msg.PuBuziMsgSender;
import nc.vo.bd.meta.BatchOperateVO;
import nc.vo.pu.m23.entity.ArriveHeaderVO;
import nc.vo.pu.m23.entity.ArriveItemVO;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * 
 * @description
 * 到货单保存时，通知请购单的制单人
 * @scene
 * 到货单保存
 * @param
 * 
 *
 * @since 6.5
 * @version 2015-3-30 下午1:48:02
 * @author luojw
 */
public class InsertSendMsgRule implements IRule<ArriveVO> {

  /**
   * 比较表头的交易类型、组织和集团
   * 
   * @since 6.0
   * @version 2015-3-30 下午1:48:02
   * @author luojw
   */
  class CompareData {

    String trantypecode, trantypeid, pk_org, pk_group;

    CompareData(String trantypecode, String trantypeid, String pk_org,
        String pk_group) {
      this.trantypecode = trantypecode;
      this.trantypeid = trantypeid;
      this.pk_org = pk_org;
      this.pk_group = pk_group;
    }

    boolean equals(CompareData cd) {
      if (this.trantypecode.equals(cd.trantypecode)
          && this.pk_org.equals(cd.pk_org) && this.pk_group.equals(cd.pk_group)) {
        return true;
      }
      return false;
    }
  }

  private String msgResCode;

  public InsertSendMsgRule(String msgResCode) {
    this.msgResCode = msgResCode;
  }

  @Override
  public void process(ArriveVO[] vos) {
    Map<String, List<ArriveVO>> uservos = this.sortVos(vos);
    IMsgresRcvConfManageService service =
        NCLocator.getInstance().lookup(IMsgresRcvConfManageService.class);
    // 根据每个制单人发送消息
    for (String maker : uservos.keySet()) {
      CompareData tempCD = null;
      BatchOperateVO returnVO = null;
      for (ArriveVO vo : uservos.get(maker)) {
        ArriveHeaderVO header = vo.getHVO();
        CompareData cd =
            new CompareData(header.getVtrantypecode(), header.getCtrantypeid(),
                header.getPk_org(), header.getPk_group());
        // 比较之前的数据是否和当前一致，来判断是否需要删除之前的，然后再做新增
        if (tempCD == null || !tempCD.equals(cd)) {
          this.deleteMsgresRcvconf(service, returnVO);
          tempCD = cd;
          returnVO = this.saveMsgresRcvconf(maker, cd, service);
        }
        this.sendMsg(vo);
      }
      this.deleteMsgresRcvconf(service, returnVO);
    }
  }

  /**
   * 删除临时配置消息人
   * 
   * @param service
   * @param vo
   */
  private void deleteMsgresRcvconf(IMsgresRcvConfManageService service,
      BatchOperateVO vo) {
    if (vo == null) {
      return;
    }
    try {
      vo.setDelObjs(vo.getAddObjs());
      vo.setAddObjs(null);
      service.batchSaveMsgresRcvConfVO(vo);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 根据到货单表体查询对应的请购单制单人
   * 
   * @param vos
   * @return
   */
  private Map<String, String> queryBillMaker(ArriveVO[] vos) {
    Map<String, String> billMakers = new HashMap<String, String>();
    List<String> idList = new ArrayList<String>();
    DataAccessUtils utils = new DataAccessUtils();
    StringBuilder builder = new StringBuilder();
    builder
        .append(
            " select a.pk_arriveorder_b,p.billmaker from po_arriveorder_b a ")
        .append("left join po_order_b o on a.csourcebid = o.pk_order_b ")
        .append("left join po_praybill p on o.cfirstid = p.pk_praybill where ");
    for (ArriveVO vo : vos) {
      for (ArriveItemVO item : vo.getBVO()) {
        String pk_arriveorder_b = item.getPk_arriveorder_b();
        idList.add(pk_arriveorder_b);
      }
    }
    builder.append(new IDQueryBuilder().buildSQL("a.pk_arriveorder_b",
        idList.toArray(new String[idList.size()])));
    IRowSet rowSet = utils.query(builder.toString());
    while (rowSet.next()) {
      billMakers.put(rowSet.getString(0), rowSet.getString(1));
    }
    return billMakers;
  }

  /**
   * 增加临时配置消息人
   * 
   * @param maker
   * @param cd
   * @param service
   * @return
   */
  private BatchOperateVO saveMsgresRcvconf(String maker, CompareData cd,
      IMsgresRcvConfManageService service) {
    MsgresRcvVO rcv = new MsgresRcvVO();
    rcv.setReceiverpk(maker);
    rcv.setReceivertype(Integer.valueOf(1));
    rcv.setDr(Integer.valueOf(0));

    MsgresRcvConfVO rcvConf = new MsgresRcvConfVO();
    rcvConf.setPk_billtypecode(cd.trantypecode);
    rcvConf.setPk_billtypeid(cd.trantypeid);
    rcvConf.setMsgres_code(this.msgResCode);
    rcvConf.setCreationtime(new UFDateTime());
    rcvConf.setPk_org(cd.pk_org);
    rcvConf.setPk_group(cd.pk_group);
    rcvConf.setDr(Integer.valueOf(0));
    rcvConf.setReceivers(new MsgresRcvVO[] {
      rcv
    });

    BatchOperateVO vo = new BatchOperateVO();
    vo.setAddObjs(new MsgresRcvConfVO[] {
      rcvConf
    });
    BatchOperateVO returnVO = null;
    try {
      returnVO = service.batchSaveMsgresRcvConfVO(vo);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    return returnVO;
  }

  /**
   * 发送消息
   * 
   * @param item
   * @param header
   */
  private void sendMsg(ArriveVO vo) {
    ArriveHeaderVO header = vo.getHVO();
    ScmSendBuziMsgPara param = new ScmSendBuziMsgPara();
    param.setMsgrescode(this.msgResCode);
    param.setBillType(header.getVtrantypecode());
    param.setMsgSourceType("notice");
    param.setContentType("text");
    param.setPk_detail(header.getPk_arriveorder());
    param.setApproverField(ArriveHeaderVO.CREATOR);
    param.setCtrantypeidField(ArriveHeaderVO.CTRANTYPEID);
    param.setPk_groupField(ArriveHeaderVO.PK_GROUP);
    param.setPk_materialField(ArriveItemVO.PK_MATERIAL);
    param.setPk_orgField(ArriveHeaderVO.PK_ORG);
    param.setVtrantypecodeField(ArriveHeaderVO.VTRANTYPECODE);

    PuBuziMsgSender sender = new PuBuziMsgSender();
    sender.send(vo, new String[] {
      header.getPk_org()
    }, param);
  }

  /**
   * 根据制单人和单据号两个维度分组，一个制单人对应多个ArriveVO
   * 
   * @param vos
   * @return
   */
  protected Map<String, List<ArriveVO>> sortVos(ArriveVO[] vos) {
    Map<String, String> billMakers = this.queryBillMaker(vos);
    Map<String, List<ArriveVO>> uservos = new HashMap<>();
    for (ArriveVO vo : vos) {
      // 先将同一个vo的表体根据请购员进行分组
      Map<String, List<ArriveItemVO>> itemvos = new HashMap<>();
      for (ArriveItemVO item : vo.getBVO()) {
        String pk_arriveorder_b = item.getPk_arriveorder_b();
        String maker = billMakers.get(pk_arriveorder_b);
        if (maker == null) {
          continue;
        }
        List<ArriveItemVO> items = itemvos.get(maker);
        if (items == null) {
          items = new ArrayList<>();
          itemvos.put(maker, items);
        }
        items.add(item);
      }
      // 将分好组的表体，分别生成不同的ArriveVO。然后放进uservos
      for(Map.Entry<String, List<ArriveItemVO>> entry : itemvos.entrySet() ){
        String maker = entry.getKey();
        List<ArriveItemVO> items = entry.getValue();
        ArriveVO newvo = new ArriveVO();
        newvo.setHVO(vo.getHVO());
        newvo.setBVO(items.toArray(new ArriveItemVO[0]));
        List<ArriveVO> volist = uservos.get(maker);
        if(volist == null){
          volist = new ArrayList<>();
          uservos.put(maker, volist);
        }
        volist.add(newvo);
      }
    }
    return uservos;
  }
}
