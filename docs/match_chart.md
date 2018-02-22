# Match Chart

## Summary

I want to see if it is worth implementing a k-sum (generalization version of the 2Sum, 3Sum) variant for finding the best matches.

In a typical scenario I would perform this operation on a sorted list of either integers or values with integer weights. As I need to match bitvectors, only perfect complements can be found easily in this polynomial time ([O(n^(k/2) log n)][1] where k is 6). Thus, I am charting Pickup-to-Recipient pairs to see whether or not perfect complements exist in pickups that cannot be fulfilled with a single recipient.

The method of using perfect complements would save a lot of computation in exchange for a small amount of space. (By caching occurences of complementary values)

If there are cases where no grouping of qualified recipients exists to consume all of the food provided by a pickup, I will not use this algorithm. (Conclusion: I didn't.)

[1]: https://cs.stackexchange.com/questions/2973/generalised-3sum-k-sum-problem 

## Bitwise Check Method

- // TODO - explain method
```
    λ: import Data.Bits
    λ: printBinAnd 60 (xor 63 7)
    	Decimal      Binary
    	   60         111100            <-- Provided, what pickup provides
    &	   56         111000            <-- Accepts, what recipient doesn't restrict
    ------------------------------
    	   56         111000            <-- Takes, what recipient takes from food provided
    λ: printBinAnd 60 (xor 63 58)
    	Decimal      Binary
    	   60         111100
    &	    5         000101
    ------------------------------
    	    4         000100
    λ: printBinOr 56 4
    	Decimal      Binary
    	   56         111000            <-- Takes #1
    |	    4         000100            <-- Takes #2
    ------------------------------
    	   60         111100            <-- All Taken
    
    λ: 60 == 60         <-- If 'Provided' == 'All Taken', a full match has been made
```

## 1. Julia Horton

8 matches with Julia Horton who is giving categories: 60 and whose pickup time is: TUESDAY at 11 (8 as shifted bitstring)

|Recipient # | Full Name | Restrictions | Time Code |
| -- | -- | -- | -- |
| 1 | Tanya Matthews	 | 7	 | 19514
| 78 | Colby Nino	 | 58	 | 13468
| 93 | Geneva Esposito	 | 18	 | 53867
| 101 | Jeffrey Vuong	 | 24	 | 39610
| 115 | Chester Smart	 | 39	 | 16861
| 116 | Travis Tiller	 | 13	 | 27951
| 126 | Janet Hardison	 | 21	 | 57624
| 135 | Treva Bryant	 | 19	 | 28504

#### Categories

Target Value

| Decimal | Binary |
| -- | -- |
| 60 | 111100 |

#### Restrictions

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

- Perfect two sum match exists
  (7)   56         111000
  (58)   4         000100

## 2. Joseph Brown

19 matches with Joseph Brown who is giving categories: 47 and whose pickup time is: TUESDAY at 15 (128 as shifted bitstring)

|Recipient # | Full Name | Restrictions | Time Code |
| -- | -- | -- | -- |
| 8 | Ruth Thornell	 | 44	 | 3795
| 15 | Kenneth Aguilar	 | 53	 | 63375
| 27 | Malcolm Miller	 | 51	 | 54177
| 35 | Donna Clark	 | 60	 | 8356
| 36 | Betty Boatner	 | 14	 | 15272
| 39 | Vicki Smith	 | 18	 | 49650
| 43 | Jackson McBride	 | 35	 | 56734
| 52 | Barbara Corso	 | 59	 | 60657
| 62 | Tonya Forster	 | 62	 | 57730
| 78 | Colby Nino	 | 58	 | 13468
| 90 | Margaret Danis	 | 58	 | 4579
| 101 | Jeffrey Vuong	 | 24	 | 39610
| 107 | Tina Hursey	 | 48	 | 19608
| 110 | James Varner	 | 37	 | 21711
| 115 | Chester Smart	 | 39	 | 16861
| 122 | Reggie Hunter	 | 4	 | 13450
| 137 | Terese Wilkins	 | 54	 | 13041
| 144 | Brian Manchester	 | 21	 | 27282
| 148 | Amy Doyle	 | 4	 | 34551

#### Categories

Target Value

| Decimal | Binary |
| -- | -- |
| Target | 47 | 101111 |

#### Restrictions

| Restrictions | Takes | Takes (Binary) |
| -- | -- | -- |
| 44 |  3 | 000011 |
| 53 | 10 | 001010 |
| 51 | 12 | 001100 |
| 60 |  3 | 000011 |
| 14 | 33 | 100001 |
| 18 | 45 | 101101 |
| 35 | 12 | 001100 |
| 59 |  4 | 000100 |   <-
| 62 |  1 | 000001 |
| 58 |  5 | 000101 |
| 58 |  5 | 000101 |
| 24 | 39 | 100111 |
| 48 | 15 | 001111 |
| 37 | 10 | 001010 |
| 39 |  8 | 001000 |
| 4 | 43 | 101011 |   <-
| 54 |  9 | 001001 |
| 21 | 42 | 101010 |
| 4 | 43 | 101011 |   <-

#### Notes

- Perfect two sum match exists
  (59)   4         000100
  (4)   43         101011

## 3. Alex Fondren

13 matches with Alex Fondren who is giving categories: 63 and whose pickup time is: WEDNESDAY at 15 (128 as shifted bitstring)

| Recipient # | Full Name | Restrictions | Time Code |
| -- | -- | -- | -- |
| 2 | Elizabeth Jimenez	 | 28	 | 7301
| 13 | Tanya Wolff	 | 45	 | 48686
| 44 | Lawrence Miller	 | 8	 | 2870
| 73 | Gail Spinney	 | 39	 | 62467
| 85 | Patricia Baxter	 | 57	 | 31862
| 86 | Marion Garland	 | 2	 | 59138
| 96 | Sharon Ashley	 | 36	 | 56532
| 107 | Tina Hursey	 | 48	 | 19608
| 117 | John Howard	 | 38	 | 44295
| 122 | Reggie Hunter	 | 4	 | 13450
| 123 | Kathryn McDuffie	 | 22	 | 35945
| 136 | Charles Wilson	 | 51	 | 53260
| 139 | Malka Weathersby	 | 45	 | 10136

#### Categories

Target Value

| Decimal | Binary |
| -- | -- |
| 63 | 111111 |

#### Restrictions

| Restrictions | Takes | Takes (Binary) |
| -- | -- | -- |
| 28 |  35 | 100011 |
| 45 |  18 | 010010 |
| 8 |  55 | 110111 |   <-
| 39 |  24 | 011000 |   <-
| 57 |   6 | 000110 |
| 2 |  61 | 111101 |
| 36 |  27 | 011011 |
| 38 |  25 | 011001 |
| 4 |  59 | 111011 |
| 22 |  41 | 101001 |
| 51 |  12 | 001100 |
| 45 |  18 | 010010 |

#### Notes

- No perfect two sum noticed
- Two delivery pair exists
  (8)   55         110111
  (39)  24         011000

## 4. James Whitehouse

5 matches with James Whitehouse who is giving categories: 22 and whose pickup time is: WEDNESDAY at 13 (32 as shifted bitstring)

|Recipient # | Full Name | Restrictions | Time Code |
| -- | -- | -- | -- |
| 47 | Nereida Seely	 | 46	 | 58909
| 69 | Joshua Novick	 | 53	 | 26806
| 131 | Maria Allen	 | 45	 | 22286
| 133 | Richard King	 | 46	 | 62441
| 134 | Hazel Pledger	 | 60	 | 7179

#### Categories

Target Value

| Decimal | Binary |
| -- | -- |
| 22 | 10110 |

#### Restrictions

| Restrictions | Takes | Takes (Binary) |
| -- | -- | -- |
| 46 | 16 | 10000 |
| 53 |  2 | 00010 |
| 45 | 18 | 10010 |   <-
| 46 | 16 | 10000 |
| 60 |  2 | 00010 |

#### Notes

- No complete match exists
  - Third bit is zero in all scenarios
- Best partial delivery
  - All to Maria Allen (Recipient 131)
    (45)  18   10010

## 5. Anna Shapiro

9 matches with Anna Shapiro who is giving categories: 59 and whose pickup time is: MONDAY at 12 (16 as shifted bitstring)

|Recipient # | Full Name | Restrictions | Time Code |
| -- | -- | -- | -- |
| 1 | Tanya Matthews	 | 7	 | 19514
| 3 | Palmer Morales	 | 15	 | 25889
| 46 | Ilse Eaves	 | 21	 | 16945
| 60 | Roger Stewart	 | 7	 | 43824
| 68 | Ronald Kennedy	 | 55	 | 37142
| 77 | Charles Fields	 | 40	 | 8001
| 116 | Travis Tiller	 | 13	 | 27951
| 121 | Frank Barcenas	 | 38	 | 58915
| 147 | Ira Maxwell	 | 11	 | 2860

#### Categories

Target Value

| Decimal | Binary |
| -- | -- |
| 59 | 111011 |

#### Restrictions

| Restrictions | Takes | Takes (Binary) |
| -- | -- | -- |
| 7) | 56 | 111000 |   <-
| 15 | 48 | 110000 |
| 21 | 42 | 101010 |   <-
| 7) | 56 | 111000 |
| 55 |  8 | 001000 |
| 40 | 19 | 010011 |   <-
| 13 | 50 | 110010 |   <-
| 38 | 25 | 011001 |   <-
| 11 | 48 | 110000 |   <-

