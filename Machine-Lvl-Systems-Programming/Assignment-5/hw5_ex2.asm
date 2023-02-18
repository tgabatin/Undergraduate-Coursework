;; Exercise #2: Counting Triplets of DNA Bases
;; Implements a program that counts non-overlapping triplets

%include "asm_io.inc"

; Initialized Data
segment .data
    multiple db 0,4,16 ; At address power, I want byte 0, 4, 16
    power   db  0      ; At address power, the number at this address is 0
    index db 0         ; Number used to go to specific place in memory of the counter
; Uninitialized Data
segment .bss
    counter resd 64   ; Declare an array of 64 counters of size 4-Byte

; Code
segment .text
    global asm_main

asm_main:
    enter 0,0   ; Set up
    pusha       ; Set up


outer_loop:
    call read_char  ; Reads input from the user as a sequence of chars, storing it into EAX
    cmp eax, -1     ; Checks to see if this is the EOF
    je program_exit ; Goes to the end of the code if the EOF is reached

inner_loop:
    cmp al, 0x43    ; Comparison of the EAX to the hex value for "C"
    je check_c      ; Jumps to the label of the C segment
    cmp al, 0x54    ; Comparison of the EAX to the hex value for "T"
    je check_t      ; Jumps to the label of the T segment
    cmp al, 0x47    ; Comparison of the EAX to the hex value for "G"
    je check_g      ; Jumps to the label of the G segment
    jmp program_exit ; Jumps to the program_exit segment of the code

check_c:
    ; Multiply 
    mov eax, 4
    mov ebx, [power] ; Value at the address
    mov ecx, [multiple]
    ;mul [multiple + ebx]
    mul ecx
    add [index], eax
    jmp inner_loop

check_g:
    ; Muliply by 2 
    mov eax, 4
    mov ebx, [power] ; Value at the address
    mov ecx, [multiple]
    ;mul [multiple + ebx]
    mul ecx
    add [index], eax
    jmp inner_loop

    
check_t:
    ; Multiply by 3
    ; Multiply 
    mov eax, 4
    mov ebx, [power] ; Value at the address
    mov ecx, [multiple]
    ;mul [multiple + ebx]
    mul ecx
    add [index], eax
    jmp inner_loop

finished_check:
    ; If the sequence is found, print the number of sequences found

program_exit:
    popa        ; Restores the general registers
    mov eax, 0  ; Set the EAX register to 0
    leave       ; Reverse the actions of the enter instruction
    ret         ; Transfers control of the return address back onto the stack
