       .text
       .align 4
       .type   copy_asm, %function
       .global copy_asm
copy_asm:
       ldr x2, [x0]
       str x2, [x1]

       ldr x2, [x0, #8]
       str x2, [x1, #8]

       ldr x2, [x0, #16]
       str x2, [x1, #16]

       ldr x2, [x0, #24]
       str x2, [x1, #24]

       ldr x2, [x0, #32]
       str x2, [x1, #32]

       ldr x2, [x0, #40]
       str x2, [x1, #40]

       ldr x2, [x0, #48]
       str x2, [x1, #48]

       ret
       .size   copy_asm, (. - copy_asm)



	.text
       .align 4
       .type   copy_asm_loop, %function
       .global copy_asm_loop
copy_asm_loop:
	mov x3, #0

start_loop:
	ldr x2, [x0], #8
       str x2, [x1], #8

	add x3, x3, #1
	cmp x3, #7
	b.ne start_loop

	ret
       .size   copy_asm_loop, (. - copy_asm_loop)
