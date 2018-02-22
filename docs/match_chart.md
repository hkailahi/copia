# Deliveries that can't be made without more than one recipient

1: Pickup #9 Julia Horton has no perfect matches. He/She is giving categories: 60 and whose pickup time is: TUESDAY at 11 (8 as shifted bitstring)
2: Pickup #10 Dorothy Robbins has no perfect matches. He/She is giving categories: 0 and whose pickup time is: SUNDAY at 12 (16 as shifted bitstring)
3: Pickup #22 Joseph Brown has no perfect matches. He/She is giving categories: 47 and whose pickup time is: TUESDAY at 15 (128 as shifted bitstring)
4: Pickup #29 Mark Ruiz has no perfect matches. He/She is giving categories: 0 and whose pickup time is: THURSDAY at 11 (8 as shifted bitstring)
5: Pickup #53 Alex Fondren has no perfect matches. He/She is giving categories: 63 and whose pickup time is: WEDNESDAY at 15 (128 as shifted bitstring)
6: Pickup #60 James Whitehouse has no perfect matches. He/She is giving categories: 22 and whose pickup time is: WEDNESDAY at 13 (32 as shifted bitstring)
7: Pickup #71 Anna Shapiro has no perfect matches. He/She is giving categories: 59 and whose pickup time is: MONDAY at 12 (16 as shifted bitstring)
8: Pickup #82 Karrie Rush has no perfect matches. He/She is giving categories: 0 and whose pickup time is: SATURDAY at 9 (2 as shifted bitstring)
9: Pickup #150 Lessie Whitlock has no perfect matches. He/She is giving categories: 58 and whose pickup time is: SATURDAY at 20 (4096 as shifted bitstring)
10: Pickup #152 Carroll Keys has no perfect matches. He/She is giving categories: 21 and whose pickup time is: THURSDAY at 20 (4096 as shifted bitstring)
11: Pickup #161 Hazel Preston has no perfect matches. He/She is giving categories: 62 and whose pickup time is: SUNDAY at 10 (4 as shifted bitstring)
12: Pickup #184 David Long has no perfect matches. He/She is giving categories: 53 and whose pickup time is: THURSDAY at 11 (8 as shifted bitstring)

# Deliveries that can't be made without more than one recipient

1 - Pickup #9 Julia Horton Food: 60 Pickup Time: TUESDAY at 11 (8 as shifted bitstring)
2 - NO FOOD - Pickup #10 Dorothy Robbins has no food to give.
3 - Pickup #22 Joseph Brown Food: 47 Pickup Time: TUESDAY at 15 (128 as shifted bitstring)
4 - NO FOOD - Pickup #29 Mark Ruiz has no food to give.
5 - Pickup #53 Alex Fondren Food: 63 Pickup Time: WEDNESDAY at 15 (128 as shifted bitstring)
6 - Pickup #60 James Whitehouse Food: 22 Pickup Time: WEDNESDAY at 13 (32 as shifted bitstring)
7 - Pickup #71 Anna Shapiro Food: 59 Pickup Time: MONDAY at 12 (16 as shifted bitstring)
8 - NO FOOD - Pickup #82 Karrie Rush has no food to give.
9 - Pickup #150 Lessie Whitlock Food: 58 Pickup Time: SATURDAY at 20 (4096 as shifted bitstring)
10 - Pickup #152 Carroll Keys Food: 21 Pickup Time: THURSDAY at 20 (4096 as shifted bitstring)
11 - Pickup #161 Hazel Preston Food: 62 Pickup Time: SUNDAY at 10 (4 as shifted bitstring)
12 - Pickup #184 David Long Food: 53 Pickup Time: THURSDAY at 11 (8 as shifted bitstring)

## Bitwise Check Method

- // TODO - explain method
```
	λ: import Data.Bits
	λ: printBinAnd 60 (xor 63 7)
		Decimal      Binary
		   60         111100
	&	   56         111000
	------------------------------
		   56         111000

	λ: printBinAnd 60 (xor 63 58)
		Decimal      Binary
		   60         111100
	&	    5         000101
	------------------------------
		    4         000100
```

## 1. Julia Horton

8 matches with Julia Horton who is giving categories: 60 and whose pickup time is: TUESDAY at 11 (8 as shifted bitstring)
Recipient #1: Tanya Matthews	 restriction: 7	 time: 19514
Recipient #78: Colby Nino	 restriction: 58	 time: 13468
Recipient #93: Geneva Esposito	 restriction: 18	 time: 53867
Recipient #101: Jeffrey Vuong	 restriction: 24	 time: 39610
Recipient #115: Chester Smart	 restriction: 39	 time: 16861
Recipient #116: Travis Tiller	 restriction: 13	 time: 27951
Recipient #126: Janet Hardison	 restriction: 21	 time: 57624
Recipient #135: Treva Bryant	 restriction: 19	 time: 28504