#### Notes

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

|Recipient # | Full Name | Restrictions | Time Code |
| -- | -- | -- | -- |
| 1 | Tanya Matthews	 | 7	 | 19514
| 8 | Ruth Thornell	 | 44	 | 3795
| 11 | Dorothy Dowdy	 | 50	 | 45939
| 49 | Donna Michalak	 | 39	 | 50768
| 63 | Arturo Harman	 | 30	 | 39704
| 68 | Ronald Kennedy	 | 55	 | 37142
| 77 | Charles Fields	 | 40	 | 8001
| 104 | Loren May	 | 23	 | 24937
| 111 | Leroy Davis	 | 30	 | 52797
| 121 | Frank Barcenas	 | 38	 | 58915
| 132 | Christopher Armer	 | 3	 | 8451
| 134 | Hazel Pledger	 | 60	 | 7179
| 137 | Terese Wilkins	 | 54	 | 13041
| 144 | Brian Manchester	 | 21	 | 27282
| 147 | Ira Maxwell	 | 11	 | 2860

#### Categories

Target Value

| Decimal | Binary |
| -- | -- | 
| 58 | 111010 |

#### Restrictions

| Restrictions | Takes | Takes (Binary) |
| -- | -- | -- |
| 7) | 56 | 111000 |   <-
| 44 | 18 | 010010 |
| 50 |  8 | 001000 |
| 39 | 24 | 011000 |
| 30 | 32 | 100000 |
| 55 |  8 | 001000 |
| 40 | 18 | 010010 |
| 23 | 40 | 101000 |
| 30 | 32 | 100000 |
| 38 | 24 | 011000 |
| 3) | 56 | 111000 |   <-
| 60 |  2 | 000010 |   <-
| 54 |  8 | 001000 |
| 21 | 42 | 101010 |
| 11 | 48 | 110000 |

