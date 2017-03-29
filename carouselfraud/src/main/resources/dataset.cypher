// Create entities
// Source: https://gist.github.com/jvilledieu/d882df51a4775a6b7588


CREATE(CO1:Company {name:  '1-Red Phonecard Co.', country: 'USA', type: 'LLC', creation_date: '19/09/2013',
                    epoch: 1375674103})
CREATE(CO2:Company {name:  '2-Black Phonecard Co.', country: 'USA', type: 'LLC', creation_date: '11/09/2013',
                    epoch: 1376472512})
CREATE(CO3:Company {name:  '3-Southern Europa Telco', country: 'Italy', type: 'SRL', creation_date: '24/08/2013',
                    epoch: 1375931887})
CREATE(CO4:Company {name:  '4-Joint Bridge Co.', country: 'Italy', type: 'SRL', creation_date: '15/08/2013',
                    epoch: 1375399377})
CREATE(CO5:Company {name:  '5-Joint Telco Co.', country: 'Italy', type: 'SRL', creation_date: '26/09/2013',
                    epoch: 1375795123})
CREATE
  (CO6:Company {name: '6-Swift Co.', country: 'Italy', type: 'SpA', creation_date: '18/08/2013', epoch: 1377926936})
CREATE(CO7:Company {name:  '7-Chips Trading Ltd.', country: 'UK', type: 'LTD', creation_date: '11/08/2013',
                    epoch: 1375385104})
CREATE
  (CO8:Company {name: '8-Chips Global', country: 'UK', type: 'LLC', creation_date: '11/09/2013', epoch: 1377453990})
CREATE
  (CO9:Company {name: '9-Strand VI Co.', country: 'UK', type: 'LLC', creation_date: '15/09/2013', epoch: 1375730265})
CREATE(CO10:Company {name:  '10-Nexus Trading UK Ltd.', country: 'UK', type: 'LTD', creation_date: '17/09/2013',
                     epoch: 1377178159})
CREATE(CO11:Company {name:  '11-Nexus Global US Ltd.', country: 'USA', type: 'LTD', creation_date: '15/09/2013',
                     epoch: 1376409943})
CREATE(CO12:HoldingCo {name:  'A-Joint IT Group', country: 'Italy', type: 'Holding', creation_date: '23/07/2013',
                       epoch: 1374132360})
CREATE(CO13:HoldingCo {name:  'B-Chips UK Group', country: 'UK', type: 'Holding', creation_date: '27/07/2013',
                       epoch: 1373826123})
CREATE(CO14:HoldingCo {name:  'C-Nexus Intl Group', country: 'UK', type: 'Holding', creation_date: '14/07/2013',
                       epoch: 1373562646})
CREATE (P01:Person {name: 'Alberico Goffredo', country: 'Italy', age: '51', criminal_status: 'nothing'})
CREATE (P02:Person {name: 'Charlie Walt', country: 'USA', age: '41', criminal_status: 'nothing'})
CREATE (P03:Person {name: 'Cletis Bysshe', country: 'USA', age: '54', criminal_status: 'nothing'})
CREATE (P04:Person {name: 'Nicodemo Gionata', country: 'Italy', age: '59', criminal_status: 'known crook'})
CREATE (P05:Person {name: 'Carmelo Achille', country: 'Italy', age: '48', criminal_status: 'nothing'})
CREATE (P06:Person {name: 'Edoardo Primo', country: 'Italy', age: '58', criminal_status: 'nothing'})
CREATE (P07:Person {name: 'Cam Esmond', country: 'UK', age: '41', criminal_status: 'known crook'})
CREATE (P08:Person {name: 'Peyton Ewart', country: 'UK', age: '44', criminal_status: 'nothing'})
CREATE (P09:Person {name: 'Vivian Vann', country: 'UK', age: '57', criminal_status: 'nothing'})
CREATE (P10:Person {name: 'Madilyn Hailey', country: 'UK', age: '30', criminal_status: 'known crook'})
CREATE (P11:Person {name: 'Suzanna Salvage', country: 'UK', age: '32', criminal_status: 'nothing'})
CREATE (P12:Person {name: 'John Hudson', country: 'UK', age: '36', criminal_status: 'nothing'})

// Create relationships
CREATE (CO1)-[:SELLS_TO {date: '41548', item_type: 'phone cards rights', epoch: 1380617873, amt: '10000000'}]->(CO3)
CREATE (CO2)-[:SELLS_TO {date: '41548', item_type: 'phone cards rights', epoch: 1380617873, amt: '15000000'}]->(CO3)
CREATE (CO3)-[:SELLS_TO {date: '41557', item_type: 'phone cards rights', epoch: 1381395473, amt: '25000000'}]->(CO4)
CREATE (CO12)-[:SELLS_TO {date: '41562', item_type: 'phone cards rights', epoch: 1381827473, amt: '25000000'}]->(CO6)
CREATE (CO6)-[:SELLS_TO {date: '41567', item_type: 'phone cards rights', epoch: 1382259473, amt: '25000000'}]->(CO7)
CREATE (CO6)-[:SELLS_TO {date: '41572', item_type: 'phone cards rights', epoch: 1382691473, amt: '25000000'}]->(CO11)
CREATE (CO8)-[:SELLS_TO {date: '41577', item_type: 'phone cards rights', epoch: 1383123473, amt: '25000000'}]->(CO9)
CREATE (CO3)-[:COLLECTS_VAT {date: '41557', item_type: 'VAT paid', epoch: 1381395473, amt: '10000000'}]->(CO4)
CREATE (CO12)-[:COLLECTS_VAT {date: '41562', item_type: 'VAT paid', epoch: 1381827473, amt: '10000000'}]->(CO6)
CREATE (CO12)-[:PARENT_OF {legal_status: 'parent company'}]->(CO4)
CREATE (CO12)-[:PARENT_OF {legal_status: 'parent company'}]->(CO5)
CREATE (CO13)-[:PARENT_OF {legal_status: 'parent company'}]->(CO7)
CREATE (CO13)-[:PARENT_OF {legal_status: 'parent company'}]->(CO8)
CREATE (CO14)-[:PARENT_OF {legal_status: 'parent company'}]->(CO10)
CREATE (CO14)-[:PARENT_OF {legal_status: 'parent company'}]->(CO11)
CREATE (P01)-[:DIRECTOR_OF {position: 'director'}]->(CO1)
CREATE (P02)-[:DIRECTOR_OF {position: 'director'}]->(CO2)
CREATE (P03)-[:DIRECTOR_OF {position: 'director'}]->(CO3)
CREATE (P04)-[:DIRECTOR_OF {position: 'director'}]->(CO4)
CREATE (P05)-[:DIRECTOR_OF {position: 'director'}]->(CO5)
CREATE (P06)-[:DIRECTOR_OF {position: 'director'}]->(CO6)
CREATE (P07)-[:DIRECTOR_OF {position: 'director'}]->(CO7)
CREATE (P08)-[:DIRECTOR_OF {position: 'director'}]->(CO8)
CREATE (P09)-[:DIRECTOR_OF {position: 'director'}]->(CO9)
CREATE (P10)-[:DIRECTOR_OF {position: 'director'}]->(CO10)
CREATE (P02)-[:DIRECTOR_OF {position: 'director'}]->(CO11)
CREATE (P04)-[:DIRECTOR_OF {position: 'director'}]->(CO12)
CREATE (P11)-[:DIRECTOR_OF {position: 'director'}]->(CO13)
CREATE (P12)-[:DIRECTOR_OF {position: 'director'}]->(CO14)

RETURN *