### Categories vs (not) Restrictions

**Categories**

Target Value
| Decimal | Binary |
| -- | -- |
| 60 | 111100 |

**Restrictions**

| Restrictions | Takes | Takes (Binary) |
| -- | -- | -- |
| **7** | ***56*** | ***111000*** |   <-
| **58** | ***4*** | ***000100*** |  <-
| 18 | 44 | 101100 |
| 24 | 36 | 100100 |
| 39 | 24 | 011000 |
| 3 | 48 | 110000 |
| 21 | 40 | 101000 |
| 19 | 44 | 101100 |

#### Notes
------------
- Perfect two sum match exists
  (7)   56         111000
  (58)   4         000100

## 2. Joseph Brown

19 matches with Joseph Brown who is giving categories: 47 and whose pickup time is: TUESDAY at 15 (128 as shifted bitstring)
Recipient #8: Ruth Thornell	 restriction: 44	 time: 3795
Recipient #15: Kenneth Aguilar	 restriction: 53	 time: 63375
Recipient #27: Malcolm Miller	 restriction: 51	 time: 54177
Recipient #35: Donna Clark	 restriction: 60	 time: 8356
Recipient #36: Betty Boatner	 restriction: 14	 time: 15272
Recipient #39: Vicki Smith	 restriction: 18	 time: 49650
Recipient #43: Jackson McBride	 restriction: 35	 time: 56734
Recipient #52: Barbara Corso	 restriction: 59	 time: 60657
Recipient #62: Tonya Forster	 restriction: 62	 time: 57730
Recipient #78: Colby Nino	 restriction: 58	 time: 13468
Recipient #90: Margaret Danis	 restriction: 58	 time: 4579
Recipient #101: Jeffrey Vuong	 restriction: 24	 time: 39610
Recipient #107: Tina Hursey	 restriction: 48	 time: 19608
Recipient #110: James Varner	 restriction: 37	 time: 21711
Recipient #115: Chester Smart	 restriction: 39	 time: 16861
Recipient #122: Reggie Hunter	 restriction: 4	 time: 13450
Recipient #137: Terese Wilkins	 restriction: 54	 time: 13041
Recipient #144: Brian Manchester	 restriction: 21	 time: 27282
Recipient #148: Amy Doyle	 restriction: 4	 time: 34551

###### Categories vs (not) Restrictions

Categories
------------
(Target) 47      101111

Restrictions
------------
(44)   3         000011
(53)  10         001010
(51)  12         001100
(60)   3         000011
(14)  33         100001
(18)  45         101101
(35)  12         001100
(59)   4         000100   <-
(62)   1         000001
(58)   5         000101
(58)   5         000101
(24)  39         100111
(48)  15         001111
(37)  10         001010
(39)   8         001000
(4)   43         101011   <-
(54)   9         001001
(21)  42         101010
(4)   43         101011   <-

Notes
------------
- Perfect two sum match exists
  (59)   4         000100
  (4)   43         101011

## 3. Alex Fondren

13 matches with Alex Fondren who is giving categories: 63 and whose pickup time is: WEDNESDAY at 15 (128 as shifted bitstring)
Recipient #2: Elizabeth Jimenez	 restriction: 28	 time: 7301
Recipient #13: Tanya Wolff	 restriction: 45	 time: 48686
Recipient #44: Lawrence Miller	 restriction: 8	 time: 2870
Recipient #73: Gail Spinney	 restriction: 39	 time: 62467
Recipient #85: Patricia Baxter	 restriction: 57	 time: 31862
Recipient #86: Marion Garland	 restriction: 2	 time: 59138
Recipient #96: Sharon Ashley	 restriction: 36	 time: 56532
Recipient #107: Tina Hursey	 restriction: 48	 time: 19608
Recipient #117: John Howard	 restriction: 38	 time: 44295
Recipient #122: Reggie Hunter	 restriction: 4	 time: 13450
Recipient #123: Kathryn McDuffie	 restriction: 22	 time: 35945
Recipient #136: Charles Wilson	 restriction: 51	 time: 53260
Recipient #139: Malka Weathersby	 restriction: 45	 time: 10136

###### Categories vs (not) Restrictions

Categories
------------
(Target) 63      111111

Restrictions
------------
(28)  35         100011
(45)  18         010010
(8)   55         110111   <-
(39)  24         011000   <-
(57)   6         000110
(2)   61         111101
(36)  27         011011
(38)  25         011001
(4)   59         111011
(22)  41         101001
(51)  12         001100
(45)  18         010010

