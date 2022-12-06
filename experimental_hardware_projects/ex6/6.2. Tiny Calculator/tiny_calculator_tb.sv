/**
  * Testbench of the tiny calculator.
  */
module tiny_calculator_tb;
    logic [7:0] SW;
    logic [6:0] HEX0;
    logic [6:0] HEX1;
    logic [6:0] HEX2;
    logic [6:0] HEX3;

    tiny_calculator dut(SW, HEX0, HEX1, HEX2, HEX3);

    initial begin
        $dumpfile("dump_tiny_calculator.vcd");
        $dumpvars;
        
        SW[3:0] = 4'b0000;
        SW[7:4] = 4'b0000;
        #10;
        assert( HEX0 === 7'b100_0000 );
        assert( HEX1 === 7'b100_0000 );
        assert( HEX2 === 7'b100_0000 );
        assert( HEX3 === 7'b100_0000 );

        SW[3:0] = 4'b0011;
        SW[7:4] = 4'b0010;
        #10;
        assert( HEX0 === 7'b011_0000 );
        assert( HEX1 === 7'b010_0100 );
        assert( HEX2 === 7'b001_0010 );
        assert( HEX3 === 7'b100_0000 );

        SW[3:0] = 4'b1110;
        SW[7:4] = 4'b0001;
        #10;
        assert( HEX0 === 7'b000_0110 );
        assert( HEX1 === 7'b111_1001 );
        assert( HEX2 === 7'b000_1110 );
        assert( HEX3 === 7'b100_0000 );
        
        SW[3:0] = 4'b1000;
        SW[7:4] = 4'b1000;
        #10;
        assert( HEX0 === 7'b000_0000 );
        assert( HEX1 === 7'b000_0000 );
        assert( HEX2 === 7'b100_0000 );
        assert( HEX3 === 7'b111_1001 );

        SW[3:0] = 4'b1111;
        SW[7:4] = 4'b1111;
        #10;
        assert( HEX0 === 7'b000_1110 );
        assert( HEX1 === 7'b000_1110 );
        assert( HEX2 === 7'b000_1110 );
        assert( HEX3 === 7'b111_1001 );

        $finish;
    end
endmodule
