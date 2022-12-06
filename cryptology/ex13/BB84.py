import random


def protocol():
    n = 160
    # Alice generate a and a' bits (n)
    a = gen_bits(n)
    a_ = gen_bits(n)
    # but why!?
    k = random.randrange(5, 10)

    # encode this bits
    aq = encode(a, a_)
    # and send them to bob

    # Bob generate b' bits (n)
    b_ = gen_bits(n)
    # measure a_
    aqb = measure(b_, aq)

    # compare a' and b'
    common = get_common(a_, b_)


def gen_bits(n):
    while True:
        bits = random.getrandbits(n)
        if bits.bit_length() == n:
            array = [0] * n
            for i in range(len(array)):
                if bits & 1:
                    array[n - 1 - i] = 1
                else:
                    array[n - 1 - i] = 0
                bits >>= 1
            return array


def encode(x, y):
    qb = []
    for i in x:
        qb.append([x[i], y[i]])
    return qb


def measure(x, y):
    for i in x:
        if x[i] == y[i][1]:
            x[i] = y[i][0]
        else:
            x[i] = random.getrandbits(1)
    return x


def get_common(x, y):
    i_list = []
    for i in x:
        if x[i] == y[i]:
            i_list.append(i)
    return i_list


def get_bits_at(x, y):
    for i in range(len(y)):
        y[i] = x[y[i]]
    return y


def remove_bits_at(x, y):
    for i in y:
        x.remove(y[len(y) - i + 1])
    return x


protocol()
