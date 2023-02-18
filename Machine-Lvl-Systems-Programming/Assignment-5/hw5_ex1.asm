;; Exercise 1: Counting DNA Bases [20 pts]
;; Reads characters from standard input until the "end of file" is reached
;; Input Provided to program contains A,C,T,G characters and new line characters to be ignored (ASCII 1- decimal)
;; Input can also be in the form of a file using "cat"

%include "asm_io.inc"

; Iniitalized Data
segment .data
    A   db  "A: ", 0  
    C   db  "C: ", 0
    T   db  "T: ", 0
    G   db  "G: ", 0
; Uninitialized Data
segment .bss
    countA  resd    1
    countC  resd    1
    countT  resd    1
    countG  resd    1
; Code
segment .text
    global asm_main

asm_main:
    enter 0,0          ; Set up
    pusha              ; Set up
while_start:
    call    read_char  ; Read input from the user as a sequence of chars
    cmp eax, -1        ; Checks if EOF 
    je program_exit    ; Goes to the end of the code if the EOF is reached

process_loop_start:
    cmp al, 0x41        ; Checks the value of EAX to see if it is "A"
    je add_A            ; If the value is "A" jump to add_A
    cmp al, 0x43        ; Compare the value of EAX to see if it is "C"
    je add_C            ; If the value is "C" jump to add_C
    cmp al, 0x54        ; Compare the value of EAX to see if it is "T"
    je add_T            ; If the value is "T", jump to add_T
    cmp al, 0x47        ; Compare the value of EAX to see if it is "G"
    je add_G            ; If the value is "G" jump to add_G
    jmp while_start     ; Error checking for any case not "S"

add_A:
    mov ecx, [countA]   ; In register ECX, store the address of countA
    inc ecx             ; Increment the counter
    mov [countA], ecx   ; Move back to memory
    jmp while_start     ; Jump to the beginning to count the next character

add_C:
    mov ecx, [countC]   ; In register ECX, store the address of countC
    inc ecx             ; Increment the counter
    mov [countC], ecx   ; Move back to memory
    jmp while_start     ; Jump to the beginning to count the next character

add_T:
    mov ecx, [countT]   ; In register ECX, store the address of countT
    inc ecx             ; Increment the counter
    mov [countT], ecx   ; Move back to memory
    jmp while_start     ; Jump to the beginning to count the next character

add_G:
    mov ecx, [countG]    ; In register ECX, store the address of countG
    inc ecx              ; Increment the counter
    mov [countG], ecx    ; Move back to memory 
    jmp while_start      ; Jump to the beginning to count the next character

program_exit:
    mov eax, A           ; In register eax, store A         
    call print_string    ; Print the string stored in EAX   
    mov eax, [countA]    ; In register eax, store the address of countA
    call print_int       ; Print the value stored in EAX 
    call print_nl        ; Move to a new line
    mov eax, C           ; In register eax, store C
    call print_string    ; Print the string stored in eax
    mov eax, [countC]    ; In register eax, store the address of countC
    call print_int       ; Print the value stored in EAX
    call print_nl        ; Move to a new line
    mov eax, T           ; In register eax, store T
    call print_string    ; Print the string stored in EAX
    mov eax, [countT]    ; In register eax, store the address of countT
    call print_int       ; Print the value stored in EAX
    call print_nl        ; Move to a new line
    mov eax, G           ; In register eax, store G
    call print_string    ; Print the string stored in eax
    mov eax, [countG]    ; In register eax, store the address of countG
    call print_int       ; Print the value stored in EAX
    call print_nl        ; Move to a new line
    popa                 ; Restores the general registers
    mov eax, 0           ; Set the register to 0
    leave                ; Reverse the actions of the enter instruction
    ret                  ; Transfers control to the return address on the stack