Notes
------------
- No perfect two sum noticed
- Two delivery pair exists
  (8)   55         110111
  (39)  24         011000

## 4. James Whitehouse

5 matches with James Whitehouse who is giving categories: 22 and whose pickup time is: WEDNESDAY at 13 (32 as shifted bitstring)
Recipient #47: Nereida Seely	 restriction: 46	 time: 58909
Recipient #69: Joshua Novick	 restriction: 53	 time: 26806
Recipient #131: Maria Allen	 restriction: 45	 time: 22286
Recipient #133: Richard King	 restriction: 46	 time: 62441
Recipient #134: Hazel Pledger	 restriction: 60	 time: 7179

###### Categories vs (not) Restrictions

Categories
------------
(Target)  22      10110

Restrictions
------------
(46)  16          10000
(53)   2          00010
(45)  18          10010   <-
(46)  16          10000
(60)   2          00010

Notes
------------
- No complete match exists
  - Third bit is zero in all scenarios
- Best partial delivery
  - All to Maria Allen (Recipient 131)
    (45)  18   10010

## 5. Anna Shapiro

9 matches with Anna Shapiro who is giving categories: 59 and whose pickup time is: MONDAY at 12 (16 as shifted bitstring)
Recipient #1: Tanya Matthews	 restriction: 7	 time: 19514
Recipient #3: Palmer Morales	 restriction: 15	 time: 25889
Recipient #46: Ilse Eaves	 restriction: 21	 time: 16945
Recipient #60: Roger Stewart	 restriction: 7	 time: 43824
Recipient #68: Ronald Kennedy	 restriction: 55	 time: 37142
Recipient #77: Charles Fields	 restriction: 40	 time: 8001
Recipient #116: Travis Tiller	 restriction: 13	 time: 27951
Recipient #121: Frank Barcenas	 restriction: 38	 time: 58915
Recipient #147: Ira Maxwell	 restriction: 11	 time: 2860

###### Categories vs (not) Restrictions

Categories
------------
(Target)  59     111011

Restrictions
------------
(7)   56         111000   <-
(15)  48         110000
(21)  42         101010   <-
(7)   56         111000
(55)   8         001000
(40)  19         010011   <-
(13)  50         110010   <-
(38)  25         011001   <-
(11)  48         110000   <-

Notes
------------
- No perfect two sum noticed
- Two delivery pair exists
  (7)   56         111000
  (40)  19         010011
          ~ OR ~
  (21)  42         101010
  (40)  19         010011
          ~ OR ~
  (21)  42         101010
  (38)  25         011001
          ~ OR ~
  (38)  25         011001
  (11)  48         110000
          ~ OR ~
  (13)  50         110010
  (38)  25         011001
  - maybe more

## 6. Lessie Whitlock

15 matches with Lessie Whitlock who is giving categories: 58 and whose pickup time is: SATURDAY at 20 (4096 as shifted bitstring)
Recipient #1: Tanya Matthews	 restriction: 7	 time: 19514
Recipient #8: Ruth Thornell	 restriction: 44	 time: 3795
Recipient #11: Dorothy Dowdy	 restriction: 50	 time: 45939
Recipient #49: Donna Michalak	 restriction: 39	 time: 50768
Recipient #63: Arturo Harman	 restriction: 30	 time: 39704
Recipient #68: Ronald Kennedy	 restriction: 55	 time: 37142
Recipient #77: Charles Fields	 restriction: 40	 time: 8001
Recipient #104: Loren May	 restriction: 23	 time: 24937
Recipient #111: Leroy Davis	 restriction: 30	 time: 52797
Recipient #121: Frank Barcenas	 restriction: 38	 time: 58915
Recipient #132: Christopher Armer	 restriction: 3	 time: 8451
Recipient #134: Hazel Pledger	 restriction: 60	 time: 7179
Recipient #137: Terese Wilkins	 restriction: 54	 time: 13041
Recipient #144: Brian Manchester	 restriction: 21	 time: 27282
Recipient #147: Ira Maxwell	 restriction: 11	 time: 2860

###### Categories vs (not) Restrictions

Categories
------------
(Target)  58     111010

Restrictions
------------
(7)   56         111000   <-
(44)  18         010010
(50)   8         001000
(39)  24         011000
(30)  32         100000
(55)   8         001000
(40)  18         010010
(23)  40         101000
(30)  32         100000
(38)  24         011000
(3)   56         111000   <-
(60)   2         000010   <-
(54)   8         001000
(21)  42         101010
(11)  48         110000

Notes
------------
- Perfect two sum exists
  (3)   56         111000
  (60)   2         000010


=======================================

## 7. Carroll Keys

