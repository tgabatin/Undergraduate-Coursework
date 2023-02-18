Gabatino, Taylor
Casanova, Henri
ICS 312: Machine Level & Systems Programming
Spring 2022
4 February 2022

-----------------------
Homework Assignment #2
-----------------------

------------------------------------
Exercise #1: [28 pts]: Memory Layout
------------------------------------

Consider the following .data segment:

A	dd	1101b
B	db	043h, 01Ah, 0CEh
C	dw	-18
D	times   2 dw	012FAh
E	db	"c", 13, "aa",  0
F	dd	71
G	dw      009h, 23o

---------------------
Question #1: [21 pts]
---------------------
Show the contents of the memory bytes starting at address A, in hex, on a machine that uses Little Endian. Indicate labels as well. For instance, a (wrong) answer could look like: 

A        B     C  ...
FF FF FA 02 03 06 ...

------
ANSWER
------

A	dd	1101b
-----------------------
4-Byte, whose address is "A". initialized to 1101 in binary
The first 4 Bytes in RAM in HEX:
0D | 00 | 00 | 00



B	db	043h, 01Ah, 0CEh
---------------------------------
3 1-Byte values, whose address is "B", initialized to 43h, 01Ah, 0CEh
The second byte in RAM in HEX is: 
43 | 1A | CE

C	dw	-18
--------------------
2-Byte, whose address is "C", initialized to -18 in decimal.
The third 2-bytes in RAM in HEX is: 
EE | FF <--- FF | EE (Big Endian) <-- 1111 1111 1110 1110 <-- 0000 0000 0001 0010


D	times   2 dw	012FAh
--------------------------------
2-Byte, whose address is "D", initialized to 12FA in hexadecimal. 
The fourth 2-bytes in RAM in HEX is:
FA | 12 | FA | 12 


E	db	"c", 13, "aa",  0
-----------------------------------
1-Byte, whose address is set to "E", initialized to "c", 13, "aa", and 0.
The fifth 1-Byte in RAM in HEX is: 
63 | 0D | 61 | 61 | 00 


F	dd	71
--------------------
4-Byte, whose address is "F", initialized to 71 in decimal
The sixth 4-Byte in RAM in HEX is:
47 | 00 | 00 | 00


G	dw      009h, 23o
--------------------------
2-Byte, whose address is "G", initialized to 9 in hex, and 23 in octal. 
The seventh 2-Byte in RAM in HEX is:
09 | 13
However, since we are given TWO BYTES EACH, with 09h and 23o, we have 4 Total Bytes for the Address. 
Therefore, the seventh 4-Byte in RAM in HEX is:
09 | 00 | 13 | 00



Therefore, the Data Segment on a Little Endian Machine is:

0D | 00 | 00 | 00 | 43 | 1A | CE | EE | FF | FA | 12 | FA | 12 | 63 | 0D | 61 | 61 | 00 | 47 | 00 | 00 | 00 | 09 | 00 | 13 | 00
A                    B              C         D                   E                        F                   G                                      


-------------------
Question #2 [7 pts]
-------------------
For each of the 7 labels, indicate whether it would lead to a different in-memory byte order on a Big Endian machine.

The labels that would change if the memory was converted to Big Endian would be everything in memory that does not have a 1-Byte space. This means excluding everything that has 'db', since 'db' doesn't change in Little Endian or Big Endian. 

------
ANSWER
------

A	dd	1101b	; Yes, this would lead to a different in-memory byte order
B	db	043h, 01Ah, 0CEh	; No, this would not lead to a different in-memory byte order
C	dw	-18	; Yes, this would lead to a different in-memory byte order
D	times   2 dw	012FAh	; Yes, this would lead to a different in-memory byte order
E	db	"c", 13, "aa",  0	; No, this would not lead to a different in-memory byte order
F	dd	71	; Yes, this would lead to a different in-memory byte order
G	dw      009h, 23o	; Yes, this would lead to a different in-memory byte order


--------------------
Exercise #2 [30 pts]
--------------------
Consider the following 15 bytes (in hex) declared by some .data segment on a Little Endian machine.

L1          L2    L3                L4    L5
03 00 00 00 6C 6C 6F 00 A1 B2 C3 13 00 FF FE

Consider now the following program fragment:

mov     eax, [L2]
inc     eax
mov     [L3], eax
mov     ebx, [L1]
mov     eax, L5
sub     eax, ebx
mov     word [eax], 01970h

After the code finishes executing, what are the contents, in hex, of the 15 memory bytes starting at address L1, on a machine using Little Endian?

Show your work for each instruction, as done for the examples in the lecture notes. An easy option is simply to show relevant register values and memory content at each step. For instance, an answer could look something like:

mov bx, [A]:
	- bx: 45 EF
	- RAM: 04 45 EF 65 AA BB CC F3 

mov eax, L3:
	- eax: points to the 5th byte
	- RAM: 04 45 EF 65 AA BB CC F3

inc eax:
	- eax: points to the 6th byte
	- RAM: 04 45 EF 65 AA BB CC F3

...

------
ANSWER
------

Citation: Messaged @Norman Overfield for assistance on this portion of the assignment. 
Citation: https://www.cs.virginia.edu/~evans/cs216/guides/x86.html <- (Used to understand x86 more in addition to the material presented in class)


L1          L2    L3                L4    L5
03 00 00 00 6C 6C 6F 00 A1 B2 C3 13 00 FF FE


Based on the assumption this is a 32-Bit Register:
(For my reference, [] is value in Register, no bracket is the Address (a.k.a. 'dereferenced' value))

mov     eax, [L2]; eax = 6C 6C 6F 00
	- eax: 00 6F 6C 6C
	- RAM: 03 00 00 00 6C 6C 6F 00 A1 B2 C3 13 00 FF FE

inc     eax	; eax = Increment the value of the address of what is stored in eax [L2]
	- eax: 00 6F 6C 6C
	- RAM: 03 00 00 00 6C 6C 6F 00 A1 B2 C3 13 00 FF FE

mov     [L3], eax; write 4 Bytes of eax to address at L3 in RAM
	- eax: 00 6F 6C 6D
	- RAM: 03 00 00 00 6C 6C 6D 6C 6F 00 C3 13 00 FF FE

mov     ebx, [L1] ; Fill the Register of ebx with 03 00 00 00
	- ebx: 00 00 00 03
	- eax: 00 6F 6C 6D
	- RAM: 03 00 00 00 6C 6C 6D 6C 6F 00 C3 13 00 FF FE

mov     eax, L5	;eax = the address of the bytes at L5 ? NOT SURE WHAT NO BRACKETS HERE MEAN
	- ebx: 00 00 00 03
	- eax: The 'dereferenced' value of L5 (Address of L5)
	- RAM: 03 00 00 00 6C 6C 6D 6C 6F 00 C3 13 00 FF FE

sub     eax, ebx
	- ebx: 00 00 00 03
	- eax: <L5>-<03h>=13h Returns the address of 13h
	- RAM: 03 00 00 00 6C 6C 6D 6C 6F 00 C3 13 00 FF FE

mov     word [eax], 01970h
	- ebx: 00 00 00 03
	- eax: <L5>-<03h>=13h Returns the address of 13h
	- RAM: 03 00 00 00 6C 6C 6D 6C 6F 00 C3 70 19 FF FE

Once the code has completed executing, the contents in hex of the 15-bytes of memory beginning at L1 on a Little Endian Machine is:

L1          L2     L3           eax L4    L5
03 00 00 00 6C 6C 6D 6C 6F 00 C3 70 19 FF FE
...
