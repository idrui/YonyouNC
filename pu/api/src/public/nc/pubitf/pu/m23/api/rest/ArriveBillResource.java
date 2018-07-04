package nc.pubitf.pu.m23.api.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.commons.lang.ArrayUtils;
import org.json.JSONString;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m23.api.IArriveBillMaintainAPI;
import nc.pubitf.pu.rest.AbstractPUResource;
import nc.vo.pu.m23.entity.ArriveVO;
import nc.vo.pub.BusinessException;
import nc.vo.scmpub.api.rest.utils.RestUtils;
import nc.vo.scmpub.json.GsonUtils;

/**
 * @description
 *
 * @scene
 * 
 * @param
 * 
 *
 * @since 6.5
 * @version 2015��11��14�� ����2:46:18
 * @author wangweir
 */
@Path("arrivebill")
public class ArriveBillResource extends AbstractPUResource {

  @POST
  @Path("insertWithArray")
  @Consumes("application/json")
  @Produces("application/json")
  public JSONString insertArriveBills(JSONString str) throws BusinessException {
    // �ӵ���ƽ̨���Ƶ���ֻ���Լ����ö���ת��
    ArriveVO[] bills =
        GsonUtils.buildNCGson4Rest().fromJson(str.toString(), ArriveVO[].class);

    if (ArrayUtils.isEmpty(bills)) {
      return null;
    }
    RestUtils.initInvocationInfo();

    // ����VO
    bills = new ArriveBillTranslateAdaptor().doTranslate(bills);

    // ִ�в���
    ArriveVO[] insertBills = NCLocator.getInstance()
        .lookup(IArriveBillMaintainAPI.class).insertBills(bills);

    List<String> vbillcodes = new ArrayList<String>();
    for (ArriveVO bill : insertBills) {
      vbillcodes.add(bill.getHVO().getVbillcode());
    }
    return RestUtils.toJSONString(vbillcodes.toArray(new String[0]));
  }
}
