import numpy


def main():
    message = "".encode()
    message_array = bytearray(message)
    m = int.from_bytes(bytes('', "ascii"), byteorder='big')
    h0 = numpy.uint32(0x67452301)
    h1 = numpy.uint32(0xefcdab89)
    h2 = numpy.uint32(0x98badcfe)
    h3 = numpy.uint32(0x10325476)
    h4 = numpy.uint32(0xc3d2e1f0)
    ml = numpy.uint64(m.bit_length())

    ret = pre_processing(m, ml)
    print(ret.real)
    print(hex(int(blocking(ret, h0, h1, h2, h3, h4))))


def pre_processing(m, ml):
    m1 = hex(m + 0x80)
    tmp = ((len(m1) - 2) * 8) % 512
    if tmp <= 448:
        return m + ((448 - tmp) // 8) + ml
    else:
        return m + ((512 - tmp + 448) // 8) + ml


def blocking(block, h0, h1, h2, h3, h4):
    w = [numpy.uint32()] * 85

    fix = int(block).bit_length() // 32
    for i in range(fix):
        tmp = block >> 32
        tmp <<= 32
        w[i] = bin(block - tmp)
        block = tmp >> 32

    for i in range(65):
        w.append(rotl(w[i + 16 - 3] ^ w[i + 16 - 8] ^ w[i + 16 - 14] ^ w[i + 16 - 16], 1))

    a = h0
    b = h1
    c = h2
    d = h3
    e = h4
    f = 0
    k = 0

    for i in range(80):
        if 0 <= i <= 19:
            f = (b and c) or (b.inv() and c)
            k = 0x5a827999
        elif 20 <= i <= 39:
            f = b ^ c ^ d
            k = 0x6ed9eba1
        elif 40 <= i <= 59:
            f = (b and c) or (b and d) or (c and d)
            k = 0x8f1bbcdc
        elif 60 <= i <= 79:
            f = b ^ c ^ d
            k = 0xca62c1d6

        tmp = f + e + k + w[i] + rotl(a, 5)
        e = d
        d = c
        c = rotl(b, 30)
        b = a
        a = tmp

    h0 += a
    h1 += b
    h2 += c
    h3 += d
    h4 += e

    return str(h0) + str(h1) + str(h2) + str(h3) + str(h4)


def rotl(num, bits):
    bit = num & (1 << (bits-1))
    num <<= 1
    if bit:
        num |= 1
    num &= (2**bits-1)

    return num


main()
