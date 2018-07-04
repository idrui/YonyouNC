package nc.pubitf.pu.m21.api.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.json.JSONString;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.pu.m21.api.IOrderMaintainAPI;
import nc.pubitf.pu.m21.api.IOrderQueryAPI;
import nc.pubitf.pu.rest.AbstractPUResource;
import nc.ui.querytemplate.operator.EqOperator;
import nc.vo.org.OrgVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pu.m21.rule.api.IOrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.scmpub.api.rest.utils.RestUtils;
import nc.vo.scmpub.json.GsonUtils;
import nc.vo.scmpub.util.QuerySchemeBuilder;
import nc.vo.scmpub.util.translate.TranslateUtils;

/**
 * @description
 *              <li>
 *              http://127.0.0.1:80/uapws/rest/purchaseOrder/bybillcode?
 *              vbillcode=SO302015111900000152
 *              </li>
 * @scene
 * 
 * @param
 * 
 *
 * @since 6.5
 * @version 2015年11月20日 上午11:15:33
 * @author wangweir
 */
@Path("purchaseOrder")
public class PurchaseOrderResource extends AbstractPUResource {

  @GET
  @Produces("application/json")
  @Path("bybillcode")
  public JSONString getSaleorderByBillcode(
      @QueryParam("orgcode") String orgcode,
      @QueryParam("vbillcode") String vbillcode) {
    if (StringUtils.isEmpty(vbillcode)) {
      return RestUtils.emptyJSONString;
    }
    RestUtils.initInvocationInfo();

    IOrderQueryAPI query = NCLocator.getInstance().lookup(IOrderQueryAPI.class);
    QuerySchemeBuilder builder =
        QuerySchemeBuilder.buildByFullClassName(OrderHeaderVO.class.getName());
    builder.append(IOrderVO.VBILLCODE, EqOperator.getInstance(), new String[] {
      vbillcode
    });

    if (!StringUtils.isEmpty(orgcode)) {
      String pkGroup = AppContext.getInstance().getPkGroup();
      OrgVO orgVO = new OrgVO();
      Map<String, String> codeToId =
          TranslateUtils.trancelateCodeToID(orgVO, new String[] {
            orgcode
      }, pkGroup);

      if (codeToId != null && codeToId.get(orgcode) != null) {
        builder.append(IOrderVO.PK_ORG, EqOperator.getInstance(), new String[] {
          codeToId.get(orgcode)
        });
      }
    }

    OrderVO[] vos = null;
    try {
      vos = query.queryVOByScheme(builder.create());
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
    if (ArrayUtils.isEmpty(vos)) {
      return RestUtils.toJSONString(new OrderVO());
    }

    return RestUtils.toJSONString(vos[0]);
  }

  @POST
  @Path("insertWithArray")
  @Consumes("application/json")
  @Produces("application/json")
  public JSONString insertArriveBills(JSONString str) throws BusinessException {
    OrderVO[] bills =
        GsonUtils.buildNCGson4Rest().fromJson(str.toString(), OrderVO[].class);

    if (ArrayUtils.isEmpty(bills)) {
      return RestUtils.emptyJSONString;
    }
    RestUtils.initInvocationInfo();

    // 翻译VO
    bills = new PurchaseOrderTranslateAdaptor().doTranslate(bills);

    // 执行插入
    OrderVO[] saleorderVOs = NCLocator.getInstance()
        .lookup(IOrderMaintainAPI.class).insertBills(bills);
    List<String> vbillCodes = new ArrayList<>();
    for (OrderVO saleorderVO : saleorderVOs) {
      vbillCodes.add(saleorderVO.getHVO().getVbillcode());
    }
    return RestUtils.toJSONString(vbillCodes.toArray(new String[0]));
  }

}
