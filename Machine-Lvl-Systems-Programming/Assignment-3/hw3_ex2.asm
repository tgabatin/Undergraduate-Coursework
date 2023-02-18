;; Exercise #2: String Transformation [30 pts]
;; Prompts the user for a 3-character string of lower-case letters (s1)
;; Prompts the user for a 4-character string (s2)


;; CITATIONS
;; Started code taken from ICS 312 Henri Casanova material
;; https://www.eecg.utoronto.ca/~amza/www.mindsec.com/files/x86regs.html
;; https://www.cs.dartmouth.edu/~sergey/cs258/tiny-guide-to-x86-assembly.pdf
;; http://aturing.umcs.maine.edu/~meadow/courses/cos335/nasm01.pdf

%include "asm_io.inc"

segment .data

	string_one	db	"Enter a 3-character string of lower-case letters: ", 0	; Get user input for 3 - character string
	string_two	db	"Enter a 4-character string: ", 0			; Get user input for 4 character string
	output_msg	db	"The encoded string is: ", 0				; The output messag
	enc_string	times 11 db 0b							; Stores the character in a new string

segment .bss

	;; Spaces in the array for the encoded string (enc_string)
	;; Place these in proper positioning
	;; s2[3] S1[0] S1[0] s2[2] S1[1] S1[1] s2[1] S1[2] S1[2] s2[0]
	;; NOTE: Did not work? (Check positioning in array for print_string method?)
	position_one	resb	1	; s2[3]
	position_two	resb	1	; s1[0]
	position_three	resb	1	; s1[0]
	position_four	resb	1	; s2[2]
	position_five	resb	1	; s1[1]
	position_six	resb	1	; s1[1]
	position_seven	resb	1	; s2[1]
	position_eight	resb	1	; s1[2]
	position_nine	resb	1	; s1[2]
	position_ten	resb	1	; s2[0]

segment .text
	global asm_main

asm_main:

	enter 0,0		; Setup
	pusha			; Setup

	;; Retrieve the input from the user for the 3-character lowercase letter
	;; HINT: CALL `read_char` 4 times here
	mov EAX, string_one		; EAX = string_one
	call print_string		; Prints the strings at EAX
	call read_char			; Reads the character input
	sub AL, 32			; Subtract the values to get the UPPERCASE
	mov BH, AL			; Move the value in al into bh
	call read_char			; Reads the character input from the user
	sub AL, 32			; Subtract the values to get the UPPERCASE
	mov BL, AL			; Move the value stored in al in bl
	call read_char			; Reads the character input from the user
	sub AL, 32			; Subtract the values to get the UPPERCASE
	mov CH, AL			; Moves the value stored in al in ch
	call read_char			; Reads the character
	sub AL, 32			; Subtract the values to get the UPPERCASE
	mov CL, AL			; Moves the value stored in al in cl
	sub AL, 32			; Subtract the values the get the UPPERCASE
	
	;; Retrieve the input for the user for the 4 - character string
	;; HINT: CALL `read_char` 5 times here
	mov EAX, string_two		; EAX = string_two
	call print_string		; Prints the string for the string_two
        call read_char			; Reads the character input from the user
	mov DH, AL			; Move the value in al into dh		
	call read_char			; Reads the character
	mov DL, Al			; Move the character from al into dl
	call read_char			; Reads the character
	mov CL,AL			; Move the value stored in al in cl
	mov [enc_string], CL		; Stores the bits in cl in the encoded string
	call read_char			; Reads the character
	mov CL, AL			; Moves the value stored in al into cl
	call read_char			; Reads the character
	
	mov EAX, 0
	
	;; Begin the encoding
	mov EAX, output_msg		; EAX = output_msg
	call print_string		; Prints the string at EAX

	;; Fourth character of the 4 - Character input
	mov AL, CL			; Move the value in cl in al s2[3]
	call print_char			; Prints the value

	;; First lowercase letter
	mov AL, BH			; Move the value stored in bh in al s1[1]
	call print_char			; Prints the value
	call print_char			; Prints the value

	;; Third character of the 4-Character input
	mov CL, [enc_string] 		; Move the address of the value in the [enc_string] to cl s2[2]
	mov AL, CL			; Move the value in cl to al
	call print_char			; Prints the value

	;; Second lowercase letter
	mov AL, BL			; Move the value in al in bl s1[2]
	call print_char			; prints the value
	call print_char			; Prints the value
	
	;; Second character of the 4-Character input
	mov AL, DL			; Moves the value in dl to al s2[3]
	call print_char			; Prints the character

	;; Third lowercase letter
	mov AL, CH			; Moves the value in ch to al s1[3]
	call print_char			; Prints the character
	call print_char			; Prints the character

	;; First character of the 4-Character input
	mov AL, DH			; Move the value in dh to al s2[4]
	call print_char			; Print the character

	;; Formatting
	call print_nl			; Prints a new line	

	;dump_regs 0			; Debugging

	popa				; Clean up
	mov	EAX, 0			; Clean up
	leave				; Clean up
	ret				; Clean up
