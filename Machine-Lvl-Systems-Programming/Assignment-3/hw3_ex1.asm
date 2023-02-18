;; Exercise #1: Warm Up [22 pts]
;; Prompts the user for one lower-case letter and one integer.
;; The program will then print the upper-case version of the letter and the opposite of the intege
;; This program assumes the user only enters valid inputr

;; CITATIONS
;; https://www.eecg.utoronto.ca/~amza/www.mindsec.com/files/x86regs.html
;; https://www.cs.dartmouth.edu/~sergey/cs258/tiny-guide-to-x86-assembly.pdf
;; https://programmersheaven.com/discussion/413781/x86-nasm-lowercase-to-uppercase
;; https://www.ic.unicamp.br/~pannain/mc404/aulas/pdfs/Art%20Of%20Intel%20x86%20Assembly.pdf

%include "asm_io.inc"

segment .data

        lower   db      "Enter a lower-case letter: ", 0
	upper	db	"The upper-case letter is: ", 0
        integ   db      "Enter an integer: ", 0
        opp     db      "The opposite integer is: ", 0
	quote	db	" ' ", 0

segment .bss

	; No need for uninitialized variables

segment .text

        global asm_main

asm_main:

        enter 0,0		; Setup
        pusha			; Setup

        ;; Retrieve the input from the user for the lowercase letter
        mov EAX, lower          ; EAX = lower
        call print_string       ; Prints the string at EAX
        call read_char          ; Retrieves the user input, saving it to al in the register
        mov EBX, EAX            ; Move this string to EBX
	sub EAX, 32		; Gets the opposite uppercase letter
	inc EAX			; Increment to input the integer

        ;; Retriever the user input for the integer
        mov EAX, integ          ; mov the integer to the EAX register
        call print_string       ; Prints the string for the second input
        call read_int           ; Reads the integer
        mov ECX, EAX            ; Moves the integer hex value to ECX
	inc EAX			; Increment to the integer

        ;; PRINT THE UPPERCASE LETTER
        mov EAX, upper		; Move the uppercase letter to the EAX register
        call print_string	; Prints the string storedi in EAX 
        mov EAX, EBX		; Moves the string stored in EBX to EAX 
        sub EAX, 32		; Subtract the values in EAX to get the uppercase letter
        call print_char		; Prints the character stored in EAX
        call print_nl           ; Print a new line

        ;; PRINT THE INTEGER
        mov EAX, opp		; Move the opposite integer to the EAX register
        call print_string	; Prints the value of the string stored at EAX
        mov EBX, ECX		; Moves the value stored at ECX to EBX
        mov EAX, 0		; Move the value of 0 to EAX
        sub EAX, EBX		; Subtract the value stored in EAX by the values in EAX
        call print_int		; Print the lowercase letter	

	call print_nl		; Print a new line

	popa			; Clean up
	mov	eax, 0		; Clean up
	leave			; Clean up
	ret			; Clean up
