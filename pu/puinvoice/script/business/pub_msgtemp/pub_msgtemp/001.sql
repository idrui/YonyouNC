insert into pub_msgtemp(pk_templet,dr,langcode,messagetitle,pk_org,pk_outtemplet,tempcode,tempdscrpt,tempname,textcontent,ts,typecode,attachdoctype,attachtype,attdeftype,tempname2,tempname3,tempname4,tempname5,tempname6,pk_temptype) values('1001Z81000000000E61E',0,'simpchn','@&m billmaker.user_name@ 提交的采购发票待审批！','GLOBLE00000000000000','1002Z810000000006GGH','PU_MP_25','采购发票消息模板','采购发票移动审批','<div class="divtext">
<span  class="labeltext">发票编号:</span >
<span  class="normaltext">@&m vbillcode@</span >
</div>
<div class="divtext">
<span  class="labeltext">财务组织:</span >
<span  class="normaltext">@&m pk_org.name@</span >
</div>
<div class="divtext">
<span  class="labeltext">发票类型:</span >
<span  class="normaltext">@&m ctrantypeid.billtypename@</span >
</div>
<div class="divtext">
<span  class="labeltext">价税合计:</span >
<span  class="keytext">@&m ntotalorigmny@</span >
</div>','2015-10-26 11:19:42','25',1,0,'1',null,null,null,null,null,'1003Z810000000001I16')
go

