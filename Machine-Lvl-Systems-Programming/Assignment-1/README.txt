Gabatino, Taylor
Casanova, Henri
ICS 312: Machine Level & Systems Programming
Spring 2022
20 January 2022

----------------------
Homework Assignment #1
----------------------

--------------------------------q
Exercise #1: Conversions [6 pts]
--------------------------------
For all of the following, perform the conversions showing your work ysing the systematic methods described in the lecture notes. 

1. hex 61E2 into binary

Convert each hex digit into a 4-bit binary number:

6_16 = 6H = 0110_2
1_16 = 1H = 0001_2
E_16 = 15H = 1110_2
2_16 = 2H = 0010_2

After gathering the individual 4-bit binary numbers, concatenate to get the value in binary
(0110 0001 1110 0010)_2

2. hex C49 into decimal

Conversion of hex to decimal requires the conversion of the individual hex to their respective position:

C49_16 = 12 * 16^2 +
       = 4 * 16^1 +
	   = 9 * 16^0
	   = 3145

3. binary 1001111010101 into hex

Conversion from binary to hex requires the splitting into 4-bit numbers, and the conversion separately. 

0001 0011 1101 0101

0001_2 = 1H
0011_2 = 3H
1101_2 = D
0101_2 = 5

The conversion is 3D5.

4. binary 1011011 into decimal

Conversion from binary to decimal is denoted by a binary representation of a number and a decimal representation

1011011_2 = (1 * 2^6) + (0 * 2^5) + (1 * 2^4) + (1 * 2^3) + (0 * 2^2) + (1 * 2^1) + (1 * 2^0)
	      = 91_10

5. decimal 178 into binary

Conversion through a series of integer divisions by 2, and recording the remainder of the division. 

Divide 178 by 2: 178 / 2 = 89 + 1
Divide 89 by 2: 89 / 2 = 44 + 0
Divide 44 by 2: 44 / 2 = 22 + 1
Divide 22 by 2: 22 / 2 = 11 + 1
Divide 11 by 2: 11 / 2 = 5 + 0
Divide 5 by 2: 5 / 2 = 2 + 0
Divide 2 by 2: 2 / 2 = 1
Divide 1 by 2: 0

Result: 10110010_2

6. decimal 637 into hex 

637_10 = 637/16 = 39*16 + 3
       = 39/16 2*16 + 7
	   = 0*16 + 2
	   Result: 27D_16

----------------------------------------------
Exercise #2: Binary and Hex Arithmetic [2 pts]
----------------------------------------------
Give the result for each of the operations below. Show your work (showing carries).

1. binary: 011101 + 11011001

     c  c
    011101
+ 11011001
-----------
 011110110
 
2. hex: 9A31B13A + 98A2C67C 

  c  c  c
  9A31B13A
+ 98A2C67C
-----------
 132D477B6

-------------------------------------
Exercise #2: Two's Complement [3 pts]
------------------------------------- 
Give the binary 16-bit two's complement representation of the following decimal integers, and show the details of your work:

1. 111

Since this is a positive number, the two's complement will follow the steps: 
1. Compute the 1's complement of the positive number(this is done by taking the original decimal number, and dividing each of the counterparts individually by 2 until the reaminder is found, "glueing" all of the components together), then add 1 to the resulting binary. 

(111)_10 = (0000 0000 0110 1111)_2

The two's complement of (111)_10 is (0000 0000 0110 1111)_2.  

2. -9

Since this is a negative number, the first step is to represent the complement of its positive counterpart. This is also done by taking the original decimal number, and dividing the counterparts individually by 2 until the remainder is found, "glueing" all of the components together to represent the binary number.

(9)_10 = (0000 0000 0000 0001)_2
Once this representation is set in place, we must "flip" the bits then add 1 to the resulting number. 
(-9)_10 = (1111 1111 1111 0111)_2

The two's complement of (-9)_10 is (1111 1111 1111 0111)_2. 

3. -83

Since this is again a negative number, the first step is to represent the complement of its positive counterpart. This is also done by taking the original decimal number, and dividing the counterparts individually by 2 until the remainder is found, "glueing" all of the components together to represent the binary number.

(83)_10 = (0000 0000 0101 0010)_2

Once this representation is set in place, we must "flip" the bits and then add 1 to the resulting number.
(-83)_10 = (1111 1111 1010 1101)_2


-------------------------------------
Exercise #4: Two's Complement [3 pts]
-------------------------------------
Give the hexadecimal 32-bit two's complement representation of the following decimal integers, and show the details of your work.

1. -11

Since this is a negative number, we need to start with the positive version of the number in its representative hexadecimal format: 

(11)_10 = (B)_16

Once this is represented in the hexadecimal format, we "flip" all the bits to get the one's complement.
(-11)_10 = (3)_16
Add one to get the final resulting number
(-11)_10 = (4)_16

Convert this resulting number to its binary representation, then flip the bits and add 1, but since this is a negative number, the leftmost bit must be 1 to represent it is of negative value.

Binary: (1111 1111 1111 1111 1111 1111 1111 0101)_2

2. -99

Since this is again a negative number, we need to start with the positive version of the number in its representative hexadecimal format:

(99)_10 = (63)_16
Once this is represented in hexadecimal format, we can "flip" all of the bits to get the one's complement. 
(-99)_10 = (9B)_16
Add one to get the final resulting number:
(-99)_10 = (9C)_16

Convert this resulting number to its binary representation, then flip the bits and add 1, but since this is a negative number, the leftmost bit must be 1 to represent it is of negative value.

Binary: (1111 1111 1111 1111 1111 1111 1001 1101)_2

3. 263

Convert the number to its hexadecimal format:
(263)_10 = (107)_16

Since this is not a negative number, we can simply take the two's complement of the value, without regards to the "flips". Since this is a positive number, and the binary representation starts with a "0", the resulting binary is the converted number. 

Binary: (0000 0000 0000 0000 0000 0001 0000 0111)_2

-------------------------------------
Exercise #5: Two's Complement [2 pts]
-------------------------------------
Give the decimal value of the following 12-bit two's complement hexadecimal representations, and show the details of your work. 

1. D19

The first step in finding the decimal value of the hexadecimal representations is to invert the hexadecimal number: 

 FFF
-D19
----
2E6

Add 1 to get the hexadecimal representation inverted: 2E7

Convert this number to decimal:

(2E7)_16 = 2 * 16^2
		 = 15 * 16^1
		 = 7 * 16^0
		 = 743
		 
The resulting decimal number is (743)_10.		 

2. 7AF

The first step in finding the decimal value of the hexadecimal representation is to invert the hexadecimal number: 

 FFF
-7AF
-----
 850
 
 Add 1 to get the hexadecimanl representation inverted: 851
 
 Convert this number to decimal:
 
(851)_16 = 8 * 16^2
		 = 5 * 16^1
		 = 1 * 16^0
		 = 2129
		 
The resulting decimal number is (2129)_10