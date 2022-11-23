/**
 * Testbench alu.
 */
module alu_tb;
    logic [9:0] SW, LEDR;
    logic [6:0] HEX0, HEX1, HEX2;

    alu_de10_lite #(4) dut( SW,
                  LEDR,
                  HEX0,
                  HEX1,
                  HEX2 );
    initial begin
        $dumpfile("dump.vcd");
        $dumpvars;

        SW[3:0] = 4'b0011;
        SW[7:4] = 4'b0010;
        SW[9:8] = 2'b00;
        #10;
        assert( HEX0 === 7'b011_0000 );
        assert( HEX1 === 7'b010_0100 );
        assert( HEX2 === 7'b001_0010 );
        assert( LEDR[0] === 1'b0 );
        assert( LEDR[1] === 1'b0 );
        assert( LEDR[7] === 1'b0 );

        SW[3:0] = 4'b1011;
        SW[7:4] = 4'b1010;
        SW[9:8] = 2'b01;
        #10;
        assert( HEX0 === 7'b000_0011 );
        assert( HEX1 === 7'b000_1000 );
        assert( HEX2 === 7'b111_1001 );
        assert( LEDR[0] === 1'b1 );
        assert( LEDR[1] === 1'b0 );
        assert( LEDR[7] === 1'b1 );

        SW[3:0] = 4'b1001;
        SW[7:4] = 4'b1110;
        SW[9:8] = 2'b10;
        #10;
        assert( HEX0 === 7'b001_0000 );
        assert( HEX1 === 7'b000_0110 );
        assert( HEX2 === 7'b000_0000 );
        assert( LEDR[0] === 1'b0 );
        assert( LEDR[1] === 1'b1 );
        assert( LEDR[7] === 1'b1 );

        SW[3:0] = 4'b1001;
        SW[7:4] = 4'b1001;
        SW[9:8] = 2'b11;
        #10;
        assert( HEX0 === 7'b001_0000 );
        assert( HEX1 === 7'b001_0000 );
        assert( HEX2 === 7'b001_0000 );
        assert( LEDR[0] === 1'b1 );
        assert( LEDR[1] === 1'b1 );
        assert( LEDR[7] === 1'b1 );

        $finish;
    end
endmodule