5 matches with Carroll Keys who is giving categories: 21 and whose pickup time is: THURSDAY at 20 (4096 as shifted bitstring)
Recipient #3: Palmer Morales	 restriction: 15	 time: 25889
Recipient #60: Roger Stewart	 restriction: 7	 time: 43824
Recipient #87: Aaron Jones	 restriction: 41	 time: 51326
Recipient #90: Margaret Danis	 restriction: 58	 time: 4579
Recipient #115: Chester Smart	 restriction: 39	 time: 16861

###### Categories vs (not) Restrictions

Categories
------------
(Target)  21     010101

Restrictions
------------
(15)  16         010000   <-
(7)   16         010000   <-
(41)  20         010100
(58)   5         000101   <-
(39)  16         010000   <-

Notes
------------
- Several perfect two sum exists
  (58)   5         000101   <-
  (39)  16         010000   <-
  - several more
- Other non (overlapping) two delivery matches exist
  - Basically (58) 5 000101 with anything else

## 8. Hazel Preston

25 matches with Hazel Preston who is giving categories: 62 and whose pickup time is: SUNDAY at 10 (4 as shifted bitstring)
Recipient #5: Beverly Caldwell	 restriction: 6	 time: 12793
Recipient #7: Eric Torres	 restriction: 49	 time: 30648
Recipient #11: Dorothy Dowdy	 restriction: 50	 time: 45939
Recipient #12: Blanche Vos	 restriction: 29	 time: 41331
Recipient #17: Jason Cook	 restriction: 56	 time: 52089
Recipient #25: Alberto Johnson	 restriction: 52	 time: 37339
Recipient #31: Melodie Upson	 restriction: 21	 time: 28572
Recipient #35: Donna Clark	 restriction: 60	 time: 8356
Recipient #39: Vicki Smith	 restriction: 18	 time: 49650
Recipient #47: Nereida Seely	 restriction: 46	 time: 58909
Recipient #49: Donna Michalak	 restriction: 39	 time: 50768
Recipient #53: Anna Smith	 restriction: 53	 time: 11088
Recipient #57: Arlene Howard	 restriction: 32	 time: 12337
Recipient #68: Ronald Kennedy	 restriction: 55	 time: 37142
Recipient #76: David Farnsworth	 restriction: 15	 time: 9821
Recipient #78: Colby Nino	 restriction: 58	 time: 13468
Recipient #91: Harriet Patel	 restriction: 40	 time: 14100
Recipient #92: Roderick Beck	 restriction: 45	 time: 27281
Recipient #104: Loren May	 restriction: 23	 time: 24937
Recipient #110: James Varner	 restriction: 37	 time: 21711
Recipient #112: Vincent Byrne	 restriction: 17	 time: 61198
Recipient #132: Christopher Armer	 restriction: 3	 time: 8451
Recipient #137: Terese Wilkins	 restriction: 54	 time: 13041
Recipient #138: Mary Thomas	 restriction: 10	 time: 45416
Recipient #140: Stacey Sutton	 restriction: 21	 time: 17242

###### Categories vs (not) Restrictions

Categories
------------
(Target)  62     111110

Restrictions
------------
(6)		56         111000
(49)	14         001110
(50)	12         001100
(29)	34         100010
(56)	 6         000110
(52)	10         001010
(21)	42         101010
(60)	 2         000010
(18)	44         101100
(46)	16         010000
(39)	24         011000
(53)	10         001010
(32)	30         011110
(55)	 8         001000
(15)	48         110000
(58)	 4         000100
(40)	22         010110
(45)	18         010010
(23)	40         101000
(37)	26         011010
(17)	46         101110
(3)		60         111100
(54)	 8         001000
(10)	52         110100
(21)	42         101010

Notes
------------


## 9. David Long

8 matches with David Long who is giving categories: 53 and whose pickup time is: THURSDAY at 11 (8 as shifted bitstring)
Recipient #26: Johnny Messina	 restriction: 13	 time: 9833
Recipient #27: Malcolm Miller	 restriction: 51	 time: 54177
Recipient #30: Mary Lininger	 restriction: 40	 time: 21505
Recipient #97: Stanley Ryan	 restriction: 37	 time: 4571
Recipient #113: Susan Carlos	 restriction: 60	 time: 5753
Recipient #117: John Howard	 restriction: 38	 time: 44295
Recipient #129: Kathy Myers	 restriction: 31	 time: 10499
Recipient #149: Gertrude Duprey	 restriction: 3	 time: 26678

###### Categories vs (not) Restrictions

Categories
------------
(Target)  53     110101

Restrictions
------------
(13)	48         110000
(51)	 4         000100
(40)	21         010101
(37)	16         010000
(60)	 1         000001
(38)	17         010001
(31)	32         100000
(3)		52         110100

Notes
------------