#### Notes

- Perfect two sum exists
  (3)   56         111000
  (60)   2         000010

## 7. Carroll Keys

5 matches with Carroll Keys who is giving categories: 21 and whose pickup time is: THURSDAY at 20 (4096 as shifted bitstring)

| Recipient # | Full Name | Restrictions | Time Code |
| -- | -- | -- | -- |
| 3 | Palmer Morales	 | 15	 | 25889
| 60 | Roger Stewart	 | 7	 | 43824
| 87 | Aaron Jones	 | 41	 | 51326
| 90 | Margaret Danis	 | 58	 | 4579
| 115 | Chester Smart	 | 39	 | 16861

#### Categories

Target Value

| Decimal | Binary |
| -- | -- |
| 21 | 010101 |

#### Restrictions

| Restrictions | Takes | Takes (Binary) |
| -- | -- | -- |
| 15 | 16 | 010000 |   <-
| 7) | 16 | 010000 |   <-
| 41 | 20 | 010100 |
| 58 |  5 | 000101 |   <-
| 39 | 16 | 010000 |   <-

#### Notes

- Several perfect two sum exists
  (58)   5         000101   <-
  (39)  16         010000   <-
  - several more
- Other non (overlapping) two delivery matches exist
  - Basically (58) 5 000101 with anything else

