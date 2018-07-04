insert into pub_msgtemp(pk_templet,dr,langcode,messagetitle,pk_org,pk_outtemplet,tempcode,tempdscrpt,tempname,textcontent,ts,typecode,attachdoctype,attachtype,attdeftype,tempname2,tempname3,tempname4,tempname5,tempname6,pk_temptype) values('1001Z81000000000E61C',0,'simpchn','@&m billmaker.user_name@ 提交的到货单待审批！','GLOBLE00000000000000','1002Z810000000017J25','PU_MP_23','到货单消息模板','采购到货单移动审批','<div class="divtext">
<span  class="labeltext">到货单号:</span >
<span  class="normaltext">@&m vbillcode@</span >
</div>
<div class="divtext">
<span  class="labeltext">库存组织:</span >
<span  class="normaltext">@&m pk_org.name@</span >
</div>
<div class="divtext">
<span  class="labeltext">到货类型:</span >
<span  class="normaltext">@&m ctrantypeid.billtypename@</span >
</div>
<div class="divtext">
<span  class="labeltext">总数量:</span >
<span  class="keytext">@&m ntotalastnum@</span >
</div>','2015-10-26 11:19:42','23',1,0,'1',null,null,null,null,null,'1003Z810000000001I15')
go

insert into pub_msgtemp(pk_templet,dr,langcode,messagetitle,pk_org,pk_outtemplet,tempcode,tempdscrpt,tempname,textcontent,ts,typecode,attachdoctype,attachtype,attdeftype,tempname2,tempname3,tempname4,tempname5,tempname6,pk_temptype) values('1003Z810000000000DWG',0,'simpchn','@&m pk_org.name@有设备到货（@&m vbillcode@），请@&b assetorg_name@接收建卡','GLOBLE00000000000000','1003Z810000000004P3J','bs_pu_m23_001','到货单生成设备卡片','到货单生成设备卡片',null,'2013-03-26 21:44:38','bs_pu_m23',null,null,null,null,null,null,null,null,null)
go

insert into pub_msgtemp(pk_templet,dr,langcode,messagetitle,pk_org,pk_outtemplet,tempcode,tempdscrpt,tempname,textcontent,ts,typecode,attachdoctype,attachtype,attdeftype,tempname2,tempname3,tempname4,tempname5,tempname6,pk_temptype) values('1003Z810000000000DWI',0,'simpchn','取消@&m pk_org.name@到货（@&m vbillcode@）的设备在@&b assetorg_name@建卡','GLOBLE00000000000000','1003Z810000000004P3J','bs_pu_m23_002','取消到货单生成设备卡片','取消到货单生成设备卡片',null,'2013-03-26 21:44:38','bs_pu_m23',null,null,null,null,null,null,null,null,null)
go

insert into pub_msgtemp(pk_templet,dr,langcode,messagetitle,pk_org,pk_outtemplet,tempcode,tempdscrpt,tempname,textcontent,ts,typecode,attachdoctype,attachtype,attdeftype,tempname2,tempname3,tempname4,tempname5,tempname6,pk_temptype) values('1003Z810000000002Z97',0,'simpchn','您请购的物料已到货','GLOBLE00000000000000','1003Z810000000004P3J','bs_pu_m23_003','采购到货通知请购员','采购到货通知请购员','您为@&m pk_org.name@请购的物料已于@&m dbilldate@到货，到货单号为：@&m vbillcode@。                    详见附件','2015-05-12 15:57:27','bs_pu_m23',0,0,'2',null,null,null,null,null,null)
go

insert into pub_msgtemp(pk_templet,dr,langcode,messagetitle,pk_org,pk_outtemplet,tempcode,tempdscrpt,tempname,textcontent,ts,typecode,attachdoctype,attachtype,attdeftype,tempname2,tempname3,tempname4,tempname5,tempname6,pk_temptype) values('1003Z810000000002Z99',0,'tradchn','您請購的物料已到貨','GLOBLE00000000000000','1003Z810000000004ROQ','bs_pu_m23_003','采購到貨通知請購員','采購到貨通知請購員','您為@&m pk_org.name@請購的物料已于@&m dbilldate@到貨，到貨單號為：@&m vbillcode@。                    詳見附件','2015-05-12 15:57:27','bs_pu_m23',0,0,'~',null,null,null,null,null,null)
go

insert into pub_msgtemp(pk_templet,dr,langcode,messagetitle,pk_org,pk_outtemplet,tempcode,tempdscrpt,tempname,textcontent,ts,typecode,attachdoctype,attachtype,attdeftype,tempname2,tempname3,tempname4,tempname5,tempname6,pk_temptype) values('1003Z810000000002Z9B',0,'english','en您请购的物料已到货','GLOBLE00000000000000','1003Z810000000004RQQ','bs_pu_m23_003','en采购到货通知请购员','en采购到货通知请购员','en您为@&m pk_org.name@请购的物料已于@&m dbilldate@到货，到货单号为：@&m vbillcode@。                    详见附件','2015-05-12 15:57:27','bs_pu_m23',0,0,'~',null,null,null,null,null,null)
go

insert into pub_msgtemp(pk_templet,dr,langcode,messagetitle,pk_org,pk_outtemplet,tempcode,tempdscrpt,tempname,textcontent,ts,typecode,attachdoctype,attachtype,attdeftype,tempname2,tempname3,tempname4,tempname5,tempname6,pk_temptype) values('1003Z810000000002ZAB',0,'simpchn','到货单@&m vbillcode@已检验','GLOBLE00000000000000','1003Z810000000004P3J','bs_pu_m23_004','采购检验完成通知库管员','采购检验完成通知库管员','到货单@&m vbillcode@已检验，可以入库。                    详见附件','2015-05-13 14:57:27','bs_pu_m23',0,0,'2',null,null,null,null,null,null)
go

insert into pub_msgtemp(pk_templet,dr,langcode,messagetitle,pk_org,pk_outtemplet,tempcode,tempdscrpt,tempname,textcontent,ts,typecode,attachdoctype,attachtype,attdeftype,tempname2,tempname3,tempname4,tempname5,tempname6,pk_temptype) values('1003Z810000000002ZBC',0,'tradchn','到貨單@&m vbillcode@已檢驗','GLOBLE00000000000000','1003Z810000000004ROQ','bs_pu_m23_004','采購檢驗完成通知庫管員','采購檢驗完成通知庫管員','到貨單@&m vbillcode@已檢驗，可以入庫。                    詳見附件','2015-05-13 14:57:27','bs_pu_m23',0,0,'~',null,null,null,null,null,null)
go

insert into pub_msgtemp(pk_templet,dr,langcode,messagetitle,pk_org,pk_outtemplet,tempcode,tempdscrpt,tempname,textcontent,ts,typecode,attachdoctype,attachtype,attdeftype,tempname2,tempname3,tempname4,tempname5,tempname6,pk_temptype) values('1003Z810000000002ZCD',0,'english','en到货单@&m vbillcode@已检验','GLOBLE00000000000000','1003Z810000000004RQQ','bs_pu_m23_004','en采购检验完成通知库管员','en采购检验完成通知库管员','en到货单@&m vbillcode@已检验，可以入库。                    详见附件','2015-05-13 14:57:27','bs_pu_m23',0,0,'~',null,null,null,null,null,null)
go

insert into pub_msgtemp(pk_templet,dr,langcode,messagetitle,pk_org,pk_outtemplet,tempcode,tempdscrpt,tempname,textcontent,ts,typecode,attachdoctype,attachtype,attdeftype,tempname2,tempname3,tempname4,tempname5,tempname6,pk_temptype) values('1003Z810000000004P1F',0,'tradchn','@&m pk_org.name@有設備到貨（@&m vbillcode@），請@&b assetorg_name@接收建卡','GLOBLE00000000000000','1003Z810000000004ROQ','bs_pu_m23_001','到貨單生成設備卡片','到貨單生成設備卡片',null,'2013-03-26 21:44:38','bs_pu_m23',null,null,null,null,null,null,null,null,null)
go

insert into pub_msgtemp(pk_templet,dr,langcode,messagetitle,pk_org,pk_outtemplet,tempcode,tempdscrpt,tempname,textcontent,ts,typecode,attachdoctype,attachtype,attdeftype,tempname2,tempname3,tempname4,tempname5,tempname6,pk_temptype) values('1003Z810000000004P1H',0,'english','en@&m pk_org.name@有设备到货（@&m vbillcode@），请@&b assetorg_name@接收建卡','GLOBLE00000000000000','1003Z810000000004RQQ','bs_pu_m23_001','en到货单生成设备卡片','en到货单生成设备卡片',null,'2013-03-26 21:44:38','bs_pu_m23',null,null,null,null,null,null,null,null,null)
go

insert into pub_msgtemp(pk_templet,dr,langcode,messagetitle,pk_org,pk_outtemplet,tempcode,tempdscrpt,tempname,textcontent,ts,typecode,attachdoctype,attachtype,attdeftype,tempname2,tempname3,tempname4,tempname5,tempname6,pk_temptype) values('1003Z810000000004P1U',0,'tradchn','取消@&m pk_org.name@到貨（@&m vbillcode@）的設備在@&b assetorg_name@建卡','GLOBLE00000000000000','1003Z810000000004ROQ','bs_pu_m23_002','取消到貨單生成設備卡片','取消到貨單生成設備卡片',null,'2013-03-26 21:44:38','bs_pu_m23',null,null,null,null,null,null,null,null,null)
go

insert into pub_msgtemp(pk_templet,dr,langcode,messagetitle,pk_org,pk_outtemplet,tempcode,tempdscrpt,tempname,textcontent,ts,typecode,attachdoctype,attachtype,attdeftype,tempname2,tempname3,tempname4,tempname5,tempname6,pk_temptype) values('1003Z810000000004P1W',0,'english','en取消@&m pk_org.name@到货（@&m vbillcode@）的设备在@&b assetorg_name@建卡','GLOBLE00000000000000','1003Z810000000004RQQ','bs_pu_m23_002','en取消到货单生成设备卡片','en取消到货单生成设备卡片',null,'2013-03-26 21:44:38','bs_pu_m23',null,null,null,null,null,null,null,null,null)
go

