/* indexcode: i_pu_4t_b_src */
create  index i_pu_4t_b_src on po_initialest_b (csourceid asc)
;

/* indexcode: i_pu_4t_b_ordb */
create  index i_pu_4t_b_ordb on po_initialest_b (pk_order_b asc)
;

/* indexcode: i_pu_4t_b_fk */
create  index i_pu_4t_b_fk on po_initialest_b (pk_initialest asc)
;

/* indexcode: i_pu_4t_b_ordh */
create  index i_pu_4t_b_ordh on po_initialest_b (pk_order asc)
;

/* indexcode: i_pu_4t_dtorg */
create  index i_pu_4t_dtorg on po_initialest (dbilldate asc,
pk_org asc)
;

/* indexcode: i_pu_4t_code */
create  index i_pu_4t_code on po_initialest (vbillcode asc)
;

