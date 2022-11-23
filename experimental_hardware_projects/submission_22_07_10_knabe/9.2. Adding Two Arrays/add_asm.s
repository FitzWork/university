        .text
        .align 4
        .type   add_asm, %function
        .global add_asm
add_asm:
start_loop:
        cmp x0, #0
        sub x0, x0, #1
        b.eq end_loop

        ldr x4, [x1], #8
        ldr x5, [x2], #8
        add x6, x4, x5
        str x6, [x3], #8

        b start_loop

end_loop:
        ret
        .size   add_asm, (. - add_asm)
