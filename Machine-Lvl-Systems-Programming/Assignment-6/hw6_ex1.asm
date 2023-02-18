;; Exercise #1: A first program [20 pts]
;; A small program that reads in one integer from standard input and prints out its binary representation
;; Assume that the user always enters a valid 32-bit integer
;; Hint: Bitwise operations on binary data

%include "asm_io.inc"

segment .data
    ; Implementation constraight: Cannot declare anything in the .data and .bss segment
segment .bss
    ; Implementation constraight: Cannot declare anything in the .data and .bss segment
segment .text
    global asm_main

asm_main:
    enter 0,0
    pusha
    call read_int   ; EAX = 4 Byte Value = 32 Bit Binary Representation
    mov ebx, 0 ; incrementer
    mov edx, eax 
loop_start:
    cmp ebx, 32 ; Compare an interment to 32 = 32 bits
    je end  ; Compare until the ebx >= 32, jump to the end of the loop
    mov ecx, edx    ; swap
    and ecx, 2147483648  ; 2^31
    shr ecx, 31 ; Shift to get expected value 
    mov eax, ecx ; Set the new binary into eax
    call print_int  ; print the value in EAX
    shl edx, 1  ; shifts to the right by 1
    inc ebx ; Increment the counter
    jmp loop_start ; Loop back
end:
    call print_nl   ; Prints the new line
    popa  
    mov eax, 0
    leave
    ret