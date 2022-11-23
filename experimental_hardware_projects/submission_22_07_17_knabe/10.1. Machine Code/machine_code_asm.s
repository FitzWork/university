        .text
        .align 4
        .type   machine_code_asm_0, %function
        .global machine_code_asm_0
machine_code_asm_0:
        eor x0, x0, x0
        eor x1, x1, x1
        eor x2, x2, x2

        adds x0, x0, #5

my_loop:
        adds x1, x1, #3
        adds x2, x2, #7

        subs x0, x0, #1
        b.ne my_loop

        adds x0, x1, x2

        ret
        .size   machine_code_asm_0, (. - machine_code_asm_0)



        .text
        .align 4
        .type   machine_code_asm_1, %function
        .global machine_code_asm_1
machine_code_asm_1:
        .inst 0xca000000
        .inst 0xca010021
        .inst 0xca020042

        .inst 0xb1001400

loop:
        .inst 0xb1000c21
        .inst 0xb1001c42

        .inst 0xf1000400
        .inst 0x54ffffa1

        .inst 0xab020020

        .inst 0xd65f03c0
        .size   machine_code_asm_1, (. - machine_code_asm_1)
