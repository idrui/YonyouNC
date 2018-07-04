/* tablename: ��λ���� */
create table po_position (name4 varchar(300) null 
/*��λ����4*/,
name6 varchar(300) null 
/*��λ����6*/,
name5 varchar(300) null 
/*��λ����5*/,
pk_position char(20) not null 
/*��λ����*/,
name3 varchar(300) null 
/*��λ����3*/,
name2 varchar(300) null 
/*��λ����2*/,
name varchar(300) null 
/*��λ����*/,
pk_group varchar(20) null default '~' 
/*����*/,
pk_org varchar(20) null default '~' 
/*��֯*/,
code varchar(30) null 
/*��λ����*/,
cemployeeid varchar(20) null default '~' 
/*�ƻ�Ա*/,
vmemo varchar(50) null 
/*��ע*/,
fpositiontype integer null 
/*��λ����*/,
 constraint pk_po_position primary key (pk_position),
 ts char(19) null,
dr smallint null default 0
)
;

/* tablename: ��λ�����ӱ� */
create table po_position_b (pk_position_b char(20) not null 
/*��λ�����ӱ�*/,
pk_org varchar(20) null default '~' 
/*��֯*/,
pk_marbasclass varchar(20) null default '~' 
/*���ϻ�������*/,
marbasclass_code varchar(50) null 
/*���ϻ����������*/,
pk_marpuclass varchar(20) null default '~' 
/*���ϲɹ�����*/,
marpuclass_code varchar(50) null 
/*���ϲɹ��������*/,
pk_material varchar(20) null default '~' 
/*����*/,
material_code varchar(50) null 
/*���ϱ���*/,
fflag integer null default 1 
/*Ӧ��/�ų�*/,
pk_position char(20) not null 
/*��λ����_����*/,
pk_srcmaterial varchar(20) null 
/*����oid*/,
 constraint pk_po_position_b primary key (pk_position_b),
 ts char(19) null,
dr smallint null default 0
)
;

