Gabatino, Taylor
Casanova, Henri
ICS 312: Machine Level & Systems Programming
Spring 2022
25 February 2022

----------------------
Homework Assignment #4
----------------------

-------------------------------
Exercise # 1: Overflow [40 pts]
-------------------------------

For each of the following hex additions:

	* 2-byte quantities: 6733 + 7B1C
	* 2-byte quantities: 8FE0 + A036
	* 1-byte quantities: 0E + F8
	* 1-byte quantities: E3 + 11
	
Answer these 3 questions:

	* Say whether the carry bit is set or not set
	* Say whether the overflow bit is set or not set and explain your reasoning
	* Say what would be printed by print_int if the result of the addition were sign-extended (as opposed to zero-extended) into the EAX register. (Remember that this macro prints signed numbers in the decimal representation)

-----------------------------------	
1. * 2-byte quantities: 6733 + 7B1C
-----------------------------------

The first step to solving this is to convert to decimal and back to hexadecimal. 

Unsigned: 26419 + 31516 = 57935 = E24Fh (No Carry, No Overflow)
Signed: 26419 + 31516 = 57935 = E24Fh (Overflow)

Is the carry bit set or not set?
The carry bit is not set because the resulting number is within the range. 

Are the overflow bits set or not set? Explain your reasoning.
The overflow bits are set because the hexadecimal value E24F is greater than 32767.

What would be printed by print_int if the result of the addition were sign-extended as oppose to zero-extended into the EAX register?
To find the values to be printed by print_int, we can first extend the signed 2-byte value to the 4 byte value, then convert to a decimal representation. 
Sign-Extended: -7601
Zero-Extended:57935

-----------------------------------
2. * 2-byte quantities: 8FE0 + A036
-----------------------------------

The first step to solving this is to convert to decimal and back to hexadecimal. 

Unsigned: 36832 + 41014 = 77846 = 13016h (Overflow)
Signed: -28704 + -24522 = =53226 = -0xCFEAh (Overflow)

Is the carry bit set or not set?
The carry bit is set because the resulting number is out of range. 

Are the overflow bits set or not set? Explain your reasoning.
The overflow bits are set because -53226 is less than -32768.

What would be printed by print_int if the result of the addition were sign-extended as oppose to zero-extended into the EAX register?
To find the values to be printed by print_int, we can first extend the signed 2-byte value to the 4 byte value, then convert to a decimal representation. 
Sign-Extended: 12310
Zero-Extended: 12310

-----------------------------------
3. * 1-byte quantities: 0E + F8
-----------------------------------

The first step to solving this is to convert to decimal and back to hexadecimal. 

Unsigned: 14 + 248 (Overflow) 
Signed: 14 + -8 (No Overflow)

Is the carry bit set or not set?
The carry bit is set because 262 is greater than 255.

Are the overflow bits set or not set? Explain your reasoning.
The overflow bits are not set because a positive and a negative will never result in an overflow.

What would be printed by print_int if the result of the addition were sign-extended as oppose to zero-extended into the EAX register?
To find the values to be printed by print_int, we can first extend the signed 2-byte value to the 4 byte value, then convert to a decimal representation. 
Sign-Extended: 6
Zero-Extended: 6

-----------------------------------
4. * 1-byte quantities: E3 + 11
-----------------------------------

The first step to solving this is to convert to decimal and back to hexadecimal. 

Unsigned: 227 + 17 (No overflow)
Signed: -29 + 17 (No Overflow)

Is the carry bit set or not set?
The carry bit is not set because 244 is less than 255.

Are the overflow bits set or not set? Explain your reasoning.
The overflow bits are not set because a positive and a negative will never result in an overflow.

What would be printed by print_int if the result of the addition were sign-extended as oppose to zero-extended into the EAX register?
To find the values to be printed by print_int, we can first extend the signed 2-byte value to the 4 byte value, then convert to a decimal representation. 
Sign-Extended: 12
Zero-Extended: 12