/* tablename: �ɹ��ɱ�Ҫ�ر��� */
create table po_costfactor_b (pk_costfactor_b char(20) not null 
/*�ɱ�Ҫ����ID*/,
pk_material char(20) not null 
/*��������ID*/,
pk_srcmaterial varchar(20) null 
/*����(OID)*/,
bshow char(1) null default 'Y' 
/*�Ƿ���ʾ*/,
ishoworder integer null 
/*��ʾ˳��*/,
pk_accountfactor varchar(20) null default '~' 
/*��Ӧ�ĺ���Ҫ��*/,
pk_costfactor char(20) not null 
/*�ɹ��ɱ�Ҫ��_����*/,
 constraint pk_po_costfactor_b primary key (pk_costfactor_b),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: �ɹ��ɱ�Ҫ�� */
create table po_costfactor (vfactorname varchar(300) null 
/*�ɱ�Ҫ������*/,
vfactorname6 varchar(300) null 
/*�ɱ�Ҫ������6*/,
vfactorname5 varchar(300) null 
/*�ɱ�Ҫ������5*/,
vfactorname4 varchar(300) null 
/*�ɱ�Ҫ������4*/,
vfactorname3 varchar(300) null 
/*�ɱ�Ҫ������3*/,
vfactorname2 varchar(300) null 
/*�ɱ�Ҫ������2*/,
pk_costfactor char(20) not null 
/*�ɱ�Ҫ��ID*/,
pk_group varchar(20) null default '~' 
/*��������*/,
pk_org varchar(20) null default '~' 
/*����֯*/,
pk_org_v char(20) null 
/*����֯�汾��Ϣ*/,
fapportionmode integer null default 0 
/*��̯��ʽ*/,
bentercost char(1) null default 'N' 
/*�Ƿ�������ɱ�*/,
ifactororder integer null 
/*�ɱ�Ҫ��˳��*/,
 constraint pk_po_costfactor primary key (pk_costfactor),
 ts char(19) null,
dr smallint null default 0
)
;

