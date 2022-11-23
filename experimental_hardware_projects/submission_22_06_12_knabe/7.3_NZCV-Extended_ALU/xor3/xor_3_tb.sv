module xor_3_tb;
    logic i_in0, i_in1, i_in2;
    logic o_result;

    xor_3 dut(i_in0, i_in1, i_in2, o_result);


    initial begin
        $dumpfile("dump.vcd");
        $dumpvars;
        
        i_in0 = 1'b0;
        i_in1 = 1'b0;
        i_in2 = 1'b0;
        #10;
        assert(o_result === !'b1);
        
        i_in0 = 1'b0;
        i_in1 = 1'b0;
        i_in2 = 1'b1;
        #10;
        assert(o_result === 1'b0);

        i_in0 = 1'b0;
        i_in1 = 1'b1;
        i_in2 = 1'b0;
        #10;
        assert(o_result === 1'b0);


        i_in0 = 1'b0;
        i_in1 = 1'b1;
        i_in2 = 1'b1;
        #10;
        assert(o_result === !'b1);

        i_in0 = 1'b1;
        i_in1 = 1'b0;
        i_in2 = 1'b0;
        #10;
        assert(o_result === 1'b0);


        i_in0 = 1'b1;
        i_in1 = 1'b0;
        i_in2 = 1'b1;
        #10;
        assert(o_result === !'b1);

        i_in0 = 1'b1;
        i_in1 = 1'b1;
        i_in2 = 1'b0;
        #10;
        assert(o_result === !'b1);

        i_in0 = 1'b1;
        i_in1 = 1'b1;
        i_in2 = 1'b1;
        #10;
        assert(o_result === 1'b0);


        $finish;
    end
endmodule