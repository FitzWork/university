module alu_nzcv_tb;
    logic [31:0] l_a, l_b, l_result;
    logic [1:0] l_alu_ctr;
    logic [3:0] l_nzcv;

    alu_nzcv #(32) dut(l_a, l_b, l_alu_ctr, l_result, l_nzcv);

    initial begin
        $dumpfile("dump.vcd");
        $dumpvars;

        l_a = 32'h0000_0000;
        l_b = 32'h0000_0000;
        l_alu_ctr = 2'b00;
        #10;
        assert(l_result === 32'h0000_0000);
        assert(l_nzcv === 4'b0100);

        l_a = 32'h0000_0000;
        l_b = 32'hffff_ffff;
        l_alu_ctr = 2'b00;
        #10;
        assert(l_result === 32'hffff_ffff);
        assert(l_nzcv === 4'b1000);

        l_a = 32'h0000_0001;
        l_b = 32'hffff_ffff;
        l_alu_ctr = 2'b00;
        #10;
        assert(l_result === 32'h0000_0000);
        assert(l_nzcv === 4'b0110);

        l_a = 32'h0000_ffff;
        l_b = 32'h0000_0001;
        l_alu_ctr = 2'b00;
        #10;
        assert(l_result === 32'h0001_0000);
        assert(l_nzcv === 4'b0000);

        l_a = 32'h0000_0000;
        l_b = 32'h0000_0000;
        l_alu_ctr = 2'b01;
        #10;
        assert(l_result === 32'h0000_0000);
        assert(l_nzcv === 4'b0110);

        l_a = 32'h0001_0000;
        l_b = 32'h0000_0001;
        l_alu_ctr = 2'b01;
        #10;
        assert(l_result === 32'h0000_ffff);
        assert(l_nzcv === 4'b0010);

        l_a = 32'hffff_ffff;
        l_b = 32'hffff_ffff;
        l_alu_ctr = 2'b10;
        #10;
        assert(l_result === 32'hffff_ffff);
        assert(l_nzcv === 4'b1000);

        l_a = 32'hffff_ffff;
        l_b = 32'h7743_3477;
        l_alu_ctr = 2'b10;
        #10;
        assert(l_result === 32'h7743_3477);
        assert(l_nzcv === 4'b0000);

        l_a = 32'h0000_0000;
        l_b = 32'hffff_ffff;
        l_alu_ctr = 2'b10;
        #10;
        assert(l_result === 32'h0000_0000);
        assert(l_nzcv === 4'b0100);

        l_a = 32'h0000_0000;
        l_b = 32'hffff_ffff;
        l_alu_ctr = 2'b11;
        #10;
        assert(l_result === 32'hffff_ffff);
        assert(l_nzcv === 4'b1000);
        
        $finish;
    end
endmodule
