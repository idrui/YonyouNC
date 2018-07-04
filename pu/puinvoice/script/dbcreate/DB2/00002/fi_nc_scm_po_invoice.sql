/* indexcode: i_pu_25_dtorg */
create  index i_pu_25_dtorg on po_invoice (dbilldate asc,
pk_org asc)
;

/* indexcode: i_pu_25_code */
create  index i_pu_25_code on po_invoice (vbillcode asc)
;

/* indexcode: i_pu_25_parent */
create  index i_pu_25_parent on po_invoice (pk_parentinvoice asc)
;

/* indexcode: i_pu_25_b_dtorg */
create  index i_pu_25_b_dtorg on po_invoice_b (dbilldate asc,
pk_org asc)
;

/* indexcode: i_pu_25_b_src */
create  index i_pu_25_b_src on po_invoice_b (csourceid asc)
;

/* indexcode: i_pu_25_b_ordb */
create  index i_pu_25_b_ordb on po_invoice_b (pk_order_b asc)
;

/* indexcode: i_pu_25_b_fk */
create  index i_pu_25_b_fk on po_invoice_b (pk_invoice asc)
;

/* indexcode: i_pu_25_b_srcb */
create  index i_pu_25_b_srcb on po_invoice_b (csourcebid asc)
;

/* indexcode: i_pu_25_b_ordh */
create  index i_pu_25_b_ordh on po_invoice_b (pk_order asc)
;

