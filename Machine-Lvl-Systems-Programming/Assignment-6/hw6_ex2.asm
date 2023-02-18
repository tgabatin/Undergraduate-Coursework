;; Exercise #2 [50 pts]
;; Reads binary sequence data from standard input, encoding itas a 4-byte value
;; A: 00, C: 01, T: 10, G: 11

%include "asm_io.inc"

segment .data
    ; Implementation Constraint: Cannot declare anything in the .data and .bss segment
segment .bss
    ; Implementation Constraint: Cannot declare anything in the .data and .bss segment
segment .text
    global asm_main 

asm_main:
    enter 0,0
    pusha

    mov edi, 0  ; Loop Variable to determine how many times to do a shift to check
    mov esi, 0  ; Counter for the GCTGAG
    mov edx, 0  ; Temporarily store EAX
    mov ebx, 0  ; Counter for the loop iterating 32 times
    mov ecx, 0  ; Set to 0
; Checks the 16-bit
loop_start:
    cmp ebx, 4  ; Checks to see whether 32 has been hit
    je second_loop ; If not jump to the next label
    call read_char  ; Reads the input taking in 8 bits
    cmp al, -1  ; If there's an end of the file then quit
    je end ; quit
    mov dl, al  ; Moves the lower parts of stuff in EAX into the lower DL
    shl edx, 8  ; Move up by a SINGLE (not 2) bytes, replacing 8 bits to the right by 0 next time we call read_char the next iterating bit is stored in DL
    inc ebx     ; Increment the counter
    jmp loop_start  ; jump back to the loop
; Checks the 12 bit 
second_loop:
    mov ebx, 0      
    cmp edi, 10 ; 10 * 20 = 20 (12 bits leftover in register) Compare the leftover bits
    je loop_start   ; If there are leftover bits, jump back to loop_start
    ; When we move Al to Dl, we use all of EDX
    mov eax, edx    ; Get the value stored in edx and put it into eax
    shl eax, cl     ; cl = 0 (Each letter is 2 bits)
    shr eax, 20     ; Isolate the remainding 10 bits by adding 0's to the left of it
    ; Can I do a dump_reg here to check if this is actually working
    cmp eax, 3507   ; GCTGAG patterm to compare the remaining 12 bits
    je counter_loop      ; jump to the counter label to increment the counter for the GCTGAG pattern
    ; If jumps to counter, it skips everything
    ; SKip to the next 12 bits without counting 
    add cl, 2       ; if doesn't jump to counter cl never increments
    inc edi         ; Increment the counter
    jmp second_loop ; Jump to the second portion of the loop

counter_loop:
    inc esi         ; Incrememnt the counter for the GCTGAG pattern
    add cl, 2       ; 0 -> 2 since each letter is represented by 2 bits, we shift to the next by 2
    inc edi         ; Loop Counter for next (When we hit 10, we've finished the comparison for the edx register)
    jmp second_loop        ; Move on to the 16 bit characters

end:
    mov eax, esi    ; Put the counter back into EAX to print
    call print_int  ; Print the variable inside of this 
    popa
    mov eax, 0
    leave
    ret
