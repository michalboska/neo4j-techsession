MATCH p = (a:Company)-[rs:SELLS_TO*]->(c:Company)
  WHERE a.country <> c.country
WITH p, a, c, rs, nodes(p) AS ns
WITH p, a, c, rs,
     filter(n IN ns
       WHERE n.epoch - 1383123473 < (90 * 60 * 60 * 24)) AS bs
WITH p, a, c, rs, head(bs) AS b
  WHERE NOT b IS NULL
WITH p, a, b, c, head(rs) AS r1, last(rs) AS rn
WITH p, a, b, c, r1, rn, rn.epoch - r1.epoch AS d
  WHERE d < (15 * 60 * 60 * 24)
RETURN a, b, c, d, r1, rn