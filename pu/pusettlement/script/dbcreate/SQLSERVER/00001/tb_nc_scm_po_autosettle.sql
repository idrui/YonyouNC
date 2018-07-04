/* tablename: �Զ�����֮��Ʊ��ⵥ */
create table po_rule_invoicestock (
pk_invoicestock nchar(20) not null 
/*��Ʊ��ⵥ����*/,
bdeptsame nchar(1) null 
/*������ͬ*/,
bbuyersame nchar(1) null 
/*�ɹ�Ա��ͬ*/,
bbatchcodesame nchar(1) null 
/*������ͬ*/,
borigpricesame nchar(1) null 
/*����˰������ͬ*/,
bnumsame nchar(1) null 
/*��Ʊ����ⵥ������ͬ*/,
bproductorsame nchar(1) null 
/*����������ͬ*/,
bprojectsame nchar(1) null 
/*��Ŀ��ͬ*/,
bfreeitemsame nchar(1) null 
/*���ɸ���������ͬ*/,
bfinanceorgsame nchar(1) null 
/*������֯��ͬ*/,
bsuppliersame nchar(1) null 
/*��Ӧ����ͬ*/,
bmaterialsame nchar(1) null 
/*������ͬ*/,
bdetailbidsame nchar(1) null 
/*��Դͬһ������ϸ����*/,
 constraint pk_le_invoicestock primary key (pk_invoicestock),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: �Զ�����֮������Ʊ */
create table po_rule_rbinvoice (
pk_rbinvoice nchar(20) not null 
/*������Ʊ����*/,
bdeptsame nchar(1) null 
/*������ͬ*/,
bbuyersame nchar(1) null 
/*�ɹ�Ա��ͬ*/,
bnumabssame nchar(1) null 
/*������Ʊ��������ֵ��ͬ*/,
bbatchcodesame nchar(1) null 
/*������ͬ*/,
bordersame nchar(1) null 
/*��Դͬһ����*/,
bproductorsame nchar(1) null 
/*����������ͬ*/,
bprojectsame nchar(1) null 
/*��Ŀ��ͬ*/,
bfreeitemsame nchar(1) null 
/*���ɸ���������ͬ*/,
bfinanceorgsame nchar(1) null 
/*������֯��ͬ*/,
bsuppliersame nchar(1) null 
/*��Ӧ����ͬ*/,
bmaterialsame nchar(1) null 
/*������ͬ*/,
binvoicetypesame nchar(1) null 
/*��Ʊ������ͬ*/,
bnorigpricesame nchar(1) null 
/*����˰������ͬ*/,
 constraint pk__rule_rbinvoice primary key (pk_rbinvoice),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

/* tablename: �Զ�����֮������ⵥ */
create table po_rule_rbstock (
pk_rbstock nchar(20) not null 
/*������ⵥ����*/,
bsuppliersame nchar(1) null 
/*��Ӧ����ͬ*/,
bdeptsame nchar(1) null 
/*������ͬ*/,
bbuyersame nchar(1) null 
/*�ɹ�Ա��ͬ*/,
bnumabssame nchar(1) null 
/*������ⵥ��������ֵ��ͬ*/,
bbatchcodesame nchar(1) null 
/*������ͬ*/,
bordersame nchar(1) null 
/*��Դͬһ����*/,
btrantypesame nchar(1) null 
/*���������ͬ*/,
borigpricesame nchar(1) null 
/*����˰������ͬ*/,
bproductorsame nchar(1) null 
/*����������ͬ*/,
bprojectsame nchar(1) null 
/*��Ŀ��ͬ*/,
bfreeitemsame nchar(1) null 
/*���ɸ���������ͬ*/,
bfinanceorgsame nchar(1) null 
/*������֯��ͬ*/,
bmaterialsame nchar(1) null 
/*������ͬ*/,
 constraint pk_po_rule_rbstock primary key (pk_rbstock),
 ts char(19) null default convert(char(19),getdate(),20),
dr smallint null default 0
)
go