## 8. Hazel Preston

25 matches with Hazel Preston who is giving categories: 62 and whose pickup time is: SUNDAY at 10 (4 as shifted bitstring)

| Recipient # | Full Name | Restrictions | Time Code |
| -- | -- | -- | -- |
| 5 | Beverly Caldwell	 | 6	 | 12793
| 7 | Eric Torres	 | 49	 | 30648
| 11 | Dorothy Dowdy	 | 50	 | 45939
| 12 | Blanche Vos	 | 29	 | 41331
| 17 | Jason Cook	 | 56	 | 52089
| 25 | Alberto Johnson	 | 52	 | 37339
| 31 | Melodie Upson	 | 21	 | 28572
| 35 | Donna Clark	 | 60	 | 8356
| 39 | Vicki Smith	 | 18	 | 49650
| 47 | Nereida Seely	 | 46	 | 58909
| 49 | Donna Michalak	 | 39	 | 50768
| 53 | Anna Smith	 | 53	 | 11088
| 57 | Arlene Howard	 | 32	 | 12337
| 68 | Ronald Kennedy	 | 55	 | 37142
| 76 | David Farnsworth	 | 15	 | 9821
| 78 | Colby Nino	 | 58	 | 13468
| 91 | Harriet Patel	 | 40	 | 14100
| 92 | Roderick Beck	 | 45	 | 27281
| 104 | Loren May	 | 23	 | 24937
| 110 | James Varner	 | 37	 | 21711
| 112 | Vincent Byrne	 | 17	 | 61198
| 132 | Christopher Armer	 | 3	 | 8451
| 137 | Terese Wilkins	 | 54	 | 13041
| 138 | Mary Thomas	 | 10	 | 45416
| 140 | Stacey Sutton	 | 21	 | 17242

#### Categories

Target Value

| Decimal | Binary |
| -- | -- |
| 62 | 111110 |

#### Restrictions

| Restrictions | Takes | Takes (Binary) |
| -- | -- | -- |
| 6)| 56 | 111000 |
| 49| 14 | 001110 |
| 50| 12 | 001100 |
| 29| 34 | 100010 |
| 56|  6 | 000110 |
| 52| 10 | 001010 |
| 21| 42 | 101010 |
| 60|  2 | 000010 |
| 18| 44 | 101100 |
| 46| 16 | 010000 |
| 39| 24 | 011000 |
| 53| 10 | 001010 |
| 32| 30 | 011110 |
| 55|  8 | 001000 |
| 15| 48 | 110000 |
| 58|  4 | 000100 |
| 40| 22 | 010110 |
| 45| 18 | 010010 |
| 23| 40 | 101000 |
| 37| 26 | 011010 |
| 17| 46 | 101110 |
| 3)| 60 | 111100 |
| 54|  8 | 001000 |
| 10| 52 | 110100 |
| 21| 42 | 101010 |

#### Notes


## 9. David Long

8 matches with David Long who is giving categories: 53 and whose pickup time is: THURSDAY at 11 (8 as shifted bitstring)

| Recipient # | Full Name | Restrictions | Time Code |
| -- | -- | -- | -- |
| 26 | Johnny Messina	 | 13	 | 9833
| 27 | Malcolm Miller	 | 51	 | 54177
| 30 | Mary Lininger	 | 40	 | 21505
| 97 | Stanley Ryan	 | 37	 | 4571
| 113 | Susan Carlos	 | 60	 | 5753
| 117 | John Howard	 | 38	 | 44295
| 129 | Kathy Myers	 | 31	 | 10499
| 149 | Gertrude Duprey	 | 3	 | 26678

#### Categories

Target Value

| Decimal | Binary |
| -- | -- |
| 53 | 110101 |

#### Restrictions

| Restrictions | Takes | Takes (Binary) |
| -- | -- | -- |
| 13 | 48 | 110000 |
| 51 |  4 | 000100 |
| 40 | 21 | 010101 |
| 37 | 16 | 010000 |
| 60 |  1 | 000001 |
| 38 | 17 | 010001 |
| 31 | 32 | 100000 |
| 3) | 52 | 110100 |

#### Notes


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